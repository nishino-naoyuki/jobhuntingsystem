package jp.ac.asojuku.jobhuntingsystem.param;

/**
 * 
 * ０：実施前
 * １：実施後
 * ２：合格
 * ３：不合格
 * @author nishino
 *
 */
public enum JHStatus {

	BEFORE(0,"実施前"),
	AFTER(1,"実施後"),
	PASSED(2,"合格"),
	NOTPASSED(3,"不合格");

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

	public boolean isBefore() {
		return equals(BEFORE.getId());
	}
	public boolean isAfter() {
		return equals(AFTER.getId());
	}
	public boolean isPassed() {
		return equals(PASSED.getId());
	}
	public boolean isNotPassed() {
		return equals(NOTPASSED.getId());
	}

	private JHStatus(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}

	public boolean equals(Integer id){
		if(id == null){
			return false;
		}

		return (this.id == id);
	}

	public static JHStatus search(Integer id){
		JHStatus type = null;
		for( JHStatus value : JHStatus.values() ) {
			if( value.equals(id) ) {
				type = value;
			}
			
		}

		return type;
	}

	public static boolean check(int id){
		boolean flg = false;
		for( JHStatus value : JHStatus.values() ) {
			if( value.equals(id) ) {
				flg = true;
			}
		}

		return flg;
	}

	public static String toString(Integer id){
		JHStatus lvl = null;
		for( JHStatus value : JHStatus.values() ) {
			if( value.equals(id) ) {
				lvl = value;
			}
		}

		return (lvl == null ? "":lvl.getMsg());
	}
}
