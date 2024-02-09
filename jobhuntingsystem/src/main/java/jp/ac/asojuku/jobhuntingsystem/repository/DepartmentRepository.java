package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.DepartmentEntity;


public interface DepartmentRepository 
		extends JpaSpecificationExecutor<DepartmentEntity>, JpaRepository<DepartmentEntity, Integer>{

	List<DepartmentEntity> findBySchoolId(Integer schoolId);
	
	DepartmentEntity findByCode(String code);
}
