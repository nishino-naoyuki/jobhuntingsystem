package jp.ac.asojuku.jobhuntingsystem.dto;

import lombok.Data;

@Data
public class DepartmentDto {

	private Integer departmentId;

	/** name. */
	private String name;

	/** 学校テーブル. */
	private SchoolDto school;

	/** code. */
	private String code;
}
