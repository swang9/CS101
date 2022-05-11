import java.util.*;
import java.util.ArrayList;

public class Playlist implements java.io.Serializable
{
	//STATE:
	private String name;
	private Date dateCreated;
	private ArrayList<Song> songList = new ArrayList<Song>();

	//CONSTRUCTORS:
	public Playlist()
	{
		name = "";
		dateCreated = new Date();
	}

	public Playlist(String n)
	{
		name = n;
		dateCreated = new Date();
	}

	//OTHER METHODS
	public void addSong(Song s)
	{
		songList.add(s);
	}

	public String toString()
	{
		return name;
	}

	public void removeSong(Song s)
	{
		songList.remove(s);
	}


	//GETTERS:

	public String getName()
	{
		return name;
	}

	public Song[] getSongList()
	{
		Song[] myList = new Song[songList.size()];
		return (Song[]) songList.toArray(myList);
	}
}
