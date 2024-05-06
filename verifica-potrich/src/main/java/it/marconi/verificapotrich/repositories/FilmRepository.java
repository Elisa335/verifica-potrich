package it.marconi.verificapotrich.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.marconi.verificapotrich.domains.Film;

public interface FilmRepository extends JpaRepository<Film, UUID> {
    
}
