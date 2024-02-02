package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
 * 就職活動詳細テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="job_hunting_detail_tbl")
public class JobHuntingDetailEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** job_hunting_detail_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobHuntingDetailId;

	/** 就職テーブル. */
	@OneToOne
    @JoinColumn(name="jobHuntingId",insertable=false ,updatable=false)
	private JobHuntingEntity jobHuntingTbl;

	/** stateus. */
	private Integer stateus;

	/** step_start_datetime. */
	private Date stepStartDatetime;

	/** イベントテーブル. */
	@OneToOne
    @JoinColumn(name="eventId",insertable=false ,updatable=false)
	private EventEntity eventTbl;

	/** need_report. */
	private Integer needReport;


}
