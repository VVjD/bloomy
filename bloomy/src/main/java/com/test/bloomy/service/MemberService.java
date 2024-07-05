package com.test.bloomy.service;

import com.test.bloomy.dto.MemberDTO;
import com.test.bloomy.entity.Member;
import com.test.bloomy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //final 필드에 대한 생성자 자동 생성
public class MemberService {

    private final MemberRepository memberRepository; //데이터베이스 작업을 위한 리퍼지토리
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //비밀번호 암호화를 위한 인코더

    public void signup(MemberDTO memberDTO) {
        //아이디 중복 검사
        boolean result = memberRepository.existsByUsername(memberDTO.getUsername()); //예약어
        if (result) {
            return;
        }

        //DTO > Entity
        Member member = Member.builder()
                .username(memberDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(memberDTO.getPassword())) //비밀번호 암호화
                .name(memberDTO.getName())
                .nickname(memberDTO.getNickname())
                .birth(memberDTO.getBirth())
                .tel(memberDTO.getTel())
                .address(memberDTO.getAddress())
                .email(memberDTO.getEmail())
                .role("ROLE_MEMBER")
                .build();

        //회원 정보 저장
        memberRepository.save(member);
    }
}
