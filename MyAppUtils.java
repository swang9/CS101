import java.util.*;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class MyAppUtils {
  private static boolean dataFolderInitiated;

  private static final String MY_APP_DATA_ROOT = "Data";

  private static void initDataFolder() throws Exception {
    if (dataFolderInitiated == false) {
      Path artistDirPath = Paths.get(MY_APP_DATA_ROOT + "/artist");
      Path userDirPath = Paths.get(MY_APP_DATA_ROOT + "/user");
      Files.createDirectories(artistDirPath);
      Files.createDirectories(userDirPath);
      dataFolderInitiated = true;
    }
  }

  public static void saveAccount(Account acct) throws Exception {
    initDataFolder();

    // delete the account (if exists) first
    deleteAccount(acct);

    String acctRootPath = null;
    if (acct instanceof ArtistAccount) {
      acctRootPath = MY_APP_DATA_ROOT + "/artist/" + acct.getUsername();
    } else if (acct instanceof UserAccount) {
      acctRootPath = MY_APP_DATA_ROOT + "/user/" + acct.getUsername();
    }

    FileOutputStream fos = new FileOutputStream(acctRootPath);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(acct);
    oos.flush();
    oos.close();
  }

  public static void deleteAccount(Account acct) throws Exception {
    initDataFolder();

    String acctRootPath = null;
    if (acct instanceof ArtistAccount) {
      acctRootPath = MY_APP_DATA_ROOT + "/artist/" + acct.getUsername();
    } else if (acct instanceof UserAccount) {
      acctRootPath = MY_APP_DATA_ROOT + "/user/" + acct.getUsername();
    }

    if (Files.exists(Paths.get(acctRootPath))) {
      Files.delete(Paths.get(acctRootPath));
    }
  }

  public static Account getAccount(String username) throws Exception {
    initDataFolder();

    Account account = null;
    ObjectInputStream ois = null;

    // try artist account first
    String acctRootPath = MY_APP_DATA_ROOT + "/artist/" + username;
    if (Files.exists(Paths.get(acctRootPath))) {
      ois = new ObjectInputStream(new FileInputStream(acctRootPath));
      account = (Account) ois.readObject();
    }

    if (account == null) {
      // try user account
      acctRootPath = MY_APP_DATA_ROOT + "/user/" + username;
      if (Files.exists(Paths.get(acctRootPath))) {
        ois = new ObjectInputStream(new FileInputStream(acctRootPath));
        account = (Account) ois.readObject();
      }
    }

    if (ois != null) {
      ois.close();
    }

    return account;
  }

  public static Song[] findSongs(String artistName, String songName, String albumName) throws Exception {
    initDataFolder();

    //System.out.println(artistName+","+songName+","+albumName);

    // one search criteria is required
    if (artistName == null && songName == null && albumName == null) {
      return null;
    }

    // get all the artist accounts
    List<ArtistAccount> artistAccounts = new ArrayList<>();
    File appDataRootDir = new File(MY_APP_DATA_ROOT+"/artist/"); //**
    for (File f : appDataRootDir.listFiles()) {
      // each "f" should be an account file
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
      Account account = (Account) ois.readObject();
      if (account instanceof ArtistAccount) {
        artistAccounts.add((ArtistAccount)account);
      }
    }
    if (artistAccounts == null) {
      // no songs available
      return null;
    }

    List<Song> songList = new ArrayList<>();

    // go through the songs of each artist
    for (ArtistAccount acct : artistAccounts) {
      if (acct.getAlbums() != null) {
        for (Album album : acct.getAlbums()) {
          if (album.getAlbumSongs() != null) {
            for (Song song : album.getAlbumSongs()) {
              //  System.out.println(song.songInfo());
              if (artistName != null) {
                // artist name has to match
                if (artistName.equalsIgnoreCase(song.getArtist())) {
                  songList.add(song);
                  continue;  // continue to check the next song
                }
              }
              if (songName != null) {
                // song name has to match
                if (songName.equalsIgnoreCase(song.getName())) {
                  songList.add(song);
                  continue;  // continue to check the next song
                }
              }
              if (albumName != null) {
                // album name has to match
                if (albumName.equalsIgnoreCase(album.getName())) {
                  songList.add(song);
                  continue;  // continue to check the next song
                }
              }
            }
          }
        }
      }
    }

    return songList.toArray(new Song[songList.size()]);
  }

}
