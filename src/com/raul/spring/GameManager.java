package com.raul.spring;

import com.raul.spring.models.Room;
import com.raul.spring.models.User;
import com.raul.spring.models.UserCard;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Vector;

@Controller
public class GameManager {

    //public static Integer[] originalDeck = new Integer[52];

    @RequestMapping(value = {"/hit"})
    public ModelAndView giveMeCard(@RequestParam(value = "roomName") String roomName, @RequestParam(value = "token") String token){
        User user = findUserByToken(token);
        System.out.println("TOKEN del HIT " +token);
        if(user != null){
            UserCard usercard = findUserCardByPlayerID(user.getUserID(), roomName);
            if (usercard!= null){
                if(usercard.getStatus().equals(UserCard.ON_TURN)){
                    Room auxRoom = RoomManager.getRoomByID(roomName);
                    int randomIndex = (int) (Math.random() * 51) % auxRoom.getAvailableCards().size();
                    int newCard = auxRoom.getAvailableCards().get(randomIndex);
                    auxRoom.getAvailableCards().remove(randomIndex);
                    usercard.getCards().add(newCard);
                    if (getHandValue(usercard.getCards()) >= 21){
                        System.out.println(getHandValue(usercard.getCards()));
                        auxRoom.nextTurn();
                    }
                }
            }
            else {
                return null;
            }
        }
        return null;
    }

    @RequestMapping(value = {"/getGameStats"})
    @ResponseBody

    public String getGameStats(@RequestParam(value = "roomName") String roomName, HttpServletResponse response){
        Room room = RoomManager.getRoomByID(roomName);
        response.setContentType("application/json");
        return room.getGameStatsJSON();
    }

    public int getHandValue(Vector<Integer> hand){
        int sum = 0;
        for (int value: hand){
            if(value >= 10){
                sum = sum + 10;
            }
            else {
                sum = sum + value + 1;
            }
        }
        return sum;
    }


    public static User findUserByToken(String token){
        for (User user : UserManager.users){
            if(token.equals(user.getToken())){
                return user;
            }
        }
        return null;
    }

    public static UserCard findUserCardByPlayerID(String playerID, String roomID){
        Room auxRoom = RoomManager.getRoomByID(roomID);
        for (UserCard usercard : auxRoom.getUserCards()){
            if(usercard.getPlayerID().equals(playerID)){
                return usercard;
            }
        }
        return null;
    }


    public static Vector<Integer> generateDeck(){
        Vector<Integer> deck = new Vector<>();
        for (int i= 0; i<13;i++){
            deck.add(i);
            deck.add(i);
            deck.add(i);
            deck.add(i);
        }
        return deck;
    }

}

