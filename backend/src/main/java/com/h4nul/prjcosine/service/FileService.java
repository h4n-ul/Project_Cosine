package com.h4nul.prjcosine.service;

import java.io.File;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.h4nul.prjcosine.entity.FileArchive;
import com.h4nul.prjcosine.exceptions.DataNotFoundException;
import com.h4nul.prjcosine.repository.FileRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepo fileRepo;
    public File getFile(String id) {
        if (id != null) {
            Optional<FileArchive> file = fileRepo.findById(id); 
            try {
                File f = new File(file.get().getLocation());
                return f;
            } catch (Exception e) {return null;}
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
