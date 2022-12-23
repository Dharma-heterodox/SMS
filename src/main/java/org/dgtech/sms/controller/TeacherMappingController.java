package org.dgtech.sms.controller;

import java.util.List;

import org.dgtech.sms.model.AuthorizeDto;
import org.dgtech.sms.model.TeacherMappingDto;
import org.dgtech.sms.sevice.TeacherMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/{schoolId}/teacherMapping")
public class TeacherMappingController {

	@Autowired
	TeacherMappingService teacherMappingService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public TeacherMappingDto createTeacherMapping(@PathVariable("schoolId") Long schoolId,
			@RequestBody TeacherMappingDto teacherMappingDto) {
		return teacherMappingService.createTeacherMapping(schoolId, teacherMappingDto);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<TeacherMappingDto> getAllTeacherMappingBySchoolId(@PathVariable("schoolId") Long schoolId) {
		return teacherMappingService.getAllTeacherMappingBySchoolId(schoolId);
	}
	
	@GetMapping(value="/student/{studentId}")
	@ResponseStatus(HttpStatus.OK)
	public List<TeacherMappingDto> getAllTeacherMappingBySchoolId(@PathVariable("schoolId") Long schoolId, @PathVariable("studentId") Long studentId) {
		return teacherMappingService.getAllTeacherMappingByStudentId(schoolId, studentId, null);
	}
	
	@GetMapping(value="/student/{studentId}/{academicYear}")
	@ResponseStatus(HttpStatus.OK)
	public List<TeacherMappingDto> getAllTeacherMappingBySchoolId(@PathVariable("schoolId") Long schoolId
			, @PathVariable("studentId") Long studentId, @PathVariable("academicYear") String academicYear) {
		return teacherMappingService.getAllTeacherMappingByStudentId(schoolId, studentId, null);
	}
	
	@PostMapping(value="/upload")
	@ResponseStatus(HttpStatus.OK)
	public int uploadMappings(@PathVariable("schoolId") Long schoolId,@RequestParam("file") MultipartFile file)throws Exception{
		return teacherMappingService.upload(schoolId, file);
	}
	
	@PostMapping(value="/approve")
	@ResponseStatus(HttpStatus.OK)
	public int approveMappings(@PathVariable("schoolId") Long schoolId,@RequestBody AuthorizeDto dto)throws Exception{
		return teacherMappingService.approveMappings(schoolId, dto.getIds());
	}
}
