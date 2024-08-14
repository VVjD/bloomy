package com.test.bloomy.repository;

import com.test.bloomy.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b left join fetch b.mainCategory order by b.seq desc")
    List<Board> findAllWithMainCategory();

    @Query("select b from Board b left join fetch b.mainCategory where b.seq = :seq")
    Board findByIdWithMainCategory(@Param("seq") Long seq);

    Page<Board> findAllByBoardRegdateAtDesc(Pageable pageable);
}
