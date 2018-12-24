<%-- 
    Document   : nav
    Created on : Dec 19, 2018, 4:47:31 PM
    Author     : pitpr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>



        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Settings</title>
    </head>
    <body>

        <header class="nav-container" >
            <nav class="navbar navbar-default navbar-expand-lg navbar-light">
                <div class="navbar-header d-flex col">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}"><img id="logo" src="http://51.15.115.175:8080/jnpepfiles/jnpepimages/1510851133_667dcf97-a5a4-46d8-bb96-f3585283518c.png"></b></a>  		
                    <a  id="homelink" href="${pageContext.request.contextPath}/user/home">Home</a>      
                    <a  id="settingslink" href="${pageContext.request.contextPath}/user/settings">Settings</a>
                    
                    <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle navbar-toggler ml-auto">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <!-- Collection of nav links, forms, and other content for toggling -->
                    <div id="navbarCollapse" class="collapse navbar-collapse justify-content-start">

                        <form class="navbar-form form-inline ">
                            <div id="navBarAll" class="input-group search-box">								
                                <input type="text" id="search" class="form-control" autocomplete="off" placeholder="Search here...">

                                <div id="searchajaxid"  class="container search-table">
                                    <!--<div>arxi</div>-->

                                    <div class="search-list">                                      
                                        <table class="table" id="myTable">

                                            <tbody id="ajaxdiv">

                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </form>

                        <ul class="nav navbar-nav navbar-right ml-auto ">

                            <li class="nav-item" id="loginbutton"> 
                                <a data-toggle="dropdown" class="nav-link dropdown-toggle" href="#">Login</a>
                                <ul class="dropdown-menu form-wrapper">					
                                    <li>
                                        <form class="mr-auto " action="${pageContext.request.contextPath}/user/login" method="post"> 
                                            <div class="form-group">
                                                <input type="text" name="username" class="form-control" placeholder="Username" required="required">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="password" class="form-control" placeholder="Password" required="required">
                                            </div>
                                            <input id="navbarsumbit" type="submit" class="btn btn-primary btn-block" value="Login">
                                            <!--                                                        <div class="form-footer">
                                                                                                        <a href="#">Forgot Your password?</a>
                                                                                                    </div>-->
                                        </form>
                                    </li>
                                </ul>
                            </li>

                            <li >

                                <span id="usergreeting">Hey ${user.username}  /</span>
                                <a  id="logoutbutton" href="${pageContext.request.contextPath}/user/logout">Logout</a>
                            </li>
                            <li class="nav-item" id="registerbutton">
                                <a href="${pageContext.request.contextPath}/user/register" class="btn btn-primary dropdown-toggle get-started-btn mt-1 mb-1">Sign up</a>

                            </li>
                        </ul>
                    </div>
                </div>
            </nav>




        </header>
        <script>
            $(document).ready(function () {
                $("#search").keyup(function () {
                    var text = $("#search").val();

                    if (text == "") {
                        $("#searchajaxid").hide();
                        console.log("hide");
                    } else {
                        $("#searchajaxid").show();
                        console.log("show");
                    }


                    console.log(text);
                    $.ajax({
                        url: '${pageContext.request.contextPath}/activity/ajaxsearch?type=' + text,
                        contentType: 'application/json',
                        success: function (result) {
                            $("#ajaxdiv").empty();
                            console.log(result);
                            $.each(result, function (i, item) {
                                console.log(result[i]);
                                //                                $p = $('<tr>').append($('<td>').text("Activity name: " + result[i].type.name + " User's description: " + result[i].name));
                                $p = $("<a href='${pageContext.request.contextPath}/activity/selectActivity?id=" + result[i].id + "'>").text("Activity name: " + result[i].type.name + " User's description: " + result[i].name);

                                $("#ajaxdiv").append("<div id='" + result[i].id + "'></div>");
                                $("#" + result[i].id).append($p);
                            });

                        }
                    });
                });

            });
        </script> 



        <script src="${pageContext.request.contextPath}/js/nav.js" ></script>
        <script>
            $(document).ready(function () {

                console.log("${user.username}");
                if (!("${user.username}" === "")) {
                    $("#usergreeting").toggle();
                    $("#logoutbutton").toggle();
                    $("#createactivity").toggle();
                    $("#homelink").toggle();
                    $("#settingslink").toggle();
                } else {
                    $("#loginbutton").toggle();
                    $("#registerbutton").toggle();
                }
            });

        </script>
    </body>
</html>
