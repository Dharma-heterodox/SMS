package org.dgtech.sms.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.dgtech.sms.entity.UserRequest;
import org.dgtech.sms.util.Constant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepo extends JpaRepository<UserRequest, Long> {
	
		@Query("SELECT userReq FROM UserRequest userReq where userReq.requestStatus ="+Constant.REQUEST_SUCCESS+" and  "
				+ "userReq.requestedType ="+Constant.REQUEST_STUDENT+" and userReq.schoolId=:schoolId ORDER BY userReq.id ASC")
		List<UserRequest> getStudentParentReq(@Param("schoolId")Long schoolId);
		
		@Query("SELECT userReq FROM UserRequest userReq where userReq.requestStatus ="+Constant.REQUEST_SUCCESS+" and  "
				+ "userReq.requestedType ="+Constant.REQUEST_STUDENT+" and userReq.id IN (:ids) ORDER BY userReq.id ASC")
		List<UserRequest> getStudentParentReq(@Param("ids")List<Long> ids);
		
		@Modifying
		@Query("UPDATE UserRequest userReq SET userReq.requestStatus =:reqStatus WHERE userReq.id IN (:ids) ")
		int updateReqStatus(@Param("reqStatus")int requestedType,@Param("ids")List<Long> ids);
		
		@Modifying
		@Query("UPDATE UserRequest userReq SET userReq.requestStatus =:reqStatus WHERE userReq.schoolId=:schoolId AND userReq.requestStatus ="+Constant.REQUEST_SUCCESS)
		int updateSchoolReqStatus(@Param("reqStatus")int requestedType,@Param("schoolId")Long schoolId);
}
