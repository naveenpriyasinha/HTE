package com.tcs.sgv.dcps.service;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.pensionproc.dao.PensionProcComparators;
import com.tcs.sgv.pensionproc.dao.PensionProcComparators.ObjectArrayComparator;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class DcpsCommonDAOImpl
  extends GenericDaoHibernateImpl
  implements DcpsCommonDAO
{
  private final Log gLogger = LogFactory.getLog(getClass());
  Session ghibSession = null;
  

  private final ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionproc/PensionCaseConstants");
  private String talukaName;
  
  public DcpsCommonDAOImpl(Class type, SessionFactory sessionFactory)
  {
    super(type);
    


    setSessionFactory(sessionFactory);
    ghibSession = getSession();
  }
  

  public List getMonths()
  {
    String query = "select monthId,monthName from SgvaMonthMst where monthId < 13";
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    return lLstReturnList;
  }
  
  public List getYears()
  {
	  /* This added code for dyanmic fin year BY Naveen Priya Sinha*/
		Date date = new Date();
		String ymd =new SimpleDateFormat("yyyyMMdd").format(date); 
		String Year=new SimpleDateFormat("yyyy").format(date); 
		String Month=new SimpleDateFormat("MM").format(date); 
		String Day=new SimpleDateFormat("dd").format(date); 
		String query = "";
		
		if(Integer.parseInt(Month)<4) {
			int year1 = Integer.parseInt(Year)-1;
			query = "SELECT finYearId, finYearDesc FROM SgvcFinYearMst WHERE langId = :langId and finYearCode BETWEEN '2007' AND '"+year1+"' ORDER BY finYearCode";
		} else {
			query = "SELECT finYearId, finYearDesc FROM SgvcFinYearMst WHERE langId = :langId and finYearCode BETWEEN '2007' AND '"+Year+"' ORDER BY finYearCode";
		}
		/* END for dyanmic fin year BY Naveen Priya Sinha*/
		
   // String query = "select finYearId,finYearCode from SgvcFinYearMst where finYearCode between '2008' and '2020'";//changed by Tejashree
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    return lLstReturnList;
  }
  
  public List getFinyears()
  {
	  
	  /*Auto Year calculation By Naveen Priya Sinha*/
			Date date = new Date();
			String ymd =new SimpleDateFormat("yyyyMMdd").format(date); 
			String Year=new SimpleDateFormat("yyyy").format(date); 
			String Month=new SimpleDateFormat("MM").format(date); 
			String Day=new SimpleDateFormat("dd").format(date); 
			String lStrBufLang = "";
		if(Integer.valueOf(Month) <3) {
			Integer tempYear= Integer.parseInt(Year)-1;
			Year= tempYear.toString() ;			
		}
    //String query = "select finYearId,finYearDesc from SgvcFinYearMst where finYearCode between '2008' and '2022'";//changed by Tejashree
		
	 String query = "select finYearId,finYearDesc from SgvcFinYearMst where finYearCode between '2008' and '"+Year+"'";
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    return lLstReturnList;
  }
  
  public String getDdoCode(Long lLngAsstPostId)
  {
    String lStrDdoCode = null;
    StringBuilder lSBQuery = new StringBuilder();
    logger.info("lLngAsstPostId123" + lLngAsstPostId);
    lSBQuery.append(" SELECT OD.ddoCode");
    lSBQuery.append(" FROM RltDdoAsst RD, OrgDdoMst OD");
    lSBQuery.append(" WHERE OD.postId = RD.ddoPostId AND RD.asstPostId = :asstPostId ");
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    logger.info("Query..123" + lSBQuery.toString());
    lQuery.setParameter("asstPostId", lLngAsstPostId);
    
    List lLstCodeList = lQuery.list();
    
    if (lLstCodeList != null)
    {
      if (lLstCodeList.size() != 0)
      {
        if (lLstCodeList.get(0) != null)
        {
          lStrDdoCode = lLstCodeList.get(0).toString();
        }
      }
    }
    
    return lStrDdoCode;
  }
  
  public List<ComboValuesVO> getAllDesignation(Long lLngDeptId, Long langId)
    throws Exception
  {
    StringBuilder lStrQuery = new StringBuilder();
    ArrayList<ComboValuesVO> lArrLstDesignation = new ArrayList();
    



    try
    {
      lStrQuery.append(" SELECT ODM.dsgnId,ODM.dsgnName FROM OrgDesignationMst ODM, MstDcpsDesignation MDD ");
      lStrQuery.append(" WHERE ODM.dsgnId = MDD.orgDesignationId AND MDD.fieldDeptId = :fieldDeptId AND MDD.langId =:langId order by upper(ODM.dsgnName),ODM.dsgnId");
      logger.info("order by desg name!");
      




      Query hqlQuery = ghibSession.createQuery(lStrQuery.toString());
      
      hqlQuery.setLong("langId", langId.longValue());
      hqlQuery.setLong("fieldDeptId", lLngDeptId.longValue());
      
      List lLstResultList = hqlQuery.list();
      Collections.sort(lLstResultList, 
        new PensionProcComparators.ObjectArrayComparator(false, 1, 
        0, 2, 0, true));
      if ((lLstResultList != null) && (lLstResultList.size() > 0)) {
        Iterator itr = lLstResultList.iterator();
        while (itr.hasNext()) {
          ComboValuesVO cmbVO = new ComboValuesVO();
          Object[] obj = (Object[])itr.next();
          cmbVO.setId(obj[0].toString());
          cmbVO.setDesc(obj[1].toString().replaceAll("&", "And"));
          lArrLstDesignation.add(cmbVO);
        }
      }
    }
    catch (Exception e) {
      logger.error("Error is :" + e, e);
    }
    
    return lArrLstDesignation;
  }
  
  public List<ComboValuesVO> getAllDepartment(Long lLngDepartmentId, Long langId)
    throws Exception
  {
    ArrayList<ComboValuesVO> lArrLstDepartnent = new ArrayList();
    StringBuilder lStrQuery = new StringBuilder();
    





    try
    {
      lStrQuery.append(" SELECT clm.locId,clm.locName FROM CmnLocationMst clm, OrgDepartmentMst odm ");
      lStrQuery.append(" WHERE odm.departmentId=:departmentId  ");
      lStrQuery.append(" AND clm.departmentId=odm.departmentId ");
      lStrQuery.append(" and clm.cmnLanguageMst.langId =:langId ");
      Query hqlQuery = ghibSession.createQuery(lStrQuery.toString());
      
      hqlQuery.setLong("langId", langId.longValue());
      hqlQuery.setLong("departmentId", lLngDepartmentId.longValue());
      hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
      List lLstResultList = hqlQuery.list();
      Collections.sort(lLstResultList, 
        new PensionProcComparators.ObjectArrayComparator(false, 1, 
        0, 2, 0, true));
      if ((lLstResultList != null) && (lLstResultList.size() > 0)) {
        Iterator itr = lLstResultList.iterator();
        while (itr.hasNext()) {
          ComboValuesVO cmbVO = new ComboValuesVO();
          Object[] obj = (Object[])itr.next();
          cmbVO.setId(obj[0].toString());
          cmbVO.setDesc(obj[1].toString().replaceAll("&", "And"));
          lArrLstDepartnent.add(cmbVO);
        }
      }
    } catch (Exception e) {
      logger.error("Error is :" + e, e);
      throw e; }
    List lLstResultList;
    return lArrLstDepartnent;
  }
  



  public List<ComboValuesVO> getHigherDepartment(Long lLngDepartmentId, Long langId)
    throws Exception
  {
    ArrayList<ComboValuesVO> lArrLstDepartnent = new ArrayList();
    StringBuilder lStrQuery = new StringBuilder();
    





    try
    {
      lStrQuery.append(" SELECT clm.locId,clm.locName FROM CmnLocationMst clm");
      lStrQuery.append(" WHERE clm.locId=:locId  ");
      
      lStrQuery.append(" and clm.cmnLanguageMst.langId =:langId ");
      Query hqlQuery = ghibSession.createQuery(lStrQuery.toString());
      
      hqlQuery.setLong("langId", langId.longValue());
      hqlQuery.setLong("locId", 10023L);
      hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
      List lLstResultList = hqlQuery.list();
      Collections.sort(lLstResultList, 
        new PensionProcComparators.ObjectArrayComparator(false, 1, 
        0, 2, 0, true));
      if ((lLstResultList != null) && (lLstResultList.size() > 0)) {
        Iterator itr = lLstResultList.iterator();
        while (itr.hasNext()) {
          ComboValuesVO cmbVO = new ComboValuesVO();
          Object[] obj = (Object[])itr.next();
          cmbVO.setId(obj[0].toString());
          cmbVO.setDesc(obj[1].toString().replaceAll("&", "And"));
          lArrLstDepartnent.add(cmbVO);
        }
      }
    } catch (Exception e) {
      logger.error("Error is :" + e, e);
      throw e; }
    List lLstResultList;
    return lArrLstDepartnent;
  }
  






  public List<ComboValuesVO> getAllHODDepartment(Long lLngDepartmentId, Long langId)
    throws Exception
  {
    ArrayList<ComboValuesVO> lArrLstDepartnent = new ArrayList();
    StringBuilder lStrQuery = new StringBuilder();
    





    try
    {
      lStrQuery.append(" SELECT clm.locId,clm.locName FROM CmnLocationMst clm, OrgDepartmentMst odm ");
      lStrQuery.append(" WHERE odm.departmentId=:departmentId  ");
      lStrQuery.append(" AND clm.departmentId=odm.departmentId ");
      lStrQuery.append(" and clm.cmnLanguageMst.langId =:langId ");
      Query hqlQuery = ghibSession.createQuery(lStrQuery.toString());
      
      hqlQuery.setLong("langId", langId.longValue());
      hqlQuery.setLong("departmentId", lLngDepartmentId.longValue());
      hqlQuery.setCacheable(true).setCacheRegion("ecache_lookup");
      List lLstResultList = hqlQuery.list();
      Collections.sort(lLstResultList, 
        new PensionProcComparators.ObjectArrayComparator(false, 1, 
        0, 2, 0, true));
      if ((lLstResultList != null) && (lLstResultList.size() > 0)) {
        Iterator itr = lLstResultList.iterator();
        while (itr.hasNext()) {
          ComboValuesVO cmbVO = new ComboValuesVO();
          Object[] obj = (Object[])itr.next();
          cmbVO.setId(obj[0].toString());
          cmbVO.setDesc(obj[1].toString().replaceAll("&", "And"));
          lArrLstDepartnent.add(cmbVO);
        }
      }
    } catch (Exception e) {
      logger.error("Error is :" + e, e);
      throw e; }
    List lLstResultList;
    return lArrLstDepartnent;
  }
  
  public List getAllTreasuries() throws Exception
  {
    String query = "select CM.locId , CM.locName from CmnLocationMst CM where departmentId = 100003 order by CM.locName";
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    
    return lLstReturnList;
  }
  
  public List getBillGroups() throws Exception
  {
    String query = "select BG.dcpsDdoBillGroupId, BG.dcpsDdoBillDescription from MstDcpsBillGroup BG";
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    
    return lLstReturnList;
  }
  
  public Date getLastDate(Integer month, Integer year)
  {
    Date date = new Date();
    
    Calendar calendar = Calendar.getInstance();
    calendar.set(year.intValue(), month.intValue(), 1);
    Integer day = Integer.valueOf(calendar.getActualMaximum(5));
    
    date.setMonth(month.intValue());
    date.setYear(year.intValue() - 1900);
    date.setDate(day.intValue());
    
    return date;
  }
  
  public Date getFirstDate(Integer month, Integer year)
  {
    Date date = new Date();
    
    date.setMonth(month.intValue());
    date.setYear(year.intValue() - 1900);
    date.setDate(1);
    
    return date;
  }
  
  public Object[] getSchemeNameFromBillGroup(Long billGroupId)
  {
    getSession();
    
    Object[] lObjArrSchemeNameAndCode = new Object[2];
    StringBuilder lSBQuery = new StringBuilder();
    List schemeList = new ArrayList();
    




    lSBQuery.append(" SELECT MDBG.SCHEME_NAME,MDBG.SCHEME_CODE,nvl(SM.DISCRIPTION,'-'),nvl(MDBG.SUB_SCHEME_CODE,'-') FROM MST_DCPS_BILL_GROUP  MDBG  ");
    lSBQuery.append(" left outer join SUBSCHEME_MASTER SM on SM.SUBSCHEME_CD=MDBG.SUB_SCHEME_CODE ");
    lSBQuery.append(" where MDBG.BILL_GROUP_ID= :billGroupId ");
    

    Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
    lQuery.setParameter("billGroupId", billGroupId);
    
    schemeList = lQuery.list();
    lObjArrSchemeNameAndCode = (Object[])schemeList.get(0);
    
    return lObjArrSchemeNameAndCode;
  }
  

  public String getYearCodeForYearId(Long yearId)
  {
    String lStrYearCode = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT finYearCode FROM SgvcFinYearMst WHERE finYearId = :yearId");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("yearId", yearId);
      lStrYearCode = hqlQuery.list().get(0).toString();

    }
    catch (Exception e)
    {
      gLogger.info("Error  while executing getCadreList of  DCPSCadreMasterDAOImpl is " + 
        e);
    }
    
    return lStrYearCode;
  }
  
  public String getMonthForId(Long monthId)
  {
    String lStrYearCode = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT monthName FROM SgvaMonthMst WHERE monthId = :monthId");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("monthId", BigDecimal.valueOf(monthId.longValue()));
      lStrYearCode = hqlQuery.list().get(0).toString();
    }
    catch (Exception e)
    {
      gLogger.info("Error is " + e);
    }
    
    return lStrYearCode;
  }
  
  public List getCadres()
  {
    String query = "select CM.cadreId,CM.cadreName FROM DcpsCadreMst CM";
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    return lLstReturnList;
  }
  
  public List getBankNames() throws Exception
  {
    String query = "select MB.bankCode, MB.bankName from MstBankPay MB order by MB.bankName,MB.bankCode";
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    return lLstReturnList;
  }
  
  public List getBranchNames(Long lLngBankCode) throws Exception
  {
    List<Object> lLstReturnList = null;
    try {
      StringBuilder lSBQuery = new StringBuilder();
      lSBQuery
        .append("SELECT RB.branchId, RB.branchName from RltBankBranchPay RB ");
      lSBQuery
        .append("WHERE RB.bankCode = :bankCodeVar order by RB.branchName,RB.branchId ");
      
      Query lObjQuery = ghibSession.createQuery(lSBQuery.toString());
      
      lObjQuery.setParameter("bankCodeVar", lLngBankCode);
      
      List lLstResult = lObjQuery.list();
      ComboValuesVO lObjComboValuesVO = null;
      lLstReturnList = new ArrayList();
      
      if ((lLstResult != null) && (lLstResult.size() != 0)) {
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
        
        for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
          Object[] obj = (Object[])lLstResult.get(liCtr);
          lObjComboValuesVO = new ComboValuesVO();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstReturnList.add(lObjComboValuesVO);
        }
      }
      else {
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
      }
    } catch (Exception e) {
      gLogger.error("Error is : " + e, e);
      
      throw e;
    }
    return lLstReturnList;
  }
  
  public List getBranchNamesWithBsrCodes(Long lLngBankCode) throws Exception
  {
    List<Object> lLstReturnList = null;
    try {
      StringBuilder lSBQuery = new StringBuilder();
      





      lSBQuery.append(" select bsr_code,branch_name,branch_id from RLT_BANK_BRANCH_PAY");
      lSBQuery.append(" where BANK_CODE = :bankCode");
      lSBQuery.append(" order by branch_name ,bsr_code");
      

      Query lObjQuery = ghibSession.createSQLQuery(lSBQuery.toString());
      
      lObjQuery.setParameter("bankCode", lLngBankCode);
      
      List lLstResult = lObjQuery.list();
      ComboValuesVO lObjComboValuesVO = null;
      lLstReturnList = new ArrayList();
      
      if ((lLstResult != null) && (lLstResult.size() != 0)) {
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
        
        for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
          Object[] obj = (Object[])lLstResult.get(liCtr);
          lObjComboValuesVO = new ComboValuesVO();
          lObjComboValuesVO.setId(obj[2].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstReturnList.add(lObjComboValuesVO);
        }
      }
      else {
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
      }
    } catch (Exception e) {
      gLogger.error("Error is : " + e, e);
      
      throw e;
    }
    return lLstReturnList;
  }
  
  public Long getIFSCCodeForBranch(Long branchName) throws Exception
  {
    Long lLngHstDcpsID = null;
    try {
      getSession();
      StringBuilder lSBQuery = new StringBuilder();
      lSBQuery.append(" SELECT micrCode FROM  RltBankBranchPay");
      lSBQuery.append(" WHERE branchId = :branchName ");
      Query lQuery = ghibSession.createQuery(lSBQuery.toString());
      lQuery.setParameter("branchName", branchName);
      lLngHstDcpsID = (Long)lQuery.list().get(0);
    }
    catch (Exception e)
    {
      gLogger.error("Error is :" + e, e);
    }
    return lLngHstDcpsID;
  }
  
  public List getStateNames(Long langId) throws Exception
  {
    ArrayList<ComboValuesVO> lstStates = new ArrayList();
    


    try
    {
      StringBuilder strQuery = new StringBuilder();
      





      strQuery.append(" SELECT stateId,stateName ");
      strQuery.append(" FROM CmnStateMst ");
      strQuery.append(" WHERE cmnLanguageMst.langId =:langId order by stateName,stateId");
      
      Query query = ghibSession.createQuery(strQuery.toString());
      
      query.setParameter("langId", langId);
      
      List resultList = query.list();
      
      ComboValuesVO cmbVO = new ComboValuesVO();
      
      if ((resultList != null) && (resultList.size() > 0)) {
        Iterator it = resultList.iterator();
        while (it.hasNext()) {
          cmbVO = new ComboValuesVO();
          Object[] obj = (Object[])it.next();
          cmbVO.setId(obj[0].toString());
          cmbVO.setDesc(obj[1].toString());
          lstStates.add(cmbVO);
        }
      }
    } catch (Exception e) {
      gLogger.error("Error is :" + e, e);
      
      throw e; }
    ComboValuesVO cmbVO;
    List resultList; return lstStates;
  }
  
  public List getDistricts(Long lStrCurrState)
    throws Exception
  {
    ArrayList<ComboValuesVO> lLstDistrict = new ArrayList();
    
    ComboValuesVO lObjComboValuesVO = null;
    if (lStrCurrState.longValue() != -1L)
    {
      try {
        StringBuilder lSBQuery = new StringBuilder();
        
        lSBQuery.append(" Select districtId,districtName ");
        lSBQuery.append(" FROM CmnDistrictMst ");
        
        lSBQuery.append(" WHERE cmnStateMst.stateId =:stateId and cmnLanguageMst.langId = 1 and activateFlag = 1 ");
        
        Query lObjQuery = ghibSession.createQuery(lSBQuery.toString());
        
        lObjQuery.setParameter("stateId", lStrCurrState);
        
        List lLstResult = lObjQuery.list();
        
        if ((lLstResult == null) || (lLstResult.size() <= 0)) return lLstDistrict;
        Iterator it = lLstResult.iterator();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("-- Select --");
        lLstDistrict.add(lObjComboValuesVO);
        while (it.hasNext()) {
          lObjComboValuesVO = new ComboValuesVO();
          Object[] obj = (Object[])it.next();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstDistrict.add(lObjComboValuesVO);
        }
      }
      catch (Exception e) {
        gLogger.error("Error is : " + e, e);
        throw e;
      }
    }
    else {
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("-- Select --");
      lLstDistrict.add(lObjComboValuesVO);
    }
    
    return lLstDistrict;
  }
  
  public List getTaluka(Long lStrCurrDst)
    throws Exception
  {
    ArrayList<ComboValuesVO> lLstTaluka = new ArrayList();
    
    try
    {
      StringBuilder lSBQuery = new StringBuilder();
      
      lSBQuery.append(" Select talukaId,talukaName ");
      lSBQuery.append(" FROM CmnTalukaMst ");
      
      lSBQuery.append(" WHERE cmnDistrictMst.districtId =:districtId ");
      
      Query lObjQuery = ghibSession.createQuery(lSBQuery.toString());
      
      lObjQuery.setParameter("districtId", lStrCurrDst);
      
      List lLstResult = lObjQuery.list();
      ComboValuesVO lObjComboValuesVO = null;
      if ((lLstResult != null) && (lLstResult.size() > 0)) {
        Iterator it = lLstResult.iterator();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("-- Select --");
        lLstTaluka.add(lObjComboValuesVO);
        while (it.hasNext()) {
          lObjComboValuesVO = new ComboValuesVO();
          Object[] obj = (Object[])it.next();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstTaluka.add(lObjComboValuesVO);
        }
      }
    }
    catch (Exception e) {
      gLogger.error("Error is : " + e, e);
      throw e;
    }
    
    return lLstTaluka;
  }
  

  public String getDdoCodeForDDO(Long lLngPostId)
  {
    String lStrDdoCode = null;
    List lLstDdoDtls = null;
    try
    {
      StringBuilder lSBQuery = new StringBuilder();
      lSBQuery.append(" SELECT OD.ddoCode");
      lSBQuery.append(" FROM  OrgDdoMst OD");
      lSBQuery.append(" WHERE OD.postId = :postId ");
      Query lQuery = ghibSession.createQuery(lSBQuery.toString());
      lQuery.setParameter("postId", lLngPostId);
      
      lLstDdoDtls = lQuery.list();
      
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
    }
    catch (Exception e)
    {
      gLogger.error("Error is :" + e, e);
    }
    
    return lStrDdoCode;
  }
  
  public List<String> getDdoCodeLvLForDDO(Long lLngPostId)
  {
    List<String> lStrDdoCode = null;
    List lLstDdoDtls = null;
    String strQuery = null;
    try
    {
      strQuery = "SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_POST_ID=" + lLngPostId;
      gLogger.info(strQuery);
      Query query = ghibSession.createSQLQuery(strQuery);
      lStrDdoCode = query.list();
    }
    catch (Exception e)
    {
      gLogger.error("Error is :" + e, e);
    }
    
    return lStrDdoCode;
  }
  
  public List getDesignations(String lStrCurrOffice)
    throws Exception
  {
    List<Object> lLstReturnList = null;
    try
    {
      StringBuilder lSBQuery = new StringBuilder();
      lSBQuery
        .append("SELECT OD.designationName FROM RltOfficeDesig OD,MstOffice OM ");
      lSBQuery
        .append(" WHERE OM.officeId = OD.officeId AND OM.officeName = :officeName");
      Session lObjSession = getReadOnlySession();
      Query lObjQuery = lObjSession.createQuery(lSBQuery.toString());
      lObjQuery.setParameter("officeName", lStrCurrOffice);
      
      List lLstResult = lObjQuery.list();
      ComboValuesVO lObjComboValuesVO = null;
      if ((lLstResult != null) && (lLstResult.size() != 0)) {
        lLstReturnList = new ArrayList();
        
        String lStrDesigName = null;
        for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
          lStrDesigName = (String)lLstResult.get(liCtr);
          lObjComboValuesVO = new ComboValuesVO();
          lObjComboValuesVO.setId(lStrDesigName);
          lObjComboValuesVO.setDesc(lStrDesigName);
          
          lLstReturnList.add(lObjComboValuesVO);
        }
      } else {
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
      }
    } catch (Exception e) {
      gLogger.error("Error is : " + e, e);
      throw e;
    }
    
    return lLstReturnList;
  }
  

  public List getCurrentOffices(String lStrDdoCode)
  {
    ArrayList<ComboValuesVO> finalList = new ArrayList();
    


    String query = "select dcpsDdoOfficeIdPk,dcpsDdoOfficeName from DdoOffice where dcpsDdoCode= :ddoCode";
    
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("ddoCode", lStrDdoCode);
    List resultList = selectQuery.list();
    
    ComboValuesVO cmbVO = new ComboValuesVO();
    
    if ((resultList != null) && (resultList.size() > 0)) {
      cmbVO = new ComboValuesVO();
      cmbVO.setId("-1");
      cmbVO.setDesc("-- Select --");
      finalList.add(cmbVO);
      Iterator it = resultList.iterator();
      while (it.hasNext()) {
        cmbVO = new ComboValuesVO();
        Object[] obj = (Object[])it.next();
        cmbVO.setId(obj[0].toString());
        cmbVO.setDesc(obj[1].toString());
        finalList.add(cmbVO);
      }
    }
    
    return finalList;
  }
  
  public List getAllOffices()
  {
    ArrayList<ComboValuesVO> finalList = new ArrayList();
    


    String query = "select dcpsDdoOfficeIdPk,dcpsDdoOfficeName from DdoOffice ";
    
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    List resultList = selectQuery.list();
    
    ComboValuesVO cmbVO = new ComboValuesVO();
    
    if ((resultList != null) && (resultList.size() > 0)) {
      cmbVO = new ComboValuesVO();
      cmbVO.setId("-1");
      cmbVO.setDesc("-- Select --");
      finalList.add(cmbVO);
      Iterator it = resultList.iterator();
      while (it.hasNext()) {
        cmbVO = new ComboValuesVO();
        Object[] obj = (Object[])it.next();
        cmbVO.setId(obj[0].toString());
        cmbVO.setDesc(obj[1].toString());
        finalList.add(cmbVO);
      }
    }
    
    return finalList;
  }
  
  public String getCmnLookupNameFromId(Long lookupId)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List<String> tempList = new ArrayList();
    

    lSBQuery
      .append(" select lookupName FROM CmnLookupMst WHERE lookupId = :lookupId");
    
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("lookupId", lookupId);
    
    tempList = lQuery.list();
    String lookupName = (String)tempList.get(0);
    return lookupName;
  }
  

  public String getDesigNameFromId(Long lookupId)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List<String> tempList = new ArrayList();
    

    lSBQuery
      .append(" select dsgnName FROM OrgDesignationMst WHERE dsgnId = :dsgnId");
    
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("dsgnId", lookupId);
    
    tempList = lQuery.list();
    String lookupName = (String)tempList.get(0);
    return lookupName;
  }
  

  public OrgDdoMst getDDOInfoVOForDDOCode(String ddoCode)
  {
    StringBuilder lSBQuery = new StringBuilder();
    
    Query lQuery = null;
    
    lSBQuery.append("FROM OrgDdoMst");
    lSBQuery.append(" WHERE ddoCode = :ddoCode ");
    lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("ddoCode", ddoCode);
    
    OrgDdoMst lObjDcpsDdoInfo = (OrgDdoMst)lQuery.uniqueResult();
    
    return lObjDcpsDdoInfo;
  }
  
  public String getDdoNameForCode(String lStrDdoCode)
  {
    String lStrDdoName = null;
    StringBuilder lSBQuery = new StringBuilder();
    logger.info("lStrDdoCode::::" + lStrDdoCode);
    lSBQuery
      .append(" SELECT ddoName from OrgDdoMst WHERE ddoCode =  :DdoCode ");
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("DdoCode", lStrDdoCode);
    
    List lLstCodeList = lQuery.list();
    lStrDdoName = lLstCodeList.get(0).toString();
    
    return lStrDdoName;
  }
  
  public DdoOffice getDdoMainOffice(String lStrDdoCode)
  {
    DdoOffice lObjDdoMainOffice = null;
    StringBuilder lSBQuery = new StringBuilder();
    lSBQuery
      .append(" from DdoOffice WHERE dcpsDdoCode =  :dcpsDdoCode and upper(dcpsDdoOfficeDdoFlag) = 'YES' ");
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("dcpsDdoCode", lStrDdoCode);
    
    List<DdoOffice> lLstOfficeList = lQuery.list();
    lObjDdoMainOffice = (DdoOffice)lLstOfficeList.get(0);
    
    return lObjDdoMainOffice;
  }
  
  public OrgDdoMst getDdoVOForDdoCode(String ddoCode)
  {
    StringBuilder lSBQuery = new StringBuilder();
    
    Query lQuery = null;
    
    lSBQuery.append("FROM OrgDdoMst");
    lSBQuery.append(" WHERE ddoCode = :ddoCode ");
    lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("ddoCode", ddoCode);
    
    OrgDdoMst lObjOrgDdoMst = (OrgDdoMst)lQuery.uniqueResult();
    
    return lObjOrgDdoMst;
  }
  
  public String getTreasuryNameForDDO(String lStrDdoCode)
  {
    String lStrTreasuryName = null;
    StringBuilder sb = new StringBuilder();
    
    sb.append("SELECT LM.locName FROM RltDdoOrg RO, CmnLocationMst LM ");
    sb
      .append("WHERE RO.ddoCode = :ddoCode AND\tLM.locationCode = RO.locationCode");
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("ddoCode", lStrDdoCode);
    lStrTreasuryName = selectQuery.list().get(0).toString();
    return lStrTreasuryName;
  }
  
  public String getTreasuryShortNameForDDO(String lStrDdoCode)
  {
    String lStrTreasuryName = null;
    StringBuilder sb = new StringBuilder();
    
    sb
      .append("SELECT LM.locShortName FROM RltDdoOrg RO, CmnLocationMst LM ");
    sb
      .append("WHERE RO.ddoCode = :ddoCode AND\tLM.locationCode = RO.locationCode");
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("ddoCode", lStrDdoCode);
    lStrTreasuryName = selectQuery.list().get(0).toString();
    return lStrTreasuryName;
  }
  
  public List getParentDeptForDDO(String lStrDdoCode)
  {
    List lStrParentDept = null;
    StringBuilder sb = new StringBuilder();
    
    sb.append("SELECT CM.locId,CM.locName FROM OrgDdoMst DM, CmnLocationMst CM ");
    sb.append("WHERE DM.ddoCode = :ddoCode AND\tCM.locId = DM.hodLocCode");
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("ddoCode", lStrDdoCode);
    lStrParentDept = selectQuery.list();
    return lStrParentDept;
  }
  
  public String getTreasuryCodeForDDO(String lStrDdoCode)
  {
    String lStrTreasuryName = null;
    StringBuilder sb = new StringBuilder();
    List tempList = null;
    
    sb.append("SELECT LM.locationCode FROM RltDdoOrg RO, CmnLocationMst LM ");
    sb.append("WHERE RO.ddoCode = :ddoCode AND\tLM.locationCode = RO.locationCode");
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("ddoCode", lStrDdoCode);
    
    tempList = selectQuery.list();
    if (tempList != null)
    {
      if (tempList.get(0) != null)
      {
        lStrTreasuryName = selectQuery.list().get(0).toString();
      }
    }
    return lStrTreasuryName;
  }
  
  public String getTreasuryCityForDDO(String lStrDdoCode)
  {
    String lStrCityName = null;
    StringBuilder sb = new StringBuilder();
    
    sb
      .append("SELECT CM.cityName FROM RltDdoOrg RO, CmnLocationMst LM ,CmnCityMst CM ");
    sb
      .append("WHERE RO.ddoCode = :ddoCode AND LM.locationCode = RO.locationCode AND LM.locCityId = CM.cityId ");
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("ddoCode", lStrDdoCode);
    if (selectQuery.list() != null)
    {
      if (selectQuery.list().size() != 0)
      {
        if (selectQuery.list().get(0) != null)
        {
          lStrCityName = selectQuery.list().get(0).toString();
        }
      }
    }
    return lStrCityName;
  }
  
  public List getCadreForDept(Long lLngDeptCode) throws Exception
  {
    List<Object> lLstReturnList = null;
    try {
      StringBuilder lSBQuery = new StringBuilder();
      lSBQuery
        .append("SELECT CM.cadreId, CM.cadreName from DcpsCadreMst CM ");
      lSBQuery.append("WHERE CM.fieldDeptId = :fieldDeptId ");
      
      Query lObjQuery = ghibSession.createQuery(lSBQuery.toString());
      
      lObjQuery.setParameter("fieldDeptId", lLngDeptCode);
      
      List lLstResult = lObjQuery.list();
      ComboValuesVO lObjComboValuesVO = null;
      if ((lLstResult != null) && (lLstResult.size() != 0)) {
        lLstReturnList = new ArrayList();
        
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
        for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
          Object[] obj = (Object[])lLstResult.get(liCtr);
          lObjComboValuesVO = new ComboValuesVO();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstReturnList.add(lObjComboValuesVO);
        }
      } else {
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
      }
    } catch (Exception e) {
      gLogger.error("Error is : " + e, e);
      
      throw e;
    }
    return lLstReturnList;
  }
  
  public List getDesigsForPFDAndCadre(Long fieldDeptId) throws Exception
  {
    List<Object> lLstReturnList = null;
    try
    {
      StringBuilder lSBQuery = new StringBuilder();
      
      lSBQuery
        .append(" SELECT ODM.dsgnId,ODM.dsgnName FROM OrgDesignationMst ODM, MstPayrollDesignationMst MDD ");
      lSBQuery
        .append(" WHERE ODM.dsgnId = MDD.orgDesignationId AND MDD.fieldDeptId = :fieldDeptId ORDER by upper(ODM.dsgnName)");
      
      Session lObjSession = getReadOnlySession();
      Query lObjQuery = lObjSession.createQuery(lSBQuery.toString());
      
      lObjQuery.setParameter("fieldDeptId", fieldDeptId);
      gLogger.error("fieldDeptId is : " + fieldDeptId);
      gLogger.error("getDesigsForPFDAndCadre is : " + lObjQuery.toString());
      
      List lLstResult = lObjQuery.list();
      ComboValuesVO lObjComboValuesVO = null;
      if ((lLstResult != null) && (lLstResult.size() != 0)) {
        lLstReturnList = new ArrayList();
        
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
        for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
          Object[] obj = (Object[])lLstResult.get(liCtr);
          lObjComboValuesVO = new ComboValuesVO();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstReturnList.add(lObjComboValuesVO);
        }
      } else {
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
      }
    } catch (Exception e) {
      gLogger.error("Error is : " + e, e);
      
      throw e;
    }
    return lLstReturnList;
  }
  
  public List getLookupValuesForParent(Long lLngParentLookupId)
    throws Exception
  {
    List<Object> lLstReturnList = null;
    try
    {
      StringBuilder lSBQuery = new StringBuilder();
      
      lSBQuery
        .append(" SELECT lookupId,lookupName FROM CmnLookupMst where parentLookupId = :parentLookupId");
      
      Session lObjSession = getReadOnlySession();
      Query lObjQuery = lObjSession.createQuery(lSBQuery.toString());
      
      lObjQuery.setParameter("parentLookupId", lLngParentLookupId);
      
      List lLstResult = lObjQuery.list();
      ComboValuesVO lObjComboValuesVO = null;
      if ((lLstResult != null) && (lLstResult.size() != 0)) {
        lLstReturnList = new ArrayList();
        
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
        for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
          Object[] obj = (Object[])lLstResult.get(liCtr);
          lObjComboValuesVO = new ComboValuesVO();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstReturnList.add(lObjComboValuesVO);
        }
      } else {
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
      }
    } catch (Exception e) {
      gLogger.error("Error is : " + e, e);
      
      throw e;
    }
    return lLstReturnList;
  }
  
  public List getDeptNameFromDdoCode(String lStrDdoCode)
  {
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    
    sb
      .append("SELECT lm.locationCode,lm.locName FROM CmnLocationMst lm,OrgDdoMst dm ");
    sb
      .append("WHERE dm.ddoCode = :ddoCode AND dm.deptLocCode = lm.locationCode");
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("ddoCode", lStrDdoCode);
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    
    return lLstReturnList;
  }
  
  public String getLocNameforLocId(Long locId)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List<String> tempList = new ArrayList();
    String locName = null;
    
    lSBQuery
      .append(" Select locName FROM CmnLocationMst WHERE locId = :locId");
    
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("locId", locId);
    
    tempList = lQuery.list();
    if ((tempList != null) && (tempList.size() != 0)) {
      locName = (String)tempList.get(0);
    }
    return locName;
  }
  
  public String getCadreNameforCadreId(Long cadreId)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List<String> tempList = new ArrayList();
    String cadreName = null;
    
    lSBQuery
      .append(" Select cadreName FROM DcpsCadreMst WHERE cadreId = :cadreId");
    
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("cadreId", cadreId);
    
    tempList = lQuery.list();
    if ((tempList != null) && (tempList.size() != 0)) {
      cadreName = (String)tempList.get(0);
    }
    return cadreName;
  }
  
  public Long getCadreCodeforCadreId(Long cadreId)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List<Long> tempList = new ArrayList();
    Long cadreCode = null;
    
    lSBQuery.append(" Select cadreCode FROM DcpsCadreMst WHERE cadreId = :cadreId");
    
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("cadreId", cadreId);
    
    tempList = lQuery.list();
    if ((tempList != null) && (tempList.size() != 0) && 
      (tempList.get(0) != null))
    {
      if (!((Long)tempList.get(0)).toString().equals(""))
      {
        cadreCode = Long.valueOf(((Long)tempList.get(0)).toString());
      }
    }
    
    return cadreCode;
  }
  
  public Long getCadreIdforCadreCodeAndFieldDept(Long cadreCode, Long fieldDeptId)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List<Long> tempList = new ArrayList();
    Long cadreId = null;
    
    lSBQuery.append(" Select cadreId FROM DcpsCadreMst WHERE cadreCode = :cadreCode and fieldDeptId = :fieldDeptId");
    
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("cadreCode", cadreCode);
    lQuery.setParameter("fieldDeptId", fieldDeptId);
    
    tempList = lQuery.list();
    if ((tempList != null) && (tempList.size() != 0) && 
      (tempList.get(0) != null))
    {
      if (!((Long)tempList.get(0)).toString().equals(""))
      {
        cadreId = Long.valueOf(((Long)tempList.get(0)).toString());
      }
    }
    
    return cadreId;
  }
  
  public String getGroupIdforCadreId(Long cadreId)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List<String> tempList = new ArrayList();
    String groupId = null;
    
    lSBQuery
      .append(" Select groupId FROM DcpsCadreMst WHERE cadreId = :cadreId");
    
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("cadreId", cadreId);
    
    tempList = lQuery.list();
    if ((tempList != null) && (tempList.size() != 0)) {
      groupId = (String)tempList.get(0);
    }
    return groupId;
  }
  
  public String getDddoOfficeNameNameforId(Long dcpsDdoOfficeIdPk)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List<String> tempList = new ArrayList();
    String ddoOfficeName = null;
    
    lSBQuery
      .append(" Select dcpsDdoOfficeName FROM DdoOffice WHERE dcpsDdoOfficeIdPk = :dcpsDdoOfficeIdPk");
    
    Query lQuery = ghibSession.createQuery(lSBQuery.toString());
    lQuery.setParameter("dcpsDdoOfficeIdPk", dcpsDdoOfficeIdPk);
    
    tempList = lQuery.list();
    if ((tempList != null) && (tempList.size() != 0)) {
      ddoOfficeName = (String)tempList.get(0);
    }
    return ddoOfficeName;
  }
  
  public List getOfficesForPost(Long lLongPostId) throws Exception
  {
    List<Object> lLstReturnList = null;
    try {
      StringBuilder lSBQuery = new StringBuilder();
      lSBQuery
        .append("SELECT DO.dcpsDdoOfficeIdPk, DO.dcpsDdoOfficeName from DdoOffice DO, HrPayOfficepostMpg OP");
      lSBQuery
        .append(" WHERE DO.dcpsDdoOfficeIdPk = OP.ddoOffice.dcpsDdoOfficeIdPk and OP.orgPostMstByPostId.postId = :lLongPostId");
      
      Query lObjQuery = ghibSession.createQuery(lSBQuery.toString());
      
      lObjQuery.setParameter("lLongPostId", lLongPostId);
      
      List lLstResult = lObjQuery.list();
      ComboValuesVO lObjComboValuesVO = null;
      if ((lLstResult != null) && (lLstResult.size() != 0)) {
        lLstReturnList = new ArrayList();
        
        lLstReturnList = new ArrayList();
        
        for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
          Object[] obj = (Object[])lLstResult.get(liCtr);
          lObjComboValuesVO = new ComboValuesVO();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstReturnList.add(lObjComboValuesVO);
        }
      } else {
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
      }
    } catch (Exception e) {
      gLogger.error("Error is : " + e, e);
      
      throw e;
    }
    return lLstReturnList;
  }
  
  public List<ComboValuesVO> getAllOrgType() throws Exception
  {
    ArrayList<ComboValuesVO> lArrLstDepartnent = new ArrayList();
    StringBuilder lStrQuery = new StringBuilder();
    





    try
    {
      lStrQuery.append(" SELECT orgId,orgDesc FROM MstDcpsOrganization MDO ");
      
      Query hqlQuery = ghibSession.createQuery(lStrQuery.toString());
      
      List lLstResultList = hqlQuery.list();
      if ((lLstResultList != null) && (lLstResultList.size() > 0)) {
        Iterator itr = lLstResultList.iterator();
        while (itr.hasNext()) {
          ComboValuesVO cmbVO = new ComboValuesVO();
          Object[] obj = (Object[])itr.next();
          cmbVO.setId(obj[0].toString());
          cmbVO.setDesc(obj[1].toString().replaceAll("&", "And"));
          lArrLstDepartnent.add(cmbVO);
        }
      }
    } catch (Exception e) {
      logger.error("Error is :" + e, e);
      throw e; }
    List lLstResultList;
    return lArrLstDepartnent;
  }
  
  public MstEmp getEmpVOForEmpId(Long dcpsEmpId) throws Exception
  {
    StringBuilder lStrQuery = new StringBuilder();
    MstEmp lObjMstEmp = null;
    try
    {
      lStrQuery.append(" FROM MstEmp WHERE dcpsEmpId=:dcpsEmpId");
      
      Query hqlQuery = ghibSession.createQuery(lStrQuery.toString());
      hqlQuery.setParameter("dcpsEmpId", dcpsEmpId);
      lObjMstEmp = (MstEmp)hqlQuery.uniqueResult();
    } catch (Exception e) {
      logger.error("Error is :" + e, e);
      throw e;
    }
    return lObjMstEmp;
  }
  
  public List getDatesFromFinYearId(Long yearId) throws Exception {
    List lstDates = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT fromDate,toDate FROM SgvcFinYearMst WHERE finYearId = :yearId");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("yearId", yearId);
      lstDates = hqlQuery.list();
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    
    return lstDates;
  }
  
  public String getCurrentInterestRate()
  {
    String interestRate = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT interest FROM InterestRate WHERE status=1");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      
      interestRate = hqlQuery.list().get(0).toString();
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    
    return interestRate;
  }
  
  public String getFinYearForYearId(Long yearId)
  {
    String lStrYearDesc = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT finYearDesc FROM SgvcFinYearMst WHERE finYearId = :yearId");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("yearId", yearId);
      lStrYearDesc = hqlQuery.list().get(0).toString();
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    
    return lStrYearDesc;
  }
  
  public Long getFinYearIdForYearCode(String yearCode)
  {
    Long lLongYearId = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try
    {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT finYearId FROM SgvcFinYearMst WHERE finYearCode = :finYearCode");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("finYearCode", yearCode);
      
      if (hqlQuery.list().get(0) != null) {
        lLongYearId = Long.valueOf(hqlQuery.list().get(0).toString());
      }
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    
    return lLongYearId;
  }
  
  public Float getCurrentDARate(String payComm)
  {
    Float daRate = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT daRate FROM DARate WHERE status=1 AND payCommission = :payComm");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("payComm", payComm);
      daRate = (Float)hqlQuery.list().get(0);
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    
    return daRate;
  }
  
  public String getTreasuryNameForTreasuryId(Long treasuryId)
  {
    String lStrYearDesc = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT locName FROM CmnLocationMst WHERE locationCode = :treasuryCode");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("treasuryCode", treasuryId.toString());
      lStrYearDesc = hqlQuery.list().get(0).toString();
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    
    return lStrYearDesc;
  }
  
  public String getBankNameForBankCode(String bankCode)
  {
    String lStrBankName = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try
    {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT bankName FROM MstBankPay WHERE bankCode = :bankCode");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("bankCode", bankCode);
      lStrBankName = hqlQuery.list().get(0).toString();
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    return lStrBankName;
  }
  
  public String getBranchNameForBranchCode(String branchId)
  {
    String lStrBranchName = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try
    {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT branchName FROM RltBankBranchPay WHERE branchId = :branchId");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("branchId", Long.valueOf(branchId));
      if (hqlQuery.list().size() == 0) {
        lStrBranchName = "";
      } else {
        lStrBranchName = hqlQuery.list().get(0).toString();
      }
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    return lStrBranchName;
  }
  
  public List getAllDDOForTreasury(String lStrTreasuryLocCode)
  {
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    
    sb
      .append("SELECT DM.ddoCode, DM.ddoName FROM RltDdoOrg RO, OrgDdoMst DM,CmnLocationMst LM ");
    sb
      .append("WHERE RO.locationCode = :locationCode AND RO.ddoCode = DM.ddoCode AND LM.locationCode = RO.locationCode");
    sb.append(" order by DM.ddoName");
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("locationCode", lStrTreasuryLocCode);
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("-- Select --");
      lLstReturnList.add(lObjComboValuesVO);
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    
    return lLstReturnList;
  }
  
  public Boolean checkPFDForDDO(String lStrDdoCode) {
    Boolean Status = Boolean.valueOf(false);
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    List<Boolean> lLstDdo = null;
    try
    {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT adminFlag FROM OrgDdoMst WHERE ddoCode = :ddoCode");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("ddoCode", lStrDdoCode);
      lLstDdo = hqlQuery.list();
      
      if (lLstDdo.size() > 0) {
        Status = (Boolean)lLstDdo.get(0);
      }
      
      if (Status.booleanValue()) {
        lSBQuery = new StringBuilder();
        lSBQuery
          .append(" update OrgDdoMst set adminFlag = 0 WHERE ddoCode = :ddoCode");
        hqlQuery = ghibSession.createQuery(lSBQuery.toString());
        hqlQuery.setParameter("ddoCode", lStrDdoCode);
        hqlQuery.executeUpdate();
      }
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    return Status;
  }
  
  public List getBillGroups(String lStrDDOCode) throws Exception
  {
    String query = "select dcpsDdoBillGroupId,dcpsDdoBillDescription from MstDcpsBillGroup where dcpsDdoCode = :dcpsDdoCode";
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("dcpsDdoCode", lStrDDOCode);
    
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    
    return lLstReturnList;
  }
  
  public Long getDDOPostIdForDDOAsst(Long lLngPostId)
  {
    Long lLongDdoPostId = null;
    List lLstDdoDtls = null;
    try
    {
      getSession();
      StringBuilder lSBQuery = new StringBuilder();
      lSBQuery.append(" SELECT ddoPostId");
      lSBQuery.append(" FROM  RltDdoAsst");
      lSBQuery.append(" WHERE asstPostId = :postId ");
      Query lQuery = ghibSession.createQuery(lSBQuery.toString());
      lQuery.setParameter("postId", lLngPostId);
      
      lLstDdoDtls = lQuery.list();
      
      if (lLstDdoDtls.size() != 0) {
        lLongDdoPostId = Long.valueOf(lLstDdoDtls.get(0).toString());
      }
    }
    catch (Exception e) {
      gLogger.error("Error is :" + e, e);
    }
    
    return lLongDdoPostId;
  }
  
  public String getFinYearCodeForYearId(Long yearId)
  {
    String lStrYearCode = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try
    {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT finYearCode FROM SgvcFinYearMst WHERE finYearId = :yearId");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("yearId", yearId);
      lStrYearCode = hqlQuery.list().get(0).toString();
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    
    return lStrYearCode;
  }
  
  public String getFinYearIdForDate(Date FinDate)
  {
    String lStrYearId = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try
    {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT finYearId FROM SgvcFinYearMst WHERE :finDate between fromDate and toDate");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("finDate", FinDate);
      lStrYearId = hqlQuery.list().get(0).toString();
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    
    return lStrYearId;
  }
  
  public List getStates(Long langId)
  {
    String query = "select stateId,stateName from CmnStateMst where cmnCountryMst.countryId = 1 and cmnLanguageMst.langId = :langId";
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setLong("langId", langId.longValue());
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    return lLstReturnList;
  }
  
  public String getAdminBudgetCodeForDDO(String lStrDDOCode) throws Exception
  {
    String lStrAdmBudgetCode = "";
    List tempList = null;
    try
    {
      StringBuilder lSBQuery = new StringBuilder();
      lSBQuery.append(" SELECT AD.admBdgtCd FROM AdmDept AD,OrgDdoMst OD, CmnLocationMst CM");
      lSBQuery.append(" WHERE OD.deptLocCode = CM.locId ");
      lSBQuery.append(" AND CM.locId = AD.locId");
      lSBQuery.append(" AND OD.ddoCode = :ddoCode");
      Query lQuery = ghibSession.createQuery(lSBQuery.toString());
      lQuery.setParameter("ddoCode", lStrDDOCode.trim());
      
      tempList = lQuery.list();
      if (tempList != null)
      {
        if (tempList.size() != 0)
        {
          if (tempList.get(0) != null)
          {
            lStrAdmBudgetCode = tempList.get(0).toString();
          }
        }
      }
    }
    catch (Exception e)
    {
      gLogger.error("Error is :" + e, e);
    }
    return lStrAdmBudgetCode;
  }
  
  public Long getDDOAsstPostIdForDDO(String lStrDDOCode)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List tempList = new ArrayList();
    Long lLongAsstPostId = Long.valueOf(0L);
    
    lSBQuery.append(" SELECT min(RD.ASST_POST_ID) FROM rlt_dcps_ddo_asst RD join org_ddo_mst OD on OD.post_id = RD.ddo_post_id where OD.ddo_code = '" + lStrDDOCode + "'");
    
    Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
    
    tempList = lQuery.list();
    lLongAsstPostId = Long.valueOf(tempList.get(0).toString());
    return lLongAsstPostId;
  }
  

  public Long getDDOPostIdForDDO(String lStrDDOCode)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List tempList = new ArrayList();
    Long lLongDDOPostId = Long.valueOf(0L);
    
    lSBQuery.append(" SELECT OD.post_id FROM org_ddo_mst OD where OD.ddo_code = '" + lStrDDOCode + "'");
    
    Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
    
    tempList = lQuery.list();
    lLongDDOPostId = Long.valueOf(tempList.get(0).toString());
    return lLongDDOPostId;
  }
  

  public Long getUserIdForPostId(Long lLongPostId)
  {
    StringBuilder lSBQuery = new StringBuilder();
    List tempList = new ArrayList();
    Long lLongAsstUserId = Long.valueOf(0L);
    
    lSBQuery.append(" select user_id from org_userpost_rlt where post_id = " + lLongPostId + " and activate_flag = 1");
    
    Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
    
    tempList = lQuery.list();
    lLongAsstUserId = Long.valueOf(tempList.get(0).toString());
    return lLongAsstUserId;
  }
  

  public String getFieldDeptOfDDO(String lStrDdoCode)
  {
    String lStrFieldDept = null;
    StringBuilder sb = new StringBuilder();
    
    sb.append(" SELECT OD.hodLocCode FROM OrgDdoMst OD ");
    sb.append(" WHERE OD.ddoCode = :ddoCode ");
    
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("ddoCode", lStrDdoCode.trim());
    lStrFieldDept = selectQuery.list().get(0).toString();
    return lStrFieldDept;
  }
  
  public String getRptDDOCodeForZPDDO(String lStrDdoCode) {
    String lStrFieldDept = null;
    StringBuilder sb = new StringBuilder();
    
    sb.append(" SELECT OD.REPT_DDO_CODE FROM ZpRltDdoMap OD ");
    sb.append(" WHERE OD.ZP_DDO_CODE = :ddoCode ");
    
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("ddoCode", lStrDdoCode.trim());
    lStrFieldDept = selectQuery.list().get(0).toString();
    return lStrFieldDept;
  }
  
  public String getGrType(String ddoCode) {
    List temp = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- getGrType---");
    String deptType = "";
    String result = "-1";
    if (ddoCode != null) {
      deptType = ddoCode.substring(0, 2);
      sb.append("select gr_no from ZP_ADMIN_OFFICE_MST where office_code=" + deptType);
      logger.info("---- getGrType DAo---" + sb);
      Query query = session.createSQLQuery(sb.toString());
      logger.info("---- query---" + sb);
      temp = query.list();
      if ((temp != null) && (temp.size() > 0)) {
        result = "1";
      }
    }
    return result;
  }
  

  public String getLocationCodeForDDO(String lStrDdoCode)
  {
    String lStrLocationCode = null;
    StringBuilder sb = new StringBuilder();
    List tempList = null;
    
    sb.append("SELECT OD.locationCode FROM OrgDdoMst OD ");
    sb.append("WHERE OD.ddoCode = :ddoCode");
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("ddoCode", lStrDdoCode);
    
    tempList = selectQuery.list();
    if (tempList != null)
    {
      if (tempList.get(0) != null)
      {
        lStrLocationCode = selectQuery.list().get(0).toString();
      }
    }
    return lStrLocationCode;
  }
  


  public List getDDODtls(String DDOCode)
  {
    List ddoDtls = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- vivek DAo---");
    sb.append("SELECT LOCATION_CODE FROM ORG_DDO_MST where DDO_CODE='" + DDOCode + "'");
    logger.info("---- vivek DAo---" + sb);
    Query query = session.createSQLQuery(sb.toString());
    ddoDtls = query.list();
    return ddoDtls;
  }
  

  public List getSubDDOs(Long locId, String talukaId, String ddoSelected)
  {
    List ddoDtls = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- getSubDDOs DAO---");
    sb.append("SELECT ddo.DDO_CODE ,ddo.LOCATION_CODE,ddo.POST_ID,ddo.DDO_office FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo, MST_DCPS_DDO_OFFICE office where lower(office.ddo_office)= 'yes' and ddo.DDO_OFFICE !='null' and office.status_flag=1 and ddo.DDO_CODE = rep.ZP_DDO_CODE and office.DDO_CODE=ddo.DDO_CODE and rep.status =1 and ((rep.REPT_DDO_POST_ID='" + locId + "') or (rep.FINAL_DDO_POST_ID='" + locId + "'))  ");
    
    if ((talukaId != null) && (talukaId != "") && (Long.parseLong(talukaId) != -1L)) {
      sb.append(" and office.taluka=" + talukaId );
    }
    if ((ddoSelected != null) && (ddoSelected != "")) {
      sb.append(" and (office.ddo_code like '" + ddoSelected + "%' or ddo.ddo_office like '%" + ddoSelected + "%') order by ddo.DDO_CODE asc");
    }
    
    logger.info("---- getSubDDOs DAo---" + sb);
    Query query = session.createSQLQuery(sb.toString());
    ddoDtls = query.list();
    return ddoDtls;
  }
  
  public List getpostRole(Long postId)
  {
    List ddoDtls = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- getpostRole DAO---");
    sb.append("  SELECT ROLE_ID FROM ACL_POSTROLE_RLT where post_id=" + postId);
    logger.info("---- getpostRole DAo---" + sb);
    Query query = session.createSQLQuery(sb.toString());
    ddoDtls = query.list();
    return ddoDtls;
  }
  

  public List getSubDDOCode(String postId)
  {
    List ddoDtls = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- getSubDDOs DAO---");
    sb.append("SELECT ddo.DDO_CODE  FROM RLT_ZP_DDO_MAP rep where rep.REPT_DDO_POST_ID=" + postId);
    logger.info("---- getSubDDOs DAo---" + sb);
    Query query = session.createSQLQuery(sb.toString());
    ddoDtls = query.list();
    return ddoDtls;
  }
  

  public List getSubOffc(String postId)
  {
    List ddoDtls = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- getSubOffc DAO---");
    sb.append("SELECT offc.LOC_ID,offc.OFF_NAME,offc.ddo_code FROM MST_DCPS_DDO_OFFICE offc,RLT_ZP_DDO_MAP ddo where ddo.ZP_DDO_CODE =offc.DDO_code  and ddo.REPT_DDO_POST_ID=" + postId);
    logger.info("---- getSubDDOs DAo---" + sb);
    Query query = session.createSQLQuery(sb.toString());
    ddoDtls = query.list();
    return ddoDtls;
  }
  
  public String getDDofromOffc(String offcId) {
    String ddoDtls = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- getDDofromOffc DAO---");
    
    sb.append("select distinct DDO_CODE from  MST_DCPS_DDO_OFFICE where LOC_ID='" + offcId + "' ");
    logger.info("---- getDDofromOffc DAo---" + sb);
    Query query = session.createSQLQuery(sb.toString());
    ddoDtls = query.uniqueResult().toString();
    return ddoDtls;
  }
  
  public List getallCadreForDept() throws Exception
  {
    List lLstReturnList = null;
    logger.info("Within the getallCadreForDept()");
    try
    {
      String strquery = "SELECT CADRE_CODE,CADRE_NAME FROM  MST_DCPS_CADRE_SUBB";
      Session hibsession = getSession();
      Query query = hibsession.createSQLQuery(strquery);
      logger.info("The SQL query :" + query);
      
      List lLstResult = query.list();
      ComboValuesVO lObjComboValuesVO = null;
      if ((lLstResult != null) && (lLstResult.size() != 0)) {
        lLstReturnList = new ArrayList();
        
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
        for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
          Object[] obj = (Object[])lLstResult.get(liCtr);
          lObjComboValuesVO = new ComboValuesVO();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstReturnList.add(lObjComboValuesVO);
        }
      } else {
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
      }
    } catch (Exception e) { gLogger.error("Error is : " + e, e);
      
      throw e;
    }
    return lLstReturnList;
  }
  
  public List getInstituteType()
  {
    List instituteType = null;
    Session session = getSession();
    String strQuery = "SELECT INSTITUTE_TYPE_ID,INSTITUTE_TYPE_NAME FROM INSTITUTE_TYPE order by INSTITUTE_TYPE_NAME";
    logger.info("StrQuery :::::::::::" + strQuery);
    Query query = session.createSQLQuery(strQuery);
    logger.info("StrQuery :::::::::::" + strQuery);
    instituteType = query.list();
    
    return instituteType;
  }
  



  public List getAppointment()
  {
    logger.info("Inside getAppointment :::::::::::");
    List Appointment = null;
    Session session = getSession();
    String strQuery = "SELECT Appointment_id,Appointment_name FROM APPOINTMENT";
    logger.info("APPOINTMENT :::::::::::" + strQuery);
    Query query = session.createSQLQuery(strQuery);
    logger.info("StrQuery :::::::::::" + strQuery);
    Appointment = query.list();
    return Appointment;
  }
  


  public String getLocationCodeforDDOloginfromEmpId(String empId)
  {
    String locationCode = null;
    List list = null;
    Session session = getSession();
    String strQuery = "SELECT LOCATION_CODE FROM MST_DCPS_EMP emp,ORG_DDO_MST ddo where ddo.DDO_CODE=emp.DDO_CODE and emp.DCPS_EMP_ID=" + empId;
    logger.info("StrQuery :::::::::::" + strQuery);
    Query query = session.createSQLQuery(strQuery);
    list = query.list();
    
    if ((list != null) || (list.size() != 0))
    {
      locationCode = list.get(0).toString();
    }
    logger.info("locationCode :::::::::::" + locationCode);
    
    return locationCode;
  }
  


  public String getFinYearDescForYearCode(String finYearCode)
  {
    String lStrYearCode = null;
    StringBuilder lSBQuery = null;
    Query hqlQuery = null;
    try
    {
      ghibSession = getSession();
      lSBQuery = new StringBuilder();
      lSBQuery
        .append(" SELECT finYearDesc FROM SgvcFinYearMst WHERE finYearCode = :finYearCode");
      hqlQuery = ghibSession.createQuery(lSBQuery.toString());
      hqlQuery.setParameter("finYearCode", finYearCode);
      lStrYearCode = hqlQuery.list().get(0).toString();
    }
    catch (Exception e)
    {
      gLogger.info("Error " + e);
    }
    
    return lStrYearCode;
  }
  

  public List getBillGroupsNotDeletedAndNotDCPS(String lStrDDOCode)
    throws Exception
  {
    String query = "select dcpsDdoBillGroupId,dcpsDdoBillDescription from MstDcpsBillGroup where dcpsDdoCode = :dcpsDdoCode";
    query = query + " and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y') ";
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("dcpsDdoCode", lStrDDOCode);
    
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    
    return lLstReturnList;
  }
  

  public List getDDOSchemesForSubDDOs(String DDOCode)
  {
    List ddoSchemesList = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("Query ################");
    sb.append("SELECT * FROM RLT_DCPS_DDO_SCHEMES where DDO_CODE =" + DDOCode);
    logger.info("Query **************** " + sb);
    Query query = session.createSQLQuery(sb.toString());
    ddoSchemesList = query.list();
    return ddoSchemesList;
  }
  



  public List allTaluka(String districtID)
  {
    List talukaList = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- get Taluka list---");
    
    sb.append("SELECT TALUKA_ID,TALUKA_NAME FROM CMN_TALUKA_MST where DISTRICT_ID=" + districtID);
    
    logger.info("---- get district---" + sb);
    Query query = session.createSQLQuery(sb.toString());
    talukaList = query.list();
    logger.info("taluka size is " + talukaList.size());
    return talukaList;
  }
  
  public String districtName(String ddoCode)
  {
    String districtId = "";
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- get district---");
    

    sb.append("SELECT DIstrict FROM MST_DCPS_DDO_OFFICE where lower(ddo_office)='yes' and ddo_code='" + ddoCode + "'");
    
    logger.info("---- get district---" + sb);
    Query query = session.createSQLQuery(sb.toString());
    districtId = (String)query.uniqueResult();
    logger.info("district name is " + districtId);
    return districtId;
  }
  




  public List getFinyearsForDelayedType()
  {
	  
	  /*Auto Year calculation By Naveen Priya Sinha*/
		Date date = new Date();
		String ymd =new SimpleDateFormat("yyyyMMdd").format(date); 
		String Year=new SimpleDateFormat("yyyy").format(date); 
		String Month=new SimpleDateFormat("MM").format(date); 
		String Day=new SimpleDateFormat("dd").format(date); 
		String lStrBufLang = "";
	if(Integer.valueOf(Month) <3) {
		Integer tempYear= Integer.parseInt(Year)-1;
		Year= tempYear.toString() ;			
	}
//String query = "select finYearId,finYearDesc from SgvcFinYearMst where finYearCode between '2005' and '2020' order by finYearCode ASC";//changed by Tejashree
	
    String query = "select finYearId,finYearDesc from SgvcFinYearMst where finYearCode between '2005' and '"+Year+"' order by finYearCode ASC";//changed by tejashree
    List<Object> lLstReturnList = null;
    StringBuilder sb = new StringBuilder();
    sb.append(query);
    Query selectQuery = ghibSession.createQuery(sb.toString());
    List lLstResult = selectQuery.list();
    ComboValuesVO lObjComboValuesVO = null;
    
    if ((lLstResult != null) && (lLstResult.size() != 0)) {
      lLstReturnList = new ArrayList();
      
      for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
        Object[] obj = (Object[])lLstResult.get(liCtr);
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId(obj[0].toString());
        lObjComboValuesVO.setDesc(obj[1].toString());
        lLstReturnList.add(lObjComboValuesVO);
      }
    } else {
      lLstReturnList = new ArrayList();
      lObjComboValuesVO = new ComboValuesVO();
      lObjComboValuesVO.setId("-1");
      lObjComboValuesVO.setDesc("--Select--");
      lLstReturnList.add(lObjComboValuesVO);
    }
    return lLstReturnList;
  }
  


  public Long checkUIDNumber(String uid, Long dcpsEmpId)
  {
    Session hibSession = getSession();
    
    Long finalCheckFlag = null;
    StringBuffer sb = new StringBuffer();
    gLogger.info("uid: " + uid);
    sb.append("SELECT count(1) FROM mst_dcps_emp where ddo_code is not null and UID_NO='" + uid + "'");
    if ((dcpsEmpId != null) && (!dcpsEmpId.equals("")))
    {
      sb.append(" and dcps_emp_id <> " + dcpsEmpId);
    }
    gLogger.info("Query to check UID number:  " + sb);
    Query sqlQuery1 = hibSession.createSQLQuery(sb.toString());
    finalCheckFlag = Long.valueOf(Long.parseLong(sqlQuery1.uniqueResult().toString()));
    
    return finalCheckFlag;
  }
  
  public String getCurrOffc(String ofcID) {
    String ddoDtls = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- getDDofromOffc DAO---");
    

    sb.append("select distinct DCPS_DDO_OFFICE_MST_ID from  MST_DCPS_DDO_OFFICE where lower(ddo_office) ='yes' and LOC_ID='" + ofcID + "' ");
    
    logger.info("---- getDDofromOffc DAo---" + sb.toString());
    Query query = session.createSQLQuery(sb.toString());
    ddoDtls = query.uniqueResult().toString();
    logger.info("Query Result is::" + ddoDtls);
    return ddoDtls;
  }
  

  public String getaccNoLengthForBankId(String bankId)
  {
    Session hibSession = getSession();
    
    String finalCheckFlag = null;
    StringBuffer sb = new StringBuffer();
    gLogger.info("bankId: " + bankId);
    

    sb.append("SELECT ACC_NO_LENGTH FROM MST_BANK_PAY where BANK_ID = '" + bankId + "'");
    gLogger.info("Query to get bank acc no. length:  " + sb);
    Query sqlQuery1 = hibSession.createSQLQuery(sb.toString());
    finalCheckFlag = sqlQuery1.uniqueResult().toString();
    
    return finalCheckFlag;
  }
  
  public Long getPinCodeCount(String pinCode, String districtID) {
    Session hibSession = getSession();
    
    Long finalCheckFlag = null;
    StringBuffer sb = new StringBuffer();
    gLogger.info("pinCode: " + pinCode + " ddoCode: " + districtID);
    

    sb.append("SELECT count(1) FROM MST_PINCODE where PIN_CODE = '" + pinCode + "' and ");
    if ((districtID != null) && (districtID != "") && (Long.parseLong(districtID) == 360L)) {
      sb.append(" DIST_ID=359");
    }
    else {
      sb.append(" DIST_ID=" + districtID);
    }
    gLogger.info("Query to get pin code count:  " + sb);
    Query sqlQuery1 = hibSession.createSQLQuery(sb.toString());
    finalCheckFlag = Long.valueOf(Long.parseLong(sqlQuery1.uniqueResult().toString()));
    
    return finalCheckFlag;
  }
  
  public List getCity(Long district) {
    List lStrParentDept = null;
    StringBuilder sb = new StringBuilder();
    
    sb.append("SELECT city_id,city_name FROM cmn_city_mst ");
    
    sb.append("WHERE district_id=" + district + " ");
    Query selectQuery = ghibSession.createSQLQuery(sb.toString());
    
    lStrParentDept = selectQuery.list();
    return lStrParentDept;
  }
  



  public int getPfSeriesCount(String pfSeriesDesc)
  {
    int pfSeriesCount = 0;
    StringBuilder sb = new StringBuilder();
    
    sb.append(" SELECT count(1) FROM RLT_DCPS_PAYROLL_EMP where upper(PF_ACNO) like '%" + pfSeriesDesc.toUpperCase() + "%' ");
    
    gLogger.info(" Query to get count of pf series number: " + sb.toString());
    
    Query selectQuery = ghibSession.createSQLQuery(sb.toString());
    

    pfSeriesCount = Integer.parseInt(selectQuery.uniqueResult().toString());
    return pfSeriesCount;
  }
  

  public List getDesigsForPFDAndCadre(Long fieldDeptId, Long selCadre)
    throws Exception
  {
    List<Object> lLstReturnList = null;
    
    logger.info("inside getDesigsForPFDAndCadre");
    try
    {
      StringBuilder lSBQuery = new StringBuilder();
      
      lSBQuery
        .append(" SELECT distinct ODM.dsgn_Id,ODM.dsgn_Name FROM Org_Designation_Mst ODM, Mst_Dcps_Designation MDD,Mst_Payroll_Designation mst ");
      
      lSBQuery
        .append("  WHERE ODM.dsgn_Id = MDD.org_Designation_Id and ODM.dsgn_Id = mst.org_Designation_Id AND MDD.field_Dept_Id in (10676,10677,10678,10679)  ");
      
      if ((selCadre != null) && (selCadre.longValue() != -1L)) {
        lSBQuery.append(" and mst.CADRE_TYPE_ID= (select group_id from mst_dcps_cadre where cadre_id=" + selCadre + ")  ");
      }
      lSBQuery.append(" order by ODM.dsgn_Name");
      Session lObjSession = getReadOnlySession();
      Query lObjQuery = lObjSession.createSQLQuery(lSBQuery.toString());
      logger.info("hhii the designation is " + lSBQuery.toString());
      

      List lLstResult = lObjQuery.list();
      ComboValuesVO lObjComboValuesVO = null;
      if ((lLstResult != null) && (lLstResult.size() != 0)) {
        lLstReturnList = new ArrayList();
        
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
        for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
          Object[] obj = (Object[])lLstResult.get(liCtr);
          lObjComboValuesVO = new ComboValuesVO();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstReturnList.add(lObjComboValuesVO);
        }
      } else {
        lLstReturnList = new ArrayList();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("--Select--");
        lLstReturnList.add(lObjComboValuesVO);
      }
    } catch (Exception e) {
      gLogger.error("Error is : " + e, e);
      
      throw e;
    }
    return lLstReturnList;
  }
  
  public String getDistrictId(String ddoCode) {
    String districtId = "";
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- get district---");
    

    sb.append("SELECT distinct DIstrict FROM MST_DCPS_DDO_OFFICE where ddo_code=" + ddoCode + " and upper(ddo_office)='YES'");
    
    logger.info("---- get district---" + sb);
    Query query = session.createSQLQuery(sb.toString());
    districtId = (String)query.uniqueResult();
    logger.info("district name is " + districtId);
    return districtId;
  }
  
  public List getDdoCodeForMdp()
  {
    logger.info("---- You are in the second level ddo code dao---");
    List lLstDdoDtls = null;
    String lStrDdoCode = "";
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    sb.append("SELECT distinct REPT_DDO_CODE FROM RLT_ZP_DDO_MAP");
    Query query = session.createSQLQuery(sb.toString());
    lLstDdoDtls = query.list();
    

    return lLstDdoDtls;
  }
  





  public List getCityClassName()
  {
    ArrayList<ComboValuesVO> lLstCityClass = new ArrayList();
    
    ComboValuesVO lObjComboValuesVO = null;
    try {
      StringBuilder lSBQuery = new StringBuilder();
      
      lSBQuery.append(" Select LOOKUP_NAME,LOOKUP_NAME ");
      lSBQuery.append(" FROM CMN_LOOKUP_MST ");
      
      lSBQuery.append(" WHERE PARENT_LOOKUP_ID =700017 ");
      
      Query lObjQuery = ghibSession.createSQLQuery(lSBQuery.toString());
      logger.info("---- getCityClassName DAo---" + lSBQuery.toString());
      


      List lLstResult = lObjQuery.list();
      
      logger.info("---- getCityClassName DAo---" + lLstResult.size());
      if ((lLstResult != null) && (lLstResult.size() > 0))
      {
        logger.info("--Inside -- getCityClassName DAo---");
        Iterator it = lLstResult.iterator();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("-- Select --");
        lLstCityClass.add(lObjComboValuesVO);
        while (it.hasNext())
        {
          logger.info("--Inside while loop-- getCityClassName DAo---");
          lObjComboValuesVO = new ComboValuesVO();
          Object[] obj = (Object[])it.next();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstCityClass.add(lObjComboValuesVO);
        }
      }
    } catch (Exception e) {
      gLogger.error("Error is : " + e, e);
    }
    return lLstCityClass;
  }
  



  public List getEmpListForCityClassUpdation(String ddoCode)
  {
    List lstQuestionList = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- getEmpListForCityClassUpdation DAO---");
    
    sb.append(" select dcp.SEVARTH_ID, dcp.EMP_NAME,org.CITY_CLASS ,dcp.DISTRICT,dist.district_id,office.DISTRICT,office.TALUKA ,dcp.dist_of_emp_city_class,dcp.Taluka_of_emp_city_class from MST_DCPS_EMP dcp ");
    sb.append(" inner join org_emp_mst org on dcp.ORG_EMP_MST_ID = org.EMP_ID and dcp.REG_STATUS in(1,2) and dcp.EMP_SERVEND_DT > sysdate ");
    sb.append(" left outer join CMN_DISTRICT_MST dist on dist.district_name = dcp.district  ");
    sb.append(" inner join ORG_USERPOST_RLT up on up.USER_ID = org.USER_ID and up.ACTIVATE_FLAG = 1 ");
    sb.append(" inner join ORG_POST_MST post on post.POST_ID = up.POST_ID and post.POST_TYPE_LOOKUP_ID is not NULL and post.ACTIVATE_FLAG =1 ");
    sb.append(" inner join org_ddo_mst ddo on ddo.LOCATION_CODE = post.LOCATION_CODE ");
    sb.append(" inner join mst_dcps_ddo_office office on office.ddo_code=ddo.DDO_CODE ");
    
    if (ddoCode.equals("20541200001"))
    {
      sb.append(" where ddo.DDO_CODE = '20541200001'  and ddo.ddo_type=20");

    }
    else
    {
      sb.append(" where ddo.DDO_CODE = '" + ddoCode + "'  and ddo.ddo_type=31");
    }
    

    logger.info("---- getEmpListForCityClassUpdation DAo---" + sb.toString());
    Query query = session.createSQLQuery(sb.toString());
    lstQuestionList = query.list();
    logger.info("Query Result is::" + lstQuestionList);
    return lstQuestionList;
  }
  

  public List getTalukaForCityClass()
    throws Exception
  {
    ArrayList<ComboValuesVO> lLstTaluka = new ArrayList();
    
    try
    {
      StringBuilder lSBQuery = new StringBuilder();
      
      lSBQuery.append(" Select talukaId,talukaName ");
      lSBQuery.append(" FROM CmnTalukaMst ");
      
      Query lObjQuery = ghibSession.createQuery(lSBQuery.toString());
      


      List lLstResult = lObjQuery.list();
      ComboValuesVO lObjComboValuesVO = null;
      if ((lLstResult != null) && (lLstResult.size() > 0)) {
        Iterator it = lLstResult.iterator();
        lObjComboValuesVO = new ComboValuesVO();
        lObjComboValuesVO.setId("-1");
        lObjComboValuesVO.setDesc("-- Select --");
        lLstTaluka.add(lObjComboValuesVO);
        while (it.hasNext()) {
          lObjComboValuesVO = new ComboValuesVO();
          Object[] obj = (Object[])it.next();
          lObjComboValuesVO.setId(obj[0].toString());
          lObjComboValuesVO.setDesc(obj[1].toString());
          lLstTaluka.add(lObjComboValuesVO);
        }
      }
    }
    catch (Exception e) {
      gLogger.error("Error is : " + e, e);
      throw e;
    }
    
    return lLstTaluka;
  }
  




  public void updateCityClassForEmployee(String arrCityClass, String arrSevarthCode, String dictrictName, String talukaName)
  {
    Session session = getSession();
    
    StringBuffer sb = new StringBuffer();
    logger.info("---- updateCityClassForEmployee ---");
    
    sb.append("update ORG_EMP_MST set CITY_CLASS='" + arrCityClass + "' where EMP_ID=(SELECT ORG_EMP_MST_ID FROM MST_DCPS_EMP where SEVARTH_ID= '" + arrSevarthCode + "')");
    
    logger.info("---- updateCityClassForEmployee DAo---" + sb.toString());
    Query query = session.createSQLQuery(sb.toString());
    query.executeUpdate();
    
    StringBuffer sb1 = new StringBuffer();
    sb1.append("update mst_dcps_emp set DIST_OF_EMP_CITY_CLASS =" + dictrictName + ",TALUKA_OF_EMP_CITY_CLASS = " + talukaName + " where SEVARTH_ID= '" + arrSevarthCode + "' ");
    logger.info("---- updateDistrictForEmployee DAo---" + sb1.toString());
    Query query1 = session.createSQLQuery(sb1.toString());
    query1.executeUpdate();
  }
  









  public void updateMajorHeadOfAccountCode(String arrHeadOfAcctCode, String arrDDOCode)
  {
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- updateMajorHeadOfAccountCode ---");
    
    sb.append("update ORG_EMP_MST set HEAD_ACT_CODE='" + arrHeadOfAcctCode + "' where EMP_ID=(SELECT DISTINCT (ORG_EMP_MST_ID) FROM MST_DCPS_EMP where SEVARTH_ID= '" + arrDDOCode + "')");
    
    logger.info("---- updateHeadOfAccountCode DAo---" + sb.toString());
    Query query = session.createSQLQuery(sb.toString());
    query.executeUpdate();
  }
  



  public List getEmpListForMajorHeadUpdation(String ddoCode)
  {
    List lstQuestionList = null;
    Session session = getSession();
    StringBuffer sb = new StringBuffer();
    logger.info("---- getEmpListForMajorHeadUpdation DAO---");
    
    sb.append(" select dcp.SEVARTH_ID, dcp.EMP_NAME,org.GRADE_ID,org.HEAD_ACT_CODE from MST_DCPS_EMP dcp ");
    sb.append(" inner join org_emp_mst org on dcp.ORG_EMP_MST_ID = org.EMP_ID and dcp.EMP_SERVEND_DT > sysdate and dcp.DCPS_OR_GPF='N' ");
    sb.append(" inner join ORG_USERPOST_RLT up on up.USER_ID = org.USER_ID and up.ACTIVATE_FLAG = 1 ");
    sb.append(" inner join ORG_POST_MST post on post.POST_ID = up.POST_ID and post.POST_TYPE_LOOKUP_ID is not NULL and post.ACTIVATE_FLAG =1 ");
    sb.append(" inner join org_ddo_mst ddo on ddo.LOCATION_CODE = post.LOCATION_CODE ");
    sb.append(" where ddo.DDO_CODE = '" + ddoCode + "' ");
    
    logger.info("---- getEmpListForMajorHeadUpdation DAo---" + sb.toString());
    Query query = session.createSQLQuery(sb.toString());
    lstQuestionList = query.list();
    logger.info("Query Result is::" + lstQuestionList);
    return lstQuestionList;
  }


public String getPayBandIdSevenPC(String sevenPcLevel) {
	
	String lStrFieldDept = null;
    StringBuilder sb = new StringBuilder();
    
    sb.append(" SELECT OD.id FROM RLTPaybandGPState7PC OD ");
    sb.append(" WHERE OD.levelId = :levelId ");
    
    Query selectQuery = ghibSession.createQuery(sb.toString());
    selectQuery.setParameter("levelId",sevenPcLevel.trim());
    lStrFieldDept = selectQuery.list().get(0).toString();
    return lStrFieldDept;
}



}