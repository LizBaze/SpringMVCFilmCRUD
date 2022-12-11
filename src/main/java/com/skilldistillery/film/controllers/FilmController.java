package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAOImpl;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAOImpl filmDao;

	@RequestMapping("home.do")
	public String home() {

		return "WEB-INF/views/home.jsp";
	}

	@RequestMapping(path = "output.do", params = "filmid", method = RequestMethod.GET)
	public ModelAndView output(String filmid) {
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(filmid);
		Film film = filmDao.findFilmById(id);
        List <Actor> actors = filmDao.findActorsByFilmId(id);
		if (film != null) {
			mv.addObject("film", film);
            mv.addObject("actors", actors);
			mv.setViewName("WEB-INF/views/output.jsp");

		} else {
			mv.addObject("outputMessage", "No Film Found");

			mv.setViewName("WEB-INF/views/error.jsp");
            
		}
		return mv;
	}
	
	@RequestMapping(path = "keyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView keyword(String keyword) {
		ModelAndView mv = new ModelAndView();
		int id = 0;
		List <Actor> actors = filmDao.findActorsByFilmId(id);
		List<Film> film = filmDao.findFilmByKeyword(keyword);
         
		
		
		if (film != null) {
		   for (Film film2 : film) {
			film2.setActors(filmDao.findActorsByFilmId(film2.getId()));
		}
			
			mv.addObject("FilmList", film);
            
			mv.setViewName("WEB-INF/views/keywordformat.jsp");

		} else {
			mv.addObject("outputMessage", "No Film Found");

			mv.setViewName("WEB-INF/views/error.jsp");
		}
		return mv;
	}
	// TODO separate jsp's for successful and unsuccessful queries

	@RequestMapping(path = "createfilm.do", method = RequestMethod.POST, params = { "title", "description",
			"releaseYear", "languageID", "rentalDuration", "rentalRate", "length", "replacementCost", "rating",
			"features" })
	public ModelAndView createFilm(String title, String description, String releaseYear, String languageID,
			String rentalDuration, String rentalRate, String length, String replacementCost, String rating,
			String features) {
		ModelAndView mv = new ModelAndView();
		int id = 0;
		List <Actor> actors = filmDao.findActorsByFilmId(id);
		Film film = null;
		try {
			short year = Short.parseShort(releaseYear);
			int duration = Integer.parseInt(rentalDuration);
			int langID = Integer.parseInt(languageID);
			double rate = Double.parseDouble(rentalRate);
			int filmLength = Integer.parseInt(length);
			double cost = Double.parseDouble(replacementCost);
			film = new Film(title, description, year, langID, duration, rate, filmLength, cost, rating, features);
		} catch (NumberFormatException e) {
			mv.addObject("film", "We were unable to add your film to the database, please try again");
			 mv.addObject("actors", actors);
			mv.setViewName("WEB-INF/views/output.jsp");
			return mv;
		}

		film = filmDao.createFilm(film);
		if (film != null) {
			mv.setViewName("WEB-INF/views/output.jsp");
			
			mv.addObject("film", film);
		} else {
			mv.setViewName("WEB-INF/views/error.jsp");
			mv.addObject("outputMessage", "We were unable to add your film to the database, please try again");
		}
		return mv;
	}

	@RequestMapping(path = "delete.do", params = "filmid")
	public ModelAndView deleteFilm(int filmid) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.findFilmById(filmid);
		film = filmDao.deleteFilm(film);
		if (film == null) {
			mv.addObject("outputMessage", "Film deletion successful");
		}
		mv.setViewName("WEB-INF/views/error.jsp");
		return mv;
	}

	@RequestMapping(path = "updateFilm.do", params = { "id", "title", "description", "releaseYear", "languageID",
			"rentalDuration", "rentalRate", "length", "replacementCost", "rating", "features" })
	public ModelAndView updateFilm(String id, String title, String description, String releaseYear,
			String languageID, String rentalDuration, String rentalRate, String length, String replacementCost,
			String rating, String features) {
		
		int filmid = 0;
		List <Actor> actors = filmDao.findActorsByFilmId(filmid);
		ModelAndView mv = new ModelAndView();
		Film film = null;
		short year = 0;
		int duration = 0;
		int langID = 0;
		double rate = 0;
		int filmLength = 0;
		double cost = 0;
		try {
			filmid = id != null ? Integer.parseInt(id) : 0;
			year = releaseYear != null ? Short.parseShort(releaseYear) : 0;
			duration = rentalDuration != null ? Integer.parseInt(rentalDuration) : 0;
			langID = languageID != null ? Integer.parseInt(languageID) : 0;
			rate = rentalRate != null ? Double.parseDouble(rentalRate) : 0;
			filmLength = length != null ? Integer.parseInt(length) : 0;
			cost = replacementCost != null ? Double.parseDouble(replacementCost) : 0;
		} catch (NumberFormatException e) {
			mv.addObject("outputMessage", "We were unable to add your film to the database, please try again");
			mv.setViewName("WEB-INF/views/error.jsp");
		}
		film = new Film();
		film.setId(filmid);
		film.setTitle(title);
		film.setDescription(description);
		film.setReleaseYear(year);
		film.setLanguageId(langID);
		film.setRentalDuration(duration);
		film.setLength(filmLength);
		film.setReplacementCost(cost);
		film.setRating(rating);
		film.setFeatures(features);
		film = filmDao.updateFilm(film);

		if (film != null) {
			mv.addObject("film", film);
			 mv.addObject("actors", actors);
			mv.setViewName("WEB-INF/views/output.jsp");
		} else {
			mv.addObject("outputMessage", "We were unable to update this film");
			mv.setViewName("WEB-INF/views/error.jsp");
		}

		return mv;
	}
	
	
	
	
}
