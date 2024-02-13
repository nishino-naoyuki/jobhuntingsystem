package jp.ac.asojuku.jobhuntingsystem.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class IndustryDto {
	private Integer id;
	private String name;
	private List<IndustryKindDto> kindList;
	
	public void addKindList(IndustryKindDto kindDto) {
		if( kindList == null ) {
			kindList = new ArrayList<>();
		}
		kindList.add(kindDto);
	}
}
