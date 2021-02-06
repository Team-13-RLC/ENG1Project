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

    Hitbox boxA = new Hitbox(0, 2, 5, 1);
    Hitbox boxB = new Hitbox(3, 2, 5, 1);
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
        assertEquals(false, laneTest.getHitbox().leaves(boxB));
        // moving the hitbox A outside of the lane's area.
        boxB.move(50, 10);
        assertEquals(true, laneTest.getHitbox().leaves(boxB));
    }

    @Test
    public void playerPenaltyTest() {

    }

    @Test
    public void gameLengthTest() {

    }

    @Test
    public void gameEndTest() {

    }

    public void finalRaceTest(){

    }

    public void qualifierRaceTest(){

    }



}
