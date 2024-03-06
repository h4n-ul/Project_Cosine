package com.h4n_ul.wave.service;

import java.security.SecureRandom;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.h4n_ul.hancrypt.CryPackage;
import com.h4n_ul.hancrypt.HanCrypt;
import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.repository.ArtistRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistService {
    public final ArtistRepo artistRepo;

    public Artist regist(String artistId, String password, String email) {
        HanCrypt hanCrypt = new HanCrypt();
        SecureRandom random = new SecureRandom();
        Artist target = new Artist();
        byte[] p = new byte[32];
        random.nextBytes(p);
        String uid = Base64.encodeBase64String(p);

        target.setArtistId(artistId);
        target.setUid(uid);

        CryPackage pack = hanCrypt.hash(uid, password, 65536, 512);

        target.setPwHash(pack.getHash());
        target.setSalt(pack.getSalt());
        target.setPasses(pack.getPasses());
        target.setArtistNname(artistId);
        target.setEmail(email);

        artistRepo.save(target);

        return target;
    }

    public Boolean login(String artistId, String password) {
        HanCrypt hanCrypt = new HanCrypt();
        Optional<Artist> target = artistRepo.findByArtistId(artistId);

        Boolean match = hanCrypt.confirm(target.get().getUid(), password, target.get().getPasses(), target.get().getPwHash(), target.get().getSalt());

        return match;
    }
}
