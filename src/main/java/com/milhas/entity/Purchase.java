package com.milhas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchases")
@Getter @Setter @NoArgsConstructor
public class Purchase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Card card;

    private BigDecimal amount;

    private LocalDateTime purchaseDate;

    private LocalDateTime expectedCreditDate;

    private Long expectedPoints;

    private Long creditedPoints = 0L;

    private boolean credited = false;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<Attachment> attachments = new ArrayList<>();
}
