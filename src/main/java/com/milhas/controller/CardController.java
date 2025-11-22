package com.milhas.controller;

import com.milhas.entity.Card;
import com.milhas.repository.CardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardRepository repo;
    public CardController(CardRepository repo){ this.repo = repo; }

    @GetMapping
    public ResponseEntity<List<Card>> list() { return ResponseEntity.ok(repo.findAll()); }

    @PostMapping
    public ResponseEntity<Card> create(@RequestBody Card c) { return ResponseEntity.ok(repo.save(c)); }
}
