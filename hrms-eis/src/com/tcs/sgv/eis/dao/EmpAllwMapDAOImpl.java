package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.CommonAllowanceDetailsVO;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpgHst;

public class EmpAllwMapDAOImpl extends GenericDaoHibernateImpl<HrPayEmpallowMpg, Long> implements EmpAllwMapDAO {
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");
	public EmpAllwMapDAOImpl(Class <HrPayEmpallowMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getAllAllowanceTypeFromMaster()
    {
		
		
		List allowanceLst=new ArrayList();
        Session hibSession = getSession();
        String queryStr ="select hrEmpAllMap.allowCode,hrEmpAllMap.allowName from HrPayAllowTypeMst as hrEmpAllMap";
        Query query= hibSession.createQuery(queryStr);
        allowanceLst = query.list();
		return allowanceLst;
    }
	public List getAllAllowanceTypeFromMpg(long empid,int month,int year)
    {
		List allowanceLst=new ArrayList();
        Session hibSession = getSession();
        logger.info("All Mapping Allowance Query...");
        String queryStr ="select hrEmpAllMpg.allowCode, hrEmpAllMpg.hrPayAllowTypeMst, round(hrEmpAllMpg.empAllowAmount),hrEmpAllMpg.month,hrEmpAllMpg.year from HrPayEmpallowMpg as hrEmpAllMpg where hrEmpAllMpg.empCurrentStatus=1 and hrEmpAllMpg.hrEisEmpMst.orgEmpMst.empId=" + empid+"  and hrEmpAllMpg.month in ("+month+",-1) and hrEmpAllMpg.year in("+year+",-1)  order by hrEmpAllMpg.hrPayAllowTypeMst.sequence_no";
        //String queryStr ="select hrEmpAllMpg.allowCode, hrEmpAllMpg.hrPayAllowTypeMst, round(hrEmpAllMpg.empAllowAmount),hrEmpAllMpg.month,hrEmpAllMpg.year from HrPayEmpallowMpg as hrEmpAllMpg where hrEmpAllMpg.empCurrentStatus=1 and hrEmpAllMpg.hrEisEmpMst.orgEmpMst.empId=" + empid+" and (hrEmpAllMpg)  order by hrEmpAllMpg.hrPayAllowTypeMst.sequence_no";
        Query query= hibSession.createQuery(queryStr);
        logger.info("query is "+query);
        allowanceLst = query.list();
		return allowanceLst;
    }
	
	/*public List getAllAllowanceTypeFromMpg(long empid)
    {
		List allowanceLst=new ArrayList();
        Session hibSession = getSession();
        String queryStr ="select hrEmpAllMpg.allowCode, hrEmpAllMpg.hrPayAllowTypeMst, round(hrEmpAllMpg.empAllowAmount) from HrPayEmpallowMpg as hrEmpAllMpg where hrEmpAllMpg.empCurrentStatus=1 and hrEmpAllMpg.hrEisEmpMst.orgEmpMst.empId=" + empid+"order by hrEmpAllMpg.hrPayAllowTypeMst.sequence_no";
        Query query= hibSession.createQuery(queryStr);
        allowanceLst = query.list();
		return allowanceLst;
    }*/
	public HrPayEmpallowMpg getHrPayEmpallowMpg(long empId,long allowCode) {
		HrPayEmpallowMpg hrPayEmpallowMpg = new HrPayEmpallowMpg();
		Session hibSession = getSession();
		String strQuery = "from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.hrEisEmpMst.orgEmpMst.empId="+empId;
		Query query=hibSession.createQuery(strQuery);
		hrPayEmpallowMpg= (HrPayEmpallowMpg)query.uniqueResult();
		return hrPayEmpallowMpg;
	}
	
	public List getAllAllowanceTypeFromMpgNew(long empid,int month,int year)
	{
		
		List allowanceLst=new ArrayList();
        Session hibSession = getSession();
        
        String queryStr ="select hrEmpAllMpg.allowCode, hrEmpAllMpg.hrPayAllowTypeMst, round(hrEmpAllMpg.empAllowAmount),hrEmpAllMpg.month,hrEmpAllMpg.year from HrPayEmpallowMpg as hrEmpAllMpg "
        	+"where hrEmpAllMpg.hrEisEmpMst.orgEmpMst.empId=" + empid+" and hrEmpAllMpg.empCurrentStatus=1 and (hrEmpAllMpg.month="+month+" and hrEmpAllMpg.year="+year+" ) " 
       /* 			"or (hrEmpAllMpg.hrPayAllowTypeMst.allowCode not in "
        	+"(select mpgSub.hrPayAllowTypeMst.allowCode from HrPayEmpallowMpg as mpgSub where mpgSub.hrEisEmpMst.orgEmpMst.empId=" + empid+" and mpgSub.month = "+month+" and mpgSub.year = "+year+" ))) "
               	+"order by hrEmpAllMpg.month";
       */ ;
        Query query= hibSession.createQuery(queryStr);
        logger.info("query is "+query);
        allowanceLst = query.list();
		return allowanceLst;
		
	}
	
	/**
	 * @author Amish Parikh
	 * @purpose	To prepare Pay Certificate 
	 * @return	List of rows of employee allowance mapping  
	 * This method will return all the allowance mapped to an employee.
	 */
	
	
	public List getMasterdataForEmpAllowMpg(long empid)
	{
		
		Session hibSession = getSession();
        
        String queryStr ="from HrPayEmpallowMpg as hrEmpAllMpg where hrEmpAllMpg.hrEisEmpMst.orgEmpMst.empId=" + empid+" and hrEmpAllMpg.empCurrentStatus=1 and hrEmpAllMpg.month=-1 and hrEmpAllMpg.year=-1"
        	+" and hrEmpAllMpg.hrPayAllowTypeMst.allowCode in ( select hrEisEmpCompMpg.compId from HrEisEmpCompMpg  as hrEisEmpCompMpg where hrEisEmpCompMpg.hrEisEmpCompGrpMst.hrEisEmpMst.orgEmpMst.empId = " +
        			empid +
        			" and hrEisEmpCompMpg.hrEisEmpCompGrpMst.isactive=1 " +
        			" and hrEisEmpCompMpg.isactive =1 " +
        			" and hrEisEmpCompMpg.cmnLookupMst.lookupId= 2500134"+
        			
        			")";
   
        Query query= hibSession.createQuery(queryStr);
        logger.info("query is "+query);
        List allowanceLst = query.list();
		return allowanceLst;
		
	}
	
	//End Amish
	
	
/*	public boolean checkHrPayEmpallowMpg(long empId,long allowCode) {
		
		Session hibSession = getSession();
		String strQuery = "from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.hrEisEmpMst.empId="+empId;
		Query query=hibSession.createQuery(strQuery);
		if(query.list()!=null && query.list().size()>0)
		{	
			return true;
		}
		else
		{
			return false;	
		}
		
	}*/
	//by manoj for pay bill generation
	public List<HrPayEmpallowMpg> getEmpallowMpg(long empId,long allowCode,long empCurrentStatus) {
		HrPayEmpallowMpg hrPayEmpallowMpg = new HrPayEmpallowMpg();
		Session hibSession = getSession();
		String strQuery = "from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.empCurrentStatus ="+empCurrentStatus+" and objEmpAllowMap.hrEisEmpMst.empId="+empId;
		Query query=hibSession.createQuery(strQuery);
		
		return query.list();
	}
	
	public List<HrPayEmpallowMpg> getEmpallowMpgNew(long empId,long allowCode,long empCurrentStatus,int month,int year) {
		HrPayEmpallowMpg hrPayEmpallowMpg = new HrPayEmpallowMpg();
		Session hibSession = getSession();
		String strQuery = "from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.empCurrentStatus ="+empCurrentStatus+" and objEmpAllowMap.month in (-1) and objEmpAllowMap.year in (-1) and objEmpAllowMap.hrEisEmpMst.empId="+empId;
		Query query=hibSession.createQuery(strQuery);
		
		return query.list();
	}
	public List<HrPayEmpallowMpg> getEmpallowMpgNew(long empId,String allowCode,long empCurrentStatus,int month,int year) {
		HrPayEmpallowMpg hrPayEmpallowMpg = new HrPayEmpallowMpg();
		Session hibSession = getSession();
		String strQuery = "from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode in( "+allowCode
			+ " ) and objEmpAllowMap.empCurrentStatus ="+empCurrentStatus+" and objEmpAllowMap.month in (-1) and objEmpAllowMap.year in (-1) and objEmpAllowMap.hrEisEmpMst.empId="+empId;
		Query query=hibSession.createQuery(strQuery);
		
		return query.list();
	}	
	public List<HrPayDeductionDtls> getEmpdeducDtls(long empId,long deducCode,long empCurrentStatus,int month,int year) {
		logger.info("executing getEmpallowMpgNew method:");
		HrPayDeductionDtls hrPayEmpDeducMpg = new HrPayDeductionDtls();
		Session hibSession = getSession();
		String strQuery = "from HrPayDeductionDtls as objEmpAllowMap where objEmpAllowMap.hrPayDeducTypeMst.deducCode = "+deducCode
			+ " and objEmpAllowMap.empCurrentStatus ="+empCurrentStatus+" and objEmpAllowMap.month in (-1,"+month+") and objEmpAllowMap.year in (-1,"+year+") and objEmpAllowMap.hrEisEmpMst.empId="+empId+" order by objEmpAllowMap.month desc";
		Query query=hibSession.createQuery(strQuery);
		
		return query.list();
	}
	public List<HrPayDeductionDtls> getEmpdeducDtls(String empId,long deducCode,long empCurrentStatus,int month,int year) {
		logger.info("executing getEmpallowMpgNew method for checking PT is mapped or not:");
		HrPayDeductionDtls hrPayEmpDeducMpg = new HrPayDeductionDtls();
		Session hibSession = getSession();
		String strQuery = "select objEmpAllowMap.deducDtlsCode,objEmpAllowMap.hrEisEmpMst.empId from HrPayDeductionDtls as objEmpAllowMap where objEmpAllowMap.hrPayDeducTypeMst.deducCode = "+deducCode
			+ " and objEmpAllowMap.empCurrentStatus ="+empCurrentStatus+" and objEmpAllowMap.month in (-1,"+month+") and objEmpAllowMap.year in (-1,"+year+") and objEmpAllowMap.hrEisEmpMst.empId in ("+empId+") order by objEmpAllowMap.month desc";
		Query query=hibSession.createQuery(strQuery);
		
		return query.list();
	}
	public List<HrPayEmpallowMpg> getEmpallowMpg(long empId,long allowCode,long empCurrentStatus,int month,int year) {
		HrPayEmpallowMpg hrPayEmpallowMpg = new HrPayEmpallowMpg();
		Session hibSession = getSession();
		
		Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, year);
        cal2.set(Calendar.MONTH, month);
        
        int maxDays = cal2.getActualMaximum(5);
        
        cal2.set(Calendar.DATE, maxDays);
        
        java.util.Date currDate1 = cal2.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        
        String currDate = sdf.format(currDate1);
        
        logger.info("the date is "+currDate);
		
		String strQuery = "from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.empCurrentStatus ="+empCurrentStatus+" and objEmpAllowMap.hrEisEmpMst.empId="+empId+
			" and objEmpAllowMap.updatedDate <= '"+currDate+"' order by objEmpAllowMap.updatedDate desc ";
			;
		Query query=hibSession.createQuery(strQuery);
		
		logger.info("ther query is "+query);
		
		return query.list();
	}
	
	public List<HrPayEmpallowMpgHst> getEmpallowMpgFromHst(long empId,long allowCode,long empCurrentStatus,int month,int year) {
		HrPayEmpallowMpg hrPayEmpallowMpg = new HrPayEmpallowMpg();
		Session hibSession = getSession();
		
		Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, year);
        cal2.set(Calendar.MONTH, month);
        
        int maxDays = cal2.getActualMaximum(5);
        
        cal2.set(Calendar.DATE, maxDays);
        
        java.util.Date currDate1 = cal2.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        
        String currDate = sdf.format(currDate1);
        
        logger.info("the date is "+currDate);
		
		String strQuery = "from HrPayEmpallowMpgHst as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.empCurrentStatus ="+empCurrentStatus+" and objEmpAllowMap.hrEisEmpMst.empId="+empId+
			" and objEmpAllowMap.updatedDate <= '"+currDate+"' order by objEmpAllowMap.updatedDate desc,objEmpAllowMap.id.trnCounter desc ";
			;
		Query query=hibSession.createQuery(strQuery);
		
		logger.info("ther query is "+query);
		
		return query.list();
	}
	
	 
	public List getAllAllowanceType(long payCommissionId,long locId)
    {
		List allowanceLst=new ArrayList();
        Session hibSession = getSession();
        String queryStr ="select hrEmpAllMap.allowCode,hrEmpAllMap.allowDisplayName from HrPayAllowTypeMst as hrEmpAllMap " +
        		" where hrEmpAllMap.payCommissionId in ("+ payCommissionId +  ",-1) and hrEmpAllMap.allowCode in (" +
        				"select deptCompo.compId from HrPayLocComMpg as deptCompo,HrEisEmpCompMpg as empCompo where deptCompo.hrpaycompgrpmst.isactive=1 and  deptCompo.cmnLookupMst.lookupId=2500134 and " +
        				"deptCompo.hrpaycompgrpmst.cmnLocationMst.locId=" + locId +
        				" and isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)" + 
        		" order by hrEmpAllMap.sequence_no ";
        Query query= hibSession.createQuery(queryStr);
        
        logger.info("check this query::::::::::::::");
        allowanceLst = query.list();
		return allowanceLst;
    }
	
	public List getAllAllowanceType()
    {
		List allowanceLst=new ArrayList();
        Session hibSession = getSession();
        String queryStr ="select hrEmpAllMap.allowCode,hrEmpAllMap.allowDisplayName from HrPayAllowTypeMst as hrEmpAllMap " +        		
        		" order by hrEmpAllMap.sequence_no ";
        Query query= hibSession.createQuery(queryStr);
        allowanceLst = query.list();
		return allowanceLst;
    }
	
	
	public List getAllAllowanceTypeMst(long payCommissionId,long locId)
    {
		List allowanceLst=new ArrayList();
        Session hibSession = getSession();
        String queryStr ="select hrEmpAllMap from HrPayAllowTypeMst as hrEmpAllMap " +
        		" where hrEmpAllMap.payCommissionId in ("+ payCommissionId +  ",-1) and hrEmpAllMap.allowCode in (" +
        				//"select deptCompo.compId from HrPayLocComMpg as deptCompo,HrEisEmpCompMpg as empCompo where deptCompo.cmnLookupMst.lookupId=2500134 and deptCompo.hrpaycompgrpmst.isactive=1 and " +
        		"select deptCompo.compId from HrPayLocComMpg as deptCompo where deptCompo.cmnLookupMst.lookupId=2500134 and deptCompo.hrpaycompgrpmst.isactive=1 and " +
        				" deptCompo.hrpaycompgrpmst.cmnLocationMst.locId=" + locId +
        				//" and deptCompo.isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)" +
        				" and deptCompo.isactive=1 )" +
        		" order by hrEmpAllMap.sequence_no ";
        logger.info("check this query::::::::::::::too ");
        Query query= hibSession.createQuery(queryStr);
        allowanceLst = query.list();
		return allowanceLst;
    }
	
	public List getAllAllowanceTypeMstForAllowTypeMst(long payCommissionId,long locId,long empId)
    {
		List allowanceLst=new ArrayList();
        Session hibSession = getSession();
        /*String queryStr ="select hrEmpAllMap from HrPayAllowTypeMst as hrEmpAllMap " +
        		" where hrEmpAllMap.payCommissionId in ("+ payCommissionId +  ",-1) and hrEmpAllMap.allowCode in (" +
        			
        		"select empCompo.compId from HrEisEmpCompMpg as empCompo where empCompo.cmnLookupMst.lookupId=2500134 and empCompo.isactive = 1 and empCompo.hrEisEmpCompGrpMst.isactive =1" +
        		" and empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.empId ="+empId+") order by hrEmpAllMap.sequence_no ";*/
        
        StringBuffer queryBuff = new StringBuffer();
        queryBuff.append(" select hrEmpAllMap from HrPayAllowTypeMst as hrEmpAllMap, HrEisEmpCompMpg as empCompo ");
        queryBuff.append(" where hrEmpAllMap.payCommissionId in (").append(payCommissionId).append(",-1) ");
        queryBuff.append(" and hrEmpAllMap.allowCode = empCompo.compId ");
        queryBuff.append(" and empCompo.cmnLookupMst.lookupId=2500134 and empCompo.isactive = 1 ");
        queryBuff.append(" and empCompo.hrEisEmpCompGrpMst.isactive =1 ");
        queryBuff.append(" and empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.empId = ").append(empId);
        queryBuff.append(" order by hrEmpAllMap.sequence_no ");
        
        String queryStr = queryBuff.toString();
        
        logger.info("check this query::::::::::::::too ");
        Query query= hibSession.createQuery(queryStr);
        allowanceLst = query.list();
		return allowanceLst;
    }
    
    public List getAllAllowanceDetailsForAllowTypeMst(long payCommissionId,long locId,long empId)
    {
		List allowanceLst=null;
		
		List allowanceDetaislList = new ArrayList();
        Session hibSession = getSession();
        ResourceBundle rsPayroll = ResourceBundle.getBundle("resources.Payroll");
        long allowanceComponent = Long.parseLong(rsPayroll.getString("allowanceComponent").toString());
        /*String queryStr ="select hrEmpAllMap from HrPayAllowTypeMst as hrEmpAllMap " +
        		" where hrEmpAllMap.payCommissionId in ("+ payCommissionId +  ",-1) and hrEmpAllMap.allowCode in (" +
        			
        		"select empCompo.compId from HrEisEmpCompMpg as empCompo where empCompo.cmnLookupMst.lookupId=2500134 and empCompo.isactive = 1 and empCompo.hrEisEmpCompGrpMst.isactive =1" +
        		" and empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.empId ="+empId+") order by hrEmpAllMap.sequence_no ";*/
        
        StringBuffer queryBuff = new StringBuffer();
        queryBuff.append(" select hrEmpAllMap.allowCode, hrEmpAllMap.allowDesc, hrEmpAllMap.allowName, edpCompo.rltBillTypeEdp.edpCode from HrPayAllowTypeMst as hrEmpAllMap, HrEisEmpCompMpg as empCompo, ");
        queryBuff.append(" HrPayEdpCompoMpg edpCompo ");
        queryBuff.append(" where hrEmpAllMap.payCommissionId in (").append(payCommissionId).append(",-1) ");
        queryBuff.append(" and hrEmpAllMap.allowCode = empCompo.compId ");
        queryBuff.append(" and empCompo.cmnLookupMst.lookupId=2500134 and empCompo.isactive = 1 ");
        queryBuff.append(" and empCompo.hrEisEmpCompGrpMst.isactive =1 ");
        queryBuff.append(" and empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.empId = ").append(empId);
        queryBuff.append(" and edpCompo.typeId = hrEmpAllMap.allowCode ");
        queryBuff.append(" and edpCompo.cmnLookupId = ").append(allowanceComponent);
        queryBuff.append(" order by hrEmpAllMap.sequence_no ");
        
        String queryStr = queryBuff.toString();
        
        logger.info("check this query::::::::::::::too ");
        Query query= hibSession.createQuery(queryStr);
        allowanceLst = query.list();
        
        CommonAllowanceDetailsVO allowDetailsVO = null;
        
        Iterator allowItr = allowanceLst.iterator();
        
        while(allowItr.hasNext())
        {
        	allowDetailsVO = new CommonAllowanceDetailsVO();
        	
        	Object row[] = (Object[])allowItr.next();
        	
        	allowDetailsVO.setAllowCode((Long)row[0]);
        	allowDetailsVO.setAllowDesc((String)row[1]);
        	allowDetailsVO.setAllowName((String)row[2]);
        	allowDetailsVO.setEdpCode((String)row[3]);
        	
        	allowanceDetaislList.add(allowDetailsVO);
        	
        }
		return allowanceDetaislList;
    }
	
	public List getAllAllowanceTypeMst()
    {
		List<HrPayAllowTypeMst> allowanceLst=new ArrayList();
        Session hibSession = getSession();
        String queryStr ="select hrEmpAllMap from HrPayAllowTypeMst as hrEmpAllMap " +        		
        		" order by hrEmpAllMap.sequence_no ";
        Query query= hibSession.createQuery(queryStr);
        allowanceLst = query.list();
		return allowanceLst;
    }
	
	/*public List getAllowListfromDeptCompMpg(String lStrMappedComoId)
	{
		List AllowMappedList = new ArrayList();
		Session hibsession = getSession();

		String HQL_QUERY = "select allowtypemst from HrPayAllowTypeMst allowtypemst where allowtypemst.allowCode in("+lStrMappedComoId+")"
		+" order by allowtypemst.payCommissionId asc";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getAllowListfromDeptCompMpg query :: "+query);
		AllowMappedList = query.list();
		logger.info("====> AllowMappedList :: "+AllowMappedList.size());
		return AllowMappedList;
	}*/
	/*public List getMappedCompoIDfromCompMpg(long locid)
	{
		List AllowMappedList = new ArrayList();
		Session hibsession = getSession();

		String HQL_QUERY = "select hrpaylocmpg from HrPayLocComMpg hrpaylocmpg where hrpaylocmpg.hrpaycompgrpmst.cmnLocationMst.locId="+locid+
							" and hrpaylocmpg.cmnLookupMst.lookupId=2500135 and hrpaylocmpg.isactive=1 and  hrpaylocmpg.hrpaycompgrpmst.isactive=1";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getAllowListfromDeptCompMpg query :: "+query);
		AllowMappedList = query.list();
		logger.info("====> AllowMappedList :: "+AllowMappedList.size());
		return AllowMappedList;
	}*/
	public long getAmountForAllowance(long allw_id,long empId)
	{
	  long amount=0;
	  String amt="";
	 // String temp="";
	  List amntList=new ArrayList();
	  
	  String query="select EMP_ALLOW_AMOUNT  from HR_PAY_EMPALLOW_MPG where EMP_ALLOW_ID ="+allw_id+"and emp_id ="+empId;
	  Session hibsession = getSession();
	  Query sqlQuery = hibsession.createSQLQuery(query);
	  amntList = sqlQuery.list();
	  if(amntList != null && amntList.size()>0)
	  {
		  amt=amntList.get(0).toString();
		//  temp=amt;
		  if(amt.contains("."))
		  {
		  int i=amt.indexOf(".");
		  logger.info("index i is "+i);
		  amount=Long.parseLong(amt.substring(0,i));
		  }
		  else
			  amount=Long.parseLong(amt);

	  }
	  return amount;
	}
	

	public List getAllAllowanceTypeForDept()
	    {
			List allowanceLst=new ArrayList();
	        Session hibSession = getSession();
	        String queryStr ="from HrPayAllowTypeMst as hrEmpAllMap order by hrEmpAllMap.allowDisplayName asc";
	        Query query= hibSession.createQuery(queryStr);
	        allowanceLst = query.list();
			return allowanceLst;
	    }
	public List getMappedCompoIDfromCompMpg(long locid)//For Emp Comp Mpg Screen
	{
		List AllowMappedList = new ArrayList();
		Session hibsession = getSession();
		
		//String HQL_QUERY = "select hrpaylocmpg from HrPayLocComMpg hrpaylocmpg,HrPayCompGrpMst hrpaycompgrpmst where hrpaycompgrpmst.cmnLocationMst.locId="+locid+
		//					" and hrpaylocmpg.cmnLookupMst.lookupId=2500134 and hrpaylocmpg.isactive=1";
		
		String HQL_QUERY = "select hrpaylocmpg from HrPayLocComMpg hrpaylocmpg,HrPayCompGrpMst hrpaycompgrpmst where hrpaycompgrpmst.cmnLocationMst.locId="+locid+
		" and hrpaylocmpg.cmnLookupMst.lookupId=2500134 and hrpaylocmpg.isactive=1"
		+" and hrpaylocmpg.hrpaycompgrpmst.compoGrpId=hrpaycompgrpmst.compoGrpId and hrpaycompgrpmst.isactive=1";
		
		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getMappedCompoIDfromCompMpg locID query :: "+query);
		AllowMappedList = query.list();
		logger.info("====> AllowMappedList :: "+AllowMappedList.size());
		return AllowMappedList;
	}
	public List getAllowListfromDeptCompMpg(long PayID,String lStrMappedComoId)//For Emp Comp Mpg Screen
	{
		List AllowMappedList = new ArrayList();
		Session hibsession = getSession();
		
		String HQL_QUERY = "select allowtypemst from HrPayAllowTypeMst allowtypemst where allowtypemst.payCommissionId in (-1,"+PayID+") and allowtypemst.allowCode in("+lStrMappedComoId+")"
		+" order by allowtypemst.allowDisplayName asc";
		
		Query query = hibsession.createQuery(HQL_QUERY);
		
		
		logger.info("===> getAllowListfromDeptCompMpg For PAyID query :: "+query);
		
		
		AllowMappedList = query.list();
		logger.info("====> AllowMappedList :: "+AllowMappedList.size());
		return AllowMappedList;
	}
	/*public HrPayEmpallowMpg getHrPayEmpallowMpg(long empId,long allowCode,int month,int year) {
		List lst=new ArrayList();
		HrPayEmpallowMpg hrPayEmpallowMpg = new HrPayEmpallowMpg();
		Session hibSession = getSession();
		String strQuery = "from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.hrEisEmpMst.orgEmpMst.empId="+empId+" and  objEmpAllowMap.month = "+month+" and objEmpAllowMap.year="+year;
		Query query=hibSession.createQuery(strQuery);
		hrPayEmpallowMpg= (HrPayEmpallowMpg)query.uniqueResult();
		lst=query.list();
		hrPayEmpallowMpg=(HrPayEmpallowMpg)lst.get(0);
		return hrPayEmpallowMpg;
	}*/
	
	
	//added by manish
	/*public List getAllAllowanceType(long payCommissionId,long locId,long empId)
    {
		List allowanceLst=new ArrayList();
        Session hibSession = getSession();
        String queryStr ="";
        if(empId!=0)
        {
        	  queryStr ="select hrEmpAllMap.allowCode,hrEmpAllMap.allowDisplayName from HrPayAllowTypeMst as hrEmpAllMap  where " +
        		"  hrEmpAllMap.payCommissionId in (2500340,-1) and hrEmpAllMap.allowCode in " +
        		" (select empCompo.compId from  HrEisEmpCompMpg as empCompo where empCompo.hrEisEmpCompGrpMst.isactive=1 and empCompo.hrEisEmpCompGrpMst.orgEmpMst.empId=" +empId +" and empCompo.cmnLookupMst.lookupId=2500134 and empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.cmnLocationMst.locId=300022 and empCompo.isactive=1 ) order by hrEmpAllMap.sequence_no ";
        }
        else
        {
        	logger.info("problem!!!!!!!! Emp Id is 0...");
        	  queryStr ="select hrEmpAllMap.allowCode,hrEmpAllMap.allowDisplayName from HrPayAllowTypeMst as hrEmpAllMap  where " +
    		"  hrEmpAllMap.payCommissionId in (2500340,-1) and hrEmpAllMap.allowCode in (" +
    		"select deptCompo.compId from HrPayLocComMpg as deptCompo,HrEisEmpCompMpg as empCompo where deptCompo.hrpaycompgrpmst.isactive=1 and  deptCompo.cmnLookupMst.lookupId=2500134 and " +
			"deptCompo.hrpaycompgrpmst.cmnLocationMst.locId=" + locId +
			" and isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)" + 
			" order by hrEmpAllMap.sequence_no ";
        }
        				"select deptCompo.compId from HrPayLocComMpg as deptCompo,HrEisEmpCompMpg as empCompo where deptCompo.hrpaycompgrpmst.isactive=1 and  deptCompo.cmnLookupMst.lookupId=2500134 and " +
        				"deptCompo.hrpaycompgrpmst.cmnLocationMst.locId=" + locId +
        				" and isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)" + 
        		" order by hrEmpAllMap.sequence_no ";
        Query query= hibSession.createQuery(queryStr);
        
        logger.info("check this query::::::::::::::");
        allowanceLst = query.list();
		return allowanceLst;
    }*/
	//ended
	
	public HrPayEmpallowMpg getHrPayEmpallowMpg(long empId,long allowCode,int month,int year) {
		HrPayEmpallowMpg hrPayEmpallowMpg = new HrPayEmpallowMpg();
		Session hibSession = getSession();
		String strQuery = "from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.hrEisEmpMst.orgEmpMst.empId="+empId+" and  objEmpAllowMap.month = "+month+" and objEmpAllowMap.year="+year;
		Query query=hibSession.createQuery(strQuery);
		hrPayEmpallowMpg= (HrPayEmpallowMpg)query.uniqueResult();
		return hrPayEmpallowMpg;
}
public boolean checkHrPayEmpallowMpg(long empId,long allowCode,int month,int year) {
		
		Session hibSession = getSession();
		String strQuery = "from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.hrEisEmpMst.empId="+empId+" and  objEmpAllowMap.month = "+month+" and objEmpAllowMap.year="+year;
		Query query=hibSession.createQuery(strQuery);
		if(query.list()!=null && query.list().size()>0)
		{	
			return true;
		}
		else
		{
			return false;	
		}
		
	}

public List getAllAllowanceType(long payCommissionId,long locId,long empId)
{
	List allowanceLst=new ArrayList();
    Session hibSession = getSession();
    String queryStr ="";
    if(empId!=0)
    {
    	  queryStr ="select hrEmpAllMap.allowCode,hrEmpAllMap.allowDisplayName from HrPayAllowTypeMst as hrEmpAllMap  where " +
    	//	"  hrEmpAllMap.payCommissionId in (2500340,-1) and hrEmpAllMap.allowCode in " +
    		"  hrEmpAllMap.payCommissionId in ("+payCommissionId+",-1) and hrEmpAllMap.allowCode in " +
    		" (select empCompo.compId from  HrEisEmpCompMpg as empCompo where empCompo.hrEisEmpCompGrpMst.isactive=1 "+
    		" and empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.orgEmpMst.empId=" +empId +" and empCompo.cmnLookupMst.lookupId=2500134 "+
    		//" and empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.cmnLocationMst.locId="+locId+" and empCompo.isactive=1 ) order by hrEmpAllMap.sequence_no ";
    		"  and empCompo.isactive=1 ) order by hrEmpAllMap.sequence_no ";
    }
    else
    {
    	logger.info("problem!!!!!!!! Emp Id is 0...");
    	  queryStr ="select hrEmpAllMap.allowCode,hrEmpAllMap.allowDisplayName from HrPayAllowTypeMst as hrEmpAllMap  where " +
		"  hrEmpAllMap.payCommissionId in ("+payCommissionId+",-1) and hrEmpAllMap.allowCode in (" +
		"select deptCompo.compId from HrPayLocComMpg as deptCompo,HrEisEmpCompMpg as empCompo where deptCompo.hrpaycompgrpmst.isactive=1 and  deptCompo.cmnLookupMst.lookupId=2500134 and " +
		"deptCompo.hrpaycompgrpmst.cmnLocationMst.locId=" + locId +
		//" and isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)" +
		//" and isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)" +
		" order by hrEmpAllMap.sequence_no ";
    }
    				/*"select deptCompo.compId from HrPayLocComMpg as deptCompo,HrEisEmpCompMpg as empCompo where deptCompo.hrpaycompgrpmst.isactive=1 and  deptCompo.cmnLookupMst.lookupId=2500134 and " +
    				"deptCompo.hrpaycompgrpmst.cmnLocationMst.locId=" + locId +
    				" and isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)" + 
    		" order by hrEmpAllMap.sequence_no ";*/
    Query query= hibSession.createQuery(queryStr);
    
    logger.info("check this query::::::::::::::"+query);
   
    allowanceLst = query.list();
	return allowanceLst;
}

public long getSumOfAllAllowanceValuesByOrgEmpId(long payCommissionId,long locId,long empId)
{
	long sumAllowance = 0;
	
	Session hibSession = getSession();
	
	StringBuffer queryBuff = new StringBuffer();
    queryBuff.append(" select empAllow.empAllowAmount from HrPayAllowTypeMst as hrEmpAllMap, HrEisEmpCompMpg as empCompo, ");
    //queryBuff.append(" HrPayEdpCompoMpg edpCompo ");
    queryBuff.append(" HrPayEmpallowMpg empAllow ");
    queryBuff.append(" where hrEmpAllMap.payCommissionId in (").append(payCommissionId).append(",-1) ");
    queryBuff.append(" and hrEmpAllMap.allowCode = empCompo.compId ");
    queryBuff.append(" and empCompo.cmnLookupMst.lookupId=2500134 and empCompo.isactive = 1 ");
    queryBuff.append(" and empCompo.hrEisEmpCompGrpMst.isactive =1 ");
    queryBuff.append(" and empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.empId = ").append(empId);
    queryBuff.append(" and empAllow.hrPayAllowTypeMst.allowCode = hrEmpAllMap.allowCode ");
    queryBuff.append(" and empAllow.hrEisEmpMst.empId = empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.empId ");
    queryBuff.append(" and empAllow.month = -1 and empAllow.year = -1 ");
    //queryBuff.append(" and edpCompo.typeId = hrEmpAllMap.allowCode ");
    //queryBuff.append(" and edpCompo.cmnLookupId = ").append(allowanceComponent);
    queryBuff.append(" order by hrEmpAllMap.sequence_no ");
    
    Query amtQuery = hibSession.createQuery(queryBuff.toString());
    List amtList = amtQuery.list();
    
    Iterator amtIter = amtList.iterator();
    while(amtIter.hasNext())
    {
    	//Object row[] = (Object[]) 
    	double allowAmt = (Double)amtIter.next();
    	
    	long amount = Math.round(allowAmt);
    	
    	sumAllowance += amount;
    }
    
    
    return sumAllowance;
}
public List getAllAllowanceTypeMstEmpId(long payCommissionId,long empId,long month ,int year)
{
	List allowanceLst=new ArrayList();
    Session hibSession = getSession();
    String queryStr ="select hrEmpAllMap from HrPayAllowTypeMst as hrEmpAllMap " +
    		" where hrEmpAllMap.payCommissionId in ("+ payCommissionId +  ",-1) and hrEmpAllMap.allowCode in (" +
    		"select empCompo.compId from HrEisEmpCompMpg as empCompo where "+
    		" empCompo.cmnLookupMst.lookupId=2500134 and empCompo.isactive = 1 and empCompo.hrEisEmpCompGrpMst.EmpCompGrpId = ( "+
				" select max(a.EmpCompGrpId) from HrEisEmpCompGrpMst as a where  a.hrEisEmpMst.empId ="+empId+" and (a.month = (select max(b.month) from HrEisEmpCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+")  "+
					" or  "+
					"  (a.hrEisEmpMst.empId ="+empId+" and ( a.year = (select max(c.year) from HrEisEmpCompGrpMst as c where c.year<="+year+" and c.hrEisEmpMst.empId ="+empId
					+" and c.isactive = 1 ) and a.month =( select max(d.month) from HrEisEmpCompGrpMst as d where d.year = (select max(e.year) from HrEisEmpCompGrpMst as e where e.year<="+year+" and e.hrEisEmpMst.empId ="+empId+" and e.isactive = 1   ) and  d.hrEisEmpMst.empId ="+empId+")) "+
    		"))"+
    		") order by hrEmpAllMap.sequence_no ";
    logger.info("check this query::::::::::::::too right now...");
    Query query= hibSession.createQuery(queryStr);
    allowanceLst = query.list();
	return allowanceLst;
}

public List getAllAllowanceTypeMst(String empIdsStr)
{
	List allowanceLst=new ArrayList();
    Session hibSession = getSession();
    StringBuffer sb = new StringBuffer();
    sb.append("SELECT grpMst.EMP_ID,allowMst.ALLOW_CODE, allowMst.ALLOW_DESC FROM HR_PAY_ALLOW_TYPE_MST allowMst, HR_EIS_EMP_COMPONENT_MPG compoMpg, "); 
    sb.append("HR_EIS_EMP_COMPONENT_GRP_MST grpMst where "); 
    sb.append("compoMpg.COMPO_GROUP_ID = grpMst.EMP_COMPO_GRP_ID and allowMst.ALLOW_CODE = compoMpg.COMPO_ID and COMPO_TYPE=2500134 and compoMpg.IS_ACTIVE=1 "); 
    sb.append(" and grpMst.IS_ACTIVE=1  and grpMst.EMP_ID in (");
    sb.append(empIdsStr);
    sb.append(") order by grpMst.EMP_ID,allowMst.SEQUENCE_NO");
    /*sb.append("                    (select hreisempco3_.EMP_ID,hreisempco3_.EMP_COMPO_GRP_ID "); 
    sb.append("                            from HR_EIS_EMP_COMPONENT_GRP_MST hreisempco3_ inner join "); 
    sb.append("                            (select hreisempco2_.EMP_ID, max(hreisempco2_.GRP_MONTH)GRP_MONTH,max(hreisempco2_.GRP_YEAR)GRP_YEAR from HR_EIS_EMP_COMPONENT_GRP_MST hreisempco2_ "); 
    sb.append("                            WHERE hreisempco2_.GRP_YEAR<= ");
    sb.append(year);
    sb.append("                            group by hreisempco2_.EMP_ID)COMPONENT_GROUP_DET on COMPONENT_GROUP_DET.EMP_ID=hreisempco3_.EMP_ID "); 
    sb.append("                            and COMPONENT_GROUP_DET.GRP_YEAR=hreisempco3_.GRP_YEAR "); 
    sb.append("                            and COMPONENT_GROUP_DET.GRP_MONTH=hreisempco3_.GRP_MONTH group by hreisempco3_.EMP_ID, hreisempco3_.EMP_COMPO_GRP_ID) COMPONENT_GROUP_ID ON "); 
    sb.append("                            COMPONENT_GROUP_ID.EMP_COMPO_GRP_ID=hreisempco1_.COMPO_GROUP_ID "); 
    sb.append("                            inner join HR_PAY_ALLOW_TYPE_MST hrpayallow0_ on "); 
    sb.append("                            hrpayallow0_.ALLOW_CODE=hreisempco1_.COMPO_ID "); 
    sb.append("                  where "); 
    sb.append("                    hreisempco1_.COMPO_TYPE=2500134 "); 
    sb.append("                    and hreisempco1_.IS_ACTIVE=1 )Allow_Details inner join "); 
    sb.append("                    (SELECT "); 
    sb.append("eisemp.EMP_ID as eis_emp_id "); 
    sb.append("    FROM "); 
    sb.append("        org_emp_mst orgemp, "); 
    sb.append("        hr_eis_emp_mst eisemp, "); 
    sb.append("        hr_eis_other_dtls dtls, "); 
    sb.append("        org_user_mst usermst, "); 
    sb.append("        ORG_USERPOST_RLT up, "); 
    sb.append("        ORG_POST_DETAILS_RLT pd, "); 
    sb.append("        HR_PAY_POST_PSR_MPG psr, "); 
    sb.append("        HR_EIS_SGD_MPG sgd, "); 
    sb.append("        HR_EIS_GD_MPG gd, "); 
    sb.append("        HR_EIS_SCALE_MST scale, "); 
    sb.append("        MST_DCPS_EMP dcpsEmp, "); 
    sb.append("        org_grade_mst gradeMst "); 
    sb.append("    where "); 
    sb.append("        gradeMst.GRADE_ID = gd.SGD_GRADE_ID "); 
    sb.append("        and orgemp.GRADE_ID=gradeMst.GRADE_ID "); 
    sb.append("        and   orgemp.EMP_ID = eisemp.EMP_MPG_ID "); 
    sb.append("        and dtls.EMP_ID=eisemp.EMP_ID "); 
    sb.append("        and orgemp.USER_ID=usermst.USER_ID "); 
    sb.append("        and usermst.ACTIVATE_FLAG=1 "); 
    sb.append("        and orgemp.EMP_SRVC_EXP >=  '");
    sb.append(givenDate);
    sb.append("'        and  orgemp.EMP_ID=dcpsemp.ORG_EMP_MST_ID "); 
    sb.append("        and orgemp.EMP_DOB <=  '");
    sb.append(givenDate);
    sb.append("'        and up.ACTIVATE_FLAG=1 "); 
    sb.append("        and up.USER_ID=usermst.USER_ID "); 
    sb.append("        and pd.POST_ID=up.POST_ID "); 
    sb.append("        and pd.LOC_ID= ");
    sb.append(locId);
    sb.append("        and pd.LANG_ID=1 "); 
    sb.append("        and psr.POST_ID=up.POST_ID "); 
    sb.append("        and psr.POST_ID = pd.POST_ID "); 
    sb.append("        and psr.BILL_NO= ");
    sb.append(billNo);
    sb.append("        and psr.LOC_ID= ");
    sb.append(locId);
    sb.append("        and   up.START_DATE <='");
    sb.append(givenDate);
    sb.append("'        and dtls.EMP_SGD_ID=sgd.SGD_MAP_ID "); 
    sb.append("        and gd.GD_MAP_ID = sgd.SGD_GD_ID "); 
    sb.append("        and sgd.SGD_SCALE_ID=scale.SCALE_ID "); 
    sb.append("        and  ( "); 
    sb.append("            up.END_DATE >='");
    sb.append(givenDate);
    sb.append("'            or up.END_DATE is null "); 
    sb.append("        ) "); 
    sb.append(")EMP_LIST on EMP_LIST.eis_emp_id=ALLOW_DETAILS.EMP_ID "); 
    sb.append("order by ALLOW_DETAILS.EMP_ID,ALLOW_DETAILS.SEQUENCE_NO ");*/
    logger.info("Query for getting mapped employee components");
    Query query= hibSession.createSQLQuery(sb.toString());
    allowanceLst = query.list();
	return allowanceLst;
}

/*public List getMappedAllowancesList(String empIdStr,long locId){
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	List allowanceList =new ArrayList();
    Session hibSession = getSession();
    StringBuffer sb = new StringBuffer();
    sb.append(rb.getString("getMappedAllowances1"));
    sb.append(rb.getString("getMappedAllowances2"));
    sb.append(rb.getString("getMappedAllowances3"));
    sb.append(" ");
    sb.append(rb.getString("getMappedAllowances4"));
    sb.append(rb.getString("getMappedAllowances5"));
    sb.append(rb.getString("getMappedAllowances6"));
    sb.append(empIdStr);
    sb.append(rb.getString("getMappedAllowances7"));
    
    logger.info("Location Id to Get Allowances is :"+locId);
    logger.info("Query for getting all allowance "+sb.toString());
    Query query= hibSession.createSQLQuery(sb.toString());
    
    query.setParameter("locId", locId);
    allowanceList = query.list();
    return allowanceList;
}*/
public List getMappedAllowancesList(String empIdStr,long locId,long billNo,Date givenDate){
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	List allowanceList =new ArrayList();
    Session hibSession = getSession();
    StringBuffer sb = new StringBuffer();
    sb.append(rb.getString("getMappedAllowances1"));
    sb.append(rb.getString("getEmployeeList"));
    sb.append(rb.getString("getMappedAllowances2"));
    sb.append(rb.getString("getMappedAllowances3"));
    sb.append(" ");
    sb.append(rb.getString("getMappedAllowances4"));
    sb.append(rb.getString("getMappedAllowances5"));
    sb.append(rb.getString("getMappedAllowances6"));
    //sb.append(empIdStr);
   // sb.append(rb.getString("getMappedAllowances7"));
    
    logger.info("Location Id to Get Allowances is :"+locId);
    logger.info("Query for getting all allowance "+sb.toString());
    Query query= hibSession.createSQLQuery(sb.toString());
    
    query.setParameter("locId", locId);
    query.setParameter("billNo", billNo);
    query.setParameter("givenDate", givenDate);
    
    allowanceList = query.list();
    return allowanceList;
}

public long getAmountForAllowance(long allw_id,long empId,int month ,int year)
{
  long amount=0;
  String amt="";
 // String temp="";
  List amntList=new ArrayList();
  
  String query="select EMP_ALLOW_AMOUNT  from HR_PAY_EMPALLOW_MPG where EMP_ALLOW_ID ="+allw_id+" and emp_id ="+empId+" and month in (-1) and year in (-1)";
  Session hibsession = getSession();
  Query sqlQuery = hibsession.createSQLQuery(query);
  amntList = sqlQuery.list();
  if(amntList != null && amntList.size()>0)
  {
	  amt=amntList.get(0).toString();
	//  temp=amt;
	  if(amt.contains("."))
	  {
	  int i=amt.indexOf(".");
	  logger.info("index i is "+i);
	  amount=Long.parseLong(amt.substring(0,i));
	  }
	  else
		  amount=Long.parseLong(amt);

  }
  return amount;
}


public List getAllowListfromDeptCompMpg(String lStrMappedComoId)
	{
		List AllowMappedList = new ArrayList();
		Session hibsession = getSession();

		String HQL_QUERY = "select allowtypemst from HrPayAllowTypeMst allowtypemst where allowtypemst.allowCode in("+lStrMappedComoId+")"
		+" order by allowtypemst.allowName asc";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getAllowListfromDeptCompMpg query :: "+query);
		AllowMappedList = query.list();
		logger.info("====> AllowMappedList :: "+AllowMappedList.size());
		return AllowMappedList;
	}


//added by Manish
public HrPayEmpallowMpg getHrPayEmpallowMpgByHremp(long empId,long allowCode,int month,int year) {
	HrPayEmpallowMpg hrPayEmpallowMpg = new HrPayEmpallowMpg();
	Session hibSession = getSession();
	String strQuery = "from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
		+ " and objEmpAllowMap.hrEisEmpMst.orgEmpMst.empId="+empId+" and  objEmpAllowMap.month = "+month+" and objEmpAllowMap.year="+year;
	Query query=hibSession.createQuery(strQuery);
	hrPayEmpallowMpg= (HrPayEmpallowMpg)query.uniqueResult();
	return hrPayEmpallowMpg;
}
//ended

public List getAllowListforBulk(String lStrMappedComoId)//For Emp Comp Mpg Screen
{
	List AllowMappedList = new ArrayList();
	Session hibsession = getSession();
	
	String HQL_QUERY = "select allowtypemst from HrPayAllowTypeMst allowtypemst where allowtypemst.allowCode in("+lStrMappedComoId+")"
	+" and allowtypemst.allowCode not in (select expMst.hrPayAllowTypeMst.allowCode from HrPayExpressionMst as expMst) order by allowtypemst.allowDisplayName asc";
	
	Query query = hibsession.createQuery(HQL_QUERY);
	
	
	logger.info("===> getAllowListfromDeptCompMpg For PAyID query :: "+query);
	
	
	AllowMappedList = query.list();
	logger.info("====> AllowMappedList :: "+AllowMappedList.size());
	return AllowMappedList;
}
public List getAllAllowanceTypeMstEmpId(long payCommissionId,String empIds,long month ,int year)
{
	List allowanceLst=new ArrayList();
    Session hibSession = getSession();
    String queryStr ="select hrEmpAllMap from HrPayAllowTypeMst as hrEmpAllMap " +
    		" where hrEmpAllMap.allowCode in (" +
    		"select empCompo.compId from HrEisEmpCompMpg as empCompo where "+
    		" empCompo.cmnLookupMst.lookupId=2500134 and empCompo.isactive = 1 and empCompo.hrEisEmpCompGrpMst.EmpCompGrpId = ( "+
				" select max(a.EmpCompGrpId) from HrEisEmpCompGrpMst as a where  a.hrEisEmpMst.empId in ("+empIds+") and (a.month = (select max(b.month) from HrEisEmpCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+")  "+
					" or  "+
					"  (a.hrEisEmpMst.empId in ("+empIds+") and ( a.year = (select max(c.year) from HrEisEmpCompGrpMst as c where c.year<="+year+" ) and a.month =( select max(d.month) from HrEisEmpCompGrpMst as d where d.year = (select max(e.year) from HrEisEmpCompGrpMst as e where e.year<="+year+") and  d.hrEisEmpMst.empId in ("+empIds+"))) "+
    		"))"+
    		") order by hrEmpAllMap.sequence_no";
    logger.info("check this query::::::::::::::too right now...");
    Query query= hibSession.createQuery(queryStr);
    allowanceLst = query.list();
	return allowanceLst;
}

public List isComponenetMapped(String empIdStr,long compoType , long compoId,long locId,long billNo,Date givenDate){
	
	logger.info("executing isComponenetMapped method for checking "+compoId);
	
	Session hibSession = getSession();
	StringBuffer sb = new StringBuffer();
	sb.append("SELECT compoMpg.EMP_COMP_ID,compoGrp.EMP_ID FROM HR_EIS_EMP_COMPONENT_MPG compoMpg, HR_EIS_EMP_COMPONENT_GRP_MST  compoGrp inner join "); 
	sb.append(payrollBundle.getString("getEmployeeList").toString());
	sb.append(" on compoGrp.EMP_ID=emp_list.emp_id ");
	//sb.append(empIdStr);
	sb.append("  where compoGrp.IS_ACTIVE = 1 and compoGrp.EMP_COMPO_GRP_ID = compoMpg.COMPO_GROUP_ID and "); 
	sb.append("compoMpg.COMPO_TYPE =");
	sb.append(compoType);
	sb.append(" and compoMpg.COMPO_ID =");
	sb.append(compoId);
	sb.append(" and compoMpg.IS_ACTIVE = 1 ");

	Query query=hibSession.createSQLQuery(sb.toString());
	query.setParameter("locId", locId);
	query.setParameter("billNo", billNo);
	query.setParameter("givenDate",givenDate);
	return query.list();
	
}

	public int updateEmpAllowResult(double empAllowAmount, long postMstId, long userMstId, Date updDate, long allowCode, long empId, long month, long year)
	{
		Session hibSession = getSession();
		
		StringBuffer strQueryBuff = new StringBuffer(); 
			
		/*strQueryBuff.append("update HrPayEmpallowMpg as empAllow set ");
		strQueryBuff.append(" empAllow.empAllowAmount = :allowAmount, ");
		strQueryBuff.append(" empAllow.orgPostMstByUpdatedByPost.postId = :postMstId, ");
		strQueryBuff.append(" empAllow.orgUserMstByUpdatedBy.userId = :userMstId, ");
		strQueryBuff.append(" empAllow.updatedDate = :updDate ");
		strQueryBuff.append(" where empAllow.hrPayAllowTypeMst.allowCode = :allowCode ");
		strQueryBuff.append(" and empAllow.hrEisEmpMst.orgEmpMst.empId=:empId and  empAllow.month = :month and empAllow.year=:year");*/
		
		strQueryBuff.append("update HR_PAY_EMPALLOW_MPG  set ");
		strQueryBuff.append(" EMP_ALLOW_AMOUNT=:allowAmount, UPDATED_BY_POST=:postMstId, ");
		strQueryBuff.append(" UPDATED_BY=:userMstId, UPDATED_DATE=:updDate ");
		strQueryBuff.append(" where EMP_ALLOW_ID=:allowCode and emp_id=:empId and MONTH=:month and YEAR=:year");
		
		//Query updQuery = hibSession.createQuery(strQueryBuff.toString());
		Query updQuery = hibSession.createSQLQuery(strQueryBuff.toString());
		updQuery.setDouble("allowAmount", empAllowAmount);
		updQuery.setLong("postMstId", postMstId);
		updQuery.setLong("userMstId", userMstId);
		updQuery.setLong("allowCode", allowCode);
		updQuery.setLong("empId", empId);
		updQuery.setLong("month", month);
		updQuery.setLong("year", year);
		updQuery.setDate("updDate", updDate);
		
		int rowCount = updQuery.executeUpdate();
		
		return rowCount;
	}
	
	public int insertEmpAllowResult(long allowCode, long empId, long empAllowId, double allowAmt, long empCurrStatus,
			long locId, long dbId, long createdBy, Date createdDate, long createdPost)
	{
		Session hibSession = getSession();
		
		int insertCount = 0;
		
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append(" insert into HR_PAY_EMPALLOW_MPG "); 
		queryBuff.append(" (ALLOW_CODE,EMP_ID,EMP_ALLOW_ID,EMP_ALLOW_AMOUNT,EMP_CURRENT_STATUS,LOC_ID,DB_ID,CREATED_BY, "); 
		queryBuff.append(" CREATED_DATE,CREATED_BY_POST,UPDATED_BY,UPDATED_DATE,UPDATED_BY_POST,TRN_COUNTER,month,year)  "); 
		queryBuff.append(" values  "); 
		queryBuff.append(" (:allowCode,:empId,:empAllowId,:allowAmt,:empCurrStatus,:locId,:dbId,:createdBy,:createdDate,:createdPost,null,null,null,1,-1,-1)");
		
		Query insertQuery = hibSession.createSQLQuery(queryBuff.toString());
		insertQuery.setLong("allowCode", allowCode);
		insertQuery.setLong("empId", empId);
		insertQuery.setLong("empAllowId", empAllowId);
		insertQuery.setDouble("allowAmt", allowAmt);
		insertQuery.setLong("empCurrStatus", empCurrStatus);
		insertQuery.setLong("locId", locId);
		insertQuery.setLong("dbId", dbId);
		insertQuery.setLong("createdBy", createdBy);
		insertQuery.setDate("createdDate", createdDate);
		insertQuery.setLong("createdPost", createdPost);
		
		insertCount = insertQuery.executeUpdate();
		
		return insertCount;
	}
public Long getgpfSubscritptionsum(long empId, int year , int month) {
		

		logger.info("hii i m in finding checkEmpCount");
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		  int prevYear = year - 1;
		  int nxtYear = year + 1;
		sb.append("SELECT nvl(sum(b.GPF_D_ARR_MR + b.GPF_ABC_ARR_MR + b.GPF_GRP_D + b.GPF_GRP_ABC + b.SVNPC_GPF_ARR_DEDU + b.SVNPC_GPF_RECO + b.GPF_IAS + b.GPF_IFS + b.GPF_IAS_ARR_MR + b.GPF_IFS_ARR_MR),0) FROM PAYBILL_HEAD_MPG a inner join HR_PAY_PAYBILL b on a.PAYBILL_ID=b.PAYBILL_GRP_ID  where ");
		if(month < 4) {
			
			sb.append("(a.PAYBILL_MONTH in (1,2,3)  and a.PAYBILL_YEAR ="+year+" or a.PAYBILL_YEAR="+prevYear+" and a.PAYBILL_MONTH in (4,5,6,7,8,9,10,11,12)) and a.APPROVE_FLAG  = '1'"); 
			sb.append(" and b.EMP_ID = '"+empId+"'" );
		  }else {
			  sb.append(" (a.PAYBILL_MONTH in (4,5,6,7,8,9,10,11,12)  and a.PAYBILL_YEAR ="+year+" or a.PAYBILL_YEAR="+nxtYear+" and a.PAYBILL_MONTH in (1,2,3)) and a.APPROVE_FLAG  = '1' "); 
			  sb.append("  and b.EMP_ID = '"+empId+"'"); 
		  }
		
		Query query = session.createSQLQuery(sb.toString());
		logger.info("query is *************" + query.toString());
		String count = query.uniqueResult().toString();
		Long totalEmp = Long.valueOf(Long.parseLong(count));
		logger.info("chkAmtExceed---" + totalEmp);
		return totalEmp.longValue();

}
public int chkEmpAlreadyExists(long hrEisEmpId, long compId) {
	int count = 0;
	Session hibSession = getSession();
	
	StringBuffer queryBuff = new StringBuffer();
	queryBuff.append(" SELECT count(*) FROM HR_PAY_DEDUCTION_DTLS where EMP_ID = '"+hrEisEmpId+"' and EMP_DEDUC_ID = "+compId+"" );
	
	Query insertQuery = hibSession.createSQLQuery(queryBuff.toString());
		count = (int) insertQuery.uniqueResult();
	
	return count;
	
}
public Long getDcpsEmp(long eisEmpId) {
    logger.error("hii i m in finding checkEmpCount");
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    sb.append("SELECT emp.REG_STATUS FROM mst_dcps_emp emp inner join HR_EIS_EMP_MST eis on emp.ORG_EMP_MST_ID = eis.EMP_MPG_ID where eis.emp_id = "+eisEmpId+" ");
    Query query = session.createSQLQuery(sb.toString());
    logger.error("query is *************"+query.toString());
    String status = query.uniqueResult().toString();
    Long regStatus = Long.valueOf(Long.parseLong(status));
    logger.error("regStatus" + regStatus);
    return regStatus.longValue();
}
}