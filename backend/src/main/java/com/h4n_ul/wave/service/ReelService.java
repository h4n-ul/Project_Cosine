package com.h4n_ul.wave.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.h4n_ul.wave.controller.dto.ReelDTO.AudioFiles;
import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.entity.Hall;
import com.h4n_ul.wave.entity.Reel;
import com.h4n_ul.wave.repository.ReelRepo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReelService {
    private final ReelRepo reelRepo;
    public Reel createReel(@NonNull Artist artist, String title, String contents, Hall hid, List<MultipartFile> files, List<AudioFiles> audioFiles) {
        Reel target = new Reel();

        SecureRandom random = new SecureRandom();
        byte[] p = new byte[32];
        random.nextBytes(p);
        String mid = Base64.encodeBase64String(p);

        target.setReelId(mid);
        target.setTitle(title);
        target.setContents(contents);
        target.setOwner(artist);

        // should save files to absolute directory and map to FileArchive and AudioArchs

        target.setHallId(hid);
        target.setRelease(LocalDateTime.now());
        target.setLastRework(LocalDateTime.now());

        reelRepo.save(target);
        return null;
    }

    public List<Reel> getAllByHall(@NonNull Hall hallId) {
        List<Reel> reellist = reelRepo.findByHallId(hallId);
        return reellist;
    }

    public Reel getById(@NonNull String mid) {
        Optional<Reel> reel = reelRepo.findById(mid);
        if (reel.isPresent()) return reel.get();
        return null;
    }
}
