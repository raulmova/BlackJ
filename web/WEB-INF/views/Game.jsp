<%@ page import="com.raul.spring.models.Room" %>
<%@ page import="com.raul.spring.models.User" %><%--
  Created by IntelliJ IDEA.
  User: rules
  Date: 3/2/2018
  Time: 12:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<%
    Room room = (Room)session.getAttribute("room");
    User user  = (User)session.getAttribute("user");
%>
<h3 class="display-3" >Room: <%= room.getRoomID() %></h3>
<h4 class="display-4" >Usuario: <%= user.getUserID() %></h4>
<div class="container">
    <div class="row justify-content-md-center">
        <div class="col col-lg-2">
            <form action="/hit" method="post">
                <button class="btn btn-primary mb-2" type="submit"> Dame Carta </button>
            </form>
        </div>
        <div class="col col-lg-2">
            <form action="" method="post">
                <button class="btn btn-primary mb-2" type="submit"> Plantarse </button>
            </form>
        </div>
        <div class="col col-lg-2">
            <form action="/BlackJack/exitGame" method="post">
                <button class="btn btn-primary mb-2" type="submit"> Salir </button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
