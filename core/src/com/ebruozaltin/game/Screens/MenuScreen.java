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
 * Created by uguresin on 04.07.2017.
 */


//TODO: Oyun seviyelere bölünsün.

public class MenuScreen extends InputAdapter implements Screen {

    UlduzGame game;
    SpriteBatch batch;
    ExtendViewport viewport;

    BitmapFont font;
    private final Utils utils;
    TextureRegion background1Region;
    TextureRegion boyRegion;
    TextureRegion girlRegion;
    TextureRegion lemonRegion;
    TextureRegion pearRegion;
    TextureRegion orangeRegion;
    TextureRegion appleRegion;
    TextureRegion closeRegion;
    TextureRegion topScoreRegion;
    TextureRegion howToPlayRegion;
    TextureRegion lockRegion;

    Vector2 backgroundSize;
    Vector2 characterSize;
    Vector2 buttonSize;

    GlyphLayout level1Layout;
    GlyphLayout level2Layout;
    GlyphLayout level3Layout;
    GlyphLayout level4Layout;

    public MenuScreen (UlduzGame game) {
        this.game = game;
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
        background1Region = Assets.instance.ulduzAssets.riverModel;
        lemonRegion = Assets.instance.ulduzAssets.lemon;
        pearRegion = Assets.instance.ulduzAssets.pear;
        orangeRegion = Assets.instance.ulduzAssets.orange;
        appleRegion = Assets.instance.ulduzAssets.apple;
        boyRegion = Assets.instance.ulduzAssets.boyWalk3Right;
        girlRegion = Assets.instance.ulduzAssets.girlWalk3Right;
        closeRegion = Assets.instance.ulduzAssets.close;
        topScoreRegion = Assets.instance.ulduzAssets.topScore;
        howToPlayRegion = Assets.instance.ulduzAssets.howToPlay;
        lockRegion = Assets.instance.ulduzAssets.asmaKilit;
        backgroundSize = new Vector2(utils.setScale(background1Region, 1.0f, Constants.MENU_WORLD_SIZE));
        characterSize = new Vector2(utils.setScale(boyRegion, 3.0f, Constants.MENU_WORLD_SIZE));
        buttonSize = new Vector2(utils.setScale(howToPlayRegion, 10.0f, Constants.MENU_WORLD_SIZE));
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.setColor(1,1,1,0.2f);
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

        //Hoşgeldin!
        font.draw(
                batch,
                Constants.MENU_LABEL,
                Constants.MENU_LABEL_MARGIN*3/2,
                Constants.MENU_WORLD_SIZE*8/10,
                0,
                Align.center,
                false
        );

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


        //Top_score Button
        utils.drawTextureRegion(
                batch,
                topScoreRegion,
                Constants.TOP_SCORE_MARGIN,
                Constants.TOP_SCORE_MARGIN,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //Nasıl Oynanır?
        utils.drawTextureRegion(
                batch,
                howToPlayRegion,
                Constants.TOP_SCORE_MARGIN*2 + Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOP_SCORE_MARGIN,
                buttonSize.x,
                buttonSize.y
        );

        //lock
        utils.drawTextureRegion(
                batch,
                lockRegion,
                Constants.LOCK_MARGIN,
                Constants.MENU_WORLD_SIZE*8/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //limon
        utils.drawTextureRegion(
                batch,
                lemonRegion,
                Constants.LOCK_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2,
                Constants.MENU_WORLD_SIZE*8/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );


        //Rakamlar
        level1Layout = new GlyphLayout(font,Constants.MENU_LEVEL_1);
        font.draw(
                batch,
                Constants.MENU_LEVEL_1,
                Constants.LOCK_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level1Layout.width/2,
                Constants.MENU_WORLD_SIZE*8/10 + level1Layout.height*3/2,
                0,
                Align.left,
                false
        );

        //lock
        utils.drawTextureRegion(
                batch,
                lockRegion,
                Constants.LOCK_MARGIN,
                Constants.MENU_WORLD_SIZE*6/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //armut
        utils.drawTextureRegion(
                batch,
                pearRegion,
                Constants.LOCK_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2,
                Constants.MENU_WORLD_SIZE*6/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //Toplama
        level2Layout = new GlyphLayout(font,Constants.MENU_LEVEL_2);
        font.draw(
                batch,
                Constants.MENU_LEVEL_2,
                Constants.LOCK_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level2Layout.width/2,
                Constants.MENU_WORLD_SIZE*6/10 + level2Layout.height*3/2,
                0,
                Align.left,
                false
        );

        //lock
        utils.drawTextureRegion(
                batch,
                lockRegion,
                Constants.LOCK_MARGIN,
                Constants.MENU_WORLD_SIZE*4/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //elma
        utils.drawTextureRegion(
                batch,
                appleRegion,
                Constants.LOCK_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2,
                Constants.MENU_WORLD_SIZE*4/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //Çıkarma
        level3Layout = new GlyphLayout(font,Constants.MENU_LEVEL_3);
        font.draw(
                batch,
                Constants.MENU_LEVEL_3,
                Constants.LOCK_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level3Layout.width/2,
                Constants.MENU_WORLD_SIZE*4/10 + level3Layout.height*3/2,
                0,
                Align.left,
                false
        );

        //lock
        utils.drawTextureRegion(
                batch,
                lockRegion,
                Constants.LOCK_MARGIN,
                Constants.MENU_WORLD_SIZE*2/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //portakal
        utils.drawTextureRegion(
                batch,
                orangeRegion,
                Constants.LOCK_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2,
                Constants.MENU_WORLD_SIZE*2/10,
                Constants.TOPSCORE_BUTTON_SIZE.x,
                Constants.TOPSCORE_BUTTON_SIZE.y
        );

        //Örüntüler
        level4Layout = new GlyphLayout(font,Constants.MENU_LEVEL_4);
        font.draw(
                batch,
                Constants.MENU_LEVEL_4,
                Constants.LOCK_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level4Layout.width/2,
                Constants.MENU_WORLD_SIZE*2/10 + level4Layout.height*3/2,
                0,
                Align.left,
                false
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

        //Clicking TopScore Button
        //TODO: Write this if clause, first complete topScoreScreen
        //Öncesinde veritabanı bağlantısı yapılmalı, ardından verileri çekebiliriz.
        if(worldTouch.x > Constants.TOP_SCORE_MARGIN && worldTouch.x < Constants.TOP_SCORE_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x){
            if(worldTouch.y > Constants.TOP_SCORE_MARGIN && worldTouch.y < Constants.TOP_SCORE_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.y){
                game.showTopScoreScreen();
            }
        }

        //nasıl oynanır?
        if(worldTouch.x > Constants.TOP_SCORE_MARGIN*2 + Constants.TOPSCORE_BUTTON_SIZE.x
                && worldTouch.y < Constants.TOP_SCORE_MARGIN*2 + Constants.TOPSCORE_BUTTON_SIZE.x + buttonSize.x){
            if(worldTouch.y > Constants.TOP_SCORE_MARGIN && worldTouch.y < Constants.TOP_SCORE_MARGIN + buttonSize.y){
                game.showHowToPlayScreen();
            }
        }

        //rakamlar
        if(worldTouch.x > Constants.LOCK_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2
                && worldTouch.x < Constants.LOCK_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level1Layout.width){
            if(worldTouch.y > Constants.MENU_WORLD_SIZE*8/10
                    && worldTouch.y < Constants.MENU_WORLD_SIZE*8/10 + Constants.TOPSCORE_BUTTON_SIZE.y ){
                //TODO: 1. seviyeye git
                game.showCharactersScreen();
            }
        }

        //toplama
        if(worldTouch.x > Constants.LOCK_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2
                && worldTouch.y < Constants.LOCK_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level2Layout.width){
            if(worldTouch.y > Constants.MENU_WORLD_SIZE*6/10
                && worldTouch.y < Constants.MENU_WORLD_SIZE*6/10 + Constants.TOPSCORE_BUTTON_SIZE.y){
                //TODO: 2.seviyeye git
                game.showCharactersScreen();
            }
        }

        //çıkarma
        if(worldTouch.x > Constants.LOCK_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2
                && worldTouch.x < Constants.LOCK_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level2Layout.width){
            if(worldTouch.y > Constants.MENU_WORLD_SIZE*4/10
                    && worldTouch.y < Constants.MENU_WORLD_SIZE*4/10 + Constants.TOPSCORE_BUTTON_SIZE.y){
                game.showCharactersScreen();
            }
        }

        //örüntü
        if(worldTouch.x > Constants.LOCK_MARGIN+Constants.TOPSCORE_BUTTON_SIZE.x*3/2
                && worldTouch.x < Constants.LOCK_MARGIN + Constants.TOPSCORE_BUTTON_SIZE.x*2 + level4Layout.width){
            if( worldTouch.y > Constants.MENU_WORLD_SIZE*2/10
                    && worldTouch.y < Constants.MENU_WORLD_SIZE*2/10 + Constants.TOPSCORE_BUTTON_SIZE.y){
                game.showCharactersScreen();
            }
        }

        //close button
        if(worldTouch.x > viewport.getWorldWidth() - Constants.FORWARD_MARGIN
                && worldTouch.x < viewport.getWorldWidth()){
            if(worldTouch.y > viewport.getWorldHeight() - Constants.FORWARD_MARGIN
                    && worldTouch.y < viewport.getWorldHeight()){
                //TODO: uygulamadan çık
            }
        }

        return true;
    }

}
