/**
 * This is a class which implements the AllowTypeMasterDAO and contains the methods for hr_pay_allow_type_mst table.
 * Made By:- Urvin shah.
 * Date:- 14/07/2007.
 */
package com.tcs.sgv.payslip.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class commonPayslipDAOImpl extends GenericDaoHibernateImpl  implements commonPayslipDAO {
	
	public commonPayslipDAOImpl(Class type, SessionFactory sessionFactory) {
        super(type);
        setSessionFactory(sessionFactory);
    }
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	// Ids for Non Government Dedcution.
	
	String oldSociety = rb.getString("societyOld").toString();		// Old Society Id from Property File.	
	String newSociety = rb.getString("society").toString();		// New Society Id from property File.		
	String karmBank = rb.getString("karmcharyBank").toString();		// Karmchary Bank Id from Property File.	
	String nagBank = rb.getString("nagrikBank").toString();		// Nagrik Bank Id from property File.
	String licDed = rb.getString("LIC").toString();		// LIC Id from Property File.	
	String POR = rb.getString("POR").toString();		// LIC Id from Property File.	
	String hbaLoanId = rb.getString("hba");	// HBA Loan Id from Property File.
	String carLoanId = rb.getString("car");	// Car Loan Id from Property File.
	String scooterLoanId = rb.getString("scooter");	// Scooter Loan Id from Property File.
	String mopedLoanId = rb.getString("moped");	// Moped Loan Id from Property File.
	String fanAdvId = rb.getString("fan");	// Fan Advance Id from Property File.
	String gpfAdvId = rb.getString("gpf");	// GPF Advance Id from Property File.
	String foodAdvId = rb.getString("foodGrain");	// Food Grain Advance Id from Property File.
	String festAdvId = rb.getString("festival");	// Food Grain Advance Id from Property File.
	String ocaAdvId = rb.getString("OCA_Cycle_Loan");	// Food Grain OCA Id from Property File.
	String principle = rb.getString("principal");
	String intrest = rb.getString("interest");
	public List getPayslipData(int month,int year,long empId,long deptId,String billNo,String classArray,String dsgnArray)
	{
        SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, (year));
        cal.set(Calendar.MONTH,(month)-1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        java.util.Date startMonthDate = cal.getTime();
        String startDate  = sdfObj.format(startMonthDate);
        
        cal.set(Calendar.YEAR, (year));
        cal.set(Calendar.MONTH,(month)-1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        cal.set(Calendar.DAY_OF_MONTH, totalDays);
        
        java.util.Date endMonthDate = cal.getTime();
        
        String endDate  = sdfObj.format(endMonthDate);
		List paySlipList = new ArrayList();
		Session hibSession = getSession();
		StringBuilder strQuery = new StringBuilder(); 
		DBsysdateConfiguration sbConf=new DBsysdateConfiguration();

		if(!sbConf.isMySQL())
		{
		strQuery.append(" 	SELECT MAX(main.COL_0_0_) AS COL_0_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_1_0_) AS COL_1_0_,	 ");
		strQuery.append(" 	       main.COL_2_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_3_0_) AS COL_3_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_4_0_) AS COL_4_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_5_0_) AS COL_5_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_6_0_) AS COL_6_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_7_0_) AS COL_7_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_8_0_) AS COL_8_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_9_0_) AS COL_9_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_10_0_) AS COL_10_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_11_0_) AS COL_11_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_12_0_) AS COL_12_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_13_0_) AS COL_13_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_14_0_) AS COL_14_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_15_0_) AS COL_15_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_16_0_) AS COL_16_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_17_0_) AS COL_17_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_18_0_) AS COL_18_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_19_0_) AS COL_19_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_20_0_) AS COL_20_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_21_0_) AS COL_21_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_22_0_) AS COL_22_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_23_0_) AS COL_23_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_24_0_) AS COL_24_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_25_0_) AS COL_25_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_26_0_) AS COL_26_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_27_0_) AS COL_27_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_28_0_) AS COL_28_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_29_0_) AS COL_29_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_30_0_) AS COL_30_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_31_0_) AS COL_31_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_32_0_) AS COL_32_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_33_0_) AS COL_33_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_34_0_) AS COL_34_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_35_0_) AS COL_35_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_36_0_) AS COL_36_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_37_0_) AS COL_37_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_38_0_) AS COL_38_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_39_0_) AS COL_39_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_40_0_) AS COL_40_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_41_0_) AS COL_41_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_42_0_) AS COL_42_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_43_0_) AS COL_43_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_44_0_) AS COL_44_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_45_0_) AS COL_45_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_46_0_) AS COL_46_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_47_0_) AS COL_47_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_48_0_) AS COL_48_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_49_0_) AS COL_49_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_50_0_) AS COL_50_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_51_0_) AS COL_51_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_52_0_) AS COL_52_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_53_0_) AS COL_53_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_54_0_) AS COL_54_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_55_0_) AS COL_55_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_56_0_) AS COL_56_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_57_0_) AS COL_57_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_58_0_) AS COL_58_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_59_0_) AS COL_59_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_60_0_) AS COL_60_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_61_0_) AS COL_61_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_62_0_) AS COL_62_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_63_0_) AS COL_63_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_64_0_) AS COL_64_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_65_0_) AS COL_65_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_66_0_) AS COL_66_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_67_0_) AS COL_67_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_68_0_) AS COL_68_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_69_0_) AS COL_69_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_70_0_) AS COL_70_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_71_0_) AS COL_71_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_72_0_) AS COL_72_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_73_0_) AS COL_73_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_74_0_) AS COL_74_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_75_0_) AS COL_75_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_76_0_) AS COL_76_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_77_0_) AS COL_77_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_78_0_) AS COL_78_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_79_0_) AS COL_79_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_80_0_) AS COL_80_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_81_0_) AS COL_81_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_82_0_) AS COL_82_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_83_0_) AS COL_83_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_84_0_) AS COL_84_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_85_0_) AS COL_85_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_86_0_) AS COL_86_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_87_0_) AS COL_87_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_88_0_) AS COL_88_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_89_0_) AS COL_89_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_90_0_) AS COL_90_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_91_0_) AS COL_91_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_92_0_) AS COL_92_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_93_0_) AS COL_93_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_94_0_) AS COL_94_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_95_0_) AS COL_95_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_96_0_) AS COL_96_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_97_0_) AS COL_97_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_98_0_) AS COL_98_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_99_0_) AS COL_99_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_100_0_) AS COL_100_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_101_0_) AS COL_101_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_102_0_) AS COL_102_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_103_0_) AS COL_103_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_104_0_) AS COL_104_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_105_0_) AS COL_105_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_106_0_) AS COL_106_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_107_0_) AS COL_107_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_108_0_) AS COL_108_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_109_0_) AS COL_109_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_110_0_) AS COL_110_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_111_0_) AS COL_111_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_112_0_) AS COL_112_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_113_0_) AS COL_113_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_114_0_) AS COL_114_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_115_0_) AS COL_115_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_116_0_) AS COL_116_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_117_0_) AS COL_117_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_118_0_) AS COL_118_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_119_0_) AS COL_119_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_120_0_) AS COL_120_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_121_0_) AS COL_121_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_122_0_) AS COL_122_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_123_0_) AS COL_123_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_124_0_) AS COL_124_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_125_0_) AS COL_125_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_126_0_) AS COL_126_0_,	 ");
		strQuery.append(" 	       MAX(main.COL_127_0_) AS COL_127_0_, ");
		strQuery.append(" 	       MAX(main.COL_128_0_) AS COL_128_0_, ");
		strQuery.append(" 	       MAX(main.COL_129_0_) AS COL_129_0_, ");//added by rahul Date 14_aug_09
        strQuery.append(" 	       MAX(main.COL_130_0_) AS COL_130_0_ ");//added by rahul Date 14_aug_09
		strQuery.append(" 	  FROM (SELECT MAX(a.PAYSLIP_ID) AS col_0_0_,	 ");
		strQuery.append(" 	               MAX(a.PAYBILL_ID) AS col_1_0_,	 ");
		strQuery.append(" 	               MAX(a.EMP_ID) AS col_2_0_,	 ");
		strQuery.append(" 	               MAX(a.PAYSLIP_MONTH) AS col_3_0_,	 ");
		strQuery.append(" 	               MAX(a.PAYSLIP_YEAR) AS col_4_0_,	 ");
		strQuery.append(" 	               MAX(a.BILL_NO) AS col_5_0_,	 ");
		strQuery.append(" 	               (SELECT hrquaterty13_.QUA_TYPE	 ");
		strQuery.append(" 	                  FROM HR_QUATER_TYPE_MST hrquaterty13_	 ");
		strQuery.append(" 	                 WHERE hrquaterty13_.QUATER_ID = a.QUATER_NO) AS col_6_0_,	 ");
		strQuery.append(" 	               MAX(a.INCREMENT_AMT) AS col_7_0_,	 ");
		strQuery.append(" 	               MAX(a.IT_ACC_NO) AS col_8_0_,	 ");
		strQuery.append(" 	               MAX(a.emp_gpf_num) AS col_9_0_,	 ");
		strQuery.append(" 	               MAX(a.TOKEN_NO) AS col_10_0_,	 ");
		strQuery.append(" 	               MAX(a.BILL_AMT) AS col_11_0_,	 ");
		strQuery.append(" 	               MAX(a.BUDGET_HEAD) AS col_12_0_,	 ");
		strQuery.append(" 	               MAX(a.PAY_SLIP_DATE) AS col_13_0_,	 ");
		strQuery.append(" 	               SUM(a.BASIC_PAY) AS col_14_0_,	 ");
		strQuery.append(" 	               SUM(a.LEAVE_PAY) AS col_15_0_,	 ");
		strQuery.append(" 	               SUM(a.SPL_PAY) AS col_16_0_,	 ");
		strQuery.append(" 	               SUM(a.PER_PAY) AS col_17_0_,	 ");
		strQuery.append(" 	               SUM(a.DA) AS col_18_0_,	 ");
		strQuery.append(" 	               SUM(a.CLA) AS col_19_0_,	 ");
		strQuery.append(" 	               SUM(a.HRA) AS col_20_0_,	 ");
		strQuery.append(" 	               SUM(a.WA) AS col_21_0_,	 ");
		strQuery.append(" 	               SUM(a.MA) AS col_22_0_,	 ");
		strQuery.append(" 	               SUM(a.IR) AS col_23_0_,	 ");
		strQuery.append(" 	               SUM(a.TR_ALLOW) AS col_24_0_,	 ");
		strQuery.append(" 	               SUM(a.I_TAX) AS col_25_0_,	 ");
		strQuery.append(" 	               SUM(a.HRR) AS col_26_0_,	 ");
		strQuery.append(" 	               SUM(a.P_TAX) AS col_27_0_,	 ");
		strQuery.append(" 	               SUM(a.GIS_1979) AS col_28_0_,	 ");
		strQuery.append(" 	               SUM(a.GPF) AS col_29_0_,	 ");
		strQuery.append(" 	               SUM(a.GPF_ADV) AS col_30_0_,	 ");
		strQuery.append(" 	               SUM(a.FES_ADV) AS col_31_0_,	 ");
		strQuery.append(" 	               SUM(a.FOOD_ADV) AS col_32_0_,	 ");
		strQuery.append(" 	               SUM(a.FAN_ADV) AS col_33_0_,	 ");
		strQuery.append(" 	               SUM(a.HBA) AS col_34_0_,	 ");
		strQuery.append(" 	               SUM(a.VEH_ADV) AS col_35_0_,	 ");
		strQuery.append(" 	               SUM(a.MISC_REC) AS col_36_0_,	 ");
		strQuery.append(" 	               SUM(a.DP) AS col_37_0_,	 ");
		strQuery.append(" 	               SUM(a.GPF) AS col_38_0_,	 ");
		strQuery.append(" 	               SUM(a.JEEP_R) AS col_39_0_,	 ");
		strQuery.append(" 	               SUM(a.NET_TOTAL) AS col_40_0_,	 ");
		strQuery.append(" 	               MAX(a.emp_fname) AS col_41_0_,	 ");
		strQuery.append(" 	               MAX(a.emp_mname) AS col_42_0_,	 ");
		strQuery.append(" 	               MAX(a.emp_lname) AS col_43_0_,	 ");
		strQuery.append(" 	               MAX(a.emp_dob) AS col_44_0_,	 ");
		strQuery.append(" 	               MAX(a.emp_doj) AS col_45_0_,	 ");
		strQuery.append(" 	               MAX(a.INCREMENT_DATE) AS col_46_0_,	 ");
		strQuery.append(" 	               MAX(a.scale_start_amt) AS col_47_0_,	 ");
		strQuery.append(" 	               MAX(a.scale_incr_amt) AS col_48_0_,	 ");
		strQuery.append(" 	               MAX(a.scale_end_amt) AS col_49_0_,	 ");
		strQuery.append(" 	               MAX(a.scale_higher_incr_amt) AS col_50_0_,	 ");
		strQuery.append(" 	               MAX(a.scale_higher_end_amt) AS col_51_0_,	 ");
		strQuery.append(" 	               MAX(a.dsgn_name) AS col_52_0_,	 ");
		strQuery.append(" 	               MAX(a.loc_name) AS col_53_0_,	 ");
		strQuery.append(" 	               SUM(a.society_old) AS col_54_0_,	 ");
		strQuery.append(" 	               SUM(a.society_new) AS col_55_0_,	 ");
		strQuery.append(" 	               SUM(a.karamchari_bank) AS col_56_0_,	 ");
		strQuery.append(" 	               SUM(a.nagrik_bank) AS col_57_0_,	 ");
		strQuery.append(" 	               SUM(a.LIC) AS col_58_0_,	 ");
		strQuery.append(" 	               SUM(a.post_office_recurring_deposit) AS col_59_0_,	 ");
		strQuery.append(" 	               (SELECT hreisbankd14_.BANK_ACCT_NO	 ");
		strQuery.append(" 	                  FROM HR_EIS_BANK_DTLS hreisbankd14_	 ");
		strQuery.append(" 	                 WHERE hreisbankd14_.BANK_EMP_ID = a.EMP_ID) AS col_60_0_,	 ");
		strQuery.append(" 	               (SELECT concat(concat(hreisbranc16_.BRANCH_NAME ,', ') ,hreisbankm17_.BANK_NAME)	 ");
		strQuery.append(" 	                  FROM HR_EIS_BANK_DTLS  hreisbankd15_,	 ");
		strQuery.append(" 	                       hr_eis_branch_mst hreisbranc16_,	 ");
		strQuery.append(" 	                       hr_eis_bank_mst   hreisbankm17_	 ");
		strQuery.append(" 	                 WHERE hreisbankd15_.BANK_EMP_ID = a.EMP_ID AND	 ");
		strQuery.append(" 	                       hreisbranc16_.BRANCH_ID = hreisbankd15_.BANK_BRANCH_ID AND	 ");
		strQuery.append(" 	                       hreisbankd15_.BANK_ID = hreisbankm17_.BANK_ID) AS col_61_0_,	 ");
		strQuery.append(" 	               MAX(a.JEEP_R) AS col_62_0_,	 ");
		strQuery.append(" 	               MAX(a.CAR_SCT_MOPED_INT) AS col_63_0_,	 ");
		strQuery.append(" 	               MAX(a.HBA_INT) AS col_64_0_,	 ");
		strQuery.append(" 	               MAX(a.FAN_INT) AS col_65_0_,	 ");
		strQuery.append(" 	               MAX(a.OCA_CYCLE_ADV) AS col_66_0_,	 ");
		strQuery.append(" 	               MAX(a.OCA_CYCLE_INT) AS col_67_0_,	 ");
		strQuery.append(" 	               MAX(a.GIS_1979) AS col_68_0_,	 ");
		strQuery.append(" 	               MAX(a.GIS_IF_1980) AS col_69_0_,	 ");
		strQuery.append(" 	               MAX(a.GIS_SF_1980) AS col_70_0_,	 ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + hbaLoanId + ") AND a.paybill_id = b.paybill_id THEN MAX(b.LOAN_ACCOUNT_NO) END col_71_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND a.paybill_id = b.paybill_id THEN MAX(b.LOAN_ACCOUNT_NO) END col_72_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + gpfAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_AMT) END col_73_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + gpfAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_INST) END col_74_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + gpfAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_INST) END col_75_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + gpfAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_AMT) END col_76_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + festAdvId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_AMT) END col_77_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + festAdvId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_INST) END col_78_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + festAdvId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_INST) END col_79_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + festAdvId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_AMT) END col_80_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + foodAdvId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_AMT) END col_81_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + foodAdvId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_INST) END col_82_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + foodAdvId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_INST) END col_83_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + foodAdvId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_AMT) END col_84_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + fanAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_AMT) END col_85_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + fanAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_INST) END col_86_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + fanAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_INST) END col_87_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + fanAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_AMT) END col_88_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + ocaAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_AMT) END col_89_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + ocaAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_INST)END col_90_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + ocaAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_INST) END col_91_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + ocaAdvId  + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_AMT)END col_92_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + hbaLoanId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_AMT)END col_93_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + hbaLoanId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_INST) END col_94_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + hbaLoanId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_INST) END col_95_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + hbaLoanId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_AMT) END col_96_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_AMT) END col_97_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_INST) END col_98_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_INST) END col_99_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND b.recovery_type IN (" + principle + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_AMT) END col_100_0_, ");
		strQuery.append(" 0 AS col_101_0_,0 AS col_102_0_,0 AS col_103_0_,0 AS col_104_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + ocaAdvId  + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_AMT) END col_105_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + ocaAdvId  + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_INST) END col_106_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + ocaAdvId  + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_INST) END col_107_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + ocaAdvId  + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_AMT) END col_108_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + hbaLoanId + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_AMT) END col_109_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + hbaLoanId + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_INST) END col_110_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + hbaLoanId + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_INST) END col_111_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + hbaLoanId + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_AMT) END col_112_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_AMT) END col_113_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(RECOVERED_INST)END col_114_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_INST) END col_115_0_, ");
		strQuery.append(" CASE WHEN b.LOAN_TYPE_ID IN (" + carLoanId + "," + scooterLoanId + "," + mopedLoanId + ") AND b.recovery_type IN (" + intrest + ") AND a.paybill_id = b.paybill_id THEN MAX(TOTAL_AMT) END col_116_0_, ");
		strQuery.append(" 	               MAX(c.TOKEN_NUM) AS col_117_0_,	 ");
		strQuery.append(" 	               MAX(c.UPDATED_DATE) AS col_118_0_,	 ");
		strQuery.append(" 	               MAX(c.Bill_Net_Amount) AS col_119_0_,	 ");
		strQuery.append(" 	               MAX(d.PROOF_NUM) AS col_120_0_,	 ");
		strQuery.append(" 	               MAX(a.pay_recover) AS col_121_0_,	 ");
		strQuery.append(" 	               a.PSR_NO AS col_122_0_,	 ");
		strQuery.append(" 	               MAX(a.emp_prefix) AS col_123_0_,	 ");
		strQuery.append(" 	               MAX(a.bonus) AS col_124_0_,	 ");
		strQuery.append(" 	               MAX(a.SCALE_GRADE_PAY) AS col_125_0_,	 ");
		strQuery.append(" 	               MAX(a.DAGPF) AS col_126_0_,	 ");
		strQuery.append(" 	               SUM(a.US_GAD) AS col_127_0_ ,");
		strQuery.append(" 				   quarterdtls AS col_128_0_,  ");
		strQuery.append(" 				   locationId AS col_129_0_,  ");//added by rahul
        strQuery.append(" 				   MAX(emailId) As col_130_0_  ");//added by rahul
		strQuery.append(" 	          FROM (SELECT hrpaypaysl0_.PAYSLIP_ID                    PAYSLIP_ID,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.PAYBILL_ID                    PAYBILL_ID,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.EMP_ID                        EMP_ID,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.PAYSLIP_MONTH                 PAYSLIP_MONTH,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.PAYSLIP_YEAR                  PAYSLIP_YEAR,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.BILL_NO                       BILL_NO,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.INCREMENT_AMT                 INCREMENT_AMT,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.IT_ACC_NO                     IT_ACC_NO,	 ");
		strQuery.append(" 	                       orgempmst6_.emp_gpf_num                    emp_gpf_num,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.TOKEN_NO                      TOKEN_NO,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.BILL_AMT                      BILL_AMT,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.BUDGET_HEAD                   BUDGET_HEAD,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.PAY_SLIP_DATE                 PAY_SLIP_DATE,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.BASIC_PAY                     BASIC_PAY,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.LEAVE_PAY                     LEAVE_PAY,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.SPL_PAY                       SPL_PAY,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.PER_PAY                       PER_PAY,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.DA                            DA,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.CLA                           CLA,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.HRA                           HRA,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.WA                            WA,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.MA                            MA,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.IR                            IR,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.TR_ALLOW                      TR_ALLOW,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.I_TAX                         I_TAX,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.HRR                           HRR,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.P_TAX                         P_TAX,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.GPF_ADV                       GPF_ADV,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.FES_ADV                       FES_ADV,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.FOOD_ADV                      FOOD_ADV,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.FAN_ADV                       FAN_ADV,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.HBA                           HBA,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.VEH_ADV                       VEH_ADV,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.MISC_REC                      MISC_REC,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.DP                            DP,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.GPF                           GPF,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.JEEP_R                        JEEP_R,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.NET_TOTAL                     NET_TOTAL,	 ");
		strQuery.append(" 	                       orgempmst6_.user_id                        user_id,	 ");
		strQuery.append(" 	                       orgempmst6_.emp_fname                      emp_fname,	 ");
		strQuery.append(" 	                       orgempmst6_.emp_mname                      emp_mname,	 ");
		strQuery.append(" 	                       orgempmst6_.emp_lname                      emp_lname,	 ");
		strQuery.append(" 	                       orgempmst6_.emp_dob                        emp_dob,	 ");
		strQuery.append(" 	                       orgempmst6_.emp_doj                        emp_doj,	 ");
		strQuery.append(" 	                       hreisother2_.INCREMENT_DATE                INCREMENT_DATE,	 ");
		strQuery.append(" 	                       hreisscale5_.scale_start_amt               scale_start_amt,	 ");
		strQuery.append(" 	                       hreisscale5_.scale_incr_amt                scale_incr_amt,	 ");
		strQuery.append(" 	                       hreisscale5_.scale_end_amt                 scale_end_amt,	 ");
		strQuery.append(" 	                       hreisscale5_.scale_higher_incr_amt         scale_higher_incr_amt,	 ");
		strQuery.append(" 	                       hreisscale5_.scale_higher_end_amt          scale_higher_end_amt,	 ");
		//strQuery.append(" 	                       orgdesigna9_.dsgn_name                     dsgn_name,	 ");
		strQuery.append(" 	                       case when  ");
		strQuery.append(" 	                       hreisother2_.emp_sgd_id is null ");
		strQuery.append(" 	                       then  ");
		strQuery.append(" 	                       orgdesigna9_.dsgn_name  ");
		strQuery.append(" 	                       else ");
	    strQuery.append(" 	                       dsgn.dsgn_name end dsgn_name , ");

		strQuery.append(" 	                       cmnlocatio10_.loc_name                     loc_name,	 ");
		strQuery.append(" 	                       hrpaypaysl1_.society_old                   society_old,	 ");
		strQuery.append(" 	                       hrpaypaysl1_.society_new                   society_new,	 ");
		strQuery.append(" 	                       hrpaypaysl1_.karamchari_bank               karamchari_bank,	 ");
		strQuery.append(" 	                       hrpaypaysl1_.nagrik_bank                   nagrik_bank,	 ");
		strQuery.append(" 	                       hrpaypaysl1_.LIC                           LIC,	 ");
		strQuery.append(" 	                       hrpaypaysl1_.post_office_recurring_deposit post_office_recurring_deposit,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.CAR_SCT_MOPED_INT             CAR_SCT_MOPED_INT,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.HBA_INT                       HBA_INT,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.FAN_INT                       FAN_INT,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.OCA_CYCLE_ADV                 OCA_CYCLE_ADV,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.OCA_CYCLE_INT                 OCA_CYCLE_INT,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.GIS_1979                      GIS_1979,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.GIS_IF_1980                   GIS_IF_1980,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.GIS_SF_1980                   GIS_SF_1980,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.pay_recover                   pay_recover,	 ");
		strQuery.append(" 	                       hrpaypaybi146_.PSR_NO                      PSR_NO,	 ");
		strQuery.append(" 	                       orgempmst6_.emp_prefix                     emp_prefix,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.bonus                         bonus,	 ");
		strQuery.append(" 	                       hreisscale5_.SCALE_GRADE_PAY               SCALE_GRADE_PAY,	 ");
		strQuery.append(" 	                       hrpaypaysl0_.dagpf                                       DAGPF, 	 ");
		strQuery.append(" 	                       hrpaypaysl0_.quater_no                     quater_no,	 ");
		strQuery.append(" 	                       hrpaypaysl1_.us_gad, ");
		strQuery.append("		( SELECT concat(concat(concat(hreisquate149_.QUA_TYPE,',  '),concat(cmnaddress147_.STREET  ,', ')),cmnaddress147_.AREA)   " )
				.append("			FROM cmn_address_mst  cmnaddress147_, hr_eis_qtr_emp_mpg hreisqtrms148_, HR_QUATER_TYPE_MST hreisquate149_  " )
				.append("				WHERE 	hrpaypaysl0_.QUATER_NO = hreisquate149_.QUATER_ID " )
				.append("					AND cmnaddress147_.ADDRESS_ID = hreisqtrms148_.address_id " )
				.append("					AND hreisqtrms148_.allocated_to = orgempmst6_.user_id " )
				.append(" 					AND hreisqtrms148_.allocation_start_date <= '"+endDate+"' ")
				.append("					AND (hreisqtrms148_.allocation_end_date is null or hreisqtrms148_.allocation_end_date > '"+startDate+"') ) quarterdtls, ");
		strQuery.append(" 	                  orgpostdet8_.loc_id locationId,	 ");//added by rahul
        strQuery.append(" 	                  hreisempms149_.email emailId 	 ");//added by rahul
		strQuery.append(" 	                  FROM HR_PAY_PAYSLIP             hrpaypaysl0_,	 ");
		strQuery.append(" 	                       HR_PAY_PAYBILL             hrpaypaybi146_,	 ");
		strQuery.append(" 	                       HR_PAY_payslip_non_govt    hrpaypaysl1_,	 ");
		strQuery.append(" 	                       HR_EIS_OTHER_DTLS_hst          hreisother2_ LEFT OUTER JOIN HR_EIS_SGD_MPG hreissgdmp3_ ON hreisother2_.EMP_SGD_ID = hreissgdmp3_.SGD_MAP_ID LEFT OUTER JOIN HR_EIS_GD_MPG hreisgdmpg4_ ON hreissgdmp3_.SGD_GD_ID = hreisgdmpg4_.GD_MAP_ID LEFT OUTER JOIN hr_eis_scale_mst hreisscale5_ ON hreissgdmp3_.SGD_SCALE_ID = hreisscale5_.scale_id	 ");
		strQuery.append(" 	                       left outer join org_designation_mst dsgn on dsgn.dsgn_id = hreisgdmpg4_.sgd_desig_id and dsgn.lang_id = 1, "); 
		strQuery.append(" 	                       org_emp_mst                orgempmst6_,	 ");
		strQuery.append(" 	                       org_userpost_rlt           orguserpos7_,	 ");
		strQuery.append(" 	                       org_post_details_rlt       orgpostdet8_,	 ");
		strQuery.append(" 	                       org_designation_mst        orgdesigna9_,	 ");
		strQuery.append(" 	                       cmn_location_mst           cmnlocatio10_,	 ");
		strQuery.append(" 	                       HR_PAY_ORDER_HEAD_MPG      hrpayorder11_,	 ");
		strQuery.append(" 	                       HR_PAY_ORDER_HEAD_POST_MPG hrpayorder12_,	 ");
		strQuery.append(" 	                       hr_eis_emp_mst             hreisempms149_	 ");
		strQuery.append(" 	                 WHERE hreisother2_.trn_counter = hrpaypaybi146_.other_trn_cntr and hrpaypaysl0_.PAYBILL_ID = hrpaypaybi146_.ID AND	 ");
		strQuery.append(" 	                       hrpaypaysl0_.EMP_ID = hreisempms149_.emp_id AND	 ");
		strQuery.append(" 	                       hrpaypaysl0_.PAYSLIP_MONTH = "+month+" AND	 ");
		strQuery.append(" 	                       hrpaypaysl0_.PAYBILL_ID = hrpaypaysl1_.paybill_id AND	 ");
		strQuery.append(" 	                       (hrpaypaybi146_.PAYBILL_GRP_ID IN	 ");
		strQuery.append(" 	                       (SELECT DISTINCT paybillhea148_.PAYBILL_ID	 ");
		strQuery.append(" 	                           FROM PAYBILL_HEAD_MPG paybillhea148_	 ");
		strQuery.append(" 	                          WHERE paybillhea148_.PAYBILL_MONTH = "+month+" AND	 ");
		strQuery.append(" 	                                paybillhea148_.PAYBILL_YEAR = "+year+" AND	 ");
		strQuery.append(" 	                                paybillhea148_.approve_flag = 1 	 ");
		if(billNo!=null&& !billNo.equals(""))		
		strQuery.append(" 	                               AND paybillhea148_.bill_no = "+billNo+"	 ");
		strQuery.append(" 	                               )) AND	 ");
		strQuery.append(" 	                       hreisother2_.EMP_ID = hrpaypaysl0_.EMP_ID AND	 ");
		strQuery.append(" 	                       hrpaypaysl0_.PAYSLIP_YEAR = "+year+" AND	 ");
		strQuery.append(" 	                       orgempmst6_.emp_id = hreisempms149_.emp_mpg_id   ");
		if(empId>0)
        strQuery.append("                          AND orgempmst6_.emp_id = " + empId);
		if(classArray!=null&& !classArray.equals(""))
		strQuery.append("                          AND orgempmst6_.grade_id in (" + classArray+ ")");	
		if(dsgnArray!=null&& !dsgnArray.equals(""))
		strQuery.append("                          AND orgdesigna9_.dsgn_id in (" + dsgnArray + ")");	
		strQuery.append(" 	                       AND orguserpos7_.user_id = orgempmst6_.user_id AND	 ");
		strQuery.append(" 	                       orguserpos7_.post_id = orgpostdet8_.post_id AND	 ");
		strQuery.append(" 	                       orgpostdet8_.loc_id = cmnlocatio10_.loc_id AND	 ");
		strQuery.append(" 	                       orgpostdet8_.dsgn_id = orgdesigna9_.dsgn_id AND	 ");
		strQuery.append(" 	                       orgpostdet8_.post_id = hrpaypaybi146_.post_id AND	 ");
		strQuery.append(" 	                       hrpayorder11_.ORDER_HEAD_ID =	 ");
		strQuery.append(" 	                       hrpayorder12_.ORDER_HEAD_ID AND	 ");
		strQuery.append(" 	                       hrpayorder12_.POST_ID = orguserpos7_.post_id AND	 ");
		strQuery.append(" 	                       (orguserpos7_.post_id IN	 ");
		strQuery.append(" 	                       (SELECT hrpaypaybi151_.post_id	 ");
		strQuery.append(" 	                           FROM HR_PAY_PAYBILL hrpaypaybi151_	 ");
		strQuery.append(" 	                          WHERE hrpaypaybi151_.ID = hrpaypaysl0_.PAYBILL_ID)) AND	 ");
		strQuery.append(" 	                       hrpaypaybi146_.post_id = orgpostdet8_.post_id AND	 ");
		strQuery.append(" 	                       orgempmst6_.lang_id = 1 AND orgpostdet8_.lang_id = 1 AND ");
		strQuery.append(" 	                       orgdesigna9_.lang_id = 1 AND cmnlocatio10_.lang_id = 1 AND	 ");
		strQuery.append(" 	                       orgpostdet8_.loc_id = "+deptId+") a LEFT JOIN (SELECT hrloanempd18_.emp_id,	 ");
		strQuery.append(" 	                                                                         MAX(hrloanempd18_.LOAN_ACCOUNT_NO) LOAN_ACCOUNT_NO,	 ");
		strQuery.append(" 	                                                                         hrloanempd18_.loan_type_id loan_type_id,	 ");
		strQuery.append(" 	                                                                         MAX(hrpaypaybi19_.RECOVERED_AMT) RECOVERED_AMT,	 ");
		strQuery.append(" 	                                                                         MAX(hrpaypaybi19_.RECOVERED_INST) RECOVERED_INST,	 ");
		strQuery.append(" 	                                                                         hrpaypaybi19_.paybill_id paybill_id,	 ");
		strQuery.append(" 	                                                                         hrpaypaybi19_.recovery_type recovery_type,	 ");
		strQuery.append(" 	                                                                         MAX(hrpaypaybi19_.total_inst) total_inst,	 ");
		strQuery.append(" 	                                                                         MAX(hrpaypaybi19_.total_amt) total_amt	 ");
		strQuery.append(" 	                                                                    FROM HR_LOAN_EMP_DTLS         hrloanempd18_,	 ");
		strQuery.append(" 	                                                                         HR_PAY_PAYBILL_LOAN_DTLS hrpaypaybi19_	 ");
		strQuery.append(" 	                                                                   WHERE hrpaypaybi19_.LOAN_TYPE_ID =	 ");
		strQuery.append(" 	                                                                         hrloanempd18_.LOAN_TYPE_ID AND	 ");
		strQuery.append(" 	                                                                         hrloanempd18_.EMP_LOAN_ID =	 ");
		strQuery.append(" 	                                                                         hrpaypaybi19_.EMP_LOAN_ID	 ");
		strQuery.append(" 	                                                                   GROUP BY hrloanempd18_.emp_id,	 ");
		strQuery.append(" 	                                                                            hrloanempd18_.loan_type_id,	 ");
		strQuery.append(" 	                                                                            hrpaypaybi19_.paybill_id,	 ");
		strQuery.append(" 	                                                                            hrpaypaybi19_.recovery_type) b ON a.emp_id = b.emp_id LEFT JOIN trn_bill_register c ON c.bill_cntrl_no = a.bill_no LEFT JOIN hr_eis_proof_dtl d ON d.user_id = a.user_id	 ");
		strQuery.append(" 	         GROUP BY a.EMP_ID,	 ");
		strQuery.append(" 	                  a.USER_ID,	 ");
		strQuery.append(" 	                  a.PSR_NO,	 ");
		strQuery.append(" 	                  a.PAYBILL_ID,	 ");
		strQuery.append(" 	                  a.QUATER_NO,	 ");
		strQuery.append(" 	                  b.loan_type_id,	 ");
		strQuery.append(" 	                  b.paybill_id,a.locationid,	 ");
		strQuery.append(" 	                  b.recovery_type) main	 ");
		strQuery.append(" 	 GROUP BY main.col_2_0_	 order by col_122_0_ ");


		Query query = hibSession.createSQLQuery(strQuery.toString());     
			  //query.setParameter("startMonthDate", startMonthDate);
        paySlipList = query.list();
        
		return paySlipList;
		}
	//  Code shown below  is only for the alternative backup so will never executed in this DAO.
	//  Only above code will execute coz it takes lesser time to execute and is effective
	//  Added by Hemant @ 31 july 2009 (Emp Id  : 307727 )
		
		else
		{			
		strQuery.append(" select max(hrpaypaysl0_.paySlipId) as col_0_0_, ");
        strQuery.append(" max(hrpaypaysl0_.hrPayPaybill.id) as col_1_0_, ");
        strQuery.append(" max(hrpaypaysl0_.hrEisEmpMst.empId) as col_2_0_, ");
        strQuery.append(" max(hrpaypaysl0_.month) as col_3_0_, ");
        strQuery.append(" max(hrpaypaysl0_.year) as col_4_0_, ");
        strQuery.append(" max(hrpaypaysl0_.billNo) as col_5_0_, ");
        strQuery.append(" (select hrquaterty14_.quaType from HrQuaterTypeMst hrquaterty14_ where hrquaterty14_.quaId = hrpaypaysl0_.hrQuaterTypeMst.quaId) as col_6_0_, ");
        strQuery.append(" max(hrpaypaysl0_.incrementAmt) as col_7_0_, ");
        strQuery.append(" max(hrpaypaysl0_.itAccNo) as col_8_0_, ");
        strQuery.append(" max(emp.empGPFnumber) as col_9_0_, ");
        strQuery.append(" max(hrpaypaysl0_.tokenNo) as col_10_0_, ");
        strQuery.append(" max(hrpaypaysl0_.billAmt) as col_11_0_, ");
        strQuery.append(" max(hrpaypaysl0_.budgetHead) as col_12_0_, ");
        strQuery.append(" max(hrpaypaysl0_.paySlipDate) as col_13_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.basicPay) as col_14_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.leavePay) as col_15_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.splPay) as col_16_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.perPay) as col_17_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.da) as col_18_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.cla) as col_19_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.hra) as col_20_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.wa) as col_21_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.ma) as col_22_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.ir) as col_23_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.trAllow) as col_24_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.ITax) as col_25_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.hrr) as col_26_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.PTax) as col_27_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.gis1979) as col_28_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.gpf) as col_29_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.gpfAdv) as col_30_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.fesAdv) as col_31_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.foodAdv) as col_32_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.fanAdv) as col_33_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.hba) as col_34_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.vehAdv) as col_35_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.miscRec) as col_36_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.dp) as col_37_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.gpf) as col_38_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.jeepRent) as col_39_0_, ");
        strQuery.append(" sum(hrpaypaysl0_.netTotal) as col_40_0_ ,");
        strQuery.append(" max(emp.empFname) as col_41_0_, ");
        strQuery.append(" max(emp.empMname) as col_42_0_, ");
        strQuery.append(" max(emp.empLname) as col_43_0_, ");
        strQuery.append(" max(emp.empDob) as col_44_0_, ");
        strQuery.append(" max(emp.empDoj) as col_45_0_, ");
        strQuery.append(" max(o.incrementDate) as  col_46_0_, ");

        strQuery.append(" max(scale.scaleStartAmt) as  col_47_0_,  ");
        strQuery.append(" max(scale.scaleIncrAmt) as  col_48_0_, ");
        strQuery.append(" max(scale.scaleEndAmt) as  col_49_0_, ");
        strQuery.append(" max(scale.scaleHigherIncrAmt) as  col_50_0_ , ");
        strQuery.append(" max(scale.scaleHigherEndAmt) as col_51_0_ ,");
        
        //strQuery.append(" max(dsgn.dsgnName) as col_52_0_ , ");
        strQuery.append(" case when max(dsgnMst.dsgnName) is null then max(dsgn.dsgnName) else max(dsgnMst.dsgnName) end , ");
        
        
        strQuery.append(" max(loc.locName) as col_53_0_,  ");
        
        
        strQuery.append(" sum(non.societyOld),");
        strQuery.append("  sum(non.societyNew),");
        strQuery.append("  sum(non.karamChariBank),");
        strQuery.append("   sum(non.nagrikBank),");
        strQuery.append("  sum(non.lic),");
        strQuery.append("  sum(non.postOfficeRecurringDeposit),");
        
        
        /*strQuery.append(" (select sum(ng.non_gov_deduc_amount) from hr_pay_non_gov_deduction ng where ng.emp_id = hrpaypaysl0_.EMP_ID and ng.non_gov_deduc_type = "+oldSociety+" ");
        strQuery.append(" and ng.non_gov_deduc_efft_start_dt <= '"+startMonthDate+"' and ng.non_gov_deduc_efft_end_dt >= '"+endMonthDate+"') as col_54_0_, ");
        strQuery.append(" (select sum(ng.non_gov_deduc_amount) from hr_pay_non_gov_deduction ng where ng.emp_id = hrpaypaysl0_.EMP_ID and ng.non_gov_deduc_type = "+newSociety+" ");
        strQuery.append(" and ng.non_gov_deduc_efft_start_dt <= '"+startMonthDate+"' and ng.non_gov_deduc_efft_end_dt >= '"+endMonthDate+"') as col_55_0_, ");
        strQuery.append(" (select sum(ng.non_gov_deduc_amount) from hr_pay_non_gov_deduction ng where ng.emp_id = hrpaypaysl0_.EMP_ID and ng.non_gov_deduc_type = "+karmBank+" ");
        strQuery.append(" and ng.non_gov_deduc_efft_start_dt <= '"+startMonthDate+"' and ng.non_gov_deduc_efft_end_dt >= '"+endMonthDate+"') as col_56_0_, ");
        strQuery.append(" (select sum(ng.non_gov_deduc_amount) from hr_pay_non_gov_deduction ng where ng.emp_id = hrpaypaysl0_.EMP_ID and ng.non_gov_deduc_type = "+nagBank+" ");
        strQuery.append(" and ng.non_gov_deduc_efft_start_dt <= '"+startMonthDate+"' and ng.non_gov_deduc_efft_end_dt >= '"+endMonthDate+"') as col_57_0_, ");
        strQuery.append(" (select sum(ng.non_gov_deduc_amount) from hr_pay_non_gov_deduction ng where ng.emp_id = hrpaypaysl0_.EMP_ID and ng.non_gov_deduc_type = "+licDed+" ");
        strQuery.append(" and ng.non_gov_deduc_efft_start_dt <='"+startMonthDate+"' and ng.non_gov_deduc_efft_end_dt >= '"+endMonthDate+"') as col_58_0_, ");
        strQuery.append(" (select sum(ng.non_gov_deduc_amount) from hr_pay_non_gov_deduction ng where ng.emp_id = hrpaypaysl0_.EMP_ID and ng.non_gov_deduc_type = "+POR+" ");
        strQuery.append(" and ng.non_gov_deduc_efft_start_dt <= '"+startMonthDate+"' and ng.non_gov_deduc_efft_end_dt >= '"+endMonthDate+"') as col_59_0_,");
        */
        strQuery.append(" (select bank.bankAcctNo from HrEisBankDtls bank where bank.hrEisEmpMst.empId =  hrpaypaysl0_.hrEisEmpMst.empId )  as col_60_0_ ,");
        strQuery.append(" (select Concat(Concat(branch.branchName,', '),bankmst.bankName)  from HrEisBankDtls bank,HrEisBranchMst branch, HrEisBankMst bankmst where bank.hrEisEmpMst.empId =  hrpaypaysl0_.hrEisEmpMst.empId  and branch.branchId = bank.hrEisBranchMst.branchId and bank.hrEisBankMst.bankId=bankmst.bankId) as col_61_0_ ,");
        
        strQuery.append(" max(hrpaypaysl0_.jeepRent) as col_62_0_, ");
        strQuery.append(" max(hrpaypaysl0_.mcaInt) as col_63_0_, ");
        strQuery.append(" max(hrpaypaysl0_.hbaInt) as col_64_0_, ");
        strQuery.append(" max(hrpaypaysl0_.fanInt) as col_65_0_, ");
        strQuery.append(" max(hrpaypaysl0_.ocaCycleAdv) as col_66_0_, ");
        strQuery.append(" max(hrpaypaysl0_.ocaCycleInt) as col_67_0_, ");
        strQuery.append(" max(hrpaypaysl0_.gis1979) as col_68_0_, ");
        strQuery.append(" max(hrpaypaysl0_.gisIf) as col_69_0_, ");
        strQuery.append(" max(hrpaypaysl0_.gisSf) as col_70_0_, ");
        
        /*71*/strQuery.append(" (select max(l.loanAccountNo) from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+")and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id )    as col_71_0_, ");
        /*72*/strQuery.append(" (select max(l.loanAccountNo) from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+") and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id ) as col_72_0_,");

        /*73*/strQuery.append(" (select prin.recoveredAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and  l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+gpfAdvId+")  and prin.recoveryType in ("+principle+") ");
        		strQuery.append(" and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id   ) as col_73_0_, "); 
        		/* and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*74*/strQuery.append(" (select prin.recoveredInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+gpfAdvId+") and prin.recoveryType in ("+principle+") ");
        	strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id ) as col_74_0_, ");
        	/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*75*/strQuery.append(" (select prin.totalInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+gpfAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_75_0_, ");
        		/*   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*76*/ strQuery.append(" (select prin.totalAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+gpfAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_76_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/    
        

        /*77*/strQuery.append(" (select prin.recoveredAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+festAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_77_0_, ");
        		/* and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*78*/strQuery.append(" (select prin.recoveredInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+festAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append(" and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_78_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*79*/strQuery.append(" (select prin.totalInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+festAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_79_0_, ");
        		/*   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*80*/strQuery.append(" (select prin.totalAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+festAdvId+") and prin.recoveryType in ("+principle+") ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id ) as col_80_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/
        
        /*strQuery.append(" (select prin.total_recoveredAmt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+festAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1  where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_77_0_, ");
        strQuery.append(" (select prin.total_recoveredInst from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+festAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append(" and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_78_0_, ");
        strQuery.append(" (select l.loan_prin_inst_no from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+festAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append(" and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_79_0_, ");
        strQuery.append(" (select l.loan_prin_amt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+festAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_80_0_, ");*/
        

        /*81*/strQuery.append(" (select prin.recoveredAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+foodAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id   ) as col_81_0_, "); 
        		/* and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*82*/strQuery.append(" (select prin.recoveredInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+foodAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_82_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*83*/strQuery.append(" (select prin.totalInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+foodAdvId+") and prin.recoveryType in ("+principle+") ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_83_0_, ");
        		/*   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*84*/strQuery.append(" (select prin.totalAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+foodAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_84_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/
        
        
        /*strQuery.append(" (select prin.total_recoveredAmt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+foodAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_81_0_, ");
        strQuery.append(" (select prin.total_recoveredInst from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+foodAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append(" and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_82_0_, ");
        strQuery.append(" (select l.loan_prin_inst_no from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+foodAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append(" and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_83_0_, ");
        strQuery.append(" (select l.loan_prin_amt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+foodAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append(" and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_84_0_, ");*/
        
        

        /*85*/strQuery.append(" (select prin.recoveredAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+")  and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_85_0_, "); 
        		/* and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*86*/strQuery.append(" (select prin.recoveredInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_86_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*87*/ strQuery.append(" (select prin.totalInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+") and prin.recoveryType in ("+principle+")   ");
        		strQuery.append(" and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_87_0_, ");
        		/*   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*88*/strQuery.append(" (select prin.totalAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_88_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/
        
        
        
        /*strQuery.append(" (select prin.total_recoveredAmt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_85_0_, ");
        strQuery.append(" (select prin.total_recoveredInst from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_86_0_, ");
        strQuery.append(" (select l.loan_prin_inst_no from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_87_0_, ");
        strQuery.append(" (select l.loan_prin_amt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_88_0_, ");*/
        
        

        /*89*/strQuery.append(" (select prin.recoveredAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+")  and prin.recoveryType in ("+principle+") ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_89_0_, "); 
        		/* and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*90*/ strQuery.append(" (select prin.recoveredInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+") and prin.recoveryType in ("+principle+") ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_90_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*91*/strQuery.append(" (select prin.totalInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id   ) as col_91_0_, ");
        		/*   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*92*/strQuery.append(" (select prin.totalAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+") and prin.recoveryType in ("+principle+") ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_92_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/
        
        
        
        
        /*strQuery.append(" (select prin.total_recoveredAmt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_89_0_, ");
        strQuery.append(" (select prin.total_recoveredInst from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_90_0_, ");
        strQuery.append(" (select l.loan_prin_inst_no from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_91_0_, ");
        strQuery.append(" (select l.loan_prin_amt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_92_0_, ");*/
        
        
        /*93*/strQuery.append(" (select prin.recoveredAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id   ) as col_93_0_, ");
        		/* and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*94*/strQuery.append(" (select prin.recoveredInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_94_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*95*/strQuery.append(" (select prin.totalInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id   ) as col_95_0_, ");
        		/*   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*96*/strQuery.append(" (select prin.totalAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_96_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/
        
        
        
        
       /* strQuery.append(" (select prin.total_recoveredAmt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("   and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_93_0_, ");
        strQuery.append(" (select prin.total_recoveredInst from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_94_0_, ");
        strQuery.append(" (select l.loan_prin_inst_no from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_95_0_, ");
        strQuery.append(" (select l.loan_prin_amt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("   and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_96_0_, ");*/
        
        
        /*97*/strQuery.append(" (select prin.recoveredAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+") and prin.recoveryType in ("+principle+")   ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id   ) as col_97_0_, "); 
        		/* and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*98*/strQuery.append(" (select prin.recoveredInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("    and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_98_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*99*/strQuery.append(" (select prin.totalInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+") and prin.recoveryType in ("+principle+")  ");
        		strQuery.append("    and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id   ) as col_99_0_, ");
        		/*   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*100*/strQuery.append(" (select prin.totalAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+") and prin.recoveryType in ("+principle+")   ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_100_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/
        
        
        
        /*strQuery.append(" (select prin.total_recoveredAmt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in  ("+carLoanId+","+scooterLoanId+","+mopedLoanId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("   and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_200_0_, ");
        strQuery.append(" (select prin.total_recoveredInst from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in  ("+carLoanId+","+scooterLoanId+","+mopedLoanId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_97_0_, ");
        strQuery.append(" (select l.loan_prin_inst_no from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in  ("+carLoanId+","+scooterLoanId+","+mopedLoanId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("   and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_98_0_, ");
        strQuery.append(" (select l.loan_prin_amt from HrLoanEmpDtls l,hr_loan_emp_prin_recover_hst prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in  ("+carLoanId+","+scooterLoanId+","+mopedLoanId+")   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("  and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')    ) as col_99_0_, ");*/
       
        		strQuery.append(" 0,0,0,0, ");
        		
        //need to change Fan Advance have no intrest 
        		/*101*///strQuery.append(" (select int.totalRecoveredInt from HrLoanEmpDtls l,HrLoanEmpIntRecoverHst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.hrLoanEmpDtls.empLoanId = l.empLoanId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+")  and int.updatedDate>='"+startMonthDate+"' and int.updatedDate < = '"+endMonthDate+"'  ");
        		//strQuery.append("   and int.trnCounter = (select max(int1.trnCounter) from HrLoanEmpIntRecoverHst int1 where int.hrLoanEmpDtls.empLoanId = int1.hrLoanEmpDtls.empLoanId and int1.updatedDate >='"+startMonthDate+"' and int1.updatedDate <='"+endMonthDate+"')   ) as col_100_0_, ");
       
        		/*102*///strQuery.append(" (select int.totalRecoveredIntInst from HrLoanEmpDtls l,HrLoanEmpIntRecoverHst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.hrLoanEmpDtls.empLoanId = l.empLoanId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+")  and int.updatedDate>='"+startMonthDate+"' and int.updatedDate < = '"+endMonthDate+"'   ");
        		//strQuery.append("   and int.trnCounter = (select max(int1.trnCounter) from HrLoanEmpIntRecoverHst int1 where int.hrLoanEmpDtls.empLoanId = int1.hrLoanEmpDtls.empLoanId and int1.updatedDate >='"+startMonthDate+"' and int1.updatedDate <='"+endMonthDate+"')   ) as col_101_0_, ");
        
        		/*103*///strQuery.append(" (select l.loan_int_inst_no from HrLoanEmpDtls l,HrLoanEmpIntRecoverHst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.hrLoanEmpDtls.empLoanId = l.empLoanId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+")  and int.updatedDate>='"+startMonthDate+"' and int.updatedDate < = '"+endMonthDate+"'   ");
        		//strQuery.append("   and int.trnCounter = (select max(int1.trnCounter) from HrLoanEmpIntRecoverHst int1 where int.hrLoanEmpDtls.empLoanId = int1.hrLoanEmpDtls.empLoanId and int1.updatedDate >='"+startMonthDate+"' and int1.updatedDate <='"+endMonthDate+"')   ) as col_102_0_, ");
       
        		/*104*///strQuery.append(" (select l.loan_interest_amt from HrLoanEmpDtls l,HrLoanEmpIntRecoverHst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.hrLoanEmpDtls.empLoanId = l.empLoanId  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+fanAdvId+")  and int.updatedDate>='"+startMonthDate+"' and int.updatedDate < = '"+endMonthDate+"'   ");
        		//strQuery.append("   and int.trnCounter = (select max(int1.trnCounter) from HrLoanEmpIntRecoverHst int1 where int.hrLoanEmpDtls.empLoanId = int1.hrLoanEmpDtls.empLoanId and int1.updatedDate >='"+startMonthDate+"' and int1.updatedDate <='"+endMonthDate+"')   ) as col_103_0_, ");
        //need to change Fan Advance have no intrest
        
        

        /*105*/ strQuery.append(" (select prin.recoveredAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+")  and prin.recoveryType in ("+intrest+")  ");
        		strQuery.append("    and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_104_0_, "); 
        		/* and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*106*/strQuery.append(" (select prin.recoveredInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+") and prin.recoveryType in ("+intrest+")   ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_105_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*107*/strQuery.append(" (select prin.totalInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+") and prin.recoveryType in ("+intrest+")" +
        		"  and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_106_0_, ");
        	/*   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*108*/strQuery.append(" (select prin.totalAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+") and prin.recoveryType in ("+intrest+")   ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_107_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/
        
        
        
        
        
        
        /*strQuery.append(" (select int.total_recovered_int from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("  and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_104_0_, ");
        strQuery.append(" (select int.total_recovered_int_inst from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("  and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_105_0_, ");
        strQuery.append(" (select l.loan_int_inst_no from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("  and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_106_0_, ");
        strQuery.append(" (select l.loan_interest_amt from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+ocaAdvId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("  and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_107_0_, ");*/

        

        /*109*/strQuery.append(" (select prin.recoveredAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+") and prin.recoveryType in ("+intrest+")   ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id   ) as col_108_0_, "); 
        		/* and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*110*/strQuery.append(" (select prin.recoveredInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+") and prin.recoveryType in ("+intrest+")   ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_109_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*111*/strQuery.append(" (select prin.totalInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+") and prin.recoveryType in ("+intrest+")   ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id   ) as col_95_110_, ");
        		/*   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*112*/strQuery.append(" (select prin.totalAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+") and prin.recoveryType in ("+intrest+")   ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id  ) as col_96_111_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/
        
        
        
        
        /*strQuery.append(" (select int.total_recovered_int from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("  and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_108_0_, ");
        strQuery.append(" (select int.total_recovered_int_inst from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("   and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_109_0_, ");
        strQuery.append(" (select l.loan_int_inst_no from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("   and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_110_0_, ");
        strQuery.append(" (select l.loan_interest_amt from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+hbaLoanId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("   and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_111_0_, ");*/


        /*113*/strQuery.append(" (select prin.recoveredAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId  and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+") and prin.recoveryType in ("+intrest+")   ");
        		strQuery.append("    and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id) as col_112_0_, "); 
        		/* and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*114*/strQuery.append(" (select prin.recoveredInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+") and prin.recoveryType in ("+intrest+")   ");
        		strQuery.append("    and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id) as col_113_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*115*/strQuery.append(" (select prin.totalInst from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+") and prin.recoveryType in ("+intrest+")  ");
        		strQuery.append("     and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id) as col_114_0_, ");
        		/*   and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/   
        /*116*/strQuery.append(" (select prin.totalAmt from HrLoanEmpDtls l,HrPayPaybillLoanDtls prin where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and prin.hrLoanAdvMst.loanAdvId = l.hrLoanAdvMst.loanAdvId and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+") and prin.recoveryType in ("+intrest+")   ");
        		strQuery.append("   and prin.paybillId.id = hrpaypaysl0_.hrPayPaybill.id) as col_115_0_, ");
        		/*  and prin.updated_date>='"+startMonthDate+"' and prin.updated_date < = '"+endMonthDate+"' and prin.trn_counter = (select max(prin1.trn_counter) from hr_loan_emp_prin_recover_hst prin1 where prin.emp_loan_id = prin1.emp_loan_id and prin1.updated_date >='"+startMonthDate+"' and prin1.updated_date <='"+endMonthDate+"')*/
        
        
        
        /*strQuery.append(" (select int.total_recovered_int from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("   and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_112_0_, ");
        strQuery.append(" (select int.total_recovered_int_inst from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+") and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("   and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_113_0_, ");
        strQuery.append(" (select l.loan_int_inst_no from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'   ");
        		strQuery.append("   and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_114_0_, ");
        strQuery.append(" (select l.loan_interest_amt from HrLoanEmpDtls l,hr_loan_emp_int_recover_hst int where l.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and int.emp_loan_id = l.emp_loan_id  and l.loanActivateFlag = 1 and l.empLoanId = prin.hrLoanEmpDtls.empLoanId and l.hrLoanAdvMst.loanAdvId in ("+carLoanId+","+scooterLoanId+","+mopedLoanId+")  and int.updated_date>='"+startMonthDate+"' and int.updated_date < = '"+endMonthDate+"'  ");
        		strQuery.append("   and int.trn_counter = (select max(int1.trn_counter) from hr_loan_emp_int_recover_hst int1 where int.emp_loan_id = int1.emp_loan_id and int1.updated_date >='"+startMonthDate+"' and int1.updated_date <='"+endMonthDate+"')   ) as col_115_0_,");*/
        
        
        /*117*/ strQuery.append(" (select max(t.tokenNum) from TrnBillRegister  t where t.billCntrlNo = hrpaypaysl0_.billNo ), ");
        /*118*/strQuery.append(" (select max(t.updatedDate) from TrnBillRegister  t where t.billCntrlNo = hrpaypaysl0_.billNo), ");
        /*119*/strQuery.append(" (select max(t.billNetAmount)from TrnBillRegister t where t.billCntrlNo = hrpaypaysl0_.billNo), ");
       	// Added By Urvin Shah.
        /*120*/strQuery.append(" (select max(pr.proofNum) from HrEisProofDtl pr where pr.orgPostMstByUserId.userId=emp.orgUserMst.userId) ");
//      Added By Urvin Shah
        /*121*/strQuery.append(" ,max(hrpaypaysl0_.payRecovery) as col_121_0_ "); // Column 121.
       	//Added By Urvin Shah
       		/*122*/strQuery.append(" ,hrpaypaysl0_.hrPayPaybill.psrNo as col_122_0_ "); // Column 122.
       	//End
        //by manoj for salutation in payslip
       	/*123*/strQuery.append(" ,max(emp.empPrefix ) as col_123_0_ ");
       	
       	//end by manoj for salutation in payslip
        //by manoj for  bonus
       	/*124*/strQuery.append(" ,max(hrpaypaysl0_.bonus) as col_124_0_ , "); // Column 124.
       	
        strQuery.append(" max(scale.scaleGradePay) as col_125_0_, ");//coulmn 125 For Grade Pay
        strQuery.append(" max(hrpaypaysl0_.DAGPF) as col_126_0_, ");//coulmn 125 For Grade Pay
        /*127*/ strQuery.append("  sum(non.underSecGAD) as col_127_0,");//For  Non-Government type - "US_GAD"
        /*128*/strQuery.append("(select hrpaypaysl0_.hrQuaterTypeMst.quaType ||',  '|| cmnAddressMst.street ||', '|| cmnAddressMst.area  from CmnAddressMst cmnAddressMst, HrEisQtrMst hrEisQtrMst where cmnAddressMst.addressId=hrEisQtrMst.cmnAddressMst.addressId and hrEisQtrMst.orgUserMstByAllocatedTo.userId=emp.orgUserMst.userId and hrEisQtrMst.allocationStartDate <= '"+endDate+"' and (hrEisQtrMst.allocationEndDate = null or hrEisQtrMst.allocationEndDate > '"+startDate+"' )) as col_128_0, ");
    	
        strQuery.append(" max(pd.cmnLocationMst.locId)	as col_129_0_, ");
        
        strQuery.append(" max(hrpaypaysl0_.hrEisEmpMst.email) 	as col_130_0_ ");
        
       	strQuery.append(" from HrPayPayslip   hrpaypaysl0_ ,HrPayPayslipNonGovt non , ");
       	strQuery.append(" HrEisOtherDtlsHst o left outer join  o.hrEisSgdMpg sgd  left outer join sgd.hrEisGdMpg gd left outer join sgd.hrEisScaleMst scale ");
       	strQuery.append(" left outer join  gd.orgDesignationMst as dsgnMst , ");
       	strQuery.append(" OrgEmpMst emp, OrgUserpostRlt up, OrgPostDetailsRlt pd, OrgDesignationMst dsgn, CmnLocationMst loc, ");
        
        //strQuery.append(" HrPayOrderHeadMpg oh, PaybillHeadMpg ph,");
       	strQuery.append(" HrPayOrderHeadMpg oh ,");
        strQuery.append(" HrPayOrderHeadPostMpg ohp ");
        // Updated By Urvin
        //strQuery.append(" ,HrPayPsrPostMpg         post ");
        //
       /* if(billNo!=null&&!billNo.equals("")&&!billNo.equals("-1"))
        {
        	strQuery.append(" ,hr_pay_post_psr_mpg         post ");
        }*/
        
        strQuery.append(" where o.id.trnCounter = hrpaypaysl0_.hrPayPaybill.otherTrnCntr and  hrpaypaysl0_.month = " + month + " and ");
        strQuery.append("  hrpaypaysl0_.hrPayPaybill.id = non.paybillID.id and ");
        //strQuery.append(" ph.hrPayPaybill=hrpaypaysl0_.hrPayPaybill.paybillGrpId  and ph.billNo.billHeadId="+billNo +" and ");
        strQuery.append(" hrpaypaysl0_.hrPayPaybill.paybillGrpId in (select distinct(ph.hrPayPaybill) from PaybillHeadMpg ph where ph.month="+month +" and ph.year="+year+" and ph.approveFlag=1 " );
        if(billNo!=null&& !billNo.equals(""))
        			strQuery.append(" and ph.billNo.billHeadId="+billNo+" ");
        strQuery.append(" 	) ");
        strQuery.append(" and o.hrEisEmpMst.empId = hrpaypaysl0_.hrEisEmpMst.empId and hrpaypaysl0_.year = " + year + " ");
        strQuery.append("  and emp.empId = hrpaypaysl0_.hrEisEmpMst.orgEmpMst.empId ");
        strQuery.append(" and up.orgUserMst.userId = emp.orgUserMst.userId  and up.orgPostMstByPostId.postId = pd.orgPostMst.postId ");
        strQuery.append(" and pd.cmnLocationMst.locId = loc.locId  and pd.orgDesignationMst.dsgnId = dsgn.dsgnId ");
        
        //strQuery.append(" and pd.loc_id = loc.loc_id  and pd.dsgn_id = dsgn.dsgn_id ");
        // Commented By Urvin Shah
        //if(psrId != null && psrId!="")
        strQuery.append(" and pd.orgPostMst.postId =hrpaypaysl0_.hrPayPaybill.orgPostMst.postId ");
        strQuery.append(" and ((up.startDate <= '"+endDate+"' and  up.endDate  >= '"+startDate+"' ) or up.endDate is null ) ");
        
        strQuery.append(" and oh.orderHeadId = ohp.orderHeadId ");
        strQuery.append(" and ohp.postId = up.orgPostMstByPostId.postId and up.orgPostMstByPostId.postId in (select b.orgPostMst.postId  from HrPayPaybill b where b.id = hrpaypaysl0_.hrPayPaybill.id) ");


        
        
        if(billNo!=null&&!billNo.equals("")&&!billNo.equals("-1"))
        {
        	
        /*StringTokenizer st1=new StringTokenizer(billNo,"~");	
        String subheadCode="";
        String classIds="";
        String desgnIds="";
        String postType="";*/
       
        /*int l=0;
        while(st1.hasMoreTokens())
        {
        	if(l==0)
        		subheadCode=st1.nextToken();
        	else if(l==1)
        		classIds=st1.nextToken();
        	else if(l==2)
        		desgnIds=st1.nextToken();
        	else if(l==3)
        		postType= st1.nextToken();
        	
         l++;
        } */
        
       /*	strQuery.append(" and oh.subhead_id = "+subheadCode+" ");
       	strQuery.append(" and emp.grade_id in ("+classIds+") ");
       	strQuery.append("  and pd.dsgn_id  in  ("+desgnIds+") ");
       	
       	strQuery.append("  and  post.post_type_lookup_id in  ("+postType+") and post.post_id = pd.post_id ");*/
       	
       	
        strQuery.append(" and hrpaypaysl0_.hrPayPaybill.orgPostMst.postId = pd.orgPostMst.postId  ");
       	
        }
        
        strQuery.append(" and emp.cmnLanguageMst.langId = 1 and pd.cmnLanguageMst.langId = 1 and dsgn.cmnLanguageMst.langId = 1 and  loc.cmnLanguageMst.langId =1 ");
        
        if(deptId>0)
        	strQuery.append(" and pd.cmnLocationMst.locId =  " + deptId);	
        if(empId>0)
            strQuery.append("  and hrpaypaysl0_.hrEisEmpMst.orgEmpMst.empId = " + empId);
        //if(psrId != null && psrId!="")
        //{
        strQuery.append(" group by hrpaypaysl0_.hrEisEmpMst.empId,hrpaypaysl0_.billNo,emp.orgUserMst.userId,hrpaypaysl0_.hrPayPaybill.psrNo,hrpaypaysl0_.hrPayPaybill.id,hrpaypaysl0_.hrQuaterTypeMst.quaId order by hrpaypaysl0_.hrPayPaybill.psrNo ");
        /*}
        else
        {
        	strQuery.append(" group by hrpaypaysl0_.hrEisEmpMst.empId,hrpaypaysl0_.billNo,emp.orgUserMst.userId,emp.empFname,emp.empMname,emp.empLname,hrpaypaysl0_.hrPayPaybill.id order by emp.empFname,emp.empMname,emp.empLname");
        }*/
        //System.out.println("the payslip query is for testing  "+strQuery.toString());
        
        
        Query query = hibSession.createQuery(strQuery.toString());
        
        //query.setParameter("endMonthDate", endMonthDate);
        //query.setParameter("startMonthDate", startMonthDate);
        
        
        paySlipList = query.list();
		
		return paySlipList;
	}
}
	public List getBillData(long finYrId,long locId) 
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
//			 Updated By Urvin
			//query.append("select s.subhead_code ,s.class_id,s.dsgn_id,s.bill_no,s.POST_TYPE,s.BILL_SUBHEAD_ID from hr_pay_bill_subhead_mpg s where fin_year_id="+finYrId+" and loc_id="+locId+" order by bill_no ");
			query.append("select s.subhead_code ,s.class_id,s.dsgn_id,s.bill_no,s.POST_TYPE,s.BILL_SUBHEAD_ID from hr_pay_bill_subhead_mpg s where loc_id="+locId+" order by bill_no ");
			// End.
			
			logger.info("Query for get getBillData is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
				
		return resList;
	}
	public List getBillNoData(long locId)
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
	
	 public List getSignaturebyMonthandLocId(long locId,int month,int year)
	 {
			Session hibSession = getSession();
			StringBuffer strQuery = new StringBuffer();
			
			Calendar cal = Calendar.getInstance();
			DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
		    SimpleDateFormat sdfObj = sbConf.GetDateFormat();
			
			if(month>0 && year>0)
			{	
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month-1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			}
			java.util.Date startMonthDate = cal.getTime();
			
			String startDate  = sdfObj.format(startMonthDate);
			
	        int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        
	        cal.set(Calendar.DAY_OF_MONTH, totalDays);
	        
	        java.util.Date endMonthDate = cal.getTime();

			String endDate  = sdfObj.format(endMonthDate);
			
			Query query;
			/*strQuery.append(" select sgn.emp_id, ");
			strQuery.append(" sgn.dsgn_id, ");
			strQuery.append(" d.dsgn_name, ");
			strQuery.append(" concat(concat(concat(o.emp_fname, concat(' ', o.emp_mname)),' '),o.emp_lname), ");
			strQuery.append(" l.loc_name ");
			strQuery.append("  from HR_PAY_SIGNATURE_DTLS sgn, ");
			strQuery.append(" hr_eis_emp_mst        e, ");
			strQuery.append(" org_emp_mst           o, ");
			strQuery.append(" org_designation_mst   d, ");
			strQuery.append(" cmn_location_mst      l ");
			strQuery.append(" where sgn.loc_id = "+locId+" and ");
			strQuery.append(" (sgn.start_date <= '"+endDate+"' and (sgn.end_date >= '"+startDate+"' or  sgn.end_date is null ))and ");
			strQuery.append("  d.dsgn_id = sgn.dsgn_id and ");
			strQuery.append(" sgn.emp_id = e.emp_id and e.emp_mpg_id = o.emp_id and o.lang_id = 1 and ");
			strQuery.append(" sgn.loc_id = l.loc_id ");
			*/
			
			strQuery.append(" select sgn.hrEisEmpMst.empId, ");
			strQuery.append(" sgn.orgDesignationMst.dsgnId, ");
			strQuery.append(" sgn.orgDesignationMst.dsgnName, ");
			strQuery.append(" concat(concat(concat(sgn.hrEisEmpMst.orgEmpMst.empFname, concat(' ',sgn.hrEisEmpMst.orgEmpMst.empMname)),' '),sgn.hrEisEmpMst.orgEmpMst.empLname), ");
			strQuery.append(" sgn.cmnLocationMst.locName ");
			strQuery.append("  from HrPaySignatureDtls sgn ");
			strQuery.append(" where sgn.cmnLocationMst.locId = "+locId+" and ");
			strQuery.append(" (sgn.startDate <= '"+endDate+"' and (sgn.endDate >= '"+startDate+"' or  sgn.endDate is null )) ");
			query = hibSession.createQuery(strQuery.toString());
			List resultList = query.list();

			return resultList;
		}
	 
	 
	 public List getClassFromBillNo(int month,int year,String billNo)
	 {
		 List  classList = null;
	     Session hibSession = getSession();
	     StringBuffer strQuery = new StringBuffer();
	     strQuery.append(" SELECT distinct grdmst.grade_id,grdmst.grade_name ");
	     strQuery.append(" FROM HR_PAY_PAYBILL  hrpaybill,org_emp_mst  o,org_post_details_rlt pd,org_designation_mst  dsgn,hr_eis_emp_mst  em,org_grade_mst grdmst");
	     strQuery.append(" WHERE grdmst.grade_id = o.grade_id and hrpaybill.EMP_ID = em.emp_id AND (hrpaybill.PAYBILL_GRP_ID IN (SELECT paybillhead.PAYBILL_ID FROM PAYBILL_HEAD_MPG paybillhead  WHERE paybillhead.PAYBILL_MONTH ="+month+" AND  paybillhead.PAYBILL_YEAR ="+year+" and paybillhead.approve_flag = "+rb.getString("approved")+"  ");
	     
	     if(billNo!=null && !billNo.trim().equals(""))
	     strQuery.append("  AND  paybillhead.bill_no ="+billNo+" ");
	     
	     strQuery.append(" )) AND");
	     strQuery.append(" o.emp_id = em.emp_mpg_id AND  pd.dsgn_id = dsgn.dsgn_id AND pd.post_id = hrpaybill.post_id AND o.lang_id = 1 AND dsgn.lang_id = 1");
	     Query query = hibSession.createSQLQuery(strQuery.toString());
		 classList = query.list();
	     return classList;
	 }
	 
	 public List getDesignationFromClassId(int month,int year,String classArray,String billNo)
	 {
		 List desgnList = null;
			Session hibSession = getSession();
		    StringBuffer sb = new StringBuffer();
		    sb.append(" SELECT distinct dsgn.dsgn_id,dsgn.dsgn_name,grdmst.grade_name ");
		    sb.append(" FROM HR_PAY_PAYBILL  hrpaybill,org_emp_mst  o,org_post_details_rlt pd,org_designation_mst  dsgn,hr_eis_emp_mst  em,org_grade_mst grdmst");
		    sb.append(" WHERE grdmst.grade_id = o.grade_id and hrpaybill.EMP_ID = em.emp_id AND (hrpaybill.PAYBILL_GRP_ID IN (SELECT paybillhead.PAYBILL_ID FROM PAYBILL_HEAD_MPG paybillhead  WHERE paybillhead.PAYBILL_MONTH ="+month+" AND  paybillhead.PAYBILL_YEAR ="+year+"   and paybillhead.approve_flag = "+rb.getString("approved")+"  ");
   	        
		    if(billNo!=null && !billNo.trim().equals(""))
   	        	sb.append("  AND  paybillhead.bill_no ="+billNo+" ");
   	        
		    sb.append(" )) AND");
		    sb.append(" o.emp_id = em.emp_mpg_id AND  pd.dsgn_id = dsgn.dsgn_id AND pd.post_id = hrpaybill.post_id AND o.lang_id = 1 AND dsgn.lang_id = 1 " );
		    
		    if(classArray!=null && !classArray.trim().equals(""))
		    sb.append(" and grdmst.grade_id in ("+classArray+") " );
		    
		    sb.append(" ORDER BY dsgn.dsgn_name ");
		    Query query = hibSession.createSQLQuery(sb.toString());
		    desgnList = query.list();
		    logger.info("query is----"+query);
			return desgnList;
	 }
}




