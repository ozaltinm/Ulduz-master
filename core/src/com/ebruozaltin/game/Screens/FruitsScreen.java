package com.ebruozaltin.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.ebruozaltin.game.Entities.Fruits;
import com.ebruozaltin.game.Entities.Player;
import com.ebruozaltin.game.Overlays.GameOverOverlay;
import com.ebruozaltin.game.Overlays.QuestionPart;
import com.ebruozaltin.game.Overlays.UlduzHUD;
import com.ebruozaltin.game.Overlays.VictoryOverlay;
import com.ebruozaltin.game.UlduzGame;
import com.ebruozaltin.game.Utilities.Assets;
import com.ebruozaltin.game.Utilities.Constants;
import com.ebruozaltin.game.Utilities.LevelManager;
import com.ebruozaltin.game.Utilities.Utils;

import java.util.ArrayList;

import static com.ebruozaltin.game.Utilities.Constants.State.GAME_PAUSED;
import static com.ebruozaltin.game.Utilities.Constants.State.GAME_RESUME;
import static com.ebruozaltin.game.Utilities.Constants.State.GAME_RUNNING;

/**
 * Created by uguresin on 17.06.2017.
 */


public class FruitsScreen extends InputAdapter implements Screen {

    public static final String TAG = FruitsScreen.class.getName();

    UlduzGame game;
    ExtendViewport fruitsViewport;
    SpriteBatch batch;

    Player player;
    Fruits fruits;

    UlduzHUD hud;
    QuestionPart questionPart;

    int topScore;
    Constants.State state;

    TextureRegion lemonRegion = Assets.instance.ulduzAssets.lemon;
    TextureRegion pauseRegion = Assets.instance.ulduzAssets.pause;

    Utils utils;

    VictoryOverlay victoryOverlay;
    GameOverOverlay gameOverOverlay;

    //TODO: müzik

    public FruitsScreen(UlduzGame game){
        this.game = game;
        state = GAME_RUNNING;
        utils = new Utils();
    }

    public void runGame(){
        state = GAME_RUNNING;

    }

    @Override
    public void show() {
        fruitsViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

        batch = new SpriteBatch();

        hud = new UlduzHUD();

                questionPart = new QuestionPart(LevelManager.getCurrentLevel());
                questionPart.viewport.update(fruitsViewport.getScreenWidth(), fruitsViewport.getScreenHeight(), true);

        victoryOverlay=new VictoryOverlay();
        victoryOverlay.viewport.update(fruitsViewport.getScreenWidth(), fruitsViewport.getScreenHeight(), true);

        gameOverOverlay=new GameOverOverlay();
        gameOverOverlay.viewport.update(fruitsViewport.getScreenWidth(), fruitsViewport.getScreenHeight(), true);

        //Soruların ne kadar geç çıkacagını buradakı Constants.QUESTION_SHOWUP_LATENCY değiştirerek yonetebılırsınız.

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                questionPart.setViewAble(true);
            }
        },Constants.QUESTION_SHOWUP_LATENCY);

        player = new Player(fruitsViewport);
        fruits = new Fruits(fruitsViewport, lemonRegion);

        Assets.instance.init();

        Gdx.input.setInputProcessor(this);
        ArrayList<String> optionsList= LevelManager.getCurrentLevel().getCurrentQuestion().getOptionsList();
        for(int i=0;i<optionsList.size();i++){
            fruits.getFruitList().get(i).setNumber(optionsList.get(i));
        }
        topScore = 0;
    }

    private void renderRunning(float delta){
        //TODO: Her yeni soruya geçişte ekranda önce soru görünsün. 2-3 sn kadar sonra meyveler düşmeye başlasın.

        player.update(delta);

        //TODO: Doğru meyveyi yakaladığında belirli bir süre VictoryOverlay görünsün
        //TODO: Yanlış meyveyi yakaladığında belirli bir süre GameOverOverlay görünsün
        //Her iki durumda da oyun devam etsin.
        //Doğru meyve puanı 5, yanlış meyve 0
        //Bir seviyede 10 tane soru sorulsun. Sonucunda ResultsScreen görünsün.
        // 7 soruya doğru cevap verip en az 35 puan toplarsa bir sonraki seviyeye geçmeye
        // hak kazansın. Aksi takdirde bu seviyeyi tekrar etsin. ResultsScreen ekranındaki
        //mesaj da ona göre değişsin.
        int fruitIndex=player.hitByFruit(fruits);
        if(fruitIndex!=-1){
            questionPart.setViewAble(false);
            if(LevelManager.getCurrentLevel().isAnswerTrue(fruitIndex)) {
                victoryOverlay.setVisible(true);

                fruits.init(lemonRegion);
                LevelManager.getCurrentLevel().incrementTrueAnswerCount();
                manageLevelProsedure();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        victoryOverlay.setVisible(false);
                        questionPart.setViewAble(true);
                    }
                }, Constants.QUESTION_SHOWUP_LATENCY);
            }else{
                fruits.init(lemonRegion);
                manageLevelProsedure();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        victoryOverlay.setVisible(false);
                        questionPart.setViewAble(true);
                    }
                }, Constants.QUESTION_SHOWUP_LATENCY);
            }
        }
        if(fruits.getFruitList().size==0){
            LevelManager.proceedNextQuestion();
        }

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //fruits
        fruitsViewport.apply(true);
        batch.begin();
        batch.setProjectionMatrix(fruitsViewport.getCamera().combined);


        //TODO: render'ın içinde her defasında işlem yaptırma (less significant)
        fruits.update(delta, lemonRegion);


        fruits.render(batch, lemonRegion);

        //pause button
        utils.drawTextureRegion(
                batch,
                pauseRegion,
                Constants.WORLD_SIZE/16,
                Constants.WORLD_SIZE/16,
                Constants.PAUSE_BUTTON_SIZE.x,
                Constants.PAUSE_BUTTON_SIZE.y
        );

        player.render(batch);

        batch.end();

        //hud

        topScore = Math.max(topScore, fruits.fruitsDodged);
        hud.render(batch,topScore,player,fruits);

        //Question
        //Eger Sorular gorumeye basladıysa render ediyor olacak.
        if(questionPart!=null) {
            questionPart.render(batch);
        }
        if(victoryOverlay.isVisible()){
            victoryOverlay.render(batch);
        }
        if(gameOverOverlay.isVisible()){
            gameOverOverlay.render(batch);
        }

    }

    private void manageLevelProsedure() {
        if (LevelManager.isThisLastQuestionOfLevel() && LevelManager.isLevelAchieved()) {
            LevelManager.proceedLevel();
            if (LevelManager.gameStatus == LevelManager.GAME_STATUS.COMPLETED) {
                renderCompleted();
            }
        }else if (LevelManager.isThisLastQuestionOfLevel() && !LevelManager.isLevelAchieved()) {
            if (LevelManager.gameStatus == LevelManager.GAME_STATUS.COMPLETED) {
                renderCompleted();
            }
            gameOverOverlay.setVisible(true);
        }else {
            LevelManager.proceedNextQuestion();
           ArrayList<String> optionsList= LevelManager.getCurrentLevel().getCurrentQuestion().getOptionsList();
            for(int i=0;i<optionsList.size();i++){
                fruits.getFruitList().get(i).setNumber(optionsList.get(i));
            }
        }
    }

    private void renderCompleted() {
        game.showResultsScreen(100);
    }

    private void renderPaused(){
        game.showPauseScreen();
    }

    @Override
    public void render(float delta) {

        //TODO: Oyun içerisinde state geçişleri sağlanmalı
        switch (state){
            case GAME_RUNNING:
                renderRunning(delta);
                break;
            case GAME_PAUSED:
                renderPaused();
                break;
            case GAME_RESUME:
                //do stuff
                break;
            case GAME_END:
                //do stuff
                break;
            default:
                break;
        }
    }
    @Override
    public void resize(int width, int height) {
        fruitsViewport.update(width, height, true);
        hud.viewport.update(width, height, true);
        if(questionPart!=null) {
            questionPart.viewport.update(width, height, true);
        }
        if(victoryOverlay!=null) {
            victoryOverlay.viewport.update(width, height, true);
        }
        if(gameOverOverlay!=null) {
            gameOverOverlay.viewport.update(width, height, true);
        }
        player.init();
        fruits.init(lemonRegion);
    }

    @Override
    public void pause() {
        state = GAME_PAUSED;
    }

    @Override
    public void resume() {
        state = GAME_RESUME;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        Assets.instance.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = fruitsViewport.unproject(new Vector2(screenX, screenY));

        //Clicking Pause Button
        if(worldTouch.x > Constants.WORLD_SIZE/16 && worldTouch.x < Constants.WORLD_SIZE/16 + Constants.PAUSE_BUTTON_SIZE.x){
            if(worldTouch.y > Constants.WORLD_SIZE/16 && worldTouch.y < Constants.WORLD_SIZE/16 + Constants.PAUSE_BUTTON_SIZE.y){
                game.showPauseScreen();
                //state = GAME_PAUSED;
            }
        }

        return true;
    }

}