package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 就職活動テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class JobHuntingTbl implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** job_hunting_id. */
	private Integer jobHuntingId;

	/** status. */
	private Integer status;

	/** step_start_datetime. */
	private Date stepStartDatetime;

	/** イベントテーブル. */
	private EventTbl eventTbl;

	/** need_report. */
	private Integer needReport;

	/** 学生テーブル. */
	private StudentTbl studentTbl;

	/** onbehalf_flg. */
	private Integer onbehalfFlg;

	/** 報告書 一覧. */
	private Set<ReportTbl> reportTblSet;

	/**
	 * コンストラクタ.
	 */
	public JobHuntingTbl() {
		this.reportTblSet = new HashSet<ReportTbl>();
	}

	/**
	 * job_hunting_id を設定します.
	 * 
	 * @param jobHuntingId
	 *            job_hunting_id
	 */
	public void setJobHuntingId(Integer jobHuntingId) {
		this.jobHuntingId = jobHuntingId;
	}

	/**
	 * job_hunting_id を取得します.
	 * 
	 * @return job_hunting_id
	 */
	public Integer getJobHuntingId() {
		return this.jobHuntingId;
	}

	/**
	 * status を設定します.
	 * 
	 * @param status
	 *            status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * status を取得します.
	 * 
	 * @return status
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * step_start_datetime を設定します.
	 * 
	 * @param stepStartDatetime
	 *            step_start_datetime
	 */
	public void setStepStartDatetime(Date stepStartDatetime) {
		this.stepStartDatetime = stepStartDatetime;
	}

	/**
	 * step_start_datetime を取得します.
	 * 
	 * @return step_start_datetime
	 */
	public Date getStepStartDatetime() {
		return this.stepStartDatetime;
	}

	/**
	 * イベントテーブル を設定します.
	 * 
	 * @param eventTbl
	 *            イベントテーブル
	 */
	public void setEventTbl(EventTbl eventTbl) {
		this.eventTbl = eventTbl;
	}

	/**
	 * イベントテーブル を取得します.
	 * 
	 * @return イベントテーブル
	 */
	public EventTbl getEventTbl() {
		return this.eventTbl;
	}

	/**
	 * need_report を設定します.
	 * 
	 * @param needReport
	 *            need_report
	 */
	public void setNeedReport(Integer needReport) {
		this.needReport = needReport;
	}

	/**
	 * need_report を取得します.
	 * 
	 * @return need_report
	 */
	public Integer getNeedReport() {
		return this.needReport;
	}

	/**
	 * 学生テーブル を設定します.
	 * 
	 * @param studentTbl
	 *            学生テーブル
	 */
	public void setStudentTbl(StudentTbl studentTbl) {
		this.studentTbl = studentTbl;
	}

	/**
	 * 学生テーブル を取得します.
	 * 
	 * @return 学生テーブル
	 */
	public StudentTbl getStudentTbl() {
		return this.studentTbl;
	}

	/**
	 * onbehalf_flg を設定します.
	 * 
	 * @param onbehalfFlg
	 *            onbehalf_flg
	 */
	public void setOnbehalfFlg(Integer onbehalfFlg) {
		this.onbehalfFlg = onbehalfFlg;
	}

	/**
	 * onbehalf_flg を取得します.
	 * 
	 * @return onbehalf_flg
	 */
	public Integer getOnbehalfFlg() {
		return this.onbehalfFlg;
	}

	/**
	 * 報告書 一覧を設定します.
	 * 
	 * @param reportTblSet
	 *            報告書 一覧
	 */
	public void setReportTblSet(Set<ReportTbl> reportTblSet) {
		this.reportTblSet = reportTblSet;
	}

	/**
	 * 報告書 を追加します.
	 * 
	 * @param reportTbl
	 *            報告書
	 */
	public void addReportTbl(ReportTbl reportTbl) {
		this.reportTblSet.add(reportTbl);
	}

	/**
	 * 報告書 一覧を取得します.
	 * 
	 * @return 報告書 一覧
	 */
	public Set<ReportTbl> getReportTblSet() {
		return this.reportTblSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobHuntingId == null) ? 0 : jobHuntingId.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		JobHuntingTbl other = (JobHuntingTbl) obj;
		if (jobHuntingId == null) {
			if (other.jobHuntingId != null) {
				return false;
			}
		} else if (!jobHuntingId.equals(other.jobHuntingId)) {
			return false;
		}
		return true;
	}

}
