package jp.ac.asojuku.jobhuntingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ac.asojuku.jobhuntingsystem.entity.AdminEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.ClassEntity;
import jp.ac.asojuku.jobhuntingsystem.form.UserInputForm;
import jp.ac.asojuku.jobhuntingsystem.param.DefineStrings;
import jp.ac.asojuku.jobhuntingsystem.param.RoleId;
import jp.ac.asojuku.jobhuntingsystem.repository.AdminRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.ClassRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Digest;

@Service
public class AdminService {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	ClassRepository classRepository;
	
	@Transactional(rollbackFor = Exception.class)
	public void insert(UserInputForm userRegiForm) {
		AdminEntity adminEntity = new AdminEntity();
		
		adminEntity.setName(userRegiForm.getName());
		adminEntity.setMail(userRegiForm.getMail());
		String hashedPwd = Digest.createPassword(userRegiForm.getMail(), userRegiForm.getPassword());
		adminEntity.setPassword(hashedPwd);
		adminEntity.setImage(DefineStrings.NOIMAGE);
		adminEntity.setRoleId(userRegiForm.getRoleselect());
		adminEntity = adminRepository.save(adminEntity);
		
		if( RoleId.TEACHER.equals(userRegiForm.getRoleselect()) ) {
			//教員の場合はクラスを登録する
			ClassEntity classEntity = classRepository.getOne(userRegiForm.getClassselect());
			classEntity.setAdminId(adminEntity.getAdminId());
			classRepository.save(classEntity);
		}
		
	}
}
