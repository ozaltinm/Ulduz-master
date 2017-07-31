package com.ebruozaltin.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ebruozaltin.game.UlduzGame;
import com.ebruozaltin.game.Utilities.Assets;
import com.ebruozaltin.game.Utilities.Constants;
import com.ebruozaltin.game.Utilities.Utils;

/**
 * Created by uguresin on 05.07.2017.
 */

public class TopScoreScreen extends InputAdapter implements Screen {

    UlduzGame game;
    ShapeRenderer renderer;
    SpriteBatch batch;
    ExtendViewport viewport;
    BitmapFont font;
    private final Utils utils;
    TextureRegion backgroundRegion;
    TextureRegion closeRegion;
    TextureRegion homeRegion;
    TextureRegion boyRegion;
    TextureRegion girlRegion;
    TextureRegion lemonRegion;
    TextureRegion pearRegion;
    TextureRegion orangeRegion;
    TextureRegion appleRegion;
    Vector2 backgroundSize;
    Vector2 characterSize;

    GlyphLayout level1Layout;
    GlyphLayout level2Layout;
    GlyphLayout level3Layout;
    GlyphLayout level4Layout;
    GlyphLayout topScoreLayout;

    public TopScoreScreen(UlduzGame game) {
        this.game = game;
        utils = new Utils();
    }


    @Override
    public void show() {
        viewport = new ExtendViewport(Constants.TOPSCORE_WORLD_SIZE, Constants.TOPSCORE_WORLD_SIZE);

        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getData().setScale(Constants.TOPSCORE_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Assets.instance.init();
        backgroundRegion = Assets.instance.ulduzAssets.riverModel;
        closeRegion = Assets.instance.ulduzAssets.close;
        homeRegion = Assets.instance.ulduzAssets.home;
        lemonRegion = Assets.instance.ulduzAssets.lemon;
        pearRegion = Assets.instance.ulduzAssets.pear;
        orangeRegion = Assets.instance.ulduzAssets.orange;
        appleRegion = Assets.instance.ulduzAssets.apple;
        boyRegion = Assets.instance.ulduzAssets.boyWalk3Right;
        girlRegion = Assets.instance.ulduzAssets.girlWalk3Right;
        characterSize = new Vector2(utils.setScale(boyRegion, 3.0f, Constants.TOPSCORE_WORLD_SIZE));
        backgroundSize = new Vector2(utils.setScale(backgroundRegion, 1.0f, Constants.TOPSCORE_WORLD_SIZE));
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

        //ali
        utils.drawTextureRegion(
                batch,
                boyRegion,
                Constants.FORWARD_MARGIN,
                Constants.MENU_WORLD_SIZE*3/10,
                characterSize.x,
                characterSize.y
        );

        //ayşe
        utils.drawTextureRegion(
                batch,
                girlRegion,
                Constants.FORWARD_MARGIN + characterSize.x,
                Constants.MENU_WORLD_SIZE*3/10,
                characterSize.x,
                characterSize.y
        );

        //limon
        utils.drawTextureRegion(
                batch,
                lemonRegion,
                Constants.TOPSCORE_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2,
                Constants.MENU_WORLD_SIZE*8/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        String score = "80";
        topScoreLayout = new GlyphLayout(font, score);

        //TODO: puan verileri çekilecek
        //1. seviye puan
        font.draw(
                batch,
                score,
                Constants.TOPSCORE_MARGIN_2 + topScoreLayout.width/2,
                Constants.MENU_WORLD_SIZE*8/10 + topScoreLayout.height*3/2,
                0,
                Align.left,
                false
        );
        //2. seviye puan
        font.draw(
                batch,
                "60",
                Constants.TOPSCORE_MARGIN_2 + topScoreLayout.width/2,
                Constants.MENU_WORLD_SIZE*6/10 + topScoreLayout.height*3/2,
                0,
                Align.left,
                false
        );
        //3. seviye puan
        font.draw(
                batch,
                "0",
                Constants.TOPSCORE_MARGIN_2 + topScoreLayout.width/2,
                Constants.MENU_WORLD_SIZE*4/10 + topScoreLayout.height*3/2,
                0,
                Align.left,
                false
        );
        //4. seviye puan
        font.draw(
                batch,
                "0",
                Constants.TOPSCORE_MARGIN_2 + topScoreLayout.width/2,
                Constants.MENU_WORLD_SIZE*2/10 + topScoreLayout.height*3/2,
                0,
                Align.left,
                false
        );

        //Rakamlar
        level1Layout = new GlyphLayout(font,Constants.MENU_LEVEL_1);
        font.draw(
                batch,
                Constants.MENU_LEVEL_1,
                Constants.TOPSCORE_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level1Layout.width/2,
                Constants.MENU_WORLD_SIZE*8/10 + level1Layout.height*3/2,
                0,
                Align.left,
                false
        );

        //armut
        utils.drawTextureRegion(
                batch,
                pearRegion,
                Constants.TOPSCORE_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2,
                Constants.MENU_WORLD_SIZE*6/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //Toplama
        level2Layout = new GlyphLayout(font,Constants.MENU_LEVEL_2);
        font.draw(
                batch,
                Constants.MENU_LEVEL_2,
                Constants.TOPSCORE_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level2Layout.width/2,
                Constants.MENU_WORLD_SIZE*6/10 + level2Layout.height*3/2,
                0,
                Align.left,
                false
        );

        //elma
        utils.drawTextureRegion(
                batch,
                appleRegion,
                Constants.TOPSCORE_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2,
                Constants.MENU_WORLD_SIZE*4/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //Çıkarma
        level3Layout = new GlyphLayout(font,Constants.MENU_LEVEL_3);
        font.draw(
                batch,
                Constants.MENU_LEVEL_3,
                Constants.TOPSCORE_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level3Layout.width/2,
                Constants.MENU_WORLD_SIZE*4/10 + level3Layout.height*3/2,
                0,
                Align.left,
                false
        );

        //portakal
        utils.drawTextureRegion(
                batch,
                orangeRegion,
                Constants.TOPSCORE_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2,
                Constants.MENU_WORLD_SIZE*2/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //Örüntüler
        level4Layout = new GlyphLayout(font,Constants.MENU_LEVEL_4);
        font.draw(
                batch,
                Constants.MENU_LEVEL_4,
                Constants.TOPSCORE_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level4Layout.width/2,
                Constants.MENU_WORLD_SIZE*2/10 + level4Layout.height*3/2,
                0,
                Align.left,
                false
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
        renderer.dispose();
        batch.dispose();
        font.dispose();
        Assets.instance.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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

        return true;
    }
}