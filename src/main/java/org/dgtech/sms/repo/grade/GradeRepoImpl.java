package org.dgtech.sms.repo.grade;

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
public class GradeRepoImpl implements GradeRepoCustom{
	
		@PersistenceContext
		private EntityManager em;
		@Override
	    public Map<String, String> getGradeMap(Long schoolId)throws Exception{
	    	Map<String, String> grades=new HashMap<String, String>();
			TypedQuery<Object[]> query=em.createQuery("SELECT gr.grade,gr.title FROM Grade gr where gr.schoolId=:schoolId ORDER BY gr.id ASC", Object[].class);
			query.setParameter("schoolId", schoolId);
			List<Object[]> results=query.getResultList();
			results.forEach(h -> {
				grades.put((String)h[0],(String) h[1]);
			});
			return grades;
		}
		
		@Override
		public List<String> getGradeList(Long schoolId)throws Exception{
	    	List<String> grades=new ArrayList<String>();
			TypedQuery<String[]> query=em.createQuery("SELECT gr.grade FROM Grade gr where gr.schoolId=:schoolId ORDER BY gr.grade ASC", String[].class);
			query.setParameter("schoolId", schoolId);
			List<String[]> results=query.getResultList();
			results.forEach(h -> {
				grades.add((String)h[0]);
			});
			return grades;
		}


}
