package com.milhas.service;

// --- IMPORTAÇÕES OBRIGATÓRIAS (Sem isto dá erro vermelho) ---
import com.milhas.entity.Attachment;
import com.milhas.entity.Purchase;
import com.milhas.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.file.upload-dir:./uploads}")
    private String uploadDir;

    private final AttachmentRepository attachmentRepository;

    public FileStorageService(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    public Attachment storeFile(MultipartFile file, Purchase purchase) throws IOException {
        String ext = "";
        // Correção do 'var' para 'String'
        String fname = file.getOriginalFilename();

        if (fname != null && fname.contains(".")) {
            ext = fname.substring(fname.lastIndexOf("."));
        }

        String storedName = UUID.randomUUID().toString() + ext;
        Path userDir = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(userDir);
        Path target = userDir.resolve(storedName);

        try (InputStream in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }

        Attachment att = new Attachment();
        att.setFileName(fname);
        att.setFileType(file.getContentType());
        att.setUrl(target.toString());
        att.setPurchase(purchase);
        return attachmentRepository.save(att);
    }

    public Path loadAsPath(String path) {
        return Paths.get(path);
    }
}