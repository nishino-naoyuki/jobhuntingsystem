package jp.ac.asojuku.jobhuntingsystem.dto;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CompanyDetailDto {
	private Integer companyId;
	private String companyname;
	private String companynamekana;
	private String companyaddress;
	private String companycode;
	private Integer companycapital;
	private Integer companyestablishment;
	private Integer companyannualsales;
	private String companypic;
	private String companymoc;
	private String companymot;
	private String companyurl;
	private List<IndustryKindDto> industryList;
	
	public void addIndustryList(IndustryKindDto industryKind) {
		if( this.industryList == null ) {
			industryList = new ArrayList<>();
		}
		this.industryList.add(industryKind);
	}
}
