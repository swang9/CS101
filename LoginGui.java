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

  private final int WINDOW_WIDTH = 260; //window width
  private final int WINDOW_HEIGHT = 170; //window height
  private final String[] typeArray = {"User","Artist"}; //account type list

  //constructorF

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
      Account acc = null;

      userIn = userText.getText();
      passIn = passText.getText();

      //make sure there is input
      if(userIn != null)
      {
        //load account info from database
        try
        {
          acc = MyAppUtils.getAccount(userIn);
        } catch (Exception err)
        {
          err.printStackTrace();
        }

        //continue if account exists
        if (acc != null)
        {
          //System.out.println("ifacc");
          //check if password matches, then load gui corresponding to account type
          if (acc.getPassword().equals(passIn))
          {
            //System.out.println(acc.getType());
            if (acc instanceof ArtistAccount)
            {
              new ArtistGui((ArtistAccount) acc);
              dispose();
            }
            if (acc instanceof UserAccount)
            {
              //System.out.println("ifuser");
              new UserGui((UserAccount) acc);
              dispose();
            }
          }
          else{
            JOptionPane.showMessageDialog(null,"Password is incorrect");
          }
        }
        else{
          JOptionPane.showMessageDialog(null,"Username does not exist");
        }
      }
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
      Account accCheck;

      userIn = userText.getText();
      passIn = passText.getText();
      type = (String) typeBox.getItemAt(typeBox.getSelectedIndex());

      //make sure there is input
      if(userIn != null)
      {
        //load account info from database
        try
        {
          accCheck = MyAppUtils.getAccount(userIn);
        } catch (Exception ex1)
        {
          accCheck = null;
          ex1.printStackTrace();
        }

        //continue if account does not exist
        if (accCheck == null)
        {
          //create and save account, and call corresponding gui
          if (type == "Artist")
          {
            ArtistAccount accRegister = new ArtistAccount(userIn,passIn);

            try
            {
              MyAppUtils.saveAccount(accRegister);
              new ArtistGui(accRegister);
              dispose();
            } catch (Exception ex2)
            {
              ex2.printStackTrace();
            }
          }
          else
          {
            UserAccount accRegister = new UserAccount(userIn,passIn);
            try
            {
              MyAppUtils.saveAccount(accRegister);
              new UserGui(accRegister);
              dispose();
            } catch (Exception ex3)
            {
              ex3.printStackTrace();
            }
          }
        }
        else{
          JOptionPane.showMessageDialog(null,"Username is taken");
        }
      }
    }
  }

  public static void main(String[] args)
  {
    LoginGui myGui = new LoginGui();
  }
}
