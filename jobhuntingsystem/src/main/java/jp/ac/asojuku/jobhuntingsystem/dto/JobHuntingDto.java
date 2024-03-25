package jp.ac.asojuku.jobhuntingsystem.dto;

import java.util.Date;

import jp.ac.asojuku.jobhuntingsystem.param.JHStatus;
import lombok.Data;

@Data
public class JobHuntingDto {
	private String companyName;
	private Integer companyId;
	private String stepName;
	private Integer stepId;
	private JHStatus status;
	private Date eventDate;
	private Integer needReport;
	
	public String getStatusName() {
		return status.getMsg();
	}
	
	public boolean isNeedReport() {
		return needReport == 1;
	}
}
