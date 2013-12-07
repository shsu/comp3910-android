package ca.bcit.turnip.domain;

/**
 * The Class QuizResult.
 */
public class QuizResult {

	/** The id. */
    private int id;

	/** The user id. */
    private int userID;

	/** The week. */
    private int week;

	/** The score. */
    private int score;

	/** The total possible score. */
    private int totalPossibleScore;

	/**
	 * Instantiates a new quiz result.
	 */
    public QuizResult() {
    }

	/**
	 * Instantiates a new quiz result.
	 * 
	 * @param score
	 *            the score
	 * @param totalPossibleScore
	 *            the total possible score
	 */
    public QuizResult(int score, int totalPossibleScore) {
        this.score = score;
        this.totalPossibleScore = totalPossibleScore;
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
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
    public int getUserID() {
        return userID;
    }

	/**
	 * Sets the user id.
	 * 
	 * @param userID
	 *            the new user id
	 */
    public void setUserID(int userID) {
        this.userID = userID;
    }

	/**
	 * Gets the week.
	 * 
	 * @return the week
	 */
    public int getWeek() {
        return week;
    }

	/**
	 * Sets the week.
	 * 
	 * @param week
	 *            the new week
	 */
    public void setWeek(int week) {
        this.week = week;
    }

	/**
	 * Gets the score.
	 * 
	 * @return the score
	 */
    public int getScore() {
        return score;
    }

	/**
	 * Sets the score.
	 * 
	 * @param score
	 *            the new score
	 */
    public void setScore(int score) {
        this.score = score;
    }

	/**
	 * Gets the total possible score.
	 * 
	 * @return the total possible score
	 */
    public int getTotalPossibleScore() {
        return totalPossibleScore;
    }

	/**
	 * Sets the total possible score.
	 * 
	 * @param totalPossibleScore
	 *            the new total possible score
	 */
    public void setTotalPossibleScore(int totalPossibleScore) {
        this.totalPossibleScore = totalPossibleScore;
    }
}
