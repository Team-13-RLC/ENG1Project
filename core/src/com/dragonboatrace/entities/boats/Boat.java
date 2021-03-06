package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Collidable;
import com.dragonboatrace.entities.Entity;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.tools.*;

/**
 * Represents a generic Boat.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class Boat extends Entity {

    /**
     * The rate at which the stamina is used or regenerated at.
     */
    private final float staminaRate = 10;

    /**
     * The minimum amount of speed gained from using stamina.
     */
    private final int minBoostSpeed = 5;

    /**
     * The formatter used to align the text on-screen.
     */
    protected GlyphLayout layout;

    /**
     * The health of the boat.
     */
    protected float health;

    /**
     * The stamina of the boat.
     */
    protected float stamina;

    /**
     * The agility of the boat.
     */
    protected final float agility;

    /**
     * The speed of the boat.
     * <p>
     * This is the speed attribute of the boat, not how fast it actually is moving.
     */
    protected float speed;

    /**
     * The maximum amount of stamina the boat can have.
     */
    protected float maxStamina;

    /**
     * The lane of the boat.
     */
    protected Lane lane;

    /**
     * The lanes hit box, use to determine if the boat is still in the lane.
     */
    protected Hitbox laneBox;

    /**
     * The name of the boat.
     */
    protected String name;

    /**
     * The total distance travelled by the boat.
     */
    protected float distanceTravelled = 0.0f;

    /**
     * If there has been a recent collision.
     */
    protected boolean recentCollision = false;

    /**
     * Timer used to countdown for when the boat can move again after a collision.
     */
    protected float collisionTime = 0;

    /**
     * Boat Type of boat used to remember the chosen boat type
     */
    protected BoatType boatType;

    /**
     * The current time of the boat in the current round.
     */
    protected float time;

    /**
     * Total time added up across all rounds.
     */
    protected float totalTime;

    /**
     * Total time penalties the boat got.
     */
    protected float penaltyTime;

    /**
     * Controlled by powerups, controls how much damage the boat receives
     */
    protected float buff = 1;

    /**
     * Generator for FreeType Font.
     */
    protected FreeTypeFontGenerator generator;

    /**
     * Parameter for FreeType Font.
     */
    protected FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    /**
     * Font for Health Bar.
     */
    protected BitmapFont healthFont;

    /**
     * Font for Stamina Bar.
     */
    protected BitmapFont staminaFont;

    /**
     * Font for Name Tag.
     */
    protected BitmapFont nameFont;

    /**
     * Creates a Boat with the specified BoatType for pre-defined values,
     * a Lane to give the boat its position and a name for easy identification.
     *
     * @param boat The type of boat to use as a template.
     * @param lane The lane the boat is in.
     * @param name The name of the boat.
     */
    public Boat(BoatType boat, Lane lane, String name) {
        /* Get boat position from the position of the lane. */
        super(VectorFactory.boatPosition(lane.getHitbox().getX(), lane.getHitbox().getWidth()), new Vector2(), EntityType.BOAT, boat.getImageSrc());
        this.health = boat.getHealth();
        this.stamina = boat.getStamina();
        this.agility = boat.getAgility();
        this.speed = boat.getSpeed();
        this.maxStamina = boat.getStamina();
        this.lane = lane;
        this.name = name;
        this.boatType = boat;
        this.time = 0;
        this.totalTime = 0;
        this.penaltyTime = 0;

        /* Store the lanes hit box to save time on using Getters. */
        laneBox = lane.getHitbox();

        /* Setup fonts to use in the HUD */
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        this.parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        this.layout = new GlyphLayout();

        /*Font for displaying the name */
        parameter.size = 50;
        parameter.color = Color.BLACK;
        this.nameFont = generator.generateFont(parameter);

        layout.setText(nameFont, this.name);
        if (this.layout.width > this.laneBox.getWidth()) {
            parameter.size = (int) (50 / (this.layout.width / this.laneBox.getWidth()));
            parameter.color = Color.BLACK;
            nameFont = generator.generateFont(parameter);
        }

        /* Font for displaying the health */
        parameter.size = 50;
        parameter.color = Color.RED;
        this.healthFont = generator.generateFont(parameter);

        layout.setText(healthFont, "Health:  XXX");
        if (this.layout.width > this.laneBox.getWidth()) {
            parameter.size = (int) (50 / (this.layout.width / this.laneBox.getWidth()));
            parameter.color = Color.RED;
            healthFont = generator.generateFont(parameter);
        }

        /* Font for displaying the stamina */
        parameter.size = 50;
        parameter.color = Color.GREEN;
        this.staminaFont = generator.generateFont(parameter);

        layout.setText(staminaFont, "Stamina: XXX");
        if (this.layout.width > this.laneBox.getWidth()) {
            parameter.size = (int) (50 / (this.layout.width / this.laneBox.getWidth()));
            parameter.color = Color.GREEN;
            staminaFont = generator.generateFont(parameter);
        }

    }

    /**
     * Return a scalar to multiply the velocity by when using stamina.
     *
     * @return A float between 0.25 and 1 which is then scaled by {@link com.dragonboatrace.tools.Settings#STAMINA_SPEED_DIVISION}.
     */
    protected float velocityPercentage() {
        double result = 0.25 + Math.log(this.stamina + 1) / 3;
        return (float) result / Settings.STAMINA_SPEED_DIVISION;
    }

    /**
     * Get the amount of stamina to use for the current amount of stamina.
     * <p>
     * The amount of stamina that gets used per cycle is not linear. The more stamina you have the slower it is used,
     * and the less stamina the faster.
     *
     * @return A float of how much stamina will be used.
     */
    protected float useStamina() {
        double result = Math.pow(this.maxStamina, -this.stamina / (2 * this.maxStamina)) * this.staminaRate + this.staminaRate + this.minBoostSpeed;
        return (float) result;
    }

    /**
     * Get the amount of stamina that will be gained for the current amount of stamina.
     *
     * @return A float of how much stamina will be gained.
     */
    protected float regenerateStamina() {
        double result = -1 * this.staminaRate * Math.pow(this.maxStamina, -this.stamina / (2 * this.maxStamina)) + this.staminaRate + 1;
        return (float) result / 10;
    }

    /**
     * Update the position of the boat in relation to the amount of time passed.
     *
     * @param deltaTime The time passed since the last frame.
     */
    public void update(float deltaTime) {

        /* Check if boat is still in the lane */
        if (this.getHitBox().leaves(this.laneBox))
            this.penaltyTime += 0.1;
        this.penaltyTime = Math.round(this.penaltyTime * 100) / (float) 100;


        this.distanceTravelled += this.velocity.y * deltaTime;

        /* Update lane contents */
        this.lane.update(deltaTime, this.velocity.y);

        /* Slowly return the velocity to 0 */
        float dampen = agility / 100;
        if (!(this.velocity.isZero((float) 0.001))) {
            this.position.x += this.velocity.x * deltaTime;
            this.velocity.scl(dampen);
        }

        /* The hit box needs moving to keep at the same pos as the boat */
        this.hitbox.move(position.x, position.y);
    }

    /**
     * Render the boat and its lane contents. Renders the boat as well as its health and stamina.
     *
     * @param batch The SpriteBatch that the renders will be added to.
     */
    public void render(SpriteBatch batch) {
        this.lane.render(batch);

        layout.setText(healthFont, "Health:  XXX");

        healthFont.draw(batch, "Health:  " + (int) this.getHealth(), this.lane.getHitbox().getX() + 5, Gdx.graphics.getHeight() - 55);

        layout.setText(staminaFont, "Stamina: XXX");

        staminaFont.draw(batch, "Stamina: " + (int) this.getStamina(), this.lane.getHitbox().getX() + 5, Gdx.graphics.getHeight() - 105);

        super.render(batch);
    }

    /**
     * Check for collisions by comparing positions of collidables in the lane to the boat position.
     * Then removing those that have been collided with and applying their effects to the boat.
     *
     * @return True if a collision occurred, False if no collision.
     */
    protected boolean checkCollisions() {
        for (Collidable collidable : lane.getCollidables()) {
            if (collidable.getHitBox().collidesWith(this.hitbox)) {
                collidable.dispose();
                lane.getCollidables().remove(collidable);
                lane.replaceCollidable();
                collidable.takeEffect(this);
                return !collidable.isPowerup();
            }
        }
        return false;
    }

    /**
     * Update the vertical position of the boat onscreen.
     * <p>
     * This is not implemented in the generic Boat class and must be implemented in the specific Boat kind.
     *
     * @param lineHeight   The height of the finish line Entity.
     * @param raceDistance The length of the race.
     */
    public void updateYPosition(int lineHeight, int raceDistance) {

    }

    /* Adders */

    /**
     * Increase the inc of the boat.
     *
     * @param inc amount by which to increase speed
     */
    public void addSpeed(float inc) {
        this.speed += inc;
    }

    /**
     * Increase the current boat health.
     *
     * @param change The amount of health to be added.
     */
    public void addHealth(float change) {
        health = Math.min(change + health, boatType.getHealth());
    }

    /**
     * Increase the current boat stamina.
     *
     * @param change The amount of health to be added.
     */
    public void addStamina(float change) {
        this.stamina += change;
    }

    /* Getters */

    /**
     * Get the current velocity of the boat.
     *
     * @return A Vector2 of the boats current velocity.
     */
    public Vector2 getVelocity() {
        return this.velocity;
    }

    /**
     * Get the speed of the boat. <p>
     * This is the speed property of the boat, not the speed at which it is moving.
     *
     * @return The speed of the boat.
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Get the current health of the boat.
     *
     * @return The health of the boat as a float.
     */
    public float getHealth() {
        return this.health;
    }

    /**
     * Get the current stamina of the boat.
     *
     * @return The stamina of the boat as a float.
     */
    public float getStamina() {
        return this.stamina;
    }

    /**
     * Get the current agility of the boat.
     *
     * @return The agility of the boat as a float.
     */
    public float getAgility() {
        return this.agility;
    }

    /**
     * Get the lane that the boat is in.
     *
     * @return The actual Lane object the boat is in.
     */
    public Lane getLane() {
        return this.lane;
    }

    /**
     * Get the name of the boat.
     *
     * @return A string of the boats name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the boat type.
     *
     * @return A BoatType.
     */
    public BoatType getBoatType() {
        return this.boatType;
    }

    /**
     * Get the boat time
     *
     * @return A float of boat time
     */
    public float getTime() {
        return this.time;
    }

    /**
     * Set the boat time
     *
     * @param nowTime The time passed since last call.
     */
    public void setTime(float nowTime) {
        this.time += nowTime;
    }

    /**
     * Set the boat time to the newtime value.
     *
     * Created to aid testing.
     * @param newtime the new time value.
     */
    public void setTestTime(float newtime){this.time = newtime;}

    /**
     * Get the total boat time
     *
     * @return A float of total boat time
     */
    public float getTotalTime() {
        return this.totalTime;
    }

    /**
     * Set the total boat time.
     *
     * @param nowTime The time passed since last call.
     */
    public void setTotalTime(float nowTime) {
        this.totalTime += nowTime;
    }

    public float getPenaltyTime() {
        return this.penaltyTime;
    }

    /**
     * Get the total distance travelled by the boat so far.
     *
     * @return A float of the distance travelled.
     */
    public float getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public void setBuff(float buff) {
        this.buff = buff;
    }

    public float getBuff() {
        return buff;
    }

    /**
     * Dispose of the fonts used in the HUD and then perform {@link Entity}'s dispose.
     */
    public void dispose() {

        this.lane.dispose();
        super.dispose();
    }

    public void restore() {
        super.restore(name);
        boatType = Prefs.Restore.getBoatType("boatType" + name);

        health = Prefs.Restore.getFloat("health" + name);
        stamina = Prefs.Restore.getFloat("stamina" + name);

        speed = boatType.getSpeed();
        maxStamina = boatType.getStamina();

        time = Prefs.Restore.getFloat("time" + name);
        totalTime = Prefs.Restore.getFloat("totalTime" + name);
        penaltyTime = Prefs.Restore.getFloat("penaltyTime" + name);
    }

    public void save() {
        super.save(name);
        Prefs.Save.putBoatType("boatType" + name, boatType);
        Prefs.Save.putFloat("health" + name, health);
        Prefs.Save.putFloat("stamina" + name, stamina);
        Prefs.Save.putFloat("time" + name, time);
        Prefs.Save.putFloat("totalTime" + name, totalTime);
        Prefs.Save.putFloat("penaltyTime" + name, penaltyTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj instanceof Boat) {
            Boat e = (Boat) obj;
            return super.equals(e) &&
                    this.boatType.equals(e.boatType) &&
                    this.health == (e.health) &&
                    this.stamina == e.stamina &&
                    this.time == e.time &&
                    this.totalTime == e.totalTime &&
                    this.penaltyTime == e.penaltyTime;
        }
        return false;
    }
}
