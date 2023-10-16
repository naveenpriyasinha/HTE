package com.tcs.sgv.nps.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

 
 
public class UpdatePranNoDAOImpl extends GenericDaoHibernateImpl{
	private Session ghibSession = null;
	private final Logger gLogger = Logger.getLogger(getClass());
	public UpdatePranNoDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
		// TODO Auto-generated constructor stub
	}
	public List testPranNO(String pranno)
	{
		
		StringBuffer stbuff = new StringBuffer();
		List<Long>  lstpn = new ArrayList();
		//Boolean flag  = false;
		stbuff.append("select emp_name,dcps_id,dcps_Emp_Id from Mst_DCPS_EMP where PRAN_NO =:pranNO");
	    logger.info("Inside testPran"+stbuff.toString());
	
		Query lstQuery = ghibSession.createSQLQuery(stbuff.toString());
		lstQuery.setString("pranNO",pranno);
		
		lstpn = lstQuery.list();
		 
		return lstpn;
		
	}


public List getAllEmp(String lStrSevaarthId ,String lStrEmployeeName, String lStrDcpsId, String lStrPpanNo,String ddoCode){
	
	List lLstEmpDeselect = null;
	StringBuilder  Strbld = new StringBuilder();
	try {//DESIG_DESC
		Strbld.append("select DISTINCT dcps.EMP_NAME, dcps.DCPS_ID, dcps.SEVARTH_ID,desig.DSGN_NAME,to_char(dcps.DOJ,'dd-MM-yyyy'), to_char(dcps.DOB,'dd-MM-yyyy'),nvl(dcps.PPAN,' '),nvl(dcps.PRAN_NO,' '),dcps.ddo_code,case when (post.end_date > sysdate or post.end_date is null)  then 1 else 0 end,nvl(to_char(post.end_date,'dd-MM-YYYY'),'01-01-1111'), user.END_DATE  from mst_dcps_emp dcps ");
		Strbld.append("inner join org_emp_mst emp on dcps.ORG_EMP_MST_ID=emp.EMP_ID ");
		Strbld.append("inner join ORG_USERPOST_RLT user on emp.USER_ID=user.USER_ID  and user.ACTIVATE_FLAG =1 ");
		Strbld.append("inner join ORG_POST_MST post on user.POST_ID=post.POST_ID AND post.activate_flag=1 "); //and (post.end_date > sysdate or post.end_date is null) 
		Strbld.append("inner join org_designation_mst desig on post.DSGN_CODE=desig.DSGN_ID ");//and desig.FIELD_DEPT_ID=dcps.PARENT_DEPT 
		Strbld.append("inner join rlt_zp_ddo_map rlt on dcps.ddo_code=rlt.zp_ddo_code ");
		//mst_payroll_designation
		Strbld.append("where dcps.pran_no is null  and (dcps.EMP_SERVEND_DT > sysdate or dcps.EMP_SERVEND_DT is null) and dcps.ZP_STATUS=10  and dcps.DCPS_OR_GPF='Y' and rlt.rept_ddo_code = '"+ddoCode+"'  ");
		
		if (lStrEmployeeName != null && !"".equals(lStrEmployeeName)) {
			Strbld.append(" AND UPPER(dcps.EMP_NAME) = :lStrEmpName");
		}
		if (lStrSevaarthId != null && !"".equals(lStrSevaarthId)) {
			Strbld.append(" AND UPPER(dcps.SEVARTH_ID) = :sevarthId");
		}
		if (lStrDcpsId != null && !"".equals(lStrDcpsId)) {
			Strbld.append(" AND UPPER(dcps.DCPS_ID) = :dcpsId");
		}
		if (lStrPpanNo != null && !"".equals(lStrPpanNo)) {
			Strbld.append(" AND UPPER(dcps.PPAN) = :ppanNo");
		}
		
		
		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		if (lStrEmployeeName != null && !"".equals(lStrEmployeeName)) {
		lQuery.setString("lStrEmpName", lStrEmployeeName);
		}
		if (lStrSevaarthId != null && !"".equals(lStrSevaarthId)) {
		lQuery.setString("sevarthId", lStrSevaarthId);
		}
		if (lStrDcpsId != null && !"".equals(lStrDcpsId)) {
			lQuery.setString("dcpsId", lStrDcpsId);
			}
		if (lStrPpanNo != null && !"".equals(lStrPpanNo)) {
			lQuery.setString("ppanNo", lStrPpanNo);
		}
//		lQuery.setString("locId", locId);
		
		logger.info("query lstgetAllEmp ---------"+ Strbld.toString());
		lLstEmpDeselect = lQuery.list();
	}
	catch(Exception e)
  	{
  		logger.info("Error occured in lstgetAllEmp ---------"+ e);
  	}
	return lLstEmpDeselect;
}

public List getAllEmpActive(String lStrSevaarthId ,String lStrEmployeeName, String lStrDcpsId, String lStrPpanNo){
	
	List lLstEmpDeselect = null;
	List lLstEmpDeselect1 = null;
	

	StringBuilder  Strbld = new StringBuilder();
	try {//DESIG_DESC
		Strbld.append("select DISTINCT dcps.EMP_NAME, dcps.DCPS_ID, dcps.SEVARTH_ID,desig.DSGN_NAME,to_char(dcps.DOJ,'dd-MM-yyyy'), to_char(dcps.DOB,'dd-MM-yyyy'),nvl(dcps.PPAN,' '),nvl(dcps.PRAN_NO,' '),dcps.ddo_code,case when (post.end_date > sysdate or post.end_date is null)  then 1 else 0 end,nvl(to_char(post.end_date,'dd-MM-YYYY'),'01-01-1111'),user.END_DATE from mst_dcps_emp dcps ");
		Strbld.append("inner join org_emp_mst emp on dcps.ORG_EMP_MST_ID=emp.EMP_ID ");
		Strbld.append("inner join ORG_USERPOST_RLT user on emp.USER_ID=user.USER_ID and user.ACTIVATE_FLAG =0 and user.END_DATE is not null  ");
		Strbld.append("inner join ORG_POST_MST post on user.POST_ID=post.POST_ID AND post.activate_flag=1 "); //and (post.end_date > sysdate or post.end_date is null) 
		Strbld.append("inner join org_designation_mst desig on post.DSGN_CODE=desig.DSGN_ID ");//and desig.FIELD_DEPT_ID=dcps.PARENT_DEPT 
		//mst_payroll_designation
		Strbld.append("where dcps.pran_no is null  and (dcps.EMP_SERVEND_DT > sysdate or dcps.EMP_SERVEND_DT is null) and dcps.ZP_STATUS=10 and dcps.DCPS_OR_GPF='Y' ");
		
		if (lStrEmployeeName != null && !"".equals(lStrEmployeeName)) {
			Strbld.append(" AND UPPER(dcps.EMP_NAME) = :lStrEmpName");
		}
		if (lStrSevaarthId != null && !"".equals(lStrSevaarthId)) {
			Strbld.append(" AND UPPER(dcps.SEVARTH_ID) = :sevarthId");
		}
		if (lStrDcpsId != null && !"".equals(lStrDcpsId)) {
			Strbld.append(" AND UPPER(dcps.DCPS_ID) = :dcpsId");
		}
		if (lStrPpanNo != null && !"".equals(lStrPpanNo)) {
			Strbld.append(" AND UPPER(dcps.PPAN) = :ppanNo");
		}
		Strbld.append(" order by user.END_DATE desc ");

		
		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		if (lStrEmployeeName != null && !"".equals(lStrEmployeeName)) {
		lQuery.setString("lStrEmpName", lStrEmployeeName);
		}
		if (lStrSevaarthId != null && !"".equals(lStrSevaarthId)) {
		lQuery.setString("sevarthId", lStrSevaarthId);
		}
		if (lStrDcpsId != null && !"".equals(lStrDcpsId)) {
			lQuery.setString("dcpsId", lStrDcpsId);
			}
		if (lStrPpanNo != null && !"".equals(lStrPpanNo)) {
			lQuery.setString("ppanNo", lStrPpanNo);
		}
//		lQuery.setString("locId", locId);
		
		logger.info("query lstgetAllEmp ---------"+ Strbld.toString());
		lLstEmpDeselect = lQuery.list();
		
		
		lLstEmpDeselect1= new ArrayList();
		if(lLstEmpDeselect!=null && lLstEmpDeselect.size() != 0)
		{
			Object[] lObj = (Object[]) lLstEmpDeselect.get(0);
			lLstEmpDeselect1.add(lObj);

		}
		System.out.println("lLstEmpDeselect1 size is"+lLstEmpDeselect1.size());

	}
	catch(Exception e)
  	{
  		logger.info("Error occured in lstgetAllEmp ---------"+ e);
  	}
	return lLstEmpDeselect1;
}

public List checkSevaarthIdExist(String lStrSevaarthId,String lStrDDOCode, String strEmpname, String strDcpsId) {


	
	StringBuilder sb = new StringBuilder();
	Query selectQuery = null;
	Date lDtCurrDate = SessionHelper.getCurDate();

	sb.append(" SELECT nvl(emp.DCPS_OR_GPF,'A'), nvl(to_char(emp.EMP_SERVEND_DT,'dd-MM-yyyy'),'01-01-1900'), nvl(emp.AC_DCPS_MAINTAINED_BY,'NA'),"
			+ "nvl(to_char(emp.DOJ,'dd-MM-yyyy'),'01-01-1900'),nvl(emp.PRAN_NO,'#'),emp.ddo_code ,nvl(emp.ppan,'0') FROM ifms.mst_dcps_emp  as emp "
			+ "inner join ifms.RLT_ZP_DDO_MAP as rlt on rlt.ZP_DDO_CODE=emp.DDO_CODE where emp.form_status=1 "
			+ "and emp.EMP_SERVEND_DT>sysdate AND emp.ZP_STATUS = 10 and rlt.REPT_DDO_CODE ='"+lStrDDOCode+"'"); 
 
	if (!"".equals(lStrSevaarthId) && lStrSevaarthId != null) {
		sb.append(" AND UPPER(emp.SEVARTH_ID) = :lStrSevaarthId");
	}
	if (!"".equals(strEmpname) && strEmpname != null) {
		sb.append(" AND UPPER(emp.emp_name) = :strEmpname");
	}
	if (!"".equals(strDcpsId) && strDcpsId != null) {
		sb.append(" AND UPPER(emp.Dcps_Id) = :strDcpsId");
	}

	logger.info("Inside checkSevaarthIdExist query is *********** "+sb.toString());
	
	selectQuery = ghibSession.createSQLQuery(sb.toString());

	
	if (!"".equals(lStrSevaarthId) && lStrSevaarthId != null) {
		selectQuery.setParameter("lStrSevaarthId", lStrSevaarthId.trim().toUpperCase());
		
	}
	if (!"".equals(strEmpname) && strEmpname != null) {
		selectQuery.setParameter("strEmpname", strEmpname.trim().toUpperCase());
		
	}
	if (!"".equals(strDcpsId) && strDcpsId != null) {
		selectQuery.setParameter("strDcpsId", strDcpsId.trim().toUpperCase());
		
	}

	//selectQuery.setParameter("lStrDDOCode", lStrDDOCode.trim());

	List exist = selectQuery.list();
	System.out.println("resultList"+exist.size());

	
	return exist;

}
public List getEmpNameAutoComplete(String searchKey,
		 String lStrDDOCode,String lStrSearchBy) {

	ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
	ComboValuesVO cmbVO;
	Object[] obj;

	StringBuilder sb = new StringBuilder();
	Query selectQuery = null;
	Date lDtCurrDate = SessionHelper.getCurDate();

	sb.append("select emp_name,emp_name from mst_dcps_emp where UPPER(emp_name) LIKE :searchKey and reg_status = 1 and form_status=1 and (EMP_SERVEND_DT > sysdate or EMP_SERVEND_DT is null) and AC_DCPS_MAINTAINED_BY in (700174,700240,700241,700242) ");
	
	if (lStrDDOCode != null) {
	
			sb.append(" and ddo_Code = :ddoCode");
		
	}
	
	sb.append(" and ( EMP_SERVEND_DT is null or EMP_SERVEND_DT  >= :currentDate ) and pran_no is null ");
    logger.info("Inside getEmpNameAutoComplete Query is********* :" +sb.toString());
	selectQuery = ghibSession.createSQLQuery(sb.toString());
	selectQuery.setParameter("searchKey", '%' + searchKey + '%');
	selectQuery.setDate("currentDate", lDtCurrDate);

	if (lStrDDOCode != null) {
		if (!"".equals(lStrDDOCode)) {
			selectQuery.setParameter("ddoCode", lStrDDOCode.trim());
		}
	}

	List resultList = selectQuery.list();

	cmbVO = new ComboValuesVO();

	if (resultList != null && resultList.size() > 0) {
		Iterator it = resultList.iterator();
		while (it.hasNext()) {
			cmbVO = new ComboValuesVO();
			obj = (Object[]) it.next();
			cmbVO.setId(obj[1].toString());
			cmbVO.setDesc(obj[1].toString());
			finalList.add(cmbVO);
		}
	}

	return finalList;

  }

public String updatePranNo(String sevarthId, String pranNo)
{
	StringBuilder strBuld = new StringBuilder();  
	Query updateQuery = null;
	String flag = "NA";
	  try
	  {
	
	strBuld.append("update ifms.mst_dcps_emp set pran_no =:pranNo ,PRAN_ACTIVE=1 ,PRAN_REMARK='Pran updated by NPS utility'  where sevarth_id =:sevarthId" );
	logger.info("Inside updatePranNo Query************" +strBuld.toString());
	SQLQuery lQuery = ghibSession.createSQLQuery(strBuld.toString());
	
	lQuery.setParameter("sevarthId", sevarthId);
	System.out.println("sevarthId"+sevarthId);
	System.out.println("pranNo"+pranNo);
	lQuery.setParameter("pranNo", pranNo);
	
	int result=lQuery.executeUpdate();
	System.out.println("result:"+result);
	if (result!= 0 && result > 0) {
		flag="Updated";
		
	}
	else {
		
		flag= "NotUpdated";
	}}
	 catch (Exception e) {
		 e.printStackTrace();
			//e.printStackTrace();
			//return objRes;
		// TODO: handle exception
	}
	return flag;
}

	/*ADDED BY SHIVRAM 02092020*/


public List checkPranNoExist(String txtPranNo, String lStrDDOCode)
{
  StringBuilder sb = new StringBuilder();
  Query selectQuery = null;
  Date lDtCurrDate = SessionHelper.getCurDate();
  
  sb.append(" SELECT nvl(DCPS_OR_GPF,'A'), nvl(to_char(EMP_SERVEND_DT,'dd-MM-yyyy'),'01-01-1900'), nvl(AC_DCPS_MAINTAINED_BY,'NA'),nvl(to_char(DOJ,'dd-MM-yyyy'),"
  		+ "'01-01-1900'),nvl(PRAN_NO,'#'),ddo_code ,nvl(ppan,'0') FROM mst_dcps_emp  as emp  inner join  rlt_zp_ddo_map rlt on "
  		+ "emp.ddo_code=rlt.zp_ddo_code where form_status=1 and  ZP_STATUS = 10 and rlt.rept_ddo_code='"+lStrDDOCode+"'");
  if ((!"".equals(txtPranNo)) && (txtPranNo != null)) {
    sb.append(" AND UPPER(PRAN_NO) = :txtPranNo");
  }
  this.logger.info("Inside PRAN_NO query is *********** " + sb.toString());
  
  selectQuery = this.ghibSession.createSQLQuery(sb.toString());
  if ((!"".equals(txtPranNo)) && (txtPranNo != null)) {
    selectQuery.setParameter("txtPranNo", txtPranNo.trim().toUpperCase());
  }
  List exist = selectQuery.list();
  System.out.println("resultList" + exist.size());
  
  return exist;
}

public List getAllEmpForPranActDeact(String lStrPpanNo)
{
  List lLstEmpDeselect = null;
  StringBuilder Strbld = new StringBuilder();
  try
  {
    Strbld.append("select DISTINCT dcps.EMP_NAME, dcps.DCPS_ID, dcps.SEVARTH_ID,desig.DSGN_NAME,to_char(dcps.DOJ,'dd-MM-yyyy'),to_char(dcps.DOB,'dd-MM-yyyy'),"
    		+ "nvl(dcps.PPAN,' '),nvl(dcps.PRAN_NO,' '),dcps.ddo_code,case when (post.end_date > sysdate or post.end_date is null)  then 1 else 0 end,"
    		+ "nvl(to_char(post.end_date,'dd-MM-YYYY'),'01-01-1111'), user.END_DATE, dcps.PRAN_REMARK,"
    		+ "case when dcps.PRAN_ACTIVE is not null then dcps.PRAN_ACTIVE else 0 end  ,to_char(dcps.UPDATED_DATE,'dd-MM-yyyy')  from mst_dcps_emp dcps ");
    Strbld.append("inner join org_emp_mst emp on dcps.ORG_EMP_MST_ID=emp.EMP_ID ");
    Strbld.append("inner join ORG_USERPOST_RLT user on emp.USER_ID=user.USER_ID  and user.ACTIVATE_FLAG =1 ");
    Strbld.append("inner join ORG_POST_MST post on user.POST_ID=post.POST_ID AND post.activate_flag=1 ");
    Strbld.append("inner join org_designation_mst desig on post.DSGN_CODE=desig.DSGN_ID ");
   // Strbld.append("where dcps.pran_no is not null  and dcps.ZP_STATUS=10 and dcps.DCPS_OR_GPF='Y' ");
    Strbld.append("where dcps.pran_no is not null and (dcps.EMP_SERVEND_DT > sysdate or dcps.EMP_SERVEND_DT is null) "
    		+ " and dcps.ZP_STATUS=10 and dcps.DCPS_OR_GPF='Y' ");
    if ((lStrPpanNo != null) && (!"".equals(lStrPpanNo))) {
      Strbld.append(" AND UPPER(dcps.PRAN_NO) = :lStrPpanNo");
    }
    this.logger.info("query lstgetAllEmp -assaaa--------" + Strbld.toString());
    Query lQuery = this.ghibSession.createSQLQuery(Strbld.toString());
    if ((lStrPpanNo != null) && (!"".equals(lStrPpanNo))) {
      lQuery.setString("lStrPpanNo", lStrPpanNo);
    }
    this.logger.info("query lstgetAllEmp ---------" + Strbld.toString());
       lLstEmpDeselect = lQuery.list();
       System.out.println("resultListSIZE" + lLstEmpDeselect.size());
  }
  catch (Exception e)
  {
    this.logger.info("Error occured in lstgetAllEmp ---------" + e);
  }
  return lLstEmpDeselect;
}

public List getAllEmpForActive(String lStrPpanNo)
{
  List lLstEmpDeselect = null;
  List lLstEmpDeselect1 = null;
  
  StringBuilder Strbld = new StringBuilder();
  try
  {
    Strbld.append("select DISTINCT dcps.EMP_NAME, dcps.DCPS_ID, dcps.SEVARTH_ID,desig.DSGN_NAME,to_char(dcps.DOJ,'dd-MM-yyyy'), to_char(dcps.DOB,'dd-MM-yyyy'),nvl(dcps.PPAN,' '),nvl(dcps.PRAN_NO,' '),dcps.ddo_code,case when (post.end_date > sysdate or post.end_date is null)  then 1 else 0 end,nvl(to_char(post.end_date,'dd-MM-YYYY'),'01-01-1111'),user.END_DATE, dcps.PRAN_REMARK,dcps.PRAN_ACTIVE,to_char(dcps.UPDATED_DATE,'dd-MM-yyyy')  from mst_dcps_emp dcps ");
    Strbld.append("inner join org_emp_mst emp on dcps.ORG_EMP_MST_ID=emp.EMP_ID ");
    Strbld.append("inner join ORG_USERPOST_RLT user on emp.USER_ID=user.USER_ID and user.ACTIVATE_FLAG =0 and user.END_DATE is not null  ");
    Strbld.append("inner join ORG_POST_MST post on user.POST_ID=post.POST_ID AND post.activate_flag=1 ");
    Strbld.append("inner join org_designation_mst desig on post.DSGN_CODE=desig.DSGN_ID ");
    
    Strbld.append("where dcps.pran_no is not null and dcps.ZP_STATUS=10 and dcps.DCPS_OR_GPF='Y' ");
   /* Strbld.append("where dcps.pran_no is not null and (dcps.EMP_SERVEND_DT > sysdate or dcps.EMP_SERVEND_DT is null)   and dcps.ZP_STATUS=10 and dcps.DCPS_OR_GPF='Y' ");*/
    if ((lStrPpanNo != null) && (!"".equals(lStrPpanNo))) {
      Strbld.append(" AND UPPER(dcps.PRAN_NO) = :lStrPpanNo");
    }
    Strbld.append(" order by user.END_DATE desc ");
    SQLQuery lQuery = this.ghibSession.createSQLQuery(Strbld.toString());
    if ((lStrPpanNo != null) && (!"".equals(lStrPpanNo))) {
      lQuery.setString("lStrPpanNo", lStrPpanNo);
    }
    this.logger.info("query lstgetAllEmp ---------" + Strbld.toString());
    lLstEmpDeselect = lQuery.list();
    
    lLstEmpDeselect1 = new ArrayList();
    if ((lLstEmpDeselect != null) && (lLstEmpDeselect.size() != 0))
    {
      Object[] lObj = (Object[])lLstEmpDeselect.get(0);
      lLstEmpDeselect1.add(lObj);
    }
    System.out.println("Error occured in lstgetAllEmp change by viru lLstEmpDeselect1 size is" + lLstEmpDeselect1.size());
  }
  catch (Exception e)
  {
    this.logger.info("Error occured in lstgetAllEmp change by viru ---------" + e);
  }
  return lLstEmpDeselect1;
}

public String updateFlag(String remark, String flag, String pranNo, Date updateDate)
{
  StringBuilder strBuld = new StringBuilder();
  Query updateQuery = null;
  String flagrsult = "NA";
  try
  {
    strBuld.append("update mst_dcps_emp set PRAN_ACTIVE =:flag, PRAN_REMARK=:remark,UPDATED_DATE=:updateDate  where PRAN_NO =:pranNo");
    this.logger.info("Inside updatePranNo Query************" + strBuld.toString());
    SQLQuery lQuery = this.ghibSession.createSQLQuery(strBuld.toString());
    lQuery.setParameter("flag", flag);
    if ((remark != null) && (!remark.isEmpty()))
    {
      this.logger.info("inside remark check by viru");
      lQuery.setParameter("remark", remark);
    }
    lQuery.setParameter("pranNo", pranNo);
    lQuery.setDate("updateDate", updateDate);
    int result = lQuery.executeUpdate();
    System.out.println("result:" + result);
    if ((result != 0) && (result > 0)) {
      flagrsult = "Updated";
    } else {
      flagrsult = "NotUpdated";
    }
  }
  catch (Exception e)
  {
    e.printStackTrace();
  }
  return flagrsult;
}


}
	
	
	
	


