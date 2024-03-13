package jp.ac.asojuku.jobhuntingsystem.dto;

import java.util.ArrayList;
import java.util.List;

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
	private List<IndustryKindDto> ikList;
	//活動履歴 todo
	
	public void addIkList(IndustryKindDto ikDto) {
		if( ikList == null ) {
			ikList = new ArrayList<>();
		}
		ikList.add(ikDto);
	}
	
	public boolean isContainList(Integer ikId) {
		if( ikList == null ) {
			return false;
		}
		boolean isContain = false;
		
		for(IndustryKindDto idDto : ikList ) {
			if( ikId == idDto.getId() ) {
				isContain = true;
				break;
			}
		}
		
		return isContain;
	}
}
