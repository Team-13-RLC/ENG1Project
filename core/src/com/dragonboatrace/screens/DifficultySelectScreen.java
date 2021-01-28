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
    /**
     * Array storing all texture names (they will later be combined with .png and _button)
     */
    private final String[] textureNames = {
            "easy",
            "easy",
            "easy",
            "easy"
//            "medium",
//            "hard",
//            "very_hard"
    };

    /**
     * Array storing all icon textures
     */
    private final Texture[] iconTextures = new Texture[4];

    /**
     * Array storing all buttons (each button keeps track of its own texture)
     */
    private final Button[] buttons = new Button[4];

    /**
     * Instance of the main game, used to have a collective spritebatch which gives better performance.
     */
    private final DragonBoatRace game;


    private final BitmapFont font;
    private final GlyphLayout layout;
    private final String title = "Choose your Difficulty:";

    private final int buttonWidth = EntityType.BUTTON.getWidth();

    private final BoatType boatType;

    /**
     * Creates a new screen to display the difficulty options to the player.
     *
     * @param game The instance of the {@link DragonBoatRace} game.
     */
    public DifficultySelectScreen(DragonBoatRace game, BoatType boatType) {
        this.game = game;
        this.boatType = boatType;

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = ButtonFactory.select(textureNames[i] + "_button");
            iconTextures[i] = new Texture(textureNames[i] + ".png");
        }

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 10.0 / Settings.SCALAR;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        layout = new GlyphLayout();
        layout.setText(font, title);
    }


    @Override
    public void show() {

    }

    /**
     * Renders the difficulty selection screen.
     *
     * @param delta The time passed since the last frame.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();

        font.draw(game.getBatch(), title, (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100);
        float scale = ((float) buttonWidth / EntityType.BOAT.getWidth()) / 2.0f;

        for (int i = 0; i < buttons.length; i++) {
            game.getBatch().draw(
                    iconTextures[i],
                    buttons[i].getHitBox().getX() + ((buttons[i].getHitBox().getWidth() - buttonWidth / 2f) / 2f),
                    150 + EntityType.BUTTON.getHeight(),
                    buttonWidth / 2f,
                    EntityType.BOAT.getHeight() * scale
            );
            buttons[i].render(game.getBatch());
        }

        for (int i = 0; i < buttons.length; i++) {
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
