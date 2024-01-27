package jp.ac.asojuku.jobhuntingsystem.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value= {"/signin",""}, method=RequestMethod.GET)
	public ModelAndView signin(
    		ModelAndView mv) {
		
		mv.setViewName("signin");
		
		return mv;
	}
	@RequestMapping(value= {"/signin"}, method=RequestMethod.POST)
	public ModelAndView auth(
    		ModelAndView mv,
    		@ModelAttribute("username")String username,
    		@ModelAttribute("password")String password
    		) {
		
		
		return new ModelAndView("redirect:dashboad");
	}
}
