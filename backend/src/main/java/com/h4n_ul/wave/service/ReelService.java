package com.h4n_ul.wave.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.h4n_ul.wave.controller.dto.ReelDTO.AudioFiles;
import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.entity.AudioArchs;
import com.h4n_ul.wave.entity.FileArchive;
import com.h4n_ul.wave.entity.Hall;
import com.h4n_ul.wave.entity.Reel;
import com.h4n_ul.wave.repository.ReelRepo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReelService {
    private static final String FILE_PATH = "/Users/H4n_uL/Database/";
    private final ReelRepo reelRepo;

    @Transactional
    public Reel createReel(@NonNull Artist artist, String title, String contents, Hall hall, List<MultipartFile> files, List<AudioFiles> audioFiles) {
        Reel target = new Reel();

        SecureRandom random = new SecureRandom();
        byte[] p = new byte[32];
        random.nextBytes(p);
        String rid = Base64.encodeBase64String(p).replace("/", "_");;

        target.setReelId(rid);
        target.setTitle(title);
        target.setContents(contents);
        target.setOwner(artist.getUid());

        List<FileArchive> fileArchiveList = new ArrayList<>();
        if (files != null) {
            for (MultipartFile file : files) {
                random.nextBytes(p);
                String fid = Base64.encodeBase64String(p).replace("/", "_");
    
                try {
                    String filepath = saveFile(file);
                    FileArchive fileArchive = new FileArchive();
                    fileArchive.setFileId(rid+"."+fid);
                    fileArchive.setLocation(filepath);
                    fileArchive.setOrigFileName(file.getOriginalFilename());
                    fileArchiveList.add(fileArchive);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        target.setFiles(fileArchiveList);

        List<AudioArchs> audioArchiveList = new ArrayList<>();
        if (audioFiles != null) {
            for (AudioFiles file : audioFiles) {
                random.nextBytes(p);
                String fid = Base64.encodeBase64String(p).replace("/", "_");;

                try {
                    String filepath = saveFile(file.getFile());
                    AudioArchs fileArchive = new AudioArchs();
                    fileArchive.setFileId(rid+"."+fid+".audio");
                    fileArchive.setLocation(filepath);
                    fileArchive.setArtist(file.getArtist());
                    fileArchive.setTitle(file.getTitle());
                    fileArchive.setOrigFileName(file.getFile().getOriginalFilename());
                    audioArchiveList.add(fileArchive);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        target.setAudiofiles(audioArchiveList);

        target.setMaster(new ArrayList<>());
        target.setDegausse(new ArrayList<>());

        target.setHallId(hall.getHallId());
        target.setRelease(LocalDateTime.now());
        target.setLastRework(LocalDateTime.now());

        reelRepo.save(target);
        return target;
    }

    private String saveFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String filepath = Paths.get(FILE_PATH, filename).toString();
        file.transferTo(new File(filepath));
        return filepath;
    }

    public List<Reel> getAllByHall(@NonNull Hall hallId) {
        List<Reel> reellist = reelRepo.findByHallId(hallId);
        return reellist;
    }

    public Reel getReel(@NonNull String mid) {
        Optional<Reel> reel = reelRepo.findById(mid);
        if (reel.isPresent()) return reel.get();
        return null;
    }
}
