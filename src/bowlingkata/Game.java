package bowlingkata;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private class Frame {

        private List<Integer> rolls;
        private int pins;

        public Frame() {
            rolls   = new ArrayList<Integer>();
            pins    = 0;
        }

        public void roll(int p) {
            rolls.add(p);
            pins += p;
        }

        private boolean isStrike(){
            return (pins == 10 && rolls.size() == 1);
        }

        private boolean isSpare(){
            return (pins == 10 && rolls.size() == 2);
        }

        private int singleBonus() {
            return rolls.get(0);
        }

        private int doubleBonus( Frame next ) {
            int bonus = pins;
            if(next != null && this.isStrike())
                bonus += next.singleBonus();
            return bonus;
        }

        private int bonus(Frame next, Frame nextNext) {
            if(next == null)
               return 0;
            if(this.isStrike())
                return next.doubleBonus(nextNext);
            else if(this.isSpare())
                return next.singleBonus();
            else
               return 0;
        }

        public int score( Frame next, Frame nextNext ) {
            return (pins + this.bonus(next, nextNext));
        }

        public boolean isComplete() {
            return this.isStrike() || (rolls.size() == 2);
        }
    }

    private Frame[] frames;
    private int   index;

    public Game() {
        frames = new Frame[12];
        for(int i=0; i<12; i++)
            frames[i] = new Frame();
        index = 0;
    }

    private Frame curFrame(){
        return frames[index];
    }

    public void roll(int pins) {
        if (curFrame().isComplete()) {
            index++;
        }
        curFrame().roll(pins);
    }

    public int score(){
        int total = 0;
        for(int i=0; i<10; i++)
            total += frames[i].score(frames[i+1], frames[i+2]);
        return total;
    }

}
