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
import org.dgtech.sms.entity.Admission;
import org.dgtech.sms.model.AdmissionDto;
import org.dgtech.sms.repo.AdmissionRepo;
import org.dgtech.sms.sevice.AdmissionService;
import org.dgtech.sms.sevice.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class AdmissionServiceImpl implements AdmissionService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AdmissionRepo admissionRepo;
	@Autowired
	private NotificationService notificationService;

	@Override
	public AdmissionDto createAdmission(Long schoolId, AdmissionDto admissionDto) {
		try {
			Admission admission = modelMapper.map(admissionDto, Admission.class);
			admission.setSchoolId(schoolId);
			admission.setActive(true);
			List<Admission> admissions = admissionRepo.findAdmission(schoolId, admission.getGrade(), admission.getMobile());
			if (!CollectionUtils.isEmpty(admissions)) {
				throw new ValidationException("Your admission request already exist.");
			}
			admission = admissionRepo.save(admission);
			admissionDto = modelMapper.map(admission, AdmissionDto.class);
			String msg = "Deaer Parent, Thanks for your interest in our school. We acknowledge the recipt of your registration form. We will contact you in 48 hours. Happy Schooling!";
			notificationService.sendSMS(admission.getMobile(), msg, "Admission", admissionDto.getId());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return admissionDto;
	}

	@Override
	public List<AdmissionDto> getAllAdmissionBySchoolId(Long schoolId) {
		List<Admission> admissions = admissionRepo.findAllBySchoolId(schoolId);
		return admissions.stream().map(a -> modelMapper.map(a, AdmissionDto.class)).collect(Collectors.toList());
	}

	@Override
	public ByteArrayInputStream download(Long schoolId) throws IOException {
		List<Admission> admissions = admissionRepo.findAllBySchoolId(schoolId);
		String[] COLUMNs = {"Id", "Student Name", "Standard", "Apply For", "Father Name", "Contact No", "Alernate Mobile", "Applied Date"};
	    try(
	        Workbook workbook = new XSSFWorkbook();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ){
	      CreationHelper createHelper = workbook.getCreationHelper();
	   
	      Sheet sheet = workbook.createSheet("admissions");
	   
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
	   
	      if (!CollectionUtils.isEmpty(admissions)) {
		      int rowIdx = 1;
		      for (Admission admission : admissions) {
		        Row row = sheet.createRow(rowIdx++);
		   
		        row.createCell(0).setCellValue(admission.getId());
		        row.createCell(1).setCellValue(admission.getStudentName());
		        row.createCell(2).setCellValue(admission.getGrade());
		        row.createCell(3).setCellValue(admission.getApplyFor());
		        row.createCell(4).setCellValue(admission.getFatherName());
		        row.createCell(5).setCellValue(admission.getMobile());
		        row.createCell(6).setCellValue(admission.getAlternateMobile());
		        row.createCell(7).setCellValue(admission.getCreatedTime());
		      }
	      }
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    }
	}

}
