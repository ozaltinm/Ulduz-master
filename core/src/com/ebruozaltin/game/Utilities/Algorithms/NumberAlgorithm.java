package com.ebruozaltin.game.Utilities.Algorithms;

import com.badlogic.gdx.math.MathUtils;
import com.ebruozaltin.game.Utilities.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by burak on 7/22/2017.
 */

public class NumberAlgorithm implements QuestionAlgorithm {
    @Override
    public Question generateNewQuestion() {

        ArrayList<String> currentList=new ArrayList<String>();
        int resultNumber = MathUtils.random(9);
        String resultString="";
        resultString = getStringValueOfNumber(resultString, resultNumber);
        currentList.add(String.valueOf(resultNumber));

        String falseOption1 = generateValue(currentList);
        currentList.add(falseOption1);

        String falseOption2 = generateValue(currentList);
        currentList.add(falseOption2);

        String falseOption3 = generateValue(currentList);
        currentList.add(falseOption3);

        Collections.shuffle(currentList,new Random(System.nanoTime()));
        int trueAnswerIndex=currentList.indexOf(String.valueOf(resultNumber));
        String questionText = String.valueOf(resultString);
        Question question=new Question(questionText,trueAnswerIndex,currentList);
        return question;
    }

    private String generateValue(ArrayList<String> currentList) {
        int value;
        do{
            value= MathUtils.random(9);
        }while(currentList.contains(String.valueOf(value)));
        return String.valueOf(value);
    }

    private String getStringValueOfNumber(String number, int a) {
        switch (a){
            case 0:
                number = "sıfır";
                break;
            case 1:
                number = "bir";
                break;
            case 2:
                number = "iki";
                break;
            case 3:
                number = "üç";
                break;
            case 4:
                number = "dört";
                break;
            case 5:
                number = "beş";
                break;
            case 6:
                number = "altı";
                break;
            case 7:
                number = "yedi";
                break;
            case 8:
                number = "sekiz";
                break;
            case 9:
                number = "dokuz";
                break;
        }
        return number;
    }
}
