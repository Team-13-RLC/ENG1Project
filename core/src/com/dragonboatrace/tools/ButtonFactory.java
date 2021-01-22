package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;

public class ButtonFactory {

    private static int mainMenuButtonCount = 0;

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

    public static Button help(String texturePrefix) {
        return new Button(
                new Vector2(0, 0),
                texturePrefix + "_active.png",
                texturePrefix + "_inactive.png"
        );
    }

    private static int boatSelectionButtonCount = 0;

    public static Button boatSelect(String texturePrefix) {
        float xOffset = (Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth() * 4.0f) / 5.0f;
        return new Button(
                new Vector2(
                        xOffset +
                                (EntityType.BUTTON.getWidth() + xOffset) *
                        boatSelectionButtonCount++,
                        100),
                texturePrefix + "_active.png",
                texturePrefix + "_inactive.png"
        );
    }
}
