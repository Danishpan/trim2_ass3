package com.example.lastattempts.domain;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "income_card")
    private Card income_card;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "outcome_card")
    private Card outcome_card;

    private Double amount;

    public Transaction() {
    }

    public Transaction(Card income_card, Card outcome_card, Double amount) {
        this.income_card = income_card;
        this.outcome_card = outcome_card;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Card getIncome_card() {
        return income_card;
    }

    public void setIncome_card(Card income_card) {
        this.income_card = income_card;
    }

    public Card getOutcome_card() {
        return outcome_card;
    }

    public void setOutcome_card(Card outcome_card) {
        this.outcome_card = outcome_card;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
