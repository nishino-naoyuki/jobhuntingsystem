package jp.ac.asojuku.jobhuntingsystem.form;

import lombok.Data;

@Data
public class PasswordChangeForm {
	private String oldPassword;
	private String newPassword;
}
