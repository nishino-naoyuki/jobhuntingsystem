package jp.ac.asojuku.jobhuntingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.InfoEntity;


public interface InfoRepository 
		extends JpaSpecificationExecutor<InfoEntity>, JpaRepository<InfoEntity, Integer>{

}
