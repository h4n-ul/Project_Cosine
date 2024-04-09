package com.h4n_ul.wave.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h4n_ul.wave.entity.Artist;
import com.h4n_ul.wave.service.ArtistService;
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

    @GetMapping("artist")
    public ResponseEntity<Map<String, Object>> hall(@RequestParam("query") String query, @RequestBody() String filter) {
        List<Artist> searched = artistSvc.search(query, filter);
        Map<String, Object> response = new HashMap<>();
        for (Artist artist : searched) {
            List<Object> info = new ArrayList<>();
            info.add(artist.getArtistNname());
            info.add(artist.getDescription());

            response.put(artist.getArtistId(), info);
        }
        return new ResponseEntity<>(null);
    }
}