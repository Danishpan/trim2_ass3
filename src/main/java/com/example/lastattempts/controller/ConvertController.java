package com.example.lastattempts.controller;

import com.example.lastattempts.domain.Card;
import com.example.lastattempts.domain.User;
import com.example.lastattempts.repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ConvertController {
    @Autowired
    private CardRepo cardRepo;

    @GetMapping("/convert")
    public String main(Map<String, Object> model, @AuthenticationPrincipal User user) {
        Iterable<Card> cards = cardRepo.findByOwnerId(user.getId());
        model.put("cards", cards);

        return "convert";
    }

    @PostMapping("/convert")
    public String convertMoney(@AuthenticationPrincipal User user,
                                @RequestParam String outcomeCard,
                                @RequestParam String outcomeCurrency,
                                @RequestParam String incomeCurrency,
                                @RequestParam String amount){
        Card outcome = cardRepo.findByNumber(Long.valueOf(outcomeCard));

        Double moneyToConvert = 0.0;


        switch (outcomeCurrency){
            case "kzt": outcome.setBalance_kzt(outcome.getBalance_kzt() - Double.valueOf(amount));
                        moneyToConvert = Double.valueOf(amount);
                        break;
            case "usd": outcome.setBalance_usd(outcome.getBalance_usd() - Double.valueOf(amount));
                        moneyToConvert = Double.valueOf(amount)*420;
                        break;
            case "eur": outcome.setBalance_eur(outcome.getBalance_eur() - Double.valueOf(amount));
                        moneyToConvert = Double.valueOf(amount)*506;
                        break;
        }

        switch (incomeCurrency){
            case "kzt": if (!outcomeCurrency.equals("kzt")) outcome.setBalance_kzt(outcome.getBalance_kzt() + moneyToConvert);
                break;
            case "usd": if (!outcomeCurrency.equals("usd")) outcome.setBalance_usd(outcome.getBalance_usd() + moneyToConvert*420);
                break;
            case "eur": if (!outcomeCurrency.equals("eur")) outcome.setBalance_eur(outcome.getBalance_eur() + moneyToConvert*506);
                break;
        }

        cardRepo.save(outcome);

        return "redirect:/main";
    }
}
