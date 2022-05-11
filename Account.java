public class Account implements java.io.Serializable
{
	//STATE:
	private String username;
	private String password;

	//CONSTRUCTORS:
	public Account()
	{
		username = "";
		password = "";
	}

	public Account(String u, String p)
	{
		username = u;
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
