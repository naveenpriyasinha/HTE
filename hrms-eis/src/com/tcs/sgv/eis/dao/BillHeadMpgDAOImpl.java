package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.MstScheme;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg; 
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.dcps.valueobject.*;

public class BillHeadMpgDAOImpl extends GenericDaoHibernateImpl<MstDcpsBillGroup  , Long> implements BillHeadMpgDAO  {
	//Added By Varun For Getting Financial Year wise Data Dt.05-08-2008
	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.Payroll");
	 long finYearID=Long.parseLong(constantsBundle.getString("finYearId"));
	 //Ended By Varun For Getting Financial Year wise Data Dt.05-08-2008
	public BillHeadMpgDAOImpl(Class<MstDcpsBillGroup> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	
	public List getAllData()
	{
		List billheadList = null;
		Session hibSession = getSession();
		//intr
        //String strQuery = "from MstDcpsBillGroup   where finYearId='"+finYearID+"' order by billId " ;//Changed By Varun For finYear wise
		String strQuery = "from MstDcpsBillGroup   order by dcpsDdoBillDescription    " ;//Changed By Varun For finYear wise
        Query query = hibSession.createQuery(strQuery);
        billheadList = query.list();
		
		return billheadList;
	}
	
	public List getAllData(String locationCode,long langId)
	{
		List billheadList = null;
		Session hibSession = getSession();
		//intr
        //String strQuery = "from MstDcpsBillGroup   ph where ph.finYearId='"+finYearID+"' and  ph.LocId = '"+locationCode+"' and ph.cmnLocationMst.cmnLanguageMst.langId="+ langId+" order by billId " ;//Changed By Varun For finYear wise
		String strQuery = "from MstDcpsBillGroup   ph where  ph.LocId = '"+locationCode+"' and ph.LangId="+ langId+" order by dcpsDdoBillDescription " ;//Changed By Varun For finYear wise
        Query query = hibSession.createQuery(strQuery);
        billheadList = query.list();
		logger.info(strQuery.toString());
		return billheadList;
	}
	
	public List getAllSubhdData()
	{
		List subheaddataList = null;
		Session hibSession = getSession();
	    String strQuery = "from SgvaBudsubhdMst as a where a.langId='en_US'";
	    Query query = hibSession.createQuery(strQuery);
	    subheaddataList = query.list();
		
		return subheaddataList;
	}	

	
	public SgvaBudsubhdMst getAllDatabySubHeadId(long subHeadId)
	{
		SgvaBudsubhdMst budsubhdMst = new SgvaBudsubhdMst();
		Session hibSession = getSession();
        Query strQuery = hibSession.createQuery(" from SgvaBudsubhdMst where budsubhdId="+subHeadId);
        budsubhdMst = (SgvaBudsubhdMst)strQuery.uniqueResult();
		
		return budsubhdMst;
	}
	
	//varun sharma
	// This method is been overloaded here as Location_id is in the form of String. EX. en_US
	// To maintain the stability with the current WS had kept the previous method as it is.
	public List chkBillHeadDataById(long billId, String location_Id, long bsubhead_id)
	{	
		List bhList = null;
		Session hibSession = getSession();
			 StringBuffer strQuery = new StringBuffer();
			 
			 //intr
			 strQuery.append(" FROM MstDcpsBillGroup   o WHERE o.billId = "+billId+" and o.LocId = '"+location_Id+"' AND  o.dcpsDdoBillGroupId !="+bsubhead_id);
		    // logger.info("In chkBillHeadDataById 1 --Query for uniqueness in bill head " + strQuery);
			 Query query = hibSession.createQuery(strQuery.toString());
			 bhList = query.list();
		return bhList;
	}
	
	public List chkBillHeadDataById(long billId,long location_Id,long bsubhead_id)
	{
		List bhList = null;
		Session hibSession = getSession();
			 StringBuffer strQuery = new StringBuffer();
			 //intr
			 strQuery.append(" FROM MstDcpsBillGroup   o WHERE o.billId = "+billId+" and o.LocId = '"+location_Id+"' AND  o.dcpsDdoBillGroupId !="+bsubhead_id);
		     //logger.info("In chkBillHeadDataById--Query for uniqueness in bill head " + strQuery);
			 Query query = hibSession.createQuery(strQuery.toString());
			 bhList = query.list();
		return bhList;
	}	
	
	/**
	 * This method will return list of PaybillHeadMpg Objects based on Location
	 * Code.
	 * @param locCode - Long
	 * @return List of PaybillHeadMpg
	 */
	public List<MstDcpsBillGroup  > getBillNofromDept(long locCode) {
		List<MstDcpsBillGroup  > lstBillNos = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(
				"from MstDcpsBillGroup   bhm where LocId="
						+ locCode + " Order by bhm.dcpsDdoBillDescription   " );
	/*	logger.info("Query in getAllDataFromBillId is------>>>>>> "
				+ strQuery.toString());*/
		Query query = hibSession.createQuery(strQuery.toString());
		lstBillNos = query.list();
		return lstBillNos;
	}
	
	public List<MstScheme> getMstScheme(String schemeCode,long locId)
	{
		List list =new ArrayList();
 		Session hibSession = getSession();
 		StringBuffer query=new StringBuffer();
 			// query.append("from MstScheme mpg where mpg.schemeCode="+schemeCode);
 		query.append("from MstScheme mpg where mpg.schemeCode='"+schemeCode+"'");
 		
 		Query hsqlQuery = hibSession.createQuery(query.toString());	  	   
 	   return hsqlQuery.list();
	}

	/**
	 * This method will return list of Increment PayOrders with details
	 * Code.
	 * @param locCode - Long langId - Long
	 * @return List of PaybillHeadMpg
	 * #@author 366673 Amish
	 */
	
	public List getAllDataForPayFixation(long locId,long langId)
	{
		
		List payFixList = null;
		Session hibSession = getSession();
		String strQuery = "select fix.incrementOrderNo,fix.incrementOrderDate,fix.status,count(fix.userId) from HrPayfixMst fix where  fix.cmnLocationMst.locId ="+locId+" and fix.cmnLanguageMst.langId="+langId+" and fix.activateFlag=1 group by fix.incrementOrderNo,fix.incrementOrderDate,fix.status";
        Query query = hibSession.createQuery(strQuery);
        payFixList = query.list();
		logger.info("******* payfixation query"+strQuery.toString());
		return payFixList;
	}
	
	
	public List getAllDataForPayFixationPk(long userid,long locId)
	{
		
		List payFixList =new ArrayList();
		Session hibSession = getSession();
		String strQuery = "from HrPayfixMst fix where  fix.cmnLocationMst.locId ="+locId+" and fix.userId="+userid+" ";
        Query query = hibSession.createQuery(strQuery);
        
        if(query.list().size()>0)
        //{
        payFixList = query.list();
       /* }
        else
        {
        	payFixList = null;
        }*/
		logger.info("******* payfixation query"+strQuery.toString());
		return payFixList;
	}
	
	public List getAllDataForPayFixationFromPk(long payfixId)
	{
		
		List payFixList = null;
		Session hibSession = getSession();
		String strQuery = "from HrPayfixMst fix where  fix.payfixId="+payfixId+" ";
        Query query = hibSession.createQuery(strQuery);
        payFixList = query.list();
		logger.info("******* payfixation query"+strQuery.toString());
		return payFixList;
	}
	
	/**
	 * This method will return list of Employees of particular location who are yet to given increment
	 * Code.
	 * @param size - Long locId - Long
	 * @return List of PaybillHeadMpg
	 * #@author 366673 Amish
	 */
	
	
	public List getEmpNamesAndBasicDtlss(long locId,long size)
	{
		
		 
		List empBasicList = null;
		Session hibSession = getSession();
		StringBuffer strQuery=new StringBuffer();
		strQuery.append("select distinct concat(o.empFname,' ',o.empMname,' ',o.empLname),ooo.otherCurrentBasic,o.orgUserMst.userId,scale.scaleEndAmt,");
		strQuery.append(" ooo.incrementDate from OrgEmpMst o,HrEisEmpMst e,OrgUserMst u,OrgPostMst p,");
		strQuery.append(" OrgUserpostRlt uu,OrgPostDetailsRlt pp,HrEisOtherDtls ooo,OrgDesignationMst dd,HrEisSgdMpg sgd,HrEisGdMpg gd,HrEisScaleMst scale");
		
		strQuery.append(" where uu.activateFlag=1 and o.empId=e.orgEmpMst.empId and o.orgUserMst.userId=u.userId and u.userId=uu.orgUserMst.userId and ");
		strQuery.append(" p.postId=uu.orgPostMstByPostId.postId and uu.orgPostMstByPostId.postId=pp.orgPostMst.postId and ");
		strQuery.append(" dd.dsgnId=pp.orgDesignationMst.dsgnId and e.empId=ooo.hrEisEmpMst.empId and pp.cmnLocationMst.locId="+locId);
		
		if(size>0)
			strQuery.append(" and o.orgUserMst.userId not in (select fix.userId from HrPayfixMst fix where fix.activateFlag=1)");
		
		strQuery.append(" and sgd.hrEisScaleMst.scaleId=scale.scaleId");
		strQuery.append(" and sgd.hrEisGdMpg.gdMapId=gd.gdMapId and gd.orgDesignationMst=dd.dsgnId and ooo.hrEisSgdMpg.sgdMapId=sgd.sgdMapId");		
              		
		
        Query query = hibSession.createQuery(strQuery.toString());
        empBasicList = query.list();
		logger.info("******* empBasicList strQuery***************"+strQuery.toString());
		logger.info("******* empBasicList query***************"+query);
		logger.info("******* empBasicList query***************"+query);
		return empBasicList;
	}
	

	
	
	public List getDataForDuplicateMessage(String orderNo,long locId,long langId)
	{
		
		List payFixList =new ArrayList();
		Session hibSession = getSession();
		String strQuery = "from HrPayfixMst fix where  fix.incrementOrderNo='"+orderNo+"' and fix.cmnLocationMst.locId="+locId+" and fix.cmnLanguageMst.langId="+langId;
        Query query = hibSession.createQuery(strQuery);
        payFixList = query.list();
		logger.info("*******payfixation query"+strQuery.toString());
		return payFixList;
	}
	public List getAllDataFromIncrementOrderNo(long locId,long langId,String IncrementOrderNo)
	{
		
		List payFixList = null;
		Session hibSession = getSession();
		String strQuery = "from HrPayfixMst fix where  fix.cmnLocationMst.locId ="+locId+" and fix.cmnLanguageMst.langId="+langId+" and fix.incrementOrderNo='"+IncrementOrderNo+"' and fix.activateFlag=1";
        Query query = hibSession.createQuery(strQuery);
        payFixList = query.list();
		logger.info("*******payfixation query"+strQuery.toString());
		return payFixList;
	}
	
	
	/**
	 * This method will return details of Employee of particular location 
	 * Code.
	 * @param userId - Long locId - Long
	 * @return List of PaybillHeadMpg
	 * #@author 366673 Amish
	 */
	
	
	public List getEmpNamesAndBasicDtls(long locId,long userId)
	{
		
		 
		List empBasicList = null;
		Session hibSession = getSession();
		/*String strQuery = "select distinct concat(o.empFname,' ',o.empMname,' ',o.empLname),ooo.otherCurrentBasic,o.orgUserMst.userId,scale.scaleEndAmt,fix.payFixDate,fix.remarks,fix.newBasic,fix.nxtIncrDate,ooo.incrementDate from OrgEmpMst o,HrEisEmpMst e,OrgUserMst u,OrgPostMst p,OrgUserpostRlt uu, ";
		strQuery+=" OrgPostDetailsRlt pp,HrEisOtherDtls ooo,OrgDesignationMst dd, ";
		strQuery+=" HrEisSgdMpg sgd,HrEisGdMpg gd,HrEisScaleMst scale,HrPayfixMst fix";
		strQuery+=" where o.empId=e.orgEmpMst.empId and o.orgUserMst.userId=u.userId and u.userId=uu.orgUserMst.userId and ";
		strQuery+=" p.postId=pp.orgPostMst.postId and uu.orgPostMstByPostId.postId=pp.orgPostMst.postId and e.empId=ooo.hrEisEmpMst.empId and fix.userId=u.userId and fix.activateFlag=1 and pp.cmnLocationMst.locId="+locId+" and o.orgUserMst.userId="+userId+" "; 
        strQuery+=" and dd.dsgnId=pp.orgDesignationMst.dsgnId ";
        strQuery+=" and sgd.hrEisGdMpg.gdMapId=gd.gdMapId and gd.orgDesignationMst=dd.dsgnId and sgd.hrEisScaleMst.scaleId=scale.scaleId";
        strQuery+=" and ooo.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and  sgd.cmnLocationMst.locId="+locId+" and gd.cmnLocationMst.locId="+locId;
		*/
		StringBuffer strQuery=new StringBuffer();
		strQuery.append("select distinct concat(o.empFname,' ',o.empMname,' ',o.empLname),ooo.otherCurrentBasic,o.orgUserMst.userId,scale.scaleEndAmt,");
		strQuery.append(" fix.payFixDate,fix.remarks,fix.newBasic,fix.nxtIncrDate,ooo.incrementDate from OrgEmpMst o,HrEisEmpMst e,OrgUserMst u,OrgPostMst p,HrPayfixMst fix,");
		strQuery.append(" OrgUserpostRlt uu,OrgPostDetailsRlt pp,HrEisOtherDtls ooo,OrgDesignationMst dd,HrEisSgdMpg sgd,HrEisGdMpg gd,HrEisScaleMst scale");
		
		strQuery.append(" where o.empId=e.orgEmpMst.empId and o.orgUserMst.userId=u.userId and u.userId=uu.orgUserMst.userId and ");
		strQuery.append(" p.postId=uu.orgPostMstByPostId.postId and uu.orgPostMstByPostId.postId=pp.orgPostMst.postId and ");
		strQuery.append(" dd.dsgnId=pp.orgDesignationMst.dsgnId and e.empId=ooo.hrEisEmpMst.empId and pp.cmnLocationMst.locId="+locId);
		
		strQuery.append(" and fix.userId=u.userId and fix.activateFlag=1 and o.orgUserMst.userId="+userId);
		
		strQuery.append(" and sgd.hrEisScaleMst.scaleId=scale.scaleId");
		strQuery.append(" and sgd.hrEisGdMpg.gdMapId=gd.gdMapId and gd.orgDesignationMst=dd.dsgnId and ooo.hrEisSgdMpg.sgdMapId=sgd.sgdMapId");	
		Query query = hibSession.createQuery(strQuery.toString());
        empBasicList = query.list();
		logger.info("*******getEmpNamesAndBasicDtls from order number query***************"+strQuery.toString());
		logger.info("*******getEmpNamesAndBasicDtls from order number query***************"+strQuery.toString());
		return empBasicList;
	}
	
	public List getPrintReportDataFromIncrementOrderNo(long locId,String incrementOrderNo)
	{
		
		List printReportlist = null;
		Session hibSession = getSession();
		String strQuery = "select distinct concat(o.empFname,'',o.empMname,'',o.empLname),d.dsgnName,fix.payFixDate,fix.oldBasic,fix.newBasic,fix.nxtIncrDate,fix.incrementOrderDate";
		strQuery+=" from OrgEmpMst o,HrEisEmpMst e,OrgUserMst u,OrgPostMst p,OrgUserpostRlt uu,OrgPostDetailsRlt post,OrgDesignationMst d,HrEisOtherDtls ooo,HrPayfixMst fix";
		strQuery+=" where o.empId=e.orgEmpMst.empId and o.orgUserMst.userId=u.userId and u.userId=uu.orgUserMst.userId and ";
		strQuery+=" p.postId=post.orgPostMst.postId and uu.orgPostMstByPostId.postId=post.orgPostMst.postId and e.empId=ooo.hrEisEmpMst.empId and fix.userId=u.userId ";
		strQuery+=" and post.orgDesignationMst.dsgnId=d.dsgnId and fix.incrementOrderNo='"+incrementOrderNo+"' and post.cmnLocationMst.locId="+locId;
        Query query = hibSession.createQuery(strQuery);
        printReportlist = query.list();
		logger.info("******* payfixation query"+strQuery.toString());
		return printReportlist;
	}
	

	public int deleteRecordsInPayIncremetTable(long userId,long locId)
	{

		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("delete from HrPayfixMst fix where fix.userId="+ userId+" and fix.cmnLocationMst.locId="+locId );
			
		Query query = hibSession.createQuery(strBfr.toString());		
		int rowsDeleted = query.executeUpdate();
		
		return rowsDeleted;
		
	}
	
	public int deleteOrderRecordsInPayIncremetTable(String orderNo,long locId)
	{

		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("delete from HrPayfixMst fix where fix.incrementOrderNo='"+orderNo+"' and fix.cmnLocationMst.locId="+locId );
			
		Query query = hibSession.createQuery(strBfr.toString());		
		int rowsDeleted = query.executeUpdate();
		
		logger.info("******* deleteOrderRecordsInPayIncremetTable rowsDeleted"+rowsDeleted);
		return rowsDeleted;
		
	}
	
}
