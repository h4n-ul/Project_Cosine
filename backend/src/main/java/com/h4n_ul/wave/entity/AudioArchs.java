package com.h4n_ul.wave.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AudioArchs {
    @Id @Column(length = 2048)
    private String fileId;

    private String origFileName;
    private String location;

    private String title;
    private String artist;
}
