package bowlingkata;

class NormalFrame extends Frame {
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
   public int doubleBonus() {
      int bonus = singleBonus();
      if (rolls.size() > 1)
         bonus += rolls.get(1);
      else
         bonus += nextFrame.singleBonus();
      return bonus;
   }

   private int bonus() {
      if (this.isStrike())
         return nextFrame.doubleBonus();
      else if (this.isSpare())
         return nextFrame.singleBonus();
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
