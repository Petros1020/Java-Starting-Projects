<%-- 
    Document   : allSongs
    Created on : Oct 25, 2018, 12:59:13 PM
    Author     : karag
--%>

<%@page import="model.Song"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Demo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>

        <meta charset="utf-8">
        <meta name="robots" content="noindex, nofollow">

        <title>Filterable Table</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <style type="text/css">
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            td,
            th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            tr:nth-child(even) {
                background-color: #dddddd;
            }
        </style>

        <script type="text/javascript">
            window.alert = function () { };
            var defaultCSS = document.getElementById('bootstrap-css');
            function changeCSS(css) {
                if (css)
                    $('head > link').filter(':first').replaceWith('<link rel="stylesheet" href="' + css + '" type="text/css" />');
                else
                    $('head > link').filter(':first').replaceWith(defaultCSS);
            }
            // $(document).ready(function () {
            //     var iframe_height = parseInt($('html').height());
            //     window.parent.postMessage(iframe_height, 'https://bootsnipp.com');
            // });
        </script>
        <script>
            $(document).ready(function () {
                $('#delete').click(function () {
                    return confirm("Are you sure you want to delete?");
                });
            });
        </script>
    </head>

    <body>
        <div class ="container">
            <h2>Songs Table</h2>
            <p>Search for your song!</p>
            <input id="myInput" type="text" placeholder="Search..">
            <br>
            <a href="index.html">Go back!</a>
            <br>

            <table>
                <thead>
                    <tr class="row">
                        <th class="col-lg-2">Song Title</th>
                        <th class="col-lg-2">Artist</th>
                        <th class="col-lg-2">Album</th>
                        <th class="col-lg-2">Year</th>
                        <th class="col-lg-4">Album Image</th>
                        
                    </tr>
                </thead>
                <tbody id="myTable">
                    <%
                        ArrayList<Song> songlist = (ArrayList<Song>) request.getAttribute("songlist");
                        for (Song song : songlist) {

                    %>
                    <tr class="row">
                        <td class="col-lg-2"><%=song.getSongtitle()%><div> <a href="Alter?id=<%=song.getId()%>" >(alter)</a><a href="Delete?id=<%=song.getId()%>" onclick="return confirm('Are you sure you want to delete?');" >(delete)</a></div></td>
                        <td class="col-lg-2"><%=song.getArtist()%> </td>
                        <td class="col-lg-2"><%=song.getAlbum()%> </td>
                        <td class="col-lg-2"><%=song.getYear()%> </td>
                        <td class="col-lg-3"><img src=<%=song.getImgurl()%> alt="album cover" width="150" height="160"> </td>
                        <td class="col-lg-1"><a href="ShowLyrics?id=<%=song.getId()%>">Show lyrics!</a></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#myInput").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $("#myTable tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });</script>


    </body>

</html>
