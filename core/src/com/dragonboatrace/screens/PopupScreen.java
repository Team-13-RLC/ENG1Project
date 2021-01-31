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
import com.dragonboatrace.tools.Settings;

public class PopupScreen implements Screen {

    private final DragonBoatRace game;
    private final Screen previousScreen;

    private final BitmapFont titleFont;
    private final BitmapFont messageFont;
    private final GlyphLayout titleLayout;
    private final GlyphLayout messageLayout;

    private final String title = "Press Space to continue";
    private final String message;


    PopupScreen(String message, Screen previousScreen, DragonBoatRace game) {
        System.out.println("yes");
        this.message = message;
        this.previousScreen = previousScreen;
        this.game = game;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size *= 7.0 / Settings.SCALAR;
        parameter.color = Color.WHITE;
        titleFont = generator.generateFont(parameter);

        parameter.size *= 0.8 / Settings.SCALAR;
        messageFont = generator.generateFont(parameter);

        titleLayout = new GlyphLayout();
        messageLayout = new GlyphLayout();
        titleLayout.setText(titleFont, title);
        messageLayout.setText(messageFont, message);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();

        titleFont.draw(
                game.getBatch(),
                title,
                (Gdx.graphics.getWidth() - titleLayout.width) / 2,
                Gdx.graphics.getHeight() - 100
        );

        messageFont.draw(
                game.getBatch(),
                message,
                (Gdx.graphics.getWidth() - messageLayout.width) / 2,
                (Gdx.graphics.getHeight() - messageLayout.height) / 2
        );
        game.getBatch().end();
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            game.setScreen(previousScreen);
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
        messageFont.dispose();
        titleFont.dispose();
    }
}
