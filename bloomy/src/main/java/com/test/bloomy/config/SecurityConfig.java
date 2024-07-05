package com.test.bloomy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //bean을 등록할 때 싱글톤이 되도록 보장해줌
@EnableWebSecurity //스프링 시큐리티 활성화, 웹 보안 설정 구성하는 데 사용
public class SecurityConfig {

    //암호 인코더
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //대부분의 시큐리티 설정을 한다.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //URI 허가
        http.authorizeHttpRequests( auth -> auth
                .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/plugins/**").permitAll()
                .requestMatchers("/blog", "/login").permitAll()
                .requestMatchers("/signup", "/signupok").permitAll()
                //.requestMatchers("/blog").hasAnyRole("MEMBER")
                .anyRequest().authenticated() //나머지 경로 > 인증 사용자에게만 허가
        );

        //CSRF 공격을 막기 위해 토큰 등록
        //CSRF 비활성화
        http.csrf(auth -> auth.disable());

        //커스텀 로그인 페이지
        http.formLogin(auth -> auth
                .loginPage("/login")
                .loginProcessingUrl("/loginok")
                .defaultSuccessUrl("/blog")
                .permitAll()
        );

        //로그아웃
        http.logout(auth -> auth.logoutUrl("/logout")
                .logoutSuccessUrl("/blog"));

        return http.build();
    }

}
