package com.test.bloomy.service;

import com.test.bloomy.dto.BoardDTO;
import com.test.bloomy.entity.Board;
import com.test.bloomy.entity.MainCategory;
import com.test.bloomy.repository.BoardRepository;
import com.test.bloomy.repository.MainCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

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

    public List<Board> list() {

        List<Board> list = boardRepository.findAll();

//        List<Board> list = boardRepository.findAllWithMainCategory();

        return list;
    }

    public Board get(Long seq) {

        Board board = boardRepository.findByIdWithMainCategory(seq);

        return board;
    }

    public void deleteDocument(Long seq, String username) throws AccessDeniedException {

        Board board = boardRepository.findById(seq)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if(!board.getUsername().equals(username)) {
            throw new AccessDeniedException("해당 게시글을 삭제할 수 없습니다.");
        }

        log.info("게시글 삭제 : {}", seq);
        boardRepository.deleteById(seq);
    }

    public void updateDocument(BoardDTO boardDTO) {
        log.info("게시글 수정 시작: {}", boardDTO);

        Board board = boardRepository.findById(boardDTO.getSeq())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        if (!board.getUsername().equals(boardDTO.getUsername())) {
            throw new RuntimeException("게시글을 수정할 권한이 없습니다.");
        }

        Board updatedBoard = board.toBuilder()
                .boardTitle(boardDTO.getBoardTitle())
                .boardContent(boardDTO.getBoardContent())
                .seqMainCategory(boardDTO.getSeqMainCategory())
                .build();

        boardRepository.save(updatedBoard);

        log.info("게시글 수정 완료: {}", updatedBoard);

    }
}
