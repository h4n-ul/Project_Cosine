package com.h4n_ul.wave.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    private Hall hallId;

    private String title;
    private String contents;

    @Column(length = 500)
    private String owner;
    @OneToMany
    private List<FileArchive> files;
    @OneToMany
    private List<AudioArchs> audiofiles;

    @ManyToMany
    private List<Artist> master;
    @ManyToMany
    private List<Artist> degausse;

    private LocalDateTime release;
    private LocalDateTime lastRework;
}
