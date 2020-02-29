import java.util.List;
import java.util.Random;

public class Animal {

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
     *
    // * @param randomAge If true, the animal will have random age.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     *                  */
    public Animal(Field field, Location location){

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

//    /**
//     * Check whether or not this animal is to give birth at this step.
//     * New births will be made into free adjacent locations.
//     *
//     * @param newAnimals A list to return newly born animals.
//     */
//    protected void giveBirth(List<Animal> newAnimals) {
//        // New animals are born into adjacent locations.
//        // Get a list of adjacent free locations.
//        List<Location> free = field.getFreeAdjacentLocations(location);
//        int births = breed();
//        for (int b = 0; b < births && free.size() > 0; b++) {
//            Location loc = free.remove(0);
//            Animal young = new Animal(false, field, loc);
//            newAnimals.add(young);
//        }
//    }
}
