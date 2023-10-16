package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtlsHst;
import com.tcs.sgv.eis.valueobject.HrPayArrearPaybill;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class ArrearBillDaoImpl extends GenericDaoHibernateImpl<HrPayArrearPaybill, Long> {
	
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
    ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");
	
    
    //Added By Varun
    public final int createFlag=Integer.parseInt(payrollBundle.getString("created")); 
	public final int approveFlag=Integer.parseInt(payrollBundle.getString("approved"));
	
	public final int arrearbillTypeId=Integer.parseInt(payrollBundle.getString("arrearbillTypeId"));//arrear bill type id is 2500338
	public final int bill_type_id=Integer.parseInt(payrollBundle.getString("paybillTypeId"));//arrear bill type id is 2500337
	
	
	public ArrearBillDaoImpl(Class<HrPayArrearPaybill> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }	

	public List getArrearBillData(int month,int year,String category,String Grade,String dsgnId,String subHeadId,long langId,String givenBillNo)
	{
		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
	    long AISGradeCode=Long.parseLong(constantsBundle.getString("AISGradeCode"));
	    long GradeCode3=Long.parseLong(constantsBundle.getString("GradeCode3"));
	    long GradeCode4=Long.parseLong(constantsBundle.getString("GradeCode4"));
	    long GradeCode1=Long.parseLong(constantsBundle.getString("GradeCode1"));
	    long GradeCode2=Long.parseLong(constantsBundle.getString("GradeCode2"));

	    if(category.equals("AIS"))
	        Grade=AISGradeCode+"";
	        else if(category.equals("Gadgeted"))
	            Grade=GradeCode1+"','"+GradeCode2;
	        else if(category.equals("Non-Gadgeted"))
	            Grade=GradeCode3+"','"+GradeCode4;
		
		List paySlipList = null;
		Session hibSession = getSession();
		StringBuilder strQuery=new StringBuilder();
		
		/*strQuery.append(" select *  from hr_pay_payslip ps, org_emp_mst o ");
		//Added By Paurav for checking employees of particular designation
		if(dsgnId!=null && !dsgnId.equals(""))
		{
			strQuery.append(", org_userpost_rlt usrpst, org_post_details_rlt pst ");
		}
		//Ended By Paurav
		strQuery.append(" where ps.emp_id = o.emp_id "); 
		if(dsgnId!=null && !dsgnId.equals(""))
		{
			strQuery.append(" and o.user_id = usrpst.user_id and pst.post_id = usrpst.post_id ");
			strQuery.append(" and usrpst.activate_flag = 1 and pst.dsgn_id in (").append(dsgnId).append(") ");
		}
        if(Grade!=null&&!Grade.equals("")&&!Grade.equals("-1"))
            strQuery.append(" and o.grade_id in ('"+Grade+"') ");
        strQuery.append(" and ps.paybill_id in (select p.id from hr_pay_paybill p where p.paybill_grp_id in ");
        strQuery.append("   (select ph.paybill_id  from paybill_head_mpg ph ");
        if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
        	strQuery.append(" 	where ph.subhead_id = "+subHeadId+"	");
        strQuery.append(" )) and ps.month = "+month+" and ps.year = "+year+" ");	
        
        //Added By Paurav for checking lang id
        strQuery.append(" and o.lang_id = ").append(langId);
        if(dsgnId!=null && !dsgnId.equals("")) strQuery.append(" and pst.lang_id = ").append(langId);
        //Ended By Paurav */ 
        
		//Added by Paurav. Query Changed by Paurav to directly get the list of employees not in payslip
		strQuery.append(" SELECT  ab.id  FROM  OrgEmpMst o, HrPayPaybill pb, HrPayArrearPaybill ab, PaybillHeadMpg phm ");
		if(dsgnId!=null && !dsgnId.equals(""))
		{
			strQuery.append(", OrgPostDetailsRlt pst, OrgUserpostRlt usrpst ");
		}
		
		strQuery.append(" WHERE pb.hrEisEmpMst.empId = o.empId ");
		
		strQuery.append(" AND phm.approveFlag = 0 and phm.hrPayPaybill = ab.paybillGrpId and pb.id = ab.paybillId.id");//only unapproved records NOT rejected or deleted
		
		if(dsgnId!=null && !dsgnId.equals(""))
		{
			strQuery.append("AND o.orgUserMst.userId = usrpst.orgUserMst.userId and pst.orgPostMst.postId = usrpst.orgPostMstByPostId.postId ");
			strQuery.append(" AND usrpst.activateFlag = 1 and pst.orgDesignationMst.dsgnId in (").append(dsgnId).append(") ");
		}
		
		if(Grade!=null&&!Grade.equals("")&&!Grade.equals("-1"))
			strQuery.append(" AND o.orgGradeMst.gradeId in('").append(Grade).append("') "); 
		
		if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
		{	
			strQuery.append(" AND pb.paybillGrpId in (SELECT DISTINCT ph.hrPayPaybill  FROM PaybillHeadMpg ph ");
			strQuery.append(" where ph.sgvaBudsubhdMst.budsubhdId = '").append(subHeadId).append("') ");
		}
		
		if(givenBillNo!=null&&!givenBillNo.equals("")&&!givenBillNo.equals("-1"))
		{	
			strQuery.append("  and pb.paybillGrpId in (SELECT b.hrPayPaybill from PaybillBillregMpg b,TrnBillRegister t ");
			strQuery.append("  where t.billNo = b.trnBillRegister ");
			strQuery.append("  and t.billNo ="+ givenBillNo + " )  ");
		
		}
		
		//comment: now paybillheadmpg will be used of year and month
		strQuery.append(" and phm.month = '").append(month).append("' and phm.year = '").append(year).append("' ");
		//comment: paybillheadmpg.bill_type_id will differentiate the bill type i.e. arrear or paybill
		strQuery.append(" and phm.billTypeId.lookupId = '").append(arrearbillTypeId).append("'");
		
		strQuery.append(" and o.cmnLanguageMst.langId = 1 ");
		
		if(dsgnId!=null && !dsgnId.equals("")) 
			strQuery.append(" and pst.cmnLanguageMst.langId = 1 ");
		
        logger.info("Query for getting unapproved from arrearbill for arrear bill  is \n " + strQuery.toString());
        
        Query query = hibSession.createQuery(strQuery.toString());
        paySlipList = query.list();		
		return paySlipList;
	}
	
	
	public List getArrearBillData()
	{
		List paySlipList = null;
		Session hibSession = getSession();
		StringBuilder strQuery=new StringBuilder();
		
        
		//Added by Paurav. Query Changed by Paurav to directly get the list of employees not in payslip
		strQuery.append("select ab.billNo,max(ab.paybillGrpId),max(ab.month),max(ab.year)," +
				"sum(ab.grossAmt),sum(ab.netTotal),max(ab.salRevId) from HrPayArrearPaybill ab group by ab.billNo,ab.salRevId");
		
		
		
		
		
        logger.info("Query for getting bill from  arrearbill  is \n " + strQuery.toString());
        
        Query query = hibSession.createQuery(strQuery.toString());
        paySlipList = query.list();		
		return paySlipList;
	}
	
	public List isArrearPaid(int month,int year,long arrearId,long paybillEmpId,long paybillPostId,long billTypeId)
	{
		
		long approved=Long.parseLong(payrollBundle.getString("approved"));
		long created=Long.parseLong(payrollBundle.getString("created"));

		List paySlipList = null;
		Session hibSession = getSession();
		
		String strQuery = "from HrPayArrearPaybill as bill ,PaybillHeadMpg as ph where bill.salRevId.salRevId = "+arrearId+" and bill.paybillId.id = ph.hrPayPaybill and ph.month=" +
				month+" and ph.year = "+year + " and ph.approveFlag in ("+approved+","+created+") and " +
						" bill.hrEisEmpMst.empId = "+paybillEmpId+" and bill.orgPostMst.postId = "+paybillPostId +" and ph.billTypeId.lookupId = "+billTypeId;
		
		Query sqlQry = hibSession.createQuery(strQuery);
			logger.info("The query from HrPayArrearbill is---->>>"+sqlQry);
	    paySlipList = sqlQry.list();
		
		return paySlipList;
	}
	
	//Added By Varun
	
	public List getArrearBillList(long paybillgrpId)
	{
		List arrearBilllist = null;
		Session hibSession = getSession();
		StringBuilder strQuery=new StringBuilder();		
		
		strQuery.append(" select paybill,phm,billRegMpg,(select proof.proofNum from HrEisProofDtl proof where  proof.orgPostMstByUserId.userId = paybill.hrEisEmpMst.orgEmpMst.orgUserMst.userId) from HrPayArrearPaybill paybill,PaybillHeadMpg phm,PaybillBillregMpg billRegMpg where" +
				" paybill.paybillGrpId in ( select distinct(phm.hrPayPaybill) from PaybillHeadMpg phm where phm.approveFlag in " +
				"("+createFlag+","+approveFlag+") ");
		
		strQuery.append(" and phm.hrPayPaybill = "+paybillgrpId+" and phm.billTypeId.lookupId = "+arrearbillTypeId +") " +
				"and phm.hrPayPaybill = paybill.paybillId.paybillGrpId and phm.orgGradeMst.gradeId = paybill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId ");
		
		strQuery.append(" and billRegMpg.hrPayPaybill = phm.hrPayPaybill order by paybill.psrNo,paybill.orgPostMst.postId,phm.year,phm.month");
		
        logger.info("Query for Paybill Date from  arrearbill  is \n " + strQuery.toString());
        
        Query query = hibSession.createQuery(strQuery.toString());
        arrearBilllist = query.list();		
		return arrearBilllist;
	}
	
	public List getTrnBillRegister(long billNo)
	{
		List trnBillRegList = null;
		Session hiSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("  from TrnBillRegister f where f.billNo="+billNo);
		
		logger.info("Query For TrnBillRegester"+strQuery.toString());
		Query query = hiSession.createQuery(strQuery.toString());
		trnBillRegList = query.list();
		
		return trnBillRegList;
	}
	
	
	public List getPaybillGrpIdFromTrnBillRstr(long billNo)
	{
		List trnBillRegList = null;
		Session hiSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append(" select arrearBill from HrPayArrearPaybill arrearBill where arrearBill.paybillGrpId in"+
				" (select k.hrPayPaybill from  PaybillBillregMpg k where k.trnBillRegister.billNo in"
				+" (select f.billNo from TrnBillRegister f where f.billNo = '"+billNo+
						"') ) and arrearBill.paybillGrpId in (select distinct(phm.hrPayPaybill) from PaybillHeadMpg phm where phm.approveFlag in ("+createFlag+","+approveFlag+")  " +
						
						
								"and phm.billTypeId.lookupId = "+arrearbillTypeId+")");
		
		logger.info("Query For TrnBillRegester"+strQuery.toString());
		Query query = hiSession.createQuery(strQuery.toString());
		trnBillRegList = query.list();
		
		return trnBillRegList;
	}
	
	
	public List getPaybillHeadMpgFromTrnBillRstr(long billNo)
	{
		List trnBillRegList = null;
		Session hiSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		
		strQuery.append(" select phm1 from  PaybillHeadMpg phm1,HrPayPaybill paybill where phm1.hrPayPaybill = paybill.paybillGrpId   and phm1.billTypeId.lookupId = "+bill_type_id +" and paybill.id in ( ");
		
		strQuery.append(" select arrearBill.paybillId.id from HrPayArrearPaybill arrearBill,PaybillHeadMpg phm where arrearBill.paybillGrpId in (select k.arrearBillId.id from  PaybillBillregMpg k where k.trnBillRegister.billNo in (select f.billNo from TrnBillRegister f where f.billNo = '"+billNo+
						"') ) and phm.approveFlag in ("+createFlag+","+approveFlag+") and phm.hrPayPaybill = arrearBill.paybillGrpId and phm.billTypeId.lookupId = "+arrearbillTypeId);
		
		strQuery.append(" )");
		
		logger.info("Query For TrnBillRegester"+strQuery.toString());
		Query query = hiSession.createQuery(strQuery.toString());
		trnBillRegList = query.list();
		
		return trnBillRegList;
	}
	
	public long getPsrNo(long billNo,long postId,long locId)
	{
		List dataList = null;
		long psrNo=0;
		Session hiSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		
		strQuery.append(" select  psr.id,psr.psr_no from hr_pay_post_psr_mpg psr where psr.post_id ="+postId+" and psr.bill_no="+billNo+" and psr.loc_id="+locId);
		
		
		logger.info("Query For getPsrNo"+strQuery.toString());
		Query query = hiSession.createSQLQuery(strQuery.toString());
		dataList = query.list();
		if(dataList!=null && dataList.size()==1)
		{
			Object[] row=(Object[])dataList.get(0);
			psrNo = Long.parseLong(row[1].toString());
		}
			
		return psrNo;
	}
	
 	public Set getUserPostRltForEmp( OrgUserMst orgUserMst,long postId,CmnLocationMst cmnLocationMst)
    {
 		HrEisOtherDtlsHst hrOtherInfo = new HrEisOtherDtlsHst();
        Session hibSession = getSession();
        Set userPostRltSet = new HashSet(0);        
        
        String query1 = "select up from OrgUserpostRlt as up,OrgPostDetailsRlt as pd where up.orgUserMst.userId = "+orgUserMst.getUserId()+
        " and up.orgPostMstByPostId.postId = pd.orgPostMst.postId and pd.orgPostMst.postId = "+postId+" and pd.cmnLocationMst.locId = '"+cmnLocationMst.getLocId()+
        "'  order by up.startDate desc";
        Query sqlQuery1 = hibSession.createQuery(query1);
        
        List userPostList = sqlQuery1.list();        
        
        logger.info("query is----"+sqlQuery1);
        
        if(userPostList!=null && userPostList.size()>0)
        {
        	for(int j=0;j<1;j++)
        	{
        		OrgUserpostRlt orgUserPostRlt = (OrgUserpostRlt)userPostList.get(j);
        		userPostRltSet.add(orgUserPostRlt);
        		
        	}
        }
        
        return userPostRltSet;
    }
	
	
}
