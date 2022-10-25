package org.dgtech.sms.repo;

import org.dgtech.sms.entity.student.StudentSectionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSectionRepo extends JpaRepository<StudentSectionRecord, Long>{

}
