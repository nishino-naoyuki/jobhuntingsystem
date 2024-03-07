package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.EventEntity;


public interface EventRepository 
		extends JpaSpecificationExecutor<EventEntity>, JpaRepository<EventEntity, Integer>{

	List<EventEntity> findByCompanyIdOrderByStartDatetimeDesc(Integer companyId);
	List<EventEntity> findByCompanyIdAndRecruitmentIdOrderByStartDatetimeDesc(Integer companyId,Integer recruitmentId);
}
