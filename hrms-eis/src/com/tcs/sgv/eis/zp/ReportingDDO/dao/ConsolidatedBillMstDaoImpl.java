package com.tcs.sgv.eis.zp.ReportingDDO.dao;

import java.util.Iterator;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class ConsolidatedBillMstDaoImpl extends GenericDaoHibernateImpl implements ConsolidatedBillMstDao
{
	Session hibSession=null;
	public ConsolidatedBillMstDaoImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		hibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}
	
	public List getBillDtl(String schemeCode,long postId,long month,long year){
		List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- getBillDtls---");
    	sb.append("SELECT CONS_BILL_ID,GROSS_AMT,NET_AMT,PF,PT from CONSOLIDATED_BILL_MST");//SUM(BILL_GROSS_AMT), SUM(BILL_NET_AMOUNT) FROM PAYBILL_HEAD_MPG where BILL_NO in (SELECT BILL_GROUP_ID FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+ schemeCode+"') and loc_id in("+locId+") and PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year);
    	sb.append("where SCHEME_CODE='"+schemeCode);
    	sb.append("' and MONTH="+month);
    	sb.append("and YEAR="+year);
    	sb.append("and POST_ID="+postId);
    	sb.append("and status=0");
    	logger.info("---- getBillDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;		
	}
	
	public void updateConsolidateBill(long billId,long gross,long net,long pf,long pt){
		List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- getBillDtls---");
    	sb.append("update CONSOLIDATED_BILL_MST set");
    	sb.append(" GROSS_AMT="+billId);
    	sb.append(",NET_AMT="+billId);
    	sb.append(",PF="+billId);
    	sb.append(",PT="+billId);
    	sb.append(",UPDATED_DATE=sysdate");
    	sb.append("where CONS_BILL_ID="+billId);    	
    	logger.info("---- getBillDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	query.executeUpdate();
    			
	}
	
	
	public List getBillDtls(String consBillId){
		List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- getBillDtls---");
    	sb.append("SELECT cons.CONS_BILL_ID,cons.GROSS_AMT,cons.NET_AMT,cons.scheme_code,cons.status from CONSOLIDATED_BILL_MST cons");//SUM(BILL_GROSS_AMT), SUM(BILL_NET_AMOUNT) FROM PAYBILL_HEAD_MPG where BILL_NO in (SELECT BILL_GROUP_ID FROM MST_DCPS_BILL_GROUP where SCHEME_CODE='"+ schemeCode+"') and loc_id in("+locId+") and PAYBILL_MONTH="+month+" and PAYBILL_YEAR="+year);
    	sb.append("where cons.CONS_BILL_ID="+consBillId);    
    //	sb.append("and ddo.ZP_DDO_POST_ID in(");
    //	sb.append(postId+")");
    	
    	logger.info("---- getBillDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;		
	}
	
	public List getConsBillDtls(String schemeCode,String month,String year,String postId){
		List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- getConsBillDtls---");
    	//modified by saurabh
    	sb.append("SELECT CONS_BILL_ID,scheme_code,GROSS_AMT,NET_AMT,status,sub_scheme_code from CONSOLIDATED_BILL_MST where scheme_code=");
    	sb.append(schemeCode+" and month="+month+" and year="+year+" and POST_ID="+postId+" and status in(0,1)");
    	logger.info("---- getBillDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;	
	}
	
	public List getConsBillDtls(String month,String year,String postId){
		List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	//logger.info("---- getConsBillDtls---");
    	//modified by saurabh
    	sb.append("SELECT CONS_BILL_ID,scheme_code,GROSS_AMT,NET_AMT,status,sub_scheme_code from CONSOLIDATED_BILL_MST where ");
    	sb.append("month="+month+" and year="+year+" and POST_ID="+postId+" and status in(0,1)");
    	logger.info("---- getBillDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	temp = query.list();
    	logger.info("---- query---"+temp.size());
    	return temp;	
	}
	
	/*public void deleteConsBill(String billId){
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getConsBillDtls---");
    	sb.append("update CONSOLIDATED_BILL_MST set status=-1 where ");
    	sb.append("CONS_BILL_ID="+billId+" and status in(0,4)");
    	logger.info("---- getBillDtls DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	query.executeUpdate();
    	StringBuffer sb2 = new StringBuffer();
    	sb2.append("update CONSOLIDATED_BILL_MPG set status=-1 where ");
    	sb2.append("CONS_BILL_ID="+billId+" and status in(0,4)");
    	Query query2 = session.createSQLQuery(sb2.toString());
    	query2.executeUpdate();
    	
    	StringBuffer sb3 = new StringBuffer();
    	sb3.append("update CONSOLIDATED_BILL_LEVEL_POST_MPG set status=-1 where ");
    	sb3.append("CONS_BILL_ID="+billId);
    	Query query3 = session.createSQLQuery(sb3.toString());
    	query3.executeUpdate();
    	
    	//added by vaibhav tyagi:start
    	List paybillId=null;
    	StringBuffer sb4 = new StringBuffer();
    	sb3.append("select PAYBILL_ID from CONSOLIDATED_BILL_MPG where ");
    	sb3.append("CONS_BILL_ID="+billId);
    	Query query4 = session.createSQLQuery(sb4.toString());
    	paybillId=query4.list();
    	
    	if (paybillId != null && paybillId.size() > 0)
		{
			Iterator IT = paybillId.iterator();

			Object[] lObj = null;
			while (IT.hasNext())
			{
				lObj = (Object[]) IT.next();
				String billNo=null;
				if(lObj[0]!=null){
					billNo=lObj[0].toString();
					StringBuffer sb5 = new StringBuffer();
			    	logger.info("---- deleteConsBill---");
			    	sb.append("update PAYBILL_HEAD_MPG set APPROVE_FLAG=2,status=0 where BILL_NO ="+billNo+" and status=2");
			    	logger.info("---- deleteConsBill DAo---"+sb.toString());
			    	Query query5 = session.createSQLQuery(sb5.toString());
			    	query.executeUpdate();
				}
			}
		}

    	
    	//added by vaibhav tyagi: end
    	
	}*/
	public void deleteConsBill(String billId){

		StringBuffer sb = new StringBuffer();
		//logger.info("---- getConsBillDtls---");
		sb.append("update CONSOLIDATED_BILL_MST set status=-1 where ");
		sb.append("CONS_BILL_ID="+billId+" and status in(0,4)");
		logger.info("---- getBillDtls DAo---"+sb.toString());
		Query query = hibSession.createSQLQuery(sb.toString());
		query.executeUpdate();


		StringBuffer sb2 = new StringBuffer();
		sb2.append("update CONSOLIDATED_BILL_MPG set status=-1 where ");
		sb2.append("CONS_BILL_ID="+billId+" and status in(0,4)");
		logger.info("---- getBillDtls DAo---"+sb2.toString());
		Query query2 = hibSession.createSQLQuery(sb2.toString());
		query2.executeUpdate();


		StringBuffer sb3 = new StringBuffer();
		sb3.append("update CONSOLIDATED_BILL_LEVEL_POST_MPG set status=-1 where ");
		sb3.append("CONS_BILL_ID="+billId);
		logger.info("---- getBillDtls DAo---"+sb3.toString());
		Query query3 = hibSession.createSQLQuery(sb3.toString());
		query3.executeUpdate();
		
		//added by vaibhav tyagi:start
		StringBuffer sb4 = new StringBuffer();
		sb4.append("update PAYBILL_HEAD_MPG set APPROVE_FLAG=2,status=0 where PAYBILL_ID in(");
		sb4.append(" select PAYBILL_ID from CONSOLIDATED_BILL_MPG where ");
		sb4.append("CONS_BILL_ID="+billId);
		sb4.append(")");
		sb4.append(" and status=4");
		logger.info("---- getBillDtls DAo---"+sb4.toString());
		Query query4 = hibSession.createSQLQuery(sb4.toString());
		query4.executeUpdate();
		//added by vaibhav tyagi: end

	}

	
	public List getBillReport(String billId){
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	List temp=null;
    	logger.info("---- getBillReport---");
    	//sb.append("select scheme.MAJOR_HEAD,scheme.MINOR_HEAD,scheme.sub_head,scheme.scheme_name,scheme.demand_code,scheme.SCHEME_CODE,bill.GROSS_AMT,bill.NET_AMT,bill.pt,bill.PF,bill.month,bill.year,bill.dcps,scheme.SUB_MAJOR_HEAD,scheme.SUB_MINOR_HEAD,bill.gis,bill.hrr,bill.ACC_POLICY,bill.sub_scheme_code,subscheme.DISCRIPTION,bill.REVENUE_STAMP from MST_SCHEME scheme  inner join CONSOLIDATED_BILL_MST bill on bill.SCHEME_CODE=scheme.SCHEME_CODE left outer join SUBSCHEME_MASTER subscheme on subscheme.SUBSCHEME_CD=bill.sub_scheme_code where ");
    	sb.append("select scheme.MAJOR_HEAD,scheme.MINOR_HEAD,scheme.sub_head,scheme.scheme_name,scheme.demand_code,scheme.SCHEME_CODE,bill.GROSS_AMT,bill.NET_AMT,bill.pt,bill.PF,bill.month,bill.year,bill.dcps,scheme.SUB_MAJOR_HEAD,scheme.SUB_MINOR_HEAD,bill.gis,bill.hrr,bill.ACC_POLICY,bill.sub_scheme_code,subscheme.DISCRIPTION,bill.NPS_EMPLR_CONTRI_DED  from MST_SCHEME scheme  inner join CONSOLIDATED_BILL_MST bill on bill.SCHEME_CODE=scheme.SCHEME_CODE left outer join SUBSCHEME_MASTER subscheme on subscheme.SUBSCHEME_CD=bill.sub_scheme_code where ");
    	sb.append(" bill.CONS_BILL_ID="+billId);
    	sb.append(" and bill.SCHEME_CODE=scheme.SCHEME_CODE");
    	Query query = session.createSQLQuery(sb.toString());
    	temp = query.list();
    	logger.info("---- query---"+query);
    	logger.info("---- query---"+temp.size());
    	return temp;	
	}
	
	
	public List getHierachyDtls(Long billId){
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	List temp=null;
    	//logger.info("---- getHierachyDtls---");
    	sb.append("select scheme.MAJOR_HEAD,scheme.MINOR_HEAD,scheme.sub_head,scheme.scheme_name,scheme.demand_code,scheme.SCHEME_CODE,bill.GROSS_AMT,bill.NET_AMT,bill.PF,bill.pt,bill.month,bill.year  from MST_SCHEME scheme,CONSOLIDATED_BILL_MST bill where ");
    	sb.append(" bill.CONS_BILL_ID="+billId);
    	sb.append(" and bill.SCHEME_CODE=scheme.SCHEME_CODE");
    	Query query = session.createSQLQuery(sb.toString());
    	temp = query.list();
    	logger.info("---- getHierachyDtls---"+temp.size());
    	return temp;	
		
	}

	public List viewConsBillDetails(String billid) {
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		List temp=null;
		logger.info("---- viewConsBillDetails---");
		sb.append("SELECT billgroup.BILL_GROUP_ID,billgroup.DESCRIPTION, office.OFF_NAME, ");
		sb.append("sum(paybill.GROSS_AMT) as Gross_Amt, sum(paybill.TOTAL_DED) as TOTAL_DED, sum(paybill.NET_TOTAL) as NET_AMT ");
		sb.append("FROM CONSOLIDATED_BILL_MPG bill, PAYBILL_HEAD_MPG pay, HR_PAY_PAYBILL paybill, ");
		sb.append("MST_DCPS_BILL_GROUP billGroup, MST_DCPS_DDO_OFFICE office ");
		sb.append("where bill.PAYBILL_ID=pay.PAYBILL_ID and pay.PAYBILL_ID=paybill.PAYBILL_GRP_ID and pay.BILL_NO=billgroup.BILL_GROUP_ID and billGroup.LOC_ID=office.LOC_ID ");
		sb.append("and paybill.emp_id is not null ");
		sb.append("and bill.CONS_BILL_ID="+billid);
		sb.append(" group by billgroup.BILL_GROUP_ID,billgroup.DESCRIPTION, office.OFF_NAME");
		logger.info("---- viewConsBillDetails---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		temp = query.list();
		logger.info("---- viewConsBillDetails---"+sb.toString());
		return temp;	
		
	}

	public List viewConsBillDetailsSum(String billid) {
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		List temp=null;
		//logger.info("---- viewConsBillDetailsSum---");
		sb.append("SELECT ");
		sb.append("sum(paybill.GROSS_AMT) as Gross_Amt, sum(paybill.TOTAL_DED) as TOTAL_DED, sum(paybill.NET_TOTAL) as NET_AMT ");
		sb.append("FROM CONSOLIDATED_BILL_MPG bill, PAYBILL_HEAD_MPG pay, HR_PAY_PAYBILL paybill, ");
		sb.append("MST_DCPS_BILL_GROUP billGroup, MST_DCPS_DDO_OFFICE office ");
		sb.append("where bill.PAYBILL_ID=pay.PAYBILL_ID and pay.PAYBILL_ID=paybill.PAYBILL_GRP_ID and pay.BILL_NO=billgroup.BILL_GROUP_ID and billGroup.LOC_ID=office.LOC_ID ");
		sb.append("and paybill.emp_id is not null and upper(office.ddo_office)='YES'");
		sb.append("and bill.CONS_BILL_ID="+billid);
		logger.info("---- viewConsBillDetailsSum---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		temp = query.list();
		logger.info("---- viewConsBillDetailsSum---"+sb.toString());
		return temp;
	}
	//added by poonam for beams
	 public int getCount(long consBillId){
			int benCount = 0;
			StringBuffer sbEmp = new StringBuffer();
			sbEmp.append("select count(1) from hr_pay_paybill where emp_id is not null ");
			sbEmp.append("and paybill_grp_id in (select paybill_id from consolidated_bill_mpg where ");
			sbEmp.append("CONS_BILL_ID="+consBillId+")");
			logger.info("---- getCount DAo---"+sbEmp.toString());
			Query query = hibSession.createSQLQuery(sbEmp.toString());
			
			benCount = Integer.parseInt(query.uniqueResult().toString());
			
			int count = 0;
			StringBuffer sb = new StringBuffer();
			sb.append("select  count(1) from consolidated_bill_mst where ((month<8 and year=2015) or year<2015) and   "); 
			sb.append(" CONS_BILL_ID="+consBillId+"");
			logger.info("---- getCount DAo---"+sb.toString());
			Query query1 = hibSession.createSQLQuery(sb.toString());
			
			count = Integer.parseInt(query1.uniqueResult().toString());
			
			
			
			StringBuffer sbDDO = new StringBuffer();
			//if(count==0)
			sbDDO.append("select distinct sum(nvl(nonGovt.NON_GOV_DEDUC_AMOUNT,0)) + sum(paybill.TOTAL_DED)-sum(paybill.pt)-sum(paybill.GPF_GRP_ABC)-sum(paybill.GPF_GRP_D)-sum(paybill.GPF_ADV_GRP_ABC)-sum(paybill.GPF_ADV_GRP_D)-sum(paybill.GPF_ABC_ARR_MR)-sum(paybill.GPF_D_ARR_MR)-sum(paybill.DCPS)-sum(paybill.DCPS_DA)-sum(paybill.DCPS_DELAY)-sum(paybill.DCPS_PAY)-sum(paybill.JANJULGISARR)-sum(paybill.ACC_POLICY)- sum(SVNPC_GPF_ARR_DEDU)-sum(SVNPC_GPF_RECO) -sum(SVNPC_DCPS_RECO),pay.LOC_ID from paybill_head_mpg pay  ");
			/*else 
			sbDDO.append("select distinct sum(nvl(nonGovt.NON_GOV_DEDUC_AMOUNT,0)) + sum(paybill.TOTAL_DED)-sum(paybill.pt)-sum(paybill.pt_Arr)-sum(paybill.GPF_GRP_ABC)-sum(paybill.GPF_GRP_D)-sum(paybill.GPF_ADV_GRP_ABC)-sum(paybill.GPF_ADV_GRP_D)-sum(paybill.GPF_ABC_ARR_MR)-sum(paybill.GPF_D_ARR_MR)-sum(paybill.it)-sum(paybill.SERVICE_CHARGE)-sum(paybill.hrr),pay.LOC_ID from paybill_head_mpg pay  ");
			*/
			sbDDO.append("inner join CONSOLIDATED_BILL_MPG cmpg on pay.PAYBILL_ID=cmpg.PAYBILL_ID ");
			 sbDDO.append("inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID=paybill.PAYBILL_GRP_ID ");
			 sbDDO.append("left outer join HR_PAY_PAYSLIP_NON_GOVT nonGovt on nonGovt.PAYBILL_ID=paybill.id ");
			 sbDDO.append("where cmpg.CONS_BILL_ID = " + consBillId + "  group by pay.LOC_ID ");
			 this.logger.info("---- getCount DAo---" + sbDDO.toString());
			 Query queryDDO = this.hibSession.createSQLQuery(sbDDO.toString());
			 List DDOList = queryDDO.list();
			 Iterator itr = DDOList.iterator();
			 Object[] obj = null;
			 while (itr.hasNext())
			 {
			     obj = (Object[]) itr.next();
			     if (obj[1] != null)
			     {
			         if (obj[0].toString() != null && !obj[0].toString().equals("0"))
			
			         {
			             benCount = benCount + 1;
			         }
			     }
			
			 }
			/*StringBuffer sbDDO = new StringBuffer();
			sbDDO.append("select count(distinct loc_id) from paybill_head_mpg where paybill_id in (select paybill_id from consolidated_bill_mpg where CONS_BILL_ID ="+consBillId+")");
			
			logger.info("---- getCount DAo---"+sbDDO.toString());
			Query queryDDO = hibSession.createSQLQuery(sbDDO.toString());
			
			benCount = benCount + Integer.parseInt(queryDDO.uniqueResult().toString());
			*/
			 
			
			
			
			
			
			return benCount;
		}
	  public List getPFDetailsForConsBill(final long consBillId)
	    {
	        List pfDetails = null;

	        final StringBuffer sb = new StringBuffer();
	     /*   sb.append("select sum(GPF_GRP_ABC + GPF_ABC_ARR_MR ), sum(GPF_GRP_D + GPF_D_ARR_MR), ");
	        sb.append("sum(GPF_ADV_GRP_ABC + GPF_ADV_GRP_ABC_INT), sum(GPF_ADV_GRP_D + GPF_ADV_GRP_D_INT) ");
	        sb.append("from hr_pay_paybill where emp_id is not null ");
	        sb.append("and paybill_grp_id in (select paybill_id from consolidated_bill_mpg where ");
	        sb.append("CONS_BILL_ID=" + consBillId + ")");*/
	        sb.append("SELECT ofc.HEAD_OF_ACCOUNT_CODE,sum(GPF_GRP_ABC + GPF_ADV_GRP_ABC +  GPF_ABC_ARR_MR + GPF_GRP_D + GPF_ADV_GRP_D + GPF_D_ARR_MR + SVNPC_GPF_ARR_DEDU + SVNPC_GPF_RECO) FROM HR_PAY_PAYBILL paybill ");
			sb.append("inner join MST_DCPS_DDO_OFFICE ofc on ofc.LOC_ID = paybill.LOC_ID  ");
			//sb.append("inner join Org_emp_mst emp on emp.EMP_ID=eis.EMP_MPG_ID ");
			sb.append(" where paybill.PAYBILL_GRP_ID in (select paybill_id from consolidated_bill_mpg where cons_bill_id in ("+consBillId+")) and ofc.HEAD_OF_ACCOUNT_CODE is not null " );
			sb.append(" group by ofc.HEAD_OF_ACCOUNT_CODE");
	        this.logger.info("---- getPfDetails DAo---" + sb.toString());
	        final Query query = this.hibSession.createSQLQuery(sb.toString());
	        pfDetails = query.list();
	        return pfDetails;
	    }
	  
	  
	  
	  
	  public List getGpfByHeadActCode(String billId){

			Session session = getSession();
			StringBuffer sb = new StringBuffer();
			List temp=null;
			//logger.info("---- getGpfByHeadActCode---");
			/*sb.append("SELECT emp.HEAD_ACT_CODE,sum(GPF_GRP_ABC + GPF_ADV_GRP_ABC +  GPF_ABC_ARR_MR),sum(GPF_GRP_D + GPF_ADV_GRP_D + GPF_D_ARR_MR) FROM HR_PAY_PAYBILL paybill ");*/
			sb.append("SELECT emp.HEAD_ACT_CODE,sum(GPF_GRP_ABC + GPF_ADV_GRP_ABC +  GPF_ABC_ARR_MR + GPF_GRP_D + GPF_ADV_GRP_D + GPF_D_ARR_MR + SVNPC_GPF_ARR_DEDU + SVNPC_GPF_RECO) FROM HR_PAY_PAYBILL paybill ");
			sb.append("inner join HR_EIS_EMP_MST eis on eis.EMP_ID=paybill.EMP_ID  ");
			sb.append("inner join Org_emp_mst emp on emp.EMP_ID=eis.EMP_MPG_ID ");
			/*sb.append("where paybill.PAYBILL_GRP_ID in (select paybill_id from consolidated_bill_mpg where cons_bill_id in ("+billId+")) and emp.HEAD_ACT_CODE is not null ");*/
			sb.append("where paybill.PAYBILL_GRP_ID in (select paybill_id from consolidated_bill_mpg where cons_bill_id in ("+billId+")) and emp.HEAD_ACT_CODE is not null and emp.HEAD_ACT_CODE <>0 ");
			sb.append("group by emp.HEAD_ACT_CODE ");
			
			//query modified by Akshay
			/*sb.append("SELECT emp.HEAD_ACT_CODE,sum(GPF_GRP_ABC + GPF_ADV_GRP_ABC +  GPF_ABC_ARR_MR),sum(GPF_GRP_D + GPF_ADV_GRP_D + GPF_D_ARR_MR),emp.GRADE_ID FROM HR_PAY_PAYBILL paybill ");
			sb.append("inner join HR_EIS_EMP_MST eis on eis.EMP_ID=paybill.EMP_ID  ");
			sb.append("inner join Org_emp_mst emp on emp.EMP_ID=eis.EMP_MPG_ID ");
			sb.append("where paybill.PAYBILL_GRP_ID in (select paybill_id from consolidated_bill_mpg where cons_bill_id in ("+billId+")) and emp.HEAD_ACT_CODE is not null ");
			sb.append("group by emp.HEAD_ACT_CODE, emp.GRADE_ID ");*/
			Query query = session.createSQLQuery(sb.toString());
			temp = query.list();
			logger.info("---- query---"+query);
			logger.info("---- query---"+temp.size());
			return temp;	
		}
		
	//update on 25012023 for MTR44 By Naveen
	  public String getDDOCodefromPostId(final Long postId)
	    {

	        String lStrDdoCode = null;
	        List lLstDdoDtls = null;

	        final StringBuilder lSBQuery = new StringBuilder();
	        lSBQuery.append(" SELECT DDO_CODE FROM ORG_DDO_MST ");
	        lSBQuery.append(" WHERE POST_ID = :postId");
	        final Query lQuery = this.hibSession.createSQLQuery(lSBQuery.toString());
	        lQuery.setParameter("postId", postId);

	        lLstDdoDtls = lQuery.list();

	        this.logger.info("Query for DDo code----------" + lSBQuery.toString());
	        this.logger.info("DDo code is----------" + lLstDdoDtls);
	        if (lLstDdoDtls != null)
	        {
	            if (lLstDdoDtls.size() != 0)
	            {
	                if (lLstDdoDtls.get(0) != null)
	                {
	                    lStrDdoCode = lLstDdoDtls.get(0).toString();
	                }
	            }
	        }

	        return lStrDdoCode;
	    }
  
	 	  
	  
    
}
