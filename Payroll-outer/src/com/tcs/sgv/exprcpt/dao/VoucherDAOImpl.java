package com.tcs.sgv.exprcpt.dao;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.billproc.counter.valueobject.NewBillVO;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.exprcpt.valueobject.DistVoucherVO;
import com.tcs.sgv.exprcpt.valueobject.TrnVoucherDetails;
import com.tcs.sgv.exprcpt.valueobject.VoucherVO;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

/**
 * ClassName VoucherDAOImpl
 * 
 * Description Implementaton of voucher Data Access Object
 * Date of Creation 26 July 2007
 *  
 * @author 602409
 *
 * Revision History:
 * Bhavesh 30 Aug 2007
 *
 */
public class VoucherDAOImpl extends GenericDaoHibernateImpl<TrnVoucherDetails,Long> implements VoucherDAO 
{
  Log logger = LogFactory.getLog(getClass());
  SessionFactory sessionFactory = null;


  public VoucherDAOImpl(Class<TrnVoucherDetails> type, SessionFactory sessionFactory)
  {
    super(type);
    setSessionFactory(sessionFactory);
    this.sessionFactory = sessionFactory;
  }	


  /**
   * This method returns List of vouchers that are not received in book branch
   * @param userId Logged in userid 
   * @return List<VoucherVO.class>
   */
  public List getVouchers(Long postId, List billLst, Long langId, String slocCode) 
  {			
    List dataList = null;
    try 
    {
      Session hibSession = getSession();
      StringBuffer sql = new StringBuffer();
      if(billLst!=null && billLst.size()>0) 
      {
        sql.append("select vd.voucherNo, vd.voucherDate, vd.billNo, br.billCntrlNo, vd.majorHead, br.billNetAmount, " +
            " br.billDate, bt.subjectDesc ,dc.cardexNo  from TrnVoucherDetails vd, TrnBillRegister br," +
            " MstBillType bt, OrgDdoMst dc, TrnBillMvmnt bm" +
            " where vd.billNo = br.billNo and bm.billNo=vd.billNo and br.subjectId = bt.subjectId and br.ddoCode =dc.ddoCode" +
            " and bm.statusUpdtPostid="+postId+" and bm.receivedFlag="+0+ " and br.currBillStatus = bm.mvmntStatus  and br.currBillStatus = '"+DBConstants.ST_VCHR_GEN+"' and bm.billNo in (");

        for (int i=0; i < billLst.size(); i++) 
          if(i!=(billLst.size()-1)) sql.append(billLst.get(i)+",");
          else sql.append(billLst.get(i));

        sql.append(")");
        sql.append(" and bt.langId="+langId+" and dc.langId="+langId+" and br.tsryOfficeCode='"+slocCode+"'");
        sql.append(" order by vd.voucherDate desc");

        Query sqlQuery=hibSession.createQuery(sql.toString());

        List resList=sqlQuery.list();
        if (resList!=null && resList.size()>0) 
        {
          dataList = new ArrayList();
          Iterator it = resList.iterator();
          while(it.hasNext()) 
          {
            VoucherVO vo=new VoucherVO();
            Object[] tuple = (Object[])it.next();
            vo.setVoucherNo(String.valueOf((Long) tuple[0]));
            vo.setVoucherDate((Date) tuple[1]);
            vo.setBillNo(Long.parseLong(tuple[2].toString()));	
            vo.setBillCntrlNo((String) tuple[3]);
            vo.setMajorHead((String) tuple[4]);
            vo.setBillNetAmount((BigDecimal) tuple[5]);
            vo.setBillDate((Date) tuple[6]);
            vo.setSubjectDesc((String) tuple[7]);
            vo.setCardexNo((String) tuple[8]);
            dataList.add(vo);
          }
        }
      }  
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getVouchers # \n"+e);
    }		
    return dataList;
  }

  /**
   * This method update the received flag when received voucher for distribution
   * @param objectArg Map consisting of billsNo(String[]), userId(long)  
   */
  public void accVouchersFromChqBranch(Map objectArg) 
  {
    String[] billsNo=(String[])objectArg.get("billsNo");
    Long userId=Long.parseLong(objectArg.get("userId").toString());
    Long postId=Long.parseLong(objectArg.get("postId").toString());
    String slocCode=(String)objectArg.get("slocCode");
    String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    try 
    {
      Session hibSession = getSession();
      if(billsNo!=null)
      {
        for(int i=0;i<billsNo.length;i++)
        {
          long billNo=Long.parseLong(billsNo[i]);
          int update = hibSession.createQuery("update TrnBillMvmnt set receivedFlag=1,receivedDate=to_date('"+dateTime+"','yyyy-mm-dd'), " +
              "updatedUserId="+userId+",updatedDate=to_date('"+dateTime+"','yyyy-mm-dd'),receivingUserId="+userId+" " +
              "where billNo="+billNo+" and statusUpdtUserid="+userId+" and statusUpdtPostid="+postId+" and locationCode='"+slocCode+"'").executeUpdate();
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.accVouchersFromChqBranch # \n"+e);
    }
  }

  /**
   * This method returns total no of undistributed vouchers
   * @param userId Logged in userid 
   * @return int 
   */
  public int countVouchersDist(Long postId, List billLst,String slocCode)
  {
    int noOfVouchers=0;;
    try 
    {
      Session hibSession = getSession();
      if(billLst!=null && billLst.size()>0) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT COUNT(BR.BILL_NO) "+
            " FROM TRN_BILL_REGISTER BR,TRN_VOUCHER_DETAILS BD,TRN_BILL_MVMNT BM ," +
            " (SELECT BILL_NO,MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
            " WHERE BR.BILL_NO=BD.BILL_NO AND BD.BILL_NO=BM.BILL_NO AND T.BILL_NO = BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
            " AND BM.RECEIVED_FLAG="+1+" AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BM.STATUS_UPDT_POSTID="+postId+" AND BM.BILL_NO IN (");

        for (int i=0; i < billLst.size(); i++) 
          if(i!=(billLst.size()-1)) sql.append(billLst.get(i)+",");
          else sql.append(billLst.get(i));

        sql.append(")");
        sql.append(" AND BR.TSRY_OFFICE_CODE='"+slocCode+"'");

        Query sqlQuery=hibSession.createSQLQuery(sql.toString());

        List resList=sqlQuery.list();
        if (resList!=null && resList.size()>0) 
        {
          Iterator it = resList.iterator();
          while(it.hasNext()) 
          {
            Object tuple = (Object)it.next();
            noOfVouchers=Integer.parseInt(tuple.toString());
          }
        }

      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.countVouchersDist # \n"+e);
    }
    return noOfVouchers;
  }

  /**
   * This method returns List of vouchers major head wise that are not distributed for Detail posting
   * @param userId Logged in userid 
   * @return List<DistVoucherVO.class>
   */	
  public List getVouchersForDist(Long postId, List billLst,String slocCode) 
  {		
    List dataList = null;
    try 
    {

      Session hibSession = getSession();
      if(billLst!=null && billLst.size()>0) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT COUNT(BR.BILL_NO),SUM(BR.BILL_NET_AMOUNT),BD.MAJOR_HEAD "+
            " FROM TRN_BILL_REGISTER BR,TRN_VOUCHER_DETAILS BD,TRN_BILL_MVMNT BM,(SELECT BILL_NO,MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
            " WHERE BR.BILL_NO=BD.BILL_NO AND BR.BILL_NO=BM.BILL_NO AND T.BILL_NO = BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
            " AND BM.RECEIVED_FLAG="+1+" AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_VCHR_GEN+"' AND BM.STATUS_UPDT_POSTID="+postId+" AND BM.BILL_NO IN (");

        for (int i=0; i < billLst.size(); i++) 
          if(i!=(billLst.size()-1)) sql.append(billLst.get(i)+",");
          else sql.append(billLst.get(i));

        sql.append(")");
        sql.append(" AND BR.TSRY_OFFICE_CODE ='"+slocCode+"'");
        sql.append(" GROUP BY BD.MAJOR_HEAD");

        Query sqlQuery=hibSession.createSQLQuery(sql.toString());

        List resList=sqlQuery.list();
        if (resList!=null && resList.size()>0) 
        {
          dataList = new ArrayList();
          Iterator it = resList.iterator();
          while(it.hasNext()) 
          {
            DistVoucherVO vo=new DistVoucherVO();
            Object[] tuple = (Object[])it.next();
            vo.setNoOfVoucher(Integer.parseInt(tuple[0].toString()));
            vo.setTotalNetAmount((BigDecimal) tuple[1]);
            vo.setMajorHead((String) tuple[2]);							
            dataList.add(vo);
            //System.out.println(tuple[0]+ " - " + tuple[1] + " - " + tuple[2]);
          }
        }				
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getVouchersForDist # \n"+e);
    }
    return dataList;
  }

  /**
   * This method returns List of vouchers that are not distributed for Detail posting
   * @param userId Logged in userid 
   * @return List<TrnVoucherDetails.class>
   */

  public List<TrnVoucherDetails> getUndistributedVouchers(Long postId, List billLst,String slocCode) 
  {	
    List dataList = null;
    try 
    {
      Session hibSession = getSession();
      StringBuffer sql = new StringBuffer();			
      sql.append("SELECT BR.BILL_NO,BR.BUDMJR_HD,BR.PHY_BILL FROM TRN_BILL_REGISTER BR, TRN_BILL_MVMNT BM ,(SELECT BILL_NO,MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
          " WHERE BR.BILL_NO = BM.BILL_NO AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_VCHR_GEN+"' AND T.BILL_NO = BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS AND BM.RECEIVED_FLAG = 1 " +
          " AND BM.STATUS_UPDT_POSTID="+postId+ " AND BR.BILL_NO IN (");

      for (int i=0; i < billLst.size(); i++) 
        if(i!=(billLst.size()-1)) sql.append(billLst.get(i)+",");
        else sql.append(billLst.get(i));

      sql.append(")");
      sql.append(" AND BR.TSRY_OFFICE_CODE='"+slocCode+"'");
      Query sqlQuery=hibSession.createSQLQuery(sql.toString());
      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) {
        dataList = new ArrayList();
        Iterator it = resList.iterator();
        while(it.hasNext()) {
          TrnVoucherDetails vo=new TrnVoucherDetails();
          Object[] tuple = (Object[])it.next();
          vo.setBillNo(Long.parseLong(tuple[0].toString()));
          vo.setMajorHead((String) tuple[1]);
          dataList.add(vo);
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getUndistributedVouchers # \n"+e);
    }
    return dataList;
  }

  /**
   * This method update the distribute flag and updated userid when distribute the voucher
   * @param objectArg Map consisting of billNo(long), userId(long)  
   */
  public void markVouchDist(Map objectArg)
  {
    try 
    {
      long billNo=Long.parseLong(objectArg.get("billNo").toString());
      long userId=Long.parseLong(objectArg.get("userId").toString());
      String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
      Session hibSession = getSession();
      hibSession.createQuery("update TrnVoucherDetails vd set distributed=1,updatedUserId="+userId+"," +
          "updatedDate='"+dateTime+"'where billNo="+billNo).executeUpdate();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.setDistributedFlag # \n"+e);
    }
  }

  /**
   * This method returns List of vouchers that are not received in Detail posting
   * @param userId Logged in userid 
   * @return List<VoucherVO.class>
   */
  public List getVouchersForDetPost(Long postId, List billLst, Long langId, String slocCode) 
  {	
    List dataList = null;
    try 
    {
      if (billLst!=null && billLst.size()>0) {
        Session hibSession = getSession();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT VD.VOUCHER_NO, VD.VOUCHER_DATE, VD.BILL_NO, BR.BILL_CNTRL_NO, "+
            " VD.MAJOR_HEAD, BR.BILL_NET_AMOUNT, BR.BILL_DATE, BT.SUBJECT_DESC ,DC.CARDEX_NO " +
            " FROM TRN_VOUCHER_DETAILS VD, TRN_BILL_REGISTER BR, MST_BILL_TYPE BT, ORG_DDO_MST DC, TRN_BILL_MVMNT BM,(SELECT BILL_NO, MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
            " WHERE VD.BILL_NO = BR.BILL_NO AND BM.BILL_NO=VD.BILL_NO AND BR.SUBJECT_ID = BT.SUBJECT_ID AND T.BILL_NO=BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
            " AND BR.DDO_CODE =DC.DDO_CODE AND BM.STATUS_UPDT_POSTID="+postId+"  AND BM.RECEIVED_FLAG="+0+ " AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_VCHR_DSTRBTD+"' AND  BM.BILL_NO IN (");

        for (int i=0; i < billLst.size(); i++) 
          if(i!=(billLst.size()-1)) sql.append(billLst.get(i)+",");
          else sql.append(billLst.get(i));

        sql.append(")");
        sql.append(" AND BT.LANG_ID="+langId+" AND DC.LANG_ID="+langId+" AND BR.TSRY_OFFICE_CODE='"+slocCode+"'");
        sql.append(" ORDER BY VD.VOUCHER_DATE DESC");

        List resList=hibSession.createSQLQuery(sql.toString()).list();
        if (resList!=null && resList.size()>0) {
          dataList = new ArrayList();
          Iterator it = resList.iterator();
          while(it.hasNext()) 
          {
            VoucherVO vo=new VoucherVO();
            Object[] tuple = (Object[])it.next();
            vo.setVoucherNo(tuple[0].toString());
            vo.setVoucherDate((Date) tuple[1]);
            vo.setBillNo(Long.parseLong(tuple[2].toString()));	
            vo.setBillCntrlNo((String) tuple[3]);
            vo.setMajorHead((String) tuple[4]);
            vo.setBillNetAmount((BigDecimal) tuple[5]);
            vo.setBillDate((Date) tuple[6]);
            vo.setSubjectDesc((String) tuple[7]);
            vo.setCardexNo((String) tuple[8]);
            dataList.add(vo);
          }
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getVouchersForDetPost # \n"+e);
    }
    return dataList;
  }

  /**
   * This method update the received flag when received voucher for Detail posting
   * @param objectArg Map consisting of billsNo(String[]), userId(long)  
   */
  public void accVouchersForDetPost(Map objectArg) 
  {
    try 
    {
      String[] billsNo=(String[])objectArg.get("billsNo");
      Long userId=Long.parseLong(objectArg.get("userId").toString());
      Long postId=Long.parseLong(objectArg.get("postId").toString());
      String slocCode=(String)objectArg.get("slocCode");
      String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
      Session hibSession = getSession();
      if(billsNo!=null)
      {
        for(int i=0;i<billsNo.length;i++)
        {
          long billNo=Long.parseLong(billsNo[i]); 
          hibSession.createQuery("update TrnBillMvmnt " +
              " set receivedFlag=1,receivedDate=to_date('"+dateTime+"','yyyy-mm-dd'),updatedUserId="+userId+",updatedDate=to_date('"+dateTime+"','yyyy-mm-dd'),receivingUserId="+userId+" " +
              " where billNo="+billNo+"and statusUpdtUserid="+userId+" and statusUpdtPostid="+postId+" and locationCode='"+slocCode+"'").executeUpdate();
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.accVouchersForDetPost # \n"+e);
    }
  }

  /**
   * This method returns List of vouchers that are received in Detail posting
   * @param userId Logged in userid 
   * @return List<VoucherVO.class>
   */
  public List getVouchersListForDet(Long postId, List billLst, Long langId, String slocCode, Long lngVchrSt) 
  {		
    List dataList = null;

    try 
    {
      Session hibSession = getSession();
      if (billLst != null && billLst.size()>0) {

        StringBuffer sql = new StringBuffer();
        if(lngVchrSt==0)
        {
          sql.append("SELECT VD.VOUCHER_NO, VD.VOUCHER_DATE, VD.BILL_NO, BR.BILL_CNTRL_NO, "+
              " VD.MAJOR_HEAD, BR.BILL_NET_AMOUNT, BR.BILL_DATE, BT.SUBJECT_DESC ,DC.CARDEX_NO,BT.SUBJECT_ID " +
              " FROM TRN_VOUCHER_DETAILS VD, TRN_BILL_REGISTER BR, MST_BILL_TYPE BT, ORG_DDO_MST DC, TRN_BILL_MVMNT BM,(SELECT BILL_NO, MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
              " WHERE VD.BILL_NO = BR.BILL_NO AND BM.BILL_NO=VD.BILL_NO AND BR.SUBJECT_ID = BT.SUBJECT_ID AND T.BILL_NO=BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
              " AND BR.DDO_CODE =DC.DDO_CODE AND BM.STATUS_UPDT_POSTID="+postId+"  AND BM.RECEIVED_FLAG=1 AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_VCHR_DSTRBTD+"' AND  BM.BILL_NO IN (");
        }
        else
        {
          Date endDate=this.getEndDateFromVerAcc(slocCode);
          String strEndDate=null;
          if(endDate == null)
          {
            strEndDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse("1997-1-1"));
          }
          else
          {
            strEndDate = endDate.toString();
          }
          sql.append("SELECT VD.VOUCHER_NO, VD.VOUCHER_DATE, VD.BILL_NO, BR.BILL_CNTRL_NO, "+
              " VD.MAJOR_HEAD, BR.BILL_NET_AMOUNT, BR.BILL_DATE, BT.SUBJECT_DESC ,DC.CARDEX_NO,BT.SUBJECT_ID " +
              " FROM TRN_VOUCHER_DETAILS VD, TRN_BILL_REGISTER BR, MST_BILL_TYPE BT, ORG_DDO_MST DC, TRN_BILL_MVMNT BM,(SELECT BILL_NO, MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
              " WHERE VD.BILL_NO = BR.BILL_NO AND BM.BILL_NO=VD.BILL_NO AND BR.SUBJECT_ID = BT.SUBJECT_ID AND T.BILL_NO=BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
              " AND BR.DDO_CODE =DC.DDO_CODE AND BM.STATUS_UPDT_POSTID="+postId+"  AND BM.RECEIVED_FLAG=0 AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_DTL_PSTNG_DONE+"' AND " +
              " TO_CHAR(VD.VOUCHER_DATE,'YYYY-MM-DD') >'"+strEndDate+"' AND BM.BILL_NO IN (");
        }

        for (int i=0; i < billLst.size(); i++) 
          if(i!=(billLst.size()-1)) sql.append(billLst.get(i)+",");
          else sql.append(billLst.get(i));

        sql.append(")");
        sql.append(" AND BT.LANG_ID="+langId+" AND DC.LANG_ID="+langId+" AND BR.TSRY_OFFICE_CODE='"+slocCode+"'");
        sql.append(" ORDER BY VD.VOUCHER_DATE DESC");
        //System.out.println("String sql is " + sql);	
        Query sqlQuery=hibSession.createSQLQuery(sql.toString());

        List resList=sqlQuery.list();
        if (resList!=null && resList.size()>0) 
        {
          dataList = new ArrayList();
          Iterator it = resList.iterator();
          while(it.hasNext()) 
          {
            VoucherVO vo=new VoucherVO();
            Object[] tuple = (Object[])it.next();
            vo.setVoucherNo(tuple[0].toString());
            vo.setVoucherDate((Date) tuple[1]);
            vo.setBillNo(Long.parseLong(tuple[2].toString()));	
            vo.setBillCntrlNo((String) tuple[3]);
            vo.setMajorHead((String) tuple[4]);
            vo.setBillNetAmount(new BigDecimal(tuple[5].toString()));
            vo.setBillDate((Date) tuple[6]);
            vo.setSubjectDesc((String) tuple[7]);
            vo.setCardexNo((String) tuple[8]);
            vo.setSubjectId(Long.parseLong(tuple[9].toString()));
            dataList.add(vo);
          }
        }

      }

    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getVouchersListForDet # \n"+e);
    }
    return dataList;
  }

  /**
   * This method to get freezed vouchers.
   * @param postId
   * @param billLst
   * @param langId
   * @param slocCode
   * @return
   */
  public List getFreezedVoucher(Long postId, List billLst, Long langId, String slocCode)
  {
    List dataList = null;
    try 
    {
      Session hibSession = getSession();
      if (billLst != null && billLst.size()>0) {

        StringBuffer sql = new StringBuffer();
        Date endDate=this.getEndDateFromVerAcc(slocCode);
        String strEndDate=null;

        if(endDate == null)
        {
          strEndDate = new SimpleDateFormat("yyyy-MM-dd").format
          (new SimpleDateFormat("yyyy-MM-dd").parse("1997-1-1"));
        }
        else
        {
          strEndDate=endDate.toString();
        }

        //System.out.println("End Date is :-"+strEndDate);
        sql.append("SELECT VD.VOUCHER_NO, VD.VOUCHER_DATE, VD.BILL_NO, BR.BILL_CNTRL_NO, "+
            " VD.MAJOR_HEAD, BR.BILL_NET_AMOUNT, BR.BILL_DATE, BT.SUBJECT_DESC ,DC.CARDEX_NO,BT.SUBJECT_ID " +
            " FROM TRN_VOUCHER_DETAILS VD, TRN_BILL_REGISTER BR, MST_BILL_TYPE BT, ORG_DDO_MST DC, " +
            " TRN_BILL_MVMNT BM,(SELECT BILL_NO, MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
            " WHERE VD.BILL_NO = BR.BILL_NO AND BM.BILL_NO=VD.BILL_NO AND BR.SUBJECT_ID = BT.SUBJECT_ID " +
            " AND T.BILL_NO=BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
            " AND BR.DDO_CODE =DC.DDO_CODE AND BM.STATUS_UPDT_POSTID="+postId+"  AND BM.RECEIVED_FLAG=0 AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_DTL_PSTNG_DONE+"' AND " +
            " TO_CHAR(VD.VOUCHER_DATE,'YYYY-MM-DD') <='"+strEndDate+"' AND BM.BILL_NO IN (");

        for (int i=0; i < billLst.size(); i++) 
          if(i!=(billLst.size()-1)) sql.append(billLst.get(i)+",");
          else sql.append(billLst.get(i));

        sql.append(")");
        sql.append(" AND BT.LANG_ID="+langId+" AND DC.LANG_ID="+langId+" AND BR.TSRY_OFFICE_CODE='"+slocCode+"'");
        sql.append(" ORDER BY VD.VOUCHER_DATE DESC");
        //System.out.println("String sql is " + sql);	
        Query sqlQuery=hibSession.createSQLQuery(sql.toString());

        List resList=sqlQuery.list();
        if (resList!=null && resList.size()>0) 
        {
          dataList = new ArrayList();
          Iterator it = resList.iterator();
          while(it.hasNext()) 
          {
            VoucherVO vo=new VoucherVO();
            Object[] tuple = (Object[])it.next();
            vo.setVoucherNo(tuple[0].toString());
            vo.setVoucherDate((Date) tuple[1]);
            vo.setBillNo(Long.parseLong(tuple[2].toString()));	
            vo.setBillCntrlNo((String) tuple[3]);
            vo.setMajorHead((String) tuple[4]);
            vo.setBillNetAmount(new BigDecimal(tuple[5].toString()));
            vo.setBillDate((Date) tuple[6]);
            vo.setSubjectDesc((String) tuple[7]);
            vo.setCardexNo((String) tuple[8]);
            vo.setSubjectId(Long.parseLong(tuple[9].toString()));
            dataList.add(vo);
          }
        }

      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getFreezedVoucher # \n"+e);
    }
    return dataList;
  }

  /**
   * This method to get voucher details
   * @param postId
   * @param billno
   * @param langId
   * @param slocCode
   * @return VoucherVO
   */
  public VoucherVO getVoucherDtls(Long postId,long billno,Long langId, String slocCode) 
  {			
    //List dataList = null;
    VoucherVO vo=null;
    try 
    {
      Session hibSession = getSession();
      Query sqlQuery=hibSession.createQuery("select vd.voucherNo, vd.voucherDate, br.billNo, br.billCntrlNo, br.billDate,br.billNetAmount,br.billGrossAmount,br.principle,br.grossInterest,br.incomeTax, " +
          " bt.subjectDesc ,dc.cardexNo,dc.ddoName,br.ddoCode, br.subjectId from TrnVoucherDetails vd, TrnBillRegister br, " +
          " MstBillType bt, OrgDdoMst dc " +
          " where vd.billNo = br.billNo and br.subjectId = bt.subjectId and br.ddoCode=dc.ddoCode and br.billNo="+billno+
          " and bt.langId="+langId+" and dc.langId="+langId+" and br.tsryOfficeCode='"+slocCode+"'");


      //System.out.println("SIZE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
      List resList=sqlQuery.list();
      //System.out.println("size of List-->"+resList.size());
      if (resList!=null && resList.size()>0) 
      {
        //dataList = new ArrayList();
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          vo=new VoucherVO();
          Object[] tuple = (Object[])it.next();
          vo.setVoucherNo(String.valueOf((Long) tuple[0]));
          vo.setVoucherDate((Date) tuple[1]);
          vo.setBillNo(Long.parseLong(tuple[2].toString()));
          vo.setBillCntrlNo((String) tuple[3]);
          vo.setBillDate((Date) tuple[4]);
          vo.setBillNetAmount((BigDecimal) tuple[5]);
          vo.setBillGrossAmount((BigDecimal) tuple[6]);
          vo.setPrinciple((BigDecimal) tuple[7]);
          vo.setBillGrossInt((BigDecimal) tuple[8]);
          vo.setIncomeTax((BigDecimal) tuple[9]);
          vo.setSubjectDesc((String) tuple[10]);
          vo.setCardexNo((String) tuple[11]);
          vo.setDdoName((String) tuple[12]);
          vo.setDdoCode((String) tuple[13]);
          vo.setSubjectId(Long.parseLong(tuple[14].toString()));
          //dataList.add(vo);
          //System.out.println("--"+tuple[0]+"--"+tuple[1]+"-"+tuple[2]+"---------------------------"+tuple[14]);
        }
      }  
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getVoucherDtls # \n"+e);
    }		
    return vo;
  }

  /**
   * This method to get tc Bill major head.
   * @param billNo
   * @param slocCode
   * @return Map
   */
  public Map getTCDetails(long billNo,String slocCode)
  {
    Map lobjTcMap=new HashMap();
    try 
    {
      Session hibSession = getSession();
      Query sqlQuery=hibSession.createQuery("select br.tcBill,rd.majorHead from TrnBillRegister br,TrnReceiptDetails rd " +
          " where br.receiptId =rd.receiptDetailId and br.billNo="+billNo+" and br.tsryOfficeCode='"+slocCode+"'");

      List resList=sqlQuery.list();
      //System.out.println("size of List in TCS Map-->"+resList.size());
      if (resList!=null && resList.size()>0) 
      {
        //dataList = new ArrayList();
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          Object tuple[] = (Object[])it.next();
          lobjTcMap.put("tcBill", tuple[0]);
          lobjTcMap.put("majorHead", tuple[1]);
        }
      }  
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getTCDetails # \n"+e);
    }		
    return lobjTcMap;
  }

  /**
   * This method to get budjet head details.
   * @param billno
   * @param slocCode
   * @return budHeadDtlVo
   */
  public TrnBillBudheadDtls getBujectHeadDtls(long billno,String slocCode) 
  {			
    //List dataList = null;
    TrnBillBudheadDtls budHeadDtlVo=null;
    try 
    {
      Session hibSession = getSession();
      Query sqlQuery=hibSession.createQuery("select bd.clsExp, bd.fund, bd.dmndNo,bd.schemeNo,bd.headChrg,bd.budType, " +
          " bd.budMjrHd ,bd.budSubmjrHd,bd.budMinHd,bd.budSubHd,bd.budDtlHd from TrnBillBudheadDtls bd " +
          "where bd.billNo="+billno);

      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {
        //dataList = new ArrayList();
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          budHeadDtlVo=new TrnBillBudheadDtls();
          Object[] tuple = (Object[])it.next();
          if(tuple[0]!=null)
            budHeadDtlVo.setClsExp(tuple[0].toString());
          if(tuple[1]!=null)
            budHeadDtlVo.setFund(tuple[1].toString());
          if(tuple[2]!=null)
            budHeadDtlVo.setDmndNo((String) tuple[2]);
          if(tuple[3]!=null)
            budHeadDtlVo.setSchemeNo((String) tuple[3]);
          if(tuple[4]!=null)
            budHeadDtlVo.setHeadChrg((String) tuple[4]);
          if(tuple[5]!=null)
            budHeadDtlVo.setBudType(tuple[5].toString());
          if(tuple[6]!=null)
            budHeadDtlVo.setBudMjrHd((String) tuple[6]);
          if(tuple[7]!=null)
            budHeadDtlVo.setBudSubmjrHd((String) tuple[7]);
          if(tuple[8]!=null)
            budHeadDtlVo.setBudMinHd((String) tuple[8]);
          if(tuple[9]!=null)
            budHeadDtlVo.setBudSubHd((String) tuple[9]);
          if(tuple[10]!=null)
            budHeadDtlVo.setBudDtlHd((String) tuple[10]);

          //System.out.println("Bud Type Is   >>>>>>>>>>>>>>>>>"+budHeadDtlVo.getBudType());
        }
      }  
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getBujectHeadDtls # \n"+e);
    }		
    return budHeadDtlVo;
  }

  /**
   * This method to delete priviously added EDP Code .
   * @param billNo
   * @param slocCode
   * @return
   */
  public Map deleteExtAddEdpCode(Long billNo,String slocCode) 
  {
    Map retEdpIdMap=new HashMap();
    try 
    {
      //System.out.println("inside Delete DAO Impl");
      Session hibSession = getSession();
      Query sqlQuery=hibSession.createQuery("select billEdpId from TrnBillEdpDtls where billNo="+billNo+" and (expRcpRec='EXP' or expRcpRec='REC')");
      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {
        Iterator it = resList.iterator();
        List expEdpIdLst=new ArrayList();
        while(it.hasNext()) 
        {
          Object tuple = (Object)it.next();
          expEdpIdLst.add(tuple);
        }
        retEdpIdMap.put("expEdpIdLst",expEdpIdLst);
      }  

      sqlQuery=hibSession.createQuery("select billEdpId from TrnBillEdpDtls where billNo="+billNo+" and expRcpRec='RCP'");
      resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {
        Iterator it = resList.iterator();
        List rcptEdpIdLst=new ArrayList();
        while(it.hasNext()) 
        {
          Object tuple = (Object)it.next();
          rcptEdpIdLst.add(tuple);
        }
        retEdpIdMap.put("rcptEdpIdLst",rcptEdpIdLst);
      }  
      hibSession.createQuery("delete from TrnBillEdpDtls where billNo="+billNo).executeUpdate();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.deleteExtAddEdpCode # \n"+e);
    }
    return retEdpIdMap;
  }

  /**
   * This method update gross interest and income tax column.
   * @param objectArg
   */
  public void updateAmount(Map objectArg) 
  {
    try 
    {
      VoucherVO vouchVo=(VoucherVO)objectArg.get("vouchVo");
      String slocCode=(String)objectArg.get("slocCode");
      BigDecimal grossInt=vouchVo.getBillGrossInt();
      BigDecimal incomeTax=vouchVo.getIncomeTax();
      long billNo=vouchVo.getBillNo();
      Session hibSession = getSession();
      hibSession.createQuery("update TrnBillRegister set grossInterest="+grossInt+",incomeTax="+incomeTax+" where billNo="+billNo+" and tsryOfficeCode='"+slocCode+"'").executeUpdate();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.accVouchersForDetPost # \n"+e);
    }
  }

  /**
   * This method to get demand no and major-head by billNo.
   * @param objectArg
   * @return ArrayList
   */
  public ArrayList getMajHdByBillNo(long billNo)
  {
    ArrayList demMajHd=null;
    try
    {
      Session hibSession = getSession();
      Query sqlQuery=hibSession.createQuery("select dmndNo,budMjrHd from TrnBillBudheadDtls where billNo="+billNo);			
      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {
        //dataList = new ArrayList();
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          demMajHd=new ArrayList();
          Object[] tuple = (Object[])it.next();
          demMajHd.add(tuple[0]);
          demMajHd.add(tuple[1]);
        }
      }  
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getMajHdByBillNo # \n"+e);
    }		
    return demMajHd;
  }

  /**
   * This method returns arraylist of all jobs in work flow by posts
   * @param postList ArrayList containing post Ids
   * @return List
   */
  public List getJobs(ArrayList postList) {
    List docList = null;
    try {
      /* create work flow object .. */
      WorkFlowVO workFlowVO = new WorkFlowVO();
      workFlowVO.setConnection(getSession().connection());			
      OrgUtilityImpl utilImpl = new OrgUtilityImpl(workFlowVO);
      List list = new ArrayList();
      list.add("13");

      /* get document list from work flow .. */
      docList = utilImpl.getDocList(list);			
    } catch(Exception ex) {
      ex.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getJobs # \n"+ex);
    }
    return docList;
  }

  /**
   * This method to get voucher details as par search crieria.
   * @param billLst
   * @param map
   * @return List
   */

  public List getSearchVoucher(Map map, List billLst) 
  {			
    List dataList = null;
    try 
    {
      Query sqlQuery=null;

      if (billLst!=null && billLst.size()>0) {

        String searchBy=(String) map.get("searchBy");
        String searchValue=(String)map.get("searchValue");
        String viewPage=(String)map.get("viewPage");
        long userId=Long.parseLong(map.get("userId").toString());
        Long postId=Long.parseLong(map.get("postId").toString());
        StringBuffer query=new StringBuffer();
        Session hibSession = getSession();
        if(viewPage.equalsIgnoreCase("accVouchFrmCB"))
        {
          //System.out.println("Inside cb voucher and user id is:-"+userId);
          query.append("SELECT VD.VOUCHER_NO, VD.VOUCHER_DATE, VD.BILL_NO, BR.BILL_CNTRL_NO, "+
              " VD.MAJOR_HEAD, BR.BILL_NET_AMOUNT, BR.BILL_DATE, BT.SUBJECT_DESC ,DC.CARDEX_NO" +
              " FROM TRN_VOUCHER_DETAILS VD, TRN_BILL_REGISTER BR, MST_BILL_TYPE BT, ORG_DDO_MST DC, TRN_BILL_MVMNT BM,(SELECT BILL_NO, MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
              " WHERE VD.BILL_NO = BR.BILL_NO AND BM.BILL_NO=VD.BILL_NO AND BR.SUBJECT_ID = BT.SUBJECT_ID AND T.BILL_NO=BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
              " AND BR.DDO_CODE =DC.DDO_CODE AND BM.STATUS_UPDT_POSTID="+postId+" AND BM.RECEIVED_FLAG=0 AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_VCHR_GEN+"'");
        } 
        else if( viewPage.equalsIgnoreCase("accVouchForDet"))
        {
          //System.out.println("Inside cb voucher and user id is:-"+userId);
          query.append("SELECT VD.VOUCHER_NO, VD.VOUCHER_DATE, VD.BILL_NO, BR.BILL_CNTRL_NO, "+
              " VD.MAJOR_HEAD, BR.BILL_NET_AMOUNT, BR.BILL_DATE, BT.SUBJECT_DESC ,DC.CARDEX_NO" +
              " FROM TRN_VOUCHER_DETAILS VD, TRN_BILL_REGISTER BR, MST_BILL_TYPE BT, ORG_DDO_MST DC, TRN_BILL_MVMNT BM,(SELECT BILL_NO, MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
              " WHERE VD.BILL_NO = BR.BILL_NO AND BM.BILL_NO=VD.BILL_NO AND BR.SUBJECT_ID = BT.SUBJECT_ID AND T.BILL_NO=BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
              " AND BR.DDO_CODE =DC.DDO_CODE AND BM.STATUS_UPDT_POSTID="+postId+"  AND BM.RECEIVED_FLAG=0 AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_VCHR_DSTRBTD+"'");
        }
        else if(viewPage.equalsIgnoreCase("vouchListForDet"))
        {
          query.append("SELECT VD.VOUCHER_NO, VD.VOUCHER_DATE, VD.BILL_NO, BR.BILL_CNTRL_NO, "+
              " VD.MAJOR_HEAD, BR.BILL_NET_AMOUNT, BR.BILL_DATE, BT.SUBJECT_DESC ,DC.CARDEX_NO" +
              " FROM TRN_VOUCHER_DETAILS VD, TRN_BILL_REGISTER BR, MST_BILL_TYPE BT, ORG_DDO_MST DC, TRN_BILL_MVMNT BM,(SELECT BILL_NO, MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
              " WHERE VD.BILL_NO = BR.BILL_NO AND BM.BILL_NO=VD.BILL_NO AND BR.SUBJECT_ID = BT.SUBJECT_ID AND T.BILL_NO=BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
              " AND BR.DDO_CODE =DC.DDO_CODE AND BM.STATUS_UPDT_POSTID="+postId+"  AND BM.RECEIVED_FLAG=1 AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_VCHR_DSTRBTD+"'");

        }
        else if(viewPage.equalsIgnoreCase("vouchListPosted"))
        {
          query.append("SELECT VD.VOUCHER_NO, VD.VOUCHER_DATE, VD.BILL_NO, BR.BILL_CNTRL_NO, "+
              " VD.MAJOR_HEAD, BR.BILL_NET_AMOUNT, BR.BILL_DATE, BT.SUBJECT_DESC ,DC.CARDEX_NO" +
              " FROM TRN_VOUCHER_DETAILS VD, TRN_BILL_REGISTER BR, MST_BILL_TYPE BT, ORG_DDO_MST DC, TRN_BILL_MVMNT BM,(SELECT BILL_NO, MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
              " WHERE VD.BILL_NO = BR.BILL_NO AND BM.BILL_NO=VD.BILL_NO AND BR.SUBJECT_ID = BT.SUBJECT_ID AND T.BILL_NO=BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
              " AND BR.DDO_CODE =DC.DDO_CODE AND BM.STATUS_UPDT_POSTID="+postId+"  AND BM.RECEIVED_FLAG=0 AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_DTL_PSTNG_DONE+"'");
        }
        else if(viewPage.equalsIgnoreCase("vouchListPosted"))
        {
          query.append("SELECT VD.VOUCHER_NO, VD.VOUCHER_DATE, VD.BILL_NO, BR.BILL_CNTRL_NO, "+
              " VD.MAJOR_HEAD, BR.BILL_NET_AMOUNT, BR.BILL_DATE, BT.SUBJECT_DESC ,DC.CARDEX_NO" +
              " FROM TRN_VOUCHER_DETAILS VD, TRN_BILL_REGISTER BR, MST_BILL_TYPE BT, ORG_DDO_MST DC, TRN_BILL_MVMNT BM,(SELECT BILL_NO, MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
              " WHERE VD.BILL_NO = BR.BILL_NO AND BM.BILL_NO=VD.BILL_NO AND BR.SUBJECT_ID = BT.SUBJECT_ID AND T.BILL_NO=BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
              " AND BR.DDO_CODE =DC.DDO_CODE AND BM.STATUS_UPDT_POSTID="+postId+"  AND BM.RECEIVED_FLAG=0 AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_DTL_PSTNG_DONE+"'");
        }
        else if(viewPage.equalsIgnoreCase("vouchListRecordRoom"))
        {
          query.append("SELECT VD.VOUCHER_NO, VD.VOUCHER_DATE, VD.BILL_NO, BR.BILL_CNTRL_NO, "+
              " VD.MAJOR_HEAD, BR.BILL_NET_AMOUNT, BR.BILL_DATE, BT.SUBJECT_DESC ,DC.CARDEX_NO" +
              " FROM TRN_VOUCHER_DETAILS VD, TRN_BILL_REGISTER BR, MST_BILL_TYPE BT, ORG_DDO_MST DC, TRN_BILL_MVMNT BM,(SELECT BILL_NO, MAX(MOVEMNT_ID) AS IDS FROM TRN_BILL_MVMNT GROUP BY BILL_NO) T " +
              " WHERE VD.BILL_NO = BR.BILL_NO AND BM.BILL_NO=VD.BILL_NO AND BR.SUBJECT_ID = BT.SUBJECT_ID AND T.BILL_NO=BM.BILL_NO AND BM.MOVEMNT_ID = T.IDS " +
              " AND BR.DDO_CODE =DC.DDO_CODE AND BM.STATUS_UPDT_POSTID="+postId+"  AND BM.RECEIVED_FLAG=0 AND BR.CURR_BILL_STATUS = BM.MVMNT_STATUS AND BR.CURR_BILL_STATUS = '"+DBConstants.ST_DTL_PSTNG_DONE+"'");
        }


        if(searchBy.equals("Bill Date"))
        {
          Date date=new SimpleDateFormat("dd/MM/yyyy").parse(searchValue);
          String SearchDate=new SimpleDateFormat("yyyy-MM-dd").format(date);
          query.append(" AND TO_CHAR(BR.BILL_DATE,'YYYY-MM-DD')='");
          query.append(SearchDate);

        }
        else if(searchBy.equals("Bill Control No"))
        {
          query.append(" AND BR.BILL_CNTRL_NO LIKE '");
          query.append(searchValue +"%");
        }
        else if(searchBy.equals("Bill Type"))
        {
          query.append(" AND BT.SUBJECT_DESC LIKE '");
          query.append(searchValue+"%");
        }
        else if(searchBy.equals("Cardex No"))
        {
          query.append(" AND DC.CARDEX_NO LIKE '");
          query.append(searchValue+"%");
        }
        else if(searchBy.equals("Voucher Date"))
        {
          Date date=new SimpleDateFormat("dd/MM/yyyy").parse(searchValue);
          String SearchDate=new SimpleDateFormat("yyyy-MM-dd").format(date);
          query.append(" AND TO_CHAR(VD.VOUCHER_DATE,'YYYY-MM-DD')='");	
          query.append(SearchDate);
        }
        else if(searchBy.equals("Voucher No"))
        {
          query.append(" AND VD.VOUCHER_NO LIKE '");
          query.append(searchValue + "%");
        }
        else if(searchBy.equals("Major Head"))
        {
          query.append(" AND VD.MAJOR_HEAD LIKE '");
          query.append(searchValue+"%");
        }
        //query.append(searchValue);
        query.append("' AND BM.BILL_NO IN (");

        for (int i=0; i < billLst.size(); i++) 
          if(i!=(billLst.size()-1)) query.append(billLst.get(i)+",");
          else query.append(billLst.get(i));

        query.append(")");

        //System.out.println("Query is=====" + query.toString());
        sqlQuery = hibSession.createSQLQuery(query.toString());
        List resList=sqlQuery.list();
        if (resList!=null && resList.size()>0) 
        {
          dataList = new ArrayList();
          Iterator it = resList.iterator();
          while(it.hasNext()) 
          {
            VoucherVO vo=new VoucherVO();
            Object[] tuple = (Object[])it.next();
            vo.setVoucherNo(tuple[0].toString());
            vo.setVoucherDate((Date) tuple[1]);
            vo.setBillNo(Long.parseLong(tuple[2].toString()));	
            vo.setBillCntrlNo((String) tuple[3]);
            vo.setMajorHead((String) tuple[4]);
            vo.setBillNetAmount((BigDecimal) tuple[5]);
            vo.setBillDate((Date) tuple[6]);
            vo.setSubjectDesc((String) tuple[7]);
            vo.setCardexNo((String) tuple[8]);
            dataList.add(vo);
          }
        }  
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getSearchVoucher # \n"+e);
    }		
    return dataList;
  }

  /**
   * This method to get next voucher majot head wise.
   * @param billNo
   * @param locationCode
   * @return TrnVoucherDetails
   */

  public TrnVoucherDetails getNextVoucherMjrHdWise(String billNo,String locationCode)
  {
    TrnVoucherDetails voucherVo = new TrnVoucherDetails();
    //System.out.println(" came in ........ dao for mvmnt");
    Session hibSession = getSession();	
    Query sqlQuery = hibSession.createSQLQuery(" SELECT BUDMJR_HD FROM TRN_BILL_REGISTER WHERE BILL_NO = "+billNo);

    Iterator iterator = sqlQuery.list().iterator();
    long maxVoucherNum = 0;
    String lStrMjrHd= "";
    while(iterator.hasNext())
    {
      lStrMjrHd = (String)iterator.next();			
    }
    voucherVo.setMajorHead(lStrMjrHd);

    sqlQuery = hibSession.createSQLQuery(" SELECT MAX(VOUCHER_NO) FROM TRN_VOUCHER_DETAILS WHERE MAJOR_HEAD ='"+lStrMjrHd+"' AND EXTRACT(MONTH FROM VOUCHER_DATE) = EXTRACT(MONTH FROM SYSDATE) AND LOCATION_CODE ='"+locationCode+"' GROUP BY MAJOR_HEAD "); 
    iterator = sqlQuery.list().iterator();		
    while(iterator.hasNext())
    {
      java.math.BigDecimal voucher = (java.math.BigDecimal)iterator.next();
      if(voucher != null)
      {				
        maxVoucherNum = voucher.longValue();
      }
    }

    //System.out.println(" maxVoucherNum is "  + maxVoucherNum);
    maxVoucherNum = maxVoucherNum + 1;		
    voucherVo.setVoucherNo(maxVoucherNum);
    voucherVo.setVoucherDate(new java.util.Date(System.currentTimeMillis()));
    //System.out.println(" \n Returning ID "  + maxVoucherNum);
    return voucherVo;
  }	
  
  /**
   * This method to get next voucher month wise.
   * @param billNo
   * @param locationCode
   * @return TrnVoucherDetails
   */

  public TrnVoucherDetails getNextVoucherMonthWise(String billNo,String locationCode)
  {
    TrnVoucherDetails voucherVo = new TrnVoucherDetails();
    //System.out.println(" came in ........ dao for mvmnt");
    Session hibSession = getSession();	

    //Query sqlQuery = hibSession.createSQLQuery(" select max(voucher_no),major_head from trn_voucher_details where major_head in (select budmjr_hd from trn_bill_register where bill_no = "+billNo+") and extract(month from voucher_date) = extract(month from current_date()) group by major_head");
    Query sqlQuery = hibSession.createSQLQuery(" SELECT BUDMJR_HD FROM TRN_BILL_REGISTER WHERE BILL_NO = "+billNo);
    Iterator iterator = sqlQuery.list().iterator();
    long maxVoucherNum = 0;
    String lStrMjrHd= "";
    while(iterator.hasNext())
    {
      lStrMjrHd = (String)iterator.next();			
    }
    voucherVo.setMajorHead(lStrMjrHd);

    sqlQuery = hibSession.createSQLQuery(" SELECT MAX(VOUCHER_NO) FROM TRN_VOUCHER_DETAILS WHERE EXTRACT(MONTH FROM VOUCHER_DATE) = EXTRACT(MONTH FROM SYSDATE) AND LOCATION_CODE ='"+locationCode+"'"); 
    iterator = sqlQuery.list().iterator();		
    while(iterator.hasNext())
    {
      java.math.BigInteger voucher = (java.math.BigInteger)iterator.next();
      if(voucher != null)
      {				
        maxVoucherNum = voucher.longValue();
      }
    }

    //System.out.println(" maxVoucherNum is "  + maxVoucherNum);
    maxVoucherNum = maxVoucherNum + 1;		
    voucherVo.setVoucherNo(maxVoucherNum);
    voucherVo.setVoucherDate(new java.util.Date(System.currentTimeMillis()));
    //System.out.println(" \n Returning ID "  + maxVoucherNum);		
    return voucherVo;
  }	

  /**
   * This method to insert a voucher details.
   * @param voucher
   */
  public void insertIntoVoucher(TrnVoucherDetails voucher)
  {
    Session hibSession = getSession();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    StringBuffer queryStr = new StringBuffer(" INSERT INTO TRN_VOUCHER_DETAILS( VOUCHER_DETAIL_ID,VOUCHER_NO,BILL_NO,VOUCHER_DATE,MAJOR_HEAD,DISTRIBUTED,CREATED_USER_ID,CREATED_POST_ID,CREATED_DATE,LOCATION_CODE,DB_ID) VALUES( " );
    queryStr.append("'"+voucher.getVoucherDetailId()+"',");
    queryStr.append("'"+voucher.getVoucherNo()+"',");
    queryStr.append("'"+voucher.getBillNo()+"',");
    queryStr.append("TO_DATE('"+sdf.format(voucher.getVoucherDate())+"','yyyy-mm-dd'),");
    queryStr.append("'"+voucher.getMajorHead()+"',");
    queryStr.append("'"+voucher.getDistributed()+"',");
    queryStr.append("'"+voucher.getCreatedUserId()+"',");
    queryStr.append("'"+voucher.getCreatedPostId()+"',");
    queryStr.append("TO_DATE('"+sdf.format(voucher.getCreatedDate())+"','yyyy-mm-dd'),");
    queryStr.append("'"+voucher.getLocationCode()+"',");
    queryStr.append("'"+voucher.getDbId()+"' )");
    //System.out.println(" Query for inser s ----  " + queryStr.toString());
    Query query = hibSession.createSQLQuery(queryStr.toString());
    try
    {
      query.executeUpdate();
      //System.out.println("After execute update ...........");
      //hibSession.connection().commit();	
      //System.out.println("   vouchare................ " + this.read(voucher.getVoucherDetailId() ));
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.insertIntoVoucher # \n"+e);
    }
  }
  /**
   * This method to get end date from trn_verified_account table.
   * @param slocCode
   * @return
   */
  public Date getEndDateFromVerAcc(String slocCode)
  {
    Date endDate=null;
    try
    {
      Session hibSession = getSession();
      Query sqlQuery=hibSession.createSQLQuery("SELECT A.END_DATE FROM TRN_VERIFIED_ACCOUNT A WHERE A.AC_ID = (SELECT MAX(A.AC_ID) FROM TRN_VERIFIED_ACCOUNT A WHERE A.LOCATION_CODE='"+slocCode+"' AND A.ACTIVE=1)");

      List resList=sqlQuery.list();
      if(resList!=null && resList.size()>0)
      {
        endDate=(Date) resList.get(0);
        //System.out.println("End date is :-"+endDate);
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getEndDateFromVerAcc # \n"+e);
    }
    return endDate;
  }
  
  /**
   * This method to get sub-treasury posted vouchers.
   * @param langId
   * @param slocCode
   * @return
   */
  public List getSubTrsyPostedVouch(Long langId, String slocCode) 
  {			
    List dataList = null;
    try 
    {
      Session hibSession = getSession();
      StringBuffer sql = new StringBuffer();
      sql.append("select vd.voucherNo, vd.voucherDate, vd.billNo, br.budmjrHd, br.billNetAmount, " +
          " br.billDate, bt.subjectDesc ,dc.cardexNo  from TrnVoucherDetails vd, TrnBillRegister br," +
          " MstBillType bt, OrgDdoMst dc " +
          " where vd.billNo = br.billNo and br.subjectId = bt.subjectId and br.ddoCode = dc.ddoCode and" +
          " br.tsryOfficeCode in (select locationCode from CmnLocationMst where parentLocId='"+slocCode+"')");
      sql.append(" and bt.langId="+langId+" and dc.langId="+langId);
      Query sqlQuery=hibSession.createQuery(sql.toString());
      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {
        dataList = new ArrayList();
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          VoucherVO vo=new VoucherVO();
          Object[] tuple = (Object[])it.next();
          vo.setVoucherNo(String.valueOf((Long) tuple[0]));
          vo.setVoucherDate((Date) tuple[1]);
          vo.setBillNo(Long.parseLong(tuple[2].toString()));	
          vo.setMajorHead((String) tuple[3]);
          vo.setBillNetAmount((BigDecimal) tuple[4]);
          vo.setBillDate((Date) tuple[5]);
          vo.setSubjectDesc((String) tuple[6]);
          vo.setCardexNo((String) tuple[7]);
          dataList.add(vo);
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getSubTrsyPostedVouch # \n"+e);
    }		
    return dataList;
  }

  /**
   * This method to get sub-Treasury voucher details.
   * @param lngBillNo
   * @param slocCode
   * @param langId
   * @return
   */
  public NewBillVO getSubTrsyVoucherDtls(Long lngBillNo, String slocCode,Long langId) 
  {			
    NewBillVO lObjNewBillVO = null;
    try 
    {
      Session hibSession = getSession();
      StringBuffer sql = new StringBuffer();
      sql.append("select dc.cardexNo, dc.ddoNo,dc.ddoName, dc.ddoCode, br.subjectId, br.billDate, br.billGrossAmount, " +
          " br.billNetAmount, br.goNgo ,br.tcBill,hd.dmndNo,hd.budMjrHd,hd.budSubmjrHd,hd.budMinHd,hd.budSubHd," +
          " hd.budDtlHd,hd.budType,hd.schemeNo,br.tsryOfficeCode" +
          " from TrnBillBudheadDtls hd, TrnBillRegister br," +
          " MstBillType bt, OrgDdoMst dc " +
          " where hd.billNo = br.billNo and br.subjectId = bt.subjectId and br.ddoCode = dc.ddoCode and" +
          " br.tsryOfficeCode in (select locationCode from CmnLocationMst where parentLocId='"+slocCode+"')");
      sql.append(" and bt.langId="+langId+" and dc.langId="+langId+" and br.billNo="+lngBillNo);
      Query sqlQuery=hibSession.createQuery(sql.toString());
      List resList=sqlQuery.list();
      if (resList!=null && resList.size()>0) 
      {
        lObjNewBillVO = new NewBillVO();
        Iterator it = resList.iterator();
        while(it.hasNext()) 
        {
          Object[] tuple = (Object[])it.next();
          lObjNewBillVO.setCardexNo(tuple[0].toString());
          lObjNewBillVO.setDdoNo(tuple[1].toString());
          lObjNewBillVO.setDdoName(tuple[2].toString());
          lObjNewBillVO.setDdoCode(tuple[3].toString());
          lObjNewBillVO.setBillType(tuple[4].toString());
          lObjNewBillVO.setBillDate(tuple[5].toString());
          lObjNewBillVO.setBillGrossAmount(tuple[6].toString());
          lObjNewBillVO.setBillNetAmount(tuple[7].toString());
          if(tuple[8]!=null)
          {
            lObjNewBillVO.setGoNgo(tuple[8].toString());
          }
          if(tuple[9]!=null)
          {
            lObjNewBillVO.setTcBill(tuple[9].toString());
          }
          lObjNewBillVO.setDmndNo(tuple[10].toString());
          lObjNewBillVO.setBudmjrHd(tuple[11].toString());
          lObjNewBillVO.setBudSubMjrHd(tuple[12].toString());
          lObjNewBillVO.setBudMinHd(tuple[13].toString());
          lObjNewBillVO.setBudSubHd(tuple[14].toString());
          lObjNewBillVO.setBudDetailHd(tuple[15].toString());
          lObjNewBillVO.setBudType(tuple[16].toString());
          lObjNewBillVO.setSchemeCode(tuple[17].toString());
          lObjNewBillVO.setLocationCode(tuple[18].toString());
          //System.out.println(tuple[0]+" "+tuple[1]+" "+tuple[2]+" "+tuple[3]+" "+tuple[4]+" "+tuple[5]+" "+tuple[6]+" "+tuple[18]);
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getSubTrsyVoucherDtls # \n"+e);
    }		
    return lObjNewBillVO;
  }

  /**
   * This method to validate vouchers(is present or not).
   * @param billNo
   * @return
   */
  public boolean isValidVoucher(String billNo)
  {
    Session hibSession = getSession();
    boolean returnFalg = false;
    Query query = hibSession.createSQLQuery("SELECT * FROM TRN_VOUCHER_DETAILS  WHERE BILL_NO =" + billNo);
    List result = query.list();
    if(result != null && result.size() >0)			
    {
      returnFalg =  false;			
    }
    else
    {
      returnFalg = true;
    }
    return returnFalg;
  }

  /**
   * This method to get voucher value object.
   * @param billNo
   * @param slocCode
   * @return
   */
  public List getVoucherVO(Long billNo,String slocCode)
  {
    Session session = getSession();		
    Query query = session.createQuery("FROM TrnVoucherDetails where billNo ="+billNo+" and locationCode='"+slocCode+"'");
    return query.list();

  }

  /**
   * this method to get budject head ID by bill no.
   * @param lngBillNo
   * @return
   */
  public Long getBudHeadId(Long lngBillNo)
  {
    Long lngBudHeadId = null;
    try
    {
      Session hibSession = getSession();
      Query query = hibSession.createSQLQuery("SELECT BILL_BUD_ID FROM TRN_BILL_BUDHEAD_DTLS WHERE BILL_NO =" + lngBillNo);
      List result = query.list();
      if(result != null)
      {
        lngBudHeadId = Long.parseLong(result.get(0).toString());
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getBudHeadId # \n"+e);
      
    }
    return lngBudHeadId;
  }

  /**
   * This method to get vocuher details ID by bill no.
   * @param lngBillNo
   * @return
   */
  public Long getVouchDtlId(Long lngBillNo)
  {
    Long lngVouchId = null;
    try
    {
      Session hibSession = getSession();
      Query query = hibSession.createSQLQuery("SELECT VOUCHER_DETAIL_ID FROM TRN_VOUCHER_DETAILS WHERE BILL_NO =" + lngBillNo);
      List result = query.list();
      if(result != null)
      {
        lngVouchId = Long.parseLong(result.get(0).toString());
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Exception occured in VoucherDAOImpl.getVouchDtlId # \n"+e);
    }
    return lngVouchId;
  }
}