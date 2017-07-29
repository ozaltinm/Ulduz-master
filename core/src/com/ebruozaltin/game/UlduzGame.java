package com.ebruozaltin.game;


import com.badlogic.gdx.Game;
import com.ebruozaltin.game.Screens.CharactersScreen;
import com.ebruozaltin.game.Screens.FruitsScreen;
import com.ebruozaltin.game.Screens.HowToPlayScreen;
import com.ebruozaltin.game.Screens.MenuScreen;
import com.ebruozaltin.game.Screens.PauseScreen;
import com.ebruozaltin.game.Screens.ResultsScreen;
import com.ebruozaltin.game.Screens.TopScoreScreen;
import com.ebruozaltin.game.Utilities.LevelManager;


public class UlduzGame extends Game{

	private FruitsScreen fruitScreen;

	@Override
	public void create() {
		showMenuScreen();
	}

	public void showFruitsScreen() {
		if(fruitScreen==null){
			fruitScreen=new FruitsScreen(this);
		}
		fruitScreen.runGame();
		setScreen(fruitScreen);
	}

	public void showMenuScreen(){
		LevelManager.initializeGame();
		setScreen(new MenuScreen(this));
	}

	public void showResultsScreen(int topScore){
		setScreen(new ResultsScreen(this, topScore));
	}

	public void showTopScoreScreen(){
		setScreen(new TopScoreScreen(this));
	}

	public void showPauseScreen(){setScreen(new PauseScreen(this));}

	public void showHowToPlayScreen(){setScreen(new HowToPlayScreen(this));}

	public void showCharactersScreen(){setScreen(new CharactersScreen(this));}

}
