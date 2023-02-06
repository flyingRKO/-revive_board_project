package com.example.communityforum.controller;

import com.example.communityforum.domain.Faq;
import com.example.communityforum.domain.constants.FaqType;
import com.example.communityforum.dto.FaqRegisterForm;
import com.example.communityforum.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

    private final FaqService faqService;

    @GetMapping
    String list(
            @PageableDefault(size = 6, sort = "registeredDate", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map){

        Page<Faq> faqs = faqService.findAll(pageable);
        map.addAttribute("faqList", faqs);
        return "faq/list";
    }

    @PostMapping
    public String listByType(@PageableDefault(size = 6, sort = "registeredDate", direction = Sort.Direction.DESC) Pageable pageable,
                             ModelMap map, @RequestParam String faqTypeStr) {

        FaqType faqType;
        Page<Faq> faqs;
        try{
            faqType = FaqType.valueOf(faqTypeStr);
            faqs = faqService.findAllByType(pageable, faqType);
        }
        catch (IllegalArgumentException e){
            faqs = faqService.findAll(pageable);
        }

        map.addAttribute("faqList", faqs);

        return "faq/list-by-type";
    }

    @GetMapping("/register")
    String register(){

        return "faq/register";
    }

    @PostMapping("/register")
    public String save(@Valid FaqRegisterForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "faq/register";
        }

        // form -> Faq 도메인으로 데이터 set 후 build
        try{
            faqService.create(form.getTitle(),
                    form.getContent(),
                    FaqType.values()[Integer.parseInt(form.getType())],
                    LocalDateTime.now());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            bindingResult.reject("faq register failed", e.getMessage());
            return "faq/register";
        }

        return "redirect:/faq";
    }
}
