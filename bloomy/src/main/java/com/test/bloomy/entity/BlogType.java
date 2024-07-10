package com.test.bloomy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BlogType")
public class BlogType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_type_seq_generator")
    @SequenceGenerator(name = "blog_type_seq_generator", sequenceName = "seqBlogType", allocationSize = 1)
    private Long seq;

    @Column(nullable = false)
    private String blogTypeName;
}