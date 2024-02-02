package jp.ac.asojuku.jobhuntingsystem.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;

@Controller
@RequestMapping(value= {"/joboffer"})
public class JobOfferController {

	Logger logger = LoggerFactory.getLogger(JobOfferController.class);
	
	@RequestMapping(value= {"/list"}, method=RequestMethod.GET)
    public ModelAndView list(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("joboffer-list！");
		
        mv.setViewName("jobofferlist");
        
		return mv;
	}
}
