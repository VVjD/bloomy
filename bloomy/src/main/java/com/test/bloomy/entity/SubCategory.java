package com.test.bloomy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SubCategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_category_seq_generator")
    @SequenceGenerator(name = "sub_category_seq_generator", sequenceName = "seqSubCategory", allocationSize = 1)
    private Long seq;

    @Column(nullable = false)
    private String subCategoryName;

    @Column(nullable = false)
    @Builder.Default
    private String subCategorySecret = "n";

    @Column(nullable = false)
    private Long seqMainCategory;

}