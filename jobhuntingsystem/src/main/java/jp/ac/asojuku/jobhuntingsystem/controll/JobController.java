package jp.ac.asojuku.jobhuntingsystem.controll;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import jp.ac.asojuku.jobhuntingsystem.dto.IndustryKindDto;
import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.exception.PermitionException;
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
	 * 求人情報の登録処理
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
	
	/**
	 * 求人情報の付随するイベントの登録画面表示処理
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 * @throws PermitionException 
	 */
	@RequestMapping(value= {"/event/regi"}, method=RequestMethod.GET)
    public ModelAndView eventInput(
    		@ModelAttribute("id")Integer id,
    		ModelAndView mv
    		) throws SystemErrorException, PermitionException {
		
		logger.info("event-input！");

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		if( loginInfoDto.isCompany() && loginInfoDto.getUid().equals(id)) {
			logger.info("企業ID：%dがイベント登録画面を表示しようとしています。",id);
		}else if( loginInfoDto.isAdmin()) {
			logger.info("管理者がイベント登録画面を表示しようとしています。");
		}else {
			//登録・更新権限ない人
			throw new PermitionException("この画面を表示する権限がありません");
		}
		
		List<IndustryKindDto> industryKindList = 
					industryService.getIndustryKind( loginInfoDto.getUid() );
		
		mv.addObject("companyName",loginInfoDto.getName());
		mv.addObject("companyId",loginInfoDto.getUid());
		mv.addObject("industryKindList",industryKindList);
		
        mv.setViewName("eventregi");
        
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
