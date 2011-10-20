package bowlingkata;

import java.util.ArrayList;
import java.util.List;

class Frame {
   private final Frame nextFrame;
   private List<Integer> rolls;
   private int pins;
   Game outer;

   public Frame(Frame nextFrame, Game outer) {
      this.outer = outer;
      this.nextFrame = nextFrame;
      this.rolls = new ArrayList<Integer>();
      this.pins = 0;
   }

   public void roll(int p) {
      rolls.add(p);
      pins += p;
   }

   private boolean hasNext() {
      return nextFrame != null;
   }

   private boolean isStrike() {
      return pins == 10 && rolls.size() == 1;
   }

   private boolean isSpare() {
      return pins == 10 && rolls.size() == 2;
   }

   private int singleBonus() {
      return rolls.get(0);
   }

   private int doubleBonus() {
      int bonus = singleBonus();
      if (this.isStrike())
         bonus += nextFrame.singleBonus();
      else
         bonus += rolls.get(1);
      return bonus;
   }

   private int bonus() {
      if (!hasNext())
         return 0;
      if (this.isStrike())
         return nextFrame.doubleBonus();
      else if (this.isSpare())
         return nextFrame.singleBonus();
      else
         return 0;
   }

   public int score() {
      int score = pins + this.bonus();
      if (hasNext())
         score += nextFrame.score();
      return score;
   }

   public boolean isComplete() {
      return (hasNext()) && (this.isStrike() || (rolls.size() == 2));
   }

   Frame getNextFrame() {
      return this.nextFrame;
   }

}
