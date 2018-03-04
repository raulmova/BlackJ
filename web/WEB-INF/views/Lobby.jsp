<%@ page import="com.raul.spring.models.User" %><%--
  Created by IntelliJ IDEA.
  User: rules
  Date: 3/1/2018
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lobby</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
    %>
    <h2 class="display-2" ><%= user.getUserID() %></h2>

    <form action="/BlackJack/game" method="post" class="form-inline">
        <div class="form-group mb-2 col col-lg-2">
            <label class="sr-only"> Room Name</label>
            <input type="text" readonly class="form-control-plaintext" id="helpTwo" value="Room Name">
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <input class="form-control" type="text" name="roomName" placeholder="Room Name"/>
        </div>
        <button class="btn btn-primary mb-2" type="submit"> Create Room </button>
    </form>


    <form action="/BlackJack/exitLobby" method="post">
        <div class="form-group mx-sm-3 mb-2">
            <button type="submit" class="btn btn-danger mb-2" >Salir</button>
        </div>
    </form>

    <div class="row justify-content-md-center table-responsive-sm">
        <div id = "table" class="col col-lg-6">
        </div>
    </div>

    <script>

        function loadXML() {
            var xmlhttp = new XMLHttpRequest();
            var url = "/BlackJack/getRooms";
            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200){
                    //show rooms table
                    showRoomsTable(this);
                }
            };

            xmlhttp.open("GET",url,true);
            xmlhttp.send();
        }

        function showRoomsTable(xml) {
            console.log(xml.responseText);
            var jsonResponse = JSON.parse(xml.responseText);

            var i;
            var rooms = jsonResponse['rooms'];
            var htmlTable = "<form  method=\"post\"> <table class='table' border='1'> <thead><tr><th> Room Number</th><th> Room Name</th><th> # Players</th><th>Join Room</th></tr></thead><tbody>";
            for (i = 0; i < rooms.length; i++) {
                htmlTable += "<tr><td> <input class='form-control-plaintext' name='rowNumber' readonly value='"+i+"'/></td><td> <input class='form-control-plaintext' readonly value='"+rooms[i].roomID +"'/></td><td>"+rooms[i].numberOfPlayers +"</td><td><div class=\"form-group mx-sm-3 mb-2\"><button type=\"submit\"  formaction=\"/BlackJack/joinRoom?roomID="+rooms[i].roomID+" \" class=\"btn btn-primary mb-2\" >Unirse</button></td></div></tr> " ;
            }
            htmlTable += "</tbody></table></form>";
            document.getElementById("table").innerHTML = htmlTable;
        }

        window.setInterval(loadXML, 1000);
    </script>

</body>
</html>
