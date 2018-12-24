<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="SpringForm"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <link rel="stylesheet"
              href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css" />

        <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.js"
                integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>

        <title>Insert title here</title>
        <link href="//cdn.jsdelivr.net/timepicker.js/latest/timepicker.min.css"  rel="stylesheet">
        <script src="//cdn.jsdelivr.net/timepicker.js/latest/timepicker.min.js"></script>
        <style>
            .bootstrapcolumn {
                border: red solid 1px;
            }

            #mapid {
                height: 400px;
                /*width: 500px;*/
            }
        </style>

    </head>
    <body>
        <SpringForm:form modelAttribute="activity" id="actform"
                         action="${pageContext.request.contextPath}/activity/createnewactivity"
                         method="POST" enctype="multipart/form-data">

            Activity Name:<SpringForm:input path="name" required="required"
                              placeholder="Activity Name" />
            <SpringForm:errors path="name" cssClass="error" />
            <br>
            <br>


            Type:
            <!--<SpringForm:select path="type" items="${actList}" itemValue="id" itemLabel="name">
            </SpringForm:select>-->

            <SpringForm:select path="type" required="true">
                <SpringForm:option value="">--Select--</SpringForm:option>
                <SpringForm:options items="${actList}" itemValue="id" itemLabel="name"/>
            </SpringForm:select>
            <SpringForm:errors path="type" cssClass="error" />
            <br>
            <br>

            Maps:<div id="mapid">
                <jsp:include page="leafletmapstest.jsp" >
                    <jsp:param name="firstName" value="Enter First Name" />
                    <jsp:param name="lastName" value="Enter Last Name" />
                </jsp:include>
                <input type="hidden" name="lat" id="lat" value="" required/> 
                <input type="hidden" name="longg" id="longg" value="" required/>

<!--                <script>
                    $(document).ready(function () {
                        $('#mapid').click(function () {
                            $('#lat').val(lat);
                            $('#longg').val(lon);
                        });
                    });
                </script>-->
            </div>
            Location Description:<textarea rows="4" cols="50" name="locationDescription"
                                           form="actform"></textarea>


            <br>
            <br>

            Date:<input type="date" required="required"  name="date" value="2018-07-22"
                        min="2018-01-01" max="2020-12-31">
            <br>
            <br>
            Time:<input type="text" name="time" id="time" placeholder="Time" required="required">
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
            <br>
            <br>

            Max Members:<SpringForm:input path="maxMembers" />
            <SpringForm:errors path="maxMembers" cssClass="error" />
            <br>
            <br>
            Image Upload:<input type="file" name="imageupload" multiple>

            <br>
            <br>
            <input type="submit" id="button" value="Submit your activity here!" />
        </SpringForm:form>



        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">

        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

    </body>
</html>