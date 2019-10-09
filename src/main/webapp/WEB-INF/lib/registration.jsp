<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="http://code.jquery.com/jquery-2.2.4.js"
                    type="text/javascript"></script>
            <script src="js/app-ajax.js" type="text/javascript"></script>
                    <script>
                        document.getElementById('check').onkeydown = function (e) {
                            return !(/^[А-Яа-яA-Za-z ]$/.test(e.key));  // IE > 9
                        }
                    </script>
        <title>Flower Shop 1.0</title>

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

<body class="w3-light-grey">


<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>Registration</h1>
</div>

<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">

        <table>
            <form method="post" action="registrationServlet">
                <tr>
                    <td colspan="1">Login:</td>
                    <td colspan="2"><input type="text" id="login" name="login"   autocomplete="off" required></td>
                </tr>
                <tr>
                    <td colspan="1">Passwords:</td>
                    <td colspan="2"><input type="password" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td colspan="1">First Name:</td>
                    <td colspan="2"><input type="text" name="firstName"  required/></td>
                </tr>
                <tr>
                     <td colspan="1">Second Name:</td>
                     <td colspan="2"><input type="text" name="lastName"  required/></td>
                </tr>
                <tr>
                      <td colspan="1">Your address:</td>
                      <td colspan="2"><input type="text" name="address" required/></td>
                </tr>
                <tr>
                  <td colspan="1">Your email:</td>
                  <td colspan="2"><input type="email"  name="email"   required/></td>
                </tr>

                <tr>
                    <td colspan="1">Your phone:</td>
                    <td colspan="2"><input  type="number" id="check" step="any" name="phoneNumber" pattern="[1-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{2}--[0-9]{2}" required/></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="1"><input class="w3-btn w3-grey w3-hover-light-green w3-round-large" id="registration" type=submit  value="Registration"/></td>
            </form>
            <form method="get" action="indexServlet">
                <td colspan="1"><input class="w3-btn w3-black w3-hover-light-green w3-round-large " type=submit  value="Back"/></td>
            </form>
            </tr>
        </table>
    </div>

    <div class="w3-container w3-center">

        <div id ="verificationUserLoginMessage" style="color:red"></div>
    </div>

</div>
    <script>
       $('#login').on('dblclick',function() {
            $('#verificationUserLoginMessage').text( "" );
                      $.ajax({
                            url: 'http://localhost:8080/flowershop/rest/v/verify/' + this.value,
                            success: function(data) {
                                $('#verificationUserLoginMessage').text(data.value);
                            },
                            error: function(data){
                                $('#verificationUserLoginMessage').text("Is Empty!");
                            }
                        });
                });
            </script>
</body>
</html>