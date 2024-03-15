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
import com.h4n_ul.wave.entity.Hall;
import com.h4n_ul.wave.repository.MixRepo;
import com.h4n_ul.wave.service.ArtistService;
import com.h4n_ul.wave.service.HallService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequiredArgsConstructor
@RequestMapping("/backend/hall")
public class HallController {
    public final ArtistService artistSvc;
    public final MixRepo mixRepo;
    public final ArtistController artistContr;
    public final HallService hallService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateHallDTO createHallDTO, HttpServletRequest request) {
        ResponseEntity<Artist> artist = artistContr.getLoginInfo(request);
        Map<String, String> response = new HashMap<>();
        if (!artist.getStatusCode().is2xxSuccessful()) {
            response.put("message", "Unauthorized");
            return new ResponseEntity<>(response, artist.getStatusCode());
        }

        Artist targetArtist = artist.getBody();
        
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
        response.put("mixtapes", mixRepo.findByHallId(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
