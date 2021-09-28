package sd_final;

public class VoiceController implements Controller {

    private MusicPlayer musicPlayer;

    public VoiceController(MusicPlayer musicPlayer){
        this.musicPlayer = musicPlayer;
    }

    @Override
    public void next() {
    	System.out.println();
        System.out.println("VOICE CONTROLLER: \"Now playing the next song from the Queue...\"");
        System.out.println();
        musicPlayer.playNextSong();
    }
}