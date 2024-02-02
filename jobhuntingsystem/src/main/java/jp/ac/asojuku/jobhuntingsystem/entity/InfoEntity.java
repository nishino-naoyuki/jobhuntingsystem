package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * お知らせテーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="info_tbl")
public class InfoEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** info_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer infoId;

	/** title. */
	private String title;

	/** msg. */
	private String msg;

	/** level. */
	private Integer level;

	/** info_date. */
	private Date infoDate;

	/**
	 * コンストラクタ.
	 */
	public InfoEntity() {
	}


}
