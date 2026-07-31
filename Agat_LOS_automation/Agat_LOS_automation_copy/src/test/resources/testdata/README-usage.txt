// ============================================================
// HOW TO USE ExcelReader IN YOUR TESTS
// ============================================================

// OPTION 1: Get single value
String amount = ExcelReader.get("Income", "incomeAmount");  // "125000000"

// OPTION 2: Get all data as Map
Map<String, String> data = ExcelReader.getData("LeadCreation");
String firstName = data.get("firstName");    // "Test"
String lastName = data.get("lastName");      // "User"
String tenure = data.get("requestedTenure"); // "30"

// OPTION 3: Get data from specific row (for multiple test data rows)
Map<String, String> row2 = ExcelReader.getData("LeadCreation", 1); // 2nd data row

// ============================================================
// EXAMPLE: Before (hardcoded)
// ============================================================
// incomePage.enterIncomeAmount("125000000");
// incomePage.selectIncomeType("1");

// ============================================================
// EXAMPLE: After (Excel driven)
// ============================================================
// Map<String, String> data = ExcelReader.getData("Income");
// incomePage.selectIncomeType(data.get("incomeType"));
// incomePage.enterIncomeAmount(data.get("incomeAmount"));
