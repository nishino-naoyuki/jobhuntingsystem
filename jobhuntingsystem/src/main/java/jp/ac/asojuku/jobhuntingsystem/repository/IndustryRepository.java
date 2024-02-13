package jp.ac.asojuku.jobhuntingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.AdminEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.IndustryEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;

public interface IndustryRepository 
	extends JpaSpecificationExecutor<IndustryEntity>, JpaRepository<IndustryEntity, Integer>{

}
