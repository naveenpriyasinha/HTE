package com.tcs.sgv.hr.payincrement.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;

public class EmpPayIncrDAOImpl extends GenericDaoHibernateImpl implements EmpPayIncrDAO{
	Log logger = LogFactory.getLog(getClass());
	@SuppressWarnings("unchecked")
	public EmpPayIncrDAOImpl(Class type,SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	
	public List getEmpPayIncrListByMonthAndYear(Date startDate,Date endDate,long locationId,long langId)
	{
		List IncrList = new ArrayList();
		try
	    {
			logger.info("=====inside DAO========");
			
			Session hibSession = getSession();
			Criteria hrmainCrit = hibSession.createCriteria(HrPayfixMst.class);
			
			hrmainCrit.add(Restrictions.eq("flagType",'Y'));
			hrmainCrit.add(Restrictions.eq("cmnLocationMst.locId",locationId ));
			//hrmainCrit.add(Restrictions.eq("cmnLocationMst.langId",langId ));
			if(startDate!=null)
				hrmainCrit.add(Restrictions.between("nxtIncrDate", startDate, endDate));
			else
			{
				System.out.println("Inside Else:-");
				hrmainCrit.add(Restrictions.le("nxtIncrDate", endDate));
			}
			
			IncrList = hrmainCrit.list();
			
			logger.info("==inside DAO=====Size==="+IncrList.size());
		}catch(Exception e)
	    {
	       
	    }
		return IncrList;
	}
	
	public HrPayfixMst getEmpCurrentPayDtlsByUserId(long userId)
	{
		List EmpNxtIncrLst = new ArrayList();
		HrPayfixMst hrPayfixMst = null;
		try
	    {
			logger.info("=====inside DAO========");
			
			Session hibSession = getSession();
			Criteria hrmainCrit = hibSession.createCriteria(HrPayfixMst.class);
			
			hrmainCrit.add(Restrictions.eq("flagType",'Y'))
					  .add(Restrictions.eq("userId.userId",userId));
			EmpNxtIncrLst = hrmainCrit.list();
			
			if(EmpNxtIncrLst!=null && !EmpNxtIncrLst.isEmpty())
				hrPayfixMst = (HrPayfixMst)EmpNxtIncrLst.get(0);
			
		}catch(Exception e){}
		
		return hrPayfixMst;
	}
	
	public List getEmployeeAllPayDtlsByUserId(long userId)
	{
		List EmpPayList = new ArrayList();
		try
	    {
			logger.info("=====inside DAO========");
			
			Session hibSession = getSession();
			Criteria hrmainCrit = hibSession.createCriteria(HrPayfixMst.class);
			
			hrmainCrit.add(Restrictions.eq("userId.userId",userId));
			
			EmpPayList = hrmainCrit.list();
			
			logger.info("==inside DAO=====Size==="+EmpPayList.size());
		}catch(Exception e){}
		
		return EmpPayList;
	}
	
	public Date getLastIncrDateByUserId(long userId)
	{
		List EmpPayList = new ArrayList();
		List EmpPayListForOneRec = new ArrayList();
		HrPayfixMst hrPayfixMst = new HrPayfixMst();
		Date date=null;
		try
	    {
			
			Session hibSession = getSession();
			Criteria hrmainCrit = hibSession.createCriteria(HrPayfixMst.class);
			Criteria hrmainCritForOneRec = hibSession.createCriteria(HrPayfixMst.class);
			
			/*hrmainCrit.add(Restrictions.eq("userId.userId",userId))
				      .add(Restrictions.eq("flagType",'N'))
			          .addOrder(Order.desc("nxtIncrDate"));
			EmpPayList = hrmainCrit.list();
			
			logger.info("EmpPayList==="+EmpPayList.size());*/
			
			hrmainCritForOneRec.add(Restrictions.eq("userId.userId",userId))
            		  .add(Restrictions.eq("flagType",'Y'));
			EmpPayListForOneRec = hrmainCritForOneRec.list();
			
			logger.info("EmpPayList1==="+EmpPayListForOneRec.size());
			
			/*if (EmpPayList.size() > 0)
			{
				hrPayfixMst = (HrPayfixMst)EmpPayList.get(0);
				date = hrPayfixMst.getNxtIncrDate();
				logger.info("Inside if date==="+date);
			}*/	
			if(EmpPayListForOneRec.size() > 0)
			{
				hrPayfixMst = (HrPayfixMst)EmpPayListForOneRec.get(0);
				date = hrPayfixMst.getPayFixDate();
				logger.info("Inside else date==="+date);
			}
			
		}catch(Exception e){}
		
		return date;
	}
	
	public List getEmpPayIncrListByMonthAndYearAndBillNo(String startDate,String endDate,long billId,String locationCode,long langId)
	{
		List EmpPayList = null;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		sb.append("select pay from HrPayfixMst pay,HrPayPsrPostMpg pp,OrgUserpostRlt up,HrEisOtherDtls od, OrgEmpMst oe");
		sb.append(" where pay.userId.userId=up.orgUserMst.userId and pay.cmnLocationMst.locationCode='"+locationCode+"' and pay.cmnLocationMst.cmnLanguageMst.langId="+langId+" and pp.billNo="+billId+" and pp.postId=up.orgPostMstByPostId.postId and oe.orgUserMst.userId = up.orgUserMst.userId and od.hrEisEmpMst.orgEmpMst.empId=oe.empId and pay.revPay > od.otherCurrentBasic and pay.nxtIncrDate between to_date('"+startDate+"','MM/YYYY') and to_date('"+endDate+"','MM/YYYY') ");
		/*sb.append("   select pay.*  ");
		sb.append("  from hr_pay_post_psr_mpg pp,org_userpost_rlt up,hr_eis_other_dtls od, org_emp_mst oe,hr_payfix_mst pay,hr_eis_other_dtls oth,cmn_location_mst cl ");
		sb.append("  where cl.loc_id= pay.loc_id and cl.location_code= '"+locationCode+"' and cl.lang_id="+langId+" and pp.bill_No = "+billId+" and pp.post_id = up.post_id and up.user_id = oe.user_id and oe.emp_id = od.emp_id and oe.user_id = pay.user_id and oe.emp_id = oth.emp_id and pay.rev_pay > oth.other_current_basic and pay.nxt_incr_date between to_date('"+startDate+"','MM/YYYY') and to_date('"+endDate+"','MM/YYYY')  ");*/
			logger.info("In getEmpPayIncrListByMonthAndYearAndBillNo--Query for getting user id is " + sb.toString());
		    Query query = hibSession.createQuery(sb.toString());
		    EmpPayList = query.list();
		    logger.info("Post List size in OrderHeadMpgDAOImpl from BillNo " + EmpPayList.size());
		return EmpPayList;
	}
	//Added by Jigna on 1st july
	
	
	public List getEmpPayIncrListByMonthAndYearAndBillNoWithoutPayfix(String startDate,String endDate,long billId,String locationCode,long langId)
	{
		List EmpPayList = null;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
        DBsysdateConfiguration sbConf=new DBsysdateConfiguration();

        String firstDate = sbConf.getFirstDateOfCurrentMonth();
		sb.append("select (oe.empFname|| ' ' || oe.empMname || ' ' || oe.empLname) as name1,");
		sb.append("oe.orgUserMst.userId,");
		sb.append("od.otherCurrentBasic as currentpay,");
		sb.append(" case when mod(ceil(od.otherCurrentBasic * 0.03), 10)>0 then");
		sb.append("(od.otherCurrentBasic +  ceil(od.otherCurrentBasic *0.03) - mod(ceil(od.otherCurrentBasic *0.03),10) + 10)" );
		sb.append("else");
		sb.append("(od.otherCurrentBasic +  ceil(od.otherCurrentBasic *0.03) - mod(ceil(od.otherCurrentBasic *0.03),10))" );
		sb.append("end ");
		sb.append("as updatedbasic,");
		sb.append("es.hrEisScaleMst.scaleId,");
		sb.append("sm.scaleStartAmt || '-' ||sm.scaleIncrAmt || '-' || sm.scaleEndAmt as scale");
		
		
		sb.append(" from HrPayPsrPostMpg pp,OrgUserpostRlt up,HrEisOtherDtls od, OrgEmpMst oe,HrEisEmpMst ee,HrEisSgdMpg es,HrEisScaleMst sm");
		sb.append(" where od.hrEisEmpMst.empId=ee.empId and pp.loc_id='"+locationCode+"' and pp.billNo="+billId+" and pp.postId=up.orgPostMstByPostId.postId and oe.orgUserMst.userId = up.orgUserMst.userId and od.hrEisEmpMst.orgEmpMst.empId=oe.empId and oe.empSrvcExp> '").append(firstDate).append("' and es.sgdMapId = od.hrEisSgdMpg.sgdMapId and sm.scaleId = es.hrEisScaleMst.scaleId and (up.endDate is null or up.endDate > '").append(firstDate).append("' ) and (od.incrementDate <= '").append(firstDate).append("' or od.incrementDate is null ) order by pp.psrId");
		/*sb.append("   select pay.*  ");
		sb.append("  from hr_pay_post_psr_mpg pp,org_userpost_rlt up,hr_eis_other_dtls od, org_emp_mst oe,hr_payfix_mst pay,hr_eis_other_dtls oth,cmn_location_mst cl ");
		sb.append("  where cl.loc_id= pay.loc_id and cl.location_code= '"+locationCode+"' and cl.lang_id="+langId+" and pp.bill_No = "+billId+" and pp.post_id = up.post_id and up.user_id = oe.user_id and oe.emp_id = od.emp_id and oe.user_id = pay.user_id and oe.emp_id = oth.emp_id and pay.rev_pay > oth.other_current_basic and pay.nxt_incr_date between to_date('"+startDate+"','MM/YYYY') and to_date('"+endDate+"','MM/YYYY')  ");*/
			logger.info("In getEmpPayIncrListByMonthAndYearAndBillNo--Query for getting user id is " + sb.toString());
		    Query query = hibSession.createQuery(sb.toString());
		    EmpPayList = query.list();
		    logger.info("Post List size in OrderHeadMpgDAOImpl from BillNo " + EmpPayList.size());
		return EmpPayList;
	}
	//End
	public String getBillNoFromBillId(long billId)
	{
		String billNo = "";
		Session hibSession = getSession();
		String qry="select b.bill_no from hr_pay_bill_subhead_mpg b where b.bill_subhead_id ="+billId;
		Query query = hibSession.createSQLQuery(qry.toString());
   		billNo = query.uniqueResult().toString();
		return billNo;
	}
	public boolean deactivateExistingEmployees(String orderNo,long userIdDetach)
	{
		Session hibSession = getSession();
		String qry=null;
		int records=-1;
		
		qry="update HrPayfixMst set activateFlag=0 where incrementOrderNo='"+orderNo+"' and userId="+userIdDetach+" and activateFlag=1";
		
		
		Query query=hibSession.createQuery(qry.toString());
		System.out.println("query--"+query.toString());
		records=(int)query.executeUpdate();
		
		logger.info("Records Updated deactivateExistingEmployees-->"+records);
		
		if(records >= 0)
			return true;
		else
			return false;
					
	}
	
	public boolean updateRemarksWEF(long userId,String wefDate,String remarks)
	{
		Session hibSession = getSession();
		String qry=null;
		int records=-1;
		
		qry="update HrPayfixMst set remarks='"+remarks+"' , payFixDate='"+wefDate+"' where userId="+userId+" and activateFlag=1";
		
		Query query=hibSession.createQuery(qry.toString());
		records=(int)query.executeUpdate();
		
		logger.info("Records Updated updateRemarksWEF-->"+records);
		
		if(records >= 0)
			return true;
		else
			return false;
	}
	
	public boolean verifyOrder(String orderNo)
	{
		Session hibSession = getSession();
		String qry=null;
		int records=-1;
		
		qry="update HrPayfixMst set status='Verified' where incrementOrderNo='"+orderNo+"' and status='Entered' and activateFlag=1";
		
		Query query=hibSession.createQuery(qry.toString());
		records=(int)query.executeUpdate();
		
		logger.info("Records Updated verifyOrder-->"+records);
		
		if(records >= 0)
			return true;
		else
			return false;
	}
	
	public List getEmployeesForParticularOrder(String orderNo)
	{
		
		List payFixList = null;
		Session hibSession = getSession();
		
		String strQuery = "select fix.userId.userId,fix.newBasic,fix.payFixDate from HrPayfixMst fix where  fix.incrementOrderNo='"+orderNo+"' and fix.activateFlag=1";
        Query query = hibSession.createQuery(strQuery);
        payFixList = query.list();
		logger.info("getEmployeesForParticularOrder query"+strQuery.toString());
		
		return payFixList;
	}
	public boolean updateIncrementDetails(long basicSalary,long userId)
	{
		Session hibSession = getSession();
		StringBuffer qry = new StringBuffer(); 
		int records=-1;
		
		qry.append("Update HrEisOtherDtls otherdtls set otherdtls.otherCurrentBasic="+basicSalary+" where ");
		qry.append(" otherdtls.hrEisEmpMst.orgEmpMst.orgUserMst.userId="+userId);
		//qry.append(" and otherdtls.hrEisEmpMst.empId=empmst.empId");
		
		Query query=hibSession.createQuery(qry.toString());
		records=(int)query.executeUpdate();
		logger.info("Records Updated updateIncrementDetails-->"+records);
		
		if(records>0)
			return true;
		else
			return false;
	}
	
	public long getEmpIdFromUserId(long userId)
	{
		Session hibSession = getSession();
		StringBuffer qry = new StringBuffer(); 
		
		qry.append("select empmst.empId from HrEisEmpMst empmst where empmst.orgEmpMst.orgUserMst.userId="+userId);
		
		Query query=hibSession.createQuery(qry.toString());
		long empId=Long.parseLong(query.uniqueResult().toString());
		
		return empId;
	}
	
	public long getOtherIdFromEmpId(long empId)
	{
		Session hibSession = getSession();
		StringBuffer qry = new StringBuffer(); 
		
		qry.append("select otherdtls.otherId from HrEisOtherDtls otherdtls where otherdtls.hrEisEmpMst.empId="+empId);
		
		Query query=hibSession.createQuery(qry.toString());
		
		long otherId=Long.parseLong(query.uniqueResult().toString());
		
		return otherId;
	}
	
	
}
