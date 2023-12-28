package com.zemsania.starwars.entity;

import java.util.Date;

public class FilmEntity {
	private Integer id;
	private String title;
	private Integer episodeId;
	private String opening_crawl;
	private String director;
	private String producer;
	private Date releaseDate;

	public FilmEntity() {
		super();
		id = 0;
		title = null;
		episodeId = 0;
		opening_crawl = null;
		director = null;
		producer = null;
		releaseDate = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Integer episodeId) {
		this.episodeId = episodeId;
	}

	public String getOpening_crawl() {
		return opening_crawl;
	}

	public void setOpening_crawl(String opening_crawl) {
		this.opening_crawl = opening_crawl;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}



}
