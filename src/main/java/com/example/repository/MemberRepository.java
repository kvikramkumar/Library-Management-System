package com.example.repository;

import com.example.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByMemberName(String memberName);

    List<Member> findByEmail(String email);

    List<Member> findByPhone(String phone);
}
