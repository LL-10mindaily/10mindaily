package com.ll.tenmindaily.base.rq;

import com.ll.tenmindaily.boundedContext.member.entity.Member;
import com.ll.tenmindaily.boundedContext.member.service.MemberService;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import jakarta.servlet.http.HttpServlet;
>>>>>>> 3630690 (Nagiltae (#9))
=======
>>>>>>> 1e04560 (feat: 네비게이션바 로그인, 로그아웃 관련 기능 구현 및 rq 도입)
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class Rq {

    private final MemberService memberService;
    private final User user;
    private Member member = null;

    public Rq(MemberService memberService) {
        this.memberService = memberService;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof User) {
            this.user = (User) authentication.getPrincipal();
        } else {
            this.user = null;
        }
    }

    public boolean isLogin() {
        return user != null;
    }

    public boolean isLogout() {
        return !isLogin();
    }

    public Member getMember() {
        if (isLogout()) return null;

        // 데이터가 없는지 체크
        if (member == null) {
            member = memberService.findByUserId(user.getUsername()).orElseThrow();
        }

        return member;
    }

}