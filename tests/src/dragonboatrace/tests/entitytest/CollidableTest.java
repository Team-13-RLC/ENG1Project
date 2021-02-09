package dragonboatrace.tests.entitytest;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.CollidableType;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Lane;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.badlogic.gdx.Gdx;
import dragonboatrace.tests.GdxTestRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import com.dragonboatrace.entities.Collidable;

import java.util.ArrayList;

/**
 * Testing methods in relation to the Collidable class.
 * Utilises the tomgrill test runner for junit4 and libgdx.
 */
@RunWith(GdxTestRunner.class)
public class CollidableTest {
    /**
     * An ArrayList that will contain all the different types of collidables for easy reference.
     */
    ArrayList<Collidable> collidableArrayList = new ArrayList<>();
    Vector2 test_pos = new Vector2(0,0);
    Lane test_lane = new Lane(test_pos,100,1);
    Boat test_boat = new Boat(BoatType.STRONG,test_lane,"test");

    /**
     * Setup before the tests, creates all the collidable objects that are to be tested.
     */
    @Before
    public void setup(){
        for (CollidableType type : CollidableType.values()){
            Collidable col = new Collidable(type,0,200);
            collidableArrayList.add(col);
            //Resets boat health after every test
            test_boat.addHealth(200);
        }
    }

    /**
     * Checks to make sure that the Rock collidable moves in the y axis but not the x axis.
     */
    @Test
    public void testRockUpdate(){
        float pre_x = collidableArrayList.get(0).getPos().x;
        float pre_y = collidableArrayList.get(0).getPos().y;
        collidableArrayList.get(0).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(0).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(0).getPos().x != pre_x);
    }
    /**
     * Checks to make sure that the Branch collidable moves in the y axis but not the x axis.
     */
    @Test
    public void testBranchUpdate(){
        float pre_x = collidableArrayList.get(1).getPos().x;
        float pre_y = collidableArrayList.get(1).getPos().y;
        collidableArrayList.get(1).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(1).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(1).getPos().x != pre_x);
    }
    /**
     * Checks to make sure that the Leaf collidable moves in the y axis but not the x axis.
     */
    @Test
    public void testLeafUpdate(){
        float pre_x = collidableArrayList.get(2).getPos().x;
        float pre_y = collidableArrayList.get(2).getPos().y;
        collidableArrayList.get(2).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(2).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(2).getPos().x != pre_x);
    }
    /**
     * Checks to make sure that the Invuln collidable moves in the y axis but not the x axis.
     */
    @Test
    public void testInvulnUpdate(){
        float pre_x = collidableArrayList.get(3).getPos().x;
        float pre_y = collidableArrayList.get(3).getPos().y;
        collidableArrayList.get(3).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(3).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(3).getPos().x != pre_x);
    }
    /**
     * Checks to make sure that the SpeedUp collidable moves in the y axis but not the x axis.
     */
    @Test
    public void testSpeedUpUpdate(){
        float pre_x = collidableArrayList.get(4).getPos().x;
        float pre_y = collidableArrayList.get(4).getPos().y;
        collidableArrayList.get(4).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(4).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(4).getPos().x != pre_x);
    }
    /**
     * Checks to make sure that the LessDamage collidable moves in the y axis but not the x axis.
     */
    @Test
    public void testLessDamageUpdate(){
        float pre_x = collidableArrayList.get(5).getPos().x;
        float pre_y = collidableArrayList.get(5).getPos().y;
        collidableArrayList.get(5).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(5).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(5).getPos().x != pre_x);
    }
    /**
     * Checks to make sure that the LessTime collidable moves in the y axis but not the x axis.
     */
    @Test
    public void testLessTimeUpdate(){
        float pre_x = collidableArrayList.get(6).getPos().x;
        float pre_y = collidableArrayList.get(6).getPos().y;
        collidableArrayList.get(6).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(6).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(6).getPos().x != pre_x);
    }
    /**
     * Checks to make sure that the Heal collidable moves in the y axis but not the x axis.
     */
    @Test
    public void testHealUpdate(){
        float pre_x = collidableArrayList.get(7).getPos().x;
        float pre_y = collidableArrayList.get(7).getPos().y;
        collidableArrayList.get(7).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(7).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(7).getPos().x != pre_x);
    }

    /**
     * Tests if the Rock collidable does the correct amount of damage. Also, this implicitly tests the .takeEffect
     * method of the collidable class.
     */
    @Test
    public void testRockDamage(){
        collidableArrayList.get(0).takeEffect(test_boat);
        assertTrue(test_boat.getHealth() == 80.0);
    }

    /**
     * Tests if the Branch collidable does the correct amount of damage.
     */
    @Test
    public void testBranchDamage(){
        collidableArrayList.get(1).takeEffect(test_boat);
        assertTrue(test_boat.getHealth() == 90);
    }

    /**
     * Tests if the Leaf collidable does the correct amount of damage.
     */
    @Test
    public void testLeafDamage(){
        collidableArrayList.get(2).takeEffect(test_boat);
        assertTrue(test_boat.getHealth() == 95);
    }

    /**
     * Tests if the Invuln powerup applies it's effect properly.
     */
    @Test
    public void testInvulnEffect(){
        collidableArrayList.get(3).takeEffect(test_boat);
        assertTrue(test_boat.getBuff() == 0);
    }

    /**
     * Tests if the Speedup powerup applies it's effect properly.
     */
    @Test
    public void testSpeedUpEffect(){
        collidableArrayList.get(4).takeEffect(test_boat);
        assertTrue(test_boat.getSpeed() == 250);
    }

    /**
     * Tests if the LessDamage powerup applies it's effect properly.
     */
    @Test
    public void testLessDamageEffect(){
        collidableArrayList.get(5).takeEffect(test_boat);
        assertTrue(test_boat.getBuff() == 0.5);
    }

    /**
     * Tests if the LessTime powerup applies it's effect correctly.
     * Testing: Time = 0, 0.5f and 10 respectively.
     */
    @Test
    public void testLessTime(){
        float[] values = {0,0.5f,10};
        for(float val : values){
            test_boat.setTestTime(val);
            collidableArrayList.get(6).takeEffect(test_boat);
            assertEquals(val - 0.5f,test_boat.getTime(),0.01f);
        }
    }

    /**
     * Tests to see if the Heal powerup applies it's effect correctly.
     */
    @Test
    public void testHealEffect(){
        test_boat.addHealth(-50);
        collidableArrayList.get(7).takeEffect(test_boat);
        assertTrue(test_boat.getHealth() == 75);
    }

}