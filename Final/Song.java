package sd_final;

public class Song implements Playable {
    private final String aName;
    private final String aArtist;
    private final int aLength;


    public Song(String pName, String pArtist, int pLength) {
        assert pName!=null && pArtist!=null && pLength >0;
        aName = pName;
        aArtist = pArtist;
        aLength = pLength;
    }

    @Override
    public boolean isPlaylist() {
        return false;
    }

    @Override
    public String getName() {
        return aName;
    }

    public String getArtist() {
        return aArtist;
    }

    public int getLength() {
        return aLength;
    }
}
