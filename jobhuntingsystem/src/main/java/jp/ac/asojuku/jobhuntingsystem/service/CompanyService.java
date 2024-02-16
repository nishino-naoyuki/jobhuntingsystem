package jp.ac.asojuku.jobhuntingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ac.asojuku.jobhuntingsystem.entity.CompanyEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.CompanyIndustryEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.IndustrykindEntity;
import jp.ac.asojuku.jobhuntingsystem.form.CompanyRegiForm;
import jp.ac.asojuku.jobhuntingsystem.repository.CompanyIndustryRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.CompanyRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.IndustryKindRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Digest;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	IndustryKindRepository industryKindRepository;
	@Autowired
	CompanyIndustryRepository companyIndustryRepository;

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
	
	/* -private method- */
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
}
