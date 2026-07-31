package com.agat.los.excel;

public final class SheetConstants {

    private SheetConstants() {}

    // ========== SHEET NAMES ==========
    public static final String SHEET_DATA_ENTRY = "DataEntry";
    public static final String SHEET_BASIC_DETAILS = "BasicDetails";
    public static final String SHEET_ADDRESS = "Address";
    public static final String SHEET_IDENTIFICATION = "Identification";
    public static final String SHEET_COLLATERAL = "Collateral";
    public static final String SHEET_DOC_CHECKLIST = "DocChecklist";
    public static final String SHEET_LOAN_DETAILS = "LoanDetails";

    // ========== COMMON COLUMN INDICES ==========
    public static final class Cols {
        public static final int TC_ID = 0;
        public static final int FIELD_NAME = 1;
        public static final int INPUT = 2;
        public static final int EXPECTED = 3;
        public static final int DESCRIPTION = 4;
        public static final int CHECK_TYPE = 5;
    }

    // ========== TC_ID PREFIXES ==========
    public static final class TC {
        public static final String DATA_ENTRY = "DE_";
        public static final String BASIC_DETAILS = "BD_";
        public static final String ADDRESS = "AD_";
        public static final String IDENTIFICATION = "ID_";
        public static final String COLLATERAL = "CL_";
        public static final String DOC_CHECKLIST = "DC_";
        public static final String LOAN_DETAILS = "LD_";
    }
}
