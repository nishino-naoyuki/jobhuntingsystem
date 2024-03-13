package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 業種テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class IndustrykindTbl implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** industrykind_id. */
	private Integer industrykindId;

	/** name. */
	private String name;

	/** 業界マスタ. */
	private IndustryTbl industryTbl;

	/** 企業業種テーブル 一覧. */
	private Set<CompanyIndustry> companyIndustrySet;

	/** 採用求人テーブル 一覧. */
	private Set<RecruitmentTbl> recruitmentTblSet;

	/** 採用求人テーブル 一覧. */
	private Set<RecruitmentTbl> recruitmentTblSet;

	/** 採用求人テーブル 一覧. */
	private Set<RecruitmentTbl> recruitmentTblSet;

	/** 採用求人テーブル 一覧. */
	private Set<RecruitmentTbl> recruitmentTblSet;

	/** 学生職種テーブル 一覧. */
	private Set<StudentIndustryTbl> studentIndustryTblSet;

	/**
	 * コンストラクタ.
	 */
	public IndustrykindTbl() {
		this.companyIndustrySet = new HashSet<CompanyIndustry>();
		this.recruitmentTblSet = new HashSet<RecruitmentTbl>();
		this.studentIndustryTblSet = new HashSet<StudentIndustryTbl>();
	}

	/**
	 * industrykind_id を設定します.
	 * 
	 * @param industrykindId
	 *            industrykind_id
	 */
	public void setIndustrykindId(Integer industrykindId) {
		this.industrykindId = industrykindId;
	}

	/**
	 * industrykind_id を取得します.
	 * 
	 * @return industrykind_id
	 */
	public Integer getIndustrykindId() {
		return this.industrykindId;
	}

	/**
	 * name を設定します.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * name を取得します.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 業界マスタ を設定します.
	 * 
	 * @param industryTbl
	 *            業界マスタ
	 */
	public void setIndustryTbl(IndustryTbl industryTbl) {
		this.industryTbl = industryTbl;
	}

	/**
	 * 業界マスタ を取得します.
	 * 
	 * @return 業界マスタ
	 */
	public IndustryTbl getIndustryTbl() {
		return this.industryTbl;
	}

	/**
	 * 企業業種テーブル 一覧を設定します.
	 * 
	 * @param companyIndustrySet
	 *            企業業種テーブル 一覧
	 */
	public void setCompanyIndustrySet(Set<CompanyIndustry> companyIndustrySet) {
		this.companyIndustrySet = companyIndustrySet;
	}

	/**
	 * 企業業種テーブル を追加します.
	 * 
	 * @param companyIndustry
	 *            企業業種テーブル
	 */
	public void addCompanyIndustry(CompanyIndustry companyIndustry) {
		this.companyIndustrySet.add(companyIndustry);
	}

	/**
	 * 企業業種テーブル 一覧を取得します.
	 * 
	 * @return 企業業種テーブル 一覧
	 */
	public Set<CompanyIndustry> getCompanyIndustrySet() {
		return this.companyIndustrySet;
	}

	/**
	 * 採用求人テーブル 一覧を設定します.
	 * 
	 * @param recruitmentTblSet
	 *            採用求人テーブル 一覧
	 */
	public void setRecruitmentTblSet(Set<RecruitmentTbl> recruitmentTblSet) {
		this.recruitmentTblSet = recruitmentTblSet;
	}

	/**
	 * 採用求人テーブル を追加します.
	 * 
	 * @param recruitmentTbl
	 *            採用求人テーブル
	 */
	public void addRecruitmentTbl(RecruitmentTbl recruitmentTbl) {
		this.recruitmentTblSet.add(recruitmentTbl);
	}

	/**
	 * 採用求人テーブル 一覧を取得します.
	 * 
	 * @return 採用求人テーブル 一覧
	 */
	public Set<RecruitmentTbl> getRecruitmentTblSet() {
		return this.recruitmentTblSet;
	}

	/**
	 * 採用求人テーブル 一覧を設定します.
	 * 
	 * @param recruitmentTblSet
	 *            採用求人テーブル 一覧
	 */
	public void setRecruitmentTblSet(Set<RecruitmentTbl> recruitmentTblSet) {
		this.recruitmentTblSet = recruitmentTblSet;
	}

	/**
	 * 採用求人テーブル を追加します.
	 * 
	 * @param recruitmentTbl
	 *            採用求人テーブル
	 */
	public void addRecruitmentTbl(RecruitmentTbl recruitmentTbl) {
		this.recruitmentTblSet.add(recruitmentTbl);
	}

	/**
	 * 採用求人テーブル 一覧を取得します.
	 * 
	 * @return 採用求人テーブル 一覧
	 */
	public Set<RecruitmentTbl> getRecruitmentTblSet() {
		return this.recruitmentTblSet;
	}

	/**
	 * 採用求人テーブル 一覧を設定します.
	 * 
	 * @param recruitmentTblSet
	 *            採用求人テーブル 一覧
	 */
	public void setRecruitmentTblSet(Set<RecruitmentTbl> recruitmentTblSet) {
		this.recruitmentTblSet = recruitmentTblSet;
	}

	/**
	 * 採用求人テーブル を追加します.
	 * 
	 * @param recruitmentTbl
	 *            採用求人テーブル
	 */
	public void addRecruitmentTbl(RecruitmentTbl recruitmentTbl) {
		this.recruitmentTblSet.add(recruitmentTbl);
	}

	/**
	 * 採用求人テーブル 一覧を取得します.
	 * 
	 * @return 採用求人テーブル 一覧
	 */
	public Set<RecruitmentTbl> getRecruitmentTblSet() {
		return this.recruitmentTblSet;
	}

	/**
	 * 採用求人テーブル 一覧を設定します.
	 * 
	 * @param recruitmentTblSet
	 *            採用求人テーブル 一覧
	 */
	public void setRecruitmentTblSet(Set<RecruitmentTbl> recruitmentTblSet) {
		this.recruitmentTblSet = recruitmentTblSet;
	}

	/**
	 * 採用求人テーブル を追加します.
	 * 
	 * @param recruitmentTbl
	 *            採用求人テーブル
	 */
	public void addRecruitmentTbl(RecruitmentTbl recruitmentTbl) {
		this.recruitmentTblSet.add(recruitmentTbl);
	}

	/**
	 * 採用求人テーブル 一覧を取得します.
	 * 
	 * @return 採用求人テーブル 一覧
	 */
	public Set<RecruitmentTbl> getRecruitmentTblSet() {
		return this.recruitmentTblSet;
	}

	/**
	 * 学生職種テーブル 一覧を設定します.
	 * 
	 * @param studentIndustryTblSet
	 *            学生職種テーブル 一覧
	 */
	public void setStudentIndustryTblSet(Set<StudentIndustryTbl> studentIndustryTblSet) {
		this.studentIndustryTblSet = studentIndustryTblSet;
	}

	/**
	 * 学生職種テーブル を追加します.
	 * 
	 * @param studentIndustryTbl
	 *            学生職種テーブル
	 */
	public void addStudentIndustryTbl(StudentIndustryTbl studentIndustryTbl) {
		this.studentIndustryTblSet.add(studentIndustryTbl);
	}

	/**
	 * 学生職種テーブル 一覧を取得します.
	 * 
	 * @return 学生職種テーブル 一覧
	 */
	public Set<StudentIndustryTbl> getStudentIndustryTblSet() {
		return this.studentIndustryTblSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((industrykindId == null) ? 0 : industrykindId.hashCode());
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
		IndustrykindTbl other = (IndustrykindTbl) obj;
		if (industrykindId == null) {
			if (other.industrykindId != null) {
				return false;
			}
		} else if (!industrykindId.equals(other.industrykindId)) {
			return false;
		}
		return true;
	}

}
