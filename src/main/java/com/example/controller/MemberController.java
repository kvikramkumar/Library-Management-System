package com.example.controller;

import com.example.model.Book;
import com.example.model.Member;
import com.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/manage-members.html")
    public ModelAndView manageBooks(){
        ModelAndView model = new ModelAndView();
        model.setViewName("manage-members");
        return model;
    }

    @GetMapping("/add-member.html")
    public ModelAndView addBookPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("add-member");
        return model;
    }

    @PostMapping("/members")
    public ResponseEntity<Map<String,String>> addBook(@RequestBody Member member){

        System.out.println(member);
        Map<String,String> response = new HashMap<>();
        response.put("message", " Member is added");

        memberService.addMember(member);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/members")
    public List<Member> getBooks(
            @RequestParam(required = false) String memberId,
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone) {


        if (memberId != null) {
            return memberService.getMemberById(Long.parseLong(memberId));
        } else if (memberName != null) {
            return memberService.getMembersByName(memberName);
        } else if (email != null) {
            return memberService.getMembersByEmail(email);
        } else if (phone != null) {
            return memberService.getMembersByPhone(phone);
        } else {
            return memberService.getMembers(); // Return all members if no criteria is specified
        }
    }

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<Map<String,String>> deleteBook(@PathVariable("memberId") String id){
        memberService.deleteMember(Long.parseLong(id));
        Map<String,String> response = new HashMap<>();
        response.put("message","Member is removed");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/members")
    public ResponseEntity<Map<String,String>> updateBook(@RequestBody Member member){

        Map<String,String> response = new HashMap<>();
        response.put("message", " Member is updated");

        System.out.println(member);
        memberService.updateBook(member);
        return ResponseEntity.ok(response);
    }
}
