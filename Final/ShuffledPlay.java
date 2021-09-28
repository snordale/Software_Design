package sd_final;

import java.util.Set;
import java.util.Random;

public class ShuffledPlay implements PlayOrder {

    private Queue queue;
    private Set<Integer> playedSongs;

    public ShuffledPlay(Queue queue, Set<Integer> playedSongs) {
        this.queue = queue;
        this.playedSongs = playedSongs;
    }

    @Override
    public int getNext() {
    	Random random = new Random();
        return random.nextInt(queue.size());
    }

    @Override
    public boolean hasNext() {
        if (playedSongs.size() < queue.size()) {
            return true;
        }
        else {
            return false;
        }
    }
}