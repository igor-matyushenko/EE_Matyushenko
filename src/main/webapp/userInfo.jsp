<%--
  Created by IntelliJ IDEA.
  User: igor.matyushenko
  Date: 03.09.2019
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flower Shop 1.0</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>Flower SHOP!</h1>
</div>
<div class="w3-container w3-center">
    <h2>Log in successfully!</h2>

    <form method="post" action="indexServlet">
        <h3>Welcome <b><% out.print( request.getParameter("login"));%></b>!</h3>
        <input class="w3-btn w3-black w3-hover-light-green w3-round-large " type=submit  value="Log out"/>
    </form>
</div>
</body>
</html>
