package com.h4nul.prjcosine.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mix {
    @Id @Column(length = 1000)
    private String mixId;

    @Column(length = 1000)
    private String parent;

    @Column(length = 500)
    private String artistId;

    @Column(length = 10000)
    private String contents;

    private Set<String> master;
    private Set<String> burn;

    private LocalDateTime release;
    private LocalDateTime lastRework;
}
