package org.dgtech.sms.repo;

import org.dgtech.sms.entity.Circular;
import org.dgtech.sms.entity.Employee;
import org.dgtech.sms.entity.Grade;
import org.dgtech.sms.entity.News;
import org.dgtech.sms.entity.Parent;
import org.dgtech.sms.entity.Section;
import org.dgtech.sms.entity.employee.TeacherMapping;
import org.dgtech.sms.entity.student.Student;

public interface SchoolCustomRepo {

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
}
