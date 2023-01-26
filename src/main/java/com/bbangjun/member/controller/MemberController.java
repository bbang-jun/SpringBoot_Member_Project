package com.bbangjun.member.controller;

import com.bbangjun.member.dto.MemberDTO;
import com.bbangjun.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save"; // save.html
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save"); // soutm
        System.out.println("memberDTO = " + memberDTO); // soutv
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        System.out.println("MemberController.login");
        System.out.println("memberDTO = " + memberDTO);
        // memberService의 login 메서드에서 로그인을 성공한 case일 때 loginResult에 담김
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult!=null){
            // login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail()); // 로그인 한 회원의 이메일 정보를 loginEmail 세션에 담아줌
            return "main";
        }
        else{
            // login 실패
            return "login";
        }
    }
}
