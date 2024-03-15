package jp.ac.asojuku.jobhuntingsystem.form;

import lombok.Data;

@Data
public class EventSearchForm {
	private String companyName;
	private Integer[] stepArry;
	private Integer[] industryArry;
	private String startDateStr;
	private String endDateStr;	
}
