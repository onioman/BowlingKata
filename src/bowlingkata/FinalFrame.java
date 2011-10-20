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
   public Frame getNextFrame() {
      return null;
   }

}
