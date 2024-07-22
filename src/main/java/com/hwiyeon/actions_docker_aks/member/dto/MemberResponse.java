package com.hwiyeon.actions_docker_aks.member.dto;


import com.hwiyeon.actions_docker_aks.member.Member;

public class MemberResponse {

    private Long id;

    private String email;

    private String name;

    public MemberResponse(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public MemberResponse(Member member) {
        this(member.getId(), member.getEmail(), member.getName());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
