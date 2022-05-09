import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchGui extends JFrame
{
  //fields

  private JLabel searchLabel; //search bar label
  private JTextField searchText; //search text field
  private JTextArea infoText; //info text field
  private JButton searchSongButton; //search song button
  private JButton searchAlbumButton; //search album button
  private JButton addButton; //add song button
  private playlist playlist; //playlist

  private final int WINDOW_WIDTH = 400;
  private final int WINDOW_HEIGHT = 350;

  //constructor

  public SearchGui(playlist play)
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
    pack();
    setVisible(true);

    //save playlist
    playlist = play;
  }

  //methods

  private void buildPanel();
  {
    //create labels
    searchLabel = new JLabel("Enter a song or album");

    //create text fields
    searchText = new JTextField(20);

    //create text areas
    infoText = new JTextArea(20,25);

    //create buttons
    searchSongButton = new JButton("Search song");
    searchSongButtonListener searchSongListener = new searchSongButtonListener();
    searchSongButton.addActionListener(searchSongListener);

    searchAlbumButton = new JButton("Search album");
    searchAlbumButtonListener searchAlbumListener = new searchAlbumButtonListener();
    searchAlbumButton.addActionListener(searchAlbumListener);

    addButton = new JButton("Add song");
    addButtonListener addListener = new addButtonListener();
    addButton.addActionListener(addListener);

    //create panel
    panel = new JPanel();

    //add components to panel
    panel.add(searchLabel);
    panel.add(searchText);
    panel.add(searchSongButton);
    panel.add(searchAlbumButton);
    panel.add(infoText);
    panel.add(addButton);
  }

  //searchSongButtonListener
  //finds song and displays info
  private class searchSongButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e) //executes when button is pressed
    {
      // if (myUtility.checkSong(String songname)) - need to add
      song s; // = myUtility.pullSong(String songname) - need to add

      //print song info
      infoText.setText(""); //clears info area

      infoText.append("Song: ");
      infoText.append(s.getName());
      infoText.append(System.lineSeparator());

      infoText.append("Artist: ");
      infoText.append(s.getArtist());
      infoText.append(System.lineSeparator());

      infoText.append(s.getDateCreate().toString());

      //show add song button
    }
  }

  //searchAlbumButtonListener
  //finds album and displays songs
  private class searchAlbumButtonListener implements ActionListener
  {
    public void actionPerformed (ActionEvent e)
    {
      // if (myUtility.checkAlbum(String albumName)) - need to add
      album a; // = myUtility.pullAlbum(String albumname) - need to add
      song[] songs;

      //print album info
      songs = a.getAlbumSongs();
      infoText.setText(""); //clears info area
      infoText.append("Album: " + a.getName());
      infoText.append(System.lineSeparator());
      infoText.append("Artist: " + a.getArtist());
      for (i = 0; i < songs.length; i++)
      {
        infoText.append(song[i].getName());
        infoText.append(System.lineSeparator());
      }

      //remove add song button

    }
  }

}
