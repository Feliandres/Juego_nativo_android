package com.jeant.game;


import com.badlogic.gdx.Game;
import com.jeant.game.game.GameScreen;
import com.jeant.game.FireBaseInterface;
import com.jeant.game.objects.Bird;

import java.util.HashMap;

public class FlappyBird extends Game {

	FireBaseInterface FIRE;
	public FlappyBird(){

	}
	public FlappyBird(FireBaseInterface FIRE){
		this.FIRE = FIRE;
	}
	@Override
	public void create() {
		Assets.load();
		setScreen(new GameScreen(this));
	}

	public FireBaseInterface getFIRE() {
		return FIRE;
	}

	public void setFIRE(FireBaseInterface FIRE) {
		this.FIRE = FIRE;
	}
}
