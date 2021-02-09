package dragonboatrace.tests.tools;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.tools.Hitbox;
import com.dragonboatrace.tools.Lane;
import dragonboatrace.tests.GdxTestRunner;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class toolsTest {

    Hitbox boxA = new Hitbox(0, 0, 5, 1);
    Hitbox boxB = new Hitbox(5, 0, 5, 1);
    Vector2 laneVector = new Vector2(0, 0);
    Lane laneTest = new Lane(laneVector, 10, 1);

    // testing for the functional requirement: collision detection.
    @Test
    public void collisionDetectionTest() {
        assertEquals(true, boxA.collidesWith(boxB));
    }

    // testing for the functional requirement: boundary detection.
    // TO DO
    @Test
    public void boundaryDetectionTest() {
        assertFalse(boxB.leaves(laneTest.getHitbox()));
        // moving the hitbox A outside of the lane's area.
        boxB.move(-1, -1);
        assertTrue(boxB.leaves(laneTest.getHitbox()));
    }


    @Test
    public void playerPenaltyTest()
    {

    }

    @Test
    public void gameEndTest() {

    }

    public void finalRaceTest() {

    }

    public void qualifierRaceTest() {

    }


}
