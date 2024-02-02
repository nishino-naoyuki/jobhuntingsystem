package jp.ac.asojuku.jobhuntingsystem.controll;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ac.asojuku.jobhuntingsystem.config.MessageProperty;
import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.service.LoginService;

@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	@Autowired
	HttpSession session;
	
	/**
	 * ログイン画面表示
	 * @param mv
	 * @return
	 */
	@RequestMapping(value= {"/signin",""}, method=RequestMethod.GET)
	public ModelAndView signin(
    		ModelAndView mv
    		) {
		
		mv.setViewName("signin");
		
		return mv;
	}
	
	/**
	 * ログイン処理
	 * @param mv
	 * @param username
	 * @param password
	 * @return
	 * @throws SystemErrorException 
	 */
	@RequestMapping(value= {"/signin"}, method=RequestMethod.POST)
	public ModelAndView auth(
    		ModelAndView mv,
    		@ModelAttribute("username")String username,
    		@ModelAttribute("password")String password
    		) throws SystemErrorException {
		
		LoginInfoDto loginInfoDto = loginService.studentLogin(username, password);
		
		if( loginInfoDto == null ) {
			//ログイン失敗
			mv.addObject("msg", MessageProperty.getInstance().getLoginErrMessage());
			mv.addObject("username", username);
			mv.setViewName("signin");
		}else {
			//ログイン成功
			mv = new ModelAndView("redirect:/dashboard");
			session.setAttribute(SessionConst.LOGININFO, loginInfoDto);
		}
		
		return mv;
	}
}
