import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//    String name = JOptionPane.showInputDialog(f,"Enter Name");

public class UserGui extends JFrame
{
  // fields

  private String username; //stores username
  private JComboBox playlistBox; //playlsit selector
  private JComboBox playlistSongBox; //song selector within playlist
  private JButton playlistDisplayButton; //button to display playlistsongbox
  private JButton playlistDeleteButton; //button to delete playlist
  private JButton playlistCreateButton; //button to create playlist
  private JButton playlistSongDeleteButton; // button to delete song from playlist
  private JButton playlistSongAddButton; //button to open song search window to add to playlist
  private JButton logoutButton; //button to return to login screen
  private playlist[] playlistArray; //arraylist of playlists
  private song[] songArray; //arraylist of songs

  private final int WINDOW_WIDTH = 400; //window width
  private final int WINDOW_HEIGHT = 400; //window height

  //constructor

  public UserGui(String user)
  {
    //set window title
    setTitle(user + "'s Libray'");

    //set size of window
    setsize(WINDOW_WIDTH,WINDOW_HEIGHT);

    //set window close behavior
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //build panel
    buildPanel(user);

    //add panel to JFrarme content pane
    add(panel);

    //Display window
    pack();
    setVisible(true);

    //save username
    username = new String(user);
  }

  //methods
  private void buildPanel(String user)
  {
    //create Buttons
    playlistDisplayButton = new JButton("Select playlist");     //create & caption button
    playlistDisplayButtonListener playlistDisplayListener = new playlistDisplayButtonListener();     //create button listener
    playlistDisplayButton.addActionListener(playlistDisplayListener);    //add listener to button

    playlistDeleteButton = new JButton("Delete playlist");
    playlistDeleteButtonListener playlistDeleteListener = new playlistDeleteButtonListener();
    playlistDeteleButton.addActionListener(playlistDeleteListener);

    playlistCreateButton = new JButton("Add new playlist");
    playlistCreateButtonListener playlistCreateListener = new playlistCreateButtonListener();
    playlistCreateButton.addActionListener(playlistDeleteListener);

    playlistSongDeleteButton = new JButton("Remove from playlist");
    playlistSongDeleteButtonListener playlistSongDeleteListener = new playlistSongDeleteButtonListener();
    playlistSongDeleteButton.addActionListener(playlistSongDeleteListener);

    playlistSongAddButton = new JButton("Add song to playlist");
    playlistSongAddButtonListener playlistSongAddListener = new playlistSongAddButtonListener();
    playlistSongAddButton.addActionListener(playlistSongAddListener);

    logoutButton = new JButton("Log out");
    logoutButtonListener logoutListener = new logoutButtonListener();
    logoutButton.addActionListener(logoutListener);

    //create selectors
    UserAccount account = new UserAccount();
    playlistArray = account.getPlaylists();
    playlistBox = new JComboBox(playlistArray);

    songArray = playlistArray[0].getSongList();
    playlistSongBox = new JComboBox(songArray);

    //create panel
    panel = new JPanel();

    //add all components to panel
    panel.add(playlistBox);
    panel.add(playlistDisplayButton);
    panel.add(playlistSongAddButton);
    panel.add(playlistDeleteButton);
    panel.add(playlistCreateButton);
    panel.add(playlistSongBox);
    panel.add(playlistSongAddButton);
    panel.add(playlistSongDeleteButton);
    panel.add(logoutButton);
  }

  //playlistDisplayButtonListener
  //on button press, updates playlsitSongBox to display songs from playlist
  private class playlistDisplayButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e) //executes when button is clicked
    {
      //fields
      playlist play = playlistBox.getItemAt(playlistBox.getSelectedIndex());
      song[] songArray1 = play.getSongList();

      playlistSongBox = new JComboBox(songArray1); //updates song display box
    }
  }

  //playlistDeleteButtonListener
  //on button press, deletes selected playlist
  private class playlistDeleteButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      //fields
      playlist play = playlistBox.getItemAt(playlistBox.getSelectedIndex());

      play.deleteList();
    }
  }

  //playlistCreateButtonListener
  //on button press, prompts user for playlist name and creates new playlist w/name
  private class playlistCreateButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      
    }
  }
}
