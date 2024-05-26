package com.h4nul.prjcosine.service;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.h4nul.prjcosine.entity.Hall;
import com.h4nul.prjcosine.repository.HallRepo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallRepo hRepo;

    public Hall create(String hallName, String desc, String src, @NonNull String uid) {
        SecureRandom random = new SecureRandom();
        byte[] p = new byte[32];
        Hall target = new Hall();

        random.nextBytes(p);
        String hid = Base64.encodeBase64String(p).replace("/", "_");;

        target.setHallId(hid);
        target.setTitle(hallName);
        target.setDescription(desc);
        target.setManager(uid);
        target.setSrc(src);
        target.setPopularity(0);
        target.setPopularityBuffer(new HashSet<>());

        hRepo.save(target);
        return target;
    }

    public Hall getHall(String src) {
        return hRepo.findBySrc(src).orElse(null);
    }

    public Hall getHallById(String id) {
        return hRepo.findById(id).orElse(null);
    }

    public List<Hall> getHallByDR() {
        List<Hall> hList = hRepo.findAll();
        hList.sort((a, b) -> b.getPopularity() - a.getPopularity());
        return hList.subList(0, Math.min(hList.size(), 10));
    }

    public List<Hall> search(String query, String filter) {
        List<Hall> hallList;

        if (filter.equals("title")){
            hallList = hRepo.findByTitleContaining(query);
        }
        else if (filter.equals("description")){
            hallList = hRepo.findByDescriptionContaining(query);
        }
        else{return null;}
        return hallList;
    }
}
