package com.zemsania.starwars.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zemsania.starwars.entity.FilmEntity;

public interface FilmDAO extends PagingAndSortingRepository<FilmEntity, Integer>{
	
	@Query(value = "SELECT c FROM FilmEntity c WHERE c.episode_id = ?1")
    List<FilmEntity> findByEpisodeId( String episode_id );
	
}
