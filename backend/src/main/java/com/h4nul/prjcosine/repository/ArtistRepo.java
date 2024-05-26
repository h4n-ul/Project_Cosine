package com.h4nul.prjcosine.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.h4nul.prjcosine.entity.Artist;
public interface ArtistRepo extends JpaRepository<Artist, String> {
   public Optional<Artist> findByArtistId(String artistId);
   public Optional<Artist> findByEmail(String email);
   public List<Artist> findByArtistNnameContaining(String keyword);
}
