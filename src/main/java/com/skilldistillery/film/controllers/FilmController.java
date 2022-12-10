package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAOImpl;
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
		mv.setViewName("WEB-INF/views/output.jsp");
		
    if(film != null) {		    
    	mv.addObject("film", film);

    } else {
    	mv.addObject("film", "No Film Found");
    }
    return mv;
	}
	
}
