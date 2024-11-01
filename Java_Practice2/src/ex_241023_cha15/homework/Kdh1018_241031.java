package ex_241023_cha15.homework;


	import java.util.List;
	import java.util.Scanner;

	public class Kdh1018_241031 {
	    private Kdh1018DAO_241031 userDAO;

	    public Kdh1018_241031() {
	        userDAO = new Kdh1018DAO_241031();
	    }

	    public void run() {
	        Scanner scanner = new Scanner(System.in);
	        while (true) {//반복문
	            System.out.println("1. 회원가입");
	            System.out.println("2. 회원 조회");
	            System.out.println("3. 종료");
	            System.out.print("선택: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 

	            if (choice == 1) {
	               
	                System.out.print("이름: ");
	                String name = scanner.nextLine();
	                System.out.print("이메일: ");
	                String email = scanner.nextLine();
	                System.out.print("비밀번호: ");
	                String password = scanner.nextLine();

	                Kdh1018DTO_241031 user = new Kdh1018DTO_241031(name, email, password);
	                userDAO.addUser(user);
	            } else if (choice == 2) {
	                
	                List<Kdh1018DTO_241031> users = userDAO.getUsers();
	                System.out.println("회원 목록:");
	                for (Kdh1018DTO_241031 user : users) {
	                    System.out.println("이름: " + user.getName() + ", 이메일: " + user.getEmail());
	                }
	            } else if (choice == 3) {
	                System.out.println("프로그램 종료.");
	                break;
	            } else {
	                System.out.println("잘못된 선택입니다.");
	            }
	        }
	        scanner.close();
	    }

	    public static void main(String[] args) {
	        Kdh1018_241031 app = new Kdh1018_241031();
	        app.run();
	    }
	}


