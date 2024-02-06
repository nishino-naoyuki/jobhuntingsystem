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

import jp.ac.asojuku.jobhuntingsystem.dto.InfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.service.InfoService;

@Controller
public class DashboardController {

	Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@Autowired
	InfoService infoService;
	@Autowired
	HttpSession session;
	
	/**
	 * ダッシュボードの表示処理
	 * 
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 */
	@RequestMapping(value= {"/dashboard"}, method=RequestMethod.GET)
    public ModelAndView dashboad(
    		ModelAndView mv
    		) throws SystemErrorException {
		
		logger.info("dashboard！");
		
		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		//お知らせ情報取得
		Integer studentId = (loginInfoDto.isStudent()? loginInfoDto.getUid():null);
		List<InfoDto> infoList = infoService.getInfoData(studentId);
		
		mv.addObject("infoList", infoList);
        mv.setViewName("dashboard");
        
		return mv;
	}
	
	
}
