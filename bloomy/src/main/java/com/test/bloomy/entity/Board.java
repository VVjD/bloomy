package com.test.bloomy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder(toBuilder = true) //수정을 허용하기 위해서 toBuilder 필요
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_generator")
    @SequenceGenerator(name = "board_seq_generator", sequenceName = "seqBoard", allocationSize = 1)
    private Long seq;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContent;

    @Column(nullable = false)
    @Builder.Default
    private LocalDate boardRegdate = LocalDate.now();

    @Column(nullable = false)
    private Integer boardViews;

    @Column(nullable = false, length = 1)
    @Builder.Default
    private String boardSecret = "n";
    
    //카테고리명을 가져오기 위한 조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seqMainCategory", insertable = false, updatable = false)
    private MainCategory mainCategory;

    @Column(nullable = false)
    private Long seqMainCategory;

    @Column
    private Long seqSubCategory;

}