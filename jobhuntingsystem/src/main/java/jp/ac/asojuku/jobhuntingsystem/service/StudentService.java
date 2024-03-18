package jp.ac.asojuku.jobhuntingsystem.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import jp.ac.asojuku.jobhuntingsystem.csv.CubicCSV;
import jp.ac.asojuku.jobhuntingsystem.csv.StudentCSV;
import jp.ac.asojuku.jobhuntingsystem.dto.IndustryKindDto;
import jp.ac.asojuku.jobhuntingsystem.dto.StudentDto;
import jp.ac.asojuku.jobhuntingsystem.entity.ClassEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.DepartmentEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentIndustryEntity;
import jp.ac.asojuku.jobhuntingsystem.exception.NotMatchPasswordException;
import jp.ac.asojuku.jobhuntingsystem.form.UserInputForm;
import jp.ac.asojuku.jobhuntingsystem.param.DefineStrings;
import jp.ac.asojuku.jobhuntingsystem.repository.ClassRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.DepartmentRepository;
import jp.ac.asojuku.jobhuntingsystem.repository.StudentIndustryRepository;
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
	@Autowired
	StudentIndustryRepository studentIndustryRepository;

	/**
	 * パスワード変更処理
	 * 
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @throws NotMatchPasswordException
	 */
	public void changePassword(Integer userId,String oldPassword,String newPassword) throws NotMatchPasswordException {
		StudentEntity studentEntity = studentRepository.getOne(userId);
		String oldPwdHash = Digest.createPassword(studentEntity.getMail(), oldPassword);
		
		//旧パスワード一致チェック
		if( !StringUtils.equals(studentEntity.getPassword(), oldPwdHash) ) {
			throw new NotMatchPasswordException();
		}
		
		String newPwdHash = Digest.createPassword(studentEntity.getMail(), newPassword);
		studentEntity.setPassword(newPwdHash);
		
		studentRepository.save(studentEntity);
	}
	/**
	 * 希望職種の更新（いったん削除してリストを作り直す）
	 * @param studentId
	 * @param industryArry
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertIndustryKind(Integer studentId,Integer[] industryArry) {
		List<StudentIndustryEntity> siList = studentIndustryRepository.findByStudentIdOrderByIndustrykindId(studentId);
		if( siList.size() > 0 ) {
			//いったん削除する
			studentIndustryRepository.deleteInBatch(siList);
		}
		//作成しなおす
		List<StudentIndustryEntity> siInsertList = new ArrayList<>();
		for(Integer industryId : industryArry) {
			StudentIndustryEntity siEntity = new StudentIndustryEntity();
			siEntity.setStudentId(studentId);
			siEntity.setIndustrykindId(industryId);
			siInsertList.add(siEntity);
		}
		studentIndustryRepository.saveAll(siInsertList);
	}
	/**
	 * ユーザー情報詳細データ取得
	 * @param studentId
	 * @return
	 */
	public StudentDto getDetail(Integer studentId) {
		StudentEntity sEntity = studentRepository.getOne(studentId);
		List<StudentIndustryEntity> siList = studentIndustryRepository.findByStudentIdOrderByIndustrykindId(studentId);
		
		return getFrom(sEntity,siList);
	}
	/**
	 * 学生登録
	 * 
	 * @param userRegiForm
	 */
	public void insertOne(UserInputForm userRegiForm) {
		
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
	
	/**
	 * 学生登録CSVチェック
	 * @param csvPath
	 * @param err
	 * @return
	 */
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

	public List<CubicCSV> checkForCubicCSV(String csvPath, List<String> err){
		List<CubicCSV> list = null;
		CsvToBean<CubicCSV> beans = null;

		try (FileReader fileReader = new FileReader(csvPath)){
			///////////////////////////////
			//CSVを読み込みマッピング
			beans =new CsvToBeanBuilder<CubicCSV>(
                    fileReader).withType(CubicCSV.class).withThrowExceptions(false).build();
			
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
	
	/**
	 * Cubicのデータを取得
	 * @param cubicCSVList
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public int updateCubicData(List<CubicCSV> cubicCSVList) {
		List<StudentEntity> studentList = new ArrayList<>();
		for( CubicCSV cubicCSV : cubicCSVList) {
			//学籍番号を取得
			String studentNo = cubicCSV.getStudentno();
			//学生データを取得
			StudentEntity studentEntity = studentRepository.findByStudentNo(studentNo);
			if( studentEntity != null ) {
				studentEntity = updateFrom(studentEntity,cubicCSV);
				studentList.add(studentEntity);
			}			
		}
		//更新
		studentList = studentRepository.saveAll(studentList);
		
		return studentList.size();
	}
	
	/* -private method- */
	private StudentEntity updateFrom(StudentEntity studentEntity,CubicCSV cubicCSV) {
		studentEntity.setCTrust(Integer.parseInt(cubicCSV.getC_trust() ) );
		studentEntity.setCEntrapment(Integer.parseInt(cubicCSV.getC_entrapment() ) );
		studentEntity.setCObjectivity(Integer.parseInt(cubicCSV.getC_objectivity() ) );
		studentEntity.setCPhysicality(Integer.parseInt(cubicCSV.getC_physicality() ) );
		studentEntity.setCMoodiness(Integer.parseInt(cubicCSV.getC_moodiness() ) );
		studentEntity.setCPersistence(Integer.parseInt(cubicCSV.getC_persistence() ) );
		studentEntity.setCRegularity(Integer.parseInt(cubicCSV.getC_regularity() ) );
		studentEntity.setCCompetitiveness(Integer.parseInt(cubicCSV.getC_competitiveness() ) );
		studentEntity.setCSelfesteem(Integer.parseInt(cubicCSV.getC_selfesteem() ) );
		studentEntity.setCPrudence(Integer.parseInt(cubicCSV.getC_prudence() ) );
		studentEntity.setCBearishness(Integer.parseInt(cubicCSV.getC_bearishness() ) );
		studentEntity.setCSurroundings(Integer.parseInt(cubicCSV.getC_surroundings() ) );
		studentEntity.setCScience(Integer.parseInt(cubicCSV.getC_science() ) );
		studentEntity.setCSociety(Integer.parseInt(cubicCSV.getC_society() ) );
		studentEntity.setCPsychology(Integer.parseInt(cubicCSV.getC_psychology() ) );
		studentEntity.setCArt(Integer.parseInt(cubicCSV.getC_art() ) );
		studentEntity.setCPositivity(Integer.parseInt(cubicCSV.getC_positivity() ) );
		studentEntity.setCCooperativeness(Integer.parseInt(cubicCSV.getC_cooperativeness() ) );
		studentEntity.setCResponsibility(Integer.parseInt(cubicCSV.getC_responsibility() ) );
		studentEntity.setCReliability(Integer.parseInt(cubicCSV.getC_reliability() ) );
		studentEntity.setCLeadership(Integer.parseInt(cubicCSV.getC_leadership() ) );
		studentEntity.setCEmpathy(Integer.parseInt(cubicCSV.getC_empathy() ) );
		studentEntity.setCEmotionalStability(Integer.parseInt(cubicCSV.getC_emotional_stability() ) );
		studentEntity.setCObedience(Integer.parseInt(cubicCSV.getC_obedience() ) );
		studentEntity.setCAutonomy(Integer.parseInt(cubicCSV.getC_autonomy() ) );
		studentEntity.setCMoratorium(Integer.parseInt(cubicCSV.getC_moratorium() ) );
		studentEntity.setCDesireSuccess(Integer.parseInt(cubicCSV.getC_desire_success() ) );
		studentEntity.setCAffinity(Integer.parseInt(cubicCSV.getC_affinity() ) );
		studentEntity.setCSeeking(Integer.parseInt(cubicCSV.getC_seeking() ) );
		studentEntity.setCManifestation(Integer.parseInt(cubicCSV.getC_manifestation() ) );
		studentEntity.setCOrder(Integer.parseInt(cubicCSV.getC_order() ) );
		studentEntity.setCMaterial(Integer.parseInt(cubicCSV.getC_material() ) );
		studentEntity.setCCrisisResistance(Integer.parseInt(cubicCSV.getC_crisis_resistance() ) );
		studentEntity.setCIndependence(Integer.parseInt(cubicCSV.getC_independence() ) );
		studentEntity.setCControl(Integer.parseInt(cubicCSV.getC_control() ) );
		studentEntity.setCWorkethic(Integer.parseInt(cubicCSV.getC_workethic() ) );
		studentEntity.setCActive(Integer.parseInt(cubicCSV.getC_active() ) );
		studentEntity.setCEnthusiasm(Integer.parseInt(cubicCSV.getC_enthusiasm() ) );
		studentEntity.setCPerseverance(Integer.parseInt(cubicCSV.getC_perseverance() ) );
		studentEntity.setCResponsibility2(Integer.parseInt(cubicCSV.getC_responsibility2() ) );
		studentEntity.setCDecisive(Integer.parseInt(cubicCSV.getC_decisive() ) );
		studentEntity.setCLeadership2(Integer.parseInt(cubicCSV.getC_leadership2() ) );
		studentEntity.setCLeader(Integer.parseInt(cubicCSV.getC_leader() ) );
		studentEntity.setCSelftrust(Integer.parseInt(cubicCSV.getC_selftrust() ) );
		studentEntity.setCAdjustment(Integer.parseInt(cubicCSV.getC_adjustment() ) );
		studentEntity.setCNegotiation(Integer.parseInt(cubicCSV.getC_negotiation() ) );
		studentEntity.setCInnovative(Integer.parseInt(cubicCSV.getC_innovative() ) );
		studentEntity.setCAnalysis(Integer.parseInt(cubicCSV.getC_analysis() ) );
		studentEntity.setCInsight(Integer.parseInt(cubicCSV.getC_insight() ) );
		studentEntity.setCPlanning(Integer.parseInt(cubicCSV.getC_planning() ) );
		studentEntity.setCExpertise(Integer.parseInt(cubicCSV.getC_expertise() ) );
		studentEntity.setCUtilization(Integer.parseInt(cubicCSV.getC_utilization() ) );
		studentEntity.setCGeneral(Integer.parseInt(cubicCSV.getC_general() ) );
		studentEntity.setCMental(Integer.parseInt(cubicCSV.getC_mental() ) );
		studentEntity.setCLegwaist(Integer.parseInt(cubicCSV.getC_legwaist() ) );
		studentEntity.setCConcentration(Integer.parseInt(cubicCSV.getC_concentration() ) );
		studentEntity.setCStandardization(Integer.parseInt(cubicCSV.getC_standardization() ) );
		studentEntity.setCRange(Integer.parseInt(cubicCSV.getC_range() ) );
		studentEntity.setCEstablished(Integer.parseInt(cubicCSV.getC_established() ) );
		studentEntity.setCTypejudge(cubicCSV.getC_typejudge()  );

		return studentEntity;
	}
	
	/**
	 * Form→Entity変換
	 * @param userRegiForm
	 * @return
	 */
	private StudentEntity getFrom(UserInputForm userRegiForm) {
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
	/**
	 * CSVデータ→Formデータ変換
	 * @param scsv
	 * @param mailDomain
	 * @return
	 */
	private UserInputForm getFrom(StudentCSV scsv,String mailDomain) {
		UserInputForm form = new UserInputForm();
		
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
	
	/**
	 * 
	 * @param sEntity
	 * @return
	 */
	private StudentDto getFrom( StudentEntity sEntity,List<StudentIndustryEntity> siList) {
		StudentDto dto = new StudentDto();

		dto.setStudentId(sEntity.getStudentId());
		dto.setName(sEntity.getName());
		dto.setStudentNo(sEntity.getStudentNo());
		dto.setJobhuntingStatusId(sEntity.getJobhuntingStatusId());
		dto.setJobhuntingStatusName(sEntity.getJobhuntingStatusTbl().getName());
		dto.setClassId(sEntity.getClassId());
		dto.setClassName(sEntity.getClassTbl().getDepartmentTbl().getName());
		dto.setTeacherName(sEntity.getClassTbl().getAdminTbl().getName());
		for(StudentIndustryEntity siEntity : siList ) {
			IndustryKindDto ikDto = new IndustryKindDto();
			ikDto.setId(siEntity.getIndustrykindId());
			ikDto.setName(siEntity.getIndustrykindTbl().getName());
			ikDto.setIndustryId(siEntity.getIndustrykindTbl().getIndustryTbl().getIndustryId());
			ikDto.setIndustryName(siEntity.getIndustrykindTbl().getIndustryTbl().getName());
			dto.addIkList(ikDto);
		}
		//活動履歴 todo
		
		return dto;
	}
	
}
