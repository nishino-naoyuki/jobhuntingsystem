package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 採用求人テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="recruitment_tbl")
public class RecruitmentEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** recruitment_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recruitmentId;

	/** 企業テーブル. */
	private Integer companyId;
	@OneToOne
    @JoinColumn(name="companyId",insertable=false ,updatable=false)
	private CompanyEntity companyTbl;

	/** type. */
	private Integer type;

	/** dormitory. */
	private Integer dormitory;

	/** salary_type. */
	private Integer salaryType;

	/** recrutiment_code. */
	private String recrutimentCode;

	/** 求人タイプテーブル. */
	private Integer recrutimentTypeId;
	@ManyToOne
    @JoinColumn(name="recrutimentTypeId",insertable=false ,updatable=false)
	private RecrutimentTypeEntity recrutimentTypeTbl;

	/** 業種テーブル. */
	private Integer industryKindId1;
	@ManyToOne
    @JoinColumn(name="industryKindId1",referencedColumnName="industrykindId",insertable=false ,updatable=false)
	private IndustrykindEntity industryKindId1industrykindTbl;

	/** 業種テーブル. */
	private Integer industryKindId2;
	@ManyToOne
    @JoinColumn(name="industryKindId2",referencedColumnName="industrykindId",insertable=false ,updatable=false)
	private IndustrykindEntity industryKindId2industrykindTbl;

	/** 業種テーブル. */
	private Integer industryKindId3;
	@ManyToOne
    @JoinColumn(name="industryKindId3",referencedColumnName="industrykindId",insertable=false ,updatable=false)
	private IndustrykindEntity industryKindId3industrykindTbl;

	/** 業種テーブル. */
	private Integer industryKindId4;
	@ManyToOne
    @JoinColumn(name="industryKindId4",referencedColumnName="industrykindId",insertable=false ,updatable=false)
	private IndustrykindEntity industryKindId4industrykindTbl;

	/** base_salary_for2. */
	private Integer baseSalaryFor2;

	/** base_salary_for3. */
	private Integer baseSalaryFor3;

	/** base_salary_for4. */
	private Integer baseSalaryFor4;

	/** salary_op1. */
	private String salaryOp1;

	/** salary_op2. */
	private String salaryOp2;

	/** salary_op3. */
	private String salaryOp3;

	/** salary_op1_for2. */
	private Integer salaryOp1For2;

	/** salary_op1_for3. */
	private Integer salaryOp1For3;

	/** salary_op1_for4. */
	private Integer salaryOp1For4;

	/** salary_op2_for2. */
	private Integer salaryOp2For2;

	/** salary_op2_for3. */
	private Integer salaryOp2For3;

	/** salary_op2_for4. */
	private Integer salaryOp2For4;

	/** salary_op3_for2. */
	private Integer salaryOp3For2;

	/** salary_op3_for3. */
	private Integer salaryOp3For3;

	/** salary_op3_for4. */
	private Integer salaryOp3For4;

	/** required_resume. */
	private Integer requiredResume;

	/** required_expected. */
	private Integer requiredExpected;

	/** required_grades. */
	private Integer requiredGrades;

	/** required_else. */
	private String requiredElse;

	/** public_date. */
	private Date publicDate;

	/** target_year. */
	private Integer targetYear;

	/** offerstart_date. */
	private Date offerstartDate;

	/** offerend_flg. */
	private Integer offerendFlg;

	/**
	 * コンストラクタ.
	 */
	public RecruitmentEntity() {
	}
}
