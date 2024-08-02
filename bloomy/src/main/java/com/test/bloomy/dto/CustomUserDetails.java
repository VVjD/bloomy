package com.test.bloomy.dto;

import com.test.bloomy.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//인증 사용자 정보 객체
public class CustomUserDetails implements UserDetails {

    //사용자 추가 정보 저장용
    private Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }
    
    //로그인한 사용자의 role 기반
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    public String getProfileImage() {
        return member.getImage();
    }

    @Override
    public boolean isAccountNonExpired() {

        //계정 만료?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        //잠금?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //자격 증명 만료
        return true;
    }

    @Override
    public boolean isEnabled() {
        //사용 유무
        return true;
    }
}
