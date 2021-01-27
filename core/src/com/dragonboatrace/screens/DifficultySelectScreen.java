package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.ButtonFactory;
import com.dragonboatrace.tools.Difficulty;
import com.dragonboatrace.tools.Settings;

public class DifficultySelectScreen implements Screen {
    private final Texture[] iconTextures = new Texture[4];
    private final String[] iconTexturenames = {
            "easy.png",
            "medium.png",
            "hard.png",
            "very_hard.png"
    };

    private final Button[] buttons = new Button[4];
    private final String[] buttonTexturenames = {
            "easy_button",
            "medium_button",
            "hard_button",
            "very_hard_button"
    };

    private final DragonBoatRace game;


    private final BitmapFont font;
    private final GlyphLayout layout;
    private final String text = "Choose your Difficulty:";

    private final int buttonWidth = EntityType.BUTTON.getWidth();

    private final BoatType boatType;
    public DifficultySelectScreen(DragonBoatRace game, BoatType boatType){
        this.game = game;
        this.boatType = boatType;

        for (int i = 0 ; i < buttons.length; i++) {
            buttons[i] = ButtonFactory.select(buttonTexturenames[i]);
            iconTextures[i] = new Texture(iconTexturenames[i]);
        }

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 10.0 / Settings.SCALAR;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        layout = new GlyphLayout();
        layout.setText(font, text);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();

        font.draw(game.getBatch(), text, (Gdx.graphics.getWidth() - layout.width)/2, Gdx.graphics.getHeight() - 100);
        float scale = ((float)buttonWidth/EntityType.BOAT.getWidth()) / 2.0f;

        for (int i = 0 ; i < buttons.length; i++) {
            game.getBatch().draw(
                    iconTextures[i],
                    buttons[i].getHitBox().getX() + ((buttons[i].getHitBox().getWidth() - buttonWidth / 2f) / 2f),
                    150 + EntityType.BUTTON.getHeight(),
                    buttonWidth / 2f,
                    EntityType.BOAT.getHeight() * scale
            );
            buttons[i].render(game.getBatch());
        }

        for (int i = 0 ; i < buttons.length; i++) {
            if (buttons[i].isHovering() && Gdx.input.isTouched()) {
                Difficulty.values()[i].set();
                this.game.setScreen(new MainGameScreen(this.game, boatType));
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
}
