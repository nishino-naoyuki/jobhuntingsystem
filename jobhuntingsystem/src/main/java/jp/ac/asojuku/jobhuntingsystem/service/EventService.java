package jp.ac.asojuku.jobhuntingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.asojuku.jobhuntingsystem.dto.EventInfoDto;
import jp.ac.asojuku.jobhuntingsystem.entity.EventEntity;
import jp.ac.asojuku.jobhuntingsystem.repository.EventRepository;

@Service
public class EventService {
	
	@Autowired
	EventRepository eventRepository;

	public List<EventInfoDto> getList(Integer companyId){
		
		List<EventEntity> entityList = 
				eventRepository.findByCompanyIdOrderByStartDatetimeDesc(companyId);
		
		
	}
}
