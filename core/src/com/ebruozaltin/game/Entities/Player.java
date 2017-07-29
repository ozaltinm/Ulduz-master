package com.ebruozaltin.game.Entities;

/**
 * Created by uguresin on 15.06.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ebruozaltin.game.Utilities.Assets;
import com.ebruozaltin.game.Utilities.Constants;
import com.ebruozaltin.game.Utilities.Utils;


public class Player {

    public static final String TAG = Player.class.getName();

    Vector2 position;

    Viewport viewport;

    public int deaths;

    Constants.Facing facing;
    Constants.WalkState walkState;
    Constants.Gender gender;
    float height, width;
    float scale = 2.5f;
    private final Utils utils;
    long walkStartTime;


    public Player(Viewport viewport) {
        this.viewport = viewport;
        deaths = 0;
        init();
        //TODO:gender'ı al
        this.gender = Constants.Gender.GIRL;
        utils = new Utils();
        facing = Constants.Facing.RIGHT;
        walkState = Constants.WalkState.WALKING;
        Assets.instance.init();

        width = utils.setScale(Assets.instance.ulduzAssets.boyStandingRight, scale).x;
        height = utils.setScale(Assets.instance.ulduzAssets.boyStandingRight, scale).y;

    }

    public void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, 0);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            position.x -= delta * Constants.PLAYER_MOVEMENT_SPEED;
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            position.x += delta * Constants.PLAYER_MOVEMENT_SPEED;
        }

        if(Gdx.input.getAccelerometerY()>0){
            //Move right
            moveRight(delta);
        } else if(Gdx.input.getAccelerometerY()<0){
            moveLeft(delta);
        } else{
            walkState = Constants.WalkState.STANDING;
        }

        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY);

        position.x += -delta * accelerometerInput * Constants.PLAYER_MOVEMENT_SPEED;

        ensureInBounds();
    }

    private void moveLeft (float delta){
        if(walkState == Constants.WalkState.STANDING){
            walkStartTime = TimeUtils.nanoTime();
        }
        facing = Constants.Facing.LEFT;
        walkState = Constants.WalkState.WALKING;
    }

    private  void moveRight(float delta){
        if(walkState == Constants.WalkState.STANDING){
            walkStartTime = TimeUtils.nanoTime();
        }
        facing = Constants.Facing.RIGHT;
        walkState = Constants.WalkState.WALKING;
    }

    private void ensureInBounds() {
        if (position.x + width > viewport.getWorldWidth()) {
            position.x = viewport.getWorldWidth() - width;
        }
        else if( position.x < viewport.getWorldWidth()/4){
            position.x = viewport.getWorldWidth()/4;
        }
    }

    public int hitByFruit(Fruits fruits) {
        for (Fruit fruit : fruits.fruitList) {
            Vector2 midPointPosition = new Vector2(fruit.position.x + fruit.width / 2, fruit.position.y + fruit.height / 2);
            if (midPointPosition.dst(position) < Constants.PLAYER_HEAD_RADIUS) {
                return fruit.fruitIndex;
            }
        }

        return -1;
    }

    public void render(SpriteBatch batch) {
        TextureRegion region;
        if (gender == Constants.Gender.BOY) {
            region = Assets.instance.ulduzAssets.boyStandingRight;
        } else{
            region = Assets.instance.ulduzAssets.girlStandingRight;
        }

        if (gender == Constants.Gender.BOY) {
            if (facing == Constants.Facing.RIGHT && walkState == Constants.WalkState.STANDING) {
                region = Assets.instance.ulduzAssets.boyStandingRight;
            } else if (facing == Constants.Facing.LEFT && walkState == Constants.WalkState.STANDING) {
                region = Assets.instance.ulduzAssets.boyStandingLeft;
            } else if (facing == Constants.Facing.LEFT && walkState == Constants.WalkState.WALKING) {
                float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
                region = Assets.instance.ulduzAssets.walkingLeftAnimation.getKeyFrame(walkTimeSeconds);
            } else if (facing == Constants.Facing.RIGHT && walkState == Constants.WalkState.WALKING) {
                float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
                region = Assets.instance.ulduzAssets.walkingRightAnimation.getKeyFrame(walkTimeSeconds);
            }
        }
        if(gender == Constants.Gender.GIRL) {
            if (facing == Constants.Facing.RIGHT && walkState == Constants.WalkState.STANDING) {
                region = Assets.instance.ulduzAssets.girlStandingRight;
            } else if (facing == Constants.Facing.LEFT && walkState == Constants.WalkState.STANDING) {
                region = Assets.instance.ulduzAssets.girlStandingLeft;
            } else if (facing == Constants.Facing.LEFT && walkState == Constants.WalkState.WALKING) {
                float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
                region = Assets.instance.ulduzAssets.walkingLeftAnimationGirl.getKeyFrame(walkTimeSeconds);
            } else if (facing == Constants.Facing.RIGHT && walkState == Constants.WalkState.WALKING) {
                float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
                region = Assets.instance.ulduzAssets.walkingRightAnimationGirl.getKeyFrame(walkTimeSeconds);
            }
        }
        utils.drawTextureRegion(
                batch,
                region,
                position.x,
                position.y,
                width,
                height
        );
    }
}
