package jp.ac.asojuku.jobhuntingsystem.param.json;

import java.util.List;

import lombok.Data;

@Data
public class ResultJson {
	private String result;
	private List<ErrorField> errorList;
	private String value;
}
