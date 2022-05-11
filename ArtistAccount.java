import java.util.ArrayList;

public class ArtistAccount extends Account{
  // data fields
  private ArrayList<Album> albums; //CHANGE TO ARRAYLIST

  //constructors
  public ArtistAccount()
  {
  }

  public ArtistAccount(String user, String pass)
  {
    super(user,pass);
    albums = new ArrayList<Album>();
  }

  //getters

  public Album[] getAlbums()
  {
    return albums.toArray(new Album[albums.size()]);
  }


  //methods
  public void addAlbum(Album a)
  {
    albums.add(a);
  }
 }
