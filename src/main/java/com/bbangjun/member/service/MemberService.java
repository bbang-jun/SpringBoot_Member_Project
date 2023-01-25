package com.bbangjun.member.service;

import com.bbangjun.member.dto.MemberDTO;
import com.bbangjun.member.entity.MemberEntity;
import com.bbangjun.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // 1. dto 객체를 entity 객체로 변환 (MemberEntity에서 진행)
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        // 2. repository의 save 메서드 호출(조건: entity 객체를 넘겨 주어야 함)
        memberRepository.save(memberEntity);
    }
}
