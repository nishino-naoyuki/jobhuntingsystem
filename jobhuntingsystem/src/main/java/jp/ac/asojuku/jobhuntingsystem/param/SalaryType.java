package jp.ac.asojuku.jobhuntingsystem.param;

public enum SalaryType {

	MONTHLY(0,"月給"),
	ANNUAL(1,"年棒"),
	DAILY(2,"日給");

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


	private SalaryType(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}
	
	public boolean isMonthly() {
		return (this==MONTHLY);
	}
	public boolean isAnnual() {
		return (this==ANNUAL);
	}
	public boolean isDaily() {
		return (this==DAILY);
	}

	public boolean equals(Integer id){
		if(id == null){
			return false;
		}

		return (this.id == id);
	}

	public static SalaryType search(Integer id){
		SalaryType type = null;
		for( SalaryType value : SalaryType.values() ) {
			if( value.equals(id) ) {
				type = value;
			}
			
		}

		return type;
	}

	public static boolean check(int id){
		boolean flg = false;
		for( SalaryType value : SalaryType.values() ) {
			if( value.equals(id) ) {
				flg = true;
			}
		}

		return flg;
	}

	public static String toString(Integer id){
		SalaryType lvl = null;
		for( SalaryType value : SalaryType.values() ) {
			if( value.equals(id) ) {
				lvl = value;
			}
		}

		return (lvl == null ? "":lvl.getMsg());
	}
}
