package com.h4nul.prjcosine.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.h4nul.prjcosine.entity.FileArchive;
public interface FileRepo extends JpaRepository<FileArchive, String> {}
