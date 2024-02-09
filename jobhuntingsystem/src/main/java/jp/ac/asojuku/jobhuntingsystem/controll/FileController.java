package jp.ac.asojuku.jobhuntingsystem.controll;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.ac.asojuku.jobhuntingsystem.config.SystemConfig;
import jp.ac.asojuku.jobhuntingsystem.dto.CSVProgressDto;
import jp.ac.asojuku.jobhuntingsystem.exception.SystemErrorException;
import jp.ac.asojuku.jobhuntingsystem.util.FileUtils;

public class FileController extends RestControllerBase{

    /**
     * CSVファイルアップロード用のディレクトリを作成する
     * @return
     * @throws AZCafeException
     */
    protected File mkCSVUploaddirs() throws SystemErrorException{
    	
    	//アップロードディレクトリを取得する
    	StringBuilder filePath = new StringBuilder(SystemConfig.getInstance().getCsvuploaddir());
    	
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        File uploadDir = new File(filePath.toString(), sdf.format(now));
        // 既に存在する場合はプレフィックスをつける
        int prefix = 0;
        while(uploadDir.exists()){
            prefix++;
            uploadDir =
                    new File(filePath.toString() + sdf.format(now) + "-" + String.valueOf(prefix));
        }

        // フォルダ作成
        FileUtils.makeDir( uploadDir.toString());

        return uploadDir;
    }

	/**
	 * エラー処理時のJSON文字列を作成する
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	protected String outputErrorResult(List<String> errMsg) throws JsonProcessingException {
		CSVProgressDto progress = new CSVProgressDto();
		StringBuffer sb = new StringBuffer();

//		for( ActionError error : errors.getList() ){
//			sb.append( error.getMessage() );
//			sb.append("\n");
//		}
		for( String error : errMsg ){
			sb.append( error );
			sb.append("\n");
		}
		progress.setErrorMsg(sb.toString());

		ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(progress);

        logger.trace("jsonString:{}",jsonString);

        return jsonString;
	}
}
