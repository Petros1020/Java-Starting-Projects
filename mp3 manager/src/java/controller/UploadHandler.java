/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Helper.SongDAO;
import Helper.databaseCommands;
import com.beaglebuddy.mp3.MP3;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Song;
import org.json.JSONException;

/**
 *
 * @author karag
 */
@WebServlet(name = "UploadHandler", urlPatterns = {"/UploadHandler"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 20, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadHandler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        final Part filepart = request.getPart("mp3file");
        final String filename = filepart.getSubmittedFileName();
        InputStream inputToDatabase=filepart.getInputStream();
        if (filename.endsWith("mp3")) {

            String path = "C:\\Users\\karag\\OneDrive\\My PC\\fileupload\\";
            filepart.write(path + filename);
            MP3 mp3 = new MP3(path + filename);

            Song song = new Song(mp3.getTitle(), mp3.getBand(), mp3.getAlbum(), mp3.getYear(), filename, inputToDatabase);
            String url=null;
            try {
                url=SongDAO.getUrl(song);
            } catch (JSONException ex) {
                Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }catch(IOException ex){
                ex.printStackTrace();
            }
            song.setImgurl(url);
            
            try {
                song.setLyrics(SongDAO.getLyricsJson(mp3));
            } catch (JSONException ex) {
                Logger.getLogger(UploadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            databaseCommands.insertIntoDatabase(song);
            RequestDispatcher rd = request.getRequestDispatcher("/beforeViewServlet");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/index.html");
            try (PrintWriter out = response.getWriter()) {
                out.append("This is not an mp3 file!");
                rd.include(request, response);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
