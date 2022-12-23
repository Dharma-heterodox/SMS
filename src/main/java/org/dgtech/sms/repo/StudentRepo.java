package org.dgtech.sms.repo;

import java.util.List;
import java.util.Set;

import org.dgtech.sms.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

	List<Student> findAllBySchoolId(Long schoolId);
	@Query(
			  value = "SELECT student FROM Student student, StudentParent studentParent WHERE student.id = studentParent.studentId and studentParent.parentId = ?1")
	List<Student> findAllStudentsByParentId(Long parentId);
	@Query(
			  value = "SELECT student FROM Student student where student.schoolId= ?1 and student.admissionNo = ?2")
	Student findByAdmissionNo(Long schoolId, String admissionNo);
	
	@Query(value="SELECT student from Student student where student.section = ?1")
	List<Student> findAllBySectionId(String section);
	
	@Query(value="SELECT student FROM Student student WHERE student.id NOT IN (:ids)")
	List<Student> findStudentsHWND(@Param("ids")List<Long> studentId);
	
	@Query(value="SELECT student.studId from Student student where student.schoolId = ?1")
	Set<Integer> getAllStudentsId(Long schoolId);
}
