package com.test.bloomy.controller;

import com.test.bloomy.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/board")
@RequiredArgsConstructor
public class BoardController {

    @GetMapping(value="/write")
    public String boardCreate () {
        return "board-add";
    }

    @PostMapping(value = "/writeok")
    public String boardCreateOk () {

        return "redirect:/blog/board";
    }

//    @GetMapping(value="/{id}}")
    @GetMapping(value = "/view")
    public String boardGet () {
        return "board-view";
    }


}
