package org.dgtech.sms.repo;

import java.util.Set;

import org.dgtech.sms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	    User findByEmail(String email);
	    User findByUserName(String userName);
	    User findByUserId(Long id);
//	    @Query(
//				  value = "SELECT user FROM User user WHERE user.mobile = ?1 and user.loginId = ?2 and user.active = true")
//	    User findByMobile(String mobile,int loginId);
	    @Query(
				  value = "SELECT user FROM User user WHERE user.mobile = ?1 and user.active = true")
	  User findByMobile(String mobile);
	    
	    @Query(
				  value = "SELECT user.mobile FROM User user WHERE user.active = true")
	  Set<String> getMobileBySchool();
	    
	    @Query(
				  value = "SELECT user FROM User user WHERE user.email = ?1 and user.password = ?2 and user.active = true ORDER BY user.id ASC")
	  User authUser(String email,String password);
	    
	    @Query(value = "SELECT COUNT(1) FROM User ue WHERE ue.email=:emailId")
	    Integer getByEmail(String emailId);

}
