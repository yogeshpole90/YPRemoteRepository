package FollowUp_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.List;

public class FU4_CommunicationType extends FU2_Login {

	@Test
	public void validateCommunicationType() throws Exception
	{
		System.out.println("========== COMMUNICATION TYPE (Dropdown) ==========");
		int caseNo = 1;

		WebElement dd = driver.findElement(By.id("communicationType"));
		Select sel = new Select(dd);

		// Case 1
		boolean disp = dd.isDisplayed();
		System.out.println("Case " + caseNo + " | displayed | Expected: true | Actual: " + disp);
		sa.assertTrue(disp, "BUG: communicationType not displayed | Expected: true | Actual: " + disp);
		caseNo++;

		// Case 2
		boolean en = dd.isEnabled();
		System.out.println("Case " + caseNo + " | enabled | Expected: true | Actual: " + en);
		sa.assertTrue(en, "BUG: communicationType not enabled | Expected: true | Actual: " + en);
		caseNo++;

		// Case 3
		String tag = dd.getTagName();
		System.out.println("Case " + caseNo + " | tagName | Expected: select | Actual: " + tag);
		sa.assertEquals(tag, "select", "BUG: communicationType tagName | Expected: select | Actual: " + tag);
		caseNo++;

		// Case 4
		String defaultVal = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | default value | Expected: contains SELECT | Actual: " + defaultVal);
		sa.assertTrue(defaultVal.contains("SELECT"), "BUG: default not --SELECT-- | Expected: contains SELECT | Actual: " + defaultVal);
		caseNo++;

		// Case 5
		List<WebElement> options = sel.getOptions();
		System.out.println("Case " + caseNo + " | options count | Expected: >1 | Actual: " + options.size());
		sa.assertTrue(options.size() > 1, "BUG: no options | Expected: >1 | Actual: " + options.size());
		caseNo++;

		// Case 6: Print all
		for (int i = 0; i < options.size(); i++) {
			System.out.println("  Option " + i + " | " + options.get(i).getText().trim());
		}

		// Case 7: No duplicates
		java.util.Set<String> unique = new java.util.HashSet<>();
		boolean hasDup = false;
		for (WebElement opt : options) {
			if (!unique.add(opt.getText().trim())) { hasDup = true; break; }
		}
		System.out.println("Case " + caseNo + " | duplicate options | Expected: false | Actual: " + hasDup);
		sa.assertFalse(hasDup, "BUG: duplicate options found | Expected: no duplicates | Actual: duplicates exist");
		caseNo++;

		// Case 8: No empty text
		boolean hasEmpty = false;
		for (WebElement opt : options) {
			if (opt.getText().trim().isEmpty()) { hasEmpty = true; break; }
		}
		System.out.println("Case " + caseNo + " | empty option text | Expected: false | Actual: " + hasEmpty);
		sa.assertFalse(hasEmpty, "BUG: empty option text found | Expected: all options have text | Actual: empty text exists");
		caseNo++;

		// Case 9
		sel.selectByIndex(0);
		Thread.sleep(300);
		String idx0 = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | select index 0 | Expected: default/SELECT | Actual: " + idx0);
		sa.assertTrue(idx0.contains("SELECT"), "BUG: index 0 not default | Expected: SELECT | Actual: " + idx0);
		caseNo++;

		// Case 10
		sel.selectByIndex(1);
		Thread.sleep(300);
		String idx1 = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | select index 1 | Expected: non-empty | Actual: " + idx1);
		sa.assertFalse(idx1.isEmpty(), "BUG: index 1 empty | Expected: non-empty | Actual: empty");
		caseNo++;

		// Case 11
		sel.selectByIndex(options.size() - 1);
		Thread.sleep(300);
		String last = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | select last option | Expected: non-empty | Actual: " + last);
		sa.assertFalse(last.isEmpty(), "BUG: last option empty | Expected: non-empty | Actual: empty");
		caseNo++;

		// Case 12
		sel.selectByIndex(1);
		Thread.sleep(300);
		String resel = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | re-select index 1 | Expected: " + idx1 + " | Actual: " + resel);
		sa.assertEquals(resel, idx1, "BUG: re-select mismatch | Expected: " + idx1 + " | Actual: " + resel);
		caseNo++;

		// Case 13
		boolean multi = sel.isMultiple();
		System.out.println("Case " + caseNo + " | isMultiple | Expected: false | Actual: " + multi);
		sa.assertFalse(multi, "BUG: should not be multi-select | Expected: false | Actual: " + multi);
		caseNo++;

		// Case 14
		System.out.println("Case " + caseNo + " | class attribute | " + dd.getAttribute("class"));
		caseNo++;

		// Case 15
		System.out.println("Case " + caseNo + " | name attribute | " + dd.getAttribute("name"));
		caseNo++;

		sel.selectByIndex(1);
		System.out.println("========== COMMUNICATION TYPE Complete ==========\n");
	}
}
