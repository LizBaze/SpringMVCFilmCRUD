package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.InventoryItem;

@Component
public class FilmDAOImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String user = "student";
	private static final String pass = "student";

	public FilmDAOImpl() {
		 try {
		      Class.forName("com.mysql.cj.jdbc.Driver");
		    }
		    catch (ClassNotFoundException e) {
		      e.printStackTrace();
		      System.err.println("Error loading MySQL Driver");
		      throw new RuntimeException("Unable to load MySQL Driver class");
		    }
		
	}
	
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			String sql = "SELECT * FROM film WHERE id = ?";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getShort("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));

				film.setActors(findActorsByFilmId(filmId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	public List<Film> findFilmByKeyword(String keyword) {
		Film film = null;
		List<Film> films = null;

		try {
			// CONCAT('%' , ? , '%') is how I chose to escape single quotes within the query
			// The query outputs "WHERE title LIKE %?%" where ? is the user's input
			String sql = "SELECT * FROM film WHERE title LIKE CONCAT('%' , ? , '%') "
					+ " OR description LIKE CONCAT('%' , ? , '%')";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);

			ResultSet rs = stmt.executeQuery();

			// Deprecated in favor of counter variable //
//			if (rs.next()) {
//				films = new ArrayList<>();
//				int filmId = rs.getInt("id");
//				film = filmGenerator(rs);
//				film.setActors(findActorsByFilmId(filmId));
//				films.add(film);
//			}
			int counter = 0;
			while (rs.next()) {
				if (counter == 0) {
					films = new ArrayList<>();
					counter++;
				}
				int filmId = rs.getInt("id");
				film = filmGenerator(rs);
				film.setActors(findActorsByFilmId(filmId));
				films.add(film);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return films;
	}

	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			// ...
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
//			actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
			}
			// ...
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

//	public List<Film> findFilmsByActorId(int actorId) {
//		List<Film> films = new ArrayList<>();
//		try {
//			Connection conn = DriverManager.getConnection(URL, user, pass);
//			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
//			sql += " rental_rate, length, replacement_cost, rating, special_features "
//					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, actorId);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int filmId = rs.getInt(1);
//				String title = rs.getString(2);
//				String desc = rs.getString(3);
//				short releaseYear = rs.getShort(4);
//				int langId = rs.getInt(5);
//				int rentDur = rs.getInt(6);
//				double rate = rs.getDouble(7);
//				int length = rs.getInt(8);
//				double repCost = rs.getDouble(9);
//				String rating = rs.getString(10);
//				String features = rs.getString(11);
//				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
//						features);
//				films.add(film);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return films;
//	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, first_name, last_name FROM  actor "
					+ "JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			int counter = 0;
			while (rs.next()) {
				if (counter == 0) {
					actors = new ArrayList<>();
					counter++;
				}
				Actor actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
				actors.add(actor);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	public String findLanguageByFilm(Film film) {
		String language = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT name FROM language JOIN film ON language.id = film.language_id";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				language = rs.getString("name");
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}

	@Override
	public String findCategoryByFilmId(int filmId) {
		String category = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT name FROM category JOIN film_category ON category.id = film_category.category_id "
					+ "JOIN film ON film.id = film_category.film_id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				category = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override // Accepting a Film object as parameter rather than filmID makes it
				// easier to add the film title to the InventoryItem object for readability
	public List<InventoryItem> findInventoryItemByFilm(Film film) {
		List<InventoryItem> inventory = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM inventory_item JOIN film ON inventory_item.film_id = film.id WHERE film.title = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			ResultSet rs = stmt.executeQuery();

			int counter = 0;

			while (rs.next()) {
				if (counter == 0) {
					inventory = new ArrayList<>();
					counter++;
				}
				InventoryItem item = inventoryItemGenerator(rs, film);
				inventory.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventory;
	}

	private InventoryItem inventoryItemGenerator(ResultSet rs, Film film) {
		InventoryItem item = null;
		try {
			item = new InventoryItem();
			item.setFilmTitle(film.getTitle());
			item.setId(rs.getInt("id"));
			item.setFilmId(rs.getInt("film_id"));
			item.setStoreId(rs.getInt("store_id"));
			item.setMediaCondition(rs.getString("media_condition"));
			item.setLastUpdate(rs.getString("last_update"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	private Film filmGenerator(ResultSet rs) {
		Film film = null;
		try {
			film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
					rs.getShort("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
					rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
					rs.getString("rating"), rs.getString("special_features"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public Actor createActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO actor (first_name, last_name) " + " VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();

				if (keys.next()) {
					int newActorId = keys.getInt(1);
					actor.setId(newActorId);

					if (actor.getFilms() != null && actor.getFilms().size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);

						for (Film film : actor.getFilms()) {
							stmt.setInt(1, film.getId());
							stmt.setInt(2, newActorId);
							updateCount = stmt.executeUpdate();
						}
					}
				}
			} else {
				actor = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + actor);
		}
		return actor;
	}

	public Film createFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO film (title, language_id) VALUES (?, 1)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			int filmsCreated = stmt.executeUpdate();

			if (filmsCreated == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					film.setId(keys.getInt(1));

					if (film.getActors() != null && !film.getActors().isEmpty()) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?, ?)";
						stmt = conn.prepareStatement(sql);
						for (Actor actor : film.getActors()) {
							stmt.setInt(1, film.getId());
							stmt.setInt(2, actor.getId());
							stmt.executeUpdate();
						}
					}
				}
			} else {
				film = null;
			}

			conn.commit();
		} catch (SQLException e) {

		}

		return film;
	}

	public boolean updateActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE actor SET first_name=?, last_name=? " + " WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setInt(3, actor.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE FROM film_actor WHERE actor_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actor.getId());
				updateCount = stmt.executeUpdate();
				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);
				for (Film film : actor.getFilms()) {
					stmt.setInt(1, film.getId());
					stmt.setInt(2, actor.getId());
					updateCount = stmt.executeUpdate();
				}
				conn.commit(); // COMMIT TRANSACTION
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public Film deleteFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, film.getId());
			int deletions = stmt.executeUpdate();
			
			if (deletions == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {

		}

		return film;
	}
}
