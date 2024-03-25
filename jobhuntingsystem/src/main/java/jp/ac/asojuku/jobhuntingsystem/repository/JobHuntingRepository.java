package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.AdminEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.IndustryEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.JobHuntingEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;

public interface JobHuntingRepository 
	extends JpaSpecificationExecutor<JobHuntingEntity>, JpaRepository<JobHuntingEntity, Integer>{

	List<JobHuntingEntity> findByEventIdOrderByStepStartDatetime(Integer eventId);
	
	JobHuntingEntity findByEventIdAndStudentId(Integer eventId,Integer studentId);

	List<JobHuntingEntity> findByStudentIdOrderByStepStartDatetimeDesc(Integer studentId);
}
