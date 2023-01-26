package com.bbangjun.member.service;

import com.bbangjun.member.dto.MemberDTO;
import com.bbangjun.member.entity.MemberEntity;
import com.bbangjun.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // 1. dto 객체를 entity 객체로 변환 (MemberEntity에서 진행)
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO); // entity class에다가 만듬
        // 2. repository의 save 메서드 호출(조건: entity 객체를 넘겨 주어야 함)
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        // 1. 회원이 입력한 이메일로 DB에서 조회를 함
        // 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        // 이 방법은 원시적인 방법임
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()){
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            MemberEntity memberEntity = byMemberEmail.get(); // optional 객체.get()을 하면 optional 안의 객체를 가져올 수 있음
            if(memberEntity.getMemberPassword().equals((memberDTO.getMemberPassword()))){
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴(MemberController로) // dto class에다가 만듬
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }
            else{
                // 비밀번호 불일치(로그인 실패)
                return null;
            }
        }
        else{
            // 조회 결과가 없다(해당 이메일을 가진 회원 정보가 없다)
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        // repository이므로 Entity 객체
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        // entity list 객체를 dto list 객체로 controller에 주어야 하므로 변환(데이터가 1개가 아니라서 단순 대입만으로는 안됨)
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemberEntity memberEntity: memberEntityList){ // for each 문법으로 변환
            // 1번째 방법
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
            // 2번째 방법
            //MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            //memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }
}

