package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 就職テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class JobHuntingTbl implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** job_hunting_id. */
	private Integer jobHuntingId;

	/** 学生テーブル. */
	private StudentTbl studentTbl;

	/** 採用求人テーブル. */
	private RecruitmentTbl recruitmentTbl;

	/** 就職活動詳細テーブル 一覧. */
	private Set<JobHuntingDetailTbl> jobHuntingDetailTblSet;

	/**
	 * コンストラクタ.
	 */
	public JobHuntingTbl() {
		this.jobHuntingDetailTblSet = new HashSet<JobHuntingDetailTbl>();
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
	 * 採用求人テーブル を設定します.
	 * 
	 * @param recruitmentTbl
	 *            採用求人テーブル
	 */
	public void setRecruitmentTbl(RecruitmentTbl recruitmentTbl) {
		this.recruitmentTbl = recruitmentTbl;
	}

	/**
	 * 採用求人テーブル を取得します.
	 * 
	 * @return 採用求人テーブル
	 */
	public RecruitmentTbl getRecruitmentTbl() {
		return this.recruitmentTbl;
	}

	/**
	 * 就職活動詳細テーブル 一覧を設定します.
	 * 
	 * @param jobHuntingDetailTblSet
	 *            就職活動詳細テーブル 一覧
	 */
	public void setJobHuntingDetailTblSet(Set<JobHuntingDetailTbl> jobHuntingDetailTblSet) {
		this.jobHuntingDetailTblSet = jobHuntingDetailTblSet;
	}

	/**
	 * 就職活動詳細テーブル を追加します.
	 * 
	 * @param jobHuntingDetailTbl
	 *            就職活動詳細テーブル
	 */
	public void addJobHuntingDetailTbl(JobHuntingDetailTbl jobHuntingDetailTbl) {
		this.jobHuntingDetailTblSet.add(jobHuntingDetailTbl);
	}

	/**
	 * 就職活動詳細テーブル 一覧を取得します.
	 * 
	 * @return 就職活動詳細テーブル 一覧
	 */
	public Set<JobHuntingDetailTbl> getJobHuntingDetailTblSet() {
		return this.jobHuntingDetailTblSet;
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