package jp.ac.asojuku.jobhuntingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.asojuku.jobhuntingsystem.entity.RecruitmentEntity;
import jp.ac.asojuku.jobhuntingsystem.form.JobOfferInputForm;
import jp.ac.asojuku.jobhuntingsystem.repository.RecruitmentRepository;

@Service
public class JobService {
	@Autowired
	RecruitmentRepository recruitmentRepository;
	
	/**
	 * 求人登録処理
	 * @param jobOfferInputForm
	 */
	public void insert(JobOfferInputForm jobOfferInputForm) {
		RecruitmentEntity recruitmentEntity = getFrom( jobOfferInputForm );
		recruitmentRepository.save(recruitmentEntity);
	}
	
	/* -private metod- */
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
		entity.setIndustryKindId2(jiForm.getJobtype2());
		entity.setIndustryKindId3(jiForm.getJobtype3());
		entity.setIndustryKindId4(jiForm.getJobtype4());
		
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
		
		return entity;
	}
}
