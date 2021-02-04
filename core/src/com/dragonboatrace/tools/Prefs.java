package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.dragonboatrace.entities.boats.BoatType;

import java.util.Arrays;

public class Prefs {
    /**
     * The instance of Preferences used to save all data
     */
    private static final Preferences prefs = Gdx.app.getPreferences(Settings.SAVE_FILE_NAME);

    /**
     * Json object used for serialization
     */
    private static final Json json = new Json();

    /**
     * Static class used to save data
     */
    public static class Save {
        /**
         * This method does nothing, but each transaction should start with open and end with close()
         */
        public static void open() {}

        /**
         * Setting the flag to say that the data has been writen.
         * Also flushes the data to a file.
         */
        public static void close() {
            prefs.putInteger("save_exists", 1);
            prefs.flush();
        }

        /**
         * Store an int
         * @param key key the int should be stored under
         * @param i int to be stored
         */
        public static void putInteger(String key, int i) {
            prefs.putInteger(key, i);
        }

        /**
         * Store a float
         * @param key key the float should be stored under
         * @param f int to be stored
         */
        public static void putFloat(String key, float f) {
            prefs.putFloat(key, f);
        }

        /**
         * Store an array of Float. Array is first serialized to JSON then stored as a string.
         * @param key key the array should be stored under
         * @param f array to be stored
         */
        public static void putArray(String key, Float[] f) {
            prefs.putString(key, json.toJson(f, Float[].class));
        }

        /**
         * Store a Vector2. Stored as 2 fields with x and y added for differentiation.
         * @param key key the vector should be stored under
         * @param v vector to be stored
         */
        public static void putVector2(String key, Vector2 v) {
            prefs.putFloat(key + 'x', v.x);
            prefs.putFloat(key + 'y', v.y);
        }

        /**
         * Store BoatType. Stored as an integer.
         * @param key key the BoatType should be stored under.
         * @param b BoatType to be stored.
         */
        public static void putBoatType(String key, BoatType b) {
            prefs.putInteger(key, Arrays.asList(BoatType.values()).indexOf(b));
            }
    }

    /**
     * Static class sued to load data.
     */
    public static class Restore {
        /**
         * Called before any other Restore methods.
         * @throws SaveDoesNotExist if save file does not exist
         */
        public static void open() throws SaveDoesNotExist {
            if (!prefs.contains("save_exists")) {
                throw new SaveDoesNotExist("No save data found");
            }
        }

        /**
         * Called after all other restore methods. Does nothing.
         */
        public static void close() {}

        /**
         * Restore an int
         * @param key key the int is stored under
         * @return the restored int
         */
        public static int getInteger(String key) {
            return prefs.getInteger(key);
        }

        /**
         * Restore a float
         * @param key key the float is stored under
         * @return the restored float
         */
        public static float getFloat(String key) {
            return prefs.getFloat(key);
        }

        /**
         * Restore a Float array. Deserialized from JSON.
         * @param key key the array is stored under.
         * @return the restored array.
         */
        public static Float[] getArray(String key) {
            return json.fromJson(Float[].class, prefs.getString(key));
        }

        /**
         * Restore a Vector2.
         * @param key key the vector is stored under.
         * @return the restored vector.
         */
        public static Vector2 getVector2(String key) {
            return new Vector2(prefs.getFloat(key + 'x'), prefs.getFloat(key + 'y'));

        }

        /**
         * Restore BoatType
         * @param key key the BoatType is stored under
         * @return the restored BoatType
         */
        public static BoatType getBoatType(String key) {
            return BoatType.values()[prefs.getInteger(key)];
        }
    }


    /**
     * Exception thrown if a save file does nto exist
     */
    public static class SaveDoesNotExist extends Exception {
        public SaveDoesNotExist(String errorMessage) {
            super(errorMessage);
        }
    }

    public static Preferences getPrefs() {
        return prefs;
    }
}
