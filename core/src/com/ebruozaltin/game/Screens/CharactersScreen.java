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
    float width, height;
    float scale = 2.0f;

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
        width= utils.setScale(boyRegion, scale).x;
        height = utils.setScale(boyRegion, scale).y;
    }

    @Override
    public void render(float delta) {
        viewport.apply();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        utils.drawTextureRegion(
                batch,
                backgroundRegion,
                0,
                0,
                viewport.getScreenWidth(),
                viewport.getScreenHeight()
        );

        //Cin Ali draw
        utils.drawTextureRegion(
                batch,
                boyRegion,
                viewport.getWorldWidth()/4 - width/2,
                viewport.getWorldHeight()/2 - height/2,
                width,
                height
        );

        //Cin Ayşe draw
        utils.drawTextureRegion(
                batch,
                girlRegion,
                viewport.getWorldWidth()*3/4 - width/2,
                viewport.getWorldHeight()/2 - height/2,
                width,
                height
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
        if(worldTouch.x > viewport.getWorldWidth()/4-width/2 && worldTouch.x < viewport.getWorldWidth()/4+width/2){
            if(worldTouch.y > viewport.getWorldHeight()/2 - height/2 && worldTouch.y < viewport.getWorldHeight()/2 + height/2){
                //cin aliyi ata
                //Constants.Gender.BOY;
            }
        }

        //if statement for cin Ayşe
        if(worldTouch.x > viewport.getWorldWidth()*3/4 - width/2 && worldTouch.x < viewport.getWorldWidth()*3/4 + width/2){
            if(worldTouch.y > viewport.getWorldHeight()/2 - height/2 && worldTouch.y < viewport.getWorldHeight() + height/2){
                //cin ayşeyi ata
                //Constants.Gender.GIRL;
            }
        }

        return true;
    }
}
