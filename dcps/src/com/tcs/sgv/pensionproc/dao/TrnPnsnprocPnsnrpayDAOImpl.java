package com.tcs.sgv.pensionproc.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class TrnPnsnprocPnsnrpayDAOImpl extends GenericDaoHibernateImpl	implements TrnPnsnprocPnsnrpayDAO {

	private final static Logger gLogger = Logger
			.getLogger(TrnPnsnprocPnsnrpayDAOImpl.class);

	private Session ghibSession = null;

	public TrnPnsnprocPnsnrpayDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
		// TODO Auto-generated constructor stub
	}

	
	public Long getPayScaleDtls(Long lLngEmpId) {		
		StringBuilder lSBQuery = new StringBuilder();
		List<Long> lLstPayScaleId = null;
		Long lLngPayScaleId = null;
		try {
			lSBQuery.append(" select ESM.hrEisScaleMst.scaleId from HrEisSgdMpg ESM,HrEisOtherDtls EOD,HrEisEmpMst HEM where ");
			lSBQuery.append(" HEM.orgEmpMst.empId = :EmpId AND HEM.empId = EOD.hrEisEmpMst.empId AND ESM.sgdMapId = EOD.hrEisSgdMpg.sgdMapId ");
			Query lHibQry = ghibSession.createQuery(lSBQuery.toString());
			lHibQry.setParameter("EmpId", lLngEmpId);
			lLstPayScaleId = lHibQry.list();		
			
			if(!lLstPayScaleId.isEmpty())
				lLngPayScaleId = lLstPayScaleId.get(0);
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnprocPnsnrpayDAOImpl : getPayScaleDtls() : Error is :" + e, e);

		}
		return lLngPayScaleId;		
	}


	
	public Long getPayInPayBand(Long lLngEmpId){
		StringBuilder lSBQuery = new StringBuilder();
		List<Long> lLstPayInPB = null;
		Long lLngPayInPB = null;
		try {
			
			lSBQuery.append("SELECT PAY_IN_PAY_BAND FROM MST_DCPS_EMP where ORG_EMP_MST_ID:lLngEmpId ");
			Query Qry = ghibSession.createSQLQuery(lSBQuery.toString());
			Qry.setParameter("lLngEmpId", lLngEmpId);
			lLstPayInPB = Qry.list();	
			if(!lLstPayInPB.isEmpty()){
				lLngPayInPB=lLstPayInPB.get(0);
			}
			
		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);

		}
		return lLngPayInPB;	
	}
			
		
	
	
	
	
	public Long getBasicPay(Long lLngEmpId) {
		StringBuilder lSBQuery = new StringBuilder();
		List lLstBasicPay = null;
		Long lLngBasicPay = null;
		String basic="";
		try {
			/*lSBQuery.append(" select  EOD.otherCurrentSevenBasic  ");
			lSBQuery.append("from HrEisEmpMst HEM,HrEisOtherDtls EOD where ");
			lSBQuery.append(" HEM.orgEmpMst.empId = :EmpId AND HEM.empId = EOD.hrEisEmpMst.empId ");*/
			

			lSBQuery.append("select (CASE WHEN EOD.OTHER_SVN_PC_BASIC > 0 THEN cast (EOD.OTHER_SVN_PC_BASIC as BIGINT) ELSE cast(EOD.OTHER_CURRENT_BASIC  as BIGINT) END) ");
			lSBQuery.append("from Hr_Eis_Emp_Mst HEM ");
			lSBQuery.append("join Hr_Eis_Other_Dtls EOD on HEM.EMP_ID=EOD.EMP_ID ");
			lSBQuery.append("where HEM.EMP_MPG_ID=:EmpId ");
			
			Query lHibQry = ghibSession.createSQLQuery(lSBQuery.toString());
			lHibQry.setParameter("EmpId", lLngEmpId);
			lLstBasicPay = lHibQry.list();		
			logger.info("EmpId***********"+lLngEmpId);
			if(!lLstBasicPay.isEmpty()){
				 basic= lLstBasicPay.get(0).toString();
				 lLngBasicPay=Long.parseLong(basic);
			}
			logger.info("basic***********"+basic);
				//lLngBasicPay = (Long) lLstBasicPay.get(0);
			
		} catch (Exception e) {
			gLogger.error("TrnPnsnprocPnsnrpayDAOImpl : getPayScaleDtls() : Error is :" + e, e);

		}
		return lLngBasicPay;	
	}


	
	public List getAvgPayDtls(Long lLngEmpId,String lStrFormYearMonth,String lStrToYearMonth,String lStrPayComm) {
	
		StringBuilder lSBQuery = new StringBuilder();
		List lLstAvgPayDtls = null;
	
		try {
			lSBQuery.append(" SELECT  CONCAT(head.PAYBILL_YEAR,lpad(head.PAYBILL_MONTH,2,0)),paybill.po ");
			if(lStrPayComm.equals("700015")){
				lSBQuery.append(",paybill.D_PAY,paybill.NON_PRAC_ALLOW ");
			}
			else if((lStrPayComm.equals("700016")) || (lStrPayComm.equals("700339"))){
				lSBQuery.append(",paybill.GPAY,paybill.NON_PRAC_ALLOW ");
			}
			lSBQuery.append(" FROM hr_pay_paybill paybill,PAYBILL_HEAD_MPG head where ");
			lSBQuery.append(" head.APPROVE_FLAG in (0,1)  and paybill.EMP_ID = :EmpId and head.PAYBILL_ID =paybill.PAYBILL_GRP_ID ");
			lSBQuery.append(" and CONCAT(head.PAYBILL_YEAR,lpad(head.PAYBILL_MONTH,2,0)) >= :lStrFormYearMonth ");
			lSBQuery.append(" and CONCAT(head.PAYBILL_YEAR,lpad(head.PAYBILL_MONTH,2,0)) <= :lStrToYearMonth ");
			Query lHibQry = ghibSession.createSQLQuery(lSBQuery.toString());
			lHibQry.setParameter("EmpId", lLngEmpId);
			lHibQry.setParameter("lStrFormYearMonth", lStrFormYearMonth);
			lHibQry.setParameter("lStrToYearMonth", lStrToYearMonth);
			lLstAvgPayDtls = lHibQry.list();					
		} catch (Exception e) {
			gLogger.error("TrnPnsnprocPnsnrpayDAOImpl : getAvgPayDtls() : Error is :" + e, e);

		}
		return lLstAvgPayDtls;
	}
	//Added by Shraddha 
	public Long getNpaAmount(Long lLngEmpId) {
		
		StringBuilder lSBQuery = new StringBuilder();
		List lLstNpaAmount = null;
		Long lLngNpaAmount = null;
		String npaAmt="";
		try {
			lSBQuery.append("SELECT NON_PRAC_ALLOW FROM HR_PAY_PAYBILL pay ");
			lSBQuery.append("join HR_EIS_EMP_MST eis on pay.EMP_ID=eis.EMP_MPG_ID ");
			lSBQuery.append("join ORG_EMP_MST emp on eis.EMP_MPG_ID=emp.EMP_ID ");
			lSBQuery.append("join MST_DCPS_EMP mde on emp.EMP_ID=mde.ORG_EMP_MST_ID ");
			lSBQuery.append("join PAYBILL_HEAD_MPG phm on pay.PAYBILL_GRP_ID=phm.PAYBILL_ID ");
			lSBQuery.append("where emp.EMP_ID=:EmpId and phm.APPROVE_FLAG in ('0','5','1')");
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			lQuery.setParameter("EmpId", lLngEmpId);
			lLstNpaAmount = lQuery.list();		
			
			if(!lLstNpaAmount.isEmpty()){
				npaAmt = lLstNpaAmount.get(0).toString();
				lLngNpaAmount = Long.parseLong(npaAmt);
			}
		} catch (Exception e) {
			gLogger.error("TrnPnsnprocPnsnrpayDAOImpl : getPayScaleDtls() : Error is :" + e, e);

		}
		return lLngNpaAmount;	
	}
}
