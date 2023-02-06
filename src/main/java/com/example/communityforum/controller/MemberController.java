package com.example.communityforum.controller;

import com.example.communityforum.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService service;

    @GetMapping("/login")
    public String login() {
        return "/member/login_form";
    }
}
