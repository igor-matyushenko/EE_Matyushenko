<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <title>Flower Shop 1.0</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>Flower SHOP!</h1>
</div>

<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">

            <table>
                <form method="post" action="loginServlet">
                <tr>
                    <td colspan="1">Login:</td>
                    <td colspan="2"><input type="text"  name="login" required></td>
                </tr>
                <tr>
                    <td colspan="1">Passwords:</td>
                    <td colspan="2"><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input class="w3-btn w3-grey w3-hover-light-green w3-round-large " type=submit  value="Log in" /></td>
                </form>
                <form method="get" action="registrationServlet">
                    <td><input class="w3-btn w3-grey w3-hover-light-green w3-round-large" type=submit  value="Registration" /></td>
                    <td></td>
                </form>
                </tr>
                <tr>
                    <td></td>
                </tr>

            </table>



    </div>

    <div class="w3-container w3-center"><%
        Object error = request.getAttribute("error");
        if(error!=null) {
            out.println("<div style=\"color: red\">" + error + "</p>");
        }%>
    </div>

</div>
</body>
</html>