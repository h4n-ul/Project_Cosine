package com.h4n_ul.wave.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    @Column(unique = true)
    private String src;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_uid")
    private Artist manager;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "Hall_managers",
        joinColumns = @JoinColumn(name = "hall_hallId"),
        inverseJoinColumns = @JoinColumn(name = "artist_uid")
    )
    private Set<Artist> subManagers;

    @OneToOne
    @JoinColumn(name = "hall_header_id")
    private FileArchive picture;
}
