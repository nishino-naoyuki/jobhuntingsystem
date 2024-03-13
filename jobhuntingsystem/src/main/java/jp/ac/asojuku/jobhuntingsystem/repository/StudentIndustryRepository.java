package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.StudentIndustryEntity;


public interface StudentIndustryRepository 
		extends JpaSpecificationExecutor<StudentIndustryEntity>, JpaRepository<StudentIndustryEntity, Integer>{

	List<StudentIndustryEntity> findByStudentIdOrderByIndustrykindId(Integer studentId);
}
