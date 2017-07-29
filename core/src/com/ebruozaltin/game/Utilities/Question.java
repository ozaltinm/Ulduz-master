package com.ebruozaltin.game.Utilities;

import java.util.ArrayList;

/**
 * Created by burak on 7/18/2017.
 */

public class Question {
    private  String questionText;

    private int resultIndex;

    private ArrayList<String> optionsList;

    public Question(String questionText, int resultIndex, ArrayList<String> optionsList) {
        this.questionText = questionText;
        this.resultIndex = resultIndex;
        this.optionsList = optionsList;
    }

    public boolean isAnswerTrue(int answer) {
        return answer==resultIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getOptionsList() {
        return optionsList;
    }
}
