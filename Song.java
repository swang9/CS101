import java.util.*;

public class Song
{
	//STATE:
	private String name;
	private String artist ;
	private Date dateCreated;

	//BEHAVIOR OR METHODS:

	//CONSTRUCTORS:
	public Song()
	{
		name = "";
		artist = "";
		dateCreated = new Date();
	}

	public Song(String n, String a)
	{
		name = n;
		artist = a;
		dateCreated = new Date();
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

	public Date getDate()
	{
		return dateCreated;
	}

}
