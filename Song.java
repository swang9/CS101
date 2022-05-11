import java.util.*;

public class Song implements java.io.Serializable
{
	//STATE:
	private String name;
	private String artist;
	private String album;

	//BEHAVIOR OR METHODS:

	//CONSTRUCTORS:
	public Song()
	{
		name = "";
		artist = "";
	}

	public Song(String n, String a)
	{
		name = n;
		artist = a;
	}

	public Song(String Sname, String Sartist, String Salbum)
	{
		name = Sname;
		artist = Sartist;
		album = Salbum;
	}

	//GETTERS:

	public String getName()
	{
		return name;
	}

	public String getAlbum()
	{
		return album;
	}

	public String toString()
	{
		return name;
	}

	public String songInfo()
	{
		String info = new String("Name: "+name+" Artist: "+artist + ((album == null) ? "" : (" Album: " + album)));
		return info;
	}

	public String getArtist()
	{
		return artist;
	}

	@Override
	public boolean equals(Object obj)
	{


		if(this == obj)
				return true;


		if(obj == null || obj.getClass()!= this.getClass())
				return false;


		Song s = (Song) obj;
			

		return (s.songInfo() == this.songInfo());
	}
}
