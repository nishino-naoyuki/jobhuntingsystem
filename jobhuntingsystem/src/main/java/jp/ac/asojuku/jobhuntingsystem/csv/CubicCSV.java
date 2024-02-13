package jp.ac.asojuku.jobhuntingsystem.csv;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class CubicCSV {
	@CsvBindByName(column = "個人ｺｰﾄﾞ", required = true)
	private String studentno;
	@CsvBindByName(column = "氏名", required = true)
	private String name;
	@CsvBindByName(column = "学校略称", required = true)
	private String schoolname;
	@CsvBindByName(column = "学科", required = true)
	private String department;
	@CsvBindByName(column = "世代", required = true)
	private String gene;
	@CsvBindByName(column = "勤続年数", required = true)
	private String workyear;
	@CsvBindByName(column = "性別", required = true)
	private String sex;
	@CsvBindByName(column = "信頼係数", required = true)
	private String c_trust;
	@CsvBindByName(column = "思索型：内閉性", required = true)
	private String c_entrapment;
	@CsvBindByName(column = "思索型：客観性", required = true)
	private String c_objectivity;
	@CsvBindByName(column = "活動型：身体性", required = true)
	private String c_physicality;
	@CsvBindByName(column = "活動型：気分性", required = true)
	private String c_moodiness;
	@CsvBindByName(column = "努力型：持続性", required = true)
	private String c_persistence;
	@CsvBindByName(column = "努力型：規則性", required = true)
	private String c_regularity;
	@CsvBindByName(column = "積極型：競争性", required = true)
	private String c_competitiveness;
	@CsvBindByName(column = "積極型：自尊心", required = true)
	private String c_selfesteem;
	@CsvBindByName(column = "自制型：慎重性", required = true)
	private String c_prudence;
	@CsvBindByName(column = "自制型：弱気さ", required = true)
	private String c_bearishness;
	@CsvBindByName(column = "日常周辺事型", required = true)
	private String c_surroundings;
	@CsvBindByName(column = "客観・科学型", required = true)
	private String c_science;
	@CsvBindByName(column = "社会・経済型", required = true)
	private String c_society;
	@CsvBindByName(column = "心理・情緒型", required = true)
	private String c_psychology;
	@CsvBindByName(column = "審美・芸術型", required = true)
	private String c_art;
	@CsvBindByName(column = "積極性", required = true)
	private String c_positivity;
	@CsvBindByName(column = "協調性", required = true)
	private String c_cooperativeness;
	@CsvBindByName(column = "責任感", required = true)
	private String c_responsibility;
	@CsvBindByName(column = "自己信頼性", required = true)
	private String c_reliability;
	@CsvBindByName(column = "指導性", required = true)
	private String c_leadership;
	@CsvBindByName(column = "共感性", required = true)
	private String c_empathy;
	@CsvBindByName(column = "感情安定性", required = true)
	private String c_emotional_stability;
	@CsvBindByName(column = "従順性", required = true)
	private String c_obedience;
	@CsvBindByName(column = "自主性", required = true)
	private String c_autonomy;
	@CsvBindByName(column = "ﾓﾗﾄﾘｱﾑ傾向", required = true)
	private String c_moratorium;
	@CsvBindByName(column = "達成欲求", required = true)
	private String c_desire_success;
	@CsvBindByName(column = "親和欲求", required = true)
	private String c_affinity;
	@CsvBindByName(column = "求知欲求", required = true)
	private String c_seeking;
	@CsvBindByName(column = "顕示欲求", required = true)
	private String c_manifestation;
	@CsvBindByName(column = "秩序欲求", required = true)
	private String c_order;
	@CsvBindByName(column = "物質的欲望", required = true)
	private String c_material;
	@CsvBindByName(column = "危機耐性", required = true)
	private String c_crisis_resistance;
	@CsvBindByName(column = "自律欲求", required = true)
	private String c_independence;
	@CsvBindByName(column = "支配欲求", required = true)
	private String c_control;
	@CsvBindByName(column = "勤労意欲", required = true)
	private String c_workethic;
	@CsvBindByName(column = "自己：積極実行", required = true)
	private String c_active;
	@CsvBindByName(column = "自己：意欲熱意", required = true)
	private String c_enthusiasm;
	@CsvBindByName(column = "自己：根気強さ", required = true)
	private String c_perseverance;
	@CsvBindByName(column = "自己：責任感", required = true)
	private String c_responsibility2;
	@CsvBindByName(column = "自己：決断勇気", required = true)
	private String c_decisive;
	@CsvBindByName(column = "自己：指導力", required = true)
	private String c_leadership2;
	@CsvBindByName(column = "自己：リーダー", required = true)
	private String c_leader;
	@CsvBindByName(column = "自己：自己信頼", required = true)
	private String c_selftrust;
	@CsvBindByName(column = "自己：調整力", required = true)
	private String c_adjustment;
	@CsvBindByName(column = "自己：折衝力", required = true)
	private String c_negotiation;
	@CsvBindByName(column = "自己：独創斬新", required = true)
	private String c_innovative;
	@CsvBindByName(column = "自己：現状分析", required = true)
	private String c_analysis;
	@CsvBindByName(column = "自己：洞察力", required = true)
	private String c_insight;
	@CsvBindByName(column = "自己：企画立案", required = true)
	private String c_planning;
	@CsvBindByName(column = "自己：専門知識", required = true)
	private String c_expertise;
	@CsvBindByName(column = "自己：情報活用", required = true)
	private String c_utilization;
	@CsvBindByName(column = "ﾊﾟｰｿﾅﾘﾃｨｽｹｯﾁ", required = false)
	private String notuse1;
	@CsvBindByName(column = "環境０１：規則にやかましい人　", required = false)
	private String notuse2;
	@CsvBindByName(column = "環境０２：仕事内容説明不足な人", required = false)
	private String notuse3;
	@CsvBindByName(column = "環境０３：逐一報告を求める人　", required = false)
	private String notuse4;
	@CsvBindByName(column = "環境０４：計画悪く時間無駄な人", required = false)
	private String notuse5;
	@CsvBindByName(column = "環境０５：無口で内向的な人　　", required = false)
	private String notuse6;
	@CsvBindByName(column = "環境０６：手柄話を自慢する人　", required = false)
	private String notuse7;
	@CsvBindByName(column = "環境０７：規則通りで堅苦しい人", required = false)
	private String notuse8;
	@CsvBindByName(column = "環境０８：気分屋でやり放しの人", required = false)
	private String notuse9;
	@CsvBindByName(column = "環境０９：暗く引っ込み思案な人", required = false)
	private String notuse10;
	@CsvBindByName(column = "環境１０：すぐしゃしゃり出る人", required = false)
	private String notuse11;
	@CsvBindByName(column = "環境１１：耳の痛い事を言う人　", required = false)
	private String notuse12;
	@CsvBindByName(column = "環境１２：考えるより行動する人", required = false)
	private String notuse13;
	@CsvBindByName(column = "環境１３：頑固で頭のかたい人　", required = false)
	private String notuse14;
	@CsvBindByName(column = "環境１４：慎重すぎて作業遅い人", required = false)
	private String notuse15;
	@CsvBindByName(column = "環境１５：空想的で現実味薄い人", required = false)
	private String notuse16;
	@CsvBindByName(column = "環境１６：オドオドと自信ない人", required = false)
	private String notuse17;
	@CsvBindByName(column = "環境１７：自分勝手わがままな人", required = false)
	private String notuse18;
	@CsvBindByName(column = "環境１８：すぐ目立ちたがる人　", required = false)
	private String notuse19;
	@CsvBindByName(column = "環境１９：世間体を気にする人　", required = false)
	private String notuse20;
	@CsvBindByName(column = "環境２０：人の面倒見が悪い人　", required = false)
	private String notuse21;
	@CsvBindByName(column = "環境２１：付合い悪く別行動の人", required = false)
	private String notuse22;
	@CsvBindByName(column = "環境２２：他人を信用しない人　", required = false)
	private String notuse23;
	@CsvBindByName(column = "環境２３：相手で意見を変える人", required = false)
	private String notuse24;
	@CsvBindByName(column = "環境２４：知ったかぶりをする人", required = false)
	private String notuse25;
	@CsvBindByName(column = "環境２５：人の意見を聞かない人", required = false)
	private String notuse26;
	@CsvBindByName(column = "環境２６：かげで他人批判する人", required = false)
	private String notuse27;
	@CsvBindByName(column = "環境２７：主観意見を押付ける人", required = false)
	private String notuse28;
	@CsvBindByName(column = "環境２８：自分以外は無関心な人", required = false)
	private String notuse29;
	@CsvBindByName(column = "環境２９：失敗をくどくど言う人", required = false)
	private String notuse30;
	@CsvBindByName(column = "環境３０：前向きな姿勢がない人", required = false)
	private String notuse31;
	@CsvBindByName(column = "環境３１：同じ事にこだわる人　", required = false)
	private String notuse32;
	@CsvBindByName(column = "環境３２：ピントずれ発言する人", required = false)
	private String notuse33;
	@CsvBindByName(column = "環境３３：愚痴ばかりこぼす人　", required = false)
	private String notuse34;
	@CsvBindByName(column = "環境３４：話の腰を折る言動の人", required = false)
	private String notuse35;
	@CsvBindByName(column = "環境３５：責任転嫁をする人　　", required = false)
	private String notuse36;
	@CsvBindByName(column = "環境３６：学歴資格ひけらかす人", required = false)
	private String notuse37;
	@CsvBindByName(column = "環境３７：感情的な態度をとる人", required = false)
	private String notuse38;
	@CsvBindByName(column = "環境３８：落ち着かずイラつく人", required = false)
	private String notuse39;
	@CsvBindByName(column = "環境３９：神経質でこまかい人　", required = false)
	private String notuse40;
	@CsvBindByName(column = "環境４０：つきあいを強要する人", required = false)
	private String notuse41;
	@CsvBindByName(column = "活力０１：休日・長期休暇は十分", required = false)
	private String notuse42;
	@CsvBindByName(column = "活力０２：整理整頓等５Ｓが徹底", required = false)
	private String notuse43;
	@CsvBindByName(column = "活力０３：仕事の役割配分に満足", required = false)
	private String notuse44;
	@CsvBindByName(column = "活力０４：仕事の進め方・質向上", required = false)
	private String notuse45;
	@CsvBindByName(column = "活力０５：直接の上司は魅力ある", required = false)
	private String notuse46;
	@CsvBindByName(column = "活力０６：適材適所の配置である", required = false)
	private String notuse47;
	@CsvBindByName(column = "活力０７：チームワークは良好か", required = false)
	private String notuse48;
	@CsvBindByName(column = "活力０８：目標計画は遅れず達成", required = false)
	private String notuse49;
	@CsvBindByName(column = "活力０９：社員の定着性は良いか", required = false)
	private String notuse50;
	@CsvBindByName(column = "活力１０：職場は明るく活気ある", required = false)
	private String notuse51;
	@CsvBindByName(column = "活力１１：新卒者の採用は十分か", required = false)
	private String notuse52;
	@CsvBindByName(column = "活力１２：会社業績の伸びは順調", required = false)
	private String notuse53;
	@CsvBindByName(column = "活力１３：業務は確実に処理する", required = false)
	private String notuse54;
	@CsvBindByName(column = "活力１４：自己能力に適した賃金", required = false)
	private String notuse55;
	@CsvBindByName(column = "活力１５：自分の勤務成績に満足", required = false)
	private String notuse56;
	@CsvBindByName(column = "活力１６：顧客の満足度を考える", required = false)
	private String notuse57;
	@CsvBindByName(column = "活力１７：今後もここで働きたい", required = false)
	private String notuse58;
	@CsvBindByName(column = "活力１８：職場人員は十分である", required = false)
	private String notuse59;
	@CsvBindByName(column = "活力１９：はたらく環境はよいか", required = false)
	private String notuse60;
	@CsvBindByName(column = "活力２０：作業環境の改善は十分", required = false)
	private String notuse61;
	@CsvBindByName(column = "活力２１：他部門の情報は十分か", required = false)
	private String notuse62;
	@CsvBindByName(column = "活力２２：職場の就業規則は厳格", required = false)
	private String notuse63;
	@CsvBindByName(column = "活力２３：今の仕事に満足である", required = false)
	private String notuse64;
	@CsvBindByName(column = "活力２４：いまの社風は好ましい", required = false)
	private String notuse65;
	@CsvBindByName(column = "活力２５：誇りと自信のある会社", required = false)
	private String notuse66;
	@CsvBindByName(column = "活力２６：友人にも勧めたい会社", required = false)
	private String notuse67;
	@CsvBindByName(column = "活力２７：経営理念・目的は明確", required = false)
	private String notuse68;
	@CsvBindByName(column = "活力２８：将来性・成長性がある", required = false)
	private String notuse69;
	@CsvBindByName(column = "活力２９：厚生施設の内容に満足", required = false)
	private String notuse70;
	@CsvBindByName(column = "活力３０：上司の業務命令は的確", required = false)
	private String notuse71;
	@CsvBindByName(column = "一般的", required = true)
	private String c_general;
	@CsvBindByName(column = "精神力", required = true)
	private String c_mental;
	@CsvBindByName(column = "足　腰", required = true)
	private String c_legwaist;
	@CsvBindByName(column = "集中力", required = true)
	private String c_concentration;
	@CsvBindByName(column = "標準化", required = true)
	private String c_standardization;
	@CsvBindByName(column = "適性の幅", required = true)
	private String c_range;
	@CsvBindByName(column = "定着・安定", required = true)
	private String c_established;
	@CsvBindByName(column = "判定結果", required = true)
	private String c_typejudge;


}
