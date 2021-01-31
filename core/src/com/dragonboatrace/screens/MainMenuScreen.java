package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.ButtonFactory;
import com.dragonboatrace.tools.CollidableStats;
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
     * The texture of the main logo.
     */
    private final Texture logo;

    /**
     * Array of functional interfaces that are invoked when each button is pressed.
     */
    private final Runnable[] buttonActions;

    /**
     * Array of buttons.
     */
    private final Button[] buttons = new Button[4];

    /**
     * Creates a new window that shows the main menu of the game.
     *
     * @param game The instance of the game.
     */
    public MainMenuScreen(DragonBoatRace game) {
        this.game = game;
        final String[] textureNames = {
                "exit",
                "help",
                "resume",
                "play"
        };

        buttonActions = new Runnable[]{
                () -> Gdx.app.exit(),
                () -> game.setScreen(new HelpScreen(this)),
                this::restore,
                () -> game.setScreen(new BoatSelectScreen(game))
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = ButtonFactory.mainMenu(textureNames[i] + "_button");
        }

        this.logo = new Texture("dragon.png");
        logoXOffset = 408f / Settings.SCALAR;
        logoYOffset = 360f / Settings.SCALAR;
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

        this.game.getBatch().draw(
                logo,
                (Gdx.graphics.getWidth() - logoXOffset) / 2.0f,
                (Gdx.graphics.getHeight() - logoYOffset + buttons[buttons.length - 1].getHitBox().getHeight() + buttons[buttons.length -1].getHitBox().getY()) / 2.0f,
                logoXOffset,
                logoYOffset
        );

        for (Button button : buttons) {
            button.render(game.getBatch());
        }

        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isHovering() && Gdx.input.isTouched()) {
                buttonActions[i].run();
            }
        }
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

    /**
     * Restores the game from the last save or notifies teh user if no such save exists.
     * Invoked by a button
     */
    private void restore() {
        try {
            Prefs.Restore.open();
        } catch (Prefs.SaveDoesNotExist saveDoesNotExist) {
            game.setScreen(new PopupScreen("Could not find a saved game", this, game));
            return;
        }
        game.restore();
        Settings.restore();
        CollidableStats.restore();
        // BoatType does not matter as it will be replaced
        MainGameScreen mainGameScreen = new MainGameScreen(game, BoatType.AGILE);
        mainGameScreen.restore();
        Prefs.Restore.close();
        game.setScreen(mainGameScreen);
    }

}
