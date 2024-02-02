package jp.ac.asojuku.jobhuntingsystem.config;

import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;

public class MessageProperty extends ConfigBase {

	//シングルトン
	private static MessageProperty prop = null;
	private static final String CONFIG_NAME = "messages.properties";
	
	public MessageProperty() throws SystemErrorException  {
		super();
	}

	//定数
	private static final String ERR_PROP_PREFIX = "errmsg";
	public static final String LOGIN_ERR_LOGINERR = "login.err.loginerr";
	
	/**
	 * インスタンスの取得
	 * @return
	 * @throws BookStoreSystemErrorException
	 */
	public static MessageProperty getInstance() throws SystemErrorException{

		if( prop == null ){
			prop = new MessageProperty();
		}

		return prop;
	}


	/**
	 * エラーコードよりエラーメッセージを取得する
	 * @param code
	 * @return
	 */
	//public String getErrorMsgFromErrCode(ErrorCode code){

	//	return getProperty(ERR_PROP_PREFIX+code.getCode());
	//}

	//　コンフィグファイルの名前を返す
	protected String getConfigName(){ return CONFIG_NAME; }
	
	public String getLoginErrMessage() { return getProperty(LOGIN_ERR_LOGINERR); }

}
