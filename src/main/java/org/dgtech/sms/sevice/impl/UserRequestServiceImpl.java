package org.dgtech.sms.sevice.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dgtech.sms.entity.Employee;
import org.dgtech.sms.entity.Parent;
import org.dgtech.sms.entity.Role;
import org.dgtech.sms.entity.User;
import org.dgtech.sms.entity.UserRequest;
import org.dgtech.sms.entity.employee.EmployeeRequest;
import org.dgtech.sms.entity.student.Student;
import org.dgtech.sms.entity.student.StudentSectionRecord;
import org.dgtech.sms.repo.EmployeeRepo;
import org.dgtech.sms.repo.EmployeeRequestRepo;
import org.dgtech.sms.repo.ParentRepo;
import org.dgtech.sms.repo.RoleRepository;
import org.dgtech.sms.repo.UserRepository;
import org.dgtech.sms.repo.UserRequestRepo;
import org.dgtech.sms.sevice.UserRequestService;
import org.dgtech.sms.sevice.UserService;
import org.dgtech.sms.util.Constant;
import org.dgtech.sms.util.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRequestServiceImpl implements UserRequestService{
	
	@Autowired
	private UserRequestRepo userRequestRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ParentRepo parentRepo;
	
	@Autowired
	private EmployeeRequestRepo employeeReqRepo;
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private Logger logger = LoggerFactory.getLogger(UserRequestServiceImpl.class);

	@Override
	@Transactional
	public int createStudentParentAcc(List<Long> ids,Long schoolId) throws Exception {
		// TODO Auto-generated method stub
		List<UserRequest> requestedUsers=null;
		List<User> userList=null;
		List<Parent> parentList=null;
		int result=0;
		logger.debug("Create student parent acc ::");
		try {
			requestedUsers=userRequestRepo.getStudentParentReq(schoolId);
//			requestedUsers=userRequestRepo.getStudentParentReq(ids);
			userList=userRepo.saveAll(getStuUserObject(requestedUsers));
			parentList=parentRepo.saveAll(getParents(userList, requestedUsers));
			if(parentList.size()>0) {
				result=userRequestRepo.updateReqStatus(Constant.REQUEST_APPROVED,ids);
			}
		}catch(Exception ex) {
			logger.debug("Exception in createStudentParentAcc");
			logger.error("createStudentParentAcc Exception :: "+ex.getMessage());
			throw new Exception(ex);
		}
		return result;
	}
	
	private List<User> getStuUserObject(List<UserRequest> userReqList) throws Exception{
		List<User> userList=new ArrayList<User>();
		Set<Role> role=getParentRole();
		try {
			userReqList.forEach(h -> {
				User user=new User();
				user.setActive(true);
				user.setContactNo(h.getMobile());
				user.setEmergencyContactNo(h.getAlternateMobile());
				user.setFirstName(h.getMotherName());
				user.setMobile(h.getMobile());
				user.setPassword(bCryptPasswordEncoder.encode(Constant.DEFAULT_PASSWORD));
				user.setRoles(role);
				user.setUserName(h.getMotherName());
				user.setUserType("");
				user.setLastName(h.getFatherName());
//				user.setLoginId(h.getStudId());
				user.setLoginId("");
				userList.add(user);
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return userList;
		
	}
	
	private List<Parent> getParents(List<User> users,List<UserRequest> userReqList)throws Exception{
		List<Parent> parents=new ArrayList<Parent>();
		try {
			for(int i=0;i<userReqList.size();i++) {
				Parent parent=new Parent();
				Student student=new Student();
				StudentSectionRecord sectionRecord=new StudentSectionRecord();
				UserRequest req=(UserRequest)userReqList.get(i);
				User user=(User)users.get(i);
				parent.setAadhaarNo(req.getAadhaarNo());
				parent.setActive(true);
				parent.setAlternateMobile(req.getAlternateMobile());
				parent.setCaste(req.getCaste());
				parent.setCasteCat(req.getCasteCat());
				parent.setDisplayName(req.getMotherName());
				parent.setDob(req.getDob());
				parent.setGender("FEMALE");
				parent.setLandLine(req.getLandLine());
				parent.setMobile(user.getMobile());
				parent.setRelationship("MOTHER");
				parent.setReligion(req.getReligion());
				parent.setSpouseName(req.getFatherName());
				parent.setUserId(user.getId());
				student.setAadhaarNo(req.getAadhaarNo());
				student.setAddress(req.getAddress());
				student.setAdmissionNo(req.getAdmissionNo());
				student.setCaste(req.getCaste());
				student.setCasteCat(req.getCasteCat());
				student.setDisplayName(req.getStudentName());
				student.setDob(req.getDob());
				student.setEmergencyContactNo(req.getAlternateMobile());
				student.setEmisno(req.getEmisno());
				student.setExamNo(req.getExamNo());
				student.setFatherName(req.getFatherName());
				student.setFirstName(req.getStudentName());
				student.setGender(req.getGender());
				student.setGrade(req.getGrade());
//				student.setGradeId(req.getGradeId());
				student.setLandLine(req.getLandLine());
				student.setMobile(req.getMobile());
				student.setReligion(req.getReligion());
				student.setRollNo(req.getRollNo());
				student.setRTE(req.getRTE());
				student.setSchoolId(req.getSchoolId());
				student.setSection(req.getSection());
//				student.setSectionId(req.getSectionId());
				student.setStudId(req.getStudId());
				sectionRecord.setAcademicYear(Constant.currentAcademicYear);
//				sectionRecord.setGradeId(req.getGradeId());
				sectionRecord.setGrade(req.getGrade());
				sectionRecord.setSection(req.getSection());
//				sectionRecord.setSectionId(req.getSectionId());
				sectionRecord.addStudent(student);
				parent.addChild(student);
				parents.add(parent);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return parents;
	}
	
	private Set<Role> getParentRole(){
		Set<Role> role=new HashSet<Role>();
		role.add(roleRepository.findByRole(Roles.PARENT.name()));
		return role;
	}
	
	@Override
	@Transactional
	public int createEmployeeAcc(List<Long> ids,Long schoolId) throws Exception {
		// TODO Auto-generated method stub
		List<EmployeeRequest> employeeRequest=null;
		List<User> userList=null;
		List<Employee> empList=null;
		int result=0;
		logger.debug("Create Employee acc ::");
		try {
//			employeeRequest=employeeReqRepo.getEmployeeReq(schoolId);
			employeeRequest=employeeReqRepo.getEmployeeReq(ids);
			userList=userRepo.saveAll(getEmpUserObject(employeeRequest));
			empList=empRepo.saveAll(getEmployeeList(employeeRequest,userList ));
			if(empList.size()>0) {
				result=employeeReqRepo.updateReqStatus(Constant.REQUEST_APPROVED,ids);
			}
		}catch(Exception ex) {
			logger.debug("Exception in createEmployeeAcc");
			logger.error("createEmployeeAcc Exception :: "+ex.getMessage());
			throw new Exception(ex);
		}
		return result;
	}
	
	private List<User> getEmpUserObject(List<EmployeeRequest> empReqList) throws Exception{
		List<User> userList=new ArrayList<User>();
		Map<String,Role> roleMap=getEmployeeRole();
		try {
			empReqList.forEach(h -> {
				User user=new User();
				user.setActive(true);
				user.setContactNo(h.getMobile());
				user.setEmergencyContactNo(h.getAlternateMobile());
				user.setFirstName(h.getEmployeeName());
				user.setMobile(h.getMobile());
				user.setPassword(bCryptPasswordEncoder.encode(Constant.DEFAULT_PASSWORD));
				user.setRoles(getRoleEntity(h.getTypeOrder(),roleMap));
				user.setUserName(h.getEmployeeName());
				user.setUserType("");
				user.setLastName(h.getEmployeeName());
//				user.setLoginId(h.getEmployeeId());
				user.setLoginId("");
				userList.add(user);
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return userList;
		
	}
	
	private Map<String,Role> getEmployeeRole(){
		List<Role> role=roleRepository.findAll();
		Map<String,Role> roleMap=new HashMap<String,Role>();
		role.forEach(h -> {
			roleMap.put(h.getRole(), h);
		});
		return roleMap;
	}
//	private Set<Role> getCoordinatorRole(){
//		Set<Role> role=new HashSet<Role>();
//		role.add(roleRepository.findByRole(Roles.COORDINATOR.name()));
//		return role;
//	}
	
	private List<Employee> getEmployeeList(List<EmployeeRequest> employeeRequest,List<User> userList)throws Exception{
		List<Employee> employees=new ArrayList<Employee>();
		try {
			for(int i=0;i<employeeRequest.size();i++) {
				Employee emp=new Employee();
				EmployeeRequest req=(EmployeeRequest)employeeRequest.get(i);
				User user=(User)userList.get(i);
				emp.setAadhaarNo(req.getAadhaarNo());
				emp.setActive(true);
				emp.setAddress(req.getAddressOne());
				emp.setAlternateMobile(req.getAlternateMobile());
				emp.setCategory(getRoleName(req.getTypeOrder()).name());
				emp.setDateOfJoin(req.getDoj());
				emp.setDisplayName(req.getEmployeeName());
				emp.setDob(req.getDob());
				emp.setGender(req.getGender());
				emp.setMobile(req.getMobile());
				emp.setQualification(req.getQualification());
				emp.setSchoolId(req.getSchoolId());
				emp.setEmployeeCode(req.getEmployeeId());
//				emp.setSubCategory(0);
				emp.setUserId(user.getId());
				employees.add(emp);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return employees;
	}
	
	private Roles getRoleName(int typeOrder) {
		switch (typeOrder) {
		case 1:
			return Roles.PRINCIPAL;
		case 2:
			return Roles.VICEPRINCIPAL;
		case 3:
			return Roles.COORDINATOR;
		case 4:
			return Roles.OFFICESTAFF;
		case 5:
			return Roles.TEACHER;
		default :
			return null;
		}
	}
		
		private Set<Role> getRoleEntity(int typeOrder,Map<String,Role> roleMap) {
			Set<Role> role=new HashSet<Role>();
			switch (typeOrder) {
			case 1:
				role.add(roleMap.get(Roles.PRINCIPAL.name()));
				return role;
			case 2:
				role.add(roleMap.get(Roles.VICEPRINCIPAL.name()));
				return role;
			case 3:
				role.add(roleMap.get(Roles.COORDINATOR.name()));
				return role;
			case 4:
				role.add(roleMap.get(Roles.OFFICESTAFF.name()));
				return role;
			case 5:
				role.add(roleMap.get(Roles.TEACHER.name()));
				return role;
			default :
				return null;
			}
	}

}
