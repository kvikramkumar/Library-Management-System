package com.example.controller;

import com.example.dto.TransactionDTO;
import com.example.model.Book;
import com.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/manage-transactions.html")
    public ModelAndView manageBooks(){
        ModelAndView model = new ModelAndView();
        model.setViewName("manage-transactions");
        return model;
    }

    @PostMapping("/transactions")
    public ResponseEntity<Map<String,String>> addBook(@RequestParam(required = true) String bookId,
                                                      @RequestParam(required = true) String memberId
                                                      ){


        Map<String,String> response = new HashMap<>();
        response.put("message", " Book is Issued");

        transactionService.addTransaction(Long.parseLong(bookId),Long.parseLong(memberId));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/transactions/renew")
    public ResponseEntity<Map<String,String>> renewBook(@RequestParam(required = true) String bookId,
                                                      @RequestParam(required = true) String memberId
    ){


        Map<String,String> response = new HashMap<>();
        response.put("message", " renewed");

        transactionService.renewTransaction(Long.parseLong(bookId),Long.parseLong(memberId));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/transactions/return")
    public ResponseEntity<Map<String,String>> returnBook(@RequestParam(required = true) String bookId,
                                                        @RequestParam(required = true) String memberId){

        Map<String,String> response = new HashMap<>();
        response.put("message", " returned");

        transactionService.returnBook(Long.parseLong(bookId),Long.parseLong(memberId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(@RequestParam(required = false) String bookId,
                                                @RequestParam(required = false) String memberId){

        Long bookID = null;
        Long memberID = null;
        if(bookId != null)
            bookID = Long.parseLong(bookId);
        if(memberId != null)
            memberID = Long.parseLong(memberId);

        return transactionService.getTransactions(bookID,memberID);

    }
}
