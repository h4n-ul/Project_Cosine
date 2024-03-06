package com.h4n_ul.wave.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FileArchive {
    @Id
    private String fileId;

    private String origFileName;
    private String location;
    private String isMusic;
}
