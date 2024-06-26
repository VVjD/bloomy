package com.test.bloomy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping(value = "/blog")
    public String blog() {
        return "blog-view";
    }

}
