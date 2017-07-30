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
import com.ebruozaltin.game.UlduzGame;
import com.ebruozaltin.game.Utilities.Assets;
import com.ebruozaltin.game.Utilities.Constants;
import com.ebruozaltin.game.Utilities.Utils;

/**
 * Created by uguresin on 22.07.2017.
 */

public class HowToPlayScreen extends InputAdapter implements Screen {

    UlduzGame game;
    SpriteBatch batch;
    ExtendViewport viewport;
    BitmapFont font;
    static final String label_1 = "Ekrani saga sola yatirarak";
    static final String label_12 = " karakteri hareket ettirebilirsin.";
    static final String label_2 = "Ekranin solunda yer alan";
    static final String label_22 = "sorunun dogru cevabini bulman gerekiyor";
    static final String label_3 = "Dogru cevabin yer aldigi meyveyi";
    static final String label_32 = "sepete denk getirmelisin";

    int page_no;
    private final Utils utils;
    TextureRegion backgroundRegion;
    TextureRegion screenshot1;
    TextureRegion screenshot2;
    TextureRegion screenshot3;
    Vector2 screenshot1Size;

    public HowToPlayScreen ( UlduzGame game){
        this.game = game;
        page_no = 1;
        utils = new Utils();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        viewport = new ExtendViewport(Constants.MENU_WORLD_SIZE, Constants.MENU_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getData().setScale(Constants.MENU_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Assets.instance.init();
        backgroundRegion = Assets.instance.ulduzAssets.riverModel;
        screenshot1 = Assets.instance.ulduzAssets.screenshot1;
        screenshot2 = Assets.instance.ulduzAssets.screenshot2;
        screenshot3 = Assets.instance.ulduzAssets.screenshot3;
        screenshot1Size = new Vector2(utils.setScale(screenshot1,1.0f, Constants.MENU_WORLD_SIZE));

        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();
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
                screenshot1Size.x,
                screenshot1Size.y
        );

        batch.setColor(1,1,1,1);
        if(page_no == 1){
            font.draw(
                    batch,
                    label_1,
                    viewport.getWorldWidth()/2,
                    viewport.getWorldHeight()*8/9,
                    0,
                    Align.center,
                    false
            );

            font.draw(
                    batch,
                    label_12,
                    viewport.getWorldWidth()/2,
                    viewport.getWorldHeight()*7/9,
                    0,
                    Align.center,
                    false
            );

            utils.drawTextureRegion(
                    batch,
                    screenshot1,
                    viewport.getWorldWidth()/4,
                    viewport.getWorldHeight()/9,
                    viewport.getWorldWidth()/2,
                    viewport.getWorldHeight()/2
            );

        }

        else if(page_no == 2){
            font.draw(
                    batch,
                    label_2,
                    viewport.getWorldWidth()/2,
                    viewport.getWorldHeight()*8/9,
                    0,
                    Align.center,
                    false
            );

            font.draw(
                    batch,
                    label_22,
                    viewport.getWorldWidth()/2,
                    viewport.getWorldHeight()*7/9,
                    0,
                    Align.center,
                    false
            );

            //ScreenShot2 draw
            utils.drawTextureRegion(
                    batch,
                    screenshot2,
                    viewport.getWorldWidth()/4,
                    viewport.getWorldHeight()/9,
                    viewport.getWorldWidth()/2,
                    viewport.getWorldHeight()/2
            );

        }

        else if(page_no == 3){
            font.draw(
                    batch,
                    label_3,
                    viewport.getWorldWidth()/2,
                    viewport.getWorldHeight()*8/9,
                    0,
                    Align.center,
                    false
            );

            font.draw(
                    batch,
                    label_32,
                    viewport.getWorldWidth()/2,
                    viewport.getWorldHeight()*7/9,
                    0,
                    Align.center,
                    false
            );

            //ScreenShot3 draw
            utils.drawTextureRegion(
                    batch,
                    screenshot3,
                    viewport.getWorldWidth()/4,
                    viewport.getWorldHeight()/9,
                    viewport.getWorldWidth()/2,
                    viewport.getWorldHeight()/2
            );
        }

        if(page_no == 2 || page_no == 3) {
            //Back Button
            TextureRegion backRegion = Assets.instance.ulduzAssets.back;
            utils.drawTextureRegion(
                    batch,
                    backRegion,
                    Constants.BACK_MARGIN,
                    Constants.BACK_MARGIN,
                    Constants.PLAY_BUTTON_SIZE.x,
                    Constants.PLAY_BUTTON_SIZE.y
            );
        }
        if(page_no == 1 || page_no == 2) {
            //Forward Button
            TextureRegion forwardRegion = Assets.instance.ulduzAssets.forward;
            utils.drawTextureRegion(
                    batch,
                    forwardRegion,
                    viewport.getWorldWidth() - Constants.FORWARD_MARGIN,
                    Constants.BACK_MARGIN,
                    Constants.PLAY_BUTTON_SIZE.x,
                    Constants.PLAY_BUTTON_SIZE.y
            );
        }

        //Close Button
        TextureRegion closeRegion = Assets.instance.ulduzAssets.close;
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
        viewport.update(width,height,true);
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
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        //Clicking close button
        if (worldTouch.x > viewport.getWorldWidth() - Constants.FORWARD_MARGIN) {
            if (worldTouch.y > viewport.getWorldHeight() - Constants.FORWARD_MARGIN) {
                game.showMenuScreen();
            }
        }

        if (page_no == 2 || page_no == 3) {
            //Clicking back button
            if (worldTouch.x < Constants.BACK_MARGIN + Constants.PLAY_BUTTON_SIZE.x) {
                if (worldTouch.y < Constants.BACK_MARGIN + Constants.PLAY_BUTTON_SIZE.y) {
                    page_no = page_no - 1;
                    Gdx.graphics.requestRendering();
                }
            }
        }

        if (page_no == 1 || page_no == 2) {
            //Clicking Forward Button
            if (worldTouch.x > viewport.getWorldWidth() - Constants.FORWARD_MARGIN) {
                if (worldTouch.y < Constants.BACK_MARGIN + Constants.PLAY_BUTTON_SIZE.y) {
                    page_no = page_no + 1;
                    Gdx.graphics.requestRendering();
                }
            }
        }
        return true;
    }
}
