package jp.ac.asojuku.jobhuntingsystem.param;

public enum JobType {

	PERMANENT(0,"正社員"),
	CONTACT(1,"契約社員"),
	PARTTIME(2,"アルバイト");

	//ステータス
	private int id;
	private String msg;

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return msg1
	 */
	public String getMsg() {
		return msg;
	}


	private JobType(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}
	
	public boolean isPermanent() {
		return (this==PERMANENT);
	}
	public boolean isContact() {
		return (this==CONTACT);
	}
	public boolean isParttime() {
		return (this==PARTTIME);
	}

	public boolean equals(Integer id){
		if(id == null){
			return false;
		}

		return (this.id == id);
	}

	public static JobType search(Integer id){
		JobType type = null;
		for( JobType value : JobType.values() ) {
			if( value.equals(id) ) {
				type = value;
			}
			
		}

		return type;
	}

	public static boolean check(int id){
		boolean flg = false;
		for( Level value : Level.values() ) {
			if( value.equals(id) ) {
				flg = true;
			}
		}

		return flg;
	}

	public static String toString(Integer id){
		Level lvl = null;
		for( Level value : Level.values() ) {
			if( value.equals(id) ) {
				lvl = value;
			}
		}

		return (lvl == null ? "":lvl.getMsg());
	}
}
