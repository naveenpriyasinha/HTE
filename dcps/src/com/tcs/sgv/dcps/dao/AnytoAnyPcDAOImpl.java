package com.tcs.sgv.dcps.dao;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;







public class AnytoAnyPcDAOImpl
  extends GenericDaoHibernateImpl
  implements AnytoanyPcDAO
{
  private final Log gLogger = LogFactory.getLog(getClass());
  Session ghibSession = null;
  
  public AnytoAnyPcDAOImpl(Class type, SessionFactory sessionFactory) {
    super(type);
    ghibSession = sessionFactory.getCurrentSession();
    setSessionFactory(sessionFactory);
  }
  


  public List getEmpIdFromSevaarthIdOrName(String lStrEmpName, String lStrSevarthId, String resFlag)
  {
    Session hibSession = getSession();
    StringBuffer sb = new StringBuffer();
    sb.append(" SELECT emp.EMP_NAME,emp.SEVARTH_ID,cmn.lookup_name,emp.DCPS_EMP_ID FROM MST_DCPS_EMP emp ");
    
    sb.append(" inner join org_emp_mst org on org.EMP_ID = emp.ORG_EMP_MST_ID ");
    
    sb.append("inner join CMN_LOOKUP_MST cmn on cmn.lookup_id = emp.PAY_COMMISSION ");
    
    logger.error("resFlag is--" + resFlag);
    if (resFlag.equals("Yes"))
    {
      logger.error("in if is--" + resFlag);
      
      sb.append(" where emp.emp_name= '" + lStrEmpName + "'and (emp.SUPER_ANN_DATE >= sysdate or emp.SUPER_ANN_DATE is null)");
    }
    else
    {
      logger.error("in else is--" + resFlag);
      
      sb.append(" where emp.SEVARTH_ID= '" + lStrSevarthId + "' and (emp.SUPER_ANN_DATE >= sysdate or emp.SUPER_ANN_DATE is null)");
    }
    Query sql1query = hibSession.createSQLQuery(sb.toString());
    logger.error("sql query for getAny to any EmpDetails is" + sql1query.toString());
    
    return sql1query.list();
  }
  

  public void savecommissiondetails(String lStrSevaarthId, String lStrPayScale, String lStrPayCommission, String lStrPayInPayBand, String lStrtxtBasicPay, String lStrtxtGradePay, String remarks, Date witheffectdate, Long empid)
  {
    try
    {
      StringBuffer str = new StringBuffer();
      str.append("update mst_dcps_emp set BASIC_PAY =" + Integer.parseInt(lStrtxtBasicPay) + ",GRADE_PAY=" + Integer.parseInt(lStrtxtGradePay) + ",PAYSCALE='" + lStrPayScale + "',PAY_IN_PAY_BAND=" + Integer.parseInt(lStrPayInPayBand) + ",PAY_COMMISSION = '" + lStrPayCommission + "',REMARKS ='" + remarks + "',WITH_EFFECT_FROM_DATE= '" + witheffectdate + "',SEVEN_PC_BASIC= 0,SEVEN_PC_LEVEL = null where SEVARTH_ID = '" + lStrSevaarthId + "' ");
      Query lQuery = ghibSession.createSQLQuery(str.toString());
      lQuery.executeUpdate();
      
      StringBuffer str1 = new StringBuffer();
      str1.append("update HR_EIS_OTHER_DTLS set OTHER_CURRENT_BASIC  =" + Integer.parseInt(lStrtxtBasicPay) + ",OTHER_SVN_PC_BASIC= 0 , UPDATED_DATE=sysdate where EMP_ID = " + empid);
      Query lQuery1 = ghibSession.createSQLQuery(str1.toString());
      lQuery1.executeUpdate();
      
      int count = checkSevenPCHstDtls(lStrSevaarthId);
      logger.error("count is---" + count);
      if (count > 0)
      {
        StringBuilder lSBQuery = new StringBuilder();
        
        lSBQuery.append("DELETE FROM HST_SEVENPC_BASIC_DTS WHERE SEVARTH_ID = '" + lStrSevaarthId + "'");
        
        Query lStrQuery = ghibSession.createSQLQuery(lSBQuery.toString());
        logger.info("lSBQuery is---" + lSBQuery.toString());
        lStrQuery.executeUpdate();
      }
      

    }
    catch (Exception e)
    {

      logger.error(" Error is : " + e, e);
    }
  }
  

  public int checkSevenPCHstDtls(String lStrSevaarthId)
  {
    int count = 0;
    List empDetailsList = new ArrayList();
    try {
      StringBuffer lSBQuery = new StringBuffer();
      lSBQuery.append(" SELECT count(*) FROM ");
      lSBQuery.append(" MST_DCPS_EMP a,HST_SEVENPC_BASIC_DTS b where a.SEVARTH_ID = b.SEVARTH_ID and ");
      lSBQuery.append(" a.SEVARTH_ID ='" + lStrSevaarthId + "'");
      
      Query query = ghibSession.createSQLQuery(lSBQuery.toString());
      logger.error("query is---" + query.toString());
      if (query.uniqueResult() != null)
      {
        count = Integer.parseInt(query.uniqueResult().toString());
      }
      logger.info("count is " + count);
    } catch (Exception e) {
      logger.error(" Error is getEmployeeInfo: " + e, e);
    }
    return count;
  }
  



  public long getEmpId(String lStrSevaarthId)
  {
    long empid = 0L;
    List empDetailsList = new ArrayList();
    try {
      StringBuilder lSBQuery = new StringBuilder();
      lSBQuery.append("SELECT hr.EMP_ID FROM MST_DCPS_EMP mst ");
      lSBQuery.append("INNER JOIN HR_EIS_EMP_MST hr ON hr.EMP_MPG_ID = mst.ORG_EMP_MST_ID ");
      lSBQuery.append("where mst.SEVARTH_ID = :sevaarthId");
      Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
      lQuery.setParameter("sevaarthId", lStrSevaarthId);
      empDetailsList = lQuery.list();
      empid = Long.parseLong(empDetailsList.get(0).toString());
      logger.info("Empid is " + empid);
    } catch (Exception e) {
      logger.error(" Error is getEmployeeInfo: " + e, e);
    }
    return empid;
  }
  
  public String getEmpDetails(String empUserName, String lStrDdoCode) {
    Session hibSession = getSession();
    
    String finalCheckFlag = "No";
    List lstFinalCheck = null;
    List chkEmpInSystem = null;
    StringBuffer sb = new StringBuffer();
    

    sb.append(" SELECT emp.EMP_NAME FROM MST_DCPS_EMP emp inner join ORG_DESIGNATION_MST des on des.DSGN_ID = emp.DESIGNATION ");
    sb.append(" inner join MST_DCPS_CADRE cad on cad.CADRE_ID = emp.CADRE ");
    sb.append(" inner join org_emp_mst org on org.EMP_ID = emp.ORG_EMP_MST_ID ");
    sb.append(" inner join ORG_GRADE_MST grade on grade.GRADE_ID = org.GRADE_ID ");
    sb.append("inner join org_ddo_mst ddo on ddo.DDO_CODE = emp.DDO_CODE ");
    sb.append("inner join CMN_LOOKUP_MST cmn on cmn.lookup_id = emp.PAY_COMMISSION ");
    sb.append("inner join RLT_DCPS_PAYROLL_EMP payroll on payroll.dcps_emp_id = emp.dcps_emp_id ");
    //sb.append(" where emp.SEVARTH_ID = '" + empUserName + "' and  ddo.DDO_CODE = " + lStrDdoCode + " AND cmn.lookup_ID in(700349) AND emp.REG_STATUS in (1,2) and payroll.GIS_APPLICABLE in (700217,700218) ");
    sb.append(" where emp.SEVARTH_ID = '" + empUserName + "' and cmn.lookup_ID in(700005) AND emp.REG_STATUS in (1,2)");
    gLogger.info("Query to get emp name with ddo:  " + sb.toString());
    Query sqlQuery = hibSession.createSQLQuery(sb.toString());
    lstFinalCheck = sqlQuery.list();
    
    if (lstFinalCheck.size() > 0)
    {
      finalCheckFlag = lstFinalCheck.get(0).toString();
      finalCheckFlag = "Yes";
    }
    return finalCheckFlag;
  }
  
  public void savePCDetails(String lStrSevaarthId, long empid)
  {
    try {
      StringBuffer str = new StringBuffer();
      str.append("update mst_dcps_emp set PAY_COMMISSION = '700016',SEVEN_PC_BASIC= 0,SEVEN_PC_LEVEL = null where SEVARTH_ID = '" + lStrSevaarthId + "'");
      Query lQuery = ghibSession.createSQLQuery(str.toString());
      lQuery.executeUpdate();
      
      StringBuffer str1 = new StringBuffer();
      str1.append("update HR_EIS_OTHER_DTLS set OTHER_SVN_PC_BASIC= 0 , UPDATED_DATE=sysdate where EMP_ID = " + empid);
      Query lQuery1 = ghibSession.createSQLQuery(str1.toString());
      lQuery1.executeUpdate();
      
      int count = checkSevenPCHstDtls(lStrSevaarthId);
      logger.info("count is---" + count);
      if (count > 0)
      {
        StringBuilder lSBQuery = new StringBuilder();
        
        lSBQuery.append("DELETE FROM HST_SEVENPC_BASIC_DTS WHERE SEVARTH_ID = '" + lStrSevaarthId + "'");
        
        Query lStrQuery = ghibSession.createSQLQuery(lSBQuery.toString());
        logger.info("lSBQuery is---" + lSBQuery.toString());
        lStrQuery.executeUpdate();
      }
    }
    catch (Exception e)
    {
      logger.error(" Error is : " + e, e);
    }
  }
}