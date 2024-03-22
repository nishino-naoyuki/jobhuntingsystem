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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import jp.ac.asojuku.jobhuntingsystem.dto.CompanyDetailDto;
import jp.ac.asojuku.jobhuntingsystem.dto.EventInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.IndustryDto;
import jp.ac.asojuku.jobhuntingsystem.dto.JobOfferListDto;
import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.StepDto;
import jp.ac.asojuku.jobhuntingsystem.exception.PermitionException;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.form.EventEntryForm;
import jp.ac.asojuku.jobhuntingsystem.form.EventSearchForm;
import jp.ac.asojuku.jobhuntingsystem.form.EventsForm;
import jp.ac.asojuku.jobhuntingsystem.form.JobSearchForm;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.service.CompanyService;
import jp.ac.asojuku.jobhuntingsystem.service.EventService;
import jp.ac.asojuku.jobhuntingsystem.service.IndustryService;

@Controller
@RequestMapping(value= {"/event"})
public class EventController extends FileController{
	Logger logger = LoggerFactory.getLogger(EventController.class);
	@Autowired
	HttpSession session;
	@Autowired
	EventService eventService;
	@Autowired
	CompanyService companyService;
	@Autowired
	IndustryService industryService;


	@RequestMapping(value= {"/entry"}, method=RequestMethod.POST)
	@ResponseBody
    public Object eventEntry(
    		@Valid EventEntryForm eventEntryForm,
    		BindingResult bindingResult
    		) throws SystemErrorException, JsonProcessingException, PermitionException, ParseException {
		
		logger.info("job-regi！");

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		if( loginInfoDto.isCompany() ) {
			throw new PermitionException("企業はこの機能をしようする権限がありません");
		}else if( loginInfoDto.isStudent() ) {
			eventEntryForm.setUId( loginInfoDto.getUid() );
		}
		
		for( Integer eventId : eventEntryForm.getEvtIdArry() ) {
			EventInfoDto eDto = eventService.getAlreadyEntry(eventEntryForm.getUId(),eventId);
			if( eDto != null ) {
				bindingResult.addError(
						new FieldError(
								bindingResult.getObjectName(), 
								"eventId", 
								eDto.getCompanyName()+"の"+eDto.getStepName()+"("+eDto.getStartDatetimeStr()+")"+"は既に登録されています") 
				);
			}
		}
		
		if( bindingResult.hasErrors() ) {
			return getJson(bindingResult);
		}
		
		eventService.entryEvent(eventEntryForm,loginInfoDto.isAdmin());
		
		return getJson(bindingResult);
	}
	
	@RequestMapping(value= {"/detail"}, method=RequestMethod.GET)
	public ModelAndView detail( 
			@ModelAttribute("eventId")Integer eventId,
			ModelAndView mv ) {
		EventInfoDto eventDto = eventService.getDetail(eventId);
		
		mv.addObject("eventDto", eventDto);

        mv.setViewName("eventdetail");
        
		return mv;
	}
	/**
	 * イベント情報検索画面表示
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 * @throws PermitionException
	 */
	@RequestMapping(value= {"/search"}, method=RequestMethod.GET)
    public ModelAndView search(
    		ModelAndView mv
    		) throws SystemErrorException, PermitionException {
		
		logger.info("event-search！");

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);

		List<IndustryDto> industryList = null;
		if( loginInfoDto.isStudent() ) {
			industryList = industryService.getIndustryDtoForStudent(loginInfoDto.getUid());
		}else {
			industryList = industryService.getAllIndustryDto();
		}
		List<StepDto> stepList = eventService.getAllStepList();
		
		mv.addObject("industryList", industryList);
		mv.addObject("stepList", stepList);
		
        mv.setViewName("eventsearch");
        
		return mv;
	}

	/**
	 * イベント検索
	 * @param eventSearchForm
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 * @throws ParseException
	 */
	@RequestMapping(value= {"/search"}, method=RequestMethod.POST)
	@ResponseBody
    public Object searchResult(
    		@Valid EventSearchForm eventSearchForm,
    		ModelAndView mv
    		) throws SystemErrorException, ParseException {
		
		List<EventInfoDto> eventList = eventService.search(eventSearchForm);
		
		return eventList;
	}
	
	/**
	 * 求人情報の付随するイベントの登録画面表示処理
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 * @throws PermitionException 
	 */
	@RequestMapping(value= {"/regi"}, method=RequestMethod.GET)
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
	@RequestMapping(value= {"/regi"}, method=RequestMethod.POST)
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
}
