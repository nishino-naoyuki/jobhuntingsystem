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

import jp.ac.asojuku.jobhuntingsystem.dto.InfoDto;
import jp.ac.asojuku.jobhuntingsystem.dto.LoginInfoDto;
import jp.ac.asojuku.jobhuntingsystem.exception.PermitionException;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.form.InfoDetailForm;
import jp.ac.asojuku.jobhuntingsystem.form.InfoRegiForm;
import jp.ac.asojuku.jobhuntingsystem.param.Level;
import jp.ac.asojuku.jobhuntingsystem.param.SessionConst;
import jp.ac.asojuku.jobhuntingsystem.param.json.ErrorField;
import jp.ac.asojuku.jobhuntingsystem.param.json.InfoDetailJson;
import jp.ac.asojuku.jobhuntingsystem.param.json.ResultJson;
import jp.ac.asojuku.jobhuntingsystem.service.InfoService;

@Controller
@RequestMapping(value= {"/info"})
public class InformationController extends RestControllerBase{
	Logger logger = LoggerFactory.getLogger(InformationController.class);

	@Autowired
	HttpSession session;
	@Autowired
	InfoService infoService;

	/**
	 * お知らせ情報を取得する
	 * @param infoDetailForm
	 * @param bindingResult
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = { "/detail" }, method = RequestMethod.POST)
	@ResponseBody
	public Object getInfoDetail(
			@Valid InfoDetailForm infoDetailForm,
			BindingResult bindingResult
			) throws JsonProcessingException {
		//エラーがあったか？
		if( bindingResult.hasErrors() ) {
			return getJson(bindingResult,(InfoDto)null);
		}
		
		//ログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		//情報取得
		InfoDto infoDto = infoService.getInfoOneData(
										loginInfoDto.getUid(), 
										infoDetailForm.getId(), 
										infoDetailForm.getType());
		
		return getJson(bindingResult,infoDto);
		
	}

	/**
	 * お知らせ情報画面の表示
	 * @param mv
	 * @return
	 * @throws SystemErrorException
	 */
	@RequestMapping(value= {"/regi"}, method=RequestMethod.GET)
    public ModelAndView registration(
    		ModelAndView mv
    		) throws SystemErrorException {

        mv.setViewName("inforegi");
        
		return mv;
	}
	

	@RequestMapping(value = { "/regi" }, method = RequestMethod.POST)
	@ResponseBody
	public Object regiInfo(
			@Valid InfoRegiForm infoRegiForm,
			BindingResult bindingResult
			) throws JsonProcessingException, PermitionException {
		//エラーがあったか？
		if( bindingResult.hasErrors() ) {
			return getJson(bindingResult);
		}
		
		//ログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		if( !loginInfoDto.isAdmin() ) {
			logger.warn("管理者以外がお知らせ情報を登録しようとしています。");
			throw new PermitionException("InformationController");
		}
		
		if( infoRegiForm.getKind() == InfoRegiForm.KIND_INFO ) {
			infoService.insertInGeneralInfo(infoRegiForm.getTitle(), infoRegiForm.getMsg(), infoRegiForm.getLevel());
		}
		
		return getJson(bindingResult);
		
	}

	@RequestMapping(value = { "/readInfo" }, method = RequestMethod.POST)
	@ResponseBody
	public Object readInfo(
			@ModelAttribute("id")Integer id,
			@ModelAttribute("type")Integer type,
			BindingResult bindingResult
			) throws JsonProcessingException {
		
		//ログイン情報取得
		LoginInfoDto loginInfoDto =
				(LoginInfoDto)session.getAttribute(SessionConst.LOGININFO);
		
		//情報取得
		if( type == InfoRegiForm.KIND_INFO ) {
			infoService.deleteUnreadInfo(loginInfoDto.getUid(), id);
		}else {
			infoService.updateReadMessage(loginInfoDto.getUid(), id);
		}
		
		return getJson(bindingResult);
		
	}
	/* -private method- */
	/**
	 * JSON変換
	 * @param bindingResult
	 * @return
	 * @throws JsonProcessingException
	 */
	private String getJson(BindingResult bindingResult,InfoDto infoDto) throws JsonProcessingException {
		InfoDetailJson infoJson = new InfoDetailJson();
		List<ErrorField> errList = new ArrayList<>();
		for(FieldError error : bindingResult.getFieldErrors() ) {
			ErrorField errField = new ErrorField();
			errField.setField( error.getField() );
			errField.setMsg( error.getDefaultMessage() );
			errList.add(errField);
		}
		infoJson.setTitle((infoDto != null ? infoDto.getTitle():""));
		infoJson.setMsg((infoDto != null ? infoDto.getMsg():""));
		infoJson.setLevel((infoDto != null ? infoDto.getLevel():Level.WARNING));
		infoJson.setErrorList(errList);
		infoJson.setResult( (errList.size() > 0 ? "NG":"OK") );

		ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(infoJson);

        logger.trace("jsonString:{}",jsonString);

        return jsonString;
	}
}
