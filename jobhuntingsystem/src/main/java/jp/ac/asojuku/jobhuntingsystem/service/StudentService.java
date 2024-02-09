package jp.ac.asojuku.jobhuntingsystem.service;

import java.io.FileReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import jp.ac.asojuku.jobhuntingsystem.csv.StudentCSV;
import jp.ac.asojuku.jobhuntingsystem.entity.ClassEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.DepartmentEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;
import jp.ac.asojuku.jobhuntingsystem.form.UserRegiForm;
import jp.ac.asojuku.jobhuntingsystem.param.DefineStrings;
import jp.ac.asojuku.jobhuntingsystem.repository.ClassRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.DepartmentRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.StudentRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Digest;

@Service
public class StudentService {
	Logger logger = LoggerFactory.getLogger(StudentService.class);
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ClassRepository classRepository;
	@Autowired
	DepartmentRepository departmentRepository;

	/**
	 * 学生登録
	 * 
	 * @param userRegiForm
	 */
	public void insertOne(UserRegiForm userRegiForm) {
		
		studentRepository.save( getFrom(userRegiForm) );
	}
	
	/**
	 * 指定した学籍番号の学生が既に居るかどうかをチェックする
	 * @param studentNo
	 * @return
	 */
	public boolean isDuplicateStudent(String studentNo) {
		return ( studentRepository.findByStudentNo(studentNo) != null );
	}
	
	public List<StudentCSV> checkForCSV(String csvPath, List<String> err){
		List<StudentCSV> list = null;
		CsvToBean<StudentCSV> beans = null;

		try (FileReader fileReader = new FileReader(csvPath)){
			///////////////////////////////
			//CSVを読み込みマッピング
			beans =new CsvToBeanBuilder<StudentCSV>(
                    fileReader).withType(StudentCSV.class).withThrowExceptions(false).build();
			
			list = beans.parse(); 

			beans.getCapturedExceptions().stream().forEach(
					ex -> err.add( ex.getMessage())
					);
		}catch (Exception e) {
        	logger.warn("CSVパースエラー：",e);
        	err.add("CSVパースエラー");
        	err.add( e.getMessage() );
        	if( beans != null) {
				beans.getCapturedExceptions().stream().forEach(
						ex -> err.add( ex.getMessage())
						);
        	}

        }
		
		return list;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void insertByCSV(List<StudentCSV> studentList,String mailDomain) {
		for( StudentCSV scsv : studentList ) {
			if( isDuplicateStudent( scsv.getStudentno()) ) {
				//既に登録済み＝更新
			}else {
				//新規登録
				insertOne( getFrom(scsv,mailDomain) );
			}
		}
	}
	
	/* -private method- */
	private StudentEntity getFrom(UserRegiForm userRegiForm) {
		StudentEntity studentEntity = new StudentEntity();
		
		studentEntity.setStudentNo(userRegiForm.getStudentNo());
		studentEntity.setName(userRegiForm.getName());
		studentEntity.setMail(userRegiForm.getMail());
		studentEntity.setClassId(userRegiForm.getClassselect());
		studentEntity.setAddress(userRegiForm.getAddress());
		studentEntity.setTel(userRegiForm.getTel());
		studentEntity.setYear(userRegiForm.getYear());
		studentEntity.setJobhuntingStatusId(1);
		studentEntity.setImage(DefineStrings.NOIMAGE);
		String hashedPwd = Digest.createPassword(userRegiForm.getMail(), userRegiForm.getPassword());
		studentEntity.setPassword(hashedPwd);
		
		return studentEntity;		
	}
	private UserRegiForm getFrom(StudentCSV scsv,String mailDomain) {
		UserRegiForm form = new UserRegiForm();
		
		form.setName(scsv.getStudentname());
		String address = scsv.getAddress1() + scsv.getAddress2() + scsv.getAddress3();
		form.setAddress(address);
		form.setPassword(scsv.getStudentno());	//デフォルトのパスワードは「学籍番号：」
		form.setStudentNo( scsv.getStudentno() );
		form.setMail(scsv.getStudentno()+"@"+mailDomain);
		form.setYear(0);	//TODO:保留
		form.setTel(scsv.getTelephone());
		DepartmentEntity dEntity = departmentRepository.findByCode(scsv.getSectioncode());
		if( dEntity != null ) {
			ClassEntity cEntity = 
					classRepository.findByDepartmentIdAndName( dEntity.getDepartmentId(),scsv.getClassroom() );
			form.setClassselect( cEntity.getClassId() );
		}else {
			logger.warn("学科コード（SECTIONCODE）の登録がありません");
		}
		
		return form;
	}
	
}
