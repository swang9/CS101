import java.util.ArrayList;

public class UserAccount extends Account{
  // data fields
  private ArrayList<Playlist> playlists;

  //constructors
  //add constructor that takes in string username and loads info from database
  public UserAccount(String s)
  {
    playlists = new ArrayList<Playlist>();
    addPlaylist("0");
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
