package org.dgtech.sms.repo.section;

import java.util.ArrayList;
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
public class SectionRepoImpl implements SectionRepoCustom{
	
	@PersistenceContext
	private EntityManager em;
	
	 @Override
	    public Map<String,String> getGradeSectionMap(Long schoolId)throws Exception{
	    	Map<String, String> grSection=new HashMap<String, String>();
			TypedQuery<Object[]> query=em.createQuery("SELECT sc.gradeId||'-'||sc.section,sc.section FROM Section sc where sc.schoolId=:schoolId", Object[].class);
			query.setParameter("schoolId", schoolId);
			List<Object[]> results=query.getResultList();
			results.forEach(h -> {
				grSection.put((String)h[0],(String)h[1]);
			});
			return grSection;
		}
	 
	 @Override
	 public List<String> getGradeSectionList(Long schoolId)throws Exception{
		 List<String> section=new ArrayList<String>();
			TypedQuery<String[]> query=em.createQuery("SELECT sc.section FROM Section sc where sc.schoolId=:schoolId ORDER BY sc.section ASC", String[].class);
			query.setParameter("schoolId", schoolId);
			List<String[]> results=query.getResultList();
			results.forEach(h -> {
				section.add(h[0]);
			});
			return section;
		}


}
