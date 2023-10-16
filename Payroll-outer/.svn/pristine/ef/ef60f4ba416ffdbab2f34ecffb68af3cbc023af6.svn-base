package com.tcs.sgv.common.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.MstBank;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.exprcpt.valueobject.BankScrollDetailsVO;
import com.tcs.sgv.exprcpt.valueobject.TrnBankScrollDetails;

/**
 * ClassName BankDAOImpl
 * 
 * Description Implementaton of Bank related Database Access object.
 * Date of Creation 1 august 2007
 *  
 * @author 602409
 * Revision History ===================== Bhavesh 23-Oct-2007 For making
 * changes for code formating
 *
 */
public class BankDAOImpl extends GenericDaoHibernateImpl<MstBank,Integer> implements BankDAO
{

  Log logger = LogFactory.getLog(getClass());

  public BankDAOImpl(Class<MstBank> type, SessionFactory sessionFactory)
  {
    super(type);
    setSessionFactory(sessionFactory);
  }

  /**
   * This method returns list of all banks
   * @return List 
   */
  public List getAllBanks(String locCode) 
  {
    List dataList = null;
    try 
    {
      Session hibSession = getSession();

      String query = "select branchCode,branchName  FROM RltBankBranchPay  where  locationCode = '"+locCode+"'";
      Iterator itrBankName = hibSession.createQuery(query).list().iterator();
      dataList = new ArrayList();
      while(itrBankName.hasNext())
      {
        MstBank bankVO = new MstBank();
        Object tuple[] = (Object[]) itrBankName.next();
        bankVO.setBankCode(tuple[0].toString());
        bankVO.setBankName((String) tuple[1]);
        dataList.add(bankVO);
      }
      //dataList = hibSession.createCriteria(MstBank.class).list();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occurred in BankDAOImpl.getAllBanks #\n"+e);
    }
    logger.info("\n\n--------- returning ---- " + dataList);
    return dataList;

  }

  /**
   * This method to get scroll details.
   * @param locCode
   * @return
   */
  public List getScrollDetails(String locCode) 
  {
    List dataList = null;
    try 
    {
      Session hibSession = getSession();

      /*String query = "select rbb.branchName, tbsd.createdDate, oem.empFname, oem.empMname, oem.empLname" +
					"  from TrnBankScrollDetails tbsd, OrgEmpMst oem, RltBankBranch rbb" +
					" where to_char(tbsd.createdDate) = to_char(to_date(sysdate, 'DD-MM-YYYY'))" +
					" and rbb.branchCode = tbsd.bankCode" +
					" and oem.userId in (select our.userId from OrgUserpostRlt our where our.postId = tbsd.createdPostId)";*/
      String query = " SELECT RBB.BRANCH_NAME, TBSD.CREATED_DATE, OEM.EMP_FNAME, OEM.EMP_MNAME, OEM.EMP_LNAME" +
      " FROM TRN_BANK_SCROLL_DETAILS TBSD, ORG_EMP_MST OEM, RLT_BANK_BRANCH RBB" +
      " WHERE TO_CHAR(TBSD.CREATED_DATE) = TO_CHAR(TO_DATE(SYSDATE, 'DD-MM-YYYY'))" +
      " AND RBB.BRANCH_CODE = TBSD.BANK_CODE" +
      " AND RBB.LOCATION_CODE = " + locCode + 
      " AND OEM.USER_ID IN (SELECT OUR.USER_ID FROM ORG_USERPOST_RLT OUR WHERE OUR.POST_ID = TBSD.CREATED_POST_ID)";
      Iterator itrBsDet = hibSession.createSQLQuery(query).list().iterator();
      dataList = new ArrayList();
      while(itrBsDet.hasNext())
      {
        BankScrollDetailsVO bsDetVO = new BankScrollDetailsVO();
        Object tuple[] = (Object[]) itrBsDet.next();
        bsDetVO.setBankName(tuple[0].toString());
        bsDetVO.setCreatedDate((Date) tuple[1]);
        bsDetVO.setEmpName(tuple[2].toString() +"   "+ tuple[3].toString() +"    "+ tuple[4].toString());
        dataList.add(bsDetVO);
        //dataList.add(empVO);
      }
      //dataList = hibSession.createCriteria(MstBank.class).list();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occurred in BankDAOImpl.getAllBanks #\n"+e);
    }
    logger.info("\n\n--------- returning ---- " + dataList);
    return dataList;

  }

  /**
   * This method to get Un-verified Bank names.
   * @param locCode
   * @return
   */
  public List getUnVerifyBank(String locCode)
  {
    List dataList=null;
    try
    {
      //System.out.println("inside bank list method DAO impl");
      Session hibSession = getSession();

      Query sqlQuery=hibSession.createQuery("select distinct mb.branchCode, mb.branchName " +
          " from RltBankBranchPay mb ,TrnBankScrollDetails bd " +
          " where mb.branchCode = bd.bankCode and bd.verified="+0+" and mb.locationCode = '"+locCode+"'");


      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {
        dataList = new ArrayList();
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          MstBank vo=new MstBank();
          Object tuple[] = (Object[])it.next();
          //	vo.setBShortName((String) tuple[0]);
          vo.setBankCode(tuple[0].toString());
          vo.setBankName((String) tuple[1]);
          dataList.add(vo);
          ////System.out.println(vo.getBShortName());
        }
      }  
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occurred in BankDAOImpl.getUnVerifyBank #\n"+e);
    }
    return dataList;
  }
  /**
   * This method to get Un-verified Dates By Bank.
   * @param bankCode
   * @param locCode
   * @return
   */
  public List getUnVerifyDate(String bankCode,String locCode)
  {
    List dataList=new ArrayList();;
    try
    {
      //System.out.println("Bank id is------------------:-"+bankCode);
      Session hibSession = getSession();
      Query sqlQuery=null;
      if(bankCode.equalsIgnoreCase("-1"))
      {
        //System.out.println("inside date list method DAO impl");

        sqlQuery=hibSession.createQuery("select distinct bd.bsDate " +
            " from RltBankBranchPay mb ,TrnBankScrollDetails bd " +
            " where mb.branchCode = bd.bankCode and bd.verified="+0+" and bd.locationCode = '"+locCode+"'");
      }
      else
      {
        //System.out.println("inside date list method DAO impl");

        sqlQuery=hibSession.createQuery("select distinct bd.bsDate " +
            " from RltBankBranchPay mb ,TrnBankScrollDetails bd " +
            " where mb.branchCode = bd.bankCode and bd.verified="+0+" and bd.bankCode="+bankCode+" and bd.locationCode = '"+locCode+"'");
      }

      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {

        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          TrnBankScrollDetails vo=new TrnBankScrollDetails();
          Object tuple = (Object)it.next();
          String bsDate=new SimpleDateFormat("dd/MM/yyyy").format(tuple);
          vo.setBsDesc(bsDate);
          dataList.add(vo);
          //System.out.println(bsDate);
          ////System.out.println(date);
        }
      }  
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occurred in BankDAOImpl.getUnVerifyDate #\n"+e);
    }
    return dataList;
  }

}


