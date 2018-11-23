/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;
import org.json.JSONObject;

/**
 *
 * @author karag
 */
public class Song {
    private int id;
    private String songtitle;
    private String artist;
    private String album;
    private int year;
    private String songfulltitle;
    private String imgurl;
    private InputStream songfile;
    private JSONObject lyrics;
    
    public Song(){}

    public Song(int id, String songtitle, String artist, String album, int year, String imgurl) {
        this.id = id;
        this.songtitle = songtitle;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.imgurl = imgurl;
    }

    
    
    public Song(String songtitle, String artist, String album, int year, String songfulltitle, InputStream songfile) {
        this.songtitle = songtitle;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.songfulltitle = songfulltitle;
        this.songfile = songfile;
    }
    
        public Song(String songtitle, String artist, String album, int year, String songfulltitle, String imgurl, InputStream songfile, int id) {
        this.songtitle = songtitle;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.songfulltitle = songfulltitle;
        this.imgurl = imgurl;
        this.songfile = songfile;
        this.id = id;
    }

    public String getSongtitle() {
        return songtitle;
    }

    public void setSongtitle(String songtitle) {
        this.songtitle = songtitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSongfulltitle() {
        return songfulltitle;
    }

    public void setSongfulltitle(String songfulltitle) {
        this.songfulltitle = songfulltitle;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public InputStream getSongfile() {
        return songfile;
    }

    public void setSongfile(InputStream songfile) {
        this.songfile = songfile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONObject getLyrics() {
        return lyrics;
    }

    public void setLyrics(JSONObject lyrics) {
        this.lyrics = lyrics;
    }


    
    
}
