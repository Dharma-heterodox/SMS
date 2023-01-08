package org.dgtech.sms.sevice.impl;

import java.util.List;

import org.dgtech.sms.repo.section.SectionRepo;
import org.dgtech.sms.sevice.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionRepo sectionRepo;

	@Override
	public List<String> getAllSection(Long schoolId) throws Exception {
		// TODO Auto-generated method stub
		return sectionRepo.getGradeSectionList(schoolId);
	}

	
}
