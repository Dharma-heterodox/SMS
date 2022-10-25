package org.dgtech.sms.repo;

import java.util.List;

import org.dgtech.sms.entity.consent.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ContestRepo extends JpaRepository<Contest, Long> {

	List<org.dgtech.sms.entity.consent.Contest> findAllBySchoolId(Long schoolId);
	
	@Query(
			  value = "SELECT contest FROM Contest contest WHERE contest.schoolId = ?1 and contest.grade != ?2 and contest.active = true ORDER BY contest.id ASC")
	List<Contest> findAllBySchoolIdAndGradeId(Long schoolId, String grade);
	
	@Query(
			  value = "SELECT contest FROM Contest contest WHERE contest.schoolId = ?1 and (contest.grade is null or contest.grade != ?2) and contest.active = true ORDER BY contest.id ASC")
	List<Contest> findMyContest(Long schoolId, String grade);
}
