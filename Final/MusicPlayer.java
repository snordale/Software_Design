package sd_final;

import java.util.LinkedHashMap;
import java.util.Map;
//My import statements. Remove if Prof says no
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A class representing a Music Player App.
 * It contains a pool of songs that allow the user to traverse and add to the queue to play.
 */
public class MusicPlayer implements Iterable<Song> {
    private Map<String, Song> aItems = new LinkedHashMap<>(); // Make sure a predictable iteration order.
    private Map<String, Playlist> aPlaylists = new LinkedHashMap<>(); // Make sure a predictable iteration order.
    private Queue aQueue = new Queue(); //

    // Initialize currentIndex to -1. If we start with Ordered Play, it will go for the next song and we need that to be 0.
    // If we start with Shuffled Play, it won't use the -1 and currentIndex will be updated to new positive value.
    private int currentIndex = -1;
    // Setting Play Order to Ordered by default
    private PlayOrder playOrder = new OrderedPlay(aQueue, currentIndex);

    private Song previousSong = null;

    // Tracking Song Indices that have been played
    private Set<Integer> aPlayedSongs = new HashSet<>();

    MusicPlayer(){}

    // (2.1)
    @Override
    public Iterator<Song> iterator() {
        return aItems.values().iterator();
    }
    
    public void printQueue() {
    	System.out.println("Queue...");
    	for (int i = 0; i < aQueue.size(); i++) {
    		System.out.println(aQueue.get(i).getName());
    	}
    	System.out.println();
    }

    // (2.2)
    public Playlist createPlaylist(String pName) {
        assert !aPlaylists.containsKey(pName);
        Playlist newPlaylist = new Playlist(pName);
        aPlaylists.put(pName, newPlaylist);
        return newPlaylist;
    }

    // (2.3)
    public void deletePlaylist(String pName) {
        assert aPlaylists.containsKey(pName);
        aPlaylists.remove(pName);
    }

    // (2.4)
    public void addPlaylistToQueue(Playlist pPlaylist) {
        Iterator<Playable> playlistIterator = pPlaylist.iterator();
        while(playlistIterator.hasNext()) {
            Playable current = playlistIterator.next();
            if (current.getClass() == Playlist.class) {
            	Playlist playlist = (Playlist) current;
                addPlaylistToQueue(playlist);
            }
            else {
            	Song song = (Song) current;
                aQueue.add(song);
            }
        }
        // (3.4) Reset aPlayedSongs and currentIndex. Reasoning detailed below.
        aPlayedSongs.clear();
        currentIndex = -1;
    }

    // (3.1) Play next Song based on currently selected Play Order
    public Song playNextSong() {
        // (3.3) Make sure we aren't pulling null
        if (playOrder.hasNext()) {
        	System.out.print("Playing next song... ");
        	
            int potentialIndex = playOrder.getNext();
            // Need this because ShuffledPlay will randomly guess indices out of the entire queue
            while (aPlayedSongs.contains(potentialIndex)) {
                potentialIndex = playOrder.getNext();
            }
            
            //Found new song to play, so we can update our Played Songs, previousSong, and currentIndex variables before returning.
            aPlayedSongs.add(potentialIndex);
            previousSong = aQueue.get(potentialIndex);
            currentIndex = potentialIndex;
            System.out.println(previousSong.getName());
            // For Ordered, this will be currentIndex+1. For Shuffled, this will be a random index that hasn't been played yet.
            return previousSong;
        }
        else {
        	System.out.println("Reached end of the queue. Replaying the last song... " + previousSong.getName());
            // (3.3) If there is no Next Song in our Play Order, just keep playing the last song because that's what Spotify does.
            return previousSong;
        }
    }
    
    public boolean hasNextSong() {
    	return playOrder.hasNext();
    }

    // (3.2) Set Play Order
    public void setShuffledPlay() {
    	aPlayedSongs.clear();
        playOrder = new ShuffledPlay(aQueue, aPlayedSongs);
    }

    public void setOrderedPlay() {
        playOrder = new OrderedPlay(aQueue, currentIndex);
    }
    
    public void printPlayOrder() {
    	System.out.println(playOrder);
    }

    /**
     * Add a single song to the music pool if a song with the same name is not already in the pool.
     * @param pItem the song to be added in the pool
     * @pre pItem !=null
     */
    public void addItem(Song pItem) {
        assert pItem != null;
        aItems.putIfAbsent(pItem.getName(), pItem);
    }

    /**
     * Add a single song to the queue if that song can be found in the music pool.
     * @param pItemName the name of the song
     * @pre pItemName !=null
     */
    // (3.4)
    public void addItemToQueue(String pItemName) {
        assert pItemName!= null;
        if (aItems.containsKey(pItemName)) {
            aQueue.add(aItems.get(pItemName));
        }

        // Reset Played Songs for Shuffled Play
        aPlayedSongs.clear();
        // If currently in Ordered Play, reset currentIndex to -1 for same reasons stated above^
        if (playOrder.getClass() == OrderedPlay.class) {
        	OrderedPlay currentPlayOrder = (OrderedPlay) playOrder;
        	currentPlayOrder.setIndex(-1);
        }
    }

    // (3.4) Copy add method, changing add to remove
    public void removeItemFromQueue(String pItemName) {
        assert pItemName!= null;
        if (aItems.containsKey(pItemName)) {
            aQueue.remove(aItems.get(pItemName));
        }

        // Reset Played Songs for Shuffled Play
        aPlayedSongs.clear();
        // If currently in Ordered Play, reset currentIndex to -1 for same reasons stated above^
        if (playOrder.getClass() == OrderedPlay.class) {
        	OrderedPlay currentPlayOrder = (OrderedPlay) playOrder;
        	currentPlayOrder.setIndex(-1);
        }
    }

    /**
     * Obtain the number of songs in the queue
     * @return the number of songs in the queue
     */
    public int getQueueSize() {
        return aQueue.size();
    }

}
