package com.example.lastattempts.controller;

import com.example.lastattempts.domain.Card;
import com.example.lastattempts.domain.Transaction;
import com.example.lastattempts.domain.User;
import com.example.lastattempts.repos.CardRepo;
import com.example.lastattempts.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HistoryController {
    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    CardRepo cardRepo;

    @GetMapping("/history")
    public String main(@AuthenticationPrincipal User user,
                       Map<String, Object> model){
        Iterable<Card> cards = cardRepo.findByOwnerId(user.getId());
        List<Transaction> incomeTransactions = new ArrayList<>();
        List<Transaction> outcomeTransactions = new ArrayList<>();


        for (Card card:
             cards) {
        List<Transaction> itransactions = transactionRepo.findByIncome_card(card.getId());
        List<Transaction> otransactions = transactionRepo.findByIncome_card(card.getId());
        incomeTransactions.addAll(itransactions);
        outcomeTransactions.addAll(otransactions);
        }

        model.put("incomeTransactions", incomeTransactions);
        model.put("outcomeTransactions", outcomeTransactions);

        return "history";
    }
}

