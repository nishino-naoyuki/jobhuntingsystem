package jp.ac.asojuku.jobhuntingsystem.param.json;

import java.util.List;

import lombok.Data;

@Data
public class CompanyListJson {
	private String result;
	List<CompanyElement> companyList;
}
