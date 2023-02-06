package com.example.communityforum.controller;

import com.example.communityforum.dto.MemberCreateForm;
import com.example.communityforum.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "/member/login_form";
    }

    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm) {
        return "/member/signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/member/signup_form";
        }

        if (!memberCreateForm.getMemberPassword().equals(memberCreateForm.getMemberPassword2())) {
            bindingResult.rejectValue("memberPassword2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "/member/signup_form";
        }

        try {
            memberService.create(
                    memberCreateForm.getMemberId(),
                    memberCreateForm.getMemberPassword(),
                    memberCreateForm.getName(),
                    memberCreateForm.getPhone(),
                    memberCreateForm.getTerm()
            );
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자 ID 입니다.");
            return "/member/signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "/member/signup_form";
        }

        return "redirect:/";
    }
}
