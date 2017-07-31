package com.ebruozaltin.game.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by uguresin on 06.06.2017.
 */
/**
 *Check out how the Assets singleton works
 *
 * This utility class holds onto all the assets used in Ulduz. It's a singleton, so the
 * constructor is private, and a single instance is created when the class is loaded. That way all
 * the entities that make up Ulduz can access their sprites in the same place, and no work loading
 * up textures is repeated.
 *
 * Each entity gets its own inner class to hold its assets. Below you'll complete the UlduzAssets
 * class by finding up the standing-right AtlasRegion within the TextureAtlas loaded in init() .
 */

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    public UlduzAssets ulduzAssets;
    AssetManager assetManager;

    //private constructor
    private Assets(){
    }

    public void init(){
        this.assetManager = new AssetManager();
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        ulduzAssets = new UlduzAssets(atlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load assets" + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }


    public class UlduzAssets{
        public final AtlasRegion lemon;
        public final AtlasRegion pear;
        public final AtlasRegion orange;
        public final AtlasRegion apple;
        public final AtlasRegion home;
        public final AtlasRegion play;
        public final AtlasRegion topScore;
        public final AtlasRegion pause;
        public final AtlasRegion forward;
        public final AtlasRegion back;
        public final AtlasRegion close;
        public final AtlasRegion backgorund1;
        public final AtlasRegion screenshot1;
        public final AtlasRegion screenshot2;
        public final AtlasRegion screenshot3;

        public final AtlasRegion boyStandingRight;
        public final AtlasRegion boyStandingLeft;
        public final AtlasRegion boyWalk1Right;
        public final AtlasRegion boyWalk2Right;
        public final AtlasRegion boyWalk3Right;
        public final AtlasRegion boyWalk1Left;
        public final AtlasRegion boyWalk2Left;
        public final AtlasRegion boyWalk3Left;
        public final AtlasRegion girlStandingRight;
        public final AtlasRegion girlStandingLeft;
        public final AtlasRegion girlWalk1Right;
        public final AtlasRegion girlWalk2Right;
        public final AtlasRegion girlWalk3Right;
        public final AtlasRegion girlWalk1Left;
        public final AtlasRegion girlWalk2Left;
        public final AtlasRegion girlWalk3Left;
        public final AtlasRegion girlHoldingCup;
        public final AtlasRegion boyHoldingCup;

        public final AtlasRegion asmaKilit;
        public final AtlasRegion riverModel;
        public final AtlasRegion gameBackground;
        public final AtlasRegion mute;
        public final AtlasRegion howToPlay;
        public final AtlasRegion soundOpen;
        public final AtlasRegion tik;

        public final Animation walkingLeftAnimation;
        public final Animation walkingRightAnimation;

        public final Animation walkingLeftAnimationGirl;
        public final Animation walkingRightAnimationGirl;

        public UlduzAssets(TextureAtlas atlas){

            asmaKilit = atlas.findRegion(Constants.LOCK_BUTTON);
            riverModel = atlas.findRegion(Constants.RIVER_MODEL);
            gameBackground = atlas.findRegion(Constants.GAME_BACKGROUND);
            mute = atlas.findRegion(Constants.MUTE_BUTTON);
            howToPlay = atlas.findRegion(Constants.NASIL_OYNANIR);
            soundOpen = atlas.findRegion(Constants.SOUND_OPEN);
            tik = atlas.findRegion(Constants.TIK_BUTTON);

            lemon = atlas.findRegion(Constants.LEMON);
            pear = atlas.findRegion(Constants.PEAR);
            orange = atlas.findRegion(Constants.ORANGE);
            apple = atlas.findRegion(Constants.APPLE);
            home = atlas.findRegion(Constants.HOME_ASSET);
            play = atlas.findRegion(Constants.PLAY_ASSET);
            topScore = atlas.findRegion(Constants.TOP_SCORE_ASSET);
            pause = atlas.findRegion(Constants.PAUSE_BUTTON);

            forward = atlas.findRegion(Constants.FORWARD_BUTTON);
            back = atlas.findRegion(Constants.BACK_BUTTON);
            close = atlas.findRegion(Constants.CLOSE_BUTTON);
            backgorund1 = atlas.findRegion(Constants.BACKGROUND_1);
            screenshot1 = atlas.findRegion(Constants.SCREENSHOT_1);
            screenshot2 = atlas.findRegion(Constants.SCREENSHOT_2);
            screenshot3 = atlas.findRegion(Constants.SCREENSHOT_3);
            boyStandingRight = atlas.findRegion(Constants.BOY_STANDING_RIGHT);
            boyStandingLeft = atlas.findRegion(Constants.BOY_STANDING_LEFT);
            boyWalk1Right = atlas.findRegion(Constants.BOY_WALK_1_RIGHT);
            boyWalk2Right = atlas.findRegion(Constants.BOY_WALK_2_RIGHT);
            boyWalk3Right = atlas.findRegion(Constants.BOY_WALK_3_RIGHT);
            boyWalk1Left = atlas.findRegion(Constants.BOY_WALK_1_LEFT);
            boyWalk2Left = atlas.findRegion(Constants.BOY_WALK_2_LEFT);
            boyWalk3Left = atlas.findRegion(Constants.BOY_WALK_3_LEFT);

            girlStandingRight = atlas.findRegion(Constants.GIRL_STANDING_RIGHT);
            girlStandingLeft = atlas.findRegion(Constants.GIRL_STANDING_LEFT);
            girlWalk1Right = atlas.findRegion(Constants.GIRL_WALK_1_RIGHT);
            girlWalk2Right = atlas.findRegion(Constants.GIRL_WALK_2_RIGHT);
            girlWalk3Right = atlas.findRegion(Constants.GIRL_WALK_3_RIGHT);
            girlWalk1Left = atlas.findRegion(Constants.GIRL_WALK_1_LEFT);
            girlWalk2Left = atlas.findRegion(Constants.GIRL_WALK_2_LEFT);
            girlWalk3Left = atlas.findRegion(Constants.GIRL_WALK_3_LEFT);

            girlHoldingCup = atlas.findRegion(Constants.GIRL_HOLDING_CUP);
            boyHoldingCup = atlas.findRegion(Constants.BOY_HOLDING_CUP);

            Array<TextureRegion> walkingLeftFrames = new Array<TextureRegion>();
            walkingLeftFrames.add(boyWalk1Left);
            walkingLeftFrames.add(boyWalk2Left);
            walkingLeftFrames.add(boyWalk3Left);
            walkingLeftFrames.add(boyWalk2Left);
            walkingLeftAnimation = new Animation(Constants.WALK_LOOP_DURATION, walkingLeftFrames, Animation.PlayMode.LOOP);

            Array<TextureRegion> walkingRightFrames = new Array<TextureRegion>();
            walkingRightFrames.add(boyWalk1Right);
            walkingRightFrames.add(boyWalk2Right);
            walkingRightFrames.add(boyWalk3Right);
            walkingRightFrames.add(boyWalk2Right);
            walkingRightAnimation = new Animation(Constants.WALK_LOOP_DURATION, walkingRightFrames, Animation.PlayMode.LOOP);

            Array<TextureRegion> walkingLeftFramesGirl = new Array<TextureRegion>();
            walkingLeftFramesGirl.add(girlWalk1Left);
            walkingLeftFramesGirl.add(girlWalk2Left);
            walkingLeftFramesGirl.add(girlWalk3Left);
            walkingLeftFramesGirl.add(girlWalk2Left);
            walkingLeftAnimationGirl = new Animation(Constants.WALK_LOOP_DURATION, walkingLeftFramesGirl, Animation.PlayMode.LOOP);

            Array<TextureRegion> walkingRightFramesGirl = new Array<TextureRegion>();
            walkingRightFramesGirl.add(girlWalk1Right);
            walkingRightFramesGirl.add(girlWalk2Right);
            walkingRightFramesGirl.add(girlWalk3Right);
            walkingRightFramesGirl.add(girlWalk2Right);
            walkingRightAnimationGirl = new Animation(Constants.WALK_LOOP_DURATION, walkingRightFramesGirl, Animation.PlayMode.LOOP);
        }

    }
}
