import models.City;
import models.Country;
import models.House;
import models.MessyHouse;
import models.Region;
import models.Street;

import org.apache.lucene.document.Document;
import org.junit.Test;

import play.modules.search.store.ConvertionUtils;
import play.test.UnitTest;

public class ConvertionUtilsTest extends UnitTest {
	@Test
	public void testToDocumentWithMessedUpJoinFields() throws Exception {
		MessyHouse myHouse = new MessyHouse(new Street(new City(null, "Messy city"), "Messy street"), null);
		myHouse.neighbor = myHouse;

		Document document = ConvertionUtils.toDocument(myHouse);

		assertEquals("MessyHouse[name=null]", 	document.getField("neighbor").stringValue()); // toString
		assertEquals("City[name=Messy city]", 	document.getField("street.city").stringValue()); // toString
		assertEquals("Messy city", 				document.getField("street.city.name").stringValue());
		assertEquals(5, document.getFields().size()); // including _docID and allfield
	}

	@Test
	public void testToDocumentWithMissingNestedJoinFields() throws Exception {
		House myHouse = new House(null, "Empty house");

		Document document = ConvertionUtils.toDocument(myHouse);

		assertEquals("Empty house", document.getField("name").stringValue());
		assertEquals(3, document.getFields().size()); // including _docID and allfield
	}

	@Test
	public void testToDocumentWithMultipleNestedJoinFields() throws Exception {
		Country norway 		= new Country("Norway");
		norway.capital 		= new City(new Region(norway, "Østlandet"), "Oslo");
		Region trondelag 	= new Region(norway, "Trøndelag");
		City trondheim 		= new City(trondelag, "Trondheim");
		Street street 		= new Street(trondheim, "Street");
		House myHouse 		= new House(street, "My house");

		Document document = ConvertionUtils.toDocument(myHouse);

		assertEquals("My house", 	document.getField("name").stringValue());
		assertEquals("Street", 		document.getField("street.name").stringValue());
		assertEquals("Trondheim", 	document.getField("street.city.name").stringValue());
		assertEquals("Norway", 		document.getField("street.city.region.country.name").stringValue());
		assertEquals("Oslo", 		document.getField("street.city.region.country.capital.name").stringValue());
		assertEquals(7, document.getFields().size()); // including _docID and allfield
	}
}
