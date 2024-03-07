package jp.ac.asojuku.jobhuntingsystem.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EventInfoDto {
	private Integer eventId;
	private Integer stepId;
	private String stepName;
	private String document;
	private LocalDateTime start_datetime;
	private LocalDateTime end_datetime;
	private String place;
	private Integer companyId;
	private String companyName;
	private Integer entryNum;
}
