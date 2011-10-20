/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bowlingkata;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author onio
 */
public class GameTest {
    private Game instance;

    public void rollMany(final int times, final int pins) {
        for (int roll=0; roll<times; roll++)
            instance.roll(pins);
    }

    public void rollStrike() {
        instance.roll(10);
    }

    @Before
    public void setUp() {
        instance = new Game();
    }

    @Test
    public void loserGame() {
        rollMany(20, 0);
        assertEquals(0, instance.score());
    }

    @Test
    public void onePinPerRollGame() {
        rollMany(20, 1);
        assertEquals(20, instance.score());
    }

    @Test
    public void oneStrikeInGame() {
        rollStrike();
        rollMany(18, 1);
        assertEquals(10 + 2 + 18, instance.score());
    }

    @Test
    public void twoChainedStrikesInGame() {
        rollStrike();
        rollStrike();
        rollMany(16, 1);
        assertEquals(
                10 + 10 + 1 +
                10 +  1 + 1 +
                16, instance.score());
    }

    @Test
    public void oneSpareInGame() {
        instance.roll(5);
        instance.roll(5);
        rollMany(18, 1);
        assertEquals(10 + 1 + 18, instance.score());
    }
    
    @Test
    public void perfectGame() {
        rollMany(10+2, 10);
        assertEquals(300, instance.score());
    }
}
