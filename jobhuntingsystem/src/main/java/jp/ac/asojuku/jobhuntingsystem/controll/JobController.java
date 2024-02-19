package jp.ac.asojuku.jobhuntingsystem.controll;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import jp.ac.asojuku.jobhuntingsystem.dto.IndustryKindDto;
import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.form.JobOfferInputForm;
import jp.ac.asojuku.jobhuntingsystem.form.UserInputForm;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.service.IndustryService;
import jp.ac.asojuku.jobhuntingsystem.service.JobService;

@Controller
@RequestMapping(value= {"/job"})
public class JobController extends FileController {

	Logger logger = LoggerFactory.getLogger(JobController.class);
	@Autowired
	HttpSession session;
	@Autowired
	IndustryService industryService;
	@Autowired
	JobService jobService;
	
	/**
	 * 企業ログイン後、企業自身が求人を登録する画面
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 */
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
		mv.addObject("companyId",loginInfoDto.getUid());
		mv.addObject("industryKindList",industryKindList);
		
        mv.setViewName("jobregi");
        
		return mv;
	}

	/**
	 * 
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value= {"/regi"}, method=RequestMethod.POST)
	@ResponseBody
    public Object register(
    		@Valid JobOfferInputForm jobOfferInputForm,
    		BindingResult bindingResult
    		) throws SystemErrorException, JsonProcessingException {
		
		logger.info("job-regi！");

		if( bindingResult.hasErrors() ) {
			return getJson(bindingResult);
		}
		//登録処理
		jobService.insert(jobOfferInputForm);
		
		return getJson(bindingResult);
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
