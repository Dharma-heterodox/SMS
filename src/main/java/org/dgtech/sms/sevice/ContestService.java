package org.dgtech.sms.sevice;

import java.util.List;

import org.dgtech.sms.model.ContestDto;
import org.springframework.web.multipart.MultipartFile;


public interface ContestService {
	List<ContestDto> getAllBySchoolId(Long schoolId);
	List<ContestDto> getAllBySchoolIdAndGradeId(Long schoolId, String grade);
	List<ContestDto> getMyContest(Long schoolId, String grade);
	ContestDto createContest(Long schoolId, ContestDto contestDto, MultipartFile file) throws Exception;
	byte[] getContest(Long id);
}
