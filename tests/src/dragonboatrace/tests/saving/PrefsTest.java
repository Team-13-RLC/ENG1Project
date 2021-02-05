package dragonboatrace.tests.saving;


import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Prefs;
import dragonboatrace.tests.GdxTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Testing all methods in the Prefs class. With regular value, and all the boundaries (where applicable).
 */
@RunWith(GdxTestRunner.class)
public class PrefsTest {

    /**
     * Key under which every test value is written
     */
    private static String key;

    /**
     * Name of the test being ran.
     */
    @Rule
    public TestName name = new TestName();

    /**
     * Setup before the test. Sets the key to the name of the current test.
     */
    @Before
    public void startTest() {
        key = name.getMethodName();
    }

    /**
     * Saving integers, then checking that the same integers are restored.
     * Testing: regular positive value, zero, negative value, maximum and minimum values.
     */
    @Test
    public void integerTest() {
        int[] values = {200, 0, -1, Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int i : values) {
            Prefs.Save.putInteger(key, i);
            assertEquals(i, Prefs.Restore.getInteger(key));
        }
    }

    /**
     * Saving floats, then checking that the same floats are restored.
     * Testing: regular positive value, zero, negative value, maximum and minimum values.
     */
    @Test
    public void floatTest() {
        float[] values ={0.4f, 0f, -0.4f, Float.MAX_VALUE, Float.MIN_VALUE};
        for(float f : values){
            Prefs.Save.putFloat(key, f);
            assertEquals(f, Prefs.Restore.getFloat(key), 0.001f);
        }
    }

    /**
     * Saving arrays of Float, then checking that the same arrays are restored.
     * Testing: regular length array with a a mixture of positive, negative , zero, maximum and minimum values.
     * Very large array (much larger then the typical size.
     * Array with 0 elements.
     */
    @Test
    public void arrayTest() {
        Float[][] values = {
                {0.4f, 0f, -0.3f, Float.MIN_VALUE, Float.MAX_VALUE, 0.7f, 0.3f, 0.11f},
                new Float[512],
                {}
        };
        Arrays.fill(values[1], 0.4f);
        for(Float[] F : values){
            Prefs.Save.putArray(key, F);
            assertArrayEquals(F, Prefs.Restore.getArray(key));
        }
    }

    /**
     * Saving LibGDX Vector2s, then checking that the same Vectors are restored.
     * Testing: regular length values,  negative and zero values, maximum and minimum values.
     */
    @Test
    public void vector2Test() {

        Vector2[] values = {
                new Vector2(2.3f, 4.5f),
                new Vector2(0f, -4.5f),
                new Vector2(Float.MAX_VALUE, Float.MIN_VALUE),
        };
        for(Vector2 v : values){
            Prefs.Save.putVector2(key, v);
            assertEquals(v.x, Prefs.Restore.getVector2(key).x, 0.001);
            assertEquals(v.y, Prefs.Restore.getVector2(key).y, 0.001);
        }

        // This method creates 2 entries with modified keys, so they cannot be removed by the endTest method.
        Prefs.getPrefs().remove(key + "x");
        Prefs.getPrefs().remove(key + "y");
    }

    /**
     * Saving every boat type, then restoring and comparing them.
     */
    @Test
    public void boatTypeTest() {
        BoatType[] values = BoatType.values();
        for (BoatType b : values) {
            Prefs.Save.putBoatType(key, b);
            assertEquals(b, Prefs.Restore.getBoatType(key));
        }
    }

    /**
     * Testing that if there is an attempt to restore a non existent save, an exception is thrown.
     * @throws Prefs.SaveDoesNotExist if the save file does not exist
     */
    @Test(expected = Prefs.SaveDoesNotExist.class)
    public void openThrowTest() throws Prefs.SaveDoesNotExist {
        Prefs.Restore.open();
    }

    /**
     * Testing if the open() method opens a save file with the "save_exists" field set
     * @throws Prefs.SaveDoesNotExist if the field is not set.
     */
    @Test
    public void openNoThrowTest() throws Prefs.SaveDoesNotExist {
        key = "save_exists";
        Prefs.getPrefs().putInteger(key, 1);
        Prefs.Restore.open();
    }

    /**
     * Testing whether closing a file creates a special field signifying that the save is valid.
     */
    @Test()
    public void closeTest(){
        Prefs.Save.close();
        key = "save_exists";
        assertEquals(1, Prefs.Restore.getInteger(key));
    }

    /**
     * Cleanup after the each test. Remove the key which got written to the save file.
     */
    @After
    public void endTest() {
        Prefs.getPrefs().remove(key);
        Prefs.getPrefs().flush();
    }
}

