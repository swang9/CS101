import java.util.ArrayList;

public class UserAccount extends Account{
  // data fields
  private ArrayList<Playlist> playlists;

  //constructors
  //add constructor that takes in string username and loads info from database
  public UserAccount(String s)
  {
  }

  //methods
  public void addPlaylist(String name) //FINISH calls playlist constructor, adds to playlist arraylist
  {

  }

  //getters
  public Playlist[] getPlaylists()
  {
    return (Playlist[]) playlists.toArray();
  }
}
