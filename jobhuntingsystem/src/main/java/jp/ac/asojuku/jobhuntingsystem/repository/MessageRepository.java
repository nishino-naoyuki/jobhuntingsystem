package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.ac.asojuku.jobhuntingsystem.entity.MessageEntity;

public interface MessageRepository 
	extends JpaSpecificationExecutor<MessageEntity>, JpaRepository<MessageEntity, Integer>{

	public List<MessageEntity> findByStudentId(Integer studentId);
	public MessageEntity findByMessageIdAndStudentId(Integer messageId,Integer studentId);
}
