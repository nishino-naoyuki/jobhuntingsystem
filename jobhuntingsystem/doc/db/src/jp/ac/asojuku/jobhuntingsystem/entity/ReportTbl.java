package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;

/**
 * 報告書 モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class ReportTbl implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** report_id. */
	private Integer reportId;

	/** 就職活動テーブル. */
	private JobHuntingTbl jobHuntingTbl;

	/**
	 * コンストラクタ.
	 */
	public ReportTbl() {
	}

	/**
	 * report_id を設定します.
	 * 
	 * @param reportId
	 *            report_id
	 */
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	/**
	 * report_id を取得します.
	 * 
	 * @return report_id
	 */
	public Integer getReportId() {
		return this.reportId;
	}

	/**
	 * 就職活動テーブル を設定します.
	 * 
	 * @param jobHuntingTbl
	 *            就職活動テーブル
	 */
	public void setJobHuntingTbl(JobHuntingTbl jobHuntingTbl) {
		this.jobHuntingTbl = jobHuntingTbl;
	}

	/**
	 * 就職活動テーブル を取得します.
	 * 
	 * @return 就職活動テーブル
	 */
	public JobHuntingTbl getJobHuntingTbl() {
		return this.jobHuntingTbl;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reportId == null) ? 0 : reportId.hashCode());
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
		ReportTbl other = (ReportTbl) obj;
		if (reportId == null) {
			if (other.reportId != null) {
				return false;
			}
		} else if (!reportId.equals(other.reportId)) {
			return false;
		}
		return true;
	}

}
