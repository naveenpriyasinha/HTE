package com.tcs.sgv.billproc.common.service;

public class BillProcConstants {
	
	public static final String DEFAULT_GROSS_AMOUNT = "0";
	
	public static final String DEFAULT_NET_AMOUNT = "0";
	
	public static final short RECEIVED = 1;
	
	public static final Integer ONE = new Integer(1);
	
	public static final String STATUS_BILL_INWARD = "a. BILLS INWARDED";
	public static final String STATUS_BILL_CARDEX = "b. BILLS WITH CARDEX";
	public static final String STATUS_BILL_AUDITOR = "c. BILLS WITH AUDITOR";
	public static final String STATUS_BILL_APPROVED = "d. BILLS APPROVED";
	public static final String STATUS_BILL_REJECTED = "e. BILLS REJECTED";
	public static final String STATUS_CHEQUE_PREPARED = "f. CHEQUES PREPARED";
	public static final String STATUS_CHEQUE_PRINTER = "g. CHEQUES WITH PRINTER";
	public static final String STATUS_CHEQUE_CUSTODIAN = "h. CHEQUES WITH CUSTODIAN";
	public static final String STATUS_CHEQUE_COUNTER = "i. CHEQUES WITH OUTWARD COUNTER";
	public static final String STATUS_VOUCHER_GENERATED = "j. VOUCHERS GENERATED";
	public static final String STATUS_VOUCHER_DISTRIBUTED = "k. VOUCHERS DISTRIBUTED";
	public static final String STATUS_DETAIL_POSTING = "l. DETAIL POSTING DONE";
	public static final String STATUS_OTHER = "m. OTHERS";
	
	public static final String STATE_WRITING = "writing";
	public static final String STATE_WRITTEN = "written";
	public static final String STATE_PRINTING = "printing";
	public static final String STATE_PRINTED = "printed";
	
	public static final Long ZERO = 0L;
	public static final String AUDITOR_LEVEL = "50";
	public static final String AUDIT_FOR_AUDITOR = "0";
	public static final String AUD_AUDITED = "1";
	public static final String AUDIT_FOR_ACCOUNTANT = "2";
	public static final String AUDIT_FOR_ATO = "3";
	public static final String AUDIT_FOR_TO = "4";
	public static final String AUDIT_APRVDRJCTD = "5";
	
	public static final Integer SRCH_TOKEN = 1;
	public static final Integer SRCH_REF = 2;
	public static final Integer SRCH_BILL_TYPE = 3;
	public static final Integer SRCH_BILL_DATE = 4;
	public static final Integer SRCH_BILL_CAT = 5;
	public static final Integer SRCH_CARDEX = 6;
	public static final Integer SRCH_DDO_NO = 7;
	public static final Integer SRCH_DDO_NAME = 8;
	public static final Integer SRCH_AUDITOR_NAME = 9;
	public static final Integer SRCH_MAJORHD = 10;
	public static final Integer SRCH_BILL_NET_AMT = 11;
	public static final Integer SRCH_CHQ_NO = 12;
	public static final Integer SRCH_CHQ_AMT = 13;
	public static final Integer SRCH_ADVICE_NO = 14;
	public static final Integer SRCH_VITO = 15;
	public static final Integer SRCH_ADVICE_DATE = 16;
	
	public static final Short INWARD_TYPE_BILL = 1;
	public static final Short INWARD_TYPE_CHEQUE = 2;
	
}
