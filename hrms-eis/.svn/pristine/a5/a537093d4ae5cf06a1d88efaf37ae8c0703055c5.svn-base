package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;





public class OrderMstDAOImpl extends GenericDaoHibernateImpl<HrPayOrderMst, Long> implements OrderMstDAO {
	Log logger = LogFactory.getLog(getClass());
	public OrderMstDAOImpl(Class<HrPayOrderMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	public List getAllData(String ddo, String locationCode)
	{
		logger.info("==> inside getAllData :: ");
		List orderMstList = null;
		Session hibSession = getSession();
        /*String strQuery = "from HrPayOrderMst orderMst where orderMst.locationCode='"+locationCode+"' " 
        		    		+" order by orderMst.orderName";*/
		StringBuffer sb= new StringBuffer();
		sb.append("from HrPayOrderMst orderMst where orderMst.locationCode in ("+locationCode+")");
		if(ddo!=null && Long.parseLong(ddo)!=-1){
			sb.append(" and orderMst.locationCode in ( select locationCode from OrgDdoMst where ddoCode='"+ddo+"')");
		}
		//sb.append("from HrPayOrderMst orderMst where orderMst.locationCode ='"+locationCode+"' ");
		sb.append(" order by orderMst.orderName");
        Query query = hibSession.createQuery(sb.toString());
        logger.info("==> in getAllData :: "+sb.toString());
        orderMstList = query.list();
        logger.info("==> in orderMstList :: "+orderMstList.size());
		
		return orderMstList;
	}	
	
public List getAllOrderData(long locId,String TodaysDate)
	{
		logger.info("==> inside getAllOrderData :: ");
		List orderMstList = null;
		Session hibSession = getSession();
        String strQuery = "from HrPayOrderMst orderMst where orderMst.locationCode in ("+locId+") order by orderMst.orderName";
        logger.info("==> in getAllOrderData :: "+strQuery);
        
        Query query = hibSession.createQuery(strQuery);
       
        orderMstList = query.list();
		
		return orderMstList;
	}	

public List getOrderName()
{
	List list=null;
	Session hibSession=getSession();
	String strQuery="from HrPayOrderMst order by orderName asc";
	Query query=hibSession.createQuery(strQuery);
	list=query.list();
	return list;
	
}


public List getAllPostData(long locId,String TodaysDate)
	{
		logger.info("==> in getAllPostData :: ");
		List orderMstList = null;
		Session hibSession = getSession();
        String strQuery = "from HrPayOrderMst orderMst where orderMst.locationCode in ("+locId+") order by orderMst.orderName";
        logger.info("==> in getAllOrderData :: "+strQuery);
        
        Query query = hibSession.createQuery(strQuery);
       
        orderMstList = query.list();
		
		return orderMstList;
	}	

public List getOrderMstDataById(Map headValues,long postType) 
{	
	List orderHeadMpgList = null;
	if(headValues!=null)
	{
	    String demandNo = (String)headValues.get("demandNo");
	    String mjrHead = (String)headValues.get("mjrHead");
	    String subMjrHead =  (String)headValues.get("subMjrHead");
	    String mnrHead = (String)headValues.get("mnrHead");
	    String subHead = (String)headValues.get("subHead");
	    String langId = (String)headValues.get("langId");
	    int fin_year_id = Integer.parseInt(headValues.get("fin_year_id").toString());
	    
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//Commented By Mrugesh for HQL conversion
		/*strQuery.append("select * from hr_pay_order_head_post_mpg p where p.order_head_id in(");
	    strQuery.append("select mpg.order_head_id from hr_pay_order_head_mpg mpg, sgva_budsubhd_mst mst where ");
	    strQuery.append(" mst.demand_code='");
	    strQuery.append(demandNo);
	    strQuery.append("' and mst.budmjrhd_code='");
	    strQuery.append(mjrHead);
	    strQuery.append("' and " );
	    strQuery.append(" mst.budsubmjrhd_code='");
	    strQuery.append(subMjrHead);
	    strQuery.append("' " ); 
	    strQuery.append(" and mst.budminhd_code='");
	    strQuery.append(mnrHead);
	    strQuery.append("' and mst.budsubhd_code='");
	    strQuery.append(subHead);
	    strQuery.append("' and mst.lang_id='");
	    strQuery.append(langId);
	    strQuery.append("' and mst.fin_yr_id=");
	    strQuery.append(fin_year_id); 
	    strQuery.append(" and mpg.subhead_id = mst.budsubhd_id)");
	    
	    //added by Ankit Bhatt
	    if(postType!=0){
	    strQuery.append(" and post_id in (select pmst.post_id from org_post_mst pmst where "); 
	    strQuery.append(" pmst.post_type_lookup_id=" + postType + ")");*/
	    //Ended By Mrugesh
	    
		//Added By Mrugesh for HQL conversion
	    strQuery.append(" from HrPayOrderHeadPostMpg p where p.orderHeadId in(");
	    strQuery.append("select mpg.orderHeadId from HrPayOrderHeadMpg mpg, SgvaBudsubhdMst mst , HrPayOrderSubHeadMpg hposm where ");
	    strQuery.append(" mst.demandCode='");
	    strQuery.append(demandNo);
	    strQuery.append("' and mst.budmjrhdCode='");
	    strQuery.append(mjrHead);
	    strQuery.append("' and " );
	    strQuery.append(" mst.budsubmjrhdCode='");
	    strQuery.append(subMjrHead);
	    strQuery.append("' " ); 
	    strQuery.append(" and mst.budminhdCode='");
	    strQuery.append(mnrHead);
	    strQuery.append("' and mst.budsubhdCode='");
	    strQuery.append(subHead);
	    strQuery.append("' and mst.langId='");
	    strQuery.append(langId);
	    strQuery.append("' and mst.finYrId=");
	    strQuery.append(fin_year_id); 
	    strQuery.append(" and hposm.sgvaBudsubhdMst.budsubhdId = mst.budsubhdId ");//change done here by chirashree
	    strQuery.append(" and mpg.subheadId = hposm.element_code)");
	    
	    
	    if(postType!=0){
	    strQuery.append(" and p.postId in (select pmst.postId from OrgPostMst pmst where "); 
	    strQuery.append(" pmst.postTypeLookupId=" + postType + ")");
	    }
	    //Ended By Mrugesh
	    logger.info("In OrderMstDaoImpl--Query for getting Order no from Head Chargable " + strQuery);
	    Query query = hibSession.createQuery(strQuery.toString());
	    orderHeadMpgList = query.list();
	}		
	return orderHeadMpgList;
}







public HrPayOrderMst getOrderMstDataByid(long orderId)
{
	HrPayOrderMst hrorderinfo = new HrPayOrderMst();
	 Session hibSession = getSession();
     String query1 = "from HrPayOrderMst as orderLookup where orderLookup.orderId ="+orderId;
     Query sqlQuery1 = hibSession.createQuery(query1);
     hrorderinfo = (HrPayOrderMst)sqlQuery1.uniqueResult();
     logger.info("Order size is:::::::::" );

 
 return hrorderinfo;

}

public List checkOrderNameAvailability(String newOrderName) {
	List  list = null;        
    Session hibSession = getSession();   
    
    Query query = hibSession.createQuery("from HrPayOrderMst as a where  lower(a.orderName)= '"+newOrderName+"'");        
    list = query.list();
    logger.info("The Size od list is:-"+list.size());
return list;

}


public List getOrderDataFromPara(String newOrderName,String dept,Date strDate) {
	logger.info("*****************inside getOrderDataFromPara************");
	List  list = null;        
    Session hibSession = getSession();   
    Criteria objCrt = hibSession.createCriteria(HrPayOrderMst.class);
    
    objCrt.add(Restrictions.ilike("orderName", newOrderName));
    objCrt.add(Restrictions.eq("orderDate", strDate));
    //objCrt.add(Restrictions.eq("locationCode", dept));
    list = objCrt.list();
    logger.info("The Size od list is:-"+list.size());
return list;

}

/*
 * @Author	: Urvin Shah
 * @Date	: 04/11/2008 	
 * Purpose	: This method search the orders on the basis of the Order Date or Order Name. 
 */

public List getSearchOrderData(String strOrderName,String strStartDate,String strEndDate,String strLocCode){
	List<HrPayOrderMst> lstSearchData = new ArrayList();
	try
	{
	Session hibSession = getSession();
	StringBuffer strQueryString = new StringBuffer();
	strQueryString.append(" from HrPayOrderMst o where o.locationCode='"+strLocCode+"'");
	
	Criteria crtSearchData = hibSession.createCriteria(HrPayOrderMst.class);
	crtSearchData.add(Restrictions.eq("locationCode",strLocCode));
	List lstSearchData1 = crtSearchData.list();
	
    logger.info("The Size of Search list is:-"+lstSearchData1.size());
	if(strOrderName!=null && !strOrderName.equals("")){
		strOrderName+="%";
		strQueryString.append(" and lower(o.orderName) like '"+strOrderName.toLowerCase()+"'");
	}
	
	
	if(strEndDate!=null && !strEndDate.equals("") ){
	    Date endDate = StringUtility.convertStringToDate(strEndDate);    
		DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	    SimpleDateFormat sdf = sbConf.GetDateFormat();
	    String dt = sdf.format(endDate);		
		strQueryString.append(" and o.endDate = '"+dt+"' " );
	}
	
	
	if(strStartDate!=null && !strStartDate.equals("") ){
	    Date StartDate = StringUtility.convertStringToDate(strStartDate);  
		DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	    SimpleDateFormat sdf = sbConf.GetDateFormat();
	    String dt = sdf.format(StartDate);
		strQueryString.append(" and o.orderDate = '"+dt+"' ");
	}
	
	
	Query querySearchOrder = hibSession.createQuery(strQueryString.toString());
	lstSearchData = querySearchOrder.list();
	
    logger.info("The Size of Search list is:-"+lstSearchData.size());
	}
    catch(Exception e)
	{
		logger.error("Error is: "+ e.getMessage());
		logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
		return lstSearchData;
	}
	    
	return lstSearchData;
}

//added by khushal

public List getPostType()
{
	Session hibSession = getSession();
	String query=" select a.lookupId,a.lookupName from CmnLookupMst a where a.parentLookupId = 10001198128";
	Query sqlQuery = hibSession.createQuery(query.toString());
	logger.info("Query for getPostType is---->>>>"+sqlQuery);
	logger.info("--Post Type----"+sqlQuery.list());
	if(sqlQuery.list()!=null && sqlQuery.list().size()>0)
	{
	return sqlQuery.list();
}
	else
	{
		return null;
	}
}

/*public List getLookUpId()
{

	Session hibSession = getSession();
	String query=" select a.lookupId from CmnLookupMst a where a.parentLookupId = 10001198128";
	Query sqlQuery = hibSession.createQuery(query.toString());
	logger.info("Query for getPostType is---->>>>"+sqlQuery);
	logger.info("--Post Type----"+sqlQuery.list());
	if(sqlQuery.list()!=null && sqlQuery.list().size()>0)
	{
	return sqlQuery.list();
}
	else
	{
		return null;
	}
}*/

// added by khushal

public List getOrderDate(long orderId)
{

	Session hibSession = getSession();
	String query=" select a.orderDate from  HrPayOrderMst a where order_id= "+orderId+"";
	Query sqlQuery = hibSession.createQuery(query.toString());
	logger.info("--khshual Date Select----"+sqlQuery.list());
	
	if(sqlQuery.list()!=null && sqlQuery.list().size()>0)
	{
	return sqlQuery.list();
}
	else
	{
		return null;
	}


// end by khushal


/*public List getSearchOrderData(String strOrderName,String strStartDate,String strEndDate,String strLocCode){
	List<HrPayOrderMst> lstSearchData = new ArrayList();
	Session hibSession = getSession();
	Criteria crtSearchData = hibSession.createCriteria(HrPayOrderMst.class);
	crtSearchData.add(Restrictions.eq("locationCode",strLocCode));
	List lstSearchData1 = crtSearchData.list();
    logger.info("The Size of Search list is:-"+lstSearchData1.size());
	if(!strOrderName.equals("") && strOrderName!=null){
		strOrderName+="%";
		crtSearchData.add(Restrictions.like("orderName", strOrderName));
	}
	
	if(!strEndDate.equals("") && strEndDate!=null){
		DateFormat dateFormatter;
		dateFormatter = new SimpleDateFormat("dd/mm/yyyy");
		Date dtStartDate = new Date();
		Date dtEndDate = new Date();
		try{
		dtStartDate = (Date)dateFormatter.parse(strStartDate);
		dtEndDate = (Date)dateFormatter.parse(strEndDate);
		}
		catch(Exception e){
			
		}
		crtSearchData.add(Restrictions.between("orderDate", dtStartDate, dtEndDate));
	}
	
	
	else if(!strStartDate.equals("") && strStartDate!=null){
		SimpleDateFormat dateFormatter;
		dateFormatter = new SimpleDateFormat("dd/mm/yyyy");
		Date dtStartDate = new Date();
		
		try{
		dtStartDate = (Date)dateFormatter.parse(strStartDate);
		}
		catch(Exception e){
			
		}
		logger.info("The Order String Start date is:-"+strStartDate);
		logger.info("The Order Start date is:-"+dtStartDate);
		crtSearchData.add(Restrictions.eq("orderDate", dtStartDate));
	}
	lstSearchData = crtSearchData.list();
    logger.info("The Size of Search list is:-"+lstSearchData.size());
	return lstSearchData;
}*/


	
}


public List getAllDcpsGR()
{
	Session hibSession = getSession();
	String query=" SELECT ORDER_ID,ORDER_NAME FROM  HR_PAY_ORDER_MST where GR_TYPE = 7";
	Query sqlQuery = hibSession.createSQLQuery(query.toString());
	logger.info("Query for GET DCPS GR is---->>>>"+sqlQuery);
	logger.info("--DCPS GR----"+sqlQuery.list());
	if(sqlQuery.list()!=null && sqlQuery.list().size()>0)
	{
	return sqlQuery.list();
}
	else
	{
		return null;
	}
}

public List getSubDDOs(String postId,String talukaId,String ddoCodeSelected){
	List ddoDtls = null;
	Session session = getSession();
	StringBuffer sb = new StringBuffer();
	logger.info("---- getSubDDOs DAO---");
	//sb.append("SELECT ddo.LOCATION_CODE,ddo.DDO_office FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_OFFICE !='null' and ddo.DDO_CODE = rep.ZP_DDO_CODE and rep.status =1 and rep.REPT_DDO_POST_ID="+postId);
	//added by roshan
	sb.append("SELECT ddo.LOCATION_CODE,ddo.DDO_office,ddo.ddo_code FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo, MST_DCPS_DDO_OFFICE office where lower(office.ddo_office)= 'yes' and ddo.DDO_OFFICE !='null' and office.status_flag=1 and ddo.DDO_CODE = rep.ZP_DDO_CODE and office.DDO_CODE=ddo.DDO_CODE and rep.status =1 and ((rep.REPT_DDO_POST_ID='" + postId + "') or (rep.FINAL_DDO_POST_ID='" + postId + "')) order by ddo.ddo_code asc");
	if((talukaId!=null)&&(talukaId!="")&&(Long.parseLong(talukaId)!=-1)){
		sb.append(" and office.taluka="+talukaId);
	}
	if((ddoCodeSelected!=null)&&(ddoCodeSelected!="")){
		sb.append(" and (office.ddo_code like '"+ddoCodeSelected+"%' or ddo.ddo_office like '%"+ddoCodeSelected+"%')");
	}
	//end by roshan
	logger.info("---- getSubDDOs DAo---"+sb);
	Query query = session.createSQLQuery(sb.toString());
	ddoDtls = query.list();
	logger.info(" hi    ddoDtls"+ddoDtls.size());
	return ddoDtls;
}

public List getpostRole(Long postId){
	List ddoDtls = null;
	Session session = getSession();
	StringBuffer sb = new StringBuffer();
	logger.info("---- getpostRole DAO---");
	sb.append("  SELECT ROLE_ID FROM ACL_POSTROLE_RLT where post_id="+postId);
	logger.info("---- getpostRole DAo---"+sb);
	Query query = session.createSQLQuery(sb.toString());
	ddoDtls = query.list();
	return ddoDtls;
}

//added by shailesh
public List getSubGRType(Long parentGRID){
	List subGRList = null;
	Session session = getSession();
	StringBuffer sb = new StringBuffer();
	logger.info("---- getpostRole DAO---");
	sb.append("  SELECT * FROM cmn_lookup_mst where PARENT_LOOKUP_ID="+parentGRID+" order by ORDER_NO ASC");
	logger.info("---- getpostRole DAo---"+sb);
	Query query = session.createSQLQuery(sb.toString());
	subGRList = query.list();
	return subGRList;
}

//added by vivek 
public List getSubLocationDDOs(String postId){
	List ddoDtls = null;
	Session session = getSession();
	StringBuffer sb = new StringBuffer();
	logger.info("---- getSubDDOs DAO---");
	//commented by roshan
	//sb.append("SELECT ddo.LOCATION_CODE FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and rep.REPT_DDO_POST_ID="+postId);
	//added by roshan
	sb.append("SELECT ddo.LOCATION_CODE FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and ((rep.REPT_DDO_POST_ID='"+postId+"') or (rep.FINAL_DDO_POST_ID='"+postId+"'))");
	//end
	logger.info("---- getSubDDOs DAo---"+sb);
	Query query = session.createSQLQuery(sb.toString());
	ddoDtls = query.list();
	return ddoDtls;
}
//ended by vivek


	// Added By Mayuresh
	
	public List getExpiryData(long locationCode)
	{
		logger.info("getExpiryDate ***********");
		List orderExpiryList = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
	
		sb.append("SELECT * FROM HR_PAY_ORDER_MST where END_DATE < CURRENT_DATE and LOCATION_CODE ="+locationCode);
		
		Query query = session.createSQLQuery(sb.toString());
		orderExpiryList = query.list();
		
		return orderExpiryList;
	}	
	
	// Ended by Mayuresh
	
	
	
	
	
	// Added By Roshan
	
	
	//start by roshan
	public String districtName(String ddoCode) {
		String districtId="";
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- get district---");
		//sb.append("SELECT ddo.LOCATION_CODE FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and ((rep.REPT_DDO_POST_ID='" + postId + "') or (rep.FINAL_DDO_POST_ID='" + postId + "'))");
		//added by roshan
		sb.append("SELECT DIstrict FROM MST_DCPS_DDO_OFFICE where ddo_code="+ddoCode+" and DDO_OFFICE='Yes'");
		//end by roshan
		logger.info("---- get district---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		districtId = (String) query.uniqueResult();
		logger.info("district name is "+districtId);
		return districtId;
		
	}
	//end by roshan
	
	

	@Override
	public List allTaluka(String districtID) {
		List talukaList=null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- get Taluka list---");
		
		sb.append("SELECT TALUKA_ID,TALUKA_NAME FROM CMN_TALUKA_MST where DISTRICT_ID="+districtID);
		//end by roshan
		logger.info("---- get district---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		talukaList = query.list();
		logger.info("taluka size is "+talukaList.size());
		return talukaList;
	}

	@Override
	public List getOfficeInATaluka(Long talukaId, String ddoCode) {
		List officeList=null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- get OfficeList list in a Taluka for a perticular DDO---");
		
		sb.append("SELECT rlt.ZP_DDO_CODE,CMN.loc_NAME FROM RLT_ZP_DDO_MAP RLT JOIN MST_DCPS_DDO_OFFICE MST ON RLT.ZP_DDO_CODE=MST.DDO_CODE INNER JOIN CMN_LOCATION_MST CMN ON MST.LOC_ID=CMN.LOC_ID WHERE MST.TALUKA='" + talukaId + "' AND RLT.REPT_DDO_CODE='" + ddoCode + "'");
		//end by roshan
		logger.info("---- get office in a taluka---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		officeList = query.list();
		logger.info("office size is"+officeList.size());
		return officeList;
	}

	//added by roshan
	public long findUsertype(String ddoCode) {
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from rlt_Zp_ddo_map where rept_ddo_code="+ddoCode);

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String count = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(count));
		  logger.info("sameCount" + count);
		  return seqId.longValue();
	}
	
	
	public long findLevel(String ddoCode) {
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select max(zplevel) from rlt_Zp_ddo_map where (Final_ddo_code='"+ddoCode+"') or (rept_ddo_code="+ddoCode+")");

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String level = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(level));
		  logger.info("sameCount" + level);
		  return seqId.longValue();



		}
	
	
	public long findUsertypeToCheckLevel3(String ddoCode) {
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from rlt_Zp_ddo_map where Final_ddo_code='"+ddoCode+"'");

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String count = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(count));
		  logger.info("sameCount" + count);
		  return seqId.longValue();
	}


	public long findUsertypeToCheckLevel4(String ddoCode) {
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from rlt_Zp_ddo_map where zp_ddo_code='"+ddoCode+"'");

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String count = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(count));
		  logger.info("sameCount" + count);
		  return seqId.longValue();
	}
	
	@Override
	public long checkUser(String ddoCode) {
		Session session = getSession();
		 StringBuffer sb = new StringBuffer();  
		logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from rlt_Zp_ddo_map where ZP_ddo_code='"+ddoCode+"'");

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String count = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(count));
		  logger.info("sameCount" + count);
		  return seqId.longValue();
	}

	@Override
	public List getFilterDdoCode(String locationCode) {
		logger.info("==> in getFilterDdoCode :: ");
		List orderMstList = null;
		Session hibSession = getSession();
        String strQuery = " SELECT DDO_CODE,DDO_OFFICE FROM org_ddo_mst where LOCATION_CODE in ("+locationCode+") order by DDO_CODE asc";
        logger.info("==> in getFilterDdoCode hiii :: "+strQuery);
        
        Query query = hibSession.createSQLQuery(strQuery);
       
        orderMstList = query.list();
        logger.info("Query Result is::"+orderMstList);
		
		return orderMstList;
	}

	@Override
	public List getAllOrderData(String ddo) {
		logger.info("==> in getAllOrderData :: ");
		List orderMstList = null;
		Session hibSession = getSession();
      String strQuery = " SELECT hr.ORDER_NAME, hr.ORDER_DATE FROM HR_PAY_ORDER_MST hr inner join org_ddo_mst ddo on hr.LOCATION_CODE=ddo.LOCATION_CODE where ddo.DDO_CODE='"+ddo+"'";
		//String strQuery = "select hr.orderName, hr.orderDate from HrPayOrderMst hr inner join OrgDdoMst ddo on hr.locationCode = ddo.locationCode where ddo.ddoCode='"+ddo+"' " ;

        logger.info("==> in getAllOrderData :: "+strQuery);
        
        Query query = hibSession.createSQLQuery(strQuery);
       
        orderMstList = query.list();
        logger.info("Query Result is::"+orderMstList);
		
		return orderMstList;
	}

	
}