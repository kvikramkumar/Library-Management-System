package com.example.repository;

import com.example.model.BookTransaction;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<BookTransaction,Long> {


    Optional<BookTransaction> findByBookIdAndMemberId(long bookId, long memberId);

    List<BookTransaction> findByBookId(Long bookId);

    List<BookTransaction> findByMemberId(Long memberId);
}
