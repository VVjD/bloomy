package com.test.bloomy.controller;

import com.test.bloomy.dto.BoardDTO;
import com.test.bloomy.entity.MainCategory;
import com.test.bloomy.repository.MainCategoryRepository;
import com.test.bloomy.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value="/write")
    public String boardCreate (Model model) {

        List<MainCategory> categories = boardService.getAllCategories();
        model.addAttribute("categories", categories);

        return "board-add";
    }

    @PostMapping(value = "/writeok")
    public String boardCreateOk (@ModelAttribute BoardDTO boardDTO, @AuthenticationPrincipal UserDetails userDetails) {

        // 현재 로그인한 사용자의 username을 설정
        boardDTO.setUsername(userDetails.getUsername());

        log.info("받은 BoardDTO: {}", boardDTO);

        // 게시글 저장 및 문서 추가를 서비스 계층에서 처리
        boardService.createDocument(boardDTO);

        return "redirect:/blog";
    }

//    @GetMapping(value="/{id}}")
    @GetMapping(value = "/view")
    public String boardGet (Model model) {

        return "board-view";
    }


}
