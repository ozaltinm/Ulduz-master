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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ebruozaltin.game.UlduzGame;
import com.ebruozaltin.game.Utilities.Assets;
import com.ebruozaltin.game.Utilities.Constants;
import com.ebruozaltin.game.Utilities.Utils;

/**
 * Created by uguresin on 11.07.2017.
 */

public class PauseScreen extends InputAdapter implements Screen {

    UlduzGame game;
    Viewport viewport;
    BitmapFont font;
    SpriteBatch batch;
    Utils utils;
    TextureRegion closeRegion;
    TextureRegion homeRegion;
    TextureRegion background1Region;
    Vector2 backgroundSize;

    public PauseScreen ( UlduzGame game){
        this.game = game;
        utils = new Utils();
    }

    @Override
    public void show(){
        viewport = new ExtendViewport(Constants.PAUSE_WORLD_SIZE, Constants.PAUSE_WORLD_SIZE);
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(Constants.PAUSE_LABEL_SCALE);
        Assets.instance.init();
        closeRegion = Assets.instance.ulduzAssets.close;
        homeRegion = Assets.instance.ulduzAssets.home;
        background1Region = Assets.instance.ulduzAssets.riverModel;
        backgroundSize = new Vector2(utils.setScale(background1Region, 1.0f, Constants.PAUSE_WORLD_SIZE));
    }

    @Override
    public void render(float delta){
        viewport.apply();
        Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.setColor(1,1,1,0.5f);
        //background
        utils.drawTextureRegion(
                batch,
                background1Region,
                0,
                0,
                backgroundSize.x,
                backgroundSize.y
        );
        batch.setColor(1,1,1,1);

        font.draw(
                batch,
                Constants.PAUSE_LABEL,
                viewport.getWorldWidth()/2,
                viewport.getWorldHeight()*2/3,
                0,
                Align.center,
                false
        );

        TextureRegion playRegion = Assets.instance.ulduzAssets.play;
        utils.drawTextureRegion(
                batch,
                playRegion,
                viewport.getWorldWidth()/2,
                viewport.getWorldHeight()/5,
                Constants.PLAY_BUTTON_SIZE.x,
                Constants.PLAY_BUTTON_SIZE.y
        );

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX,screenY));

        //Clicking play button
        if(worldTouch.x > viewport.getWorldWidth()/2 && worldTouch.x < viewport.getWorldWidth()/2 + Constants.PLAY_BUTTON_SIZE.x){
            if(worldTouch.y > viewport.getWorldHeight()/5 && worldTouch.y < viewport.getWorldHeight()/5 + Constants.PLAY_BUTTON_SIZE.y){
               //TODO: FruitScreen'deki oyunun önceki durumunu kaybetmeden devam etsin
                game.showFruitsScreen();
            }
        }
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
        return true;
    }

}

