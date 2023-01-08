package org.dgtech.sms.repo.message;

import java.util.List;

import org.dgtech.sms.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Messages,Long>{
	
	@Query(value= "SELECT msg FROM Messages msg WHERE msg.id IN ?1")
	List<Messages> getPCMsg(List<Long> ids);
	

}
