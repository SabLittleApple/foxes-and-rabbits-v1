import org.junit.Test;

import static org.junit.Assert.*;

/*
    Write a test to ensure that, at the end of a simulation step, there is no animal (dead or alive)
    in the field that is not in one of the lists and vice versa.
     Should there be any dead animals in any of those places at that stage?
 */
public class SimulatorTest {

    @Test
    public void populate(){
        Simulator simulator = new Simulator();
        simulator.simulateOneStep();

       // assertArrayEquals();

    }

}