package jp.ac.asojuku.jobhuntingsystem.dto;

import java.time.LocalDateTime;

import jp.ac.asojuku.jobhuntingsystem.param.Level;
import jp.ac.asojuku.jobhuntingsystem.util.Exchange;
import lombok.Data;

@Data
public class InfoDto {
	private boolean alreadReadFlg;	//既読フラグ
	private LocalDateTime infoDate;	//記事の投稿時間
	private String title;			//タイトル
	private String msg;				//メッセージ
	private Level level;
	
	public String getDate() {
		return Exchange.toFormatString(infoDate);
	}
}
