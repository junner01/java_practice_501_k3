package ex_241023_ch7.homework2;

public class Kjy1122HashMap {
	 private String name;
	 private String contact;
	 

	    public Kjy1122HashMap(String name, String contact ) {
	        this.name = name;
	        this.contact = contact;
	       
	    }

		public void setName(String name) {
			this.name = name;
		}



		public void setContact(String contact) {
			this.contact = contact;
		}



		public String getName() {
	        return name;
	    }

	    public String getContact() {
	        return contact;
	    }

	    public void printInfo() {
	        System.out.println("이름: " + name);
	        System.out.println("연락처: " + contact);
	    }
}
