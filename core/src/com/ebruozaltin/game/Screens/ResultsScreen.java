package com.ebruozaltin.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ebruozaltin.game.UlduzGame;
import com.ebruozaltin.game.Utilities.Assets;
import com.ebruozaltin.game.Utilities.Constants;
import com.ebruozaltin.game.Utilities.Utils;

/**
 * Created by uguresin on 04.07.2017.
 */


public class ResultsScreen extends InputAdapter implements Screen {

    public static final String TAG = ResultsScreen.class.getName();

    UlduzGame game;
    FitViewport viewport;
    SpriteBatch batch;
    BitmapFont font;
    int topScore;
    private final Utils utils;

    //constructor
    public ResultsScreen(UlduzGame game, int topScore){
        this.game = game;
        this.topScore = topScore;
        utils = new Utils();
    }

    @Override
    public void show() {
        viewport = new FitViewport(Constants.RESULTS_WORLD_SIZE,Constants.RESULTS_WORLD_SIZE);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(Constants.RESULTS_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Gdx.input.setInputProcessor(this);

        Assets.instance.init();
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        //İyi İş Çıkardın!
        font.draw(
                batch,
                Constants.RESULT_LABEL,
                Constants.RESULTS_WORLD_SIZE/2,
                Constants.RESULTS_WORLD_SIZE*4/5,
                0,
                Align.center,
                false
        );

        //Toplam Puan
        final String topScoreLabel = "Toplam Puan : " + topScore;
        font.draw(
                batch,
                topScoreLabel,
                Constants.RESULTS_WORLD_SIZE/2,
                Constants.RESULTS_WORLD_SIZE*3/5,
                0,
                Align.center,
                false
        );

        //Buttonların konumunu düzenle
        //Home Button
        TextureRegion menuRegion = Assets.instance.ulduzAssets.home;
        utils.drawTextureRegion(
                batch,
                menuRegion,
                Constants.RESULTS_WORLD_SIZE/5,
                Constants.RESULTS_WORLD_SIZE/5,
                Constants.HOME_BUTTON_SIZE.x,
                Constants.HOME_BUTTON_SIZE.y
                );


        //Play Button
        //add text "tekrar oyna"
        TextureRegion playRegion = Assets.instance.ulduzAssets.play;
        utils.drawTextureRegion(
                batch,
                playRegion,
                Constants.RESULTS_WORLD_SIZE/5,
                Constants.RESULTS_WORLD_SIZE/2,
                Constants.PLAY_BUTTON_SIZE.x,
                Constants.PLAY_BUTTON_SIZE.y
        );

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        Assets.instance.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        //Clicking Home Button
        if(worldTouch.x > Constants.RESULTS_WORLD_SIZE/5 && worldTouch.x < Constants.RESULTS_WORLD_SIZE/5 + Constants.HOME_BUTTON_SIZE.x){
            if(worldTouch.y > Constants.RESULTS_WORLD_SIZE/5 && worldTouch.y < Constants.RESULTS_WORLD_SIZE/5 + Constants.HOME_BUTTON_SIZE.y){
                game.showMenuScreen();
            }
        }
        //Clicking Play Button
        else if(worldTouch.x > Constants.RESULTS_WORLD_SIZE/2 && worldTouch.x < Constants.RESULTS_WORLD_SIZE/2 + Constants.PLAY_BUTTON_SIZE.x){
            if(worldTouch.y > Constants.RESULTS_WORLD_SIZE/5 && worldTouch.y < Constants.RESULTS_WORLD_SIZE/5 + Constants.PLAY_BUTTON_SIZE.y){
                game.showFruitsScreen();
            }
        }

        return true;
    }
}
