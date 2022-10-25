package org.dgtech.sms.repo.section;

import java.util.List;

import org.dgtech.sms.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SectionRepo extends JpaRepository<Section, Integer>,SectionRepoCustom {

	List<Section> findAllBySchoolId(Long schoolId);
	@Query(
			  value = "SELECT section FROM Section section WHERE section.schoolId = ?1 and section.gradeId = ?2 and section.active = true ORDER BY section.id ASC")
	List<Section> findAllBySchoolIdAndGradeId(Long schoolId, Long gradeId);
	@Query(
			  value = "SELECT section FROM Section section WHERE section.schoolId = ?1 and section.gradeId = ?2 and section.section = ?3 and section.active = true")
	Section findAllBySchoolIdAndSection(Long schoolId, Long gradeId, String section);

}
