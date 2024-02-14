package jp.ac.asojuku.jobhuntingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.CompanyEntity;


public interface CompanyRepository 
		extends JpaSpecificationExecutor<CompanyEntity>, JpaRepository<CompanyEntity, Integer>{

}
