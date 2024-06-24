package org.cbox.todo1.security;

import java.util.stream.Collectors;

import org.cbox.todo1.domain.Member;
import org.cbox.todo1.dto.MemberDTO;
import org.cbox.todo1.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService{

    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("......loadUserByUsername......");
        log.info(username);
        log.info("......loadUserByUsername......");
        Member member = memberRepository.getWithRoles(username);



        if (member == null) {
            throw new UsernameNotFoundException("Not Found");
            
        }

        MemberDTO memberDTO = new MemberDTO(
            member.getEmail(), 
            member.getPw(), 
            member.getNickname(), 
            member.isSocial(),
             member.getMemberRoleList().stream().map(memberRole -> memberRole.name()).collect(Collectors.toList()));

        log.info(memberDTO);

        return memberDTO;
    }
    
}
