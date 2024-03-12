package jp.ac.asojuku.jobhuntingsystem.controll;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.exception.PermitionException;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.form.FavoritForm;
import jp.ac.asojuku.jobhuntingsystem.form.JobOfferInputForm;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.service.FavoriteService;

@Controller
@RequestMapping(value= {"/favorit"})
public class FavoriteController extends FileController {
	Logger logger = LoggerFactory.getLogger(FavoriteController.class);
	
	@Autowired
	FavoriteService favoritService;
	@Autowired
	HttpSession session;

	/**
	 * お気に入り登録処理
	 * 
	 * @param favoritForm
	 * @param bindingResult
	 * @return
	 * @throws SystemErrorException
	 * @throws JsonProcessingException
	 * @throws PermitionException
	 */
	@RequestMapping(value= {"/regi"}, method=RequestMethod.POST)
	@ResponseBody
    public Object register(
    		@Valid FavoritForm favoritForm,
    		BindingResult bindingResult
    		) throws SystemErrorException, JsonProcessingException, PermitionException {
		
		logger.info("job-regi！");

		//せっしょんからログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		if( !loginInfoDto.isStudent() ) {
			//お気に入り登録は学生のみ
			throw new PermitionException("この画面を表示する権限がありません");
		}
		
		//登録済みチェック
		if(favoritService.isAlreadyRegi(loginInfoDto.getUid(), favoritForm.getCompanyId())) {
			bindingResult.addError(new FieldError(bindingResult.getObjectName(), "company", "この企業はすでに登録済みです") );
		}

		if( bindingResult.hasErrors() ) {
			return getJson(bindingResult);
		}
		
		//登録処理
		favoritService.insert(loginInfoDto.getUid(), favoritForm.getCompanyId());
		
		return getJson(bindingResult);
	}
}
