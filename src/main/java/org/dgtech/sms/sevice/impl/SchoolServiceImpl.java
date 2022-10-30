package org.dgtech.sms.sevice.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dgtech.sms.entity.Circular;
import org.dgtech.sms.entity.Employee;
import org.dgtech.sms.entity.Grade;
import org.dgtech.sms.entity.News;
import org.dgtech.sms.entity.Organization;
import org.dgtech.sms.entity.Parent;
import org.dgtech.sms.entity.Section;
import org.dgtech.sms.entity.User;
import org.dgtech.sms.entity.employee.TeacherMapping;
import org.dgtech.sms.entity.student.Student;
import org.dgtech.sms.repo.SchoolRepo;
import org.dgtech.sms.sevice.OrganizationService;
import org.dgtech.sms.sevice.SchoolService;
import org.dgtech.sms.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepo schoolRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService orgservice;
	
	@Override
	public Employee saveEmployeeDetails(Employee employee) {
		return schoolRepo.saveEmployeeDetails(employee);
	}
	
	public TeacherMapping assignTeacher(TeacherMapping teacherMapping) {
		return schoolRepo.assignTeacher(teacherMapping);
	}

	@Override
	public Grade addGrade(Grade grade) {
		return schoolRepo.addGrade(grade);
	}

	@Override
	public Section addSection(Section section) {
		return schoolRepo.addSection(section);
	}

	
	@Override
	public Student addStudent(Student student) {
		student = schoolRepo.addStudent(student);
		createStudentAccount(student);
		return student;
	}
	
	@Override
	public Parent addParent(Parent parent) {
		parent = schoolRepo.addParent(parent);
		createParentAccount(parent);
		return parent;
	}

	private User createStudentAccount(Student student) {
		User user = new User();
		user.setUserName(student.getMobile());
		user.setPassword("test123$");
		user.setEmail(student.getEmail());
		user.setActive(true);
		if(student.getSchoolId() != null) {
			Organization org = orgservice.getOrganization(student.getSchoolId());
			Set<Organization> orgs = new HashSet<Organization>();
			orgs.add(org);
//			user.setOrganizations(orgs);
		}
		userService.saveUser(user);
		return user;
	}
	
	private User createParentAccount(Parent parent) {
		User user = new User();
		user.setUserName(parent.getMobile());
		user.setPassword("test123$");
		user.setEmail(parent.getEmail());
		user.setActive(true);
		/*
		 * if(parent.getSchoolId() != null) { Organization org =
		 * orgservice.getOrganization(student.getSchoolId()); Set<Organization> orgs =
		 * new HashSet<Organization>(); orgs.add(org); user.setOrganizations(orgs); }
		 */
		userService.saveUser(user);
		return user;
	}
	
	
	public Circular addCircular(Circular circular) {
		return schoolRepo.addCircular(circular);
	}
	
	public News addNews(News news) {
		return schoolRepo.addNews(news);
	}



	@Override
	public List<Grade> getGrades(Long schoolId, Long boardId) {
		return schoolRepo.getGrades(schoolId, boardId);
	}

	@Override
	public List<Section> getSections(Long schoolId, Long gradeId) {
		return schoolRepo.getSections(schoolId, gradeId);
	}

	@Override
	public List<Employee> getEmployees(Long schoolId) {
		return schoolRepo.getEmployees(schoolId);
	}

//	@Override
//	public List<Feedback> getFeedback(Long schoolId) {
//		return schoolRepo.getFeedback(schoolId);
//	}

	@Override
	public List<Circular> getCircular(Long schoolId) {
		return schoolRepo.getCircular(schoolId);
	}


	@Override
	public List<News> getNews(Long schoolId) {
		return schoolRepo.getNews(schoolId);
	}

	@Override
	public List<Student> getStudents(Long schoolId) {
		return schoolRepo.getStudents(schoolId);
	}

	@Override
	public List<TeacherMapping> getTeachers(Long schoolId, Long gradeId, Long sectionId, String academicYear) {
		return schoolRepo.getTeachers(schoolId, gradeId, sectionId, academicYear);
	}

	@Override
	public TeacherMapping getTeacher(Long schoolId, Long gradeId, Long sectionId, String academicYear, Long subject) {
		return schoolRepo.getTeacher(schoolId, gradeId, sectionId, academicYear, subject);
	}
}
