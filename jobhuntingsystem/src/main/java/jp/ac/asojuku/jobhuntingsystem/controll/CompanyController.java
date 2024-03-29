package jp.ac.asojuku.jobhuntingsystem.controll;

import java.util.ArrayList;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.ac.asojuku.jobhuntingsystem.dto.CompanyDetailDto;
import jp.ac.asojuku.jobhuntingsystem.dto.CompanyInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.IndustryDto;
import jp.ac.asojuku.jobhuntingsystem.dto.InfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.JobOfferListDto;
import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.RecrutimentTypeDto;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.form.CompanyRegiForm;
import jp.ac.asojuku.jobhuntingsystem.form.CompanySearchForm;
import jp.ac.asojuku.jobhuntingsystem.param.Level;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.param.json.ErrorField;
import jp.ac.asojuku.jobhuntingsystem.param.json.InfoDetailJson;
import jp.ac.asojuku.jobhuntingsystem.service.CompanyService;
import jp.ac.asojuku.jobhuntingsystem.service.IndustryService;
import jp.ac.asojuku.jobhuntingsystem.service.JobService;

@Controller
@RequestMapping(value= {"/company"})
public class CompanyController extends RestControllerBase{

	Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	IndustryService industryService;
	@Autowired
	CompanyService companyService;
	@Autowired
	JobService jobService;
	@Autowired
	HttpSession session;
	
	/**
	 * 企業の登録画面表示
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 */
	@RequestMapping(value= {"/regi"}, method=RequestMethod.GET)
    public ModelAndView input(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("company-input！");
		
		List<IndustryDto> industryList = industryService.getAllIndustryDto();
		mv.addObject("industryList", industryList);
		mv.addObject("fromLogin",false);
		
        mv.setViewName("companyregi");
        
		return mv;
	}

	/**
	 * 企業の登録画面表示
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 */
	@RequestMapping(value= {"/regi/new"}, method=RequestMethod.GET)
    public ModelAndView inputFromLogin(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("company-input！");
		
		List<IndustryDto> industryList = industryService.getAllIndustryDto();
		mv.addObject("industryList", industryList);
		mv.addObject("fromLogin",true);
        mv.setViewName("companyregi");
        
		return mv;
	}
	
	/**
	 * 企業の登録処理（管理者による登録）
	 * @param companyRegiForm
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value= {"/regi"}, method=RequestMethod.POST)
	@ResponseBody
    public Object registry(
    		@Valid CompanyRegiForm companyRegiForm,
    		BindingResult bindingResult
    		) throws SystemErrorException, JsonProcessingException {
		
		logger.info("company-registry");
		if( bindingResult.getErrorCount() == 0 ) {
			companyService.insert(companyRegiForm);
		}		
        
        return getJson(bindingResult);
	}
	
	/**
	 * 企業検索画面表示処理
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 */
	@RequestMapping(value= {"/search"}, method=RequestMethod.GET)
    public ModelAndView search(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("search-company！");

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		List<RecrutimentTypeDto> ryDtoList = companyService.getRecrutimentTypeAllList();
		List<IndustryDto> industryList = null;
		if( loginInfoDto.isStudent() ) {
			industryList = industryService.getIndustryDtoForStudent(loginInfoDto.getUid());
		}else {
			industryList = industryService.getAllIndustryDto();
		}
		
		mv.addObject("industryList", industryList);
		mv.addObject("ryDtoList",ryDtoList);
		
        mv.setViewName("companysearch");
        
		return mv;
	}

	/**
	 * 企業検索処理
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value= {"/search/result"}, method=RequestMethod.POST)
	@ResponseBody
    public Object searchResult(
    		@Valid CompanySearchForm companySearchForm,
    		BindingResult bindingResult
    		) throws SystemErrorException, JsonProcessingException {
		
		logger.info("search-company！");
		
		List<CompanyInfoDto> list = companyService.search(companySearchForm);
		
		return list;
	}

	/**
	 * 企業詳細表示
	 * @param username
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 */
	@RequestMapping(value= {"/detail"}, method=RequestMethod.GET)
	public ModelAndView detail(
			@ModelAttribute("id")Integer companyId,
    		ModelAndView mv
    		) throws SystemErrorException {
		logger.info("companydetail");
		
		CompanyDetailDto companyDto = companyService.getDetail(companyId);
		List<JobOfferListDto> jobList = jobService.getList(companyId);

		mv.addObject("companyDto", companyDto);
		mv.addObject("jobList", jobList);
        mv.setViewName("companydetail");
        
        return mv;
	}

	/* -private method- */
}
