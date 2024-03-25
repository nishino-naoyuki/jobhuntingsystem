package jp.ac.asojuku.jobhuntingsystem.controll;

import java.util.List;

import javax.servlet.http.HttpSession;
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

import jp.ac.asojuku.jobhuntingsystem.dto.DepartmentDto;
import jp.ac.asojuku.jobhuntingsystem.dto.IndustryDto;
import jp.ac.asojuku.jobhuntingsystem.dto.JobHuntingDto;
import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.StudentDto;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;
import jp.ac.asojuku.jobhuntingsystem.exception.NotMatchPasswordException;
import jp.ac.asojuku.jobhuntingsystem.exception.PermitionException;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.form.PasswordChangeForm;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.param.json.DepartmentJson;
import jp.ac.asojuku.jobhuntingsystem.service.EventService;
import jp.ac.asojuku.jobhuntingsystem.service.IndustryService;
import jp.ac.asojuku.jobhuntingsystem.service.StudentService;

@Controller
@RequestMapping(value= {"/student"})
public class StudentController extends RestControllerBase{

	Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	StudentService studentService;
	@Autowired
	IndustryService industryService;
	@Autowired
	HttpSession session;
	@Autowired
	EventService eventService;
	

	/**
	 * 
	 * @param passwordChangeForm
	 * @return
	 * @throws JsonProcessingException
	 * @throws PermitionException
	 * @throws NotMatchPasswordException
	 */
	@RequestMapping(value = { "/passchg" }, method = RequestMethod.POST)
	@ResponseBody
	public Object passChange(
			@Valid PasswordChangeForm passwordChangeForm,
			BindingResult bindingResult
			) throws JsonProcessingException, PermitionException {

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);

		if( !loginInfoDto.isStudent() ) {
			//お気に入り登録は学生のみ
			throw new PermitionException("この画面を表示する権限がありません");
		}
		
		try {
			studentService.changePassword(
					loginInfoDto.getUid(), 
					passwordChangeForm.getOldPassword(), 
					passwordChangeForm.getNewPassword());
		}catch(NotMatchPasswordException e) {
			bindingResult.addError(new FieldError(bindingResult.getObjectName(), "password", "旧パスワードが一致しません") );
		}

		return getJson(bindingResult);		
	}
	
	/**
	 * 
	 * @param industryArry
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = { "/industryArry" }, method = RequestMethod.POST)
	@ResponseBody
	public Object savaIndustryArry(
			@ModelAttribute("industryArry")Integer[] industryArry
			) throws JsonProcessingException {
		String result = "OK";

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		studentService.insertIndustryKind(loginInfoDto.getUid(), industryArry);

		DepartmentJson jsonObj = new DepartmentJson();
		
		jsonObj.setResult( result );

		ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(jsonObj);

        logger.trace("jsonString:{}",jsonString);

        return jsonString;
	}
	
	/**
	 * 参加中のイベント一覧を取得する
	 * @param userId
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 * @throws PermitionException
	 */
	@RequestMapping(value= {"/progress"}, method=RequestMethod.GET)
    public ModelAndView progress(
    		@ModelAttribute("id")Integer userId,
    		ModelAndView mv
    		) throws SystemErrorException, PermitionException {
		
		logger.info("student-progress！");

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		if( loginInfoDto.isStudent() && userId != loginInfoDto.getUid() ) {
			throw new PermitionException("この画面を表示する権限がありません");
		}else if( loginInfoDto.isCompany() ) {
			throw new PermitionException("この画面を表示する権限がありません");
		}
		
		List<JobHuntingDto> jhList = eventService.getEntryList(userId);
		
		mv.addObject("jhList", jhList);
        mv.setViewName("studentprogress");
        
		return mv;
	}

	/**
	 * 詳細情報取得
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 */
	@RequestMapping(value= {"/detail"}, method=RequestMethod.GET)
    public ModelAndView detail(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("student-detail！");

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);

		StudentDto studentDto = studentService.getDetail( loginInfoDto.getUid() );
		List<IndustryDto> industryList = industryService.getAllIndustryDto();
		
		mv.addObject("industryList", industryList);
		mv.addObject("studentDto", studentDto);
        mv.setViewName("studentdetail");
        
		return mv;
	}

	@RequestMapping(value= {"/search"}, method=RequestMethod.GET)
    public ModelAndView search(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("student-search！");
		
        mv.setViewName("studentsearch");
        
		return mv;
	}

	@RequestMapping(value= {"/input"}, method=RequestMethod.GET)
    public ModelAndView input(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("student-input！");
		
        mv.setViewName("studentinput");
        
		return mv;
	}
}
