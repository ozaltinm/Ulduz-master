package com.ebruozaltin.game.Utilities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by uguresin on 04.07.2017.
 */

public class Utils {

    public Vector2 setScale(TextureRegion region, float scale)
    {
        Vector2 assetSize;
        float rate = (float)region.getRegionWidth()/(float)region.getRegionHeight();
        float height = (float)Constants.WORLD_SIZE / scale;
        float width = rate * height;
        assetSize = new Vector2(width,height);
        return assetSize;
    }

    public Vector2 setScale(TextureRegion region, float scale, float viewportHeight)
    {
        Vector2 assetSize;
        float rate = (float)region.getRegionWidth()/(float)region.getRegionHeight();
        float height = viewportHeight / scale;
        float width = rate * height;
        assetSize = new Vector2(width,height);
        return assetSize;
    }


    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y, float width, float height) {
        batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                width,
                height,
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false
        );

    }
}
