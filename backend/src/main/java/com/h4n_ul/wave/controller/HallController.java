package com.h4n_ul.wave.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h4n_ul.wave.controller.dto.CreateHallDTO;
import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.repository.ArtistRepo;
import com.h4n_ul.wave.service.ArtistService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/backend/hall")
public class HallController {
    public final ArtistService artistSvc;
    public final ArtistRepo artistRepo;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateHallDTO createHallDTO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Map<String, String> response = new HashMap<>();

        if (session == null) {
            response.put("message", "No active session");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        Artist loggedInArtist = (Artist) session.getAttribute("loggedInArtist");
        if (loggedInArtist == null) {
            response.put("message", "No logged in user");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return null;

        // response.put("artistId", loggedInArtist.getArtistId());
        // response.put("email", loggedInArtist.getEmail());
        // response.put("sessionId", session.getId());
        // return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
