package jp.ac.asojuku.jobhuntingsystem.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;

@Controller
public class CompanyController {

	Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@RequestMapping(value= {"/inputcompany"}, method=RequestMethod.GET)
    public ModelAndView input(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("inputcompany！");
		
        mv.setViewName("inputcompany");
        
		return mv;
	}
}
