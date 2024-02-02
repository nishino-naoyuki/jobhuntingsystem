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
 * 報告書 モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="report_tbl")
public class ReportEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** report_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reportId;

	/** 就職活動詳細テーブル. */
	@OneToOne
    @JoinColumn(name="jobHuntingDetailId",insertable=false ,updatable=false)
	private JobHuntingDetailEntity jobHuntingDetailTbl;

	/**
	 * コンストラクタ.
	 */
	public ReportEntity() {
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
	 * 就職活動詳細テーブル を設定します.
	 * 
	 * @param jobHuntingDetailTbl
	 *            就職活動詳細テーブル
	 */
	public void setJobHuntingDetailTbl(JobHuntingDetailEntity jobHuntingDetailTbl) {
		this.jobHuntingDetailTbl = jobHuntingDetailTbl;
	}

	/**
	 * 就職活動詳細テーブル を取得します.
	 * 
	 * @return 就職活動詳細テーブル
	 */
	public JobHuntingDetailEntity getJobHuntingDetailTbl() {
		return this.jobHuntingDetailTbl;
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
		ReportEntity other = (ReportEntity) obj;
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
