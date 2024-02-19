package jp.ac.asojuku.jobhuntingsystem.form;

import lombok.Data;

@Data
public class CompanySearchForm {
	private String companyName;
	private String companyAddress;
	private Integer[] industryArry;
}
