package bowlingkata;

import java.util.*;

public abstract class Frame {
   protected List<Integer> rolls;
   protected int pins;

   public Frame() {
      this.rolls = new ArrayList<Integer>();
      this.pins = 0;
   }

   public void roll(int p) {
      rolls.add(p);
      pins += p;
   }

   public int sumNextRolls(int numRolls) {
      int bonus = 0;
      for (int i=0; i<rolls.size() && i<numRolls; i++)
         bonus += rolls.get(i);
      return bonus;
   }

   public abstract boolean isComplete();

   public abstract int score();

   public abstract Frame getNextFrame();
}
