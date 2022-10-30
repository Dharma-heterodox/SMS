package org.dgtech.sms.sevice;

import java.util.List;

import org.dgtech.sms.model.CircularDto;
import org.springframework.web.multipart.MultipartFile;


public interface CircularService {

	CircularDto createCircular(Long schoolId, CircularDto circularDto, MultipartFile file) throws Exception;

	List<CircularDto> getAllCircularBySchoolId(Long schoolId);
	byte[] getCircular(Long id);
}
