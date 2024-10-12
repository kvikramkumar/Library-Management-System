package com.example.service;

import com.example.dto.TransactionDTO;
import com.example.model.BookTransaction;
import com.example.model.Member;
import com.example.repository.BookRepository;
import com.example.repository.MemberRepository;
import com.example.repository.TransactionRepository;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.model.Book;
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void addTransaction(Long bookId, Long memberId) {

        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            Book existingBook = book.get();
            if(existingBook.getQuantity() > 0){
                BookTransaction bookTransaction = new BookTransaction();
                bookTransaction.setBookId(bookId);
                bookTransaction.setMemberId(memberId);
                bookTransaction.setIssueDate(LocalDateTime.now());
                bookTransaction.setDueDate(LocalDateTime.now().plusDays(30));
                bookTransaction.setStatus("issued");

                transactionRepository.save(bookTransaction);
                existingBook.setQuantity(existingBook.getQuantity() - 1);
                bookRepository.save(existingBook);
            }
        }
    }

    public void renewTransaction(long bookId, long memberId) {
        Optional<BookTransaction> bookTransaction = transactionRepository.findByBookIdAndMemberId(bookId,memberId);

        if(bookTransaction.isPresent()){

            BookTransaction existingTransaction = bookTransaction.get();
            existingTransaction.setDueDate(existingTransaction.getDueDate().plusDays(30));
            existingTransaction.setStatus("renewed");
            transactionRepository.save(existingTransaction);
        }
    }

    public void returnBook(long bookId, long memberId) {
        Optional<BookTransaction> bookTransaction = transactionRepository.findByBookIdAndMemberId(bookId,memberId);

        if(bookTransaction.isPresent()){
            BookTransaction existingTransaction = bookTransaction.get();
            existingTransaction.setReturnDate(LocalDateTime.now());
            existingTransaction.setStatus("returned");
            transactionRepository.save(existingTransaction);
        }
    }

    public List<TransactionDTO> getTransactions(Long bookId, Long memberId) {

        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        if(bookId != null && memberId != null)
        {
            TransactionDTO transactionDTO = new TransactionDTO();
            Optional<BookTransaction> transactions = transactionRepository.findByBookIdAndMemberId(bookId,memberId);
            if(transactions.isPresent()){
                Optional<Book> book = bookRepository.findById(bookId);
                Optional<Member> member = memberRepository.findById(memberId);

                transactionDTO.setTransactionId(transactions.get().getTransactionId());
                transactionDTO.setIssueDate(transactions.get().getIssueDate());
                transactionDTO.setDueDate(transactions.get().getDueDate());
                transactionDTO.setStatus(transactions.get().getStatus());

                transactionDTO.setBookId(book.get().getBookId());
                transactionDTO.setBookTitle(book.get().getTitle());
                transactionDTO.setAuthor(book.get().getAuthor());

                transactionDTO.setMemberId(member.get().getMemberId());
                transactionDTO.setMemberName(member.get().getMemberName());
                transactionDTO.setPhone(member.get().getPhone());

                return Arrays.asList(transactionDTO);
            }
        }
        else if(bookId != null){
            List<BookTransaction> transactionsList = transactionRepository.findByBookId(bookId);


            for(BookTransaction transaction : transactionsList) {
                TransactionDTO transactionDTO = new TransactionDTO();
                Optional<Book> book = bookRepository.findById(bookId);
                Optional<Member> member = memberRepository.findById(transaction.getMemberId());

                transactionDTO.setTransactionId(transaction.getTransactionId());
                transactionDTO.setIssueDate(transaction.getIssueDate());
                transactionDTO.setDueDate(transaction.getDueDate());
                transactionDTO.setStatus(transaction.getStatus());

                transactionDTO.setBookId(book.get().getBookId());
                transactionDTO.setBookTitle(book.get().getTitle());
                transactionDTO.setAuthor(book.get().getAuthor());

                transactionDTO.setMemberId(member.get().getMemberId());
                transactionDTO.setMemberName(member.get().getMemberName());
                transactionDTO.setPhone(member.get().getPhone());

                transactionDTOList.add(transactionDTO);
            }
            return transactionDTOList;
        }
        else if(memberId != null){
            List<BookTransaction> transactionsList = transactionRepository.findByMemberId(memberId);


            for(BookTransaction transaction : transactionsList) {
                TransactionDTO transactionDTO = new TransactionDTO();
                Optional<Book> book = bookRepository.findById(transaction.getBookId());
                Optional<Member> member = memberRepository.findById(memberId);

                transactionDTO.setTransactionId(transaction.getTransactionId());
                transactionDTO.setIssueDate(transaction.getIssueDate());
                transactionDTO.setDueDate(transaction.getDueDate());
                transactionDTO.setStatus(transaction.getStatus());

                transactionDTO.setBookId(book.get().getBookId());
                transactionDTO.setBookTitle(book.get().getTitle());
                transactionDTO.setAuthor(book.get().getAuthor());

                transactionDTO.setMemberId(member.get().getMemberId());
                transactionDTO.setMemberName(member.get().getMemberName());
                transactionDTO.setPhone(member.get().getPhone());

                transactionDTOList.add(transactionDTO);
            }
            return transactionDTOList;
        }

        return null;
    }
}
