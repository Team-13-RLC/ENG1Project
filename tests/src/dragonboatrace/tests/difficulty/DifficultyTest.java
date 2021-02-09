package dragonboatrace.tests.difficulty;

import com.dragonboatrace.DragonBoatRace;
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
public class DifficultyTest {

    DragonBoatRace game;
    Race race1, race2, race3;

    @Before
    public void setUp() {
        game = mock(DragonBoatRace.class);
        game.create();
        race1 = new Race(10000, BoatType.AGILE, 1);
        race2 = new Race(10000, BoatType.AGILE, 2);
        race3 = new Race(10000, BoatType.AGILE, 3);
    }

    @Test
    public void obstacleIncrease() {
        race1.update(0.16f, game);
        ArrayList<?> colList1 = race1.getPlayer().getLane().getRandomWaitTimes();
        int size1 = colList1.size();


        race2.update(0.16f, game);
        ArrayList<?> colList2 = race2.getPlayer().getLane().getRandomWaitTimes();
        int size2 = colList2.size();

        race3.update(0.16f, game);
        ArrayList<?> colList3 = race3.getPlayer().getLane().getRandomWaitTimes();
        int size3 = colList3.size();


        System.out.println(size1 + " " + size2 + " " + size3);

        assertTrue(size1 <= size2 && size2 <= size3);

    }
}
