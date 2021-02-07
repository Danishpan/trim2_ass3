package com.example.lastattempts.controller;

import com.example.lastattempts.domain.Card;
import com.example.lastattempts.domain.Message;
import com.example.lastattempts.domain.Transaction;
import com.example.lastattempts.domain.User;
import com.example.lastattempts.repos.CardRepo;
import com.example.lastattempts.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TransferController {
    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @GetMapping("/transfer")
    public String sendCards(Map<String, Object> model, @AuthenticationPrincipal User user) {
        Iterable<Card> cards = cardRepo.findByOwnerId(user.getId());
        model.put("cards", cards);

        return "transfer";
    }

    @PostMapping("/transfer")
    public String transferMoney(@AuthenticationPrincipal User user,
                                @RequestParam String outcomeCard,
                                @RequestParam String incomeCard,
                                @RequestParam String amount, Map<String, Object> model){
        Card outcome = cardRepo.findByNumber(Long.valueOf(outcomeCard));
        Card income = cardRepo.findByNumber(Long.valueOf(incomeCard));

        if (Double.valueOf(amount)>100000 || !outcome.getBank().equals(income.getBank())){
            outcome.setBalance_kzt(outcome.getBalance_kzt() - Double.valueOf(amount)*1.01);
        } else {
            outcome.setBalance_kzt(outcome.getBalance_kzt() - Double.valueOf(amount));
        }
        income.setBalance_kzt(income.getBalance_kzt() + Double.valueOf(amount));


        cardRepo.save(outcome);
        cardRepo.save(income);

        Transaction transaction = new com.example.lastattempts.domain.Transaction(income, outcome, Double.valueOf(amount));
        transactionRepo.save(transaction);

        return "redirect:/main";
    }

    @GetMapping("/transfer/own")
    public String sendCardsOwn(Map<String, Object> model, @AuthenticationPrincipal User user) {
        Iterable<Card> cards = cardRepo.findByOwnerId(user.getId());
        model.put("cards", cards);

        return "transfer_own";
    }

    @PostMapping("/transfer/own")
    public String transferMoneyToOwnCard(@AuthenticationPrincipal User user,
                                @RequestParam String outcomeCard,
                                @RequestParam String incomeCard,
                                @RequestParam String amount, Map<String, Object> model){
        Card outcome = cardRepo.findByNumber(Long.valueOf(outcomeCard));
        Card income = cardRepo.findByNumber(Long.valueOf(incomeCard));

        if (outcome.getId() != income.getId()){
            outcome.setBalance_kzt(outcome.getBalance_kzt() - Double.valueOf(amount));
            income.setBalance_kzt(income.getBalance_kzt() + Double.valueOf(amount));
        }

        cardRepo.save(outcome);
        cardRepo.save(income);


        return "redirect:/main";
    }
}
