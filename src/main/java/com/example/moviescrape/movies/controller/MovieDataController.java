package com.example.moviescrape.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moviescrape.movies.model.MovieModel;
import com.example.moviescrape.movies.service.MovieService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MovieDataController {

	@Autowired
	private MovieService service;
	
	@RequestMapping(path = "/imdb")
	public List<MovieModel> getData() {
		return service.getIMDBData();
	}
	
	@RequestMapping(path = "/rotten")
	public List<MovieModel> getDataRotten() {
		return service.getRottenData(); 
	}
	
	@RequestMapping(path = "rotten/{id}/desc")
	public String getDescriptionRotten(@PathVariable int id) {
		return service.getDescriptionRotten(id);
	}
}
