package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;

public class ButtonFactory {
    public static Button mainMenu(String texturePrefix, float yOffset){
        return new Button(
                new Vector2(
                        (Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()) / 2.0f,
                        yOffset / Settings.SCALAR
                ),
                texturePrefix + "_active.png",
                texturePrefix + "_inactive.png"
        );
    }
}
