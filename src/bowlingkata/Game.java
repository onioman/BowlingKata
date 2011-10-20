package bowlingkata;

import java.util.ArrayList;
import java.util.List;

public class Game {

   private class Frame {

      private final Frame nextFrame;
      private List<Integer> rolls;
      private int pins;

      public Frame(Frame nextFrame) {
         this.nextFrame = nextFrame;
         this.rolls = new ArrayList<Integer>();
         this.pins = 0;
      }

      public void roll(int p) {
         rolls.add(p);
         pins += p;
      }

      private boolean isStrike() {
         return (pins == 10 && rolls.size() == 1);
      }

      private boolean isSpare() {
         return (pins == 10 && rolls.size() == 2);
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
         if (nextFrame == null)
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
         if (nextFrame != null)
            score += nextFrame.score();
         return score;
      }

      public boolean isComplete() {
         return (nextFrame != null) &&
                 (this.isStrike() || (rolls.size() == 2));
      }

      private Frame getNextFrame() {
         return this.nextFrame;
      }
   }

   private Frame firstFrame;
   private Frame currentFrame;

   public Game() {
      this.firstFrame = createLinkedFrames(10);
      this.currentFrame = firstFrame;
   }

   private Frame createLinkedFrames(int number) {
      if (number == 1)
         return new Frame(null);
      else
         return new Frame(createLinkedFrames(number - 1));
   }

   private Frame curFrame() {
      return currentFrame;
   }

   public void roll(int pins) {
      if (curFrame().isComplete())
         currentFrame = currentFrame.getNextFrame();
      curFrame().roll(pins);
   }

   public int score() {
      return firstFrame.score();
   }
}
