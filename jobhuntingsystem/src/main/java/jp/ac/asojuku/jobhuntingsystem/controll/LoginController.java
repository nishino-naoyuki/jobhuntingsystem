package jp.ac.asojuku.jobhuntingsystem.controll;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

	////////test/////////////
	@Autowired
	private JavaMailSender javaMailSender;

    public void javaMailSender() {
        // 新しいメッセージを作成
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
        	MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom("xxxx@gmail.com");
            messageHelper.setTo("nishino@asojuku.ac.jp");
            messageHelper.setText("お元気ですかテストです。JavaMail利用です",
                    "<span style='color: red'>赤文字出力</span><br>段落下げた");
            messageHelper.setSubject("タイトルですよ");

            javaMailSender.send(message);
        } catch(MessagingException e) {
            throw new RuntimeException("メッセージの設定に失敗しました", e);
        }
    }
	/////////////////////////
	/**
	 * ログイン画面表示
	 * @param mv
	 * @return
	 */
	@RequestMapping(value= {"/signin/admin","/admin"}, method=RequestMethod.GET)
	public ModelAndView signinAdmin(
    		ModelAndView mv
    		) {
		
		mv.setViewName("signinadmin");
		
		return mv;
	}

	@RequestMapping(value= {"/signin/company","/company"}, method=RequestMethod.GET)
	public ModelAndView signinCompany(
    		ModelAndView mv
    		) {
		mv.setViewName("signincompany");
		
		//javaMailSender();//test
		
		
		return mv;
	}
	
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
		
		return getModelAndViewAfterAuth(mv,loginInfoDto,username,"signin");
	}

	/**
	 * 管理者ログイン処理
	 * @param mv
	 * @param username
	 * @param password
	 * @return
	 * @throws SystemErrorException 
	 */
	@RequestMapping(value= {"/signin/admin"}, method=RequestMethod.POST)
	public ModelAndView authAdmin(
    		ModelAndView mv,
    		@ModelAttribute("username")String username,
    		@ModelAttribute("password")String password
    		) throws SystemErrorException {
		
		LoginInfoDto loginInfoDto = loginService.adminLogin(username, password);
		
		return getModelAndViewAfterAuth(mv,loginInfoDto,username,"signinadmin");
	}

	@RequestMapping(value= {"/signin/company"}, method=RequestMethod.POST)
	public ModelAndView authCompany(
    		ModelAndView mv,
    		@ModelAttribute("username")String username,
    		@ModelAttribute("password")String password
    		) throws SystemErrorException {
		
		LoginInfoDto loginInfoDto = loginService.companyLogin(username, password);
		
		return getModelAndViewAfterAuth(mv,loginInfoDto,username,"signincompany");
	}
	
	@RequestMapping(value= {"/logout"}, method=RequestMethod.GET)
	public ModelAndView logout(
    		ModelAndView mv
    		) throws SystemErrorException {

		//ログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		String redirectUrl = "/signin";
		if(loginInfoDto != null ) {
			redirectUrl = (loginInfoDto.isAdmin()? "/signin/admin" : (loginInfoDto.isStudent() ? "/signin":"/signin/company"));
		}
		
		return new ModelAndView("redirect:"+redirectUrl);
	}
	
	
	/* -private method- */
	private ModelAndView getModelAndViewAfterAuth(
			ModelAndView mv,
			LoginInfoDto loginInfoDto,
			String username,String returnViewname) throws SystemErrorException {

		if( loginInfoDto == null ) {
			//ログイン失敗
			mv.addObject("msg", MessageProperty.getInstance().getLoginErrMessage());
			mv.addObject("username", username);
			mv.setViewName(returnViewname);
		}else {
			//ログイン成功
			mv = new ModelAndView("redirect:/dashboard");
			session.setAttribute(SessionConst.LOGININFO, loginInfoDto);
		}
		
		return mv;
	}
}
