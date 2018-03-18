package com.raul.spring;


import com.raul.spring.models.Room;
import com.raul.spring.models.User;
import com.raul.spring.models.UserCard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Vector;

@Controller
public class RoomManager {

    public static Vector<Room> rooms = new Vector<>();


    @RequestMapping(value = {"/game"})
    public ModelAndView newRoom(@RequestParam(value = "roomName") String roomName){

        if (isInRooms(roomName)) {
            ModelAndView mavtemp = new ModelAndView("generalView");
            mavtemp.addObject("message","Room ya existe");
            return mavtemp;
        }else {
            rooms.add(new Room(roomName));
            ModelAndView mavtemp = new ModelAndView("Lobby");
            return mavtemp;
        }
    }

    @RequestMapping(value = {"/joinRoom"})
    public ModelAndView joinRoom(HttpSession session, @RequestParam(value = "roomID") String roomName){
        //System.out.println("JOIN ROOM: "+roomName);
        User user = (User)session.getAttribute("user");
        //System.out.println("ROW: " + row);
        if(user.getRoomID() == ""){
            Room tempRoom = getRoomByID(roomName);
            tempRoom.addNewPlayer(user.getUserID());
            ModelAndView mav = new ModelAndView("Game");
            session.setAttribute("room", tempRoom);
            user.setRoomID(roomName);
            return mav;
        }else{
            //System.out.println("Usuario ya esta en una room");
            ModelAndView mavtemp = new ModelAndView("generalView");
            mavtemp.addObject("message","Usuario ya en una Room");
            return mavtemp;
        }
    }

    public static Room getRoomByID(String roomName) {
        for (Room room : rooms){
            if(roomName.equals(room.getRoomID())){
                return room;
            }
        }
        return null;
    }

    @RequestMapping(value = {"/getRooms"})
    @ResponseBody
    public String getRooms(HttpServletResponse response){
        response.setContentType("application/json");
        String jsonArray = "{ \"rooms\":  [ ";
        for (int i = 0; i < rooms.size(); i++) {
            jsonArray += rooms.get(i).stringToJSON();
            if (i != rooms.size()-1){
                jsonArray+= ",";
            }
        }
        return jsonArray + "]}";
    }

    public static boolean isInRooms(String roomID){
        for (Room room : rooms){
            if(roomID.equals(room.getRoomID())){
                return true;
            }
        }
        return false;
    }

    /*
    public static void updateRoom(String roomID){
        for (Room room : rooms){
            if(roomID.equals(room.getRoomID())){
                room.newPlayer();
                //room.setNumberOfPlayers(room.getNumberOfPlayers() + 1);
            }
        }
    }
    */

}
