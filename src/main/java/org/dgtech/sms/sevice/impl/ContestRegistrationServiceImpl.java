package org.dgtech.sms.sevice.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dgtech.sms.entity.consent.ContestRegistration;
import org.dgtech.sms.model.ContestRegistrationDto;
import org.dgtech.sms.model.StudentDto;
import org.dgtech.sms.repo.ContestRegistrationRepo;
import org.dgtech.sms.sevice.ContestRegistrationService;
import org.dgtech.sms.sevice.NotificationService;
import org.dgtech.sms.sevice.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ContestRegistrationServiceImpl implements ContestRegistrationService {

	@Autowired
	private ContestRegistrationRepo contestRegistrationRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private StudentService studentService;
	
	public List<ContestRegistrationDto> gteAllRegistrations(Long schoolId) {
		List<ContestRegistration> contests = contestRegistrationRepo.findAllBySchoolId(schoolId);
		if(contests == null) {
			return null;
		}
		return contests.stream().map(c -> modelMapper.map(c, ContestRegistrationDto.class)).collect(Collectors.toList());
	}

	@Override
	public ContestRegistrationDto createRegistration(Long schoolId, ContestRegistrationDto contestRegsitrationDto) {
		StudentDto student = studentService.findByAdmissionNo(schoolId, contestRegsitrationDto.getAdmissionNo());
		if(student == null) {
			throw new ValidationException("Admission no not valid");
		}
		List<ContestRegistration> contests = contestRegistrationRepo.findContest(schoolId, contestRegsitrationDto.getContest(), contestRegsitrationDto.getContactNo());
		if(!CollectionUtils.isEmpty(contests))
			throw new ValidationException("Your already registered for this contest.");
		ContestRegistration contest = modelMapper.map(contestRegsitrationDto, ContestRegistration.class);
		contest.setSchoolId(schoolId);
		contest = contestRegistrationRepo.save(contest);
		contestRegsitrationDto = modelMapper.map(contest, ContestRegistrationDto.class);
		String msg = "Deaer Parent, We have enrolled your ward in the "+contestRegsitrationDto.getContest()+". Further information will be sent to your registered mobile number. Happy contesting!";
		notificationService.sendSMS(contestRegsitrationDto.getContactNo(), msg, "Contest", contestRegsitrationDto.getId());
		return contestRegsitrationDto;
	}
	
	public ByteArrayInputStream download(Long schoolId) throws IOException {
		List<ContestRegistration> contestRegistrations = contestRegistrationRepo.findAllBySchoolId(schoolId);
		String[] COLUMNs = {"Id", "Admission No", "Child Name", "Standard", "section", "Contest", "Father Name", "Contact No", "Applied Date"};
	    try(
	        Workbook workbook = new XSSFWorkbook();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ){
	      CreationHelper createHelper = workbook.getCreationHelper();
	   
	      Sheet sheet = workbook.createSheet("contesters");
	   
	      Font headerFont = workbook.createFont();
	      headerFont.setBold(true);
	      headerFont.setColor(IndexedColors.BLUE.getIndex());
	   
	      CellStyle headerCellStyle = workbook.createCellStyle();
	      headerCellStyle.setFont(headerFont);
	   
	      // Row for Header
	      Row headerRow = sheet.createRow(0);
	   
	      // Header
	      for (int col = 0; col < COLUMNs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(COLUMNs[col]);
	        cell.setCellStyle(headerCellStyle);
	      }
	   
	      // CellStyle for Age
	      CellStyle ageCellStyle = workbook.createCellStyle();
	      ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
	   
	      if (!CollectionUtils.isEmpty(contestRegistrations)) {
		      int rowIdx = 1;
		      for (ContestRegistration contest : contestRegistrations) {
		        Row row = sheet.createRow(rowIdx++);
		   
		        row.createCell(0).setCellValue(contest.getId());
		        row.createCell(1).setCellValue(contest.getAdmissionNo());
		        row.createCell(2).setCellValue(contest.getChildName());
		        row.createCell(3).setCellValue(contest.getGrade());
		        row.createCell(4).setCellValue(contest.getSection());
		        row.createCell(5).setCellValue(contest.getContest());
		        row.createCell(6).setCellValue(contest.getFatherName());
		        row.createCell(7).setCellValue(contest.getContactNo());
		        row.createCell(8).setCellValue(contest.getCreatedTime().toString());
		      }
	      }
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    }
	  }
}