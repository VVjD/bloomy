package com.test.bloomy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Blog")
public class Blog {

    @Id
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String blogName;

    @Column(nullable = false)
    private String blogIntro;

}