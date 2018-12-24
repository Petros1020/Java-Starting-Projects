<%-- 
    Document   : newActivityForm
    Created on : Dec 17, 2018, 12:41:23 PM
    Author     : karag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="SpringForm"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <script src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
        <link rel="stylesheet"
              href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css" />

        <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.js"
                integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
        <script src ="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js" ></script> 
        <link href="//cdn.jsdelivr.net/timepicker.js/latest/timepicker.min.css"  rel="stylesheet">
        <script src="//cdn.jsdelivr.net/timepicker.js/latest/timepicker.min.js"></script>
        <link rel="stylesheet" href="<c:url value="/css/newact.css" />" />
        <style>

            /*            #mapid {
                            height: 400px;
                            width: 500px;
                        }*/
        </style>
        <title>new activity</title>
    </head>
    <body>

        <!--
            <div id="logoutbut">
                <input id="logout" name="whatevs" type="submit" value="Logout">
            </div>
        -->
        <!--End of Logout -->

        <SpringForm:form modelAttribute="activity" id="actform"
                         action="${pageContext.request.contextPath}/activity/new"
                         method="POST" enctype="multipart/form-data">
            <div id="formunderlay"> 
                <div id="activityform">

                    <div id="formHolder">  

                        <div>   <label class="desc" id="title1" for="Field1">Title: </label>
                            <div>   <SpringForm:input path="name" required="required" placeholder="Activity Name" tabindex="1"/>
                                <SpringForm:errors path="name" cssClass="error" />
                            </div>
                        </div>

                        <div>   <label class="desc" id="description1" for="Field2">Description: </label>
                            <div>   <textarea name="locationDescription" form="actform" spellcheck="true"  tabindex="2"></textarea>
                            </div>
                        </div>

                        <span>   <label class="desc" id="description3" for="category">Category:</label>
                            <span>
                                <SpringForm:select path="type" required="true" tabIndex="3">
                                    <SpringForm:option value="">--Select--</SpringForm:option>
                                    <SpringForm:options items="${actList}" itemValue="id" itemLabel="name"/>
                                </SpringForm:select>
                                <SpringForm:errors path="type" cssClass="error" />
                            </span>
                        </span> 


                        <div class="uploadimage">
                            <div class="input file">
                                <label id="uploadlabel" for="file1">        
                                    <span id="uploadimage">+Upload Image</span>
                                    <input type="file" name="imageupload" multiple tabindex=1 />
                                </label> 
                            </div>
                        </div>


                        <span>   <label class="membersin" id="title1" for="Field1">Members: </label>
                            <span> <SpringForm:input path="maxMembers" type="number" min="0" tabindex="1"/>
                                <SpringForm:errors path="maxMembers" cssClass="error" />  
                            </span>
                        </span>



                        <span>   <label class="desc" id="title1" for="Field1">Date: </label>
                            <span> <input type="date" required="required"  name="date" value="2018-07-22"
                                          min="2018-01-01" max="2020-12-31" tabindex="1">
                            </span>
                        </span>

                        <span>   <label class="timein" id="title1" for="Field1">Time: </label>
                            <span> <input type="text" name="time" id="time" placeholder="Time" required="required" tabindex="1">  
                            </span>
                        </span>


                        <a href="leafletmapstest.jsp"></a>

                        <div><input href="" id="saveForm" name="saveForm" type="submit" value="Submit"></div>

                    </div><!--End FormHolder-->

                    <div id="spacer"></div> 
                    <div id="register">

                        <iframe src="mapReturn" name="mapframe" id="mapidd" class="iframe-map"  frameborder="0" style="border:0" allowfullscreen="">
                        </iframe>
                        <input type="hidden" name="lat" id="lattt" value="" required/> 
                        <input type="hidden" name="longg" id="longgg" value="" required/>


                        <%--<jsp:include page="leafletmapstest.jsp" >--%>
                        <%--<jsp:param name="firstName" value="Enter First Name" />--%>
                        <%--<jsp:param name="lastName" value="Enter Last Name" />--%>
                        <%--</jsp:include>--%>

                    </div>

                </div>
            </div>

        </SpringForm:form>     
        <script>
            var timepicker = new TimePicker('time', {
                lang: 'en',
                theme: 'dark'
            });
            timepicker.on('change', function (evt) {

                var value = (evt.hour || '00') + ':' + (evt.minute || '00');
                evt.element.value = value;

            });
        </script> 



        <script src="<c:url value="/js/newact.js" />"></script>

        <script>
            $(document).ready(function () {
                $('#mapidd').on("load",function() {
                var iframeDoc = $('#mapidd').contents().get(0);
                console.log(iframeDoc);
                $(iframeDoc).on('click', function (event) {
                    console.log("mpike sto click");
                    var lat = $('iframe[name=mapframe]').contents().find("#latt").val();
                    var lon = $('iframe[name=mapframe]').contents().find("#longg").val();
                    console.log(lat + " ta pire leme  " + lon);
                    $('#lattt').val(lat);
                    $('#longgg').val(lon);
                });
            });
            });
        </script>
    </body>

</html>
