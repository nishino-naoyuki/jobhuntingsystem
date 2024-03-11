package jp.ac.asojuku.jobhuntingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.RecrutimentTypeEntity;

public interface RecruitmentTypeRepository 
	extends JpaSpecificationExecutor<RecrutimentTypeEntity>, JpaRepository<RecrutimentTypeEntity, Integer>{

}
