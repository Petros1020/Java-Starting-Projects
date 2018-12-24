<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="SpringForm"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Leaflet Plugin Sample</title>
        <link rel="stylesheet"
              href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">
        <meta http-equiv='content-type' content='text/html; charset=utf-8' />
        <meta name='viewport' content='initial-scale=1.0 maximum-scale=1.0'>
        <style type="text/css">
            html, body, #map {
                height: 100%;
                width: 100%;
                margin: 0;
                padding: 0;
            }
        </style>
    </head>
    <body>
        <div id="map"></div>
        <input type="hidden" name="lat" id="latt" value="" required/> 
        <input type="hidden" name="longg" id="longg" value="" required/>
        <script
        src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/2.4.1/lodash.min.js"></script>
        <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
        <script
        src="https://rawgit.com/calvinmetcalf/leaflet-ajax/master/dist/leaflet.ajax.min.js"></script>
        <!--<script src="${pageContext.request.contextPath}/js/search.js" type="text/javascript"></script>-->
        <!--<script src="${pageContext.request.contextPath}/js/jsmaps/index.js" type="text/javascript"></script>-->


        <script>
            // refer to the IControl interface
// http://leafletjs.com/reference.html#icontrol
// sample influenced by leaflet-geocoder
// https://github.com/perliedman/leaflet-control-geocoder
            console.log("search js");
            function sortParks(a, b) {
                var _a = a.feature.properties.park;
                var _b = b.feature.properties.park;
                if (_a < _b) {
                    return -1;
                }
                if (_a > _b) {
                    return 1;
                }
                return 0;
            }

            L.Control.Search = L.Control.extend({
                options: {
                    // topright, topleft, bottomleft, bottomright
                    position: 'topright',
                    placeholder: 'Search...'
                },
                initialize: function (options /*{ data: {...}  }*/) {
                    // constructor
                    L.Util.setOptions(this, options);
                },
                onAdd: function (map) {
                    // happens after added to map
                    var container = L.DomUtil.create('div', 'search-container');
                    this.form = L.DomUtil.create('form', 'form', container);
                    var group = L.DomUtil.create('div', 'form-group', this.form);
                    this.input = L.DomUtil.create('input', 'form-control input-sm', group);
                    this.input.type = 'text';
                    this.input.placeholder = this.options.placeholder;
                    this.results = L.DomUtil.create('div', 'list-group', group);
                    L.DomEvent.addListener(this.input, 'keyup', _.debounce(this.keyup, 300), this);
                    L.DomEvent.addListener(this.form, 'submit', this.submit, this);
                    L.DomEvent.disableClickPropagation(container);
                    return container;
                },
                onRemove: function (map) {
                    // when removed
                    L.DomEvent.removeListener(this._input, 'keyup', this.keyup, this);
                    L.DomEvent.removeListener(form, 'submit', this.submit, this);
                },
                keyup: function (e) {
                    console.log("ok keyup");
                    if (e.keyCode === 38 || e.keyCode === 40) {
                        // do nothing
                    } else {
                        this.results.innerHTML = '';
                        if (this.input.value.length > 2) {
                            var value = this.input.value;
                            var results = _.take(_.filter(this.options.data, function (x) {
                                return x.feature.properties.park.toUpperCase().indexOf(value.toUpperCase()) > -1;
                            }).sort(sortParks), 10);
                            _.map(results, function (x) {
                                var a = L.DomUtil.create('a', 'list-group-item');
                                a.href = '';
                                a.setAttribute('data-result-name', x.feature.properties.park);
                                a.innerHTML = x.feature.properties.park;
                                this.results.appendChild(a);
                                L.DomEvent.addListener(a, 'click', this.itemSelected, this);
                                return a;
                            }, this);
                        }
                    }
                },
                itemSelected: function (e) {
                    L.DomEvent.preventDefault(e);
                    var elem = e.target;
                    var value = elem.innerHTML;
                    this.input.value = elem.getAttribute('data-result-name');
                    var feature = _.find(this.options.data, function (x) {
                        return x.feature.properties.park === this.input.value;
                    }, this);
                    if (feature) {
                        this._map.fitBounds(feature.getBounds());
                    }
                    this.results.innerHTML = '';
                },
                submit: function (e) {
                    L.DomEvent.preventDefault(e);
                }
            });

            L.control.search = function (id, options) {
                return new L.Control.Search(id, options);
            }

        </script>

        <!--        <script>
                    console.log("index js");
                    var map = L.map('map').setView([37.9802, 23.7329], 13);
        
                    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
                        maxZoom: 18
                    }).addTo(map);
        
                    var items = [];
        
        // nice leaflet-ajax plugin
        // https://github.com/calvinmetcalf/leaflet-ajax
                    var geojsonLayer = L.geoJson.ajax('https://d2ad6b4ur7yvpq.cloudfront.net/naturalearth-3.3.0/ne_10m_parks_and_protected_lands_area.geojson', {
        
                        onEachFeature: function (data, layer) {
                            console.log("mesa sto geojson");
                            items.push(layer);
                            layer.bindPopup('<h3>' + data.properties.park + '</h3>');
                        }
                    });
                    console.log(geojsonLayer);
                    console.log("prin to map");
                    geojsonLayer.addTo(map);
                    console.log("mpen sto map");
        
                    L.control.search({
                        data: items
                    }).addTo(map);
        
                </script>-->


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
            L.control.search({
                data: searchOpts
            }).addTo(map);
//            map.addControl(new L.Control.Search(searchOpts));

        </script>



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
                console.log("You clicked the map at LAT: " + lat + " and LONG: "
                        + lon);
                //Clear existing marker, 

                if (theMarker != undefined) {
                    map.removeLayer(theMarker);
                }
                ;

                //Add a marker to show where you clicked.
                theMarker = L.marker([lat, lon]).addTo(map);
            });
        </script> 
        <!--        <script>
                    $(document).ready(function () {
                        $('#mapid').click(function () {
                            $('#lat').val(lat);
                            $('#longg').val(lon);
                        });
                    });
                </script>-->
        <!--  	<script src='js/controls/search.js'></script>
        <script src='js/index.js'></script>-->
    </body>
</html>
