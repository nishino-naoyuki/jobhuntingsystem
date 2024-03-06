package jp.ac.asojuku.jobhuntingsystem.form;

import lombok.Data;

@Data
public class EventForm {
	private Integer eventId;
	private Integer kind;
	private String place;
	private String startDateStr;
	private String endDateStr;
	private String documentStr;
}
