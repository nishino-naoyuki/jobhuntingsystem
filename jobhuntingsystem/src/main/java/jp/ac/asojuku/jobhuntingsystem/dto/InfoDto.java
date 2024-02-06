package jp.ac.asojuku.jobhuntingsystem.dto;

import java.time.LocalDateTime;

import jp.ac.asojuku.jobhuntingsystem.param.Level;
import jp.ac.asojuku.jobhuntingsystem.util.Exchange;
import lombok.Data;

@Data
public class InfoDto {
	public static int KIND_INFO = 0;
	public static int KIND_MSG = 1;
	
	private boolean alreadReadFlg;	//既読フラグ
	private LocalDateTime infoDate;	//記事の投稿時間
	private String title;			//タイトル
	private String msg;				//メッセージ
	private Level level;
	private Integer kind;
	private Integer id;
	
	public String getDateString() {
		return Exchange.toFormatString(infoDate);
	}
}
