<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flower Shop 1.0</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
    .raz {
      all: unset;
      -moz-appearance: textfield;
      width: 3em;
      text-align: center;
    }
    .raz::-webkit-inner-spin-button {
      display: none;
    }
    </style>
</head>
<body>
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>Flower SHOP!</h1>
</div>
    <div class="w3-container w3-white-black w3-left-align">
                <h3>Welcome <b> ${user.login}</b>!</h3>
                <form method="get" action="logoutServlet">
                       <input class="w3-btn w3-black w3-hover-light-green w3-round-large " type=submit  value="Log out"/>
                </form
                <p>Your balance: ${user.balance} rub</p>
                <p>Your discount: ${user.discount} %</p>
    </div>
<div>
    <table  border="1">
                   <tr align="center">
                         <th colspan="5">Список цветов </th>
                   </tr>
                        <form method="get" action="rrrrrrrrrrrr">
                    <tr align="center">
    				    <th>Название</th>
    				    <th>Количество<br>на складе</th>
    				    <th>Цена</th>
                        <th>Заказать<br>кол-во</th>
    				    <th>Добавить <br>в корзину</th>
    				</tr>
                       <c:forEach items = "${flowers}" var="item">
                       <tr>
    				        <td>${item.titleFlower}</td>
    				        <td align="center">${item.quantity}</td>
    					    <td align="center">${item.priceFlower}</td>
    					    <td align="center">
    					        <button type="button" onclick="this.nextElementSibling.stepDown()">-</button>
                                    <input type="number" min="0" max="1000" value="0" readonly name="quaToBasket" class="raz">
                                <button type="button" onclick="this.previousElementSibling.stepUp()">+</button>
                            </td>
    					    <td align="center"><input type=submit value="Add to basket"/><td>
    					</tr>
    			   </c:forEach>
    			   </form>
    			</table>
</div>

</body>
</html>
