package com.h4nul.prjcosine.controller.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AudioFiles {
    private MultipartFile file;
    private String title;
    private List<String> artist;
    private Boolean isOriginal;
}
