package com.h4n_ul.wave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h4n_ul.wave.entity.Mixtape;
import com.h4n_ul.wave.service.MixService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/backend/mixtape")
public class MixtapeController {
    private final MixService mixSvc;

    @PostMapping("create")
    public Mixtape createMix(HttpServletRequest request, String title, String contents) {
        HttpSession session = request.getSession(false);
        System.out.println(session.getId());
        String loggedInArtist = (String) session.getAttribute("loggedInArtist");
        System.out.println(loggedInArtist);
        mixSvc.createMix(loggedInArtist, title, contents, null);
        return null;
    }

    @GetMapping("{id}")
    public Mixtape getMix(@PathVariable String id) {
        // HttpSession session = request.getSession();
        // System.out.println(session.getId());
        // String loggedInArtist = (String) session.getAttribute("loggedInArtist");
        // System.out.println(loggedInArtist);
        // mixSvc.createMix(loggedInArtist, title, contents, null);
        return null;
    }

    @PostMapping("rework")
    public Mixtape rework(HttpServletRequest request, String id, String title, String contents) {
        // HttpSession session = request.getSession();
        // System.out.println(session.getId());
        // String loggedInArtist = (String) session.getAttribute("loggedInArtist");
        // System.out.println(loggedInArtist);
        // mixSvc.createMix(loggedInArtist, title, contents, null);
        return null;
    }
}
