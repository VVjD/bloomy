package com.test.bloomy.service;

import com.test.bloomy.dto.BoardDTO;
import com.test.bloomy.entity.Board;
import com.test.bloomy.entity.MainCategory;
import com.test.bloomy.repository.BoardRepository;
import com.test.bloomy.repository.MainCategoryRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j //로깅 프레임워크
public class BoardService {

    private final BoardRepository boardRepository;
    private final MainCategoryRepository mainCategoryRepository;

    public List<MainCategory> getAllCategories() {
        return mainCategoryRepository.findAll();
    }

    public void createDocument(BoardDTO boardDTO) {

        log.info("게시글 작성 시작: {}", boardDTO);

        Board board = Board.builder()
                .username(boardDTO.getUsername())
                .boardTitle(boardDTO.getBoardTitle())
                .boardContent(boardDTO.getBoardContent())
                .seqMainCategory(boardDTO.getSeqMainCategory())
                .boardViews(0)
                .build();

        log.info("생성된 Board 엔티티: {}", board);

        boardRepository.save(board);

        log.info("게시글 저장 완료");

        // 저장된 Board의 seq를 DTO에 설정 (필요한 경우)
        //boardDTO.setSeq(savedBoard.getSeq());
    }

}
