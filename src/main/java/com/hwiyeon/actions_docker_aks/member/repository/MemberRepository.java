package com.hwiyeon.actions_docker_aks.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hwiyeon.actions_docker_aks.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
