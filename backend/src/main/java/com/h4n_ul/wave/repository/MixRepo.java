package com.h4n_ul.wave.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.h4n_ul.wave.entity.Hall;
import com.h4n_ul.wave.entity.Mixtape;
import java.util.List;
public interface MixRepo extends JpaRepository<Mixtape, String>{
    public List<Mixtape> findByHallId(Hall hall);
}
