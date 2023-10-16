package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.*;

public class MapEmpWithArrearDAOImpl extends GenericDaoHibernateImpl implements MapEmpWithArrearDAO{

	public MapEmpWithArrearDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	public List getSalRevOrderData(long locId)
	{	
		Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrPaySalRevMst.class);	        
        objCrt.add(Restrictions.like("cmnLocationMst.locId", locId));
        objCrt.addOrder(Order.desc("salRevId") );
        return objCrt.list();
	}
	// Following query is with respect to employee's paybill exists
	public List getEmployeeByBillNoDesig(String billSubHeadId,long dsgnId,long locId,String orderEffDate,String orderEffEndDate)
	{
		List empList = new ArrayList();
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
				
		Date tempDate = new Date();
		try
		{
		tempDate = StringUtility.convertStringToDate(orderEffEndDate);
		
		Calendar cal = Calendar.getInstance();        		
		cal.setTime(tempDate);
        cal.add(Calendar.MONTH,1);		
        Date toDt=cal.getTime();
        SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
		
        DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	    SimpleDateFormat sdf = sbConf.GetDateFormat();
	    String dtOrderStart = sdf.format(fmt.parse(orderEffDate));
	    String dtOrderEnd = sdf.format(toDt);
	    //Getting first day and last day
	    cal = Calendar.getInstance();
	    cal.setTime(StringUtility.convertStringToDate(orderEffDate));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay=cal.getTime();//First day of month
        
        Date today = cal.getTime();
        cal.setTime(StringUtility.convertStringToDate(orderEffEndDate));
        cal.add(Calendar.MONTH,1); 
        cal.set(Calendar.DAY_OF_MONTH,1); 
        cal.add(Calendar.DATE,-1);   // last day of month. 
        Date lastDay=cal.getTime();
        
        String firstDate = sdf.format(firstDay);
        String lastDate = sdf.format(lastDay);
	    //
	    
		sb.append("select distinct hrEmp.emp_id hrEmpId,\n");
		sb.append("       concat(concat(concat(emp.emp_fname, ' '), concat(emp.emp_mname, ' ')),\n"); 
		sb.append("              concat(emp.emp_lname, ' ')) empName,\n");
		sb.append("       pbl.post_id postId,\n");
		sb.append("   	  gm.grade_name,\n ");
		sb.append("       desig.dsgn_shrt_name dsgnName,\n");
		sb.append("       psr.psr_no,\n");
		sb.append("       odtls.other_current_basic,\n");
		
		sb.append("       emp.user_id empUserId,\n"); 
		sb.append("       bhm.bill_subhead_id,\n"); 
		sb.append("       bhm.bill_no,\n"); 
		
		 
		sb.append("       up.post_id userPostId,\n"); 
		sb.append("       pd.dsgn_id dsgnId,\n"); 
		sb.append("       pd.loc_id\n"); 
		sb.append("  from hr_pay_bill_subhead_mpg bhm,\n");   
	    sb.append("       hr_pay_paybill          pbl left outer join hr_pay_post_psr_mpg psr on psr.post_id = pbl.post_id  ,\n");
		sb.append("       paybill_head_mpg        phm,\n");
		//sb.append("       hr_pay_post_psr_mpg     psr,\n"); 
		sb.append("       org_userpost_rlt        up,\n"); 
		sb.append("       org_post_details_rlt    pd,\n");
		sb.append(" 	  org_designation_mst desig,\n" );

		sb.append("       hr_eis_other_dtls       odtls,\n" );
		sb.append("       hr_eis_emp_mst          hrEmp,\n" ); 
		sb.append("       org_emp_mst             emp left outer join hr_pay_gpf_details gpf on gpf.user_id = emp.user_id\n" ); 
		sb.append("       left outer join org_grade_mst gm on gm.grade_id=gpf.grade_id");
		//sb.append(" 	where bhm.bill_subhead_id = phm.bill_no and psr.post_id = up.post_id and\n" );
		sb.append(" 	where bhm.bill_subhead_id = phm.bill_no \n" );
		sb.append("     and  up.post_id = pd.post_id and emp.user_id = up.user_id and\n" );
		sb.append("       pd.loc_id = ").append(locId);
		sb.append(" and (up.end_date is null or (up.end_date >= '").append(firstDate).append("' ");
		sb.append(" and up.start_date <= '").append(lastDate).append("')) and emp.lang_id = 1 \n" ); 
		sb.append(" and desig.dsgn_id = pd.dsgn_id and \n");
		

		sb.append("		  phm.bill_no = bhm.bill_subhead_id and\n" );
		sb.append("       phm.paybill_id = pbl.paybill_grp_id and\n" ); 
		sb.append("       pbl.emp_id = hrEmp.Emp_Id and\n" );
		sb.append("       phm.approve_flag in (1) and\n" );
		sb.append("       phm.bill_category = emp.grade_id and\n" ); 
		sb.append("       hrEmp.Emp_Mpg_Id = emp.emp_id\n");		
		sb.append("       and odtls.emp_id = hrEmp.Emp_Id\n");
		sb.append("		  and pbl.approve_reject_date \n");
		sb.append("		  between '").append(firstDate).append("' and\n"); 
		sb.append("       '").append(dtOrderEnd).append("' \n"); 
		if(dsgnId != 0 ){
			sb.append(" and pd.dsgn_id = ").append(dsgnId);        
		}
		
		if(!"0".equals(billSubHeadId)){
			sb.append(" and bhm.bill_subhead_id in (").append(billSubHeadId).append(")");        
		}
		
		sb.append("\n order by psr.psr_no ");
		Query sqlquery = session.createSQLQuery(sb.toString());

		logger.info("Query---->>"+sqlquery.toString());

		empList = sqlquery.list();
		}
		catch (Exception e)
		{
			logger.error("Error In getEmpArrearCmpAmtDtls in dao: " , e);
			logger.error("Error is: "+ e.getMessage());
		}
		return empList;
	}
	public List getSalRevOrdersData(long lOrderId)
	{
		Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrPaySalRevMst.class);	        
        objCrt.add(Restrictions.like("salRevId", lOrderId));	        
        return objCrt.list();
	}
	public List getEmpArrearBillMpgs(long locId)
	{
		List mpgDataList = new ArrayList();
		Session session = getSession();
		StringBuffer sb = new StringBuffer();				
		try
		{
			sb.append("select abm.sal_rev_id,\n" );
			sb.append("       sal.rev_order_no,\n" ); 
			sb.append("       abm.bill_no billSubHeadId,\n" ); 
			sb.append("       bhm.bill_no,\n" ); 
			sb.append("       sal.rev_effective_from_date,\n" ); 
			sb.append("       sal.rev_effective_to_date,\n" ); 
			sb.append("       sal.rev_pay_out_date,\n" ); 
			sb.append("       sal.rev_reason,\n" );
			sb.append("       sal.rev_order_date\n" );
			
			sb.append("  from hr_pay_arrear_billpost_mpg abm,\n" ); 
			sb.append("       hr_pay_sal_rev_mst         sal,\n" ); 
			sb.append("       hr_pay_bill_subhead_mpg    bhm\n" ); 
			sb.append(" 	where sal.sal_rev_id = abm.sal_rev_id and\n" ); 
			sb.append("       bhm.bill_subhead_id = abm.bill_no and sal.loc_id=").append(locId).append("\n" ); 
			sb.append(" 	group by abm.sal_rev_id,\n" ); 
			sb.append("          abm.bill_no,\n" ); 
			sb.append("          bhm.bill_no,\n" ); 
			sb.append("          sal.rev_order_no,\n" ); 
			sb.append("          sal.rev_order_date,\n" ); 
			sb.append("          sal.rev_effective_from_date,\n" ); 
			sb.append("          sal.rev_effective_to_date,\n" ); 
			sb.append("          sal.rev_pay_out_date,\n" ); 
			sb.append("          sal.rev_reason,sal.sal_rev_id,");
			sb.append("           abm.bill_no");
			sb.append("          order by sal.sal_rev_id desc,abm.bill_no desc");

			Query sqlquery = session.createSQLQuery(sb.toString());

			logger.info("Query to getEmpArrearBillMpgs---->>"+sqlquery.toString());

			mpgDataList = sqlquery.list();
		}
		catch (Exception e)
		{
			logger.error("Error In getEmpArrearCmpAmtDtls in dao: " , e);
			logger.error("Error is: "+ e.getMessage());
		}
		return mpgDataList;
	} 
	
	public List getEmpArrearCmpAmtDtls(long langId,long salRevId,long billSubheadId,String orderEffDate,String orderEffEndDate)
	{
		List mpgDataList = new ArrayList();
		Session session = getSession();
		StringBuffer sb = new StringBuffer();				
		try
		
		{	
			DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
		    SimpleDateFormat sdf = sbConf.GetDateFormat();		    
		    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");		
		    Date dtOrderStart = fmt.parse(orderEffDate);
		    Date dtOrderEnd = fmt.parse(orderEffEndDate);
		    
			sb.append("	select b.compo_amt_mpg_id,\n");
			sb.append("       concat(concat(concat(emp.emp_fname, ' '), concat(emp.emp_mname, ' ')),\n"); 
			sb.append("              concat(emp.emp_lname, ' ')) empName,\n"); 
			sb.append("       a.post_id,\n");
			sb.append("       rlt.edp_short_name,\n");			
			sb.append("       b.compo_id,\n"); 
			sb.append("       b.amount,\n"); 
			sb.append("       hremp.emp_id,\n");
			sb.append("       dm.dsgn_shrt_name,\n");
			sb.append("       gm.grade_name\n");
			sb.append("  FROm \n"); 
			sb.append("       hr_pay_arrear_billpost_mpg  a,\n"); 
			sb.append("       hr_pay_arrear_compo_amt_mpg b,\n"); 
			//sb.append("       org_userpost_rlt            up,\n"); 
			sb.append("       org_emp_mst                 emp,\n");
			sb.append("       rlt_bill_type_edp           rlt,\n");
			sb.append("       hr_eis_emp_mst              hremp,\n");
			sb.append("       org_post_details_rlt        pd,\n");	
			sb.append("       org_designation_mst         dm,\n");
			sb.append("       org_grade_mst               gm\n");
			sb.append(" where a.arrear_billpost_id = b.arrear_billpost_id and\n"); 
			sb.append("       pd.post_id = a.post_id and hremp.emp_id = a.emp_id and\n");
			sb.append(" 	  rlt.type_edp_id = b.compo_id and \n");
			sb.append(" 	  hremp.emp_mpg_id = emp.emp_id and  \n");			
			sb.append("       emp.lang_id = ").append(langId).append(" and a.sal_rev_id = ").append(salRevId).append(" and\n"); 
			sb.append("       a.bill_no = ").append(billSubheadId).append("\n"); 
			
			//sb.append("       and up.start_date <='").append(sdf.format(dtOrderEnd)).append("' and (up.end_date >='").append(sdf.format(dtOrderStart)).append("' or up.end_date is null) \n");
			
			sb.append("      and pd.post_id = a.post_id and dm.dsgn_id = pd.dsgn_id and emp.grade_id=gm.grade_id");
			sb.append(" order by a.post_id, b.compo_id");


			Query sqlquery = session.createSQLQuery(sb.toString());

			logger.info("Query to getEmpArrearCmpAmtDtls---->>"+sqlquery.toString());

			mpgDataList = sqlquery.list();
		}
		catch (Exception e)
		{
			logger.error("Error In getEmpArrearCmpAmtDtls in dao: " , e);
			logger.error("Error is: "+ e.getMessage());
		}
		return mpgDataList;
	} 
	public List getEmpArrearCmpCount(long postId,long langId,long salRevId,long billSubheadId)
	{
		List mpgDataList = new ArrayList();
		Session session = getSession();
		int cmpCount=0;
		StringBuffer sb = new StringBuffer();				
		try
		{	

			sb.append(" select cmp.compo_id,rlt.edp_short_name \n" );
			sb.append("  from hr_pay_arrear_compo_amt_mpg cmp, hr_pay_arrear_billpost_mpg a,rlt_bill_type_edp rlt\n" ); 
			sb.append(" where rlt.type_edp_id=cmp.compo_id and cmp.arrear_billpost_id = a.arrear_billpost_id and a.post_id = ").append(postId).append("\n" );
			sb.append("  and a.sal_rev_id = ").append(salRevId).append(" and\n"); 
			sb.append(" a.bill_no = ").append(billSubheadId).append(" order by rlt.type_edp_id\n"); 
			
			Query sqlquery = session.createSQLQuery(sb.toString());

			logger.info("Query to getEmpArrearCmpCount---->>"+sqlquery.toString());

			mpgDataList = sqlquery.list();
		}
		catch (Exception e)
		{
			logger.error("Error In getEmpArrearCmpCount in dao: " , e);
			logger.error("Error is: "+ e.getMessage());
		}
		return mpgDataList;
	}
	public List getArrearBillPostMpgData(long salRevId,long billSubheadId)
	{
		Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrPayArrearBillpostMpg.class);	        
        objCrt.add(Restrictions.like("hrPaySalRevMst.salRevId", salRevId));
        objCrt.add(Restrictions.like("MstDcpsBillGroup.dcpsDdoBillGroupId", billSubheadId));
        return objCrt.list();
	}
	public List getArrearBillCmpAmtMpgData(long arrearBillpostId)
	{
		Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrPayArrearCompoAmtMpg.class);	        
        objCrt.add(Restrictions.like("arrearBillpostMpg.arrearBillpostId", arrearBillpostId));        
        return objCrt.list();
	}

	// Following query is with respect to employee's paybill not exists
	public List getEmpListIndependentOfPaybill(long locId,String billSubHeadId,String orderEffDate,String orderEffEndDate,long postId)
	{
		List mpgDataList = new ArrayList();
		Session session = getSession();
		StringBuffer sb = new StringBuffer();	
		Date tempDate = new Date();
		try
		{
			tempDate = StringUtility.convertStringToDate(orderEffEndDate);
			
			Calendar cal = Calendar.getInstance();        		
			cal.setTime(tempDate);
	        cal.add(Calendar.MONTH,1);
	        cal.set(Calendar.DAY_OF_MONTH,4);
	        Date toDt=cal.getTime();
	        SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
	        
	        DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
		    SimpleDateFormat sdf = sbConf.GetDateFormat();
		    String dtOrderStart = sdf.format(fmt.parse(orderEffDate));
		    String dtOrderEnd = sdf.format(toDt);
		    
			sb.append("select distinct hrEmp.emp_id hrEmpId,\n");	//0
			sb.append("                concat(concat(concat(emp.emp_fname, ' '),\n"); 
			sb.append("                              concat(emp.emp_mname, ' ')),\n"); 
			sb.append("                       concat(emp.emp_lname, ' ')) empName,\n");//1 
			sb.append("                pd.post_id postId,\n"); //2
			sb.append("                gm.grade_name,\n"); //3
			sb.append("                desig.dsgn_shrt_name dsgnName,\n");//4 
			sb.append("                psr.psr_no,\n"); //5
			sb.append("                odtls.other_current_basic,\n");//6 
			sb.append("                emp.user_id empUserId,\n"); //7
			sb.append("                bhm.bill_subhead_id,\n"); //8
			sb.append("                bhm.bill_no,\n"); //9
			sb.append("                up.post_id userPostId,\n");//10 
			sb.append("                pd.dsgn_id dsgnId,\n"); //11
			sb.append("                pd.loc_id,\n");//12
			sb.append("				   hrEmp.Emp_Id      hrEmpId,\n");//13
			sb.append("				   bhm.subhead_code,\n");//14
			sb.append("				   odtls.other_id,\n");//15
			sb.append("				   psr.bill_no psrBillNo\n");//16
			
			sb.append("  from hr_pay_bill_subhead_mpg bhm,\n"); 
			sb.append("       hr_pay_post_psr_mpg     psr,\n"); 
			sb.append("       org_userpost_rlt        up,\n"); 
			sb.append("       org_post_details_rlt    pd,\n");
			sb.append("       org_designation_mst     desig,\n"); 
			sb.append("       hr_eis_other_dtls       odtls,\n" );
			sb.append("       hr_eis_emp_mst          hrEmp,\n" ); 
			sb.append("       org_emp_mst             emp left outer join hr_pay_gpf_details gpf on gpf.user_id = emp.user_id\n" ); 
			sb.append("       left outer join org_grade_mst gm on gm.grade_id=gpf.grade_id"); 
			sb.append(" where bhm.bill_subhead_id = psr.bill_no and psr.post_id = up.post_id and\n"); 
			sb.append("       up.post_id = pd.post_id and emp.user_id = up.user_id and\n"); 
			sb.append("       pd.loc_id = ").append(locId).append(" and emp.lang_id = 1 and\n"); 
			sb.append("       desig.dsgn_id = pd.dsgn_id and \n"); 
			sb.append("       hrEmp.Emp_Mpg_Id = emp.emp_id and odtls.emp_id = hrEmp.Emp_Id \n");			

			if(!"0".equals(billSubHeadId)){
				sb.append(" and bhm.bill_subhead_id in (").append(billSubHeadId).append(")");        
			}
			if(postId!=0){
				sb.append(" and pd.post_id =").append(postId).append("");        
			}
			
			sb.append(" order by psr.psr_no "); 

			
			Query sqlquery = session.createSQLQuery(sb.toString());

			logger.info("Query to getEmpListIndependentOfPaybill---->>"+sqlquery.toString());

			mpgDataList = sqlquery.list();
		}
		catch (Exception e)
		{
			logger.error("Error In getEmpListIndependentOfPaybill in dao: " , e);
			logger.error("Error is: "+ e.getMessage());
		}
		return mpgDataList;	
	}
	public MstDcpsBillGroup   getHrPBLSubheadMpgByBillSubheadId(long billSubheadId)
	{
		Criteria objCrt = null;
        Session hibSession = getSession();
        List<MstDcpsBillGroup> dataList = new ArrayList();
        MstDcpsBillGroup   payBillHeadMpg = new MstDcpsBillGroup();
        objCrt = hibSession.createCriteria(MstDcpsBillGroup.class);	        
        objCrt.add(Restrictions.like("dcpsDdoBillGroupId", billSubheadId));    
        dataList = objCrt.list();
        if(dataList!=null && dataList.size()>0)
        {
        	payBillHeadMpg = (MstDcpsBillGroup)dataList.get(0);
        }         
        return payBillHeadMpg;
	}	
	public PaybillHeadMpg checkPaybillHeadMpgData(int month,int year,long phmBillNo,long billTypeId)
	{
		Criteria objCrt = null;
        Session hibSession = getSession();
        List<PaybillHeadMpg> dataList = new ArrayList();
        PaybillHeadMpg phm = null;
        objCrt = hibSession.createCriteria(PaybillHeadMpg.class);	        
        objCrt.add(Restrictions.like("month", month*1.0));
        objCrt.add(Restrictions.like("year", year*1.0));    
        objCrt.add(Restrictions.like("billNo.billHeadId", phmBillNo));    
        objCrt.add(Restrictions.like("billTypeId.lookupId", billTypeId));    
        dataList = objCrt.list();
        if(dataList!=null && dataList.size()>0)
        {
        	phm = new PaybillHeadMpg();
        	phm =(PaybillHeadMpg)dataList.get(0);       
        }         
       	return phm;
	}	
	public OrgDdoMst getOrgDDOMstVO(long postId)
	{
		Criteria objCrt = null;
        Session hibSession = getSession();
        List<OrgDdoMst> dataList = new ArrayList();
        OrgDdoMst odm = null;
        objCrt = hibSession.createCriteria(OrgDdoMst.class);	        
        objCrt.add(Restrictions.like("postId", postId));        
        dataList = objCrt.list();
        if(dataList!=null && dataList.size()>0)
        {
        	odm = new OrgDdoMst();
        	odm =(OrgDdoMst)dataList.get(0);       
        }         
       	return odm;
	}
	public boolean checkIsPaybillGenerated(long postId,long locId,int month,int year)
	{
		List mpgDataList = new ArrayList();
		Session session = getSession();
		boolean flag=false;
		StringBuffer sb = new StringBuffer();				
		try
		{
			sb.append("select phm.id phmId,pb.id pbId\n" );
			sb.append("  from paybill_head_mpg phm, hr_pay_paybill pb\n" ); 
			sb.append(" where pb.paybill_grp_id = phm.paybill_id and pb.emp_id is not null and phm.approve_flag = 1 and\n" ); 
			sb.append("       phm.loc_id = ").append(locId).append(" and phm.paybill_month = ").append(month).append(" and\n" );
			sb.append("       phm.paybill_year = ").append(year).append(" and pb.post_id = ").append(postId);

			
			Query sqlquery = session.createSQLQuery(sb.toString());

			logger.info("Query to checkIsPaybillGenerated---->>"+sqlquery.toString());

			mpgDataList = sqlquery.list();
			if(mpgDataList!=null&&mpgDataList.size()>0)
			flag=true;
		}
		catch (Exception e)
		{
			logger.error("Error In checkIsPaybillGenerated in dao: " , e);
			logger.error("Error is: "+ e.getMessage());
		}
		return flag;
	}
	public List getEmpDetailsFromEmpID(long empId,long locId)
	{

		List empDataList = new ArrayList();
		Session session = getSession();
		
		StringBuffer sb = new StringBuffer();				
		try
		{
			sb.append(" select pd.post_id,\n" );
			sb.append("       e.emp_id hrEmpId,\n" ); 
			sb.append("       concat(concat(concat(emp.emp_fname, ' '), concat(emp.emp_mname, ' ')),\n" ); 
			sb.append("              concat(emp.emp_lname, ' ')) empName,\n" );
			sb.append("       gm.grade_name,\n" );
			sb.append("       desg.dsgn_shrt_name\n" ); 
			sb.append("  from org_emp_mst          emp,\n" ); 
			sb.append("       hr_eis_emp_mst       e,\n" );
			sb.append("       hr_eis_other_dtls odtls,\n" );
			sb.append("       org_post_details_rlt pd,\n" ); 
			sb.append("       org_designation_mst desg,\n" ); 
			sb.append("       org_userpost_rlt     up left outer join hr_pay_gpf_details gpf on gpf.user_id = up.user_id left outer join org_grade_mst gm on gm.grade_id = gpf.grade_id\n" ); 
			sb.append(" where emp.emp_id = e.emp_mpg_id and emp.user_id = up.user_id and\n" );
			sb.append("       up.post_id = pd.post_id and desg.dsgn_id=pd.dsgn_id\n" );
			sb.append("       and pd.loc_id= ").append(locId).append(" \n" ); 
			sb.append("       and emp.emp_id= ").append(empId).append(" and odtls.emp_id = e.emp_id \n");
			sb.append("      order by up.start_date desc\n" ); 
			
			Query sqlquery = session.createSQLQuery(sb.toString());

			logger.info("Query to getEmpDetailsFromEmpID---->>"+sqlquery.toString());

			empDataList = sqlquery.list();
			
		}
		catch (Exception e)
		{
			logger.error("Error In getEmpDetailsFromEmpID in dao: " , e);
			logger.error("Error is: "+ e.getMessage());
		}
		return empDataList;
	}
}
