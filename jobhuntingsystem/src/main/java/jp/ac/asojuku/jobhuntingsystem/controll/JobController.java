package jp.ac.asojuku.jobhuntingsystem.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;

@Controller
public class JobController {

	Logger logger = LoggerFactory.getLogger(JobController.class);
	
	@RequestMapping(value= {"/inputjob"}, method=RequestMethod.GET)
    public ModelAndView input(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("inputjob！");
		
        mv.setViewName("inputjob");
        
		return mv;
	}
}
