package com.test.bloomy.controller;

import com.test.bloomy.dto.BoardDTO;
import com.test.bloomy.entity.Board;
import com.test.bloomy.entity.MainCategory;
import com.test.bloomy.repository.BoardRepository;
import com.test.bloomy.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Controller
@RequestMapping("/blog/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

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

    @GetMapping(value = "/view/{seq}")
    public String boardGet (Model model, @PathVariable Long seq) { //@PathVariable : 경로 변수를 표시하기 위한 매개 변수

        Board board = boardService.get(seq);

        model.addAttribute("board", board);

        return "board-view";
    }

    @DeleteMapping(value = "/delete/{seq}")
    public ResponseEntity<String> boardDelete (@PathVariable Long seq, @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {

        try {
            //게시글 삭제
            boardService.deleteDocument(seq, userDetails.getUsername());
            return ResponseEntity.ok().body(("게시글이 성공적으로 삭제되었습니다."));
        } catch (Exception e) {
            log.error("게시글 삭제 오류 : seq = {}", seq, e);
            return ResponseEntity.badRequest().body("게시글 삭제에 실패하였습니다.");
        }
    }


}
