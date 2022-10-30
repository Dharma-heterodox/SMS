package org.dgtech.sms.sevice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.dgtech.sms.model.AdmissionDto;


public interface AdmissionService {

	AdmissionDto createAdmission(Long schoolId, AdmissionDto admissionDto);

	List<AdmissionDto> getAllAdmissionBySchoolId(Long schoolId);
	ByteArrayInputStream download(Long schoolId) throws IOException;
}
