package com.h4n_ul.wave.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.h4n_ul.wave.entity.Reel;
import java.util.List;
public interface ReelRepo extends JpaRepository<Reel, String>{
    public List<Reel> findByHallId(String hall);
    public List<Reel> findByArtistId(String artist);
    public List<Reel> findByTitleContaining(String keyword);
    public List<Reel> findByContentsContaining(String keyword);
}
