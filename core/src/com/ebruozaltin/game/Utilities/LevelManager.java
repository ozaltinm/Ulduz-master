package com.ebruozaltin.game.Utilities;

import com.ebruozaltin.game.Utilities.Algorithms.AdditionAlgorithm;
import com.ebruozaltin.game.Utilities.Algorithms.NumberAlgorithm;
import com.ebruozaltin.game.Utilities.Algorithms.SequenceAlgorithm;
import com.ebruozaltin.game.Utilities.Algorithms.SubstractionAlgorithm;

import java.util.ArrayList;

/**
 * Created by burak on 7/18/2017.
 */

public class LevelManager {

    public enum GAME_STATUS {
        RUNNING,
        COMPLETED,
        FAILED,
    }
    public static ArrayList<LevelData> levels=new ArrayList<LevelData>();
    public static int currentLevelIndex=0;
    public static GAME_STATUS gameStatus;
    private static int pointsCollectedInLevel=0;

    public static void initializeGame() {

        LevelData firstLevel=new LevelData(new NumberAlgorithm(),1,1,2);
        LevelData secLevel=new LevelData(new AdditionAlgorithm(),1,1,2);
        LevelData thrdLevel=new LevelData(new SubstractionAlgorithm(),1,1,2);
        LevelData fourthLevel=new LevelData(new SequenceAlgorithm(),1,1,2);
        firstLevel.initialize();
        levels.add(firstLevel);
        levels.add(secLevel);
        levels.add(thrdLevel);
        levels.add(fourthLevel);
    }

    public static void proceedLevel(){
        currentLevelIndex++;
        if(currentLevelIndex >=levels.size()){
            gameStatus=GAME_STATUS.COMPLETED;
            currentLevelIndex =0;
        }else{
            levels.get(currentLevelIndex).initialize();
            Question question=levels.get(currentLevelIndex).getAlgorithm().generateNewQuestion();
            levels.get(currentLevelIndex).setCurrentQuestion(question);
        }

    }
    public static LevelData getCurrentLevel(){
        return levels.get(currentLevelIndex);
    }
    public static void proceedNextQuestion(){
        Question question=levels.get(currentLevelIndex).getAlgorithm().generateNewQuestion();
        levels.get(currentLevelIndex).setCurrentQuestion(question);
        levels.get(currentLevelIndex).incrementQuestionNumber();
    }

    public static boolean isGameCompleted(){
        return currentLevelIndex==levels.size();
    }

    public static boolean isThisLastQuestionOfLevel(){
        return levels.get(currentLevelIndex).isOnLastQuestion();
    }

    public static boolean isLevelAchieved(){
        if(levels.get(currentLevelIndex).getTrueAnswerCount() >= levels.get(currentLevelIndex).getLevelMinPassPoint()){
            return true;
        }
        return false;
    }

    public static boolean isTopScorePassed(){
        if(levels.get(currentLevelIndex).getTrueAnswerCount() >= levels.get(currentLevelIndex).getLevelTopScore()){
            return true;
        }
        return false;
    }
}
