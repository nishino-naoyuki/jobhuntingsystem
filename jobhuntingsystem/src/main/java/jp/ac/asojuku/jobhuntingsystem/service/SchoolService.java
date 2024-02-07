package jp.ac.asojuku.jobhuntingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.asojuku.jobhuntingsystem.dto.ClassDto;
import jp.ac.asojuku.jobhuntingsystem.dto.DepartmentDto;
import jp.ac.asojuku.jobhuntingsystem.dto.SchoolDto;
import jp.ac.asojuku.jobhuntingsystem.entity.ClassEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.DepartmentEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.SchoolEntity;
import jp.ac.asojuku.jobhuntingsystem.repository.ClassRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.DepartmentRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.SchoolRepository;

@Service
public class SchoolService {
	
	@Autowired
	SchoolRepository schoolRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	ClassRepository classRepository;
	
	/**
	 * 学校の一覧を取得する
	 * @return
	 */
	public List<SchoolDto> getSchoolList(){
		List<SchoolEntity> sEntityList = schoolRepository.findAll();
		
		List<SchoolDto> list = new ArrayList<>();
		for( SchoolEntity entity : sEntityList) {
			list.add( getFrom(entity) );
		}
		
		return list;
	}
	
	/**
	 * 指定した学校に属している学科の一覧を取得する
	 * @param schoolId
	 * @return
	 */
	public List<DepartmentDto> getDepartmentList(int schoolId){
		List<DepartmentEntity> dEntityList = departmentRepository.findBySchoolId(schoolId);
		
		List<DepartmentDto> list = new ArrayList<>();
		for( DepartmentEntity entity : dEntityList) {
			list.add( getFrom(entity) );
		}
		
		return list;
		
	}
	
	/**
	 * クラス一覧取得
	 * @param departmentId
	 * @return
	 */
	public List<ClassDto> getClassList(int departmentId){
		List<ClassEntity> cEntityList = classRepository.findByDepartmentId(departmentId);
		
		List<ClassDto> list = new ArrayList<>();
		for( ClassEntity entity : cEntityList) {
			list.add( getFrom(entity) );
		}
		
		return list;
	}
	
	/* -private method- */
	private SchoolDto getFrom(SchoolEntity schoolEntity) {
		SchoolDto dto = new SchoolDto();

		dto.setSchoolId(schoolEntity.getSchoolId());
		dto.setName(schoolEntity.getName());
		dto.setCode(schoolEntity.getCode());
		dto.setAbbreviation(schoolEntity.getAbbreviation());
		
		return dto;
	}
	
	private DepartmentDto getFrom(DepartmentEntity departmentEntity) {
		DepartmentDto dto = new DepartmentDto();

		dto.setDepartmentId(departmentEntity.getDepartmentId());
		dto.setName(departmentEntity.getName());
		dto.setCode(departmentEntity.getCode());
		dto.setSchool(getFrom(departmentEntity.getSchoolTbl()));
		
		return dto;
	}
	
	private ClassDto getFrom(ClassEntity classEntity) {
		ClassDto dto = new ClassDto();
		
		dto.setName(classEntity.getName());
		dto.setClassId(classEntity.getClassId());
		dto.setDepartmentTbl(getFrom(classEntity.getDepartmentTbl()));
		
		return dto;
	}
}
