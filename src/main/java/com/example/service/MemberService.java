package com.example.service;

import com.example.model.Member;
import com.example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    public void addMember(Member member) {
        memberRepository.save(member);
    }

    public List<Member> getMemberById(long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent())
        {
            return Arrays.asList(member.get());
        }
        return null;
    }

    public List<Member> getMembersByName(String memberName) {
        List<Member> memberList = memberRepository.findByMemberName(memberName);
        return memberList;
    }

    public List<Member> getMembersByEmail(String email) {
        List<Member> memberList = memberRepository.findByEmail(email);
        return memberList;
    }

    public List<Member> getMembersByPhone(String phone) {
        List<Member> memberList = memberRepository.findByPhone(phone);
        return memberList;
    }

    public List<Member> getMembers() {
        List<Member> memberList = memberRepository.findAll();
        return memberList;
    }

    public void deleteMember(long id) {
        memberRepository.deleteById(id);
    }

    public void updateBook(Member member) {
        memberRepository.save(member);
    }
}
