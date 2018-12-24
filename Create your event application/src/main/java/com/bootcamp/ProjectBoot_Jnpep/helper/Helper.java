/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.helper;

import com.bootcamp.ProjectBoot_Jnpep.models.ImageName;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ImageNameRepository;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author pitpr
 */
@Controller
public class Helper {
    
 

    public void RandomStringGenerator() {

        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        System.out.println(generatedString);
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public static String saveProfileImage(MultipartFile fileUpload,String alternateFileNameString) {
        String filename = fileUpload.getOriginalFilename();

        if (!filename.equals("")) {

            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://51.15.115.175:8080/JnpepImages/Upload");
                File file = Helper.convert(fileUpload);
                FileBody uploadFilePart = new FileBody(file);
                StringBody uploadFileDirectory = new StringBody("profileimages/");
                
                
                
                StringBody alternateFileName = new StringBody(alternateFileNameString);
                MultipartEntity reqEntity = new MultipartEntity();
                
                reqEntity.addPart("file", uploadFilePart);
                reqEntity.addPart("filecategory", uploadFileDirectory);
                reqEntity.addPart("alternatefilename", alternateFileName);
                
                httpPost.setEntity(reqEntity);
                HttpResponse response = httpclient.execute(httpPost);
                String responseXml = EntityUtils.toString(response.getEntity());
                EntityUtils.consume(response.getEntity());
                System.out.println(responseXml);
                return ("http://51.15.115.175:8080/jnpepfiles/profileimages/" + alternateFileNameString);
            } catch (IOException ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "There was a problem with the image";
    }
}
