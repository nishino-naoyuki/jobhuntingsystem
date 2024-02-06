package jp.ac.asojuku.jobhuntingsystem.param.json;

import lombok.Data;

@Data
public class ErrorField {
	private String field;
	private String msg;
}
