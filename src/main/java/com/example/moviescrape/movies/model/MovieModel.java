package com.example.moviescrape.movies.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class MovieModel {

	private int id;
	private String title;
	private String year;
	private String rating;
	private String description;

	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public MovieModel() {}
	
	public MovieModel(String title) {
		this.title=title;
	}
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "MovieModel [id=" + id + ", title=" + title + ", year=" + year + ", rating=" + rating + "]";
	}


	
}
