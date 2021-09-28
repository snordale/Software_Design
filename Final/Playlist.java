package sd_final;

import java.util.ArrayList;
import java.util.Iterator;

public class Playlist implements Playable, Iterable<Playable> {
    private final String aName;
    private ArrayList<Playable> aPlayables;

    public Playlist(String pName) {
        assert pName!=null;
        aName = pName;
		aPlayables = new ArrayList<>();
    }

    public void addPlayable(Playable pPlayable) {
        assert pPlayable!=null;
        aPlayables.add(pPlayable);
    }

    public void deletePlayable(Playable pPlayable) {
        assert aPlayables.contains(pPlayable);
        aPlayables.remove(pPlayable);
    }

    @Override
    public String getName() {
        return aName;
    }

    public Iterator<Playable> iterator() { return aPlayables.iterator(); }

    @Override
    public boolean isPlaylist() {
        return true;
    }
}
