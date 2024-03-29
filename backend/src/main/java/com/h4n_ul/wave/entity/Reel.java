package com.h4n_ul.wave.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Reel {
    @Id @Column(length = 1000)
    private String reelId;

    @ManyToOne
    private Hall hallId;

    private String title;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "artist_uid")
    private Artist owner;
    @OneToMany
    private List<FileArchive> files;
    @OneToMany
    private List<AudioArchs> audiofiles;

    private List<Artist> master;
    private List<Artist> degausse;

    private LocalDateTime release;
    private LocalDateTime lastRework;
}
