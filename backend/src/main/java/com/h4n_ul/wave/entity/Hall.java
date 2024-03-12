package com.h4n_ul.wave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Hall {
    @Id @Column(length = 500)
    private String hallId;

    private String title;
    private String description;
    private String src;

    @ManyToOne
    @JoinColumn(name = "artist_uid")
    private Artist owner;

    private FileArchive picture;
}
