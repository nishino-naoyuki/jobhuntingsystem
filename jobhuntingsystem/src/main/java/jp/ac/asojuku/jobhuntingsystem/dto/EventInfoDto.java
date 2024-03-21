package jp.ac.asojuku.jobhuntingsystem.dto;

import java.time.LocalDateTime;

import jp.ac.asojuku.jobhuntingsystem.util.Exchange;
import lombok.Data;

@Data
public class EventInfoDto {
	private Integer eventId;
	private Integer stepId;
	private String stepName;
	private String document;
	private String overview;
	private LocalDateTime start_datetime;
	private LocalDateTime end_datetime;
	private String place;
	private Integer companyId;
	private String companyName;
	private Integer entryNum;
	private String startDatetimeStr;
	private String endDatetimeStr;
	
}
