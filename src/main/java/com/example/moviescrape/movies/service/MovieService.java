package com.example.moviescrape.movies.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.moviescrape.movies.model.MovieModel;

@Service
public class MovieService {
	

	private MovieModel movie;
	private List<MovieModel> titles;
	
	private List<MovieModel> titlesRotten = new ArrayList<>();
	
	public List<MovieModel> getIMDBData() {
		
		titles = new ArrayList<MovieModel>();
		
		try {
			
			Document doc = Jsoup.connect("https://www.imdb.com/chart/top/").userAgent("Chrome/17.0").get();
			
			Elements el = doc.getElementsByClass("titleColumn");
			Elements el2 = doc.getElementsByTag("strong");
			
			//Elements toataLista = doc.getElementsByClass("lister-list");
			
			System.out.println("Incepe...");
			
			
				int index=0;
			  for(Element element: el) {
			  
					  movie = new MovieModel();
					  
					  movie.setTitle(element.getElementsByTag("a").first().text());
					  movie.setYear(element.getElementsByTag("a").next().text());
					  movie.setId(++index);
					  
					  titles.add(movie);
				  
			  }
			  
			  int count=0;
			  for(Element r: el2) {
				  
				  titles.get(count).setRating(r.text());
				  count++;
				  
			  }
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return titles;
	}
	
	
	public List<MovieModel> getRottenData(){
		
		
		if(titlesRotten.isEmpty()) {
			try {
				Document doc = Jsoup.connect("https://www.metacritic.com/browse/movies/score/metascore/all/filtered").userAgent("Chrome/17.0").get();
				
			
				Elements elems = doc.getElementsByTag("h3");
				Elements year = doc.getElementsByClass("clamp-details");
				Elements rate = doc.getElementsByAttributeValueContaining("class", "metascore_w");
				
				System.out.println("bitch");
				
				int index=0;
				for(Element e: elems) {
					movie = new MovieModel();
					
					//e.getElementsByAttributeValueContaining(key, match)
					movie.setTitle(e.text());
					movie.setId(++index);
					titlesRotten.add(movie);
				}
				
				int i=0;
				for(Element y: year) {
					
					titlesRotten.get(i).setYear(y.removeClass("cert_rating Approved").text());
					//titles.get(i).setYear(y.getElementsByAttributeValueNot("class", "cert_rating Approved").text());
					//titles.get(i).setYear(year.next().text()); -- asta ia descrierea 
					i++;
				}
				
				
				  int y=0; 
				  //System.out.println(titlesRotten.lastIndexOf(titles.get(99)));
				  System.out.println(rate.size());
				
					  
					  for(Iterator<Element> it = rate.iterator(); it.hasNext();) {
						  if(y<100) {
							  Element temp = it.next();
							  titlesRotten.get(y).setRating(temp.text());
							  y++;
						  }else {
							  it.next();
							  it.remove();
						  }
						  
					  }
		
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return titlesRotten;
			
		}else return titlesRotten;
		
		
	}
	
	
	public String getDescriptionRotten(int id) {
		//titles.get(i).setYear(year.next().text()); -- asta ia descrierea 
		String description="";
		
		try {
			
			Document doc = Jsoup.connect("https://www.metacritic.com/browse/movies/score/metascore/all/filtered").userAgent("Chrome/17.0").get();
			
			Elements desc = doc.getElementsByClass("summary");
			
			MovieModel temp = titlesRotten.get(id-1);
			System.out.println(temp.getTitle());
			
			List<String> tempList = desc.eachText();
			temp.setDescription(tempList.get(id-1));
			
			description = temp.getDescription();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return description;
	
	}
	
}
