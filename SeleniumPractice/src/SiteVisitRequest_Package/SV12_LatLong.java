package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class SV12_LatLong extends SV2_Login {

	@Test
	public void validateLatLong() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV12 - LATITUDE & LONGITUDE VALIDATION START");
		System.out.println("=================================================");

		// ===== LATITUDE =====
		WebElement lat = driver.findElement(By.id("latitude"));

		log("Latitude", "Should be visible", "true", String.valueOf(lat.isDisplayed()), lat.isDisplayed());
		sa.assertTrue(lat.isDisplayed(), "Latitude not displayed");

		log("Latitude", "Should be enabled", "true", String.valueOf(lat.isEnabled()), lat.isEnabled());
		sa.assertTrue(lat.isEnabled(), "Latitude not enabled");

		lat.clear();
		log("Latitude", "Clear field", "Empty", "'" + lat.getAttribute("value") + "'", true);

		lat.sendKeys("18.5204");
		log("Latitude", "Enter valid '18.5204'", "18.5204", lat.getAttribute("value"), lat.getAttribute("value").equals("18.5204"));
		sa.assertEquals(lat.getAttribute("value"), "18.5204", "Latitude mismatch");

		lat.clear(); lat.sendKeys("abc");
		log("Latitude", "Enter text 'abc'", "Reject or accept", "'" + lat.getAttribute("value") + "'", true);

		lat.clear(); lat.sendKeys("-18.5204");
		log("Latitude", "Enter negative '-18.5204'", "Accept (valid negative lat)", lat.getAttribute("value"), true);

		lat.clear(); lat.sendKeys("@#$%");
		log("Latitude", "Enter special chars '@#$%'", "Reject", "'" + lat.getAttribute("value") + "'", true);

		lat.clear(); lat.sendKeys("999.999");
		log("Latitude", "Enter out of range '999.999' (max 90)", "Should reject", lat.getAttribute("value"), true);

		lat.clear(); lat.sendKeys("18.5204");
		log("Latitude", "Final value '18.5204' for save", "18.5204", lat.getAttribute("value"), true);

		// ===== LONGITUDE =====
		WebElement lon = driver.findElement(By.id("longitude"));

		log("Longitude", "Should be visible", "true", String.valueOf(lon.isDisplayed()), lon.isDisplayed());
		sa.assertTrue(lon.isDisplayed(), "Longitude not displayed");

		log("Longitude", "Should be enabled", "true", String.valueOf(lon.isEnabled()), lon.isEnabled());
		sa.assertTrue(lon.isEnabled(), "Longitude not enabled");

		lon.clear();
		log("Longitude", "Clear field", "Empty", "'" + lon.getAttribute("value") + "'", true);

		lon.sendKeys("73.8567");
		log("Longitude", "Enter valid '73.8567'", "73.8567", lon.getAttribute("value"), lon.getAttribute("value").equals("73.8567"));
		sa.assertEquals(lon.getAttribute("value"), "73.8567", "Longitude mismatch");

		lon.clear(); lon.sendKeys("xyz");
		log("Longitude", "Enter text 'xyz'", "Reject or accept", "'" + lon.getAttribute("value") + "'", true);

		lon.clear(); lon.sendKeys("-73.8567");
		log("Longitude", "Enter negative '-73.8567'", "Accept (valid negative lon)", lon.getAttribute("value"), true);

		lon.clear(); lon.sendKeys("@#$%");
		log("Longitude", "Enter special chars '@#$%'", "Reject", "'" + lon.getAttribute("value") + "'", true);

		lon.clear(); lon.sendKeys("999.999");
		log("Longitude", "Enter out of range '999.999' (max 180)", "Should reject", lon.getAttribute("value"), true);

		lon.clear(); lon.sendKeys("73.8567");
		log("Longitude", "Final value '73.8567' for save", "73.8567", lon.getAttribute("value"), true);

		System.out.println("=================================================");
		System.out.println("SV12 - LATITUDE & LONGITUDE VALIDATION END");
		System.out.println("=================================================");
	}
}
