/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import controller.UploadHandler;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.JSONParser;
import model.Song;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author karag
 */
public class databaseCommands {

    public static void insertIntoDatabase(Song song) {
        Connection conn = DBconnection.getConnection();
        String sql = "INSERT INTO mp3library.songs(song_full_title, file_song, song_title, album, year, artist, file_img,lyrics) VALUES (?,?,?,?,?,?,?,?);";
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, song.getSongfulltitle());
            pstm.setBlob(2, song.getSongfile());
            pstm.setString(3, song.getSongtitle());
            pstm.setString(4, song.getAlbum());
            pstm.setInt(5, song.getYear());
            pstm.setString(6, song.getArtist());
            pstm.setString(7, song.getImgurl());
            if (song.getLyrics() == null) {
                pstm.setObject(8, null);
            } else {
                pstm.setObject(8, song.getLyrics().toString());
            }
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ArrayList<Song> getSongsFromDB() {
        ArrayList<Song> songlist = new ArrayList<Song>();
        Connection conn = DBconnection.getConnection();
        String sql = "SELECT * FROM mp3library.songs;";
        PreparedStatement pstm = null;
        ResultSet rst = null;

        try {
            pstm = conn.prepareStatement(sql);
            rst = pstm.executeQuery();

            while (rst.next()) {
                Song song = new Song(rst.getString("song_title"), rst.getString("artist"), rst.getString("album"), rst.getInt("year"), rst.getString("song_full_title"), rst.getString("file_img"), rst.getBlob("file_song").getBinaryStream(), rst.getInt("id"));
                songlist.add(song);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
                pstm.close();
                rst.close();
            } catch (SQLException ex) {
                Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return songlist;
    }

    public static Song getSongById(int id) {
        Song song = null;

        Connection conn = DBconnection.getConnection();
        String sql = "SELECT * FROM mp3library.songs where id=?;";
        PreparedStatement pstm = null;
        ResultSet rst = null;

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if (rst.next()) {
                song = new Song(rst.getString("song_title"), rst.getString("artist"), rst.getString("album"), rst.getInt("year"), rst.getString("song_full_title"), rst.getString("file_img"), rst.getBlob("file_song").getBinaryStream(), rst.getInt("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
                pstm.close();
                rst.close();
            } catch (SQLException ex) {
                Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return song;
    }

    public static void updateDB(Song song) {
        Connection conn = DBconnection.getConnection();
        String sql = "UPDATE mp3library.songs SET song_title=?, artist=?, album=?, year=?, file_img=? WHERE id=?;";
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, song.getSongtitle());
            pstm.setString(2, song.getArtist());
            pstm.setString(3, song.getAlbum());
            pstm.setInt(4, song.getYear());
            pstm.setString(5, song.getImgurl());
            pstm.setInt(6, song.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void DeleteFromDB(int id) {
        Connection conn = DBconnection.getConnection();
        String sql = "DELETE FROM mp3library.songs WHERE id=?;";
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static JSONObject getSongLyricsDB(int id) {
        JSONObject json = null;
        Connection conn = DBconnection.getConnection();
        String sql = "SELECT * FROM mp3library.songs where id=?;";
        PreparedStatement pstm = null;
        ResultSet rst = null;

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if (rst.next()) {
                try {
                    json = new JSONObject((String) rst.getObject("lyrics"));

                } catch (JSONException ex) {
                    return null;
                }catch(NullPointerException ex){
                    return null;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
                pstm.close();
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return json;
    }
}
