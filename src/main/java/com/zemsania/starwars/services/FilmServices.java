package com.zemsania.starwars.services;

import java.util.List;

import com.zemsania.starwars.entity.FilmEntity;

public interface FilmServices {
	
	public FilmEntity create(FilmEntity entity);

    public FilmEntity update(FilmEntity entity);
    
    public boolean delete(Integer codigo);

    public List<FilmEntity> selectAll();

    public FilmEntity findByCodigo(Integer codigo);

    public List<FilmEntity> findByEstado(String estado);
}
