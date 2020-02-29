import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Fox extends Animal {


    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    private int RABBIT_FOOD_VALUE = 9;
    // A shared random number generator to control breeding.

    private int foodLevel;

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     *
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    public Fox(boolean randomAge, Field field, Location location) {
        super(randomAge, field, location);

        BREEDING_AGE = 15;
        MAX_AGE = 150;
        BREEDING_PROBABILITY = 0.08;
        MAX_LITTER_SIZE = 2;

        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        } else {
            // leave age at 0
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        }
    }

    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     *
     * //@param field    The field currently occupied.
     * @param newFoxes A list to return newly born foxes.
     */
    public void hunt(List<Fox> newFoxes) {
        incrementAge();
        incrementHunger();
        if (alive) {
            giveBirth(newFoxes);
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if (newLocation == null) {
                // No food found - try to move to a free location.
                newLocation = field.freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }



    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     *
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood() {
        List<Location> adjacent = field.adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        Location location = null;
        while (it.hasNext()) {
            Location newLocation = it.next();
            Object animal = field.getObjectAt(newLocation);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel += RABBIT_FOOD_VALUE;
                    location = newLocation;
                }
            }
        }
        return location;
    }

    /**
     * Check whether or not this fox is to give birth at this step.
     * New births will be made into free adjacent locations.
     *
     * @param newFoxes A list to return newly born foxes.
     */
    private void giveBirth(List<Fox> newFoxes) {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = field.getFreeAdjacentLocations(location);
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Fox young = new Fox(false, field, loc);
            newFoxes.add(young);
        }
    }



}
