package com.test.bloomy;

import com.test.bloomy.dto.MemberDTO;
import com.test.bloomy.entity.Member;
import com.test.bloomy.repository.MemberRepository;
import com.test.bloomy.service.MemberService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @Disabled
    public void testSignup() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUsername("test");
        memberDTO.setPassword("1234");
        memberDTO.setName("테스트");
        memberDTO.setNickname("테스트");
        memberDTO.setBirth("20240628");
        memberDTO.setTel("01012345678");
        memberDTO.setAddress("주소");
        memberDTO.setEmail("test@example.com");

        memberService.signup(memberDTO);

        Member savedMember = memberRepository.findByUsername("test");
        assertNotNull(savedMember);
        assertEquals(memberDTO.getName(), savedMember.getName());
        assertEquals(memberDTO.getNickname(), savedMember.getNickname());
        assertTrue(bCryptPasswordEncoder.matches(memberDTO.getPassword(), savedMember.getPassword()));

    }
}
