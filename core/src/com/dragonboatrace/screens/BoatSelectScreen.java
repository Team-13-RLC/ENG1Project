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
import com.dragonboatrace.tools.Settings;

/**
 * Displays the screen that allows the player to choose a boat at the beginning of the game.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class BoatSelectScreen implements Screen {
    /**
     * Array storing all texture names (they will later be combined with .png and _button)
     */
    private final String[] textureNames = {
            "fast",
            "agile",
            "strong",
            "endurance"
    };

    /**
     * Array storing all boat textures
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
    private final String title = "Choose your Boat:";

    private final int buttonWidth;

    /**
     * Creates a new screen to display the boat options to the player.
     *
     * @param game The instance of the {@link DragonBoatRace} game.
     */
    public BoatSelectScreen(DragonBoatRace game) {

        this.game = game;

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = ButtonFactory.select(textureNames[i] + "_button");
            iconTextures[i] = new Texture(textureNames[i] + ".png");
        }

        this.buttonWidth = EntityType.BUTTON.getWidth();

        /* Font related items */
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
     * Renders the boat selection screen.
     *
     * @param delta The time passed since the last frame.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();

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
            if (buttons[i].isHovering() && Gdx.input.justTouched()) {
                this.game.setScreen(new DifficultySelectScreen(this.game, BoatType.values()[i]));
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
}
