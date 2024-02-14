package jp.ac.asojuku.jobhuntingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.asojuku.jobhuntingsystem.dto.IndustryDto;
import jp.ac.asojuku.jobhuntingsystem.dto.IndustryKindDto;
import jp.ac.asojuku.jobhuntingsystem.entity.IndustryEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.IndustrykindEntity;
import jp.ac.asojuku.jobhuntingsystem.repository.IndustryRepository;

@Service
public class IndustryService {
	@Autowired
	IndustryRepository industryRepository;
	
	/**
	 * 業界リストを取得する
	 * @return
	 */
	public List<IndustryDto> getAllIndustryDto() {
		List<IndustryDto> list = new ArrayList<>();
		
		List<IndustryEntity> industryEntityList = industryRepository.findAll();
		
		for( IndustryEntity iEntity : industryEntityList ) {
			IndustryDto dto = new IndustryDto();
			dto.setId( iEntity.getIndustryId() );
			dto.setName( iEntity.getName() );
			for( IndustrykindEntity ikEntity : iEntity.getIndustrykindTbl()) {
				IndustryKindDto kDto = new IndustryKindDto();
				kDto.setId(ikEntity.getIndustrykindId());
				kDto.setName(ikEntity.getName());
				dto.addKindList(kDto);
			}
			list.add(dto);
		}
		
		return list;
	}
}
