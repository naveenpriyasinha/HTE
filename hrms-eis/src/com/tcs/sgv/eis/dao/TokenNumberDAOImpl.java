package com.tcs.sgv.eis.dao;

import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class TokenNumberDAOImpl extends GenericDaoHibernateImpl<TrnBillRegister, Long> 
{
	
	public TokenNumberDAOImpl(Class<TrnBillRegister> type, SessionFactory sessionFactory) 
	{
        super(type);
        setSessionFactory(sessionFactory);
    }
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	public List getTokenDataforDisplay(int Month, String Year, long locId, String billNo,String billtype, String billStatus)
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append( " select DISTINCT  t.bill_cntrl_no, t.token_num, t.updated_date, t.bill_net_amount, t.curr_bill_status,  bs.bill_no billOrder, T.BILL_NO, T.BILL_DATE, t.bill_gross_amount ");
			query.append( " FROM  trn_bill_register t  ,paybill_billreg_mpg pb, hr_pay_bill_subhead_mpg bs , paybill_head_mpg ph   ");
			query.append( " where t.location_code = " + locId + " and   t.bill_no = pb.trn_bill_id and  pb.paybill_id =  ph.paybill_id  and   ph.bill_no = bs.bill_subhead_id and  "); 
			if(Month > 0 && Year != null && !Year.equals(""))
				query.append( " ph.paybill_month = " + Month + " and  ph.paybill_year = " + Year );
			else
				query.append( "  and ph.paybill_month = null and ph.paybill_year = null ) )");
			if(billNo != null && !billNo.equals(""))
				query.append( "  and bs.bill_subhead_id = " + billNo);
			if(billtype != null && !billtype.equals(""))
				query.append( " and ph.bill_type_id = " + billtype );
			if(billStatus != null && !billStatus.equals(""))
				query.append( "  and ph.approve_flag = " + billStatus);
			else
				query.append( "  and ph.approve_flag  in (" + rb.getString("created") +"," + rb.getString("approved") + ")");
			
			query.append( " order by bs.bill_no");
			logger.info("Query for get getTokenDataforDisplay is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
				
		return resList;
	}
	/*public List getBillListForDisplay(long locId)
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append("select s.bill_no,s.BILL_SUBHEAD_ID from hr_pay_bill_subhead_mpg s where loc_id="+locId+" order by bill_no ");
			logger.info("Query for get getBillNoData is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
				
		return resList;
	}*/
	/*public List getTokenDataforDisplay(int Month, String Year, String billNo,String billtype)
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append( " select pb.ID,scheme.scheme_code, scheme.scheme_name,pb.GROSS_AMT,pb.NET_TOTAL,bill.BILL_GROUP_ID,bill.DESCRIPTION,mpg.APPROVE_FLAG ");
			query.append( " from hr_pay_paybill AS pb, paybill_head_mpg AS mpg,mst_scheme AS scheme, mst_dcps_bill_group AS bill ");
			query.append( " where pb.ID=mpg.PAYBILL_ID "); 
			if(Month > 0 && Year != null && !Year.equals(""))
				query.append( " and mpg.PAYBILL_MONTH=" + Month + " and  mpg.PAYBILL_YEAR=" + Year );
			else
				query.append( "  and mpg.PAYBILL_MONTH = null and mpg.PAYBILL_YEAR = null ) )");
				query.append( "  and scheme.scheme_id=mpg.SUBHEAD_ID and mpg.BILL_NO = bill.BILL_GROUP_ID ");
				query.append( "  and mpg.APPROVE_FLAG in (0,1) and ");
			if(billtype != null && !billtype.equals(""))
				query.append( " mpg.BILL_TYPE_ID=" + billtype );
			if(billNo != null && !billNo.equals(""))
				query.append( " and bill.BILL_GROUP_ID="+billNo);
			query.append( "  order by bill.BILL_GROUP_ID");
			logger.info("Query for get getTokenDataforDisplay is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
				
		return resList;
	}*/
	public List getTokenDataforDisplay(int Month, String Year, String billNo,String billtype,String billStatus,long locId)
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append( " select distinct scheme.scheme_code, scheme.scheme_name,mpg.BILL_GROSS_AMT,mpg.BILL_NET_AMOUNT,bill.BILL_GROUP_ID,bill.DESCRIPTION,mpg.APPROVE_FLAG,mpg.status,bill.sub_scheme_code ");
			query.append( " from  paybill_head_mpg AS mpg,mst_scheme AS scheme, mst_dcps_bill_group AS bill ");
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
			
			//added by poonam for change statment
			else
				query.append("  and mpg.approve_flag  in ("
						+ rb.getString("created") + ",6,7,8,9,"
						+ rb.getString("approved") + "," + rb.getString("rejected")
						+ ")");
			//added by poonam for change statment
		/*else	commented by poonam
				/query.append( "  and mpg.approve_flag  in (" + rb.getString("created") +"," + rb.getString("approved") + "," + rb.getString("rejected") +")");
			*/
			if(billNo != null && !billNo.equals(""))
				query.append( " and bill.BILL_GROUP_ID="+billNo);
			
			//added by vaibhav tyagi: start
			query.append(" and mpg.status in (0,2,4)");
			//added by vaibhav tyagi: end
			query.append( "  order by bill.BILL_GROUP_ID");
			logger.info("Query for get getTokenDataforDisplay is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
				
		return resList;
	}
	/*public List getBillListForDisplay(long locId)
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append("select s.bill_no,s.BILL_SUBHEAD_ID from hr_pay_bill_subhead_mpg s where loc_id="+locId+" order by bill_no ");
			logger.info("Query for get getBillNoData is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
				
		return resList;
	}Commented by Javed */
	
	public List getBillListForDisplay(long locId)
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append("select s.BILL_GROUP_ID,s.SCHEME_CODE from mst_dcps_bill_group s where LOC_ID="+locId+" and (BILL_DELETED is null or BILL_DELETED <> 'Y') and (BILL_DCPS is null or BILL_DCPS <> 'Y') order by BILL_GROUP_ID ");
			logger.info("Query for get getBillNoData is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
			logger.info("Size of getBillNoData is---->>>>"+resList.size());
		return resList;
	}
	public List getTokenDataforDisplayMaha(int Month, String Year, long locId, String billNo,String billtype, String billStatus)
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append( " select pb.ID,scheme.scheme_code, scheme.scheme_name,pb.GROSS_AMT,pb.NET_TOTAL AS paybillID from hr_pay_paybill AS pb, paybill_head_mpg AS mpg,mst_scheme AS scheme  ");
			query.append( " where pb.ID=mpg.PAYBILL_ID and scheme.scheme_id=mpg.SUBHEAD_ID ");			
			if(Month > 0 && Year != null && !Year.equals(""))
				query.append( " and mpg.PAYBILL_MONTH=  " + Month + " and mpg.PAYBILL_YEAR= " + Year );			
			if(billNo != null && !billNo.equals(""))
				query.append( "  and bs.bill_subhead_id = " + billNo);
			if(billtype != null && !billtype.equals(""))
				query.append( " and mpg.BILL_TYPE_ID= " + billtype );
			if(billStatus != null && !billStatus.equals(""))
				query.append( "  and ph.approve_flag = " + billStatus);
			else
				query.append( "  and ph.approve_flag  in (" + rb.getString("created") +"," + rb.getString("approved") + ")");
			query.append( "  order by bs.bill_no");
			logger.info("Query for get getTokenDataforDisplay is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
				
		return resList;
	}
	
	//Added by Abhilash
	
	public List getDataForPrintAllReports(long locId,String billNo,int month,String year)
	{
		List  resList = null;
		try
		{
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(" select sum(pb.netTotal),sum(pb.deduc9780+pb.deduc1038+pb.deduc1037+pb.deduc1039+pb.deduc1040+pb.adv5054),sum(pb.deduc9583+pb.adv5055),sum(pb.deduc9510),sum(pb.deduc9570),sum(pb.deduc9550+pb.deduc1041),sum(pb.loan5061),sum(pb.loanInt5061),sum(pb.deduc1005),sum(pb.deduc1004),sum(pb.deduc1000),sum(pb.deduc1001),sum(pb.deduc1002)  "); 
			query.append(" ,sum(pb.loan5058),sum(pb.loan5067),sum(pb.loan5056),sum(pb.loan5051),sum(pb.adv5059),sum(pb.adv5052),sum(pb.adv5053),sum(pb.loanInt5051)"); 
			query.append(" ,sum(pb.loanInt5056),sum(pb.loanInt5067),sum(pb.adv5068),sum(pb.deduc9711),sum(pb.loan5064),sum(pb.loanInt5064),sum(pb.loan5065),sum(pb.loanInt5065) ");
			//query.append(" ,sum(pb.loan5057),sum(pb.loanInt5057),sum(pb.deduc9134),sum(pb.deduc9135) ");
			query.append(" ,sum(pb.loan5057),sum(pb.loanInt5057),sum(pb.deduc9134)  ");
			
			query.append(" from HrPayPaybill pb,PaybillHeadMpg ph,MstDcpsBillGroup mpg where ph.billNo=mpg.dcpsDdoBillGroupId and ph.billNo = " +billNo +" and pb.cmnLocationMst.locId = " + locId +"  ");
			query.append(" and ph.month="+month+" and ph.year= "+year+" and ph.approveFlag in(0,1) and mpg.LocId= "+locId+" ");  
			query.append(" and pb.paybillGrpId=ph.hrPayPaybill"); 
			query.append(" and pb.hrEisEmpMst.empId is not null ");

			logger.info("Query from getDataForPrintAllReports " + query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			
		}
		
		return resList;
		
	}
	
	public List getPaybillgroupIdFromPayBill(long locId,String billNo,int month,String year)
	{
		List  resList = null;
		try
		{
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(" select pb.paybillGrpId,pb.month,mpg.dcpsDdoBillDescription "); 
			query.append(" from HrPayPaybill pb,PaybillHeadMpg ph,MstDcpsBillGroup mpg where ph.billNo=mpg.dcpsDdoBillGroupId and ph.billNo = " +billNo +" and pb.cmnLocationMst.locId = " + locId +" "); 
			query.append(" and ph.month="+month+" and ph.year= "+year+" and ph.approveFlag in(0,1) and mpg.LocId= "+locId+" ");  
			query.append(" and pb.paybillGrpId=ph.hrPayPaybill"); 
			query.append(" and pb.hrEisEmpMst.empId is not null ");

			logger.info("Query from getDataForPrintAllReports " + query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			
		}
		
		return resList;
		
	}
	
	public List getSeriesListFromBillNo(long locId,int month,String year,long paybillGrpId)
	{
		List  resList = null;
		try
		{ 
			
			
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(" select distinct gpf.pfSeries,up.activateFlag "); 
			query.append(" from HrPayPaybill pb,HrPayGpfBalanceDtls gpf,OrgUserpostRlt up where gpf.userId=up.orgUserMst.userId "); 
			query.append(" and up.orgPostMstByPostId.postId=pb.orgPostMst.postId and gpf.orgGradeMst.gradeId not in(100001,100064,100065,100066) and up.activateFlag=1 and pb.month="+month+" and pb.year= "+year+" ");  
			query.append(" and pb.cmnLocationMst.locId = " + locId +" and pb.paybillGrpId="+paybillGrpId+" and pb.hrEisEmpMst.empId is not null  and upper(gpf.pfSeries) not like upper('%dcps%')");

			logger.info("Query from getSeriesListFromBillNo " + query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			
		}
		
		return resList;
		
	}
	
	public List getSeriesListForOtherThanFromBillNo(long locId,int month,String year,long paybillGrpId)
	{
		List  resList = null;
		try
		{ 
			
			
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(" select distinct gpf.pfSeries,up.activateFlag "); 
			query.append(" from HrPayPaybill pb,HrPayGpfBalanceDtls gpf,OrgUserpostRlt up where gpf.userId=up.orgUserMst.userId "); 
			query.append(" and up.orgPostMstByPostId.postId=pb.orgPostMst.postId and up.activateFlag=1 and gpf.orgGradeMst.gradeId<>100067 and pb.month="+month+" and pb.year= "+year+" ");  
			query.append(" and pb.cmnLocationMst.locId = " + locId +" and pb.paybillGrpId="+paybillGrpId+" and pb.hrEisEmpMst.empId is not null  and upper(gpf.pfSeries) not like upper('%dcps%')");

			logger.info("Query from getSeriesListFromBillNo " + query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			
		}
		
		return resList;
		
	}
	
	public List getBillListForReport(long locId)
	{			
		List resList=null;
		
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer(); 
			query.append("select s.BILL_GROUP_ID,s.SCHEME_CODE,s.DESCRIPTION from mst_dcps_bill_group s where LOC_ID="+locId+" and (BILL_DELETED is null or BILL_DELETED <> 'Y') and (BILL_DCPS is null or BILL_DCPS <> 'Y') order by BILL_GROUP_ID ");
			logger.info("Query for get getBillNoData is---->>>>"+query.toString());
			Query sqlQuery=hibSession.createSQLQuery(query.toString());										
			resList=sqlQuery.list();
			logger.info("Size of getBillNoData is---->>>>"+resList.size());
		return resList;
	}

	public List checkDuplicateRecords(long locId,String billNo,int month,String year)
	{
		List  resList = null;
		try
		{
			Session hibSession = getSession();
			StringBuffer query = new StringBuffer();
			query.append(" select ph from PaybillHeadMpg ph"); 
			query.append(" where ph.month="+month+" and ph.year= "+year+" and ph.billNo = " +billNo +" and ph.approveFlag in(0,1) and ph.cmnLocationMst.locId= "+locId+""); 
			 

			logger.info("Query from checkDuplicateRecords " + query.toString());
			Query sqlQuery = hibSession.createQuery(query.toString());
			resList = sqlQuery.list();
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			
		}
		
		return resList;
		
	}
	
	//Ended by Abhilash
	//added by roshan
	public List getBillListForAllLevelReport(long locId, String selectedLocCode) {
		List resList=null;
		logger.info("hii i m in getBillListForAllLevelReport");
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer(); 
		query.append("select s.BILL_GROUP_ID,s.SCHEME_CODE,s.DESCRIPTION from mst_dcps_bill_group s where LOC_ID in (select location_code from org_ddo_mst where ddo_code in (select zp_ddo_code from ifms.rlt_Zp_ddo_map where (zp_ddo_code=(select ddo_code from org_ddo_mst where location_code="+locId+")) or (rept_ddo_code=(select ddo_code from org_ddo_mst where location_code="+locId+")) or (final_ddo_code=(select ddo_code from org_ddo_mst where location_code="+locId+")) or (special_ddo_code=(select ddo_code from org_ddo_mst where location_code="+locId+")))) and (BILL_DELETED is null or BILL_DELETED <> 'Y') and (BILL_DCPS is null or BILL_DCPS <> 'Y') ");
		if((selectedLocCode!=null)&&(selectedLocCode!="")&&(Long.parseLong(selectedLocCode)!=-1)){
			query.append(" and s.loc_id="+selectedLocCode+"");
		}
		query.append(" order by BILL_GROUP_ID");
		logger.info("Query for get getBillNoData is---->>>>"+query.toString());
		Query sqlQuery=hibSession.createSQLQuery(query.toString());										
		resList=sqlQuery.list();
		logger.info("Size of getBillNoData is-by roshan************************--->>>>"+resList.size());
	return resList;
	}
	public long getLocationCode(String billNo) {
		logger.info("hii i m in finding location code for bill number");
		List list = null;
		long locationCode = 0;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select loc_id from mst_Dcps_bill_group where bill_group_id="+billNo+"");
		logger.info("Query for get getBillNoData is---->>>>"+sb.toString());
		Query sqlQuery=session.createSQLQuery(sb.toString());										
		 list = sqlQuery.list();
			
		 if(list!= null && list.size()>0)
		 {
			 locationCode = Long.parseLong(list.get(0).toString());
			 
		 }
		 logger.info("getBillNoData is-by roshan************************--->>>>"+locationCode);
		 return locationCode;
	
		
	
	}
	public List getAllAsstDDOList(String loggedInddoCode, String selectedDDOCodeForLevelTwo) {
		List asstDDO=null;
		logger.info("the ddo code is"+loggedInddoCode);
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select zp.zp_ddo_code, offc.ddo_office,offc.location_code from rlt_zp_ddo_map zp inner join org_ddo_mst offc on offc.ddo_code=zp.zp_ddo_code where ((zp.rept_ddo_code='"+loggedInddoCode+"') or (zp.final_ddo_code='"+loggedInddoCode+"'))");
		if(selectedDDOCodeForLevelTwo !=null){
			sb.append(" and zp.rept_ddo_code="+selectedDDOCodeForLevelTwo+"");		
		}
		sb.append(" order by zp.zp_ddo_code");	
		Query sqlQuery= session.createSQLQuery(sb.toString());
		logger.info("the query is"+sb.toString());
		if(sqlQuery.list()!=null){
			asstDDO=sqlQuery.list();
		}
		return asstDDO;	
	}
	public List getAllReptDDOList(String loggedInddoCode) {
		List reptDDO=null;
		logger.info("the ddo code is"+loggedInddoCode);
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select ddo_code, ddo_office,location_code from org_ddo_mst where ddo_code in(select rept_ddo_code from rlt_zp_ddo_map where final_ddo_code='"+loggedInddoCode+"')");
		Query sqlQuery= session.createSQLQuery(sb.toString());
		logger.info("the query is"+sb.toString());
		if(sqlQuery.list()!=null){
			reptDDO=sqlQuery.list();
		}
		return reptDDO;	
	}
	

	//Ended by roshan
}
