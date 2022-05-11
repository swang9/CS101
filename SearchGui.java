import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SearchGui extends JFrame
{
  //fields

  private JLabel searchLabel; //search bar label
  private JTextField searchText; //search text field
  private JTextArea infoText; //info text field
  private JButton searchButton; //search button
  private JButton addButton; //add song button
  private JButton exitButton; //exits search window
  private JComboBox songSelect; //song selector
  private JPanel panel;

  private final Playlist playlist; //playlist
  private final UserAccount account; //user account

  private final int WINDOW_WIDTH = 600;
  private final int WINDOW_HEIGHT = 300;

  //constructor

  public SearchGui(Playlist play,UserAccount accountIn)
  {
    //set window title
    setTitle("Song search");

    //set size of window
    setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

    //set window close behavior
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //build panel to add components
    buildPanel();

    //add panel to JFrame
    add(panel);

    //display window
    setVisible(true);

    //save playlist & user
    playlist = play;
    account = accountIn;
  }

  //methods

  private void buildPanel()
  {
    //create labels
    searchLabel = new JLabel("Enter a song, album or artist");

    //create text field
    searchText = new JTextField(20);

    //create text area
    infoText = new JTextArea(15,30);

    //create buttons
    searchButton = new JButton("Search");
    searchButtonListener searchSongListener = new searchButtonListener();
    searchButton.addActionListener(searchSongListener);

    addButton = new JButton("Add song");
    addButtonListener addListener = new addButtonListener();
    addButton.addActionListener(addListener);

    exitButton = new JButton("Exit search");
    exitButtonListener exitListener = new exitButtonListener();
    exitButton.addActionListener(exitListener);

    songSelect = new JComboBox();

    //create panel
    panel = new JPanel();

    //add components to panel
    panel.add(searchLabel);
    panel.add(searchText);
    panel.add(searchButton);
    panel.add(exitButton);
    panel.add(infoText);
    panel.add(songSelect);
    panel.add(addButton);
    songSelect.setVisible(false);
    addButton.setVisible(false);
  }

  //searchButtonListener
  //finds song and displays info
  private class searchButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e) //executes when button is pressed
    {
      String search;
      Song[] songList;
      String info;

      //get search bar text
      search = searchText.getText();

      //System.out.println("searchbutton");

      // call search utility, save songs
      try{
        songList = MyAppUtils.findSongs(search,search,search);
      } catch (Exception ex1){
        ex1.printStackTrace();
        songList = new Song[0];
      }

      if (songList.length >= 1)
      {
        int size = 0;
        info = "";
        for (int i = 0; i < songList.length; i++)
        {
          info = info.concat((i+1) + "  " + songList[i].songInfo() + "\n");
          if (songList[i].songInfo().length() > size)
          {
            size = songList[i].songInfo().length();
          }
        }

        //update info box & set box size
        //System.out.println(info);
        infoText.setSize(songList.length,size);
        infoText.setText(info);

        //show add song button
        //show song selector
        songSelect.setModel(new DefaultComboBoxModel(songList));
        songSelect.setVisible(true);
        addButton.setVisible(true);
      }
    }
  }

  //addButtonListener
  //adds song in search text
  private class addButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      Song s = (Song) songSelect.getItemAt(songSelect.getSelectedIndex());
      //add song to playlist
      playlist.addSong(s);
      try{
        MyAppUtils.saveAccount(account);
      } catch (Exception ex1)
      {
        ex1.printStackTrace();
      }
    }
  }

  //exitButtonListener
  //closes search window
  private class exitButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      dispose();
    }
  }
}
