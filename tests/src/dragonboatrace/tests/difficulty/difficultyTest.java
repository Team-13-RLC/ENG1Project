package dragonboatrace.tests.difficulty;

import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Collidable;
import com.dragonboatrace.entities.boats.BoatType;

import com.dragonboatrace.tools.Race;
import dragonboatrace.tests.GdxTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(GdxTestRunner.class)
public class difficultyTest {

    DragonBoatRace game;
    Race race1, race2, race3;

    @Before
    public void setUp() throws Exception {
        game = mock(DragonBoatRace.class);
        game.create();
        race1 = new Race(10000, BoatType.AGILE, 1);
        race2 = new Race(10000, BoatType.AGILE, 2);
        race3 = new Race(10000, BoatType.AGILE, 3);
    }

    @Test
    public void obstacleIncrease() {
        for (int i = 0; i < 100; i++) {
            race1.update(0.16f, game);
        }
        ArrayList<Collidable> colList1 = race1.getPlayer().getLane().getCollidables();
        int size1 = colList1.size();

        for (int i = 0; i < 100; i++) {
            race2.update(0.16f, game);
        }
        ArrayList<Collidable> colList2 = race2.getPlayer().getLane().getCollidables();
        int size2 = colList2.size();

        for (int i = 0; i < 100; i++) {
            race3.update(0.16f, game);
        }
        ArrayList<Collidable> colList3 = race3.getPlayer().getLane().getCollidables();
        int size3 = colList3.size();

        assertTrue(size1 == size2);

    }

    @Test
    public void powerupDecrease() {

    }


}
