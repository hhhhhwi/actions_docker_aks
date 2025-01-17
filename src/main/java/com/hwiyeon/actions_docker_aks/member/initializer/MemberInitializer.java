package com.hwiyeon.actions_docker_aks.member.initializer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.hwiyeon.actions_docker_aks.member.Member;
import com.hwiyeon.actions_docker_aks.member.MemberRole;
import com.hwiyeon.actions_docker_aks.member.repository.MemberRepository;

@Component
public class MemberInitializer implements ApplicationRunner {

    public static final String FIRST_USER_EMAIL = "first@test.com";
    public static final String SECOND_USER_EMAIL = "second@test.com";
    public static final String FIRST_USER_PASSWORD = "password";
    public static final String SECOND_USER_PASSWORD = "password";
    public static final String FIRST_USER_NAME = "first";
    public static final String SECOND_USER_NAME = "second";
    public static final String DUMMY_ADMIN_EMAIL = "admin@test.com";
    public static final String DUMMY_ADMIN_PASSWORD = "password";
    public static final String DUMMY_ADMIN_NAME = "admin";

    private final MemberRepository memberRepository;

    public MemberInitializer(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memberRepository.save(new Member(FIRST_USER_EMAIL, FIRST_USER_PASSWORD, FIRST_USER_NAME, MemberRole.MEMBER));
        memberRepository.save(new Member(SECOND_USER_EMAIL, SECOND_USER_PASSWORD, SECOND_USER_NAME, MemberRole.MEMBER));
        memberRepository.save(new Member(DUMMY_ADMIN_EMAIL, DUMMY_ADMIN_PASSWORD, DUMMY_ADMIN_NAME, MemberRole.ADMIN));
    }
}
