package jp.ac.asojuku.jobhuntingsystem.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * CSV処理の途中経過
 * @author nishino
 *
 */
@Data
public class CSVProgressDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;	//処理すべき件数
	private int now;	//現在処理した件数
	private String errorMsg;

}

