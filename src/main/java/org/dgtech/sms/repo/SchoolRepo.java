package org.dgtech.sms.repo;

import java.util.List;

import org.dgtech.sms.entity.Circular;
import org.dgtech.sms.entity.Employee;
import org.dgtech.sms.entity.Grade;
import org.dgtech.sms.entity.News;
import org.dgtech.sms.entity.Organization;
import org.dgtech.sms.entity.Section;
import org.dgtech.sms.entity.employee.TeacherMapping;
import org.dgtech.sms.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SchoolRepo extends JpaRepository<Organization, Long>, SchoolCustomRepo {

	Employee saveEmployeeDetails(Employee employee);
//	@Query(
//			  value = "SELECT board FROM SchoolBoard board WHERE board.schoolId = ?1 and board.active = true")
//	List<SchoolBoard> getAvailableBoards(Long schoolId);
	@Query(
			  value = "SELECT grade FROM Grade grade WHERE grade.schoolId = ?1 and grade.boardId = ?2 and grade.active = true")
	List<Grade> getGrades(Long schoolId, Long boardId);
	
	@Query(
			  value = "SELECT section FROM Section section WHERE section.schoolId = ?1 and section.gradeId = ?2 and section.active = true")
	List<Section> getSections(Long schoolId, Long gradeId);
	
	@Query(
			  value = "SELECT employee FROM Employee employee WHERE employee.schoolId = ?1 and employee.active = true")
	List<Employee> getEmployees(Long schoolId);
	
//	@Query(
//			  value = "SELECT feedback FROM Feedback feedback WHERE feedback.schoolId = ?1 and feedback.active = true")
//	List<Feedback> getFeedback(Long schoolId);
	
	@Query(
			  value = "SELECT circular FROM Circular circular WHERE circular.schoolId = ?1 and circular.active = true")
	List<Circular> getCircular(Long schoolId);
	
//	@Query(
//			  value = "SELECT homework FROM Homework homework WHERE homework.schoolId = ?1")
//	List<Homework> getHomework(Long schoolId);
//	
	@Query(
			  value = "SELECT news FROM News news WHERE news.schoolId = ?1 and news.active = true")
	List<News> getNews(Long schoolId);
	
	@Query(
			  value = "SELECT student FROM Student student WHERE student.schoolId = ?1 and student.active = true")
	List<Student> getStudents(Long schoolId);
	
	@Query(
			  value = "SELECT teacher FROM TeacherMapping teacher WHERE teacher.schoolId = ?1 and teacher.gradeId = ?2 and teacher.sectionId=?3 and teacher.academicYear=?4  and teacher.active = true")
	List<TeacherMapping> getTeachers(Long schoolId, Long gradeId, Long sectionId, String academicYear);
	
	@Query(
			  value = "SELECT teacher FROM TeacherMapping teacher WHERE teacher.schoolId = ?1 and teacher.gradeId = ?2 and teacher.sectionId=?3 and teacher.academicYear=?4 and teacher.subjectId=?5 and teacher.active = true")
	TeacherMapping getTeacher(Long schoolId, Long gradeId, Long sectionId, String academicYear, Long subjectId);
	
}
