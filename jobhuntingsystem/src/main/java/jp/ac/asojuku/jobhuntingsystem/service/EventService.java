package jp.ac.asojuku.jobhuntingsystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.asojuku.jobhuntingsystem.dto.EventInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.StepDto;
import jp.ac.asojuku.jobhuntingsystem.entity.EventEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StepEntity;
import jp.ac.asojuku.jobhuntingsystem.repository.EventRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.StepRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Exchange;

@Service
public class EventService {
	
	@Autowired
	EventRepository eventRepository;
	@Autowired
	StepRepository stepRepository;

	/**
	 * EventInfoDtoのリストを取得する
	 * 
	 * @param companyId
	 * @return
	 */
	public List<EventInfoDto> getList(Integer companyId,Integer recruitmentId){
		List<EventInfoDto> list = new ArrayList<>();
		List<EventEntity> entityList = 
				eventRepository.findByCompanyIdAndRecruitmentIdOrderByStartDatetimeDesc(companyId,recruitmentId);
		
		for( EventEntity entity : entityList ) {
			list.add( getFrom(entity) );
		}
		
		return list;
	}
	
	/**
	 * Stepリストの取得
	 * 
	 * @return
	 */
	public List<StepDto> getAllStepList(){
		List<StepDto> list = new ArrayList<>();
		List<StepEntity> stepEntityList = stepRepository.findAll();
		
		for( StepEntity entity : stepEntityList) {
			StepDto dto = new StepDto();
			dto.setId(entity.getStepId());
			dto.setName(entity.getName());
			list.add(dto);
		}
		
		return list;
	}
	/* -private methods- */
	/**
	 * Entity→DTO変換
	 * 
	 * @param entity
	 * @return
	 */
	private EventInfoDto getFrom(EventEntity entity) {
		EventInfoDto dto = new EventInfoDto();
		
		dto.setEventId(entity.getEventId());
		dto.setStepId(entity.getStepTbl().getStepId());
		dto.setStepName(entity.getStepTbl().getName());
		dto.setDocument(entity.getDocument());
		dto.setStart_datetime(Exchange.toLocalDateTime(entity.getStartDatetime()));
		dto.setEnd_datetime(Exchange.toLocalDateTime(entity.getEndDatetime()));
		dto.setPlace( entity.getPlace() );
		dto.setCompanyId( entity.getCompanyTbl().getCompanyId() );
		dto.setCompanyName( entity.getCompanyTbl().getName() );
		
		return dto;
	}
}
