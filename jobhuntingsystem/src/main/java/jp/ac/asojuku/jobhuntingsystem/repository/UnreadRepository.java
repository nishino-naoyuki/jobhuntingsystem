package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.InfoEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.UnreadEntity;


public interface UnreadRepository 
		extends JpaSpecificationExecutor<UnreadEntity>, JpaRepository<UnreadEntity, Integer>{

	public List<UnreadEntity> findByStudentId(Integer studentId);
	
	public UnreadEntity findByInfoIdAndStudentId(Integer infoId,Integer studentId);
}
