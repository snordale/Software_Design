package sd_final;

import java.util.Iterator;

public class HomeEntertainmentSystem {

	public static void main(String[] args) {
		
		MusicPlayer musicPlayer = new MusicPlayer();
		musicPlayer.addItem(new Song("Rat Tat Tat Tat", "Dr. Dre", 3));
		musicPlayer.addItem(new Song("DHL", "Frank Ocean", 2));
		musicPlayer.addItem(new Song("Who Am I?", "Snoop Dogg", 3));
		musicPlayer.addItem(new Song("Reborn", "KIDS SEE GHOSTS", 2));
		
		Song song1 = new Song("Gangsta Gangsta", "NWA", 4);
		Song song2 = new Song("Texas Sun", "Kruangbin", 3);
		Song song3 = new Song("Babushka Boy", "A$AP Rocky", 3);
		musicPlayer.addItem(song1);
		musicPlayer.addItem(song2);
		musicPlayer.addItem(song3);
		
		Iterator<Song> playerIterator = musicPlayer.iterator();
		while (playerIterator.hasNext()) {
			Song current = playerIterator.next();
			System.out.println(current.getName() + " --- " + current.getArtist() + " --- " + current.getLength() + "min.");
		}
		System.out.println();
		
		Playlist dopeHits = musicPlayer.createPlaylist("Dope Hits");
		System.out.println("Created Playlist \"Dope Hits\"...");
		System.out.println();
		dopeHits.addPlayable(song1);
		dopeHits.addPlayable(song2);
		dopeHits.addPlayable(song3);
		dopeHits.addPlayable(song2);
		System.out.println("Added 4 Songs to \"Dope Hits\"...");
		System.out.println();
		
		Iterator<Playable> playlistIterator = dopeHits.iterator();
		System.out.println("Printing Playlist \"Dope Hits\"...");
		while(playlistIterator.hasNext()) {
			Playable current = playlistIterator.next();
			System.out.println(current.getName());
		}
		System.out.println();
		
		Playlist newerBetterHits = new Playlist("Newer Better Hits");
		newerBetterHits.addPlayable(song1);
		newerBetterHits.addPlayable(song1);
		
		System.out.println("Printing Playlist \"Newer Better Hits\"...");
		playlistIterator = newerBetterHits.iterator();
		while(playlistIterator.hasNext()) {
			Playable current = playlistIterator.next();
			System.out.println(current.getName());
		}
		System.out.println();
		
		dopeHits.addPlayable(newerBetterHits);
		
		System.out.println("Printing Playlist \"Dope Hits\" after adding Playlist \"Newer Better Hits\" to it...");
		
		playlistIterator = dopeHits.iterator();
		while(playlistIterator.hasNext()) {
			Playable current = playlistIterator.next();
			System.out.println(current.getName());
		}
		System.out.println();
		
		System.out.println("Adding Playlist \"Dope Hits\" to the Queue...");
		
		musicPlayer.addPlaylistToQueue(dopeHits);
		System.out.println();
		System.out.println("Printing the Queue...");
		System.out.println();
		musicPlayer.printQueue();
		
		
		RemoteController remoteController = new RemoteController(musicPlayer);
		VoiceController voiceController = new VoiceController(musicPlayer);
		
		System.out.println("Playing next six songs in Queue using RemoteController...");
		remoteController.next();
		remoteController.next();
		remoteController.next();
		remoteController.next();
		remoteController.next();
		remoteController.next();
		System.out.println();
		
		System.out.println("Setting MusicPlayer to Shuffle Play...");
		musicPlayer.setShuffledPlay();
		System.out.println();
		
		System.out.println("Playing next six songs selected from Queue using RemoteController...");
		remoteController.next();
		remoteController.next();
		remoteController.next();
		remoteController.next();
		remoteController.next();
		remoteController.next();
		System.out.println();
		System.out.println("Setting Play Order back to Ordered");
		musicPlayer.setOrderedPlay();
		System.out.println();
		System.out.println("Printing current Queue...");
		System.out.println();
		musicPlayer.printQueue();
		System.out.println("Remove \"Gangsta Gangsta\" from Queue...");
		musicPlayer.removeItemFromQueue("Gangsta Gangsta");
		System.out.println();
		musicPlayer.printQueue();
		
		
		System.out.println("This should reset our traversal, so when we play next 2 songs we should get the first 2 songs in the Queue...");
		System.out.println();
		System.out.println("Playing next song with Voice Controller...");
		voiceController.next();
		System.out.println();
		System.out.println("Playing the next song with Voice Controller...");
		voiceController.next();
		System.out.println();
		System.out.println();
		System.out.println("Now we\'ll add Babushka Boy to the queue...");
		musicPlayer.addItemToQueue("Babushka Boy");
		System.out.println();
		
		System.out.println("We\'ll also set the Play Order to Shuffle...");
		musicPlayer.setShuffledPlay();
		System.out.println();
		
		System.out.println("Now we'll play completely play through the queue...");
		System.out.println();
		while (musicPlayer.hasNextSong()) {
			remoteController.next();
		}
		
		System.out.println();
		System.out.println("By comparing this to the most recent queue printed out, you can see we played every song in the queue with the addition of one Babushka Boy.");
		System.out.println();
		System.out.println("This shows that MusicPlayer is resetting correctly for Additions and Deletions, as well as Ordered and Shuffled Play.");
		System.out.println();
		System.out.println("My use of both controllers also demonstrates their functionality.");
		System.out.println();
	}
}