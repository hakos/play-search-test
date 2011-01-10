package models;

import play.db.jpa.Model;

public class Region extends Model {
	public Country country;

	public String name;

	public Region(Country country, String name) {
		this.country = country;
		this.name = name;
	}
}
