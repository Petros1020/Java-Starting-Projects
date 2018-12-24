<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="SpringForm"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Index Page</title>

        <style>
            .bootstrapcolumn {
                border: red solid 1px;
            }
        </style>
        <script src="<c:url value="/js/angular.min.js" />"></script>
    </head>
    <body ng-app="myApp">

        <div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-3 bootstrapcolumn"><input type="text"></div>
                    <div class="col-xl-9 bootstrapcolumn" name="ajaxs">ajax search</div>
                </div>
                <div class="row">
                    <div class="col-xl-3 bootstrapcolumn">
                        <div ng-controller="customersCtrl">
                            <ul>
                                <li ng-repeat="x in myData">{{ x.name}}
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-xl-6 bootstrapcolumn">
                        <form action="${pageContext.request.contextPath}/user/login"
                              method="POST">
                            <input type="text" name="username" placeholder="username" /> <input
                                type="password" name="password" placeholder="password" /> <input
                                type="submit" />
                        </form>
                    </div>
                    <div class="col-xl-3 bootstrapcolumn">
                        <a href="${pageContext.request.contextPath}/user/registrationform">New here? Register!</a>


                    </div>
                </div>

            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.js"
                integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>

        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">

        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous">
        </script>
        <script>

//            $(document).ready(function () {
//                $("#ajaxkeyup").keyup(function () {
//                    let searchInput = $("#ajaxkeyup").val();
//                    var app = angular.module('myApp', []);
//                    app.controller('customersCtrl1', function ($scope, $http) {
//                        $http.get("${pageContext.request.contextPath}/activity/ajaxsearch?type=" + searchInput).then(function (response) {
//                            $scope.myData = response.data;
//                        });
//                    });
//
////                    alert(searchInput);
//                });
//            });
            $(document).ready(function () {
                $("#ajaxkeyup").keyup(function () {
                    let searchInput = $("#ajaxkeyup").val();
                    var app = angular.module('myApp', []);
                    app.controller('customersCtrl', function ($scope, $http) {
                        $http.get("${pageContext.request.contextPath}/activity/ajaxsearch?type=1").then(function (response) {
                            $scope.myData = response.data;
                        });
                    });
                });
            });

//            app.controller('activitiesSearch', function ($scope, $http) {//
//                $http.get("${pageContext.request.contextPath}/activity/ajaxsearch")
//                        .then(function (response) {
//                            $scope.myData = response.data.records;
//                        });
//            });

        </script>

    </body>
</html>