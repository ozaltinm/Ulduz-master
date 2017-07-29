package com.ebruozaltin.game.Overlays;


 /* Created by uguresin on 06.06.2017.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ebruozaltin.game.Entities.Fruits;
import com.ebruozaltin.game.Entities.Player;
import com.ebruozaltin.game.Utilities.Constants;
import com.ebruozaltin.game.Utilities.LevelManager;

public class UlduzHUD {

    public static final String TAG = UlduzHUD.class.getName();

    public final Viewport viewport;
    BitmapFont font;

    public UlduzHUD(){
        this.viewport = new ScreenViewport();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void render(SpriteBatch batch, int topScore, Player player, Fruits fruits){
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();


        final String leftHudText = Constants.DEATHS_LABEL + player.deaths;

        final String rightHudText =
                Constants.SCORE_LABEL + LevelManager.getCurrentLevel().getTrueAnswerCount() +
                "\n" + Constants.TOP_SCORE_LABEL + LevelManager.getCurrentLevel().getLevelTopScore();

        font.draw(
                batch,
                leftHudText,
                Constants.HUD_MARGIN,
                viewport.getWorldHeight() - Constants.HUD_MARGIN
        );

        font.draw(
                batch,
                rightHudText,
                viewport.getWorldWidth() - Constants.HUD_MARGIN,
                viewport.getWorldHeight() - Constants.HUD_MARGIN,
                0,
                Align.right,
                false
        );

        batch.end();
    }
}
