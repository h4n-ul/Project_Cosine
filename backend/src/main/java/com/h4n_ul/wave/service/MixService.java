package com.h4n_ul.wave.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.h4n_ul.wave.entity.FileArchive;
import com.h4n_ul.wave.entity.Mixtape;
import com.h4n_ul.wave.repository.ArtistRepo;
import com.h4n_ul.wave.repository.MixRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MixService {
    private final MixRepo mixRepo;
    private final ArtistRepo artistRepo;
    public Mixtape createMix(String ownerId, String title, String contents, List<FileArchive> files) {
        Mixtape target = new Mixtape();

        target.setTitle(title);
        target.setContents(contents);
        target.setOwner(artistRepo.findById(ownerId).get());
        target.setFiles(files);

        mixRepo.save(target);
        return null;
    }
}
