package jp.ac.asojuku.jobhuntingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.entity.AdminEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;
import jp.ac.asojuku.jobhuntingsystem.repository.AdminRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.StudentRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Digest;

@Service
public class LoginService {
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	AdminRepository adminRepository;
	
	/**
	 * 学生ログイン処理
	 * @param mail
	 * @param pass
	 * @return
	 */
	public LoginInfoDto studentLogin(String mail,String pass) {
		LoginInfoDto loginInfoDto = null;
		String pwdHash = Digest.createPassword(mail, pass);
		
		StudentEntity studentEntity = studentRepository.findByMailAndPassword(mail,pwdHash);
		
		if( studentEntity != null ) {
			loginInfoDto = getFrom( studentEntity );
		}
		
		return loginInfoDto;
	}

	/**
	 * 管理者ログイン
	 * 
	 * @param mail
	 * @param pass
	 * @return
	 */
	public LoginInfoDto adminLogin(String mail,String pass) {
		LoginInfoDto loginInfoDto = null;
		String pwdHash = Digest.createPassword(mail, pass);
		
		AdminEntity adminEntity = adminRepository.findByMailAndPassword(mail,pwdHash);
		
		if( adminEntity != null ) {
			loginInfoDto = getFrom( adminEntity );
		}
		
		return loginInfoDto;
	}
	
	
	/* -private method- */
	/**
	 * Entity→Dto変換
	 * @param studentEntity
	 * @return
	 */
	private LoginInfoDto getFrom(StudentEntity studentEntity) {
		LoginInfoDto loginInfoDto = new LoginInfoDto();
		
		loginInfoDto.setName( studentEntity.getName() );
		loginInfoDto.setUid( studentEntity.getStudentId() );
		loginInfoDto.setMail( studentEntity.getMail() );
		loginInfoDto.setRoleName( studentEntity.getRoleTbl().getName() );
		loginInfoDto.setAdmin(false);
		loginInfoDto.setCompany(false);
		loginInfoDto.setStudent(true);
		
		return loginInfoDto;
	}

	/**
	 * Entity→Dto変換
	 * @param adminEntity
	 * @return
	 */
	private LoginInfoDto getFrom(AdminEntity adminEntity) {
		LoginInfoDto loginInfoDto = new LoginInfoDto();
		
		loginInfoDto.setName( adminEntity.getName() );
		loginInfoDto.setUid( adminEntity.getAdminId() );
		loginInfoDto.setMail( adminEntity.getMail() );
		loginInfoDto.setRoleName( adminEntity.getRoleTbl().getName() );
		loginInfoDto.setAdmin(true);
		loginInfoDto.setCompany(false);
		loginInfoDto.setStudent(false);
		
		return loginInfoDto;
	}
}