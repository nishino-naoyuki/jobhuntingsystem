package jp.ac.asojuku.jobhuntingsystem.csv;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class StudentCSV {
	@CsvBindByName(column = "sectioncode", required = true) 
	private String sectioncode;
	@CsvBindByName(column = "sectionmidname", required = true) 
	private String sectionmidname;
	@CsvBindByName(column = "grade", required = true) 
	private String grade;
	@CsvBindByName(column = "classroom", required = true) 
	private String classroom;
	@CsvBindByName(column = "teacher", required = true) 
	private String teacher;
	@CsvBindByName(column = "studentno", required = true) 
	private String studentno;
	@CsvBindByName(column = "studentname", required = true) 
	private String studentname;
	@CsvBindByName(column = "studentnamek", required = true) 
	private String studentnamek;
	@CsvBindByName(column = "sex", required = true) 
	private String sex;
	@CsvBindByName(column = "birthday", required = true) 
	private String birthday;
	@CsvBindByName(column = "postcode", required = true) 
	private String postcode;
	@CsvBindByName(column = "address1", required = false) 
	private String address1;
	@CsvBindByName(column = "address2", required = false) 
	private String address2;
	@CsvBindByName(column = "address3", required = false) 
	private String address3;
	@CsvBindByName(column = "telephone", required = true) 
	private String telephone;
	@CsvBindByName(column = "otherpostcode", required = false) 
	private String otherpostcode;
	@CsvBindByName(column = "otheraddress1", required = false) 
	private String otheraddress1;
	@CsvBindByName(column = "otheraddress2", required = false) 
	private String otheraddress2;
	@CsvBindByName(column = "otheraddress3", required = false) 
	private String otheraddress3;
	@CsvBindByName(column = "othertelephone", required = true) 
	private String othertelephone;
	@CsvBindByName(column = "schoolname", required = true) 
	private String schoolname;
	@CsvBindByName(column = "attendancenumber", required = true) 
	private String attendancenumber;
	@CsvBindByName(column = "sort", required = false) 
	private String sort;
	@CsvBindByName(column = "sectionshortname", required = true) 
	private String sectionshortname;
	@CsvBindByName(column = "systemkbn", required = true) 
	private String systemkbn;
	@CsvBindByName(column = "mainsort", required = true) 
	private String mainsort;
	@CsvBindByName(column = "bikou", required = false) 
	private String bikou;


}
