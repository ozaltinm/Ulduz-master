package com.ebruozaltin.game.Utilities.Algorithms;

import com.badlogic.gdx.math.MathUtils;
import com.ebruozaltin.game.Utilities.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by burak on 7/22/2017.
 */

public class SequenceAlgorithm implements QuestionAlgorithm {
    @Override
    public Question generateNewQuestion() {
        //the algorithm
       int secenek = MathUtils.random(3);
        int a=0,b=0,c=0,result=0,e=0;
        switch (secenek){
            case 0:
                a = MathUtils.random(95);
                b = a+1;
                c = a+2;
                result = a+3;
                e = a+4;
                break;
            case 1:
                a = MathUtils.random(5);
                a = 10*a;
                b = a+10;
                c = a+20;
                result = a+30;
                e = a+40;
                break;
            case 2:
                a = MathUtils.random(6);
                a = 2*a;
                b = a+2;
                c = a+4;
                result = a+6;
                e = a+8;
                break;
            case 3:
                a = MathUtils.random(4, 20);
                b = a-1;
                c = a-2;
                result = a-3;
                e = a-4;
                break;
            default:
                break;
        }
        int firstFalseAnswer= result - random(1,3);
        int secondFalseAnswer= result + random(1,3);
        int thirdFalseAnswer= result - random(4,5);
        ArrayList<String> optionList=new ArrayList<String>();
        optionList.add(String.valueOf(firstFalseAnswer));
        optionList.add(String.valueOf(secondFalseAnswer));
        optionList.add(String.valueOf(thirdFalseAnswer));
        optionList.add(String.valueOf(result));
        Collections.shuffle(optionList, new Random(System.currentTimeMillis()));
        String questionText = a + ", " + b + ", " + c + ", ? ," + e;

        int resultIndex=optionList.indexOf(String.valueOf(result));

        Question question=new Question(questionText,resultIndex,optionList);
        return question;
    }
}
