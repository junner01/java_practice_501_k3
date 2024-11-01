package ex_241023_cha15.homework;

public class Cgw0910UserData {
    private String userPassword;
    private String userEmail;

    public Cgw0910UserData(String userPassword, String userEmail) {
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }
}