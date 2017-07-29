package com.ebruozaltin.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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


//TODO: Oyun seviyelere bölünsün.

public class MenuScreen extends InputAdapter implements Screen {

    UlduzGame game;
    //ShapeRenderer renderer;
    SpriteBatch batch;
    FitViewport viewport;

    BitmapFont font;
    private final Utils utils;
    TextureRegion background1Region;

    public MenuScreen (UlduzGame game) {
        this.game = game;
        utils = new Utils();
    }

    @Override
    public void show() {
        //renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        viewport = new FitViewport(Constants.MENU_WORLD_SIZE, Constants.MENU_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getData().setScale(Constants.MENU_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Assets.instance.init();
        background1Region = Assets.instance.ulduzAssets.backgorund1;
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        utils.drawTextureRegion(
                batch,
                background1Region,
                0,
                0,
                viewport.getScreenWidth(),
                viewport.getScreenHeight()
        );

        //Hoşgeldin!
        font.draw(
                batch,
                Constants.MENU_LABEL,
                Constants.MENU_WORLD_SIZE/2,
                Constants.MENU_WORLD_SIZE*3/5,
                0,
                Align.center,
                false
        );
        //Play Button
        TextureRegion playRegion = Assets.instance.ulduzAssets.play;
        utils.drawTextureRegion(
                batch,
                playRegion,
                Constants.MENU_WORLD_SIZE/5,
                Constants.MENU_WORLD_SIZE/5,
                Constants.PLAY_BUTTON_SIZE.x,
                Constants.PLAY_BUTTON_SIZE.y
        );


        //Top_score Button
        TextureRegion topSooreRegion = Assets.instance.ulduzAssets.topScore;
        utils.drawTextureRegion(
                batch,
                topSooreRegion,
                Constants.MENU_WORLD_SIZE*3/5,
                Constants.MENU_WORLD_SIZE/5,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
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
        //renderer.dispose();
        Assets.instance.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX,screenY));

        //Clicking Play Button
        if(worldTouch.x > Constants.MENU_WORLD_SIZE/5 && worldTouch.x < Constants.MENU_WORLD_SIZE/5 + Constants.PLAY_BUTTON_SIZE.x){
            if(worldTouch.y > Constants.MENU_WORLD_SIZE/5 && worldTouch.y < Constants.MENU_WORLD_SIZE/5 + Constants.PLAY_BUTTON_SIZE.y){
                game.showFruitsScreen();
            }
        }

        //Clicking TopScore Button
        //TODO: Write this if clause, first complete topScoreScreen
        //Öncesinde veritabanı bağlantısı yapılmalı, ardından verileri çekebiliriz.


        return true;
    }

}
