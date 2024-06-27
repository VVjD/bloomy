package com.test.bloomy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {

    private String username;
    private String password;
    private String name;
    private String nickname;
    private String birth;
    private String tel;
    private String address;
    private String email;
    private String regdate;
    private String role;
    private String image;

}
