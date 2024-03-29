package jp.ac.asojuku.jobhuntingsystem.service;

import static jp.ac.asojuku.jobhuntingsystem.repository.RecruitmentSpecifications.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jp.ac.asojuku.jobhuntingsystem.dto.IndustryKindDto;
import jp.ac.asojuku.jobhuntingsystem.dto.JobOfferListDto;
import jp.ac.asojuku.jobhuntingsystem.entity.CompanyEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.IndustrykindEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.JobDetailDto;
import jp.ac.asojuku.jobhuntingsystem.entity.RecruitmentEntity;
import jp.ac.asojuku.jobhuntingsystem.form.JobOfferInputForm;
import jp.ac.asojuku.jobhuntingsystem.form.JobSearchForm;
import jp.ac.asojuku.jobhuntingsystem.param.JobType;
import jp.ac.asojuku.jobhuntingsystem.repository.RecruitmentRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Exchange;

@Service
public class JobService {
	@Autowired
	RecruitmentRepository recruitmentRepository;
	
	private final String DATE_FMT = "yyyy-MM-dd";
	
	/**
	 * 詳細取得
	 * @param recruitmentId
	 * @return
	 */
	public JobDetailDto getDetal(Integer recruitmentId) {
		
		RecruitmentEntity entity = recruitmentRepository.getOne(recruitmentId);
		
		return getDtoFrom(entity);
	}
	/**
	 * 求人検索処理
	 * 
	 * @param jobSearchForm
	 * @return
	 */
	public List<JobOfferListDto> search(JobSearchForm jobSearchForm){
		List<JobOfferListDto> searchResultList = new ArrayList<>();

		List<Integer> industryList = new ArrayList<>();
		if( jobSearchForm.getIndustryArry() != null ) {
			industryList = Arrays.asList(jobSearchForm.getIndustryArry());
		}
		
		List<RecruitmentEntity> entityList = 
				recruitmentRepository.findAll(
				Specification.where(companyContains( jobSearchForm.getCompanyName()) )
				.and( companyKanaContains(jobSearchForm.getCompanyNameKana()) )
				.and( targetYearEqual( jobSearchForm.getTargetYear() ))
				.and( industryContains(industryList) ),
				Sort.by(Sort.Direction.ASC,"publicDate")
				.and( Sort.by(Sort.Direction.ASC,"companyId") )
				);
		
		for( RecruitmentEntity cEntity : entityList ) {
			searchResultList.add(getFrom(cEntity));
		}
		
		return searchResultList;
	}
	
	/**
	 * 求人登録処理
	 * @param jobOfferInputForm
	 */
	public Integer insert(JobOfferInputForm jobOfferInputForm) {
		RecruitmentEntity recruitmentEntity = getFrom( jobOfferInputForm );
		recruitmentEntity = recruitmentRepository.save(recruitmentEntity);
		
		return recruitmentEntity.getRecruitmentId();
	}
	
	/**
	 * 企業が出している求人の一覧を表示する
	 * @param companyId
	 * @return
	 */
	public List<JobOfferListDto> getList(Integer companyId){
		List<JobOfferListDto> list = new ArrayList<>();
		List<RecruitmentEntity> entityList =
					recruitmentRepository.findByCompanyIdOrderByPublicDateDesc(companyId);
		
		for( RecruitmentEntity entity : entityList) {
			list.add(getFrom(entity));
		}
		
		return list;
	}
	
	
	/* -private metod- */
	/**
	 * 
	 * @param entity
	 * @return
	 */
	private JobDetailDto getDtoFrom(RecruitmentEntity entity) {
		JobDetailDto dto = new JobDetailDto();
		
		dto.setCompanyId( entity.getCompanyId() );
		dto.setCompanyName( entity.getCompanyTbl().getName() );
		
		dto.setJobtype1( entity.getIndustryKindId1industrykindTbl().getName() );
		dto.setJobtype2( (entity.getIndustryKindId2industrykindTbl() != null ? entity.getIndustryKindId2industrykindTbl().getName():"") );
		dto.setJobtype3( (entity.getIndustryKindId3industrykindTbl() != null ? entity.getIndustryKindId3industrykindTbl().getName():"") );
		dto.setJobtype4( (entity.getIndustryKindId4industrykindTbl() != null ? entity.getIndustryKindId4industrykindTbl().getName():"") );
		
		dto.setEmploymentType( entity.getType() );
		dto.setHousingAvailability( entity.getDormitory() );
		dto.setSalaryType( entity.getSalaryType() );
		
		dto.setBaseSalaryFor2( entity.getBaseSalaryFor2() );
		dto.setBaseSalaryFor3( entity.getBaseSalaryFor3() );
		dto.setBaseSalaryFor4( entity.getBaseSalaryFor4() );
		
		dto.setOptionName1( entity.getSalaryOp1() );
		dto.setOptionName2( entity.getSalaryOp2() );
		dto.setOptionName3( entity.getSalaryOp3() );
		dto.setOption1For2( entity.getSalaryOp1For2() );
		dto.setOption1For3( entity.getSalaryOp1For3() );
		dto.setOption1For4( entity.getSalaryOp1For4() );
		dto.setOption2For2( entity.getSalaryOp2For2() );
		dto.setOption2For3( entity.getSalaryOp2For3() );
		dto.setOption2For4( entity.getSalaryOp2For4() );
		dto.setOption3For2( entity.getSalaryOp3For2() );
		dto.setOption3For3( entity.getSalaryOp3For3() );
		dto.setOption3For4( entity.getSalaryOp3For4() );
		
		dto.setResume( entity.getRequiredResume() );
		dto.setExpectedGraduation( entity.getRequiredExpected() );
		dto.setTranscript( entity.getRequiredGrades() );
		dto.setOtherInput( entity.getRequiredElse() );
		
		dto.setTargetYear( entity.getTargetYear() );
		dto.setJoboffer( entity.getOfferendFlg() );
		if( entity.getPublicDate() != null ) {
			dto.setPublicDate( Exchange.toLocalDateTime(entity.getPublicDate()) );
		}else {
			dto.setPublicDate(null);
		}
		
		return dto;
	}
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	private JobOfferListDto getFrom(RecruitmentEntity entity) {
		JobOfferListDto dto = new JobOfferListDto();
		dto.setCompanyName(entity.getCompanyTbl().getName());
		dto.setCode(entity.getRecrutimentCode());
		dto.setRecruitmentId(entity.getRecruitmentId());
		dto.setRecruitmentType(entity.getRecrutimentTypeTbl().getName());
		dto.setInOffer(entity.getOfferendFlg() == 0);
		dto.setTarget(entity.getTargetYear());
		Integer type = entity.getType();
		JobType jt = JobType.search(type);
		dto.setType(jt.getMsg());
		dto = addIndustryKindList(dto,entity);
		
		return dto;
	}
	/**
	 * 
	 * @param dto
	 * @param entity
	 * @return
	 */
	private JobOfferListDto addIndustryKindList(JobOfferListDto dto,RecruitmentEntity entity) {
		IndustrykindEntity[] IKArray = {
				entity.getIndustryKindId1industrykindTbl(),
				entity.getIndustryKindId2industrykindTbl(),
				entity.getIndustryKindId3industrykindTbl(),
				entity.getIndustryKindId4industrykindTbl()
		};
		
		for(IndustrykindEntity ikEntity : IKArray) {
			if( ikEntity != null ) {
				IndustryKindDto ikDto = new IndustryKindDto();

				ikDto.setIndustryId(ikEntity.getIndustryId());
				ikDto.setIndustryName(ikEntity.getName());
				ikDto.setId(ikEntity.getIndustrykindId());
				ikDto.setName(ikEntity.getName());
				dto.addIndustryList(ikDto);
			}
		}
		
		return dto;
	}
	
	/**
	 * 
	 * @param jiForm
	 * @return
	 */
	private RecruitmentEntity getFrom(JobOfferInputForm jiForm) {
		RecruitmentEntity entity = new RecruitmentEntity();

		entity.setCompanyId(jiForm.getCompanyId());
		entity.setRecrutimentTypeId(1);
		
		entity.setRecrutimentCode(String.format("%05d", jiForm.getCompanyId()));
		//寮の有無など
		entity.setType(jiForm.getEmploymentType());
		entity.setDormitory( jiForm.getHousingAvailability() );
		entity.setSalaryType(jiForm.getSalaryType());

		//募集職種
		entity.setIndustryKindId1(jiForm.getJobtype1());
		entity.setIndustryKindId2( ( jiForm.getJobtype2() == -1 ? null:jiForm.getJobtype2() ) );
		entity.setIndustryKindId3( ( jiForm.getJobtype3() == -1 ? null:jiForm.getJobtype3() ) );
		entity.setIndustryKindId4( ( jiForm.getJobtype4() == -1 ? null:jiForm.getJobtype4() ) );
		
		//給料
		entity.setBaseSalaryFor2(jiForm.getBaseSalaryFor2());
		entity.setBaseSalaryFor3(jiForm.getBaseSalaryFor3());
		entity.setBaseSalaryFor4(jiForm.getBaseSalaryFor4());
		//オプション
		entity.setSalaryOp1(jiForm.getOptionName1());
		entity.setSalaryOp1For2(jiForm.getOption1For2());
		entity.setSalaryOp1For3(jiForm.getOption1For3());
		entity.setSalaryOp1For4(jiForm.getOption1For4());
		entity.setSalaryOp2(jiForm.getOptionName2());
		entity.setSalaryOp2For2(jiForm.getOption2For2());
		entity.setSalaryOp2For3(jiForm.getOption2For3());
		entity.setSalaryOp2For4(jiForm.getOption2For4());
		entity.setSalaryOp3(jiForm.getOptionName3());
		entity.setSalaryOp3For2(jiForm.getOption3For2());
		entity.setSalaryOp3For3(jiForm.getOption3For3());
		entity.setSalaryOp3For4(jiForm.getOption3For4());
		
		//必要書類
		entity.setRequiredResume( (jiForm.getResume().equals("1") ? 1:0) );
		entity.setRequiredExpected( (jiForm.getExpectedGraduation().equals("1") ? 1:0) );
		entity.setRequiredGrades( (jiForm.getTranscript().equals("1") ? 1:0) );
		entity.setRequiredElse( jiForm.getOtherInput() );
		
		//対象・募集状況・情報公開開始日
		entity.setTargetYear( jiForm.getTargetYear() );
		entity.setOfferendFlg( jiForm.getJoboffer() );
		try {
			entity.setPublicDate( Exchange.toDate(jiForm.getPublicDate(),DATE_FMT) );
		} catch (Exception e) {
			entity.setPublicDate(null);
		}
		
		return entity;
	}
}
