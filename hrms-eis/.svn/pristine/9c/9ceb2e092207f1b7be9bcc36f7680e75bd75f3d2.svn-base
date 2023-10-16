package com.tcs.sgv.eis.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;//  below thing addded by Tejashreer 

@SuppressWarnings("unchecked")
public class EmployeeIncrementDAOImpl extends GenericDaoHibernateImpl<HrPayfixMst, Long>
{
	public EmployeeIncrementDAOImpl(Class<HrPayfixMst> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getAllDataForPayFixation(long locId)
	{
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("select fix.incrementOrderNo, fix.incrementOrderDate, fix.status, count(fix.userId) ");
		strQuery.append("from HrPayfixMst fix ");
		strQuery.append("where  fix.cmnLocationMst.locId = " + locId + " ");
		strQuery.append("and fix.cmnLanguageMst.langId = 1 ");
		strQuery.append("and fix.activateFlag in (0,1,-1) ");
		strQuery.append("group by fix.incrementOrderNo, fix.incrementOrderDate,fix.status");
		Query query = hibSession.createQuery(strQuery.toString());
		logger.info("******* getAllDataForPayFixation query" + strQuery.toString());
		return query.list();
	}

	public List getEmpNamesAndBasicDtls(long locId, long size, long payCommId, long billNo)
	{
		Session hibSession = getSession();
		/*StringBuffer strQuery = new StringBuffer();
		strQuery.append("select o.empFname, o.empMname, o.empLname, ooo.otherCurrentBasic, o.orgUserMst.userId, scale.scaleEndAmt, ");
		strQuery.append("ooo.incrementDate,scale.scaleIncrAmt, scale.scaleGradePay from OrgEmpMst o, HrEisEmpMst e, OrgUserMst u, OrgPostMst p, ");
		strQuery.append("OrgUserpostRlt uu, OrgPostDetailsRlt pp, HrEisOtherDtls ooo, OrgDesignationMst dd, HrEisSgdMpg sgd, ");
		strQuery.append("HrEisGdMpg gd, HrEisScaleMst scale, HrPayPsrPostMpg postpsr  ");
		strQuery.append("where uu.activateFlag = 1 and o.empId = e.orgEmpMst.empId and o.orgUserMst.userId = u.userId ");
		strQuery.append("and u.userId = uu.orgUserMst.userId and p.postId = uu.orgPostMstByPostId.postId ");
		strQuery.append("and uu.orgPostMstByPostId.postId = pp.orgPostMst.postId and dd.dsgnId = pp.orgDesignationMst.dsgnId ");
		strQuery.append("and e.empId = ooo.hrEisEmpMst.empId and pp.cmnLocationMst.locId = " + locId + " ");
		if (size > 0)
		{
			strQuery.append("and o.orgUserMst.userId not in (select fix.userId from HrPayfixMst fix where ");
			strQuery.append("(fix.activateFlag=1 and year(fix.payFixDate)=2015) or (fix.activateFlag = 0 and fix.nxtIncrDate > sysdate and year(fix.payFixDate)=2015)) ");
		}
		strQuery.append("and sgd.hrEisScaleMst.scaleId = scale.scaleId ");
		strQuery.append("and sgd.hrEisGdMpg.gdMapId = gd.gdMapId and gd.orgDesignationMst = dd.dsgnId and ooo.hrEisSgdMpg.sgdMapId = sgd.sgdMapId ");
		strQuery.append("and scale.hrPayCommissionMst.id = " + payCommId + " ");
		strQuery.append("and postpsr.billNo = " + billNo + " ");
		strQuery.append("and pp.orgPostMst.postId = postpsr.postId ");
		Query query = hibSession.createQuery(strQuery.toString());
		logger.info("******* empBasicList strQuery***************" + strQuery.toString());
		logger.info("******* empBasicList query***************" + query);
		logger.info("******* empBasicList query***************" + query);*/
		
		StringBuffer strQuery = new StringBuffer();
		strQuery.append(" select o.emp_Fname, o.emp_Mname, o.emp_Lname,ooo.OTHER_CURRENT_BASIC,o.USER_ID,scale.SCALE_END_AMT,ooo.INCREMENT_DATE,");
		//Added By Tejashree//
		if (payCommId == 2500347L) {
		     strQuery.append(" scale.scale_incr_amt , scale.SCALE_GRADE_PAY ,scale.SCALE_START_AMT ,ooo.OTHER_SVN_PC_BASIC,emp.grade_pay,emp.SEVARTH_ID ");
		   } else {
			   strQuery.append("scale.SCALE_INCR_AMT,scale.SCALE_GRADE_PAY");
		   }strQuery.append(" from Org_Emp_Mst o ");
		   /*Ended By Tejashree 17/02/2020*/
		strQuery.append(" inner join hr_eis_emp_mst e on e.EMP_MPG_ID=o.EMP_ID inner join mst_dcps_emp emp on emp.ORG_EMP_MST_ID=o.EMP_ID ");
		strQuery.append(" inner join ORG_USERPOST_RLT uu on uu.USER_ID =o.USER_ID and uu.ACTIVATE_FLAG = 1 inner join org_user_mst u on u.user_id=uu.USER_ID ");
		strQuery.append(" inner join HR_EIS_SCALE_MST scale on scale.SCALE_ID=emp.PAYSCALE inner join ORG_POST_MST post on post.POST_ID=uu.POST_ID ");
		strQuery.append(" inner join ORG_POST_DETAILS_RLT postdetails on postdetails.POST_ID=post.POST_ID inner join HR_EIS_OTHER_DTLS ooo on ooo.EMP_ID=e.EMP_ID ");
		strQuery.append(" inner join Org_Designation_Mst desig on desig.DSGN_ID=postdetails.DSGN_ID inner join HR_PAY_POST_PSR_MPG postpsr on postpsr.POST_ID=postdetails.POST_ID ");
		/*strQuery.append(" where post.ACTIVATE_FLAG=1 and uu.ACTIVATE_FLAG=1 and  ");
		strQuery.append(" scale.commission_id = " + payCommId + " and postpsr.bill_No = " + billNo + " and postdetails.loc_Id = " + locId + " ");*/
		/*added by tejashree*/
		if (payCommId == 2500347L) {
		     strQuery.append(" where postdetails.loc_id = " + locId + " and scale.commission_id= 2500341 and  emp.SEVEN_PC_BASIC > 0 ");
		   } else {
		     strQuery.append(" where postdetails.loc_id = " + locId + " and scale.commission_id= " + payCommId + " and  emp.SEVEN_PC_BASIC = 0 ");
		   }
		   strQuery.append(" and postpsr.BILL_NO = " + billNo + " ");
/*Ended By Tejashree 17/02/2020*/
		if (size > 0)
                {
                        strQuery.append("and o.user_Id not in (select fix.user_Id from Hr_Payfix_Mst fix where  ");
                        strQuery.append("(fix.activate_Flag=1 and year(fix.pay_Fix_Date)=2016) or (fix.activate_Flag = 0 and fix.nxt_Incr_Date > sysdate and year(fix.pay_Fix_Date)=2016)) ");
                }
              
                Query query = hibSession.createSQLQuery(strQuery.toString());
                logger.info("******* empBasicList strQuery***************" + strQuery.toString());
                logger.info("******* empBasicList query***************" + query);
                logger.info("******* empBasicList query***************" + query);
		return query.list();
	}

	public List getDataForDuplicateMessage(String orderNo, long locId, long langId)
	{
		Session hibSession = getSession();
		String strQuery = "from HrPayfixMst fix where  fix.incrementOrderNo='" + orderNo + "' and fix.cmnLocationMst.locId=" + locId + " and fix.cmnLanguageMst.langId=" + langId;
		Query query = hibSession.createQuery(strQuery);
		logger.info("*******payfixation query" + strQuery.toString());
		return query.list();
	}

	public boolean deleteRecordsByOrderNumber(String orderNo, Date orderDate)
	{
		Session hibSession = getSession();
		StringBuffer strBuff = new StringBuffer();
		int records = -1;

		strBuff.append("DELETE FROM HrPayfixMst fix WHERE fix.incrementOrderNo = :orderNo and fix.incrementOrderDate = :orderDate ");
		Query query = hibSession.createQuery(strBuff.toString());
		query.setString("orderNo", orderNo);
		query.setDate("orderDate", orderDate);
		records = (int) query.executeUpdate();
		logger.info("Records Updated updateRemarksWEF-->" + records);
		if (records >= 0)
			return true;
		else
			return false;
	}

	public List getEmpNamesAndBasicDtlsByOrderNo(long locId, String IncrementOrderNo)
	{
		List empBasicList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("select o.empFname, ");//0
		strQuery.append("o.empMname, ");//1
		strQuery.append("o.empLname, ");//2
		strQuery.append("ooo.otherCurrentBasic, ");//3
		strQuery.append("o.orgUserMst.userId, ");//4
		strQuery.append("scale.scaleEndAmt, ");//5
		strQuery.append("fix.payFixDate, ");//6
		strQuery.append("fix.remarks, ");//7
		strQuery.append("fix.newBasic, ");//8
		strQuery.append("fix.nxtIncrDate, ");//9
		strQuery.append("ooo.incrementDate, ");//10
		strQuery.append("fix.incrementOrderDate, ");//11
		strQuery.append("fix.status, ");//12
		strQuery.append("fix.payCommissionId, ");//13
		strQuery.append("scale.scaleIncrAmt ");//14
		strQuery.append("from OrgEmpMst o,HrEisEmpMst e,OrgUserMst u,OrgPostMst p,HrPayfixMst fix, ");
		strQuery.append("OrgUserpostRlt uu,OrgPostDetailsRlt pp,HrEisOtherDtls ooo,OrgDesignationMst dd,HrEisSgdMpg sgd, ");
		strQuery.append("HrEisGdMpg gd,HrEisScaleMst scale ");
		strQuery.append("where o.empId=e.orgEmpMst.empId and o.orgUserMst.userId=u.userId and u.userId=uu.orgUserMst.userId and ");
		strQuery.append("p.postId=uu.orgPostMstByPostId.postId and uu.orgPostMstByPostId.postId=pp.orgPostMst.postId and ");
		strQuery.append("dd.dsgnId=pp.orgDesignationMst.dsgnId and e.empId=ooo.hrEisEmpMst.empId and pp.cmnLocationMst.locId=" + locId + " ");
		strQuery.append("and fix.userId=u.userId and fix.activateFlag=1 and o.orgUserMst.userId=fix.userId.userId ");
		strQuery.append("and sgd.hrEisScaleMst.scaleId=scale.scaleId ");
		strQuery.append("and sgd.hrEisGdMpg.gdMapId=gd.gdMapId and gd.orgDesignationMst=dd.dsgnId and ooo.hrEisSgdMpg.sgdMapId=sgd.sgdMapId ");
		strQuery.append("and fix.incrementOrderNo = '" + IncrementOrderNo + "' ");
		Query query = hibSession.createQuery(strQuery.toString());
		empBasicList = query.list();
		logger.info("*******getEmpNamesAndBasicDtls from order number query***************" + strQuery.toString());
		logger.info("*******getEmpNamesAndBasicDtls from order number query***************" + strQuery.toString());

		return empBasicList;
	}

	public List getDetailOfEmployeeByOrderNo(String orderNo, String ddoCodeOfOrder)
	{
		Session hibSession = getSession();
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("SELECT payfix.userId.userId, payfix.newBasic, other , dcps  ");
		strBuff.append("FROM HrEisOtherDtls other, HrPayfixMst payfix, HrEisEmpMst eis, OrgEmpMst emp, MstEmp dcps ");
		strBuff.append("where other.hrEisEmpMst.empId = eis.empId ");
		strBuff.append("and emp.orgUserMst.userId = payfix.userId.userId ");
		strBuff.append("and payfix.incrementOrderNo = :orderNo ");
		strBuff.append("and emp.empId = eis.orgEmpMst.empId ");
		strBuff.append("and dcps.orgEmpMstId = emp.empId ");
		strBuff.append("and payfix.activateFlag = 1 and dcps.ddoCode='"+ddoCodeOfOrder+"' ");
		Query query = hibSession.createQuery(strBuff.toString());
		logger.info("query is PARVEZ AND ASHISH 24 JUlY 2020*************"+strBuff.toString());
		query.setString("orderNo", orderNo);
		return query.list();
	}

	public boolean updateStatusOfHrPayfixMst(String orderNo, String ddoCodeOfOrder)
	{
		Session hibSession = getSession();
		int records = -1;
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("update Hr_Payfix_Mst set status = 'Verified' , activate_Flag = 0 ");
		strBuff.append("where incr_certi_Order_No = :orderNo and activate_Flag = 1 and loc_id=(select location_code from org_ddo_mst where ddo_code='"+ddoCodeOfOrder+"')");
		Query query = hibSession.createSQLQuery(strBuff.toString());
		query.setString("orderNo", orderNo);
		records = (int) query.executeUpdate();
		if (records >= 0)
			return true;
		else
			return false;
	}
/*
	public List getAllBillsByLoc(long locId)
	{
		Session hibSession = getSession();
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("SELECT bill from MstDcpsBillGroup bill where bill.LocId = " + locId + "  ");
		Query query = hibSession.createQuery(strBuff.toString());
		
		return query.list();
		
	}
	*/
	
	public List getAllBillsByLoc(long locId)
	{
		Session hibSession = getSession();
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("SELECT bill from MstDcpsBillGroup bill where bill.LocId = " + locId + " and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y') ORDER BY dcpsDdoBillDescription");
		Query query = hibSession.createQuery(strBuff.toString());
		logger.info("bill No isquery **************************** "+query.toString());
		return query.list();
		
	}

	public List getPayCommissionList()
	{
		Session hibSession = getSession();
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("SELECT comm from HrPayCommissionMst comm ");
		Query query = hibSession.createQuery(strBuff.toString());
		return query.list();
	}

	public List getPrintReportDataFromIncrementOrderNo(long locId, String incrementOrderNo)
	{
		List printReportlist = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("select o.empFname,o.empMname,o.empLname,d.dsgnName,fix.payFixDate,fix.oldBasic,fix.newBasic,fix.nxtIncrDate,fix.incrementOrderDate,fix.payCommissionId ");
	//	strQuery.append("select o.empFname,o.empMname,o.empLname,d.dsgnName,fix.payFixDate,fix.oldBasic,fix.newBasic,fix.nxtIncrDate,fix.incrementOrderDate,(year(fix.payFixDate)-1), (month(fix.payFixDate)), (day(fix.payFixDate))  ");
		strQuery.append("from OrgEmpMst o,HrEisEmpMst e,OrgUserMst u,OrgPostMst p,OrgUserpostRlt uu,OrgPostDetailsRlt post,OrgDesignationMst d,HrEisOtherDtls ooo,HrPayfixMst fix ");
//		strQuery.append("where o.empId=e.orgEmpMst.empId and o.orgUserMst.userId=u.userId and u.userId=uu.orgUserMst.userId and ");
		strQuery.append("where o.empId=e.orgEmpMst.empId and o.orgUserMst.userId=u.userId and u.userId=uu.orgUserMst.userId and uu.activateFlag = 1 and ");
		strQuery.append("p.postId=post.orgPostMst.postId and uu.orgPostMstByPostId.postId=post.orgPostMst.postId and e.empId=ooo.hrEisEmpMst.empId and fix.userId=u.userId ");
		strQuery.append("and post.orgDesignationMst.dsgnId=d.dsgnId and fix.incrementOrderNo='" + incrementOrderNo + "' and post.cmnLocationMst.locId=" + locId + " ");
		Query query = hibSession.createQuery(strQuery.toString());
		printReportlist = query.list();
		logger.info("******* payfixation query" + strQuery.toString());
		return printReportlist;
	}
	public List getOfficeName(long locId)
	{
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT lc.OFF_NAME FROM MST_DCPS_DDO_OFFICE lc " );
		strQuery.append("where lc.LOC_ID="+ locId);
		Query query = hibSession.createSQLQuery(strQuery.toString());
		logger.info("*******payfixation query" + strQuery.toString());
		return query.list();
	}
	//added by vivek for Reporting ddo
	public boolean rejectHrPayfixMst(String orderNo, String ddoCodeOfOrder)
	{
		Session hibSession = getSession();
		int records = -1;
		StringBuffer strBuff = new StringBuffer();
		//strBuff.append("update HrPayfixMst set status = 'Rejected' , activateFlag = 0 ");
		strBuff.append("update Hr_Payfix_Mst set status = 'Rejected' , activate_Flag = -1 ");
		strBuff.append("where incr_certi_Order_No = :orderNo and activate_Flag = 1 and loc_id =(select location_code from org_ddo_mst where ddo_code='"+ddoCodeOfOrder+"') ");
		Query query = hibSession.createSQLQuery(strBuff.toString());
		logger.info("Query is **************"+strBuff.toString());
		query.setString("orderNo", orderNo);
		records = (int) query.executeUpdate();
		if (records >= 0)
			return true;
		else
			return false;
	}
	public List getAllDataForIncrement(long postId, String currYear)
	{
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT  distinct p.INCR_CERTI_ORDER_NO,p.loc_id,p.INCR_ORDER_DATE,p.status,zp.zp_ddo_code,ddo.ddo_office ");
		strQuery.append("FROM RLT_ZP_DDO_MAP zp, ORG_DDO_MST ddo, HR_PAYFIX_MST p  ");
		strQuery.append("where p.LOC_ID = ddo.LOCATION_CODE ");
		strQuery.append("and zp.ZP_DDO_CODE = ddo.DDO_CODE ");
		strQuery.append(" and p.activate_Flag in (0,1,-1) ");
		strQuery.append("and zp.ZP_DDO_CODE = ddo.DDO_CODE ");
		strQuery.append("and zp.REPT_DDO_POST_ID = "+postId+" and year(p.CREATED_DATE)="+currYear+" order by zp.zp_ddo_code");
//		strQuery.append("  group by p.incrementOrderNo, p.incrementOrderDate,p.status");
		Query query =hibSession.createSQLQuery(strQuery.toString()); 
		logger.info("******* getAllDataForPayFixation query" + strQuery.toString());
		return query.list();
	}
	//added by roshan
	public Long getLocationCode(String orderNumber, String ddoCodeOfOrder) {
		logger.info("hii i m in finding location code for bill number");
		List list = null;
		long loc_id = 0;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select loc_id from mst_dcps_emp where org_emp_mst_id in " +
				"(select emp_id from org_emp_mst where user_id in" +
				"(SELECT user_id from hr_payfix_mst where INCR_CERTI_ORDER_NO='"+orderNumber+"' AND loc_id=(select location_code from org_ddo_mst where ddo_code='"+ddoCodeOfOrder+"')))");
		logger.info("Query for get getLocationCode is---->>>>"+sb.toString());
		Query sqlQuery=session.createSQLQuery(sb.toString());										
		 list = sqlQuery.list();
			
		 if(list!= null && list.size()>0)
		 {
			 loc_id = Long.parseLong(list.get(0).toString());
			 
		 }
		 logger.info("getLocationCode is-by roshan************************--->>>>"+loc_id);
		 return loc_id;
	}
	

	public Long getpayCommID(String orderNumber, String ddoCodeOfOrder) {
		logger.info("hii i m in finding getpayCommID code for order number");
		List list = null;
		long payCommId = 0;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select pay_commission_id from hr_payfix_mst where INCR_CERTI_ORDER_NO='"+orderNumber+"' AND loc_id=(select location_code from org_ddo_mst where ddo_code='"+ddoCodeOfOrder+"')");
		logger.info("Query for get getpayCommID is---->>>>"+sb.toString());
		Query sqlQuery=session.createSQLQuery(sb.toString());										
		 list = sqlQuery.list();
			
		 if(list!= null && list.size()>0)
		 {
			 payCommId = Long.parseLong(list.get(0).toString());
			 
		 }
		 logger.info("getpayCommID is-by roshan************************--->>>>"+payCommId);
		 return payCommId;
	}

	public Long getBillNo(String orderNumber, String ddoCodeOfOrder) {
		logger.info("hii i m in finding location code for bill number");
		List list = null;
		long billNo = 0;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select billgroup_id from mst_dcps_emp where org_emp_mst_id in " +
				"(select emp_id from org_emp_mst where user_id in" +
				"(SELECT user_id from hr_payfix_mst where INCR_CERTI_ORDER_NO='"+orderNumber+"' AND loc_id=(select location_code from org_ddo_mst where ddo_code='"+ddoCodeOfOrder+"')))");
		logger.info("Query for get getBillNo is---->>>>"+sb.toString());
		Query sqlQuery=session.createSQLQuery(sb.toString());										
		 list = sqlQuery.list();
			
		 if(list!= null && list.size()>0)
		 {
			 billNo = Long.parseLong(list.get(0).toString());
			 
		 }
		 logger.info("getBillNo is-by roshan************************--->>>>"+billNo);
		 return billNo;
	}

	public String getOrderDate(String orderNumber, String ddoCodeOfOrder) {
		logger.info("hii i m in finding getpayCommID code for order number");
		List list = null;
		String orderDate = null;
		try{
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select incr_order_date from hr_payfix_mst where INCR_CERTI_ORDER_NO='"+orderNumber+"' AND loc_id=(select location_code from org_ddo_mst where ddo_code='"+ddoCodeOfOrder+"')");
		logger.info("Query for get getpayCommID is---->>>>"+sb.toString());
		Query sqlQuery=session.createSQLQuery(sb.toString());										
		 list = sqlQuery.list();
			
		 if(list!= null && list.size()>0)
		 {
			 orderDate = list.get(0).toString();
			 
		 }
		 logger.info("orderDate is-by roshan************************--->>>>"+orderDate);
		 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return orderDate;
	}

	public List<HrPayfixMst> getAllDataForEditIncrement(Long lngPostId,
			String orderNumber) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT  distinct p.INCR_CERTI_ORDER_NO,p.loc_id,p.INCR_ORDER_DATE,p.status ");
		strQuery.append("FROM RLT_ZP_DDO_MAP zp, ORG_DDO_MST ddo, HR_PAYFIX_MST p  ");
		strQuery.append("where p.LOC_ID = ddo.LOCATION_CODE ");
		strQuery.append("and zp.ZP_DDO_CODE = ddo.DDO_CODE ");
		strQuery.append("and p.activate_Flag in (0,1,-1) ");
		strQuery.append("and zp.ZP_DDO_CODE = ddo.DDO_CODE and p.incr_certi_order_no='"+orderNumber+"' ");
		strQuery.append("and zp.REPT_DDO_POST_ID = "+lngPostId);
//		strQuery.append("  group by p.incrementOrderNo, p.incrementOrderDate,p.status");
		Query query =hibSession.createSQLQuery(strQuery.toString()); 
		logger.info("******* getAllDataForPayFixation query" + strQuery.toString());
		return query.list();
	}

	public void UpdateLocCode(String orderNo, Long locationCodeOfLevel1) {
		Session hibSession = getSession();
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("update HR_PAYFIX_MST set LOC_ID="+locationCodeOfLevel1+" where INCR_CERTI_ORDER_NO='"+orderNo+"' ");
		Query query = hibSession.createSQLQuery(strBuff.toString());
		int u=query.executeUpdate();
		logger.info("query is"+strBuff.toString());
		logger.info("count is "+u);
	}

	public List getAllBillsByDDO(String ddoCodeOfOrder) {
		Session hibSession = getSession();
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("SELECT bill from MstDcpsBillGroup bill where bill.dcpsDdoCode = '" + ddoCodeOfOrder + "' and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y') ORDER BY dcpsDdoBillDescription");
		Query query = hibSession.createQuery(strBuff.toString());
		logger.info("bill No isquery **************************** "+query.toString());
		logger.info("bill No size is *************************** "+query.toString());
		return query.list();
	}

	public String getStatusOfDist(long locationId) {
		Session hibSession = getSession();
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("SELECT cast(Allowed_or_not as varchar(5)) from cmn_district_mst where district_id in (select district from mst_dcps_ddo_office where loc_id="+locationId+")");
		Query query = hibSession.createSQLQuery(strBuff.toString());
		logger.info("getStatusOfDist isquery **************************** "+query.toString());
		logger.info("getStatusOfDist size is *************************** "+query.list().size());
		return  (String) query.list().get(0);
	}
	/*Added By Tejashree 17/02/2020*/
	public List getBasicAsPerMatrix(String gradeId, Long newBasicAccToCal)
	  {
	    List newBasicAsPErMat = null;
	    Session hibSession = getSession();
	    StringBuffer queryBuffer = new StringBuffer();
	    queryBuffer.append("SELECT " + gradeId + " FROM MST_state_MATRIX_7THPAY where  " + gradeId + " > " + newBasicAccToCal + " and " + gradeId + " > 0 order by S_1 FETCH FIRST 1 ROWS only ");
	    Query query = hibSession.createSQLQuery(queryBuffer.toString());
	    this.logger.error("Query for getNEwBasicAsPerMAtrix :: " + queryBuffer.toString());
	    if ((query.list() != null) && (query.list().size() > 0) && (query.list().get(0) != null)) {
	      newBasicAsPErMat = query.list();
	    }
	    return newBasicAsPErMat;
	  }
	 public List getNEwBasicAsPerMAtrix(String gradeId, Long newBasicAccToCal)
	  {
	    List newBasicAsPErMat = null;
	    Session hibSession = getSession();
	    StringBuffer queryBuffer = new StringBuffer();
	    //queryBuffer.append("SELECT " + gradeId + " FROM MST_MATRIX_7THPAY where  " + gradeId + " > " + newBasicAccToCal + " and " + gradeId + " > 0 order by GRADE_1 FETCH FIRST 1 ROWS only ");
	    queryBuffer.append("SELECT " + gradeId + " FROM MST_STATE_MATRIX_7THPAY where  " + gradeId + " > " + newBasicAccToCal + " and " + gradeId + " > 0 order by S_1 FETCH FIRST 1 ROWS only ");
	    Query query = hibSession.createSQLQuery(queryBuffer.toString());
	    this.logger.error("Query for getNEwBasicAsPerMAtrix :: " + queryBuffer.toString());
	    if ((query.list() != null) && (query.list().size() > 0) && (query.list().get(0) != null)) {
	      newBasicAsPErMat = query.list();
	    }
	    return newBasicAsPErMat;
	  }
	 public boolean deleteRecordsByOrderNumber(String orderNo, Date orderDate, long locId)
	  {
	    Session hibSession = getSession();
	    StringBuffer strBuff = new StringBuffer();
	    int records = -1;
	    strBuff.append("DELETE FROM HrPayfixMst fix WHERE fix.incrementOrderNo = :orderNo and fix.incrementOrderDate = :orderDate and fix.cmnLocationMst.locId = :locID ");
	    Query query = hibSession.createQuery(strBuff.toString());
	    query.setString("orderNo", orderNo);
	    query.setDate("orderDate", orderDate);
	    query.setLong("locID", locId);
	    records = query.executeUpdate();
	    this.logger.info("Records Updated updateRemarksWEF-->" + records);
	    if (records >= 0) {
	      return true;
	    }
	    return false;
	  }
public boolean getExistingCount(String orderNo, Date incrOrderDt, Long locId)
  {
    Boolean lBlCount = Boolean.valueOf(false);
    List lLstDataEntry = null;
    Session ghibSession = getSession();
    this.logger.error("getExistingCount");
    this.logger.error("orderNo" + orderNo);
    this.logger.error("incrOrderDt" + incrOrderDt);
    this.logger.error("locId" + locId);
    try
    {
      StringBuilder lSBQuery = new StringBuilder();
      lSBQuery.append(" SELECT * FROM HR_PAYFIX_MST fix WHERE fix.INCR_CERTI_ORDER_NO =:orderNo and fix.INCR_ORDER_DATE =:orderDate and fix.LOC_ID =:locID ");
      Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
      lQuery.setString("orderNo", orderNo);
      lQuery.setDate("orderDate", incrOrderDt);
      lQuery.setLong("locID", locId.longValue());
      lLstDataEntry = lQuery.list();
      if ((lLstDataEntry != null) && (lLstDataEntry.size() > 0)) {
        lBlCount = Boolean.valueOf(true);
      } else {
        lBlCount = Boolean.valueOf(false);
      }
      this.logger.error("lBlCount" + lBlCount);
    }
    catch (Exception e)
    {
      this.logger.error("Exception in getExistingCount  : ", e);
    }
    return lBlCount.booleanValue();
  }
public List getEmpNamesAndBasicDtlsByOrderNo(long locId, String IncrementOrderNo, long payCommId)
{
  List empBasicList = null;
  Session hibSession = getSession();
  StringBuffer strQuery = new StringBuffer();
  strQuery.append("select o.empFname, ");
  strQuery.append("o.empMname, ");
  strQuery.append("o.empLname, ");
  if (payCommId == 2500347L) {
    strQuery.append("ooo.otherCurrentSevenBasic, ");
  } else {
    strQuery.append("ooo.otherCurrentBasic, ");
  }
  strQuery.append("o.orgUserMst.userId, ");
  strQuery.append("scale.scaleEndAmt, ");
  strQuery.append("fix.payFixDate, ");
  strQuery.append("fix.remarks, ");
  strQuery.append("fix.newBasic, ");
  strQuery.append("fix.nxtIncrDate, ");
  strQuery.append("ooo.incrementDate, ");
  strQuery.append("fix.incrementOrderDate, ");
  strQuery.append("fix.status, ");
  strQuery.append("fix.payCommissionId, ");
  if (payCommId == 2500347L) {
    strQuery.append("scale.scaleIncrAmt,scale.scaleStartAmt,emp.gradePay,emp.sevarthId ");
  } else {
    strQuery.append("scale.scaleIncrAmt ");
  }
  strQuery.append("from OrgEmpMst o,HrEisEmpMst e,HrPayfixMst fix, ");
  strQuery.append("OrgUserpostRlt uu,OrgPostDetailsRlt pp,HrEisOtherDtls ooo,HrEisSgdMpg sgd, ");
  if (payCommId == 2500347L)
  {
    strQuery.append("HrEisGdMpg gd,HrEisScaleMst scale,MstEmp emp ");
    strQuery.append("where o.empId=e.orgEmpMst.empId and o.orgUserMst.userId = uu.orgUserMst.userId and o.empId = emp.orgEmpMstId and ");
  }
  else
  {
    strQuery.append("HrEisGdMpg gd,HrEisScaleMst scale ");
    strQuery.append("where o.empId=e.orgEmpMst.empId and o.orgUserMst.userId = uu.orgUserMst.userId and ");
  }
  strQuery.append("  uu.orgPostMstByPostId.postId=pp.orgPostMst.postId and ");
  strQuery.append(" e.empId=ooo.hrEisEmpMst.empId and pp.cmnLocationMst.locId=" + locId + " ");
  strQuery.append("and fix.userId=o.orgUserMst.userId and o.orgUserMst.userId=fix.userId.userId ");
  strQuery.append("and sgd.hrEisScaleMst.scaleId=scale.scaleId ");
  strQuery.append("and sgd.hrEisGdMpg.gdMapId=gd.gdMapId and gd.orgDesignationMst= pp.orgDesignationMst.dsgnId and ooo.hrEisSgdMpg.sgdMapId=sgd.sgdMapId ");
  if (payCommId == 2500347L) {
    strQuery.append("and uu.activateFlag = 1 and fix.incrementOrderNo = '" + IncrementOrderNo + "' and ooo.otherCurrentSevenBasic > 0 ");
  } else {
    strQuery.append("and uu.activateFlag = 1 and fix.incrementOrderNo = '" + IncrementOrderNo + "' and ooo.otherCurrentSevenBasic = 0 ");
  }
  Query query = hibSession.createQuery(strQuery.toString());
  empBasicList = query.list();
  this.logger.info("*******getEmpNamesAndBasicDtls from order number query***************" + strQuery.toString());
  this.logger.info("*******getEmpNamesAndBasicDtls from order number query***************" + strQuery.toString());
  return empBasicList;
}
	/*Ended By Tejashree*/
}
