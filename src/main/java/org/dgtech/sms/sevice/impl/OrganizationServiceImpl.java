package org.dgtech.sms.sevice.impl;

import java.util.HashSet;
import java.util.Set;

import org.dgtech.sms.entity.Organization;
import org.dgtech.sms.entity.User;
import org.dgtech.sms.model.OrganizationDto;
import org.dgtech.sms.repo.OrganizationRepo;
import org.dgtech.sms.sevice.GradeService;
import org.dgtech.sms.sevice.OrganizationService;
import org.dgtech.sms.sevice.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired
	private OrganizationRepo organizationRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private GradeService gradeService;
	
	public Organization getOrganization(Long id) {
		return organizationRepo.getOne(id);
	}
	
	public OrganizationDto getOrganizationDetail(Long id)throws Exception {
		Organization org = organizationRepo.getOne(id);
		if(org == null) {
			return null;
		}
		OrganizationDto organizationDto = mapper.map(org, OrganizationDto.class);
		organizationDto.setGrades(gradeService.getAllGradesBySchoolId(organizationDto.getId()));
		return organizationDto;
	}
	
	public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
		//AddressDto addressDto = organizationDto.getAddress();
		Organization organization = mapper.map(organizationDto, Organization.class);
        Organization updatedOrganization = organizationRepo.save(organization);
		/*
		 * if(addressDto != null && addressDto.getAddressLine1() != null) {
		 * addressDto.setOrganizationId(updatedOrganization.getId());
		 * addressService.createAddress(addressDto); }
		 */
//        updatedOrganization.setTenantId(updatedOrganization.getId().intValue());
        updatedOrganization = organizationRepo.save(updatedOrganization);
        createOrgAccount(updatedOrganization);
        return mapper.map(updatedOrganization, OrganizationDto.class);
    }
	
	private User createOrgAccount(Organization organization) {
		User user = new User();
		user.setUserName(organization.getEmail());
		user.setPassword("test123$");
		user.setEmail(organization.getEmail());
		user.setActive(true);
		Set<Organization> orgs = new HashSet<Organization>();
		orgs.add(organization);
//		user.setOrganizations(orgs);
		userService.saveOrgAdminAccount(user);
		return user;
	}
}
