<%-- 
    Document   : testleaflet
    Created on : Dec 17, 2018, 8:03:51 PM
    Author     : karag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="SpringForm"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> 
<html> 
    <head> 
        <title></title> 
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.0/dist/leaflet.css" />
        <link rel="stylesheet" href="<c:url value="/css/testleaflet-search.css" />" />
        <link rel="stylesheet" href="<c:url value="/css/teststyle.css" />" />
        <style>
            #jsonresp {
                width:10em;
                min-height:10.5em;
                margin-left:1em;
                padding:.25em;
                overflow-y:scroll;
                float:left;
                background:#eee;
                border:1px solid #aaa;
                box-shadow: 2px 2px 6px #999;
                color:#666;
            }
        </style>
    </head>

    <body>
        <div id="map"></div>
        <input type="hidden" name="lat" id="latt" value="" required/> 
        <input type="hidden" name="longg" id="longg" value="" required/>


        <script src="https://unpkg.com/leaflet@1.3.0/dist/leaflet.js"></script>
        <script src="<c:url value="/js/testleaflet-search.js" />"></script>
        
        <script>
            var map = new L.Map('map', {zoom: 9, center: new L.latLng([37.9802, 23.7329], 13)});
            map.addLayer(new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'));	//base layer

            function formatJSON(rawjson) {	//callback that remap fields name
                var json = {},
                        key, loc, disp = [];

                for (var i in rawjson)
                {
                    disp = rawjson[i].display_name.split(',');

                    key = disp[0] + ', ' + disp[1];

                    loc = L.latLng(rawjson[i].lat, rawjson[i].lon);

                    json[ key ] = loc;	//key,value format
                }

                return json;
            }

            var searchOpts = {
                url: 'http://nominatim.openstreetmap.org/search?format=json&q={s}',
                jsonpParam: 'json_callback',
                formatData: formatJSON,
                zoom: 10,
                minLength: 2,
                autoType: false,
                marker: {
                    icon: false,
                    animate: false
                }
            };

            map.addControl(new L.Control.Search(searchOpts));

        </script>
        <!--<script type="text/javascript" src="labs-common.js"></script>-->
        <script>
            var theMarker = {};

            map.on('click', function (e) {
                lat = e.latlng.lat;
                lon = e.latlng.lng;
                document.getElementById("latt").value = lat;
                document.getElementById("longg").value = lon;
                var re = document.getElementById('latt').value;
                var rex = document.getElementById("longg").value;
                console.log("You clicked the map at LAT: " + re + " and LONG: "
                        + rex);
                //Clear existing marker, 

                if (theMarker != undefined) {
                    map.removeLayer(theMarker);
                }
                ;

                //Add a marker to show where you clicked.
                theMarker = L.marker([lat, lon]).addTo(map);
            });
        </script> 



    </body>
</html>

