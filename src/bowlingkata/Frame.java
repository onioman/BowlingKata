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

   public int singleBonus() {
      return rolls.get(0);
   }

   public abstract boolean isComplete();

   public abstract int score();

   public abstract int doubleBonus();

   public abstract Frame getNextFrame();
}
