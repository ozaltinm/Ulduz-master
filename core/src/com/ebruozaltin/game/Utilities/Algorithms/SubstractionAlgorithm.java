package com.ebruozaltin.game.Utilities.Algorithms;

import com.ebruozaltin.game.Utilities.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by burak on 7/22/2017.
 */

public class SubstractionAlgorithm implements QuestionAlgorithm {
    @Override
    public Question generateNewQuestion() {
        //the algorithm
        int a = random(20);
        int b = random(a);

        int result = a - b;
        int firstFalseAnswer= result - random(1,3);
        int secondFalseAnswer= result + random(1,3);
        int thirdFalseAnswer= result - random(4,5);
        ArrayList<String> optionList=new ArrayList<String>();
        optionList.add(String.valueOf(firstFalseAnswer));
        optionList.add(String.valueOf(secondFalseAnswer));
        optionList.add(String.valueOf(thirdFalseAnswer));
        optionList.add(String.valueOf(result));
        Collections.shuffle(optionList, new Random(System.currentTimeMillis()));
        String questionText = a + " - " + b + " = ?";

        int resultIndex=optionList.indexOf(String.valueOf(result));

        Question question=new Question(questionText,resultIndex,optionList);
        return question;
    }
}
