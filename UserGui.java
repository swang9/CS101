import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserGui extends JFrame
{
  // fields

  private JComboBox playlistBox; //playlsit selector
  private JComboBox playlistSongBox; //song selector within playlist
  private JButton playlistDisplayButton; //button to display playlistSongBox
  private JButton playlistDeleteButton; //button to delete playlist
  private JButton playlistCreateButton; //button to create playlist
  private JButton playlistSongDeleteButton; // button to delete song from playlist
  private JButton playlistSongAddButton; //button to open song search window to add to playlist
  private JButton logoutButton; //button to return to login screen
  private Playlist[] playlistArray; //arraylist of playlists
  private Song[] songArray; //arraylist of songs
  private UserAccount account;
  private JPanel panel;

  private final int WINDOW_WIDTH = 400; //window width
  private final int WINDOW_HEIGHT = 200; //window height

  //constructor

  public UserGui(UserAccount accountIn)
  {
    //save account
    account = accountIn;

    //set window title
    setTitle(account.getUsername() + "'s Libray");

    //set size of window
    setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

    //set window close behavior
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //build panel
    buildPanel(account);

    //add panel to JFrarme content pane
    add(panel);

    //Display window
    setVisible(true);
  }

  //methods
  private void buildPanel(UserAccount acc)
  {
    //create Buttons
    playlistDisplayButton = new JButton("Select playlist");     //create & caption button
    playlistDisplayButtonListener playlistDisplayListener = new playlistDisplayButtonListener();     //create button listener
    playlistDisplayButton.addActionListener(playlistDisplayListener);    //add listener to button

    playlistDeleteButton = new JButton("Delete playlist");
    playlistDeleteButtonListener playlistDeleteListener = new playlistDeleteButtonListener();
    playlistDeleteButton.addActionListener(playlistDeleteListener);

    playlistCreateButton = new JButton("Add new playlist");
    playlistCreateButtonListener playlistCreateListener = new playlistCreateButtonListener();
    playlistCreateButton.addActionListener(playlistCreateListener);

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
    playlistArray = acc.getPlaylists();
    playlistBox = new JComboBox(account.getPlaylists());

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
      Playlist play = (Playlist) playlistBox.getItemAt(playlistBox.getSelectedIndex());
      Song[] songArray1 = play.getSongList();

      //update song display box
      playlistSongBox.setModel(new DefaultComboBoxModel(songArray1));
    }
  }

  //playlistDeleteButtonListener
  //on button press, deletes selected playlist
  private class playlistDeleteButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      //fields
      Playlist play = (Playlist) playlistBox.getItemAt(playlistBox.getSelectedIndex());

      try{
        account.removePlaylist(play);
        MyAppUtils.saveAccount(account);
      } catch (Exception ex1) {
        ex1.printStackTrace();
      }

      //update playlist selector
      Playlist[] playlistArray1 = account.getPlaylists();
      playlistBox.setModel(new DefaultComboBoxModel(playlistArray1));
    }
  }

  //playlistCreateButtonListener
  //on button press, prompts user for playlist name and creates new playlist w/name
  private class playlistCreateButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      //prompt user for playlist name
      String name = JOptionPane.showInputDialog("Enter name");

      //create playlist & save to database
      try{
        account.addPlaylist(name);
        MyAppUtils.saveAccount(account);
      } catch (Exception ex2)
      {
        ex2.printStackTrace();
      }

      //update playlist selector
      Playlist[] playlistArray1 = account.getPlaylists();
      playlistBox.setModel(new DefaultComboBoxModel(playlistArray1));
    }
  }

  //playlistSongDeleteButtonListener
  //on button press, deletes selected song from playlist
  private class playlistSongDeleteButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      //fields
      Song song = (Song) playlistSongBox.getItemAt(playlistSongBox.getSelectedIndex());
      Playlist play = (Playlist) playlistBox.getItemAt(playlistBox.getSelectedIndex());

      //remove song and update database
      try{
        play.removeSong(song);
        MyAppUtils.saveAccount(account);
      } catch (Exception ex3) {
        ex3.printStackTrace();
      }

      //update song selctor
      Song[] songArray1 = play.getSongList();
      playlistSongBox.setModel(new DefaultComboBoxModel(songArray1));
    }
  }

  //playlistSongAddButtonListener
  //calls search gui to add song
  private class playlistSongAddButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      //pass playlist to seach gui
      SearchGui search = new SearchGui((Playlist) playlistBox.getItemAt(playlistBox.getSelectedIndex()),account);

      //update song selector
      Playlist play = (Playlist) playlistBox.getItemAt(playlistBox.getSelectedIndex());
      Song[] songArray1 = play.getSongList();

      //update song selector
      playlistSongBox.setModel(new DefaultComboBoxModel(songArray1));
    }
  }

  //logoutButtonListener
  //closes userGui and calls loginGui
  private class logoutButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      new LoginGui();
      dispose();
    }
  }
}
