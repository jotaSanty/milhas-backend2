package com.milhas.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "card_brands")
public class CardBrand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public CardBrand() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}