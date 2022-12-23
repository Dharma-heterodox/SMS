package org.dgtech.sms.sevice.impl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.dgtech.sms.entity.Employee;
import org.dgtech.sms.entity.Parent;
import org.dgtech.sms.entity.Role;
import org.dgtech.sms.entity.User;
import org.dgtech.sms.entity.UserRequest;
import org.dgtech.sms.entity.employee.EmployeeRequest;
import org.dgtech.sms.entity.student.Student;
import org.dgtech.sms.entity.student.StudentSectionRecord;
import org.dgtech.sms.repo.EmployeeRepo;
import org.dgtech.sms.repo.EmployeeRequestRepo;
import org.dgtech.sms.repo.ParentRepo;
import org.dgtech.sms.repo.RoleRepository;
import org.dgtech.sms.repo.UserRepository;
import org.dgtech.sms.repo.UserRequestRepo;
import org.dgtech.sms.sevice.UserRequestService;
import org.dgtech.sms.sevice.UserService;
import org.dgtech.sms.util.Constant;
import org.dgtech.sms.util.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRequestServiceImpl implements UserRequestService{
	
	@Autowired
	private UserRequestRepo userRequestRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ParentRepo parentRepo;
	
	@Autowired
	private EmployeeRequestRepo employeeReqRepo;
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private Logger logger = LoggerFactory.getLogger(UserRequestServiceImpl.class);

	@Override
	@Transactional
	public int createStudentParentAcc(List<Long> ids,Long schoolId,String createdBy) throws Exception {
		// TODO Auto-generated method stub
		List<UserRequest> requestedUsers=null;
		Map<String,User> userMap=null;
		List<Parent> parentList=null;
		int result=0;
		logger.debug("Create student parent acc ::");
		try {
			if(createdBy==null) {
				createdBy="Admin";
			}
			requestedUsers=userRequestRepo.getStudentParentReq(schoolId);
//			requestedUsers=userRequestRepo.getStudentParentReq(ids);
			userMap=getStuUserObject(requestedUsers,createdBy,userRepo);
			parentList=getParents(userMap, requestedUsers,parentRepo);
			if(parentList.size()>0) {
				result=userRequestRepo.updateSchoolReqStatus(Constant.REQUEST_APPROVED,schoolId);
			}
		}catch(Exception ex) {
			logger.debug("Exception in createStudentParentAcc");
			logger.error("createStudentParentAcc Exception :: "+ex.getMessage());
			throw new Exception(ex);
		}
		return result;
	}
	
	private Map<String,User> getStuUserObject(List<UserRequest> userReqList,String createdBy,UserRepository userRepo) throws Exception{
		Map<String,User> userMap = new HashMap<String,User>();
		Set<Role> role=getParentRole();
		try {
			userReqList.forEach(h -> {
				Integer oldUser=userRepo.getByEmail(h.getEmailId());
				if(oldUser!=null && oldUser==0) {
					User user=new User();
					user.setActive(true);
					user.setContactNo(h.getMobile());
					user.setEmergencyContactNo(h.getAlternateMobile());
					user.setFirstName(h.getMotherName());
					user.setMobile(h.getMobile());
					user.setPassword(bCryptPasswordEncoder.encode(Constant.DEFAULT_PASSWORD));
					user.setRoles(role);
					user.setUserName(h.getMotherName());
					user.setUserType("");
					user.setLastName(h.getFatherName());
					user.setEmail(h.getEmailId());
//					user.setLoginId(h.getStudId());
					user.setLoginId(h.getEmailId());
					user.setCreatedBy(createdBy);
					user.setCreatedTime(LocalDateTime.now());
					userMap.put(h.getEmailId(), userRepo.saveAndFlush(user)) ;
				}
				
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			userRepo.flush();
		}
		return userMap;
		
	}
//	Save user object in map , send to this method and process the remaining
	private List<Parent> getParents(Map<String,User> users,List<UserRequest> userReqList,ParentRepo repo)throws Exception{
		List<Parent> parents=new ArrayList<Parent>();
		try {
			for(int i=0;i<userReqList.size();i++) {
				UserRequest req=(UserRequest)userReqList.get(i);
				try {
					User user=users.get(req.getEmailId());
					Parent oldParent=repo.findByEmail(req.getEmailId());
					if(oldParent==null) {//First Child
						Parent parent=new Parent();
						Student student=new Student();
						StudentSectionRecord sectionRecord=new StudentSectionRecord();
						parent.setFatherAadhaarNo(req.getFatheraadhaarNo());
						parent.setMotheraadhaarNo(req.getMotheraadhaarNo());
						parent.setActive(true);
						parent.setAlternateMobile(req.getAlternateMobile());
						parent.setCaste(req.getCaste());
						parent.setCasteCat(req.getCasteCat());
						parent.setDisplayName(req.getMotherName());
//						parent.setDob(req.getDob());
						parent.setGender("FEMALE");
						parent.setLandLine(req.getLandLine());
						parent.setMobile(user.getMobile());
						parent.setRelationship("MOTHER");
						parent.setReligion(req.getReligion());
//						parent.setSpouseName(req.getFatherName());
						parent.setUserId(user.getUserId());
						parent.setAddress(req.getAddress());
						parent.setPinCode(req.getPincode());
						parent.setCreatedBy(user.getCreatedBy());
						parent.setCreatedTime(LocalDateTime.now());
						parent.setEmail(req.getEmailId());
						parent.setFatherName(req.getFatherName());
						parent.setMotherName(req.getMotherName());
						student.setAadhaarNo(req.getAadhaarNo());
						student.setAddress(req.getAddress());
						student.setAdmissionNo(req.getAdmissionNo());
						student.setCaste(req.getCaste());
						student.setCasteCat(req.getCasteCat());
						student.setDisplayName(req.getFirstName()+" "+req.getLastName());
//						student.setDob(req.getDob());
						student.setEmergencyContactNo(req.getAlternateMobile());
						student.setEmisno(req.getEmisno());
						student.setExamNo(req.getExamNo());
						student.setFatherName(req.getFatherName());
						student.setFirstName(req.getFirstName());
						student.setLastName(req.getLastName());
						student.setGender(req.getGender());
						student.setGrade(req.getGrade());
						student.setPinCode(req.getPincode());
						student.setLocality(req.getLocality());
//						student.setGradeId(req.getGradeId());
						student.setLandLine(req.getLandLine());
						student.setMobile(req.getMobile());
						student.setReligion(req.getReligion());
						student.setRollNo(req.getRollNo());
						student.setRTE(req.getRTE());
						student.setSchoolId(req.getSchoolId());
						student.setSection(req.getSection());
						student.setCreatedBy(user.getCreatedBy());
						student.setCreatedTime(LocalDateTime.now());
//						student.setSectionId(req.getSectionId());
						student.setStudId(req.getStudId());
						sectionRecord.setAcademicYear(Constant.currentAcademicYear);
//						sectionRecord.setGradeId(req.getGradeId());
						sectionRecord.setGrade(req.getGrade());
						sectionRecord.setSection(req.getSection());
//						sectionRecord.setSectionId(req.getSectionId());
						sectionRecord.addStudent(student);
						parent.addChild(student);
						parents.add(repo.saveAndFlush(parent));	
					}else {//Second Child
						Student student=new Student();
						StudentSectionRecord sectionRecord=new StudentSectionRecord();
						student.setAadhaarNo(req.getAadhaarNo());
						student.setAddress(req.getAddress());
						student.setAdmissionNo(req.getAdmissionNo());
						student.setCaste(req.getCaste());
						student.setCasteCat(req.getCasteCat());
						student.setDisplayName(req.getFirstName()+" "+req.getLastName());
//						student.setDob(req.getDob());
						student.setEmergencyContactNo(req.getAlternateMobile());
						student.setEmisno(req.getEmisno());
						student.setExamNo(req.getExamNo());
						student.setFatherName(req.getFatherName());
						student.setFirstName(req.getFirstName());
						student.setLastName(req.getLastName());
						student.setGender(req.getGender());
						student.setGrade(req.getGrade());
						student.setPinCode(req.getPincode());
						student.setLocality(req.getLocality());
//						student.setGradeId(req.getGradeId());
						student.setLandLine(req.getLandLine());
						student.setMobile(req.getMobile());
						student.setReligion(req.getReligion());
						student.setRollNo(req.getRollNo());
						student.setRTE(req.getRTE());
						student.setSchoolId(req.getSchoolId());
						student.setSection(req.getSection());
//						student.setSectionId(req.getSectionId());
						student.setStudId(req.getStudId());
						student.setCreatedBy(user.getCreatedBy());
						student.setCreatedTime(LocalDateTime.now());
						sectionRecord.setAcademicYear(Constant.currentAcademicYear);
//						sectionRecord.setGradeId(req.getGradeId());
						sectionRecord.setGrade(req.getGrade());
						sectionRecord.setSection(req.getSection());
//						sectionRecord.setSectionId(req.getSectionId());
						sectionRecord.addStudent(student);
						oldParent.addChild(student);
						parents.add(repo.saveAndFlush(oldParent));
					}
				}catch(ConstraintViolationException | DataIntegrityViolationException dive) {
					
				}
				}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return parents;
	}
	
	private Set<Role> getParentRole(){
		Set<Role> role=new HashSet<Role>();
		role.add(roleRepository.findByRole(Roles.PARENT.name()));
		return role;
	}
	
	@Override
	@Transactional
	public int createEmployeeAcc(List<Long> ids,Long schoolId,String createdBy) throws Exception {
		// TODO Auto-generated method stub
		List<EmployeeRequest> employeeRequest=null;
		List<User> userList=null;
		List<Employee> empList=null;
		int result=0;
		logger.debug("Create Employee acc ::");
		try {
//			employeeRequest=employeeReqRepo.getEmployeeReq(schoolId);
			employeeRequest=employeeReqRepo.getEmployeeReq(ids);
			userList=userRepo.saveAll(getEmpUserObject(employeeRequest,createdBy));
			empList=empRepo.saveAll(getEmployeeList(employeeRequest,userList ));
			if(empList.size()>0) {
				result=employeeReqRepo.updateReqStatus(Constant.REQUEST_APPROVED,ids);
			}
		}catch(Exception ex) {
			logger.debug("Exception in createEmployeeAcc");
			logger.error("createEmployeeAcc Exception :: "+ex.getMessage());
			throw new Exception(ex);
		}
		return result;
	}
	
	private List<User> getEmpUserObject(List<EmployeeRequest> empReqList,String createdBy) throws Exception{
		List<User> userList=new ArrayList<User>();
		Map<String,Role> roleMap=getEmployeeRole();
		try {
			empReqList.forEach(h -> {
				User user=new User();
				user.setActive(true);
				user.setContactNo(h.getMobile());
				user.setEmergencyContactNo(h.getAlternateMobile());
				user.setFirstName(h.getFirstName());
				user.setMobile(h.getMobile());
				user.setPassword(bCryptPasswordEncoder.encode(Constant.DEFAULT_PASSWORD));
				user.setRoles(getRoleEntity(h.getTypeOrder(),roleMap));
				user.setUserName(h.getFirstName());
				user.setUserType("");
				user.setLastName(h.getLastName());
//				user.setLoginId(h.getEmployeeId());
				user.setLoginId(h.getEmailId());
				user.setEmail(h.getEmailId());
				user.setCreatedBy(createdBy);
				user.setCreatedTime(LocalDateTime.now());
				userList.add(user);
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return userList;
		
	}
	
	private Map<String,Role> getEmployeeRole(){
		List<Role> role=roleRepository.findAll();
		Map<String,Role> roleMap=new HashMap<String,Role>();
		role.forEach(h -> {
			roleMap.put(h.getRole(), h);
		});
		return roleMap;
	}
//	private Set<Role> getCoordinatorRole(){
//		Set<Role> role=new HashSet<Role>();
//		role.add(roleRepository.findByRole(Roles.COORDINATOR.name()));
//		return role;
//	}
	
	private List<Employee> getEmployeeList(List<EmployeeRequest> employeeRequest,List<User> userList)throws Exception{
		List<Employee> employees=new ArrayList<Employee>();
		try {
			for(int i=0;i<employeeRequest.size();i++) {
				Employee emp=new Employee();
				EmployeeRequest req=(EmployeeRequest)employeeRequest.get(i);
				User user=(User)userList.get(i);
				emp.setAadhaarNo(req.getAadhaarNo());
				emp.setActive(true);
				emp.setAddress(req.getAddressOne());
				emp.setAlternateMobile(req.getAlternateMobile());
				emp.setCategory(getRoleName(req.getTypeOrder()).name());
				emp.setDateOfJoin(req.getDoj());
				emp.setDisplayName(req.getFirstName()+" "+req.getLastName());
				emp.setDob(req.getDob());
				emp.setGender(req.getGender());
				emp.setMobile(req.getMobile());
				emp.setQualification(req.getQualification());
				emp.setSchoolId(req.getSchoolId());
				emp.setEmployeeCode(req.getEmployeeId());
				emp.setFirstName(req.getFirstName());
				emp.setLastName(req.getLastName());
				emp.setPinCode(req.getPincode());
//				emp.setSubCategory(0);
				emp.setUserId(user.getUserId());
				employees.add(emp);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return employees;
	}
	
	private Roles getRoleName(int typeOrder) {
		switch (typeOrder) {
		case 1:
			return Roles.PRINCIPAL;
		case 2:
			return Roles.VICEPRINCIPAL;
		case 3:
			return Roles.COORDINATOR;
		case 4:
			return Roles.OFFICESTAFF;
		case 5:
			return Roles.TEACHER;
		default :
			return null;
		}
	}
		
		private Set<Role> getRoleEntity(int typeOrder,Map<String,Role> roleMap) {
			Set<Role> role=new HashSet<Role>();
			switch (typeOrder) {
			case 1:
				role.add(roleMap.get(Roles.PRINCIPAL.name()));
				return role;
			case 2:
				role.add(roleMap.get(Roles.VICEPRINCIPAL.name()));
				return role;
			case 3:
				role.add(roleMap.get(Roles.COORDINATOR.name()));
				return role;
			case 4:
				role.add(roleMap.get(Roles.OFFICESTAFF.name()));
				return role;
			case 5:
				role.add(roleMap.get(Roles.TEACHER.name()));
				return role;
			default :
				return null;
			}
	}
		
		
//		private List<String> getPermissionParent(){
//			List<String> permissions=new ArrayList<String>();
//			try {
//				permissions.add(Permissions.homeworkPrem+Permissions.separeator+Permissions.read);
//				permissions.add(Permissions.consentPrem+Permissions.separeator+Permissions.read);
//				permissions.add(Permissions.messagePrem+Permissions.separeator+Permissions.read);
//				permissions.add(Permissions.circularPrem+Permissions.separeator+Permissions.read);
//				permissions.add(Permissions.timetablePrem+Permissions.separeator+Permissions.read);
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			
//			return permissions;
//		}
		

}
