package com.tcs.sgv.eis.zp.ReportingDDO.dao;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;

public class ReportingDDODaoImpl extends GenericDaoHibernateImpl implements ReportingDDODao
{
	Session hibSession=null;
	public ReportingDDODaoImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		hibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	@Override
	public List getDistrict(long stateId)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT DISTRICT_code,DISTRICT_NAME FROM CMN_DISTRICT_MST where STATE_ID = "+stateId+" and LANG_ID=1 and ACTIVATE_FLAG=1";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			logger.error("---------------------getDistrict Size"+sqlQuery.toString());
			temp= sqlQuery.list();
			logger.error("---------------------getDistrict Size"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ReportingDDODaoImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public List getTaluka(long districtId)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT TALUKA_ID,TALUKA_name FROM CMN_TALUKA_MST where district_id="+districtId+" and lang_id=1 and ACTIVATE_FLAG=1";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			logger.error("---------------------getTaluka Size"+sqlQuery.toString());
			temp= sqlQuery.list();
			logger.error("---------------------getTaluka Size"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ReportingDDODaoImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	
	public List getTreasury(){
		List temp=null;
		try
		{		
			String branchQuery = "SELECT * FROM CMN_LOCATION_MST where DEPARTMENT_ID= 100003";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			logger.error("---------------------get Treasury query"+sqlQuery.toString());
			temp= sqlQuery.list();
			logger.error("---------------------getTreasurySize"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ReportingDDODaoImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	
	public List getSubTreasury(long treasuryId){
		List temp=null;
		try
		{		
			String branchQuery = "SELECT loc_id,loc_name from CMN_LOCATION_MST where DEPARTMENT_ID= 100006 and PARENT_LOC_ID="+treasuryId;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			logger.error("---------------------get getSubTreasury query"+sqlQuery.toString());
			temp= sqlQuery.list();
			logger.error("---------------------getSubTreasury"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ReportingDDODaoImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	
	public void updateType(String ddoCode)
	{
		String branchQuery = "update org_ddo_mst set DDO_TYPE = 1 where DDO_CODE=:ddoCode";
		Query sqlQuery= hibSession.createSQLQuery(branchQuery);
		sqlQuery.setString("ddoCode", ddoCode);
		logger.error("---------------------get getSubTreasury query"+sqlQuery.toString());
		sqlQuery.executeUpdate();
		
	}
	
	public List getLvl2DDo(){
		
		List temp=null;
		try
		{		
			String branchQuery = "select a.ddo_code,b.admin_office from ORG_DDO_MST a,reporting_ddo_mst b where b.ddo_code=a.ddo_code and b.status='Y' order by DDO_ID desc";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			logger.error("---------------------get getLvl2DDo query"+sqlQuery.toString());
			temp= sqlQuery.list();
			logger.error("---------------------getLvl2DDo"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in getLvl2DDo \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	
	/*public List getSeqTable(String lStrLocCode){
		List temp=null;
		try
		{		
			String branchQuery = "SELECT table_name,pk_length FROM CMN_TABLE_SEQ_MST where LOCATION_CODE= '2000032'";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			logger.error("---------------------getSeqTable"+sqlQuery.toString());
			temp= sqlQuery.list();
			logger.error("---------------------getSeqTable"+temp.size());
			Iterator IT = temp.iterator();
			Object o[]= null;
			String locIdStr= "";
			String tableNname="";
			String pkLength="";
			while(IT.hasNext()){
				o= (Object [])IT.next();
				tableNname= o[0].toString();
				pkLength=o[1].toString();
				insertSeqMst(tableNname,pkLength,lStrLocCode);
				
			}
			
		}
		catch(Exception e){
			logger.error("Error in getSeqTable \n " + e);
			e.printStackTrace();
		}
		return temp;
	}*/
	public List getSeqTable(String lStrLocCode,Map objectArgs,Long seqId ){
		List temp=null;
		try
		{		
			/*String branchQuery = "SELECT table_name,pk_length FROM CMN_TABLE_SEQ_MST where LOCATION_CODE= '2000032'";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			logger.error("---------------------getSeqTable"+sqlQuery.toString());
			temp= sqlQuery.list();
			logger.error("---------------------getSeqTable"+temp.size());
			Iterator IT = temp.iterator();
			Object o[]= null;
			String locIdStr= "";
			String tableNname="";
			String pkLength="";
			Integer i=1;

			while(IT.hasNext()){
				o= (Object [])IT.next();
				tableNname= o[0].toString();
				pkLength=o[1].toString();				
				Long seqId = IFMSCommonServiceImpl.getNextSeqNum("CMN_TABLE_SEQ_MST",objectArgs);
				seqId=seqId+i;
				insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
				i++;

			}*/

			ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
			//Long seqId = rp.getNextSeqNum("CMN_TABLE_SEQ_MST");
			Integer pkLength=0;
			StringBuilder tableNname=new StringBuilder();
			tableNname.append("CMN_TALUKA_MST");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();tableNname=new StringBuilder();
			
			//2
			tableNname.append("HR_EIS_EMP_COMPONENT_GRP_MST");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			
			//3
			tableNname.append("HR_EIS_EMP_COMPONENT_MPG");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//4
			tableNname.append("HR_LOAN_EMP_DTLS");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//5
			tableNname.append("HR_LOAN_EMP_INT_RECOVER_DTLS");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//6
			tableNname.append("HR_LOAN_EMP_PRIN_RECOVER_DTLS");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//7
			tableNname.append("HR_LOAN_RECOVERY_CHALLAN");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//8
			tableNname.append("HR_PAYBILL_TRN_LOG");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//9
			tableNname.append("HR_PAYFIX_MST");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//10
			tableNname.append("HR_PAY_DEDUCTION_DTLS");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//11
			tableNname.append("HR_PAY_EMPALLOW_MPG");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//12
			tableNname.append("HR_PAY_PAYBILL");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//13
			tableNname.append("HR_PAY_PAYBILL_BILL_COMP_MPG");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//14
			tableNname.append("HR_PAY_PAYBILL_EMP_COMP_MPG");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//15
			tableNname.append("HR_PAY_PAYBILL_LOAN_DTLS");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//16
			tableNname.append("HR_PAY_PAYBILL_UPDATE_LOG");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//17
			tableNname.append("HR_PAY_PAYSLIP");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//18
			tableNname.append("HR_PAY_PAYSLIP_NON_GOVT");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//19
			tableNname.append("HST_DCPS_CHANGES");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//20
			tableNname.append("HST_DCPS_CONTRIBUTION");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//21
			tableNname.append("HST_DCPS_NOMINEE_CHANGES");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//22
			tableNname.append("HST_DCPS_OFFICE_CHANGES");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//23
			tableNname.append("HST_DCPS_OTHER_CHANGES");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//24
			tableNname.append("HST_DCPS_PERSONAL_CHANGES");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//25
			tableNname.append("HST_DCPS_PHOTOANDSIGN_CHANGES");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//26
			tableNname.append("MST_DCPS_DDO_OFFICE");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//27
			tableNname.append("MST_DCPS_EMP");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//28
			tableNname.append("MST_DCPS_EMP_NMN");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//29
			tableNname.append("RLT_DCPS_DDO_SCHEMES");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//30
			tableNname.append("RLT_DCPS_PAYROLL_EMP");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//31
			tableNname.append("RLT_DCPS_PHY_FORM_STATUS");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//32
			tableNname.append("SH_DDO_OFFICE_RQT_MPG");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//33
			tableNname.append("TRN_DCPS_CHANGES");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//34
			tableNname.append("TRN_DCPS_CONTRIBUTION");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//35
			tableNname.append("TRN_DCPS_DEPUTATION_CONTRIBUTION");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//36
			tableNname.append("frm_login_audit");
			pkLength=10;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//37
			tableNname.append("hst_dcps_emp_deputation");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//38
			tableNname.append("hst_dcps_emp_details");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//39
			tableNname.append("mst_dcps_bill_group");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//40
			tableNname.append("mst_dcps_contri_voucher_dtls");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//41
			tableNname.append("mst_dcps_feedback");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//42
			tableNname.append("mst_dcps_tieri_cntrnbtn");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//43
			tableNname.append("paybill_head_mpg");
			pkLength=19;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//44
			tableNname.append("rlt_dcps_billgroup_classgroup");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//45
			tableNname.append("rlt_dcps_sixpc_yearly");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//46
			tableNname.append("trn_dcps_missing_credits_dtls");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			//47
			tableNname.append("trn_dcps_terminal_dtls");
			pkLength=20;
			insertSeqMst(tableNname,pkLength,lStrLocCode,seqId++);
			tableNname=null;tableNname=new StringBuilder();
			

		}
		catch(Exception e){
			logger.error("Error in getSeqTable \n " + e);
			e.printStackTrace();
		}
		return temp;
	}



	public synchronized Long getNextSeqNum() {
		Long seqId=null;
		StringBuffer sb= new StringBuffer();
		sb.append("select GENERATED_ID from CMN_TABLE_SEQ_MST where upper(table_name)='CMN_TABLE_SEQ_MST'");
		Query query = hibSession.createSQLQuery(sb.toString());
		seqId=Long.parseLong(query.uniqueResult().toString());		
		logger.info("seqId............"+seqId);
		Long seqNo= seqId+50;
		StringBuffer sb2= new StringBuffer();
		sb2.append("update CMN_TABLE_SEQ_MST set GENERATED_ID="+seqNo+" where (TABLE_NAME)= 'cmn_table_seq_mst'");
		Query query2 = hibSession.createSQLQuery(sb2.toString());
		query2.executeUpdate();
		return seqId;
	}
	
	public synchronized Long getNextSeqNum(int len) {
		Long seqId=null;
		StringBuffer sb= new StringBuffer();
		sb.append("select GENERATED_ID from CMN_TABLE_SEQ_MST where upper(table_name)='CONSOLIDATED_BILL_MPG'");
		Query query = hibSession.createSQLQuery(sb.toString());
		seqId=Long.parseLong(query.uniqueResult().toString()); 
		logger.info("seqId............"+seqId);
		Long seqNo= seqId+len+2;
		StringBuffer sb2= new StringBuffer();
		sb2.append("update CMN_TABLE_SEQ_MST set GENERATED_ID="+seqNo+" where upper(TABLE_NAME)= 'CONSOLIDATED_BILL_MPG'");
		Query query2 = hibSession.createSQLQuery(sb2.toString());
		query2.executeUpdate();
		return seqId;
		}

	/*private void insertSeqMst(String tableNname,String pkLength,String lStrLocCode){
		try
		{		
			StringBuffer sb = new StringBuffer();
	    	sb.append("INSERT INTO CMN_TABLE_SEQ_MST  VALUES (");
	    	sb.append("(select max(id)+1  from CMN_TABLE_SEQ_MST),'");
	    	sb.append(tableNname);
	    	sb.append("',1,");
	    	sb.append(pkLength);
	    	sb.append(",1,1,Sysdate,1,1,Sysdate,'");
	    	sb.append(lStrLocCode);
	    	sb.append("','N')");
			Query sqlQuery= hibSession.createSQLQuery(sb.toString());
			logger.error("---------------------insertSeqMst"+sqlQuery.toString());
			sqlQuery.executeUpdate();			
			
						
		}
		catch(Exception e){
			logger.error("Error in getSeqTable \n " + e);
			e.printStackTrace();
		}
		
	}*/
	private void insertSeqMst(StringBuilder tableNname,Integer pkLength,String lStrLocCode, Long seqId){
		try
		{		

			StringBuffer sb = new StringBuffer();
			logger.info("seqId..."+seqId);
			logger.info("pkLength..."+pkLength);

			sb.append("INSERT INTO CMN_TABLE_SEQ_MST VALUES (");
			sb.append(seqId+",'");
			sb.append(tableNname);
			sb.append("',1,");
			sb.append(pkLength);
			sb.append(",1,1,Sysdate,1,1,Sysdate,'");
			sb.append(lStrLocCode);
			sb.append("','N')");
			Query sqlQuery= hibSession.createSQLQuery(sb.toString());
			logger.error("---------------------insertSeqMst"+sqlQuery.toString());
			sqlQuery.executeUpdate();			


		}
		catch(Exception e){
			logger.error("Error in getSeqTable \n " + e);
			e.printStackTrace();
		}

	}
	public void insertOrgDdoMst(String lStrDdoCode,String lStrDdoName,String lStrDdoPrsnlName,Long lLngPostId,Long lLngUserIdCrtd,
			String lStrLocationCode,Long lLngPostIdCrtd,String depTLocCode,String hodLocCode,Long ddoType,Map inputMap)throws Exception
			
	{
		Long lLndDdoId = null;
		try{
			OrgDdoMst lObjOrgDdoMst = new OrgDdoMst();
			lLndDdoId = IFMSCommonServiceImpl.getNextSeqNum("org_ddo_mst", inputMap);
			lObjOrgDdoMst.setDdoId(lLndDdoId);
			lObjOrgDdoMst.setDdoCode(lStrDdoCode);
			lObjOrgDdoMst.setDdoName(lStrDdoName);
			lObjOrgDdoMst.setDdoPersonalName(lStrDdoPrsnlName);
			lObjOrgDdoMst.setPostId(lLngPostId);
			lObjOrgDdoMst.setLangId(1l);
			lObjOrgDdoMst.setCreatedBy(lLngUserIdCrtd);
			lObjOrgDdoMst.setCreatedByPost(lLngPostIdCrtd);
			lObjOrgDdoMst.setCreatedDate(DBUtility.getCurrentDateFromDB());
			lObjOrgDdoMst.setDbId(99l);
			lObjOrgDdoMst.setLocationCode(lStrLocationCode);
			lObjOrgDdoMst.setHodLocCode(hodLocCode);
			lObjOrgDdoMst.setDeptLocCode(depTLocCode);
			lObjOrgDdoMst.setddoType(ddoType);
			hibSession.save(lObjOrgDdoMst);
			hibSession.flush();
		}catch(Exception e){
			  logger.error(" Error is : " + e, e);
			  throw e;
		}
	}
	public void insertRepoDDO(String ddoCode, Long ddoType, Character status) {
	    try {
	        String query = "INSERT INTO REPORTING_DDO_MST (id, ddo_code, status, ddo_type) VALUES (:id, :ddoCode, :status, :ddoType)";

	        BigInteger nextId = (BigInteger) hibSession.createSQLQuery("SELECT MAX(id) + 1 FROM REPORTING_DDO_MST").uniqueResult();

	        if (nextId == null) {
	            nextId = BigInteger.ONE;
	        }

	        SQLQuery sqlQuery = hibSession.createSQLQuery(query);
	        sqlQuery.setParameter("id", nextId);
	        sqlQuery.setParameter("ddoCode", ddoCode);
	        sqlQuery.setParameter("status", status);
	        sqlQuery.setParameter("ddoType", ddoType);

	        sqlQuery.executeUpdate();
	        logger.error("---------------------insertRepoDDO" + sqlQuery.toString());
	    } catch (Exception e) {
	        logger.error("Error in insertRepoDDO", e);
	        e.printStackTrace();
	    }
	}

	public void insertWfOrgUsr(Long userId){
		try
		{		
			StringBuffer sb = new StringBuffer();
	    	sb.append("INSERT INTO WF_ORG_USR_MPG_MST VALUES ('");
	    	sb.append(userId);
	    	sb.append("',99,101)");	    	
			Query sqlQuery= hibSession.createSQLQuery(sb.toString());
			logger.error("---------------------insertWfOrgUsr"+sqlQuery.toString());
			sqlQuery.executeUpdate();			
			
						
		}
		catch(Exception e){
			logger.error("Error in insertWfOrgUsr \n " + e);
			e.printStackTrace();
		}
		
	}
	public void insertWfOrgpost(Long postId){
		try
		{		
			StringBuffer sb = new StringBuffer();
	    	sb.append("INSERT INTO wf_org_post_mpg_mst VALUES ('");
	    	sb.append(postId);
	    	sb.append("',99,101)");	    	
			Query sqlQuery= hibSession.createSQLQuery(sb.toString());
			logger.error("---------------------insertWfOrgpost"+sqlQuery.toString());
			sqlQuery.executeUpdate();			
			
						
		}
		catch(Exception e){
			logger.error("Error in insertWfOrgpost \n " + e);
			e.printStackTrace();
		}
		
	}
	
	public void insertWfOrgLoc(String locId){
		try
		{		
			StringBuffer sb = new StringBuffer();
	    	sb.append("INSERT INTO WF_ORG_LOC_MPG_MST VALUES ('");
	    	sb.append(locId);
	    	sb.append("',101)");	    	
			Query sqlQuery= hibSession.createSQLQuery(sb.toString());
			logger.error("---------------------insertWfOrgLoc"+sqlQuery.toString());
			sqlQuery.executeUpdate();			
			
						
		}
		catch(Exception e){
			logger.error("Error in insertWfOrgpost \n " + e);
			e.printStackTrace();
		}
		
	}
	
	
	public List getSubDDOs(String postId){
    	List ddoDtls = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getSubDDOs DAO---");
    	sb.append("SELECT ZP_DDO_CODE  FROM RLT_ZP_DDO_MAP where rept_DDO_post_id="+postId);
    	logger.info("---- getSubDDOs DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	ddoDtls = query.list();
    	return ddoDtls;
    	
    }
	
	//public List get 
	public List getDDODtls(String DDOCode){
    	List ddoDtls = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- vivek DAo---");
    	sb.append("SELECT LOCATION_CODE,HOD_LOC_CODE,post_ID FROM ORG_DDO_MST where DDO_CODE='"+DDOCode+"'");
    	logger.info("---- vivek DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	ddoDtls = query.list();
    	return ddoDtls;
    	
    }
    
    public List getSubDDOList(String DDOCode){
    	List ddoDtls = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getSubDDOList DAO---");
    	sb.append("SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE='"+DDOCode+"'");
    	logger.info("---- getSubDDOList DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	ddoDtls = query.list();
    	return ddoDtls;
    	
    }
    
    public List getSchemeCode(String ddoCode){
    	List bill = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getBilId DAO---");
    	sb.append("select scheme_code,Loc_iD from mst_dcps_bill_group where ddo_code='"+ddoCode+"'");
    	logger.info("---- getBilId DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	bill = query.list();
    	return bill;    	
    }
    
    
    
    
    
    public List getSchemeCodeByPost(Long post)
    {
    	List bill = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getSchemeCodeByPost DAO---");
    	sb.append("SELECT scheme_id,scheme_code,scheme_name FROM MST_SCHEME where scheme_code in(select scheme_code from mst_dcps_bill_group where ddo_code in(select Zp_ddo_code from rlt_zp_DDO_map where rept_DDO_post_id="+post+" ))");
    	logger.info("---- getSchemeCodeByPost DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	bill = query.list();
    	return bill; 
    	
    }
    public List getGrossNet(String locId,String month,String year,String schemeCode)
    {
    	List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getGross---");
    	sb.append("SELECT SUM(BILL_GROSS_AMT), SUM(BILL_NET_AMOUNT) FROM PAYBILL_HEAD_MPG where BILL_NO in (SELECT BILL_GROUP_ID FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+ schemeCode+"') and loc_id in("+locId+") and PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year);
    	logger.info("---- getBilId DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;    	
    }
    
    
    public List getAllowDed(String paybillId,String month,String year,String schemeCode)
    {
    	List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getAllowDed---");
    	sb.append("SELECT sum(PT), sum(GPF_GRP_ABC + GPF_GRP_D) FROM HR_PAY_PAYBILL where PAYBILL_GRP_ID in ("+paybillId+") and PAYBILL_MONTH="+month+" AND PAYBILL_YEAR="+year+" and approve_flag=0");
    	logger.info("---- getAllowDed DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;    	
    }
    
    
    public List getGrossNetByPayBillId(String paybillId)
    {
    	List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getGrossNetByPayBillId---");
    	sb.append("SELECT SUM(BILL_GROSS_AMT), SUM(BILL_NET_AMOUNT) FROM PAYBILL_HEAD_MPG where PAYBILL_ID in("+paybillId+") and approve_flag=0");
    	logger.info("---- getGrossNetByPayBillId DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;    	
    }
        
    public List getAllowDedByPayBillId(String paybillId)
    {
    	List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getAllowDedByPayBillId---");	
    //	sb.append("SELECT sum(PT), sum(GPF_GRP_ABC + GPF_GRP_D+GPF_ADV_GRP_ABC+GPF_ADV_GRP_D+GPF_ABC_ARR_MR+GPF_D_ARR_MR +SVNPC_GPF_ARR_DEDU +SVNPC_GPF_RECO),sum(DCPS + DCPS_DELAY + DCPS_PAY + DCPS_DA + JanJulGisArr +SVNPC_DCPS_RECO),sum(GIS),sum(HRR + SERVICE_CHARGE),sum(ACC_POLICY),sum(REVENUE_STAMP) FROM HR_PAY_PAYBILL where PAYBILL_GRP_ID in ("+paybillId+")");
    	sb.append("SELECT sum(PT), sum(GPF_GRP_ABC + GPF_GRP_D+GPF_ADV_GRP_ABC+GPF_ADV_GRP_D+GPF_ABC_ARR_MR+GPF_D_ARR_MR +SVNPC_GPF_ARR_DEDU +SVNPC_GPF_RECO),sum(DCPS + DCPS_DELAY + DCPS_PAY + DCPS_DA + JanJulGisArr +SVNPC_DCPS_RECO),sum(GIS),sum(HRR + SERVICE_CHARGE),sum(ACC_POLICY),sum(NPS_EMPLR_CONTRI_DED)  FROM HR_PAY_PAYBILL where PAYBILL_GRP_ID in ("+paybillId+")");
    	logger.info("---- getAllowDedByPayBillId DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- getAllowDedByPayBillId---"+sb);
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;    	
    }
    
    public List getSchemeDtls(String schemeCode,String subSchemecode)
    {

    	List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getSchemeDtls---");
    	//sb.append("select MAJOR_HEAD,MINOR_HEAD,SUB_HEAD,SCHEME_NAME,DEMAND_CODE,SUB_MAJOR_HEAD,SUB_MINOR_HEAD FROM MST_SCHEME where SCHEME_CODE='"+schemeCode+"'");
    	//modified by saurabh
    	sb.append("select MAJOR_HEAD,MINOR_HEAD,SUB_HEAD,SCHEME_NAME,DEMAND_CODE,schMst.DISCRIPTION FROM MST_SCHEME mst  ");
		sb.append("left outer join SCHEME_SUBSCHEME sch on sch.SCHEME_CD = mst.SCHEME_CODE ");
		sb.append("left outer join SUBSCHEME_MASTER schMst on schMst.SUBSCHEME_CD = sch.SUBSCHEME_CD ");
		sb.append("where mst.SCHEME_CODE='"+schemeCode+"'  ");
		if(!subSchemecode.equals("-") && !subSchemecode.equals("-1") && !subSchemecode.equals(null))
		{
			sb.append(" and schMst.SUBSCHEME_CD = '"+subSchemecode+"' ");
		}
    	logger.info("---- getSchemeDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp; 
    	
    }
    
    public String getTreasuryByPostId(Long postId)
    {

    	String temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getTreasuryByPostId---");
    	sb.append("SELECT LOC_NAME FROM CMN_LOCATION_MST where LOC_ID=(SELECT LOCATION_CODE FROM org_ddo_mst where post_id="+postId+")");
    	logger.info("---- getTreasuryByPostId DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	temp = query.uniqueResult().toString();
    	//logger.info("---- query---"+temp.size());
    	return temp; 
    	
    }
    
    public List getReptDdo(Long postId)
    {
    	List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getReptDdo---");
    	sb.append("SELECT REPT_DDO_POST_ID,FINAL_DDO_POST_ID,SPECIAL_DDO_POST_ID,ZPLEVEL from RLT_ZP_DDO_MAP where REPT_DDO_POST_ID="+postId);
    	logger.info("---- getTreasuryByPostId DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- getReptDdo---"+sb);
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp; 
    	
    }
    
	public void updateUserMstSeq(){
		try
		{		
			StringBuffer sb = new StringBuffer();
	    	sb.append("update CMN_TABLE_SEQ_MST set GENERATED_ID= GENERATED_ID+1 where TABLE_NAME='org_user_mst'");
			Query sqlQuery= hibSession.createSQLQuery(sb.toString());
			logger.error("---------------------updateUserMstSeq"+sqlQuery.toString());
			sqlQuery.executeUpdate();			
			
						
		}
		catch(Exception e){
			logger.error("Error in getSeqTable \n " + e);
			e.printStackTrace();
		}
		
	}
	
	public String getDDoDtls(String ddoCode){
		String temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getDDoDtls---");
    	sb.append("SELECT loc_name FROM CMN_LOCATION_MST where LOC_ID=(select LOCATION_CODE FROM org_ddo_mst where DDO_CODE="+ddoCode+" )");
    	logger.info("---- getDDoDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	temp = query.uniqueResult().toString();
    	return temp;
		
	}
	public String getDsgDtls(String ddoCode){
		String temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getDsgDtls---");
    	sb.append("SELECT POST_SHORT_NAME FROM ORG_POST_DETAILS_RLT where post_ID=(select post_id FROM org_ddo_mst where DDO_CODE="+ddoCode+" )");
    	logger.info("---- getDsgDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	temp = query.uniqueResult().toString();
    	return temp;
		
	}
	
	public List getDDounderDept(String admincode,String fieldLoc, String treasuryId){
		List result=null;
		Session session=getSession();
		logger.info("---- getDDounderDept---");
		logger.info("admincode "+admincode);
		logger.info("fieldLoc "+fieldLoc);
		StringBuffer sb = new StringBuffer();		
		//DIRECTORATE OF TECHNICAL EDUCATION 
		/*commented by roshan on 15-12-12
		 * if(fieldLoc.equals("99100001287") || fieldLoc.equals("99100001288") || fieldLoc.equals("99100001289")){
    	sb.append("SELECT DDO_CODE,DDO_NAME FROM ORG_DDO_MST where DEPT_LOC_CODE="+admincode +" and HOD_LOC_CODE=10678 and ACTIVATE_FLAG=1");
		}
		//higher education
		else if(fieldLoc.equals("99100001274") || fieldLoc.equals("99100001284")){
			sb.append("SELECT DDO_CODE,DDO_NAME FROM ORG_DDO_MST where DEPT_LOC_CODE="+admincode +" and HOD_LOC_CODE=10677 and ACTIVATE_FLAG=1");
		}
		//vocational field department
		else if(fieldLoc.equals("99100001285") || fieldLoc.equals("99100001286")){
			sb.append("SELECT DDO_CODE,DDO_NAME FROM ORG_DDO_MST where DEPT_LOC_CODE="+admincode +" and HOD_LOC_CODE=10679 and ACTIVATE_FLAG=1");
		}
		else sb.append("SELECT DDO_CODE,DDO_NAME FROM ORG_DDO_MST where DEPT_LOC_CODE="+admincode +" and HOD_LOC_CODE="+fieldLoc+" and ACTIVATE_FLAG=1");*/
    	
		
		/*
		 //added by roshan.
		if(fieldLoc.equals("99100001361") || fieldLoc.equals("99100001355") || fieldLoc.equals("10677")){
	    	sb.append("SELECT DDO_CODE,DDO_NAME FROM ORG_DDO_MST where HOD_LOC_CODE=10677 and ACTIVATE_FLAG=1 AND DDO_CODE in ('1309002205', '3401002205', '5301002205', '2501002205', '3101002205', '6101002205', '7101002205', '2201002205', '4601002205')");
			}
			//higher education
			else if(fieldLoc.equals("99100001360") || fieldLoc.equals("99100001359")|| fieldLoc.equals("99100001358")||fieldLoc.equals("10678"))
			{
				sb.append("SELECT DDO_CODE,DDO_NAME FROM ORG_DDO_MST where DEPT_LOC_CODE="+admincode +" and HOD_LOC_CODE=10678 and ACTIVATE_FLAG=1 AND DDO_name like 'Joint%'");
			}
			//vocational field department
			else if(fieldLoc.equals("99100001285") || fieldLoc.equals("99100001286")||fieldLoc.equals("10679")){
				sb.append("SELECT DDO_CODE,DDO_NAME FROM ORG_DDO_MST where HOD_LOC_CODE = 10679 and ACTIVATE_FLAG=1 and DDO_CODE  in (7101002070,1201002070,1301002070,1401002070,1501002070,2201002070,2301002070,2401002070,2501002070,2601002070,5101002070,5201002070,5301002070,5401002070,3101002070,3201002070,3301002070,3401002070,3701002070,3601002070,3501002070,6101002070,6201002070,6301002070,6401002070,4601002070,4801002070,4901002070,4701002070,4501002070,7101012070,4401002070,6501002070,3801002070,5501002070)");
			}
		
		   //Directorate of Art
			else if(fieldLoc.equals("10676")){
				sb.append("SELECT DDO_CODE,DDO_NAME FROM ORG_DDO_MST where HOD_LOC_CODE = 10676 and ACTIVATE_FLAG=1 and DDO_CODE like '%2149%'");
			}
			else sb.append("SELECT DDO_CODE,DDO_NAME FROM ORG_DDO_MST where DEPT_LOC_CODE="+admincode +" and HOD_LOC_CODE="+fieldLoc+" and ACTIVATE_FLAG=1");
			*/
		
		//START Edited by samadhan
		//added by roshan.
		if(fieldLoc.equals("99100001361") || fieldLoc.equals("99100001355") || fieldLoc.equals("10677")){
	    	sb.append("SELECT ddo.DDO_CODE,ddo.DDO_NAME FROM ORG_DDO_MST ddo, MST_DCPS_DDO_OFFICE ddooffice,CMN_LOCATION_MST loc ");
	    	sb.append(" where ddo.HOD_LOC_CODE=10677 and ddo.ACTIVATE_FLAG=1 and ddo.DDO_CODE = ddooffice.DDO_CODE ");
	    	sb.append(" AND ddo.DDO_CODE in ('1309002205', '3401002205', '5301002205', '2501002205', '3101002205', '6101002205', '7101002205', '2201002205', '4601002205') ");
	    	sb.append(" and ddooffice.DISTRICT =loc.LOC_DISTRICT_ID and loc.LOC_ID = "+treasuryId+" ");
			}
			//higher education
			else if(fieldLoc.equals("99100001360") || fieldLoc.equals("99100001359")|| fieldLoc.equals("99100001358")||fieldLoc.equals("10678"))
			{
				sb.append(" SELECT ddo.DDO_CODE,ddo.DDO_NAME FROM ORG_DDO_MST ddo, MST_DCPS_DDO_OFFICE ddooffice,CMN_LOCATION_MST loc ");
				sb.append(" where ddo.DEPT_LOC_CODE="+admincode +" and ddo.HOD_LOC_CODE=10678 and ddo.ACTIVATE_FLAG=1 AND ddo.DDO_name like 'Joint%' ");
				sb.append(" and ddo.DDO_CODE = ddooffice.DDO_CODE  ");
				sb.append(" and ddooffice.DISTRICT =loc.LOC_DISTRICT_ID and loc.LOC_ID = "+treasuryId+" ");
			}
			//vocational field department
			else if(fieldLoc.equals("99100001285") || fieldLoc.equals("99100001286")||fieldLoc.equals("10679")){
				sb.append(" SELECT ddo.DDO_CODE,ddo.DDO_NAME FROM ORG_DDO_MST ddo, MST_DCPS_DDO_OFFICE ddooffice,CMN_LOCATION_MST loc ");
				sb.append(" where ddo.HOD_LOC_CODE = 10679 and ddo.ACTIVATE_FLAG=1 and ddo.DDO_CODE  in (7101002070,1201002070,1301002070,1401002070,1501002070,2201002070,2301002070,2401002070,2501002070,2601002070,5101002070,5201002070,5301002070,5401002070,3101002070,3201002070,3301002070,3401002070,3701002070,3601002070,3501002070,6101002070,6201002070,6301002070,6401002070,4601002070,4801002070,4901002070,4701002070,4501002070,7101012070,4401002070,6501002070,3801002070,5501002070) ");
				sb.append(" and ddo.DDO_CODE = ddooffice.DDO_CODE  ");
				sb.append(" and ddooffice.DISTRICT =loc.LOC_DISTRICT_ID and loc.LOC_ID = "+treasuryId+" ");
			}
		
		   //Directorate of Art
			else if(fieldLoc.equals("10676")){
				sb.append(" SELECT ddo.DDO_CODE,ddo.DDO_NAME FROM ORG_DDO_MST ddo, MST_DCPS_DDO_OFFICE ddooffice,CMN_LOCATION_MST loc ");
				sb.append(" where ddo.HOD_LOC_CODE = 10676 and ddo.ACTIVATE_FLAG=1 and ddo.DDO_CODE like '%2149%' ");
				sb.append(" and ddo.DDO_CODE = ddooffice.DDO_CODE  ");
				sb.append(" and ddooffice.DISTRICT =loc.LOC_DISTRICT_ID and loc.LOC_ID = "+treasuryId+" ");
			}
			else 
				{
				sb.append(" SELECT ddo.DDO_CODE,ddo.DDO_NAME FROM ORG_DDO_MST ddo, MST_DCPS_DDO_OFFICE ddooffice,CMN_LOCATION_MST loc ");
				sb.append(" where ddo.DEPT_LOC_CODE="+admincode +" and ddo.HOD_LOC_CODE="+fieldLoc+" and ddo.ACTIVATE_FLAG=1 ");
				sb.append(" and ddo.DDO_CODE = ddooffice.DDO_CODE  ");
				sb.append(" and ddooffice.DISTRICT =loc.LOC_DISTRICT_ID and loc.LOC_ID = "+treasuryId+" ");
				}
		//END edited by samadhan
		
		
		logger.info("---- ########################### getDDounderDept DAo ########################### ---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	result = query.list();
    	return result;
	
}
	
	
	
	public List getDesgnOffc(String ddoCode){
		List result=null;
		Session session=getSession();
		logger.info("---- getDesgnOffc---");
		StringBuffer sb = new StringBuffer();
    	sb.append("SELECT DSGN_NAME,DDO_OFFICE FROM ORG_DDO_MST where DDO_CODE="+ddoCode);
    	logger.info("---- getDDounderDept DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	result = query.list();
    	return result;
	
}
	
	public void updateDDoMst(String ddoCode,String ddoType){
		Session session=getSession();
		logger.info("---- updateDDoMst---");
		StringBuffer sb = new StringBuffer();
    	sb.append("update org_ddo_mst set ddo_type= "+ddoType+" where DDO_CODE="+ddoCode);
    	logger.info("---- updateDDoMst DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	query.executeUpdate();
	}
	
	//added by Demolisher
	public List getTreasuryNamefromTreasuryId(Long treasuryId)
	{
		List result=null;
		Session session=getSession();
		logger.info("---- getDesgnOffc---");
		StringBuffer sb = new StringBuffer();
    	sb.append("SELECT LOC_ID,LOC_NAME FROM CMN_LOCATION_MST where DEPARTMENT_ID=100003 and LOC_ID="+treasuryId);
    	logger.info("---- getDDounderDept DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	result = query.list();
    	return result;
	}
	//ended by Demolisher
	
	
	public int updatePaybillStatus(String billNo, int month, String year) {
		Session session=getSession();
		logger.info("---- updatePaybillStatus---");
		StringBuffer sb = new StringBuffer();
		sb.append("update PAYBILL_HEAD_MPG set status=2 where PAYBILL_MONTH="+month+" and PAYBILL_YEAR= "+year+" and BILL_NO ="+billNo+" and status=0");
		logger.info("---- updatePaybillStatus DAo---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		logger.info("---- query---"+sb.toString());
		return query.executeUpdate();
	}
	
	public int updatePaybillStatusAfterConsd(String billNo, int month, String year) {
		Session session=getSession();
		logger.info("---- updatePaybillStatus---");
		StringBuffer sb = new StringBuffer();
		sb.append("update PAYBILL_HEAD_MPG set status=4 where PAYBILL_MONTH="+month+" and PAYBILL_YEAR= "+year+" and paybill_id in ("+billNo+") and status=2");
		logger.info("---- updatePaybillStatus DAo---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		logger.info("---- query---"+sb.toString());
		return query.executeUpdate();
	}

	@Override
	public List getSeqTable(String strLocCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//added by saurabh
	
	public List getGpfByPayBillId(String paybillId)
	{
		List temp = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- getGpfByPayBillId---");
	
		/*sb.append("SELECT emp.HEAD_ACT_CODE,sum(GPF_GRP_ABC + GPF_ADV_GRP_ABC +  GPF_ABC_ARR_MR),sum(GPF_GRP_D + GPF_ADV_GRP_D + GPF_D_ARR_MR) FROM HR_PAY_PAYBILL paybill ");
		sb.append("inner join HR_EIS_EMP_MST eis on eis.EMP_ID=paybill.EMP_ID  ");
		sb.append("inner join Org_emp_mst emp on emp.EMP_ID=eis.EMP_MPG_ID ");
		sb.append("where paybill.PAYBILL_GRP_ID in ("+paybillId+")");
		sb.append("group by emp.HEAD_ACT_CODE ");*/
		
		sb.append("SELECT emp.HEAD_ACT_CODE,sum(GPF_GRP_ABC + GPF_ADV_GRP_ABC +  GPF_ABC_ARR_MR + GPF_GRP_D + GPF_ADV_GRP_D + GPF_D_ARR_MR + SVNPC_GPF_ARR_DEDU + SVNPC_GPF_RECO ) FROM HR_PAY_PAYBILL paybill ");
		sb.append("inner join HR_EIS_EMP_MST eis on eis.EMP_ID=paybill.EMP_ID  ");
		sb.append("inner join Org_emp_mst emp on emp.EMP_ID=eis.EMP_MPG_ID ");
		sb.append("where paybill.PAYBILL_GRP_ID in ("+paybillId+") and emp.HEAD_ACT_CODE is not null and emp.HEAD_ACT_CODE <>0 ");
		sb.append("group by emp.HEAD_ACT_CODE ");
		
		//Query modified by Akshay on 12/05/2017
		
		/*sb.append("SELECT emp.HEAD_ACT_CODE,sum(GPF_GRP_ABC + GPF_ADV_GRP_ABC +  GPF_ABC_ARR_MR),sum(GPF_GRP_D + GPF_ADV_GRP_D + GPF_D_ARR_MR),emp.GRADE_ID FROM HR_PAY_PAYBILL paybill ");
		sb.append("inner join HR_EIS_EMP_MST eis on eis.EMP_ID=paybill.EMP_ID  ");
		sb.append("inner join Org_emp_mst emp on emp.EMP_ID=eis.EMP_MPG_ID ");
		sb.append("where paybill.PAYBILL_GRP_ID in ("+paybillId+")");
		sb.append("group by emp.HEAD_ACT_CODE, emp.GRADE_ID ");*/
		logger.info("---- getGpfByPayBillId DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		logger.info("---- getGpfByPayBillId---"+sb);
		temp = query.list();
		logger.info("---- query---"+temp.size());
		return temp;    	
	}
	
	
	
	
	public Long getBillCount(String billNo, int month, String year)
    {
        Session session=getSession();
        logger.info("---- getBillCount---");
        StringBuffer sb = new StringBuffer();
        sb.append("select count(1) from PAYBILL_HEAD_MPG where PAYBILL_MONTH="+month+" and PAYBILL_YEAR= "+year+" and BILL_NO ="+billNo+" and approve_flag <>3   ");
        logger.info("---- getBillCount DAo---"+sb.toString());
        Query query = session.createSQLQuery(sb.toString());
        logger.info("---- query---"+sb.toString());
        return Long.parseLong(query.uniqueResult().toString());
}
	
	//ended by roshan
	
	
	//added by saurabh
	
	public List getLoansAndAdvancesByConsBillId(String paybillId)
    {
    	List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getLoansAndAdvancesByPayBillId---");
    	
    	//sb.append("SELECT  sum(CO_HSG_SOC), sum(CO_HSG_SOC_INT), sum(COMP_AIS), sum(COMP_AIS_INT), sum(COMPUTER_ADV), sum(COMPUTER_ADV_INT), sum(EXC_PAYRC), ");
    	//sb.append("sum(FAN_ADV), sum(FESTIVAL_ADVANCE), sum(GPF_ADV), sum(GPF_ADV_GRP_ABC), sum(GPF_ADV_GRP_ABC_INT), sum(GPF_ADV_GRP_D), sum(GPF_ADV_GRP_D_INT), ");
    	//sb.append("sum(GPF_IAS_LOAN), sum(GPF_IV_ADV), sum(GPF_OTHER_STATE), sum(HBA_AIS), sum(HBA_AIS_INT), sum(HBA_CONSTRUCTION), sum(HBA_CONSTRUCTION_INT), ");
    	//sb.append("sum(HBA_HOUSE), sum(HBA_HOUSE_INT), sum(HBA_LAND), sum(HBA_LAND_INT), sum(MCA_AIS), sum(MCA_AIS_INT), sum(MCA_LAND), sum(MCA_LAND_INT), ");
    	//sb.append("sum(MOTOR_CYCLE_ADV), sum(MOTOR_CYCLE_ADV_INT), sum(OTHER_ADV), sum(OTHER_ADV_INT), sum(OTHER_VEH_ADV), sum(OTHER_VEH_ADV_INT), sum(PAY_ADVANCE), ");
    	sb.append("SELECT  sum(CO_HSG_SOC), sum(CO_HSG_SOC_INT), sum(COMPUTER_ADV), ");
    	sb.append("sum(MCA_LAND), sum(MCA_LAND_INT),sum(HBA),sum(HBA_AIS),sum(HBA_AIS_INT),sum(HBA_CONSTRUCTION),sum(HBA_CONSTRUCTION_INT),sum(HBA_HOUSE),sum(HBA_HOUSE_INT),sum(HBA_LAND),sum(HBA_LAND_INT),sum(OTHER_VEH_ADV),sum(OTHER_VEH_ADV_INT) FROM HR_PAY_PAYBILL " +
    			"where PAYBILL_GRP_ID in (select paybill_id from consolidated_bill_mpg where cons_bill_id in ("+paybillId+")) ");
    	logger.info("---- getLoansAndAdvancesByPayBillId DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- getLoansAndAdvancesByPayBillId---"+sb);
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;    	
    }
	
	
	
	public List getLoansAndAdvancesByPayBillId(String paybillId)
    {
    	List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getLoansAndAdvancesByPayBillId---");
    	
    	//sb.append("SELECT  sum(CO_HSG_SOC), sum(CO_HSG_SOC_INT), sum(COMP_AIS), sum(COMP_AIS_INT), sum(COMPUTER_ADV), sum(COMPUTER_ADV_INT), sum(EXC_PAYRC), ");
    	//sb.append("sum(FAN_ADV), sum(FESTIVAL_ADVANCE), sum(GPF_ADV), sum(GPF_ADV_GRP_ABC), sum(GPF_ADV_GRP_ABC_INT), sum(GPF_ADV_GRP_D), sum(GPF_ADV_GRP_D_INT), ");
    	//sb.append("sum(GPF_IAS_LOAN), sum(GPF_IV_ADV), sum(GPF_OTHER_STATE), sum(HBA_AIS), sum(HBA_AIS_INT), sum(HBA_CONSTRUCTION), sum(HBA_CONSTRUCTION_INT), ");
    	//sb.append("sum(HBA_HOUSE), sum(HBA_HOUSE_INT), sum(HBA_LAND), sum(HBA_LAND_INT), sum(MCA_AIS), sum(MCA_AIS_INT), sum(MCA_LAND), sum(MCA_LAND_INT), ");
    	//sb.append("sum(MOTOR_CYCLE_ADV), sum(MOTOR_CYCLE_ADV_INT), sum(OTHER_ADV), sum(OTHER_ADV_INT), sum(OTHER_VEH_ADV), sum(OTHER_VEH_ADV_INT), sum(PAY_ADVANCE), ");
    	sb.append("SELECT  sum(CO_HSG_SOC), sum(CO_HSG_SOC_INT), sum(COMPUTER_ADV), ");
    	sb.append("sum(MCA_LAND), sum(MCA_LAND_INT),sum(HBA),sum(HBA_AIS),sum(HBA_AIS_INT),sum(HBA_CONSTRUCTION),sum(HBA_CONSTRUCTION_INT),sum(HBA_HOUSE),sum(HBA_HOUSE_INT),sum(HBA_LAND),sum(HBA_LAND_INT),sum(OTHER_VEH_ADV),sum(OTHER_VEH_ADV_INT) FROM HR_PAY_PAYBILL " +
    			"where PAYBILL_GRP_ID in  ("+paybillId+") ");
    	logger.info("---- getLoansAndAdvancesByPayBillId DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- getLoansAndAdvancesByPayBillId---"+sb);
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;    	
    }
//update on 25012023 for MTR44 By Naveen
	public List getDDOOfficeDataforMTR44(String ddoCode) {
		List temp=null;
		try
		{	
			String branchQuery = "SELECT  b.DDO_OFFICE ,b.DDO_CODE, b.DSGN_NAME FROM  ORG_DDO_MST b where  DDO_CODE="+ddoCode;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			//logger.error("---------------------get getSubTreasury query"+sqlQuery.toString());
			temp= sqlQuery.list();
			//logger.error("---------------------getSubTreasury"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ReportingDDODaoImpl \n " + e);
			e.printStackTrace();
		}
		return temp;	
	}
	public List getTresaryofficeListforMTR44(String ddoCode) {
		List temp=null;
		try
		{	
			String branchQuery = "SELECT a.LOC_ID ,a.LOC_NAME FROM  CMN_LOCATION_MST  a inner join  RLT_DDO_ORG b ON a.LOC_ID =b.LOCATION_CODE where b.DDO_CODE="+ddoCode;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			//logger.error("---------------------get getSubTreasury query"+sqlQuery.toString());
			temp= sqlQuery.list();
			//logger.error("---------------------getSubTreasury"+temp.size());
			
		}
		catch(Exception e){
			logger.error("Error in ReportingDDODaoImpl \n " + e);
			e.printStackTrace();
		}
		return temp;	
	}
	
	
	
}
