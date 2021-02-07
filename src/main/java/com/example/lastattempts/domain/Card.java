package com.example.lastattempts.domain;


import javax.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Long number;
    private String bank;
    private Double balance_kzt;
    private Double balance_usd;
    private Double balance_eur;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    public Card() {
    }

    public Card(Long number, String bank, Double balance_kzt, Double balance_usd, Double balance_eur, User user){
        this.number = number;
        this.bank = bank;
        this.balance_kzt = balance_kzt;
        this.balance_usd = balance_usd;
        this.balance_eur = balance_eur;
        this.owner = user;
    }

    public String getOwnerName() {
        return owner != null ? owner.getUsername() : "<none>";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Double getBalance_kzt() {
        return balance_kzt;
    }

    public void setBalance_kzt(Double balance_kzt) {
        this.balance_kzt = balance_kzt;
    }

    public Double getBalance_usd() {
        return balance_usd;
    }

    public void setBalance_usd(Double balance_usd) {
        this.balance_usd = balance_usd;
    }

    public Double getBalance_eur() {
        return balance_eur;
    }

    public void setBalance_eur(Double balance_eur) {
        this.balance_eur = balance_eur;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

