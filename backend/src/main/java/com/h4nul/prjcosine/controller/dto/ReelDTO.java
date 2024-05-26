package com.h4nul.prjcosine.controller.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReelDTO {
    private String title;
    private String contents;
    private String hallId;
    private List<MultipartFile> files;
    private List<AudioFiles> audio;
}
