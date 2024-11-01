package ex_241023_cha15.homework;

public class Kjy1122DTO {
    private String name;
    private String email;
    private String password;

    // 기본 생성자
    public Kjy1122DTO() {}

    // 매개변수 있는 생성자
    public Kjy1122DTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter와 Setter 메서드
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member [name=" + name + ", email=" + email + ", password=" + password + "]";
    }
}
