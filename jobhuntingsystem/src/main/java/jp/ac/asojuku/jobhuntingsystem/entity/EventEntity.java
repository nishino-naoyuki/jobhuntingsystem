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
 * イベントテーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="event_tbl")
public class EventEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** name. */
	private String name;

	/** event_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventId;

	/** ステップテーブル. */
	private Integer stepId;
	@OneToOne
    @JoinColumn(name="stepId",insertable=false ,updatable=false)
	private StepEntity stepTbl;

	/** document. */
	private String document;

	/** start_datetime. */
	private Date startDatetime;

	/** end_datetime. */
	private Date endDatetime;

	/** 企業テーブル. */
	private Integer companyId;
	@OneToOne
    @JoinColumn(name="companyId",insertable=false ,updatable=false)
	private CompanyEntity companyTbl;

	/** place. */
	private String place;

	/** 採用求人テーブル. */
	private Integer recruitmentId;
	@OneToOne
    @JoinColumn(name="recruitmentId",insertable=false ,updatable=false)
	private RecruitmentEntity recruitmentTbl;

	/** overview */
	private String overview;


}
