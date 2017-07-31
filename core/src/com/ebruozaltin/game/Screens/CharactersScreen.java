package com.ebruozaltin.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.ebruozaltin.game.UlduzGame;
import com.ebruozaltin.game.Utilities.Assets;
import com.ebruozaltin.game.Utilities.Constants;
import com.ebruozaltin.game.Utilities.Utils;

/**
 * Created by uguresin on 24.07.2017.
 */

public class CharactersScreen extends InputAdapter implements Screen {

    UlduzGame game;
    ExtendViewport viewport;
    SpriteBatch batch;
    private final Utils utils;
    TextureRegion backgroundRegion;
    TextureRegion boyRegion;
    TextureRegion girlRegion;
    TextureRegion closeRegion;
    TextureRegion homeRegion;
    float scale = 2.0f;
    Vector2 characterSize;
    Vector2 backgroundSize;

    public CharactersScreen(UlduzGame game){
        this.game = game;
        utils = new Utils();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        viewport = new ExtendViewport(Constants.MENU_WORLD_SIZE, Constants.MENU_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        Assets.instance.init();
        backgroundRegion = Assets.instance.ulduzAssets.riverModel;
        boyRegion = Assets.instance.ulduzAssets.boyStandingLeft;
        girlRegion = Assets.instance.ulduzAssets.girlStandingLeft;
        closeRegion = Assets.instance.ulduzAssets.close;
        homeRegion = Assets.instance.ulduzAssets.home;
        backgroundSize = new Vector2(utils.setScale(backgroundRegion, 1.0f, Constants.MENU_WORLD_SIZE));
        characterSize = new Vector2(utils.setScale(boyRegion, 2.0f, Constants.MENU_WORLD_SIZE));
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
        //Cin Ali draw
        utils.drawTextureRegion(
                batch,
                boyRegion,
                viewport.getWorldWidth()/4 - characterSize.x/2,
                viewport.getWorldHeight()/2 - characterSize.y/2,
                characterSize.x,
                characterSize.y
        );

        //Cin Ayşe draw
        utils.drawTextureRegion(
                batch,
                girlRegion,
                viewport.getWorldWidth()*3/4 - characterSize.x/2,
                viewport.getWorldHeight()/2 - characterSize.y/2,
                characterSize.x,
                characterSize.y
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
        Assets.instance.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        //if statement for Cin Ali
        if(worldTouch.x > viewport.getWorldWidth()/4-characterSize.x/2 && worldTouch.x < viewport.getWorldWidth()/4+characterSize.x/2){
            if(worldTouch.y > viewport.getWorldHeight()/2 - characterSize.y/2 && worldTouch.y < viewport.getWorldHeight()/2 + characterSize.y/2){
                //TODO: cin aliyi ata
                game.showFruitsScreen();
            }
        }

        //if statement for cin Ayşe
        if(worldTouch.x > viewport.getWorldWidth()*3/4 - characterSize.x/2 && worldTouch.x < viewport.getWorldWidth()*3/4 + characterSize.x/2){
            if(worldTouch.y > viewport.getWorldHeight()/2 - characterSize.y/2 && worldTouch.y < viewport.getWorldHeight() + characterSize.y/2){
                //TODO: cin ayşeyi ata
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
