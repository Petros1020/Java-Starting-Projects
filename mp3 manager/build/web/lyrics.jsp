<%-- 
    Document   : lyrics
    Created on : Oct 28, 2018, 5:43:00 PM
    Author     : karag
--%>

<%@page import="model.Song"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  String x = (String) request.getAttribute("lyrics");
            if (x==null) { %>

            <jsp:forward page="errorLyrics.html"></jsp:forward>
            
        <%  } else {%>
        <%Song song = (Song) request.getAttribute("song");%>
        <h1><%=song.getSongtitle()%> - <%=song.getArtist()%></h1>

        <pre><%=request.getAttribute("lyrics")%></pre>
        <%}%>
    </body>
</html>
