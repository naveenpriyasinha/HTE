package com.tcs.sgv.dcps.dao;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EmployeeSevenIncrDaompl
  extends GenericDaoHibernateImpl
{
  private final Log gLogger = LogFactory.getLog(getClass());
  Session ghibSession = null;
  
  public EmployeeSevenIncrDaompl(Class type, SessionFactory sessionFactory)
  {
    super(type);
    
    this.ghibSession = sessionFactory.getCurrentSession();
    setSessionFactory(sessionFactory);
  }
  
  public List getbillGroupList(String ddoCode)
  {
    Session hibSession = getSession();
    StringBuffer sb = new StringBuffer();
    sb.append("SELECT BILL_GROUP_ID,DESCRIPTION FROM MST_DCPS_BILL_GROUP where ddo_code = '" + ddoCode + "' and (BILL_DELETED <> 'Y' or BILL_DELETED is  null) ");
    
    Query sql1query = hibSession.createSQLQuery(sb.toString());
    
    this.logger.error("sql query for getBilldetailsList is" + sql1query.toString());
    this.logger.error("sql1query.list()" + sql1query.list().size());
    return sql1query.list();
  }
  
  public List getSevenPCEmpIncrDtls(long locId, String billNo)
  {
    Session hibSession = getSession();
    StringBuffer strQuery = new StringBuffer();
    
    strQuery.append(" select temp.* from ( ");
    strQuery.append(" select distinct mst.emp_name, mst.Sevarth_id, ");
    strQuery.append(" mst.DDO_CODE,emp.user_id,jan.MONTH,jan.Remarks from  org_emp_mst emp ");
    strQuery.append(" inner join org_userpost_rlt userpost on  emp.user_id=userpost.user_id and userpost.activate_flag= 1 ");
    strQuery.append(" inner join  hr_eis_emp_mst  eis on emp.emp_id=eis.emp_mpg_id ");
    strQuery.append(" inner join org_post_details_rlt postdtl on  userpost.post_id=postdtl.post_id ");
    strQuery.append(" inner join hr_pay_post_psr_mpg paypost on postdtl.post_id=paypost.POST_ID ");
    strQuery.append(" inner join HR_EIS_OTHER_DTLS hreis on eis.emp_id=hreis.EMP_ID ");
    strQuery.append(" inner join mst_dcps_emp mst on eis.emp_mpg_id = mst.org_emp_mst_id  and mst.pay_commission = '700349'");
    strQuery.append(" inner join hr_eis_scale_mst eisscale on  mst.payscale=eisscale.scale_id ");
    strQuery.append(" LEFT JOIN RLT_JANJULY_PAYFIX jan on mst.SEVARTH_ID = jan.SEVARTH_ID ");
    strQuery.append(" where postdtl.loc_id = " + locId + " and eisscale.commission_id= 2500341 and  mst.SEVEN_PC_BASIC > 0 ");
    strQuery.append(" and paypost.BILL_NO = " + billNo + " ");
    strQuery.append(" ) temp ");
    strQuery.append(" where temp.user_id not in ");
    strQuery.append(" ( select payfix.USER_ID from  HR_PAYFIX_MST payfix where ");
    strQuery.append(" payfix.LOC_ID = " + locId + " and ((payfix.ACTIVATE_FLAG= 1 ) or (payfix.ACTIVATE_FLAG= 0 and  payfix.NXT_INCR_DATE>sysdate))) ");
    
    Query query = hibSession.createSQLQuery(strQuery.toString());
    this.logger.info("******* getSevenPCEmpIncrDtls strQuery***************" + strQuery.toString());
    
    return query.list();
  }
  
  public void savce7PCIncrementData(String sevaarthId, String monthValue, String remarks, long userId)
  {
    try
    {
      StringBuffer str = new StringBuffer();
      Session hibSession = getSession();
      
      str.append("insert into RLT_JANJULY_PAYFIX(SEVARTH_ID,MONTH,REMARKS,UPDATED_DATE,UPDATED_BY)values('" + sevaarthId + "','" + monthValue + "','" + remarks + "',sysdate," + userId + ")");
      
      Query query = hibSession.createSQLQuery(str.toString());
      this.logger.info("savce7PCIncrementData------" + str.toString());
      
      query.executeUpdate();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public String getIncrMonth(String sevaarthId)
  {
    Session hibSession = getSession();
    String incrMonth = null;
    StringBuffer sb = new StringBuffer();
    sb.append(" SELECT rlt.Month FROM MST_DCPS_EMP mst,RLT_JANJULY_PAYFIX rlt ");
    sb.append(" where mst.SEVARTH_ID = rlt.SEVARTH_ID and rlt.SEVARTH_ID =  '" + sevaarthId + "'");
    Query sql1query = hibSession.createSQLQuery(sb.toString());
    if (sql1query.uniqueResult() != null) {
      incrMonth = sql1query.uniqueResult().toString();
    }
    this.logger.error("sql query for getIncrMonth is" + sql1query.toString());
    
    return incrMonth;
  }
  
  public String getMonthIncrGivenUserId(String userId)
  {
    Session hibSession = getSession();
    String incrMonth = null;
    StringBuffer sb = new StringBuffer();
    sb.append(" SELECT rlt.Month FROM MST_DCPS_EMP mst,RLT_JANJULY_PAYFIX rlt,ORG_EMP_MST org ");
    sb.append(" where mst.SEVARTH_ID = rlt.SEVARTH_ID and mst.ORG_EMP_MST_ID = org.EMP_ID and org.USER_ID = " + userId);
    Query sql1query = hibSession.createSQLQuery(sb.toString());
    if (sql1query.uniqueResult() != null) {
      incrMonth = sql1query.uniqueResult().toString();
    }
    this.logger.error("sql query for getIncrMonth is" + sql1query.toString());
    
    return incrMonth;
  }
}