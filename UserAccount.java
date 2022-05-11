import java.util.ArrayList;

public class UserAccount extends Account{
  // data fields
  private ArrayList<Playlist> playlists;

  //constructors
  public UserAccount()
  {
    playlists = new ArrayList<Playlist>();
    addPlaylist("Playlist 0");
  }

  public UserAccount(String user, String pass)
  {
    super(user,pass);
    playlists = new ArrayList<Playlist>();
    addPlaylist("Playlist 0");
  }

  //methods
  public void addPlaylist(String name) //FINISH calls playlist construcor, adds playlist to arraylist
  {
    playlists.add(new Playlist(name));
  }

  public void removePlaylist(Playlist playlist)
  {
    playlists.remove(playlist);
  }

  //getters
  public Playlist[] getPlaylists()
  {
    Playlist[] mylist = new Playlist[playlists.size()];
    return (Playlist[])playlists.toArray(mylist);
  }
}
