package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.ButtonFactory;
import com.dragonboatrace.tools.Prefs;
import com.dragonboatrace.tools.Settings;

/**
 * Represents the Main Menu where the game first starts.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class MainMenuScreen implements Screen {

    /**
     * The instance of the game.
     */
    protected final DragonBoatRace game;
    /**
     * The logo position X offset.
     */
    private final float logoXOffset;
    /**
     * The logo position Y offset.
     */
    private final float logoYOffset;
    /**
     * The button used to exit the game.
     */
    private final Button exitButton;
    /**
     * The button used to start the game.
     */
    private final Button playButton;
    /**
     * The button used to go to the help screen.
     */
    private final Button helpButton;
    /**
     * The texture of the main logo.
     */
    private final Texture logo;

    /**
     * Creates a new window that shows the main menu of the game.
     *
     * @param game The instance of the game.
     */
    public MainMenuScreen(DragonBoatRace game) {
        this.game = game;
        // Note: Order matters. Buttons have to appear in opposite order
        this.exitButton = ButtonFactory.mainMenu("exit_button");
        this.helpButton = ButtonFactory.mainMenu("help_button");
        this.playButton = ButtonFactory.mainMenu("play_button");
        //TODO: add a "load" button
        this.logo = new Texture("dragon.png");
        logoXOffset = 680f / Settings.SCALAR;
        logoYOffset = 600f / Settings.SCALAR;
    }


    @Override
    public void show() {

    }

    /**
     * Renders the main window.
     *
     * @param delta The time passed since the last frame.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.game.getBatch().begin();

        this.game.getBatch().draw(logo, (Gdx.graphics.getWidth() - logoXOffset) / 2.0f, (Gdx.graphics.getHeight() - logoYOffset + playButton.getHitBox().getHeight() + playButton.getHitBox().getY()) / 2.0f, logoXOffset, logoYOffset);

        exitButton.render(this.game.getBatch());
        if (this.exitButton.isHovering() && Gdx.input.isTouched()) {
            Gdx.app.exit();
        }
        playButton.render(this.game.getBatch());
        if (this.playButton.isHovering() && Gdx.input.isTouched()) {
            game.setScreen(new BoatSelectScreen(this.game));
        }
        helpButton.render(this.game.getBatch());
        if (this.helpButton.isHovering() && Gdx.input.isTouched()) {
            game.setScreen(new HelpScreen(this));
        }

        //FIXME: This is temporary and should be replaced by a button
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            restore();

        this.game.getBatch().end();
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

    @Override
    public void dispose() {

    }
    private void restore(){
        try {
            Prefs.Restore.open();
        } catch (Prefs.SaveDoesNotExist saveDoesNotExist) {
            System.out.println("saveDoesNotExist");
            saveDoesNotExist.printStackTrace();
        }
        game.restore();

        // BoatType does not matter as it will be replaced
        MainGameScreen mainGameScreen = new MainGameScreen(game, BoatType.AGILE);
        mainGameScreen.restore();
        Prefs.Restore.close();
        game.setScreen(mainGameScreen);
    }

}
