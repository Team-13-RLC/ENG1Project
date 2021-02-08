package dragonboatrace.tests.difficulty;

import com.dragonboatrace.tools.CollidableStats;
import com.dragonboatrace.tools.Difficulty;
import com.dragonboatrace.tools.Prefs;
import com.dragonboatrace.tools.Settings;
import dragonboatrace.tests.GdxTestRunner;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class DifficultySelectionTest {
    /**
     * Backup of the save file
     */
    private static Map<String, ?> saveBackup;

    @BeforeClass
    public static void backupSave(){
        saveBackup = Prefs.getPrefs().get();
    }

    /**
     * Saving the current state of settings. This creates a point to which settings
     * can easily be returned to maintain independence between tests.
     */
    @Before
    public void setup() {
        Prefs.Save.open();
        Settings.save();
        CollidableStats.save();
        Prefs.Save.close();
    }

    /**
     * Testing if setting difficulty to EASY applies appropriate settings.
     */
    @Test
    public void selectEasy() {
        Difficulty.EASY.set();
        assertEquals(Settings.OBSTACLE_SPAWN_CHANCE, 0.2, 0.0001);
        assertEquals(Settings.OBSTACLE_DAMAGE_MULTIPLIER, 0.5, 0.0001);
        assertEquals(Settings.OBSTACLE_COLLISION_TIME, 0, 0.0001);
        assertEquals(CollidableStats.INVULN_FOR, 5, 0.0001);
        assertEquals(CollidableStats.SPEEDUP_FOR, 10, 0.0001);
        assertEquals(CollidableStats.LESSDAMAGE_FOR, 10, 0.0001);
    }

    /**
     * Testing if setting difficulty to MEDIUM applies appropriate settings.
     */
    @Test
    public void selectMedium() {
        Difficulty.MEDIUM.set();
        assertEquals(Settings.OBSTACLE_SPAWN_CHANCE, 0.5, 0.0001);
        assertEquals(Settings.OBSTACLE_DAMAGE_MULTIPLIER, 1, 0.0001);
        assertEquals(Settings.OBSTACLE_COLLISION_TIME, 0.5, 0.0001);
        assertEquals(CollidableStats.INVULN_FOR, 2.5, 0.0001);
        assertEquals(CollidableStats.SPEEDUP_FOR, 5, 0.0001);
        assertEquals(CollidableStats.LESSDAMAGE_FOR, 5, 0.0001);
    }

    /**
     * Testing if setting difficulty to HARD applies appropriate settings.
     */
    @Test
    public void selectHard() {
        Difficulty.HARD.set();
        assertEquals(Settings.OBSTACLE_SPAWN_CHANCE, 0.8, 0.0001);
        assertEquals(Settings.OBSTACLE_DAMAGE_MULTIPLIER, 2, 0.0001);
        assertEquals(Settings.OBSTACLE_COLLISION_TIME, 1, 0.0001);
        assertEquals(CollidableStats.INVULN_FOR, 0, 0.0001);
        assertEquals(CollidableStats.SPEEDUP_FOR, 2.5, 0.0001);
        assertEquals(CollidableStats.LESSDAMAGE_FOR, 2.5, 0.0001);
    }

    /**
     * Testing if setting difficulty to VERYHARD applies appropriate settings.
     */
    @Test
    public void selectVeryHard() {
        Difficulty.VERYHARD.set();
        assertEquals(Settings.OBSTACLE_SPAWN_CHANCE, 1, 0.0001);
        assertEquals(Settings.OBSTACLE_DAMAGE_MULTIPLIER, 100, 0.0001);
        assertEquals(Settings.OBSTACLE_COLLISION_TIME, 0, 0.0001);
        assertEquals(CollidableStats.INVULN_FOR, 0, 0.0001);
        assertEquals(CollidableStats.SPEEDUP_FOR, 0, 0.0001);
        assertEquals(CollidableStats.LESSDAMAGE_FOR, 0, 0.0001);
    }

    /**
     * Restoring Settings between each test
     * @throws Prefs.SaveDoesNotExist if settings file was not written
     */
    @After
    public void teardown() throws Prefs.SaveDoesNotExist {
        Prefs.Restore.open();
        Settings.restore();
        CollidableStats.restore();
        Prefs.Restore.close();

    }

    @AfterClass
    public static void restoreBackup(){
        Prefs.getPrefs().put(saveBackup);
        Prefs.getPrefs().flush();
    }
}
