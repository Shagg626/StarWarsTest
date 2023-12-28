package com.zemsania.starwars.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zemsania.starwars.entity.FilmEntity;
import com.zemsania.starwars.services.FilmServices;

import jakarta.persistence.EntityNotFoundException;

public class FilmControllerTest {

	@Mock
    private FilmServices filmService;

    @InjectMocks
    private FilmController filmController;

    @Test
    public void testGetFilmById_ValidId_ReturnsFilm() {
        Integer validId = 1;
        FilmEntity mockFilm = new FilmEntity(); 

        when(filmService.findByCodigo(validId)).thenReturn(mockFilm);

        ResponseEntity<Object> response = filmController.getFilmById(validId + "");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetFilmById_InvalidId_ReturnsNoContent() {
        Integer invalidId = 99999999;

        when(filmService.findByCodigo(invalidId)).thenReturn(null);

        ResponseEntity<Object> response = filmController.getFilmById(invalidId + "");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetFilmById_InvalidIdFormat_ReturnsBadRequest() {
        String invalidId = "not_a_valid_id";

        ResponseEntity<Object> response = filmController.getFilmById(invalidId + "");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    public void testUpdateFilm_ValidId_ReturnsUpdatedFilm() {
        Integer validId = 1;
        FilmEntity updatedFilm = new FilmEntity();
        
        when(filmService.update(any(FilmEntity.class))).thenReturn(updatedFilm);

        ResponseEntity<FilmEntity> response = filmController.updateFilm(validId, updatedFilm);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateFilm_InvalidId_ReturnsNotFound() {
        Integer invalidId = 999999999;
        FilmEntity updatedFilm = new FilmEntity(); 

        when(filmService.update(any(FilmEntity.class))).thenThrow(new EntityNotFoundException("No se encontró el registro"));

        ResponseEntity<FilmEntity> response = filmController.updateFilm(invalidId, updatedFilm);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteFilm_ValidId_ReturnsNoContent() {
        Integer validId = 1;

        doNothing().when(filmService).delete(eq(validId));

        ResponseEntity<Void> response = filmController.deleteFilm(validId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteFilm_InvalidId_ReturnsNotFound() {
        Integer invalidId = 9999999;

        doThrow(new EntityNotFoundException("No se encontró el registro")).when(filmService).delete(eq(invalidId));

        ResponseEntity<Void> response = filmController.deleteFilm(invalidId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
}
