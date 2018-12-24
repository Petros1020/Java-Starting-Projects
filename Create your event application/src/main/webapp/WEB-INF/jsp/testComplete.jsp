<%-- 
    Document   : testSuccess
    Created on : Dec 11, 2018, 8:56:07 PM
    Author     : pitpr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>The test was concluded</h1>
       
        <h2>  The results are:
        <br/>   
         <c:forEach items="${result}" var = "item">
        <c:out value = "${item}"/><p>
             <!--${item}--> 
      </c:forEach>
             <h2/>
    </body>
</html>
