package bowlingkata;

public class NormalFrame extends Frame {
   private final Frame nextFrame;

   public NormalFrame(Frame nextFrame) {
      this.nextFrame = nextFrame;
   }

   @Override
   public Frame getNextFrame() {
      return this.nextFrame;
   }

   private boolean isStrike() {
      return pins == 10 && rolls.size() == 1;
   }

   private boolean isSpare() {
      return pins == 10 && rolls.size() == 2;
   }

   @Override
   public int sumNextRolls(int numRolls) {
      int bonus = super.sumNextRolls(numRolls);
      if (rolls.size()<numRolls)
         bonus += nextFrame.sumNextRolls(numRolls - rolls.size());
      return bonus;
   }

   private int bonus() {
      if (this.isStrike())
         return nextFrame.sumNextRolls(2);
      else if (this.isSpare())
         return nextFrame.sumNextRolls(1);
      else
         return 0;
   }

   @Override
   public int score() {
      return pins + bonus() + nextFrame.score();
   }

   @Override
   public boolean isComplete() {
      return this.isStrike() || (rolls.size() == 2);
   }

}
