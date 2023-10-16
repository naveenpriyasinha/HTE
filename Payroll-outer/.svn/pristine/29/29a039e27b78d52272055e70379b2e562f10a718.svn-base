/**
 * package : com.tcs.sgv.onlinebillprep.service 
 * @verion : 1.0
 * @author Keyur Shah, Amit Singh. 
 *
 */

package com.tcs.sgv.onlinebillprep.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
import com.tcs.sgv.onlinebillprep.dao.MedBillDAO;
import com.tcs.sgv.onlinebillprep.dao.MedBillDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnMedblAprvdDtlsDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnMedblAprvdDtlsDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnMedblDtlsDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnMedblDtlsDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnMedblHdrDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnMedblHdrDAOImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblAprvdDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblHdr;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblRqst;

/**
 * Its a Medical Bill Specific Service.
 * 
 * @author Keyur Shah, Amit Singh.
 * @version 1.0
 */
public class MedBillServiceImpl extends ServiceImpl implements MedBillService
{
	/* Global Variable for Logger Class */
	public static Log gLogger = LogFactory.getLog(MedBillServiceImpl.class);


	/**
     * Returns the Medical Bill Specific Details.
     * 
     * @param Map
     *            inputMap
     * @return ResultObject
     */ 
	public ResultObject getMedBillDtls(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        
        try
        {
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

            String[] lStrRqstIdArr = (String[]) inputMap.get("AprdRqstIdArray");
            List lLstReturn = new ArrayList();
            
            for(int lIntCount = 0; lIntCount < lStrRqstIdArr.length; ++lIntCount)
            {
	            MedBillDAO lObjDAO = new MedBillDAOImpl(serv.getSessionFactory());
	            TrnMedblRqst lObjVO = lObjDAO.getMedBillDtls(Long.decode(lStrRqstIdArr[lIntCount]));
	            lLstReturn.add(lObjVO);
            }
            
            inputMap.put("ResultList", lLstReturn);
            
            objRes.setResultValue(inputMap);
        }
        catch (Exception e)
        {
            gLogger.error("Error in getMedBillDtls and Error is : " + e, e);

        }

        return objRes;
    }

	/**
     * Save the Medical Bill Specific Details in Respective VOs. 
     * 
     * @param Map
     *            inputMap
     * @return ResultObject
     */ 
	public ResultObject saveBill(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        Map lMapBillMap = (Map) inputMap.get("BillMap");

        TrnMedblHdr lObjMedHdr = null;
        TrnMedblDtls lObjMedDtl = null;
        TrnMedblAprvdDtls lObjMedAprvdDtl = null;

        List<TrnMedblDtls> lLstMedDtl = null;
        List<TrnMedblAprvdDtls> lLstMedAprvdDtls = null;

        try
        {
            TrnBillRegister lObjBillReg = (TrnBillRegister) inputMap.get("BillRegVO");

            lObjMedHdr = (TrnMedblHdr) lMapBillMap.get("MedBillHdr");
            lLstMedDtl = (List<TrnMedblDtls>) lMapBillMap.get("MedBillDtls");
            lLstMedAprvdDtls = (List<TrnMedblAprvdDtls>) lMapBillMap.get("MedBillAprvdDtls");

            lObjMedHdr.setBillNo(lObjBillReg.getBillNo());
            saveMedBillHdr(lObjMedHdr, inputMap);

            gLogger.info("Size of lLstMedDtl is : " + lLstMedDtl.size());
            gLogger.info("Bill No is : " + lObjBillReg.getBillNo());
            // Deleting the previously inserted data

            MedBillDAO lObjMedBillDAO = new MedBillDAOImpl(serv.getSessionFactory());
            TrnMedblDtlsDAO lObjMedBlDtlsDAO = new TrnMedblDtlsDAOImpl(TrnMedblDtls.class, serv.getSessionFactory());
            TrnMedblAprvdDtlsDAO lObjAprvdDtlsDAO = new TrnMedblAprvdDtlsDAOImpl(TrnMedblAprvdDtls.class, serv
                    .getSessionFactory());
            
            List lLstMedBillPK = lObjMedBillDAO.getPKForTableTrnMedBlDtls(lObjBillReg.getBillNo());
            if(lLstMedBillPK != null && lLstMedBillPK.size() > 0)
            {
            	Object[] lObj = null;
            	for(int i = 0; i < lLstMedBillPK.size(); ++i)
            	{
            		lObj = (Object[]) lLstMedBillPK.get(i);
            		lObjMedDtl = lObjMedBlDtlsDAO.read((Long) lObj[1]);
                    lObjMedBlDtlsDAO.delete(lObjMedDtl);
            	}
            }
            
            lLstMedBillPK = lObjMedBillDAO.getPKForTableMedBlAprvdDtls(lObjBillReg.getBillNo());
            if(lLstMedBillPK != null && lLstMedBillPK.size() > 0)
            {
            	for(int i = 0; i < lLstMedBillPK.size(); ++i)
            	{
            		lObjMedAprvdDtl = lObjAprvdDtlsDAO.read((Long) lLstMedBillPK.get(i));
                    lObjAprvdDtlsDAO.delete(lObjMedAprvdDtl);
            	}
            }
            
            Iterator<TrnMedblDtls> lItrDtl = lLstMedDtl.iterator();
            
            while (lItrDtl.hasNext())
            {
            	lObjMedDtl = lItrDtl.next();
                lObjMedDtl.setTrnMedblHdrId(lObjMedHdr.getTrnMedblHdrId());

                saveMedBillDtls(lObjMedDtl, inputMap);
            }

            Iterator<TrnMedblAprvdDtls> lItrMedAprvd = lLstMedAprvdDtls.iterator();

            while (lItrMedAprvd.hasNext())
            {
            	lObjMedAprvdDtl = lItrMedAprvd.next();
                lObjMedAprvdDtl.setTrnMedblHdrId(lObjMedHdr.getTrnMedblHdrId());

                saveMedBillAprvdDtls(lObjMedAprvdDtl, inputMap);
            }

            inputMap.put("MedBillHdr", lObjMedHdr);
            inputMap.put("MedBillDtl", lLstMedDtl);
            inputMap.put("MedBillAprvdDtl", lLstMedAprvdDtls);

            objRes.setResultValue(inputMap);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            gLogger.error("Error in saveBill. Error is : " + e, e);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setThrowable(e);
        }
        return objRes;
    }


	/**
     * Save the Medical Bill Details of MedBillHdr VO.
     * 
     * @param TrnMedblHdr, Map
     *            lObjMedBillHdr, inputMap
     * @return void
     */ 
	public static void saveMedBillHdr(TrnMedblHdr lObjMedBillHdr, Map inputMap)
    {
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        TrnMedblHdrDAO lObjMedBill = new TrnMedblHdrDAOImpl(TrnMedblHdr.class, serv.getSessionFactory());
        Long lLngMedBillHdrId = null;

        if (lObjMedBillHdr.getTrnMedblHdrId() != null)
        {
            //lObjMedBill.update(lObjMedBillHdr);
        }
        else
        {
            lLngMedBillHdrId = BptmCommonServiceImpl.getNextSeqNum("trn_medbl_hdr", inputMap);
            lObjMedBillHdr.setTrnMedblHdrId(lLngMedBillHdrId);
            //lObjMedBill.create(lObjMedBillHdr);
        }
    }


	/**
     * Save the Medical Bill Details of MedBillDtls VO.
     * 
     * @param TrnMedblDtls, Map
     *            lObjMedBillDtl, inputMap
     * @return void
     */ 
	public static void saveMedBillDtls(TrnMedblDtls lObjMedBillDtl, Map inputMap) throws Exception
    {
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        TrnMedblDtlsDAO lObjMedBill = new TrnMedblDtlsDAOImpl(TrnMedblDtls.class, serv.getSessionFactory());
        Long lLngMedBillDtlId = null;

        lLngMedBillDtlId = BptmCommonServiceImpl.getNextSeqNum("trn_medbl_dtls", inputMap);
        lObjMedBillDtl.setTrnMedblDtlsId(lLngMedBillDtlId);
        lObjMedBill.create(lObjMedBillDtl);
    }


	/**
     * Save the Medical Bill Details of MedBillAprvdDtls VO.
     * 
     * @param TrnMedblAprvdDtls, Map
     *            lObjAprvdDtls, inputMap
     * @return void
     */ 
	public static void saveMedBillAprvdDtls(TrnMedblAprvdDtls lObjAprvdDtls, Map inputMap)
    {
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

        TrnMedblAprvdDtlsDAO lObjMedBill = new TrnMedblAprvdDtlsDAOImpl(TrnMedblAprvdDtls.class, serv
                .getSessionFactory());

        Long lLngMedBillAprvdDtlId = null;

        lLngMedBillAprvdDtlId = BptmCommonServiceImpl.getNextSeqNum("trn_medbl_aprvd_dtls", inputMap);
        lObjAprvdDtls.setTrnMedblAprvdDtlsId(lLngMedBillAprvdDtlId);
       // lObjMedBill.create(lObjAprvdDtls);
    }


    /**
     * It gets the Medical Bill Specific Data required for Bill Preparation .
     * 
     * @param inputMap
     *            InputMap.
     * @return objRes ResultObject
     */
    public ResultObject getMedBillData(Map lMapInput)
    {
        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {
            MedBillDAOImpl lObj = new MedBillDAOImpl(serv.getSessionFactory());
            Long lLngBillNo = null;
            Map lMapMedData = null;
            String lStrBillNo = (String) lMapInput.get("billNo");

            if (lStrBillNo != null)
            {
                lLngBillNo = Long.parseLong(lStrBillNo);
                lMapMedData = lObj.getMedBillData(lLngBillNo, serv);
                gLogger.info("lMapMedData in getMedBillData is : " + lMapMedData);
            }
            objRes.setResultValue(lMapMedData);
        }
        catch (Exception e)
        {
            gLogger.error("Error in getMedBillData of Class MedBillServiceImpl and Error is : " + e, e);
        }

        return objRes;
    }
    /**
     * Method to Save the Digital Signatures for Medical bill Details
     * 
     * @param Map
     *            inputMap
     * @return ResultObject
     */

    public ResultObject saveDigiSigDtls(Map inputMap)
    {
        String lstrKey = null;
        String lstrAppId = null;
        
        String lstrRowId = null;
        String lstrfkVal = null;
        String lstrPkVal3 = null;
        String lstrPkVal2 = null;
        String lstrPkVal1 = null;
        
        int lIntDigiSeq4 = 0;
        int lIntDigiSeq5 = 0;
        int lIntDigiSeq6 = 0;
        int lIntDigiRowNumConst = 0;
        int lIntDigiRowNumIncr = 0;
        
        ResourceBundle lObjRsrcBundle = null;

        List<TrnMedblAprvdDtls> lLstMedBlAprvdDtls = null;
        List<TrnMedblDtls> lLstMedBlDtl = null;

        TrnMedblDtls lObjMedDtl = null;
        TrnMedblAprvdDtls lObjAprvdDtls = null;
        TrnMedblHdr lObjMedBlHdr = null;
        
        try
        {
            ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            SessionFactory sesfactory = serv.getSessionFactory();
            
            
            lstrAppId = (String) inputMap.get("ApplicationId");
            lstrKey = (String) inputMap.get("DigitalKey");
            lObjRsrcBundle = (ResourceBundle) inputMap.get("BundleObjcet");
            
            
            lIntDigiSeq4 = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiSeq4"));
            lIntDigiSeq5 = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiSeq5"));
            lIntDigiSeq6 = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiSeq6"));
            lIntDigiRowNumConst = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiRowNumConst"));
            lIntDigiRowNumIncr = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiRowNumIncr"));
            
            gLogger.info("this is at key" + lstrKey);
            
            
            BillCommonDAOImpl objDao = new BillCommonDAOImpl(sesfactory);
            lObjMedBlHdr = (TrnMedblHdr) inputMap.get("MedBillHdr");
            lLstMedBlAprvdDtls = (List<TrnMedblAprvdDtls>) inputMap.get("MedBillAprvdDtl");
            lLstMedBlDtl = (List<TrnMedblDtls>) inputMap.get("MedBillDtl");

            gLogger.info("size of MedBillAprvdDtl size is " + lLstMedBlAprvdDtls.size());
            gLogger.info("size of MedBillDtl list is" + lLstMedBlDtl.size());

            Iterator<TrnMedblDtls> lItrMedBlDtl = lLstMedBlDtl.iterator();

            while (lItrMedBlDtl.hasNext())
            {
                lObjMedDtl = lItrMedBlDtl.next();
                lstrPkVal2 = lObjMedDtl.getTrnMedblDtlsId().toString();
                gLogger.info("pk val is " + lstrPkVal2);
                int result = objDao.insertDigiMapping(lstrAppId, lIntDigiSeq4, lstrPkVal2, lstrfkVal, lIntDigiRowNumIncr, lstrRowId,
                        lstrKey);
                gLogger.info("This is at inserting TrnMedblDtls " + result + lIntDigiRowNumIncr);
                lIntDigiRowNumIncr++;

            }
            
            lIntDigiRowNumIncr = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiRowNumIncr"));
            Iterator<TrnMedblAprvdDtls> lItrAprvdDtl = lLstMedBlAprvdDtls.iterator();

            while (lItrAprvdDtl.hasNext())
            {
                lObjAprvdDtls = lItrAprvdDtl.next();
                lstrPkVal3 = lObjAprvdDtls.getTrnMedblAprvdDtlsId().toString();
                int result = objDao.insertDigiMapping(lstrAppId, lIntDigiSeq5, lstrPkVal3, lstrfkVal, lIntDigiRowNumIncr, lstrRowId,
                        lstrKey);
                gLogger.info(" This is at inserting medical bill aprvd dtls" + result + lIntDigiRowNumIncr);
                lIntDigiRowNumIncr++;
            }

            lstrPkVal1 = lObjMedBlHdr.getTrnMedblHdrId().toString();
            int result = objDao.insertDigiMapping(lstrAppId, lIntDigiSeq6, lstrPkVal1, lstrfkVal, lIntDigiRowNumConst, lstrRowId, lstrKey);
            gLogger.info(" This is at inserting TrnMedblHdr dtls" + result + lIntDigiRowNumConst);

            return objRes;
        }
        catch (Exception e)
        {
            gLogger.error("Error in insertDigiSign. Error is : " + e, e);
            return null;
        }
    }

    /**
     * Method to  Verify the Digital Signatures for Medical bill Details
     * 
     * @param Map
     *            lMapMedData
     * @return ResultObject
     */
    public ResultObject verifyMedBillDigiData(Map lMapMedData)
    {
        String lstrRowId = null;
        String lstrfkVal = null;
        String lstrPkVal3 = null;
        String lstrPkVal2 = null;
        String lstrPkVal1 = null;
        String lStrDigiKey = null;
        String lStrAppId = null;
        
        
        int lIntDigiSeq4 = 0;
        int lIntDigiSeq5 = 0;
        int lIntDigiSeq6 = 0;
        int lIntDigiRowNumConst = 0;
        int lIntDigiRowNumIncr = 0;
        
        ResourceBundle lObjRsrcBundle = null;

        List<TrnMedblAprvdDtls> lLstMedBlAprvdDtls = null;
        List<TrnMedblDtls> lLstMedBlDtl = null;

        Map lMapGetData = null;
        TrnMedblDtls lObjMedDtl = null;
        TrnMedblAprvdDtls lObjAprvdDtls = null;
        TrnMedblHdr lObjMedBlHdr = null;

        try
        {
            ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            lStrAppId = (String) lMapMedData.get("ApplicationId");
            lStrDigiKey = (String) lMapMedData.get("DigiKey");
            gLogger.info("This is in medbill serviceimpl   " + lStrAppId + "***" + lStrDigiKey);
            
            lObjRsrcBundle = (ResourceBundle) lMapMedData.get("BundleObjcet");
                        
            lIntDigiSeq4 = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiSeq4"));
            lIntDigiSeq5 = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiSeq5"));
            lIntDigiSeq6 = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiSeq6"));
            lIntDigiRowNumConst = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiRowNumConst"));
            lIntDigiRowNumIncr = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiRowNumIncr"));

            lMapGetData = (Map) lMapMedData.get("BillDtls");

            lObjMedBlHdr = (TrnMedblHdr) lMapGetData.get("TrnMedblHdr");
            lLstMedBlAprvdDtls = (List<TrnMedblAprvdDtls>) lMapGetData.get("TrnMedblAprvdDtls");
            lLstMedBlDtl = (List<TrnMedblDtls>) lMapGetData.get("TrnMedblDtls");

            ServiceLocator serv = (ServiceLocator) lMapMedData.get("serviceLocator");
            SessionFactory sesfactory = serv.getSessionFactory();
            BillCommonDAOImpl objDao = new BillCommonDAOImpl(sesfactory);

            gLogger.info("size of MedBillAprvdDtl size is " + lLstMedBlAprvdDtls.size());
            gLogger.info("size of MedBillDtl list is" + lLstMedBlDtl.size());
           
            Iterator<TrnMedblDtls> lItrMedBlDtl = lLstMedBlDtl.iterator();
            while (lItrMedBlDtl.hasNext())
            {
                lObjMedDtl = lItrMedBlDtl.next();
                lstrPkVal1 = lObjMedDtl.getTrnMedblDtlsId().toString();
               
                int result1 = objDao.insertDigiMapping(lStrAppId, lIntDigiSeq4, lstrPkVal1, lstrfkVal, lIntDigiRowNumIncr,
                        lstrRowId, lStrDigiKey);
               
                lIntDigiRowNumIncr++;
                objRes.setResultValue(result1);
            }
            
            lIntDigiRowNumIncr = Integer.parseInt(lObjRsrcBundle.getString("DIGI.DigiRowNumIncr"));
            Iterator<TrnMedblAprvdDtls> lItrAprvdDtl = lLstMedBlAprvdDtls.iterator();
            while (lItrAprvdDtl.hasNext())
            {
                lObjAprvdDtls = lItrAprvdDtl.next();
                lstrPkVal2 = lObjAprvdDtls.getTrnMedblAprvdDtlsId().toString();
                int result2 = objDao.insertDigiMapping(lStrAppId, lIntDigiSeq5, lstrPkVal2, lstrfkVal, lIntDigiRowNumIncr,
                        lstrRowId, lStrDigiKey);
                gLogger.info(" This is at inserting medical bill aprvd dtls" + result2 + lIntDigiRowNumIncr);
                objRes.setResultValue(result2);
                lIntDigiRowNumIncr++;
            }

            lstrPkVal3 = lObjMedBlHdr.getTrnMedblHdrId().toString();
            int result3 = objDao.insertDigiMapping(lStrAppId, lIntDigiSeq6, lstrPkVal3, lstrfkVal, lIntDigiRowNumConst, lstrRowId,
                    lStrDigiKey);
            gLogger.info(" This is at inserting TrnMedblHdr dtls" + result3 + lIntDigiRowNumConst);

            objRes.setResultValue(result3);
            return objRes;

        }
        catch (Exception e)
        {
            gLogger.error("Error while inserting data in to sgva_mapping_Key_temp table for medical bill");
            return null;

        }

    }
}