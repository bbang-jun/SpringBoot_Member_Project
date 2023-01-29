package com.bbangjun.member.controller;

import com.bbangjun.member.dto.MemberDTO;
import com.bbangjun.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping("/member/")
    public String findAll(Model model){
        // db에서 저장되어 있는 모든 정보를 가져온다는 개념.
        // 회원하는 여러 개의 데이터이므로 java util에서 제공하는 List<> 사용
        // 특정 html로 가져갈 데이터가 있다면 model 객체 사용
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    @GetMapping("/member/{id}") // {}: 이 경로에 있는 값을 취하겠다는 의미
    public String findById(@PathVariable Long id, Model model){ // 취한 값을 받아주는 어노테이션이 @PathVariable임.
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("member/update")
    public String updateForm(HttpSession session, Model model){
        // getAttribute 메서드는 return type이 Object인데 String에 담으려고 해서 오류 발생
        // 따라서 String으로 강제 형 변환 진행(캐스팅)
        String myEmail = (String) session.getAttribute("loginEmail");
        // 이메일로 조회해서 memberDTO로 가져옴
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        // model에 조회한 데이터들을 담아서 update.html로 이동
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("member/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("member/delete/{id}")
    public String deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        // redirect 잊지 말기!
        return "redirect:/member/";
    }
}
