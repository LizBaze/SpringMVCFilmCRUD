package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		try {
		int id = Integer.parseInt(filmid);
		Film film = filmDao.findFilmById(id);
		List<Actor> actors = filmDao.findActorsByFilmId(id);
		if (film != null) {
			film.setCategory(filmDao.findCategoryByFilmId(film.getId()));

			mv.addObject("film", film);
			mv.addObject("actors", actors);
			mv.setViewName("WEB-INF/views/output.jsp");

		} else {
			mv.addObject("outputMessage", "No Film Found");

			mv.setViewName("WEB-INF/views/error.jsp");

		}
		} catch (NumberFormatException e) {
			mv.addObject("outputMessage", "No Film Found, please try entering a valid number");
			mv.setViewName("WEB-INF/views/error.jsp");
		}
		return mv;
	}
	@RequestMapping(path = "keyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView keyword(String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> film = filmDao.findFilmByKeyword(keyword);

		if (film != null) {

			for (Film film2 : film) {
				film2.setCategory(filmDao.findCategoryByFilmId(film2.getId()));
			}

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

	@RequestMapping(path = "createfilm.do", method = RequestMethod.POST, params = { "title", "description",
			"releaseYear", "languageID", "rentalDuration", "rentalRate", "length", "replacementCost", "rating",
			"features" })
	public ModelAndView createFilm(String title, String description, String releaseYear, String languageID,
			String rentalDuration, String rentalRate, String length, String replacementCost, String rating,
			String features, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		int id = 0;
		List<Actor> actors = filmDao.findActorsByFilmId(id);
		Film film = null;
		try {
			short year = !releaseYear.equals("") ? Short.parseShort(releaseYear) : 0;
			int duration = !rentalDuration.equals("") ? Integer.parseInt(rentalDuration) : 0;
			int langID = !languageID.equals("") ? Integer.parseInt(languageID) : 0;
			double rate = !rentalRate.equals("") ? Double.parseDouble(rentalRate) : 0;
			int filmLength = !length.equals("") ? Integer.parseInt(length) : 0;
			double cost = !replacementCost.equals("") ? Double.parseDouble(replacementCost) : 0;
			film = new Film(title, description, year, langID, duration, rate, filmLength, cost, rating, features);
		} catch (NumberFormatException e) {
			mv.addObject("outputMessage", "We were unable to add your film to the database, please try again");
			mv.setViewName("WEB-INF/views/error.jsp");
			return mv;
		}

		film = filmDao.createFilm(film);
		if (film == null || film.getId() == 0) {
			mv.setViewName("WEB-INF/views/error.jsp");
			mv.addObject("outputMessage", "We were unable to add your film to the database, please try again");
		} else {
			redir.addFlashAttribute("actors", actors);
			redir.addFlashAttribute("film", film);
			film.setCategory(filmDao.findCategoryByFilmId(film.getId()));
			
			mv.setViewName("redirect:created.do");
		}
		return mv;
	}
	
	@RequestMapping(path = "created.do", method = RequestMethod.GET)
	public ModelAndView filmCreated() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("WEB-INF/views/output.jsp");
		return mv;
	}

	@RequestMapping(path = "delete.do", params = "filmid")
	public ModelAndView deleteFilm(int filmid) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.findFilmById(filmid);
		film = filmDao.deleteFilm(film);
		if (film == null) {
			mv.addObject("outputMessage", "Film deletion successful");
		} else {
			mv.addObject("outputMessage", "We were unable to delete this film");
		}
		mv.setViewName("WEB-INF/views/error.jsp");
		return mv;
	}

	@RequestMapping(path = "updateFilm.do", method = RequestMethod.POST,
			params = { "id", "title", "description", "releaseYear", "languageID",
			"rentalDuration", "rentalRate", "length", "replacementCost", "rating", "features" })
	public ModelAndView updateFilm(RedirectAttributes redir, String id, String title, String description, String releaseYear, String languageID,
			String rentalDuration, String rentalRate, String length, String replacementCost, String rating,
			String features) {

		int filmid = 0;
		ModelAndView mv = new ModelAndView();
		Film film = null;
		short year = 0;
		int duration = 0;
		int langID = 0;
		double rate = 0;
		int filmLength = 0;
		double cost = 0;
		// looks up film by id, checks the rest of fields for 
		// empty inputs and sets them to the film's existing values
		try {
			filmid = Integer.parseInt(id);
			film = filmDao.findFilmById(filmid);
			if (film != null) {
			year = !releaseYear.equals("")  ? Short.parseShort(releaseYear) : film.getReleaseYear();
			duration = !rentalDuration.equals("")  ? Integer.parseInt(rentalDuration) : film.getRentalDuration();
			langID = !languageID.equals("")  ? Integer.parseInt(languageID) : film.getLanguageId();
			rate = !rentalRate.equals("")  ? Double.parseDouble(rentalRate) : film.getRentalRate();
			filmLength = !length.equals("")  ? Integer.parseInt(length) : film.getLength();
			cost = !replacementCost.equals("") ? Double.parseDouble(replacementCost) : film.getReplacementCost();
			title = title.equals("") ? film.getTitle() : title;
			description = description.equals("") ? film.getDescription() : description;
			rating = rating.equals("") ? film.getRating() : rating;
			features = features.equals("") ? film.getFeatures() : features;
			}
		} catch (NumberFormatException e) {
			mv.addObject("outputMessage", "We were unable to update this film with the information provided, please try again");
			mv.setViewName("WEB-INF/views/error.jsp");
		}
		List<Actor> actors = null;
		if (film != null) {
		film.setId(filmid);
		film.setTitle(title);
		film.setDescription(description);
		film.setReleaseYear(year);
		film.setLanguageId(langID);
		film.setRentalRate(rate);
		film.setRentalDuration(duration);
		film.setLength(filmLength);
		film.setReplacementCost(cost);
		film.setRating(rating);
		film.setFeatures(features);
		actors = ( filmDao.findActorsByFilmId(filmid) );
		film = filmDao.updateFilm(film);
		}

		if (film != null) {
			film.setCategory(filmDao.findCategoryByFilmId(film.getId()));
			redir.addFlashAttribute("film", film);
			redir.addFlashAttribute("actors", actors);
			mv.setViewName("redirect:filmupdated.do");

		} else {
			mv.addObject("outputMessage", "We were unable to update this film");
			mv.setViewName("WEB-INF/views/error.jsp");
		}

		return mv;
	}
	
	@RequestMapping(path = "filmupdated.do", method =RequestMethod.GET)
	public ModelAndView filmUpdated(Film film) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("WEB-INF/views/output.jsp");
		return mv;
	}

	@RequestMapping(path = "update.do", params= "filmid")
	public ModelAndView update(int filmid) {
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("filmid", filmid);
		mv.setViewName("WEB-INF/views/update.jsp");
		return mv;
	}

}
