package org.dgtech.sms.repo;

import java.util.List;

import org.dgtech.sms.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AdmissionRepo extends JpaRepository<Admission, Integer> {

	List<Admission> findAllBySchoolId(Long schoolId);
	@Query(
			  value = "SELECT admission FROM Admission admission where admission.schoolId= ?1 and admission.grade = ?2 and admission.mobile = ?3 ORDER BY admission.id ASC")
	List<Admission> findAdmission(Long schoolId, String grade, String mobile);
}
