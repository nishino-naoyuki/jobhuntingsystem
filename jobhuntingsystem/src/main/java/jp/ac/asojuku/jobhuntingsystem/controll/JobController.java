package jp.ac.asojuku.jobhuntingsystem.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;

@Controller
@RequestMapping(value= {"/job"})
public class JobController {

	Logger logger = LoggerFactory.getLogger(JobController.class);
	
	@RequestMapping(value= {"/input"}, method=RequestMethod.GET)
    public ModelAndView input(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("job-input！");
		
        mv.setViewName("jobinput");
        
		return mv;
	}
	

	@RequestMapping(value= {"/search"}, method=RequestMethod.GET)
    public ModelAndView search(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("job-search！");
		
        mv.setViewName("jobsearch");
        
		return mv;
	}
}
