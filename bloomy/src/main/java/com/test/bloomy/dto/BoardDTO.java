package com.test.bloomy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {

    private Long seq;
    private String username;
    private String boardTitle;
    private String boardContent;
    private Long seqMainCategory;
    private Integer boardViews;
    private String boardSecret;

    private String mainCategoryName;

}
