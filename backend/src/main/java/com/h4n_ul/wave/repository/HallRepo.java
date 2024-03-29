package com.h4n_ul.wave.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.h4n_ul.wave.entity.Hall;
public interface HallRepo extends JpaRepository<Hall, String>{
    public Optional<Hall> findBySrc(String src);
}
