package com.h4nul.prjcosine.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.h4nul.hancrypt.CryPackage;
import com.h4nul.hancrypt.HanCrypt;
import com.h4nul.prjcosine.entity.Artist;
import com.h4nul.prjcosine.repository.ArtistRepo;

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

    public Artist getArtistByUid(String uid) {
        return artistRepo.findById(uid).orElse(null);
    }

    public Artist getArtistByPubId(String artistId) {
        return artistRepo.findByArtistId(artistId).orElse(null);
    }

    public Boolean login(String artistId, String password) {
        HanCrypt hanCrypt = new HanCrypt();
        Optional<Artist> target = artistRepo.findByArtistId(artistId);

        Boolean match = hanCrypt.confirm(target.get().getUid(), password, target.get().getPasses(), target.get().getPwHash(), target.get().getSalt());

        return match;
    }

    public List<Artist> search(String query, String filter) {
        return artistRepo.findByArtistNnameContaining(query);
    }
}
