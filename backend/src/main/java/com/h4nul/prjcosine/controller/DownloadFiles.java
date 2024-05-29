package com.h4nul.prjcosine.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/download")
public class DownloadFiles {
    @GetMapping("reel/{id}")
    public String getAllFilesFromReel(@PathVariable("id") String id) {
        return new String();
    }
    @GetMapping("{id}")
    public String getFileById(@PathVariable("id") String fileId) {
        return new String();
    }
    
}
