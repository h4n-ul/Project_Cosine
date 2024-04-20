package com.h4n_ul.wave.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hall {
    @Id @Column(length = 500)
    private String hallId;

    private String title;
    private String description;
    @Column(unique = true)
    private String src;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Hall_manager")
    private Artist manager;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "Hall_subManagers",
        joinColumns = @JoinColumn(name = "hall_hallId"),
        inverseJoinColumns = @JoinColumn(name = "artist_uid")
    )
    private Set<Artist> subManagers;
    private Set<String> subscribed_by;

    @OneToOne
    @JoinColumn(name = "hall_header_id")
    private FileArchive picture;
}
