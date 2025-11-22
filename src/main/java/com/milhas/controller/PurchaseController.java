package com.milhas.controller;

import com.milhas.entity.Attachment;
import com.milhas.entity.Purchase;
import com.milhas.service.FileStorageService;
import com.milhas.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final FileStorageService fileStorageService;

    public PurchaseController(PurchaseService purchaseService, FileStorageService fileStorageService){
        this.purchaseService = purchaseService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createPurchase(@RequestPart("purchase") Purchase purchase,
                                            @RequestPart(value = "files", required = false) MultipartFile[] files,
                                            Principal principal) throws Exception {
        Purchase saved = purchaseService.save(purchase, principal.getName());
        if (files != null) {
            for (MultipartFile f : files) {
                Attachment att = fileStorageService.storeFile(f, saved);
                saved.getAttachments().add(att);
            }
            saved = purchaseService.save(saved, principal.getName());
        }
        return ResponseEntity.ok(saved);
    }
}