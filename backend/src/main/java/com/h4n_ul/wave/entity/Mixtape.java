package com.h4n_ul.wave.entity;

import java.util.List;

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
public class Mixtape {
    @Id
    private String mixId;

    private String title;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "artist_uid")
    private Artist owner;
    @OneToMany
    private List<FileArchive> files;
}
