package jp.ac.asojuku.jobhuntingsystem.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;

@Controller
@RequestMapping(value= {"/student"})
public class StudentControll {

	Logger logger = LoggerFactory.getLogger(StudentControll.class);
	
	@RequestMapping(value= {"/progress"}, method=RequestMethod.GET)
    public ModelAndView progress(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("student-progress！");
		
        mv.setViewName("studentprogress");
        
		return mv;
	}

	@RequestMapping(value= {"/favorit"}, method=RequestMethod.GET)
    public ModelAndView favorit(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("student-favorit！");
		
        mv.setViewName("studentfavorit");
        
		return mv;
	}

	@RequestMapping(value= {"/detail"}, method=RequestMethod.GET)
    public ModelAndView detail(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("student-detail！");
		
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
