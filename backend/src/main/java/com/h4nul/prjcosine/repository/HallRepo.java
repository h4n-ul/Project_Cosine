package com.h4nul.prjcosine.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.h4nul.prjcosine.entity.Hall;
public interface HallRepo extends JpaRepository<Hall, String>{
    public Optional<Hall> findBySrc(String src);
    public List<Hall> findByTitleContaining(String query);
    public List<Hall> findByDescriptionContaining(String query);
}
