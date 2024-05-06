package it.marconi.verificapotrich.services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.marconi.verificapotrich.domains.Film;
import it.marconi.verificapotrich.domains.FilmForm;
import it.marconi.verificapotrich.repositories.FilmRepository;


@Service
public class FilmService {
    
    @Autowired
    private FilmRepository filmRepo;

    public Film save(FilmForm filmForm) {

        Film f = mapFilm(filmForm);
        return filmRepo.save(f);
    }

    private Film mapFilm(FilmForm filmForm) {

        Film f = new Film();
        f.setTitolo(filmForm.getTitolo());
        f.setGenere(filmForm.getGenere());
        f.setAnno(filmForm.getAnno());
        f.setVoto(filmForm.getVoto());
        
        return f;
    }

    // un Optional è un contenitore che controlla l'effettiva presenza di un valore non null
    public Optional<Film> get(UUID id) {
        return filmRepo.findById(id);
    }

    // recupera tutti i contatti dal database
    public List<Film> findAll() {
        return filmRepo.findAll();
    }

    // elimina un contatto noto il suo id
    public void deleteById(UUID id) {
        filmRepo.deleteById(id); 
    }


}
