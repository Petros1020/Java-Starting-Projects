<%-- 
    Document   : index_front
    Created on : Dec 14, 2018, 6:23:26 PM
    Author     : karag
--%>

<%@page import="com.bootcamp.ProjectBoot_Jnpep.models.ActivitiesDefault"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>

        <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" ></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/queries.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/grid.css" />

        <link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet" type="text/css">

        <!-- search functions -->
        <script src="${pageContext.request.contextPath}/js/angular.min.js"></script>

        <title>Jnpep</title>


    </head>
    <body>

        <section class="firstpart ">
            <header class="nav-container" >
                <jsp:include page="nav.jsp" />


                <div class="jumbotron">

                    <div class="hero-text-box">
                        <h1>Create your<br> experience</h1>

                        <a class="btn btn-ghost" id="createactivity" href="${pageContext.request.contextPath}/activity/create">Start a new activity! </a>
                    </div>

                </div>

            </header>
        </section>

        <section class="container section-cardcar">




            <div class="container-fluid">
                <h1 class="text-center mb-3">Activities</h1>
                <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="5000" data-wrap="true">
                    <div class="carousel-inner row w-100 mx-auto">





                        <div class="carousel-item col-md-4 active">
                            <div class="card">
                                <img class="card-img-top img-fluid" src="${firstAct.defaultImageUrl}" alt="Card image cap">
                                <div class="card-body">
                                    <h4 class="card-title">${firstAct.name}</h4>

                                </div>
                            </div>
                        </div>


                        <c:forEach items="${actdef}" var="item">                  
                            <div class="carousel-item col-md-4">
                                <div class="card">
                                    <img class="card-img-top img-fluid" src="<c:out value="${item.defaultImageUrl}"/>" alt="Card image cap">
                                    <div class="card-body">
                                        <h4 class="card-title"><c:out value="${item.name}"/></h4>

                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <!--  
                                               <div class="carousel-item col-md-4">
                                                   <div class="card">
                                                       <img class="card-img-top img-fluid" src="http://placehold.it/800x600/3ed846/fff" alt="Card image cap">
                                                       <div class="card-body">
                                                           <h4 class="card-title">Card 3</h4>
                                                           <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                                                                       <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                                                       </div>
                                                   </div>
                                               </div>
                                               <div class="carousel-item col-md-4">
                                                   <div class="card">
                                                       <img class="card-img-top img-fluid" src="http://placehold.it/800x600/42ebf4/fff" alt="Card image cap">
                                                       <div class="card-body">
                                                           <h4 class="card-title">Card 4</h4>
                                                           <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                       
                                                       </div>
                                                   </div>
                                               </div>
                                               <div class="carousel-item col-md-4">
                                                   <div class="card">
                                                       <img class="card-img-top img-fluid" src="http://placehold.it/800x600/f49b41/fff" alt="Card image cap">
                                                       <div class="card-body">
                                                           <h4 class="card-title">Card 5</h4>
                                                           <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                       
                                                       </div>
                                                   </div>
                                               </div>
                                               <div class="carousel-item col-md-4">
                                                   <div class="card">
                                                       <img class="card-img-top img-fluid" src="http://placehold.it/800x600/f4f141/fff" alt="Card image cap">
                                                       <div class="card-body">
                                                           <h4 class="card-title">Card 6</h4>
                                                           <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                       
                                                       </div>
                                                   </div>
                                               </div>
                                               <div class="carousel-item col-md-4">
                                                   <div class="card">
                                                       <img class="card-img-top img-fluid" src="http://placehold.it/800x600/8e41f4/fff" alt="Card image cap">
                                                       <div class="card-body">
                                                           <h4 class="card-title">Card 7</h4>
                                                           <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                                                       </div>
                                                   </div>
                                               </div>-->
                    </div>
                    <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>


        </section>

        <footer class="page-footer font-small unique-color-dark pt-4">


            <div class="footer-copyright text-center py-3">© 2018 Copyright:
                <p class="copy">
                    Jnpep </p>
            </div>


        </footer>




<!--        <script src="${pageContext.request.contextPath}/js/popper.min.js" ></script>-->


        <script src="${pageContext.request.contextPath}/js/my.js" ></script>
        <script>
            $(document).ready(function () {
                $("#navBarAll").css("margin-left", "-100px");
                $("#navBarAll").css("width", "500px");
            });
        </script>
    </body>

</html>


