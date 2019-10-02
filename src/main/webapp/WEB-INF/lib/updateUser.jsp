<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
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
              font-size: 15px;
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

<body class="w3-light-grey">
<script>
    document.getElementById('check').onkeydown = function (e) {
        return !(/^[А-Яа-яA-Za-z ]$/.test(e.key));  // IE > 9
    }
    </script>
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>User list</h1>
    <br>
    <form method="get" action="logoutServlet">
        <input class="w3-btn w3-black w3-hover-light-green w3-round-large " type=submit  value="Log out"/>
     </form>
     <br>
</div>

<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <table border="2">
        <c:forEach items = "${userListAdmin}" var="iterator">

                 <tr>
                    <th>LOGIN</th>
                    <th>PASSWORD</th>
                    <th>DISCOUNT</th>
                    <th>BALANCE</th>
                    <th>FIRST<br>NAME</th>
                 </tr>


            <form method="post" action="updateUserServlet">
               <tr>
                    <td colspan="1"><input type="text"  name="login" value="${iterator.login}" required></td>
                    <td colspan="1"><input type="text"  name="password" value="${iterator.password}" required></td>
                    <td colspan="1"><input type="text"  name="discount" value="${iterator.discount}" required></td>
                    <td colspan="1"><input type="text"  name="balance" value="${iterator.balance}" required></td>
                    <td colspan="1"><input type="text" name="firstName" value="${iterator.firstName}" /></td>
                </tr>
                <tr>
                                    <th>SECOND<br>NAME</th>
                                    <th>ADDRESS</th>
                                    <th>EMAIL</th>
                                    <th>PHONE<br>NUMBER</th>
                                </tr>
                <tr>
                    <td colspan="1"><input type="text" name="lastName"  value="${iterator.lastName}"/></td>
                    <td colspan="1"><input type="text" name="address" value="${iterator.address}" /></td>
                    <td colspan="1"><input type="email"  name="email"   value="${iterator.email}"/></td>
                    <td colspan="1"><input  type="number" id="check" step="any" name="phoneNumber" value="${iterator.phoneNumber}" pattern="[1-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{2}--[0-9]{2}" /></td>
                    <td>
                            <input class="w3-btn w3-black w3-hover-light-green w3-round-large " type=submit  value="Update"/>
                    </td>
                </tr>
            </form>

            </c:forEach>

        </table>
    </div>
</div>
</body>
</html>