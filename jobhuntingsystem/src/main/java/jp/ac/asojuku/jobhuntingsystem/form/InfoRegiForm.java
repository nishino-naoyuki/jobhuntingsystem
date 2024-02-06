package jp.ac.asojuku.jobhuntingsystem.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InfoRegiForm {
	public static int KIND_INFO = 0;
	public static int KIND_MSG = 1;
	
	@NotEmpty(message = "{errmsg0203}")
	private String title;
	@NotEmpty(message = "{errmsg0204}")
	private String msg;
	@NotNull(message = "{errmsg0205}")
	private Integer level;
	private Integer kind;
	private Integer[] toStudentIdList;
}
