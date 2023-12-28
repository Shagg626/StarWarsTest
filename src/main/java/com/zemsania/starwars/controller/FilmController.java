package com.zemsania.starwars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zemsania.starwars.entity.FilmEntity;
import com.zemsania.starwars.services.FilmServices;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping(FilmController.PATH)
public class FilmController {

    public static final String PATH = "/concepto";

    @Autowired
    FilmServices service;
    
    @PostConstruct
    public void init() {
    	
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getFilmById(@PathVariable String id) {
        if (id.length() > 10) {
            return new ResponseEntity<>("error en la solicitud", HttpStatus.BAD_REQUEST);
        }

        try {
            Integer filmId = Integer.parseInt(id);

            if (service.findByCodigo(filmId).getReleaseDate().equals(null)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            FilmEntity filmFromApi = service.findByCodigo(filmId);

            if (filmFromApi != null && filmFromApi.getEpisodeId() != null &&
                filmFromApi.getTitle() != null && filmFromApi.getReleaseDate() != null) {

                FilmEntity savedFilm = service.create(filmFromApi);

                return new ResponseEntity<>(savedFilm, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error en la solicitud", HttpStatus.BAD_REQUEST);
            }

        } catch (NumberFormatException e) {
            return new ResponseEntity<>("error en la solicitud", HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FilmEntity> updateFilm(@PathVariable Integer id, @RequestBody FilmEntity updatedFilm) {
        try {
            FilmEntity result = service.update(updatedFilm);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
