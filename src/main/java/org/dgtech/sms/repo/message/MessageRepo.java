package org.dgtech.sms.repo.message;

import java.util.List;

import org.dgtech.sms.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Messages,Long>{
	
	@Query(value= "SELECT msg FROM Messages msg WHERE msg.id IN ?1 AND msg.display = true")
	List<Messages> getPCMsg(List<Long> ids);
	
	@Query(value= "SELECT msg FROM Messages msg WHERE msg.display = false")
	List<Messages> msg4Approval();
	
	@Modifying
	@Query(value = "UPDATE Messages msg SET msg.display= true WHERE msg.id IN ?1")
	int approveMsg(List<Long> ids)throws Exception;

	
	

}
