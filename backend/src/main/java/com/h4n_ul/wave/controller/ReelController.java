package com.h4n_ul.wave.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h4n_ul.wave.controller.dto.ReelDTO;
import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.entity.AudioArchs;
import com.h4n_ul.wave.entity.FileArchive;
import com.h4n_ul.wave.entity.Reel;
import com.h4n_ul.wave.repository.ArtistRepo;
import com.h4n_ul.wave.service.HallService;
import com.h4n_ul.wave.service.ReelService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/backend/reel")
public class ReelController {
    private final ArtistRepo artistRepo;
    private final ReelService reelSvc;
    private final HallService hallSvc;

    @PostMapping("record")
    public ResponseEntity<Map<String, Object>> record(HttpServletRequest request, @ModelAttribute ReelDTO reelDTO) {
        HttpSession session = request.getSession(false);
        Map<String, Object> response = new HashMap<>();
        if (session == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Artist targetArtist = (Artist) session.getAttribute("loggedInArtist");
        if (targetArtist == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Reel reel = reelSvc.record(targetArtist, reelDTO.getTitle(), reelDTO.getContents(), hallSvc.getHall(reelDTO.getHallId()), reelDTO.getFiles(), reelDTO.getAudio());
        response.put("link", reel.getReelId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> getReel(@PathVariable("id") String id) {
        Reel reel = reelSvc.getReel(id);
        Map<String, Object> response = new HashMap<>();
        if (reel == null) {
            response.put("message", "Not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        reel.setArtistId(artistRepo.findById(reel.getArtistId()).get().getArtistNname());

        response.put("title", reel.getTitle());
        response.put("contents", reel.getContents());
        response.put("owner", reel.getArtistId());
        response.put("reelId", reel.getReelId());
        response.put("release", reel.getRelease());
        response.put("lastRework", reel.getLastRework());
        response.put("master", reel.getMaster());
        response.put("degausse", reel.getDegausse());
        response.put("hallId", hallSvc.getHallById(reel.getHallId()).getSrc());

        if (reel.getFiles() != null) {
            Map<String, Object> file = new HashMap<>();
            List<Map<String, Object>> files = new ArrayList<>();
            List<FileArchive> farch = reel.getFiles();
            for (FileArchive f : farch) {
                file.put("fileId", f.getFileId());
                file.put("originalFileName", f.getOrigFileName());
                files.add(file);
            }
            response.put("files", files);
        }

        if (reel.getAudiofiles() != null) {
            Map<String, Object> audio = new HashMap<>();
            List<Map<String, Object>> audios = new ArrayList<>();
            List<AudioArchs> aarch = reel.getAudiofiles();
            for (AudioArchs a : aarch) {
                audio.put("fileId", a.getFileId());
                audio.put("title", a.getTitle());
                audio.put("artist", a.getArtist());
                audio.put("isOriginal", a.getIsOriginal());
                audios.add(audio);
            }
            response.put("audio", audios);
        }

        // response.put("data", reel);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("rework")
    public Reel rework(HttpServletRequest request, @ModelAttribute ReelDTO reelDTO) {
        // HttpSession session = request.getSession();
        // System.out.println(session.getId());
        // String loggedInArtist = (String) session.getAttribute("loggedInArtist");
        // System.out.println(loggedInArtist);
        // reelSvc.createReel(loggedInArtist, title, contents, null);
        return null;
    }

    @PostMapping("incinerate")
    public Reel incinerate(HttpServletRequest request, String id) {
        // HttpSession session = request.getSession();
        // System.out.println(session.getId());
        // String loggedInArtist = (String) session.getAttribute("loggedInArtist");
        // System.out.println(loggedInArtist);
        // reelSvc.createReel(loggedInArtist, title, contents, null);
        return null;
    }
}
