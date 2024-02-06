package jp.ac.asojuku.jobhuntingsystem.param;

public enum Level {

	NORMAL(0,"通常"),
	IMPORTANT(1,"重要"),
	EMERGENCY(2,"緊急"),
	WARNING(3,"警告");

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


	private Level(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}
	
	public boolean isNormal() {
		return (this==NORMAL);
	}
	public boolean isImportant() {
		return (this==IMPORTANT);
	}
	public boolean isEmergency() {
		return (this==EMERGENCY);
	}
	public boolean isWarning() {
		return (this==WARNING);
	}

	public boolean equals(Integer id){
		if(id == null){
			return false;
		}

		return (this.id == id);
	}

	public static Level search(Integer id){
		Level lvl = null;
		for( Level value : Level.values() ) {
			if( value.equals(id) ) {
				lvl = value;
			}
			
		}

		return lvl;
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
