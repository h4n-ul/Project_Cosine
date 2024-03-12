package com.h4n_ul.wave.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.h4n_ul.wave.entity.Artist;
public interface ArtistRepo extends JpaRepository<Artist, String> {
   public Optional<Artist> findByArtistId(String artistId);
   public Optional<Artist> findByEmail(String email);
}
