package jp.ac.asojuku.jobhuntingsystem.dto;

import lombok.Data;

@Data
public class JobOfferListDto {
	private Integer recruitmentId;
	private String code;
	private String type;
	private String recruitmentType;
}
