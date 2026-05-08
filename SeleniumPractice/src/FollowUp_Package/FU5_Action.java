package FollowUp_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.List;

public class FU5_Action extends FU2_Login {

	@Test
	public void validateAction() throws Exception
	{
		System.out.println("========== ACTION (Dropdown) ==========");
		int caseNo = 1;

		WebElement dd = driver.findElement(By.id("action"));
		Select sel = new Select(dd);

		boolean disp = dd.isDisplayed();
		System.out.println("Case " + caseNo + " | displayed | Expected: true | Actual: " + disp);
		sa.assertTrue(disp, "BUG: action not displayed | Expected: true | Actual: " + disp);
		caseNo++;

		boolean en = dd.isEnabled();
		System.out.println("Case " + caseNo + " | enabled | Expected: true | Actual: " + en);
		sa.assertTrue(en, "BUG: action not enabled | Expected: true | Actual: " + en);
		caseNo++;

		String tag = dd.getTagName();
		System.out.println("Case " + caseNo + " | tagName | Expected: select | Actual: " + tag);
		sa.assertEquals(tag, "select", "BUG: action tagName | Expected: select | Actual: " + tag);
		caseNo++;

		String defaultVal = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | default value | Expected: contains SELECT | Actual: " + defaultVal);
		sa.assertTrue(defaultVal.contains("SELECT"), "BUG: default not --SELECT-- | Expected: contains SELECT | Actual: " + defaultVal);
		caseNo++;

		List<WebElement> options = sel.getOptions();
		System.out.println("Case " + caseNo + " | options count | Expected: >1 | Actual: " + options.size());
		sa.assertTrue(options.size() > 1, "BUG: no options | Expected: >1 | Actual: " + options.size());
		caseNo++;

		for (int i = 0; i < options.size(); i++) {
			System.out.println("  Option " + i + " | " + options.get(i).getText().trim());
		}

		java.util.Set<String> unique = new java.util.HashSet<>();
		boolean hasDup = false;
		for (WebElement opt : options) {
			if (!unique.add(opt.getText().trim())) { hasDup = true; break; }
		}
		System.out.println("Case " + caseNo + " | duplicate options | Expected: false | Actual: " + hasDup);
		sa.assertFalse(hasDup, "BUG: duplicate options found | Expected: no duplicates | Actual: duplicates exist");
		caseNo++;

		boolean hasEmpty = false;
		for (WebElement opt : options) {
			if (opt.getText().trim().isEmpty()) { hasEmpty = true; break; }
		}
		System.out.println("Case " + caseNo + " | empty option text | Expected: false | Actual: " + hasEmpty);
		sa.assertFalse(hasEmpty, "BUG: empty option text | Expected: all have text | Actual: empty exists");
		caseNo++;

		sel.selectByIndex(0);
		Thread.sleep(300);
		String idx0 = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | select index 0 | Expected: default/SELECT | Actual: " + idx0);
		sa.assertTrue(idx0.contains("SELECT"), "BUG: index 0 not default | Expected: SELECT | Actual: " + idx0);
		caseNo++;

		sel.selectByIndex(1);
		Thread.sleep(300);
		String idx1 = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | select index 1 | Expected: non-empty | Actual: " + idx1);
		sa.assertFalse(idx1.isEmpty(), "BUG: index 1 empty | Expected: non-empty | Actual: empty");
		caseNo++;

		sel.selectByIndex(options.size() - 1);
		Thread.sleep(300);
		String last = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | select last option | Expected: non-empty | Actual: " + last);
		sa.assertFalse(last.isEmpty(), "BUG: last option empty | Expected: non-empty | Actual: empty");
		caseNo++;

		sel.selectByIndex(1);
		Thread.sleep(300);
		String resel = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | re-select index 1 | Expected: " + idx1 + " | Actual: " + resel);
		sa.assertEquals(resel, idx1, "BUG: re-select mismatch | Expected: " + idx1 + " | Actual: " + resel);
		caseNo++;

		boolean multi = sel.isMultiple();
		System.out.println("Case " + caseNo + " | isMultiple | Expected: false | Actual: " + multi);
		sa.assertFalse(multi, "BUG: multi-select | Expected: false | Actual: " + multi);
		caseNo++;

		System.out.println("Case " + caseNo + " | class attribute | " + dd.getAttribute("class"));
		caseNo++;
		System.out.println("Case " + caseNo + " | name attribute | " + dd.getAttribute("name"));
		caseNo++;

		sel.selectByIndex(1);
		System.out.println("========== ACTION Complete ==========\n");
	}
}
