package ex_241023_cha15;

public class JDBC_Ex_DTO {
	int deptno;  // dept 테이블의 칼럼을 저장하기 위한 인스턴스 변수를 정의.
	String dname;
	String loc;
	
	public JDBC_Ex_DTO(int deptno, String dname, String loc) {
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}
	public JDBC_Ex_DTO() {}

	public int getDeptno() { // 인스턴스 변수에 값을 설정하는 Setter 메소드와 인스턴스 변수의 값을
		// 읽어 오는 Getter 메소드를 정의.
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
}
