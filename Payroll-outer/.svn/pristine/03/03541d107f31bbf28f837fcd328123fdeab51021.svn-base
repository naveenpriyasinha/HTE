/**
 * package : com.tcs.sgv.onlinebillprep.service
 * 
 * @verion : 1.0
 * @author : Keyur Shah, Nirav Bumia *
 */

package com.tcs.sgv.onlinebillprep.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TABillDAO;
import com.tcs.sgv.onlinebillprep.dao.TABillDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnTablAmtDtlsDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnTablAmtDtlsDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnTablHdrDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnTablHdrDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnTablTrvlDtlsDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnTablTrvlDtlsDAOImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablAmtDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablHdr;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablTrvlDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTarqstExpsumm;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTarqstHdr;

/**
 * TA Bill Specifis Service Class.
 * 
 * @author Keyur Shah, Nirav Bumia
 * @version 1.0
 */
public class TABillServiceImpl extends ServiceImpl implements TABillService
{

	 /* Global Variable for Logger Class */
	Log gLogger = LogFactory.getLog(getClass());


	/**
     * Gets the TA Bill Specific Requsted Details.
     * 
     * @param Map lMapInput
     * @return ResultObject objRes.
     */
	public ResultObject getTABillRqstDtls(Map lMapInput)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {
            ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
            Long lLngTrnAprvdRqstId = null;
            String[] lStrRqstID = null;
            List lLstVO = null;
            TrnTarqstHdr lObjTrnTaRqstHdr = null;
            TrnTarqstExpsumm lObjTrnTaRqstExpSum = null;
            List lLstTrnTablRqst = new ArrayList();

            lStrRqstID = request.getParameterValues("chkSelected");

            if (lStrRqstID != null && lStrRqstID.length > 0)
            {
                lLngTrnAprvdRqstId = Long.parseLong(lStrRqstID[0]);
            }

            TABillDAO lObjDAO = new TABillDAOImpl(serv.getSessionFactory());
            lLstVO = lObjDAO.getTABillRqstDtls(lLngTrnAprvdRqstId, serv);

            if (lLstVO != null & lLstVO.size() > 0)
            {
                lObjTrnTaRqstHdr = (TrnTarqstHdr) lLstVO.get(0);
                lObjTrnTaRqstExpSum = (TrnTarqstExpsumm) lLstVO.get(1);
                lLstTrnTablRqst = (List) lLstVO.get(2);
            }

            lMapInput.put("TrnTarqstHdr", lObjTrnTaRqstHdr);
            lMapInput.put("TrnTarqstExpsumm", lObjTrnTaRqstExpSum);
            lMapInput.put("TrnTablRqst", lLstTrnTablRqst); // This is list of
                                                            // TrnTablRqst VOs

            objRes.setResultValue(lMapInput);
        }
        catch (Exception e)
        {
            gLogger.error("Error in getTABillRqstDtls of Class  TABillServiceImpl and Error is : " + e, e);
        }

        return objRes;
    }


	/**
     * Save the TA Bill
     * 
     * @param Map lMapInput
     * @return ResultObject objRes.
     */
	
    public ResultObject saveBill(Map inputMap)
    {
        gLogger.info("Inside saveBill() method of TABillServiceImpl Class ");

        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        Map lMapBillMap = (Map) inputMap.get("BillMap");

        TrnTablHdr lObjTrnTablHdr = null;
        TrnTablAmtDtls lObjTrnTablAmtDtls = null;
        TrnTablTrvlDtls lObjTrnTablTrvlDtls = null;

        List<TrnTablTrvlDtls> lLstTrnTablTrvlDtls = null;

        try
        {
            TrnBillRegister lObjBillReg = (TrnBillRegister) inputMap.get("BillRegVO");

            lObjTrnTablHdr = (TrnTablHdr) lMapBillMap.get("TrnTablHdr");
            lObjTrnTablAmtDtls = (TrnTablAmtDtls) lMapBillMap.get("TrnTablAmtDtls");
            lLstTrnTablTrvlDtls = (List<TrnTablTrvlDtls>) lMapBillMap.get("TrnTablTrvlDtlsLst");

            // save into Header Table
            gLogger.info("Bill number generated is = " + lObjBillReg.getBillNo());
            lObjTrnTablHdr.setBillNo(lObjBillReg.getBillNo());
            saveTABillHdr(lObjTrnTablHdr, inputMap);

            // save into Amount Table
            lObjTrnTablAmtDtls.setTrnTablHdrId(lObjTrnTablHdr.getTrnTablHdrId());
            saveTrnTablAmtDtls(lObjTrnTablAmtDtls, inputMap);

            // Deleting the previously inserted data of travel table
            TABillDAO lObjTABillDAO = new TABillDAOImpl(serv.getSessionFactory());
            List lLstTAPK = lObjTABillDAO.getPKForTable(lObjBillReg.getBillNo());
            TrnTablTrvlDtlsDAO lObjTablTrvlDtlsDAO = new TrnTablTrvlDtlsDAOImpl(TrnTablTrvlDtls.class, serv
                    .getSessionFactory());

            if (lLstTAPK != null && lLstTAPK.size() > 0)
            {
                Object[] lObjArrPK = null;
                Iterator<Object[]> lItrTAPK = lLstTAPK.iterator();

                while (lItrTAPK.hasNext())
                {
                    lObjArrPK = lItrTAPK.next();

                    lObjTrnTablTrvlDtls = lObjTablTrvlDtlsDAO.read((Long) lObjArrPK[2]);
                    lObjTablTrvlDtlsDAO.delete(lObjTrnTablTrvlDtls);
                }
            }

            // save into Travel Table, there may be multiple entries
            if (lLstTrnTablTrvlDtls != null)
            {
                Iterator lItrTrnTablTrvlDtls = lLstTrnTablTrvlDtls.iterator();

                while (lItrTrnTablTrvlDtls.hasNext())
                {
                    lObjTrnTablTrvlDtls = (TrnTablTrvlDtls) lItrTrnTablTrvlDtls.next();
                    lObjTrnTablTrvlDtls.setTrnTablHdrId(lObjTrnTablHdr.getTrnTablHdrId());
                    saveTrnTablTrvlDtls(lObjTrnTablTrvlDtls, inputMap);
                }
            }

            inputMap.put("TrnTablHdr", lObjTrnTablHdr);
            inputMap.put("TrnTablAmtDtls", lObjTrnTablAmtDtls);
            inputMap.put("TrnTablTrvlDtlsLst", lLstTrnTablTrvlDtls);

            objRes.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("In the save bill : " + e + "\n", e);
        }
        gLogger.info("Out of saveBill() method of TABillServiceImpl Class ");

        return objRes;
    }


    /**
     * Save the Detail of TABillHdr Vo. 
     * 
     * @param TrnTablHdr, Map
     * 			lObjTrnTablHdr, lMapInput 
     * @return void.
     */
    public static void saveTABillHdr(TrnTablHdr lObjTrnTablHdr, Map inputMap) throws Exception
    {
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        TrnTablHdrDAO lObjTrnTablHdrDao = new TrnTablHdrDAOImpl(TrnTablHdr.class, serv.getSessionFactory());
        Long lLngTrnTablHdrId = null;

        if (lObjTrnTablHdr.getTrnTablHdrId() != null)
        {
            lObjTrnTablHdrDao.update(lObjTrnTablHdr);
        }
        else
        {
            lLngTrnTablHdrId = BptmCommonServiceImpl.getNextSeqNum("trn_tabl_hdr", inputMap);
            lObjTrnTablHdr.setTrnTablHdrId(lLngTrnTablHdrId);
            lObjTrnTablHdrDao.create(lObjTrnTablHdr);
        }

        inputMap.put("trnTablHdrId", lObjTrnTablHdr.getTrnTablHdrId());
    }


    /**
     * Save the Detail of TablAmtDtls Vo. 
     * 
     * @param TrnTablAmtDtls, Map
     * 			lObjTrnTablAmtDtls, lMapInput 
     * @return void.
     */
    public static void saveTrnTablAmtDtls(TrnTablAmtDtls lObjTrnTablAmtDtls, Map inputMap) throws Exception
    {
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        TrnTablAmtDtlsDAO lObjTrnTablAmtDtlsDao = new TrnTablAmtDtlsDAOImpl(TrnTablAmtDtls.class, serv
                .getSessionFactory());
        Long lLngTrnTablAmtDtlsId = null;
        Long trnTablHdrId = (Long) inputMap.get("trnTablHdrId");

        if (lObjTrnTablAmtDtls.getTrnTablAmtDtlsId() != null && trnTablHdrId != null)
        {
            lObjTrnTablAmtDtls.setTrnTablHdrId(trnTablHdrId);
            lObjTrnTablAmtDtlsDao.update(lObjTrnTablAmtDtls);
        }
        else
        {
            lLngTrnTablAmtDtlsId = BptmCommonServiceImpl.getNextSeqNum("trn_tabl_amt_dtls", inputMap);
            lObjTrnTablAmtDtls.setTrnTablAmtDtlsId(lLngTrnTablAmtDtlsId);
            lObjTrnTablAmtDtlsDao.create(lObjTrnTablAmtDtls);
        }
    }


    /**
     * Save the Detail of TablTrvlDtls Vo. 
     * 
     * @param TrnTablTrvlDtls, Map
     * 			lObjTrnTablTrvlDtls, lMapInput 
     * @return void.
     */
    public static void saveTrnTablTrvlDtls(TrnTablTrvlDtls lObjTrnTablTrvlDtls, Map inputMap) throws Exception
    {

        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        TrnTablTrvlDtlsDAO lObjTrnTablTrvlDtlsDao = new TrnTablTrvlDtlsDAOImpl(TrnTablTrvlDtls.class, serv
                .getSessionFactory());
        Long lLngTrnTablTrvlDtlsId = null;

        lLngTrnTablTrvlDtlsId = BptmCommonServiceImpl.getNextSeqNum("trn_tabl_trvl_dtls", inputMap);
        lObjTrnTablTrvlDtls.setTrnTablTrvlDtlsId(lLngTrnTablTrvlDtlsId);
        lObjTrnTablTrvlDtlsDao.create(lObjTrnTablTrvlDtls);
    }


   /**
     * Show TA Bill Data using following method. 
     * 
     * @param Map inputMap
     * @return ResultObject objRes.
     */
    public ResultObject getTABillData(Map inputMap)
    {

        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");

        String lStrBillNo = null;
        Map lMapTAData = null;
        TABillDAOImpl lObjTABillDAOImpl = new TABillDAOImpl(serv.getSessionFactory());

        try
        {
            lStrBillNo = (String) inputMap.get("billNo");
            lMapTAData = lObjTABillDAOImpl.getTABillData(Long.parseLong(lStrBillNo), serv);
        }
        catch (Exception e)
        {
            gLogger.error("Error in getTABillData() of Class  TABillServiceImpl and Error is : " + e, e);
        }

        objRes.setResultValue(lMapTAData);

        return objRes;
    }
       
   /**
    * Method to Save the Digital Signatures for TABill Details
    * 
    * @param Map
    *            inputMap
    * @return ResultObject
    */
    public ResultObject saveDigiSigDtls(Map inputMap)
    {

        String lStrAppId = null;
        String lStrKey = null;
        String lStrfkVal = null;
        String lStrRowId = null;

        String lStrtrnTaBlHdrId = null;
        String lStrtrnTaBlTrvlDtlsId = null;

        String lStrtrnTaBlAmtDtlsId = null;

        Long lLngtrnTaBlHdrId = 0L;
        Long lLngtrnTaBlTrvlDtlsId = 0L;
        Long lLngtrnTaBlAmtDtlsId = 0L;

        int lIntRowNum1 = 1;
        int lIntRowNum2 = 1;
        int lIntRowNum3 = 1;

        TrnTablHdr lObjTrnTablHdr = null;
        TrnTablAmtDtls lObjTrnTablAmtDtls = null;
        TrnTablTrvlDtls lObjTrnTablTrvlDtls = null;
        List<TrnTablTrvlDtls> lLstTrnTablTrvlDtls = null;

        try
        {
            ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");

            lStrKey = (String) inputMap.get("DigitalKey");
            //System.out.println("this is at key************" + lStrKey);
            lStrAppId = (String) inputMap.get("ApplicationId");

            BillCommonDAOImpl lobgBillsCmnDao = new BillCommonDAOImpl(srvcLoc.getSessionFactory());

            lObjTrnTablHdr = (TrnTablHdr) inputMap.get("TrnTablHdr");
            lLngtrnTaBlHdrId = lObjTrnTablHdr.getTrnTablHdrId();
            lStrtrnTaBlHdrId = lLngtrnTaBlHdrId.toString();

            lObjTrnTablAmtDtls = (TrnTablAmtDtls) inputMap.get("TrnTablAmtDtls");
            lLngtrnTaBlAmtDtlsId = lObjTrnTablAmtDtls.getTrnTablAmtDtlsId();
            lStrtrnTaBlAmtDtlsId = lLngtrnTaBlAmtDtlsId.toString();

            lLstTrnTablTrvlDtls = (List<TrnTablTrvlDtls>) inputMap.get("TrnTablTrvlDtlsLst");
            Iterator<TrnTablTrvlDtls> lItrTrnTablTrvlDtls = lLstTrnTablTrvlDtls.iterator();
            while (lItrTrnTablTrvlDtls.hasNext())
            {
                lObjTrnTablTrvlDtls = lItrTrnTablTrvlDtls.next();
                lLngtrnTaBlTrvlDtlsId = lObjTrnTablTrvlDtls.getTrnTablTrvlDtlsId();
                lStrtrnTaBlTrvlDtlsId = lLngtrnTaBlTrvlDtlsId.toString();

                int LintTrvlDtlsRes = lobgBillsCmnDao.insertDigiMapping(lStrAppId, 4, lStrtrnTaBlTrvlDtlsId, lStrfkVal,
                        lIntRowNum2, lStrRowId, lStrKey);
                gLogger.info("This is at inserting Travel Details" + LintTrvlDtlsRes);
                lIntRowNum2++;
            }

            int lIntAmtDtlsRes = lobgBillsCmnDao.insertDigiMapping(lStrAppId, 5, lStrtrnTaBlAmtDtlsId, lStrfkVal,
                    lIntRowNum3, lStrRowId, lStrKey);
            gLogger.info("This is at inserting Amount deatails " + lIntAmtDtlsRes);

            int lIntHdrRes = lobgBillsCmnDao.insertDigiMapping(lStrAppId, 6, lStrtrnTaBlHdrId, lStrfkVal, lIntRowNum1,
                    lStrRowId, lStrKey);
            gLogger.info("This is at inserting Header deatails " + lIntHdrRes);

            return objRes;
        }
        catch (Exception e)
        {
            gLogger.error("Error while inserting the Digisigns at Ta Bill" + e, e);
            return null;
        }
    }
    /**
     * Method  to  Verify the Digital Signatures forTABill Details
     * 
     * @param Map
     *            lMapTAData
     * @return ResultObject
     */

    public ResultObject verifyTABillDigiData(Map lMapTAData)
    {

        String lstrRowId = null;
        String lstrfkVal = null;
        String lStrPkVal3 = null;
        String lStrPkVal2 = null;
        String lStrPkVal1 = null;
        String lStrDigiKey = null;
        String lStrAppId = null;

        int lIntSeq1 = 4;
        int lIntSeq2 = 5;
        int lIntSeq3 = 6;

        int lIntRowNum1 = 1;
        int lIntRowNum2 = 1;
        int lIntRowNum3 = 1;

        Map lMapGetData = null;
        TrnTablHdr lObjTrnTablHdr = null;
        TrnTablAmtDtls lObjTrnTablAmtDtls = null;
        TrnTablTrvlDtls lObjTrnTablTrvlDtls = null;
        List<TrnTablTrvlDtls> lLstTrnTablTrvlDtls = null;

        try
        {
            ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            ServiceLocator serv = (ServiceLocator) lMapTAData.get("serviceLocator");
            SessionFactory sesfactory = serv.getSessionFactory();
            BillCommonDAOImpl objDao = new BillCommonDAOImpl(sesfactory);

            lStrAppId = (String) lMapTAData.get("ApplicationId");
            lStrDigiKey = (String) lMapTAData.get("DigiKey");

            //System.out.println("This is in Tabill serviceimpl   " + lStrAppId + "***" + lStrDigiKey);

            lMapGetData = (Map) lMapTAData.get("BillDtls");

            lObjTrnTablHdr = (TrnTablHdr) lMapGetData.get("TrnTablHdr");
            lStrPkVal3 = lObjTrnTablHdr.getTrnTablHdrId().toString();

            lObjTrnTablAmtDtls = (TrnTablAmtDtls) lMapGetData.get("TrnTablAmtDtls");
            lStrPkVal2 = lObjTrnTablAmtDtls.getTrnTablAmtDtlsId().toString();

            lLstTrnTablTrvlDtls = (List<TrnTablTrvlDtls>) lMapGetData.get("TrnTablTrvlDtlsLst");
            Iterator<TrnTablTrvlDtls> lItrTATrvlDtl = lLstTrnTablTrvlDtls.iterator();
            while (lItrTATrvlDtl.hasNext())
            {
                lObjTrnTablTrvlDtls = lItrTATrvlDtl.next();
                lStrPkVal1 = lObjTrnTablTrvlDtls.getTrnTablTrvlDtlsId().toString();
                int res1 = objDao.insertDigiMapping(lStrAppId, lIntSeq1, lStrPkVal1, lstrfkVal, lIntRowNum1, lstrRowId,
                        lStrDigiKey);
                gLogger.info("This is at inserting travl dtls" + res1 + "***" + lStrPkVal1 + "**" + lIntRowNum1);
                lIntRowNum1 = lIntRowNum1 + 1;
            }

            int res2 = objDao.insertDigiMapping(lStrAppId, lIntSeq2, lStrPkVal2, lstrfkVal, lIntRowNum2, lstrRowId,
                    lStrDigiKey);
            gLogger.info("This is at inserting TABill Amt Dtls " + res2 + "***" + lStrPkVal2 + "**" + lIntRowNum2);

            int res3 = objDao.insertDigiMapping(lStrAppId, lIntSeq3, lStrPkVal3, lstrfkVal, lIntRowNum3, lstrRowId,
                    lStrDigiKey);
            gLogger.info("This is at inserting TAbill hdr dtls" + res3 + "***" + lStrPkVal3 + "***" + lIntRowNum3);

            Map ResMap = new HashMap();
            ResMap.put("Result2", res2);
            ResMap.put("res3", res3);
            objRes.setResultValue(ResMap);
            return objRes;

        }
        catch (Exception e)
        {
            gLogger.info("Error at verification of digital signatures in Taserviceimpl" + e);
            return null;
        }

    }
}