package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.CompanyEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.CompanyIndustryEntity;


public interface CompanyIndustryRepository 
		extends JpaSpecificationExecutor<CompanyIndustryEntity>, JpaRepository<CompanyIndustryEntity, Integer>{

	List<CompanyIndustryEntity> findByCompanyIdOrderByIndustrykindId(Integer companyId);
}
