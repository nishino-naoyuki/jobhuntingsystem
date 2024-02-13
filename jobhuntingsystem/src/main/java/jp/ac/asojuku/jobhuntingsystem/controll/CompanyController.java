package jp.ac.asojuku.jobhuntingsystem.controll;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.asojuku.jobhuntingsystem.dto.IndustryDto;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.form.CompanyRegiForm;
import jp.ac.asojuku.jobhuntingsystem.service.IndustryService;

@Controller
@RequestMapping(value= {"/company"})
public class CompanyController {

	Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	IndustryService industryService;
	
	@RequestMapping(value= {"/regi"}, method=RequestMethod.GET)
    public ModelAndView input(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("company-input！");
		
		List<IndustryDto> industryList = industryService.getAllIndustryDto();
		mv.addObject("industryList", industryList);
		
        mv.setViewName("companyregi");
        
		return mv;
	}
	
	@RequestMapping(value= {"/regi"}, method=RequestMethod.POST)
    public ModelAndView registry(
    		@Valid CompanyRegiForm companyRegiForm,
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("company-input！");
		
		List<IndustryDto> industryList = industryService.getAllIndustryDto();
		mv.addObject("industryList", industryList);
		
        mv.setViewName("companyregi");
        
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
