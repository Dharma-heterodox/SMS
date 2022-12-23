package org.dgtech.sms.repo;

import java.util.List;

import org.dgtech.sms.entity.employee.TeacherMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherMappingRepo extends JpaRepository<TeacherMapping, Long> {

	List<TeacherMapping> findAllBySchoolId(Long schoolId);
	@Query(
			  value = "SELECT mapping FROM TeacherMapping mapping WHERE mapping.schoolId = ?1 and mapping.grade = ?2 and mapping.section = ?3 and mapping.academicYear = ?4 and mapping.active = true")
	List<TeacherMapping> findMappedTeachers(Long schoolId, String grade, String section, String acadamicYear);
	
	@Query(
			  value = "SELECT mapping FROM TeacherMapping mapping WHERE mapping.teacherId = ?1 and mapping.academicYear = ?2 and mapping.active = true")
	List<TeacherMapping> findMapByTeacher(Long teacherId, String acadamicYear);
	
	@Query(
			  value = "SELECT mapping FROM TeacherMapping mapping WHERE mapping.grade = ?1 and mapping.academicYear = ?2 and mapping.active = true")
	List<TeacherMapping> findTeachersSection(String gradeId,String acadamicYear);
}
