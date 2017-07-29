package com.ebruozaltin.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ebruozaltin.game.UlduzGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new UlduzGame(), config);
	}

	//telefonu açık tut
	@Override protected void createWakeLock(boolean use) {
		use=true; super.createWakeLock(use);
	}
}
