package it.marconi.verificapotrich.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.marconi.verificapotrich.domains.Film;
import it.marconi.verificapotrich.domains.FilmForm;
import it.marconi.verificapotrich.services.FilmService;
import jakarta.validation.Valid;

//gli dico che è un controller
@Controller
@RequestMapping("/films")
public class FilmController {

    // richiede che il componente sia gestito dal framework
    @Autowired
    private FilmService filmService;

    @RequestMapping()
    public ModelAndView viewHomePage() {
        return new ModelAndView("film-homepage");
    }

    @GetMapping("/nuovo")
    public ModelAndView newContactForm() {
        return new ModelAndView("film-form").addObject(new FilmForm());
    }

    @PostMapping("/nuovo")
    public ModelAndView handleNewContact(
        @ModelAttribute @Valid FilmForm filmForm,
        BindingResult br    // esito della validazione (va inserito sempre dopo il parametro da validare)
    ) {

        // verifico la presenza di errori di validazione, e ricarico la pagina
        if (br.hasErrors())
            return new ModelAndView("film-form");

        Film film = filmService.save(filmForm);

        // applico pattern PRG (Post Redirect Get)
        return new ModelAndView("redirect:/films?codice=" + film.getCodice()); 
    }

    // permette di intercettare gli URL dotati di un parametro "id"
    @GetMapping(params = "codice")
    public ModelAndView showContact(@RequestParam("id") UUID filmId) {

        Optional<Film> opFilm = filmService.get(filmId);

        // controllo se il servizio mi ha passato un dato esistente
        if (opFilm .isPresent())
            return new ModelAndView("film-detail").addObject("film", opFilm.get());
        else // se è null sollevo un errore
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film non trovato");

    }

    @GetMapping
    public ModelAndView showFilmList() {
        return new ModelAndView("film-homepage").addObject("films", filmService.findAll());
    }

    @GetMapping("/delete/{codice}") 
    public ModelAndView deleteFilm(
        @PathVariable("codice") UUID filmCodice,
        RedirectAttributes attr
    ) {
        filmService.deleteById(filmCodice); 

        // aggiunto un boolean agli attributi del redirect
        attr.addFlashAttribute("deleted", true);
        return new ModelAndView("redirect:/films");
    }


    
}
