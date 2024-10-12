package com.example.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long memberId;

    @Column(name = "name")
    private String memberName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "membership_date",insertable = false,updatable = false)
    private LocalDateTime memberShipDate;

    public Member(String memberName, String email, String phone, String address, LocalDateTime memberShipDate) {
        this.memberName = memberName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.memberShipDate = memberShipDate;
    }

    public Member() {
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberName='" + memberName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", memberShipDate=" + memberShipDate +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getMemberShipDate() {
        return memberShipDate;
    }

    public Long getMemberId() {
        return memberId;
    }
}
