package org.dgtech.sms.repo;

import java.util.List;

import org.dgtech.sms.entity.Circular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircularRepo extends JpaRepository<Circular, Long> {
	List<Circular> findAllBySchoolId(Long schoolId);
}
