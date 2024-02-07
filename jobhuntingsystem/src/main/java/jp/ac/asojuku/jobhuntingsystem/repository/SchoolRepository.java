package jp.ac.asojuku.jobhuntingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.SchoolEntity;


public interface SchoolRepository 
		extends JpaSpecificationExecutor<SchoolEntity>, JpaRepository<SchoolEntity, Integer>{

}
