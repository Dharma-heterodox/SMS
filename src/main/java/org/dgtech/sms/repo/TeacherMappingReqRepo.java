package org.dgtech.sms.repo;

import java.util.List;

import org.dgtech.sms.entity.employee.TeacherMappingRequest;
import org.dgtech.sms.util.Constant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TeacherMappingReqRepo extends JpaRepository<TeacherMappingRequest, Long>{
	
	@Query(value="SELECT requestMapping FROM TeacherMappingRequest requestMapping WHERE requestMapping.requestStatus="+Constant.REQUEST_SUCCESS+" "
			+ " and requestMapping.id IN (:ids) ORDER BY requestMapping.id ASC")
	List<TeacherMappingRequest> getPendingRequestId(@Param("ids")List<Long> ids);
	
	@Query(value="SELECT requestMapping FROM TeacherMappingRequest requestMapping WHERE requestMapping.requestStatus="+Constant.REQUEST_SUCCESS+" "
			+ " and requestMapping.schoolId=:schoolId ORDER BY requestMapping.id ASC")
	List<TeacherMappingRequest> getPendingSchoolReq(@Param("schoolId")Long schoolId);
	
	@Modifying
	@Query(value="UPDATE TeacherMappingRequest requestMapping SET requestMapping.requestStatus="+Constant.REQUEST_APPROVED+" WHERE "
			+ " requestMapping.requestStatus="+Constant.REQUEST_SUCCESS+" and requestMapping.id IN (:ids)")
	int updateRequestStatus(@Param("ids")List<Long> ids);
	
	

}
