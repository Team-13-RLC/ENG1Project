package dragonboatrace.tests.entitytest;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.CollidableType;
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

@RunWith(GdxTestRunner.class)
public class CollidableTest {

    ArrayList<Collidable> collidableArrayList = new ArrayList<>();


    @Before
    public void setup(){
        for (CollidableType type : CollidableType.values()){
            Collidable col = new Collidable(type,0,200);
            collidableArrayList.add(col);
        }
    }

    @Test
    public void testRockUpdate(){
        float pre_x = collidableArrayList.get(0).getPos().x;
        float pre_y = collidableArrayList.get(0).getPos().y;
        collidableArrayList.get(0).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(0).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(0).getPos().x != pre_x);
    }
    @Test
    public void testBranchUpdate(){
        float pre_x = collidableArrayList.get(1).getPos().x;
        float pre_y = collidableArrayList.get(1).getPos().y;
        collidableArrayList.get(1).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(1).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(1).getPos().x != pre_x);
    }
    @Test
    public void testLeafUpdate(){
        float pre_x = collidableArrayList.get(2).getPos().x;
        float pre_y = collidableArrayList.get(2).getPos().y;
        collidableArrayList.get(2).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(2).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(2).getPos().x != pre_x);
    }
    @Test
    public void testInvulnUpdate(){
        float pre_x = collidableArrayList.get(3).getPos().x;
        float pre_y = collidableArrayList.get(3).getPos().y;
        collidableArrayList.get(3).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(3).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(3).getPos().x != pre_x);
    }
    @Test
    public void testSpeedUpUpdate(){
        float pre_x = collidableArrayList.get(4).getPos().x;
        float pre_y = collidableArrayList.get(4).getPos().y;
        collidableArrayList.get(4).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(4).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(4).getPos().x != pre_x);
    }
    @Test
    public void testLessDamageUpdate(){
        float pre_x = collidableArrayList.get(5).getPos().x;
        float pre_y = collidableArrayList.get(5).getPos().y;
        collidableArrayList.get(5).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(5).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(5).getPos().x != pre_x);
    }
    @Test
    public void testLessTimeUpdate(){
        float pre_x = collidableArrayList.get(6).getPos().x;
        float pre_y = collidableArrayList.get(6).getPos().y;
        collidableArrayList.get(6).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(6).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(6).getPos().x != pre_x);
    }
    @Test
    public void testHealUpdate(){
        float pre_x = collidableArrayList.get(7).getPos().x;
        float pre_y = collidableArrayList.get(7).getPos().y;
        collidableArrayList.get(7).update(2,5);
        assertTrue("Y position should move.",collidableArrayList.get(7).getPos().y < pre_y);
        assertFalse("X position shouldn't move.",collidableArrayList.get(7).getPos().x != pre_x);
    }

}