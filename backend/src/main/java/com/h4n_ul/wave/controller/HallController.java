package com.h4n_ul.wave.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h4n_ul.wave.controller.dto.CreateHallDTO;
import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.entity.Hall;
import com.h4n_ul.wave.entity.Reel;
import com.h4n_ul.wave.service.ArtistService;
import com.h4n_ul.wave.service.HallService;
import com.h4n_ul.wave.service.ReelService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequiredArgsConstructor
@RequestMapping("/backend/hall")
public class HallController {
    public final ArtistService artistSvc;
    public final ArtistController artistContr;
    public final ReelService reelService;
    public final HallService hallService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateHallDTO createHallDTO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Map<String, String> response = new HashMap<>();
        if (session == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Artist targetArtist = (Artist) session.getAttribute("loggedInArtist");
        if (targetArtist == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (createHallDTO.getHallName() == null) {
            response.put("message", "Hall title not provided");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (createHallDTO.getDescription() == null) {
            response.put("message", "Hall description not provided");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (createHallDTO.getSrc() == null) {
            response.put("message", "Hall link not provided");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (hallService.get(createHallDTO.getSrc()) != null) {
            response.put("message", "Link already exists");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        hallService.create(createHallDTO.getHallName(), createHallDTO.getDescription(), createHallDTO.getSrc(), targetArtist.getUid());

        response.put("message", "Hall created successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getHall(@PathVariable("id") String id) {
        Hall h = hallService.get(id);
        Map<String, Object> response = new HashMap<>();
        if (h == null) {
            response.put("message", "Not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("hallId", h.getHallId());
        response.put("title", h.getTitle());
        response.put("description", h.getDescription());
        response.put("src", h.getSrc());
        response.put("managerId", h.getManager().getUid());
        response.put("managerName", h.getManager().getArtistId());

        List<Reel> reelList = reelService.getAllByHall(h);
        response.put("reeltapes", reelList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
