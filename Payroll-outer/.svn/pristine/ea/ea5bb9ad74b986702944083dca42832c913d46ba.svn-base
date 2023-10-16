package com.tcs.sgv.exprcpt.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.exprcpt.valueobject.BankScrollVO;
import com.tcs.sgv.exprcpt.valueobject.ChequeDetailVO;
import com.tcs.sgv.exprcpt.valueobject.RcptMisCheqDtlVO;
import com.tcs.sgv.exprcpt.valueobject.TrnBankScrollDetails;

/**
 * ClassName BankDAOImpl
 * 
 * Description Implementaton of Bank Scroll related Database Access object.
 * Date of Creation 1 august 2007
 *  
 * @author 602409
 * Revision History ===================== Bhavesh 23-Oct-2007 For making
 * changes for code formating
 *
 */
public class BankScrollDAOImpl extends GenericDaoHibernateImpl<TrnBankScrollDetails,Long> implements BankScrollDAO {

  Log logger = LogFactory.getLog(getClass());

  public BankScrollDAOImpl(Class<TrnBankScrollDetails> type, SessionFactory sessionFactory)
  {
    super(type);
    setSessionFactory(sessionFactory);
  }
  /**
   * Method return bankscroll Id by bank code and Date.
   * @param map
   * @return
   */
  public long getBsDetailId(Map map)
  {
    long bsDetailId=0;
    try 
    {
      Session hibSession = getSession();
      String bankCode=(String) map.get("bankCode");
      String date=(String)map.get("date");

      Query sqlQuery=hibSession.createQuery("select bd.bsDetailsId from TrnBankScrollDetails bd where bd.bsDate=to_date('"+date+"','dd-mm-yyyy') and bd.bankCode='"+bankCode+"'");
      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          Object tuple = (Object)it.next();
          bsDetailId=Long.parseLong(tuple.toString());
        }
      }


    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.countVouchersDist # \n"+e);
    }
    return bsDetailId;
  }
  /**
   * This method return all mis-match cheque list - Payment
   * @param map
   * @return
   */
  public List getUnVrfPaySclDtl(Map map) 
  {
    List dataList = null;
    try 
    {

      Session hibSession = getSession();
      String bankCode=(String) map.get("bankCode");
      String date=(String)map.get("date");
      long userId=Long.parseLong(map.get("userId").toString());
      List resList=null;

      Query sqlQuery=hibSession.createQuery("select cd.chequeNo, cd.fromDt, pe.chequeDate, cd.chequeAmt, pe.amount " +
          " from TrnChequeDtls cd, TrnBsPayEntries pe,TrnBankScrollDetails bd " +
          " where cd.chequeNo = pe.chequeNo and cd.chequeAmt <> pe.amount and pe.bsDetailId = bd.bsDetailsId and " +
          " bd.verified ="+0+" and bd.bsType='payment' and bd.bsDate =to_date('"+date+"','dd/mm/yyyy') and" +
          " bd.bankCode='"+bankCode+"'");
      resList=sqlQuery.list();

      if (resList!=null && resList.size()>0) 
      {
        dataList = new ArrayList();
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          ChequeDetailVO vo=new ChequeDetailVO();
          Object[] tuple = (Object[])it.next();
          vo.setChequeNo(Long.parseLong(tuple[0].toString()));
          vo.setIssueDate((Date) tuple[1]);
          vo.setClearedDate((Date) tuple[2]);
          vo.setChequeAmt((BigDecimal) tuple[3]);
          vo.setScrollAmt((BigDecimal) tuple[4]);
          dataList.add(vo);
          //System.out.println(tuple[0]+" - "+tuple[1]+" - "+ tuple[2]+" - "+ tuple[3] + " - " + tuple[4]);
        }
      }  
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in BankScrollDAOImpl.getUnVerifiedScrollDtl # \n"+e);
    }		
    return dataList;
  }
  /** 
   * This method update verified fields - Payment
   * @param objectArg
   */
  public void updateUnVrfPayDtl(Map objectArg) 
  {
    //System.out.println("Inside DAO Impl");
    String[] chequesNo=(String[])objectArg.get("chequesNo");
    String[] scrollsAmt=(String[])objectArg.get("scrollsAmt");
    String[] clearDates=(String[])objectArg.get("clearDates");
    try 
    {
      Session hibSession = getSession();
      if(scrollsAmt!=null)
      {
        for(int i=0;i<scrollsAmt.length;i++)
        {
          //System.out.println("chequeno  +"+chequesNo[i]);
          //System.out.println("scroll amt  +"+scrollsAmt[i]);
          long chequeNo=Long.parseLong(chequesNo[i]);
          double scrollAmt=Double.parseDouble(scrollsAmt[i]);
          String clearDate=clearDates[i];
          hibSession.createQuery("update TrnBsPayEntries pe set pe.amount="+scrollAmt+" where pe.chequeNo="+chequeNo).executeUpdate();
          hibSession.createQuery("update TrnChequeDtls cd set cd.clearedDt=to_date('"+clearDate+"','yyyy-mm-dd') where cd.chequeNo="+chequeNo+" and cd.chequeAmt="+scrollAmt).executeUpdate();
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in BankScrollDAOImpl.getUnVerifiedScrollDtl # \n"+e);
    }	

  }

  /** 
   * This method return all mis-match cheque list
   * @param userId
   * @return List
   */

  public List getUnVrfRecSclDtl(long userId) {
    List dataList = null;
    try 
    {
      //System.out.println("Inside DAO Impl");
      Session hibSession = getSession();
      String date="2007-04-09";
      String bankname="Bank Of Baroda";
      Query sqlQuery=hibSession.createQuery("select rd.receiptNo,rd.receiptDate, rd.majorHead, rd.amount, re.majorHead, re.amount " +
          " from TrnBsRcptEntries re, TrnReceiptDetails rd,TrnBankScrollDetails bd " +
          " where rd.receiptNo = re.receiptNo and (rd.amount<>re.amount or rd.majorHead<>re.majorHead)and " +
          " bd.verified ="+0+" and bd.bsDate =to_date('"+date+"','yyyy-mm-dd') and " +
          " bd.bankCode=(select mb.bankId from MstBank mb where mb.bankName='"+bankname+"')");

      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {
        dataList = new ArrayList();
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          RcptMisCheqDtlVO vo=new RcptMisCheqDtlVO();
          Object[] tuple = (Object[])it.next();
          vo.setReceipNo(Integer.parseInt(tuple[0].toString()));
          vo.setReceiptDate((Date)tuple[1]);
          vo.setRcptMajorHead((String) tuple[2]);
          vo.setRcptAmount((BigDecimal) tuple[3]);
          vo.setScrollMajorHead((String) tuple[4]);
          vo.setScrollAmount((BigDecimal) tuple[5]);
          dataList.add(vo);
          //System.out.println(tuple[0]+" - "+tuple[1]+" - "+ tuple[2]+" - "+ tuple[3] + " - " + tuple[4] + " - " + tuple[5]);
        }
      }  
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in BankScrollDAOImpl.getUnVrfRecSclDtl # \n"+e);
    }		
    return dataList;
  }

  /** 
   * This method update verified fields - Receipt
   * @param objectArg
   */

  public void updateUnVrfRecDtl(Map objectArg) 
  {
    //System.out.println("Inside DAO Impl");
    String[] receiptNos=(String[])objectArg.get("receipNos");
    String[] majorHeads=(String[])objectArg.get("sclMjrHeads");
    String[] amounts=(String[])objectArg.get("sclAmt");
    long userId=Long.parseLong(objectArg.get("userId").toString());
    String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    try 
    {
      Session hibSession = getSession();
      if(receiptNos!=null)
      {
        for(int i=0;i<receiptNos.length;i++)
        {
          long receiptNo=Long.parseLong(receiptNos[i]);
          BigDecimal scrollAmt=new BigDecimal(amounts[i]);
          String majorHead=majorHeads[i];
          hibSession.createQuery("update TrnBsRcptEntries re set re.amount="+scrollAmt+" ,re.majorHead='"+majorHead+"' where re.receiptNo="+receiptNo).executeUpdate();
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in BankScrollDAOImpl.getUnVerifiedScrollDtl # \n"+e);
    }	
  }


  /** 
   * This method returns all banks scrolls payment/receipt
   * @param userId
   * @return List 
   */


  public List getAllScrolls(long userId) {		
    List dataList = null;
    try 
    {
      Session hibSession = getSession();
      Query sqlQuery=hibSession.createQuery("select td.bsDetailsId,bi.bankName,td.bsDate,td.bsType,td.attachmentId from MstBank bi,TrnBankScrollDetails td where td.bankCode=bi.bankCode");
      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {
        dataList = new ArrayList();
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          BankScrollVO vo=new BankScrollVO();
          Object[] tuple = (Object[])it.next();
          vo.setBsDetailsId(Long.parseLong ( tuple[0].toString()));
          vo.setBankName((String) tuple[1]);
          vo.setBsDate((Date)tuple[2]);
          vo.setBsType((String) tuple[3]);
          vo.setAttachmentId((Long)tuple[4]);
          dataList.add(vo);
          //System.out.println(tuple[0]+" - "+tuple[1]+" - "+ tuple[2]+" - "+ tuple[3] + " " + tuple[4]);
        }
      }  
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in BankScrollDAOImpl.getAllScrolls #\n"+e);
    }		
    logger.info("\n\n--------- returning ---- " + dataList);
    return dataList;		
  }
  /**
   * Method to get RptPaymentVO By Cheque No.
   * @param chequeNo
   * @return
   */
  public List getPaymentVOByChequeNo(Long chequeNo)
  {
    Session session = getSession();		
    Query query = session.createQuery("FROM RptPaymentDtls where chqNo ="+chequeNo);
    return query.list();
  }
}
