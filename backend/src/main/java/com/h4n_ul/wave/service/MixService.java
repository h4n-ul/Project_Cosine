package com.h4n_ul.wave.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.entity.FileArchive;
import com.h4n_ul.wave.entity.Hall;
import com.h4n_ul.wave.entity.Mixtape;
import com.h4n_ul.wave.repository.MixRepo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MixService {
    private final MixRepo mixRepo;
    public Mixtape createMix(@NonNull Artist artist, String title, String contents, List<FileArchive> files) {
        Mixtape target = new Mixtape();

        target.setTitle(title);
        target.setContents(contents);
        target.setOwner(artist);
        target.setFiles(files);

        mixRepo.save(target);
        return null;
    }
    
    public List<Mixtape> getAllByHall(@NonNull Hall hallId) {
        List<Mixtape> mixlist = mixRepo.findByHallId(hallId);
        return mixlist;
    }
}
