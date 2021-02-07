package dragonboatrace.tests.difficulty;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Collidable;
import com.dragonboatrace.screens.MainGameScreen;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.entities.boats.BoatType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class difficultyTest {

    DragonBoatRace game;
    MainGameScreen main;

    @Before
    public void setUp() throws Exception {
        game = mock(DragonBoatRace.class);
        MainGameScreen main = new MainGameScreen(game, BoatType.AGILE);
    }

    @Test
    public void obstacleIncrease() {
        for (int i = 0; i < 600; i++) {
            main.render((float) 0.16);
        }
        ArrayList<Collidable> colList = main.getRace().getPlayer().getLane().getCollidables();
        System.out.println(colList.size());
    }

    @Test
    public void powerupDecrease() {

    }


}
