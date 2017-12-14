package com.airhacks;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Example of how to do bullet-proof system tests using Json-P and reference JSON files as specs
 * @author ratcashdev
 */
public class InterfaceTest {
	
	public JsonObject generateGenuineMascot() {
		return Json.createObjectBuilder()
				.add("name", "Duke")
				.add("role", "mascot").build();
	}
	
	public JsonObject generateFalseMascot() {
		return Json.createObjectBuilder()
				.add("name", "Ellington")
				.add("role", "mascott").build();
	}
	
	public JsonObject generateFalseMascotWithBadType() {
		return Json.createObjectBuilder()
				.add("name", "Ellington")
				.add("role", Json.createObjectBuilder().add("prop1", "random")).build();
	}
	
	public JsonObject generateWorkShopObj() {
		return Json.createObjectBuilder()
				.add("workshop", "Java EE 8 and Java 9")
				.add("date", "2017-12-14").build();
	}
	
	JsonObject readFile(String fileName) {
		try (InputStream fis = getClass().getResourceAsStream(fileName)) {
			JsonReader reader = Json.createReader(fis);
			JsonObject obj = reader.readObject();
			return obj;
		} catch (IOException ex) {
			Logger.getLogger(InterfaceTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	@Test
	public void testJsonInclValues() {
		String serviceSpecFileName = "service-mascot.json";
		JsonObject refObject = readFile(serviceSpecFileName);
		JsonArray diff1 = ExtendedJsonPatchGenerator.createDiff(generateGenuineMascot(), refObject);
		assertThat(diff1.size(), is(equalTo(0)));
		
		JsonArray diff2 = ExtendedJsonPatchGenerator.createDiff(refObject, generateFalseMascot());
		assertThat(diff2.size(), is(not(equalTo(0))));
	}
	
	@Test
	public void testJsonStructureOnly() {
		String serviceSpecFileName = "service-mascot.json";
		JsonObject refObject = readFile(serviceSpecFileName);
		
		JsonArray diff1 = ExtendedJsonPatchGenerator.createDiff(generateFalseMascot(), refObject);
		
		// not even the structure is correct
		assertTrue(ExtendedJsonPatchGenerator.isValueChangeOnly(diff1));
		
		// type difference should not be considered structural identity
		JsonArray diff2 = ExtendedJsonPatchGenerator.createDiff(refObject, generateFalseMascotWithBadType());
		assertFalse(ExtendedJsonPatchGenerator.isValueChangeOnly(diff2));
	}
	
	@Test
	public void testJsonBadInterface() {
		String serviceSpecFileName = "service-mascot.json";
		JsonObject refObject = readFile(serviceSpecFileName);
		JsonArray diff1 = ExtendedJsonPatchGenerator.createDiff(refObject, generateWorkShopObj());
		assertThat(diff1.size(), is(not(equalTo(0))));
		
		// not even the structure is correct
		assertFalse(ExtendedJsonPatchGenerator.isValueChangeOnly(diff1));
	}

}
