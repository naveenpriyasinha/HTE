package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;



public class OrderHeadMpgDAOImpl extends GenericDaoHibernateImpl<HrPayOrderHeadMpg, Long> implements OrderHeadMpgDAO {
	Log logger = LogFactory.getLog(getClass());

    ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
	
	public final int FIN_YEAR_ID=Integer.parseInt(constantsBundle.getString("FIN_YEAR_ID")); //Financial Year for Budget Heads.

	
	
	public OrderHeadMpgDAOImpl(Class<HrPayOrderHeadMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
public List getAllData()
	{
		List orderMstList = null;
		Session hibSession = getSession();
        String strQuery = "from HrPayOrderMst";
        Query query = hibSession.createQuery(strQuery);
        orderMstList = query.list();
		
		return orderMstList;
	}	

public List getAllUserPostRltData(long loc_id)
{
	List userpostrlt = null;
	Session hibSession = getSession();
	logger.info("Before Execution:-");
    String strQuery = "SELECT a.orgPostMst.postId, a.postName, b.empFname, b.empMname, b.empLname FROM OrgPostDetailsRlt a, OrgEmpMst b WHERE b.orgUserMst.userId IN ( SELECT c.userId FROM OrgUserMst c WHERE c.userId IN ( SELECT d.orgUserMst.userId FROM OrgUserpostRlt d  WHERE d.orgPostMstByPostId.postId=a.orgPostMst.postId )) AND a.cmnLocationMst.locId=" +loc_id+" ORDER BY b.empFname, b.empMname, b.empLname ";
    Query query = hibSession.createQuery(strQuery);
    userpostrlt = query.list();
    logger.info("List size is:-"+userpostrlt);
	
	return userpostrlt;
}	
public List getAllUserPostRltDatabyDesg(long loc_id,long desgId)
{
	List userpostrlt = null;
	Session hibSession = getSession(); 
	StringBuffer strQuery = new StringBuffer();
	strQuery.append("SELECT a.orgPostMst.postId, a.postName, b.empFname, b.empMname, b.empLname FROM OrgPostDetailsRlt a, OrgEmpMst b, OrgDesignationMst desg WHERE desg.dsgnId=a.orgDesignationMst.dsgnId AND  b.orgUserMst.userId IN ( SELECT c.userId") ;
	strQuery.append(" FROM OrgUserMst c WHERE c.userId IN ( SELECT d.user_id FROM OrgUserpostRlt d  WHERE d.orgPostMstByPostId.postId=a.orgPostMst.postId)) AND a.orgDesignationMst.dsgnId="+desgId+" AND b.cmnLanguageMst.langId = 1 AND a.loc_id=");
	strQuery.append(loc_id);
	strQuery.append(" ORDER BY b.empFname, b.empMname, b.empLname ");
    Query query = hibSession.createQuery(strQuery.toString());
    userpostrlt = query.list();
    logger.info("List size is:-"+userpostrlt);
	
	return userpostrlt;
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

public List getAlldeptData()
{
	List getAlldeptData = null;
	Session hibSession = getSession();
    String strQuery = "from CmnLocationMst as a where a.departmentId='300001'";
    Query query = hibSession.createQuery(strQuery);
    getAlldeptData = query.list();
	
	return getAlldeptData;
}	

public List getPostListByOrderId(long orderNo)
{	
	List postList = null;
	
	    
		Session hibSession = getSession();
		
		//"13" is hard coded value coming from cmn_lookup_mst table for Primary Post.
		StringBuffer strQuery = new StringBuffer();
	    strQuery.append("FROM HrPayOrderHeadPostMpg mpg WHERE mpg.orderHeadId=");
	    strQuery.append(orderNo);  
	    strQuery.append(" AND mpg.postId IN ( SELECT p.postId FROM OrgPostMst p WHERE p.CmnLookupMst.lookupId=13)");
	    logger.info("In OrderHeadMpgDAOImpl--Query for getting Post List from OrderNo " + strQuery);
	    Query query = hibSession.createQuery(strQuery.toString());
	    postList = query.list();
	    logger.info("Post List size in OrderHeadMpgDAOImpl from OrderNo " + postList.size());
	return postList;
}	



public List getOrderHeadMstDataByid()
{	
	List postList = null;
	
	
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		 strQuery.append("select ohmpg.orderHeadId,omst.orderName,bud.budsubhdDescLong from HrPayOrderMst omst,HrPayOrderHeadMpg ohmpg,SgvaBudsubhdMst bud where omst.orderId = ohmpg.orderId and bud.budsubhdId = ohmpg.subheadId");
			 logger.info("In OrderHeadMpgDAOImpl--Query for getting Post List from OrderNo " + strQuery);
		    Query query = hibSession.createQuery(strQuery.toString());
	  postList = query.list();
	  logger.info("The Sie is :-"+postList.size());
	return postList;
}

public List searchOrderHeadMst(String locCode,String strOrderName,String strStartDate,String strEndDate,int finYrId){
	List lstSearchData = new ArrayList();
	try{
	Session hibSession = getSession();
	StringBuffer strQuery = new StringBuffer();
	strQuery.append("select ohmpg.orderHeadId,omst.orderName,bud.budsubhdDescLong from HrPayOrderMst omst,HrPayOrderHeadMpg ohmpg,SgvaBudsubhdMst bud,HrPayOrderSubHeadMpg shm where omst.orderId = ohmpg.orderId and bud.budsubhdId = shm.sgvaBudsubhdMst.budsubhdId and  ohmpg.subheadId = shm.element_code and shm.finYearId = "+finYrId+" and omst.locationCode='"+locCode+"'");
	if(strOrderName!=null && !strOrderName.equals("")){
		strOrderName+="%";
		strQuery.append(" and lower(omst.orderName) like '"+strOrderName.toLowerCase()+"'");
	}
		
	if(strEndDate!=null && !strEndDate.equals("") ){
	    Date endDate = StringUtility.convertStringToDate(strEndDate);    
		DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	    SimpleDateFormat sdf = sbConf.GetDateFormat();
	    String dt = sdf.format(endDate);		
	    strQuery.append(" and omst.endDate = '"+dt+"' " );
	}
		
	if(strStartDate!=null && !strStartDate.equals("") ){
	    Date StartDate = StringUtility.convertStringToDate(strStartDate);  
		DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	    SimpleDateFormat sdf = sbConf.GetDateFormat();
	    String dt = sdf.format(StartDate);
	    strQuery.append(" and omst.orderDate = '"+dt+"' ");
	}
		
	logger.info("The Query String for the Order Head Search is:-"+strQuery.toString());
	Query query = hibSession.createQuery(strQuery.toString());
	lstSearchData = query.list();
	logger.info("The Size is :-"+lstSearchData.size());
	}
	catch(Exception e)
	{
		logger.error("Error is: "+ e.getMessage());
		logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
		return lstSearchData;
	}
	return lstSearchData;
}



public List getOrderHeadMstDataByid(String locationCode,int finYrId)
{	
	List postList = null;
	
	
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		 strQuery.append("select ohmpg.orderHeadId,omst.orderName,bud.budsubhdDescLong from HrPayOrderMst omst,HrPayOrderHeadMpg ohmpg,SgvaBudsubhdMst bud,HrPayOrderSubHeadMpg shm where omst.orderId = ohmpg.orderId and bud.budsubhdId = shm.sgvaBudsubhdMst.budsubhdId and  ohmpg.subheadId = shm.element_code and shm.finYearId = "+finYrId+" and omst.locationCode='"+locationCode+"'");
			 logger.info("In OrderHeadMpgDAOImpl--Query for getting Post List from OrderNo " + strQuery);
		    Query query = hibSession.createQuery(strQuery.toString());
	  postList = query.list();
	  logger.info("The Sie is :-"+postList.size());
	return postList;
}

public HrPayOrderHeadMpg getOrderHeadMasterData(Long ohMapId)
{
	HrPayOrderHeadMpg hrPayOrderHeadMpg = new HrPayOrderHeadMpg();
    try
    {
        Session hibSession = getSession();
        String query1 = "from HrPayOrderHeadMpg as a where a.orderHeadId ="+ohMapId;
        Query sqlQuery1 = hibSession.createQuery(query1);
        hrPayOrderHeadMpg = (HrPayOrderHeadMpg)sqlQuery1.uniqueResult();
    }
    catch(Exception e)
    {
        logger.error("Error is: "+ e.getMessage());
    }
    return hrPayOrderHeadMpg;
}

public List getOrderHeadDataById(long ohMapId,int finYrId)
{	
	List ohList = null;
	
	
		Session hibSession = getSession();
		 StringBuffer strQuery = new StringBuffer();
		 strQuery.append(" SELECT a.budsubhdId, " );
	     strQuery.append(" a.budsubhdCode, " );
	     strQuery.append(" a.demandCode, " );
	     strQuery.append(" a.budmjrhdCode, " );
	     strQuery.append(" a.budsubmjrhdCode, " );
	     strQuery.append(" a.budminhdCode, " );
	     strQuery.append(" oh.orderId, o.locationCode " );
	     strQuery.append(" FROM SgvaBudsubhdMst a, HrPayOrderHeadMpg oh, HrPayOrderMst o, HrPayOrderSubHeadMpg shm " );
	    // strQuery.append(" WHERE a.langId = 'en_US' AND a.finYrId = '"+FIN_YEAR_ID+"' AND o.orderId = oh.orderId AND " );// hardcoded yeadId and langId
	     strQuery.append(" WHERE a.langId = 'en_US'  AND o.orderId = oh.orderId AND " );// hardcoded yeadId and langId
	     strQuery.append(" a.budsubhdId = shm.sgvaBudsubhdMst.budsubhdId and oh.subheadId = shm.element_code and shm.finYearId="+finYrId+" AND oh.orderHeadId = "+ohMapId+" " );
	     logger.info("In OrderHeadMpgDAOImpl--Query for getting order head List from OrderhHeadId " + strQuery);
		    Query query = hibSession.createQuery(strQuery.toString());
		    ohList = query.list();
	  logger.info("The Sie is :-"+ohList.size());
	return ohList;
}	

public List chkOrderHeadDataById(long orderId,long headId,long ohMapId)
{	
	List ohList = null;
	Session hibSession = getSession();
		 StringBuffer strQuery = new StringBuffer();
		 strQuery.append(" FROM HrPayOrderHeadMpg o WHERE o.orderId = "+orderId+" AND o.subheadId = '"+headId+"' AND  o.orderHeadId != "+ohMapId+"  " );
	     logger.info("In OrderHeadMpgDAOImpl--Query for uniqueness in order head " + strQuery);
		 Query query = hibSession.createQuery(strQuery.toString());
		 ohList = query.list();
	return ohList;
}	

public String getElementCodeFromSubHdId(long subHeadId) {
	String subHdId = null;
	Session hibSession = getSession();
	StringBuffer strQuery = new StringBuffer(
			"select distinct shm.element_code from HrPayOrderSubHeadMpg shm where shm.sgvaBudsubhdMst.budsubhdId="
					+ subHeadId);
	logger
			.info("Query in getElementCodeFromSubHdId is "
					+ strQuery.toString());
	Query query = hibSession.createQuery(strQuery.toString());
	subHdId = query.uniqueResult().toString();
	return subHdId;
}

public String getSubHdIdFromElementCode(long subHeadId,long finYrId) {
	String subHdId = null;
	Session hibSession = getSession();
	StringBuffer strQuery = new StringBuffer(
			"select distinct shm.sgvaBudsubhdMst.budsubhdId from HrPayOrderSubHeadMpg shm where shm.finYearId ="+finYrId+" and shm.element_code="
					+ subHeadId);
	logger
			.info("Query in getElementCodeFromSubHdId is "
					+ strQuery.toString());
	Query query = hibSession.createQuery(strQuery.toString());
	subHdId = query.uniqueResult().toString();
	return subHdId;
}

public List getOrderData()
{
	 List psrList = null;
	 Session hibSession = getSession();
	 
	 StringBuffer strQuery = new StringBuffer();
	 strQuery.append("from HrPayOrderHeadMpg");
	 		
	 
	 psrList = hibSession.createQuery(strQuery.toString()).list();
	 logger.info("sizeeeeeeeeeeeeee " + psrList.size());
	 return psrList;
}
public String getBillScheme(long billId)
{
	 String billScheme = "";
	 Session hibSession = getSession();
	 
	 StringBuffer strQuery = new StringBuffer();
	 strQuery.append("select dcpsDdoSchemeCode from MstDcpsBillGroup where dcpsDdoBillGroupId="+billId);
	 
	 List billList = hibSession.createQuery(strQuery.toString()).list();
	 billScheme = billList.get(0).toString();
	 logger.info("sizeeeeeeeeeeeeee " + billScheme);
	 return billScheme;
}

}
