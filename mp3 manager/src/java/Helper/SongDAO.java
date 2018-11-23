/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import static Helper.JsonReader.readJsonFromUrl;
import com.beaglebuddy.mp3.MP3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import model.Song;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author karag
 */
public class SongDAO {

    public static JSONObject getLyricsJson(MP3 mp3) throws IOException, JSONException {

        String title = mp3.getTitle();
        String band = mp3.getBand();

        if (title == null || band == null) {
            return null;
        }

        title = title.replace(" ", "+");
        band = band.replace(" ", "+");

        JSONObject json = readJsonFromUrl("https://api.lyrics.ovh/v1/" + band + "/" + title);
        return json;
       // return (String) json.get("lyrics");
    }

    public static String getUrl(Song song) throws IOException, JSONException {
         String artist = song.getArtist();
         String album = song.getAlbum();

        if (artist == null || album == null) {
            return null;
        }
        
        artist = artist.replace(" ", "+");
        album = album.replace(" ", "+");
        
        String url = "https://album-art-o2s77e5c7ryz.runkit.sh/?search=" + artist + "&album=" + album;
        String url1 = "https://album-art-o2s77e5c7ryz.runkit.sh/?search=eminem&album=encore";
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        String returnUrl = null;
        while ((inputLine = in.readLine()) != null) {
            returnUrl = inputLine;
        }
        in.close();
        return returnUrl;
    }



}
