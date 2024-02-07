package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.ClassEntity;


public interface ClassRepository 
		extends JpaSpecificationExecutor<ClassEntity>, JpaRepository<ClassEntity, Integer>{

	List<ClassEntity> findByDepartmentId(Integer departmentId);
	
}
