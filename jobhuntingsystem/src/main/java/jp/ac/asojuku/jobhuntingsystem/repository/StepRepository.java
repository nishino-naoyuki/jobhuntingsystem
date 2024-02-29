package jp.ac.asojuku.jobhuntingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.StepEntity;


public interface StepRepository 
		extends JpaSpecificationExecutor<StepEntity>, JpaRepository<StepEntity, Integer>{

}
