package com.bbangjun.member.service;

import com.bbangjun.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSerice {
    private final MemberRepository memberRepository;
    public void save(MemberDto memberDto) {
    }
}
