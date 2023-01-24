package com.bbangjun.member.controller;

import com.bbangjun.member.dto.MemberDto;
import com.bbangjun.member.service.MemberSerice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberSerice memberSerice;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save"; // save.html
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDto memberDto){
        System.out.println("MemberController.save");
        System.out.println("memberDto = " + memberDto);
        memberSerice.save(memberDto);
        return "index"; // index.html 화면으로 이동
    }
}
