package org.dgtech.sms.sevice;

import org.dgtech.sms.entity.Organization;
import org.dgtech.sms.model.OrganizationDto;

public interface OrganizationService {

	OrganizationDto saveOrganization(OrganizationDto organization);
	Organization getOrganization(Long id);
	OrganizationDto getOrganizationDetail(Long id)throws Exception;
}
