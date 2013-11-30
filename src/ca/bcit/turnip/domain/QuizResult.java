package ca.bcit.turnip.domain;

public class QuizResult {
    private int id;
    private int userID;
    private int week;
    private int score;
    private int totalPossibleScore;

    public QuizResult() {
    }

    public QuizResult(int score, int totalPossibleScore) {
        this.score = score;
        this.totalPossibleScore = totalPossibleScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalPossibleScore() {
        return totalPossibleScore;
    }

    public void setTotalPossibleScore(int totalPossibleScore) {
        this.totalPossibleScore = totalPossibleScore;
    }
}
