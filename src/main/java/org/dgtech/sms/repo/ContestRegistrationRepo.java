package org.dgtech.sms.repo;

import java.util.List;

import org.dgtech.sms.entity.consent.ContestRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ContestRegistrationRepo extends JpaRepository<ContestRegistration, Long> {

	List<ContestRegistration> findAllBySchoolId(Long schoolId);
	@Query(
			  value = "SELECT contestRegistration FROM ContestRegistration contestRegistration where contestRegistration.schoolId= ?1 and contestRegistration.contest = ?2 and contestRegistration.contactNo = ?3 ORDER BY contestRegistration.id ASC")
	List<ContestRegistration> findContest(Long schoolId, String contest, String contactNo);
}
