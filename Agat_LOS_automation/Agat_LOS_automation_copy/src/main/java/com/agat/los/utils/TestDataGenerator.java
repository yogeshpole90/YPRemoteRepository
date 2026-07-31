package com.agat.los.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

/**
 * Run this ONCE to generate TestData.xlsx
 * After generation, delete this file or keep for reference.
 * 
 * Usage: Right-click → Run As → Java Application
 */
public class TestDataGenerator {

    public static void main(String[] args) throws Exception {
        Workbook wb = new XSSFWorkbook();

        // ========== Sheet 1: LeadCreation ==========
        createSheet(wb, "LeadCreation", new String[]{
            "firstName", "middleName", "lastName", "countryCode", "dob",
            "loanOfficer", "idType", "idNumber", "applicationType",
            "product", "otp", "requestedAmount", "requestedTenure", "primarySource"
        }, new String[]{
            "Test", "Auto", "User", "998", "01-01-1995",
            "n.namozov", "Passport", "AA1234567", "INDIVIDUAL",
            "1", "1234", "90000000", "30", "1"
        });

        // ========== Sheet 2: BasicDetails ==========
        createSheet(wb, "BasicDetails", new String[]{
            "gender", "dob", "maritalStatus", "nationality", "countryOfBirth",
            "placeOfBirth", "educationLevel", "dependents", "primarySource",
            "relatedParty", "guarantorAmount"
        }, new String[]{
            "1", "01-01-1995", "1", "01", "UZ",
            "Tashkent", "3", "0", "1",
            "N", "0"
        });

        // ========== Sheet 3: Address ==========
        createSheet(wb, "Address", new String[]{
            "addressType", "startDate", "province", "mohallaSearch",
            "addressLine", "registrationDate", "cadastre"
        }, new String[]{
            "1", "01-01-2020", "11", "test",
            "Tashkent City Center", "01-01-2020", "12345"
        });

        // ========== Sheet 4: Identification ==========
        createSheet(wb, "Identification", new String[]{
            "placeOfIssuance"
        }, new String[]{
            "Tashkent"
        });

        // ========== Sheet 5: Employment ==========
        createSheet(wb, "Employment", new String[]{
            "phone", "website", "email", "remarks"
        }, new String[]{
            "998901234567", "www.testcompany.uz", "test@company.uz", "Automation Test"
        });

        // ========== Sheet 6: BankDetails ==========
        createSheet(wb, "BankDetails", new String[]{
            "accountType", "bankName", "cardNumber", "expiryDate", "nameOnCard", "isDisbursed"
        }, new String[]{
            "2", "APB", "8600123456789012", "12-2028", "Test User", "Y"
        });

        // ========== Sheet 7: ReferenceDetails ==========
        createSheet(wb, "ReferenceDetails", new String[]{
            "contactType", "phone", "status", "otp"
        }, new String[]{
            "1", "998901112233", "1", "1234"
        });

        // ========== Sheet 8: ProductDetails ==========
        createSheet(wb, "ProductDetails", new String[]{
            "loanAmountIncrease", "remarks", "insuranceRemarks"
        }, new String[]{
            "5000000", "Loan amount updated +5mn", "Insurance added via automation"
        });

        // ========== Sheet 9: Collateral ==========
        createSheet(wb, "Collateral", new String[]{
            "collateralType", "collateralSubType", "usageType", "vehicleCategory",
            "vehicleMaker", "colour", "manufactureYear", "vehicleDesc",
            "dealerName", "vehiclePrice", "collateralCurrency", "isApplicantOwner",
            "bodyType", "ownerIssuer", "valuatorName", "valuationAmount",
            "availableCollateralAmt", "natureOfCharge", "address"
        }, new String[]{
            "33", "1", "1", "1",
            "5", "White", "2024", "Test Vehicle Automation",
            "Test Dealer", "125000000", "UZS", "1",
            "3", "Test Issuer", "2", "125000000",
            "125000000", "2", "Tashkent Uzbekistan"
        });

        // ========== Sheet 10: Income ==========
        createSheet(wb, "Income", new String[]{
            "incomeType", "incomeAmount", "comments"
        }, new String[]{
            "1", "125000000", "Monthly salary income"
        });

        // ========== Sheet 11: Expense ==========
        createSheet(wb, "Expense", new String[]{
            "expenseType", "expenseAmount"
        }, new String[]{
            "1", "5000000"
        });

        // ========== Sheet 12: CreditBureau ==========
        createSheet(wb, "CreditBureau", new String[]{
            "reportType", "customerName", "pinfl", "dob",
            "fiName", "facilityType", "accountStatus",
            "agreementStartDate", "agreementEndDate",
            "outstandingBalance", "assetClassification", "installmentAmount",
            "creditLimit", "disbursedAmount", "overdueAmount",
            "securityCode", "monthlyObligation"
        }, new String[]{
            "177", "Test Auto User", "12345678901234", "01-01-1995",
            "Agat Credit", "30", "1",
            "01-01-2021", "01-01-2022",
            "5000000", "Standard", "500000",
            "10000000", "10000000", "0",
            "Vehicle", "500000"
        });

        // ========== Sheet 13: PolicyReview (Expected values for validation) ==========
        createSheet(wb, "PolicyReview", new String[]{
            "expectedIncome", "expectedEmploymentType", "expectedEmployerType",
            "expectedMaritalStatus", "expectedEducation", "expectedNationality"
        }, new String[]{
            "125000000", "Salaried", "Permanent",
            "Not Married", "High Education", "Uzbek"
        });

        // ========== Sheet 14: CreditApproval ==========
        createSheet(wb, "CreditApproval", new String[]{
            "username", "password"
        }, new String[]{
            "s.shasultonov", "1"
        });

        // ========== Sheet 15: OfferAcceptance ==========
        createSheet(wb, "OfferAcceptance", new String[]{
            "username", "password"
        }, new String[]{
            "n.namozov", "1"
        });

        // Write file
        try (FileOutputStream fos = new FileOutputStream("src/test/resources/testdata/TestData.xlsx")) {
            wb.write(fos);
        }
        wb.close();
        System.out.println("✅ TestData.xlsx generated successfully!");
    }

    private static void createSheet(Workbook wb, String sheetName, String[] headers, String[] values) {
        Sheet sheet = wb.createSheet(sheetName);
        Row headerRow = sheet.createRow(0);
        Row dataRow = sheet.createRow(1);

        // Bold header style
        CellStyle headerStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        for (int i = 0; i < headers.length; i++) {
            Cell hCell = headerRow.createCell(i);
            hCell.setCellValue(headers[i]);
            hCell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(i);

            if (i < values.length) {
                dataRow.createCell(i).setCellValue(values[i]);
            }
        }
    }
}
