package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.acl.valueobject.AclRoleMst;
import com.tcs.sgv.common.valueobject.MstScheme;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;

public class PaybillHeadMpgDAOImpl extends GenericDaoHibernateImpl<PaybillHeadMpg,Long> {
	public PaybillHeadMpgDAOImpl(Class<PaybillHeadMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	public boolean checkPaybillGenerated(int month,int year,String billNo)
	{
		Session hibSession = getSession();
		List paybillList=null;
		StringBuffer sb = new StringBuffer("from PaybillHeadMpg ph where ph.billNo= "+billNo+" and ph.month="+month+" and ph.year="+year+" and ph.approveFlag  in( 0,1) ");
		Query qry = hibSession.createQuery(sb.toString());
		paybillList = qry.list();
		logger.info("The paybillList size from paybillHeadMpg is--->"+paybillList.size());
		
		if(paybillList.size()==0)
			return false;
		else
			return true;
	}
	
	public boolean isLoggedInUserAdmin(Map loginMap)
	{
		ResourceBundle lResourceBundle =  ResourceBundle.getBundle("resources/Payroll");
		ArrayList<AclRoleMst> userRoles = (ArrayList)loginMap.get("userRoles");
		logger.info("userRoles::"+userRoles);				
		long roleId = Long.parseLong(lResourceBundle.getString("adminRoleId"));
		logger.info("roleId::"+roleId);
		long adminRoleId=0L;
		boolean isRoleAdminFlag=false;
		if(userRoles!=null)
		{
			for(int i=0;i<userRoles.size();i++)
			{	
				adminRoleId =userRoles.get(i).getRoleId();
				if(roleId==adminRoleId)
				{
					isRoleAdminFlag=true;
					break;
				}					
			}
		}
		return isRoleAdminFlag; 
	}
	
	public Date getCreatedDateForLSLE(double month,double year,long empId)
	{
		Date createdDate=null;
		
		List list =new ArrayList();
 		Session hibSession = getSession();
 		StringBuffer query=new StringBuffer();
 		 query.append("select hh.createdDate from PaybillHeadMpg hh,HrPayPaybill pp,HrEisEmpMst mm where hh.approveFlag=1 and hh.hrPayPaybill=pp.paybillGrpId " );
 		 query.append(" and hh.month="+month+" and hh.year="+year+" and pp.hrEisEmpMst.empId="+empId );
 		Query hsqlQuery = hibSession.createQuery(query.toString());	 
 	    
 	   list= hsqlQuery.list();
 	    if(list.size()>0)
 	    	createdDate=(Date)list.get(0);
		
		return createdDate;
	}
	
	
	/**
	 * Method will return list of paybill_head_mpg table according to
	 * given month, year, bill number, bill type, location id and approve flag.
	 * @param month
	 * @param year
	 * @param locId
	 * @param billNo
	 * @param billType
	 * @param approveFlag
	 * @return List of PaybillHeadMpg
	 */
	public List<PaybillHeadMpg> getDatabyMonthYear(int month, int year, long locId, long billNo, long billType, int approveFlag){
		List list =new ArrayList();
 		Session hibSession = getSession();
 		StringBuffer query=new StringBuffer();
 		 query.append("from PaybillHeadMpg mpg where month=");
 		query.append(month);
 		query.append(" and mpg.year=");
 		query.append(year);
 		query.append(" and mpg.cmnLocationMst.locId=");
 		query.append(locId);
 		query.append(" and mpg.billNo=");
 		query.append(billNo);
 		query.append(" and mpg.billTypeId=");
 		query.append(billType);
 		query.append(" and mpg.approveFlag=");
 		query.append(approveFlag); 		 
 		Query hsqlQuery = hibSession.createQuery(query.toString());	  	   
 	   return hsqlQuery.list();
	}
	
	public List<MstScheme> getMstScheme(String schemeCode,String ddoCode)
	{
		List list =new ArrayList();
 		Session hibSession = getSession();
 		StringBuffer query=new StringBuffer();
// 		 query.append("select mpg from MstScheme mpg,MstDcpsBillGroup mstBill where mpg.schemeCode="+schemeCode+" and mstBill.dcpsDdoSchemeCode="+ schemeCode +" and mstBill.dcpsDdoCode="+ddoCode);
  		query.append("select mpg from MstScheme mpg,MstDcpsBillGroup mstBill where mpg.schemeCode='"+schemeCode+"' and mstBill.dcpsDdoSchemeCode='"+ schemeCode +"' and mstBill.dcpsDdoCode='"+ddoCode+"'");
 		Query hsqlQuery = hibSession.createQuery(query.toString());	  	   
 	   return hsqlQuery.list();
	}
	public List getpaybillheadFromid(long billid,long approvalFlag,long month, long year) 
	{
		
		logger.info("query to getpaybillheadFromid is " + approvalFlag);
		List list =new ArrayList();
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("from PaybillHeadMpg as mpg where ");
		strBfr.append("mpg.billNo.dcpsDdoBillGroupId="+billid);
		strBfr.append(" and mpg.month=");
		strBfr.append(month);
		strBfr.append(" and mpg.year=");
		strBfr.append(year);
		strBfr.append("and mpg.approveFlag in(");
		//commented by Poonam for change statement
		strBfr.append(approvalFlag);
		strBfr.append(",0,2,6,7,8,9)");

		logger.info("query to getpaybillheadFromid is " + strBfr.toString());
		Query updateQuery = hibSession.createQuery(strBfr.toString());
		/*Object paybillObject = updateQuery.uniqueResult();
		if(paybillObject!=null)
		 return (PaybillHeadMpg)updateQuery.uniqueResult();
		else
			return null;*/
		list =updateQuery.list();
		return list;
	}
	public int deleteNonGov(long Paybillid)
	{
		List DeltedList=null;
		Session hibSession = getSession();
		
		StringBuffer query = new StringBuffer(); 
		
		query.append("delete from hr_pay_payslip_non_govt where PAYBILL_ID="+Paybillid);
		logger.info("Query for get deleteNonGov is---->>>>"+query.toString());
		Query sqlQuery=hibSession.createSQLQuery(query.toString());										
		int rowsDeleted = sqlQuery.executeUpdate();
		
		return rowsDeleted;
	}
	public int deleteLoanDtls(long Paybillid)
	{
		List DeltedList=null;
		Session hibSession = getSession();
		
		StringBuffer query = new StringBuffer(); 
		query.append("delete from hr_pay_paybill_loan_dtls where PAYBILL_ID="+Paybillid);
		logger.info("Query for get deleteLoanDtls is---->>>>"+query.toString());
		Query sqlQuery=hibSession.createSQLQuery(query.toString());										
		int rowsDeleted = sqlQuery.executeUpdate();
		
		return rowsDeleted;
	}
	
	//added by Ankit bhatt
	public List<PaybillHeadMpg> getPaybillHeadFromPaybillId(long paybillId) {
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append("from PaybillHeadMpg as mpg where ");
		strBfr.append("mpg.hrPayPaybill=" + paybillId);
		logger.info("query to getpaybillheadFromid is " + strBfr.toString());
		Query updateQuery = hibSession.createQuery(strBfr.toString());		
		return updateQuery.list();
	}
	//ended
	//added by Amish Parikh
	public List getGeneratedBillList(long locationId) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select head.BILL_NO,bg.DESCRIPTION from PAYBILL_HEAD_MPG head , MST_DCPS_BILL_GROUP bg where head.APPROVE_FLAG=0 "); 
		sb.append("and bg.BILL_GROUP_ID = head.BILL_NO and head.PAYBILL_MONTH = month(sysdate)and head.PAYBILL_YEAR = year(sysdate) and bg.LOC_ID = ");
		sb.append(locationId);
		logger.info("query to getGeneratedBillList is " + sb.toString());
		Query sqlQuery=hibSession.createSQLQuery(sb.toString());	
		
		return sqlQuery.list();
	}
	//ended
	//added by Amish Parikh
	public List getGeneratedBillList(long locationId,int month,int year) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select head.BILL_NO,bg.DESCRIPTION from PAYBILL_HEAD_MPG head , MST_DCPS_BILL_GROUP bg where head.APPROVE_FLAG=0 "); 
		sb.append("and bg.BILL_GROUP_ID = head.BILL_NO and head.PAYBILL_MONTH = :month and head.PAYBILL_YEAR = :year and bg.LOC_ID = ");
		sb.append(locationId);
		logger.info("query to getGeneratedBillList is " + sb.toString());
		Query sqlQuery=hibSession.createSQLQuery(sb.toString());	
		sqlQuery.setParameter("month", month);
		sqlQuery.setParameter("year", year);
		
		return sqlQuery.list();
	}
	//ended
	//added by Amish Parikh
	public Long getUserPostCountForBill(long billNo,String operationalFlag) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		if(operationalFlag.equalsIgnoreCase("billUserCount")){
			sb.append("select count(emp_id) from HR_PAY_PAYBILL where emp_id is not null and PAYBILL_GRP_ID in ");
			sb.append("(select paybill_id from PAYBILL_HEAD_MPG where PAYBILL_MONTH = month(sysdate) ");
			sb.append(" and PAYBILL_YEAR = year(sysdate) and APPROVE_FLAG = 0 and BILL_NO =");
			sb.append(billNo);
			sb.append(")");			
		}
		if(operationalFlag.equalsIgnoreCase("billPostCount")){
			sb.append("select count(post_id) from HR_PAY_PAYBILL where PAYBILL_GRP_ID in ");
			sb.append("(select paybill_id from PAYBILL_HEAD_MPG where PAYBILL_MONTH = month(sysdate) ");
			sb.append("and PAYBILL_YEAR = year(sysdate) and APPROVE_FLAG = 0 and BILL_NO =");
			sb.append(billNo);
			sb.append(")");
		}
		if(operationalFlag.equalsIgnoreCase("psrPostCount")){
			sb.append("select count(post_id) from HR_PAY_POST_PSR_MPG where BILL_NO = ");
			sb.append(billNo);			
		}
		if(operationalFlag.equalsIgnoreCase("userCount")){
			sb.append("select count(user_id) from ORG_USERPOST_RLT where ACTIVATE_FLAG = 1 and");
			sb.append(" POST_ID IN (select post_id from HR_PAY_POST_PSR_MPG where BILL_NO =");
			sb.append(billNo);
			sb.append(")");
		}
		if(operationalFlag.equalsIgnoreCase("postEndCount")){
			sb.append("SELECT count(POST_ID) FROM org_post_mst where END_DATE < sysdate and POST_ID in(");
			sb.append("select post_id from HR_PAY_POST_PSR_MPG where BILL_NO =");
			sb.append(billNo);
			sb.append(")");
		}
		if(operationalFlag.equalsIgnoreCase("userServEndCount")){
			sb.append("SELECT count(emp_id) FROM org_emp_mst where EMP_SRVC_EXP < sysdate and user_id in(select user_id");
			sb.append(" from ORG_USERPOST_RLT where ACTIVATE_FLAG = 1 and POST_ID in (select post_id from  HR_PAY_POST_PSR_MPG where BILL_NO =");
			sb.append(billNo);
			sb.append("))");
		}
		 
		Query sqlQuery=hibSession.createSQLQuery(sb.toString());	
		return (Long.parseLong(sqlQuery.list().get(0).toString()));
	}
	//ended
	//added by Amish Parikh
	public List getDesigwisePostListMst(long billNo) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select POST_short_name,count(post_short_name) from ORG_POST_DETAILS_RLT where post_id in "); 
		sb.append("(select post_id from HR_PAY_POST_PSR_MPG where BILL_NO = ");
		sb.append(billNo);
		sb.append(") group by post_short_name order by post_short_name");
		logger.info("query to getDesigwisePostListMst is " + sb.toString());
		Query sqlQuery=hibSession.createSQLQuery(sb.toString());	
		
		return sqlQuery.list();
	}
	//ended
	//added by Amish Parikh
	public List getDesigwisePostListInBill(long billNo) {
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select rlt.POST_SHORT_NAME,count(rlt.POST_ID) from ORG_POST_DETAILS_RLT rlt, HR_PAY_PAYBILL bill, PAYBILL_HEAD_MPG head where "); 
		sb.append(" head.PAYBILL_MONTH = month(sysdate) and head.PAYBILL_YEAR = year(sysdate) and head.APPROVE_FLAG = 0 and");
		sb.append(" head.PAYBILL_ID = bill.PAYBILL_GRP_ID and bill.POST_ID = rlt.POST_ID and head.BILL_NO =");
		sb.append(billNo);
		sb.append(" group by rlt.POST_SHORT_NAME order by rlt.POST_SHORT_NAME");
		logger.info("query to getDesigwisePostListInBill is " + sb.toString());
		Query sqlQuery=hibSession.createSQLQuery(sb.toString());	
		
		return sqlQuery.list();
	}
	//ended
		
}
