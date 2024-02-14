package jp.ac.asojuku.jobhuntingsystem.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import lombok.Data;

@Data
public class CompanyRegiForm {
	@NotEmpty(message = "{errmsg0301}")
	@Size(max = 200, message="{errmsg0308}")
	private String companyname;
	@NotEmpty(message = "{errmsg0302}")
	@Size(max = 400, message="{errmsg0309}")
	private String companynamekana;
	@NotEmpty(message = "{errmsg0303}")
	@Size(max = 400, message="{errmsg0310}")
	private String companyaddress;
	private String companycode;
	@NotEmpty(message = "{errmsg0304}")
	@Range(min=0, max=999999,message = "{errmsg0311}" )
	private String companycapital;
	@Range(min=1000, max=9999,message = "{errmsg0312}" )
	private String companyestablishment;
	@Range(min=0, max=999999,message = "{errmsg0313}" )
	private String companyannualsales;
	@NotEmpty(message = "{errmsg0305}")
	private String companypic;
	@NotEmpty(message = "{errmsg0306}")
	@Email(message = "{errmsg0314}")
	private String companymoc;
	private String companymot;
	@NotEmpty(message = "{errmsg0307}")
	@URL(message = "{errmsg0315}")
	private String companyurl;
	private String password;
	private Integer[] industryArry;
}
