package com.tcs.sgv.pension.report;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ibm.icu.text.SimpleDateFormat;
import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.onlinebillprep.report.ReportQueryDAOImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public class PensionReportQueryDAOImpl extends GenericDaoHibernateImpl<TrnPensionRqstHdr,BigDecimal>{
	
	Log gLogger = LogFactory.getLog(getClass());
	
	String[] majorNames ={""," Thousand"," Lac"," Crore"," Trillion"," Quadrillion"," Quintillion"}; 	
		
	String[] tensNames = {""," Ten"," Twenty"," Thirty"," Fourty"," Fifty"," Sixty"," Seventy"," Eighty"," Ninety"};
		
	String[] numNames = {""," One"," Two"," Three"," Four"," Five"," Six"," Seven"," Eight"," Nine"," Ten"," Eleven"," Twelve"," Thirteen"," Fourteen"," Fifteen"," Sixteen"," Seventeen"," Eighteen"," Nineteen"};
		
	// SessionFactory sessionFactory = null;

	/* Global Variable for Session Class */
	Session ghibSession = null;
	
	public PensionReportQueryDAOImpl(Class<TrnPensionRqstHdr> type,SessionFactory sessionFactory) 
	{
		super(type);
		setSessionFactory(sessionFactory);
	//	this.sessionFactory = sessionFactory;
		ghibSession = sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		super.setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}
	
	public PensionReportQueryDAOImpl() 
	{		
		super(TrnPensionRqstHdr.class);		
	}
	
	private static final Logger glogger=Logger.getLogger(ReportQueryDAOImpl.class);
	
    private ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    
	/**
	 *  To display values in combo box on parameter page
	 * @param lStrName String
	 * @param lStrLangId String
	 * @param lstrLocId String
	 * @return ArrayList
	 */
	public ArrayList getComboValues(String lStrName, String lStrLangId, String lstrLocId) throws Exception
    {
		ArrayList arrValues = new ArrayList();
		try
		{
			Session hibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			StringBuilder lSBQuery = new StringBuilder();
						
			lSBQuery.append(" select c1.lookupId,c1.lookupName from CmnLookupMst c1,CmnLookupMst c2,CmnLanguageMst c3 ");
	        lSBQuery.append(" where c1.cmnLanguageMst.langId = c2.cmnLanguageMst.langId ");
	        lSBQuery.append(" and c3.langShortName = :lLangId ");
	        lSBQuery.append(" and c1.parentLookupId = c2.lookupId ");
	        lSBQuery.append(" and c2.lookupName = :lLookupName ");
			
			Query lQuery = hibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lLangId", lStrLangId);
	        lQuery.setParameter("lLookupName", lStrName.toString());
	        
	        List listValues = lQuery.list();
	        
			if (listValues!=null && !listValues.isEmpty()) 
			{	
				Iterator it = listValues.iterator();
				while(it.hasNext()) 
				{
					ComboValuesVO vo= new ComboValuesVO(  );
					Object[] tuple = (Object[])it.next();
					vo.setId(tuple[0].toString());
					vo.setDesc(tuple[1].toString());
					arrValues.add(vo);
				}	
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Error is :"+e);
			throw(e);
		}
		return arrValues;
    }
	
	public ArrayList getPensionType(String lStrName, String lStrLangId, String lstrLocId) throws Exception
    {
		glogger.info("in method getPensionType");
		ArrayList arrValues = new ArrayList();
		try
		{
			Session hibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			StringBuilder lSBQuery = new StringBuilder();
						
			lSBQuery.append(" select c1.lookupName from CmnLookupMst c1,CmnLookupMst c2,CmnLanguageMst c3 ");
	        lSBQuery.append(" where c1.cmnLanguageMst.langId = c2.cmnLanguageMst.langId ");
	        lSBQuery.append(" and c3.langShortName = :lLangId ");
	        lSBQuery.append(" and c1.parentLookupId = c2.lookupId ");
	        lSBQuery.append(" and c2.lookupName = :lLookupName ");
			
			Query lQuery = hibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lLangId", lStrLangId);
	        lQuery.setParameter("lLookupName", lStrName.toString());
	        
	        List listValues = lQuery.list();
	        
			if (listValues!=null && !listValues.isEmpty()) 
			{	
				for(int x=0;x<listValues.size();x++)
				{
					ComboValuesVO vo= new ComboValuesVO();
					vo.setId((String) listValues.get(x));
					vo.setDesc((String) listValues.get(x));
					arrValues.add(vo);
				}
				
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Error is :"+e);
			throw(e);
		}
		return arrValues;
    }
	/**
	 * To display values in List box Counter/Auditor on parameter page
	 * @param lStrLangId String
	 * @param lstrLocId String
	 * @return Arraylist
	 */
	public ArrayList getCounter(Hashtable hashtable, String lStrLangId, String lstrLocId) throws Exception
    {
		ArrayList arrCounter = new ArrayList();
		String lStrAuditor = "Pension_Inward";		
		
		 LoginDetails loginVO = (LoginDetails) hashtable.get("loginDetails");
         String locID = loginVO.getLocation().getLocationCode();
		
		try
		{
			Session hibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			StringBuilder lSBQuery = new StringBuilder();

			lSBQuery.append(" SELECT EMP.empFname||EMP.empMname||EMP.empLname FROM OrgEmpMst EMP, ");
            lSBQuery.append(" OrgUserpostRlt UP, AclPostroleRlt PR, AclRoleDetailsRlt RDR,CmnLanguageMst c3,OrgPostMst OPM");
            lSBQuery.append(" WHERE UP.orgUserMst.userId = EMP.orgUserMst.userId "); 
            lSBQuery.append(" AND PR.orgPostMst.postId = UP.orgPostMstByPostId.postId ");
            lSBQuery.append(" AND RDR.aclRoleMst.roleId = PR.aclRoleMst.roleId");
            lSBQuery.append(" AND RDR.roleName = :lAuditor ");
            lSBQuery.append(" AND EMP.cmnLanguageMst.langId = c3.langId  ");
            lSBQuery.append(" AND c3.langShortName = :lLangId ");
            lSBQuery.append(" AND PR.orgPostMst.postId = OPM.postId AND OPM.locationCode = :locCode ");

	        Query lQuery = hibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lAuditor", lStrAuditor);
	        lQuery.setParameter("lLangId", lStrLangId);
	        lQuery.setParameter("locCode", locID);
	         
	        
	        List listValues = lQuery.list();
	        
			if (listValues!=null && !listValues.isEmpty()) 
			{	
				Iterator it = listValues.iterator();
				while(it.hasNext()) 
				{
					String lSBName = new String();
					ComboValuesVO vo= new ComboValuesVO(  );
					Object tuple = (Object)it.next();
					//vo.setId(tuple.toString());
					if(tuple != null)
					{
						lSBName = tuple.toString();
					}
					/*if(tuple[1] != null)
					{
						lSBName.append(tuple[1].toString()+" ");
					}
					if(tuple[2] != null)
					{
						lSBName.append(tuple[2].toString());
					}*/
					vo.setDesc(lSBName);
					arrCounter.add(vo);
				}	
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Error is :"+e);
			throw(e);
		}
		
        return arrCounter;
    }
	/**
	 * Method for query of Last Seen pension case report
	 * @param toDate
	 * @param fromDate
	 * @return List
	 */
	public List getLastSeenPensionCaseReport(String toDate,String fromDate,long langID)
	{
		ArrayList arrList = null;
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			
			lSBQuery.append(" SELECT A.ppoNo,B.firstName||' '||B.middleName||' '||B.lastName,CM.locName,CM.parentLocId,C.seenDate, ");
			lSBQuery.append(" (SELECT CL.locName FROM CmnLocationMst CL WHERE CL.locationCode = CM.parentLocId AND CL.cmnLanguageMst.langId = "+langID+"),A.dppfAmount");
			lSBQuery.append(" FROM TrnPensionRqstHdr A,MstPensionerHdr B,TrnPensionerSeenDtls C,CmnLocationMst CM ");
			lSBQuery.append(" WHERE A.pensionerCode = B.pensionerCode ");
			lSBQuery.append(" AND B.pensionerCode = C.pensionerCode ");	
			lSBQuery.append(" AND CM.locationCode = B.locationCode ");
			lSBQuery.append(" AND C.seenFlage = 'Y' AND CM.cmnLanguageMst.langId ="+langID);
		
			//if para values are not null
			if(fromDate != null && toDate != null)
			{
				lSBQuery.append(" AND C.seenDate >= '"+fromDate+"'");
				lSBQuery.append(" AND C.seenDate <= '"+toDate+"'");
			}
			
			Query Query=ghibSession.createQuery(lSBQuery.toString());
			
			List resList=Query.list();
			
			if (resList!=null && !resList.isEmpty()) 
			{
				arrList=new ArrayList();
				Iterator it = resList.iterator();
				int lSr = 1;
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					ArrayList arrInner = new ArrayList();
					arrInner.add(lSr);//SrNo.
					if(tuple[0] != null)
					{
						arrInner.add(tuple[0]);//PPONo
					}
					else
					{
						arrInner.add(" ");//PPONo
					}
					if(tuple[1] != null)
					{
						arrInner.add(tuple[1]);//Name
					}
					else
					{
						arrInner.add(" ");//Name
					}
					
					if(tuple[3] != null)
					{
						if("100051".equals(tuple[3].toString()))
						{
							if(tuple[2] != null)
							{
								arrInner.add(tuple[2]);//Treasury
							}
							else
							{
								arrInner.add("-");//Treasury
							}							
						}
						else
						{
							if(tuple[5] != null)
							{
								arrInner.add(tuple[5]);//Treasury
							}
							else
							{
								arrInner.add("-");//Treasury
							}							
						}
						if("100051".equals(tuple[3].toString()))
						{
							arrInner.add("-");//Sub-Treasury
						}
						else
						{
							arrInner.add(tuple[2]);//Sub-Treasury
						}	
					}
					else
					{
						arrInner.add(" ");//Treasury
						arrInner.add(" ");//Sub-Treasury
					}
					if(tuple[4] != null)
					{
						arrInner.add(tuple[4]);//SeenDate
					}
					else
					{
						arrInner.add(" ");//SeenDate
					}
					if(tuple[6] != null)
					{
						arrInner.add(tuple[6]);//DPPFAmount
					}
					else
					{
						arrInner.add(new BigDecimal(0));//DPPFAmount
					}
										
					arrList.add(arrInner);
					lSr++;
				}
			}
			else
			{
				ArrayList arrInner = new ArrayList();
				arrInner.add(" ");
				arrInner.add(" ");
				arrInner.add(" ");
				arrInner.add(" ");
				arrInner.add(" ");
				arrInner.add(" ");
				arrInner.add(new BigDecimal(0));
				arrList.add(arrInner);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return arrList;
	}
	/**
	 * Method for query of Inward pension case report
	 * @param toDate
	 * @param fromDate
	 * @param inwardMode
	 * @param ppoNo
	 * @param pensionType
	 * @return
	 */
	public List getInwardPensionCaseReport(String toDate,String fromDate,String inwardMode,String ppoNo,String pensionType,String[] counter)
	{
		ArrayList arrList = null;
		
		try{
			StringBuilder lSBQuery = new StringBuilder();
			
			//get the list of counters/auditors and seperate them
			String[] lCntrList = counter;
			String[] lFname= null;
			String[] lMname= null;
			String[] lLname= null;
			String[] lName = new String[3];
			StringBuilder lPartyName = new StringBuilder();
			StringBuilder lCounterName = new StringBuilder();
			
			StringBuffer lStrList = new StringBuffer();
					
			if(!lCntrList[0].equals("-1"))
			{
				lStrList.append("("); 
				int flag = 0;
				for(int x=0;x<lCntrList.length;x++)
				{
					if(flag == 1)
					{
						lStrList.append(","); 
					}
					String lStrName = lCntrList[x];
					lStrList.append("'");
					lStrList.append(lStrName); 
					lStrList.append("'");
					flag = 1;
				}
				lStrList.append(")");
			}
			
			//simplest case
			lSBQuery.append("select distinct rqst.ppoInwardDate, rqst.ppoNo, ");
			lSBQuery.append("hdr.firstName, hdr.middleName, hdr.lastName, ");
			lSBQuery.append("rqst.pensionType, rqst.cvpAmount, rqst.dcrgAmount,rqst.basicPensionAmount, ");
			lSBQuery.append("e.empFname, e.empMname, e.empLname ");
			lSBQuery.append("from TrnPensionRqstHdr rqst, MstPensionerHdr hdr,TrnPensionCaseMvmnt mvt, OrgEmpMst e ");
			lSBQuery.append("where rqst.pensionerCode = hdr.pensionerCode ");
			lSBQuery.append("and rqst.ppoNo = mvt.ppoNo ");
			lSBQuery.append("and (mvt.movementId, mvt.ppoNo) in ");
			lSBQuery.append("(select max(m.movementId), m.ppoNo ");
			lSBQuery.append("from TrnPensionCaseMvmnt m ");
			lSBQuery.append("where m.pensionRequestId = rqst.pensionRequestId ");
			lSBQuery.append("group by m.ppoNo) ");
			lSBQuery.append("and e.orgUserMst.userId = mvt.currentUserId ");

		
			//if para values are not null
			if(fromDate != null)
			{
				lSBQuery.append(" and rqst.ppoInwardDate >= '"+fromDate+"'");
			}
			if(toDate != null)
			{
				lSBQuery.append(" and rqst.ppoInwardDate <= '"+toDate+"'");
			}
			if(Integer.parseInt(inwardMode) != -1)
			{
				lSBQuery.append(" and rqst.inwardMode= '"+inwardMode.trim()+"'");
			}
			if(ppoNo != "")
			{
				lSBQuery.append(" and rqst.ppoNo= '"+ppoNo.trim()+"'");
			}
			if(!"-1".equals(pensionType))
			{
				glogger.info("in condition of pension type"+pensionType);
				lSBQuery.append(" and rqst.pensionType = '"+pensionType.trim()+"'");
			}
			/*if(lFname!=null)
			{
				lSBQuery.append(" and e.empFname in (");
				int flag=0;
				for(int i=0;i<lFname.length;i++)
				{	if(flag==0)
					{
						lSBQuery.append(" '"+lFname[i]+"'");
						flag=1;
					}
					else if(flag==1)
					{
						lSBQuery.append(" ,'"+lFname[i]+"'");
					}
				}
				lSBQuery.append(" )");
			}
			
			if(lMname!=null)
			{
				lSBQuery.append(" and e.empMname in (");
				int flag=0;
				for(int i=0;i<lMname.length;i++)
				{	if(flag==0)
					{
						lSBQuery.append(" '"+lMname[i]+"'");
						flag=1;
					}
					else if(flag==1)
					{
						lSBQuery.append(" ,'"+lMname[i]+"'");
					}
				}
				lSBQuery.append(" )");
			}
			
			if(lLname!=null)
			{
				lSBQuery.append(" and e.empLname in (");
				int flag=0;
				for(int i=0;i<lLname.length;i++)
				{	if(flag==0)
					{
						lSBQuery.append(" '"+lLname[i]+"'");
						flag=1;
					}
					else if(flag==1)
					{
						lSBQuery.append(" ,'"+lLname[i]+"'");
					}
				}
				lSBQuery.append(" )");
			}*/
			
			if(lStrList != null)
			{
				lSBQuery.append(" and (e.empFname||e.empMname||e.empLname) in "+lStrList.toString());
			}
				
			Query Query=ghibSession.createQuery(lSBQuery.toString());
			gLogger.info("------------the query is:"+Query);
			List resList=Query.list();
			gLogger.info("------------size of result list is:"+resList.size());
			if (resList!=null && !resList.isEmpty()) 
			{
				arrList=new ArrayList();
				Iterator it = resList.iterator();
				int lSr = 1;
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					ArrayList arrInner = new ArrayList();
					arrInner.add(lSr);
					arrInner.add(tuple[0]);
					arrInner.add(tuple[1]);
					lPartyName = new StringBuilder();
					if(tuple[2] != null)
					{
						lPartyName.append(tuple[2]);
						lPartyName.append(" ");
					}
					if(tuple[3] != null)
					{
						lPartyName.append(tuple[3]);
						lPartyName.append(" ");
					}
					if(tuple[4] != null)
					{
						lPartyName.append(tuple[4]);
					}
					arrInner.add(lPartyName.toString());
					arrInner.add(tuple[5]);
					arrInner.add(tuple[6]);
					arrInner.add(tuple[7]);	
					arrInner.add(tuple[8]);
					lCounterName = new StringBuilder();
					if(tuple[9] != null)
					{
						lCounterName.append(tuple[9].toString());
						lCounterName.append(" ");
					}
					if(tuple[10] != null)
					{
						lCounterName.append(tuple[10].toString());
						lCounterName.append(" ");
					}
					if(tuple[11] != null)
					{
						lCounterName.append(tuple[11].toString());
					}
					arrInner.add(lCounterName.toString());
										
					arrList.add(arrInner);
					lSr++;
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return arrList;
	}
	/**
	 * Method to get the list of Months
	 * 
	 * @param  String lStrLangId
	 * @return ArrayList
	 * @author jigar
	 * @throws Exception 
	 */
	public ArrayList getMonths(String lStrLangId,String lStrLocationcode) throws Exception
	{
		List lLstVO;
		ArrayList arrMothsList = new ArrayList();
		try
		{
			StringBuffer lsb = new StringBuffer("select monthNo,monthName from SgvaMonthMst where langId = :lLangId order by monthId");
			Session hibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			Query lQuery = hibSession.createQuery(lsb.toString());
			lQuery.setParameter("lLangId",lStrLangId);
			lLstVO = lQuery.list();
			if(lLstVO != null && lLstVO.size()>0)
			{
				for(Object row : lLstVO)
				{
					ComboValuesVO vo = new ComboValuesVO();
					Object[] cols = (Object[])row;					
					vo.setId((String)(""+cols[0]));
					vo.setDesc((String)(""+cols[1]));
					arrMothsList.add(vo);
				}
			}
		} 
		catch (Exception e) 
		{
			glogger.error("Exception::" + e.getMessage(), e);
			throw(e);
		}
		return arrMothsList;
	}
	
	/**
	 * Method to get the list of Years
	 * 
	 * @param  String lStrLangId
	 * @return ArrayList
	 * @author jigar
	 * @throws Exception 
	 */
	public ArrayList getYears(String lStrLangId,String lStrLocationcode) throws Exception 
	{	
		List lLstVO;
		ArrayList arrYearsList = new ArrayList();
		String myYear="";
		try
		{
			StringBuffer lsb = new StringBuffer("select finYearCode from SgvcFinYearMst where langId = :lLangId order by finYearCode");
			Session hibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			Query lQuery = hibSession.createQuery(lsb.toString());
			lQuery.setParameter("lLangId",lStrLangId);
			lLstVO = lQuery.list();
			if(lLstVO != null && lLstVO.size()>0)
			{
				for(Object row : lLstVO)
				{
					ComboValuesVO vo = new ComboValuesVO();		
					myYear = row.toString();
					vo.setId(myYear);
					vo.setDesc(myYear);
					arrYearsList.add(vo);
				}
			}
		} 
		catch (Exception e) 
		{
			glogger.error("Exception::" + e.getMessage(), e);
			throw(e);
		}
		return arrYearsList;
	}

	/**
	 * Method to get the list of HeadCodes
	 * 
	 * @param  String lStrLangId
	 * @return ArrayList
	 * @author jigar
	 * @throws Exception 
	 */
	public ArrayList getHeadCodes(Hashtable hashtable, String lStrLocationCode,String lStrLocationcode) throws Exception 
	{
		List lLstVO;
		ArrayList arrHeadCodes = new ArrayList();
		String myHeadCode="";
		try
		{			
			LoginDetails loginVO = (LoginDetails) hashtable.get("loginDetails");
			lStrLocationCode = loginVO.getLocation().getLocationCode();
			//StringBuffer lsb = new StringBuffer("select distinct headCode from MstPensionHeadcode where locationCode = :lLocationCode order by finYearCode");
			String mySql = "select distinct headCode from MstPensionHeadcode order by headCode";			
			Session hibSession = 

			ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();			
			Query lQuery = hibSession.createQuery(mySql);			
			lLstVO = lQuery.list();
			if(lLstVO != null && lLstVO.size()>0)
			{
				for(Object row : lLstVO)
				{
					ComboValuesVO vo = new ComboValuesVO();
					myHeadCode = row.toString();					
					vo.setId(myHeadCode);
					vo.setDesc(myHeadCode);
					arrHeadCodes.add(vo);
				}
			}
		} 
		catch (Exception e) 
		{
			glogger.error("Exception::" + e.getMessage(), e);
			throw(e);
		}
		return arrHeadCodes;
	}
	
	public ArrayList getAllBranchsForLocation(Hashtable hashtable, String langId, String locationCode) throws Exception {
		ArrayList<ComboValuesVO> arrBranchVO = new ArrayList<ComboValuesVO>();
		StringBuilder strQuery = new StringBuilder();
		List resultList;
		ComboValuesVO cmbVO;
		Object[] obj;
		
		try {			
			strQuery.append(" SELECT branchCode,branchName ");
			strQuery.append(" FROM RltBankBranch ");
			strQuery.append(" where locationCode=:locationCode order by branchName");	
			Session hibSession = 

			ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			LoginDetails loginVO = (LoginDetails) hashtable.get("loginDetails");
			locationCode = loginVO.getLocation().getLocationCode();
			Query hqlQuery = hibSession.createQuery(strQuery.toString());			
			hqlQuery.setString("locationCode", locationCode);
			resultList = hqlQuery.list();
			cmbVO = new ComboValuesVO();
			cmbVO.setId("-1");
			cmbVO.setDesc("--Select--");
			arrBranchVO.add(cmbVO);
			if (resultList != null && resultList.size() > 0) {
				Iterator itr = resultList.iterator();
				while (itr.hasNext()) {
					cmbVO = new ComboValuesVO();
					obj = (Object[]) itr.next();
					cmbVO.setId(obj[0].toString());
					cmbVO.setDesc(obj[1].toString());
					arrBranchVO.add(cmbVO);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return arrBranchVO;
	}
	public List getPSBPaymentReportData(String fromMonth,String fromYear, String toMonth,String toYear, String headCode, String branchName,String locationCode,String reportType) throws Exception
	{
		ArrayList outerArr = null;
		ArrayList innerArr = null;
		Query lQuery = null;
		int fromMonthYear=0;
		int toMonthYear=0;
		try
		{
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();			
			query.append("select distinct(ppd.ppoNo), mpr.firstName || ' ' || mpr.middleName || ' ' || mpr.lastName, ppd.actualPayment, ppd.lessPayment," +
					" ppd.excessPayment,prd.headCode || '-' || mph.description "+" from TrnPensionPsbDtls ppd, TrnPensionRqstHdr prd, MstPensionerHdr mpr,MstPensionHeadcode mph "+
			" where ppd.ppoNo = prd.ppoNo and prd.locationCode= :lLocationcode and prd.pensionerCode=mpr.pensionerCode "+
			" and mph.headCode=prd.headCode ");
			
			if(reportType.equals("voucherReport"))
			{
				query.append(" and ppd.voucherNo = 0 ");
			}
			
			if(fromMonth !="-1" && fromYear !="-1")
			{
				if(Integer.parseInt(fromMonth) < 10 )
				{
					fromMonth= "0"+fromMonth;
				}
				fromMonthYear = Integer.parseInt(fromYear+fromMonth);
				query.append(" and ppd.monthYear >= "+fromMonthYear);			

	
			}
			if(toMonth !="-1" && toYear !="-1")
			{
				if(Integer.parseInt(toMonth) < 10 )
				{
					toMonth= "0"+toMonth;
				}
				toMonthYear = Integer.parseInt(toYear+toMonth);
				query.append(" and ppd.monthYear <= " +toMonthYear);			

	
			}
			if(headCode !="-1")
			{
				query.append(" and prd.headCode = "+headCode);				
			}
			if(branchName !="-1")
			{
				query.append(" and ppd.bankCode ='"+branchName+"'");			

	
			}
			lQuery = hibSession.createQuery(query.toString());
			lQuery.setParameter("lLocationcode", locationCode);
			
			List lLst = lQuery.list();
			
			if(lLst != null && !lLst.isEmpty())
			{
				int count=0;
				outerArr = new ArrayList();
				for(Object row: lLst)
				{
					innerArr = new ArrayList();
					Object[] cols = (Object[]) row;
					innerArr.add(++count);
					innerArr.add(cols[0].toString());
					innerArr.add(cols[1].toString());
					innerArr.add(cols[2]);
					innerArr.add(cols[3]);
					innerArr.add(cols[4]);	
					innerArr.add(cols[5].toString());
					outerArr.add(innerArr);
				}
			}
		}
		catch(Exception e)
		{
			throw(e);
		}
		return outerArr;
	}
	public List getPSBPaymentSubReportData(String fromMonth,String fromYear, String toMonth,String toYear, String headCode, String branchName,String locationCode,String reportType) throws Exception
	{
		ArrayList outerArr = null;
		ArrayList innerArr = null;
		Query lQuery = null;
		int fromMonthYear=0;
		int toMonthYear=0;
		try
		{
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();		
		
			query.append("select prd.headCode,count(ppd.ppoNo),sum(ppd.actualPayment),sum(ppd.lessPayment),sum(ppd.excessPayment)"+
			" from TrnPensionPsbDtls ppd, TrnPensionRqstHdr prd, MstPensionerHdr mpr "+ 
			" where ppd.ppoNo = prd.ppoNo and prd.locationCode=:lLocationcode and prd.pensionerCode=mpr.pensionerCode ");
			
			if(reportType.equals("voucherReport"))
			{
				query.append(" and ppd.voucherNo = 0 ");
			}
			
			//query.append("select ppd.ppoNo from TrnPensionPsbDtls ppd,TrnPensionRqstHdr prd where ppd.ppoNo = prd.ppoNo and prd.locationCode=:lLocationcode");
			
			if(!fromMonth.equals("-1") && !fromYear.equals("-1"))
			{
				if(Integer.parseInt(fromMonth) < 10 )
				{
					fromMonth= "0"+fromMonth;
				}
				fromMonthYear = Integer.parseInt(fromYear+fromMonth);
				query.append(" and ppd.monthYear >= "+fromMonthYear);			

	
			}
			if(!toMonth.equals("-1") && !toYear.equals("-1"))
			{
				if(Integer.parseInt(toMonth) < 10 )
				{
					toMonth= "0"+toMonth;
				}
				toMonthYear = Integer.parseInt(toYear+toMonth);
				query.append(" and ppd.monthYear <= " +toMonthYear);			

	
			}
			if(!headCode.equals("-1"))
			{
				query.append(" and prd.headCode = "+headCode);				
			}
			if(!branchName.equals("-1"))
			{
				query.append(" and ppd.bankCode ='"+branchName+"'");			

	
			}
			query.append(" group by prd.headCode ");
			lQuery = hibSession.createQuery(query.toString());
			lQuery.setParameter("lLocationcode", locationCode);
			
			List lLst = lQuery.list();
			
			if(lLst != null && !lLst.isEmpty())
			{
				int count=0;
				outerArr = new ArrayList();
				for(Object row: lLst)
				{
					innerArr = new ArrayList();
					Object[] cols = (Object[]) row;
					innerArr.add(++count);
					innerArr.add(cols[0]);
					innerArr.add(cols[1]);
					innerArr.add(cols[2]);
					innerArr.add(cols[3]);
					innerArr.add(cols[4]);					
					outerArr.add(innerArr);
				}
			}
			else
			{
				innerArr = new ArrayList();
				innerArr.add("0.00");
				innerArr.add("0.00");
				innerArr.add("0.00");
				innerArr.add("0.00");
				innerArr.add("0.00");
				innerArr.add("0.00");					
				outerArr.add("0.00");
			}
		}
		catch(Exception e)
		{
			throw(e);
		}
		return outerArr;
	}
	 /**
     * Method for query of Provisional pension case report
     * @param toDate
     * @param fromDate
     * @param PPO.No.
     * @return list
     */
    
    
    public List getProvisionalPensionCaseReport(String toDate, String fromDate, String PpoNo)
    {
        ArrayList arrList = null;
        StringBuilder lPensionerName;
        BigDecimal basic_pension = new BigDecimal(0);
        BigDecimal dcrg_amnt = new BigDecimal(0);
        BigDecimal total_amnt = new BigDecimal(0);
        String designation = "";
        String officeAddr = "";
        
        try
        {
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" select hd.ppo_no,ms.first_name,ms.middle_name,ms.last_name, ");
            lSBQuery.append(" hd.commension_Date,hd.basic_Pension_Amount, ");
            lSBQuery.append(" hd.dcrg_Amount,sum(d.reduced_Pension),dsg.dsgn_name,ms.office_Addr ");
            lSBQuery.append(" from Trn_Pension_Rqst_Hdr hd left join Mst_Pensioner_Hdr ms on ms.pensioner_Code = hd.pensioner_Code ");
            lSBQuery.append(" left join Trn_Pension_Bill_Dtls d on hd.pensioner_Code = d.pensioner_Code left join org_designation_mst dsg on dsg.dsgn_id = ms.designation ");
            lSBQuery.append(" where hd.type_Flag = 'P' ");
            //lSBQuery.append(" ms.firstName,ms.middleName,ms.lastName,hd.commensionDate, ");
            //lSBQuery.append(" hd.basicPensionAmount,hd.dcrgAmount ");
            lSBQuery.append(" and hd.ppo_Inward_Date >= '"+fromDate+"'");
            lSBQuery.append(" and hd.ppo_Inward_Date <= '"+toDate+"'");
            
            if((PpoNo != null) && ( ! PpoNo.equals("")))
            {
                lSBQuery.append(" and hd.ppo_No= '"+PpoNo+"'");
            }
            lSBQuery.append(" group by hd.ppo_No,ms.first_Name,ms.middle_Name,ms.last_Name, ");
            lSBQuery.append(" hd.commension_Date,hd.basic_Pension_Amount,hd.dcrg_Amount,dsg.dsgn_name,ms.office_Addr ");
            
            Query Query=ghibSession.createSQLQuery(lSBQuery.toString());
            List resList=Query.list();
            
            if (resList!=null && !resList.isEmpty()) 
            {
                arrList=new ArrayList();
                for(int i=0;i<resList.size();i++)
                {
                	Object[] tuple = (Object[]) resList.get(i) ;                    
                    ArrayList arrInner = new ArrayList();
                    
                    arrInner.add(i+1);//for serial no.
                    arrInner.add(tuple[0]);
                    
                    lPensionerName = new StringBuilder();
                    if(tuple[1] != null)
                    {
                        lPensionerName.append(tuple[1]);
                        lPensionerName.append(" ");
                    }
                    if(tuple[2] != null)
                    {
                        lPensionerName.append(tuple[2]);
                        lPensionerName.append(" ");
                    }
                    if(tuple[3] != null)
                    {
                        lPensionerName.append(tuple[3]);
                    }
                    arrInner.add(lPensionerName.toString());
                    if(tuple[8] != null)
                    {
                    	designation = tuple[8].toString();
                    	arrInner.add(designation);
                    }
                    else
                    {
                    	arrInner.add(designation);
                    }
                    if(tuple[9] != null)
                    {
                    	officeAddr = tuple[9].toString();
                    	arrInner.add(officeAddr);
                    }
                    else
                    {
                    	arrInner.add(officeAddr);
                    }
                    arrInner.add(tuple[4]);
                    
                    if(tuple[5] != null)
                    {
	                    basic_pension = new BigDecimal(tuple[5].toString()).setScale(2,BigDecimal.ROUND_UP);
	                    arrInner.add(basic_pension);
                    }
                    else
                    {
                    	arrInner.add("0.00");
                    }
                    
                    if(tuple[6] != null)
                    {
	                    dcrg_amnt = new BigDecimal(tuple[6].toString()).setScale(2,BigDecimal.ROUND_UP);
	                    arrInner.add(dcrg_amnt);
                    }
                    else
                    {
                    	arrInner.add("0.00");
                    }
                    
                    if(tuple[7] == null)
                    {
                        arrInner.add("0.00");
                    }
                    else
                    {
                    	total_amnt = new BigDecimal(tuple[7].toString()).setScale(2,BigDecimal.ROUND_UP);
                        arrInner.add(total_amnt);
                    }
                    arrList.add(arrInner);
                }
            }
        }
        catch (Exception e) 
        {
        	gLogger.error("Error is;" + e, e);
            e.printStackTrace();
        }
        return arrList;
    }
    public List getPensionerDetailsReport(String lStrPPONo)
    {
    	List resList = null;
		try
		{			
			StringBuilder lSBQuery = new StringBuilder();
			
			lSBQuery.append(" SELECT distinct(tpbd.pensionerName),mph.pensnerAddr,mph.panNo, ");
			lSBQuery.append(" mb.bankName,rbb.branchName,tpbd.accountNo ");
			lSBQuery.append(" FROM MstPensionerHdr mph,MstBank mb,RltBankBranch rbb,TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh ");			
			lSBQuery.append(" WHERE mph.pensionerCode = tpbd.pensionerCode ");
			lSBQuery.append(" AND  tpbd.trnPensionBillHdrId = tpbh.trnPensionBillHdrId ");
			//lSBQuery.append(" AND mb.bankCode = tpbh.bankCode ");
			lSBQuery.append(" AND mb.bankCode = rbb.bankCode ");
			lSBQuery.append(" AND tpbd.ppoNo = :ppoNo");
			lSBQuery.append(" AND tpbh.branchCode = rbb.branchCode ");
			lSBQuery.append(" AND mph.caseStatus = 'APPROVED' ");
			 			 
			 Query Query=ghibSession.createQuery(lSBQuery.toString());
			 Query.setParameter("ppoNo", lStrPPONo);
			 
			resList=Query.list();
    	}
    	catch(Exception e)
    	{
    		gLogger.info(e);
    		e.printStackTrace();
    	}
    	return resList;                     
    }
    
    public List getLocationReport(String locCode,long langID)
    {
    	List resList = null;
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			
			lSBQuery.append(" SELECT cl.locName, cl.locAddr1 || ' ' || cl.locAddr2 ");
			lSBQuery.append(" FROM CmnLocationMst cl ");
			lSBQuery.append(" WHERE cl.locationCode = :lLocationcode ");
			lSBQuery.append(" AND cl.cmnLanguageMst.langId = :langId ");
			
			Query Query=ghibSession.createQuery(lSBQuery.toString());
			Query.setParameter("lLocationcode", locCode);
			Query.setParameter("langId", langID);
			
			resList=Query.list();
		}
    	catch(Exception e)
    	{
    		gLogger.error(e);
    		e.printStackTrace();
    	}
    	return resList;
    }
    
    public List getPayableDetailsReport(String lStrPPONo,String lStrForMonthYear)
    {
    	List resList = null;
    	List resList1 = null;
    	List resListDCRG = null;
    	List resListCVP = null;
    	List NewResList = null;
    	List Return = null;
    	String billType[] = new String[20000];
    	String month = null;
		String year = null;
		String[] lName = null;
		String forMonthYear[] = new String[20000];
		Double amountCVP = 0.00;
		Double amountDCRG = 0.00;
		Double varTotal = 0.00;
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT tpbh.billDate,tpbh.billType ");
			lSBQuery.append(" FROM TrnPensionBillHdr tpbh,TrnPensionBillDtls tpbd ");
			lSBQuery.append(" WHERE tpbh.trnPensionBillHdrId = tpbd.trnPensionBillHdrId ");
			lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
			
			Query Query=ghibSession.createQuery(lSBQuery.toString());
			Query.setParameter("ppoNo", lStrPPONo);			
			resList=Query.list();
			if (resList!=null && !resList.isEmpty()) 
            {   
                for(int i=0;i<resList.size();i++)
                {
                    Object[] tuple = (Object[])resList.get(i);
                    if(tuple[0] != null)
                    {
        				lName = tuple[0].toString().split("-");
            			year = lName[0];
            			month = lName[1];
            			
            			if(month != null && month.length() == 1)
            			{
            				month = "0" + month;
            			}
            			
            			forMonthYear[i] = year + month;
                    } 
                    if(tuple[1] != null)
                    {
                    	billType[i] = tuple[1].toString();
                    }        			
                }
            }
			else
			{
				NewResList = new ArrayList();
				Return = new ArrayList();
				
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));	
			}
			
			for(int i=0;i<resList.size();i++)
			{
				lSBQuery = new StringBuilder();
				
				if(billType[i] != null)
				{
					if(billType[i].equals("CVP") || billType[i].equals("DCRG"))
					{
						if(forMonthYear[i].equals(lStrForMonthYear))
						{
							lSBQuery.append(" SELECT tpbd.pensionAmount ,tpbd.cvpMonthAmount,tpbd.pensionAmount-tpbd.cvpMonthAmount,tpbd.pensnCutAmount, ");
							lSBQuery.append(" tpbd.dpAmount,tpbd.tiAmount,tpbd.medicalAllowenceAmount, ");			
							lSBQuery.append(" tpbd.arrearAmount+tpbd.personalPensionAmount+tpbd.irAmount, ");
							lSBQuery.append(" tpbd.tiArrearAmount,tpbd.specialCutAmount,tpbd.pensionAmount-tpbd.cvpMonthAmount+tpbd.dpAmount-tpbd.pensnCutAmount+tpbd.tiAmount+tpbd.medicalAllowenceAmount+tpbd.arrearAmount+tpbd.personalPensionAmount+tpbd.irAmount+tpbd.tiArrearAmount-tpbd.specialCutAmount ");
							lSBQuery.append(" FROM TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh ");
							lSBQuery.append(" WHERE tpbh.trnPensionBillHdrId = tpbd.trnPensionBillHdrId ");
							lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
						}				
					}
					if(billType[i].equals("PENSION")|| billType[i].equals("Monthly"))
					{				
						lSBQuery.append(" SELECT tpbd.pensionAmount ,tpbd.cvpMonthAmount,tpbd.pensionAmount-tpbd.cvpMonthAmount,tpbd.pensnCutAmount, ");
						lSBQuery.append(" tpbd.dpAmount,tpbd.tiAmount,tpbd.medicalAllowenceAmount, ");
						lSBQuery.append(" tpbd.arrearAmount+tpbd.personalPensionAmount+tpbd.irAmount, ");
						lSBQuery.append(" tpbd.tiArrearAmount,tpbd.specialCutAmount,tpbd.pensionAmount-tpbd.cvpMonthAmount+tpbd.dpAmount-tpbd.pensnCutAmount+tpbd.tiAmount+tpbd.medicalAllowenceAmount+tpbd.arrearAmount+tpbd.personalPensionAmount+tpbd.irAmount+tpbd.tiArrearAmount-tpbd.specialCutAmount ");
						lSBQuery.append(" FROM TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh ");
						lSBQuery.append(" WHERE tpbh.trnPensionBillHdrId = tpbd.trnPensionBillHdrId ");
						lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
						lSBQuery.append(" AND tpbh.forMonth = " + lStrForMonthYear);
					}
					Query Query1=ghibSession.createQuery(lSBQuery.toString());
					Query1.setParameter("ppoNo", lStrPPONo);
					resList1=Query1.list();	
					
					lSBQuery = new StringBuilder();
					if(billType[i].equals("CVP"))
					{
						if(forMonthYear[i].equals(lStrForMonthYear))
						{
							lSBQuery.append(" SELECT tpbd.reducedPension ");
							lSBQuery.append(" FROM TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh ");
							lSBQuery.append(" WHERE tpbh.trnPensionBillHdrId = tpbd.trnPensionBillHdrId ");
							lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
						}
						Query QueryCVP=ghibSession.createQuery(lSBQuery.toString());
						QueryCVP.setParameter("ppoNo", lStrPPONo);
						resListCVP=QueryCVP.list();
						if (resListCVP!=null && !resListCVP.isEmpty()) 
			            {                
			                Iterator it = resListCVP.iterator();
			                BigDecimal lObjBigDecimal = null;
			                while(it.hasNext()) 
			                {
			                	lObjBigDecimal = (BigDecimal) it.next();
			                    if(lObjBigDecimal != null)
			                    {
			                    	amountCVP += lObjBigDecimal.doubleValue();
			                    }		                    
			                }
			            }
					}
					if(billType[i].equals("DCRG"))
					{
						if(forMonthYear[i].equals(lStrForMonthYear))
						{
							lSBQuery.append(" SELECT tpbd.reducedPension ");
							lSBQuery.append(" FROM TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh ");
							lSBQuery.append(" WHERE tpbh.trnPensionBillHdrId = tpbd.trnPensionBillHdrId ");
							lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
						}
						Query QueryDCRG=ghibSession.createQuery(lSBQuery.toString());
						QueryDCRG.setParameter("ppoNo", lStrPPONo);
						resListDCRG=QueryDCRG.list();
						if (resListDCRG!=null && !resListDCRG.isEmpty()) 
			            {                
			                Iterator it = resListDCRG.iterator();
			                BigDecimal lObjBigDecimal = null;
			                while(it.hasNext()) 
			                {
			                	lObjBigDecimal = (BigDecimal) it.next();
			                    if(lObjBigDecimal != null)
			                    {
			                    	amountDCRG += lObjBigDecimal.doubleValue();
			                    }		                    
			                }
			            }
					}
				}
			}
			
			//integration.....
			if (resList1!=null && !resList1.isEmpty()) 
            {
				NewResList = null;
				Return = new ArrayList();
				Iterator it = resList1.iterator();
                while(it.hasNext()) 
                {
                	NewResList = new ArrayList();
                    Object[] tuple = (Object[])it.next();
                   
                    NewResList.add(new BigDecimal(tuple[0].toString()));
                    
                    if(tuple[1] == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(tuple[1].toString()));
                    }
                    
                    if(tuple[2] == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(tuple[2].toString()));
                    }
                    
                    if(tuple[3] == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(tuple[3].toString()));
                    }
                    
                    
                    if(tuple[4] == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(tuple[4].toString()));
                    }
                    
                    
                    if(tuple[5] == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(tuple[5].toString()));
                    }
                    
                    if(tuple[6] == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(tuple[6].toString()));
                    }
                    

                    if(amountCVP == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(amountCVP.toString()));
                    }

                    if(amountDCRG == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(amountDCRG.toString()));
                    }

                    if(tuple[7] == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(tuple[7].toString()));
                    }
                    if(tuple[8] == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(tuple[8].toString()));
                    }
                    if(tuple[9] == null)
                    {
                    	NewResList.add(new BigDecimal(0));
                    }
                    else
                    {
                    	NewResList.add(new BigDecimal(tuple[9].toString()));
                    }
                     
                    //Total------>
                    if(tuple[0] != null)
                    {
                    	varTotal +=Double.parseDouble(tuple[0].toString());
                    }
                    if(tuple[1] != null)
                    {
                    	varTotal -=Double.parseDouble(tuple[1].toString());
                    }
                    if(tuple[3] != null)
                    {
                    	varTotal -=Double.parseDouble(tuple[3].toString());
                    }
                    if(tuple[4] != null)
                    {
                    	varTotal +=Double.parseDouble(tuple[4].toString());
                    }
                    if(tuple[5] != null)
                    {
                    	varTotal +=Double.parseDouble(tuple[5].toString());
                    }
                    if(tuple[6] != null)
                    {
                    	varTotal +=Double.parseDouble(tuple[6].toString());
                    }
                    if(amountCVP != null)
                    {
                    	varTotal += amountCVP;
                    }
                    if(amountDCRG != null)
                    {
                    	varTotal += amountDCRG;
                    }
                    if(tuple[7] != null)
                    {
                    	varTotal +=Double.parseDouble(tuple[7].toString());
                    }
                    if(tuple[8] != null)
                    {
                    	varTotal +=Double.parseDouble(tuple[8].toString());
                    }
                    if(tuple[9] != null)
                    {
                    	varTotal -=Double.parseDouble(tuple[9].toString());
                    }
                    
                    NewResList.add(new BigDecimal(varTotal.toString())); 
                }
                
            }
			else
			{
				NewResList = new ArrayList();
				Return = new ArrayList();
				
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));
				NewResList.add(new BigDecimal(0));				
			}
			Return.add(NewResList);
    	}
    	catch(Exception e)
    	{
    		gLogger.info(e);
    		e.printStackTrace();
    	}
    	return Return;
    }
    public List getDeductionDetailsReport(String lStrPPONo,String lStrForMonthYear)
    {
    	List resList = null;
    	String recoveryType = null;
    	String amountHBA = null;
    	String amountMCA = null;
    	Double amountRent = 0.00;
    	String amountOthers = null;
    	String amountIT = null;
    	List returnList = new ArrayList();
    	List returnLST = new ArrayList();
    	Double varTotal = 0.00;
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			
			lSBQuery.append(" SELECT tprd.recoveryType,tprd.amount,tpitcd.amount ");
			lSBQuery.append(" FROM TrnPensionRecoveryDtls tprd,TrnPensionBillDtls tpbd,TrnPensionItCutDtls tpitcd ");
			lSBQuery.append(" WHERE tpbd.pensionerCode = tprd.pensionerCode ");
			lSBQuery.append(" AND tprd.pensionerCode = tpitcd.pensionerCode ");
			lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
			lSBQuery.append(" AND tpitcd.fromMonth <= " + lStrForMonthYear);
			lSBQuery.append(" AND tpitcd.toMonth >= " + lStrForMonthYear);
			lSBQuery.append(" AND tprd.fromMonth <= " + lStrForMonthYear);
			lSBQuery.append(" AND tprd.toMonth >= " + lStrForMonthYear);
			
			
			Query Query=ghibSession.createQuery(lSBQuery.toString());
			Query.setParameter("ppoNo", lStrPPONo);	
			resList=Query.list();
			if (resList!=null && !resList.isEmpty()) 
            {                
                Iterator it = resList.iterator();
                while(it.hasNext()) 
                {
                    Object[] tuple = (Object[])it.next();
                    
                    if(tuple[0] != null)
                    {
                    	recoveryType = tuple[0].toString();
                    	if(tuple[1] != null)
                    	{                    		
                    		if(recoveryType.equals("HBA"))
                        	{
                        		amountHBA = tuple[1].toString();
                        	}
                        	if(recoveryType.equals("MCA"))
                        	{
                        		amountMCA = tuple[1].toString();
                        	}
                        	if(recoveryType.equals("Others"))
                        	{
                        		amountOthers = tuple[1].toString();
                        	}
                    	}
                    	if(tuple[2] != null)
                    	{
                    		amountIT = tuple[2].toString();
                    	}
                    }
                }   
            }
            if(amountHBA != null)
            {
            	returnList.add(new BigDecimal(amountHBA));
            	varTotal +=Double.parseDouble(amountHBA); 
            }
            else
            {
            	returnList.add(new BigDecimal(0));
            }
            if(amountMCA != null)
            {
            	returnList.add(new BigDecimal(amountMCA));
            	varTotal +=Double.parseDouble(amountMCA); 
            }
            else
            {
            	returnList.add(new BigDecimal(0));
            }
            if(amountRent != null)
            {
            	returnList.add(new BigDecimal(amountRent));
            }
            else
            {
            	returnList.add(new BigDecimal(0));
            }
            if(amountIT != null)
            {
            	returnList.add(new BigDecimal(amountIT));
            	varTotal +=Double.parseDouble(amountIT); 
            }
            else
            {
            	returnList.add(new BigDecimal(0));
            }
            if(amountOthers != null)
            {
            	returnList.add(new BigDecimal(amountOthers));
            	varTotal +=Double.parseDouble(amountOthers); 
            }
            else
            {
            	returnList.add(new BigDecimal(0));
            }
            if(varTotal != null)
            {
            	returnList.add(new BigDecimal(varTotal.toString()));
            }
            else
            {
            	returnList.add(new BigDecimal(0));
            }
            
            returnLST.add(returnList);
            			
    	}
    	catch(Exception e)
    	{
    		gLogger.info(e);
    		e.printStackTrace();
    	}
    	return returnLST;
    }
    public List getRemarksReport(String lStrPPONo,String lStrForMonthYear)
    {
    	List resList = null;
    	List resList1 = null;
    	List resListDCRG = null;
    	List resListCVP = null;
    	List NewResList = null;
    	List Return = null;
    	String billType[] = new String[20000];
    	String month = null;
		String year = null;
		String[] lName = null;
		String forMonthYear[] = new String[20000];
		Double amountCVP = 0.00;
		Double amountDCRG = 0.00;
		Double varTotalPay = 0.00;
		Double varTotalDeduc = 0.00;
		List resList11 = null;
    	String recoveryType = null;
    	String amountHBA = null;
    	String amountMCA = null;
    	String amountRent = null;
    	String amountOthers = null;
    	String amountIT = null;
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
		
			lSBQuery.append(" SELECT tpbh.billDate,tpbh.billType ");
			lSBQuery.append(" FROM TrnPensionBillHdr tpbh,TrnPensionBillDtls tpbd ");
			lSBQuery.append(" WHERE tpbh.trnPensionBillHdrId = tpbd.trnPensionBillHdrId ");
			lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
			
			Query Query=ghibSession.createQuery(lSBQuery.toString());
			Query.setParameter("ppoNo", lStrPPONo);			
			resList=Query.list();
			if (resList!=null && !resList.isEmpty()) 
	        {   
	            for(int i=0;i<resList.size();i++)
	            {
	                Object[] tuple = (Object[])resList.get(i);
	                if(tuple[0] != null)
	                {
	    				lName = tuple[0].toString().split("-");
	        			year = lName[0];
	        			month = lName[1];
	        			
	        			if(month != null && month.length() == 1)
	        			{
	        				month = "0" + month;
	        			}
	        			
	        			forMonthYear[i] = year + month;
	                } 
	                if(tuple[1] != null)
	                {
	                	billType[i] = tuple[1].toString();
	                }        			
	            }
	        }
			
			for(int i=0;i<resList.size();i++)
			{
				lSBQuery = new StringBuilder();
				
				if(billType[i] != null)
				{
					if(billType[i].equals("CVP") || billType[i].equals("DCRG"))
					{
						if(forMonthYear[i].equals(lStrForMonthYear))
						{
							lSBQuery.append(" SELECT tpbd.pensionAmount ,tpbd.cvpMonthAmount,tpbd.pensionAmount-tpbd.cvpMonthAmount,tpbd.pensnCutAmount, ");
							lSBQuery.append(" tpbd.dpAmount,tpbd.tiAmount,tpbd.medicalAllowenceAmount, ");			
							lSBQuery.append(" tpbd.arrearAmount+tpbd.personalPensionAmount+tpbd.irAmount, ");
							lSBQuery.append(" tpbd.tiArrearAmount,tpbd.specialCutAmount,tpbd.pensionAmount-tpbd.cvpMonthAmount+tpbd.dpAmount-tpbd.pensnCutAmount+tpbd.tiAmount+tpbd.medicalAllowenceAmount+tpbd.arrearAmount+tpbd.personalPensionAmount+tpbd.irAmount+tpbd.tiArrearAmount-tpbd.specialCutAmount ");
							lSBQuery.append(" FROM TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh ");
							lSBQuery.append(" WHERE tpbh.trnPensionBillHdrId = tpbd.trnPensionBillHdrId ");
							lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
						}				
					}
					if(billType[i].equals("PENSION")|| billType[i].equals("Monthly"))
					{				
						lSBQuery.append(" SELECT tpbd.pensionAmount ,tpbd.cvpMonthAmount,tpbd.pensionAmount-tpbd.cvpMonthAmount,tpbd.pensnCutAmount, ");
						lSBQuery.append(" tpbd.dpAmount,tpbd.tiAmount,tpbd.medicalAllowenceAmount, ");
						lSBQuery.append(" tpbd.arrearAmount+tpbd.personalPensionAmount+tpbd.irAmount, ");
						lSBQuery.append(" tpbd.tiArrearAmount,tpbd.specialCutAmount,tpbd.pensionAmount-tpbd.cvpMonthAmount+tpbd.dpAmount-tpbd.pensnCutAmount+tpbd.tiAmount+tpbd.medicalAllowenceAmount+tpbd.arrearAmount+tpbd.personalPensionAmount+tpbd.irAmount+tpbd.tiArrearAmount-tpbd.specialCutAmount ");
						lSBQuery.append(" FROM TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh ");
						lSBQuery.append(" WHERE tpbh.trnPensionBillHdrId = tpbd.trnPensionBillHdrId ");
						lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
						lSBQuery.append(" AND tpbh.forMonth = " + lStrForMonthYear);
					}
					Query Query1=ghibSession.createQuery(lSBQuery.toString());
					Query1.setParameter("ppoNo", lStrPPONo);
					resList1=Query1.list();	
					
					lSBQuery = new StringBuilder();
					if(billType[i].equals("CVP"))
					{
						if(forMonthYear[i].equals(lStrForMonthYear))
						{
							lSBQuery.append(" SELECT tpbd.reducedPension ");
							lSBQuery.append(" FROM TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh ");
							lSBQuery.append(" WHERE tpbh.trnPensionBillHdrId = tpbd.trnPensionBillHdrId ");
							lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
						}
						Query QueryCVP=ghibSession.createQuery(lSBQuery.toString());
						QueryCVP.setParameter("ppoNo", lStrPPONo);
						resListCVP=QueryCVP.list();
						if (resListCVP!=null && !resListCVP.isEmpty()) 
			            {                
			                Iterator it = resListCVP.iterator();
			                BigDecimal lObjBigDecimal = null;
			                while(it.hasNext()) 
			                {
			                	lObjBigDecimal = (BigDecimal) it.next();
			                    if(lObjBigDecimal != null)
			                    {
			                    	amountCVP += lObjBigDecimal.doubleValue();
			                    }		                    
			                }
			            }
					}
					if(billType[i].equals("DCRG"))
					{
						if(forMonthYear[i].equals(lStrForMonthYear))
						{
							lSBQuery.append(" SELECT tpbd.reducedPension ");
							lSBQuery.append(" FROM TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh ");
							lSBQuery.append(" WHERE tpbh.trnPensionBillHdrId = tpbd.trnPensionBillHdrId ");
							lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
						}
						Query QueryDCRG=ghibSession.createQuery(lSBQuery.toString());
						QueryDCRG.setParameter("ppoNo", lStrPPONo);
						resListDCRG=QueryDCRG.list();
						if (resListDCRG!=null && !resListDCRG.isEmpty()) 
			            {                
			                Iterator it = resListDCRG.iterator();
			                BigDecimal lObjBigDecimal = null;
			                while(it.hasNext()) 
			                {
			                	lObjBigDecimal = (BigDecimal) it.next();
			                    if(lObjBigDecimal != null)
			                    {
			                    	amountDCRG += lObjBigDecimal.doubleValue();
			                    }		                    
			                }
			            }
					}
				}
			}
			if (resList1!=null && !resList1.isEmpty()) 
	        {
				Iterator it = resList1.iterator();
                while(it.hasNext()) 
                {
                    Object[] tuple = (Object[])it.next();
                    
					BigDecimal lObjBigDecimal = new BigDecimal("0");
		            if(tuple[0] != null)
		            {
		            	varTotalPay +=Double.parseDouble(tuple[0].toString());
		            }
		            if(tuple[1] != null)
		            {
		            	varTotalPay -=Double.parseDouble(tuple[1].toString());
		            }
		            if(tuple[3] != null)
		            {
		            	varTotalPay -=Double.parseDouble(tuple[3].toString());
		            }
		            if(tuple[4] != null)
		            {
		            	varTotalPay +=Double.parseDouble(tuple[4].toString());
		            }
		            if(tuple[5] != null)
		            {
		            	varTotalPay +=Double.parseDouble(tuple[5].toString());
		            }
		            if(tuple[6] != null)
		            {
		            	varTotalPay +=Double.parseDouble(tuple[6].toString());
		            }
		            if(amountCVP != null)
		            {
		            	varTotalPay += amountCVP;
		            }
		            if(amountDCRG != null)
		            {
		            	varTotalPay += amountDCRG;
		            }
		            if(tuple[7] != null)
		            {
		            	varTotalPay +=Double.parseDouble(tuple[7].toString());
		            }
		            if(tuple[8] != null)
		            {
		            	varTotalPay +=Double.parseDouble(tuple[8].toString());
		            }
		            if(tuple[9] != null)
		            {
		            	varTotalPay -=Double.parseDouble(tuple[9].toString());
		            }
                }
	        }
			
			//Deductions............
			
			StringBuilder lSBQuery1 = new StringBuilder();
			
			lSBQuery1.append(" SELECT tprd.recoveryType,tprd.amount,tpitcd.amount ");
			lSBQuery1.append(" FROM TrnPensionRecoveryDtls tprd,TrnPensionBillDtls tpbd,TrnPensionItCutDtls tpitcd ");
			lSBQuery1.append(" WHERE tpbd.pensionerCode = tprd.pensionerCode ");
			lSBQuery1.append(" AND tprd.pensionerCode = tpitcd.pensionerCode ");
			lSBQuery1.append(" AND tpbd.ppoNo = :ppoNo ");
			lSBQuery1.append(" AND tpitcd.fromMonth <= " + lStrForMonthYear);
			lSBQuery1.append(" AND tpitcd.toMonth >= " + lStrForMonthYear);
			lSBQuery1.append(" AND tprd.fromMonth <= " + lStrForMonthYear);
			lSBQuery1.append(" AND tprd.toMonth >= " + lStrForMonthYear);
			
			
			Query Query11=ghibSession.createQuery(lSBQuery1.toString());
			Query11.setParameter("ppoNo", lStrPPONo);	
			resList11=Query11.list();
			if (resList11!=null && !resList11.isEmpty()) 
	        {                
	            Iterator it = resList11.iterator();
	            while(it.hasNext()) 
	            {
	                Object[] tuple = (Object[])it.next();
	                
	                if(tuple[0] != null)
	                {
	                	recoveryType = tuple[0].toString();
	                	if(tuple[1] != null)
	                	{                    		
	                		if(recoveryType.equals("HBA"))
	                    	{
	                    		amountHBA = tuple[1].toString();
	                    	}
	                    	if(recoveryType.equals("MCA"))
	                    	{
	                    		amountMCA = tuple[1].toString();
	                    	}
	                    	if(recoveryType.equals("Others"))
	                    	{
	                    		amountOthers = tuple[1].toString();
	                    	}
	                	}
	                	if(tuple[2] != null)
	                	{
	                		amountIT = tuple[2].toString();
	                	}
	                }
	            }   
	        }
	        if(amountHBA != null)
	        {
	        	varTotalDeduc +=Double.parseDouble(amountHBA); 
	        }
	        if(amountMCA != null)
	        {
	        	varTotalDeduc +=Double.parseDouble(amountMCA); 
	        }
	        if(amountRent != null)
	        {
	        	varTotalDeduc +=Double.parseDouble(amountRent); 
	        }
	        if(amountIT != null)
	        {
	        	varTotalDeduc +=Double.parseDouble(amountIT); 
	        }
	        if(amountOthers != null)
	        {
	        	varTotalDeduc +=Double.parseDouble(amountOthers); 
	        }
	        Return = new ArrayList();
	        
	        Return.add(varTotalPay - varTotalDeduc);
		}
		catch(Exception e)
    	{
    		gLogger.info(e);
    		e.printStackTrace();
    	}
		return Return;
    }
    //Method by Rupsa for pensioner's monthly Details
    public List getPensionerDetailsForMonthly(String lStrPPONo,String lStrForMonthYear,String locCode)
    {
    	List resList = null;
    	String lStrMonthly = "Monthly";
    	
		try
		{			
			StringBuilder lSBQuery = new StringBuilder();
			int for_month_year = Integer.parseInt(lStrForMonthYear);
			
			lSBQuery.append(" SELECT distinct tpbd.ppoNo,tpbd.pensionerName,tprh.schemeType,tprh.pensionType, ");
			lSBQuery.append(" mb.bankName,rbb.branchName,tpbd.accountNo, ");
			lSBQuery.append(" tpbd.pensionAmount-tpbd.pensnCutAmount+tpbd.dpAmount-tpbd.cvpMonthAmount, ");
			lSBQuery.append(" tpbd.tiAmount,tpbd.tiArrearAmount,tpbd.irAmount,tpbd.medicalAllowenceAmount,tpbd.personalPensionAmount, ");
			lSBQuery.append(" tpbd.moCommission,tpbd.incomeTaxCutAmount, ");
			lSBQuery.append(" tpbd.reducedPension ");
			lSBQuery.append(" FROM MstBank mb,RltBankBranch rbb,TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh,TrnPensionRqstHdr tprh ");			
			lSBQuery.append(" WHERE tpbd.ppoNo = :ppoNo ");
			lSBQuery.append(" AND tprh.ppoNo = tpbd.ppoNo ");
			lSBQuery.append(" AND  tpbd.trnPensionBillHdrId = tpbh.trnPensionBillHdrId ");
			lSBQuery.append(" AND  tpbh.billType = :monthly ");
			lSBQuery.append(" AND  tpbh.forMonth = :forMonthYear ");
			lSBQuery.append(" AND mb.bankCode = rbb.bankCode ");
			lSBQuery.append(" AND tpbh.branchCode = rbb.branchCode AND rbb.locationCode = :locationCode ");
			 			 
			 Query Query=ghibSession.createQuery(lSBQuery.toString());
			 Query.setParameter("ppoNo", lStrPPONo);
			 Query.setParameter("monthly", lStrMonthly);
			 Query.setParameter("forMonthYear", for_month_year);
			 Query.setParameter("locationCode", locCode);
			 
			resList=Query.list();
			//System.out.println("resList.size is "+resList.size());
    	}
    	catch(Exception e)
    	{
    		gLogger.info(e);
    		e.printStackTrace();
    	}
    	return resList;
    	
    }
    
    //Method by Rupsa for Recovery
    public BigDecimal getRecoveryDeduction(String lStrPPONo,String lStrForMonthYear)
    {
    	BigDecimal total_deduction = null;	
    	String lStrRecoveryFromFlag = "Monthly";
    	
		try
		{
			List resList = null;
			
			StringBuilder lSBQuery = new StringBuilder();
			
			lSBQuery.append(" SELECT sum(tprd.amount) ");
			lSBQuery.append(" FROM TrnPensionRecoveryDtls tprd,TrnPensionBillDtls tpbd ");
			lSBQuery.append(" WHERE tpbd.pensionerCode = tprd.pensionerCode ");
			lSBQuery.append(" AND tpbd.ppoNo = :ppoNo ");
			lSBQuery.append(" AND tprd.recoveryFromFlag = :recoveryFromFlag ");
			lSBQuery.append(" AND tprd.fromMonth <= " + lStrForMonthYear);
			lSBQuery.append(" AND tprd.toMonth >= " + lStrForMonthYear);
			
			Query Query=ghibSession.createQuery(lSBQuery.toString());
			Query.setParameter("ppoNo", lStrPPONo);	
			Query.setParameter("recoveryFromFlag", lStrRecoveryFromFlag);	
			
			resList=Query.list();
			if(resList.size() > 0)
			{
				total_deduction  = ((BigDecimal)resList.get(0));
			}
			else
				total_deduction = new BigDecimal(0);
            			
    	}
    	catch(Exception e)
    	{
    		gLogger.info(e);
    		e.printStackTrace();
    	}
    	return total_deduction;
    }
    //Method by Rupsa for previous month amount
    public BigDecimal getPreviousmonthAmount(String lStrPPONo,String lStrForMonthYear)
    {
    	BigDecimal previousAmount = null;
    	List resList = null;
    	String  lStrMonthly = "Monthly";
    	
		try
		{			
			StringBuilder lSBQuery = new StringBuilder();
			
			int for_month = Integer.parseInt(lStrForMonthYear.substring(4,6));
			int for_year = Integer.parseInt(lStrForMonthYear.substring(0,4));
			
			int previous_year = 0;
			int previous_month = 0;
			
			if(for_month == 1)
			{
				previous_year = for_year - 1;
			}
			else
			{
				previous_year = for_year;
			}
			if(for_month == 1)
			{
				previous_month = 12;
			}
			else
			{
				previous_month = for_month - 1;
			}
			
			String last_month = String.valueOf(previous_month);
			
			if(Integer.parseInt(last_month) < 10)
			{
				last_month = "0" + last_month;
			}
			String last_forMonth = previous_year + last_month;
			int new_last_month = Integer.parseInt(last_forMonth);
			
			lSBQuery.append(" SELECT tpbd.reducedPension ");
			lSBQuery.append(" FROM TrnPensionBillDtls tpbd,TrnPensionBillHdr tpbh ");			
			lSBQuery.append(" WHERE tpbd.ppoNo = :ppoNo ");
			lSBQuery.append(" AND  tpbd.trnPensionBillHdrId = tpbh.trnPensionBillHdrId ");
			lSBQuery.append(" AND  tpbh.billType = :monthly ");
			lSBQuery.append(" AND tpbh.forMonth = :lastForMonth ");
			
			 			 
			 Query Query=ghibSession.createQuery(lSBQuery.toString());
			 Query.setParameter("ppoNo", lStrPPONo);
			 Query.setParameter("lastForMonth", new_last_month);
			 Query.setParameter("monthly", lStrMonthly);
			 
			 resList = Query.list();
			 
			 if(resList.size() > 0)
			 {
				 previousAmount  = (BigDecimal)resList.get(0);
			 }
			 else
				 previousAmount = new BigDecimal(0);
    	}
    	catch(Exception e)
    	{
    		gLogger.info(e);
    		e.printStackTrace();
    	}
    	return previousAmount;
    }
    
    public List getPensionCaseReport(String toDate, String fromDate, String Scheme, String Class,long langId)
	{
		ArrayList arrList = null;
		
		StringBuilder lPensionerName;
		
		String lstrScheme = Scheme;
		String lstrClass = Class;
		
		BigDecimal reduced_pension = new BigDecimal(0);
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" select A.ppoNo,B.firstName,B.middleName,B.lastName, ");
			lSBQuery.append(" D.locName,A.schemeType,A.dppfAmount,A.typeFlag,D.parentLocId, ");
			lSBQuery.append(" (SELECT CL.locName FROM CmnLocationMst CL WHERE CL.locationCode = D.parentLocId and CL.cmnLanguageMst.langId = :langId)");
			lSBQuery.append(" from TrnPensionRqstHdr A,MstPensionerHdr B,CmnLocationMst D ");
			lSBQuery.append(" where A.pensionerCode = B.pensionerCode ");
			lSBQuery.append(" and A.locationCode = D.locationCode and D.cmnLanguageMst.langId = :langId ");
			lSBQuery.append(" and A.caseStatus = 'APPROVED' and B.caseStatus = 'APPROVED'");
			
			if(fromDate != null)
			{
				lSBQuery.append(" and A.ppoInwardDate >= '"+fromDate+"'");
			}
			if(toDate != null)
			{
				lSBQuery.append(" and A.ppoInwardDate <= '"+toDate+"'");
			}
			if(lstrScheme != "")
			{
				lSBQuery.append(" and A.schemeType= '"+lstrScheme+"'" );
			}
			if(lstrClass != "" && lstrClass.equalsIgnoreCase("Actual"))
			{
				lSBQuery.append(" and A.typeFlag = 'R'" );
			}
			else if(lstrClass != "" && lstrClass.equalsIgnoreCase("Provisional"))
			{
				lSBQuery.append(" and A.typeFlag = 'P'" );
			}
			
			Query Query=ghibSession.createQuery(lSBQuery.toString());
			Query.setParameter("langId", langId);
			
			List resList=Query.list();
			
			if (resList!=null && !resList.isEmpty()) 
			{
				arrList=new ArrayList();
				Iterator it = resList.iterator();
				int srNo = 1;
				
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					
					ArrayList arrInner = new ArrayList();
					arrInner.add(srNo);
					arrInner.add(tuple[0]);
					
					lPensionerName = new StringBuilder();
					if(tuple[1] != null)
					{
						lPensionerName.append(tuple[1]);
						lPensionerName.append(" ");
					}
					if(tuple[2] != null)
					{
						lPensionerName.append(tuple[2]);
						lPensionerName.append(" ");
					}
					if(tuple[3] != null)
					{
						lPensionerName.append(tuple[3]);
					}
					arrInner.add(lPensionerName.toString());
					
					
					if(tuple[8].toString().equals("100051"))//treasury
					{
						arrInner.add(tuple[4]);
						arrInner.add("");
					}
					else//subtreasury
					{
						arrInner.add(tuple[9]);
						arrInner.add(tuple[4]);						
					}
					
					
					arrInner.add(tuple[5]);
					if(tuple[6] != null)
					{
						reduced_pension = new BigDecimal(tuple[6].toString()).setScale(2,BigDecimal.ROUND_UP);
						arrInner.add(reduced_pension);
					}
					else
					{
						arrInner.add("0.00");
					}
					
					if(tuple[7] != null )
					{	
						
						if(tuple[7].toString().equalsIgnoreCase("R"))
							{
								arrInner.add("Final");
							}
						else
						{
							
							arrInner.add("Provisional");
						}
					}
					arrList.add(arrInner);
					srNo++;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return arrList;
	}
    

    public ArrayList getSchemeValues(String lStrScheme, String lStrLangId, String lstrLocId) throws Exception
    {
		ArrayList arrValues = new ArrayList();
		long lLangId = 0l;
		
		try
		{
			Session hibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			StringBuilder lSBQuery = new StringBuilder();
						
			lSBQuery.append(" select c1.lookupName,c1.lookupId from CmnLookupMst c1,CmnLookupMst c2,CmnLanguageMst c3 ");
	        lSBQuery.append(" where c1.cmnLanguageMst.langId = c2.cmnLanguageMst.langId ");
	        lSBQuery.append(" and c3.langShortName = :lLangId ");
	        lSBQuery.append(" and c1.parentLookupId = c2.lookupId ");
	        lSBQuery.append(" and c2.lookupName = :lLookupName ");
			
			Query lQuery = hibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lLangId", lStrLangId);
	        lQuery.setParameter("lLookupName", lStrScheme.toString());
	        
	        List listValues = lQuery.list();
	        
			if (listValues!=null && !listValues.isEmpty()) 
			{	
				Iterator it = listValues.iterator();
				while(it.hasNext()) 
				{
					ComboValuesVO vo= new ComboValuesVO(  );
					Object[] tuple = (Object[])it.next();
					vo.setDesc(tuple[0].toString());
					vo.setId(tuple[1].toString());
					
					arrValues.add(vo);
				}	
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Error is :"+e);
			throw(e);
		}
		return arrValues;
    }
	
	/**
	 * To display values in combo box Actual/Provional on parameter page
	 * @param lStrLangId String
	 * @param lstrLocId String
	 * @return Arraylist
	 */
	
	public ArrayList getClass(String lStrClass, String lStrLangId, String lstrLocId) throws Exception
    {
		ArrayList arrValues = new ArrayList();
		long lLangId = 0l;
		
		try
		{
			Session hibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			StringBuilder lSBQuery = new StringBuilder();
						
			lSBQuery.append(" select c1.lookupName,c1.lookupId from CmnLookupMst c1,CmnLookupMst c2,CmnLanguageMst c3 ");
	        lSBQuery.append(" where c1.cmnLanguageMst.langId = c2.cmnLanguageMst.langId ");
	        lSBQuery.append(" and c3.langShortName = :lLangId ");
	        lSBQuery.append(" and c1.parentLookupId = c2.lookupId ");
	        lSBQuery.append(" and c2.lookupName = :lLookupName ");
			
			Query lQuery = hibSession.createQuery(lSBQuery.toString());

	        lQuery.setParameter("lLangId", lStrLangId);
	        lQuery.setParameter("lLookupName", lStrClass.toString());
	        
	        List listValues = lQuery.list();
	        
			if (listValues!=null && !listValues.isEmpty()) 
			{	
				Iterator it = listValues.iterator();
				while(it.hasNext()) 
				{
					ComboValuesVO vo= new ComboValuesVO(  );
					Object[] tuple = (Object[])it.next();
					vo.setDesc(tuple[0].toString());
					vo.setId(tuple[1].toString());
					
					
					arrValues.add(vo);
				}	
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Error is :"+e);
			throw(e);
		}
		return arrValues;
    }
	
    
    //to get head code and corresponding description 
	public ArrayList getHeadCode(String lStrLangId,String lStrLocationCode) throws Exception 
	{
		List lLstVO;
		String subjectId = null;
		String subjectDesc = null;
		ArrayList arrHeadCodes = new ArrayList();
		Hashtable hashtable = null;
		String myHeadCode="";
		int i=0;
		
		try
		{			
		
			//StringBuffer lsb = new StringBuffer("select distinct headCode from MstPensionHeadcode where locationCode = :lLocationCode order by finYearCode");
			String mySql = "SELECT h.headCode,h.description FROM MstPensionHeadcode h group by  h.headCode,h.description order by h.headCode";			
			Session hibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();			
			Query lQuery = hibSession.createQuery(mySql);			
			lLstVO = lQuery.list();
			if(lLstVO != null && lLstVO.size()>0)
			{	
				for(i=0;i<lLstVO.size();i++)
				{
					Object[] lObjArray = (Object[]) lLstVO.get(i);
					
					if(lObjArray != null)
					{	
						String lHeadCode = lObjArray[0].toString();
						String lHeadDesc = lObjArray[1].toString();
						String lFullHeadDesc = lHeadCode+"  __  "+lHeadDesc;
												
						ComboValuesVO vo = new ComboValuesVO();
						vo.setId(lHeadCode);
						vo.setDesc(lFullHeadDesc);
						arrHeadCodes.add(vo);
					}
				}
			}
//			inputMap.put("HeadCodes", arrHeadCodes);
//			resObj.setResultValue(inputMap);
			
		} 
		catch (Exception e) 
		{
			glogger.error("Exception::" + e.getMessage(), e);
			throw(e);
		}
		return arrHeadCodes;
	}
	
	
	
	 /**
     * Method for query of Head Wise pension Summay report
     * @param toDate
     * @param fromDate
     * @param HeadCode
     * @return Scheme
     */
	public List getPensionerHeadWiseSummary(String toDate, String fromDate,Long headCode, String Scheme)
	{
		ArrayList arrList = null;
		StringBuilder lPensionerName;
		String lstrScheme = Scheme;
		Long lheadCode = headCode;
		DecimalFormat df=new DecimalFormat("0.00");
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" select rq.ppoNo,hd.firstName,hd.middleName,hd.lastName, ");
			lSBQuery.append(" pd.acountNo,sum(bd.pensionAmount),rq.dpPercent,rq.tiPercent,  ");
			lSBQuery.append(" sum(bd.medicalAllowenceAmount),sum(bd.cvpMonthAmount),sum(bd.arrearAmount), ");
			lSBQuery.append(" sum(bd.incomeTaxCutAmount),sum(bd.recoveryAmount),sum(bd.pensnCutAmount),hc.description ");
			lSBQuery.append(" from TrnPensionRqstHdr rq, MstPensionHeadcode hc,MstPensionerHdr hd, ");
			lSBQuery.append(" MstPensionerDtls pd,TrnPensionBillDtls bd,TrnPensionBillHdr  bh ");
			lSBQuery.append(" where hd.pensionerCode = rq.pensionerCode and pd.pensionerCode = rq.pensionerCode and  ");
			lSBQuery.append(" bd.pensionerCode = rq.pensionerCode and bh.trnPensionBillHdrId = bd.trnPensionBillHdrId and ");
			lSBQuery.append(" hc.headCode = rq.headCode  ");
			
			
			if(fromDate != null)
			{
				lSBQuery.append(" and bh.billDate >= '"+fromDate+"'");
			}
			if(toDate != null)
			{
				lSBQuery.append(" and bh.billDate <= '"+toDate+"'");
			}
			if(lstrScheme != null)
			{
				lSBQuery.append(" and rq.schemeType= '"+lstrScheme+"'" );
			}
			if(lheadCode != null)
			{
				lSBQuery.append(" and bh.headCode = '"+lheadCode+"'" );
			}
			/*else if(lstrClass != "" && lstrClass.equalsIgnoreCase("Provisional"))
			{
				lSBQuery.append(" and A.typeFlag = 'P'" );
			}*/
			lSBQuery.append(" group by rq.ppoNo,hd.firstName,hd.middleName,hd.lastName, ");
			lSBQuery.append(" pd.acountNo, rq.dpPercent,rq.tiPercent,hc.description " );
			
			Query lQuery=ghibSession.createQuery(lSBQuery.toString());
			List resList=lQuery.list();
			
			if (resList!=null && !resList.isEmpty()) 
			{
				arrList=new ArrayList();
				Iterator it = resList.iterator();
				
				
				while(it.hasNext()) 
				{
					BigDecimal total = new BigDecimal(0);
					BigDecimal pensionAmount = new BigDecimal(0);
					BigDecimal ldpAmount = new BigDecimal(0);
					BigDecimal pensionCut = new BigDecimal(0);
					BigDecimal basicPension = new BigDecimal(0);
					
					Object[] tuple = (Object[])it.next();
					
					ArrayList<Object> arrInner = new ArrayList();
					arrInner.add(tuple[0]);//ppoNo
					
					
					lPensionerName = new StringBuilder();
					if(tuple[1] != null)
					{
						lPensionerName.append(tuple[1]);
						lPensionerName.append(" ");
					}
					if(tuple[2] != null)
					{
						lPensionerName.append(tuple[2]);
						lPensionerName.append(" ");
					}
					if(tuple[3] != null)
					{
						lPensionerName.append(tuple[3]);
					}
					arrInner.add(lPensionerName.toString());//name
					
					if(tuple[4]!=null)
					{
						arrInner.add(tuple[4]);//A/C no
					}
					else
					{
						arrInner.add(0);
					}
					
					if(tuple[5]!=null)
					{						
						pensionAmount = new BigDecimal(tuple[5].toString()).setScale(2,BigDecimal.ROUND_UP);
						pensionCut = new BigDecimal(tuple[13].toString()).setScale(2,BigDecimal.ROUND_UP);
						basicPension = pensionAmount.subtract(pensionCut);
						total = total.add(pensionAmount);

						arrInner.add(pensionAmount);//pension amount						
					}
					else 
					{
						arrInner.add("0.00");
					}
					if(tuple[6]!=null)//dp Amnt
					{
						BigDecimal dpPercent = new BigDecimal(tuple[6].toString());
						ldpAmount = basicPension.add(basicPension.multiply(dpPercent.divide(new BigDecimal(100)))).setScale(2,BigDecimal.ROUND_UP); 
						
						arrInner.add(ldpAmount);
						total = total.add(ldpAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[7]!=null)//Ti Amnt
					{
						BigDecimal tiPercent = new BigDecimal(tuple[7].toString());					
						BigDecimal basicpension1 = basicPension.add(ldpAmount);
						BigDecimal ltiAmount = basicpension1.add(basicpension1.multiply(tiPercent.divide(new BigDecimal(100)))).setScale(2,BigDecimal.ROUND_UP);	
						
						arrInner.add(ltiAmount);
						total = total.add(ltiAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[8]!=null)
					{
						BigDecimal maAmount = new BigDecimal(tuple[8].toString()).setScale(2,BigDecimal.ROUND_UP);					
						
						arrInner.add(maAmount);//M A AMNt
						total = total.add(maAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[9]!=null)
					{
						BigDecimal cvpAmount = new BigDecimal(tuple[9].toString()).setScale(2,BigDecimal.ROUND_UP);						
						arrInner.add(cvpAmount);// CVP Month
						total = total.subtract(cvpAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[10]!=null)
					{
						BigDecimal arrearAmount = new BigDecimal(tuple[10].toString()).setScale(2,BigDecimal.ROUND_UP);						
						arrInner.add(arrearAmount);// Arrear amnt
						total = total.add(arrearAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[11]!=null)
					{
						BigDecimal itCutAmount = new BigDecimal(tuple[11].toString()).setScale(2,BigDecimal.ROUND_UP);						
						arrInner.add(itCutAmount);// IT cut
						total = total.subtract(itCutAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[12]!=null)
					{
						BigDecimal recoveryAmount = new BigDecimal(tuple[12].toString()).setScale(2,BigDecimal.ROUND_UP);						
						arrInner.add(recoveryAmount);//recovery
						total = total.subtract(recoveryAmount); 
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[13]!=null)
					{
						BigDecimal pnsnCutAmount = new BigDecimal(tuple[13].toString()).setScale(2,BigDecimal.ROUND_UP);						
						arrInner.add(pnsnCutAmount);//pnsn Cut
						total = total.subtract(pnsnCutAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					if(total != null)
					{
						arrInner.add(total);//total
					}
					else
					{
						arrInner.add("0.00");
					}
					arrInner.add(tuple[14]);
					arrList.add(arrInner);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return arrList;
	}
	
	
	  /**
     * Method for query of Amount Wise pension case report
     * @param toDate
     * @param fromDate
     * @param PPO.No.
     * @return list
     */
	
	
	public List getAmntWiseReport(String toDate, String fromDate, String Scheme, String Class)
	{
		ArrayList arrList = null;
		StringBuilder lPensionerName;
		String lstrScheme = Scheme;
		String lstrClass = Class;
		DecimalFormat df=new DecimalFormat("0.00");
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" select rq.ppoNo,hd.firstName,hd.middleName,hd.lastName, ");
			lSBQuery.append(" pd.acountNo,sum(bd.pensionAmount),rq.dpPercent,rq.tiPercent,  ");
			lSBQuery.append(" sum(bd.medicalAllowenceAmount),sum(bd.cvpMonthAmount),sum(bd.arrearAmount), ");
			lSBQuery.append(" sum(bd.incomeTaxCutAmount),sum(bd.recoveryAmount),sum(bd.pensnCutAmount),rq.typeFlag ");
			lSBQuery.append(" from TrnPensionRqstHdr rq,MstPensionerHdr hd, ");
			lSBQuery.append(" MstPensionerDtls pd,TrnPensionBillDtls bd,TrnPensionBillHdr  bh ");
			lSBQuery.append(" where hd.pensionerCode = rq.pensionerCode and pd.pensionerCode = rq.pensionerCode and  ");
			lSBQuery.append(" bd.pensionerCode = rq.pensionerCode and bh.trnPensionBillHdrId = bd.trnPensionBillHdrId and  ");
			lSBQuery.append(" (bh.billType = 'Monthly'  or bh.billType = 'Pension'  )");
			
			if(fromDate != null)
			{
				lSBQuery.append(" and bh.billDate >= '"+fromDate+"'");
			}
			if(toDate != null)
			{
				lSBQuery.append(" and bh.billDate <= '"+toDate+"'");
			}
			if(lstrScheme != "")
			{
				lSBQuery.append(" and rq.schemeType= '"+lstrScheme+"'" );
			}
			if(lstrClass != "" && lstrClass.equalsIgnoreCase("Actual"))
			{
				lSBQuery.append(" and rq.typeFlag = 'R'" );
			}
			else if(lstrClass != "" && lstrClass.equalsIgnoreCase("Provisional"))
			{
				lSBQuery.append(" and rq.typeFlag = 'P'" );
			}
			
			lSBQuery.append(" group by rq.ppoNo,hd.firstName,hd.middleName,hd.lastName, ");
			lSBQuery.append(" pd.acountNo, rq.dpPercent,rq.tiPercent,rq.typeFlag  ");
			
			Query lQuery=ghibSession.createQuery(lSBQuery.toString());
			List resList=lQuery.list();
			
			if (resList!=null && !resList.isEmpty()) 
			{
				arrList=new ArrayList();
				Iterator it = resList.iterator();
				BigDecimal total = new BigDecimal(0);
				BigDecimal pensionAmount = new BigDecimal(0);
				BigDecimal ldpAmount = new BigDecimal(0);
				BigDecimal pensionCut = new BigDecimal(0);
				BigDecimal basicPension = new BigDecimal(0);
			
			
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
				
					ArrayList<Object> arrInner = new ArrayList();
					arrInner.add(tuple[0]);//ppoNo
					
				
					lPensionerName = new StringBuilder();
					if(tuple[1] != null)
					{
						lPensionerName.append(tuple[1]);
						lPensionerName.append(" ");
					}
					if(tuple[2] != null)
					{
						lPensionerName.append(tuple[2]);
						lPensionerName.append(" ");
					}
					if(tuple[3] != null)
					{
						lPensionerName.append(tuple[3]);
					}
					arrInner.add(lPensionerName.toString());//name
					
					if(tuple[4]!=null)
					{
						arrInner.add(tuple[4]);//A/C no
					}
					else
					{
						arrInner.add("");
					}
					
					if(tuple[5]!=null)
					{
						pensionAmount = new BigDecimal(tuple[5].toString()).setScale(2,BigDecimal.ROUND_UP);
						pensionCut = new BigDecimal(tuple[13].toString()).setScale(2,BigDecimal.ROUND_UP);
						basicPension = pensionAmount.subtract(pensionCut);
						total = total.add(pensionAmount);
						arrInner.add(pensionAmount);//pension amount
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[6]!=null)//dp Amnt
					{
						BigDecimal dpPercent = new BigDecimal(tuple[6].toString()); 
						ldpAmount = basicPension.add(basicPension.multiply(dpPercent.divide(new BigDecimal(100)))).setScale(2,BigDecimal.ROUND_UP); 						
						arrInner.add(ldpAmount);
						total = total.add(ldpAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[7]!=null)//Ti Amnt
					{
						BigDecimal tiPercent = new BigDecimal(tuple[7].toString());					
						BigDecimal basicpension1 = basicPension.add(ldpAmount);
						BigDecimal ltiAmount = basicpension1.add(basicpension1.multiply(tiPercent.divide(new BigDecimal(100)))).setScale(2,BigDecimal.ROUND_UP);	
						
						arrInner.add(ltiAmount);
						total = total.add(ltiAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[8]!=null)
					{
						BigDecimal maAmount = new BigDecimal(tuple[8].toString()).setScale(2,BigDecimal.ROUND_UP);					
						
						arrInner.add(maAmount);//M A AMNt
						total = total.add(maAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[9]!=null)
					{
						BigDecimal cvpAmount = new BigDecimal(tuple[9].toString()).setScale(2,BigDecimal.ROUND_UP);						
						arrInner.add(cvpAmount);// CVP Month
						total = total.subtract(cvpAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[10]!=null)
					{
						BigDecimal arrearAmount = new BigDecimal(tuple[10].toString()).setScale(2,BigDecimal.ROUND_UP);						
						arrInner.add(arrearAmount);// Arrear amnt
						total = total.add(arrearAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[11]!=null)
					{
						BigDecimal itCutAmount = new BigDecimal(tuple[11].toString()).setScale(2,BigDecimal.ROUND_UP);						
						arrInner.add(itCutAmount);// IT cut
						total = total.subtract(itCutAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[12]!=null)
					{
						BigDecimal recoveryAmount = new BigDecimal(tuple[12].toString()).setScale(2,BigDecimal.ROUND_UP);						
						arrInner.add(recoveryAmount);//recovery
						total = total.subtract(recoveryAmount); 
					}
					else 
					{
						arrInner.add("0.00");
					}
					
					if(tuple[13]!=null)
					{
						BigDecimal pnsnCutAmount = new BigDecimal(tuple[13].toString()).setScale(2,BigDecimal.ROUND_UP);						
						arrInner.add(pnsnCutAmount);//pnsn Cut
						total = total.subtract(pnsnCutAmount);
					}
					else 
					{
						arrInner.add("0.00");
					}
					if(total != null)
					{
						arrInner.add(total);//total
					}
					else
					{
						arrInner.add("0.00");
					}					
					if(tuple[14]!=null && tuple[14].toString().equalsIgnoreCase("R"))
					{
						arrInner.add("Actual");
					}
					else if(tuple[14]!=null && tuple[14].toString().equalsIgnoreCase("P"))
					{
						arrInner.add("Provisional");
					}
					arrList.add(arrInner);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return arrList;
	}
    
	 /**
      * First Payment Report.
      * @author Sagar Patel
      * @param toDate
      * @param fromDate
      * @return
      */
     public List getFirstPaymentReport(String toDate, String fromDate)
     {
         ArrayList arrList = null;
         StringBuilder lPensionerName;
         
         String lStrApproved = bundleConst.getString("STATUS.APPROVED");
         String lStrY = bundleConst.getString("ACTV.YES");
         
         try
         {
             StringBuilder lSBQuery = new StringBuilder();
             
             lSBQuery.append(" select hd.head_code, mhd.description, br.ppo_no, mh.first_name, mh.middle_name, ");
             lSBQuery.append(" mh.last_name,br.subject_id,br.bill_net_amount  ");
             lSBQuery.append(" from trn_bill_register br, trn_pension_rqst_hdr hd,mst_pensioner_hdr mh, ");
             lSBQuery.append(" mst_pension_headcode mhd ");
             lSBQuery.append(" where br.ppo_no = hd.ppo_no and hd.case_status = :lApproved ");
             lSBQuery.append(" and hd.pensioner_code = mh.pensioner_code and mh.case_status = :lApproved ");
             lSBQuery.append(" and hd.head_code = mhd.head_code and mhd.active_flag = :lY ");       
             
             /*if(fromDate != null)
             {
                 lSBQuery.append(" and  br.cheque_disp_dt >= '"+fromDate+"'");
             }
             if(toDate != null)
             {
                 lSBQuery.append(" br.cheque_disp_dt <= '"+toDate+"'");
             }*/
             
             lSBQuery.append(" and br.subject_id in (9,10,11) and br.ppo_no is not null ORDER BY br.ppo_no,br.subject_id");
             
             Query lQuery=ghibSession.createSQLQuery(lSBQuery.toString());
             
             lQuery.setParameter("lApproved", lStrApproved);
             lQuery.setParameter("lY", lStrY);
             
             List resList=lQuery.list();
               
             
             if (resList!=null && !resList.isEmpty()) 
             {
                 String lStrOldPPONo = null;
                 String lStrNewPPONo = null;
                 
                 int flag=0;
                 
                 arrList=new ArrayList();
                 ArrayList arrInner = new ArrayList();
                 
                 for(int i=0;i<resList.size();i++)
                 {
                     Object[] tuple = (Object[]) resList.get(i) ;
                     
                     lStrNewPPONo = tuple[2].toString();
                     
                     if(i == 0)
                     {
                         arrInner = new ArrayList();
                     }
                     else if(!(lStrOldPPONo.equalsIgnoreCase(lStrNewPPONo)))
                     {
                         arrList.add(arrInner);
                         arrInner = new ArrayList();
                         flag = 0;
                     }
                     else if(lStrOldPPONo.equalsIgnoreCase(lStrNewPPONo))
                     {
                         flag = 1;
                     }
                     
                     if(flag == 0)
                     {
                         arrInner.add(0,tuple[0]);      
                         arrInner.add(1,tuple[1]);
                         arrInner.add(2,tuple[2]);
                         
                         lPensionerName = new StringBuilder();
                     
                         if(tuple[3] != null)  
                         {
                             lPensionerName.append(tuple[3].toString());
                             lPensionerName.append(" ");
                         }
                         if(tuple[4] != null)
                         {
                             lPensionerName.append(tuple[4]);
                             lPensionerName.append(" ");
                         } 
                         if(tuple[5] != null)
                         {
                             lPensionerName.append(tuple[5]);
                         }
                         arrInner.add(3,lPensionerName.toString());
                         
                         Integer vint = Integer.parseInt(tuple[6].toString());
                         
                         arrInner.add(4,(vint == 9 ? tuple[7].toString()+".00" : "-"));
                         arrInner.add(5,(vint == 10 ? tuple[7].toString()+".00" : "-"));
                         arrInner.add(6,(vint == 11 ? tuple[7].toString()+".00" : "-"));
                         
                    }
                         
                    if(flag == 1) 
                    {
                        Integer vint = Integer.parseInt(tuple[6].toString());
                        if( vint == 9)
                        {
                           arrInner.add(4,tuple[7].toString()+".00");
                        }    
                        if( vint ==10 )
                        {
                           arrInner.add(5,tuple[7].toString()+".00");
                        }
                        if( vint ==11 )
                        {
                           arrInner.add(6,tuple[7].toString()+".00");
                        }
                    }
                    
                    lStrOldPPONo = tuple[2].toString();
                    
                    if(i==((resList.size())-1))
                    {
                        arrList.add(arrInner);
                    }
                    
                 }
             }
         }
         catch (Exception e) 
         {
        	 gLogger.error(" Erroor  In >> "+e, e);
             e.printStackTrace();
         }
         return arrList;
     }
     
     /**
 	 * Treasury Transfer Case Type
 	 * 
 	 * @author Sagar
 	 * @throws Exception 
 	 */
 	public ArrayList getTrnsfrCaseType(String lStrLangId,String lStrLocationcode) throws Exception
 	{
 		String lStrTrnsfered = bundleConst.getString("STATUS.TRANSFERED");
 		String lStrReceived = bundleConst.getString("STATUS.RECEIVED");
 		
 		ArrayList arrMothsList = new ArrayList();
 		try
 		{
			ComboValuesVO vo1 = new ComboValuesVO();
			vo1.setId("1");
			vo1.setDesc(lStrTrnsfered);
			arrMothsList.add(vo1);
			ComboValuesVO vo2 = new ComboValuesVO();
			vo2.setId("2");
			vo2.setDesc(lStrReceived);
			arrMothsList.add(vo2);
 			
 		} 
 		catch (Exception e) 
 		{
 			glogger.error("Exception::" + e.getMessage(), e);
 			throw(e);
 		}
 		return arrMothsList;
 	}
 	
 	/**
     * get All TresuryList
     * 
     * Written By Sagar
     */
	public ArrayList getAllTreasuryLst(String lStrLangId,String lStrLocationcode) throws Exception
    {
		ArrayList lListReturn = new ArrayList();
		List restLst = null;		
		try
        {
			Session hibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
            StringBuilder lSBQuery = new StringBuilder();

			lSBQuery.append(" select CLM.locId, CLM.locName " );
			lSBQuery.append(" from CmnLocationMst CLM ");
			lSBQuery.append(" where CLM.parentLocId = 100051 order by CLM.locName ");
			
			Query lQuery = hibSession.createQuery(lSBQuery.toString());

			restLst = lQuery.list();
			
			if (restLst != null)
            {
                Iterator lItr = restLst.iterator();
                Object[] lObj = null;
                ComboValuesVO lComVo = null;

                while (lItr.hasNext())
                {
                    lObj = (Object[]) lItr.next();
                    
                    if (lObj != null)
                    {
                        lComVo = new ComboValuesVO();
                        lComVo.setId(lObj[0].toString());
                        lComVo.setDesc(lObj[1].toString());
                        lListReturn.add(lComVo);
                    }
                }
            }
			
		}catch (Exception e) {
			logger.error("Error is : " + e, e);
            throw(e);
		}
		
		return lListReturn ;
	}
	
	/**
     * get All sub TresuryList of Treasury
     * 
     * Written By Sagar
     */
	public ArrayList getSubTreasurysOfTreasury(String parentParamValue,String langId, String locId) throws Exception
    {
        ArrayList arrBranchVO = new ArrayList();
        StringBuilder strQuery = new StringBuilder();
        ComboValuesVO cmbVO = null;
        List resultList;
        Iterator itr;
        Object[] obj;
        try
        {
        	Session hibSession = ServiceLocator.getServiceLocator().getSessionFactory().getCurrentSession();
			
        	strQuery.append(" SELECT locationCode,locName ");
            strQuery.append(" FROM CmnLocationMst ");
            strQuery.append(" WHERE parentLocId=:parentLocId ");
            //strQuery.append(" and cmnLanguageMst.langId=:langId ");
            Query hqlQuery = hibSession.createQuery(strQuery.toString());
            hqlQuery.setLong("parentLocId", Long.valueOf(parentParamValue));
            //hqlQuery.setString("langId", langId);
            resultList = hqlQuery.list();
            cmbVO = new ComboValuesVO();
            
            if (resultList != null && resultList.size() > 0)
            {
                itr = resultList.iterator();
                while (itr.hasNext())
                {
                    cmbVO = new ComboValuesVO();
                    obj = (Object[]) itr.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrBranchVO.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
        	logger.error("Error is : " + e, e);
            throw e;
        }
        return arrBranchVO;
    }
	
	/**
     * Transfer Case Data Report.
     * @author Sagar Patel
     * @param toDate
     * @param fromDate
     * @return
     */
    public List getTransferCaseData(String toDate, String fromDate,String caseType,String trsryCode,Long crntLocId,Long langID)
    {
        ArrayList arrList = null;
        StringBuilder lPensionerName;     
        
        try
        {
        	        	
            StringBuilder lSBQuery = new StringBuilder();
            
            lSBQuery.append(" SELECT TD.ppoNo ");
            lSBQuery.append(" ,(SELECT mh.firstName || ' ' || mh.middleName || ' ' || mh.lastName FROM MstPensionerHdr mh WHERE mh.pensionerCode = TD.pensionerCode)");
            lSBQuery.append(" ,(SELECT CL1.locName FROM CmnLocationMst CL1 WHERE CL1.locationCode = TD.fromLocation and CL1.cmnLanguageMst.langId = :llangID)");
            lSBQuery.append(" ,(SELECT CL2.locName FROM CmnLocationMst CL2 WHERE CL2.locationCode = TD.toLocation and CL2.cmnLanguageMst.langId = :llangID)");
            lSBQuery.append(" ,TD.status ");
            
            if(caseType.equalsIgnoreCase("1"))
        	{            	
            	lSBQuery.append(" ,(SELECT e.empPrefix || ' ' || e.empFname || ' ' || e.empMname || ' ' || e.empLname ");
            	lSBQuery.append(" FROM OrgEmpMst e, OrgUserpostRlt u WHERE u.orgUserMst.userId = e.orgUserMst.userId AND u.orgPostMstByPostId.postId = TD.receivePostId )");
            	lSBQuery.append(" FROM TrnPensionTransferDtls TD");
            	lSBQuery.append(" WHERE TD.toLocation = :lTrsryCode and TD.fromLocation = :lCrntLocId ");
            	lSBQuery.append(" AND TD.transferDate >= '"+fromDate+"' and TD.transferDate <= '"+toDate+"'");
        	}
            else if(caseType.equalsIgnoreCase("2"))
        	{
            	lSBQuery.append(" ,(SELECT e.empPrefix || ' ' || e.empFname || ' ' || e.empMname || ' ' || e.empLname ");
            	lSBQuery.append(" FROM OrgEmpMst e, OrgUserpostRlt u WHERE u.orgUserMst.userId = e.orgUserMst.userId AND u.orgPostMstByPostId.postId = TD.transferPostId )");
            	lSBQuery.append(" FROM TrnPensionTransferDtls TD");
            	lSBQuery.append(" WHERE TD.toLocation = :lCrntLocId and TD.fromLocation = :lTrsryCode ");
            	lSBQuery.append(" AND TD.receiveDate >= '"+fromDate+"' and TD.receiveDate <= '"+toDate+"' ");
        	}
                        
            Query lQuery=ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("lTrsryCode", trsryCode);
            lQuery.setParameter("lCrntLocId", crntLocId.toString());
            lQuery.setParameter("llangID", langID);
            
            List resList=lQuery.list();
              
            
            if (resList!=null && !resList.isEmpty()) 
            {
                arrList=new ArrayList();
                ArrayList arrInner = null;
                
                for(int i=0;i<resList.size();i++)
                {
                	arrInner = new ArrayList();
                	Object[] tuple = (Object[]) resList.get(i) ;
                    
                    arrInner.add(i+1);
                    
                    if(tuple[0] != null)  
                    {
                    	arrInner.add(tuple[0]);
                    }
                    else
                    {
                    	arrInner.add("-");
                    }
                    
                    if(tuple[1] != null)  
                    {
                    	arrInner.add(tuple[1]);
                    }
                    else
                    {
                    	arrInner.add("-");
                    }
                    
                    if(tuple[2] != null)  
                    {
                    	arrInner.add(tuple[2]);
                    }
                    else
                    {
                    	arrInner.add("-");
                    }
                    
                    if(tuple[3] != null)  
                    {
                    	arrInner.add(tuple[3]);
                    }
                    else
                    {
                    	arrInner.add("-");
                    }
                    
                    if(tuple[4] != null)  
                    {
                    	arrInner.add(tuple[4]);
                    }
                    else
                    {
                    	arrInner.add("-");
                    }
                    
                    if(tuple[5] != null)  
                    {
                    	arrInner.add(tuple[5]);
                    }
                    else
                    {
                    	arrInner.add("-");
                    }
                    
                    arrList.add(arrInner);
                }
            }
        }
        catch (Exception e) 
        {
        	gLogger.error(" Erroor  In >> "+e, e);
            e.printStackTrace();
        }
        return arrList;
    }
}
