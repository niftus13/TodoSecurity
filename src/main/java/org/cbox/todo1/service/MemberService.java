package org.cbox.todo1.service;

import java.util.stream.Collectors;

import org.cbox.todo1.domain.Member;
import org.cbox.todo1.domain.MemberRole;
import org.cbox.todo1.dto.MemberDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {

    MemberDTO get(String email);

    MemberDTO MakeSocialMember(String email);

    
    default MemberDTO entityToDTO (Member member){

        MemberDTO memberDTO = new MemberDTO(
            member.getEmail(),
            member.getPw(),
            member.getNickname(),
            member.isSocial(),
            member.getMemberRoleList().stream().map(memberRole -> memberRole.name()).collect(Collectors.toList())
        );

        return memberDTO;
    }

    default Member dtoTOEntity (MemberDTO memberDTO) {


        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .pw(memberDTO.getPw())
                .nickname(memberDTO.getNickname())
                .social(memberDTO.isSocial())
                .build();

        for (String roleName : memberDTO.getRoleNames()) {

            member.addRole(MemberRole.valueOf(roleName));
        }

        return member;
    }
}
