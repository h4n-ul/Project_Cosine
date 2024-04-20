package com.h4n_ul.wave.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h4n_ul.wave.controller.dto.ReelDTO;
import com.h4n_ul.wave.entity.Artist;
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

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> createReel(HttpServletRequest request, @ModelAttribute ReelDTO reelDTO) {
        HttpSession session = request.getSession(false);
        Map<String, Object> response = new HashMap<>();
        if (session == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Artist targetArtist = (Artist) session.getAttribute("loggedInArtist");
        if (targetArtist == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Reel reel = reelSvc.createReel(targetArtist, reelDTO.getTitle(), reelDTO.getContents(), hallSvc.get(reelDTO.getHallId()), reelDTO.getFiles(), reelDTO.getAudioFiles());
        response.put("link", reel.getReelId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> getReel(@PathVariable("id") String id) {
        System.out.println(id);
        Reel reel = reelSvc.getById(id);
        Map<String, Object> response = new HashMap<>();
        if (reel == null) {
            response.put("message", "Not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        reel.setOwner(artistRepo.findById(reel.getOwner()).get().getArtistNname());

        response.put("data", reel);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("rework")
    public Reel rework(HttpServletRequest request, String id, String title, String contents) {
        // HttpSession session = request.getSession();
        // System.out.println(session.getId());
        // String loggedInArtist = (String) session.getAttribute("loggedInArtist");
        // System.out.println(loggedInArtist);
        // reelSvc.createReel(loggedInArtist, title, contents, null);
        return null;
    }
}
