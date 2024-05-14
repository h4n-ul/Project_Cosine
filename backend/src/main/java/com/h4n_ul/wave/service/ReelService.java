package com.h4n_ul.wave.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.h4n_ul.wave.controller.dto.AudioFiles;
import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.entity.AudioArchs;
import com.h4n_ul.wave.entity.FileArchive;
import com.h4n_ul.wave.entity.Hall;
import com.h4n_ul.wave.entity.Reel;
import com.h4n_ul.wave.repository.AudiRepo;
import com.h4n_ul.wave.repository.FileRepo;
import com.h4n_ul.wave.repository.ReelRepo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReelService {
    private static final String FILE_PATH = "/Users/H4n_uL/Database/";
    private final ReelRepo reelRepo;
    private final FileRepo fileRepo;
    private final AudiRepo audiRepo;

    @Transactional
    public Reel record(@NonNull Artist artist, String title, String contents, Hall hall, List<MultipartFile> files, List<AudioFiles> audioFiles) {
        Reel target = new Reel();

        SecureRandom random = new SecureRandom();
        byte[] p = new byte[32];
        random.nextBytes(p);
        String rid = Base64.encodeBase64String(p).replace("/", "_");

        target.setReelId(rid);
        target.setTitle(title);
        target.setContents(contents);
        target.setArtistId(artist.getUid());

        List<FileArchive> fileArchiveList = new ArrayList<>();
        if (files != null) {
            for (MultipartFile file : files) {
                random.nextBytes(p);
                String fid = Base64.encodeBase64String(p).replace("/", "_");
    
                try {
                    fid = rid+"."+fid;
                    String filepath = saveFile(file, fid);
                    FileArchive fileArchive = new FileArchive();
                    fileArchive.setFileId(fid);
                    fileArchive.setLocation(filepath);
                    fileArchive.setOrigFileName(file.getOriginalFilename());
                    fileRepo.save(fileArchive);
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
                String fid = Base64.encodeBase64String(p).replace("/", "_");

                try {
                    fid = rid+"."+fid+".audio";
                    String filepath = saveFile(file.getFile(), fid);
                    AudioArchs audioArch = new AudioArchs();
                    audioArch.setFileId(fid);
                    audioArch.setLocation(filepath);
                    audioArch.setArtist(file.getArtist());
                    audioArch.setTitle(file.getTitle());
                    audioArch.setOrigFileName(file.getFile().getOriginalFilename());
                    audioArch.setIsOriginal(file.getIsOriginal());
                    audiRepo.save(audioArch);
                    audioArchiveList.add(audioArch);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        target.setAudiofiles(audioArchiveList);

        target.setMaster(new HashSet<>());
        target.setDegausse(new HashSet<>());

        target.setHallId(hall.getHallId());
        LocalDateTime now = LocalDateTime.now();
        target.setRelease(now);
        target.setLastRework(now);

        reelRepo.save(target);
        return target;
    }

    @Transactional
    public Reel rework(@NonNull String rid, @NonNull Artist artist, String title, String contents, Hall hall, List<MultipartFile> files, List<AudioFiles> audioFiles) {
        Reel target = new Reel();

        SecureRandom random = new SecureRandom();
        byte[] p = new byte[32];

        target.setReelId(rid);
        target.setTitle(title);
        target.setContents(contents);
        target.setArtistId(artist.getUid());

        List<FileArchive> fileArchiveList = new ArrayList<>();
        if (files != null) {
            for (MultipartFile file : files) {
                random.nextBytes(p);
                String fid = Base64.encodeBase64String(p).replace("/", "_");
    
                try {
                    fid = rid+"."+fid;
                    String filepath = saveFile(file, fid);
                    FileArchive fileArchive = new FileArchive();
                    fileArchive.setFileId(fid);
                    fileArchive.setLocation(filepath);
                    fileArchive.setOrigFileName(file.getOriginalFilename());
                    fileRepo.save(fileArchive);
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
                String fid = Base64.encodeBase64String(p).replace("/", "_");

                try {
                    fid = rid+"."+fid+".audio";
                    String filepath = saveFile(file.getFile(), fid);
                    AudioArchs audioArch = new AudioArchs();
                    audioArch.setFileId(fid);
                    audioArch.setLocation(filepath);
                    audioArch.setArtist(file.getArtist());
                    audioArch.setTitle(file.getTitle());
                    audioArch.setOrigFileName(file.getFile().getOriginalFilename());
                    audioArch.setIsOriginal(file.getIsOriginal());
                    audiRepo.save(audioArch);
                    audioArchiveList.add(audioArch);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        target.setAudiofiles(audioArchiveList);

        target.setMaster(new HashSet<>());
        target.setDegausse(new HashSet<>());

        target.setHallId(hall.getHallId());
        LocalDateTime now = LocalDateTime.now();
        target.setRelease(now);
        target.setLastRework(now);

        reelRepo.save(target);
        return target;
    }

    private String saveFile(MultipartFile file, String fid) throws IOException {
        String filename = fid;
        String filepath = Paths.get(FILE_PATH, filename).toString();
        file.transferTo(new File(filepath));
        return filepath;
    }

    public List<Reel> getAllByHall(String hallId) {
        List<Reel> reellist = reelRepo.findByHallId(hallId);
        return reellist;
    }

    public Reel getReel(@NonNull String mid) {
        Optional<Reel> reel = reelRepo.findById(mid);
        if (reel.isPresent()) return reel.get();
        return null;
    }
}
