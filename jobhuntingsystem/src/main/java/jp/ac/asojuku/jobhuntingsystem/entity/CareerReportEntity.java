package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 進路決定テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="career_report_tbl")
public class CareerReportEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** career_report_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer careerReportId;

	/** 学生テーブル. */
	@OneToOne
    @JoinColumn(name="studentId",insertable=false ,updatable=false)
	private StudentEntity studentNostudentTbl;

}