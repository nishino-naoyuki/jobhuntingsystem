package jp.ac.asojuku.jobhuntingsystem.param.json;

import java.util.List;

import jp.ac.asojuku.jobhuntingsystem.param.Level;
import lombok.Data;

@Data
public class InfoDetailJson {
	private String title;
	private String msg;
	private Level level;
	private String result;
	private List<ErrorField> errorList;
}
