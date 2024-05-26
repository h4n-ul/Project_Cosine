package com.h4nul.prjcosine.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tika.Tika;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h4nul.prjcosine.entity.AudioArchs;
import com.h4nul.prjcosine.repository.AudiRepo;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/stream")
public class Player {
    private final AudiRepo audiRepo;
    // private final FileService fileService;
    private final Tika tika = new Tika();

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> stream(@PathVariable String id, HttpServletRequest request) throws Exception {
        AudioArchs audio = audiRepo.findById(id).orElse(null);
        File file = new File(audio.getLocation());
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        String range = request.getHeader(HttpHeaders.RANGE);

        String mimeType = tika.detect(file);
        MediaType contentType = MediaType.parseMediaType(mimeType);

        if (range != null && !range.isBlank()) {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .contentType(contentType)
                    .body(resource);
        } else {
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(contentType)
                    .body(resource);
        }
    }
}
