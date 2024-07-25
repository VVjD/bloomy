package com.test.bloomy.controller;

import com.test.bloomy.dto.BoardDTO;
import com.test.bloomy.dto.MemberDTO;
import com.test.bloomy.entity.Board;
import com.test.bloomy.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blog/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value="/write")
    public String boardCreate () {
        return "board-add";
    }

    @PostMapping(value = "/writeok")
    public String boardCreateOk (@ModelAttribute BoardDTO boardDTO, @AuthenticationPrincipal UserDetails userDetails) {

        // 현재 로그인한 사용자의 username을 설정
        boardDTO.setUsername(userDetails.getUsername());

        // 게시글 저장 및 문서 추가를 서비스 계층에서 처리
        boardService.createDocument(boardDTO);

        return "redirect:/blog/board";
    }

//    @GetMapping(value="/{id}}")
    @GetMapping(value = "/view")
    public String boardGet () {
        return "board-view";
    }


}
