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
<%
    Room room = (Room)session.getAttribute("room");
    User user  = (User)session.getAttribute("user");
%>


<script>
    var standing = false;
    function doStand() {
        document.getElementById("hitButton").disabled =  true;
        standing = true;
    }

    function doHit() {
        var xmlhttp = new XMLHttpRequest();
        var url = "/BlackJack/hit?roomName=<%=room.getRoomID()%>&token=<%=user.getToken()%>";
        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200){
                //document.getElementById("hitButton").disabled =  false;
                loadXML();
            }
        };

        xmlhttp.open("POST",url,true);
        xmlhttp.send();
    }

    function loadXML() {
        var xmlhttp = new XMLHttpRequest();
        var url = "/BlackJack/getGameStats?roomName=<%=room.getRoomID().replace(" ","%20")%>";
        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200){
                showGameTable(this);
            }
        };

        xmlhttp.open("GET",url,true);
        xmlhttp.send();
    }



    function showGameTable(xml) {
        console.log("function showGameTable");
        document.getElementById("info").innerHTML = xml.responseText;
        var jsonResponse = JSON.parse(xml.responseText);

        var i;
        var players = jsonResponse.userCards;
        var cards;
        var total = 0;

        for (var iter = 0; iter<players.length; iter++){
            if (players[iter].userCards.playerID == "<%=user.getUserID()%>"){
                cards = players[iter].userCards.cards;
            }
        }

        console.log(cards);
        var htmlTable = "<table class='table'> <thead><tr><th> Cards </th></tr></thead><tbody>";
        for (i = 0; i < cards.length; i++) {
            htmlTable += "<tr><td>"+ (cards[i].cardKey + 1)  +"</td><tr> " ;
            if((cards[i].cardKey + 1) >=10){
                total = total + 10;
            }
            else{
                total = total + cards[i].cardKey + 1;
            }
        }

        if(total > 21){
            document.getElementById("hitButton").disabled =  true;
            document.getElementById("result").innerHTML = "Perdiste. Tu total fue de " + total;
        }

        htmlTable += "</tbody></table>";

        document.getElementById("myCards").innerHTML = htmlTable;
    }

    window.setInterval(loadXML, 1000);
</script>


<body>

<h3 class="display-3" >Room: <%= room.getRoomID() %></h3>
<h4 class="display-4" >Usuario: <%= user.getUserID() %></h4>
<div class="container">
    <div class="row justify-content-md-center">
        <div class="col col-lg-2">
            <button id="hitButton" class="btn btn-primary mb-2" onclick="doHit()">
                Dame Carta
            </button>
        </div>
        <div class="col col-lg-2">
            <button class="btn btn-primary mb-2" onclick="doStand()">
                Plantarse
            </button>
        </div>
        <div class="col col-lg-2">
            <form action="/BlackJack/exitGame" method="post">
                <button class="btn btn-primary mb-2" type="submit"> Salir </button>
            </form>
        </div>
    </div>
</div>
    <div id="info">

    </div>
    <div class="page-header" id="result">

    </div>
<div class="row justify-content-md-center table-responsive-sm">
    <div id = "myCards" class="col col-lg-6">
    </div>
</div>

</body>
</html>
