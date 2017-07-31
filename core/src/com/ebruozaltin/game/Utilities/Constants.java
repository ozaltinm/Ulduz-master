package com.ebruozaltin.game.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by uguresin on 06.06.2017.
 */

public class Constants {
    //main
    public static final float WORLD_SIZE = 10.0f;
    public static final Color BACKGROUND_COLOR = Color.BLUE;

    public static final float FRUSTUM_WIDTH = WORLD_SIZE/4;
    public static final float FRUSTUM_HEIGHT = WORLD_SIZE;

    //Assets
    public static final String TEXTURE_ATLAS = "images/ulduz.pack.atlas";
    public static final String LEMON = "lemon-min";
    public static final String APPLE = "apple-min";
    public static final String ORANGE = "orange-min";
    public static final String PEAR = "pear-min";

    public static final String HOME_ASSET = "home-button";
    public static final String PLAY_ASSET = "play-button";
    public static final String TOP_SCORE_ASSET = "kupa";

    public static final String FORWARD_BUTTON = "right";
    public static final String BACK_BUTTON = "left";
    public static final String CLOSE_BUTTON = "close-button";
    public static final String BACKGROUND_1 = "background1";
    public static final String SCREENSHOT_1 = "screenshot1";
    public static final String SCREENSHOT_2 = "screenshot2";
    public static final String SCREENSHOT_3 = "screenshot3";

    public static final String LOCK_BUTTON = "asma-kilit";
    public static final String RIVER_MODEL = "dereli-model";
    public static final String GAME_BACKGROUND = "game-background";
    public static final String MUTE_BUTTON = "mute";
    public static final String PAUSE_BUTTON = "pause-button";
    public static final String NASIL_OYNANIR = "question";
    public static final String SOUND_OPEN = "sound-open";
    public static final String TIK_BUTTON = "tik-button";

    public static final String GIRL_HOLDING_CUP = "ayse-kupa";
    public static final String BOY_HOLDING_CUP = "ali-kupa";

    public static final String BOY_STANDING_RIGHT = "boy-standing-right";
    public static final String BOY_STANDING_LEFT = "boy-standing-left";
    public static final String BOY_WALK_1_RIGHT = "boy-walk-1-right";
    public static final String BOY_WALK_2_RIGHT = "boy-walk-2-right";
    public static final String BOY_WALK_3_RIGHT = "boy-walk-3-right";
    public static final String BOY_WALK_1_LEFT = "boy-walk-1-left";
    public static final String BOY_WALK_2_LEFT = "boy-walk-2-left";
    public static final String BOY_WALK_3_LEFT = "boy-walk-3-left";

    public static final String GIRL_STANDING_RIGHT = "girl-standing-right";
    public static final String GIRL_STANDING_LEFT = "girl-standing-left";
    public static final String GIRL_WALK_1_RIGHT = "girl-walk-1-right";
    public static final String GIRL_WALK_2_RIGHT = "girl-walk-2-right";
    public static final String GIRL_WALK_3_RIGHT = "girl-walk-3-right";
    public static final String GIRL_WALK_1_LEFT = "girl-walk-1-left";
    public static final String GIRL_WALK_2_LEFT = "girl-walk-2-left";
    public static final String GIRL_WALK_3_LEFT = "girl-walk-3-left";

    public static final float BACK_MARGIN = 20.0f;
    public static final float FORWARD_MARGIN = 50.0f;

    //MENU SCREEN
    public static final float MENU_WORLD_SIZE = 480.0f;
    public static final float MENU_LABEL_SCALE = 2.0f;

    public static final String MENU_LABEL = "Merhaba Dostum!";
    public static final float MENU_LABEL_MARGIN = 130.0f;

    public static final Vector2 TOPSCORE_BUTTON_SIZE = new Vector2(MENU_WORLD_SIZE/10, MENU_WORLD_SIZE/10);
    public static final float TOP_SCORE_MARGIN = 20.0f;
    public static final float LOCK_MARGIN = 370.0f;

    public static final String MENU_LEVEL_1 = "Rakamlar";
    public static final String MENU_LEVEL_2 = "Toplama";
    public static final String MENU_LEVEL_3 = "Cikarma";
    public static final String MENU_LEVEL_4 = "Oruntuler";

    //Spawns
    public static final float MEDIUM_SPAWNS_PER_SECOND = 0.7f;



    //Player
    public static final float PLAYER_HEAD_RADIUS = 0.5f;
    public static final float PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEAD_RADIUS;
    public static final float PLAYER_LIMB_WIDTH = 0.1f;
    public static final int PLAYER_HEAD_SEGMENTS = 20;
    public static final Color PLAYER_COLOR = Color.BLACK;
    public static final float PLAYER_MOVEMENT_SPEED = 10.0f;

    public static final float WALK_LOOP_DURATION = 0.25f;


    //Accelerometer
    public static final float ACCELEROMETER_SENSITIVITY = 0.7f;
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;


    //Hud
    public static final float HUD_FONT_REFERENCE_SCREEN_SIZE = 60.0f;
    public static final float HUD_MARGIN = 20.0f;


    public static final String SCORE_LABEL = "Score: ";
    public static final String DEATHS_LABEL = "Deaths: ";
    public static final String TOP_SCORE_LABEL = "Top Score: ";




    //FRUITS

    public static final Vector2 FRUITS_ACCELERATION = new Vector2(0, -0.1f);
    public static final int INITIAL_FRUITS_ARRAY_CAPACITY = 0;
    public static final float FRUITS_HEIGHT = 1.0f;

    //public static final int FRUITS_RANDOM_RANGE = 3;
    public static final float[] FRUIT_COORDINATES = {0.25f, 0.45f, 0.65f, 0.85f};

    public static final float NUMBER_SCALE = 1.5f;
    public static final float FRUIT_SCALE = 13;

    //Victory/Game Over Screens
    //Use the duration
    public static final float OVERLAY_DURATION = 5;
    public static final String VICTORY_MESSAGE = "Aferin!";
    public static final String GAME_OVER_MESSAGE = "Tekrar Dene!";
    public static final String FONT_FILE = "font/header.fnt";


    //QUESTION PART
    public static final float QUESTION_WORLD_SIZE = 480.0f;
    public static final float QUESTION_LABEL_SCALE = 2.0f;


    //RESULTS
    public static final float RESULTS_WORLD_SIZE = 480.0f;
    public static final float RESULTS_LABEL_SCALE = 2.0f;

    public static final String RESULT_LABEL = "iyi is Cikardin!";

    //assets

    public static final Vector2 HOME_BUTTON_SIZE = new Vector2(RESULTS_WORLD_SIZE/12,RESULTS_WORLD_SIZE/12);
    public static final Vector2 PLAY_BUTTON_SIZE = new Vector2(RESULTS_WORLD_SIZE/12,RESULTS_WORLD_SIZE/12);
    public static final Vector2 PAUSE_BUTTON_SIZE = new Vector2(WORLD_SIZE/12,WORLD_SIZE/12);


    //PAUSE
    public static final float PAUSE_WORLD_SIZE = 480.0f;
    public static final String PAUSE_LABEL = "Oyun duraklatildi";
    public static final float PAUSE_LABEL_SCALE = 4.0f;

    //TOP_SCORE
    public static final float TOPSCORE_WORLD_SIZE = 480.0f;
    public static final float TOPSCORE_LABEL_SCALE = 2.0f;
    public static final float TOPSCORE_MARGIN = 360.0f;
    public static final float TOPSCORE_MARGIN_2 = 340.0f;

    //STORE DATA
    public static final Preferences prefs = Gdx.app.getPreferences("My Preferences");
    public static final float QUESTION_SHOWUP_LATENCY = 2;
    public static final String GAME_NAME = "GAME_NAME";
    public static String Game_State_SAve="Game_State_SAve";


    //enums
    public enum Gender{
        BOY,
        GIRL
    }

    public enum Facing {
        LEFT,
        RIGHT
    }

    public enum WalkState {
        STANDING,
        WALKING
    }


    public enum State {
        GAME_RUNNING,
        GAME_PAUSED,
        GAME_RESUME,
        GAME_END
    }

}
