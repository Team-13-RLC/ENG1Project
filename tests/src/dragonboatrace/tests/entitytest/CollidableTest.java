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

}