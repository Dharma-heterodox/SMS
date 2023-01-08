package org.dgtech.sms.repo.notifygrade;

import org.dgtech.sms.entity.NotifyGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyGradesRepo extends JpaRepository<NotifyGrade, Long>,NotifyGradesCustomRepo{
	

}
