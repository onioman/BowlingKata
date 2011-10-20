package bowlingkata;

public class FinalFrame extends Frame {

   @Override
   public boolean isComplete() {
      return false;
   }

   @Override
   public int score() {
      return pins;
   }

   @Override
   public int doubleBonus() {
      return singleBonus() + rolls.get(1);
   }

   @Override
   public Frame getNextFrame() {
      return null;
   }

}
