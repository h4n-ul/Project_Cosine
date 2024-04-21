package com.h4n_ul.wave.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashSet;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.entity.Mix;
import com.h4n_ul.wave.repository.MixRepo;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MixService {
    private final MixRepo mixRepo;

    public Mix getMix(String id) {
        return mixRepo.findById(id).orElse(null);
    }

    public Mix createMix(@NonNull Artist artist, String contents, String parentId) {
        SecureRandom random = new SecureRandom();
        byte[] p = new byte[32];
        random.nextBytes(p);
        String mid = Base64.encodeBase64String(p).replace("/", "_");;
        Mix target = new Mix();

        target.setMixId(mid);

        target.setContents(contents);   
        target.setArtistId(artist.getUid());
        target.setParent(parentId);

        target.setMaster(new HashSet<>());
        target.setBurn(new HashSet<>());
        LocalDateTime now = LocalDateTime.now();
        target.setRelease(now);
        target.setLastRework(now);
        return null;
    }
}
