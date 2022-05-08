public class account
{
	//STATE:
	private String username;
	private String password;
	
	//CONSTRUCTORS:
	public account() 
	{
		username = "";
		password = "";
	}
	
	public account(String u, String p){
		username = u;
		password = p;
	}
	
	//OTHER METHODS:
	public void deleteAccount() 
	{
		username = "";
		password = "";
	}
	
	//SETTERS:
	public void setUsername(String u) 
	{
		username = u;
	}
	
	public void setPassword(String p) 
	{
		password = p;
	}
	
	//GETTERS:

	public String getUsername() 
	{
		return username;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
}
