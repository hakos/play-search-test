package models;

import play.db.jpa.Model;

public class City extends Model {
	public String name;

	public Region region;

	public City(Region region, String name) {
		this.region = region;
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("City[name=%s]", name);
	}
}
