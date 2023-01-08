package org.dgtech.sms.repo.section;

import java.util.List;

import org.dgtech.sms.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SectionRepo extends JpaRepository<Section, Integer>,SectionRepoCustom {


}
