package org.dgtech.sms.sevice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.dgtech.sms.model.ContestRegistrationDto;


public interface ContestRegistrationService {

	List<ContestRegistrationDto> gteAllRegistrations(Long schoolId);
	ContestRegistrationDto createRegistration(Long schoolId, ContestRegistrationDto contestRegsitrationDto);
	ByteArrayInputStream download(Long schoolId) throws IOException;
}
