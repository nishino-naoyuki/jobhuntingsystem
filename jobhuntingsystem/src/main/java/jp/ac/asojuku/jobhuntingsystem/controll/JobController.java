package jp.ac.asojuku.jobhuntingsystem.controll;

import java.text.ParseException;
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

import jp.ac.asojuku.jobhuntingsystem.dto.CompanyDetailDto;
import jp.ac.asojuku.jobhuntingsystem.dto.EventInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.IndustryDto;
import jp.ac.asojuku.jobhuntingsystem.dto.IndustryKindDto;
import jp.ac.asojuku.jobhuntingsystem.dto.JobOfferListDto;
import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.StepDto;
import jp.ac.asojuku.jobhuntingsystem.entity.JobDetailDto;
import jp.ac.asojuku.jobhuntingsystem.exception.PermitionException;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.form.EventsForm;
import jp.ac.asojuku.jobhuntingsystem.form.JobOfferInputForm;
import jp.ac.asojuku.jobhuntingsystem.form.JobSearchForm;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.service.CompanyService;
import jp.ac.asojuku.jobhuntingsystem.service.EventService;
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
	@Autowired
	EventService eventService;
	@Autowired
	CompanyService companyService;
	
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
		Integer recruitmentId = jobService.insert(jobOfferInputForm);
		
		return getJson(bindingResult,String.valueOf(recruitmentId));
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
    		@ModelAttribute("companyId")Integer companyId,
    		//@ModelAttribute("recruitmentId")Integer recruitmentId,
    		ModelAndView mv
    		) throws SystemErrorException, PermitionException {
		
		logger.info("event-input！");

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		if( loginInfoDto.isCompany() && loginInfoDto.getUid().equals(companyId)) {
			logger.info("企業ID：%dがイベント登録画面を表示しようとしています。",companyId);
		}else if( loginInfoDto.isAdmin()) {
			logger.info("管理者がイベント登録画面を表示しようとしています。");
		}else {
			//登録・更新権限ない人
			throw new PermitionException("この画面を表示する権限がありません");
		}
		
		List<EventInfoDto> eventList = eventService.getList(companyId);
		List<StepDto> stepList = eventService.getAllStepList();
		CompanyDetailDto companyDetailDto = companyService.getDetail(companyId);
		
		mv.addObject("eventList",eventList);
		mv.addObject("companyId",companyId);
		//mv.addObject("recruitmentId",recruitmentId);
		mv.addObject("stepList",stepList);
		mv.addObject("companyDetailDto",companyDetailDto);
		
        mv.setViewName("eventregi");
        
		return mv;
	}

	/**
	 * イベント登録処理
	 * @param eventsForm
	 * @param bindingResult
	 * @return
	 * @throws SystemErrorException
	 * @throws JsonProcessingException
	 * @throws PermitionException
	 * @throws ParseException
	 */
	@RequestMapping(value= {"/event/regi"}, method=RequestMethod.POST)
	@ResponseBody
    public Object eventRegister(
    		@Valid EventsForm eventsForm,
    		BindingResult bindingResult
    		) throws SystemErrorException, JsonProcessingException, PermitionException, ParseException {
		
		logger.info("job-regi！");

		if( bindingResult.hasErrors() ) {
			return getJson(bindingResult);
		}

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		if( loginInfoDto.isCompany() && loginInfoDto.getUid().equals(eventsForm.getCompanyId())) {
			logger.info("企業ID：%dがイベント登録画面を表示しようとしています。",eventsForm.getCompanyId());
		}else if( loginInfoDto.isAdmin()) {
			logger.info("管理者がイベント登録画面を表示しようとしています。");
		}else {
			//登録・更新権限ない人
			throw new PermitionException("この画面を表示する権限がありません");
		}
		
		eventService.insertUpdateDelete(eventsForm);
		
		return getJson(bindingResult);
	}
    
	/**
	 * 求人検索
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 */
	@RequestMapping(value= {"/search"}, method=RequestMethod.GET)
    public ModelAndView search(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("job-search！");

		List<IndustryDto> industryList = industryService.getAllIndustryDto();
		mv.addObject("industryList", industryList);
		
        mv.setViewName("jobsearch");
        
		return mv;
	}

	/**
	 * 求人検索処理
	 * @param jobSearchForm
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 */
	@RequestMapping(value= {"/search"}, method=RequestMethod.POST)
	@ResponseBody
    public Object searchResult(
    		@Valid JobSearchForm jobSearchForm,
    		ModelAndView mv
    		) throws SystemErrorException {
		
		List<JobOfferListDto> jobList = jobService.search(jobSearchForm);
		
		return jobList;
	}

	@RequestMapping(value= {"/detail"}, method=RequestMethod.GET)
    public ModelAndView detial(
    		@ModelAttribute("recruitmentId")Integer recruitmentId,
    		ModelAndView mv
    		) throws SystemErrorException, PermitionException {
		
		logger.info("job-detial！");

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		JobDetailDto jobDetail = jobService.getDetal(recruitmentId);
		
		//不正チェック
		if( loginInfoDto.isCompany() && loginInfoDto.getUid() != jobDetail.getCompanyId() ) {
			//企業アカウントで別ギキョウの求人は見れない
			throw new PermitionException("この画面を表示する権限がありません");
		}
		
		mv.addObject("jobDetail", jobDetail);
        mv.setViewName("jobdetail");
        
		return mv;
	}
	
}
