package com.h4n_ul.wave.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.h4n_ul.wave.entity.Mixtape;
public interface MixRepo extends JpaRepository<Mixtape, String>{}
