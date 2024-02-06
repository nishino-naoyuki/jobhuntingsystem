package jp.ac.asojuku.jobhuntingsystem.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InfoDetailForm {
	@NotNull(message = "{errmsg0201}")
	private Integer id;
	@NotNull(message = "{errmsg0202}")
	private Integer type;
}
