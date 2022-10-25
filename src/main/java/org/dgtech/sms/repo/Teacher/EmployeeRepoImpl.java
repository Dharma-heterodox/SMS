package org.dgtech.sms.repo.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class EmployeeRepoImpl implements EmployeeRepoCustom{
	
	@PersistenceContext
	private EntityManager em;
	
	 @Override
	    public Map<Integer,Long> getEmployeeMap(Long schoolId)throws Exception{
	    	Map<Integer, Long> grSection=new HashMap<Integer, Long>();
			TypedQuery<Object[]> query=em.createQuery("SELECT em.employeeCode,em.id FROM Employee em where em.schoolId=:schoolId", Object[].class);
			query.setParameter("schoolId", schoolId);
			List<Object[]> results=query.getResultList();
			results.forEach(h -> {
				grSection.put((Integer)h[0],(Long)h[1]);
			});
			return grSection;
		}

}
