package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;

/**
 * Contains Static methods used to create various buttons.
 * Each method takes just a singe texturePrefix
 * which gets turned into full texture names by appending either '_active' or '_inactive' to the end of it.
 */
public class ButtonFactory {

    /**
     * Static variable used to keep track of how many times the mainMenu method is called
     */
    private static int mainMenuButtonCount = 0;

    /**
     * Creates the buttons on the MainMenuScreen.
     * yOffset is how far off the bottom edge of the screen the first button is.
     * spacing is how much space there is between the bottom of one button and the bottom of another.
     * mainMenuButtonCount increments automatically,
     * so the buttons get spaced correctly without needing to provide exact coordinates for each.
     *
     * @param texturePrefix The first part of the name of the texture.
     * @return the instance of Button.
     */
    public static Button mainMenu(String texturePrefix) {
        float yOffset = 100f;
        float spacing = 10f + EntityType.BUTTON.getHeight();
        return new Button(
                new Vector2(
                        (Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()) / 2.0f,
                        (yOffset + spacing * mainMenuButtonCount++) / Settings.SCALAR
                ),
                texturePrefix + "_active.png",
                texturePrefix + "_inactive.png"
        );
    }

    /**
     * Creates the 'back 'button in the HelpScreen. Button is created at 0,0.
     * @param texturePrefix The first part of the name of the texture.
     * @return the instance of Button.
     */
    public static Button help(String texturePrefix) {
        return new Button(
                new Vector2(0, 0),
                texturePrefix + "_active.png",
                texturePrefix + "_inactive.png"
        );
    }

    /**
     * Static variable used to keep track of how many times the boatSelect method is called.
     */
    private static int boatSelectionButtonCount = 0;

    /**
     * Creates the buttons on the BoatSelectScreen.
     * xOffset is how far off the left edge of the screen the first button is.
     * spacing is how much space there is between the left edge of one button and the left edge of another.
     * boatSelectionButtonCount increments just like it does in mainMenu().
     * @param texturePrefix The first part of the name of the texture.
     * @return the instance of Button.
     */
    public static Button boatSelect(String texturePrefix) {
        float xOffset = (Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth() * 4.0f) / 5.0f;
        float spacing = (EntityType.BUTTON.getWidth() + xOffset);
        return new Button(
                new Vector2(xOffset + spacing * boatSelectionButtonCount++, 100),
                texturePrefix + "_active.png",
                texturePrefix + "_inactive.png"
        );
    }
}
