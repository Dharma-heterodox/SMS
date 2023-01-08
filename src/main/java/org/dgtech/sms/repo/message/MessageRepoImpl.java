package org.dgtech.sms.repo.message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class MessageRepoImpl implements MessageCustomeRepo{
	
	@PersistenceContext
	private EntityManager em;
	
//	@Override
//	public List<Messages> getLast30Msg(String[] selGrade)throws Exception{
//		TypedQuery<Messages> query = em.createQuery( "SELECT msg from Messages msg WHERE msg.display = true AND msg.notifyGrades IN (?1) "
//			+ "AND msg.notifyGrades.createdDate "
//			+ "BETWEEN ?2 AND ?3 ORDER BY msg.id DESC",Messages.class);
//		query.setParameter(1, selGrade);
//		query.setParameter(2, LocalDateTime.now().minusMonths(1));
//		query.setParameter(3, LocalDateTime.now());
//		List<Messages> qResult= query.getResultList();
//		return qResult;
//	}

}
