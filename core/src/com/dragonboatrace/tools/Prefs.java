package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.dragonboatrace.entities.boats.BoatType;

public class Prefs {
    private static final Preferences prefs = Gdx.app.getPreferences("save_data");
    private static final Json json = new Json();

    public static class Save {
        public static void open() {
            prefs.putInteger("save_exists", 1);
        }

        public static void close() {
            prefs.flush();
        }

        public static void putInteger(String key, int i) {
            prefs.putInteger(key, i);
        }

        public static void putFloat(String key, float f) {
            prefs.putFloat(key, f);
        }

        public static void putString(String key, String s) {
            prefs.putString(key, s);
        }

        public static void putArray(String key, Float[] f) {
            prefs.putString(key, json.toJson(f, Float[].class));
        }

        public static void putVector2(String key, Vector2 v) {
            prefs.putFloat(key + 'x', v.x);
            prefs.putFloat(key + 'y', v.y);
        }

    }

    public static class Restore {
        public static void open() throws SaveDoesNotExist {
            if (!prefs.contains("save_exists")) {
                throw new SaveDoesNotExist("No save data found");
            }
        }

        public static void close() {
        } // Exists only for consistency

        public static int getInteger(String key) {
            return prefs.getInteger(key);
        }

        public static float getFloat(String key) {
            return prefs.getFloat(key);
        }

        public static String getString(String key) {
            return prefs.getString(key);
        }

        public static Float[] getArray(String key) {
            return json.fromJson(Float[].class, prefs.getString(key));
        }

        public static Vector2 getVector2(String key) {
            return new Vector2(prefs.getFloat(key + 'x'), prefs.getFloat(key + 'y'));

        }

        public static BoatType getBoatType(String key) {
            return BoatType.values()[prefs.getInteger(key)];
        }
    }


    public static class SaveDoesNotExist extends Exception {
        public SaveDoesNotExist(String errorMessage) {
            super(errorMessage);
        }
    }
}
