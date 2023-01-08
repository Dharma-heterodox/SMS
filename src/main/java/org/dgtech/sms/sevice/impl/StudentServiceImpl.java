package org.dgtech.sms.sevice.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dgtech.sms.entity.Organization;
import org.dgtech.sms.entity.User;
import org.dgtech.sms.entity.UserRequest;
import org.dgtech.sms.entity.UserRequestErrors;
import org.dgtech.sms.entity.student.Student;
import org.dgtech.sms.entity.student.StudentParent;
import org.dgtech.sms.model.GradeDto;
import org.dgtech.sms.model.ParentDto;
import org.dgtech.sms.model.SectionDto;
import org.dgtech.sms.model.StudentDto;
import org.dgtech.sms.repo.StudentRepo;
import org.dgtech.sms.repo.UserRequestRepo;
import org.dgtech.sms.repo.grade.GradeRepo;
import org.dgtech.sms.repo.section.SectionRepo;
import org.dgtech.sms.sevice.FileUploads;
import org.dgtech.sms.sevice.GradeService;
import org.dgtech.sms.sevice.OrganizationService;
import org.dgtech.sms.sevice.ParentService;
import org.dgtech.sms.sevice.SectionService;
import org.dgtech.sms.sevice.StudentParentService;
import org.dgtech.sms.sevice.StudentService;
import org.dgtech.sms.sevice.UserService;
import org.dgtech.sms.util.Constant;
import org.dgtech.sms.util.ErrorCodeV;
import org.dgtech.sms.util.StringUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StudentServiceImpl implements StudentService,FileUploads {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private GradeRepo gradeRepo;
	
	@Autowired
	private SectionRepo sectionRepo;

	@Autowired
	private ParentService parentService;

	@Autowired
	private StudentParentService studentParentService;

	@Autowired
	private OrganizationService orgservice;

	@Autowired
	private UserService userService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private UserRequestRepo userRequestRepo;

	@Override
	public StudentDto addStudent(StudentDto studentDto) {
		Student student = modelMapper.map(studentDto, Student.class);
		student.setActive(true);
		student = studentRepo.save(student);
		return modelMapper.map(student, StudentDto.class);
	}

	public StudentDto findByAdmissionNo(Long schoolId, String admissionNo) {
		Student student = studentRepo.findByAdmissionNo(schoolId, admissionNo);
		if (student == null)
			return null;
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto createStudent(Long schoolId, StudentDto studentDto) throws Exception {
		
		return null;
	}

	@Override
	public List<StudentDto> getAllStudentBySchoolId(Long schoolId) {
		List<Student> students = studentRepo.findAllBySchoolId(schoolId);
		if (students == null)
			return null;
		return students.stream().map(a -> modelMapper.map(a, StudentDto.class)).collect(Collectors.toList());
	}

	private User createStudentAccount(Student student) {
		User user = new User();
		if (student.getAdmissionNo() != null) {
			user.setUserName(student.getAdmissionNo());
		} else {
			user.setUserName(student.getMobile());
		}

		String dob = student.getDob().toString().replaceAll("-", "");
		System.out.println("username === " + user.getUserName() + "       password ===" + dob);
		user.setPassword(dob);
		user.setEmail(student.getEmail());
		user.setMobile(student.getMobile());
		user.setActive(true);
		if (student.getSchoolId() != null) {
//			Organization org = orgservice.getOrganization(student.getSchoolId());
//			Set<Organization> orgs = new HashSet<Organization>();
//			orgs.add(org);
//			user.set;
		}
		userService.saveStudentAccount(user);
		return user;
	}

	@Override
	public StudentDto getStudentById(Long studentId) {
		return modelMapper.map(studentRepo.getOne(studentId), StudentDto.class);
	}

	@Override
	public List<StudentDto> getStudentsByParentId(Long parentId) throws Exception {
		List<Student> students = studentRepo.findAllStudentsByParentId(parentId);
		if (students == null)
			return null;
		return getStudentDto(students);
	}

//	@Override
//	public boolean upload(Long schoolId, MultipartFile file) {
//		Set<StudentDto> students;
//		try {
//			students = getStudentsFromfile(file);
//			if (!CollectionUtils.isEmpty(students)) {
//				for (StudentDto studentDto : students) {
//					try {
//						createStudent(schoolId, studentDto);
//					} catch (Exception e) {
//						log.error(e.getMessage());
//					}
//				}
//			}
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return true;
//	}
	
	@Override
	@Transactional
	public boolean upload(Long schoolId, MultipartFile file) {
		List<UserRequest> students;
		try {
			students = getStudentsFromfile(schoolId,file);
			userRequestRepo.saveAll(students);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	private List<UserRequest> getStudentsFromfile(Long schoolId,MultipartFile file) throws Exception {
		Workbook workbook = null;
		List<UserRequest> requestList = new ArrayList<UserRequest>();
		List<String> sectionList=null;
		List<String> gradeList=null;
//		Set<String> mobiles=null;
//		Set<String> emails=null;
		Set<Integer> studIds=null;
		try {
			workbook = new XSSFWorkbook(file.getInputStream());
			gradeList = gradeRepo.getGradeList(schoolId);
			sectionList = sectionRepo.getGradeSectionList(schoolId);
//			mobiles=userService.getMobileNo();
			studIds=studentRepo.getAllStudentsId(schoolId);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			int length = datatypeSheet.getLastRowNum();
			DataFormatter objDefaultFormat = new DataFormatter();
			FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
			UserRequest request = null;
			rowLevel:
			for (int i = 1; i < length; i++) {
				try {
					Row row = datatypeSheet.getRow(i);
					boolean studIdFound=false;
					if (row == null)
						break;
					request = new UserRequest();
					cellLevel:
					for (int j = 0; j < row.getLastCellNum(); j++) {
						String cellValueStr =null;
						try {
							Cell currentCell = row.getCell(j);
							objFormulaEvaluator.evaluate(currentCell); // This will evaluate the cell, And any type of cell
																		// will return string value
							cellValueStr = objDefaultFormat.formatCellValue(currentCell, objFormulaEvaluator).trim();
							if(!isEmpty(cellValueStr) && "END".equals(cellValueStr))
			        	    	break rowLevel;
							switch (j) {
							case 0:
								if (isEmpty(cellValueStr)) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.STUDID_NOTEMPTY));
								}else if(studIds.contains(Integer.valueOf(cellValueStr))) {
									studIdFound=true;
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.STUDID_FOUND));
								}
								request.setStudId(Integer.valueOf(cellValueStr));
								break;
							case 1:
								request.setExamNo(cellValueStr);
								break;
							case 2:
								String name = StringUtil.fullCapitalize(cellValueStr);
								request.setFirstName(name);
								if (isEmpty(request.getFirstName())) {
									request.addUserRequestError(new UserRequestErrors("Student " + ErrorCodeV.NAME_NOTEMPTY));
								} else if (!request.getFirstName().matches(Constant.NAME_REGEX)) {
									request.addUserRequestError(new UserRequestErrors("Student " + ErrorCodeV.NAME_REGEX));
								}
								break;
							case 3:
								String lastName = StringUtil.fullCapitalize(cellValueStr);
								request.setLastName(lastName);
								if (isEmpty(request.getLastName())) {
									request.addUserRequestError(new UserRequestErrors("Student " + ErrorCodeV.NAME_NOTEMPTY));
								} else if (!request.getLastName().matches(Constant.NAME_REGEX)) {
									request.addUserRequestError(new UserRequestErrors("Student " + ErrorCodeV.NAME_REGEX));
								}
								break;
							case 4:
								request.setGender(StringUtil.fullCapitalize(cellValueStr));
								if (isEmpty(request.getGender())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.GENDER_NOTEMPTY));
								} else if (!request.getGender().matches(Constant.GENDER_REGEX)) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.GENDER_REGEX));
								}
								break;
							case 5:
								if (isEmpty(cellValueStr)) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.DOB_NOTEMPTY));
								}else {
									request.setDob(cellValueStr);
								}
								break;
							case 6:
								request.setMotherName(StringUtil.fullCapitalize(cellValueStr));
								if (isEmpty(request.getMotherName())) {
									request.addUserRequestError(new UserRequestErrors("Mother " + ErrorCodeV.NAME_NOTEMPTY));
								} else if (!request.getMotherName().matches(Constant.NAME_REGEX)) {
									request.addUserRequestError(new UserRequestErrors("Mother " + ErrorCodeV.NAME_REGEX));
								}
								break;
							case 7:
								request.setMotheraadhaarNo(cellValueStr);
								if (isEmpty(request.getMotheraadhaarNo())) {
									request.addUserRequestError(new UserRequestErrors("Mother aadhar " + ErrorCodeV.AADHAR_NOTEMPTY));
								} else if (!request.getMotheraadhaarNo().matches(Constant.AADHAR_REGEX)) {
									request.addUserRequestError(new UserRequestErrors("Mother aadhar" + ErrorCodeV.AADHAR_REGEX));
								}
								break;
							case 8:
								request.setFatherName(StringUtil.fullCapitalize(cellValueStr));
								if (isEmpty(request.getFatherName())) {
									request.addUserRequestError(new UserRequestErrors("Father " + ErrorCodeV.NAME_NOTEMPTY));
								} else if (!request.getFatherName().matches(Constant.NAME_REGEX)) {
									request.addUserRequestError(new UserRequestErrors("Father " + ErrorCodeV.NAME_REGEX));
								}
								break;
							case 9:
								request.setFatheraadhaarNo(cellValueStr);
								if (isEmpty(request.getFatheraadhaarNo())) {
									request.addUserRequestError(new UserRequestErrors("Father aadhar " + ErrorCodeV.AADHAR_NOTEMPTY));
								} else if (!request.getFatheraadhaarNo().matches(Constant.AADHAR_REGEX)) {
									request.addUserRequestError(new UserRequestErrors("Father aadhar" + ErrorCodeV.AADHAR_REGEX));
								}
								break;	
							case 10:
								request.setCasteCat(cellValueStr);
								if (isEmpty(request.getCasteCat())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.CASTCAT_NOTEMPTY));
								} else if (!request.getCasteCat().matches(Constant.CASTECAT_REGEX)) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.CASTCAT_REGEX));
								}
								break;
							case 11:
								request.setCaste(cellValueStr);
								if (isEmpty(request.getCaste())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.CAST_NOTEMPTY));
								}
								break;
							case 12:
								request.setReligion(cellValueStr);
								if (isEmpty(request.getReligion())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.RELIGION_NOTEMPTY));
								}
								break;
							case 13:
								request.setAddress(cellValueStr);
								if (isEmpty(request.getAddress())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.ADDRESS_NOTEMPTY));
								}
								break;
							case 14:
								request.setMobile(cellValueStr);
								if (isEmpty(request.getMobile())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.MOBILE_NOTEMPTY));
								} else if (!request.getMobile().matches(Constant.MOBILE_REGEX) ) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.MOBILE_REGEX));
								}
//								else if(studIdFound && mobiles.contains(request.getMobile())) {
//									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.MOBILE_FOUND));
//								}
								break;
							case 15:
								request.setAlternateMobile(cellValueStr);
								if (!isEmpty(request.getAlternateMobile())
										&& !request.getAlternateMobile().matches(Constant.MOBILE_REGEX)) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.MOBILE_REGEX));
								}
								break;
							case 16:
								request.setLandLine(cellValueStr);
								if (!isEmpty(request.getLandLine())
										&& !request.getLandLine().matches(Constant.LANDLINE_REGEX)) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.LANDLINE_REGEX));
								}
								break;
							case 17:
								request.setGrade(cellValueStr);
								if (isEmpty(request.getGrade())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.STD_NOTEMPTY));
								} else if (!gradeList.contains(request.getGrade())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.STD_REGEX));
								}
								break;
							case 18:
								if (isEmpty(cellValueStr)) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.SECTION_NOTEMPTY));
								}else if(!sectionList.contains(cellValueStr)){
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.SECTION_NOTMATCHED));
//									request.setSectionId(grSection.get(request.getGrade()+"-"+request.getSection()));
								}
								request.setSection(cellValueStr);
								break;
							case 19:
								request.setRTE(cellValueStr);
								if (isEmpty(request.getRTE())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.RTE_NOTEMPTY));
								}
								break;
							case 20:
								request.setAadhaarNo(cellValueStr);
								if (isEmpty(request.getAadhaarNo())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.AADHAR_NOTEMPTY));
								} else if (!request.getAadhaarNo().matches(Constant.AADHAR_REGEX)) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.AADHAR_REGEX));
								}
								break;
							case 21:
								request.setEnrollmentNo(cellValueStr);
								break;
							case 22:
								request.setEmisno(cellValueStr);
								if (isEmpty(request.getEmisno())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.EMINO_NOTEMPTY));
								}
								break;
							case 23:
								request.setEmailId(cellValueStr);
								if (isEmpty(request.getEmailId())) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.EMAIL_NOTEMPTY));
								} else if (!request.getEmailId().matches(Constant.EMAIL_REGEX)) {
									request.addUserRequestError(new UserRequestErrors(ErrorCodeV.EMAIL_NOTVALID));
								}
								break;
							default:
								break cellLevel;
							}
						}catch(Exception jex) {
							jex.getMessage();
							log.error("Row :"+i+" Cell :" + j + " : "+cellValueStr+" - " + jex.getMessage());
						}
					}
					if (request.getErrors().size() > 0) {
						request.setRequestStatus(Constant.REQUEST_FAILED);
					} else {
						request.setRequestStatus(Constant.REQUEST_SUCCESS);
					}
					request.setSchoolId(schoolId);
					request.setRequestedType(Constant.REQUEST_STUDENT);
					requestList.add(request);
					
				} catch (Exception ex) {
					log.error("Row :" + i + " : " + ex.getMessage());
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return requestList;
	}

	private List<StudentDto> getStudentDto(List<Student> student) throws Exception {
		List<StudentDto> dtoList = new ArrayList<StudentDto>();
		student.forEach(h -> {
			StudentDto dto = new StudentDto();
			dto.setAadhaarNo(h.getAadhaarNo());
			dto.setActive(true);
			dto.setAddress(h.getAddress());
			dto.setAdmissionNo(h.getAdmissionNo());
			dto.setBloodGroup(h.getBloodGroup());
//			dto.setBoardId(h.getBoardId());
			dto.setDisplayName(h.getDisplayName());
			dto.setDob(h.getDob());
			dto.setEmail(h.getEmail());
//			dto.setEmergencyContactNo(h.getEmergencyContactNo());
			dto.setFatherName(h.getFatherName());
			dto.setFirstName(h.getFirstName());
			dto.setGender(h.getGender());
//			dto.setGradeId(h.getGradeId());
			dto.setGrade(h.getGrade());
			dto.setId(h.getId());
			dto.setIdNumber(h.getIdNumber());
			dto.setLastName(h.getLastName());
//			dto.setMediumId(h.getMediumId());
			dto.setMobile(h.getMobile());
			dto.setMotherName(h.getMotherName());
			dto.setRollNo(h.getRollNo());
			dto.setSchoolId(h.getSchoolId());
			dto.setSection(h.getSection());
//			dto.setSectionId(h.getSectionId());
			dto.setChildOrder(h.getChildOrder());
			dtoList.add(dto);
		});
		return dtoList;

	}
	


}
