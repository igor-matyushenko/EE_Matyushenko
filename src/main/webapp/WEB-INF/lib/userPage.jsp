<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Flower Shop 1.0</title>

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
                            </form>
                            <p>Your balance: ${user.balance} rub</p>
                            <p>Your discount: ${user.discount} %</p>
<br>
<div>
    <form method="post" action="searchFlowerServlet">
    <table>
        <tr>
            <td colspan="2" width="70"><input type="search"  name="flowerName" placeholder="Введите текст для поиска" ></td>
            <td colspan="1"><input type=submit value="Поиск"></td>
        </tr>
            <td colspan="1" width="35"><input type="number" name="flowerMinPrice" placeholder="начальная цена" ></td>
            <td colspan="1"><input type="number" name="flowerMaxPrice" placeholder="конечная цена"></td>
        </tr>
    </table>
<br>
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
                       <tr>
                         <td>${item.titleFlower}</td>
    				     <td align="center">${item.quantity}</td>
    					 <td align="center">${item.priceFlower}</td>
    					 </form>
    					 <td align="center">
    					 <form method="post" action="addBasketServlet">
    					 <input type="hidden" name="flowerID" value="${item.id}"/>
                         <input type="hidden" name="quantity" value="${item.quantity}"/>
                         <input type="hidden" name="price" value="${item.priceFlower}"/>
    					 <button type="button" onclick="this.nextElementSibling.stepDown()">-</button>
                             <input type="number" min="0" max="1000" value="0" readonly name="quantityToPos" class="raz">
                          <button type="button" onclick="this.previousElementSibling.stepUp()">+</button>
                          </td>
    					  <td align="center"><input type=submit value="Add to basket"/><td>
    					</tr>
    					</form>
    			   </c:forEach>
</table>
</div>
    <div>

       <c:if test = "${basket != null}">


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
 <p> <span style="color: red">${orderMessage}</span></p>
 <br>
 <table align="left" border="1">


     <tr align="left">
         <th>Id Order</th>
         <th>Date create</th>
         <th>Date close</th>
         <th>Sum</th>
         <th>Status order</th>
     </tr>
     <c:forEach items="${orderList}" var="item" >
     <form   method="post" action="payCreateOrderServlet">
        <tr>
             <td>${item.id}</td>
             <td>${item.dateCreate}</td>
             <td>${item.dateClose}</td>
             <td>${item.totalPrice}</td>
             <td>${item.statusOrder}</td>
             <input type="hidden" name="orderListPayId" value="${item.id}"/>
                           <c:if test="${item.statusOrder eq 'CREATED'}">
                                  <td><input type=submit value="Оплатить" ></td>
                           </c:if>
      </form>
         </tr>

              <td colspan="3"><tr>
                   <td colspan="5">
                        <details>
                          <table>
                              <c:forEach items = "${order.orderPositionList}" var="iterator">
                                   <tr>
                                        <td >${iterator.flowerName}   </td>
                                        <td >${iterator.quantity}  шт. </td>
                                        <td >${iterator.totalPrice} р.</td>
                                    </tr>
                              </c:forEach>
                            </table>
                           </details>
                     </td>


          </c:forEach>

 </table>


 </div>
 <br>
 <br>
  <br>
   <br>
    <br>
     <br>
      <br>
       <br> <br>




</body>
</html>
