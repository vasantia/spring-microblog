package com.theironyard;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasantia on 7/18/16.
 */

@Controller
public class SpringMicroblogController {

    public static final String SESSION_USER = "userName";
    List<Message> messages = new ArrayList<>();

    @RequestMapping(path ="/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
    String userName = (String) session.getAttribute(SESSION_USER);
    model.addAttribute("name", userName);
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(HttpSession session, String login){
        session.setAttribute(SESSION_USER, login);
        return "redirect:/";
    }

    @RequestMapping(path="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path="/add-message", method = RequestMethod.POST)
    public String addMessage(String text){

        int id = messages.size() + 1;
        Message message = new Message(id, text);
        messages.add(message);
        return "redirect:/";
    }

    @RequestMapping(path="/delete-message", method = RequestMethod.POST)
    public String deleteMessage(HttpSession session, int id){
        session.getAttribute("id");
        messages.remove(id-1);
        return "redirect:/";

    }

}
