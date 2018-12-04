<%-- 
    Document   : success
    Created on : Nov 9, 2018, 1:15:46 PM
    Author     : karag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/resources/theme1/css/cssp5.css"/>" rel="stylesheet">
        <!--<link type="text/css" href="${pageContext.request.contextPath}/resources/theme1/css/cssp5.css" rel="stylesheet">-->
        <style>
            h1{
                position: absolute;
                height: 50%;
                width: 50%;
            }
        </style>
    </head>
    <body>
        <h1>successfully logged in!</h1>
        <canvas></canvas>
        
        <script src="${pageContext.request.contextPath}/resources/theme1/js/jsp5.js"></script>
    </body>
</html>
