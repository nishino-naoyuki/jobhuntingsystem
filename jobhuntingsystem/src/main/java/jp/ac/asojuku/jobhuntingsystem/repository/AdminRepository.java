package jp.ac.asojuku.jobhuntingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.AdminEntity;

public interface AdminRepository 
	extends JpaSpecificationExecutor<AdminEntity>, JpaRepository<AdminEntity, Integer>{

	public AdminEntity findByMailAndPassword(String mail,String password);
}
