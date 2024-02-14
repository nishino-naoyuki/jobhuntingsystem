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
 * 企業テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="company_tbl")
public class CompanyEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** company_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer companyId;

	/** name. */
	private String name;

	/** furigana. */
	private String furigana;

	/** address. */
	private String address;

	/** code. */
	private String code;

	/** capital. */
	private Integer capital;

	/** establishment. */
	private Integer establishment;

	/** annualsales. */
	private Integer annualsales;

	/** url. */
	private String url;

	/** password. */
	private String password;

	/** pic. */
	private String pic;

	/** moc. */
	private String moc;

	/** toc. */
	private String toc;

}
