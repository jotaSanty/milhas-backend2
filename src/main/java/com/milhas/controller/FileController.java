package com.milhas.controller;

import com.milhas.entity.Attachment;
import com.milhas.repository.AttachmentRepository;
import com.milhas.service.FileStorageService;
import org.springframework.core.io.PathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final AttachmentRepository attachmentRepository;
    private final FileStorageService fileStorageService;

    public FileController(AttachmentRepository attachmentRepository, FileStorageService fileStorageService) {
        this.attachmentRepository = attachmentRepository;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> download(@PathVariable Long id) {
        Optional<Attachment> a = attachmentRepository.findById(id);
        if (!a.isPresent()) return ResponseEntity.notFound().build();
        Path p = fileStorageService.loadAsPath(a.get().getUrl());
        return ResponseEntity.ok().body(new PathResource(p));
    }
}
