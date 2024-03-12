package com.h4n_ul.wave.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.h4n_ul.wave.entity.FileArchive;
public interface FileRepo extends JpaRepository<FileArchive, String> {}
