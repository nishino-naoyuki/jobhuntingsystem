package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.AdminEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.RecruitmentEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;

public interface RecruitmentRepository 
	extends JpaSpecificationExecutor<RecruitmentEntity>, JpaRepository<RecruitmentEntity, Integer>{

	List<RecruitmentEntity> findByCompanyIdOrderByPublicDateDesc(Integer companyId);
}
