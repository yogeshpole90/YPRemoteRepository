package com.ebid.lcs.excel;

public final class SheetConstants {

    private SheetConstants() {}

    // ========== SHEET NAMES ==========
    public static final String SHEET_FULL_PTP = "FullPTP";
    public static final String SHEET_DOC_UPLOAD = "DocUpload";
    public static final String SHEET_CASE_STATUS = "CaseStatus";
    public static final String SHEET_FOLLOW_UP = "FollowUp";
    public static final String SHEET_DEMAND_LETTER = "DemandLetter";
    public static final String SHEET_REMINDER = "Reminder";
    public static final String SHEET_SITE_VISIT = "SiteVisit";
    public static final String SHEET_REMEDIAL = "Remedial";
    public static final String SHEET_LEGAL_ORDER = "LegalOrder";
    public static final String SHEET_COURT_CASE = "CourtCase";
    public static final String SHEET_DOWNPAYMENT = "Downpayment";
    public static final String SHEET_FEES_LEGAL = "FeesLegalCharges";
    public static final String SHEET_PHONE_BOOK = "PhoneBook";
    public static final String SHEET_CALENDAR = "Calendar";
    public static final String SHEET_LEGAL_DIARY = "LegalDiary";
    public static final String SHEET_ACTION_DOC_MAP = "ActionDocMap";
    public static final String SHEET_LAW_FIRM = "LawFirm";
    public static final String SHEET_LAWYER_DETAILS = "LawyerDetails";

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
        // FullPTP
        public static final String PTP_OVERDUE = "OD_";
        public static final String PTP_DATE = "DT_";
        public static final String PTP_REMARKS = "RM_";
        public static final String PTP_TYPE = "TY_";
        public static final String PTP_PAY_MODE = "PM_";
        public static final String PTP_PLANNED_AMT = "PA_";
        public static final String PTP_REM_AMT = "RA_";
        public static final String PTP_PLANNED_DATE = "PD_";
        // DocUpload
        public static final String DOC_UPLOAD = "DU_";
        // CaseStatus
        public static final String CASE_STATUS = "CS_";
        // FollowUp
        public static final String FOLLOW_UP = "FU_";
        // DemandLetter
        public static final String DEMAND_LETTER = "DL_";
        // Reminder
        public static final String REMINDER = "RM_";
        // SiteVisit
        public static final String SITE_VISIT = "SV_";
        // Remedial
        public static final String REMEDIAL = "RA_";
        // LegalOrder
        public static final String LEGAL_ORDER = "LO_";
        // CourtCase
        public static final String COURT_CASE = "CC_";
        // Downpayment
        public static final String DOWNPAYMENT = "DP_";
        // FeesLegalCharges
        public static final String FEES_LEGAL = "FLC_";
        // PhoneBook
        public static final String PHONE_BOOK = "PB_";
        // Calendar
        public static final String CALENDAR = "CAL_";
        // LegalDiary
        public static final String LEGAL_DIARY = "LD_";
        // ActionDocMap
        public static final String ACTION_DOC_MAP = "ADM_";
        // LawFirm
        public static final String LAW_FIRM = "LF_";
        // LawyerDetails
        public static final String LAWYER_DETAILS = "LWD_";
    }
}
