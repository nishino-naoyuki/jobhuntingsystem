package jp.ac.asojuku.jobhuntingsystem.dto;

import lombok.Data;

@Data
public class StudentDto {
	private Integer studentId;
	private String name;
	private String studentNo;
	private Integer jobhuntingStatusId;
	private String jobhuntingStatusName;
	private Integer classId;
	private String className;
	private String teacherName;
	//活動履歴 todo
}
