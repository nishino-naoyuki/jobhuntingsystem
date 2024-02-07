package jp.ac.asojuku.jobhuntingsystem.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserRegiInputDto {
	private List<SchoolDto> schoolList;
}
