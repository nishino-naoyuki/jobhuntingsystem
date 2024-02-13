package jp.ac.asojuku.jobhuntingsystem.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CompanyRegiForm {
	@NotEmpty(message = "{errmsg0301}")
	private String companyname;
	@NotEmpty(message = "{errmsg0302}")
	private String companynamekana;
	@NotEmpty(message = "{errmsg0303}")
	private String companyaddress;
	private String companycode;
	@NotEmpty(message = "{errmsg0304}")
	private String companycapital;
	private String companyestablishment;
	private String companyannualsales;
	@NotEmpty(message = "{errmsg0305}")
	private String companypic;
	@NotEmpty(message = "{errmsg0306}")
	private String companymoc;
	private String companymot;
	@NotEmpty(message = "{errmsg0307}")
	private String companyurl;
	private String[] industryArry;
}
