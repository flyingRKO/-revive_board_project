package com.example.communityforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faq")
public class FaqController {

    @GetMapping
    String list(){
        return "faq/list";
    }

    @GetMapping("/register")
    String register(){
        return "faq/register";
    }
}
