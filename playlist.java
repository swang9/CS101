import java.util.*;

//CHANGE TO ARRAYLIST

public class playlist
{
	//STATE:
	private String name;
	private Date dateCreated;
	private String [] songList;
	private String[] newArr = null;
	
	//CONSTRUCTORS:
	public playlist() 
	{
		name = "";
		songList = new String [0];
		dateCreated = new Date();
	}
	
	public playlist(String n, int a) 
	{
		name = n;
		songList = new String[a];
		dateCreated = new Date();
	}
	
	//OTHER METHODS
	int head = 0;
	public void addSong(String s) 
	{
		songList[head] = s;
		head++;
	}
	
	public void removeSong(String s) 
	{
		for (int i = 0; i < songList.length-1; i++) 
		{
            if(songList[i].equals(s))
            {
                newArr = new String[songList.length - 1];
                for(int index = 0; index < i; index++)
                {
                    newArr[index] = songList[index];
                }
                
                for(int j = i; j < songList.length - 1; j++)
                {
                    newArr[j] = songList[j+1];
                }
                break;
            }
        } 
		songList = newArr;
	
	}
	
	public void deleteList()
	{
		songList = new String [0];
	}
	
	
	//GETTERS:

	public String getName() 
	{
		return name;
	}
	
	public String getList() 
	{
		return Arrays.toString(songList);
	}
	
	public Date getDate() 
	{
		return dateCreated;
	}
	
}

