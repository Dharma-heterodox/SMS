package org.dgtech.sms.sevice.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.dgtech.sms.entity.consent.Contest;
import org.dgtech.sms.model.ContestDto;
import org.dgtech.sms.repo.ContestRepo;
import org.dgtech.sms.sevice.ContestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ContestServiceImpl implements ContestService {

	@Autowired
	private ContestRepo contestRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ContestDto> getAllBySchoolId(Long schoolId) {
		List<Contest> contests = contestRepo.findAllBySchoolId(schoolId);
		if(contests == null) {
			return null;
		}
		return contests.stream().map(c -> modelMapper.map(c, ContestDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ContestDto> getAllBySchoolIdAndGradeId(Long schoolId, String grade) {
		List<Contest> contests = contestRepo.findAllBySchoolIdAndGradeId(schoolId, grade);
		if(contests == null) {
			return null;
		}
		return contests.stream().map(c -> modelMapper.map(c, ContestDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ContestDto> getMyContest(Long schoolId, String grade) {
		List<Contest> contests = contestRepo.findMyContest(schoolId, grade);
		if(contests == null) {
			return null;
		}
		return contests.stream().map(c -> modelMapper.map(c, ContestDto.class)).collect(Collectors.toList());
	}

	public byte[] getContest(Long id) {
		Contest contest = contestRepo.getOne(id);
		return contest != null ? contest.getFile() : null;
	}
	
	@Override
	public ContestDto createContest(Long schoolId, ContestDto contestDto, MultipartFile file) throws Exception {
		Contest contest = modelMapper.map(contestDto, Contest.class);
		contest.setSchoolId(schoolId);
		contest.setActive(true);
		try {
			contest.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage(), e);
		}
		contest = contestRepo.save(contest);
		return modelMapper.map(contest, ContestDto.class);
	}

}
