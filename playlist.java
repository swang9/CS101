import java.util.*;
import java.util.ArrayList;

public class playlist
{
	//STATE:
	private String name;
	private Date dateCreated;
	private ArrayList<String> songList = new ArrayList<String>();
	
	//CONSTRUCTORS:
	public playlist() 
	{
		name = "";
		dateCreated = new Date();
	}
	
	public playlist(String n) 
	{
		name = n;
		dateCreated = new Date();
	}
	
	//OTHER METHODS
	public void addSong(String s) 
	{
		songList.add(s);
	}
	
	public void removeSong(String s) 
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
	
	public ArrayList<String> getList() 
	{
		return songList;
	}
	
	public Date getDate() 
	{
		return dateCreated;
	}
	
}
