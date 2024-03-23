package com.h4n_ul.wave.controller.dto;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MixDTO {
    private String title;
    private String contents;
    private List<MultipartFile> file;
    private List<Map<MultipartFile, AudioMetadata>> audioFiles;

    @Getter
    @Setter
    private static class AudioMetadata {
        private String title;
        private String artist;
    }
}
