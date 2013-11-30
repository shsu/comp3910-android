package ca.bcit.turnip.domain;

public class QuizUser {

    private int id;
    private String username;
    private String password;
    private String studentNumber;
    private String firstName;
    private String lastName;

    public QuizUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public QuizUser(String username, String password, String studentNumber,
            String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
