package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.FavoriteEntity;

public interface FavoriteRepository 
	extends JpaSpecificationExecutor<FavoriteEntity>, JpaRepository<FavoriteEntity, Integer>{

	List<FavoriteEntity> findByStudentIdOrderByRegdatetimeDesc(Integer studentId);
	FavoriteEntity findByStudentIdAndCompanyId(Integer studentId,Integer companyId);
}
