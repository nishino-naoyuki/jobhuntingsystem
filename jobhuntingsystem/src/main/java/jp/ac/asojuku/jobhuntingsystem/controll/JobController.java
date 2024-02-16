package jp.ac.asojuku.jobhuntingsystem.controll;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.asojuku.jobhuntingsystem.dto.IndustryKindDto;
import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.service.IndustryService;

@Controller
@RequestMapping(value= {"/job"})
public class JobController {

	Logger logger = LoggerFactory.getLogger(JobController.class);
	@Autowired
	HttpSession session;
	@Autowired
	IndustryService industryService;
	
	@RequestMapping(value= {"/regi"}, method=RequestMethod.GET)
    public ModelAndView input(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("job-input！");

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		List<IndustryKindDto> industryKindList = 
					industryService.getIndustryKind( loginInfoDto.getUid() );
		
		mv.addObject("companyName",loginInfoDto.getName());
		mv.addObject("industryKindList",industryKindList);
		
        mv.setViewName("jobregi");
        
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
