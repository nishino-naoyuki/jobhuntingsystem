package jp.ac.asojuku.jobhuntingsystem.form;

import java.util.List;

import lombok.Data;

@Data
public class EventsForm {
	private Integer companyId;
	private Integer recruitmentId;
	private List<EventForm> addEvents;
	private List<EventForm> editEvents;
}
