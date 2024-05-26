package com.h4nul.prjcosine.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

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
    private Boolean isOriginal;

    private String title;
    private List<String> artist;
}
