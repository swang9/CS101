import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGui extends JFrame
{
  // fields

  private JLabel userLabel; //username label
  private JLabel passLabel; //password label
  private JLabel typeLabel; //account type label
  private JTextField userText; //username text field
  private JPasswordField passText; //password text field
  private JComboBox typeBox; //account type selector
  private JButton loginButton; //login button
  private JButton registerButton; //register button
  private JPanel panel; //panel object

  private final int WINDOW_WIDTH = 400; //window width
  private final int WINDOW_HEIGHT = 350; //window height
  private final String[] typeArray = {"Listener","Artist"}; //account type list

  //constructor

  public LoginGui()
  {
    //set window title
    setTitle("Login");

    //set size of window
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

    //set window close behavior
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //build panel to add components
    buildPanel();

    //add panel to JFrame
    add(panel);

    //display window
    pack();
    setVisible(true);
  }


  //methods

  private void buildPanel()
  {
    //create labels
    userLabel = new JLabel("User");
    passLabel = new JLabel("Password");
    typeLabel = new JLabel("Account Type");

    //create text fields 15 characters in length
    userText = new JTextField(15);
    passText = new JPasswordField(15);

    //create switch
    typeBox = new JComboBox(typeArray);

    //create buttons
    loginButton = new JButton("Log in");     //create & caption button
    LoginButtonListener logListener = new LoginButtonListener();     //create button listener
    loginButton.addActionListener(logListener);    //add listener to button

    registerButton = new JButton("Register new account");
    RegisterButtonListener regListener = new RegisterButtonListener();
    registerButton.addActionListener(regListener);

    //create panel
    panel = new JPanel();

    //add all components to panel
    panel.add(userLabel);
    panel.add(userText);
    panel.add(passLabel);
    panel.add(passText);
    panel.add(typeLabel);
    panel.add(typeBox);
    panel.add(loginButton);
    panel.add(registerButton);
  }

  //loginbuttonListener is an action listener for the login button
  //checks if username and password for account type match an existing account
  //then calls corresponding gui
  private class LoginButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e) //executes when button is clicked
    {
      // fields
      String userIn;
      String passIn;
      String type;

      /***    NEEDS IMPLEMENTED ACCOUNT LOGIN SYSTEM     ***/
      //   -  check if account username, pass and type matches existing account
      //   -  if so, send to listener or artist gui
      //   -  if not, do nothing
    }
  }

  //registerbuttonlistener is an action listener for the login button
  //checks if user name is taken. if not, registers new user and password
  //under the given user type and calls corresponding gui
  private class RegisterButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      //fields
      String userIn;
      String passIn;
      String type;

      /***    NEEDS IMPLEMENTED ACCOUNT REGISTRATION SYSTEM     ***/
      //    -  check if account user matches existing account
      //    -  if not, create new user with given info and send to
      //       corresponding gui
      //    -  if account user is taken, do nothing
    }
  }

  public static void main(String[] args)
  {
    LoginGui myGui = new LoginGui();
  }
}
