package jp.ac.asojuku.jobhuntingsystem.param.json;

import java.util.List;

import jp.ac.asojuku.jobhuntingsystem.dto.ClassDto;
import lombok.Data;

@Data
public class ClassJson {
	private String result;
	private List<ClassDto> clsList;
}
