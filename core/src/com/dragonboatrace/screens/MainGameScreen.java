package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Timer;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Race;
import com.dragonboatrace.tools.ScrollingBackground;
import com.dragonboatrace.tools.Settings;

/**
 * Represents the Main Game Screen where the game actually happens.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class MainGameScreen implements Screen {

    /**
     * The game instance.
     */
    private final DragonBoatRace game;

    /**
     * Used to make sure the countdown happens at equal intervals.
     */
    private final Timer timer;

    /**
     * The race instance.
     */
    private final Race race;

    /**
     * The background of the window.
     */
    private final ScrollingBackground background;

    /**
     * Use to log the FPS for debugging.
     */
    private final FPSLogger logger;

    /**
     * GlyphLayout used for centering fonts
     */
    private final GlyphLayout layout;

    /**
     * Font used for rendering to screen
     */
    private final BitmapFont font;

    /**
     * What state the game is in, starts in COUNTDOWN.
     * Can also be RUNNING or PAUSED.
     */
    private byte gameState = State.COUNTDOWN;

    /**
     * The time left on the initial countdown.
     */
    private int countDownRemaining = 3;

    /**
     * The String being displayed in the countdown.
     */
    private String countDownString = "";

    /**
     * The screen the user sees when the game is paused
     */
    private final PauseScreen pauseScreen;


    /**
     * Creates a new game screen with a game instance.
     *
     * @param game The game instance.
     * @param boatChosen The {@link BoatType} that the player chose.
     */
    public MainGameScreen(DragonBoatRace game, BoatType boatChosen) {
        this.game = game;
        pauseScreen = new PauseScreen(this, game);


        this.logger = new FPSLogger();

        this.race = new Race(10000, boatChosen, this.game.getRound());
        this.background = new ScrollingBackground();
        this.background.resize(Gdx.graphics.getWidth());

        /* Font related items */
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 10.0 / Settings.SCALAR;
        parameter.color = Color.BLACK;
        this.font = generator.generateFont(parameter);
        this.layout = new GlyphLayout();

        /* Countdown initialisation */
        Timer.Task countDownTask = new Timer.Task() {
            @Override
            public void run() {
                gameState = State.COUNTDOWN;
                if (countDownRemaining == 3) {
                    countDownString = "READY";
                    countDownRemaining--;
                } else if (countDownRemaining == 2) {
                    countDownString = "STEADY";
                    countDownRemaining--;
                } else if (countDownRemaining == 1) {
                    countDownString = "GO";
                    countDownRemaining--;
                } else {
                    countDownString = "";
                    gameState = State.RUNNING;
                    this.cancel();
                }
            }
        };
        timer = new Timer();
        timer.scheduleTask(countDownTask, 0, 1);
        // We don't want the countdown to start before the screen has displayed.
        timer.stop();
    }

    /**
     * Runs when the window first starts. Runs the countdown starter.
     */
    public void show() {
        timer.start();
    }

    /**
     * Render the main game window. Includes rendering the background and the {@link Race}.
     * Will switch to the pause screen if gameState is PAUSED
     * @param deltaTime The time since the last frame.
     */
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        setPausedState();
        this.game.getBatch().begin();
        switch (gameState){
            case State.RUNNING:
                this.logger.log();
                this.background.update(deltaTime * this.race.getPlayer().getVelocity().y);
                this.background.render(game.getBatch());
                this.race.update(deltaTime, this.game);
                this.race.render(game.getBatch());
                break;
            case State.COUNTDOWN:
                this.background.render(game.getBatch());
                this.race.render(game.getBatch());
                displayCountDown();
                break;
            case State.PAUSED:
                game.setScreen(pauseScreen);
                gameState = State.RUNNING;
                break;
        }
        this.game.getBatch().end();
    }

    /**
     * Render the current status of the countdown.
     */
    private void displayCountDown() {
        layout.setText(font, this.countDownString);
        font.draw(game.getBatch(), this.countDownString, (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() / 2.0f);
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    /**
     * Check if game is being paused, if it is, gameState is set to PAUSED
     */
    private void setPausedState(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gameState = State.PAUSED;
        }
    }

    @Override
    public void dispose() {
        this.game.getBatch().dispose();
    }

    public void restore() {
        race.restore();
    }

    public void save() {
        race.save();
    }

    /**
     * Giving names to game states.
     */
    private static class State{
        private static final byte RUNNING = 0;
        private static final byte PAUSED = 1;
        private static final byte COUNTDOWN = 2;
    }

    public Race getRace(){
        return this.race;
    }
}
