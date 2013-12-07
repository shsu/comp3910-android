package ca.bcit.turnip.domain;

/**
 * The Class QuizQuestion.
 */
public class QuizQuestion {

	/** The id. */
    private int id;

	/** The week. */
    private int week;

	/** The question number. */
    private int questionNumber;

	/** The question. */
    private String question;

	/** The choice a. */
    private String choiceA;

	/** The choice b. */
    private String choiceB;

	/** The choice c. */
    private String choiceC;

	/** The choice d. */
    private String choiceD;

	/** The answer. */
    private Character answer;// enum?

	/** The selected answer. */
	private Character selectedAnswer;

	/**
	 * Instantiates a new quiz question.
	 */
    public QuizQuestion() {
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
	 * Gets the question number.
	 * 
	 * @return the question number
	 */
    public int getQuestionNumber() {
        return questionNumber;
    }

	/**
	 * Sets the question number.
	 * 
	 * @param questionNumber
	 *            the new question number
	 */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

	/**
	 * Gets the question.
	 * 
	 * @return the question
	 */
    public String getQuestion() {
        return question;
    }

	/**
	 * Sets the question.
	 * 
	 * @param question
	 *            the new question
	 */
    public void setQuestion(String question) {
        this.question = question;
    }

	/**
	 * Gets the choice a.
	 * 
	 * @return the choice a
	 */
    public String getChoiceA() {
        return choiceA;
    }

	/**
	 * Sets the choice a.
	 * 
	 * @param choiceA
	 *            the new choice a
	 */
    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

	/**
	 * Gets the choice b.
	 * 
	 * @return the choice b
	 */
    public String getChoiceB() {
        return choiceB;
    }

	/**
	 * Sets the choice b.
	 * 
	 * @param choiceB
	 *            the new choice b
	 */
    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

	/**
	 * Gets the choice c.
	 * 
	 * @return the choice c
	 */
    public String getChoiceC() {
        return choiceC;
    }

	/**
	 * Sets the choice c.
	 * 
	 * @param choiceC
	 *            the new choice c
	 */
    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

	/**
	 * Gets the choice d.
	 * 
	 * @return the choice d
	 */
    public String getChoiceD() {
        return choiceD;
    }

	/**
	 * Sets the choice d.
	 * 
	 * @param choiceD
	 *            the new choice d
	 */
    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

	/**
	 * Gets the answer.
	 * 
	 * @return the answer
	 */
    public Character getAnswer() {
        return answer;
    }

	/**
	 * Sets the answer.
	 * 
	 * @param answer
	 *            the new answer
	 */
    public void setAnswer(Character answer) {
        this.answer = answer;
    }

	/**
	 * Gets the selected answer.
	 * 
	 * @return the selected answer
	 */
	public Character getSelectedAnswer() {
		return selectedAnswer;
	}

	/**
	 * Sets the selected answer.
	 * 
	 * @param selectedAnswer
	 *            the new selected answer
	 */
	public void setSelectedAnswer(Character selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
}
