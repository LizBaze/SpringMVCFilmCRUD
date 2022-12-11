package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.InventoryItem;

public interface FilmDAO {
	public Film findFilmById(int filmId);

	public Actor findActorById(int actorId);

	public List<Actor> findActorsByFilmId(int filmId);

	public String findLanguageByFilm(Film film);

	public List<Film> findFilmByKeyword(String keyword);

	public String findCategoryByFilmId(int filmId);

	public List<InventoryItem> findInventoryItemByFilm(Film film);

	public Film createFilm(Film film);

	public Film deleteFilm(Film film);
}
