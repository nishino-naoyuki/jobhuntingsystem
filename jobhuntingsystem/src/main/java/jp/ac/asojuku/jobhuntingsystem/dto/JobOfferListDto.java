package jp.ac.asojuku.jobhuntingsystem.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class JobOfferListDto {
	private Integer recruitmentId;
	private String code;
	private String type;
	private String recruitmentType;
	private boolean inOffer;
	private int target;
	private List<IndustryKindDto> industryList;

	public void addIndustryList(IndustryKindDto industryKind) {
		if( this.industryList == null ) {
			industryList = new ArrayList<>();
		}
		this.industryList.add(industryKind);
	}
	
}
