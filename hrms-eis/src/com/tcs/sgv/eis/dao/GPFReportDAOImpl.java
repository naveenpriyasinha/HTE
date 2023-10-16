package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;



public class GPFReportDAOImpl extends GenericDaoHibernateImpl<HrPayPaybill, Long> implements GPFReportDAO
{

	public GPFReportDAOImpl(Class<HrPayPaybill> type) 
	{
		super(type);
	}
	
	@SuppressWarnings("unchecked")
	public List getGpfForClassFourTotalDtls(String BillNo,String month,String year,long locationId,String PFSeries)
	{
		List list = new ArrayList();
		Session hibSession = getSession();
		logger.info("ClassIV**********");
		StringBuffer strQuery = new StringBuffer();
		
		/*strQuery.append("select distinct (gpf.gpfAccNo), emp.empLname ||' '||emp.empFname||' '||emp.empMname, ");
		strQuery.append("paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9583,paybill.allow1149,paybill.adv5055,emp.empDoj,emp.orgUserMst.userId,eisemp.sevarthEmpCode,gpf.pfSeries ");
	
		strQuery.append("from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ");
		strQuery.append("OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd, ");
		strQuery.append("HrEisScaleMst scale,OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head, ");
		strQuery.append("HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde ");
		
		
		strQuery.append("where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId  ");
		strQuery.append(" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId ");

		if( PFSeries != null && PFSeries != "" && !PFSeries.equals("-1"))
		{
			strQuery.append(" and gpf.pfSeries='"+PFSeries+"'");
			
		}
		strQuery.append(" and other.hrEisEmpMst.empId=eisemp.empId and other.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and dsgn.dsgnId=gd.orgDesignationMst.dsgnId ");
		strQuery.append(" and scale.scaleId=sgd.hrEisScaleMst.scaleId and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and userpost.orgPostMstByPostId.postId=payorder.postId");
		strQuery.append(" and orde.orderHeadId=payorder.orderHeadId  and paybill.orgPostMst.postId=payorder.postId  and head.billTypeId=2500337");
		strQuery.append(" and (paybill.deduc9583 <> 0 or paybill.adv5055 <> 0)");
		strQuery.append(" and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)");
		strQuery.append(" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locationId+" and head.hrPayPaybill=paybill.paybillGrpId group by gpf.gpfAccNo,");
		strQuery.append(" emp.empLname ||' '||emp.empFname||' '||emp.empMname,paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9583,paybill.allow1149,paybill.adv5055,emp.empDoj,emp.orgUserMst.userId,eisemp.sevarthEmpCode,gpf.pfSeries");
		strQuery.append(" order by gpf.gpfAccNo");*/

		 
		//Modified
		
		strQuery.append("select distinct (gpf.gpfAccNo), emp.empLname ||' '||emp.empFname||' '||emp.empMname, ");
		strQuery.append("paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9583,paybill.allow1149, ");
		strQuery.append("paybill.adv5055,emp.empDoj,emp.orgUserMst.userId,eisemp.sevarthEmpCode,gpf.pfSeries ");
		
		strQuery.append(" from OrgEmpMst emp,HrEisEmpMst eisemp, ");
		strQuery.append("OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf, ");
		strQuery.append("OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head, ");
		strQuery.append("HrPayPaybill paybill ");
		
		strQuery.append(" where emp.empId=eisemp.orgEmpMst.empId and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId ");
		strQuery.append(" and paybill.orgPostMst.postId=postdtls.orgPostMst.postId and gpf.userId=emp.orgUserMst.userId ");
		strQuery.append(" and eisemp.empId=paybill.hrEisEmpMst.empId and emp.empDoj<'2005-11-01' and emp.orgGradeMst.gradeId=100067 ");
		
		if( PFSeries != null && PFSeries != "" && !PFSeries.equals("-1"))
		{
			strQuery.append(" and gpf.pfSeries='"+PFSeries+"'");
			
		}
		strQuery.append(" and head.billTypeId=2500337 and (paybill.deduc9583 <> 0 or paybill.adv5055 <> 0) ");
		strQuery.append(" and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)");
		strQuery.append(" and bill.dcpsDdoBillGroupId=head.billNo and paybill.cmnLocationMst.locId="+locationId+" ");
		strQuery.append(" and head.hrPayPaybill=paybill.paybillGrpId group by gpf.gpfAccNo,");
		strQuery.append(" emp.empLname ||' '||emp.empFname||' '||emp.empMname,paybill.basic0101,paybill.basic0102,");
		strQuery.append(" paybill.allow0120,paybill.deduc9583,paybill.allow1149,paybill.adv5055,emp.empDoj,");
		strQuery.append(" emp.orgUserMst.userId,eisemp.sevarthEmpCode,gpf.pfSeries");
		strQuery.append(" order by gpf.gpfAccNo");
		
		
		
		Query query1 = hibSession.createQuery(strQuery.toString());
		logger.info("==> getGpfForClassFourTotalDtls query :: "+query1);
		logger.info("==> getGpfForClassFourTotalDtls query :: "+query1.toString());
		list= query1.list();	 	   	    	 			 			 		
		return list;
	}
	@SuppressWarnings("unchecked")
	public List getGpfForOtherThanClassFourTotalDtls(String BillNo,String month,String year,long locationId,String PFSeries)
	{
		List list = new ArrayList();
		Session hibSession = getSession();
		logger.info("Otherthan ClassIV**********");
		StringBuffer strQuery = new StringBuffer();
		
		
		
		strQuery.append(" select distinct (gpf.gpfAccNo), concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),");
		strQuery.append(" paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9780+paybill.deduc1038+paybill.deduc1037+paybill.deduc1039+paybill.deduc1040,paybill.allow1149,paybill.adv5054,emp.empDoj,emp.orgUserMst.userId,eisemp.sevarthEmpCode,gpf.pfSeries ");
		
		//strQuery.append(" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ");
		//strQuery.append(" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd,");
		//strQuery.append(" HrEisScaleMst scale,OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,");
		//strQuery.append(" HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde");
		
		strQuery.append(" from OrgEmpMst emp,HrEisEmpMst eisemp,");
		strQuery.append(" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,");
		strQuery.append(" OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,");
		strQuery.append(" HrPayPaybill paybill");
		
		strQuery.append(" where emp.empId=eisemp.orgEmpMst.empId ");
		strQuery.append(" and paybill.orgPostMst.postId=postdtls.orgPostMst.postId and emp.orgUserMst.userId=gpf.userId ");
		if( PFSeries != null && PFSeries != "" && !PFSeries.equals("-1"))
		{
			strQuery.append("and gpf.pfSeries='"+PFSeries+"'");
		}
		strQuery.append(" and emp.orgGradeMst.gradeId<>100067 and emp.empDoj<'2005-11-01' and paybill.hrEisEmpMst.empId=eisemp.empId and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId");
		strQuery.append(" and head.billTypeId=2500337 ");
		//strQuery.append(" and (paybill.deduc9780 <> 0 or paybill.adv5054 <> 0) ");
		strQuery.append(" and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)");
		strQuery.append(" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locationId+" and head.hrPayPaybill=paybill.paybillGrpId group by gpf.gpfAccNo");
		strQuery.append( " ,concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),");
		strQuery.append(" paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9780+paybill.deduc1038+paybill.deduc1037+paybill.deduc1039+paybill.deduc1040,paybill.allow1149,paybill.adv5054,emp.empDoj,emp.orgUserMst.userId,eisemp.sevarthEmpCode,gpf.pfSeries ");
		strQuery.append(" order by gpf.gpfAccNo");
		
		
		Query query1 = hibSession.createQuery(strQuery.toString());
		logger.info("==> getGpfForOtherThanClassFourTotalDtls query :: "+query1);
		logger.info("==> getGpfForOtherThanClassFourTotalDtls query :: "+query1.toString());
		list= query1.list();	 	   	    	 			 			 		
		return list;
	}
	@SuppressWarnings("unchecked")
	//public List getGpfForClassFour(String BillNo,String month,String year,long locationId,String empUserId)
	
	public List getGpfForClassFour(String BillNo,String month,String year,long locationId,long empUserId)
	{
		
 		List list = new ArrayList();
 		Session hibSession = getSession();
 		
 		/*String HQL_QUERY = " select distinct (gpf.gpfAccNo), concat(emp.empLname,' ',emp.empFname,' ',emp.empMname,'(',eisemp.sevarthEmpCode,')'),";

 		HQL_QUERY+=" paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9583,paybill.allow1149,paybill.adv5055,hrloan.totalInst,hrloan.recoveredInst,emp.empDoj,emp.orgUserMst.userId ";
 		HQL_QUERY+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
 		HQL_QUERY+=" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd,";
 		HQL_QUERY+=" HrEisScaleMst scale,OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
 		HQL_QUERY+=" HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde,HrPayPaybillLoanDtls hrloan";
		
 		HQL_QUERY+=" where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";
 		HQL_QUERY+=" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId ";
		
 		if( PFSeries != null && PFSeries != "" && !PFSeries.equals("-1"))
		{
 			HQL_QUERY += "and gpf.pfSeries='"+PFSeries+"'";
		}
 		
 		HQL_QUERY+=" and other.hrEisEmpMst.empId=eisemp.empId and other.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and dsgn.dsgnId=gd.orgDesignationMst.dsgnId";
 		HQL_QUERY+=" and scale.scaleId=sgd.hrEisScaleMst.scaleId and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and userpost.orgPostMstByPostId.postId=payorder.postId";
 		HQL_QUERY+=" and orde.orderHeadId=payorder.orderHeadId and paybill.orgPostMst.postId=payorder.postId and hrloan.paybillId.id=paybill.id and hrloan.hrLoanAdvMst.loanAdvId=55  and head.billTypeId=2500337 ";
 		HQL_QUERY+=" and emp.orgUserMst.userId in ("+empUserId+") and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
 		HQL_QUERY+=" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locationId+" and head.hrPayPaybill=paybill.paybillGrpId group by gpf.gpfAccNo";
 		HQL_QUERY+= ", concat(emp.empLname,' ',emp.empFname,' ',emp.empMname,'(',eisemp.sevarthEmpCode,')'),";
 		HQL_QUERY+=" paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9583,paybill.allow1149,paybill.adv5055,hrloan.totalInst,hrloan.recoveredInst,emp.empDoj,emp.orgUserMst.userId ";
 		Query query = hibSession.createQuery(HQL_QUERY);*/
		
 		//Modified
 		StringBuffer strQuery = new StringBuffer();
 		strQuery.append("select distinct (gpf.gpfAccNo), emp.empLname ||' '||emp.empFname||' '||emp.empMname, ");
		strQuery.append("paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9583,paybill.allow1149, ");
		strQuery.append("paybill.adv5055,hrloan.totalInst,hrloan.recoveredInst,emp.empDoj,emp.orgUserMst.userId ");
		
		strQuery.append(" from OrgEmpMst emp,HrEisEmpMst eisemp, ");
		strQuery.append("OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf, ");
		strQuery.append("OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head, ");
		strQuery.append("HrPayPaybill paybill,HrPayPaybillLoanDtls hrloan ");
		
		strQuery.append(" where emp.empId=eisemp.orgEmpMst.empId and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId ");
		strQuery.append(" and paybill.orgPostMst.postId=postdtls.orgPostMst.postId and gpf.userId=emp.orgUserMst.userId ");
		strQuery.append(" and eisemp.empId=paybill.hrEisEmpMst.empId and emp.empDoj<'2005-11-01' and emp.orgGradeMst.gradeId=100067 ");
		strQuery.append(" and hrloan.paybillId.id=paybill.id and hrloan.hrLoanAdvMst.loanAdvId=55 and head.billTypeId=2500337 ");
		strQuery.append(" and emp.orgUserMst.userId in ("+empUserId+") ");
		strQuery.append(" and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)");
		strQuery.append(" and bill.dcpsDdoBillGroupId=head.billNo and paybill.cmnLocationMst.locId="+locationId+" ");
		strQuery.append(" and head.hrPayPaybill=paybill.paybillGrpId group by gpf.gpfAccNo,");
		strQuery.append(" emp.empLname ||' '||emp.empFname||' '||emp.empMname,paybill.basic0101,paybill.basic0102,");
		strQuery.append(" paybill.allow0120,paybill.deduc9583,paybill.allow1149,paybill.adv5055,hrloan.totalInst,hrloan.recoveredInst,emp.empDoj,");
		strQuery.append(" emp.orgUserMst.userId ");
		Query query = hibSession.createQuery(strQuery.toString());
 		
 		
         
         
		logger.info("==> getGpfForClassFour query :: "+query);
		logger.info("==> getGpfForClassFour query :: "+query.toString());
         
         list= query.list();	 	   	    	 			 			 		
         
         return list;
    
	}
	@SuppressWarnings("unchecked")
	public List getGpfForOtherThanClassFour(String BillNo,String month,String year,long locationId,long empUserId)
	{

		List list = new ArrayList();
		Session hibSession = getSession();
		

		logger.info("getGpfForOtherThanClassFour  in paybilldaoimpl**********");

		/*String query = " select distinct (gpf.gpfAccNo), concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),";
		query+=" paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9780+paybill.deduc1038+paybill.deduc1037+paybill.deduc1039+paybill.deduc1040,paybill.allow1149,paybill.adv5054,hrloan.totalInst,hrloan.recoveredInst,emp.empDoj,emp.orgUserMst.userId,eisemp.sevarthEmpCode ";


		query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
		query+=" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd,";
		query+=" HrEisScaleMst scale,OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
		query+=" HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde,HrPayPaybillLoanDtls hrloan";

		query+=" where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";
		query+=" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId ";

		query+=" and other.hrEisEmpMst.empId=eisemp.empId and other.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and dsgn.dsgnId=gd.orgDesignationMst.dsgnId";
		query+=" and scale.scaleId=sgd.hrEisScaleMst.scaleId and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and userpost.orgPostMstByPostId.postId=payorder.postId";
		query+=" and orde.orderHeadId=payorder.orderHeadId and paybill.orgPostMst.postId=payorder.postId and hrloan.paybillId.id=paybill.id and hrloan.hrLoanAdvMst.loanAdvId=54 and head.billTypeId=2500337 ";
		query+=" and emp.orgUserMst.userId in ("+empUserId+") and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
		query+=" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locationId+" and head.hrPayPaybill=paybill.paybillGrpId group by gpf.gpfAccNo";
		query+= ", concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),";
		query+=" paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9780+paybill.deduc1038+paybill.deduc1037+paybill.deduc1039+paybill.deduc1040,paybill.allow1149,paybill.adv5054,hrloan.totalInst,hrloan.recoveredInst,emp.empDoj,emp.orgUserMst.userId,eisemp.sevarthEmpCode ";
*/
		
		//Modified
		
		String query = " select distinct (gpf.gpfAccNo), concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),";
		query+=" paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9780+paybill.deduc1038+paybill.deduc1037+paybill.deduc1039+paybill.deduc1040,paybill.allow1149,paybill.adv5054,hrloan.totalInst,hrloan.recoveredInst,emp.empDoj,emp.orgUserMst.userId,eisemp.sevarthEmpCode ";

		
		query+=" from OrgEmpMst emp,HrEisEmpMst eisemp,  ";
		query+=" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf, ";
		query+=" OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
		query+=" HrPayPaybill paybill,HrPayPaybillLoanDtls hrloan";

		query+=" where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=gpf.userId ";
		query+=" and paybill.orgPostMst.postId=postdtls.orgPostMst.postId ";
		query+=" and paybill.hrEisEmpMst.empId=eisemp.empId and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId";
		query+=" and hrloan.paybillId.id=paybill.id and hrloan.hrLoanAdvMst.loanAdvId=54 and head.billTypeId=2500337 ";
		query+=" and emp.orgUserMst.userId in ("+empUserId+") and head.billNo="+BillNo+" and head.month='"+month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
		query+=" and emp.orgGradeMst.gradeId<>100067 and emp.empDoj<'2005-11-01' ";
		
		query+=" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locationId+" and head.hrPayPaybill=paybill.paybillGrpId group by gpf.gpfAccNo";
		query+= ", concat(emp.empLname,' ',emp.empFname,' ',emp.empMname),";
		query+=" paybill.basic0101,paybill.basic0102,paybill.allow0120,paybill.deduc9780+paybill.deduc1038+paybill.deduc1037+paybill.deduc1039+paybill.deduc1040,paybill.allow1149,paybill.adv5054,hrloan.totalInst,hrloan.recoveredInst,emp.empDoj,emp.orgUserMst.userId,eisemp.sevarthEmpCode ";

		//Ended
		
		Query query1 = hibSession.createQuery(query);

		logger.info("==> getGpfForOtherThanClassFour query :: "+query1);
		logger.info("==> getGpfForOtherThanClassFour query :: "+query1.toString());

		list= query1.list();	 	   	    	 			 			 		

		return list;

	}
	//Added by Ravish
	public List getGpfTotalDtls(String BillNo,String month,String year,long locationId, String PFSeries, long loantype)
	{

		List list = new ArrayList();
		Session hibSession = getSession();
		StringBuffer lsbquery = new StringBuffer();
		
		
		lsbquery.append(" select hrpaygpfba3_.GPF_ACC_NO, ");
		lsbquery.append(" orgempmst0_.emp_lname || ' ' || orgempmst0_.emp_fname || ' ' ||");
		lsbquery.append(" orgempmst0_.emp_mname,");
		lsbquery.append(" hrpaypaybi7_.PO ,");
		lsbquery.append(" hrpaypaybi7_.PE ,");
		lsbquery.append(" hrpaypaybi7_.D_PAY ,");
		if(loantype == 54){
			lsbquery.append(" hrpaypaybi7_.GPF_GRP_ABC + hrpaypaybi7_.GPF_IAS +");
			lsbquery.append(" hrpaypaybi7_.GPF_IAS_OTHER + hrpaypaybi7_.GPF_IPS +");
			//lsbquery.append(" hrpaypaybi7_.GPF_IFS + hrpaypaybi7_.GPF_GRP_D,");
			lsbquery.append(" hrpaypaybi7_.GPF_IFS,");
			lsbquery.append(" hrpaypaybi7_.DA_Arr,");
			//lsbquery.append(" hrpaypaybi7_.GPF_ADV_GRP_ABC + hrpaypaybi7_.GPF_ADV_GRP_D,");
			lsbquery.append(" hrpaypaybi7_.GPF_ADV_GRP_ABC,");
		}
		if(loantype == 55){
			lsbquery.append(" hrpaypaybi7_.GPF_GRP_D,");
			lsbquery.append(" hrpaypaybi7_.DA_Arr,");
			lsbquery.append(" hrpaypaybi7_.GPF_ADV_GRP_D,");
		}
		lsbquery.append(" orgempmst0_.emp_doj ,");
		lsbquery.append(" orgempmst0_.user_id ,");
		lsbquery.append(" hreisempms1_.SEVARTH_EMP_CD ,");
		lsbquery.append(" hrpaygpfba3_.pf_series ,");
		lsbquery.append(" (select TOTAL_INST from HR_PAY_PAYBILL_LOAN_DTLS where paybill_id = hrpaypaybi7_.ID and LOAN_TYPE_ID = "+loantype+" )TOTAL_INST,");
		lsbquery.append(" (select RECOVERED_INST from HR_PAY_PAYBILL_LOAN_DTLS where paybill_id = hrpaypaybi7_.ID and LOAN_TYPE_ID = "+loantype+" )RECOVERED_INST, ");                              
		lsbquery.append(" hrpaypaybi7_.ID      ");   
		if(loantype == 55){
			lsbquery.append(" ,hrpaypaybi7_.GPF_D_Arr_Mr, ");
		}
		if(loantype == 54){
			lsbquery.append(" ,hrpaypaybi7_.GPF_ABC_Arr_Mr + hrpaypaybi7_.GPF_IAS_Arr_Mr +hrpaypaybi7_.GPF_IFS_Arr_Mr +hrpaypaybi7_.GPF_D_Arr_Mr , ");
		}
		lsbquery.append(" hrpaypaybi7_.SVNPC_GPF_ARR_DEDU, hrpaypaybi7_.SVNPC_GPF_RECO");
		lsbquery.append(" from org_emp_mst          orgempmst0_,");
		lsbquery.append(" hr_eis_emp_mst       hreisempms1_,");
		lsbquery.append(" hr_pay_gpf_details   hrpaygpfba3_,");
		lsbquery.append(" PAYBILL_HEAD_MPG     paybillhea6_,");
		lsbquery.append(" HR_PAY_PAYBILL       hrpaypaybi7_ ,mst_Dcps_emp dcps ");    
		lsbquery.append(" where orgempmst0_.emp_id = hreisempms1_.emp_mpg_id and");
		lsbquery.append(" orgempmst0_.user_id = hrpaygpfba3_.USER_ID ");
		if( PFSeries != null && PFSeries != "" && !PFSeries.equals("-1"))
		{
			lsbquery.append("and hrpaygpfba3_.pf_Series='"+PFSeries+"'");
		}
		if(loantype == 54)
			lsbquery.append(" and orgempmst0_.grade_id <> 100067 ");
		if(loantype == 55)
			lsbquery.append(" and orgempmst0_.grade_id = 100067 ");
		lsbquery.append(" and orgempmst0_.EMP_ID = dcps.ORG_EMP_MST_ID and dcps.DCPS_OR_GPF='N' and ");
		lsbquery.append(" hrpaypaybi7_.EMP_ID = hreisempms1_.emp_id and");
		lsbquery.append(" paybillhea6_.bill_type_id = 2500337 and");
		lsbquery.append(" paybillhea6_.bill_no = "+BillNo+" and");
		lsbquery.append(" paybillhea6_.PAYBILL_MONTH = '"+month+"' and");
		lsbquery.append(" paybillhea6_.PAYBILL_YEAR = '"+year+"' and");
		lsbquery.append(" (paybillhea6_.approve_flag in (0, 1)) and");
		lsbquery.append(" hrpaypaybi7_.loc_id = "+locationId+" and");
		lsbquery.append(" paybillhea6_.PAYBILL_ID = hrpaypaybi7_.PAYBILL_GRP_ID   ");    
		lsbquery.append(" order by GPF_ACC_NO");
		
		
		logger.info("GPFReportDAOImpl :: getGpfTotalDtls :: lsbquery  :: "+ lsbquery);
		Query queryobj = hibSession.createSQLQuery(lsbquery.toString());

		
		list= queryobj.list();	 	   	    	 			 			 		

		return list;

	}
	//Ended by Ravusg
	
	public List getLoanDetailsForAIS(long loanId,long BillNo,long Month,long year,long locId)
	{
		
		HrLoanEmpDtls hrloanempDtls = new HrLoanEmpDtls();
		HrLoanEmpPrinRecoverDtls printloan = new HrLoanEmpPrinRecoverDtls();
	
 		List list = new ArrayList();
 		Session hibSession = getSession();

 		 String HQL_QUERY = "select distinct printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname||' '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||' '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empMname,";
 		 HQL_QUERY+= "paybillLoan.totalAmt,paybillLoan.recoveredAmt,paybillLoan.totalInst,paybillLoan.recoveredInst"; 
 		 HQL_QUERY+= ",det.orgDesignationMst.dsgnName,printloan.hrLoanEmpDtls.hrEisEmpMst.sevarthEmpCode ";
	 	 if(loanId==64)
	 		HQL_QUERY+=",paybillLoan.paybillId.loan5064";
	 	 else if(loanId==65)
	 		HQL_QUERY+=",paybillLoan.paybillId.loan5065";
	 	
	 	 
	 	 HQL_QUERY+= " ,printloan.hrLoanEmpDtls.voucherNo,printloan.hrLoanEmpDtls.voucherDate ";
	 	 
	 	 HQL_QUERY+= " from PaybillHeadMpg ph,MstDcpsBillGroup bill,HrPayPaybillLoanDtls paybillLoan,HrLoanEmpPrinRecoverDtls printloan, ";
	 	 HQL_QUERY+= " OrgPostDetailsRlt det,HrEisEmpMst eisemp ";
	 	 HQL_QUERY+=" where printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId ";
	 	 HQL_QUERY+=" and det.orgPostMst.postId=paybillLoan.paybillId.orgPostMst.postId and eisemp.empId=paybillLoan.paybillId.hrEisEmpMst.empId and paybillLoan.recoveryType=300428";
	 	 HQL_QUERY+=" and  paybillLoan.paybillId.cmnLocationMst.locId="+locId+" and paybillLoan.hrLoanAdvMst.loanAdvId="+loanId+"";
	 	 HQL_QUERY+=" and paybillLoan.paybillId.paybillGrpId = ";
	 	 HQL_QUERY+=" ph.hrPayPaybill and bill.dcpsDdoBillGroupId=ph.billNo and ph.billNo.dcpsDdoBillGroupId="+BillNo;		 	 
	 	 HQL_QUERY+=" and ph.month="+Month+"";
	 	 HQL_QUERY+=" and ph.year="+year+" and ph.cmnLocationMst.locId="+locId+"";		 	 
	 	 HQL_QUERY+=" and ph.approveFlag in(0,1)";
         Query query = hibSession.createQuery(HQL_QUERY);
         
         logger.info("==> getLoanDetailsForAIS query :: "+query);
         logger.info("==> getLoanDetailsForAIS query :: "+query.toString());
         
         list= query.list();	 	   	    	 			 			 		
         
         return list;
    
	}
	
	public List getLoanDetailsForAISInt(long loanId,long BillNo,long Month,long year,long locId)
	{
		HrLoanEmpDtls hrloanempDtls = new HrLoanEmpDtls();
		HrLoanEmpPrinRecoverDtls printloan = new HrLoanEmpPrinRecoverDtls();
		
 		List list = new ArrayList();
 		Session hibSession = getSession();

 		 String HQL_QUERY = "select distinct paybillLoan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname||' '||paybillLoan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||' '||paybillLoan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empMname,";
 		 HQL_QUERY+= "paybillLoan.totalAmt,paybillLoan.recoveredAmt,paybillLoan.totalInst,paybillLoan.recoveredInst"; 
 		 HQL_QUERY+= ",dsgn.dsgnName,paybillLoan.hrLoanEmpDtls.hrEisEmpMst.sevarthEmpCode ";
	 	 if(loanId==71)
	 		HQL_QUERY+=",paybillLoan.paybillId.loanInt5064";
	 	 else if(loanId==69)
	 		HQL_QUERY+=",paybillLoan.paybillId.loanInt5065";
	 	
	 	HQL_QUERY+=",hrloanemp.voucherNo,hrloanemp.voucherDate";
	 	
	 	
		HQL_QUERY+=" from OrgEmpMst emp,HrEisEmpMst eisemp,";
	 	HQL_QUERY+=" OrgPostDetailsRlt postdtls,";
	 	HQL_QUERY+=" OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
	 	HQL_QUERY+=" HrPayPaybill paybill,HrPayPaybillLoanDtls paybillLoan,";
	 	HQL_QUERY+=" HrLoanEmpDtls hrloanemp";
		
	 	if(loanId==71 || loanId==69) 
	 	HQL_QUERY+=" ,HrLoanEmpIntRecoverDtls printloan ";
	 	
	 	
	 	HQL_QUERY+=" where emp.empId=eisemp.orgEmpMst.empId ";
	 	HQL_QUERY+=" and paybill.orgPostMst.postId=postdtls.orgPostMst.postId ";
	 	HQL_QUERY+=" and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId";
	 	HQL_QUERY+=" and eisemp.empId=paybill.hrEisEmpMst.empId ";
	 	HQL_QUERY+=" and paybillLoan.paybillId.id=paybill.id";
	 	HQL_QUERY+=" and hrloanemp.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and hrloanemp.hrEisEmpMst.empId=eisemp.empId and paybillLoan.hrLoanAdvMst.loanAdvId="+loanId+"";
		
	 	if(loanId==71)
	 		HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
	 	if(loanId==69)
	 		HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
	 	
	 	
	 	HQL_QUERY+=" and head.billTypeId=2500337 and head.billNo="+BillNo+" and head.month='"+Month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
	 	HQL_QUERY+=" and bill.dcpsDdoBillGroupId=head.billNo and paybill.cmnLocationMst.locId="+locId+" and head.hrPayPaybill=paybill.paybillGrpId ";
	 	
	 	//ended
	 	
         Query query = hibSession.createQuery(HQL_QUERY);
         logger.info("==> getLoanDetailsForHBACA query :: "+query);
         logger.info("==> getLoanDetailsForHBACA query :: "+query.toString());
 		list= query.list();	 	   	    	 			 			 		
        return list;
	}
	
	public List getLoanDetailsForHBACA(long loanId,long BillNo,long Month,long year,long locId)
	{
		HrLoanEmpDtls hrloanempDtls = new HrLoanEmpDtls();
		HrLoanEmpPrinRecoverDtls printloan = new HrLoanEmpPrinRecoverDtls();
		
 		List list = new ArrayList();
 		Session hibSession = getSession();

 		 String HQL_QUERY = "select paybillLoan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname||' '||paybillLoan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||' '||paybillLoan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empMname,";
 		 HQL_QUERY+= "paybillLoan.totalAmt,paybillLoan.recoveredAmt,paybillLoan.totalInst,paybillLoan.recoveredInst"; 
 		 HQL_QUERY+= ",dsgn.dsgnName,paybillLoan.hrLoanEmpDtls.hrEisEmpMst.sevarthEmpCode ";
	 	 if(loanId==70)
	 		HQL_QUERY+=",paybillLoan.paybillId.loanInt5056";
	 	 else if(loanId==73)
	 		HQL_QUERY+=",paybillLoan.paybillId.loanInt5067";
	 	 else if(loanId==59)
	 		HQL_QUERY+=",paybillLoan.paybillId.adv5059";
	 	else if(loanId==52)
	 		HQL_QUERY+=",paybillLoan.paybillId.adv5052";
	 	else if(loanId==68)
	 		HQL_QUERY+=",paybillLoan.paybillId.adv5068";
	 	else if(loanId==72)
	 		HQL_QUERY+=",paybillLoan.paybillId.loanInt5051";
	 	else if(loanId==78)
	 		HQL_QUERY+=",paybillLoan.paybillId.loanInt5057";
	 	else 
	 		HQL_QUERY+=",paybillLoan.paybillId.adv5053";
	 	HQL_QUERY+=",hrloanemp.voucherNo,hrloanemp.voucherDate";
	 	
	 	/*
	 	HQL_QUERY+=" from OrgEmpMst emp,HrEisEmpMst eisemp,OrgUserMst userr,OrgUserpostRlt userpost,OrgPostMst post, ";
	 	HQL_QUERY+=" OrgPostDetailsRlt postdtls,HrPayGpfBalanceDtls gpf,HrEisOtherDtls other,HrEisSgdMpg sgd,HrEisGdMpg gd,";
	 	HQL_QUERY+=" HrEisScaleMst scale,OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
	 	HQL_QUERY+=" HrPayPaybill paybill,HrPayOrderHeadPostMpg payorder,HrPayOrderHeadMpg orde,HrPayPaybillLoanDtls paybillLoan,";
	 	HQL_QUERY+=" HrLoanEmpDtls hrloanemp";
		
	 	if(loanId==70 || loanId==73 || loanId==68 || loanId==72) 
	 	HQL_QUERY+=" ,HrLoanEmpIntRecoverDtls printloan ";
	 	
	 	
	 	HQL_QUERY+=" where emp.empId=eisemp.orgEmpMst.empId and emp.orgUserMst.userId=userr.userId and userr.userId=userpost.orgUserMst.userId ";
	 	HQL_QUERY+=" and userpost.orgPostMstByPostId.postId=post.postId and userpost.orgPostMstByPostId.postId=postdtls.orgPostMst.postId and userr.userId=gpf.userId ";
	 	HQL_QUERY+=" and other.hrEisEmpMst.empId=eisemp.empId and other.hrEisSgdMpg.sgdMapId=sgd.sgdMapId and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId and dsgn.dsgnId=gd.orgDesignationMst.dsgnId";
	 	HQL_QUERY+=" and scale.scaleId=sgd.hrEisScaleMst.scaleId and other.hrEisEmpMst.empId=paybill.hrEisEmpMst.empId and userpost.orgPostMstByPostId.postId=payorder.postId";
	 	HQL_QUERY+=" and orde.orderHeadId=payorder.orderHeadId and paybill.orgPostMst.postId=payorder.postId and paybillLoan.paybillId.id=paybill.id";
	 	HQL_QUERY+=" and hrloanemp.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and hrloanemp.hrEisEmpMst.empId=eisemp.empId and paybillLoan.hrLoanAdvMst.loanAdvId="+loanId+"";
		
	 	if(loanId==70)
	 		HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
	 	if(loanId==73)
	 		HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
	 	if(loanId==72)
	 		HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
	 	if(loanId==68)
	 		HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId";
	 	
	 	HQL_QUERY+=" and head.billTypeId=2500337 and userpost.activateFlag=1 and head.billNo="+BillNo+" and head.month='"+Month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
	 	HQL_QUERY+=" and bill.dcpsDdoBillGroupId=head.billNo and postdtls.cmnLocationMst.locId="+locId+" and head.hrPayPaybill=paybill.paybillGrpId ";
	 	 */
	 	
	 	
	 	
	 	
	 	//modified
	 	
		HQL_QUERY+=" from OrgEmpMst emp,HrEisEmpMst eisemp,";
	 	HQL_QUERY+=" OrgPostDetailsRlt postdtls,";
	 	HQL_QUERY+=" OrgDesignationMst dsgn,MstDcpsBillGroup bill,PaybillHeadMpg head,";
	 	HQL_QUERY+=" HrPayPaybill paybill,HrPayPaybillLoanDtls paybillLoan,";
	 	HQL_QUERY+=" HrLoanEmpDtls hrloanemp";
		
	 	if(loanId==70 || loanId==73 || loanId==68 || loanId==72 || loanId==78) 
	 	HQL_QUERY+=" ,HrLoanEmpIntRecoverDtls printloan ";
	 	
	 	
	 	HQL_QUERY+=" where emp.empId=eisemp.orgEmpMst.empId ";
	 	HQL_QUERY+=" and paybill.orgPostMst.postId=postdtls.orgPostMst.postId ";
	 	HQL_QUERY+=" and dsgn.dsgnId=postdtls.orgDesignationMst.dsgnId";
	 	HQL_QUERY+=" and eisemp.empId=paybill.hrEisEmpMst.empId ";
	 	HQL_QUERY+=" and paybillLoan.paybillId.id=paybill.id";
	 	HQL_QUERY+=" and hrloanemp.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and hrloanemp.hrEisEmpMst.empId=eisemp.empId and paybillLoan.hrLoanAdvMst.loanAdvId="+loanId+"";
		
	 	if(loanId==70)
	 		HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
	 	if(loanId==73)
	 		HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
	 	if(loanId==72)
	 		HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId and paybillLoan.recoveryType=300429 ";
	 	if(loanId==68)
	 		HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId";
	 	
	 	HQL_QUERY+=" and head.billTypeId=2500337 and head.billNo="+BillNo+" and head.month='"+Month+"' and head.year='"+year+"' and head.approveFlag in(0,1)";
	 	HQL_QUERY+=" and bill.dcpsDdoBillGroupId=head.billNo and paybill.cmnLocationMst.locId="+locId+" and head.hrPayPaybill=paybill.paybillGrpId ";
	 	if(loanId==70 || loanId==73 || loanId==68 || loanId==72 || loanId==78) 
		 	HQL_QUERY+=" and printloan.hrLoanEmpDtls.empLoanId=hrloanemp.empLoanId";
	 	//ended
	 	
         Query query = hibSession.createQuery(HQL_QUERY);
         logger.info("==> getLoanDetailsForHBACA query :: "+query);
         logger.info("==> getLoanDetailsForHBACA query :: "+query.toString());
 		list= query.list();	 	   	    	 			 			 		
        return list;
	}
	public List getLoanDetailsFor7610(long loanId,long BillNo,long Month,long year,long locId)
	{
		
		HrLoanEmpDtls hrloanempDtls = new HrLoanEmpDtls();
		HrLoanEmpPrinRecoverDtls printloan = new HrLoanEmpPrinRecoverDtls();
	
 		List list = new ArrayList();
 		Session hibSession = getSession();

 		 String HQL_QUERY = "select printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empLname||' '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empFname||' '||printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empMname,";
 		 HQL_QUERY+= "paybillLoan.totalAmt,paybillLoan.recoveredAmt,paybillLoan.totalInst,paybillLoan.recoveredInst"; 
 		 HQL_QUERY+= ",det.orgDesignationMst.dsgnName,printloan.hrLoanEmpDtls.hrEisEmpMst.sevarthEmpCode ";
	 	 if(loanId==58)
	 		HQL_QUERY+=",paybillLoan.paybillId.loan5058";
	 	 else if(loanId==51)
	 		HQL_QUERY+=",paybillLoan.paybillId.loan5051";
	 	 else if(loanId==56)
	 		HQL_QUERY+=",paybillLoan.paybillId.loan5056";
	 	 else if(loanId==67)
		 	HQL_QUERY+=",paybillLoan.paybillId.loan5067";
	 	else if(loanId==57)
	 		HQL_QUERY+=",paybillLoan.paybillId.loan5057";
	 	 HQL_QUERY+= " ,printloan.hrLoanEmpDtls.voucherNo,printloan.hrLoanEmpDtls.voucherDate ";
	 	 
	 	 HQL_QUERY+= " from PaybillHeadMpg ph,MstDcpsBillGroup bill,HrPayPaybillLoanDtls paybillLoan,HrLoanEmpPrinRecoverDtls printloan, ";
	 	 HQL_QUERY+= " OrgPostDetailsRlt det,HrEisEmpMst eisemp ";
	 	 HQL_QUERY+=" where printloan.hrLoanEmpDtls.empLoanId=paybillLoan.hrLoanEmpDtls.empLoanId ";
	 	// HQL_QUERY+=" and up.orgUserMst.userId=printloan.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.orgUserMst.userId";
	 	 HQL_QUERY+=" and det.orgPostMst.postId=paybillLoan.paybillId.orgPostMst.postId and eisemp.empId=paybillLoan.paybillId.hrEisEmpMst.empId and paybillLoan.recoveryType=300428";
	 	 HQL_QUERY+=" and  paybillLoan.paybillId.cmnLocationMst.locId="+locId+" and paybillLoan.hrLoanAdvMst.loanAdvId="+loanId+"";
	 	 HQL_QUERY+=" and paybillLoan.paybillId.paybillGrpId = ";
	 	 HQL_QUERY+=" ph.hrPayPaybill and bill.dcpsDdoBillGroupId=ph.billNo and ph.billNo.dcpsDdoBillGroupId="+BillNo;		 	 
	 	 HQL_QUERY+=" and ph.month="+Month+"";
	 	 HQL_QUERY+=" and ph.year="+year+" and ph.cmnLocationMst.locId="+locId+"";		 	 
	 	 HQL_QUERY+=" and ph.approveFlag in(0,1)";
         Query query = hibSession.createQuery(HQL_QUERY);
         
         logger.info("==> getLoanDetailsFor7610 query :: "+query);
         logger.info("==> getLoanDetailsFor7610 query :: "+query.toString());
         
         list= query.list();	 	   	    	 			 			 		
         
         return list;
    
	}
}