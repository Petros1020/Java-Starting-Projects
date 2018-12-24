<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
<!--        <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" ></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/act.css">

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/queries.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/grid.css" />

        <link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet" type="text/css">

        
        <script src="${pageContext.request.contextPath}/js/angular.min.js"></script>-->


        <script src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
        <title>Profile</title>
    </head>
    <body>

        <%--<jsp:include page="nav.jsp" />--%>
        <!--<a href="${pageContext.request.contextPath}/activity/navReturn"> asdadssd</a>-->
        <div></div>

        <div id="formunderlay"> 

            <form action="" id="newActivity">
                <div id="activityform">
                    <div id="formHolder">  

                        <a href="${pageContext.request.contextPath}/" id="homebtn" type="button">Index</a>
                        <a href="${pageContext.request.contextPath}/user/settings" id="setbtn" type="button">Settings</a>

                        <img id="profileimage" src="<c:out value="${sessionScope.user.profilePic}" />" alt="Italian Trulli">

                        <h2 id="prname">Welcome ${user.username}!</h2>

                        <a href="${pageContext.request.contextPath}/activity/create" id="cract">Create new activity!</a>







                        </form>  
                    </div><!--End FormHolder-->

                    <div id="spacer"></div> 
                    <div id="register">
                        <div id="activ" class="col-md-12" ><h2>
                                Upcoming Activities</h2>
                            <nav id="activilist">
                                <ul>
                                    <c:forEach items="${upcoming}" var="item"> 
                                        <li><div class="card">

                                                <div class="card-body">
                                                    <h5 class="card-title">${item.name} - Date:${item.date} Time:${item.time}</h5>

                                                    <a href="${pageContext.request.contextPath}/activity/selectActivity?id=${item.id}" class="btn" type="button">See more</a>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </nav>
                            <br>
                            <div id="activ" class="col-md-12" ><h2>
                                    Past Activities</h2>    
                                <nav id="activilist">
                                    <ul>
                                        <c:forEach items="${past}" var="item"> 
                                            <li>
                                                <div class="card">

                                                    <div class="card-body">
                                                        <h5 class="card-title">${item.name} - Date:${item.date} Time:${item.time}</h5>

                                                        <a href="${pageContext.request.contextPath}/activity/selectActivity?id=${item.id}" class="btn btn-primary">See more</a>
                                                        <br>
                                                    </div>
                                                </div>
                                            </li> 
                                        </c:forEach>
                                    </ul>
                                </nav>     




                            </div>

                        </div>
                    </div>
                </div>
        </div>


        <script>
            $(document).ready(function () {
                $("#navBarAll").css("margin-left", "-100px");
                $("#navBarAll").css("width", "500px");
            });
        </script>

    </body>

</html>