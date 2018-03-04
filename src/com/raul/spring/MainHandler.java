package com.raul.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MainHandler {

    @RequestMapping(value = {"/main"}) //va a contestar esta funcion cuando vayas al /main

    public ModelAndView doMain(){

        ModelAndView myFinalView = new ModelAndView("generalView");
        myFinalView.addObject("message", "Hello There!");
        myFinalView.addObject("secondMessage", "Bye!");


        return myFinalView;
    }

    @RequestMapping(value = {"/restCall"})
    @ResponseBody
    public String restCall(HttpServletResponse response){
        response.setContentType("application/json");
        return "{\"data\":123}";
    }

    @RequestMapping(value = {"/api/{user}"})
    @ResponseBody
    public String restGetCall(@PathVariable("user") String user){
        return "<html>" + user + "</html>";
    }

}
