import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Rabbit extends Animal {

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     *
     * @param randomAge If true, the rabbit will have a random age.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location) {
        super(randomAge, field, location);
        BREEDING_AGE = 5;
        MAX_AGE = 40;
        BREEDING_PROBABILITY = 0.12;
        MAX_LITTER_SIZE = 4;
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
        }

    }

    /**
     * This is what the rabbit does most of the time - it runs
     * around. Sometimes it will breed or die of old age.
     *
     * @param newRabbits A list to return newly born rabbits.
     */
    public void run(List<Rabbit> newRabbits) {
        incrementAge();
        if (alive) {
            giveBirth(newRabbits);
            // Try to move into a free location.
            Location newLocation = field.freeAdjacentLocation(location);
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }


    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     *
     * @param newRabbits A list to return newly born rabbits.
     */
    private void giveBirth(List<Rabbit> newRabbits) {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = field.getFreeAdjacentLocations(location);
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(false, field, loc);
            newRabbits.add(young);
        }
    }
}




