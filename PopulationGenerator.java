import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PopulationGenerator extends Simulator {

    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;
    // Lists of animals in the field.
    protected List<Animal> animals;
//

    public PopulationGenerator() {
        super();
        view.setColor(Rabbit.class, Color.ORANGE);
        view.setColor(Fox.class, Color.BLUE);
        animals = new ArrayList<>();
        reset();
    }

    /**
     * Run the simulation from its current state for a single step. Iterate
     * over the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep() {
        step++;

        // Provide space for newborn animals.
        List<Animal> newbornAnimals = new ArrayList<>();
        // Let all animals act.
        for (Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            animal.act(newbornAnimals);
            if (!animal.isAlive()) {
                it.remove();
            }
        }

        // Add the newly born foxes and rabbits to the main lists.
        animals.addAll(newbornAnimals);
        animals.addAll(newbornAnimals);
        view.showStatus(step, field);
    }

    /**
     * Reset the simulation to a starting position.
     */
    void reset() {
        step = 0;
        animals.clear();
        populate();
        // Show the starting state in the view.
        view.showStatus(step, field);
    }

    /**
     * Randomly populate the field with foxes and rabbits.
     */
    protected void populate() {
        Random rand = Randomizer.getRandom();
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                if (rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    animals.add(fox);
                } else if (rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    animals.add(rabbit);
                }
                // else leave the location empty.
            }
        }
    }

}
