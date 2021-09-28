package sd_final;

public class RemoteController implements Controller {

    private MusicPlayer musicPlayer;

    public RemoteController(MusicPlayer musicPlayer){
        this.musicPlayer = musicPlayer;
    }

    @Override
    public void next() {
        musicPlayer.playNextSong();
    }
}