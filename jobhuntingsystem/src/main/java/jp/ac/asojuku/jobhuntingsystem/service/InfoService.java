package jp.ac.asojuku.jobhuntingsystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ac.asojuku.jobhuntingsystem.dto.InfoDto;
import jp.ac.asojuku.jobhuntingsystem.entity.InfoEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.MessageEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.UnreadEntity;
import jp.ac.asojuku.jobhuntingsystem.form.InfoRegiForm;
import jp.ac.asojuku.jobhuntingsystem.param.Level;
import jp.ac.asojuku.jobhuntingsystem.repository.InfoRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.MessageRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.StudentRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.UnreadRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Exchange;

@Service
public class InfoService {
	
	@Autowired
	InfoRepository infoRepository;
	@Autowired
	UnreadRepository unreadRepository;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	StudentRepository studentRepository;
	
	/**
	 * お知らせ情報の取得
	 * 
	 * @param studentId
	 * @return
	 */
	public List<InfoDto> getInfoData(Integer studentId) {
		List<InfoEntity> infoList = infoRepository.findAll();
		List<UnreadEntity> unreadList = new ArrayList<>();
		List<MessageEntity> messageList = new ArrayList<>();
		if( studentId != null ) {
			unreadList = unreadRepository.findByStudentId(studentId);
			messageList = messageRepository.findByStudentId(studentId);
		}
		
		return getDtoBy(infoList,unreadList,messageList);
	}
	
	/**
	 * 詳細データの取得
	 * @param id
	 * @param kind
	 * @return
	 */
	public InfoDto getInfoOneData(Integer studentId,Integer id,Integer kind) {
		InfoDto infoDto = null;
		if( kind == InfoDto.KIND_INFO ) {
			InfoEntity infoEntity = infoRepository.getOne(id);
			infoDto = getDtoBy(infoEntity);
			
		}else if( kind == InfoDto.KIND_MSG ) {
			MessageEntity messageEntity = messageRepository.findByMessageIdAndStudentId(studentId,id);
			infoDto = getDtoBy(messageEntity);			
		}else {
			infoDto = new InfoDto();
			infoDto.setTitle("Ooops");
			infoDto.setMsg("この情報は存在しません。");
		}
		
		return infoDto;
	}
	
	/**
	 * お知らせ情報の登録
	 * @param title
	 * @param msg
	 * @param level
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertInGeneralInfo(String title,String msg,Integer level) {
		//お知らせ情報を登録
		InfoEntity infoEntity = new InfoEntity();
		infoEntity.setLevel(level);
		infoEntity.setMsg(msg);
		infoEntity.setTitle(title);
		infoEntity.setInfoDate( Exchange.nowDate() );
		
		infoEntity = infoRepository.save(infoEntity);
		
		//未読テーブルにレコード追加
		List<StudentEntity> studentList = studentRepository.findAll();
		List<UnreadEntity> unreadEntityList = new ArrayList<>();
		for( StudentEntity student : studentList) {
			UnreadEntity unreadEntity = new UnreadEntity();
			unreadEntity.setInfoId(infoEntity.getInfoId());
			unreadEntity.setStudentId(student.getStudentId());
			
			unreadEntityList.add(unreadEntity);
		}
		unreadRepository.saveAll(unreadEntityList);
	}
	
	/**
	 * お知らせ情報未読テーブルを削除
	 * 
	 * @param studentId
	 * @param id
	 */
	public void deleteUnreadInfo(Integer studentId,Integer id) {
		UnreadEntity unreadEntity = unreadRepository.findByInfoIdAndStudentId(id,studentId);
		if( unreadEntity != null ) {
			unreadRepository.delete(unreadEntity);
		}
	}
	
	/**
	 * 個人あてメッセージを既読にする
	 * 
	 * @param studentId
	 * @param messageId
	 */
	public void updateReadMessage(Integer studentId,Integer messageId) {
		MessageEntity messageEntity = messageRepository.findByMessageIdAndStudentId(messageId, studentId);
		if( messageEntity != null ) {
			messageEntity.setReadFlg(1);
			messageRepository.save(messageEntity);
		}
	}
	
	/* -private method- */
	/**
	 * Entity→DTO変換
	 * @param infoEntity
	 * @return
	 */
	private InfoDto getDtoBy(InfoEntity infoEntity) {
		InfoDto infoDto = new InfoDto();
		if( infoEntity == null ) {
			infoDto.setMsg("この情報は存在しません。");
		}
		infoDto.setTitle(infoEntity.getTitle());
		infoDto.setMsg(infoEntity.getMsg());
		infoDto.setLevel( Level.search(infoEntity.getLevel()) );
		infoDto.setInfoDate( Exchange.toLocalDateTime(infoEntity.getInfoDate()) );
		infoDto.setId(infoEntity.getInfoId());
		infoDto.setKind(InfoDto.KIND_INFO);
		
		return infoDto;
	}
	/**
	 * Entity→DTO変換
	 * @param messageEntity
	 * @return
	 */
	private InfoDto getDtoBy(MessageEntity messageEntity) {
		InfoDto infoDto = new InfoDto();
		if( messageEntity == null ) {
			infoDto.setMsg("この情報は存在しません。");
		}
		
		infoDto.setTitle(messageEntity.getMsg());
		infoDto.setMsg(messageEntity.getMsg());
		infoDto.setLevel( Level.search(messageEntity.getLevel()) );
		infoDto.setInfoDate( Exchange.toLocalDateTime(messageEntity.getRegDate()) );
		infoDto.setAlreadReadFlg( (messageEntity.getReadFlg()==1 ? true:false) );
		infoDto.setId(messageEntity.getMessageId());
		infoDto.setKind(InfoDto.KIND_MSG);

		return infoDto;
	}
	/**
	 * 
	 * Entity→DTO変換
	 * @param infoList
	 * @param unreadList
	 * @param messageList
	 * @return
	 */
	private List<InfoDto> getDtoBy(
			List<InfoEntity> infoList,
			List<UnreadEntity> unreadList,
			List<MessageEntity> messageList ){
		List<InfoDto> list = new ArrayList<>();
		
		for( InfoEntity infoEntity : infoList ) {
			InfoDto infoDto = getDtoBy(infoEntity);
			//未読テーブルにデータがあれば「未読」
			boolean unreadFlg = false;
			for(UnreadEntity unread : unreadList) {
				if( unread.getInfoTbl().getInfoId() == infoEntity.getInfoId() ) {
					unreadFlg = true;
					break;
				}
			}
			infoDto.setAlreadReadFlg(!unreadFlg);
			list.add(infoDto);
		}
		
		for(MessageEntity message : messageList) {
			list.add(getDtoBy(message));
		}
		
		//降順にソート
		list.sort(
				(o1,o2)-> o2.getInfoDate().compareTo(o1.getInfoDate())
				);
		
		return list;
	}
}
