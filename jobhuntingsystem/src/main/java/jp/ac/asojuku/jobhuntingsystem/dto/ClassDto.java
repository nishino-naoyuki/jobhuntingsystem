package jp.ac.asojuku.jobhuntingsystem.dto;


import lombok.Data;

@Data
public class ClassDto {

	private Integer classId;
	private String name;
	private DepartmentDto departmentTbl;
}
