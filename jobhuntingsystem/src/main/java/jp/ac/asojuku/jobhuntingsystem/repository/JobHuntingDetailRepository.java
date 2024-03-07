package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.AdminEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.IndustryEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.JobHuntingDetailEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;

public interface JobHuntingDetailRepository 
	extends JpaSpecificationExecutor<JobHuntingDetailEntity>, JpaRepository<JobHuntingDetailEntity, Integer>{

	List<JobHuntingDetailEntity> findByEventIdOrderByStepStartDatetime(Integer eventId);
}
