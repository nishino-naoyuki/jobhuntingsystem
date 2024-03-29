package jp.ac.asojuku.jobhuntingsystem.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserInputForm {
	private Integer roleselect;
	private Integer schoolselect;
	private Integer departmentselect;
	private Integer classselect;
	@NotEmpty(message = "{errmsg0101}")
	private String name;
	@NotEmpty(message = "{errmsg0102}")
	@Email(message = "{errmsg0103}")
	private String mail;
	@NotEmpty(message = "{errmsg0104}")
	private String password;
	private String studentNo;
	private String address;
	private Integer year;	//卒業年
	private String tel;	//電話番後う
	
}
