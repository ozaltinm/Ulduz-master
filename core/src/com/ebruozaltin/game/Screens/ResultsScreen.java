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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
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
    ExtendViewport viewport;
    SpriteBatch batch;
    BitmapFont font;
    int topScore;
    private final Utils utils;
    TextureRegion backgroundRegion;
    TextureRegion homeRegion;
    TextureRegion closeRegion;
    TextureRegion boyHoldingRegion;
    TextureRegion girlHoldingRegion;
    Vector2 characterSize;
    Vector2 backgroundSize;

    //constructor
    public ResultsScreen(UlduzGame game, int topScore){
        this.game = game;
        this.topScore = topScore;
        utils = new Utils();
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(Constants.RESULTS_WORLD_SIZE,Constants.RESULTS_WORLD_SIZE);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(Constants.RESULTS_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Gdx.input.setInputProcessor(this);

        Assets.instance.init();
        backgroundRegion = Assets.instance.ulduzAssets.riverModel;
        homeRegion = Assets.instance.ulduzAssets.home;
        closeRegion = Assets.instance.ulduzAssets.close;
        backgroundSize = new Vector2(utils.setScale(backgroundRegion, 1.0f, Constants.MENU_WORLD_SIZE));
        boyHoldingRegion = Assets.instance.ulduzAssets.boyHoldingCup;
        girlHoldingRegion = Assets.instance.ulduzAssets.girlHoldingCup;
        characterSize = new Vector2(utils.setScale(boyHoldingRegion, 2.0f, Constants.RESULTS_WORLD_SIZE));
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.setColor(1,1,1,0.2f);
        utils.drawTextureRegion(
                batch,
                backgroundRegion,
                0,
                0,
                backgroundSize.x,
                backgroundSize.y
        );
        batch.setColor(1,1,1,1);

        //karakter
        //TODO:oynanan karaktere göre sonuç karakteri
        utils.drawTextureRegion(
                batch,
                girlHoldingRegion,
                Constants.FORWARD_MARGIN,
                Constants.RESULTS_WORLD_SIZE*3/10,
                characterSize.x,
                characterSize.y
        );

        //İyi İş Çıkardın!
        font.draw(
                batch,
                Constants.RESULT_LABEL,
                Constants.RESULTS_WORLD_SIZE*3/4,
                Constants.RESULTS_WORLD_SIZE*8/10,
                0,
                Align.center,
                false
        );

        //Toplam Puan
        final String topScoreLabel = "Toplam Puan : " + topScore;
        font.draw(
                batch,
                topScoreLabel,
                Constants.RESULTS_WORLD_SIZE*3/4,
                Constants.RESULTS_WORLD_SIZE*6/10,
                0,
                Align.center,
                false
        );

        //Buttonların konumunu düzenle
        //Home Button

        //Home Button
        utils.drawTextureRegion(
                batch,
                homeRegion,
                viewport.getWorldWidth() - 2*Constants.FORWARD_MARGIN,
                viewport.getWorldHeight() - Constants.FORWARD_MARGIN,
                Constants.PLAY_BUTTON_SIZE.x,
                Constants.PLAY_BUTTON_SIZE.y
        );

        //Close button
        utils.drawTextureRegion(
                batch,
                closeRegion,
                viewport.getWorldWidth() - Constants.FORWARD_MARGIN,
                viewport.getWorldHeight() - Constants.FORWARD_MARGIN,
                Constants.PLAY_BUTTON_SIZE.x,
                Constants.PLAY_BUTTON_SIZE.y
        );
        //Play Button
        //add text "tekrar oyna"
        TextureRegion playRegion = Assets.instance.ulduzAssets.play;
        utils.drawTextureRegion(
                batch,
                playRegion,
                Constants.RESULTS_WORLD_SIZE*5/8,
                Constants.RESULTS_WORLD_SIZE*4/10,
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

        //Home Button
        if(worldTouch.x > viewport.getWorldWidth() - 2*Constants.FORWARD_MARGIN
                && worldTouch.x < viewport.getWorldWidth() - 2*Constants.FORWARD_MARGIN + Constants.PLAY_BUTTON_SIZE.x){
            if(worldTouch.y > viewport.getWorldHeight() - Constants.FORWARD_MARGIN
                    && worldTouch.y < viewport.getWorldHeight() - Constants.FORWARD_MARGIN + Constants.PLAY_BUTTON_SIZE.y){
                game.showMenuScreen();
            }
        }

        //close button
        if(worldTouch.x > viewport.getWorldWidth() - Constants.FORWARD_MARGIN
                && worldTouch.x < viewport.getWorldWidth()){
            if(worldTouch.y > viewport.getWorldHeight() - Constants.FORWARD_MARGIN
                    && worldTouch.y < viewport.getWorldHeight()){
                //TODO: Oyundan çık
                game.showMenuScreen();
            }
        }
        //Clicking Play Button
        //TODO: doğru konumu ver
        else if(worldTouch.x > Constants.RESULTS_WORLD_SIZE/2 && worldTouch.x < Constants.RESULTS_WORLD_SIZE/2 + Constants.PLAY_BUTTON_SIZE.x){
            if(worldTouch.y > Constants.RESULTS_WORLD_SIZE/5 && worldTouch.y < Constants.RESULTS_WORLD_SIZE/5 + Constants.PLAY_BUTTON_SIZE.y){
                game.showFruitsScreen();
            }
        }

        return true;
    }
}
