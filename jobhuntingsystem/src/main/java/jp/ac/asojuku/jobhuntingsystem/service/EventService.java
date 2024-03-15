package jp.ac.asojuku.jobhuntingsystem.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ac.asojuku.jobhuntingsystem.dto.EventInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.StepDto;
import jp.ac.asojuku.jobhuntingsystem.entity.EventEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.JobHuntingDetailEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StepEntity;
import jp.ac.asojuku.jobhuntingsystem.form.EventForm;
import jp.ac.asojuku.jobhuntingsystem.form.EventSearchForm;
import jp.ac.asojuku.jobhuntingsystem.form.EventsForm;
import jp.ac.asojuku.jobhuntingsystem.repository.EventRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.JobHuntingDetailRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.StepRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Exchange;

import static jp.ac.asojuku.jobhuntingsystem.repository.EventSpecifications.*;

@Service
public class EventService {
	private final String DATETIME_FMT = "yyyy-MM-dd'T'HH:mm";
	private final String DATETIME_FMT_DISP = "yyyy-MM-dd HH:mm";
	
	@Autowired
	EventRepository eventRepository;
	@Autowired
	StepRepository stepRepository;
	@Autowired
	JobHuntingDetailRepository jobHuntingDetailRepository;

	/**
	 * イベント情報検索処理
	 * @param eventSearchForm
	 * @return
	 * @throws ParseException
	 */
	public List<EventInfoDto> search(EventSearchForm eventSearchForm) throws ParseException{
		List<EventInfoDto> list = new ArrayList<>();

		List<Integer> stepList = new ArrayList<>();
		if( eventSearchForm.getStepArry() != null ) {
			stepList = Arrays.asList(eventSearchForm.getStepArry());
		}
		List<Integer> industryList = new ArrayList<>();
		if( eventSearchForm.getIndustryArry() != null ) {
			industryList = Arrays.asList(eventSearchForm.getIndustryArry());
		}
		
		List<EventEntity> eventEntityList = eventRepository.findAll(
				Specification.where(companyContains( eventSearchForm.getCompanyName() ) )
							.and(startDateGreaterThan( Exchange.toDate(eventSearchForm.getStartDateStr(), DATETIME_FMT)  ))
							.and(endDateLessThan( Exchange.toDate(eventSearchForm.getEndDateStr(), DATETIME_FMT)  ))
							.and(stepContains(stepList))
							.and(industryContains(industryList)),
							Sort.by(Sort.Direction.DESC,"startDatetime")
							.and( Sort.by(Sort.Direction.ASC,"stepId") )
				);
		
		for(EventEntity eEntity : eventEntityList) {
			list.add( getFrom(eEntity) );
		}
		
		return list;
	}
	/**
	 * イベントの更新、削除、追加をいっぺんに行う
	 * @param eventsForm
	 * @throws ParseException
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertUpdateDelete(EventsForm eventsForm) throws ParseException {
		//更新
		update(eventsForm.getEditEvents(),eventsForm.getCompanyId(),eventsForm.getRecruitmentId());
		//削除
		delete(eventsForm.getDelEvents());
		//挿入
		insert(eventsForm.getAddEvents(),eventsForm.getCompanyId(),eventsForm.getRecruitmentId());
	}
	/**
	 * 挿入する
	 * @param eventsForm
	 * @param companyId
	 * @param recruitmentId
	 * @throws ParseException
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insert(EventsForm eventsForm) throws ParseException {
		
		insert(eventsForm.getAddEvents(),eventsForm.getCompanyId(),eventsForm.getRecruitmentId());
	}
	
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
	 * EventInfoDtoのリストを取得する
	 * 
	 * @param companyId
	 * @return
	 */
	public List<EventInfoDto> getList(Integer companyId){
		List<EventInfoDto> list = new ArrayList<>();
		List<EventEntity> entityList = 
				eventRepository.findByCompanyIdOrderByStartDatetimeDesc(companyId);
		
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
		List<JobHuntingDetailEntity> entryList =
				jobHuntingDetailRepository.findByEventIdOrderByStepStartDatetime(entity.getEventId());
		dto.setEntryNum(entryList.size());
		dto.setStartDatetimeStr(Exchange.toFormatString(entity.getStartDatetime(),DATETIME_FMT_DISP));
		dto.setEndDatetimeStr(Exchange.toFormatString(entity.getEndDatetime(),DATETIME_FMT_DISP));
		
		return dto;
	}
	

	private void insert(List<EventForm> addEvents,Integer companyId,Integer recruitmentId) throws ParseException {
		if( addEvents == null ) {
			return;
		}
		List<EventEntity> evEntityList = new ArrayList<>();
		
		for(EventForm eForm : addEvents ) {
			EventEntity eEntity = new EventEntity();
			eEntity.setName("");
			eEntity.setPlace( eForm.getPlace() );
			eEntity.setDocument( eForm.getDocumentStr() );
			eEntity.setStartDatetime( Exchange.toDate(eForm.getStartDateStr(), DATETIME_FMT) );
			eEntity.setEndDatetime( Exchange.toDate(eForm.getEndDateStr(), DATETIME_FMT) );
			eEntity.setStepId( eForm.getKind() );
			eEntity.setCompanyId( companyId );
			eEntity.setRecruitmentId( recruitmentId );
			evEntityList.add(eEntity);
		}
		eventRepository.saveAll(evEntityList);
	}

	private void update(List<EventForm> editEvents,Integer companyId,Integer recruitmentId) throws ParseException {
		if( editEvents == null ) {
			return;
		}
		List<EventEntity> evEntityList = new ArrayList<>();
		
		for(EventForm eForm : editEvents ) {
			EventEntity eEntity = eventRepository.getOne(eForm.getEventId());
			eEntity.setName("");
			eEntity.setPlace( eForm.getPlace() );
			eEntity.setDocument( eForm.getDocumentStr() );
			eEntity.setStartDatetime( Exchange.toDate(eForm.getStartDateStr(), DATETIME_FMT) );
			eEntity.setEndDatetime( Exchange.toDate(eForm.getEndDateStr(), DATETIME_FMT) );
			eEntity.setStepId( eForm.getKind() );
			eEntity.setCompanyId( companyId );
			eEntity.setRecruitmentId( recruitmentId );
			evEntityList.add(eEntity);
		}
		eventRepository.saveAll(evEntityList);
	}
	

	private void delete(List<EventForm> delEvents) throws ParseException {
		if( delEvents == null ) {
			return;
		}
		
		for(EventForm eForm : delEvents ) {
			List<JobHuntingDetailEntity> entryList =
				jobHuntingDetailRepository.findByEventIdOrderByStepStartDatetime(eForm.getEventId());
			if( entryList.size() == 0) {
				//外部キーを張っているデータがない場合のみ即削除
				EventEntity eEntity = eventRepository.getOne(eForm.getEventId());
				eventRepository.delete(eEntity);
			}
		}
	}
}
