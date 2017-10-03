package stepDefinition;

import appModules.IMDB_Search_Action;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.IMDB_Page;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IMDB_Steps {
	
	private String director;
	int numberOfFilmsDirected;
	IMDB_Page imdb_page;

	@Given("^the director \"([^\"]*)\"$")
	public void the_director(String director) throws Throwable {
		this.director = director;
	}

	@When("^I search IMDB$")
	public void i_search_IMDB() throws Throwable {
		IMDB_Search_Action.search(director);
		assertThat(IMDB_Search_Action.isNamePresent(director),is(true));
		IMDB_Search_Action.clickOnLinkFor(director);
	}

	@Then("^I should find \"([^\"]*)\" films directed$")
	public void i_should_find_films_directed(String numberExpected) throws Throwable {
		int numberDirected = IMDB_Search_Action.getFilmsDirected(director);
		assertThat(numberDirected,is(Integer.parseInt(numberExpected)));
	}
	@Then("^those films should include$")
	public void those_films_should_include(DataTable dtFilmNamesAndYears) throws Throwable {
		List<FilmNameAndYear> filmNameAndYears = IMDB_Search_Action.getFilmsDirected();
		List<FilmNameAndYear> filmNameAndYearsExpected = convertDataTableToFilmNameAndYear(dtFilmNamesAndYears);
		boolean foundAllFilms = true;
		for (FilmNameAndYear filmNameAndYearExpected : filmNameAndYearsExpected) {
			boolean foundFilm = false;
			for (FilmNameAndYear filmNameAndYear : filmNameAndYears) {
				if (filmNameAndYear.filmName.equals(filmNameAndYearExpected.filmName)
						&& filmNameAndYear.year.equals(filmNameAndYearExpected.year)) foundFilm = true;
			}
			if (!foundFilm) foundAllFilms = false;
		}
		assertThat(foundAllFilms,is(true));
	}

	private List<FilmNameAndYear> convertDataTableToFilmNameAndYear(DataTable dtFilmNamesAndYears) {
		List<FilmNameAndYear> filmNameAndYears = new ArrayList<>();
		for (int i=0; i<dtFilmNamesAndYears.getGherkinRows().size(); i++) {
			filmNameAndYears.add(new FilmNameAndYear(
					dtFilmNamesAndYears.getGherkinRows().get(i).getCells().get(0),
					Integer.parseInt(dtFilmNamesAndYears.getGherkinRows().get(i).getCells().get(1))));
		}
		return filmNameAndYears;
	}

	public static class FilmNameAndYear {
		private String filmName;
		private Integer year;

		public FilmNameAndYear(String filmName, Integer year) {
			this.filmName = filmName;
			this.year = year;
		}

		public String getFilmName() {
			return filmName;
		}

		public Integer getYear() {
			return year;
		}

		public void setFilmName(String filename) {
			this.filmName = filmName;
		}

		public void setYear(String year) {
			this.year = Integer.parseInt(year);
		}

	}
}
