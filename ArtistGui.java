import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ArtistGui extends JFrame
{
  //fields
  private JPanel panel;
  private JButton singleButton;
  private JButton albumButton;
  private JButton logoutButton;
  private JTextArea textArea;

  private final ArtistAccount account;
  private final String artistName;

  //constructor

  public ArtistGui(ArtistAccount accountIn)
  {

    //save account
    account = accountIn;
    artistName = account.getUsername();

    //set window title
    setTitle(account.getUsername() + "'s Artist Dashboard");

    //set window close behavior
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //build panel
    buildPanel();

    //add panel to JFrarme content pane
    add(panel);

    //set window size
    setSize(400,150);

    //Display window
    setVisible(true);
  }

  //methods
  private void buildPanel()
  {
    //create buttons
    singleButton = new JButton("Release Single");
    singleButtonListener singleListener = new singleButtonListener();
    singleButton.addActionListener(singleListener);

    albumButton = new JButton("Release Album");
    albumButtonListener albumListener = new albumButtonListener();
    albumButton.addActionListener(albumListener);

    logoutButton = new JButton("Log out");
    logoutButtonListener logoutListener = new logoutButtonListener();
    logoutButton.addActionListener(logoutListener);

    panel = new JPanel();

    //add all components to panel
    panel.add(singleButton);
    panel.add(albumButton);
    panel.add(logoutButton);
  }

  //singleButtonListener
  //allows user to enter a song name and releases that song into database
  private class singleButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      //prompt user for song name
      String name = JOptionPane.showInputDialog("Enter song name");

      //create song
      Song song = new Song(name, artistName);
      //add song to album to create single release
      Song[] single = new Song[] {song};
      Album album = new Album(name, artistName, single);
      //add to artist discography and update database
      try{
        account.addAlbum(album);
        MyAppUtils.saveAccount(account);
      } catch (Exception ex1) {
        ex1.printStackTrace();
      }
    }
  }

  //albumButtonListener
  //takes album name, # songs, and song names, then releases album into database
  private class albumButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      String albumName;
      int numSongs = 0;
      ArrayList<Song> songList = new ArrayList<Song>();
      Boolean canSubmit = true;
      Album album;

      //get album name
      albumName = JOptionPane.showInputDialog("Enter album name");

      //get # of songs
      try
      {
        numSongs = Integer.parseInt(JOptionPane.showInputDialog("Enter number of songs (as an integer)"));
      }
      catch (NumberFormatException a)
      {
        canSubmit = false;
        JOptionPane.showMessageDialog(null,"Failed, please enter an interger");
      }

      if(canSubmit)
      {
        //get name of each song
        for (int i = 0; i < numSongs; i++)
        {
          songList.add(new Song(JOptionPane.showInputDialog("Enter song " + (i + 1) + " name"),artistName,albumName));
        }

        //create album
        Song[] myList = new Song[songList.size()];
        album = new Album(albumName,artistName,(Song[]) songList.toArray(myList));
        //add to artist discography and update database
        try{
          account.addAlbum(album);
          MyAppUtils.saveAccount(account);
        } catch (Exception ex2) {
          ex2.printStackTrace();
        }
      }
    }
  }

  //logoutButtonListener
  //closes artistGui and calls loginGui
  private class logoutButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      new LoginGui();
      dispose();
    }
  }
}
