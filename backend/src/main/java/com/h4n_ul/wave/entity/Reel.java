package com.h4n_ul.wave.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reel {
    @Id @Column(length = 1000)
    private String reelId;

    private String hallId;

    private String title;
    @Column(length = 1073741824)
    private String contents;

    @Column(length = 500)
    private String artistId;
    @OneToMany
    private List<FileArchive> files;
    @OneToMany
    private List<AudioArchs> audiofiles;

    private Set<String> master;
    private Set<String> degausse;

    private LocalDateTime release;
    private LocalDateTime lastRework;

    private Integer dynamicRange;
}
