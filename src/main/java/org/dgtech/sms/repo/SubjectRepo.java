package org.dgtech.sms.repo;

import java.util.List;

import org.dgtech.sms.entity.Subject;
import org.dgtech.sms.repo.subject.SubjectRepoCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long>,SubjectRepoCustom {
	
	List<Subject> findAllBySchoolId(Long schoolId);
	@Query(
			  value = "SELECT subject FROM Subject subject WHERE subject.schoolId = ?1 and subject.grade = ?2 and subject.active = true")
	List<Subject> findAllBySchoolIdAndGradeId(Long schoolId, String grade);
	@Query(
			  value = "SELECT subject FROM Subject subject WHERE subject.schoolId = ?1 and subject.subjectName = ?2 and subject.active = true")
	Subject findAllBySchoolIdAndSubject(Long schoolId, String subject);
	@Query(
			  value = "SELECT subject FROM Subject subject WHERE subject.schoolId = ?1 and subject.grade = ?2 and subject.subjectName = ?3 and subject.active = true")
	Subject findSubject(Long schoolId, String grade, String subject);
	
	@Query(
			  value = "SELECT subject FROM Subject subject WHERE subject.grade = ?1 and subject.active = true")
	List<Subject> getAllByGradeId( String grade);

}
