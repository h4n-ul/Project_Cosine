package com.h4n_ul.wave.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.entity.Hall;
import com.h4n_ul.wave.entity.Reel;
import com.h4n_ul.wave.service.ArtistService;
import com.h4n_ul.wave.service.FileService;
import com.h4n_ul.wave.service.HallService;
import com.h4n_ul.wave.service.ReelService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/backend/search")
public class SearchController {
    private final HallService hallSvc;
    private final ReelService reelSvc;
    private final ArtistService artistSvc;
    private final FileService fileSvc;

    @GetMapping("artist")
    public ResponseEntity<Map<String, Object>> artist(@RequestParam("query") String query, @RequestBody() String filter) {
        List<Artist> searched = artistSvc.search(query, filter);
        Map<String, Object> response = new HashMap<>();
        for (Artist artist : searched) {
            List<Object> info = new ArrayList<>();
            info.add(artist.getArtistNname());
            info.add(artist.getDescription());

            response.put(artist.getArtistId(), info);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("hall")
    public ResponseEntity<Map<String, Object>> hall(@RequestParam("query") String query, @RequestBody() String filter) {
        List<Hall> searched = hallSvc.search(query, filter);
        Map<String, Object> response = new HashMap<>();
        for (Hall hall : searched) {
            List<Object> info = new ArrayList<>();
            info.add(hall.getTitle());
            info.add(hall.getDescription());
            info.add(fileSvc.getFile(hall.getPicture().getFileId()));

            response.put(hall.getHallId(), info);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("reel")
    public ResponseEntity<Map<String, Object>> reel(@RequestParam("query") String query, @RequestBody() String filter) {
        List<Reel> searched = reelSvc.search(query, filter);
        Map<String, Object> response = new HashMap<>();
        for (Reel reel : searched) {
            List<Object> info = new ArrayList<>();
            info.add(reel.getTitle());
            info.add(reel.getContents());

            response.put(reel.getReelId(), info);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
