package com.tcs.sgv.dcps.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class EmpSevenPCNewDaoImpl extends GenericDaoHibernateImpl{
	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;
	
	public EmpSevenPCNewDaoImpl(Class type, SessionFactory sessionFactory) {
		super(type);

		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}
	
	public List getSevenPCStateEmpDetails(String strEmpName, long locId, String flag) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT emp.EMP_NAME,emp.DDO_CODE,grade.GRADE_NAME,eis.SCALE_NAME,emp.PAY_IN_PAY_BAND,emp.GRADE_PAY,emp.BASIC_PAY,eis.SCALE_START_AMT,eis.SCALE_END_AMT,emp.SEVARTH_ID,emp.doj,emp.dob,emp.PAY_COMMISSION FROM MST_DCPS_EMP emp inner join ORG_DESIGNATION_MST des on des.DSGN_ID = emp.DESIGNATION ");	
		sb.append(" inner join MST_DCPS_CADRE cad on cad.CADRE_ID = emp.CADRE ");	
		sb.append(" inner join org_emp_mst org on org.EMP_ID = emp.ORG_EMP_MST_ID ");	
		sb.append(" inner join ORG_GRADE_MST grade on grade.GRADE_ID = org.GRADE_ID ");	
		sb.append(" inner join HR_EIS_SCALE_MST eis on eis.SCALE_ID = emp.PAYSCALE  ");	
		sb.append("inner join org_ddo_mst ddo on ddo.DDO_CODE = emp.DDO_CODE ");
		sb.append("inner join CMN_LOOKUP_MST cmn on cmn.lookup_id = emp.PAY_COMMISSION ");
		sb.append("inner join RLT_DCPS_PAYROLL_EMP payroll on payroll.dcps_emp_id = emp.dcps_emp_id ");
		
		
		if(flag.equals("Yes"))
		//sb.append(" where emp.SEVARTH_ID = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and payroll.GIS_APPLICABLE in (700217,700218) ");	
//		
//	    sb.append(" where emp.SEVARTH_ID = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and emp.GRADE_PAY in (6000,7000,8000,9000,10000) ");	
			sb.append(" where emp.SEVARTH_ID = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and emp.GRADE_PAY in (0,1300,1600,1800,1900,2000,2400,2800,4200,4300,4400,4600,4800,5400,5700,6000,7000,8000,9000,10000,12000) ");// Added By Tejashree//
			else
//		sb.append(" where emp.emp_name = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and emp.GRADE_PAY in (6000,7000,8000,9000,10000)  ");
				sb.append(" where emp.emp_name = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and emp.GRADE_PAY in (0,1300,1600,1800,1900,2000,2400,2800,4200,4300,4400,4600,4800,5400,5700,6000,7000,8000,9000,10000,12000)  ");// Added By Tejashree//
		//sb.append(" where emp.emp_name = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and payroll.GIS_APPLICABLE in (700217,700218) ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
	
		logger.info("sql query for getSevenPCEmpDetails is 1 "+sql1query.toString());
		logger.info("getSevenPCEmpDetails sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	
	//Deputation Employee List before 01/01/2016
	public List getSevenPCStateDeputationEmpDetails(String strEmpName, long locId, String flag,String sign,String dateDoj) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT emp.EMP_NAME,emp.DDO_CODE,grade.GRADE_NAME,eis.SCALE_NAME,emp.PAY_IN_PAY_BAND,emp.GRADE_PAY,emp.BASIC_PAY,eis.SCALE_START_AMT,eis.SCALE_END_AMT,emp.SEVARTH_ID,emp.doj,emp.dob,emp.PAY_COMMISSION FROM MST_DCPS_EMP emp inner join ORG_DESIGNATION_MST des on des.DSGN_ID = emp.DESIGNATION ");	
		sb.append(" inner join MST_DCPS_CADRE cad on cad.CADRE_ID = emp.CADRE ");	
		sb.append(" inner join org_emp_mst org on org.EMP_ID = emp.ORG_EMP_MST_ID ");	
		sb.append(" inner join ORG_GRADE_MST grade on grade.GRADE_ID = org.GRADE_ID ");	
		sb.append(" inner join HR_EIS_SCALE_MST eis on eis.SCALE_ID = emp.PAYSCALE  ");	
		sb.append("inner join org_ddo_mst ddo on ddo.DDO_CODE = emp.DDO_CODE ");
		sb.append("inner join CMN_LOOKUP_MST cmn on cmn.lookup_id = emp.PAY_COMMISSION ");
		sb.append("inner join RLT_DCPS_PAYROLL_EMP payroll on payroll.dcps_emp_id = emp.dcps_emp_id ");
		//pooja 16-11-2018
		if(flag.equals("Yes"))
		
			//sb.append(" where emp.SEVARTH_ID = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and payroll.GIS_APPLICABLE in (700217,700218)  and emp.DOJ "+sign+" '"+dateDoj+"'");	
			sb.append(" where emp.SEVARTH_ID = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and emp.DOJ "+sign+" '"+dateDoj+"'");	
	
		else
			//sb.append(" where emp.emp_name = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and payroll.GIS_APPLICABLE in (700217,700218) and emp.DOJ "+sign+" '"+dateDoj+"'");
		sb.append(" where emp.emp_name = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2)  and emp.DOJ "+sign+" '"+dateDoj+"'");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
	
		logger.error("sql query for getSevenPCStateDeputationEmpDetails is"+sql1query.toString());
		logger.error("getSevenPCStateDeputationEmpDetails sql1query.list()"+sql1query.list().size());
		
		return sql1query.list();
	}
	
	//Deputation Employee List after 01/01/2016
	public List getSevenPCStateDeputationMEmpDetails(String strEmpName, long locId, String flag) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		/*sb.append(" SELECT emp.emp_name, pay.GPAY,pay.PO,emp.SEVARTH_ID,pay.PAYBILL_MONTH,pay.PAYBILL_YEAR,emp.DDO_CODE,emp.SEVARTH_ID FROM  ");
		sb.append(" hr_pay_paybill pay   ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID    ");
		sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID  ");
		sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID    ");
		sb.append(" INNER JOIN MST_DCPS_BILL_GROUP bill on mpg.BILL_NO=bill.BILL_GROUP_ID   ");   
		sb.append(" inner join ORG_DESIGNATION_MST des on des.DSGN_ID = emp.DESIGNATION  ");
		sb.append(" inner join MST_DCPS_CADRE cad on cad.CADRE_ID = emp.CADRE  ");
		sb.append(" inner join org_emp_mst org on org.EMP_ID = emp.ORG_EMP_MST_ID  ");
		sb.append(" inner join ORG_GRADE_MST grade on grade.GRADE_ID = org.GRADE_ID  ");
		sb.append(" inner join HR_EIS_SCALE_MST eis on eis.SCALE_ID = emp.PAYSCALE   ");
		sb.append(" inner join org_ddo_mst ddo on ddo.DDO_CODE = emp.DDO_CODE  ");
		sb.append(" inner join CMN_LOOKUP_MST cmn on cmn.lookup_id = emp.PAY_COMMISSION  ");
		sb.append(" inner join RLT_DCPS_PAYROLL_EMP payroll on payroll.dcps_emp_id = emp.dcps_emp_id  ");
		sb.append(" where  cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and payroll.GIS_APPLICABLE in (700217)  ");
		sb.append(" and mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (2)  and emp.doj > '2016-01-01 00:00:00.0'  ");
		sb.append(" and pay.PAYBILL_YEAR <> 2016 and pay.PAYBILL_MONTH >= 1 and emp.SUPER_ANN_DATE >= sysdate and  ");*/
		
		sb.append(" SELECT emp.EMP_NAME,emp.SEVARTH_ID,emp.DDO_CODE,bill.PAYBILL_MONTH,bill.PAYBILL_YEAR FROM mst_dcps_emp emp   ");
		sb.append(" INNER JOIN HR_EIS_EMP_MST hr on emp.ORG_EMP_MST_ID=hr.EMP_MPG_ID   ");
		sb.append(" INNER JOIN HR_PAY_PAYBILL bill on hr.EMP_ID=bill.EMP_ID ");
		sb.append(" INNER JOIN PAYBILL_HEAD_MPG pay on bill.PAYBILL_GRP_ID=pay.PAYBILL_ID and pay.APPROVE_FLAG=1   ");
		if(flag.equals("Yes")){
		sb.append(" where emp.SEVARTH_ID='"+strEmpName+"' and emp.reg_status in (1,2) and emp.DOJ BETWEEN '2016-01-01' and '2018-12-31' and emp.PAY_COMMISSION=700016 and emp.SUPER_ANN_DATE >= sysdate   ");
		}else{
			sb.append(" where emp.EMP_NAME='"+strEmpName+"' and emp.reg_status in (1,2) and emp.DOJ BETWEEN '2016-01-01' and '2018-12-31' and emp.PAY_COMMISSION=700016 and emp.SUPER_ANN_DATE >= sysdate   ");
		}
		sb.append(" and bill.PAYBILL_YEAR=(SELECT MIN(bill.PAYBILL_YEAR) FROM mst_dcps_emp emp   ");
		sb.append(" INNER JOIN HR_EIS_EMP_MST hr on emp.ORG_EMP_MST_ID=hr.EMP_MPG_ID   ");
		sb.append(" INNER JOIN HR_PAY_PAYBILL bill on hr.EMP_ID=bill.EMP_ID");
		sb.append(" INNER JOIN PAYBILL_HEAD_MPG pay on bill.PAYBILL_GRP_ID=pay.PAYBILL_ID and pay.APPROVE_FLAG=1   ");
		if(flag.equals("Yes")){
		sb.append(" where emp.SEVARTH_ID='"+strEmpName+"') ");
		}else{
			sb.append(" where emp.EMP_NAME = '"+strEmpName+"') ");	
		}
		sb.append(" and bill.PAYBILL_MONTH=(SELECT MIN(bill.PAYBILL_MONTH) FROM mst_dcps_emp emp   ");
		sb.append(" INNER JOIN HR_EIS_EMP_MST hr on emp.ORG_EMP_MST_ID=hr.EMP_MPG_ID   ");
		sb.append(" INNER JOIN HR_PAY_PAYBILL bill on hr.EMP_ID=bill.EMP_ID ");
		sb.append(" INNER JOIN PAYBILL_HEAD_MPG pay on bill.PAYBILL_GRP_ID=pay.PAYBILL_ID and pay.APPROVE_FLAG=1   ");
		if(flag.equals("Yes")){
		sb.append(" where emp.SEVARTH_ID='"+strEmpName+"' and bill.PAYBILL_YEAR=(SELECT MIN(bill.PAYBILL_YEAR) FROM mst_dcps_emp emp   ");
		}else{
			sb.append(" where emp.EMP_NAME='"+strEmpName+"' and bill.PAYBILL_YEAR=(SELECT MIN(bill.PAYBILL_YEAR) FROM mst_dcps_emp emp   ");
		}
		sb.append(" INNER JOIN HR_EIS_EMP_MST hr on emp.ORG_EMP_MST_ID=hr.EMP_MPG_ID   ");
		sb.append(" INNER JOIN HR_PAY_PAYBILL bill on hr.EMP_ID=bill.EMP_ID ");
		sb.append(" INNER JOIN PAYBILL_HEAD_MPG pay on bill.PAYBILL_GRP_ID=pay.PAYBILL_ID and pay.APPROVE_FLAG=1   ");
		if(flag.equals("Yes")){
		sb.append(" where emp.SEVARTH_ID='"+strEmpName+"')) ");
		}else{
			
			sb.append(" where emp.EMP_NAME = '"+strEmpName+"')) ");
		}
		
		
		//	sb.append(" where emp.SEVARTH_ID = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and payroll.GIS_APPLICABLE in (700217)  and emp.DOJ < '2016-01-01'");	
	    //sb.append(" where emp.emp_name = '"+strEmpName+"' and  ddo.LOCATION_CODE = "+locId+" AND cmn.lookup_ID in(700016) AND emp.REG_STATUS in (1,2) and payroll.GIS_APPLICABLE in (700217) and emp.DOJ < '2016-01-01'");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
	
		logger.error("sql query for getSevenPCStateDeputationEmpDetails is"+sql1query.toString());
		logger.error("getSevenPCStateDeputationEmpDetails sql1query.list()"+sql1query.list().size());
		
		return sql1query.list();
	}
	
	
	public List getSevenPCEmpDetails(String strEmpName, long locId, String flag) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT pay.GPAY,pay.PO,emp.SEVARTH_ID from hr_pay_paybill pay ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID  ");
		sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID  ");
		sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID  ");
		sb.append(" INNER JOIN MST_DCPS_BILL_GROUP bill on mpg.BILL_NO=bill.BILL_GROUP_ID  ");
	
		//pooja 16-11-2018
		if(flag.equals("Yes"))
			sb.append(" where emp.SEVARTH_ID='"+strEmpName+"' and  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (1,2) and pay.PAYBILL_YEAR in(2016) and pay.PAYBILL_MONTH in (1) AND pay.GPAY in (1300,1400,1600,1700, 1800,1900,2000,2400,2500,2800,3000,3500,4100,4200,4300, 4400,4600 ,4800,5000,5400,5500, 5800,6600,6900,7600,7900,8700,8800,8900,1000,0,1650,2900,4100,4500,4900,5700,12000 )");
		
		else
				sb.append(" where emp.EMP_NAME='"+strEmpName+"' and  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (1,2) and pay.PAYBILL_YEAR in(2016) and pay.PAYBILL_MONTH in (1) AND pay.GPAY in (1300,1400,1600,1700, 1800,1900,2000,2400,2500,2800,3000,3500,4100,4200,4300, 4400,4600 ,4800,5000,5400,5500, 5800,6600,6900,7600,7900,8700,8800,8900,1000,0,1650,2900,4100,4500,4900,5700,12000 )");
				Query sql1query=hibSession.createSQLQuery(sb.toString());
		logger.error("sql query for getSevenPCEmpDetails new is"+sql1query.toString());
		logger.error("getSevenPCEmpDetails sql1query.list()"+sql1query.list().size());
		return sql1query.list();
	}
	
	public long getEmpDetailsList(String sevaarthId,String flag) {
				Session hibSession = getSession();
				Long gisAppl = 0l;
				StringBuffer sb = new StringBuffer();
				sb.append(" SELECT cmn.LOOKUP_ID FROM MST_DCPS_EMP mst,RLT_DCPS_PAYROLL_EMP rlt,CMN_LOOKUP_MST cmn ");
				if(flag.equals("Yes")){
				sb.append(" where mst.DCPS_EMP_ID = rlt.DCPS_EMP_ID and rlt.GIS_APPLICABLE = cmn.LOOKUP_ID and mst.SEVARTH_ID =  '"+sevaarthId+"'");	
				}else{
					sb.append(" where mst.DCPS_EMP_ID = rlt.DCPS_EMP_ID and rlt.GIS_APPLICABLE = cmn.LOOKUP_ID and mst.EMP_NAME =  '"+sevaarthId+"'");	
				}
				Query sql1query=hibSession.createSQLQuery(sb.toString());
				if(sql1query.uniqueResult()!=null){
					gisAppl = Long.parseLong(sql1query.uniqueResult().toString());
				}
				logger.error("sql query for getSevenPCEmpDetails new is"+sql1query.toString());
			
				return gisAppl;
			}
	public List getSevenPCEmpNewDetails(String empSevarthID, Long newBasicAccToCal,Long newBasicPerMatrix) {
			Session hibSession = getSession();
			StringBuffer sb = new StringBuffer();	
				
//			sb.append(" SELECT  emp.EMP_NAME,emp.SEVARTH_ID,bill.ddo_code,pay.GPAY, pay.PO,"+newBasicAccToCal+" AS Basic_FACTOR,"+newBasicPerMatrix+" AS Seven_PC_BASIC from hr_pay_paybill pay ");
//			sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID  ");
//			sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID  ");
//			sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID  ");
//			sb.append(" INNER JOIN MST_DCPS_BILL_GROUP bill on mpg.BILL_NO=bill.BILL_GROUP_ID  ");
//			sb.append(" where emp.SEVARTH_ID='"+empSevarthID+"' and  mpg.APPROVE_FLAG=1  ");
//			sb.append(" and pay.PAYBILL_YEAR in(2016) and pay.PAYBILL_MONTH in (1) AND pay.GPAY in (6000,7000,8000,9000,10000)");
			sb.append("SELECT EMP_NAME,SEVARTH_ID,ddo_code,GRADE_PAY,basic_pay,"+newBasicAccToCal+" AS Basic_FACTOR,"+newBasicPerMatrix+" AS Seven_PC_BASIC FROM MST_DCPS_EMP ") ;
//			sb.append("where GRADE_PAY in (6000,7000,8000,9000,10000) and SEVARTH_ID='"+empSevarthID+"'");
			sb.append("where GRADE_PAY in (0,1300,1600,1800,1900,2000,2400,2800,4100,4200,4300,4400,4600,4800,5400,5700,6000,7000,8000,9000,10000,12000) and SEVARTH_ID='"+empSevarthID+"'");//Added By Tejashree//
			Query sql1query=hibSession.createSQLQuery(sb.toString());
			logger.error("SQL Main Query ListView Revision of 6thPC to 7th PC >>>"+sql1query.toString());
			logger.info("check>>>");
			
			try {
				logger.info("SQL Main11 Query ListView Size Is >>>"+sql1query.list().size());
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("SQL Main Query SQL Main Query ListView Size Is >>> >>>"+e);
				logger.error(e.getLocalizedMessage());
				e.printStackTrace();
				
			}
//			logger.error("SQL Main Query ListView Size Is >>>"+sql1query.list().size());
			return sql1query.list();
	
	}
	
	//Added by pooja for Viklp EMployee 
	public List getSevenPCViklpEmpNewDetails(String empSevarthID, Long newBasicAccToCal,Long newBasicPerMatrix,String month,String year) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
			
		sb.append(" SELECT  emp.EMP_NAME,emp.SEVARTH_ID,bill.ddo_code,pay.GPAY, pay.PO,"+newBasicAccToCal+" AS Basic_FACTOR,"+newBasicPerMatrix+" AS Seven_PC_BASIC from hr_pay_paybill pay ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID  ");
		sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID  ");
		sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID  ");
		sb.append(" INNER JOIN MST_DCPS_BILL_GROUP bill on mpg.BILL_NO=bill.BILL_GROUP_ID  ");
		sb.append(" where emp.SEVARTH_ID='"+empSevarthID+"' and  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (1,2) ");
		sb.append(" and pay.PAYBILL_YEAR ="+Integer.parseInt(year)+" and pay.PAYBILL_MONTH="+Integer.parseInt(month));
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		logger.error("sql query for getSevenPCEmpDetails is 3 "+sql1query.toString());
		logger.error("getSevenPCEmpDetails sql1query.list()"+sql1query.list().size());
		return sql1query.list();

}

	//added by pooja - 06-02-2019
	public List getNEwBasicAsPerMAtrixForBunching(String gradeId,
			Long newBasicAccToCal) {
		List temp=null;
		List lLstReturnList=null;
		Session session = getSession();
	
		try
		{		
			String branchQuery = "SELECT cell,"+gradeId+" FROM MST_state_MATRIX_7THPAY where  "+gradeId+" > "+newBasicAccToCal+" and  "+gradeId+" > 0 order by S_1 FETCH FIRST 5 ROWS only";
			Query sqlQuery= session.createSQLQuery(branchQuery);
			logger.error("sqlQuery Size"+sqlQuery.toString());
			lLstReturnList= sqlQuery.list();
		
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return lLstReturnList;
	
	}
	
	//added by pooja - 06-02-2019
		public List getStateNEwBasicAsPerMAtrixForBunching(String gradeId,
				Long newBasicAccToCal) {
			
			List lLstReturnList=null;
			Session session = getSession();
			
			//hibSession = getSession();
			try
			{		
				String queryBuffer = "SELECT "+gradeId+" FROM MST_state_MATRIX_7THPAY where  "+gradeId+" > "+newBasicAccToCal+" and  "+gradeId+" > 0 order by S_1 FETCH FIRST 4 ROWS only";
				Query query = session.createSQLQuery(queryBuffer.toString());
				logger.error("Query for getStateNewBasicAsPerMAtrix :: new--"+queryBuffer.toString());
				 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
					 lLstReturnList = query.list();
				}
			}
			catch(Exception e){
				logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
				e.printStackTrace();
			}
			return lLstReturnList;
		}
		public List getStateGradePayList(Long levelId) {
		
			List lLstReturnList=null;
			Session session = getSession();
		
			try
			{		
				String queryBuffer = "SELECT level_id,ID FROM RLT_PAYBAND_GP_state_7pc where LEVEL_ID >= "+levelId+" ORDER by LEVEL_ID ASC";
				Query query = session.createSQLQuery(queryBuffer.toString());
				logger.error("Query for getStateGradePayList:: new--"+queryBuffer.toString());
				 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
					 lLstReturnList = query.list();
				}
			}
			catch(Exception e){
				logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
				e.printStackTrace();
			}
			return lLstReturnList;
		}
		
	/*	public List getIsolatedPostStateGradePayList(Long levelId) {
			
			List lLstReturnList=null;
			Session session = getSession();
		
			try
			{		
				String queryBuffer = "SELECT level_id,ID FROM RLT_PAYBAND_GP_state_7pc where LEVEL_ID > "+levelId+" ORDER by LEVEL_ID ASC";
				Query query = session.createSQLQuery(queryBuffer.toString());
				logger.error("Query for getStateGradePayList:: new--"+queryBuffer.toString());
				 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
					 lLstReturnList = query.list();
				}
			}
			catch(Exception e){
				logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
				e.printStackTrace();
			}
			return lLstReturnList;
		}
		*/
		//FOr Punishment
		public List getPunishmentStateGradePayList(Long levelId) {
			
			List lLstReturnList=null;
			Session session = getSession();
		
			try
			{		
				String queryBuffer = "SELECT level_id,ID FROM RLT_PAYBAND_GP_state_7pc where LEVEL_ID = "+levelId+" ORDER by LEVEL_ID ASC";
				Query query = session.createSQLQuery(queryBuffer.toString());
				logger.error("Query for getStateGradePayList:: new--"+queryBuffer.toString());
				 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
					 lLstReturnList = query.list();
				}
			}
			catch(Exception e){
				logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
				e.printStackTrace();
			}
			return lLstReturnList;
		}
		//for isolated post employee
   public List getStateGradePayList() {
			
			List lLstReturnList=null;
			Session session = getSession();
		
			try
			{		
				String queryBuffer = "SELECT level_id,ID FROM RLT_PAYBAND_GP_state_7pc ORDER by LEVEL_ID ASC";
				Query query = session.createSQLQuery(queryBuffer.toString());
				logger.error("Query for getStateGradePayList:: isolate--"+queryBuffer.toString());
				 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
					 lLstReturnList = query.list();
				}
			}
			catch(Exception e){
				logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
				e.printStackTrace();
			}
			return lLstReturnList;
		}
		//demotion gradepay list
		public List getStateGradePayDemotionList(Long levelId) {
			
			List lLstReturnList=null;
			Session session = getSession();
		
			try
			{		
				String queryBuffer = "SELECT level_id,ID FROM RLT_PAYBAND_GP_state_7pc where LEVEL_ID <= "+levelId+" ORDER by LEVEL_ID ASC";
				Query query = session.createSQLQuery(queryBuffer.toString());
				logger.error("Query for getStateGradePayList:: new--"+queryBuffer.toString());
				 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
					 lLstReturnList = query.list();
				}
			}
			catch(Exception e){
				logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
				e.printStackTrace();
			}
			return lLstReturnList;
		}
		
		//Isolted Post EMployee demotion level
     /* public List getIsolatedPostStateGradePayDemotionList(Long levelId) {
			
			List lLstReturnList=null;
			Session session = getSession();
		
			try
			{		
				String queryBuffer = "SELECT level_id,ID FROM RLT_PAYBAND_GP_state_7pc where LEVEL_ID <= "+levelId+" ORDER by LEVEL_ID ASC";
				Query query = session.createSQLQuery(queryBuffer.toString());
				logger.error("Query for getIsolatedPostStateGradePayDemotionList:: new--"+queryBuffer.toString());
				 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
					 lLstReturnList = query.list();
				}
			}
			catch(Exception e){
				logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
				e.printStackTrace();
			}
			return lLstReturnList;
		}*/
		

		public List getGradePayList(String gradePay) {
			
			List lLstReturnList=null;
			Session session = getSession();
		
			try
			{		
				String queryBuffer = "SELECT level_id ,ID FROM RLT_PAYBAND_GP_7pc where GRADE_PAY >'"+gradePay+"'";
				Query query = session.createSQLQuery(queryBuffer.toString());
				logger.error("Query for getStateGradePayList:: new--"+queryBuffer.toString());
				 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
					 lLstReturnList = query.list();
				}
			}
			catch(Exception e){
				logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
				e.printStackTrace();
			}
			return lLstReturnList;
		}
		
		public void insertOldSvnPCbasicDetails(String strSevarthId, long userId,long existSvnBasic,String remarks,String txtAuthorityLetterNo,String orderDate) {
			try{
				StringBuffer str = new StringBuffer();
				Session hibSession = getSession();
				logger.info("ddoCode daoimpl"+strSevarthId);
				logger.info("activateFlag daoimpl"+userId);
				str.append("insert into HST_SEVENPC_BASIC_DTS(SEVARTH_ID,PRE_SVNPAY_BASIC,UPDATE_DATE,UDAPTED_BY,REMARKS,ORDER_NO,ORDER_DATE)values('"+strSevarthId+"',"+existSvnBasic+",sysdate,"+userId+",'"+remarks+"','"+txtAuthorityLetterNo+"','"+orderDate+"')");
				Query query = hibSession.createSQLQuery(str.toString());
				logger.info("insertOldSvnPCbasicDetails------"+str.toString());

				query.executeUpdate();
			}
			catch(Exception e){
				e.printStackTrace();

			}
			
		}
		
		public void updateNewBasicPay(String strSevarthId, Long newbasicPaySvnPC,Date effectDate,String levelId,String gradePay) {		
			
			Session hibSession =getSession();
			StringBuffer str = new StringBuffer();	
			String PayCommission = "700005";
			str.append("update mst_dcps_emp set SEVEN_PC_BASIC = "+newbasicPaySvnPC+",PAY_COMMISSION = '"+PayCommission+"',SEVEN_PC_LEVEL='"+levelId+"',GRADE_PAY='"+gradePay+"' where SEVARTH_ID = '"+strSevarthId+"' ");
			logger.info("updateEmpGPFDetails------"+str.toString());
			Query query1 = hibSession.createSQLQuery(str.toString());
			query1.executeUpdate();
			
			StringBuffer str1 = new StringBuffer();
			//str1.append(" update HR_EIS_OTHER_DTLS set other_svn_pc_basic = "+newbasicPaySvnPC+",SVN_PC_BASIC_EFFECT_DT ='"+effectDate+"' where emp_id =(SELECT emp_id FROM HR_EIS_EMP_MST where EMP_MPG_ID =(SELECT org_emp_mst_id FROM MST_DCPS_EMP where SEVARTH_ID = '"+strSevarthId+"'))");
			str1.append(" update HR_EIS_OTHER_DTLS set other_svn_pc_basic =:newbasicPaySvnPC ,SVN_PC_BASIC_EFFECT_DT =:effectDate where emp_id =(SELECT emp_id FROM HR_EIS_EMP_MST where EMP_MPG_ID =(SELECT org_emp_mst_id FROM MST_DCPS_EMP where SEVARTH_ID =:strSevarthId ))");

			logger.info("updateEmpGPFDetails------"+str1.toString());
			Query query2 = hibSession.createSQLQuery(str1.toString());
			query2.setLong("newbasicPaySvnPC", newbasicPaySvnPC);
			query2.setDate("effectDate", effectDate);
			query2.setString("strSevarthId", strSevarthId);
			query2.executeUpdate();
			
			/*StringBuffer str2 = new StringBuffer();
			str2.append(" update HR_PAY_DEDUCTION_DTLS set  EMP_DEDUC_AMOUNT =:empDeducAmt,UPDATED_DATE =:effectDate where emp_id =(SELECT org_emp_mst_id FROM MST_DCPS_EMP where SEVARTH_ID =:strSevarthId and reg_status =:regStatus) and EMP_DEDUC_id in (72,36,75,76,77,78)");

			logger.info("updateEmpGPFDetails------"+str2.toString());
			Query query3 = hibSession.createSQLQuery(str2.toString());
			query3.setInteger("empDeducAmt", 0);
			query3.setDate("effectDate", effectDate);
			query3.setString("strSevarthId", strSevarthId);
			query3.setInteger("regStatus", 2);
			query3.executeUpdate();*/
			
			
		}

		public int checkStateNEwBasicAsPerMAtrixValue(String emplevelId,
				long matrixValue) {

			List lLstReturnList=null;
			int count = 0;
			Session session = getSession();
			try
			{		
				String queryBuffer = "SELECT * FROM MST_state_MATRIX_7THPAY where "+emplevelId+" = "+matrixValue;
				Query query = session.createSQLQuery(queryBuffer.toString());
				logger.error("Query for checkStateNEwBasicAsPerMAtrixValue :: new--"+queryBuffer.toString());
				 if(query.list()!=null && query.list().size()>0 && query.list().get(0)!=null){
					 lLstReturnList = query.list();
					 count = 1;
				}
			}
			catch(Exception e){
				logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
				e.printStackTrace();
			}
			return count;
		}

		public List getLevelId(String ID) {
			
			Session hibSession = getSession();
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer.append("SELECT LEVEL_ID,GRADE_PAY FROM RLT_PAYBAND_GP_state_7pc where ID='"+ID+"'");	
			Query sql1query=hibSession.createSQLQuery(queryBuffer.toString());
			logger.error("sql query for getLevelId is"+sql1query.toString());
		
			return sql1query.list();
				
		}

		public int checkStateLevelIdForGivenEmp(String sevarthId,String flag) {
			int count = 0;
			Session hibSession = getSession();
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer.append(" SELECT count(*) FROM ");
			queryBuffer.append(" MST_DCPS_EMP a,HST_SEVENPC_BASIC_DTS b where  a.SEVARTH_ID = b.SEVARTH_ID and ");
		
			if(flag.equals("Yes")){
			queryBuffer.append(" a.SEVARTH_ID ='"+sevarthId+"'");	
			}else{
				
				queryBuffer.append(" a.EMP_NAME ='"+sevarthId+"'");
			}
			Query query = hibSession.createSQLQuery(queryBuffer.toString());
			logger.error("Query to get checkStateLevelIdForGivenEmp :: "+queryBuffer.toString());
			if(query.uniqueResult()!=null){
				count = Integer.parseInt(query.uniqueResult().toString());
			}
			return count;
		}
		
		public int checkStateLevelIdForGivenEmp(String sevarthId) {
			int count = 0;
			Session hibSession = getSession();
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer.append(" SELECT count(*) FROM ");
			queryBuffer.append(" MST_DCPS_EMP a,HST_SEVENPC_BASIC_DTS b where  a.SEVARTH_ID = b.SEVARTH_ID and ");
		    queryBuffer.append(" a.SEVARTH_ID ='"+sevarthId+"'");	
			
			Query query = hibSession.createSQLQuery(queryBuffer.toString());
			logger.error("Query to get checkStateLevelIdForGivenEmp :: "+queryBuffer.toString());
			if(query.uniqueResult()!=null){
				count = Integer.parseInt(query.uniqueResult().toString());
			}
			return count;
		}
		
		public List getStateGradeIdDetailsList(String gradePay) {
			Session hibSession = getSession();
			StringBuffer sb = new StringBuffer();	
				
//			sb.append(" SELECT id,level FROM RLT_PAYBAND_GP_state_7pc where grade_pay = '"+gradePay+"' ");	
			sb.append(" SELECT id,level,level_id FROM RLT_PAYBAND_GP_state_7pc");	
			Query sql1query=hibSession.createSQLQuery(sb.toString());
			logger.info("reflect");
			//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
			logger.error("sql query for getStategradeIdDetailsList is"+sql1query.toString());
			return sql1query.list();
		}
		
		//Get Viklp Employee Data - 13-03-2019
		public List getSevenPCViklpEmpDetails(String strEmpName, long locId, String flag,String month,String year) {
			Session hibSession = getSession();
			StringBuffer sb = new StringBuffer();	
			sb.append(" SELECT pay.GPAY,pay.PO,emp.SEVARTH_ID from hr_pay_paybill pay ");
			sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID  ");
			sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID  ");
			sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID  ");
			sb.append(" INNER JOIN MST_DCPS_BILL_GROUP bill on mpg.BILL_NO=bill.BILL_GROUP_ID  ");
		
			if(flag.equals("Yes"))
				sb.append(" where emp.SEVARTH_ID='"+strEmpName+"' and  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (1,2) and pay.PAYBILL_YEAR = "+Integer.parseInt(year)+" and pay.PAYBILL_MONTH = "+Integer.parseInt(month));
			
			else
					sb.append(" where emp.EMP_NAME='"+strEmpName+"' and  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (1,2) and pay.PAYBILL_YEAR  = "+Integer.parseInt(year)+" and pay.PAYBILL_MONTH = "+Integer.parseInt(month));
					Query sql1query=hibSession.createSQLQuery(sb.toString());
			logger.error("sql query for getSevenPCEmpDetails new is"+sql1query.toString());
			logger.error("getSevenPCEmpDetails sql1query.list()"+sql1query.list().size());
			return sql1query.list();
		}

		//for isolated post employess - 17-03-2019
		public List getSevenPCIsoltedEmpNewDetails(String empSevarthID){
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();	
		sb.append(" SELECT pay.GPAY,pay.PO,emp.SEVARTH_ID from hr_pay_paybill pay ");
		sb.append(" inner join HR_EIS_EMP_MST eis on eis.EMP_ID = pay.EMP_ID  ");
		sb.append(" inner join PAYBILL_HEAD_MPG mpg on mpg.PAYBILL_ID = pay.PAYBILL_GRP_ID  ");
		sb.append(" inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID  ");
		sb.append(" INNER JOIN MST_DCPS_BILL_GROUP bill on mpg.BILL_NO=bill.BILL_GROUP_ID  ");
		sb.append(" where emp.SEVARTH_ID='"+empSevarthID+"' and  mpg.APPROVE_FLAG=1 and mpg.BILL_CATEGORY in (2) and pay.PAYBILL_YEAR = 2016 and pay.PAYBILL_MONTH = 1" );
		sb.append(" AND pay.GPAY in (1300,1400,1600,1700, 1800,1900,2000,2400,2500,2800,3000,3500,4200,4300, 4400,4600, 4800,5000,5400,5500, 5800,6600,6900,7600,7900,8700,8800,8900,1000,0,1650,2900,4100,4500,4900,5700,12000) ");
		Query sql1query=hibSession.createSQLQuery(sb.toString());
		logger.error("sql query for getSevenPCIsoltedEmpNewDetails new is"+sql1query.toString());
	
		return sql1query.list();
		}

		public String getGradePay(String level) {
			String gradePay = null;
			Session hibSession = getSession();
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer.append(" SELECT GRADE_PAY FROM RLT_PAYBAND_GP_state_7pc where  ID ='"+level+"'");
			Query query = hibSession.createSQLQuery(queryBuffer.toString());
			logger.error("Query is :: "+queryBuffer.toString());
			if(query.uniqueResult()!=null){
				gradePay = query.uniqueResult().toString();
			}
			return gradePay;
		}

}