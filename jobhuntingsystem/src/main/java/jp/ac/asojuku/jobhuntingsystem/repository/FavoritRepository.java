package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.FavoritEntity;

public interface FavoritRepository 
	extends JpaSpecificationExecutor<FavoritEntity>, JpaRepository<FavoritEntity, Integer>{

	List<FavoritEntity> findByStudentIdOrderByRegdatetimeDesc(Integer studentId);
	FavoritEntity findByStudentIdAndCompanyId(Integer studentId,Integer companyId);
}
