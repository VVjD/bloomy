package com.test.bloomy.controller;

import com.test.bloomy.dto.MemberDTO;
import com.test.bloomy.serviece.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/signup")
    public String signup() {

        return "signup";
    }

    @PostMapping(value = "/signupok")
    public String signupok(MemberDTO memberDTO) {

        System.out.println("dto : " + memberDTO);

        memberService.signup(memberDTO);

        return "redirect:/login";
    }

    @GetMapping(value = "/blog")
    public String blog() {
        return "blog-view";
    }

}
