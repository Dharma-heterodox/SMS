package org.dgtech.sms.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.dgtech.sms.entity.Circular;
import org.dgtech.sms.entity.Employee;
import org.dgtech.sms.entity.Grade;
import org.dgtech.sms.entity.News;
import org.dgtech.sms.entity.Parent;
import org.dgtech.sms.entity.Section;
import org.dgtech.sms.entity.employee.TeacherMapping;
import org.dgtech.sms.entity.student.Student;
import org.springframework.stereotype.Repository;


@Repository
public class SchoolRepoImpl implements SchoolCustomRepo {

	@PersistenceContext
	private EntityManager em;
	
    @Transactional
	public Employee saveEmployeeDetails(Employee employee) {
		em.persist(employee);
		return employee;
	}
    
    @Transactional
	public TeacherMapping assignTeacher(TeacherMapping teacherMapping) {
		em.persist(teacherMapping);
		return teacherMapping;
	}
    
    @Transactional
	public Grade addGrade(Grade grade) {
		em.persist(grade);
		return grade;
	}
    
    @Transactional
	public Section addSection(Section section) {
		em.persist(section);
		return section;
	}
    
//    @Transactional
//	public SchoolBoard addSchoolBoard(SchoolBoard board) {
//		em.persist(board);
//		return board;
//	}

    @Transactional
	public Student addStudent(Student student) {
		em.persist(student);
		return student;
	}

    @Transactional
	public Parent addParent(Parent parent) {
		em.persist(parent);
		return parent;
	}
	
//    @Transactional
//	public Homework addHomework(Homework homework) {
//		em.persist(homework);
//		return homework;
//	}
    
    @Transactional
	public Circular addCircular(Circular circular) {
		em.persist(circular);
		return circular;
	}
    
    @Transactional
	public News addNews(News news) {
		em.persist(news);
		return news;
	}
    
//    @Transactional
//	public Feedback addFeedback(Feedback feedback) {
//		em.persist(feedback);
//		return feedback;
//	}
}
