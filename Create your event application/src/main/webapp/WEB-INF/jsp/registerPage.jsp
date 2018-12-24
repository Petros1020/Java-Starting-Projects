<%@page language="java"  contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="user"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Registration page</title> 

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

        <!--    <link rel="stylesheet" type="text/css" href="css/style.css">-->
        <link rel="stylesheet" href="<c:url value="/css/reg.css" />" />
        <style>
            .error{color:red;}
        </style>
    </head>
    <body>







        <div class="confirm">
            <i class='close'>×</i>
            <h1><i class="fa fa-check-circle fa-3x"></i>Boom! Profile Created</h1>
        </div>
        <user:form id="registrationform" modelAttribute="user"
                   action="${pageContext.request.contextPath}/user/new"
                   method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
            <h1>
                Create Your Profile
                <br>
                <label id="fileuploadlabel" for="file-upload" class="fa fa-camera-retro fa-lg"> <input name="imageupload" id="file-upload" type="file"  onchange="readURL(this);"/></label>

                <h2 id="fileuploadlabeltext">Upload Image
                </h2>
            </h1>
            <img id="profileimage"  src="#">
            <br>
            <div class="float-label">
                <user:input type="text" name="username" id="username"  path="username" required="required"  />
                <label for="username">Username</label>
                <user:errors path="username" cssClass="error"  />

            </div>

            <div class="float-label">
                <user:input  name="email" id="email" path="email" required="required" type="email" />
                <label for="email">Email</label>
                <user:errors path="email" cssClass="error" />

            </div>


            <div class="float-label">
                <fa class="fa eye fa-eye-slash"></fa>
                    <user:input name="password" id="password" path="password" type="password" required="required"  />
                <label for="password">Password</label>
                <user:errors path="password" cssClass="error" />
                <span class="error"> ${message}</span>
            </div>
            <div class="float-label">
                <fa class="fa eye fa-eye-slash"></fa>
                <input type="password" name="password1" required="required" id="password1"  />
                <label for="password1">Repeat Password</label>
            </div>


            <div class="age" >

                <label class="container">
                    <input type="checkbox" required="required"><h5>I confirm that I am over 13 years of age and I agree to the <a hreg="terms">Terms and Conditions and Privacy Policy</a> </h5>
                    <span class="checkmark"></span>
                </label>
            </div>   


            <button class="btn" type="submit">Submit</button>
            <button class="btn" id="clear" type="reset" value="Reset">Reset</button>
        </user:form>



        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>



        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
        <script src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" ></script><script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js" > src = "https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" ></script>
        <script src="<c:url value="/js/user.js" />"></script>

        <script></script>
    </body>

</html>