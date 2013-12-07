package ca.bcit.turnip.domain;

/**
 * The Class QuizUser.
 */
public class QuizUser {

	/** The id. */
    private int id;

	/** The username. */
    private String username;

	/** The password. */
    private String password;

	/** The student number. */
    private String studentNumber;

	/** The first name. */
    private String firstName;

	/** The last name. */
    private String lastName;

	/**
	 * Instantiates a new quiz user.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 */
    public QuizUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

	/**
	 * Instantiates a new quiz user.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param studentNumber
	 *            the student number
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 */
    public QuizUser(String username, String password, String studentNumber,
            String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
    public int getId() {
        return id;
    }

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
    public void setId(int id) {
        this.id = id;
    }

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
    public String getUsername() {
        return username;
    }

	/**
	 * Sets the username.
	 * 
	 * @param username
	 *            the new username
	 */
    public void setUsername(String username) {
        this.username = username;
    }

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
    public String getPassword() {
        return password;
    }

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
    public void setPassword(String password) {
        this.password = password;
    }

	/**
	 * Gets the student number.
	 * 
	 * @return the student number
	 */
    public String getStudentNumber() {
        return studentNumber;
    }

	/**
	 * Sets the student number.
	 * 
	 * @param studentNumber
	 *            the new student number
	 */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

	/**
	 * Gets the first name.
	 * 
	 * @return the first name
	 */
    public String getFirstName() {
        return firstName;
    }

	/**
	 * Sets the first name.
	 * 
	 * @param firstName
	 *            the new first name
	 */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	/**
	 * Gets the last name.
	 * 
	 * @return the last name
	 */
    public String getLastName() {
        return lastName;
    }

	/**
	 * Sets the last name.
	 * 
	 * @param lastName
	 *            the new last name
	 */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
