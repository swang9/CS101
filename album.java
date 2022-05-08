
import java.util.Arrays;
import java.util.Date;

public class album
{
	//STATE:
	private String name;
	private String artist;
	private Date dateCreated;
	private String [] albumSongs; //LIST OF SONG OBJECTS INSTEAD OF STRINGS
	
	//CONSTRUCTORS:
	public album() 
	{
		name = "";
		artist = "";
		albumSongs = new String [0];
		dateCreated = new Date();
	}
	
	public album(String n, String a, int s) 
	{
		name = n;
		artist = a;
		albumSongs = new String[s];
		dateCreated = new Date();
	}
	
	//OTHER METHODS
	int head = 0;
	public void addSong(String s) 
	{
		albumSongs[head] = s;
		head++;
	}
	
	
	//GETTERS:

	public String getName() 
	{
		return name;
	}
	
	public String getArtist() 
	{
		return artist;
	}
	
	public String getalbumSongs() 
	{
		return Arrays.toString(albumSongs);
	}
	
	public Date getDate() 
	{
		return dateCreated;
	}
	
}
