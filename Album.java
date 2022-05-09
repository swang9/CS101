import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;

public class Album
{
	//STATE:
	private String name;
	private String artist;
	private Date dateCreated;
	private ArrayList<Song> albumSongs;

	//CONSTRUCTORS:
	public Album()
	{
		name = "";
		artist = "";
		albumSongs = new ArrayList<Song>();
		dateCreated = new Date();
	}

	public Album(String n, String a, Song[] s)
	{
		name = n;
		artist = a;
		albumSongs = new ArrayList<Song>(Arrays.asList(s));
		dateCreated = new Date();
	}

	//OTHER METHODS


	//GETTERS:

	public String getName()
	{
		return name;
	}

	public String getArtist()
	{
		return artist;
	}

	public Song[] getAlbumSongs()
	{
		return (Song[]) albumSongs.toArray();
	}

	public Date getDate()
	{
		return dateCreated;
	}
}
