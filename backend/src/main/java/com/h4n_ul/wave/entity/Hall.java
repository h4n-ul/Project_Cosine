package com.h4n_ul.wave.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    private String manager;
    private Set<String> subManagers;
    private Set<String> subscribed_by;

    @OneToOne
    @JoinColumn(name = "hall_header_id")
    private FileArchive picture;
    
    private Set<String> popularityBuffer; // 30분 단위 버퍼

    private Integer popularity;
}
