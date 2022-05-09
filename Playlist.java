import java.util.*;
import java.util.ArrayList;

public class Playlist
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
		for (int i = 0; i < songList.size()-1; i++)
		{
            if(songList.get(i).equals(s))
            {
               songList.remove(i);
            }
        }
	}

	public void deleteList()
	{
		songList.clear();
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

	public Date getDate()
	{
		return dateCreated;
	}

}
