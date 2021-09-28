package sd_final;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class TestShuffledPlay {

	
	// Test to make sure getNext() is generating a valid index.
	@Test
    public int testGettingRandomCorrectly() {
    	Queue queue = new Queue();
    	queue.add(new Song("Happy", "Pharrel", 3));
    	queue.add(new Song("Happy", "Pharrel", 3));
    	queue.add(new Song("Happy", "Pharrel", 3));

    	Set<Integer> playedSongs = new HashSet<>();
    	ShuffledPlay shuffledPlay = new ShuffledPlay(queue, playedSongs);
    	
    	int r = shuffledPlay.getNext();
        assertTrue( r >= 0 && r < queue.size());
    }

	// The next three test methods test different scenarios for hasNext().
	
	// If there are no songs in queue, there should be no next song. 
    @Test
    public boolean testEmptyQueueHasNext() {
    	Queue queue = new Queue();
    	Set<Integer> playedSongs = new HashSet<>();
    	ShuffledPlay shuffledPlay = new ShuffledPlay(queue, playedSongs);
    	assertFalse(shuffledPlay.hasNext());
    }
    
    // If there are songs in queue that have not been played, there should be a next song. 
    @Test
    public boolean testNonEmptyQueueHasNext() {
    	Queue queue = new Queue();
    	queue.add(new Song("Happy", "Pharrel", 3));
    	Set<Integer> playedSongs = new HashSet<>();
    	ShuffledPlay shuffledPlay = new ShuffledPlay(queue, playedSongs);
    	assertTrue(shuffledPlay.hasNext());
    }
    
    // If all songs that are in queue have been played, there should be no next song. 
    @Test
    public boolean testNonEmptyQueueAndPlayedSongHasNext() {
    	Queue queue = new Queue();
    	queue.add(new Song("Happy", "Pharrel", 3));
    	Set<Integer> playedSongs = new HashSet<>();
    	playedSongs.add(0);
    	ShuffledPlay shuffledPlay = new ShuffledPlay(queue, playedSongs);
    	assertFalse(shuffledPlay.hasNext());
    }
    
    
    
}
