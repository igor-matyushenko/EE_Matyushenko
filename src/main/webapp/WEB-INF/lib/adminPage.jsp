<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flower Shop 1.0</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style type="text/css">
        table {
          font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
          border-collapse: collapse;
          color: #686461;
        }
        caption {
          padding: 10px;
          color: white;
          background: #8FD4C1;
          font-size: 18px;
          text-align: left;
          font-weight: bold;
        }
        th {
          border-bottom: 3px solid #B9B29F;
          padding: 10px;
          text-align: left;
        }
        td {
          padding: 10px;
        }
        tr:nth-child(odd) {
          background: white;
        }
        tr:nth-child(even) {
          background: #E8E6D1;
        }
    </style>
</head>
<body>
<div class="w3-container ">
    <div class="w3-container w3-opacity w3-left-align w3-blue-grey">
        <h1>Flower SHOP!</h1>
    </div>
    <div class="w3-container w3-opacity w3-right-align">
        <table align=right>
            <tr valign=middle>
                <td colspan="1" > <h2>Welcome <b> ${user.login}</b>!</h2></td>
                <td colspan="1">
                    <form method="get" action="logoutServlet">
                            <input class="w3-btn w3-black w3-hover-light-green w3-round-large " type=submit  value="Log out"/>
                     </form>
                </td>
            </tr>
        </table>
    </div>
</div>

<div div class="w3-container w3-center">
<table align="center" border="1">
    <caption> Список заказов </caption>
    <tr align=center>
        <th>idOrder</th>
        <th>idUser</th>
        <th>sum</th>
        <th>Date create</th>
        <th>Date close</th>
        <th>Order status</th>
    </tr>
    <c:forEach var="order" items="${orderListAdmin}">
        <tr>
            <td>${order.id}</td>
            <td>${order.id}</td>
            <td>${order.totalPrice}</td>
            <td>${order.dateCreate}</td>
            <td>${order.dateClose}</td>

            <td>
                <c:if test="${order.status  eq 'CLOSED'}">

                    <form method="post" action="ClosedServlet">  >
                	<input type="button" type=submit  value="Closed"/>
                	<form>

                </c:if>

            </td>
        </tr>
    </c:forEach>

</table>
</div>
</body>
</html>
