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

import com.tcs.sgv.billproc.counter.dao.TrnRcptDtlsDAOImpl;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.valueobject.SgvaBudmjrhdMst;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.exprcpt.valueobject.DistChallanVO;
import com.tcs.sgv.exprcpt.valueobject.TrnVoucherDetails;
import com.tcs.sgv.exprcpt.valueobject.WebChallanDtlsVO;
import com.tcs.sgv.pdpla.service.PDPLADataServiceImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChallan;

/**
 * @author vimal
 *
 */
public class ReceiptDAOImpl extends GenericDaoHibernateImpl<TrnReceiptDetails,Long> 
{

    Log logger = LogFactory.getLog(getClass());

    static HashMap hmMajorHeadCnt  = new HashMap();
    static Long nextRcptNo = new Long(0);
    static Long nextRcptId = new Long(0);

    /**
     * @param type
     * @param sessionFactory
     */
    public ReceiptDAOImpl(Class<TrnReceiptDetails> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }

    /**
     * @param receiptDetailId
     * @return List
     */
    public List getReceipts11(String  receiptDetailId) {
        List dataList = null;
        try 
        {
            Session hibSession = getSession();
            String sQuery  = " Select  rcpt_bud_id from trn_rcpt_budhead_dtls where receipt_detail_id = " + receiptDetailId ;
            List lst = hibSession.createSQLQuery(sQuery).list();

            return lst;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getReceipts # \n"+e);
        }
        return dataList;
    }

    /**
     * @param userId
     * @param receiptList
     * @return List
     */
    public List getReceipts(long userId,List receiptList) 
    {
        List dataList = null;
        try 
        {
            Session hibSession = getSession();
            StringBuffer sql = new StringBuffer();
            sql.append("select rd.receiptNo, rd.receiptDate, rd.depositorName, lpad(NVL(rd.majorHead,0),4,'0'), rd.amount, rd.receiptDetailId,rd.rcvdByBankDate " +
            " from TrnReceiptDetails rd,TrnRcptMvmnt rm where rd.receiptDetailId=rm.receiptDetailId and rm.receivedFlag=0 and rm.receiptDetailId in (");
            for (int i=0; i < receiptList.size(); i++) 
                if(i!=(receiptList.size()-1)) sql.append(receiptList.get(i)+",");
                else sql.append(receiptList.get(i));
            sql.append(" )");
            Query sqlQuery=hibSession.createQuery(sql.toString());

            List resList=sqlQuery.list();
            if (resList!=null && resList.size()>0) 
            {
                dataList = new ArrayList<TrnReceiptDetails>();
                Iterator it = resList.iterator();
                while(it.hasNext()) 
                {
                    TrnReceiptDetails vo=new TrnReceiptDetails();
                    Object[] tuple = (Object[])it.next();
                    vo.setReceiptNo((String)tuple[0]);
                    vo.setReceiptDate((Date) tuple[1]);
                    vo.setDepositorName((String) tuple[2]);
                    vo.setMajorHead((String) tuple[3]);
                    vo.setAmount((BigDecimal) tuple[4]);
                    vo.setReceiptDetailId((Long)tuple[5]);						
                    vo.setRcvdByBankDate((Date) tuple[6]);
                    dataList.add(vo);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getReceipts # \n"+e);
        }
        return dataList;
    }


    /**
     * @return List
     */
    public List getMajorHeadList()
    {
        List dataList = null;
        try 
        {
            Session hibSession = getSession();
            String sql = " select distinct sbm.budmjrhdCode from SgvaBudmjrhdMst sbm";
            Query sqlQuery=hibSession.createQuery(sql);

            List resList=sqlQuery.list();
            if (resList!=null && resList.size()>0) 
            {
                dataList = new ArrayList();
                Iterator it = resList.iterator();
                while(it.hasNext()) 
                {
                    SgvaBudmjrhdMst vo = new SgvaBudmjrhdMst();
                    vo.setBudmjrhdCode((String)it.next());
                    dataList.add(vo);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getMajorHeadList # \n"+e);
        }
        return dataList;
    }

    /**
     * @param objectArg
     * @param objectArgs
     * @param sessionFactory
     */
    public void accReceiptsFromChqBranch(Map<String,Object> objectArg,Map objectArgs,SessionFactory sessionFactory) 
    {
        String[] RecNos=(String[])objectArg.get("RecNos");
        String[] mjrHead = (String[]) objectArg.get("mjrHead");
        long userId=Long.parseLong(objectArg.get("userId").toString());
        String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        HashMap<String, Object> majorMap = (HashMap<String, Object>) objectArg.get("majorMap");
        HashMap<String, Object> pdPlaMap = new HashMap<String, Object>();

        TrnRcptDtlsDAOImpl rcptDAO = new TrnRcptDtlsDAOImpl(TrnReceiptDetails.class,sessionFactory);
        TrnPdChallan pdPlaVO = new TrnPdChallan();
        TrnReceiptDetails rcptVO = new TrnReceiptDetails();
        List<Object> pdPlaVOList = new ArrayList<Object>();

        try 
        {
            Session hibSession = getSession();
            for(int i=0;i<RecNos.length;i++)
            {
                if(majorMap.containsKey(mjrHead[i]))
                {
                    Long receiptDetailId=Long.parseLong(RecNos[i]);
                    rcptVO = rcptDAO.read(receiptDetailId);
                    pdPlaVO.setPdMjrhd(rcptVO.getMajorHead());
                    pdPlaVO.setPayeeNm(rcptVO.getDepositorName());
                    pdPlaVO.setAmount(rcptVO.getAmount());
                    pdPlaVO.setLocCode(rcptVO.getLocationCode());
                    pdPlaVO.setChallanReceiptDate(rcptVO.getRcvdByBankDate());
                    pdPlaVO.setChallanDate(rcptVO.getRcvdByBankDate());

                    pdPlaVOList.add(pdPlaVO);

                    pdPlaMap.put("TrnPdChallanVO",pdPlaVO);
                    pdPlaMap.put("TrnPdChallanVOArrlst",pdPlaVOList);

                    hibSession.createSQLQuery("delete  from TrnRcptMvmnt trm where trm.receiptDetailId = " + receiptDetailId);
                    hibSession.createSQLQuery("delete  from TrnReceiptDetails trd where trd.receiptDetailId = " + receiptDetailId).executeUpdate();
                }
                else
                {
                    Long receiptDetailId=Long.parseLong(RecNos[i]);
                    hibSession.createQuery("update TrnRcptMvmnt set receivedFlag=1,receivedDate=to_date('"+dateTime+"','yyyy-mm-dd'), updatedUserId="+userId+",receivedUserId="+userId+" where receiptDetailId="+receiptDetailId).executeUpdate();
                    hibSession.createQuery("update TrnReceiptDetails set majorHead = '"+ mjrHead[i] +"' where receiptDetailId="+receiptDetailId).executeUpdate();
                }
            }

            pdPlaMap.put("map",objectArgs);
            PDPLADataServiceImpl pdPlaService = new PDPLADataServiceImpl();
            pdPlaService.insertChallan(pdPlaMap);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.accReceiptsFromChqBranch # \n"+e);
        }
    }

    /**
     * @param userId
     * @param receiptList
     * @return int
     */
    public int countChallanDist(long userId,List receiptList)
    {
        int noOfVouchers=0;;
        try 
        {
            Session hibSession = getSession();
            StringBuffer sql = new StringBuffer();
            sql.append("select count(rd.receiptDetailId) " +
                    " from TrnReceiptDetails rd,TrnRcptMvmnt rm where rd.receiptDetailId=rm.receiptDetailId and"+
                    " rm.receivedFlag="+1+" and rm.mvmntStatus='" + DBConstants.ST_CHALLAN_GEN +"'  and rm.receiptDetailId in (");
            for (int i=0; i < receiptList.size(); i++) 
                if(i!=(receiptList.size()-1)) sql.append(receiptList.get(i)+",");
                else sql.append(receiptList.get(i));
            sql.append(" )");

            Query sqlQuery=hibSession.createQuery(sql.toString());

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
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.countChallanDist # \n"+e);
        }
        return noOfVouchers;
    }

    /**
     * @param userId
     * @param receiptList
     * @return List
     */
    public List getChallansForDist(long userId,List receiptList) 
    {		
        List dataList = null;
        try 
        {
            Session hibSession = getSession();
            StringBuffer sql = new StringBuffer();
            sql.append("select count(tr.receipt_detail_id),sum(tr.amount),lpad(NVL(tr.major_head,0),4,'0')" +
                    " from trn_receipt_details tr , trn_rcpt_mvmnt tm , (select max(movemnt_id) as m_id, receipt_detail_id from trn_rcpt_mvmnt group by receipt_detail_id )  temp" +
                    " where tr.receipt_detail_id = tm.receipt_detail_id" +
                    " and temp.m_id = tm.movemnt_id" +
                    " and temp.receipt_detail_id = tm.receipt_detail_id" +
                    " and tm.received_flag = 1" +
                    " and tm.mvmnt_status = 'Challan Created'" +
            " and tm.receipt_detail_id in(");
            for (int i=0; i < receiptList.size(); i++) 
                if(i!=(receiptList.size()-1)) sql.append(receiptList.get(i)+",");
                else sql.append(receiptList.get(i));
            sql.append(")");
            sql.append(" group by tr.major_Head");

            Query sqlQuery=hibSession.createSQLQuery(sql.toString());

            List resList=sqlQuery.list();
            if (resList!=null && resList.size()>0) 
            {
                dataList = new ArrayList();
                Iterator it = resList.iterator();
                while(it.hasNext())
                {
                    DistChallanVO vo=new DistChallanVO();
                    Object[] tuple = (Object[])it.next();
                    vo.setNoOfChallan(Integer.parseInt(tuple[0].toString()));
                    vo.setTotalAmount(Double.parseDouble(tuple[1].toString()));
                    vo.setMajorHead((String) tuple[2]);
                    dataList.add(vo);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getChallansForDist # \n"+e);
        }
        return dataList;
    }

    /**
     * @param userId
     * @param receiptList
     * @return List
     */
    public List<TrnReceiptDetails> getUndistributedChallan(long userId, List receiptList) 
    {	
        List dataList = null;
        try 
        {
            Session hibSession = getSession();

            StringBuffer sql = new StringBuffer();
            sql.append("select tr.receipt_detail_id,lpad(NVL(tr.major_head,0),4,'0')" +
                    " from trn_receipt_details tr , trn_rcpt_mvmnt tm , (select max(movemnt_id) as m_id, receipt_detail_id from trn_rcpt_mvmnt group by receipt_detail_id )  temp" +
                    " where tr.receipt_detail_id = tm.receipt_detail_id" +
                    " and temp.m_id = tm.movemnt_id" +
                    " and temp.receipt_detail_id = tm.receipt_detail_id" +
                    " and tm.received_flag = 1" +
            " and tm.receipt_detail_id in (");
            for (int i=0; i < receiptList.size(); i++) 
                if(i!=(receiptList.size()-1)) sql.append(receiptList.get(i)+",");
                else sql.append(receiptList.get(i));
            sql.append(")");

            Query sqlQuery=hibSession.createSQLQuery(sql.toString());
            List resList=sqlQuery.list();
            if (resList!=null && resList.size()>0) {
                dataList = new ArrayList();
                Iterator it = resList.iterator();
                while(it.hasNext()) {
                    TrnReceiptDetails vo=new TrnReceiptDetails();
                    Object[] tuple = (Object[])it.next();
                    vo.setReceiptDetailId(Long.parseLong(tuple[0].toString()));
                    vo.setMajorHead((String) tuple[1]);
                    dataList.add(vo);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getUndistributedChallan # \n"+e);
        }
        return dataList;
    }

    /**
     * @param userId
     * @param receiptList
     * @return List
     */
    public List getReceiptsForDetPost(long userId,List receiptList) 
    {

        List dataList = null;
        try 
        {
            Session hibSession = getSession();
            StringBuffer sql = new StringBuffer();
            sql.append("select rd.receiptNo,rd.receiptDate, rd.depositorName, lpad(NVL(rd.majorHead,0),4,'0'), rd.amount, rd.receiptDetailId, rd.rcvdByBankDate, rd.recRev " +
            " from TrnReceiptDetails rd,TrnRcptMvmnt rm where  rd.receiptDetailId=rm.receiptDetailId and rm.receivedFlag=0  and rm.receiptDetailId in (");
            for (int i=0; i < receiptList.size(); i++) 
                if(i!=(receiptList.size()-1)) sql.append(receiptList.get(i)+",");
                else sql.append(receiptList.get(i));
            sql.append(" )");

            Query sqlQuery=hibSession.createQuery(sql.toString());

            List resList=sqlQuery.list();
            if (resList!=null && resList.size()>0) 
            {
                dataList = new ArrayList();
                Iterator it = resList.iterator();
                while(it.hasNext()) 
                {
                    TrnReceiptDetails vo=new TrnReceiptDetails();
                    Object[] tuple = (Object[])it.next();
                    vo.setReceiptNo((String)tuple[0]);
                    vo.setReceiptDate((Date) tuple[1]);
                    vo.setDepositorName((String) tuple[2]);
                    vo.setMajorHead((String) tuple[3]);
                    vo.setAmount((BigDecimal) tuple[4]);
                    vo.setReceiptDetailId((Long)tuple[5]);
                    vo.setRcvdByBankDate((Date)tuple[6]);
                    vo.setRecRev((Integer) tuple[7]);
                    dataList.add(vo);
                    //System.out.println("Receipt Recovery Is is :-"+vo.getRecRev());
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getReceiptsForDetPost # \n"+e);
        }
        return dataList;
    }

    /**
     * @param objectArg
     */
    public void accReceiptsForDetPost(Map objectArg) 
    {
        String[] RcptDtIds=(String[])objectArg.get("RcptDtId");

        long userId=Long.parseLong(objectArg.get("userId").toString());
        String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        try 
        {
            Session hibSession = getSession();
            if(RcptDtIds!=null)
            {
                for(int i=0;i<RcptDtIds.length;i++)
                {
                    long rcptDetailId=Long.parseLong(RcptDtIds[i]); 
                    hibSession.createQuery("update TrnRcptMvmnt set receivedFlag=1,receivedDate=sysdate,updatedUserId="+userId+",receivedUserId="+userId+" where receiptDetailId="+rcptDetailId).executeUpdate();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.accReceiptsForDetPost # \n"+e);
        }

    }

    /**
     * @param endDate
     * @param userId
     * @param receiptList
     * @param posted
     * @return List
     */
    public List getChallanListForDet(Date endDate,long userId,List receiptList,Integer posted) 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        List dataList = null;
        try 
        {
            Session hibSession = getSession();
            StringBuffer sql = new StringBuffer();
            if(posted != null && posted == 0)
            {
                sql.append(" select tr.receipt_no,tr.receipt_date,tr.depositor_name,lpad(NVL(tr.major_head,0),4,'0'),tr.amount,tr.receipt_detail_id,tr.rcvd_by_bank_date,tr.rec_rev"+
                        " from trn_receipt_details tr , trn_rcpt_mvmnt tm , (select max(movemnt_id) as m_id, receipt_detail_id from trn_rcpt_mvmnt group by receipt_detail_id )  temp" +
                        " where tr.receipt_detail_id = tm.receipt_detail_id" +
                        " and temp.m_id = tm.movemnt_id" +
                        " and temp.receipt_detail_id = tm.receipt_detail_id" +
                        " and tm.mvmnt_status = 'Challan Distributed'" +
                " and tm.receipt_detail_id in (");
                for (int i=0; i < receiptList.size(); i++) 
                    if(i!=(receiptList.size()-1)) sql.append(receiptList.get(i)+",");
                    else sql.append(receiptList.get(i));
                sql.append(" ) order by tr.rcvd_By_Bank_Date");;
                //System.out.println("Query is--> "+sql );
            }
            else if(posted != null && posted == 1)
            {
                sql.append(" select tr.receipt_no,tr.receipt_date,tr.depositor_name,lpad(NVL(tr.major_head,0),4,'0'),tr.amount,tr.receipt_detail_id,tr.rcvd_by_bank_date,tr.rec_rev"+
                        " from trn_receipt_details tr , trn_rcpt_mvmnt tm , (select max(movemnt_id) as m_id, receipt_detail_id from trn_rcpt_mvmnt group by receipt_detail_id )  temp" +
                        " where tr.receipt_detail_id = tm.receipt_detail_id" +
                        " and temp.m_id = tm.movemnt_id" +
                        " and temp.receipt_detail_id = tm.receipt_detail_id" +
                        " and tm.mvmnt_status = 'DTL_PSTNG_DONE'" +
                        " and tr.rcvd_by_bank_date >= to_date('" + sdf.format(endDate) + "','MM-dd-yyyy')"+
                " and tm.receipt_detail_id in (");
                for (int i=0; i < receiptList.size(); i++) 
                    if(i!=(receiptList.size()-1)) sql.append(receiptList.get(i)+",");
                    else sql.append(receiptList.get(i));
                sql.append(" ) order by tr.rcvd_By_Bank_Date");;
                //System.out.println("Query is--> "+sql );
            }
            //System.out.println("querryyyyyyyy " + sql);
            Query sqlQuery=hibSession.createSQLQuery(sql.toString());

            List resList=sqlQuery.list();
            if (resList!=null && resList.size()>0) 
            {
                dataList = new ArrayList();
                Iterator it = resList.iterator();
                while(it.hasNext()) 
                {
                    TrnReceiptDetails vo=new TrnReceiptDetails();
                    Object[] tuple = (Object[])it.next();					
                    vo.setReceiptNo((String)tuple[0]);
                    vo.setReceiptDate((Date) tuple[1]);
                    vo.setDepositorName((String) tuple[2]);
                    vo.setMajorHead((String) tuple[3]);
                    vo.setAmount((BigDecimal) tuple[4]);
                    vo.setRcvdByBankDate((Date)tuple[6]);
                    try
                    {
                        vo.setReceiptDetailId(Long.parseLong(tuple[5].toString()));
                        vo.setRecRev(Integer.parseInt(tuple[7].toString()));
                    }
                    catch(Exception ex)	{}
                    dataList.add(vo);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getChallanListForDet # \n"+e);
        }
        return dataList;
    }

    /**
     * @param map
     * @param receiptList
     * @return List
     */
    public List getSearchChallan(Map map, List receiptList)
    {
        List dataList = null;
        try 
        {
            if (receiptList!=null && receiptList.size()>0) 
            {
                String searchBy=(String) map.get("searchBy");
                String searchValue=(String)map.get("searchValue");
                String viewPage=(String)map.get("viewPage");
                long userId=Long.parseLong(map.get("userId").toString());
                Session hibSession = getSession();
                StringBuffer sql = new StringBuffer();
                sql.append("select rd.receiptNo, rd.receiptDate, rd.depositorName, rd.majorHead, rd.amount, rd.receiptDetailId, rd.rcvdByBankDate " +
                        " from TrnReceiptDetails rd,TrnRcptMvmnt rm" +
                        " where rd.receiptDetailId=rm.receiptDetailId" +
                        " and rm.statusUpdatedUserid=" + userId );

                if(viewPage.equalsIgnoreCase("rcptForDetPosting"))
                {
                    sql.append(" and rm.receivedFlag = 0");
                }
                else if(viewPage.equalsIgnoreCase("rcptListForDetPost"))
                {
                    sql.append(" and rm.receivedFlag = 1");
                }

                if(searchBy.equals("Challan Date"))
                {
                    Date date=new SimpleDateFormat("dd-MM-yyyy").parse(searchValue);
                    String SearchDate=new SimpleDateFormat("MM-dd-yyyy").format(date);
                    sql.append(" and to_char(rd.rcvdByBankDate,'dd-mm-yyyy') = '");
                    sql.append(searchValue);
                }
                else if(searchBy.equals("Challan No"))
                {
                    sql.append(" and rd.receiptNo LIKE '");
                    sql.append(searchValue + "%");
                }
                else if(searchBy.equals("Major Head"))
                {
                    sql.append(" and rd.majorHead LIKE '");
                    sql.append(searchValue+"%");
                }
                sql.append("' and rm.receiptDetailId in (");

                for (int i=0; i < receiptList.size(); i++) 
                    if(i!=(receiptList.size()-1)) sql.append(receiptList.get(i)+",");
                    else sql.append(receiptList.get(i));
                sql.append(" )");
                Query sqlQuery=hibSession.createQuery(sql.toString());

                List resList=sqlQuery.list();
                if (resList!=null && resList.size()>0) 
                {
                    dataList = new ArrayList();
                    Iterator it = resList.iterator();
                    while(it.hasNext()) 
                    {
                        TrnReceiptDetails vo=new TrnReceiptDetails();
                        Object[] tuple = (Object[])it.next();					
                        vo.setReceiptNo(tuple[0].toString());
                        vo.setReceiptDate((Date) tuple[1]);
                        vo.setDepositorName((String) tuple[2]);
                        vo.setMajorHead((String) tuple[3]);
                        vo.setAmount((BigDecimal) tuple[4]);
                        vo.setReceiptDetailId((Long)tuple[5]);
                        vo.setRcvdByBankDate((Date)tuple[6]);
                        dataList.add(vo);
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getSerchChallan # \n"+e);
        }
        return dataList;
    }

    public Long getNextChallanNo(String sMjrHead,String locCode)
    {
        synchronized (hmMajorHeadCnt)
        {
            if(hmMajorHeadCnt.containsKey(locCode))
            {
                HashMap<String,Long> mjrHeads = (HashMap)hmMajorHeadCnt.get(locCode);
                if(mjrHeads.containsKey(sMjrHead))
                {
                    Long lCounter =  mjrHeads.get(sMjrHead);
                    lCounter = lCounter +1 ;
                    mjrHeads.put(sMjrHead,lCounter);
                    hmMajorHeadCnt.put(locCode,mjrHeads );
                    return lCounter;
                }
                else
                {
                    Long lCounter =  getNextReceiptMjrhdWise(/*sMjrHead, */locCode);
                    mjrHeads.put(sMjrHead,lCounter);
                    hmMajorHeadCnt.put(locCode,mjrHeads );
                    return lCounter;
                }
            }
            else
            {
                HashMap<String,Long> mjrHeads = new HashMap<String,Long>();
                Long lCounter =  getNextReceiptMjrhdWise(/*sMjrHead,*/ locCode);
                mjrHeads.put(sMjrHead,lCounter);
                hmMajorHeadCnt.put(locCode,mjrHeads );
                return lCounter;
            }
        }
    }

    /**
     * @param locCode
     * @return Long
     */
    public Long getNextRcptNo(String locCode)
    {
        synchronized (nextRcptNo)
        {
            if(nextRcptNo == 0)
                nextRcptNo = getNextReceiptMjrhdWise(locCode);
            else
                nextRcptNo++;
            return nextRcptNo;
        }
    }

    /**
     * @param objectArgs
     * @return Long
     */
    public Long getNextRcptId(Map objectArgs)
    {
        synchronized (nextRcptId)
        {
            if(nextRcptId == 0)
                nextRcptId = BptmCommonServiceImpl.getNextSeqNum("trn_receipt_details", objectArgs);
            else
                nextRcptId++;
            return nextRcptId;
        }
    }

    /**
     * @param locCode
     * @return
     */
    private Long getNextReceiptMjrhdWise(String locCode)
    {
        Session hibSession = getSession();	
        String mjrHead = null;//if need to use pass as parameter
        //Query sqlQuery = hibSession.createSQLQuery(" select max(receipt_no) from trn_receipt_details where major_head ='"+mjrHead+"' and location_Code = '"+ locCode+"' group by major_head"); 
        Query sqlQuery = hibSession.createSQLQuery("select max(abs(receipt_no)) from trn_receipt_details"); 
        Long maxReceiptNum = new Long(0);
        try
        {
            List lstResult  = sqlQuery.list();
            if( lstResult != null && lstResult.size()>0)
            {
                //System.out.println("size of result List is :-"+lstResult.size());
                if(lstResult != null && lstResult.size() != -1)
                {
                    Iterator iterator = lstResult.iterator();		
                    while(iterator.hasNext())
                    {
                        String recNo = iterator.next().toString();
                        if(recNo.trim().equals(""))
                            return(new Long(1));
                        Long receipt = new Long(recNo);
                        if(receipt != null)
                        {				
                            maxReceiptNum = receipt; 
                        }
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        //System.out.println(" maxVoucherNum is "  + maxReceiptNum);
        maxReceiptNum = maxReceiptNum + 1;		

        return maxReceiptNum;
    }

    /**
     * @param receiptId
     * @return TrnVoucherDetails
     */
    public TrnVoucherDetails getVoucherMjrhead(Long receiptId)
    {
        TrnVoucherDetails vo = new TrnVoucherDetails();
        Session hibSession = getSession();
        Query sqlQuery = hibSession.createSQLQuery("select voucher_no, major_head  from trn_voucher_details  where bill_no = (select bill_no from trn_bill_register tb where tb.tc_bill = 'TC' and receipt_id =" + receiptId +")"); 
        Iterator itr = sqlQuery.list().iterator();
        if(itr.hasNext())
        {
            Object row[] = (Object[]) itr.next();
            vo.setVoucherNo(Long.parseLong(row[0].toString()));
            vo.setMajorHead(row[1].toString());
        }
        return(vo);

    }

    /**
     * @param endDate
     * @param receiptList
     * @param postId
     * @param locCode
     * @return List
     */
    public List getRecordReceiptsforSubTreasury(Date endDate,List receiptList,String postId,String locCode)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        List dataList = null;
        try 
        {
            Session hibSession = getSession();
            StringBuffer sql = new StringBuffer();

            sql.append("select tr.receipt_no,tr.receipt_date,tr.depositor_name,lpad(NVL(tr.major_head,0),4,'0'),tr.amount,tr.receipt_detail_id,tr.rcvd_by_bank_date,tr.rec_rev"+
                    " from trn_receipt_details tr , trn_rcpt_mvmnt tm , (select max(movemnt_id) as m_id, receipt_detail_id from trn_rcpt_mvmnt group by receipt_detail_id )  temp" +
                    " where tr.receipt_detail_id = tm.receipt_detail_id" +
                    " and temp.m_id = tm.movemnt_id" +
                    " and temp.receipt_detail_id = tm.receipt_detail_id" +
                    " and tm.mvmnt_status = 'DTL_PSTNG_DONE'" +
                    " and tr.rcvd_by_bank_date < to_date('" + sdf.format(endDate) + "','MM-dd-yyyy')"+ 
            " and tm.receipt_detail_id in (");
            for (int i=0; i < receiptList.size(); i++) 
                if(i!=(receiptList.size()-1)) sql.append(receiptList.get(i)+",");
                else sql.append(receiptList.get(i));
            sql.append(" )  union all ");
            sql.append("select tr.receipt_no,tr.receipt_date,tr.depositor_name,lpad(NVL(tr.major_head,0),4,'0'),tr.amount,tr.receipt_detail_id,tr.rcvd_by_bank_date,tr.rec_rev" +
                    " from trn_receipt_details tr" +
                    " where tr.created_Post_Id = " + postId +
                    " and tr.location_code = " + locCode );

            Query sqlQuery=hibSession.createSQLQuery(sql.toString());

            List resList=sqlQuery.list();
            if (resList!=null && resList.size()>0) 
            {
                dataList = new ArrayList();
                Iterator it = resList.iterator();
                while(it.hasNext()) 
                {
                    TrnReceiptDetails vo=new TrnReceiptDetails();
                    Object[] tuple = (Object[])it.next();
                    vo.setReceiptNo((String)tuple[0]);
                    vo.setReceiptDate((Date) tuple[1]);
                    vo.setDepositorName((String) tuple[2]);
                    vo.setMajorHead((String) tuple[3]);
                    vo.setAmount((BigDecimal) tuple[4]);
                    vo.setReceiptDetailId(Long.parseLong(tuple[5].toString()));						
                    vo.setRcvdByBankDate((Date) tuple[6]);
                    dataList.add(vo);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getReceiptsforSubTreasury # \n"+ex);
        }
        return dataList;
    }


    /**
     * @param postId
     * @return List
     */
    public List getReceiptsforSubTreasury(String postId) 
    {
        List dataList = null;
        try 
        {
            Session hibSession = getSession();
            StringBuffer sql = new StringBuffer();
            sql.append("select rd.receiptNo, rd.receiptDate, rd.depositorName, lpad(NVL(rd.majorHead,0),4,'0'), rd.amount, rd.receiptDetailId,rd.rcvdByBankDate " +
                    " from TrnReceiptDetails rd where rd.createdPostId = " + postId);
            Query sqlQuery=hibSession.createQuery(sql.toString());

            List resList=sqlQuery.list();
            if (resList!=null && resList.size()>0) 
            {
                dataList = new ArrayList();
                Iterator it = resList.iterator();
                while(it.hasNext()) 
                {
                    TrnReceiptDetails vo=new TrnReceiptDetails();
                    Object[] tuple = (Object[])it.next();
                    vo.setReceiptNo((String)tuple[0]);
                    vo.setReceiptDate((Date) tuple[1]);
                    vo.setDepositorName((String) tuple[2]);
                    vo.setMajorHead((String) tuple[3]);
                    vo.setAmount((BigDecimal) tuple[4]);
                    vo.setReceiptDetailId((Long)tuple[5]);						
                    vo.setRcvdByBankDate((Date) tuple[6]);
                    dataList.add(vo);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getReceiptsforSubTreasury # \n"+ex);
        }
        return dataList;
    }
    
    public List getOnlineChallan() 
    {
        List dataList = new ArrayList();
        try 
        {
            String query =  " SELECT WCD.WCD_CHALLAN_ID, " +
            " WCD.PMT_EFFCT_DATE, " +
            " WCD.WCD_DLR_NAME," +
            " RBB.BRANCH_NAME," +
            " WCD.WCD_TOTAL_AMOUNT , WCD.WCD_TAX_TYPE " +
            " FROM WEB_CHALLAN_DETAILS WCD, RLT_BANK_BRANCH RBB" +
            " WHERE RBB.BRANCH_CODE = WCD.WCD_BANK_CODE AND WCD.WCD_APPROVED <> 1 AND" +
            " WCD.WCD_VERIFIED = 1" ;
            
            org.hibernate.SQLQuery oQuery  = getSession().createSQLQuery(query);
            List lstResult = oQuery.list();
            
            if (lstResult!=null && lstResult.size()>0) 
            {
                Iterator it = lstResult.iterator();
                while(it.hasNext()) 
                {
                    WebChallanDtlsVO vo = new WebChallanDtlsVO();
                    Object[] tuple = (Object[])it.next();
                    vo.setWcdChallanId((String)tuple[0]);
                    vo.setDateEffective((Date)tuple[1]);
                    vo.setPartyName((String)tuple[2]);
                    vo.setBrachName((String)tuple[3]);
                    vo.setTaxType(tuple[5].toString());
                    try{vo.setAmout(new BigDecimal(tuple[4].toString()));}catch(Exception ex){}
                    dataList.add(vo);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            logger.error("Exception occured in ReceiptDAOImpl.getReceiptsforSubTreasury # \n"+ex);
        }
        return dataList;
    }


}
