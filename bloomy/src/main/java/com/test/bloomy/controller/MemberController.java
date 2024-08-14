package com.test.bloomy.controller;

import com.test.bloomy.dto.MemberDTO;
import com.test.bloomy.entity.Board;
import com.test.bloomy.service.BoardService;
import com.test.bloomy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BoardService boardService;

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

}
