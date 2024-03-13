SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS career_report_tbl;
DROP TABLE IF EXISTS favorite_tbl;
DROP TABLE IF EXISTS report_tbl;
DROP TABLE IF EXISTS job_hunting_detail_tbl;
DROP TABLE IF EXISTS job_hunting_tbl;
DROP TABLE IF EXISTS message_tbl;
DROP TABLE IF EXISTS student_industry_tbl;
DROP TABLE IF EXISTS unread_tbl;
DROP TABLE IF EXISTS student_tbl;
DROP TABLE IF EXISTS class_tbl;
DROP TABLE IF EXISTS admin_tbl;
DROP TABLE IF EXISTS company_industry;
DROP TABLE IF EXISTS event_tbl;
DROP TABLE IF EXISTS graduate_tbl;
DROP TABLE IF EXISTS recruitment_tbl;
DROP TABLE IF EXISTS company_tbl;
DROP TABLE IF EXISTS department_tbl;
DROP TABLE IF EXISTS Industrykind_tbl;
DROP TABLE IF EXISTS industry_tbl;
DROP TABLE IF EXISTS info_tbl;
DROP TABLE IF EXISTS jobhunting_status_tbl;
DROP TABLE IF EXISTS recrutiment_type_tbl;
DROP TABLE IF EXISTS school_tbl;
DROP TABLE IF EXISTS step_tbl;




/* Create Tables */

CREATE TABLE admin_tbl
(
	admin_id int NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	mail varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	image varchar(1024) NOT NULL COMMENT '画像パス',
	role_id int NOT NULL COMMENT '役割ID
1:教員
2:職員',
	PRIMARY KEY (admin_id)
);


CREATE TABLE career_report_tbl
(
	career_report_id int NOT NULL AUTO_INCREMENT,
	student_no int NOT NULL COMMENT '学籍番号',
	PRIMARY KEY (career_report_id),
	UNIQUE (student_no)
);


CREATE TABLE class_tbl
(
	class_id int NOT NULL AUTO_INCREMENT COMMENT 'クラスID',
	name varchar(200) NOT NULL,
	department_id int NOT NULL,
	admin_id int NOT NULL COMMENT '担任の先生のID',
	PRIMARY KEY (class_id)
) COMMENT = 'クラスがない場合は「なし」のデータに紐づける';


CREATE TABLE company_industry
(
	ci_id int NOT NULL AUTO_INCREMENT,
	company_id int NOT NULL,
	industrykind_id int NOT NULL,
	PRIMARY KEY (ci_id)
);


CREATE TABLE company_tbl
(
	company_id int NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	furigana varchar(1024) NOT NULL COMMENT 'フリガナ',
	address varchar(1024) NOT NULL COMMENT '住所',
	code varchar(32) NOT NULL COMMENT '企業コード',
	capital int COMMENT '資本金',
	establishment int COMMENT '設立年（西暦）',
	annualsales int COMMENT '年商（千円）',
	url varchar(1024),
	password varchar(255) COMMENT 'パスワード
NULLは初期値で、NULLの場合はパスワード変更を促される',
	pic varchar(200) NOT NULL COMMENT '担当者名',
	moc varchar(255) NOT NULL COMMENT '担当者メール',
	toc varchar(255) COMMENT '担当者電話番号',
	PRIMARY KEY (company_id),
	UNIQUE (code)
);


CREATE TABLE department_tbl
(
	department_id int NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL COMMENT '学科名',
	school_id int NOT NULL,
	code varchar(10) NOT NULL COMMENT '塾システムの学科コード',
	PRIMARY KEY (department_id),
	UNIQUE (code)
);


CREATE TABLE event_tbl
(
	name varchar(200) NOT NULL,
	event_id int NOT NULL AUTO_INCREMENT,
	step_id int NOT NULL COMMENT 'イベント種類',
	document varchar(2000) NOT NULL COMMENT '必要書類',
	start_datetime datetime NOT NULL COMMENT 'イベント開始日時',
	end_datetime date COMMENT '終了日時
不明な場合はNULL',
	company_id int NOT NULL,
	place varchar(1024) NOT NULL COMMENT '開催場所',
	recruitment_id int COMMENT '求人情報へのリンク
まだ、求人がない場合もあるのでNULL許可',
	recruit_startdatetime datetime NOT NULL COMMENT '募集開始日時',
	recruit_enddatetime datetime NOT NULL COMMENT '募集終了日時',
	PRIMARY KEY (event_id)
);


CREATE TABLE favorite_tbl
(
	favorite_id int NOT NULL AUTO_INCREMENT,
	company_id int NOT NULL,
	student_id int NOT NULL,
	regdatetime timestamp NOT NULL,
	PRIMARY KEY (favorite_id)
);


CREATE TABLE graduate_tbl
(
	graduate_id int NOT NULL AUTO_INCREMENT,
	student_no varchar(7) NOT NULL COMMENT '学籍番号',
	name varchar(255) NOT NULL COMMENT '名前',
	year int NOT NULL COMMENT '卒業年',
	c_trust int COMMENT 'キュービック：信頼係数',
	c_entrapment int COMMENT 'キュービック：内閉性',
	c_objectivity int COMMENT 'キュービック：
思索型：客観性',
	c_physicality int COMMENT 'キュービック：
活動型：身体性',
	c_moodiness int COMMENT 'キュービック：
活動型：気分性',
	c_persistence int COMMENT 'キュービック：
努力型：持続性',
	c_regularity int COMMENT 'キュービック：
努力型：規則性',
	c_competitiveness int COMMENT 'キュービック：
積極型：競争性',
	c_selfesteem int COMMENT 'キュービック：
積極型：自尊心',
	c_prudence int COMMENT 'キュービック：
自制型：慎重性',
	c_bearishness int COMMENT 'キュービック：
自制型：弱気さ',
	c_surroundings int COMMENT 'キュービック：
日常周辺事型',
	c_science int COMMENT 'キュービック：
客観・科学型',
	c_society int COMMENT 'キュービック：
社会・経済型',
	c_psychology int COMMENT 'キュービック：
心理・情緒型',
	c_art int COMMENT 'キュービック：
　審美・芸術型',
	c_positivity int COMMENT 'キュービック：
積極性',
	c_cooperativeness int COMMENT 'キュービック：
協調性',
	c_responsibility int COMMENT 'キュービック：
積極性',
	c_reliability int COMMENT 'キュービック：
自己信頼性',
	c_leadership int COMMENT 'キュービック：
指導性',
	c_empathy int COMMENT 'キュービック：
共感性',
	c_emotional_stability int COMMENT 'キュービック：
感情安定性',
	c_obedience int COMMENT 'キュービック：
従順性',
	c_autonomy int COMMENT '自主性',
	c_moratorium int COMMENT 'キュービック：
モラトリアム',
	c_desire_success int COMMENT 'キュービック：
達成要求',
	c_affinity int COMMENT 'キュービック：
親和要求',
	c_seeking int COMMENT 'キュービック：
求知要求',
	c_manifestation int COMMENT 'キュービック：
顕示要求',
	c_order int COMMENT 'キュービック：
秩序要求',
	c_material int COMMENT 'キュービック：
　物理要求',
	c_crisis_resistance int COMMENT 'キュービック：
危機耐性',
	c_independence int COMMENT 'キュービック：
自律欲求',
	c_control int COMMENT 'キュービック：
支配要求',
	c_workethic int COMMENT 'キュービック：
労働意欲',
	c_active int COMMENT 'キュービック：
積極実行',
	c_enthusiasm int COMMENT 'キュービック：
自己：意欲熱意',
	c_perseverance int COMMENT 'キュービック：
自己：根気強さ',
	c_responsibility2 int COMMENT 'キュービック：
自己：責任感',
	c_decisive int COMMENT '自己：決断勇気',
	c_leadership2 int COMMENT 'キュービック：
自己：指導力',
	c_leader int COMMENT 'キュービック：
リーダー',
	c_selftrust int COMMENT 'キュービック：
自己：自己信頼',
	c_adjustment int COMMENT 'キュービック：
自己：調整力',
	c_negotiation int COMMENT 'キュービック：
自己：折衝力',
	c_innovative int COMMENT 'キュービック：
自己：独創斬新',
	c_analysis int COMMENT 'キュービック：
自己：現状分析',
	c_Insight int COMMENT 'キュービック：
洞察力',
	c_planning int COMMENT 'キュービック：
自己：企画立案',
	c_expertise int COMMENT 'キュービック：
自己：専門知識',
	c_utilization int COMMENT 'キュービック：
自己：情報活用',
	c_general int COMMENT 'キュービック：
一般的',
	c_mental int COMMENT 'キュービック：
精神力',
	c_legwaist int COMMENT 'キュービック：
足腰',
	c_concentration int COMMENT 'キュービック：
集中力',
	c_standardization int COMMENT 'キュービック：
標準化',
	c_range int COMMENT 'キュービック：
適性の幅',
	c_established int COMMENT 'キュービック：
安定・定着',
	c_typejudge varchar(5) COMMENT 'キュービック：
判定',
	school_name varchar(100) NOT NULL COMMENT '学校名',
	department_name varchar(100) NOT NULL COMMENT '学科名',
	teacher_name varchar(30) NOT NULL COMMENT '担任名',
	class_name varchar(100) NOT NULL COMMENT 'クラス名
無い場合は空文字',
	company_id int COMMENT '内定先企業',
	PRIMARY KEY (graduate_id),
	UNIQUE (student_no)
);


CREATE TABLE Industrykind_tbl
(
	industrykind_id int NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL COMMENT '業種名',
	industry_id int NOT NULL,
	PRIMARY KEY (industrykind_id)
);


CREATE TABLE industry_tbl
(
	industry_id int NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL COMMENT '業界名
（IT、医療などの大分類）',
	PRIMARY KEY (industry_id)
);


CREATE TABLE info_tbl
(
	info_id int NOT NULL AUTO_INCREMENT,
	title varchar(50) NOT NULL,
	msg varchar(10000) NOT NULL COMMENT '内容',
	level int NOT NULL COMMENT 'レベル
０：通常
１：重要
２：緊急
３：警告',
	info_date datetime NOT NULL,
	PRIMARY KEY (info_id)
);


CREATE TABLE jobhunting_status_tbl
(
	jobhunting_status_id int NOT NULL AUTO_INCREMENT COMMENT '担任名',
	name varchar(255) NOT NULL COMMENT '進路決定済み
斡旋停止中
活動中
など',
	PRIMARY KEY (jobhunting_status_id)
);


CREATE TABLE job_hunting_detail_tbl
(
	job_hunting_detail_id int NOT NULL AUTO_INCREMENT,
	job_hunting_id int NOT NULL,
	stateus int NOT NULL COMMENT '状況
０：実施前
１：実施後
２：合格
３：不合格',
	step_start_datetime datetime NOT NULL COMMENT 'このステップの開始日時',
	event_id int NOT NULL,
	need_report int NOT NULL COMMENT 'レポート必須
０：不要
１：必須',
	PRIMARY KEY (job_hunting_detail_id)
);


CREATE TABLE job_hunting_tbl
(
	job_hunting_id int NOT NULL AUTO_INCREMENT,
	student_id int NOT NULL,
	recruitment_id int NOT NULL,
	PRIMARY KEY (job_hunting_id)
);


CREATE TABLE message_tbl
(
	message_id int NOT NULL AUTO_INCREMENT,
	msg varchar(1000) NOT NULL,
	student_id int NOT NULL,
	reg_date date NOT NULL,
	level int NOT NULL,
	read_flg int NOT NULL COMMENT '既読フラグ
0:未読
1:既読',
	PRIMARY KEY (message_id)
);


CREATE TABLE recruitment_tbl
(
	recruitment_id int NOT NULL AUTO_INCREMENT,
	company_id int NOT NULL,
	type int NOT NULL COMMENT '雇用形式
０：正社員
１：契約社員
２：アルバイト',
	dormitory int NOT NULL COMMENT '寮の有無
０：なし
１：あり',
	salary_type int NOT NULL COMMENT '賃金タイプ
０：月給
１：年俸',
	recrutiment_code varchar(10) NOT NULL COMMENT '求人番号',
	recrutiment_type_id int NOT NULL COMMENT '求人タイプ',
	industry_kind_id1 int COMMENT '募集職種１',
	industry_kind_id2 int COMMENT '募集職種２',
	industry_kind_id3 int COMMENT '募集職種３',
	industry_kind_id4 int COMMENT '募集職種４',
	base_salary_for2 int NOT NULL COMMENT '基本給２年コース',
	base_salary_for3 int NOT NULL COMMENT 'base_salary_for3',
	base_salary_for4 int NOT NULL COMMENT 'base_salary_for4',
	salary_op1 varchar(100) COMMENT '手当項目１（オプション）',
	salary_op2 varchar(100) COMMENT '手当項目２',
	salary_op3 varchar(200),
	salary_op1for2 int,
	salary_op1for3 int,
	salary_op1for4 int,
	salary_op2for2 int,
	salary_op2for3 int,
	salary_op2for4 int,
	salary_op3for2 int,
	salary_op3for3 int,
	salary_op3for4 int,
	required_resume int NOT NULL COMMENT '必要書類：履歴書
０：不要
１：必要',
	required_expected int NOT NULL COMMENT '必要書類：卒業見込み
０：不要
１：必要',
	required_grades int NOT NULL COMMENT '必須書類：成績表
０：不要
１：必要',
	required_else varchar(2048) COMMENT '必要書類：その他
NULL：なし
',
	public_date date COMMENT '情報公開日
NULLの場合は公開されない',
	target_year int NOT NULL COMMENT '対象となる学生（卒業年度）',
	offerstart_date date DEFAULT NOW(), SYSDATE() COMMENT '募集開始日',
	offerend_flg int DEFAULT 0 NOT NULL COMMENT '0:募集中
1:募集終了',
	PRIMARY KEY (recruitment_id)
);


CREATE TABLE recrutiment_type_tbl
(
	recrutiment_type_id int NOT NULL AUTO_INCREMENT,
	name varchar(30) NOT NULL,
	PRIMARY KEY (recrutiment_type_id)
);


CREATE TABLE report_tbl
(
	report_id int NOT NULL AUTO_INCREMENT,
	job_hunting_detail_id int NOT NULL COMMENT 'どれに対する報告書か',
	PRIMARY KEY (report_id)
);


CREATE TABLE school_tbl
(
	school_id int NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL COMMENT '学校名',
	abbreviation varchar(255) NOT NULL COMMENT '略称',
	code varchar(10) NOT NULL COMMENT '塾システムの「学校コード」',
	PRIMARY KEY (school_id),
	UNIQUE (code)
);


CREATE TABLE step_tbl
(
	step_id int NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	PRIMARY KEY (step_id)
);


CREATE TABLE student_industry_tbl
(
	si_id int NOT NULL AUTO_INCREMENT,
	student_id int NOT NULL,
	industrykind_id int NOT NULL,
	PRIMARY KEY (si_id)
);


CREATE TABLE student_tbl
(
	student_id int NOT NULL AUTO_INCREMENT,
	password varchar(255) NOT NULL,
	student_no varchar(7) NOT NULL COMMENT '学籍番号',
	name varchar(255) NOT NULL COMMENT '名前',
	mail varchar(255) NOT NULL COMMENT 'メアド',
	class_id int NOT NULL COMMENT 'クラスID',
	jobhunting_status_id int NOT NULL COMMENT '担任名',
	image varchar(1024) NOT NULL COMMENT 'イメージ画像',
	tel varchar(30) NOT NULL COMMENT '電話番号
ハイフンありで登録',
	address varchar(512) NOT NULL COMMENT '住所',
	year int NOT NULL COMMENT '卒業年',
	c_trust int COMMENT 'キュービック：信頼係数',
	c_entrapment int COMMENT 'キュービック：内閉性',
	c_objectivity int COMMENT 'キュービック：
思索型：客観性',
	c_physicality int COMMENT 'キュービック：
活動型：身体性',
	c_moodiness int COMMENT 'キュービック：
活動型：気分性',
	c_persistence int COMMENT 'キュービック：
努力型：持続性',
	c_regularity int COMMENT 'キュービック：
努力型：規則性',
	c_competitiveness int COMMENT 'キュービック：
積極型：競争性',
	c_selfesteem int COMMENT 'キュービック：
積極型：自尊心',
	c_prudence int COMMENT 'キュービック：
自制型：慎重性',
	c_bearishness int COMMENT 'キュービック：
自制型：弱気さ',
	c_surroundings int COMMENT 'キュービック：
日常周辺事型',
	c_science int COMMENT 'キュービック：
客観・科学型',
	c_society int COMMENT 'キュービック：
社会・経済型',
	c_psychology int COMMENT 'キュービック：
心理・情緒型',
	c_art int COMMENT 'キュービック：
　審美・芸術型',
	c_positivity int COMMENT 'キュービック：
積極性',
	c_cooperativeness int COMMENT 'キュービック：
協調性',
	c_responsibility int COMMENT 'キュービック：
積極性',
	c_reliability int COMMENT 'キュービック：
自己信頼性',
	c_leadership int COMMENT 'キュービック：
指導性',
	c_empathy int COMMENT 'キュービック：
共感性',
	c_emotional_stability int COMMENT 'キュービック：
感情安定性',
	c_obedience int COMMENT 'キュービック：
従順性',
	c_autonomy int COMMENT '自主性',
	c_moratorium int COMMENT 'キュービック：
モラトリアム',
	c_desire_success int COMMENT 'キュービック：
達成要求',
	c_affinity int COMMENT 'キュービック：
親和要求',
	c_seeking int COMMENT 'キュービック：
求知要求',
	c_manifestation int COMMENT 'キュービック：
顕示要求',
	c_order int COMMENT 'キュービック：
秩序要求',
	c_material int COMMENT 'キュービック：
　物理要求',
	c_crisis_resistance int COMMENT 'キュービック：
危機耐性',
	c_independence int COMMENT 'キュービック：
自律欲求',
	c_control int COMMENT 'キュービック：
支配要求',
	c_workethic int COMMENT 'キュービック：
労働意欲',
	c_active int COMMENT 'キュービック：
積極実行',
	c_enthusiasm int COMMENT 'キュービック：
自己：意欲熱意',
	c_perseverance int COMMENT 'キュービック：
自己：根気強さ',
	c_responsibility2 int COMMENT 'キュービック：
自己：責任感',
	c_decisive int COMMENT '自己：決断勇気',
	c_leadership2 int COMMENT 'キュービック：
自己：指導力',
	c_leader int COMMENT 'キュービック：
リーダー',
	c_selftrust int COMMENT 'キュービック：
自己：自己信頼',
	c_adjustment int COMMENT 'キュービック：
自己：調整力',
	c_negotiation int COMMENT 'キュービック：
自己：折衝力',
	c_innovative int COMMENT 'キュービック：
自己：独創斬新',
	c_analysis int COMMENT 'キュービック：
自己：現状分析',
	c_Insight int COMMENT 'キュービック：
洞察力',
	c_planning int COMMENT 'キュービック：
自己：企画立案',
	c_expertise int COMMENT 'キュービック：
自己：専門知識',
	c_utilization int COMMENT 'キュービック：
自己：情報活用',
	c_general int COMMENT 'キュービック：
一般的',
	c_mental int COMMENT 'キュービック：
精神力',
	c_legwaist int COMMENT 'キュービック：
足腰',
	c_concentration int COMMENT 'キュービック：
集中力',
	c_standardization int COMMENT 'キュービック：
標準化',
	c_range int COMMENT 'キュービック：
適性の幅',
	c_established int COMMENT 'キュービック：
安定・定着',
	c_typejudge varchar(5) COMMENT 'キュービック：
判定',
	PRIMARY KEY (student_id),
	UNIQUE (student_no)
);


CREATE TABLE unread_tbl
(
	unread_id int NOT NULL AUTO_INCREMENT,
	student_id int NOT NULL,
	info_id int NOT NULL,
	PRIMARY KEY (unread_id)
);



/* Create Foreign Keys */

ALTER TABLE class_tbl
	ADD FOREIGN KEY (admin_id)
	REFERENCES admin_tbl (admin_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE student_tbl
	ADD FOREIGN KEY (class_id)
	REFERENCES class_tbl (class_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE company_industry
	ADD FOREIGN KEY (company_id)
	REFERENCES company_tbl (company_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE event_tbl
	ADD FOREIGN KEY (company_id)
	REFERENCES company_tbl (company_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE favorite_tbl
	ADD FOREIGN KEY (company_id)
	REFERENCES company_tbl (company_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE graduate_tbl
	ADD FOREIGN KEY (company_id)
	REFERENCES company_tbl (company_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE recruitment_tbl
	ADD FOREIGN KEY (company_id)
	REFERENCES company_tbl (company_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE class_tbl
	ADD FOREIGN KEY (department_id)
	REFERENCES department_tbl (department_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE job_hunting_detail_tbl
	ADD FOREIGN KEY (event_id)
	REFERENCES event_tbl (event_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE company_industry
	ADD FOREIGN KEY (industrykind_id)
	REFERENCES Industrykind_tbl (industrykind_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE recruitment_tbl
	ADD FOREIGN KEY (industry_kind_id2)
	REFERENCES Industrykind_tbl (industrykind_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE recruitment_tbl
	ADD FOREIGN KEY (industry_kind_id3)
	REFERENCES Industrykind_tbl (industrykind_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE recruitment_tbl
	ADD FOREIGN KEY (industry_kind_id1)
	REFERENCES Industrykind_tbl (industrykind_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE recruitment_tbl
	ADD FOREIGN KEY (industry_kind_id4)
	REFERENCES Industrykind_tbl (industrykind_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE student_industry_tbl
	ADD FOREIGN KEY (industrykind_id)
	REFERENCES Industrykind_tbl (industrykind_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Industrykind_tbl
	ADD FOREIGN KEY (industry_id)
	REFERENCES industry_tbl (industry_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE unread_tbl
	ADD FOREIGN KEY (info_id)
	REFERENCES info_tbl (info_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE student_tbl
	ADD FOREIGN KEY (jobhunting_status_id)
	REFERENCES jobhunting_status_tbl (jobhunting_status_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE report_tbl
	ADD FOREIGN KEY (job_hunting_detail_id)
	REFERENCES job_hunting_detail_tbl (job_hunting_detail_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE job_hunting_detail_tbl
	ADD FOREIGN KEY (job_hunting_id)
	REFERENCES job_hunting_tbl (job_hunting_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE event_tbl
	ADD FOREIGN KEY (recruitment_id)
	REFERENCES recruitment_tbl (recruitment_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE job_hunting_tbl
	ADD FOREIGN KEY (recruitment_id)
	REFERENCES recruitment_tbl (recruitment_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE recruitment_tbl
	ADD FOREIGN KEY (recrutiment_type_id)
	REFERENCES recrutiment_type_tbl (recrutiment_type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE department_tbl
	ADD FOREIGN KEY (school_id)
	REFERENCES school_tbl (school_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE event_tbl
	ADD FOREIGN KEY (step_id)
	REFERENCES step_tbl (step_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE career_report_tbl
	ADD FOREIGN KEY (student_no)
	REFERENCES student_tbl (student_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE favorite_tbl
	ADD FOREIGN KEY (student_id)
	REFERENCES student_tbl (student_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE job_hunting_tbl
	ADD FOREIGN KEY (student_id)
	REFERENCES student_tbl (student_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE message_tbl
	ADD FOREIGN KEY (student_id)
	REFERENCES student_tbl (student_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE student_industry_tbl
	ADD FOREIGN KEY (student_id)
	REFERENCES student_tbl (student_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE unread_tbl
	ADD FOREIGN KEY (student_id)
	REFERENCES student_tbl (student_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



