package com.test.bloomy.controller;

import com.test.bloomy.entity.MainCategory;
import com.test.bloomy.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice //모든 컨트롤러에 적용되는 전역
@RequiredArgsConstructor
public class GlobalModelAttributes {

    private final BoardService boardService;

    @ModelAttribute("categories") //@ModelAttribute 어노테이션 : 해당 컨트롤러의 모든 메서드에서 접근 가능
    public List<MainCategory> getCategories() {
        return boardService.getAllCategories();
    }

}
