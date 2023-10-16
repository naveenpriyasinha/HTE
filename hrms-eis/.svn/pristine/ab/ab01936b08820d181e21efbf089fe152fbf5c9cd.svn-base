package com.tcs.sgv.eis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBConnection;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;

public class commonDetailsDaoImpl extends GenericDaoHibernateImpl<TrnBillRegister, Long> 
{
	public commonDetailsDaoImpl(Class<TrnBillRegister> type, SessionFactory sessionFactory) 
	{
	super(type);
    setSessionFactory(sessionFactory);
	}
	public List getDataforDisplay(int Month, String Year, long locId,long langId, String billNo)
	{			
		ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");	
		ResourceBundle rbd = ResourceBundle.getBundle("resources.eis.eis_Constants");
		logger.info(":::::::::::::::::::::::::::::::   in executinmg long query :::::::::::::::::::::::::::::::::::");
		String hbaLoan = rb.getString("hba").toString();// hbaLoan Id from Property File.	
		String carLoanId = rb.getString("car");	        // Car Loan Id from Property File.
		String scooterLoanId = rb.getString("scooter");	// Scooter Loan Id from Property File.
		String mopedLoanId = rb.getString("moped");	    // Moped Loan Id from Property File.
		String gpfAdvId = rb.getString("gpf");	        // GPF Advance Id from Property File.
		String principle = rb.getString("principal");   // principal Advance Id from Property File.
		String created = rb.getString("created");	    // created bill  Id from Property File.
		String approved = rb.getString("approved");     // approved bill Id from Property File.
		String intrest = rb.getString("interest");	    // interest Advance Id from Property File.
		String class1 = rbd.getString("GradeCode1");	// class1 code Advance Id from Property File.
		String class2 = rbd.getString("GradeCode2");	// class2 code Advance Id from Property File.
		String class3 = rbd.getString("GradeCode3");	// class3 code Advance Id from Property File.
		String class4 = rbd.getString("GradeCode4");	// class4 code Advance Id from Property File.
		String AIS    = rbd.getString("AISGradeCode");	// AIS code Advance Id from Property File.
		
		List resList=new ArrayList();
		int year = Integer.parseInt(Year.toString());

		DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	    
	    String getNullValueforMySql = sbConf.getNullValue("a.emp_mname");
		
		SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
	    
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, (year));
	    cal.set(Calendar.MONTH,(Month)-1);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.set(Calendar.YEAR, (year));
	    cal.set(Calendar.MONTH,(Month)-1);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    
	    java.util.Date startMonthDate = cal.getTime();
	    SimpleDateFormat sdf = sbConf.GetDateFormat();
	    int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    cal.set(Calendar.DAY_OF_MONTH, totalDays);
	    java.util.Date endMonthDate = cal.getTime();

	    String startDate    =  sdf.format(startMonthDate);
	    String endDate      =  sdf.format(endMonthDate);
		String getOrderDate =  sbConf.to_char("MAX(a.ORDER_DATE)");
		String freestring   =  sbConf.to_nchar("~~~");
		String getmonth     =  sbConf.to_getmonth("MAX(b.loan_date)");
		
	    
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append("  SELECT main.COL_0_0_,       MAX(main.COL_1_0_) AS COL_1_0_,       MAX(main.COL_2_0_) AS COL_2_0_,       MAX(main.COL_3_0_) AS COL_3_0_,       MAX(main.COL_4_0_) AS COL_4_0_,       MAX(main.COL_5_0_) AS COL_5_0_,       MAX(main.COL_6_0_) AS COL_6_0_,       MAX(main.COL_7_0_) AS COL_7_0_,       MAX(main.COL_8_0_) AS COL_8_0_,       MAX(main.COL_9_0_) AS COL_9_0_,       MAX(main.COL_10_0_) AS COL_10_0_,       MAX(main.COL_11_0_) AS COL_11_0_,       MAX(main.COL_12_0_) AS COL_12_0_,       MAX(main.COL_13_0_) AS COL_13_0_,       MAX(main.COL_14_0_) AS COL_14_0_,       MAX(main.COL_15_0_) AS COL_15_0_,       MAX(main.COL_16_0_) AS COL_16_0_,       MAX(main.COL_17_0_) AS COL_17_0_,       MAX(main.COL_18_0_) AS COL_18_0_,       MAX(main.COL_19_0_) AS COL_19_0_,       MAX(main.COL_20_0_) AS COL_20_0_,       MAX(main.COL_21_0_) AS COL_21_0_,       MAX(main.COL_22_0_) AS COL_22_0_,       MAX(main.COL_23_0_) AS COL_23_0_,       MAX(main.COL_24_0_) AS COL_24_0_,       MAX(main.COL_25_0_) AS COL_25_0_,       MAX(main.COL_26_0_) AS COL_26_0_,       MAX(main.COL_27_0_) AS COL_27_0_,       MAX(main.COL_28_0_) AS COL_28_0_,       MAX(main.COL_29_0_) AS COL_29_0_,       MAX(main.COL_30_0_-main.COL_32_0_) AS COL_30_0_,       MAX(main.COL_31_0_) AS COL_31_0_,       MAX(main.COL_32_0_) AS COL_32_0_,       MAX(main.COL_33_0_-main.COL_35_0_) AS COL_33_0_,       MAX(main.COL_34_0_) AS COL_34_0_,       MAX(main.COL_35_0_) AS COL_35_0_,       MAX(main.COL_36_0_) AS COL_36_0_,       MAX(main.COL_37_0_) AS COL_37_0_,       MAX(main.COL_38_0_) AS COL_38_0_,       MAX(main.COL_39_0_) AS COL_39_0_,       MAX(main.COL_40_0_) AS COL_40_0_,       MAX(main.COL_41_0_) AS COL_41_0_,       MAX(main.COL_42_0_) AS COL_42_0_,  ");
			query.append("  MAX(main.COL_43_0_) AS COL_43_0_,       MAX(main.COL_44_0_) AS COL_44_0_,       MAX(main.COL_45_0_) AS COL_45_0_,       MAX(main.COL_46_0_) AS COL_46_0_,       MAX(main.COL_47_0_) AS COL_47_0_,       MAX(main.COL_48_0_) AS COL_48_0_,       MAX(main.COL_49_0_) AS COL_49_0_,       MAX(main.COL_50_0_) AS COL_50_0_,       MAX(main.COL_51_0_) AS COL_51_0_,       MAX(main.COL_52_0_) AS COL_52_0_,       MAX(main.COL_53_0_) AS COL_53_0_,       MAX(main.COL_54_0_) AS COL_54_0_,       MAX(main.COL_55_0_) AS COL_55_0_,       MAX(main.COL_56_0_) AS COL_56_0_,       MAX(main.COL_57_0_) AS COL_57_0_,       MAX(main.COL_58_0_) AS COL_58_0_,       MAX(main.COL_59_0_) AS COL_59_0_,       MAX(main.COL_60_0_) AS COL_60_0_,       MAX(main.COL_61_0_) AS COL_61_0_,       MAX(main.COL_62_0_) AS COL_62_0_,       MAX(main.COL_63_0_) AS COL_63_0_,       MAX(main.COL_64_0_) AS COL_64_0_,       MAX(main.COL_65_0_) AS COL_65_0_,       MAX(main.COL_66_0_) AS COL_66_0_,       MAX(main.COL_67_0_) AS COL_67_0_,       MAX(main.COL_68_0_) AS COL_68_0_,       MAX(main.COL_69_0_) AS COL_69_0_,       MAX(main.COL_70_0_) AS COL_70_0_,       MAX(main.COL_71_0_) AS COL_71_0_,       MAX(main.COL_72_0_) AS COL_72_0_,       MAX(main.COL_73_0_) AS COL_73_0_,       MAX(main.COL_74_0_) AS COL_74_0_,       MAX(main.COL_75_0_) AS COL_75_0_,       MAX(main.COL_76_0_) AS COL_76_0_, MAX(main.COL_90_0_) AS COL_90_0_,      MAX(main.COL_77_0_) AS COL_77_0_,       MAX(main.COL_78_0_) AS COL_78_0_,       MAX(main.COL_79_0_) AS COL_79_0_,       MAX(main.COL_80_0_) AS COL_80_0_,       MAX(main.COL_81_0_) AS COL_81_0_,       MAX(main.COL_82_0_) AS COL_82_0_,       MAX(main.COL_83_0_) AS COL_83_0_,       MAX(main.COL_84_0_) AS COL_84_0_, MAX(main.COL_89_0_) AS COL_89_0_,      MAX(main.COL_85_0_) AS COL_85_0_,       MAX(main.COL_86_0_) AS COL_86_0_ ,       MAX(main.COL_87_0_) AS COL_87_0_  ");
			query.append("  FROM (SELECT MAX(a.PSR_NO) AS col_0_0_,   MAX(d.PROOF_NUM) AS col_1_0_,   MAX(a.emp_prefix) AS col_2_0_,   trim(MAX(concat(concat(concat(a.emp_fname, ' '),  concat(" + getNullValueforMySql + ", ' ')),       concat(a.emp_lname, ' ')))) AS col_3_0_,   MAX(concat(concat(a.scale_start_amt, ' - '), a.scale_end_amt)) AS col_4_0_,   MAX(a.grade_name) AS col_5_0_,   MAX(a.dsgn_name) AS col_6_0_,   MAX(a.emp_gpf_num) AS col_7_0_, MAX(a.SancNumber)  AS col_8_0_,   ' ' as col_9_0_,  " + getOrderDate + " as col_10_0_ ,  (SELECT concat(concat(hreisbankm17_.BANK_NAME, ', '),      hreisbranc16_.branch_name)      FROM HR_EIS_BANK_DTLS  hreisbankd15_,     hr_eis_branch_mst hreisbranc16_,     hr_eis_bank_mst   hreisbankm17_     WHERE hreisbankd15_.BANK_EMP_ID = a.EMP_ID AND     hreisbranc16_.BRANCH_ID = hreisbankd15_.BANK_BRANCH_ID AND     hreisbankd15_.BANK_ID = hreisbankm17_.BANK_ID) AS col_11_0_,   (SELECT hreisbankd14_.BANK_ACCT_NO      FROM HR_EIS_BANK_DTLS hreisbankd14_     WHERE hreisbankd14_.BANK_EMP_ID = a.EMP_ID) AS col_12_0_,   (SELECT hreisbranc16_.micr_code      FROM HR_EIS_BANK_DTLS  hreisbankd15_,     hr_eis_branch_mst hreisbranc16_,     hr_eis_bank_mst   hreisbankm17_     WHERE hreisbankd15_.BANK_EMP_ID = a.EMP_ID AND     hreisbranc16_.BRANCH_ID = hreisbankd15_.BANK_BRANCH_ID AND     hreisbankd15_.BANK_ID = hreisbankm17_.BANK_ID) AS col_13_0_,  " ); 
			query.append("  CASE when(SUM(a.hrr) + SUM(a.rent)) > 0 then a.quarterdtls       when(SUM(a.hrr) + SUM(a.rent))  <= 0  then " + freestring + " End col_14_0_,     CASE   WHEN b.LOAN_TYPE_ID IN (" +  hbaLoan + ") AND a.paybill_id = b.paybill_id THEN   MAX(b.LOAN_ACCOUNT_NO)   END col_15_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" +  hbaLoan + ") AND b.recovery_type IN (" + principle + ") AND   a.paybill_id = b.paybill_id THEN   MAX(TOTAL_AMT)   END col_16_0_, CASE   WHEN b.LOAN_TYPE_ID IN (" +  hbaLoan + ") AND b.recovery_type IN (" + principle + ") AND   a.paybill_id = b.paybill_id THEN   MAX(RECOVERED_INST)   END col_17_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" +  hbaLoan + ") AND b.recovery_type IN (" + principle + ") AND   a.paybill_id = b.paybill_id THEN   MAX(TOTAL_INST)   END col_18_0_, CASE   WHEN b.LOAN_TYPE_ID IN (" +  hbaLoan + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN trim(" + getmonth + " )  End  col_19_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" +  carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND   a.paybill_id = b.paybill_id THEN   MAX(b.LOAN_ACCOUNT_NO)   END col_20_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND   b.recovery_type IN (" + intrest + ") AND   a.paybill_id = b.paybill_id THEN " ); 
			query.append("  MAX(TOTAL_AMT)   END col_21_0_,     CASE   WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND   b.recovery_type IN (" + intrest + ") AND   a.paybill_id = b.paybill_id THEN   MAX(RECOVERED_INST)   END col_22_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND   b.recovery_type IN (" + intrest + ") AND   a.paybill_id = b.paybill_id THEN   MAX(TOTAL_INST)   END col_23_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND  b.recovery_type IN (" + intrest + ") AND       a.paybill_id = b.paybill_id THEN trim(" + getmonth + " )  END AS col_24_0_  ,  ' ' as col_25_0_,   SUM(a.society_old) as col_26_0_,   SUM(a.society_new) as col_27_0_  ,   CASE   WHEN b.LOAN_TYPE_ID IN ("  +  gpfAdvId + ") AND b.recovery_type IN (" + principle + ") AND   a.paybill_id = b.paybill_id THEN   MAX(RECOVERED_AMT)   END col_28_0_,   CASE   WHEN b.LOAN_TYPE_ID IN ("  +  gpfAdvId + ") AND b.recovery_type IN (" + principle + ") AND   a.paybill_id = b.paybill_id THEN   MAX(RECOVERED_INST)   END col_29_0_,     SUM(a.po) AS col_30_0_,   SUM(a.PER_PAY) AS col_31_0_,   case  when    MAX(a.grade_id)  IN (" + class1 + ", " + class2 + ", " + AIS+ ") then  SUM(round(((a.po+a.pe+a.LEAVE_PAY)/a.current_basic)*a.SCALE_GRADE_PAY))   END col_32_0_, " ); 
			query.append("  SUM(a.pe) AS col_33_0_,   SUM(a.SPL_PAY) AS col_34_0_,   case  when    MAX(a.grade_id) IN (" + class3 + ", " + class4 + ") then SUM(round(((a.po+a.pe+a.LEAVE_PAY)/a.current_basic)*a.SCALE_GRADE_PAY))   END col_35_0_,     SUM(a.LEAVE_PAY) AS col_36_0_,     SUM(a.le) AS col_37_0_,     case  when    MAX(a.grade_id)  IN (" + class1 + ", " + class2 + ", " + AIS+ ") then  SUM(a.dp_gazzeted)   END col_38_0_,     case  when    MAX(a.grade_id) IN (" + class3 + ", " + class4 + ") then    SUM(a.d_pay)   END col_39_0_, " );
			query.append("  SUM(a.DA) col_40_0_,   0 AS col_41_0_,   SUM(a.HRA) AS col_42_0_,   SUM(a.CLA) AS col_43_0_,   Max(a.otherAlnc) AS Col_44_0_,   SUM(a.MA) AS col_45_0_,   MAX(a.bonus) AS col_46_0_,   SUM(a.WA) AS col_47_0_,   SUM(a.othercharges) AS Col_48_0_,   SUM(a.TR_ALLOW) AS col_49_0_,   0 AS col_50_0_,   SUM(a.FES_ADV) AS col_51_0_,   SUM(a.FOOD_ADV) AS col_52_0_,   '' AS col_53_0_,   SUM(a.grossAmt) AS col_54_0_,   ' ' as col_55_0_,   SUM(a.I_TAX) AS col_56_0_,   SUM(a.surcharge) AS col_57_0_,   SUM(a.HRR) AS col_58_0_,   0 AS col_59_0_,   SUM(a.rent) AS col_60_0_,   0 AS col_61_0_,   0 AS col_62_0_,   SUM(a.P_TAX) AS col_63_0_,   SUM(a.GIS_1979) AS col_64_0_,   MAX(a.GIS_IF_1980) AS col_65_0_,   MAX(a.GIS_SF_1980) AS col_66_0_,   SUM(a.AISIF) AS col_67_0_,   SUM(a.AISSF) AS col_68_0_,   MAX(a.gpfOther) AS col_69_0_,   SUM(a. cpf) AS col_70_0_,  MAX(a.aisPf) AS col_71_0_,   SUM(a.da_gpf) AS col_72_0_,    SUM(a.GPF_ADV) AS col_73_0_,   SUM(a.car_sct_moped_adv) AS col_74_0_,   SUM(a.car_sct_moped_int) AS col_75_0_,   sum(a.OCA_CYCLE_ADV) AS col_76_0_,sum(a.OCA_CYCLE_INT) AS col_90_0_,   SUM(a.HBA) AS col_77_0_,   MAX(a.HBA_INT) AS col_78_0_,   SUM(a.FAN_ADV) AS col_79_0_,   MAX(a.FAN_INT) AS col_80_0_,   SUM(a.JEEP_R) AS col_81_0_,   SUM(a.MISC_REC) AS col_82_0_,   SUM(a.gpf_iv) AS col_83_0_,   SUM(a.da_gpfiv) AS col_84_0_, sum(a.gpf_iv_adv) as col_89_0_,  SUM(a.total_ded) AS col_85_0_,   SUM(a.NET_TOTAL) as col_87_0_ , Max((select ps.bill_no from hr_pay_bill_subhead_mpg  ps where ps.bill_subhead_id in (SELECT distinct paybillhea148_.bill_no FROM PAYBILL_HEAD_MPG paybillhea148_ where  paybillhea148_.paybill_id = a.PAYBILL_GRP_ID) )) as col_86_0_   FROM (SELECT hrpaypaybi146_.id PAYBILL_ID,     hrpaypaybi146_.EMP_ID EMP_ID,     orgempmst6_.emp_gpf_num emp_gpf_num,     hrpaypaybi146_.ls LEAVE_PAY,     hrpaypaybi146_.SPL_PAY SPL_PAY,     hrpaypaybi146_.PER_PAY PER_PAY,     hrpaypaybi146_.DA DA,     hrpaypaybi146_.CLA CLA,     hrpaypaybi146_.HRA HRA,     hrpaypaybi146_.WA WA,     hrpaypaybi146_.MA MA,     hrpaypaybi146_.trans_all TR_ALLOW,     hrpaypaybi146_.it I_TAX,  " );
			query.append("  hrpaypaybi146_.HRR HRR,     hrpaypaybi146_.pt P_TAX,     hrpaypaybi146_.GPF_ADV GPF_ADV,     hrpaypaybi146_.FES_ADV FES_ADV,     hrpaypaybi146_.FOOD_ADV FOOD_ADV,     hrpaypaybi146_.FAN_ADV FAN_ADV,     hrpaypaybi146_.HBA HBA,     hrpaypaybi146_.misc_recov MISC_REC,     hrpaypaybi146_.GPF_C GPF,     hrpaypaybi146_.gpf_c gpfOther,     hrpaypaybi146_.JEEP_R JEEP_R,     hrpaypaybi146_.NET_TOTAL NET_TOTAL,     orgempmst6_.user_id user_id,     orgempmst6_.emp_fname emp_fname,     orgempmst6_.emp_mname emp_mname,     orgempmst6_.emp_lname emp_lname,     orgempmst6_.emp_dob emp_dob,     orgempmst6_.emp_doj emp_doj,     hreisother2_.INCREMENT_DATE INCREMENT_DATE,     hreisscale5_.scale_start_amt scale_start_amt,     hreisscale5_.scale_incr_amt scale_incr_amt,     hreisscale5_.scale_end_amt scale_end_amt,     hreisscale5_.scale_higher_incr_amt scale_higher_incr_amt,     hreisscale5_.scale_higher_end_amt scale_higher_end_amt,     orgdesigna9_.dsgn_shrt_name dsgn_name,     cmnlocatio10_.loc_name loc_name,     hrpaypaysl1_.society_old society_old,     hrpaypaysl1_.society_new society_new,     hrpaypaysl1_.karamchari_bank karamchari_bank,     hrpaypaysl1_.nagrik_bank nagrik_bank,     hrpaypaysl1_.LIC LIC,     hrpaypaysl1_.post_office_recurring_deposit post_office_recurring_deposit,     hrpaypaybi146_.CAR_SCT_MOPED_INT CAR_SCT_MOPED_INT,     hrpaypaybi146_.HBA_INT HBA_INT,     hrpaypaybi146_.FAN_INT FAN_INT,     hrpaypaybi146_.OCA_CYCLE_ADV OCA_CYCLE_ADV,     hrpaypaybi146_.OCA_CYCLE_INT OCA_CYCLE_INT,     hrpaypaybi146_.sis_gis_1979 GIS_1979,     hrpaypaybi146_.sis_if_1981 GIS_IF_1980,     hrpaypaybi146_.sis_sf_1981 GIS_SF_1980,     hrpaypaybi146_.pay_recover pay_recover,     hrpaypaybi146_.PSR_NO PSR_NO,     orgempmst6_.emp_prefix emp_prefix,     hrpaypaybi146_.bonus bonus,  " ); 
			query.append("  hreisscale5_.SCALE_GRADE_PAY SCALE_GRADE_PAY,     (SELECT concat(concat(concat(concat(cmnaddress147_.AREA,     '~'),    concat(cmnaddress147_.STREET,     '~')),   concat(hreisquate149_.QUA_TYPE,    '~')),  concat(ccm.city_name, '')) quarterdtls  FROM cmn_address_mst    cmnaddress147_, hr_eis_qtr_emp_mpg hreisqtrms148_, HR_QUATER_TYPE_MST hreisquate149_, cmn_city_mst ccm WHERE hreisquate149_.quater_id = hreisqtrms148_.quartertype_lookupid and cmnaddress147_.ADDRESS_ID = hreisqtrms148_.address_id AND ccm.city_id = cmnaddress147_.city_id AND hreisqtrms148_.allocated_to = orgempmst6_.user_id AND  " ); 
			query.append("  hreisqtrms148_.allocation_start_date <=  '" + endDate + "' AND (hreisqtrms148_.allocation_end_date is null or  hreisqtrms148_.allocation_end_date >  '"+ startDate +"'  )) quarterdtls,     hrpaypaybi146_.d_pay d_pay, hrpaypaybi146_.dp_gazzeted dp_gazzeted,     hrpaypaybi146_.other_allow otherAlnc,     hrpaypaybi146_.other_chrgs othercharges,     hrpaypaybi146_.gross_amt grossAmt,     hrpaypaybi146_.slo slo,     hrpaypaybi146_.surcharge surcharge,     hrpaypaybi146_.rent_b rent,     hrpaypaybi146_.ais_if_1980 AISIF,     hrpaypaybi146_.ais_sf_1980 AISSF,     grdmst.grade_name grade_name,     ORDERMST.ORDER_NAME ORDER_NAME,     ORDERMST.ORDER_DATE ORDER_DATE,     hrpaypaybi146_.po po,     hrpaypaybi146_.pe pe,     grdmst.grade_id grade_id,     hrpaypaybi146_.Le le,     hrpaypaybi146_.cpf cpf,  ");
			query.append("  hrpaypaybi146_.da_gpfiv da_gpfiv,     hrpaypaybi146_.car_sct_moped_adv car_sct_moped_adv,       hrpaypaybi146_.gpf_iv gpf_iv, hrpaypaybi146_.gpf_iv_adv gpf_iv_adv,    hrpaypaybi146_.da_gpf da_gpf,     hrpaypaybi146_.total_ded total_ded,     hrpaypaybi146_.Ais_Pf aisPf,  hreisother2_.other_current_basic current_basic,   (select hrpayorder24_.ORDER_NAME    from HR_PAY_ORDER_MST hrpayorder24_   where hrpayorder24_.ORDER_ID = hrpayorder11_.ORDER_ID)  SancNumber , hrpaypaybi146_.PAYBILL_GRP_ID  PAYBILL_GRP_ID     FROM HR_PAY_PAYBILL hrpaypaybi146_       LEFT OUTER JOIN HR_PAY_payslip_non_govt hrpaypaysl1_ on hrpaypaybi146_.ID = hrpaypaysl1_.paybill_id       LEFT OUTER JOIN HR_EIS_OTHER_DTLS hreisother2_ on hreisother2_.EMP_ID = hrpaypaybi146_.EMP_ID       LEFT OUTER JOIN HR_EIS_SGD_MPG hreissgdmp3_ ON hreisother2_.EMP_SGD_ID = hreissgdmp3_.SGD_MAP_ID       ");
			query.append("  LEFT OUTER JOIN HR_EIS_GD_MPG hreisgdmpg4_ ON hreissgdmp3_.SGD_GD_ID = hreisgdmpg4_.GD_MAP_ID       LEFT OUTER JOIN hr_eis_scale_mst hreisscale5_ ON hreissgdmp3_.SGD_SCALE_ID = hreisscale5_.scale_id       LEFT OUTER JOIN hr_eis_emp_mst hreisempms149_ on hrpaypaybi146_.EMP_ID = hreisempms149_.emp_id       LEFT OUTER JOIN org_emp_mst orgempmst6_ on orgempmst6_.emp_id = hreisempms149_.emp_mpg_id       LEFT OUTER JOIN org_userpost_rlt orguserpos7_ on orguserpos7_.user_id = orgempmst6_.user_id AND orguserpos7_.post_id = hrpaypaybi146_.post_id       LEFT OUTER JOIN org_post_details_rlt orgpostdet8_ on orguserpos7_.post_id = orgpostdet8_.post_id AND orgpostdet8_.post_id = hrpaypaybi146_.post_id            ");
			query.append("  AND orgempmst6_.lang_id = " + langId  + " AND orgpostdet8_.lang_id = " + langId  + " and orgpostdet8_.loc_id =  " + locId + "       LEFT OUTER JOIN HR_PAY_ORDER_HEAD_POST_MPG hrpayorder12_ on hrpayorder12_.POST_ID = hrpaypaybi146_.post_id       LEFT OUTER JOIN HR_PAY_ORDER_HEAD_MPG hrpayorder11_ on hrpayorder11_.order_head_id = hrpayorder12_.order_head_id       LEFT OUTER JOIN HR_PAY_ORDER_MST ORDERMST on ORDERMST.ORDER_ID = hrpayorder11_.order_id       LEFT OUTER JOIN org_designation_mst orgdesigna9_ on orgpostdet8_.dsgn_id = orgdesigna9_.dsgn_id AND orgdesigna9_.lang_id = " + langId  + "       LEFT OUTER JOIN cmn_location_mst cmnlocatio10_ on orgpostdet8_.loc_id = cmnlocatio10_.loc_id AND cmnlocatio10_.lang_id = " + langId  + "        ");
			query.append("  LEFT OUTER JOIN org_grade_mst grdmst on grdmst.grade_id = orgempmst6_.grade_id     WHERE (hrpaypaybi146_.PAYBILL_GRP_ID IN     (SELECT paybillhea148_.PAYBILL_ID   FROM PAYBILL_HEAD_MPG paybillhea148_  WHERE paybillhea148_.PAYBILL_MONTH = " + Month+ "  AND  paybillhea148_.PAYBILL_YEAR = " + year+ " AND  paybillhea148_.approve_flag in  (" + created + "," + approved + ") "); 
			if(billNo !=null && !billNo.equals("") )
			{
				query.append("  AND  paybillhea148_.bill_no in ( " + billNo + ") ");
			}
			query.append(" ))) a LEFT JOIN (SELECT hrloanempd18_.emp_id,   MAX(hrloanempd18_.LOAN_ACCOUNT_NO) LOAN_ACCOUNT_NO,   hrloanempd18_.loan_type_id loan_type_id,   MAX(hrpaypaybi19_.RECOVERED_AMT) RECOVERED_AMT,   MAX(hrpaypaybi19_.RECOVERED_INST) RECOVERED_INST,   hrpaypaybi19_.paybill_id paybill_id,   MAX(hrloanempd18_.loan_date) loan_date,     ");
			query.append("  hrpaypaybi19_.recovery_type recovery_type,   MAX(hrpaypaybi19_.total_inst) total_inst,   MAX(hrpaypaybi19_.total_amt) total_amt    FROM HR_LOAN_EMP_DTLS   hrloanempd18_,   HR_PAY_PAYBILL_LOAN_DTLS hrpaypaybi19_   WHERE hrpaypaybi19_.LOAN_TYPE_ID =   hrloanempd18_.LOAN_TYPE_ID AND   hrloanempd18_.EMP_LOAN_ID =   hrpaypaybi19_.EMP_LOAN_ID   GROUP BY hrloanempd18_.emp_id,hrloanempd18_.loan_type_id,hrpaypaybi19_.paybill_id,hrpaypaybi19_.recovery_type) b ON a.emp_id = b.emp_id LEFT JOIN hr_eis_proof_dtl d ON d.user_id = a.user_id   GROUP BY a.EMP_ID,  a.PSR_NO,      a.USER_ID,      a.PSR_NO,      a.PAYBILL_ID,      b.loan_type_id,      b.paybill_id,      b.recovery_type) main GROUP BY main.col_0_0_ order by col_86_0_, col_0_0_  ");
			
			logger.info("Query for get getDataforDisplay is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>> resListresListresListresListresListresList : " + resList.size());

		return resList;
	}
public List getDataforDisplay(int Month, String Year, long locId,long langId, List billNo)
{
	
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");	
	ResourceBundle rbd = ResourceBundle.getBundle("resources.eis.eis_Constants");
	logger.info(":::::::::::::::::::::::::::::::   in executinmg long query :::::::::::::::::::::::::::::::::::");
	String hbaLoan = rb.getString("hba").toString();// hbaLoan Id from Property File.	
	String carLoanId = rb.getString("car");	        // Car Loan Id from Property File.
	String scooterLoanId = rb.getString("scooter");	// Scooter Loan Id from Property File.
	String mopedLoanId = rb.getString("moped");	    // Moped Loan Id from Property File.
	String gpfAdvId = rb.getString("gpf");	        // GPF Advance Id from Property File.
	String principle = rb.getString("principal");   // principal Advance Id from Property File.
	String created = rb.getString("created");	    // created bill  Id from Property File.
	String approved = rb.getString("approved");     // approved bill Id from Property File.
	String intrest = rb.getString("interest");	    // interest Advance Id from Property File.
	String class1 = rbd.getString("GradeCode1");	// class1 code Advance Id from Property File.
	String class2 = rbd.getString("GradeCode2");	// class2 code Advance Id from Property File.
	String class3 = rbd.getString("GradeCode3");	// class3 code Advance Id from Property File.
	String class4 = rbd.getString("GradeCode4");	// class4 code Advance Id from Property File.
	String AIS    = rbd.getString("AISGradeCode");	// AIS code Advance Id from Property File.
	
	List resList=new ArrayList();
	int year = Integer.parseInt(Year.toString());

	DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
    
    String getNullValueforMySql = sbConf.getNullValue("a.emp_mname");
	
	SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
    
	Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, (year));
    cal.set(Calendar.MONTH,(Month)-1);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.set(Calendar.YEAR, (year));
    cal.set(Calendar.MONTH,(Month)-1);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    
    java.util.Date startMonthDate = cal.getTime();
    SimpleDateFormat sdf = sbConf.GetDateFormat();
    int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    cal.set(Calendar.DAY_OF_MONTH, totalDays);
    java.util.Date endMonthDate = cal.getTime();

    String startDate    =  sdf.format(startMonthDate);
    String endDate      =  sdf.format(endMonthDate);
	String getOrderDate =  sbConf.to_char("MAX(a.ORDER_DATE)");
	String freestring   =  sbConf.to_nchar("~~~");
	String getmonth     =  sbConf.to_getmonth("MAX(b.loan_date)");
	Connection lCon = null;
	PreparedStatement lPStmt = null;
    ResultSet lRs = null;
    try {	           
        lCon = DBConnection.getConnection();
    }
    catch(Exception e) {
        logger.error("Exception in Connecting to database " + e);
    }
    
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer(); 
		query.append("  SELECT main.COL_0_0_,       MAX(main.COL_1_0_) AS COL_1_0_,       MAX(main.COL_2_0_) AS COL_2_0_,       MAX(main.COL_3_0_) AS COL_3_0_,       MAX(main.COL_4_0_) AS COL_4_0_,       MAX(main.COL_5_0_) AS COL_5_0_,       MAX(main.COL_6_0_) AS COL_6_0_,       MAX(main.COL_7_0_) AS COL_7_0_,       MAX(main.COL_8_0_) AS COL_8_0_,       MAX(main.COL_9_0_) AS COL_9_0_,       MAX(main.COL_10_0_) AS COL_10_0_,       MAX(main.COL_11_0_) AS COL_11_0_,       MAX(main.COL_12_0_) AS COL_12_0_,       MAX(main.COL_13_0_) AS COL_13_0_,       MAX(main.COL_14_0_) AS COL_14_0_,       MAX(main.COL_15_0_) AS COL_15_0_,       MAX(main.COL_16_0_) AS COL_16_0_,       MAX(main.COL_17_0_) AS COL_17_0_,       MAX(main.COL_18_0_) AS COL_18_0_,       MAX(main.COL_19_0_) AS COL_19_0_,       MAX(main.COL_20_0_) AS COL_20_0_,       MAX(main.COL_21_0_) AS COL_21_0_,       MAX(main.COL_22_0_) AS COL_22_0_,       MAX(main.COL_23_0_) AS COL_23_0_,       MAX(main.COL_24_0_) AS COL_24_0_,       MAX(main.COL_25_0_) AS COL_25_0_,       MAX(main.COL_26_0_) AS COL_26_0_,       MAX(main.COL_27_0_) AS COL_27_0_,       MAX(main.COL_28_0_) AS COL_28_0_,       MAX(main.COL_29_0_) AS COL_29_0_,       MAX(main.COL_30_0_-main.COL_32_0_) AS COL_30_0_,       MAX(main.COL_31_0_) AS COL_31_0_,       MAX(main.COL_32_0_) AS COL_32_0_,       MAX(main.COL_33_0_-main.COL_35_0_) AS COL_33_0_,       MAX(main.COL_34_0_) AS COL_34_0_,       MAX(main.COL_35_0_) AS COL_35_0_,       MAX(main.COL_36_0_) AS COL_36_0_,       MAX(main.COL_37_0_) AS COL_37_0_,       MAX(main.COL_38_0_) AS COL_38_0_,       MAX(main.COL_39_0_) AS COL_39_0_,       MAX(main.COL_40_0_) AS COL_40_0_,       MAX(main.COL_41_0_) AS COL_41_0_,       MAX(main.COL_42_0_) AS COL_42_0_,  ");
		query.append("  MAX(main.COL_43_0_) AS COL_43_0_,       MAX(main.COL_44_0_) AS COL_44_0_,       MAX(main.COL_45_0_) AS COL_45_0_,       MAX(main.COL_46_0_) AS COL_46_0_,       MAX(main.COL_47_0_) AS COL_47_0_,       MAX(main.COL_48_0_) AS COL_48_0_,       MAX(main.COL_49_0_) AS COL_49_0_,       MAX(main.COL_50_0_) AS COL_50_0_,       MAX(main.COL_51_0_) AS COL_51_0_,       MAX(main.COL_52_0_) AS COL_52_0_,       MAX(main.COL_53_0_) AS COL_53_0_,       MAX(main.COL_54_0_) AS COL_54_0_,       MAX(main.COL_55_0_) AS COL_55_0_,       MAX(main.COL_56_0_) AS COL_56_0_,       MAX(main.COL_57_0_) AS COL_57_0_,       MAX(main.COL_58_0_) AS COL_58_0_,       MAX(main.COL_59_0_) AS COL_59_0_,       MAX(main.COL_60_0_) AS COL_60_0_,       MAX(main.COL_61_0_) AS COL_61_0_,       MAX(main.COL_62_0_) AS COL_62_0_,       MAX(main.COL_63_0_) AS COL_63_0_,       MAX(main.COL_64_0_) AS COL_64_0_,       MAX(main.COL_65_0_) AS COL_65_0_,       MAX(main.COL_66_0_) AS COL_66_0_,       MAX(main.COL_67_0_) AS COL_67_0_,       MAX(main.COL_68_0_) AS COL_68_0_,       MAX(main.COL_69_0_) AS COL_69_0_,       MAX(main.COL_70_0_) AS COL_70_0_,       MAX(main.COL_71_0_) AS COL_71_0_,       MAX(main.COL_72_0_) AS COL_72_0_,       MAX(main.COL_73_0_) AS COL_73_0_,       MAX(main.COL_74_0_) AS COL_74_0_,       MAX(main.COL_75_0_) AS COL_75_0_,       MAX(main.COL_76_0_) AS COL_76_0_, MAX(main.COL_90_0_) AS COL_90_0_,      MAX(main.COL_77_0_) AS COL_77_0_,       MAX(main.COL_78_0_) AS COL_78_0_,       MAX(main.COL_79_0_) AS COL_79_0_,       MAX(main.COL_80_0_) AS COL_80_0_,       MAX(main.COL_81_0_) AS COL_81_0_,       MAX(main.COL_82_0_) AS COL_82_0_,       MAX(main.COL_83_0_) AS COL_83_0_,       MAX(main.COL_84_0_) AS COL_84_0_, MAX(main.COL_89_0_) AS COL_89_0_,      MAX(main.COL_85_0_) AS COL_85_0_,       MAX(main.COL_86_0_) AS COL_86_0_ ,       MAX(main.COL_87_0_) AS COL_87_0_  ");
		query.append("  FROM (SELECT MAX(a.PSR_NO) AS col_0_0_,   MAX(d.PROOF_NUM) AS col_1_0_,   MAX(a.emp_prefix) AS col_2_0_,   trim(MAX(concat(concat(concat(a.emp_fname, ' '),  concat(" + getNullValueforMySql + ", ' ')),       concat(a.emp_lname, ' ')))) AS col_3_0_,   MAX(concat(concat(a.scale_start_amt, ' - '), a.scale_end_amt)) AS col_4_0_,   MAX(a.grade_name) AS col_5_0_,   MAX(a.dsgn_name) AS col_6_0_,   MAX(a.emp_gpf_num) AS col_7_0_, MAX(a.SancNumber)  AS col_8_0_,   ' ' as col_9_0_,  " + getOrderDate + " as col_10_0_ ,  (SELECT concat(concat(hreisbankm17_.BANK_NAME, ', '),      hreisbranc16_.branch_name)      FROM HR_EIS_BANK_DTLS  hreisbankd15_,     hr_eis_branch_mst hreisbranc16_,     hr_eis_bank_mst   hreisbankm17_     WHERE hreisbankd15_.BANK_EMP_ID = a.EMP_ID AND     hreisbranc16_.BRANCH_ID = hreisbankd15_.BANK_BRANCH_ID AND     hreisbankd15_.BANK_ID = hreisbankm17_.BANK_ID) AS col_11_0_,   (SELECT hreisbankd14_.BANK_ACCT_NO      FROM HR_EIS_BANK_DTLS hreisbankd14_     WHERE hreisbankd14_.BANK_EMP_ID = a.EMP_ID) AS col_12_0_,   (SELECT hreisbranc16_.micr_code      FROM HR_EIS_BANK_DTLS  hreisbankd15_,     hr_eis_branch_mst hreisbranc16_,     hr_eis_bank_mst   hreisbankm17_     WHERE hreisbankd15_.BANK_EMP_ID = a.EMP_ID AND     hreisbranc16_.BRANCH_ID = hreisbankd15_.BANK_BRANCH_ID AND     hreisbankd15_.BANK_ID = hreisbankm17_.BANK_ID) AS col_13_0_,  " ); 
		query.append("  CASE when(SUM(a.hrr) + SUM(a.rent)) > 0 then a.quarterdtls       when(SUM(a.hrr) + SUM(a.rent))  <= 0  then " + freestring + " End col_14_0_,     CASE   WHEN b.LOAN_TYPE_ID IN (" +  hbaLoan + ") AND a.paybill_id = b.paybill_id THEN   MAX(b.LOAN_ACCOUNT_NO)   END col_15_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" +  hbaLoan + ") AND b.recovery_type IN (" + principle + ") AND   a.paybill_id = b.paybill_id THEN   MAX(TOTAL_AMT)   END col_16_0_, CASE   WHEN b.LOAN_TYPE_ID IN (" +  hbaLoan + ") AND b.recovery_type IN (" + principle + ") AND   a.paybill_id = b.paybill_id THEN   MAX(RECOVERED_INST)   END col_17_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" +  hbaLoan + ") AND b.recovery_type IN (" + principle + ") AND   a.paybill_id = b.paybill_id THEN   MAX(TOTAL_INST)   END col_18_0_, CASE   WHEN b.LOAN_TYPE_ID IN (" +  hbaLoan + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN trim(" + getmonth + " )  End  col_19_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" +  carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND   a.paybill_id = b.paybill_id THEN   MAX(b.LOAN_ACCOUNT_NO)   END col_20_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND   b.recovery_type IN (" + intrest + ") AND   a.paybill_id = b.paybill_id THEN " ); 
		query.append("  MAX(TOTAL_AMT)   END col_21_0_,     CASE   WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND   b.recovery_type IN (" + intrest + ") AND   a.paybill_id = b.paybill_id THEN   MAX(RECOVERED_INST)   END col_22_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND   b.recovery_type IN (" + intrest + ") AND   a.paybill_id = b.paybill_id THEN   MAX(TOTAL_INST)   END col_23_0_,   CASE   WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND  b.recovery_type IN (" + intrest + ") AND       a.paybill_id = b.paybill_id THEN trim(" + getmonth + " )  END AS col_24_0_  ,  ' ' as col_25_0_,   SUM(a.society_old) as col_26_0_,   SUM(a.society_new) as col_27_0_  ,   CASE   WHEN b.LOAN_TYPE_ID IN ("  +  gpfAdvId + ") AND b.recovery_type IN (" + principle + ") AND   a.paybill_id = b.paybill_id THEN   MAX(RECOVERED_AMT)   END col_28_0_,   CASE   WHEN b.LOAN_TYPE_ID IN ("  +  gpfAdvId + ") AND b.recovery_type IN (" + principle + ") AND   a.paybill_id = b.paybill_id THEN   MAX(RECOVERED_INST)   END col_29_0_,     SUM(a.po) AS col_30_0_,   SUM(a.PER_PAY) AS col_31_0_,   case  when    MAX(a.grade_id)  IN (" + class1 + ", " + class2 + ", " + AIS+ ") then  SUM(round(((a.po+a.pe+a.LEAVE_PAY)/a.current_basic)*a.SCALE_GRADE_PAY))   END col_32_0_, " ); 
		query.append("  SUM(a.pe) AS col_33_0_,   SUM(a.SPL_PAY) AS col_34_0_,   case  when    MAX(a.grade_id) IN (" + class3 + ", " + class4 + ") then SUM(round(((a.po+a.pe+a.LEAVE_PAY)/a.current_basic)*a.SCALE_GRADE_PAY))   END col_35_0_,     SUM(a.LEAVE_PAY) AS col_36_0_,     SUM(a.le) AS col_37_0_,     case  when    MAX(a.grade_id)  IN (" + class1 + ", " + class2 + ", " + AIS+ ") then  SUM(a.dp_gazzeted)   END col_38_0_,     case  when    MAX(a.grade_id) IN (" + class3 + ", " + class4 + ") then    SUM(a.d_pay)   END col_39_0_, " );
		query.append("  SUM(a.DA) col_40_0_,   0 AS col_41_0_,   SUM(a.HRA) AS col_42_0_,   SUM(a.CLA) AS col_43_0_,   Max(a.otherAlnc) AS Col_44_0_,   SUM(a.MA) AS col_45_0_,   MAX(a.bonus) AS col_46_0_,   SUM(a.WA) AS col_47_0_,   SUM(a.othercharges) AS Col_48_0_,   SUM(a.TR_ALLOW) AS col_49_0_,   0 AS col_50_0_,   SUM(a.FES_ADV) AS col_51_0_,   SUM(a.FOOD_ADV) AS col_52_0_,   '' AS col_53_0_,   SUM(a.grossAmt) AS col_54_0_,   ' ' as col_55_0_,   SUM(a.I_TAX) AS col_56_0_,   SUM(a.surcharge) AS col_57_0_,   SUM(a.HRR) AS col_58_0_,   0 AS col_59_0_,   SUM(a.rent) AS col_60_0_,   0 AS col_61_0_,   0 AS col_62_0_,   SUM(a.P_TAX) AS col_63_0_,   SUM(a.GIS_1979) AS col_64_0_,   MAX(a.GIS_IF_1980) AS col_65_0_,   MAX(a.GIS_SF_1980) AS col_66_0_,   SUM(a.AISIF) AS col_67_0_,   SUM(a.AISSF) AS col_68_0_,   MAX(a.gpfOther) AS col_69_0_,   SUM(a. cpf) AS col_70_0_,  MAX(a.aisPf) AS col_71_0_,   SUM(a.da_gpf) AS col_72_0_,    SUM(a.GPF_ADV) AS col_73_0_,   SUM(a.car_sct_moped_adv) AS col_74_0_,   SUM(a.car_sct_moped_int) AS col_75_0_,   sum(a.OCA_CYCLE_ADV) AS col_76_0_,sum(a.OCA_CYCLE_INT) AS col_90_0_,   SUM(a.HBA) AS col_77_0_,   MAX(a.HBA_INT) AS col_78_0_,   SUM(a.FAN_ADV) AS col_79_0_,   MAX(a.FAN_INT) AS col_80_0_,   SUM(a.JEEP_R) AS col_81_0_,   SUM(a.MISC_REC) AS col_82_0_,   SUM(a.gpf_iv) AS col_83_0_,   SUM(a.da_gpfiv) AS col_84_0_, sum(a.gpf_iv_adv) as col_89_0_,  SUM(a.total_ded) AS col_85_0_,   SUM(a.NET_TOTAL) as col_87_0_ , Max((select ps.bill_no from hr_pay_bill_subhead_mpg  ps where ps.bill_subhead_id in (SELECT distinct paybillhea148_.bill_no FROM PAYBILL_HEAD_MPG paybillhea148_ where  paybillhea148_.paybill_id = a.PAYBILL_GRP_ID) )) as col_86_0_   FROM (SELECT hrpaypaybi146_.id PAYBILL_ID,     hrpaypaybi146_.EMP_ID EMP_ID,     orgempmst6_.emp_gpf_num emp_gpf_num,     hrpaypaybi146_.ls LEAVE_PAY,     hrpaypaybi146_.SPL_PAY SPL_PAY,     hrpaypaybi146_.PER_PAY PER_PAY,     hrpaypaybi146_.DA DA,     hrpaypaybi146_.CLA CLA,     hrpaypaybi146_.HRA HRA,     hrpaypaybi146_.WA WA,     hrpaypaybi146_.MA MA,     hrpaypaybi146_.trans_all TR_ALLOW,     hrpaypaybi146_.it I_TAX,  " );
		query.append("  hrpaypaybi146_.HRR HRR,     hrpaypaybi146_.pt P_TAX,     hrpaypaybi146_.GPF_ADV GPF_ADV,     hrpaypaybi146_.FES_ADV FES_ADV,     hrpaypaybi146_.FOOD_ADV FOOD_ADV,     hrpaypaybi146_.FAN_ADV FAN_ADV,     hrpaypaybi146_.HBA HBA,     hrpaypaybi146_.misc_recov MISC_REC,     hrpaypaybi146_.GPF_C GPF,     hrpaypaybi146_.gpf_c gpfOther,     hrpaypaybi146_.JEEP_R JEEP_R,     hrpaypaybi146_.NET_TOTAL NET_TOTAL,     orgempmst6_.user_id user_id,     orgempmst6_.emp_fname emp_fname,     orgempmst6_.emp_mname emp_mname,     orgempmst6_.emp_lname emp_lname,     orgempmst6_.emp_dob emp_dob,     orgempmst6_.emp_doj emp_doj,     hreisother2_.INCREMENT_DATE INCREMENT_DATE,     hreisscale5_.scale_start_amt scale_start_amt,     hreisscale5_.scale_incr_amt scale_incr_amt,     hreisscale5_.scale_end_amt scale_end_amt,     hreisscale5_.scale_higher_incr_amt scale_higher_incr_amt,     hreisscale5_.scale_higher_end_amt scale_higher_end_amt,     orgdesigna9_.dsgn_shrt_name dsgn_name,     cmnlocatio10_.loc_name loc_name,     hrpaypaysl1_.society_old society_old,     hrpaypaysl1_.society_new society_new,     hrpaypaysl1_.karamchari_bank karamchari_bank,     hrpaypaysl1_.nagrik_bank nagrik_bank,     hrpaypaysl1_.LIC LIC,     hrpaypaysl1_.post_office_recurring_deposit post_office_recurring_deposit,     hrpaypaybi146_.CAR_SCT_MOPED_INT CAR_SCT_MOPED_INT,     hrpaypaybi146_.HBA_INT HBA_INT,     hrpaypaybi146_.FAN_INT FAN_INT,     hrpaypaybi146_.OCA_CYCLE_ADV OCA_CYCLE_ADV,     hrpaypaybi146_.OCA_CYCLE_INT OCA_CYCLE_INT,     hrpaypaybi146_.sis_gis_1979 GIS_1979,     hrpaypaybi146_.sis_if_1981 GIS_IF_1980,     hrpaypaybi146_.sis_sf_1981 GIS_SF_1980,     hrpaypaybi146_.pay_recover pay_recover,     hrpaypaybi146_.PSR_NO PSR_NO,     orgempmst6_.emp_prefix emp_prefix,     hrpaypaybi146_.bonus bonus,  " ); 
		query.append("  hreisscale5_.SCALE_GRADE_PAY SCALE_GRADE_PAY,     (SELECT concat(concat(concat(concat(cmnaddress147_.AREA,     '~'),    concat(cmnaddress147_.STREET,     '~')),   concat(hreisquate149_.QUA_TYPE,    '~')),  concat(ccm.city_name, '')) quarterdtls  FROM cmn_address_mst    cmnaddress147_, hr_eis_qtr_emp_mpg hreisqtrms148_, HR_QUATER_TYPE_MST hreisquate149_, cmn_city_mst ccm WHERE hreisquate149_.quater_id = hreisqtrms148_.quartertype_lookupid and cmnaddress147_.ADDRESS_ID = hreisqtrms148_.address_id AND ccm.city_id = cmnaddress147_.city_id AND hreisqtrms148_.allocated_to = orgempmst6_.user_id AND  " ); 
		query.append("  hreisqtrms148_.allocation_start_date <=  '" + endDate + "' AND (hreisqtrms148_.allocation_end_date is null or  hreisqtrms148_.allocation_end_date >  '"+ startDate +"'  )) quarterdtls,     hrpaypaybi146_.d_pay d_pay, hrpaypaybi146_.dp_gazzeted dp_gazzeted,     hrpaypaybi146_.other_allow otherAlnc,     hrpaypaybi146_.other_chrgs othercharges,     hrpaypaybi146_.gross_amt grossAmt,     hrpaypaybi146_.slo slo,     hrpaypaybi146_.surcharge surcharge,     hrpaypaybi146_.rent_b rent,     hrpaypaybi146_.ais_if_1980 AISIF,     hrpaypaybi146_.ais_sf_1980 AISSF,     grdmst.grade_name grade_name,     ORDERMST.ORDER_NAME ORDER_NAME,     ORDERMST.ORDER_DATE ORDER_DATE,     hrpaypaybi146_.po po,     hrpaypaybi146_.pe pe,     grdmst.grade_id grade_id,     hrpaypaybi146_.Le le,     hrpaypaybi146_.cpf cpf,  ");
		query.append("  hrpaypaybi146_.da_gpfiv da_gpfiv,     hrpaypaybi146_.car_sct_moped_adv car_sct_moped_adv,       hrpaypaybi146_.gpf_iv gpf_iv, hrpaypaybi146_.gpf_iv_adv gpf_iv_adv,    hrpaypaybi146_.da_gpf da_gpf,     hrpaypaybi146_.total_ded total_ded,     hrpaypaybi146_.Ais_Pf aisPf,  hreisother2_.other_current_basic current_basic,   (select hrpayorder24_.ORDER_NAME    from HR_PAY_ORDER_MST hrpayorder24_   where hrpayorder24_.ORDER_ID = hrpayorder11_.ORDER_ID)  SancNumber , hrpaypaybi146_.PAYBILL_GRP_ID  PAYBILL_GRP_ID     FROM HR_PAY_PAYBILL hrpaypaybi146_       LEFT OUTER JOIN HR_PAY_payslip_non_govt hrpaypaysl1_ on hrpaypaybi146_.ID = hrpaypaysl1_.paybill_id       LEFT OUTER JOIN HR_EIS_OTHER_DTLS hreisother2_ on hreisother2_.EMP_ID = hrpaypaybi146_.EMP_ID       LEFT OUTER JOIN HR_EIS_SGD_MPG hreissgdmp3_ ON hreisother2_.EMP_SGD_ID = hreissgdmp3_.SGD_MAP_ID       ");
		query.append("  LEFT OUTER JOIN HR_EIS_GD_MPG hreisgdmpg4_ ON hreissgdmp3_.SGD_GD_ID = hreisgdmpg4_.GD_MAP_ID       LEFT OUTER JOIN hr_eis_scale_mst hreisscale5_ ON hreissgdmp3_.SGD_SCALE_ID = hreisscale5_.scale_id       LEFT OUTER JOIN hr_eis_emp_mst hreisempms149_ on hrpaypaybi146_.EMP_ID = hreisempms149_.emp_id       LEFT OUTER JOIN org_emp_mst orgempmst6_ on orgempmst6_.emp_id = hreisempms149_.emp_mpg_id       LEFT OUTER JOIN org_userpost_rlt orguserpos7_ on orguserpos7_.user_id = orgempmst6_.user_id AND orguserpos7_.post_id = hrpaypaybi146_.post_id       LEFT OUTER JOIN org_post_details_rlt orgpostdet8_ on orguserpos7_.post_id = orgpostdet8_.post_id AND orgpostdet8_.post_id = hrpaypaybi146_.post_id            ");
		query.append("  AND orgempmst6_.lang_id = " + langId  + " AND orgpostdet8_.lang_id = " + langId  + " and orgpostdet8_.loc_id =  " + locId + "       LEFT OUTER JOIN HR_PAY_ORDER_HEAD_POST_MPG hrpayorder12_ on hrpayorder12_.POST_ID = hrpaypaybi146_.post_id       LEFT OUTER JOIN HR_PAY_ORDER_HEAD_MPG hrpayorder11_ on hrpayorder11_.order_head_id = hrpayorder12_.order_head_id       LEFT OUTER JOIN HR_PAY_ORDER_MST ORDERMST on ORDERMST.ORDER_ID = hrpayorder11_.order_id       LEFT OUTER JOIN org_designation_mst orgdesigna9_ on orgpostdet8_.dsgn_id = orgdesigna9_.dsgn_id AND orgdesigna9_.lang_id = " + langId  + "       LEFT OUTER JOIN cmn_location_mst cmnlocatio10_ on orgpostdet8_.loc_id = cmnlocatio10_.loc_id AND cmnlocatio10_.lang_id = " + langId  + "        ");
		query.append("  LEFT OUTER JOIN org_grade_mst grdmst on grdmst.grade_id = orgempmst6_.grade_id     WHERE (hrpaypaybi146_.PAYBILL_GRP_ID IN     (SELECT paybillhea148_.PAYBILL_ID   FROM PAYBILL_HEAD_MPG paybillhea148_  WHERE paybillhea148_.PAYBILL_MONTH = " + Month+ "  AND  paybillhea148_.PAYBILL_YEAR = " + year+ " AND  paybillhea148_.approve_flag in  (" + created + "," + approved + ") "); 
		if(billNo.size() > 0 )
		{
			query.append("  AND  paybillhea148_.bill_no in ( " + billNo.get(0));
    	if(billNo.size() > 1 )
    	for(int i = 1; i < billNo.size(); i++ )
    	{
    		query.append("," + billNo.get(i));
    	}
    	query.append(" ) " );
		}
		query.append(" ))) a LEFT JOIN (SELECT hrloanempd18_.emp_id,   MAX(hrloanempd18_.LOAN_ACCOUNT_NO) LOAN_ACCOUNT_NO,   hrloanempd18_.loan_type_id loan_type_id,   MAX(hrpaypaybi19_.RECOVERED_AMT) RECOVERED_AMT,   MAX(hrpaypaybi19_.RECOVERED_INST) RECOVERED_INST,   hrpaypaybi19_.paybill_id paybill_id,   MAX(hrloanempd18_.loan_date) loan_date,     ");
		query.append("  hrpaypaybi19_.recovery_type recovery_type,   MAX(hrpaypaybi19_.total_inst) total_inst,   MAX(hrpaypaybi19_.total_amt) total_amt    FROM HR_LOAN_EMP_DTLS   hrloanempd18_,   HR_PAY_PAYBILL_LOAN_DTLS hrpaypaybi19_   WHERE hrpaypaybi19_.LOAN_TYPE_ID =   hrloanempd18_.LOAN_TYPE_ID AND   hrloanempd18_.EMP_LOAN_ID =   hrpaypaybi19_.EMP_LOAN_ID   GROUP BY hrloanempd18_.emp_id,hrloanempd18_.loan_type_id,hrpaypaybi19_.paybill_id,hrpaypaybi19_.recovery_type) b ON a.emp_id = b.emp_id LEFT JOIN hr_eis_proof_dtl d ON d.user_id = a.user_id   GROUP BY a.EMP_ID,  a.PSR_NO,      a.USER_ID,      a.PSR_NO,      a.PAYBILL_ID,      b.loan_type_id,      b.paybill_id,      b.recovery_type) main GROUP BY main.col_0_0_ order by col_86_0_, col_0_0_  ");
		
		logger.info("Query for get getDataforDisplay is---->>>>"+query.toString());
		//Query sqlQuery=hibSession.createSQLQuery(query.toString());										
		//resList=sqlQuery.list();
		try{
			logger.info("query here is ::::"+query);
		lPStmt = lCon.prepareStatement(query.toString());
		
		lRs = lPStmt.executeQuery();
		
		while(lRs.next()) 
		{
			Object[] rowNew=new Object[90];
			for(int i=1;i<91;i++)
			{
			String row = lRs.getString(i)!=null?lRs.getString(i).toString():"";
			rowNew[i-1]=row;
		
			}
			
		resList.add(rowNew);	
		}
		logger.info("resList size=="+resList.size());
	}
		catch(SQLException se){
			logger.error("Error is: "+ se.getMessage());
            logger.error("Exception in getDataforDisplay " + se.getStackTrace());	
		}
		finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
	return resList;

}

public List getGeneratedBillList(long locId, int Month, String Year)
{			
	List resList = new ArrayList();
	List billData = new ArrayList();
	Session hibSession = getSession();
	StringBuffer query = new StringBuffer(); 
	query.append( " select ps.bill_no from hr_pay_bill_subhead_mpg ps where ps.bill_subhead_id in (select distinct ph.bill_no from paybill_head_mpg ph where  ph.approve_flag in (0,1) ");
	if(Month != -1 )
		query.append( " and ph.paybill_month = " + Month);
	if(Year != null && !Year.equals(""))
		query.append( " and ph.paybill_year = " + Year);
	if(locId != -1 )
		query.append( " and ph.loc_id = " + locId);
	query.append( " )order by ps.bill_no ");
	logger.info("Query for get getGeneratedBillList is---->>>>  " + query.toString());
	Query sqlQuery=hibSession.createSQLQuery(query.toString());		
	resList=sqlQuery.list();
	 for (Iterator it = resList.iterator(); it.hasNext();) 
	 {
         //Object[] row = (Object[]) it.next();
         billData.add(it.next().toString()); 
     }
 		return billData;
}	
public List getxtraDataforDisplay(long locId, String billNo,String Year )
{			
	List resList=null;
	
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer(); 

		query.append(" select distinct  bshm.budsubhd_id ,  bshm.demand_code, bshm.budmjrhd_code, bshm.budsubmjrhd_code, bshm.budminhd_code, bshm.budsubhd_code, hpbsm.head_chargable, hpbsm.head_chargable , hpbsm.bill_no ");
		query.append(" from SGVC_FIN_YEAR_MST        fym, sgva_budsubhd_mst        bshm,  hr_pay_order_subhead_mpg osm, hr_pay_bill_subhead_mpg  hpbsm, org_grade_mst  ogm ");
		query.append(" where   hpbsm.subhead_code = osm.element_code and   osm.budsubhd_id = bshm.budsubhd_id and bshm.budsubhd_id = osm.budsubhd_id ");
		query.append(" and  bshm.lang_id = 'en_US' and fym.fin_year_id = bshm.fin_yr_id  ");
		query.append(" and  hpbsm.bill_subhead_id in (" + billNo + ") ");
		if(locId > -1)
			query.append(" and  hpbsm.loc_id = " + locId);
		if(Year!=null && !Year.equals(""))
			query.append(" and  fym.fin_year_code =  " + Year);
		query.append(" order by hpbsm.bill_no ");

		logger.info("Query for get getBillNoData is---->>>>"+query.toString());
		Query sqlQuery=hibSession.createSQLQuery(query.toString());										
		resList=sqlQuery.list();
			
	return resList;
}
public List getxtraDataforDisplay(long locId, List billNo,String Year )
{
	List resList=null;
	Session hibSession = getSession();
	StringBuffer query = new StringBuffer(); 
	query.append(" select distinct  bshm.budsubhd_id ,  bshm.demand_code, bshm.budmjrhd_code, bshm.budsubmjrhd_code, bshm.budminhd_code, bshm.budsubhd_code, hpbsm.head_chargable, hpbsm.head_chargable , hpbsm.bill_no ");
	query.append(" from SGVC_FIN_YEAR_MST        fym, sgva_budsubhd_mst        bshm,  hr_pay_order_subhead_mpg osm, hr_pay_bill_subhead_mpg  hpbsm, org_grade_mst  ogm ");
	query.append(" where   hpbsm.subhead_code = osm.element_code and   osm.budsubhd_id = bshm.budsubhd_id and bshm.budsubhd_id = osm.budsubhd_id ");
	query.append(" and  bshm.lang_id = 'en_US' and fym.fin_year_id = bshm.fin_yr_id  ");
	if(billNo.size() > 0 )
	{
		query.append(" and  hpbsm.bill_no in (");
		query.append(billNo.get(0));
		if(billNo.size() > 1 )
		for(int i = 1; i < billNo.size(); i++ )
		{
			query.append("," + billNo.get(i));
		}
		query.append(")");
	}
	if(locId > -1)
		query.append(" and  hpbsm.loc_id = " + locId);
	if(Year!=null && !Year.equals(""))
		query.append(" and  fym.fin_year_code =  " + Year);
	query.append(" order by hpbsm.bill_no ");
	logger.info("Query for get getBillNoData is---->>>>"+query.toString());
	Query sqlQuery=hibSession.createSQLQuery(query.toString());										
	resList=sqlQuery.list();
	logger.info(":::::::::::::::::::::::::::::::::  in dao Impl " + resList );
		
return resList;
}
public List getBillListForDisplay(long locId)
{			
	List resList=null;
	
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer(); 
		query.append("select s.bill_no,s.BILL_SUBHEAD_ID from hr_pay_bill_subhead_mpg s where loc_id="+locId+" order by bill_no ");
		logger.info("Query for get getBillNoData is---->>>>"+query.toString());
		Query sqlQuery=hibSession.createSQLQuery(query.toString());										
		resList=sqlQuery.list();
			
	return resList;
}	
public List getPaybillGrpId(int month,String year, String BillNo,long locId)
{			
	List resList=null;
	
		Session hibSession = getSession();
		String query = "select distinct regMpg.trnBillRegister.billNo, pb.billHeadId from PaybillHeadMpg hh, HrPayBillHeadMpg pb,PaybillBillregMpg regMpg where regMpg.hrPayPaybill=hh.hrPayPaybill and " +
 		 		"hh.cmnLocationMst.locId="+locId+" and hh.month="+month+" and hh.year="+year+" and pb.billHeadId in (" + BillNo + " ) and pb.billHeadId=hh.billNo.billHeadId and hh.approveFlag in (0,1)";
 		
		logger.info("Query for get getBillNoData is---->>>>"+query.toString());
		Query hsqlQuery = hibSession.createQuery(query);
		resList = hsqlQuery.list();
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   getPaybillGrpId : " + resList);
		
	return resList;
}	

}

