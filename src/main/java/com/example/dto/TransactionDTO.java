package com.example.dto;

import java.time.LocalDateTime;

public class TransactionDTO {


    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    private Long transactionId;

    private Long bookId;

    private String bookTitle;

    private String author;

    private Long memberId;

    private String memberName;

    private String phone;

    public Long getTransactionId() {
        return transactionId;
    }

    private LocalDateTime issueDate;

    private LocalDateTime dueDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public TransactionDTO(Long bookId, String bookTitle, String author, Long memberId, String memberName, String phone, LocalDateTime issueDate, LocalDateTime dueDate,String status) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.memberId = memberId;
        this.memberName = memberName;
        this.phone = phone;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public TransactionDTO() {
    }

    public Long getTransactionIdId() {
        return transactionId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
