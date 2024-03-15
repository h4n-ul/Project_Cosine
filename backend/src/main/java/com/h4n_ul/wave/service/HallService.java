package com.h4n_ul.wave.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.h4n_ul.wave.entity.Hall;
import com.h4n_ul.wave.repository.ArtistRepo;
import com.h4n_ul.wave.repository.HallRepo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallRepo hRepo;
    private final ArtistRepo aRepo;

    public Hall create(String hallName, String desc, String src, @NonNull String uid) {
        Hall target = new Hall();
        target.setHallId(hallName);
        target.setTitle(hallName);
        target.setDescription(desc);
        target.setManager(aRepo.findById(uid).get());

        hRepo.save(target);
        return target;
    }

    public Hall get(String src) {
        Optional<Hall> hallOpt = hRepo.findBySrc(src);
        if (!hallOpt.isPresent()) {
            return null;
        }
        return hallOpt.get();
    }
}
