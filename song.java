

import java.util.*;

public class song
{
	//STATE:
	private String name;
	private String artist ;
	private Date dateCreated;
	
	//BEHAVIOR OR METHODS:
	
	//CONSTRUCTORS:
	public song() 
	{
		name = "";
		artist = "";
		dateCreated = new Date();
	}
	
	public song(String n, String a) 
	{
		name = n;
		artist = a;
		dateCreated = new Date();
	}
	
	
	//SETTERS:
	public void setName(String n) 
	{
		name = n;
	}
	
	public void setArtist(String a) 
	{
		artist = a;
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


