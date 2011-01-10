package models;

import play.db.jpa.Model;
import play.modules.search.Field;
import play.modules.search.Indexed;

@Indexed
public class MessyHouse extends Model {
	@Field
	public String name;

	@Field
	public MessyHouse neighbor;

	@Field(joinField = { "naame", "city", "city.name.name", "city.name", "city.region.country.capital.name" })
	public Street street;

	public MessyHouse(Street street, String name) {
		this.street = street;
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("MessyHouse[name=%s]", name);
	}
}
