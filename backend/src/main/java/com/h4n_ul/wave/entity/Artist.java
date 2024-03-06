package com.h4n_ul.wave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Artist {
    @Id
    @Column(length = 500)
    private String uid;

    @Column(unique = true)
    private String artistId;
    private String artistNname;

    @Column(length = 1000)
    private String pwHash;
    @Column(length = 1000)
    private String salt;
    private int passes;

    @Column(length = 320)
    private String email;
}
