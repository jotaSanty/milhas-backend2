package com.milhas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "card_brands")
@Getter @Setter @NoArgsConstructor
public class CardBrand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
