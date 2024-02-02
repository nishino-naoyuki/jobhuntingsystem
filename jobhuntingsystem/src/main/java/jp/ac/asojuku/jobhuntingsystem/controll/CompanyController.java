package jp.ac.asojuku.jobhuntingsystem.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;

@Controller
@RequestMapping(value= {"/company"})
public class CompanyController {

	Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@RequestMapping(value= {"/input"}, method=RequestMethod.GET)
    public ModelAndView input(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("company-input！");
		
        mv.setViewName("companyinput");
        
		return mv;
	}
	
	@RequestMapping(value= {"/search"}, method=RequestMethod.GET)
    public ModelAndView search(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("search-company！");
		
        mv.setViewName("companysearch");
        
		return mv;
	}
}
