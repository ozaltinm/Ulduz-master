package com.ebruozaltin.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.ebruozaltin.game.Utilities.Constants;
import com.ebruozaltin.game.Utilities.LevelManager;
import com.ebruozaltin.game.Utilities.Utils;

/**
 * Created by uguresin on 06.06.2017.
 */

public class Fruit {

    public static final String TAG = Fruit.class.getName();

    public Vector2 position;
    public Vector2 velocity;
    float height, width;
    private final Utils utils;
    BitmapFont font;
    String number = "3";
    GlyphLayout numberLayout;
    int fruitIndex;
    float startLatency=0f;
    private boolean shouldStartMoving=false;


    public Fruit(Vector2 position, TextureRegion region, int value, float random){
        this.position = position;
        this.velocity = new Vector2();
        utils = new Utils();
        this.fruitIndex=value;
        startLatency=random;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                shouldStartMoving=true;
            }
        },random);
        width = utils.setScale(region,Constants.FRUIT_SCALE).x;
        height = utils.setScale(region,Constants.FRUIT_SCALE).y;
        font = new BitmapFont();
        font.getData().setScale(width/15,height/15);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setUseIntegerPositions(false);
        numberLayout = new GlyphLayout(font, number);
    }


    public void update( float delta){
        if(shouldStartMoving) {
            velocity.mulAdd(Constants.FRUITS_ACCELERATION, delta);
            position.mulAdd(velocity, delta);
        }
    }

    public Integer getNumber() {
        return Integer.valueOf(number);
    }

    public void render(SpriteBatch batch, TextureRegion region) {

        utils.drawTextureRegion(
                batch,
                region,
                position.x,
                position.y,
                width,
                height
        );

        numberLayout=font.draw(
                batch,
                LevelManager.getCurrentLevel().getCurrentQuestion().getOptionsList().get(fruitIndex),
                position.x + numberLayout.width/2,
                position.y + numberLayout.height
        );


    }

    public void setNumber(String number) {
        this.number = number;
    }
}