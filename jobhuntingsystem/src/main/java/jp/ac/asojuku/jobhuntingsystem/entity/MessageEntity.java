package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 個別メッセージテーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="message_tbl")
public class MessageEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** message_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageId;

	/** msg. */
	private String msg;

	/** 学生テーブル. */
	private Integer studentId;
	@OneToOne
    @JoinColumn(name="studentId",insertable=false ,updatable=false)
	private StudentEntity studentTbl;

	/** reg_date. */
	private Date regDate;

	/** level. */
	private Integer level;

	/** read_flg. */
	private Integer readFlg;
	
	/**
	 * コンストラクタ.
	 */
	public MessageEntity() {
	}


}