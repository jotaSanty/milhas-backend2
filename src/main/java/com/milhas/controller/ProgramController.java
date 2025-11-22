package com.milhas.controller;

import com.milhas.entity.Program;
import com.milhas.repository.ProgramRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {
    private final ProgramRepository repo;
    public ProgramController(ProgramRepository r){ this.repo = r; }

    @GetMapping
    public ResponseEntity<List<Program>> list() { return ResponseEntity.ok(repo.findAll()); }

    @PostMapping
    public ResponseEntity<Program> create(@RequestBody Program p) { return ResponseEntity.ok(repo.save(p)); }
}
