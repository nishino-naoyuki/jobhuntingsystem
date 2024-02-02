package jp.ac.asojuku.jobhuntingsystem.dto;

import lombok.Data;

@Data
public class LoginInfoDto {
	private Integer uid;
	private String name;
	private String mail;
	private String roleName;
	private boolean admin;
	private boolean student;
	private boolean company;
	
	public boolean isAdmin() {
		return admin;
	}
	public boolean isStudent() {
		return !student;
	}
	
	public boolean isCompany() {
		return company;
	}
}
