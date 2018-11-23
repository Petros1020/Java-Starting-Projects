<%-- 
    Document   : alter
    Created on : Oct 26, 2018, 10:45:38 AM
    Author     : karag
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Update">
            <div>Song title:  <input type="text" name="songtitle" value="${song.songtitle}"></div>
            <div>Artist:  <input type="text" name="artist" value="${song.artist}"></div>
            <div>Album:  <input type="text" name="album" value="${song.album}"></div>
            <div>Year:  <input type="text" name="year" value="${song.year}"></div>
            <div>Image URL:  <input type="text" name="imgurl" value="${song.imgurl}"></div>
            <input type="hidden" name="id" value="${song.id}">
            <button type="input">Save</button>
        </form>
    </body>
</html>
