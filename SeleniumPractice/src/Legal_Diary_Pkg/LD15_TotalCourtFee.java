package Legal_Diary_Pkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD15_TotalCourtFee extends LD2_Login {

	public void validateTotalCourtFee() {
		WebElement f = driver.findElement(By.id("totalCourtFee_txt"));
		String fn = "Total Court Fee";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());

		String ro = f.getAttribute("readonly"); String dis = f.getAttribute("disabled");
		log(fn, "Check ReadOnly/Disabled", "readonly or disabled", "readonly=" + ro + " disabled=" + dis, ro != null || dis != null);

		String autoVal = f.getAttribute("value");
		logInfo(fn, "Auto-Calculated Value", autoVal);

		boolean notEditable = false;
		try {
			f.clear(); f.sendKeys("9999"); String after = f.getAttribute("value");
			notEditable = !after.equals("9999");
		} catch (Exception e) { notEditable = true; }
		log(fn, "User cannot type (read-only)", "true", String.valueOf(notEditable), notEditable);

		try {
			double cf = getVal("courtFee_txt"); double pf = getVal("processFee_txt"); double rb = getVal("replevinBondFee_txt");
			double ef = getVal("executionFee_txt"); double pt = getVal("petitionFee_txt"); double of = getVal("otherFee_txt");
			double expected = cf + pf + rb + ef + pt + of;
			double actual = autoVal.isEmpty() ? 0 : Double.parseDouble(autoVal);
			log(fn, "Sum check (CF+PF+RB+EF+PT+OF)", String.valueOf(expected), String.valueOf(actual), expected == actual);
		} catch (Exception e) {
			logInfo(fn, "Sum check ERROR", e.getMessage());
		}
	}

	private double getVal(String id) {
		try { String v = driver.findElement(By.id(id)).getAttribute("value"); return (v != null && !v.isEmpty()) ? Double.parseDouble(v) : 0; }
		catch (Exception e) { return 0; }
	}
}
