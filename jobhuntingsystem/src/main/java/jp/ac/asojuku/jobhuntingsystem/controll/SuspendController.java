package jp.ac.asojuku.jobhuntingsystem.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;

@Controller
@RequestMapping(value= {"/susupended"})
public class SuspendController {

	Logger logger = LoggerFactory.getLogger(SuspendController.class);
	
	@RequestMapping(value= {"/list"}, method=RequestMethod.GET)
    public ModelAndView progress(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("susupend-list！");
		
        mv.setViewName("suspendedlist");
        
		return mv;
	}
}
