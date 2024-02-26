package com.jeant.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jeant.game.Assets;
import com.jeant.game.FlappyBird;
import com.jeant.game.objects.Bird;
import com.jeant.game.screens.Screens;

public class GameScreen extends Screens {
    static final int STATE_READY = 0;
    static final int STATE_RUNNING = 1;
    static final int STATE_GAME_OVER = 2;

    int state;


    WorldGame oWorld;
    WorldGameRenderer renderer;
    Image getReady, tap, gameOver;

    public GameScreen(FlappyBird game){
        super(game);


        state = STATE_READY;

        oWorld = new WorldGame();
        renderer = new WorldGameRenderer(spriteBatch,oWorld);

        getReady = new Image(Assets.getReady);
        getReady.setPosition(SCREEN_WIDTH/2-getReady.getWidth()/2,600);

        tap = new Image(Assets.tap);
        tap.setPosition(SCREEN_WIDTH/2-tap.getWidth()/2,310);

        gameOver = new Image(Assets.gameOver);
        gameOver.setPosition(SCREEN_WIDTH/2-getReady.getWidth()/2,350);

        stage.addActor(getReady);
        stage.addActor(tap);

    }

    @Override
    public void draw(float delta) {
        renderer.render(delta);

        oCam.update();
        spriteBatch.setProjectionMatrix(oCam.combined);

        spriteBatch.begin();
        float width = Assets.getTextWidth(oWorld.score + "");
        Assets.font.draw(spriteBatch,oWorld.score+"",SCREEN_WIDTH/2f - width/2,700);

        //Assets.font.draw(spriteBatch,"" + oWorld.arrBodies.size, 10,200);
        spriteBatch.end();
    }

    @Override
    public void update(float delta) {
        switch (state){
            case STATE_READY:
                updateReady(delta);
                break;
            case STATE_RUNNING:
                updateRunning(delta);
                break;
            case STATE_GAME_OVER:
                updateGameOver(delta);
                break;
        }
    }

    private void updateGameOver(float delta) {
        if (Gdx.input.justTouched()){
            gameOver.addAction(Actions.sequence(
                    Actions.fadeOut(.3f),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            gameOver.remove();
                            game.setScreen(new GameScreen(game));
                        }
                    })
            ));
        }

    }

    private void updateRunning(float delta) {

        boolean jump = Gdx.input.justTouched();

        oWorld.update(delta,jump);

        if (oWorld.state == WorldGame.STATE_GAME_OVER){
            state = STATE_GAME_OVER;
            stage.addActor(gameOver);
        }
    }

    private void updateReady(float delta) {
        if (Gdx.input.justTouched()){
            getReady.addAction(Actions.fadeOut(.3f));
            tap.addAction(Actions.sequence(
                    Actions.fadeOut(.3f),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            getReady.remove();
                            tap.remove();
                            state = STATE_RUNNING;
                        }
                    })
            ));
        }
    }


}
