package models;

import play.db.jpa.Model;
import play.modules.search.Field;
import play.modules.search.Indexed;

@Indexed
public class House extends Model {
	@Field
	public String name;

	@Field(joinField = "name")
	public House neighbor;

	@Field(joinField = { 
			"name",
			"city.name",
			"city.name.city",
			"city.region.country.name",
			"city.region.country.capital.name",
			"city.region.country.capital.region.name" })
	public Street street;

	public House(Street street, String name) {
		this.street = street;
		this.name = name;
	}
}
