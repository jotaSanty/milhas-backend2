package com.milhas.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alias;
    private String cardNumberMasked;
    private String type;

    @ManyToOne
    private CardBrand brand;

    @ManyToOne
    private Program program;

    @ManyToOne
    private User user;

    private Long programBalance = 0L;

    public Card() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getCardNumberMasked() { return cardNumberMasked; }
    public void setCardNumberMasked(String cardNumberMasked) { this.cardNumberMasked = cardNumberMasked; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public CardBrand getBrand() { return brand; }
    public void setBrand(CardBrand brand) { this.brand = brand; }

    public Program getProgram() { return program; }
    public void setProgram(Program program) { this.program = program; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Long getProgramBalance() { return programBalance; }
    public void setProgramBalance(Long programBalance) { this.programBalance = programBalance; }
}