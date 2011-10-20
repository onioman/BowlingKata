package bowlingkata;

public class Game {

   private Frame firstFrame;
   private Frame currentFrame;

   public Game() {
      this.firstFrame = createLinkedFrames(10);
      this.currentFrame = firstFrame;
   }

   private Frame createLinkedFrames(int number) {
      if (number == 1)
         return new Frame(null, this);
      else
         return new Frame(createLinkedFrames(number - 1), this);
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
