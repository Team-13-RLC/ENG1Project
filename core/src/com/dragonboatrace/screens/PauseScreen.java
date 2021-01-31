package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.tools.ButtonFactory;
import com.dragonboatrace.tools.Prefs;
import com.dragonboatrace.tools.Settings;

public class PauseScreen implements Screen {
    private final Runnable[] buttonActions;

    private final Button[] buttons = new Button[3];

    private final DragonBoatRace game;
    private final MainGameScreen previousScreen;

    private final BitmapFont font;
    private final GlyphLayout layout;
    private final String title = "The game is paused";

    public PauseScreen(MainGameScreen previousScreen, DragonBoatRace game) {
        final String[] textureNames = {
                "exit",
                "resume",
                "save"
        };
        this.previousScreen = previousScreen;

        buttonActions = new Runnable[]{
                () -> Gdx.app.exit(),
                () -> game.setScreen(previousScreen),
                () -> {
                    System.out.println("Game is saved");
                    save();
                }
        };
        this.game = game;

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = ButtonFactory.mainMenu(textureNames[i] + "_button");
        }

        /* Font related items */
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 10.0 / Settings.SCALAR;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        layout = new GlyphLayout();
        layout.setText(font, title);

    }

    private void save() {
        Prefs.Save.open();
        game.save();
        previousScreen.save();
        Prefs.Save.close();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        setResumeState();
        game.getBatch().begin();

        font.draw(game.getBatch(), title, (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100);
        for (Button button : buttons) {
            button.render(game.getBatch());
        }

        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isHovering() && Gdx.input.isTouched()) {
                buttonActions[i].run();
            }
        }
        game.getBatch().end();
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

    private void setResumeState() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            buttonActions[1].run(); // resume the game;
        }
    }


}
