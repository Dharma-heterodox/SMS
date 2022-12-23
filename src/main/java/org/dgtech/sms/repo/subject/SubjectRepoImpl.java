package org.dgtech.sms.repo.subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public class SubjectRepoImpl implements SubjectRepoCustom{
	
	@PersistenceContext
	private EntityManager em;
	
	 @Override
	    public Map<String,String> getGradeSubjectMap(Long schoolId)throws Exception{
	    	Map<String, String> grSection=new HashMap<String, String>();
			TypedQuery<Object[]> query=em.createQuery("SELECT sc.grade||'-'||sc.subjectName,sc.subjectName FROM Subject sc where sc.schoolId=:schoolId", Object[].class);
			query.setParameter("schoolId", schoolId);
			List<Object[]> results=query.getResultList();
			results.forEach(h -> {
				grSection.put((String)h[0],(String)h[1]);
			});
			return grSection;
		}
	 
	 @Override
	    public List<String> getSubjectIdMap(Long schoolId)throws Exception{
//	    	Map<Long, String> grSection=new HashMap<Long, String>();
			TypedQuery<String> query=em.createQuery("SELECT sc.subjectName FROM Subject sc where sc.schoolId=:schoolId", String.class);
			query.setParameter("schoolId", schoolId);
			List<String> results=query.getResultList();
//			results.forEach(h -> {
//				grSection.put((Long)h[0],(String)h[1]);
//			});
			return results;
		}

}
