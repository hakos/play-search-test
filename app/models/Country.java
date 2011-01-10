package models;

import play.db.jpa.Model;

public class Country extends Model {
	public City capital;

	public String name;

	public Country(String name) {
		this.name = name;
	}
}
