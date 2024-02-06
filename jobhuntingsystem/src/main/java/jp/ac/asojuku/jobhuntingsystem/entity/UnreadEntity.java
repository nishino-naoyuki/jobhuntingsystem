package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 未読テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="unread_tbl")
public class UnreadEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** unread_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer unreadId;

	/** 学生テーブル. */
	private Integer studentId;
	@OneToOne
    @JoinColumn(name="studentId",insertable=false ,updatable=false)
	private StudentEntity studentTbl;

	/** お知らせテーブル. */
	private Integer infoId;
	@OneToOne
    @JoinColumn(name="infoId",insertable=false ,updatable=false)
	private InfoEntity infoTbl;


}
