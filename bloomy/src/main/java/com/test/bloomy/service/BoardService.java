package com.test.bloomy.service;

import com.test.bloomy.dto.BoardDTO;
import com.test.bloomy.entity.Board;
import com.test.bloomy.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void createDocument(BoardDTO boardDTO) {
        Board board = Board.builder()
                .username(boardDTO.getUsername())
                .boardTitle(boardDTO.getBoardTitle())
                .boardContent(boardDTO.getBoardContent())
                .seqMainCategory(boardDTO.getSeqMainCategory())
                .boardViews(0)
                .build();

        boardRepository.save(board);

        // 저장된 Board의 seq를 DTO에 설정 (필요한 경우)
        //boardDTO.setSeq(savedBoard.getSeq());
    }

}
