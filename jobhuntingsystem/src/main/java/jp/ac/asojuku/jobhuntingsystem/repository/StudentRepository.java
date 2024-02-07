package jp.ac.asojuku.jobhuntingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;

public interface StudentRepository 
	extends JpaSpecificationExecutor<StudentEntity>, JpaRepository<StudentEntity, Integer>{

	public StudentEntity findByMailAndPassword(String mail,String password);
	
	public StudentEntity findByStudentNo(String studentNo);
}
