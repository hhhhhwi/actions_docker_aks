package com.hwiyeon.actions_docker_aks.member.service;

import com.hwiyeon.actions_docker_aks.member.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.hwiyeon.actions_docker_aks.error.exception.MemberAlreadyExistsException;
import com.hwiyeon.actions_docker_aks.error.exception.MemberNotExistsException;
import com.hwiyeon.actions_docker_aks.error.exception.PasswordNotMatchedException;
import com.hwiyeon.actions_docker_aks.login.LoginMember;
import com.hwiyeon.actions_docker_aks.login.service.LoginMemberService;
import com.hwiyeon.actions_docker_aks.member.Member;
import com.hwiyeon.actions_docker_aks.member.MemberRole;
import com.hwiyeon.actions_docker_aks.member.dto.MemberRequest;
import com.hwiyeon.actions_docker_aks.member.dto.MemberResponse;

@Service
public class MemberService implements LoginMemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> findMembers() {
        return memberRepository.findAll().stream()
            .map(MemberResponse::new)
            .collect(Collectors.toList());
    }

    @Override
    public LoginMember getLoginMember(String email, String password) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(MemberNotExistsException::new);

        if (!member.isMatchedPassword(password)) {
            throw new PasswordNotMatchedException();
        }

        return new LoginMember(member.getId(), member.getName(), member.getRole());
    }

    public MemberResponse save(MemberRequest memberRequest) {
        if (memberRepository.findByEmail(memberRequest.getEmail()).isPresent()) {
            throw new MemberAlreadyExistsException();
        }

        Member member = memberRepository.save(
            new Member(memberRequest.getEmail(), memberRequest.getPassword(),
                memberRequest.getName(), MemberRole.MEMBER));

        return new MemberResponse(member);
    }
}
