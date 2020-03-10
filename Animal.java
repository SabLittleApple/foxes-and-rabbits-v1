import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class Animal {

    // Characteristics shared by all animals.
    // The age at which an animal can start to breed.
    protected int BREEDING_AGE;
    // The age to which an animal can live.
    protected int MAX_AGE;
    // The likelihood of an animal breeding.
    protected double BREEDING_PROBABILITY;
    // The maximum number of births.
    protected int MAX_LITTER_SIZE;


    // Whether the animal is alive or not.
    protected boolean alive;
    // The animal's position.
    protected Location location;
    // The field occupied.
    protected Field field;

    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();

    /**
     * Create an animal.
     * <p>
     * // * @param randomAge If true, the animal will have random age.
     *
     * @param field    The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location) {

        alive = true;
        this.field = field;
        setLocation(location);
    }

    /**
     * Place the fox at the new location in the given field.
     *
     * @param newLocation The fox's new location.
     */
    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    /**
     * Check whether the fox is alive or not.
     *
     * @return True if the fox is still alive.
     */
    protected boolean isAlive() {

        return alive;
    }

    /**
     * Return the fox's location.
     *
     * @return The fox's location.
     */
    protected Location getLocation() {

        return location;
    }


    /**
     * Indicate that the fox is no longer alive.
     * It is removed from the field.
     */
    protected void setDead() {
        alive = false;
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    // Let all animals act.
abstract public void act (List <Animal> newAnimals);
}
