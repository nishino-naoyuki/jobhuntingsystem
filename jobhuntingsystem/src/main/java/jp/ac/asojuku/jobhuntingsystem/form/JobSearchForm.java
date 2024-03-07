package jp.ac.asojuku.jobhuntingsystem.form;

import lombok.Data;

@Data
public class JobSearchForm {
	private String companyName;
	private String companyNameKana;
	private Integer targetYear;
	private Integer[] industryArry;
}
