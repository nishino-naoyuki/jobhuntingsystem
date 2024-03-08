package jp.ac.asojuku.jobhuntingsystem.entity;

import java.time.LocalDateTime;

import jp.ac.asojuku.jobhuntingsystem.param.JobType;
import jp.ac.asojuku.jobhuntingsystem.param.SalaryType;
import jp.ac.asojuku.jobhuntingsystem.util.Exchange;
import lombok.Data;

@Data
public class JobDetailDto {

	private Integer companyId;
	private String companyName;
	
	private String jobtype1;
	private String jobtype2;
	private String jobtype3;
	private String jobtype4;
 
	private Integer employmentType;
	private Integer housingAvailability;
	private Integer salaryType;

	private Integer baseSalaryFor2;
	private Integer baseSalaryFor3;
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
 
	private Integer resume;
	private Integer expectedGraduation;
	private Integer transcript;
	private String otherInput;
	private Integer targetYear;
	private Integer joboffer;
	private LocalDateTime publicDate;
	
	public String getPublicDateString() {
		return Exchange.toFormatString(publicDate, "yyyy/MM/dd");
	}
	
	public String getEmploymentTypeStr() {
		return JobType.toString( employmentType );
	}

	public String getSalaryTypeStr() {
		return SalaryType.toString( salaryType );
	}
}
