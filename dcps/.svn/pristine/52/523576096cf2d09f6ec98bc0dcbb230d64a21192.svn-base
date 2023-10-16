package com.tcs.sgv.pensionproc.dao;


import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class PensionerReportDaoImpl  extends GenericDaoHibernateImpl
implements PensionerReportDao{

	private final Log gLogger = LogFactory.getLog(getClass());
	  Session ghibSession = null;
	  private final ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");

	  public PensionerReportDaoImpl(Class type, SessionFactory sessionFactory)
	  {
	    super(type);

	    setSessionFactory(sessionFactory);
	    this.ghibSession = getSession();
	  }
	
	  public List getTreasury(String DeptCodeS)
	  {
	    List lLstReturnList = null;
	    StringBuilder sb = new StringBuilder();
	    long DeptCode = Long.parseLong(DeptCodeS);

	    sb.append("SELECT LM.locationCode, LM.locName FROM CmnLocationMst LM ");
	    sb.append
	      ("where LM.departmentId=:deptCode order by LM.locName asc");

	    this.gLogger.info("deptCode:" + DeptCode);
	    Query selectQuery = this.ghibSession.createQuery(sb.toString());
	    selectQuery.setParameter("deptCode", Long.valueOf(DeptCode));
	    List lLstResult = selectQuery.list();
	    ComboValuesVO lObjComboValuesVO = null;
	    if ((lLstResult != null) && (lLstResult.size() != 0)) {
	      lLstReturnList = new ArrayList();
	      lObjComboValuesVO = new ComboValuesVO();
	      lObjComboValuesVO.setId("--");
	      lObjComboValuesVO.setDesc("--Select --");
	      lLstReturnList.add(lObjComboValuesVO);

	      for (int liCtr = 0; liCtr < lLstResult.size(); ++liCtr) {
	        Object[] obj = (Object[])lLstResult.get(liCtr);
	        lObjComboValuesVO = new ComboValuesVO();
	        lObjComboValuesVO.setId(obj[0].toString());
	        lObjComboValuesVO.setDesc(obj[1].toString());
	        lLstReturnList.add(lObjComboValuesVO);
	      }
	    } else {
	      lLstReturnList = new ArrayList();
	      lObjComboValuesVO = new ComboValuesVO();
	      lObjComboValuesVO.setId("--");
	      lObjComboValuesVO.setDesc("--Select--");
	      lLstReturnList.add(lObjComboValuesVO);
	    }

	    return lLstReturnList;
	  }
	  
	  public List getBankForTreasuryDao(String LocCode) {
		    List lLstReturnList = null;
		    StringBuilder sb = new StringBuilder();

		    sb.append("SELECT distinct MB.BANK_CODE,MB.BANK_NAME FROM rlt_bank_branch RB, MST_BANK MB ");
		    sb.append
		      ("where RB.BANK_CODE=MB.BANK_CODE and RB.LOCATION_CODE=:locCode order by MB.BANK_NAME asc");

		    this.gLogger.info("ddo:" + LocCode);
		    Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
		    selectQuery.setParameter("locCode", LocCode);
		    List lLstResult = selectQuery.list();
		    ComboValuesVO lObjComboValuesVO = null;
		    if ((lLstResult != null) && (lLstResult.size() != 0)) {
		      lLstReturnList = new ArrayList();
		      lObjComboValuesVO = new ComboValuesVO();
		      lObjComboValuesVO.setId("-1");
		      lObjComboValuesVO.setDesc("-- Select --");
		      lLstReturnList.add(lObjComboValuesVO);

		      for (int liCtr = 0; liCtr < lLstResult.size(); ++liCtr) {
		        Object[] obj = (Object[])lLstResult.get(liCtr);
		        lObjComboValuesVO = new ComboValuesVO();
		        lObjComboValuesVO.setId(obj[0].toString());
		        String desc = "<![CDATA[" + obj[1].toString() + "]]>";
		        lObjComboValuesVO.setDesc(desc);
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
	  
	  public List getBranchForBankDao(String LocId, String BankCode)
	  {
	    List lLstReturnList = null;
	    StringBuilder sb = new StringBuilder();

	    sb.append("SELECT distinct RB.BRANCH_CODE,RB.BRANCH_NAME FROM RLT_BANK_BRANCH RB join MST_BANK MB ");
	    sb.append
	      ("on RB.BANK_CODE=MB.BANK_CODE and RB.LOCATION_CODE=:locId and RB.BANK_CODE=:bankCode order by RB.BRANCH_NAME asc");
	    this.gLogger.info("ddo:" + BankCode);
	    Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
	    selectQuery.setParameter("locId", LocId);
	    selectQuery.setParameter("bankCode", BankCode);

	    List lLstResult = selectQuery.list();
	    ComboValuesVO lObjComboValuesVO = null;
	    if ((lLstResult != null) && (lLstResult.size() != 0)) {
	      lLstReturnList = new ArrayList();
	      lObjComboValuesVO = new ComboValuesVO();
	      lObjComboValuesVO.setId("-1");
	      lObjComboValuesVO.setDesc("-- Select --");
	      lLstReturnList.add(lObjComboValuesVO);

	      for (int liCtr = 0; liCtr < lLstResult.size(); ++liCtr) {
	        Object[] obj = (Object[])lLstResult.get(liCtr);
	        lObjComboValuesVO = new ComboValuesVO();
	        lObjComboValuesVO.setId(obj[0].toString());
	        lObjComboValuesVO.setDesc(obj[1].toString());
	        lLstReturnList.add(lObjComboValuesVO);
	      }

	    }
	    else
	    {
	      lLstReturnList = new ArrayList();
	      lObjComboValuesVO = new ComboValuesVO();
	      lObjComboValuesVO.setId("-1");
	      lObjComboValuesVO.setDesc("--Select--");
	      lLstReturnList.add(lObjComboValuesVO);
	    }
	    return lLstReturnList;
	  }
	  
	  public List getNameForAutoComplete(String searchKey, String bankId, String branchId, String locId)
	  {
	    this.logger.info("Inside getNameForAutoComplete****** ");

	    ArrayList finalList = new ArrayList();

	    StringBuilder sb = new StringBuilder();
	    Query selectQuery = null;

	    this.logger.info("In getEmpNameForAutoCompletes searchKey is ************* " + searchKey);
	    sb.append("SELECT TPRH.PPO_NO,PH.FIRST_NAME as PensionerName ");
	    sb.append("FROM mst_pensioner_hdr PH   join trn_pension_Rqst_hdr TPRH on TPRH.PENSIONER_CODE = PH.PENSIONER_CODE and PH.LOCATION_CODE=" + locId + " and UPPER(PH.FIRST_NAME) like '%" + searchKey + "%'");
	    sb.append("join mst_pensioner_dtls PD on PD.PENSIONER_CODE = TPRH.PENSIONER_CODE and PD.BANK_CODE='" + bankId + "' and PD.BRANCH_CODE='" + branchId+"' ");

	    selectQuery = this.ghibSession.createSQLQuery(sb.toString());

	    this.logger.info("query is ****************" + sb.toString());
	    List resultList = selectQuery.list();

	    ComboValuesVO cmbVO = new ComboValuesVO();

	    if ((resultList != null) && (resultList.size() > 0)) {
	      Iterator it = resultList.iterator();
	      while (it.hasNext()) {
	        cmbVO = new ComboValuesVO();
	        Object[] obj = (Object[])it.next();
	        this.logger.info("Inside getEmpNameForAutoComplete List results are--->" + obj[0].toString());
	        
	        cmbVO.setId(obj[0].toString());
	       
	      
	        cmbVO.setDesc(obj[1].toString());
	        

	        finalList.add(cmbVO);
	      }
	    }

	    return finalList;
	  }
	  
	  
	  public List checkAccNo(String ppoNo, String bankCode, String branchCode)
	  {
	    this.logger.info("Inside****** checkAccNo");

	    List resultList = null;

	    StringBuilder sb = new StringBuilder();
	    Query selectQuery = null;

	    sb.append("SELECT TRIM(TRIM(L '0' FROM TRIM(MPD.ACOUNT_NO))) FROM MST_PENSIONER_DTLS MPD ");
	    sb.append("join trn_pension_Rqst_hdr TPRH on MPD.PENSIONER_CODE=TPRH.PENSIONER_CODE and TPRH.PPO_NO='" + ppoNo + "' and MPD.BANK_CODE=" + bankCode + " and MPD.BRANCH_CODE=" + branchCode);

	    selectQuery = this.ghibSession.createSQLQuery(sb.toString());

	    this.logger.info("query is ****************" + sb.toString());
	    resultList = selectQuery.list();

	    this.logger.info("Resulting account num is**********" + resultList);
	    return resultList;
	  }
	  
}
