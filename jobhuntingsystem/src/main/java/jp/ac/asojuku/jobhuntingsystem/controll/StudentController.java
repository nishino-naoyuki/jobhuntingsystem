package jp.ac.asojuku.jobhuntingsystem.controll;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.StudentDto;
import jp.ac.asojuku.jobhuntingsystem.entity.StudentEntity;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.service.StudentService;

@Controller
@RequestMapping(value= {"/student"})
public class StudentController {

	Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	StudentService studentService;
	@Autowired
	HttpSession session;
	
	@RequestMapping(value= {"/progress"}, method=RequestMethod.GET)
    public ModelAndView progress(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("student-progress！");
		
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
