package org.dgtech.sms.sevice;

import java.util.List;

import org.dgtech.sms.entity.Circular;
import org.dgtech.sms.entity.Employee;
import org.dgtech.sms.entity.Grade;
import org.dgtech.sms.entity.News;
import org.dgtech.sms.entity.Parent;
import org.dgtech.sms.entity.Section;
import org.dgtech.sms.entity.employee.TeacherMapping;
import org.dgtech.sms.entity.student.Student;
import org.springframework.stereotype.Service;


@Service
public interface SchoolService {

	Employee saveEmployeeDetails(Employee employee);
	TeacherMapping assignTeacher(TeacherMapping teacherMapping);
	Grade addGrade(Grade grade);
	Section addSection(Section section);
	Student addStudent(Student student);
	Parent addParent(Parent parent);
//	Homework addHomework(Homework homework);
	Circular addCircular(Circular circular);
	News addNews(News news);
//	Feedback addFeedback(Feedback feedback);
//	List<SchoolBoard> getAvailableBoards(Long schoolId);
	List<Grade> getGrades(Long schoolId, Long boardId);
	List<Section> getSections(Long schoolId, Long gradeId);
	List<Employee> getEmployees(Long schoolId);
//	List<Feedback> getFeedback(Long schoolId);
	List<Circular> getCircular(Long schoolId);
//	List<Homework> getHomework(Long schoolId);
	List<News> getNews(Long schoolId);
	List<Student> getStudents(Long schoolId);
	List<TeacherMapping> getTeachers(Long schoolId, Long gradeId, Long sectionId, String academicYear);
	TeacherMapping getTeacher(Long schoolId, Long gradeId, Long sectionId, String academicYear, Long subject);
}
