package com.test.bloomy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MainCategory")
public class MainCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_category_seq_generator")
    @SequenceGenerator(name = "main_category_seq_generator", sequenceName = "seqMainCategory", allocationSize = 1)
    private Long seq;

    @Column(nullable = false)
    private String mainCategoryName;

    @Column(nullable = false)
    @Builder.Default
    private String mainCategorySecret = "n";

    @Column(nullable = false)
    private String username;

}