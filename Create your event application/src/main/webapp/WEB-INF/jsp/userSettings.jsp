<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="user"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <style>
            form{
                color:white;
            }
            .error{color:red;}



            .table-borderless td:first-child  {
                text-align:right;
            }



            input[type="submit"],button{
                background-color:black;

            }
            #registrationform input,#registrationform select,#registrationform textarea,#passwordform input,#passwordform select,#passwordform textarea{
                color: black;
            }

        </style>




        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.js"
                integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/queries.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/grid.css" />

        <link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet" type="text/css">

        <!-- search functions -->
        <script src="${pageContext.request.contextPath}/js/angular.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

        <!--    <link rel="stylesheet" type="text/css" href="css/style.css">-->
        <link rel="stylesheet" href="<c:url value="/css/settings.css" />" />
    </head>
    <body>
        <div style="height:100px;"> </div>
        <jsp:include page="nav.jsp" />







        <user:form id="registrationform" modelAttribute="user"
                   action="${pageContext.request.contextPath}/user/update"
                   method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
            <table class="table table-borderless">
                <thead>
                    <tr>
                        <th></th>
                        <th >Profile information</th>

                    </tr>
                </thead>

                <tbody>
                <img id="profileimage"  src="${user.profilePic}">

                <h1>
                    <br>
                    <label id="fileuploadlabel" for="file-upload" class="fa fa-camera-retro fa-lg"> <input name="imageupload" id="file-upload" type="file"  onchange="readURL(this);"/></label>

                    <h2 id="fileuploadlabeltext">Upload Image
                    </h2>
                </h1>
                <br><br>

                <tr>
                    <td>Username:</td>
                    <td><user:input path="username" name="username" required="required" placeholder="New username" /></td>
                    <td>  <user:errors path="username" cssClass="error"  /></td>
                </tr>
                <tr>
                    <td> E-mail:</td>
                    <td>  <user:input path="email" required="required" type="email" placeholder="New e-mail" /></td>
                    <td>  <user:errors path="email" cssClass="error" /></td>
                </tr>
                <tr>
                    <td> Enter your password <br>
                        for confirmation:</td>
                    <td>    <user:input id="password"  path="password" type="password" required="required" placeholder="Account password" /></td>
                    <td>   <span> ${passwordconfirmation1}</span></td>
                </tr>  
                <tr>
                    <td></td>
                    <td>   <button class="btn" type="submit">Submit</button></td>
                </tr>  


                <user:input path="profilePic" hidden="true" />


            </tbody>

        </table>
        <h1> ${infomessage}</h1>
    </user:form>

















    <user:form modelAttribute="user"
               action="${pageContext.request.contextPath}/user/switchpassword"
               method="POST" id="passwordform" >



        <table class="table table-borderless">
            <thead>
                <tr>
                    <th></th>
                    <th >Password update</th>

                </tr>
            </thead>

            <tbody>


                <tr>
                    <td>  New password:</td>
                    <td> <input type="password" name="newpassword" required="required" placeholder="New password" /></td>
                    <td>    <span> ${passwordmessage}</span></td>
                </tr>
                <tr>
                    <td> Repeat new password:</td>
                    <td>   <input type="password" name="newpasswordconfirm" required="required" placeholder="New password repeat" /></td>
                    <td>    <span> ${passwordmessage2}</span></td>
                </tr>
                <tr>
                    <td>      Enter your password<br>
                        for confirmation: </td>
                    <td>    <user:input id="password"  path="password" type="password" required="required" placeholder="Account password" /></td>
                    <td>  <span> ${passwordconfirmation2}</span></td>
                </tr>  
                <tr>
                    <td></td>
                    <td>     <button class="btn" type="submit" id="button" value="Change your password" > Submit </button></td>
                </tr>  


                <user:input path="profilePic" hidden="true" />


            </tbody>

        </table>

        <user:input path="email"  type="email" hidden="true"     />
        <user:input path="username"  hidden="true" />



    </user:form>



    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" ></script>
    <script src="<c:url value="/js/settings.js" />"></script>


    <script>
                        $(document).ready(function () {
                            $("#navBarAll").css("margin-left", "-100px");
                            $("#navBarAll").css("width", "500px");


                        });
    </script>

</body>
</html>