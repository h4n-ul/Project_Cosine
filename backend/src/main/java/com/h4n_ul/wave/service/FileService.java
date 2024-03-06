package com.h4n_ul.wave.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.h4n_ul.wave.entity.FileArchive;
import com.h4n_ul.wave.exceptions.DataNotFoundException;
import com.h4n_ul.wave.repository.FileRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepo fileRepo;
    public FileArchive getFile(String id) {
        if (id != null) {
            Optional<FileArchive> file = fileRepo.findById(id); 
            return file.get();
        }
        else throw new DataNotFoundException("");
    }
    public FileArchive saveFile(String id) {
        if (id != null) {
            Optional<FileArchive> file = fileRepo.findById(id); 
            return file.get();
        }
        else throw new DataNotFoundException("");
    }
}
