package com.example.communityforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/boards")
@Controller
public class BoardController {

    @GetMapping()
    public String boards(ModelMap map) {
        map.addAttribute("boards", List.of());
        return "boards/index";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable Long boardId, ModelMap map){
        map.addAttribute("board", "board");
        map.addAttribute("boardComments", List.of());

        return "boards/detail";

    }


}
