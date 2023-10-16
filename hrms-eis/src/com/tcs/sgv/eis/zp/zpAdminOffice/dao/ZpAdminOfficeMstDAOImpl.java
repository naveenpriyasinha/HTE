package com.tcs.sgv.eis.zp.zpAdminOffice.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.mstscr.util.MstScrnUtility;
public class ZpAdminOfficeMstDAOImpl extends GenericDaoHibernateImpl<ZpAdminOfficeMst,Long> implements ZpAdminOfficeMstDAO
{
	MstScrnUtility mstScrnUtility = new MstScrnUtility();
	public ZpAdminOfficeMstDAOImpl(Class<com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List retriveOfcList(String OfcName) 
	{
		ZpAdminOfficeMst objZpAdminOfficeMst = null;
		List temp=null;
		Long Ofc_Code;
		Session hibSession = getSession();
		try
		{		
			String branchQuery = "select OFC_ID,OFFICE_NAME,Office_Code from ZP_ADMIN_OFFICE_MST ";
			//edited by shailesh
			//String branchQuery = "SELECT id, ADMIN_CODE, ADMIN_NAME FROM ZP_ADMIN_NAME_MST ";
			 
			/*if(!OfcName.equalsIgnoreCase("")){
				branchQuery+="where LOWER(OFFICE_NAME) like LOWER('%"+OfcName+"%')";
			}*/
			branchQuery+="order by OFC_ID asc";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();
			logger.error("List Size"+temp.size());
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
		
	}
	
	public List retriveMaxOfcCode() 
	{
		ZpAdminOfficeMst objZpAdminOfficeMst = null;
		List temp=null;
		Long Ofc_Code;
		Session hibSession = getSession();
		try
		{		
			String branchQuery = "select OFC_ID,max(OFFICE_CODE)as Office_Code from ZP_ADMIN_OFFICE_MST group by OFC_ID order by OFFICE_CODE desc";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();
			logger.error("List Size"+temp.size());
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
		
	}
	@Override
	public List searchZpAdminOfficeDetailsList(ZpAdminOfficeMst zpAdminOfficeMstVO, int startIndex, int pageSize) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*public List searchZpAdminOfficeDetailsList (ZpAdminOfficeMst zpAdminOfficeMstVO, int startIndex, int pageSize) throws Exception{
		logger.info("getZpAdminOfficeDetailsList");
		Session session=getSession(); 
		List zpAdminOfficeMstVOList = new ArrayList(); 
		String strWhereClause = "";
		String strQuery = "select * from ( select temp.*, ROWNUM rnum from ( select count(1) over (partition by 1) totalcnt,DD.* from ( select mst.OFFICE_NAME,mst.OFFICE_CODE,mst.OFC_ID from ZP_ADMIN_OFFICE_MST mst WHERE 1=1 ";

		if(zpAdminOfficeMstVO.getOfficeName()!=null && !zpAdminOfficeMstVO.getOfficeName().equals(""))
			strWhereClause = strWhereClause + " AND mst.OFFICE_NAME like '%" + zpAdminOfficeMstVO.getOfficeName()+"%'";
		if(zpAdminOfficeMstVO.getOfficeCode()!=null && zpAdminOfficeMstVO.getOfficeCode() >0 )
			strWhereClause = strWhereClause + " AND mst.OFFICE_CODE = "+ zpAdminOfficeMstVO.getOfficeCode();
		strQuery = strQuery+ strWhereClause + ") DD ) temp) where rnum between " + startIndex + " and " + (startIndex+pageSize);
		Query sqlQuery = session.createSQLQuery(strQuery.toString());
		zpAdminOfficeMstVOList = sqlQuery.list();
		zpAdminOfficeMstVOList = mstScrnUtility.getMstScrnCustVOLstFromTuppleByColumnLength(zpAdminOfficeMstVOList,5);
		return zpAdminOfficeMstVOList;
	}*/

	public int checkSchemeCode(String schemeCode) 
	{
		ZpAdminOfficeMst objZpAdminOfficeMst = null;
		String temp="";
		int isValid=0;
		Long Ofc_Code;
		Session hibSession = getSession();
		try
		{		
			String branchQuery = "SELECT count(SCHEME_CODE) FROM MST_SCHEME where SCHEME_CODE ='"+schemeCode+"'";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.uniqueResult().toString();
			isValid=Integer.parseInt(temp);
			logger.error("scheme Code present="+temp);
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return isValid;
		
	}
	public List retriveGRNo(String GRNo) 
	{
		HrPayOrderMst objHrPayOrderMst = null;
		List temp=null;
		int isValid=0;
		Long Ofc_Code;
		Session hibSession = getSession();
		try
		{		
			String branchQuery = "SELECT ORDER_NAME FROM HR_PAY_ORDER_MST where GR_TYPE ='"+GRNo+"'";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();
			//isValid=Integer.parseInt(temp);
			logger.error("GR Name present="+temp);
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List retriveDateFromGRNo(String GRNo) 
	{
		HrPayOrderMst objHrPayOrderMst = null;
		List temp=null;
		int isValid=0;
		Long Ofc_Code;
		Session hibSession = getSession();
		try
		{		
			String branchQuery = "SELECT date(order_date) FROM HR_PAY_ORDER_MST where ORDER_NAME='"+GRNo+"'";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();
			//isValid=Integer.parseInt(temp);
			logger.error("GR Name present="+temp);
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
/*public List getallAdminName()
    { 
    	List temp=null;
    	Session hibSession = getSession();
    	try
		{
    	  
	      String mainquery = " SELECT ADMIN_CODE,ADMIN_NAME from ZP_ADMIN_NAME_MST";
	      Query sqlQuery= hibSession.createSQLQuery(mainquery);
	      temp= sqlQuery.list();
	      logger.error("Admin Name:::="+temp.size());
		}
    	catch(Exception e){
    			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
    			e.printStackTrace();
    	}
	return temp;
    }	
*/
	public List getallAdminName()
    { 
    	List temp=null;
    	Session hibSession = getSession();
    	try
		{
    	  
	      String mainquery = " SELECT ADMIN_CODE,ADMIN_NAME from ZP_ADMIN_NAME_MST order by ADMIN_CODE ASC";
	      Query sqlQuery= hibSession.createSQLQuery(mainquery);
	      temp= sqlQuery.list();
	      logger.error("Admin Name:::="+temp.size());
		}
    	catch(Exception e){
    			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
    			e.printStackTrace();
    	}
	return temp;
    }	

	
}
