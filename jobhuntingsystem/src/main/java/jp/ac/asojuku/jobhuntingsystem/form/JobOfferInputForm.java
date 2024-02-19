package jp.ac.asojuku.jobhuntingsystem.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class JobOfferInputForm {
	@NotNull(message = "{errmsg0401}" )
	private Integer companyId;

	@NotNull(message = "{errmsg0402}" )
	private Integer jobtype1;
	private Integer jobtype2;
	private Integer jobtype3;
	private Integer jobtype4;
 
	private Integer employmentType;
	private Integer housingAvailability;
	private Integer salaryType;

	@NotNull(message = "{errmsg0403}" )
	@Range(min=0, max=999999,message = "{errmsg0406}" )
	private Integer baseSalaryFor2;
	@NotNull(message = "{errmsg0404}" )
	@Range(min=0, max=999999,message = "{errmsg0406}" )
	private Integer baseSalaryFor3;
	@NotNull(message = "{errmsg0405}" )
	@Range(min=0, max=999999,message = "{errmsg0406}" )
	private Integer baseSalaryFor4;
	private String optionName1;
	private String optionName2;
	private String optionName3;
	private Integer option1For2;
	private Integer option2For2;
	private Integer option3For2;
	private Integer option1For3;
	private Integer option2For3;
	private Integer option3For3;
	private Integer option1For4;
	private Integer option2For4;
	private Integer option3For4;
 
	private String resume;
	private String expectedGraduation;
	private String transcript;
	private String otherInput;
}
