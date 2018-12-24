<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>



        <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" ></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/queries.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/grid.css" />

        <link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/act_1.css">

        <!-- search functions -->
        <script src="${pageContext.request.contextPath}/js/angular.min.js"></script>


        <title>Jnpep</title>

    </head>
    <body>



        <jsp:include page="nav.jsp" />
        <div style="height: 100px"></div>


        <div class="panel  coupon">
            <div class="panel-heading" id="head">
                <div href="#" class="panel-title" id="title">
                    <i class="fa fa-github fa-2x"></i>

                    <span href="#" class="visible-xs">${activity.name}</span>
                </div>
            </div>
            <div class="panel-body">




                <img src="${activity.image}" class="coupon-img img-rounded">

                <!--<img class="mapimage img-rounded" src="http://51.15.115.175:8080/jnpepfiles/activityimages/fbg.jpg" class="coupon-img img-rounded">-->
                <div id="mapping" class="mapimage img-rounded">
                    <jsp:include page="testleafletShowLoc.jsp" />
                </div>


                <div class="col-md-12 well well-sm">
                    <div id="activity-info">
                        <ul>

                            <!--<li><span><i class="info"></i> Location: here</span></li>-->
                            <li><span><i class="info"></i> Date: ${activity.date} </span></li>
                            <li><span><i class="info"></i> Time: ${activity.time} </span></li>
                            <li><span><i class="info"></i> Attendees: ${members}</span></li>
                            <li> <span> <i class="info"></i>
                                    <a href="${pageContext.request.contextPath}/activity/leave/${activity.id}" >
                                        <button  class="themeBtn">Leave Activity</button>
                                    </a>
                                    <br>


                                </span> </li>

                            <!--
                                                        <div id="leave">
                                        <div class="container-fluid  margin">
                                    <button  href="#" target="_blank" class="themeBtn">Leave Activity</button>
                                                    <br>
                                    
                                    
                            </div>
                            
                                    
                                    </div>
                            -->



                        </ul>
                    </div>
                </div>

                <div class="row">                       
                    <div id="descr1" class="col-md-6">
                        <h2 >Description</h2>
                        <div class="description">${activity.locationDescription}</div>
                    </div>

                    <div id="attendees1" class="col-md-6" ><h2>
                            Attendees</h2>
                        <nav id="attenlist">
                            <ul>
                                <c:forEach items="${userlist}" var="item"> 
                                    <li>${item.username}</li>
                                    </c:forEach>


                            </ul>
                        </nav>


                    </div>

                </div>
            </div>
        </div>
        <div class="panel  coupon1">
            <div class="panel-heading" id="head">
                <div href="#" class="panel-title" id="title">
                    <i class="fa fa-github fa-2x"></i>

                    <span href="#" class="visible-xs">Chat</span>
                </div>
            </div>
            <div class="panel-body">






                <div>
                    <div id="cont"><div id="content"></div></div>
                    <br>
                    <div id="messages" > <textarea name="message" id="message" rows="4" cols="50"></textarea></div><br>
                    <div id="chatbtn"><input onclick="formSubmit()" type="submit" value="Send!"></div><br>




                </div>


            </div>
        </div>





        <footer class="page-footer ">


            <div class="footer-copyright text-center py-3">© 2018 Copyright:
                <div class="foot">

                    <p >
                        Jnpep </p>
                </div>
            </div>


        </footer>




        <script src="${pageContext.request.contextPath}/js/my.js"></script>
        <script>

                        $(document).ready(function () {
                            $("#navBarAll").css("margin-left", "-100px");
                            $("#navBarAll").css("width", "500px");
                            $('#map').css("height", "300px");
                            $('#map').css("width", "300px");
                            $('#map').css("margin", "4px 35px");
                            $('.navbar').css('z-index', 3000);
                        });
                        var socket = new WebSocket("ws://localhost:8080${pageContext.request.contextPath}/chat/${activity.id}");
                        socket.onmessage = onMessage;

                        function onMessage(event) {
                            var newMessage = event.data;
                            var chats = document.getElementById("content");
                            var newHtml = chats.innerHTML + "</br>" + newMessage;
                            document.getElementById("content").innerHTML = newHtml;
                        }

                        function say(name, text) {
                            var msg = {
                                type: "message",
                                text: text,
                                name: name,
                                date: new Date(Date.now()).toLocaleString() //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date/toLocaleString,
                            };

                            //Convert a JavaScript object into a string with JSON.stringify().
                            socket.send(JSON.stringify(msg));
                        }

                        function formSubmit() {

                            var name = '${user.username}';
                            // var name = document.getElementById("name").value;
                            var text = document.getElementById("message").value;

                            try {
                                say(name, text);

                            } catch (e) {
                                throw new Error(e.message);
                            }
                            return false;
                        }



                        //function register(){
                        //      var name = document.getElementById("name").value; 
                        //}
                        $('.navbar').css('z-index', 3000);
        </script>

    </body>

</html>


