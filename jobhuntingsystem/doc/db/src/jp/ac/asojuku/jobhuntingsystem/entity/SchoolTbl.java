package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 学校テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class SchoolTbl implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** school_id. */
	private Integer schoolId;

	/** name. */
	private String name;

	/** abbreviation. */
	private String abbreviation;

	/** code. */
	private String code;

	/** 学科テーブル 一覧. */
	private Set<DepartmentTbl> departmentTblSet;

	/**
	 * コンストラクタ.
	 */
	public SchoolTbl() {
		this.departmentTblSet = new HashSet<DepartmentTbl>();
	}

	/**
	 * school_id を設定します.
	 * 
	 * @param schoolId
	 *            school_id
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * school_id を取得します.
	 * 
	 * @return school_id
	 */
	public Integer getSchoolId() {
		return this.schoolId;
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
	 * abbreviation を設定します.
	 * 
	 * @param abbreviation
	 *            abbreviation
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * abbreviation を取得します.
	 * 
	 * @return abbreviation
	 */
	public String getAbbreviation() {
		return this.abbreviation;
	}

	/**
	 * code を設定します.
	 * 
	 * @param code
	 *            code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * code を取得します.
	 * 
	 * @return code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 学科テーブル 一覧を設定します.
	 * 
	 * @param departmentTblSet
	 *            学科テーブル 一覧
	 */
	public void setDepartmentTblSet(Set<DepartmentTbl> departmentTblSet) {
		this.departmentTblSet = departmentTblSet;
	}

	/**
	 * 学科テーブル を追加します.
	 * 
	 * @param departmentTbl
	 *            学科テーブル
	 */
	public void addDepartmentTbl(DepartmentTbl departmentTbl) {
		this.departmentTblSet.add(departmentTbl);
	}

	/**
	 * 学科テーブル 一覧を取得します.
	 * 
	 * @return 学科テーブル 一覧
	 */
	public Set<DepartmentTbl> getDepartmentTblSet() {
		return this.departmentTblSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schoolId == null) ? 0 : schoolId.hashCode());
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
		SchoolTbl other = (SchoolTbl) obj;
		if (schoolId == null) {
			if (other.schoolId != null) {
				return false;
			}
		} else if (!schoolId.equals(other.schoolId)) {
			return false;
		}
		return true;
	}

}
