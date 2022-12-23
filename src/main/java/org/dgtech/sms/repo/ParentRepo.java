package org.dgtech.sms.repo;

import org.dgtech.sms.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {

	//List<Parent> findAllBySchoolId(Long schoolId);
	Parent findByMobile(String mobile);
	
	Parent findByEmail(String email);
	
	@Query(value="SELECT COUNT(1) FROM Parent pr where pr.email=:emailId")
	Integer getByEmail(String emailId);
}
