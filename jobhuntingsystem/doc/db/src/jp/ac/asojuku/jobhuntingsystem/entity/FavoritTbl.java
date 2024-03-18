package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * お気に入りテーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class FavoritTbl implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** favorit_id. */
	private Integer favoritId;

	/** 企業テーブル. */
	private CompanyTbl companyTbl;

	/** 学生テーブル. */
	private StudentTbl studentTbl;

	/** regdatetime. */
	private Date regdatetime;

	/**
	 * コンストラクタ.
	 */
	public FavoritTbl() {
	}

	/**
	 * favorit_id を設定します.
	 * 
	 * @param favoritId
	 *            favorit_id
	 */
	public void setFavoritId(Integer favoritId) {
		this.favoritId = favoritId;
	}

	/**
	 * favorit_id を取得します.
	 * 
	 * @return favorit_id
	 */
	public Integer getFavoritId() {
		return this.favoritId;
	}

	/**
	 * 企業テーブル を設定します.
	 * 
	 * @param companyTbl
	 *            企業テーブル
	 */
	public void setCompanyTbl(CompanyTbl companyTbl) {
		this.companyTbl = companyTbl;
	}

	/**
	 * 企業テーブル を取得します.
	 * 
	 * @return 企業テーブル
	 */
	public CompanyTbl getCompanyTbl() {
		return this.companyTbl;
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
	 * regdatetime を設定します.
	 * 
	 * @param regdatetime
	 *            regdatetime
	 */
	public void setRegdatetime(Date regdatetime) {
		this.regdatetime = regdatetime;
	}

	/**
	 * regdatetime を取得します.
	 * 
	 * @return regdatetime
	 */
	public Date getRegdatetime() {
		return this.regdatetime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((favoritId == null) ? 0 : favoritId.hashCode());
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
		FavoritTbl other = (FavoritTbl) obj;
		if (favoritId == null) {
			if (other.favoritId != null) {
				return false;
			}
		} else if (!favoritId.equals(other.favoritId)) {
			return false;
		}
		return true;
	}

}