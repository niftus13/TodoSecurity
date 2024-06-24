package org.cbox.todo1.service;

import org.cbox.todo1.domain.Member;
import org.cbox.todo1.dto.MemberDTO;
import org.cbox.todo1.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService{
   
    private final MemberRepository memberRepository;
   
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberDTO get(String email) {

        Member member = memberRepository.getWithRoles(email);
        return entityToDTO(member);
    }

    @Override
    public MemberDTO MakeSocialMember(String email) {

    //            Member member = Member.builder()
    //            .email(email)
    //            .social(true)
    //            .nickname("Social Member")
    //            .pw(passwordEncoder.encode("1111"))
    //            .build();

    //    member.addRole(MemberRole.USER);

    //    Member result = memberRepository.save(member);

    //    return entityToDTO(result);

    return null;

    }
    


}
