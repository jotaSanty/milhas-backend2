package com.milhas.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchases")
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

    public Purchase() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Card getCard() { return card; }
    public void setCard(Card card) { this.card = card; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate = purchaseDate; }

    public LocalDateTime getExpectedCreditDate() { return expectedCreditDate; }
    public void setExpectedCreditDate(LocalDateTime expectedCreditDate) { this.expectedCreditDate = expectedCreditDate; }

    public Long getExpectedPoints() { return expectedPoints; }
    public void setExpectedPoints(Long expectedPoints) { this.expectedPoints = expectedPoints; }

    public Long getCreditedPoints() { return creditedPoints; }
    public void setCreditedPoints(Long creditedPoints) { this.creditedPoints = creditedPoints; }

    public boolean isCredited() { return credited; }
    public void setCredited(boolean credited) { this.credited = credited; }

    public List<Attachment> getAttachments() { return attachments; }
    public void setAttachments(List<Attachment> attachments) { this.attachments = attachments; }
}