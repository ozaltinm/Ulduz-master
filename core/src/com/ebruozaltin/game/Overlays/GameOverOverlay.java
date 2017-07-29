package com.ebruozaltin.game.Overlays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ebruozaltin.game.Utilities.Constants;

/**
 * Created by uguresin on 06.06.2017.
 */

public class GameOverOverlay {
    public static final String TAG = GameOverOverlay.class.getName();

    public final Viewport viewport;
    final BitmapFont font;
    private boolean isVisible=false;

    public GameOverOverlay(){
        this.viewport = new ExtendViewport(Constants.QUESTION_WORLD_SIZE, Constants.QUESTION_WORLD_SIZE);
        font = new BitmapFont();
        font.getData().setScale(Constants.QUESTION_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    //public void init(){}

    public void render(SpriteBatch batch){

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        font.draw(batch,
                Constants.GAME_OVER_MESSAGE,
                viewport.getWorldWidth()/2,
                viewport.getWorldHeight()/2,
                0,
                Align.center,
                false
                );

        batch.end();
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
