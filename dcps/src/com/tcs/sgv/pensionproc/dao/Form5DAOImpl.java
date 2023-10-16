package com.tcs.sgv.pensionproc.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class Form5DAOImpl extends GenericDaoHibernateImpl{

	
	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;
	public Form5DAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	
}
	public String checkCaseStatus(String sevaarthID) {
		
		String caseStatus="";
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT CASE_STATUS FROM TRN_PNSNPROC_INWARDPENSION where SEVAARTH_ID=:sevaarthID");
			Query selectQuery = ghibSession.createSQLQuery(sb.toString());
			selectQuery.setParameter("sevaarthID", sevaarthID);
			List lLstResult = selectQuery.list();
			if(lLstResult.size()!=0){
				caseStatus=lLstResult.get(0).toString();
			}
			else{
				caseStatus=null;
			}
		}catch (Exception e) {
			logger.error("Error is :" + e);
			e.printStackTrace();
		}
		return caseStatus;
	}
	
	
	public Object[] getForm5Dtls(String sevaarthID) {
		
		Object[] Form5Dtls=null;
		try{
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT mde.EMP_NAME,to_char(tpp.BIRTH_DATE,'DD-MM-YYYY'),to_char(tpp.RETIREMENT_DATE,'DD-MM-YYYY'),tpp.HEIGHT_FEET,tpp.REMARKS,tpp.PNSNR_ADDR_FLAT   ||  ',' || tpp.PNSNR_ADDR_ROAD  ||  ',' || tpp.PNSNR_ADDR_AREA  ||  ',' ||  csm.STATE_NAME ||  ',' || cdm.DISTRICT_NAME ||  '-' || tpp.PNSNR_ADDR_PINCODE, ");
			sb.append(" odm.DDO_OFFICE,ofc.ADDRESS1 ||  '-' || ofc.OFFICE_PIN,to_char(tpp.JOINING_DATE,'DD-MM-YYYY'),tpp.DESIGNATION,tpp.PNSNR_ADDR_RET_FLAT || ',' || tpp.PNSNR_ADDR_RET_ROAD || ',' || tpp.PNSNR_ADDR_RET_AREA  ||  ',' || tpp.PNSNR_ADDR_RET_STATECODE  ||  ',' || tpp.PNSNR_ADDR_RET_DISTCODE  ||  '-' || tpp.PNSNR_ADDR_RET_PINCODE ");
			sb.append(" FROM MST_DCPS_EMP mde join ORG_DDO_MST odm on mde.DDO_CODE=odm.DDO_CODE ");
			sb.append(" join MST_DCPS_DDO_OFFICE ofc on ofc.DDO_CODE=mde.DDO_CODE ");
			sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on mde.SEVARTH_ID=tpi.SEVAARTH_ID ");
			sb.append(" join TRN_PNSNPROC_PNSNRDTLS tpp on tpi.INWARD_PENSION_ID=tpp.INWARD_PENSION_ID ");
			sb.append(" join CMN_STATE_MST csm on tpp.PNSNR_ADDR_STATECODE=csm.STATE_ID ");
			sb.append(" join CMN_DISTRICT_MST cdm on tpp.PNSNR_ADDR_DISTCODE=cdm.DISTRICT_ID");
			sb.append(" where mde.SEVARTH_ID=:sevaarthID");
			Query selectQuery = ghibSession.createSQLQuery(sb.toString());
			selectQuery.setParameter("sevaarthID", sevaarthID);
			List lLstResult = selectQuery.list();
			if(lLstResult.size()!=0){
				Form5Dtls=(Object[]) lLstResult.get(0);
			}
			else{
				Form5Dtls=null;
			}
		}catch (Exception e) {
			logger.error("Error is :" + e);
			e.printStackTrace();
		}
		
		return Form5Dtls;
	}
	public Object[] getPensionerDtls(String sevaarthID, String empName) {
		
		Object[] PensionerDtls=null;
		try{
			List lLstResult=null;
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT mde.EMP_NAME,mde.SEVARTH_ID,opd.POST_NAME,to_char(mde.DOB,'DD-MM-YYYY'),to_char(mde.EMP_SERVEND_DT,'DD-MM-YYYY') ");
			sb.append(" FROM MST_DCPS_EMP mde join ORG_EMP_MST emp on mde.ORG_EMP_MST_ID=emp.EMP_ID ");
			sb.append(" join ORG_USERPOST_RLT opr on emp.USER_ID=opr.USER_ID ");
			sb.append(" join ORG_POST_DETAILS_RLT opd on opr.POST_ID=opd.POST_ID ");
			if(sevaarthID!=""){
				sb.append("where mde.SEVARTH_ID=:sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
			lLstResult = selectQuery.list();
			}
			else{
			sb.append("where mde.EMP_NAME=:empName");
			Query selectQuery = ghibSession.createSQLQuery(sb.toString());
			selectQuery.setParameter("empName", empName);
			lLstResult = selectQuery.list();
			}
			
			
			
			
			if(lLstResult.size()!=0){
				PensionerDtls=(Object[]) lLstResult.get(0);
			}
			else{
				PensionerDtls=null;
			}
	}catch (Exception e) {
		logger.error("Error is :" + e);
		e.printStackTrace();
	}
		
		return PensionerDtls;
		
		
	}
	public List getFamilyDtls(String sevaarthID) {
		
		List FamilyDtls=null;
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT tpf.NAME,tpf.BIRTH_DATE,tpf.RELATION FROM TRN_PNSNPROC_FAMILYDTLS tpf  ");
			sb.append(" join TRN_PNSNPROC_PNSNRDTLS tpp on tpf.PENSIONERDTL_ID=tpp.PENSIONERDTL_ID ");
			sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tpf.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
			sb.append(" where tpi.SEVAARTH_ID=:sevaarthID");
			Query selectQuery = ghibSession.createSQLQuery(sb.toString());
			selectQuery.setParameter("sevaarthID", sevaarthID);
			FamilyDtls = selectQuery.list();
			if(FamilyDtls.size()==0){
				FamilyDtls=null;
			}
			
			
		}catch (Exception e) {
			logger.error("Error is :" + e);
			e.printStackTrace();
		}
		return FamilyDtls;
	}
	public List getNomineeDtls(String sevaarthID) {
		
		List NomineeDtls=null;
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT tpn.NAME,tpn.RELATION,tpn.AGE,tpn.AMT_OF_SHARE_PERCN,tpn.ALTERNATE_NOMINEE, ");
			sb.append(" tpn.FLAT_NO || ',' || tpn.ROAD || ',' || tpn.AREA || ',' || csm.STATE_NAME || ',' || cdm.DISTRICT_NAME || '-' || tpn.PINCODE ");
			sb.append(" FROM TRN_PNSNPROC_NOMINEEDTLS tpn  ");
			sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tpn.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
			sb.append(" join CMN_STATE_MST csm on csm.STATE_ID=tpn.STATE ");
			sb.append(" join CMN_DISTRICT_MST cdm on cdm.DISTRICT_ID=tpn.DISTRICT ");
			sb.append(" where tpi.SEVAARTH_ID=:sevaarthID");
			Query selectQuery = ghibSession.createSQLQuery(sb.toString());
			selectQuery.setParameter("sevaarthID", sevaarthID);
			NomineeDtls = selectQuery.list();
			if(NomineeDtls.size()==0){
				NomineeDtls= null;
			}
			
		}catch (Exception e) {
			logger.error("Error is :" + e);
			e.printStackTrace();
		}
		
		return NomineeDtls;
	}
}