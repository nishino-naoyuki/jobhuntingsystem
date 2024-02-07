package jp.ac.asojuku.jobhuntingsystem.param.json;

import java.util.List;

import jp.ac.asojuku.jobhuntingsystem.dto.DepartmentDto;
import lombok.Data;

@Data
public class DepartmentJson {
	private String result;
	private List<DepartmentDto> depList;
}
