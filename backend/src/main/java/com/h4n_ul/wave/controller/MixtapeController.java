package com.h4n_ul.wave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h4n_ul.wave.entity.Mixtape;
import com.h4n_ul.wave.service.MixService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/backend/mixtape")
public class MixtapeController {
    private final MixService mixSvc;

    @PostMapping("create")
    public Mixtape createMix(HttpServletRequest request, String title, String contents) {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        String loginuid = (String) session.getAttribute("loginuid");
        System.out.println(loginuid);
        mixSvc.createMix(loginuid, title, contents, null);
        return null;
    }
}
