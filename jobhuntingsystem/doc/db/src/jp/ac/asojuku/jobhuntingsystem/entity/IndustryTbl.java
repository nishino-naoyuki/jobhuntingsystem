package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 業界マスタ モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class IndustryTbl implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** industry_id. */
	private Integer industryId;

	/** name. */
	private String name;

	/** 業種テーブル 一覧. */
	private Set<IndustrykindTbl> industrykindTblSet;

	/**
	 * コンストラクタ.
	 */
	public IndustryTbl() {
		this.industrykindTblSet = new HashSet<IndustrykindTbl>();
	}

	/**
	 * industry_id を設定します.
	 * 
	 * @param industryId
	 *            industry_id
	 */
	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	/**
	 * industry_id を取得します.
	 * 
	 * @return industry_id
	 */
	public Integer getIndustryId() {
		return this.industryId;
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
	 * 業種テーブル 一覧を設定します.
	 * 
	 * @param industrykindTblSet
	 *            業種テーブル 一覧
	 */
	public void setIndustrykindTblSet(Set<IndustrykindTbl> industrykindTblSet) {
		this.industrykindTblSet = industrykindTblSet;
	}

	/**
	 * 業種テーブル を追加します.
	 * 
	 * @param industrykindTbl
	 *            業種テーブル
	 */
	public void addIndustrykindTbl(IndustrykindTbl industrykindTbl) {
		this.industrykindTblSet.add(industrykindTbl);
	}

	/**
	 * 業種テーブル 一覧を取得します.
	 * 
	 * @return 業種テーブル 一覧
	 */
	public Set<IndustrykindTbl> getIndustrykindTblSet() {
		return this.industrykindTblSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((industryId == null) ? 0 : industryId.hashCode());
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
		IndustryTbl other = (IndustryTbl) obj;
		if (industryId == null) {
			if (other.industryId != null) {
				return false;
			}
		} else if (!industryId.equals(other.industryId)) {
			return false;
		}
		return true;
	}

}
