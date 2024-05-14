package com.h4n_ul.wave.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h4n_ul.wave.controller.dto.LoginDTO;
import com.h4n_ul.wave.controller.dto.RegistDTO;
import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.repository.ArtistRepo;
import com.h4n_ul.wave.service.ArtistService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
@RequestMapping("/backend/artist")
public class ArtistController {
    public final ArtistService artistSvc;
    public final ArtistRepo artistRepo;

    @PostMapping("register")
    public ResponseEntity<Map<String, String>> regist(@RequestBody RegistDTO registDTO, HttpSession session) {
        Artist target = artistSvc.regist(registDTO.getArtistId(), registDTO.getPassword(), registDTO.getEmail());

        session.setAttribute("loginArtistUid", target.getUid());
        Map<String, String> response = new HashMap<>();
        response.put("sessionId", session.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Optional<Artist> targetOpt = artistRepo.findByArtistId(loginDTO.getArtistId());
        Artist target = null;
        if (!targetOpt.isPresent()) {
            targetOpt = artistRepo.findByEmail(loginDTO.getArtistId());
            if (!targetOpt.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Invalid ID or email.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
        target = targetOpt.get();
        Boolean pwMatch = artistSvc.login(loginDTO.getArtistId(), loginDTO.getPassword());
        Map<String, String> response = new HashMap<>();

        if (pwMatch == false) {
            response.put("message", "Invalid Password");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 로그인 성공 처리
        session.setAttribute("loginArtistUid", target.getUid());
        response.put("message", "Login successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getLoginInfo")
    public ResponseEntity<Artist> getLoginInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Artist loginArtistUid = artistSvc.getArtistByUid((String) session.getAttribute("loginArtistUid"));
        if (loginArtistUid == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(loginArtistUid, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistInfo(@PathVariable("id") String artistId) {
        Artist artist = artistSvc.getArtistByPubId(artistId);
        if (artist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }
}
