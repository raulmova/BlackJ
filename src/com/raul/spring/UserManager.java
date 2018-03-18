package com.raul.spring;

import com.raul.spring.models.Room;
import com.raul.spring.models.User;
import com.raul.spring.models.UserCard;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Vector;

import static com.raul.spring.GameManager.findUserCardByPlayerID;

@Controller
public class UserManager {

    public static Vector<User> users = new Vector<>();

    @RequestMapping(value = {"/lobby"})
    public ModelAndView getToken(HttpSession session, @RequestParam(value = "userid") String userID){

        if (isInUsers(userID)){
            ModelAndView mavtemp = new ModelAndView("generalView");
            mavtemp.addObject("message","Tu UserId ya existe");
            return mavtemp;

        } else {

            ModelAndView mav = new ModelAndView("Lobby");
            User newUser = new User(userID,createToken(),"");

            session.setAttribute("user", newUser);

            users.add(newUser);
            System.out.println(users.toString());
            mav.addObject("token", newUser.getToken());
            return mav;
        }

    }


    //For the Home View
    @RequestMapping(value = {"/"})
    public ModelAndView getHome(){

        ModelAndView mav = new ModelAndView("Home");
        return mav;
    }

    @RequestMapping(value = {"/exitLobby"})
    public ModelAndView signOut(HttpSession session){
        User tempUser = (User)session.getAttribute("user");
        users.remove(tempUser);
       // System.out.println(users.toString());
        ModelAndView mav = new ModelAndView("Home");
        return mav;
    }

    @RequestMapping(value = {"/exitGame"})
    public ModelAndView exitGame(HttpSession session){
        User tempUser = (User)session.getAttribute("user");
        users.remove(tempUser);
        Room room = (Room)session.getAttribute("room");
       // System.out.println("Exit game: "+room.toString());
        room.setNumberOfPlayers(room.getNumberOfPlayers()-1);
        System.out.println(users);
        UserCard usercard = findUserCardByPlayerID(tempUser.getUserID(), room.getRoomID());
        room.getUserCards().remove(usercard);
        ModelAndView mav = new ModelAndView("Home");
        return mav;
    }

    public static String createToken(){
        //TODO
        //More elegant token Generator
        String token = ""+Math.random();
        //tokens.add(token);
        //System.out.println(tokens.toString());
        return token;
    }

    public static boolean isInUsers(String userID){
        for (User user : users){
            if(userID.equals(user.getUserID())){
                return true;
            }
        }
        return false;
    }

}
