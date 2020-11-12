package com.dragonboatrace.entities.boats;


import com.badlogic.gdx.graphics.Texture;

public enum BoatType{
    /* ENUM(health, stamina, agility, speed, maxSpeed, image) */
    
    FAST(50, 4000, 85, 200, 10, "fast.png"),
        AGILE(50, 4000, 95, 160, 8, "agile.png"),
    ENDURANCE(70, 6000, 90, 100, 8, "endurance.png"),
    STRONG(100, 3500, 98, 55, 5, "strong.png");

    private float health, stamina, agility, speed, maxSpeed;
    private String imageSrc;

    BoatType(float health, float stamina, float agility, float speed, float maxSpeed, String imageSrc){
        this.health = health;
        this.stamina = stamina;
        this.agility = agility;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.imageSrc = imageSrc;
    }
 
    public float getHealth() { return this.health; }
    public float getStamina() { return this.stamina; }
    public float getAgility() { return this.agility; }
    public float getSpeed() { return this.speed; }
    public float getMaxSpeed() { return this.maxSpeed; }
    public String getImageSrc() { return this.imageSrc; }
    
}
