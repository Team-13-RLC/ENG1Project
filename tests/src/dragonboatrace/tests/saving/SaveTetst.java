package dragonboatrace.tests.saving;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Prefs;
import com.dragonboatrace.tools.Race;
import com.dragonboatrace.tools.Settings;
import dragonboatrace.tests.GdxTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Testing The save() and restore methods of Race.
 */
@RunWith(GdxTestRunner.class)
public class SaveTetst {
    /**
     * Backup of the old save fale.
     */
    private Map<String, ?> oldSave;

    /**
     * Preferences object to interact with the save file
     */
    private Preferences prefs;

    /**
     * Method ran before the test.
     * Sets up the Preferences object. Backs up the old save file.
     * Clears the save file, then writes to it
     */
    @Before
    public void testStart(){
        prefs = Gdx.app.getPreferences(Settings.SAVE_FILE_NAME);
        oldSave = prefs.get();
        prefs.clear();
        prefs.flush();
    }

    /**
     * The main test, creates every possible Race object. (One for every boat type and round).
     * Creates a second object with the same parameters.
     * Saves the first Race, then restores the second one (they use the same save file).
     * If the objects are equal, the test passes.
     * @throws Prefs.SaveDoesNotExist if a save file was not created.
     */
    @Test
    public void saveRaceTest() throws Prefs.SaveDoesNotExist {
        for (int i = 0; i < 5; i++) {
            for (BoatType b : BoatType.values()) {

                Race r1 = new Race(10000, b, i);

                Prefs.Save.open();
                r1.save();
                Prefs.Save.close();

                Race r2 = new Race(10000, b, i);

                Prefs.Restore.open();
                r2.restore();
                Prefs.Restore.close();

                assertEquals(r1, r2);

                r1.dispose();
                r2.dispose();
            }
        }
    }

    /**
     * Method ran after the test.
     * clears the save file. Loads the backed up file. Writes to the file.
     */
    @After
    public void endTest(){
        prefs.clear();
        prefs.put(oldSave);
        prefs.flush();
    }
}

