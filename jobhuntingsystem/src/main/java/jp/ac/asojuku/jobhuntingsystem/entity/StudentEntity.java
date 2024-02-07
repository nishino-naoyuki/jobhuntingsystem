package jp.ac.asojuku.jobhuntingsystem.entity;

import java.io.Serializable;
import java.util.HashSet;
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
 * 学生テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="student_tbl")
public class StudentEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** student_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;

	/** password. */
	private String password;

	/** student_no. */
	private String studentNo;

	/** name. */
	private String name;

	/** mail. */
	private String mail;

	/** 学科テーブル. */
	private Integer classId;
	@OneToOne
    @JoinColumn(name="classId",insertable=false ,updatable=false)
	private ClassEntity classTbl;

	/** 就職活動状況マスタ. */
	private Integer jobhuntingStatusId;
	@OneToOne
    @JoinColumn(name="jobhuntingStatusId",insertable=false ,updatable=false)
	private JobhuntingStatusEntity jobhuntingStatusTbl;

	/** image. */
	private String image;

	/** tel. */
	private Integer tel;

	/** address. */
	private String address;

	/** year. */
	private Integer year;

	/** 役割テーブル. */
	@OneToOne
    @JoinColumn(name="roleId",insertable=false ,updatable=false)
	private RoleEntity roleTbl;
	
	/** c_trust. */
	private Integer cTrust;

	/** c_entrapment. */
	private Integer cEntrapment;

	/** c_objectivity. */
	private Integer cObjectivity;

	/** c_physicality. */
	private Integer cPhysicality;

	/** c_moodiness. */
	private Integer cMoodiness;

	/** c_persistence. */
	private Integer cPersistence;

	/** c_regularity. */
	private Integer cRegularity;

	/** c_competitiveness. */
	private Integer cCompetitiveness;

	/** c_selfesteem. */
	private Integer cSelfesteem;

	/** c_prudence. */
	private Integer cPrudence;

	/** c_bearishness. */
	private Integer cBearishness;

	/** c_surroundings. */
	private Integer cSurroundings;

	/** c_science. */
	private Integer cScience;

	/** c_society. */
	private Integer cSociety;

	/** c_psychology. */
	private Integer cPsychology;

	/** c_art. */
	private Integer cArt;

	/** c_positivity. */
	private Integer cPositivity;

	/** c_cooperativeness. */
	private Integer cCooperativeness;

	/** c_responsibility. */
	private Integer cResponsibility;

	/** c_reliability. */
	private Integer cReliability;

	/** c_leadership. */
	private Integer cLeadership;

	/** c_empathy. */
	private Integer cEmpathy;

	/** c_emotional_stability. */
	private Integer cEmotionalStability;

	/** c_obedience. */
	private Integer cObedience;

	/** c_autonomy. */
	private Integer cAutonomy;

	/** c_moratorium. */
	private Integer cMoratorium;

	/** c_desire_success. */
	private Integer cDesireSuccess;

	/** c_affinity. */
	private Integer cAffinity;

	/** c_seeking. */
	private Integer cSeeking;

	/** c_manifestation. */
	private Integer cManifestation;

	/** c_order. */
	private Integer cOrder;

	/** c_material. */
	private Integer cMaterial;

	/** c_crisis_resistance. */
	private Integer cCrisisResistance;

	/** c_independence. */
	private Integer cIndependence;

	/** c_control. */
	private Integer cControl;

	/** c_workethic. */
	private Integer cWorkethic;

	/** c_active. */
	private Integer cActive;

	/** c_enthusiasm. */
	private Integer cEnthusiasm;

	/** c_perseverance. */
	private Integer cPerseverance;

	/** c_responsibility2. */
	private Integer cResponsibility2;

	/** c_decisive. */
	private Integer cDecisive;

	/** c_leadership2. */
	private Integer cLeadership2;

	/** c_leader. */
	private Integer cLeader;

	/** c_selftrust. */
	private Integer cSelftrust;

	/** c_adjustment. */
	private Integer cAdjustment;

	/** c_negotiation. */
	private Integer cNegotiation;

	/** c_innovative. */
	private Integer cInnovative;

	/** c_analysis. */
	private Integer cAnalysis;

	/** c_Insight. */
	private Integer cInsight;

	/** c_planning. */
	private Integer cPlanning;

	/** c_expertise. */
	private Integer cExpertise;

	/** c_utilization. */
	private Integer cUtilization;

	/** c_general. */
	private Integer cGeneral;

	/** c_mental. */
	private Integer cMental;

	/** c_legwaist. */
	private Integer cLegwaist;

	/** c_concentration. */
	private Integer cConcentration;

	/** c_standardization. */
	private Integer cStandardization;

	/** c_range. */
	private Integer cRange;

	/** c_established. */
	private Integer cEstablished;

	/** c_typejudge. */
	private String cTypejudge;




}
