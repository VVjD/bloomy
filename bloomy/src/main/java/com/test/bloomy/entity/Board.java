package com.test.bloomy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@ToString
@Builder
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

    @Lob
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

    @Column(nullable = false)
    private Long seqMainCategory;

    @Column
    private Long seqSubCategory;

}