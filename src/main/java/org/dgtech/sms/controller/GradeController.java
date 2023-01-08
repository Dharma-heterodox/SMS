package org.dgtech.sms.controller;

import java.util.List;

import org.dgtech.sms.sevice.GradeService;
import org.dgtech.sms.sevice.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grade")
public class GradeController {
	
	@Autowired
	public GradeService gradeService;
	
	@Autowired
	private SectionService sectionService;
	
	@GetMapping(value="/{schoolId}")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getAllGrades(@PathVariable("schoolId")Long schoolId)throws Exception{
		return gradeService.getAllGrades(schoolId);
	}
	
	@GetMapping(value="/section/{schoolId}")
	@ResponseStatus
	public List<String> getAllSections(@PathVariable("schoolId")Long schoolId)throws Exception{
		return sectionService.getAllSection(schoolId);
	}

}
