package com.raul.spring;

import com.raul.spring.models.Room;
import com.raul.spring.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class GameManager {
    @RequestMapping(value = {"/hit"})
    public ModelAndView giveMeCard(@RequestParam(value = "roomName") String roomName){

        return null;
    }

}
