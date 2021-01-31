package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

public class Prefs {
    private static final Preferences prefs = Gdx.app.getPreferences("save_data");
    private static final Json json = new Json();
    public static class Save{
        public static void open(){
            prefs.putInteger("save_exists", 1);
        }

        public static void close(){
            prefs.flush();
        }

        public static void putInteger(String key, int i){
            prefs.putInteger(key, i);
        }

        public static void putFloat(String key, float f){
            prefs.putFloat(key, f);
        }

        public static void putString(String key, String s){
            prefs.putString(key, s);
        }

        public static void putArray(String key, float[] f){
            prefs.putString(key, json.toJson(f, float[].class));
        }

    }

    public static class Restore {
        public static void open() throws SaveDoesNotExist {
            if(!prefs.contains("save_exists")) {
                throw new SaveDoesNotExist("No save data found");
            }
        }

        public static void close(){} // Exists only for consistency

        public static int getInteger(String key){
            return prefs.getInteger(key);
        }

        public static float getFloat(String key){
            return prefs.getFloat(key);
        }

        public static String getString(String key){
            return prefs.getString(key);
        }

        public static float[] getArray(String key){
            return json.fromJson(float[].class,prefs.getString(key));
        }

    }


    public static class SaveDoesNotExist extends Exception {
        public SaveDoesNotExist (String errorMessage) {
            super(errorMessage);
        }
    }
}
