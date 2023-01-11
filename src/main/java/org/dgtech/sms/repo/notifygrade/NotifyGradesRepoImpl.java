package org.dgtech.sms.repo.notifygrade;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class NotifyGradesRepoImpl implements NotifyGradesCustomRepo {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Long> msgIdValue(String[] grades)throws Exception{
		LocalDateTime ldt =LocalDateTime.now();
		TypedQuery<Long> query = em.createQuery( "SELECT ngy.msg.id FROM NotifyGrade ngy WHERE ngy.createdDate BETWEEN ?1 AND ?2 AND ngy.grade IN ?3 "
				+ "AND ngy.display = true",Long.class);
			query.setParameter(1, ldt.minusDays(30));
			query.setParameter(2, ldt);
			query.setParameter(3, Arrays.asList(grades));
			List<Long> qResult= query.getResultList();
			return qResult;
	}

}
