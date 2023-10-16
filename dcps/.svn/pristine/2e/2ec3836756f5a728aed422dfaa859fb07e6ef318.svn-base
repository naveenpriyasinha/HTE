package com.tcs.sgv.pensionproc.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class PensionCaseReportDAOImpl extends GenericDaoHibernateImpl{

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;
	public PensionCaseReportDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	
}

		public Object[] getPensionerDtls(String sevaarthID) {
		
		Object[] PensionerDtls=null;
		try{
			List lLstResult=null;
			StringBuilder sb = new StringBuilder();
			
			//commented by harsh
			/*sb.append("SELECT mde.EMP_NAME,mde.SEVARTH_ID,opd.POST_NAME,to_char(mde.DOB,'DD-MM-YYYY'),to_char(mde.EMP_SERVEND_DT,'DD-MM-YYYY') ");
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
			//added by shraddha
			if(lLstResult.size()==0){
				StringBuilder sb1 = new StringBuilder();
				
				sb1.append("SELECT mde.EMP_NAME,mde.SEVARTH_ID,post.POST_NAME,to_char(mde.DOB,'DD-MM-YYYY'),to_char(mde.EMP_SERVEND_DT,'DD-MM-YYYY') ");
				sb1.append("from TRN_PNSNPROC_INWARDPENSION inwrd  ");
				sb1.append("join TRN_PNSNPROC_PNSNRDTLS dtls on inwrd.INWARD_PENSION_ID=dtls.INWARD_PENSION_ID ");
				sb1.append("join MST_DCPS_EMP mde on inwrd.SEVAARTH_ID=mde.SEVARTH_ID ");
				sb1.append("join ORG_EMP_MST emp on mde.ORG_EMP_MST_ID=emp.EMP_ID "); 
				sb1.append("join ORG_POST_DETAILS_RLT post on mde.DESIGNATION=post.DSGN_ID and inwrd.LOCATION_CODE=post.LOC_ID ");
				//sb.append("join cmn_location_mst loc on substr(mdd.DDO_CODE,1,4) =loc.location_code ");
				sb1.append("where inwrd.SEVAARTH_ID=:sevaarthID "); 
				sb1.append("fetch first rows only ");	
				Query selectQuery1 = ghibSession.createSQLQuery(sb1.toString());
				selectQuery1.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery1.list();
				
			}			*/
			//Added By Harsh	
			sb.append("SELECT tpp.PNSNR_NAME,tpr.SEVAARTH_ID,opd.POST_NAME,to_char(tpp.BIRTH_DATE,'DD-MM-YYYY'),to_char(tpp.RETIREMENT_DATE,'DD-MM-YYYY') ");
			sb.append("from TRN_PNSNPROC_INWARDPENSION tpr  ");
			sb.append("join TRN_PNSNPROC_PNSNRDTLS tpp on tpp.INWARD_PENSION_ID=tpr.INWARD_PENSION_ID ");
			sb.append(" join  MST_DCPS_EMP mde on  mde.SEVARTH_ID=tpr.sevaarth_id ");
			sb.append("join ORG_EMP_MST emp on mde.ORG_EMP_MST_ID=emp.EMP_ID "); 
			sb.append("join ORG_USERPOST_RLT opr on emp.USER_ID=opr.USER_ID ");
			sb.append("join ORG_POST_DETAILS_RLT opd on opr.POST_ID=opd.POST_ID ");
			sb.append("where tpr.SEVAARTH_ID=:sevaarthID ");
			sb.append("fetch first rows only ");	
			Query selectQuery1 = ghibSession.createSQLQuery(sb.toString());
			selectQuery1.setParameter("sevaarthID", sevaarthID);
			logger.info("selectQuery1 is"+selectQuery1.toString());
			lLstResult = selectQuery1.list();
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
		
		
		public Object[] getDDODtls(String sevaarthID) {
			
			Object[] getDDODtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
			
				//get pension case status
				String pension_case_status="";
				
				pension_case_status=getPensionCaseStatus(sevaarthID);
				
				
				if(pension_case_status.equalsIgnoreCase("Deputation")){
					sb.append("SELECT mde.DDO_CODE,mdd.off_name || ',' ||  mdd.ADDRESS1 || ',' || csm.STATE_NAME || ',' || cdm.DISTRICT_NAME || '-' || mdd.OFFICE_PIN, ");
					//sb.append(" mdd.EMAIL,mdd.TEL_NO1,mdd.FAX,mde.SEVARTH_ID,mde.EMP_NAME,mde.UID_NO,mde.EID_NO,oem.EMP_FNAME,oem.EMP_MNAME,oem.EMP_LNAME,to_char(mde.DOB,'DD/MM/YYYY'), ");
					//Addecd by harsh
					sb.append(" mdd.EMAIL,mdd.TEL_NO1,mdd.FAX,mde.SEVARTH_ID,tpp.PNSNR_NAME,mde.UID_NO,mde.EID_NO,oem.EMP_FNAME,oem.EMP_MNAME,oem.EMP_LNAME,to_char(mde.DOB,'DD/MM/YYYY'), ");
					sb.append(" mde.FATHER_OR_HUSBAND,mde.CELL_NO,mde.EMAIL_ID,mde.RELIGION,mde.GENDER,mdd.TEL_NO2,mdd.OFF_NAME,loc.loc_name FROM MST_DCPS_EMP mde ");
					sb.append("join MST_DCPS_DDO_OFFICE mdd on mde.DDO_CODE=mdd.DDO_CODE and mdd.DDO_OFFICE='Yes' ");
					//Addecd by harsh
					sb.append("join TRN_PNSNPROC_INWARDPENSION tpr on tpr.SEVAARTH_ID=mde.SEVARTH_ID ");
					sb.append("join TRN_PNSNPROC_PNSNRDTLS tpp on tpp.INWARD_PENSION_ID=tpr.INWARD_PENSION_ID ");
					///-----------end------------
					sb.append("join ORG_DDO_MST odm on mde.DDO_CODE=odm.DDO_CODE ");
					sb.append("join CMN_STATE_MST csm on mdd.STATE=csm.STATE_ID ");
					sb.append("join CMN_DISTRICT_MST cdm on mdd.DISTRICT=cdm.DISTRICT_ID ");
					sb.append(" join ORG_EMP_MST oem on mde.ORG_EMP_MST_ID=oem.EMP_ID ");
					sb.append("join cmn_location_mst loc on substr(mdd.DDO_CODE,1,4) =loc.location_code ");
					sb.append("where mde.SEVARTH_ID=:sevaarthID ");
					
			
				}
				else{
					sb.append("SELECT mde.DDO_CODE,mdd.off_name || ',' || mdd.ADDRESS1 || ',' || csm.STATE_NAME || ',' || cdm.DISTRICT_NAME || '-' || mdd.OFFICE_PIN, ");
					//sb.append(" mdd.EMAIL,mdd.TEL_NO1,mdd.FAX,mde.SEVARTH_ID,mde.EMP_NAME,mde.UID_NO,mde.EID_NO,oem.EMP_FNAME,oem.EMP_MNAME,oem.EMP_LNAME,to_char(mde.DOB,'DD/MM/YYYY'), "); 
					sb.append(" mdd.EMAIL,mdd.TEL_NO1,mdd.FAX,mde.SEVARTH_ID,tpp.PNSNR_NAME,mde.UID_NO,mde.EID_NO,oem.EMP_FNAME,oem.EMP_MNAME,oem.EMP_LNAME,to_char(mde.DOB,'DD/MM/YYYY'), "); 
					sb.append(" mde.FATHER_OR_HUSBAND,mde.CELL_NO,mde.EMAIL_ID,mde.RELIGION,mde.GENDER,mdd.TEL_NO2,mdd.OFF_NAME,loc.loc_name FROM MST_DCPS_EMP mde ");
					sb.append(" join MST_DCPS_DDO_OFFICE mdd on mde.DDO_CODE=mdd.DDO_CODE and mdd.DDO_OFFICE='Yes' ");
					sb.append("join TRN_PNSNPROC_INWARDPENSION tpr on tpr.SEVAARTH_ID=mde.SEVARTH_ID ");
					sb.append("join TRN_PNSNPROC_PNSNRDTLS tpp on tpp.INWARD_PENSION_ID=tpr.INWARD_PENSION_ID ");
					sb.append(" join ORG_DDO_MST odm on mde.DDO_CODE=odm.DDO_CODE ");
					sb.append(" join CMN_STATE_MST csm on mdd.STATE=csm.STATE_ID ");
					sb.append(" join CMN_DISTRICT_MST cdm on mdd.DISTRICT=cdm.DISTRICT_ID ");
					sb.append(" join ORG_EMP_MST oem on mde.ORG_EMP_MST_ID=oem.EMP_ID ");
					sb.append("join cmn_location_mst loc on substr(mdd.DDO_CODE,1,4) =loc.location_code ");
					sb.append(" where mde.SEVARTH_ID=:sevaarthID ");
				}
				
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
			
			
					  
					  
				
				if(lLstResult.size() > 0 && lLstResult!=null)
				{
					getDDODtls=(Object[]) lLstResult.get(0);
				}
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			
			return getDDODtls;
		}
		//added by ankita-19-10-2015
		public String getPensionCaseStatus(String sevaarthID){
			
			String PensionCaseStatus="";
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();

				sb.append("SELECT PENSION_CASE_TYPE FROM TRN_PNSNPROC_INWARDPENSION WHERE SEVaARTH_ID= :sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null && lLstResult.get(0)!=null){
					PensionCaseStatus=lLstResult.get(0).toString();
					System.out.println("PensionCaseStatus##########"+PensionCaseStatus);
				}
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return PensionCaseStatus;
		}
		
		public String getGPFAccNo(String sevaarthID){
			
			String gpfAccNo="";
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();

				sb.append("SELECT hpf.GPF_ACC_NO FROM HR_PAY_GPF_DETAILS hpf "); 
				sb.append(" join ORG_EMP_MST oem on oem.USER_ID=hpf.USER_ID ");
				sb.append(" join MST_DCPS_EMP mde on mde.ORG_EMP_MST_ID=oem.EMP_ID ");
				sb.append(" where mde.SEVARTH_ID=:sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					gpfAccNo=lLstResult.get(0).toString();
				}
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return gpfAccNo;
		}
		
		public Object[] getCaseNomineeDtls(String sevaarthID){
			
			Object[] lLstNomineeDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT tpn.NAME,tpn.RELATION FROM TRN_PNSNPROC_NOMINEEDTLS tpn ");
				sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tpn.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" where tpi.SEVAARTH_ID=:sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					lLstNomineeDtls=(Object[]) lLstResult.get(0);
					
				}
					
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return lLstNomineeDtls;
		}
		
		
		public Object[] getPensionerAddr(String sevaarthID){
			
			Object[] PensionerAddrDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				/*sb.append("SELECT tpp.PNSNR_ADDR_RET_FLAG,tpp.PNSNR_ADDR_FLAT || ',' || tpp.PNSNR_ADDR_ROAD || ',' || tpp.PNSNR_ADDR_AREA || ',' || cdm.DISTRICT_NAME || '-' || tpp.PNSNR_ADDR_RET_PINCODE || ',' || csm.STATE_NAME, "); 
				sb.append(" tpp.PNSNR_ADDR_MOBILENO,tpp.BANK_NAME,tpp.BANK_BRANCH_NAME,tpp.BANK_IFSC_CODE,tpp.BANK_ACCOUNT_NO,cmm.LOC_NAME, ");
				sb.append(" to_char(tpp.JOINING_DATE,'DD/MM/YYYY'),to_char(tpp.RETIREMENT_DATE,'DD/MM/YYYY'),to_char(tpp.DEATH_DATE,'DD/MM/YYYY'),tpi.PENSION_TYPE,tpp.DESIGNATION,to_char(tpi.COMMENCEMENT_DATE,'DD/MM/YYYY') ");
				sb.append(" FROM TRN_PNSNPROC_PNSNRDTLS tpp join TRN_PNSNPROC_INWARDPENSION tpi on tpp.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" join CMN_DISTRICT_MST cdm on tpp.PNSNR_ADDR_DISTCODE=cdm.DISTRICT_ID ");
				sb.append(" join CMN_STATE_MST csm on tpp.PNSNR_ADDR_STATECODE=csm.STATE_ID  ");
				sb.append(" join CMN_LOCATION_MST cmm on cmm.LOC_ID=tpi.TRSRY_ID_PENSION ");
				sb.append(" where tpi.SEVAARTH_ID=:sevaarthID ");
			*/	
				//added by ankita-04-12-2014
				sb.append("SELECT tpp.PNSNR_ADDR_RET_FLAG,tpp.PNSNR_ADDR_FLAT || ',' || tpp.PNSNR_ADDR_ROAD || ',' || tpp.PNSNR_ADDR_AREA || ',' || cdm.DISTRICT_NAME || '-' || tpp.PNSNR_ADDR_PINCODE || ',' || csm.STATE_NAME, ");  
				sb.append(" tpp.PNSNR_ADDR_MOBILENO,mb.BANK_NAME,rbb.BRANCH_NAME,tpp.BANK_IFSC_CODE,tpp.BANK_ACCOUNT_NO,cmm.LOC_NAME, ");
				sb.append(" to_char(tpp.JOINING_DATE,'DD/MM/YYYY'),to_char(tpp.RETIREMENT_DATE,'DD/MM/YYYY'),to_char(tpp.DEATH_DATE,'DD/MM/YYYY'),tpi.PENSION_TYPE,tpp.DESIGNATION,to_char(tpi.COMMENCEMENT_DATE,'DD/MM/YYYY') "); 
				sb.append(" ,tpp.BN_OR_AN,to_char(tpp.Date_of_confirmation,'DD/MM/YYYY') FROM TRN_PNSNPROC_PNSNRDTLS tpp join TRN_PNSNPROC_INWARDPENSION tpi on tpp.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID  ");
				sb.append(" join CMN_DISTRICT_MST cdm on tpp.PNSNR_ADDR_DISTCODE=cdm.DISTRICT_ID ");
				sb.append(" join CMN_STATE_MST csm on tpp.PNSNR_ADDR_STATECODE=csm.STATE_ID  ");
				sb.append("left outer join mst_bank mb on mb.BANK_CODE=tpp.BANK_NAME ");
				sb.append("left outer join RLT_BANK_BRANCH rbb on rbb.BRANCH_CODE=tpp.BANK_BRANCH_NAME ");
				sb.append(" join CMN_LOCATION_MST cmm on cmm.LOC_ID=tpi.TRSRY_ID_PENSION  ");
				sb.append("where tpi.SEVAARTH_ID=:sevaarthID ");
				
				Query selectQuery=ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult=selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					PensionerAddrDtls=(Object[]) lLstResult.get(0);
				}
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return PensionerAddrDtls;
			
		}
		
	public Object[] getAddtnCVPDtls(String sevaarthID){
			
			Object[] PensionerAddrDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				
				//added by ankita-04-12-2014
				sb.append("SELECT tpc.CVP_AMOUNT,tpc.CVP_MON_AMOUNT,tpc.PENSION_TOTAL_AMOUNT,tpc.PENSION_REDUCED_AMOUNT,tpc.WITHELD_GRATUITY "); 
				sb.append("FROM Trn_Pnsnproc_PnsnCalc tpc ");
				sb.append("join TRN_PNSNPROC_INWARDPENSION tpi on tpi.INWARD_PENSION_ID=tpc.INWARD_PENSION_ID ");
				sb.append("where tpi.SEVAARTH_ID=:sevaarthID ");
				
				Query selectQuery=ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult=selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					PensionerAddrDtls=(Object[]) lLstResult.get(0);
				}
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return PensionerAddrDtls;
			
		}
		
		//added by ankita-04-12-2014

		public Object[] getGrossQualifyingService(String sevaarthID){
			
			Object[] AddrRetirementDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT (tpp.QLY_YEAR+tpp.ACTUAL_YEAR),(tpp.QLY_MONTH+tpp.ACTUAL_MONTH),(tpp.QLY_DAY+tpp.ACTUAL_DAY),tpp.BRK_YEAR,tpp.BRK_MONTH,tpp.BRK_day, ");
				sb.append("tpp.qulify_Year,tpp.qulify_month,tpp.qulify_day ");
				sb.append("FROM trn_pnsnproc_pnsnrpay tpp ");
				sb.append("join TRN_PNSNPROC_INWARDPENSION tpi on tpp.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID "); 
				sb.append("where tpi.SEVAARTH_ID=:sevaarthID ");
				Query selectQuery=ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult=selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					AddrRetirementDtls=(Object[]) lLstResult.get(0);
				}
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return AddrRetirementDtls;
			
		}
		
		
		public Object[] getPensionerAddrRetirement(String sevaarthID){
			
			Object[] AddrRetirementDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT tpp.PNSNR_ADDR_RET_FLAG,tpp.PNSNR_ADDR_RET_FLAT || ',' || tpp.PNSNR_ADDR_RET_ROAD || ',' || tpp.PNSNR_ADDR_RET_AREA || ',' || cdm.DISTRICT_NAME || '-' || tpp.PNSNR_ADDR_RET_PINCODE || ',' || csm.STATE_NAME,tpp.PNSNR_ADDR_RET_MOBILENO ");
				sb.append(" FROM TRN_PNSNPROC_PNSNRDTLS tpp join TRN_PNSNPROC_INWARDPENSION tpi on tpp.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" join CMN_DISTRICT_MST cdm on tpp.PNSNR_ADDR_RET_DISTCODE=cdm.DISTRICT_ID ");
				sb.append(" join CMN_STATE_MST csm on tpp.PNSNR_ADDR_RET_STATECODE=csm.STATE_ID ");
				sb.append(" where tpi.SEVAARTH_ID=:sevaarthID ");
				Query selectQuery=ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult=selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					AddrRetirementDtls=(Object[]) lLstResult.get(0);
				}
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return AddrRetirementDtls;
			
		}
		
		public List getFamilyCaseDtls(String sevaarthID){
			
			List lLstFamilyDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT tpf.NAME,to_char(tpf.BIRTH_DATE,'DD/MM/YYYY'),tpf.RELATION,tpf.HANDICAPE_FLAG FROM TRN_PNSNPROC_FAMILYDTLS tpf ");
				sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tpf.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" where tpi.SEVAARTH_ID=:sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					lLstFamilyDtls=lLstResult;
					
				}
					
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return lLstFamilyDtls;
		}
		
		
		public List PensionerNomineeDtls(String sevaarthID){
			
			
			List lLstNomineeDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT nvl(tpn.NAME,' '),tpp.PNSNR_ADDR_FLAT || ',' || tpp.PNSNR_ADDR_ROAD || ',' || tpp.PNSNR_ADDR_AREA || ',' || cdm.DISTRICT_NAME || '-' || tpp.PNSNR_ADDR_PINCODE || ',' || csm.STATE_NAME,  ");
				sb.append(" tpn.RELATION,tpn.PERCENTAGE,tpn.ALTERNATE_NOMINEE ");
				sb.append(" FROM TRN_PNSNPROC_NOMINEEDTLS tpn ");
				sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tpn.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" join TRN_PNSNPROC_PNSNRDTLS tpp on tpn.INWARD_PENSION_ID=tpp.INWARD_PENSION_ID ");
				sb.append(" join CMN_STATE_MST csm on csm.STATE_ID=tpp.PNSNR_ADDR_STATECODE ");
				sb.append(" join CMN_DISTRICT_MST cdm on cdm.DISTRICT_ID=tpp.PNSNR_ADDR_DISTCODE ");
				sb.append(" where tpi.SEVAARTH_ID=:sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					lLstNomineeDtls=lLstResult;
				}
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return lLstNomineeDtls;
		}
		
		
		
		public List getServiceBrkDtls(String sevaarthID){
			
			List lLstServiceBrkDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT nvl(to_char(tps.BREAK_FROM_DATE,'DD/MM/YYYY'),' '),nvl(to_char(tps.BREAK_TO_DATE,'dd/mm/yyyy'),' '),tps.SERVICE_BREAKTYPE_LOOKUPID,clm.LOOKUP_NAME FROM TRN_PNSNPROC_PNSNRSERVCBREAK tps ");
				sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tps.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" join CMN_LOOKUP_MST clm on clm.LOOKUP_ID=tps.SERVICE_BREAKTYPE_LOOKUPID");
				sb.append(" where tpi.SEVAARTH_ID=:sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					lLstServiceBrkDtls=lLstResult;
				}
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return lLstServiceBrkDtls;
		}
		
		
		public List getAvgPayDtls(String sevaarthID){
			
			List lLstAvgPayDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT to_char(tpavg.FROM_DATE,'DD/MM/YYYY'),tpavg.BASIC,tpavg.DP,tpavg.NPA,tpavg.TOTAL,tppay.GRAND_TOTAL,tppay.AVERAGE_PAY FROM TRN_PNSNPROC_AVG_PAY_CALC tpavg ");
				sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tpavg.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" join TRN_PNSNPROC_PNSNRPAY tppay on tppay.INWARD_PENSION_ID=tpavg.INWARD_PENSION_ID ");
				sb.append(" where tpi.SEVAARTH_ID=:sevaarthID order by tpavg.FROM_DATE ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					lLstAvgPayDtls=lLstResult;
				}
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return lLstAvgPayDtls;
		}
		
		
		public List getAdvanceDtls(String sevaarthID){
			
			List lLstAdvanceDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT tpa.ADVNCEBAL_TYPE_ID,tpa.ADVANCE_AMOUNT,tpa.ADVANCE_SCHEME_CODE FROM TRN_PNSNPROC_ADVNCEBAL tpa ");
				sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tpa.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" where tpi.SEVAARTH_ID=:sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size() > 0 && lLstResult!=null){
					lLstAdvanceDtls=lLstResult;
				}
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return lLstAdvanceDtls;
		}
			
		
		public Object[] getPensionerPayDtls(String sevaarthID){
			
			
			Object[] lLstPensionerPayDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT hes.SCALE_DESC,tpp.GRADE_PAY,tpp.AVERAGE_PAY,tpp.basic_pay FROM TRN_PNSNPROC_PNSNRPAY tpp "); 
				sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tpp.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" join HR_EIS_SCALE_MST hes on tpp.LAST_PAY_SCALE=hes.SCALE_ID ");
				sb.append(" join mst_dcps_emp mde on tpi.sevaarth_id=mde.sevarth_id ");
				sb.append(" where tpi.SEVAARTH_ID=:sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size()!=0){
					lLstPensionerPayDtls=(Object[]) lLstResult.get(0);
				}
				
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return lLstPensionerPayDtls;
		
		}
		
		public Object[] getPensionCalcDtls(String sevaarthID){
			
			Object[] lLstPensionCalcDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT to_char(tpc.CVP_APPLICATION_DATE,'dd-mm-yyyy'),tpi.COMMUTE_VAL,tpc.CVP_MON_AMOUNT,tpc.CVP_AMOUNT,tpc.FP1_AMOUNT,tpc.FP2_AMOUNT,tpc.dcrg_amount,tpc.pension_total_amount  "); 
				sb.append(" FROM TRN_PNSNPROC_PNSNCALC tpc ");
				sb.append(" join TRN_PNSNPROC_INWARDPENSION tpi on tpc.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" where tpi.SEVAARTH_ID=:sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size()!=0){
					lLstPensionCalcDtls=(Object[]) lLstResult.get(0);
				}
			}catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return lLstPensionCalcDtls;
		
		}
		
public List getPensionType(String sevaarthID){
			
		List lPesionType=null;
			try{
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT pension_type from trn_pnsnproc_inwardpension ");
				sb.append(" where SEVAARTH_ID=:sevaarthID ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				selectQuery.setParameter("sevaarthID", sevaarthID);
				lPesionType = selectQuery.list();
			}
			catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return lPesionType;
		
}


public Object[] getprovDtls(String sevaarthID){
			
	Object[] lprovDtls=null;
			try{
				List lLstResult=null;
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT nvl(to_char(prov.APP_FROM_DATE,'dd-mm-yyyy'),' '),nvl(to_char(prov.APP_TO_DATE,'dd-mm-yyyy'),' '),prov.PENSION_AMOUNT from  TRN_PNSNPROC_PROVISIONAL_PAID prov ");
				sb.append("join trn_pnsnproc_inwardpension tpi on prov.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
				sb.append(" where tpi.SEVAARTH_ID='"+sevaarthID+"' ");
				Query selectQuery = ghibSession.createSQLQuery(sb.toString());
				logger.info("selectQuery getprovDtls:"+selectQuery);
				//selectQuery.setParameter("sevaarthID", sevaarthID);
				lLstResult = selectQuery.list();
				if(lLstResult.size()!=0){
					lprovDtls=(Object[]) lLstResult.get(0);
				}
			}
			catch (Exception e) {
				logger.error("Error is :" + e);
				e.printStackTrace();
			}
			return lprovDtls;
		
}

public int updateDdoCodeForNull(String sevaarthID){

	int count=0;
	List lLstResult=null;
	Object[] ddoCode=null;
	List ddoResult=null;
	int ddoResult1=0;
	try
	{
	StringBuilder sb = new StringBuilder();
	sb.append("SELECT nvl(ddo_code,0) from mst_dcps_emp  ");
	sb.append(" where SEVARTH_ID=:sevaarthID ");
	Query selectQuery = ghibSession.createSQLQuery(sb.toString());
	selectQuery.setParameter("sevaarthID", sevaarthID);
	lLstResult = selectQuery.list();
	if(lLstResult.size()!=0){
		//ddoCode=(Object[]) lLstResult.get(0);
		String ddoCodeValue= lLstResult.get(0).toString();
		System.out.println("ddoCodeValue******"+ddoCodeValue);
			if(ddoCodeValue.equals("0")){
			
				logger.info("Inside if");
				StringBuilder sb1 = new StringBuilder();
				sb1.append("SELECT ddo_code from TRN_PNSNPROC_INWARDPENSION  ");
				sb1.append(" where SEVAARTH_ID=:sevaarthID ");
				Query selectQuery1 = ghibSession.createSQLQuery(sb1.toString());
				selectQuery1.setParameter("sevaarthID", sevaarthID);			
				ddoResult=selectQuery1.list();
				
				StringBuilder sb2 = new StringBuilder();
				sb2.append("update mst_dcps_emp set ddo_code=:ddoResult  ");
				sb2.append(" where SEVARTH_ID=:sevaarthID ");
				Query selectQuery2 = ghibSession.createSQLQuery(sb2.toString());
				logger.info("Query is ****"+sb2.toString());
				selectQuery2.setParameter("sevaarthID", sevaarthID);	
				selectQuery2.setParameterList("ddoResult",ddoResult);
				
				ddoResult1 = selectQuery2.executeUpdate();
				logger.info("No of rows updated**"+ddoResult1);
				
				
				
		}
	}
	}
	catch (Exception e) {
		logger.error("Error is :" + e);
		e.printStackTrace();
	}
return count;
}
		
//Added by shraddha for Gratuity DDO Code

public Object[] getGratDdoDtls(String sevaarthID){

	Object[] lgratDdoDtls=null;
	try{
		List lLstResult=null;
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT tpi.GRAT_DDO_CODE,mdd.off_name || ',' || mdd.ADDRESS1 || ',' || csm.STATE_NAME || ',' || cdm.DISTRICT_NAME || '-' || mdd.OFFICE_PIN ");
		sb.append("FROM TRN_PNSNPROC_INWARDPENSION tpi  ");
		sb.append("join MST_DCPS_EMP mde on tpi.SEVAARTH_ID=mde.SEVARTH_ID ");
		sb.append("join MST_DCPS_DDO_OFFICE mdd on tpi.GRAT_DDO_CODE=mdd.DDO_CODE and mdd.DDO_OFFICE='Yes'  ");
		sb.append("join ORG_DDO_MST odm on tpi.GRAT_DDO_CODE=odm.DDO_CODE  ");
		sb.append("join CMN_STATE_MST csm on mdd.STATE=csm.STATE_ID  ");
		sb.append("join CMN_DISTRICT_MST cdm on mdd.DISTRICT=cdm.DISTRICT_ID  ");
		sb.append("join ORG_EMP_MST oem on mde.ORG_EMP_MST_ID=oem.EMP_ID  ");
		sb.append(" where tpi.SEVAARTH_ID='"+sevaarthID+"' ");
		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		logger.info("selectQuery getprovDtls:"+selectQuery);
		lLstResult = selectQuery.list();
		if(lLstResult.size()!=0){
			lgratDdoDtls=(Object[]) lLstResult.get(0);
		}
	}
	catch (Exception e) {
		logger.error("Error is :" + e);
		e.printStackTrace();
	}
	return lgratDdoDtls;

}

//Added by shraddha for Family Pensioner

public Object[] getFamilyPensionerDtls(String sevaarthID){

	List lLstResult=null;
	StringBuilder sb = new StringBuilder();
	Object[] familyPensioner=null;
	
	try{
	sb.append("SELECT tpf.NAME,tpf.RELATION,to_char(tpf.BIRTH_DATE,'dd/MM/yyyy') ");
	sb.append(" FROM TRN_PNSNPROC_FAMILYDTLS tpf ");
	sb.append("join TRN_PNSNPROC_INWARDPENSION tpi on tpi.INWARD_PENSION_ID=tpf.INWARD_PENSION_ID ");
	sb.append("where tpi.SEVAARTH_ID=:sevaarthID  and tpf.FAMLY_PEN_FLAG='Y' ");
	Query selectQuery = ghibSession.createSQLQuery(sb.toString());
	selectQuery.setParameter("sevaarthID", sevaarthID);
	logger.info("Family pensioner query**"+sb.toString());
	lLstResult = selectQuery.list();
	
if(lLstResult.size()!=0){
		familyPensioner=(Object[]) lLstResult.get(0);
	}

	}
	catch (Exception e) {
		logger.error("Error is :" + e);
		e.printStackTrace();
	}
	return familyPensioner;
}

//Added by shraddha to validate Sevarth Id
public String checkSevarthId(String sevaarthID,Long gLngPostId){
	
	List lLstResult=null;
	List lLstResult1=null;
	List lLstResult2=null;
	StringBuilder sb = new StringBuilder();
	String chckSevarthId="valid";
	String ddoCode="";
	String ddoCode1="";
	
	try{
		sb.append("select * from mst_dcps_emp where sevarth_id=:sevaarthID ");
		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		selectQuery.setParameter("sevaarthID", sevaarthID);
		lLstResult = selectQuery.list();
		logger.info("lLstResult.size()***"+lLstResult.size());
		if(lLstResult.size()==0){
			chckSevarthId="Invalid";
		}
		else{
			
			StringBuilder sb1 = new StringBuilder();
			sb1.append("SELECT ddo.DDO_CODE FROM RLT_DCPS_DDO_ASST ast ");
			sb1.append("join org_ddo_mst ddo on ast.DDO_POST_ID=POST_ID ");
			sb1.append("where ast.ASST_POST_ID=:gLngPostId "); 	
			Query selectQuery1 = ghibSession.createSQLQuery(sb1.toString());
			selectQuery1.setParameter("gLngPostId", gLngPostId);
			lLstResult1 = selectQuery1.list();
			if(lLstResult1.size()!=0){
				ddoCode=lLstResult1.get(0).toString();
			}
			StringBuilder sb2 = new StringBuilder();
			sb2.append("SELECT pen_ddo_code from trn_pnsnproc_inwardpension ");
			sb2.append("where sevaarth_id=:sevaarthID  "); 	
			Query selectQuery2 = ghibSession.createSQLQuery(sb2.toString());
			selectQuery2.setParameter("sevaarthID", sevaarthID);
			lLstResult2 = selectQuery2.list();
			if(lLstResult2.size()!=0){
				ddoCode1=lLstResult2.get(0).toString();
			}
			if(lLstResult2.size()==0){
			 chckSevarthId="noCase";
			}
			if(lLstResult2.size()!=0 && lLstResult1.size()!=0){
			if(!ddoCode.equals(ddoCode1)){
				chckSevarthId="inavalidLocDdo";
			}
			}
		}
		
	}
	
	catch (Exception e) {
		logger.error("Error is :" + e);
		e.printStackTrace();
	}
	return chckSevarthId;
}

//Added by shraddha for Provisional Pension details
public List getProvPension(String sevaarthID){

	List lLstResult=null;
	StringBuilder sb = new StringBuilder();
	List provPension=null;
	
	try{
	sb.append("SELECT (case when tpp.SANCTION_AUTHO_NAME='N' then 'AG NAPUR' when tpp.SANCTION_AUTHO_NAME='M' THEN 'AG MUMBAI'  ELSE '' END), "); 
	sb.append("	to_char(tpp.SANCTION_DATE,'dd/mm/yyyy'),tpp.PENSION_AMOUNT,tpp.VOUCHER_NO,to_char(tpp.VOUCHER_DATE,'dd/mm/yyyy')  FROM TRN_PNSNPROC_PROVISIONAL_PAID tpp "); 
	sb.append("join TRN_PNSNPROC_INWARDPENSION tpi on tpp.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
	sb.append("where tpi.SEVAARTH_ID=:sevaarthID AND tpp.BILL_TYPE='P' ");
	Query selectQuery = ghibSession.createSQLQuery(sb.toString());
	selectQuery.setParameter("sevaarthID", sevaarthID);
	logger.info("Family pensioner query**"+sb.toString());
	lLstResult = selectQuery.list();
	
	if(lLstResult.size() > 0 && lLstResult!=null){
		provPension=lLstResult;
	}

	}
	catch (Exception e) {
		logger.error("Error is :" + e);
		e.printStackTrace();
	}
	return provPension;
}

public List getProvGrat(String sevaarthID){

	List lLstResult=null;
	StringBuilder sb = new StringBuilder();
	List provGrat=null;
	
	try{
	sb.append("SELECT (case when tpp.SANCTION_AUTHO_NAME='N' then 'AG NAPUR' when tpp.SANCTION_AUTHO_NAME='M' THEN 'AG MUMBAI'  ELSE '' END), "); 
	sb.append("	to_char(tpp.SANCTION_DATE,'dd/mm/yyyy'),tpp.PENSION_AMOUNT,tpp.VOUCHER_NO,to_char(tpp.VOUCHER_DATE,'dd/mm/yyyy')  FROM TRN_PNSNPROC_PROVISIONAL_PAID tpp "); 
	sb.append("join TRN_PNSNPROC_INWARDPENSION tpi on tpp.INWARD_PENSION_ID=tpi.INWARD_PENSION_ID ");
	sb.append("where tpi.SEVAARTH_ID=:sevaarthID AND tpp.BILL_TYPE='G' ");
	Query selectQuery = ghibSession.createSQLQuery(sb.toString());
	selectQuery.setParameter("sevaarthID", sevaarthID);
	logger.info("Family pensioner query**"+sb.toString());
	lLstResult = selectQuery.list();
	
	if(lLstResult.size() > 0 && lLstResult!=null){
		provGrat=lLstResult;
	}

	}
	catch (Exception e) {
		logger.error("Error is :" + e);
		e.printStackTrace();
	}
	return provGrat;
}


}

