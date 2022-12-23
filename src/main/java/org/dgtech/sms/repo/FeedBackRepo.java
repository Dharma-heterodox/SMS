package org.dgtech.sms.repo;

import java.util.List;

import org.dgtech.sms.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepo extends JpaRepository<Feedback, Long> {

	List<Feedback> findAllBySchoolId(Long schoolId);
	List<Feedback> findAllByStudentId(Long studentId);

}
