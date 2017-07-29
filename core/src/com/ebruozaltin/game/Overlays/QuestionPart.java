package com.ebruozaltin.game.Overlays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ebruozaltin.game.Utilities.Constants;
import com.ebruozaltin.game.Utilities.LevelData;
import com.ebruozaltin.game.Utilities.LevelManager;


/**
 * Created by uguresin on 07.07.2017.
 */

public class QuestionPart {

    public final Viewport viewport;
    BitmapFont font;
    int firstVariable,secondVariable, total;
    private LevelData currentLevel;
    private boolean isViewAble=false;

    public void setViewAble(boolean viewAble) {
        isViewAble = viewAble;
    }

    public QuestionPart(LevelData level){
        this.viewport = new ExtendViewport(Constants.QUESTION_WORLD_SIZE, Constants.QUESTION_WORLD_SIZE);
        font = new BitmapFont();
        font.getData().setScale(Constants.QUESTION_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.currentLevel=level;
        init();
    }

    public void init(){

    }

    public void render(SpriteBatch batch){
        if(isViewAble) {
            viewport.apply();
            batch.setProjectionMatrix(viewport.getCamera().combined);
            batch.begin();

            //writing question to the screen
            final GlyphLayout questionLayout = new GlyphLayout(font, LevelManager.getCurrentLevel().getCurrentQuestion().getQuestionText());
            font.draw(
                    batch,
                    LevelManager.getCurrentLevel().getCurrentQuestion().getQuestionText(),
                    viewport.getWorldWidth() / 16,
                    viewport.getWorldHeight() / 2 + questionLayout.height / 2,
                    0,
                    Align.left,
                    false
            );

            //fruit interconnection


            batch.end();
        }
    }

    public boolean isAnswerTrue(int answer){
        return currentLevel.isAnswerTrue(answer);
    }

}
