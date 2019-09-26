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
			                <h3>Welcome <b> ${user.login}</b>!</h3>
                            <form method="get" action="logoutServlet">
                                   <input class="w3-btn w3-black w3-hover-light-green w3-round-large " type=submit  value="Log out"/>
                            </form
                            <p>Your balance: <${user.balance}> rub</p>
                            <p>Your discount: ${user.discount} %</p>
<br>
    <table  border="1">
                   <tr  >
                         <th colspan="5" align="center">Список цветов </th>
                   </tr>
                    <tr align="center">
    				    <th>Название</th>
    				    <th>Количество<br>на складе</th>
    				    <th>Цена</th>
                        <th>Заказать<br>кол-во</th>
    				    <th>Добавить <br>в корзину</th>
    				</tr>

                   <c:forEach items = "${flowers}" var="item">
                       <form method="post" action="addBasketServlet">
                       <tr>
                       <input type="hidden" name="flowerID" value="${item.id}"/>
                       <input type="hidden" name="quantity" value="${item.quantity}"/>
                       <input type="hidden" name="price" value="${item.priceFlower}"/>
                         <td>${item.titleFlower}</td>
    				     <td align="center">${item.quantity}</td>
    					 <td align="center">${item.priceFlower}</td>
    					 <td align="center">
    					 <button type="button" onclick="this.nextElementSibling.stepDown()">-</button>
                             <input type="number" min="0" max="1000" value="0" readonly name="quantityToBasket" class="raz">
                          <button type="button" onclick="this.previousElementSibling.stepUp()">+</button>
                          </td>
    					  <td align="center"><input type=submit value="Add to basket"/><td>
    					</tr>
    					</form>
    			   </c:forEach>

    			</table>
</article>
    <div>
       <p>${orderMessage}</p>
       <c:if test = "${basket != null}">

                <h6>Корзина</h6>
    			    <table  border="1">
                                   <tr align="center">
                                         <th colspan="4">Корзина</th>
                                   </tr>
                                    <tr align="center">
                                        <th></th>
                    				    <th>Название</th>
                    				    <th>Количество</th>
                                        <th>Цена</th>
                     				</tr>
                                    <c:forEach items = "${basket}" var="item">
                     				    <tr>
                                            <td></td>
                     				        <td>${item.flowerName}</td>
                     				        <td>${item.quantity}</td>
                                            <td>${item.totalPrice}</td>
                     				    </tr>
                     				</c:forEach>
                     				    <tr align="center">
                     				        <td colspan="4"> </td>
                     				    </tr>
                     				        <tr>
                                                <td align="left" colspan="3">total cost: </td>
                                                <td><input type="number" readonly value="${total}"/></td>
                                            </tr>
                     				        <tr>
                     				            <td colspan="3"></td>
                                                 <form method="post" action="createOrderServlet">
                                        		<td><input type=submit value="Создать заказ" ></td>
                                        		</form>
                                            </tr>
                                        </c:if>
                                </table>
                                <br>
                                <div align="left">
                                </div>
                </div>
    <div>
    <br>
    </div>
 <div div class="w3-container w3-center">

 <br>
 <table align="left" border="1">
  <form   method="post" action="payCreateOrderServlet">

     <tr align="left">
         <th>Id Order</th>
         <th>Date create</th>
         <th>Date close</th>
         <th>Sum</th>
         <th>Status order</th>
     </tr>
        <c:forEach items="${orderList}" var="item" >
        <tr>
             <td>${item.id}</td>
             <td>${item.dateCreate}</td>
             <td>${item.dateClose}</td>
             <td>${item.totalPrice}</td>
             <td>${item.status}</td>



                     <td colspan="3"></td>
                     <c:if test="${item.status eq 'CREATED'}">
                         <td><input type=submit value="Оплатить" ></td>
                     </c:if>

          </c:forEach>
        </form>
 </table>

 </div>
 <br>
 <br>
<d

</body>
</html>
