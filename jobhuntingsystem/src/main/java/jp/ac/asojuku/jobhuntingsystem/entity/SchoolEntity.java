package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 学校テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="school_tbl")
public class SchoolEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** school_tbl. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer schoolId;

	/** name. */
	private String name;

	/** abbreviation. */
	private String abbreviation;

	/** code. */
	private String code;


}