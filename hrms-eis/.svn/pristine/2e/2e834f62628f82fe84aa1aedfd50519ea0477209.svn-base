package com.tcs.sgv.eis.dao;

import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;

public class ChangeStatementDAOImpl extends GenericDaoHibernateImpl<PaybillHeadMpg,Long> {
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	public ChangeStatementDAOImpl(Class<PaybillHeadMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	public int getApproveFlagForGivenBill(long lbillid, long month, long year){
		int status = -1;
		Session hibSession = getSession();
		StringBuffer strBfr = new StringBuffer();
		strBfr.append(" ");	
		return status;
	}
	
	
	public List getChngeStmntDataToDisplay(int Month, String Year, String billNo,String billtype,String billStatus,long locId,String userType)
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append( " select scheme.scheme_code, scheme.scheme_name,mpg.BILL_GROSS_AMT,mpg.BILL_NET_AMOUNT,bill.BILL_GROUP_ID,bill.DESCRIPTION,mpg.APPROVE_FLAG ");
			query.append( " ,mpg.reason_for_rejection from  paybill_head_mpg AS mpg,mst_scheme AS scheme, mst_dcps_bill_group AS bill ");
			query.append( " where  mpg.LOC_ID="+locId+" "); 
			if(Month > 0 && Year != null && !Year.equals(""))
				query.append( " and mpg.PAYBILL_MONTH=" + Month + " and  mpg.PAYBILL_YEAR=" + Year );
			else
				query.append( "  and mpg.PAYBILL_MONTH = null and mpg.PAYBILL_YEAR = null ) )");
				query.append( "  and scheme.scheme_id=mpg.SUBHEAD_ID and mpg.BILL_NO = bill.BILL_GROUP_ID ");
				//query.append( "  and mpg.APPROVE_FLAG in (0,1) and ");
			if(billtype != null && !billtype.equals(""))
				query.append( " and mpg.BILL_TYPE_ID=" + billtype );
			if(billStatus != null && !billStatus.equals(""))
				query.append( "  and mpg.approve_flag = " + billStatus);
			else 
				if(userType != "" && userType != null && userType.equals("DDO"))
					query.append( "  and mpg.approve_flag  in ( " + rb.getString("changeStatementFrwrd")+","+
							rb.getString("changeStatementApproved")+" )");
				else 
					query.append( "  and mpg.approve_flag  in ( " + rb.getString("changeStatementCreated")
							+","+rb.getString("changeStatementFrwrd")+","+rb.getString("changeStatementApproved")+
							","+rb.getString("created")+","+rb.getString("changeStatementRejected")+","+
							rb.getString("approved")+" )");
			if(billNo != null && !billNo.equals(""))
				query.append( " and bill.BILL_GROUP_ID="+billNo);
			query.append( "  order by bill.BILL_GROUP_ID");
			logger.info("Query for get getTokenDataforDisplay is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
				
		return resList;
	}

    public List getSubDDOsOffc(long postId)
    {
        List ddoDtls = null;
        Session session = getSession();
        StringBuffer sb = new StringBuffer();
        logger.info("---- getSubDDOs DAO---");
        sb.append("SELECT ddo.DDO_CODE ,ddo.DDO_office FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo,MST_dcps_ddo_office office where office.loc_id=ddo.location_code and ddo.DDO_CODE = rep.ZP_DDO_CODE and rep.status in (0,1) and rep.REPT_DDO_POST_ID="+postId+"");
        sb.append(" order by ddo.ddo_code asc");
        logger.info("---- getSubDDOs DAo---"+sb);
        Query query = session.createSQLQuery(sb.toString());

        ddoDtls = query.list();
        return ddoDtls;
    }

    public List getChangeStatemtnList(String month, String year, Long postId, String ddoCode, Long status)
    {
        List ddoDtls = null;
        Session session = getSession();
        StringBuffer sb = new StringBuffer();
        logger.info("---- getSubDDOs DAO---");
        sb.append(" SELECT head.BILL_NO,head.BILL_GROSS_AMT,head.BILL_NET_AMOUNT,bill.DESCRIPTION ,bill.SCHEME_NAME,bill.SCHEME_CODE,head.REASON_FOR_REJECTION  ,head.paybill_id,bill.sub_scheme_code ");
        sb.append(" FROM RLT_ZP_DDO_MAP rep inner join  ORG_DDO_MST ddo on ddo.DDO_CODE = rep.ZP_DDO_CODE  ");
        sb.append(" inner join PAYBILL_HEAD_MPG HEAD on ddo.LOCATION_CODE=head.LOC_ID ");
        sb.append(" inner join MST_DCPS_BILL_GROUP bill on bill.BILL_GROUP_ID=head.BILL_NO ");
        sb.append(" where  rep.status in (0,1) and rep.REPT_DDO_POST_ID= "+postId+" and head.APPROVE_FLAG="+status+" and head.PAYBILL_YEAR="+year+" and head.PAYBILL_MONTH="+month+" ");
        if(ddoCode!=null && !ddoCode.equals("") && Long.parseLong(ddoCode)!=-1)
            sb.append("  and rep.zp_ddo_code='"+ddoCode+"'");
        sb.append(" order by ddo.ddo_code asc");
        logger.info("---- getSubDDOs DAo---"+sb);
        Query query = session.createSQLQuery(sb.toString());

        ddoDtls = query.list();
       logger.info("ddoDtls size ***************"+ddoDtls.size());
        return ddoDtls;
    }

    public void updateCHangeStmntStatus(String action, String billNo, String remark)
    {

        
        Session session = getSession();
        StringBuffer sb = new StringBuffer();
        logger.info("---- updateCHangeStmntStatus ---");
        sb.append(" update paybill_head_mpg set Approve_flag= "+action+" ");
        if(remark !=null && !remark.equals(""))
        sb.append(" ,REASON_FOR_REJECTION= '"+remark+"' ");
        
        sb.append(" where paybill_id= "+billNo+" ");
      
        logger.info("---- updateCHangeStmntStatus ---"+sb);
        Query query = session.createSQLQuery(sb.toString());
        query.executeUpdate();
       
      
    
        
    }

    public List getDDOType(String loggedInDdoCode)
    {
        List ddoDtls = null;
        Session session = getSession();
        StringBuffer sb = new StringBuffer();
        logger.info("---- getSubDDOs DAO---");
        sb.append("SELECT  distinct DDO_OFFICE_TYPE ||'##'|| USED_AS_LEVEL2 FROM CMN_DDO_MST where DDO_CODE= '"+loggedInDdoCode+"' order by DDO_OFFICE_TYPE asc ");
        
        logger.info("---- getSubDDOs DAo---"+sb);
        Query query = session.createSQLQuery(sb.toString());

        ddoDtls = query.list();
        return   ddoDtls;
    }
	
	


}
