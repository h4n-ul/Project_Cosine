package com.h4n_ul.wave.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.h4n_ul.wave.entity.AudioArchs;
public interface AudiRepo extends JpaRepository<AudioArchs, String> {}
