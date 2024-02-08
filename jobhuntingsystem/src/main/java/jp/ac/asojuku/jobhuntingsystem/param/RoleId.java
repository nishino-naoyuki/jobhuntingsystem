package jp.ac.asojuku.jobhuntingsystem.param;

public enum RoleId {

	STUDENT(0,"学生"),
	TEACHER(1,"教員"),
	ADMIN(2,"管理者");
	

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


	private RoleId(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}
	
	public boolean isStudent() {
		return (this==STUDENT);
	}
	public boolean isTeacher() {
		return (this==TEACHER);
	}
	public boolean isAdmin() {
		return (this==ADMIN);
	}

	public boolean equals(Integer id){
		if(id == null){
			return false;
		}

		return (this.id == id);
	}

	public static RoleId search(Integer id){
		RoleId lvl = null;
		for( RoleId value : RoleId.values() ) {
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
		RoleId lvl = null;
		for( RoleId value : RoleId.values() ) {
			if( value.equals(id) ) {
				lvl = value;
			}
		}

		return (lvl == null ? "":lvl.getMsg());
	}
}
