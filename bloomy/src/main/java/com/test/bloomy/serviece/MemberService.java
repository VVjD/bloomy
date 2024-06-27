package com.test.bloomy.serviece;

import com.test.bloomy.dto.MemberDTO;
import com.test.bloomy.entity.Member;
import com.test.bloomy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signup(MemberDTO memberDTO) {
        //아이디 중복 검사
        boolean result = memberRepository.existsByUsername(memberDTO.getUsername()); //예약어
        if (result) {
            return;
        }

        //DTO > Entity
        Member member = Member.builder()
                .username(memberDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(memberDTO.getPassword()))
                .name(memberDTO.getName())
                .nickname(memberDTO.getNickname())
                .birth(memberDTO.getBirth())
                .tel(memberDTO.getTel())
                .address(memberDTO.getAddress())
                .email(memberDTO.getEmail())
                .role("ROLE_MEMBER")
                .build();

        memberRepository.save(member);
    }
}
