package jp.ac.asojuku.jobhuntingsystem.dto;

import lombok.Data;

@Data
public class FavoritDto {
	private Integer studentId;
	private String studentName;
	private Integer companyId;
	private String companyName;
	private String registryDatetime;
}
