package com.milhas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Getter @Setter @NoArgsConstructor
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
}
