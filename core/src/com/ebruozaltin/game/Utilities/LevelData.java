package com.ebruozaltin.game.Utilities;

import com.ebruozaltin.game.Utilities.Algorithms.QuestionAlgorithm;

/**
 * Created by burak on 7/18/2017.
 */

public class LevelData {

    private com.ebruozaltin.game.Utilities.Algorithms.QuestionAlgorithm algorithm;
    private int levelMinPassPoint;
    private int levelTopScore;
    private Question currentQuestion;
    private int currentQuestionNumber;
    private int questionSize;
    private int trueAnswerCount=0;

    public LevelData(QuestionAlgorithm algorithm, int levelMinPassPoint, int levelTopScore, int questionSize) {
        this.algorithm = algorithm;
        this.levelMinPassPoint = levelMinPassPoint;
        this.levelTopScore = levelTopScore;
        this.questionSize = questionSize;
    }

    public com.ebruozaltin.game.Utilities.Algorithms.QuestionAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(com.ebruozaltin.game.Utilities.Algorithms.QuestionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public int getLevelMinPassPoint() {
        return levelMinPassPoint;
    }

    public void setLevelMinPassPoint(int levelMinPassPoint) {
        this.levelMinPassPoint = levelMinPassPoint;
    }

    public int getLevelTopScore() {
        return levelTopScore;
    }

    public void setLevelTopScore(int levelTopScore) {
        this.levelTopScore = levelTopScore;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public boolean isAnswerTrue(int answer) {
        return currentQuestion.isAnswerTrue(answer);
    }

    public void incrementQuestionNumber() {
        currentQuestionNumber++;
    }

    public boolean isOnLastQuestion() {
        return currentQuestionNumber==questionSize;
    }

    public void initialize() {
        this.currentQuestion=getAlgorithm().generateNewQuestion();
        currentQuestionNumber=1;
    }

    public void incrementTrueAnswerCount(){
        trueAnswerCount++;
    }
    public int getTrueAnswerCount(){
        return trueAnswerCount;
    }
    public void setQuestionSize(int questionSize) {
        this.questionSize = questionSize;
    }
}
