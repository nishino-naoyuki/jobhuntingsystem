package jp.ac.asojuku.jobhuntingsystem.controll;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.ac.asojuku.jobhuntingsystem.config.SystemConfig;
import jp.ac.asojuku.jobhuntingsystem.csv.StudentCSV;
import jp.ac.asojuku.jobhuntingsystem.dto.CSVProgressDto;
import jp.ac.asojuku.jobhuntingsystem.dto.ClassDto;
import jp.ac.asojuku.jobhuntingsystem.dto.DepartmentDto;
import jp.ac.asojuku.jobhuntingsystem.dto.SchoolDto;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.form.UserInputCSVForm;
import jp.ac.asojuku.jobhuntingsystem.form.UserRegiForm;
import jp.ac.asojuku.jobhuntingsystem.param.json.ClassJson;
import jp.ac.asojuku.jobhuntingsystem.param.json.DepartmentJson;
import jp.ac.asojuku.jobhuntingsystem.service.AdminService;
import jp.ac.asojuku.jobhuntingsystem.service.SchoolService;
import jp.ac.asojuku.jobhuntingsystem.service.StudentService;
import jp.ac.asojuku.jobhuntingsystem.util.FileUtils;

@Controller
@RequestMapping(value= {"/user"})
public class UserController extends FileController{
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	AdminService adminService;
	@Autowired
	SchoolService schoolService;
	@Autowired
	StudentService studentService;

	@RequestMapping(value= {"/regi"}, method=RequestMethod.GET)
    public ModelAndView input(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("user-registration！");
		
		List<SchoolDto> schoolList = schoolService.getSchoolList();
		
		mv.addObject("schoolList",schoolList);
        mv.setViewName("userregi");
        
		return mv;
	}

	/**
	 * 学科の取得
	 * 
	 * @param sid
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = { "/getdep" }, method = RequestMethod.GET)
	@ResponseBody
	public Object getDepartmentList(
			@ModelAttribute("sid")Integer sid
			) throws JsonProcessingException {
		String result = "NG";
		List<DepartmentDto> departmentList = null;
		if( sid != null ) {
			departmentList = schoolService.getDepartmentList(sid);
			result = "OK";
		}

		DepartmentJson jsonObj = new DepartmentJson();
		
		jsonObj.setResult( result );
		jsonObj.setDepList(departmentList);

		ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(jsonObj);

        logger.trace("jsonString:{}",jsonString);

        return jsonString;
	}

	@RequestMapping(value = { "/getcls" }, method = RequestMethod.GET)
	@ResponseBody
	public Object getClassList(
			@ModelAttribute("did")Integer did
			) throws JsonProcessingException {
		String result = "NG";
		List<ClassDto> classList = null;
		if( did != null ) {
			classList = schoolService.getClassList(did);
			result = "OK";
		}

		ClassJson jsonObj = new ClassJson();
		
		jsonObj.setResult( result );
		jsonObj.setClsList(classList);

		ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(jsonObj);

        logger.trace("jsonString:{}",jsonString);

        return jsonString;
	}

	/**
	 * 学生1件登録
	 * @param userRegiForm
	 * @param bindingResult
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = { "/regi/one" }, method = RequestMethod.POST)
	@ResponseBody
	public Object regiOne(
			@Valid UserRegiForm userRegiForm,
			BindingResult bindingResult
			) throws JsonProcessingException {

		if( userRegiForm.getRoleselect() == 0 ) {
			//学生
			//登録済みか？
			if( studentService.isDuplicateStudent( userRegiForm.getStudentNo() ) ) {
				bindingResult.addError(new FieldError(bindingResult.getObjectName(), "studentNo", "学籍番号が重複しています") );
			}else {
				studentService.insertOne(userRegiForm);
			}
		}else {
			//教員、管理者
			adminService.insert(userRegiForm);
		}

		return getJson(bindingResult);
	}

	@RequestMapping(value = { "/regi/studentcsv" }, method = RequestMethod.POST)
	@ResponseBody
	public Object regiStudentByCSV(
			@Valid UserInputCSVForm userInputCSVForm,
			BindingResult bindingResult
			) throws IOException, SystemErrorException {

		 //ディレクトリを作成する
	    File uploadDir = mkCSVUploaddirs();

	    //出力ファイル名を決定する
	    File uploadFile = new File(uploadDir.getPath() + "/" + "temp.csv");
	    //アップロードファイルを取得
	    String encode = SystemConfig.getInstance().getCsvoutputencode();
	    byte[] sjisbytes = userInputCSVForm.getInputuserfile().getBytes();
	    byte[] bytes = (new String(sjisbytes,encode)).getBytes();
	    //出力ストリームを取得
	    try(BufferedOutputStream uploadFileStream =
              new BufferedOutputStream(new FileOutputStream(uploadFile))){
		    //ストリームに書き込んでクローズ
		    uploadFileStream.write(bytes);
	    }

        //エラーチェックを行う
	    List<String> errMsg = new ArrayList<>();
        List<StudentCSV> studentList = studentService.checkForCSV(uploadFile.getAbsolutePath(),errMsg);

        //もうファイルはいらないので削除
		FileUtils.delete(uploadFile.getParentFile());
		
		//if( errors.isHasErr() ){
        if(errMsg.size()>0) {
			String jsonMsg =  outputErrorResult(errMsg);
			logger.info(jsonMsg);
			return jsonMsg;
		}

        //メアドのドメインを取得する
        String mailDomain = SystemConfig.getInstance().getMaildomainstudent();
		//登録処理
        studentService.insertByCSV(studentList,mailDomain);

		//処理件数を返す
        return outputResult(studentList);
	}
	
	/* -private- */
	/**
	 * 処理結果のJSON文字列を作成する
	 * 
	 * @param userList
	 * @return
	 * @throws JsonProcessingException
	 */
	private String outputResult(List<StudentCSV> userList ) throws JsonProcessingException {

		CSVProgressDto progress = new CSVProgressDto();
		
		progress.setTotal(userList.size());
		progress.setNow(userList.size());

		ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(progress);

        logger.trace("jsonString:{}",jsonString);

        return jsonString;

	}
}
