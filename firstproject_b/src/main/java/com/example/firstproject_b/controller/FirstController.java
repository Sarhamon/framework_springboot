package com.example.firstproject_b.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {


    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "이태경");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname", "이태경");
        return "goodbye";
    }
}
