package jp.ac.asojuku.jobhuntingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;
import jp.ac.asojuku.jobhuntingsystem.form.UserRegiForm;
import jp.ac.asojuku.jobhuntingsystem.param.DefineStrings;
import jp.ac.asojuku.jobhuntingsystem.repository.StudentRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Digest;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;

	/**
	 * 学生登録
	 * 
	 * @param userRegiForm
	 */
	public void insertOne(UserRegiForm userRegiForm) {
		StudentEntity studentEntity = new StudentEntity();
		
		studentEntity.setStudentNo(userRegiForm.getStudentNo());
		studentEntity.setName(userRegiForm.getName());
		studentEntity.setMail(userRegiForm.getMail());
		studentEntity.setClassId(userRegiForm.getClassselect());
		studentEntity.setAddress(userRegiForm.getAddress());
		studentEntity.setJobhuntingStatusId(1);
		studentEntity.setImage(DefineStrings.NOIMAGE);
		String hashedPwd = Digest.createPassword(userRegiForm.getMail(), userRegiForm.getPassword());
		studentEntity.setPassword(hashedPwd);
		
		studentRepository.save(studentEntity);
	}
	
	/**
	 * 指定した学籍番号の学生が既に居るかどうかをチェックする
	 * @param studentNo
	 * @return
	 */
	public boolean isDuplicateStudent(String studentNo) {
		return ( studentRepository.findByStudentNo(studentNo) != null );
	}
}
