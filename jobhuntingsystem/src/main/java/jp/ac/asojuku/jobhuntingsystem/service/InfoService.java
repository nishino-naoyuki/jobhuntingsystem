package jp.ac.asojuku.jobhuntingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.asojuku.jobhuntingsystem.dto.InfoDto;
import jp.ac.asojuku.jobhuntingsystem.entity.InfoEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.MessageEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.UnreadEntity;
import jp.ac.asojuku.jobhuntingsystem.param.Level;
import jp.ac.asojuku.jobhuntingsystem.repository.InfoRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.MessageRepository;
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
	
	/* -private method- */
	private List<InfoDto> getDtoBy(
			List<InfoEntity> infoList,
			List<UnreadEntity> unreadList,
			List<MessageEntity> messageList ){
		List<InfoDto> list = new ArrayList<>();
		
		for( InfoEntity info : infoList ) {
			InfoDto infoDto = new InfoDto();
			infoDto.setTitle(info.getTitle());
			infoDto.setMsg(info.getMsg());
			infoDto.setLevel( Level.search(info.getLevel()) );
			infoDto.setInfoDate( Exchange.toLocalDateTime(info.getInfoDate()) );
			//未読テーブルにデータがあれば「未読」
			boolean unreadFlg = false;
			for(UnreadEntity unread : unreadList) {
				if( unread.getInfoTbl().getInfoId() == info.getInfoId() ) {
					unreadFlg = true;
					break;
				}
			}
			infoDto.setAlreadReadFlg(!unreadFlg);
			list.add(infoDto);
		}
		
		for(MessageEntity message : messageList) {
			InfoDto infoDto = new InfoDto();
			
			infoDto.setTitle(message.getMsg());
			infoDto.setMsg(message.getMsg());
			infoDto.setLevel( Level.search(message.getLevel()) );
			infoDto.setInfoDate( Exchange.toLocalDateTime(message.getRegDate()) );
			infoDto.setAlreadReadFlg( (message.getReadFlg()==1 ? true:false) );

			list.add(infoDto);
		}
		
		//降順にソート
		list.sort(
				(o1,o2)-> o2.getInfoDate().compareTo(o1.getInfoDate())
				);
		
		return list;
	}
}
