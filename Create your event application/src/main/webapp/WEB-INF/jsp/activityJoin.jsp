<%-- 
    Document   : activityJoin
    Created on : Dec 19, 2018, 2:44:39 PM
    Author     : karag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <head>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/queries.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/grid.css" />

        <link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet" type="text/css">

        <!-- search functions -->
        <script src="${pageContext.request.contextPath}/js/angular.min.js"></script>
        <link rel="stylesheet" href="<c:url value="/css/activityJoinnormalize.css" />" />




        <!--<link rel="stylesheet" type="text/css" href="resources/css/queries.css">-->
        <link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet" type="text/css">
        <!--<link rel="stylesheet" type="text/css" href="vendors/css/grid.css">-->
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/activityJoin-grid.css" />-->
        <link rel="stylesheet" href="<c:url value="/css/activityJoingrid.css" />" />


        <!--<link rel="stylesheet" type="text/css" href="resources/css/style.css">-->
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/activityJoin-style.css" />-->
        <link rel="stylesheet" href="<c:url value="/css/activityJoin-style.css" />" />


        <!--<link rel="stylesheet" type="text/css" href="resources/css/act.css">-->
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/activityJoin-act.css" />-->
        <link rel="stylesheet" href="<c:url value="/css/activityJoin-act.css" />" />

        <style>
            .coupon-img {
                height:280px;
            }

            #mapping{
                height:280px;
            }

        </style>

        <title>Jnpep</title>



    </head>
    <body>



        <jsp:include page="nav.jsp" />


        <div class="panel  coupon">
            <div class="panel-heading" id="head">
                <div href="#" class="panel-title" id="title">
                    <i class="fa fa-github fa-2x"></i>
                    <span href="#" class="hidden-xs">${activity.type.name}</span>
                    <span href="#" class="visible-xs">${activity.type.name}</span>
                </div>
            </div>
            <div class="panel-body">

                <div class="row">

                    <div class="col-md-6">
                        <img src="${activity.image}" class="coupon-img img-rounded">
                    </div>

                    <div class="col-md-6">
                        <div id="mapping">
                            <jsp:include page="testleafletShowLoc.jsp" />
                        </div>
                    </div>

                </div>

                <div class="col-md-12 well well-sm">
                    <div id="activity-info">
                        <ul>

                            <li><span><i class="info"></i> Date: ${activity.date} </span></li>
                            <li><span><i class="info"></i> Time: ${activity.time} </span></li>
                            <li><span><i class="info"></i> Attendees: ${members}</span></li>

                        </ul>
                    </div>
                </div>


                <div id="descr" class="col-md-12">
                    <h2 >Description</h2>
                    <p class="discription">${activity.locationDescription}</p>
                </div>
                <div id="join">
                    <div class="container-fluid  margin"><a href="${pageContext.request.contextPath}/activity/join/${activity.id}" >
                            <button   class="themeBtn">Join Activity</button>
                        </a>
                        <br>


                    </div>


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




        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
        <script src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js" ></script>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" ></script>
        <script src="<c:url value="/js/activityJoinmy.js" />"></script>

        <script>
            $(document).ready(function () {
                $('#map').css("height", "280px");
                $('#map').css("width", "400px");
                $('.navbar').css('z-index', 3000);
                $("#navBarAll").css("margin-left", "-100px");
                $("#navBarAll").css("width", "500px");
            });


        </script>
    </body>

</html>


