import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/*
    Write a test to ensure that, at the end of a simulation step, there is no animal (dead or alive)
    in the field that is not in one of the lists and vice versa.
     Should there be any dead animals in any of those places at that stage?

what to test: for each fox/rabbit in the arrayList, the parameter field is
 */
//public class SimulatorTest {
//    Simulator simulator = new Simulator();
//
//    @Test
//    public boolean testing() {
//        simulator.simulateOneStep();
//        boolean fox_condition = false;
//        boolean rabbit_condition = false;
//        for (Fox fox : simulator.foxList()) {
//            if (fox.field == simulator.getField()) {
//                fox_condition = true;
//            }
//        }
//
//        for (Rabbit rabbit : simulator.rabbitList()) {
//            if (rabbit.field != simulator.getField()) {
//                rabbit_condition = true;
//            }
//        }
//
//        if (fox_condition = true & rabbit_condition = true) {
//            return true;
//        }
//
//        assertTrue(true);
//    }
//}