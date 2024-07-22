package com.hwiyeon.actions_docker_aks.member.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hwiyeon.actions_docker_aks.member.dto.MemberRequest;
import com.hwiyeon.actions_docker_aks.member.dto.MemberResponse;
import com.hwiyeon.actions_docker_aks.member.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    ResponseEntity<List<MemberResponse>> getMembers() {
        return ResponseEntity.ok().body(memberService.findMembers());
    }

    @PostMapping
    ResponseEntity<MemberResponse> saveMember(@RequestBody MemberRequest memberRequest) {
        return ResponseEntity.ok().body(memberService.save(memberRequest));
    }
}
