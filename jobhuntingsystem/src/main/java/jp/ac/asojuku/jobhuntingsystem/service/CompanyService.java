package jp.ac.asojuku.jobhuntingsystem.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ac.asojuku.jobhuntingsystem.dto.CompanyDetailDto;
import jp.ac.asojuku.jobhuntingsystem.dto.CompanyInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.IndustryDto;
import jp.ac.asojuku.jobhuntingsystem.dto.IndustryKindDto;
import jp.ac.asojuku.jobhuntingsystem.dto.RecrutimentTypeDto;
import jp.ac.asojuku.jobhuntingsystem.entity.CompanyEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.CompanyIndustryEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.IndustrykindEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.RecrutimentTypeEntity;
import jp.ac.asojuku.jobhuntingsystem.form.CompanyRegiForm;
import jp.ac.asojuku.jobhuntingsystem.form.CompanySearchForm;
import jp.ac.asojuku.jobhuntingsystem.repository.CompanyIndustryRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.CompanyRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.IndustryKindRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.RecruitmentTypeRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Digest;
import static jp.ac.asojuku.jobhuntingsystem.repository.CompanySpecifications.*;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	IndustryKindRepository industryKindRepository;
	@Autowired
	CompanyIndustryRepository companyIndustryRepository;
	@Autowired
	RecruitmentTypeRepository recruitmentTypeRepository;

	/**
	 * 企業の登録を行う
	 * @param companyRegiForm
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insert(CompanyRegiForm companyRegiForm) {
		//企業登録
		CompanyEntity cEntity = getFrom(companyRegiForm);
		cEntity = companyRepository.save(cEntity);
		//企業の募集職種を登録
		List<CompanyIndustryEntity> ciEntityList = new ArrayList<>();
		for(int kCode : companyRegiForm.getIndustryArry()) {
			IndustrykindEntity idEneity = industryKindRepository.getOne(kCode);
			CompanyIndustryEntity ciEntity = new CompanyIndustryEntity();
			
			ciEntity.setCompanyId(cEntity.getCompanyId());
			ciEntity.setIndustrykindId(idEneity.getIndustrykindId());
			ciEntityList.add(ciEntity);
		}
		if( ciEntityList.size() > 0 ) {
			companyIndustryRepository.saveAll(ciEntityList);			
		}
	}
	
	/**
	 * 求人タイプ取得
	 * @return
	 */
	public List<RecrutimentTypeDto> getRecrutimentTypeAllList(){
		List<RecrutimentTypeDto> list = new ArrayList<>();
		List<RecrutimentTypeEntity> entityList = recruitmentTypeRepository.findAll();
		
		for( RecrutimentTypeEntity entity : entityList ) {
			RecrutimentTypeDto dto = new RecrutimentTypeDto();
			
			dto.setId(entity.getRecrutimentTypeId());
			dto.setName(entity.getName());
			
			list.add(dto);
		}
		
		return list;
	}
	
	/**
	 * 企業検索処理
	 * @param scForm
	 * @return
	 */
	public List<CompanyInfoDto> search(CompanySearchForm scForm){
		List<CompanyInfoDto> searchResultList = new ArrayList<>();
		List<Integer> industryList = new ArrayList<>();
		if( scForm.getIndustryArry() != null ) {
			industryList = Arrays.asList(scForm.getIndustryArry());
		}
		
		List<CompanyEntity> entityList = 
				companyRepository.findAll(
				Specification.where(companyContains( scForm.getCompanyName()) )
				.and( adressContains(scForm.getCompanyAddress()) )
				.and( industryContains(industryList) ),
				Sort.by(Sort.Direction.ASC,"furigana")
				.and( Sort.by(Sort.Direction.ASC,"companyId") )
				);
		
		for( CompanyEntity cEntity : entityList ) {
			searchResultList.add(getFrom(cEntity));
		}
		
		return searchResultList;
	}
	
	/**
	 * 企業の詳細情報を取得
	 * @param companyId
	 * @return
	 */
	public CompanyDetailDto getDetail(Integer companyId) {
		CompanyEntity cEntity = companyRepository.getOne(companyId);
		CompanyDetailDto dto = null;
		if( cEntity != null ) {
			dto =getDetailFrom(cEntity);
		}
		return dto;
	}
	
	/* -private method- */
	private CompanyDetailDto getDetailFrom(CompanyEntity cEntity) {
		CompanyDetailDto dto = new CompanyDetailDto();

		dto.setCompanyId(cEntity.getCompanyId());
		dto.setCompanyname(cEntity.getName());
		dto.setCompanynamekana(cEntity.getFurigana());
		dto.setCompanyaddress(cEntity.getAddress());
		dto.setCompanycode(cEntity.getCode());
		dto.setCompanycapital(cEntity.getCapital());
		dto.setCompanyestablishment(cEntity.getEstablishment());
		dto.setCompanyannualsales(cEntity.getCompanyId());
		dto.setCompanypic(cEntity.getPic());
		dto.setCompanymoc(cEntity.getMoc());
		dto.setCompanymot(cEntity.getToc());
		dto.setCompanyurl(cEntity.getUrl());
		
		for( CompanyIndustryEntity ciEntity : cEntity.getCompanyIndustryTbl() ) {
			IndustryKindDto ikDto = new IndustryKindDto();
			ikDto.setIndustryId(ciEntity.getIndustrykindTbl().getIndustryId());
			ikDto.setIndustryName(ciEntity.getIndustrykindTbl().getIndustryTbl().getName());
			ikDto.setId(ciEntity.getIndustrykindId());
			ikDto.setName(ciEntity.getIndustrykindTbl().getName());	
			dto.addIndustryList(ikDto);
		}
		return dto;
	}
	private CompanyEntity getFrom(CompanyRegiForm form) {
		CompanyEntity cEntity = new CompanyEntity();
		
		cEntity.setName( form.getCompanyname() );
		cEntity.setFurigana(form.getCompanynamekana());
		cEntity.setAddress(form.getCompanyaddress());
		cEntity.setAnnualsales(Integer.parseInt(form.getCompanyannualsales()));
		cEntity.setCapital(Integer.parseInt(form.getCompanycapital()));
		cEntity.setCode("0000");
		cEntity.setEstablishment(Integer.parseInt(form.getCompanyestablishment()));
		cEntity.setUrl(form.getCompanyurl());
		cEntity.setPic(form.getCompanypic());
		cEntity.setMoc(form.getCompanymoc());
		cEntity.setToc(form.getCompanymot());
		cEntity.setPassword( Digest.createPassword(form.getCompanymoc(), form.getPassword()) );
		
		return cEntity;
	}
	
	/**
	 * Entity→DTO取得
	 * @param cEntity
	 * @return
	 */
	private CompanyInfoDto getFrom(CompanyEntity cEntity) {
		CompanyInfoDto dto = new CompanyInfoDto();
		
		dto.setId(cEntity.getCompanyId());
		dto.setName(cEntity.getName());
		dto.setKana(cEntity.getFurigana());
		
		return dto;
	}
}
