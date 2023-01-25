package com.bbangjun.member.service;

import com.bbangjun.member.dto.MemberDTO;
import com.bbangjun.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
    }
}
