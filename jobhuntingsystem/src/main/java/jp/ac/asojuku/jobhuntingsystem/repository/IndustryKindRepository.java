package jp.ac.asojuku.jobhuntingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.IndustrykindEntity;

public interface IndustryKindRepository 
	extends JpaSpecificationExecutor<IndustrykindEntity>, JpaRepository<IndustrykindEntity, Integer>{

}
