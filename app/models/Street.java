package models;

import play.db.jpa.Model;

public class Street extends Model {
	public City city;

	public String name;

	public Street(City city, String name) {
		this.city = city;
		this.name = name;
	}
}
