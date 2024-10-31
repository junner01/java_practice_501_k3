package ex_241023_cha15;

public class JDBC_Ex_DTO {
	// 모델 정의, 데이터를 
	// 비지니스 모델 (실제 디비)-> 프레젠테이션 모델(출력 형식)
	int deptno;  // dept 테이블의 칼럼을 저장하기 위한 인스턴스 변수를 정의.
	String dname;
	String loc;
	
	public JDBC_Ex_DTO() {
	
	}
	
	public JDBC_Ex_DTO(int deptno, String dname, String loc) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}
	public int getDeptno() {
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
