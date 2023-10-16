/**
 * package : com.tcs.sgv.onlinebillprep.service
 * 
 * @verion : 1.0
 * @author : Keyur Shah, Nirav Bumia *
 */

package com.tcs.sgv.onlinebillprep.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.ls.LSParser;

import com.tcs.sgv.billproc.common.dao.RemarksDAO;
import com.tcs.sgv.billproc.common.dao.RemarksDAOImpl;
import com.tcs.sgv.billproc.common.dao.ShowBillDAOImpl;
import com.tcs.sgv.billproc.common.valueobject.TrnBillRemarks;
import com.tcs.sgv.billproc.common.valueobject.TrnShowBill;
import com.tcs.sgv.billproc.counter.dao.BdgtHeadDtlsDAOImpl;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.billproc.counter.dao.TrnRcptDtlsDAOImpl;
import com.tcs.sgv.common.dao.BillMovementDAOImpl;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.DDOInfoDAO;
import com.tcs.sgv.common.dao.DDOInfoDAOImpl;
import com.tcs.sgv.common.dao.RltBillPartyDAO;
import com.tcs.sgv.common.dao.RltBillPartyDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.util.DigitalFrameworkUtil;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.RltBillParty;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillEdpDtls;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnRcptBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.service.DssDataService;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.exprcpt.dao.RcptBudDtlsDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.BillRequestDAO;
import com.tcs.sgv.onlinebillprep.dao.BillRequestDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.OnlineBillDAO;
import com.tcs.sgv.onlinebillprep.dao.OnlineBillDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.RltBillRqstDAO;
import com.tcs.sgv.onlinebillprep.dao.RltBillRqstDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnAprvdRqstDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnAprvdRqstDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnChallanPartyDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnOnlinebillEmpDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnOnlinebillEmpDAOImpl;
import com.tcs.sgv.onlinebillprep.util.ConfigOnlineBillUtil;
import com.tcs.sgv.onlinebillprep.util.OnlineBillUtil;
import com.tcs.sgv.onlinebillprep.valueobject.ConfigOnlineBill;
import com.tcs.sgv.onlinebillprep.valueobject.RltBillRqst;
import com.tcs.sgv.onlinebillprep.valueobject.TrnAprvdRqst;
import com.tcs.sgv.onlinebillprep.valueobject.TrnChallanParty;
import com.tcs.sgv.onlinebillprep.valueobject.TrnMedblHdr;
import com.tcs.sgv.onlinebillprep.valueobject.TrnOnlinebillEmp;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablHdr;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.util.WorkFlowUtility;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;
import com.tcs.sgv.common.utils.StringUtility;

/**
 * Service class for Online Bill Preparation.
 * 
 * @author Keyur Shah, Nirav Bumia
 * @version 1.0
 */
public class OnlineBillServiceImpl extends ServiceImpl implements OnlineBillService
{
    /* Global Variable for PostId */
    Long gLngPostId = null;

    /* Global Variable for UserId */
    Long gLngUserId = null;

    /* Global Variable for LangId */
    Long gLngLangId = null;

    /* Global Variable for EmpId */
    Long gLngEmpId = null;

    /* Global Variable for Location Id */
    String gStrLocId = null;

    /* Global Variable for DB Id */
    Long gLngDBId = null;

    /* Global Variable for Location Code */
    String gStrLocationCode = null;

    /* Global Variable for ResourceBundle */
    private static ResourceBundle bundleConst = ResourceBundle
            .getBundle("resources/onlinebillprep/OnlineBillConstants");

    private static ResourceBundle verifybundleConst = ResourceBundle
            .getBundle("resources/onlinebillprep/DigiVerifyConstants");

    /* Global Variable for Logger Class */
    Log gLogger = LogFactory.getLog(getClass());


    /**
     * Saves the OnlineBill Details in case of Bill Creation as well as
     * Updation. Its being called from createOnlineBillFromWF method.
     * 
     * @param objectArgs ServiceMap
     * @return Map OutputMap.
     */
    
    public synchronized Map saveOnlineBillDtls(Map objectArgs) throws Exception
    {

    	//commented by Ankit Bhatt
        //WorkFlowVO wfVO = null;
    	//comment ended
        Map lMapService = null;

        String lStrAction = null;
        boolean lBoolIsNewBill = false;

        gLogger.info("in saveOnlineBillDtls for testing ctrlNo "+ objectArgs.get("ctrlNo")+" .............");
        
        //commented by Ankit Bhatt
        /*if (objectArgs.get("WorkFlowVO") != null) // Will be called only in
        // case of Workflow
        {
            wfVO = (WorkFlowVO) objectArgs.get("WorkFlowVO");
            lMapService = wfVO.getAppMap();
            lBoolIsNewBill = true;
            // lMapService.put("DIGIMETHOD", "SAVE");
        }
        else // Else Update the data
        {*/
        //comment ended
            lMapService = objectArgs;
         
            // lMapService.put("DIGIMETHOD", "EDIT");
        //}

        lMapService.put("IsNewBill", lBoolIsNewBill);

        gLogger.info("second time in saveOnlineBillDtls for testing ctrlNo "+ lMapService.get("ctrlNo")+" .............");
        
        Long lLngBillNo = 0L;

        ServiceLocator srvcLoc = (ServiceLocator) lMapService.get("serviceLocator");
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        TrnBillRegister lObjBillReg = (TrnBillRegister) lMapService.get("BillRegVO");
        
        lObjBillReg.setBillCntrlNo(lObjBillReg.getBillCntrlNo()+"( "+lMapService.get("ctrlNo")+" )");
                
        TrnBillBudheadDtls lObjBudHeadVO = (TrnBillBudheadDtls) lMapService.get("BillHeadVO");
        
        //commented by Ankit Bhatt
        /*TrnOnlinebillEmp lObjEmpDtlsVO = (TrnOnlinebillEmp) lMapService.get("EmpDtlsVO");
        TrnBillMvmnt lObjBillMvmnt = (TrnBillMvmnt) lMapService.get("BillMvmntVO");
        TrnBillRemarks lObjBillRmrks = (TrnBillRemarks) lMapService.get("BillRmrksVO");*/
        //comment ended

        Map lMapBillEDPDtls = null;
        Map lMapDSSOutput = null;

        RltBillRqst lObjBillRqst = null;
        RltBillParty lObjBillParty = null;

        List<RltBillRqst> lLstBillRqst = null;
        List<RltBillParty> lLstBillParty = null;

        RltBillRqstDAO lObjBillRqstDAO = new RltBillRqstDAOImpl(RltBillRqst.class, srvcLoc.getSessionFactory());
        TrnAprvdRqstDAO lObjAprvdRqstDAO = new TrnAprvdRqstDAOImpl(TrnAprvdRqst.class, srvcLoc.getSessionFactory());
        RltBillPartyDAO lObjBillPartyDAO = new RltBillPartyDAOImpl(RltBillParty.class, srvcLoc.getSessionFactory());

        /*try
        {*/
            gLogger.info("lMapService in saveOnlineBillDtls is : " + lMapService);
          /*  // Added By Urvin shah.
            
            ResultObject resultBill = srvcLoc.executeService("generatePaybill", lMapService);
		    Map billMap = (Map)resultBill.getResultValue();
		    HttpServletRequest request = (HttpServletRequest)billMap.get("requestObj");
		    
		    
		    //by manoj for exception handling as exception in generate bill service
		    String paybillGenerated = billMap.get("paybillGenerated").toString();
		  	    
		    gLogger.error("got paybillGenerated from generate bill service is "+paybillGenerated);
		    
		    
		    if(paybillGenerated!=null && paybillGenerated.equals("3"))
		    {	    	
		    	lMapService.put("paybillGenerated", paybillGenerated);
		    	return lMapService;
		    	//throw new Exception("Exception in generatePaybill");
		    }
		    
		    // End by Urvin shah.
*/
		    //commented by Ankit Bhatt.
            // Call for VOGEN & save SRVC for challan - start - jay
            // call VOGEN for Challan
            /*if (lObjBillReg.getTcBill().equalsIgnoreCase("TC"))
            {

                objRes = srvcLoc.executeService("GET_CHALLAN_MAP", lMapService);
                lMapService = (Map) objRes.getResultValue();

                // save challan
                objRes = srvcLoc.executeService("SAVE_CHALLAN_OBPM", lMapService);
                lMapService = (Map) objRes.getResultValue();

                // Call for VOGEN & save SRVC for challan - end - jay
                lObjBillReg.setReceiptId(lMapService.get("challanRcptId").toString());
            }*/
		    //comment ended
		    
            saveBillRegister(lObjBillReg, lMapService);
            
            
            //added by Ankit Bhatt for calling GeneratePaybillService.
            gLogger.info("going to execute Paybill service....ankit");
                        
            //lMapService.put("deptNo","100007");
            lMapService.put("demandNo",lObjBudHeadVO.getDmndNo());
            lMapService.put("mjrHead",lObjBudHeadVO.getBudMjrHd());
            lMapService.put("subMjrHead",lObjBudHeadVO.getBudSubmjrHd());
            lMapService.put("mnrHead",lObjBudHeadVO.getBudMinHd());
            lMapService.put("subHead",lObjBudHeadVO.getBudSubHd());
            lMapService.put("dtlHead","-1");
            
            String billTypeCmb = lMapService.get("billTypeCmb").toString();
            
            gLogger.info("the billTypeCmb in onlineBillService is "+billTypeCmb);
            
            lMapService.put("trnBillReg",lObjBillReg);
            
            
            Map billMap=new HashMap();
            ResultObject resultBill = new ResultObject();
            
            /*if(billTypeCmb.equalsIgnoreCase("arrears"))
            {
            	synchronized(this)
            	{
            		resultBill = srvcLoc.executeService("generateArrearbillService", lMapService);
            	}
            }
            else
            {
            	synchronized(this)
            	{
            		resultBill = srvcLoc.executeService("generatePaybill", lMapService);
            	}
            }*/
            gLogger.info("Going to call Paybill service frm IFMS");
            resultBill = srvcLoc.executeService("generatePaybill", lMapService);
            billMap = (Map)resultBill.getResultValue();
            String paybillGenerated = billMap.get("paybillGenerated").toString();
            gLogger.error("got paybillGenerated from generate bill service is "+paybillGenerated);
            long isBillGenerated = 0l;
            if(paybillGenerated!=null && !paybillGenerated.trim().equals(""))
            	isBillGenerated = Long.valueOf(paybillGenerated);
            if(isBillGenerated!=0) {
             gLogger.info("Going to call Outer service frm IFMS");
             srvcLoc.getServiceLocator().getSessionFactory().getCurrentSession().flush();
             resultBill = srvcLoc.executeService("GENERATE_OUTER", lMapService);
            }
            else
            	throw new Exception("Paybill is not generated");


            //Temp code - Commented
            //remove comment to generate Outer
            //resultBill = srvcLoc.executeService("GENERATE_OUTER", lMapService);
            
            
            
            
            
		     
		    HttpServletRequest request = (HttpServletRequest)billMap.get("requestObj");
            
		    
		    
		    //by manoj for exception handling as exception in generate bill service
		    
		  	    
		    
		    
		    lMapService.put("paybillGenerated", paybillGenerated);
		    if(paybillGenerated!=null && paybillGenerated.equals("0"))
		    {
		    	//return lMapService;
		    	lMapService.put("StateMsg", "Bill already Generated");
		    	//throw new Exception("Exception in generatePaybill");
		    }
            lMapService.put("paybillGenerated", paybillGenerated);
            
           
            /*String Status = billMap.get("status").toString();
            System.out.println("Bill Status is:::::::::::::::::"+Status);
            	
            	if(Status.equals("1"))
            	{
            		lMapService.put("StateMsg", "Bill Saved Successfully");
            	}
            	else if(Status.equals("2"))
            	{
            		lMapService.put("StateMsg", "Bill already Generated");
            	}
            	else
            	{
            		lMapService.put("StateMsg", "There is some problem. Please try again later. ");
            	}*/
		    //end by manoj for exception handling as exception in generate bill service
		    String s[] = request.getParameterValues("hdPayEdpId");
		    gLogger.info("edp id list size after paybill is " + s.length);		    
		    
		     gLogger.info("Paybill service is completed...." + request.getParameter("txtExpenditure"));
		     gLogger.info("Request object after bill generation" + request.toString());
		     lMapService.put("requestObj", request);
		     double lDblExpAmt = Double.parseDouble(OnlineBillUtil.requestSetter("txtExpenditure", request));
		     double lDblRecAmt = Double.parseDouble(OnlineBillUtil.requestSetter("txtRecovery", request));
		     double lDblDedA = Double.parseDouble(OnlineBillUtil.requestSetter("DeductionA", request));
		     double lDblDedB = Double.parseDouble(OnlineBillUtil.requestSetter("DeductionB", request));
		     gLogger.info("Total Expenditure nad Recovery in OnlineBillService is " + lDblExpAmt + "---" + lDblRecAmt);
		     lObjBillReg.setBillGrossAmount(new BigDecimal(lDblExpAmt - lDblRecAmt));
		     lObjBillReg.setBillNetAmount(new BigDecimal(lDblExpAmt - lDblRecAmt - lDblDedA - lDblDedB));
		    /* lObjBillReg.setDeptCode(String.valueOf(lMapService.get("deptNo")));
		     gLogger.info("Dept Code in TrnBillRegVO is " + lMapService.get("deptNo"));*/
            
		     //ended by Ankit Bhatt.

            lLngBillNo = lObjBillReg.getBillNo();
            
            //Commented by Paurav 
            //HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
            //Ended by Paurav
            
            //commented by Ankit Bhatt
            /*if (lBoolIsNewBill) // Insert in bill movement only at time of bill
            // creation
            {
                lObjBillMvmnt.setBillNo(lLngBillNo);
                saveBillMovement(lObjBillMvmnt, lMapService);
            }
            
            //Commented by Paurav as Employee Details would be absent in Paybill
            //lObjEmpDtlsVO.setBillNo(lLngBillNo);
            //saveEmployeeDtls(lObjEmpDtlsVO, lMapService);
            //Ended by Paurav
            
            lMapService.put("TrnEmpId", lObjEmpDtlsVO.getTrnOnlinebillEmpId());*/
            //comment ended
            lObjBudHeadVO.setBillNo(lLngBillNo);
            saveBillBudHd(lObjBudHeadVO, lMapService);

            //commented by Ankit Bhatt
            /*lObjBillRmrks.setBillNo(lLngBillNo);
            saveBillRemarks(lObjBillRmrks, lMapService);*/
            
            //comment ended
            
            lMapService.put("BillNo", lLngBillNo);

            // Saving the DSS Data
            lMapDSSOutput = saveDSSData(lMapService);

            //commneted by Ankit Bhatt  -  Audit Service
            
            /*ResultObject lResObj = srvcLoc.executeService("OBJ_VOGEN", lMapService);
            lMapService = (Map) lResObj.getResultValue();
            List lList = (List) lMapService.get("auditObjList");

            AuditorObjectionDAO lObjAdtObjDAO = new AuditorObjectionDAOImpl(RltBillObjection.class, srvcLoc
                    .getSessionFactory());
            lObjAdtObjDAO.updateObjection(lList, String.valueOf(lLngBillNo), String.valueOf(gLngUserId),
                    lMapService);*/
            //comment ended
            
            // Deleting previous EDP Data
            srvcLoc.executeService("DELETE_EXT_EDP_CODE", lMapService);

            lMapService.put("billNo", String.valueOf(lLngBillNo));
            ResultObject lRsBillEDPDtls = srvcLoc.executeService("GENERATE_EDP_VO", lMapService);
            lMapBillEDPDtls = (Map) lRsBillEDPDtls.getResultValue();

            // Inserting New EDP Data
            srvcLoc.executeService("INSERT_EDP_DTLS", lMapBillEDPDtls);

            
            //commented by Ankit Bhatt.
            /*// the Bill Request Data only in case of Bill creation.
            if (lMapService.get("BillRqstDtls") != null)
            {
                lLstBillRqst = (List<RltBillRqst>) lMapService.get("BillRqstDtls");

                Iterator<RltBillRqst> lItrBillRqst = lLstBillRqst.iterator();
                TrnAprvdRqst lObjAprvdRqst = null;

                while (lItrBillRqst.hasNext())
                {
                    lObjBillRqst = lItrBillRqst.next();

                    lObjBillRqst.setRltBillRqstId(BptmCommonServiceImpl.getNextSeqNum("rlt_bill_rqst", lMapService));
                    lObjBillRqst.setBillNo(lLngBillNo);

                    lObjBillRqstDAO.create(lObjBillRqst);

                    lObjAprvdRqst = lObjAprvdRqstDAO.read(lObjBillRqst.getRqstId());
                    lObjAprvdRqst.setIsBillCrtd("Y");

                    gLogger.info("TrnAprvdRqstId is : " + lObjAprvdRqst.getTrnAprvdRqstId());
                    gLogger.info("IsBillCrtd is :" + lObjAprvdRqst.getIsBillCrtd());

                    lObjAprvdRqstDAO.update(lObjAprvdRqst);
                }
            }

            // Deleting previous party Info
            lLstBillParty = lObjBillPartyDAO.getPartyByBill(lLngBillNo, srvcLoc.getSessionFactory());
            Iterator<RltBillParty> lItrBillParty = lLstBillParty.iterator();

            while (lItrBillParty.hasNext())
            {
                lObjBillPartyDAO.delete(lItrBillParty.next());
            }

            // Saving the Bill Party Details
            if (lMapService.get("BillPartyDtls") != null)
            {
                lLstBillParty = (List<RltBillParty>) lMapService.get("BillPartyDtls");

                lItrBillParty = lLstBillParty.iterator();

                while (lItrBillParty.hasNext())
                {
                    lObjBillParty = lItrBillParty.next();
                    lObjBillParty.setBillNo(lLngBillNo);
                    saveBillParty(lObjBillParty, lMapService);
                }
            }
            ConfigOnlineBill lObjConfigBill = ConfigOnlineBillUtil.getInstance(lObjBillReg.getSubjectId(), srvcLoc);
            objRes = srvcLoc.executeService(lObjConfigBill.getSaveBillSrvc(), lMapService);*/
            //comment ended by Ankit Bhatt.
            
            String lStrDigiFlag = bundleConst.getString("DigiEnabled");
            gLogger.info("Digital Signatures has been enabled ?" + lStrDigiFlag);
            // Insert the Digital Signature
            if (lStrDigiFlag.equalsIgnoreCase("YES"))
            {
                if (OnlineBillUtil.requestSetter("userAction", request) != null)
                {
                    lStrAction = OnlineBillUtil.requestSetter("userAction", request);
                }

                if (lStrAction.equals("approve") && lStrAction != null)
                {
                    gLogger.info("This is at service Before calling insertDigiSign method");
                    insertDigiSign(lMapService);
                    gLogger.info("This is at service after calling insertDigiSign method");
                }
            }
            
            //commented by Ankit Bhatt
            //Map lMapBillSaveResult = (Map) objRes.getResultValue();
            //comment ended
            
            lMapService.put("jobId", lLngBillNo + "");
            lMapService.put("postId", gLngPostId);
            
            //commented by Ankit Bhatt
            //lMapService.put("BillMovementVO", lObjBillMvmnt);
            //comment ended
            
            

            //commented by Ankit Bhatt
            /*if (wfVO != null)
            {
                wfVO.setJobRefId(lLngBillNo + "");
                lMapService.put("WorkFlowVO", wfVO);
                wfVO.setAppMap(lMapService);
            }*/
            //comment ended
/*        }
        catch (Exception e)
        {
            
        	gLogger.error("changed Error in saveOnlineBillDtls. Error is : " + e, e);
        }*/

        return lMapService;
    }


    public void saveEmployeeDtls(TrnOnlinebillEmp lObjTrnOnlineBillEmpVO, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        TrnOnlinebillEmpDAO lObjTrnOnlinebillEmpDAO = new TrnOnlinebillEmpDAOImpl(TrnOnlinebillEmp.class, srvcLoc
                .getSessionFactory());

        try
        {
            if (lObjTrnOnlineBillEmpVO.getTrnOnlinebillEmpId() == null)
            {
                lObjTrnOnlineBillEmpVO.setTrnOnlinebillEmpId(BptmCommonServiceImpl.getNextSeqNum("trn_onlinebill_emp",
                        inputMap));
                lObjTrnOnlinebillEmpDAO.create(lObjTrnOnlineBillEmpVO);
            }
            else
            {
                lObjTrnOnlinebillEmpDAO.update(lObjTrnOnlineBillEmpVO);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error in saveEmployeeDtls. Error is : " + e, e);
            throw e;
        }
    }


    /**
     * Saves the Bills data in TrnBillRegister. In case of Bill Creation it
     * inserts the data else it updates the data.
     * 
     * @param lObjTrnBillRegVO TrnBillRegisterVO
     * @param inputMap InputMap.
     * @throws Exception  Exception
     */
    public void saveBillRegister(TrnBillRegister lObjTrnBillRegVO, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(TrnBillRegister.class, srvcLoc.getSessionFactory());

        try
        {
            if (lObjTrnBillRegVO.getBillNo() > 0)
            {
                lObjPhyBillDAOImpl.update(lObjTrnBillRegVO);
            }
            else
            {
                BptmCommonServiceImpl.insertBillRegister(lObjTrnBillRegVO, inputMap);
            }

            inputMap.put("billNo", lObjTrnBillRegVO.getBillNo());
        }
        catch (Exception e)
        {
            gLogger.error("Error in saveBillRegister. Error is : " + e, e);
            throw e;
        }
    }


    /**
     * Saves the Party data associated with the Bills in RltBillParty. the
     * data.
     * 
     * @param RltBillParty lObjBillParty, Map InputMap.
     * @throws Exception Exception
     */

    public void saveBillParty(RltBillParty lObjBillParty, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        RltBillPartyDAO lObjBillPartyDAO = new RltBillPartyDAOImpl(RltBillParty.class, srvcLoc.getSessionFactory());
        Long lLngBillPartyId = null;

        try
        {
            lLngBillPartyId = BptmCommonServiceImpl.getNextSeqNum("rlt_bill_party", inputMap);
            lObjBillParty.setBillPartyId(lLngBillPartyId);
            lObjBillPartyDAO.create(lObjBillParty);
        }
        catch (Exception e)
        {
            gLogger.error("Error in saveBillParty. Error is : " + e, e);
            throw e;
        }
    }


    /**
     * Saves the Bill Movement Data in TrnBillMvmnt. This method will be
     * called only at time of Bill Creation.
     * 
     * @param lObjBillMvmntVO TrnBillMvmntVO
     * @param inputMap InputMap.
     * @throws Exception Exception
     */
    public void saveBillMovement(TrnBillMvmnt lObjBillMvmntVO, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        BillMovementDAOImpl lObjMvmntDAO = new BillMovementDAOImpl(TrnBillMvmnt.class, srvcLoc.getSessionFactory());

        try
        {
            if (lObjBillMvmntVO.getBillMvmtId() > 0)
            {
                lObjMvmntDAO.update(lObjBillMvmntVO);
            }
            else
            {
                BptmCommonServiceImpl.insertMovement(lObjBillMvmntVO, inputMap);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error in saveBillMovement. Error is : " + e, e);
            throw e;
        }
    }


    /**
     * Saves the Bill Remarks Data in TrnBillRemarks. In case of Bill
     * Creation it inserts the data else it updates the data.
     * 
     * @param lObjBillRmk TrnBillRemarksVO
     * @param inputMap InputMap.
     * @throws Exception Exception
     */
    public void saveBillRemarks(TrnBillRemarks lObjBillRmk, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        RemarksDAO lObjRmksDAO = new RemarksDAOImpl(TrnBillRemarks.class, srvcLoc.getSessionFactory());

        try
        {
            if (lObjBillRmk.getRmrksId() > 0)
            {
                inputMap.put("TrnBillRemarksVO", lObjBillRmk);
                lObjRmksDAO.updateRemarks(inputMap);
            }
            else
            {
                BptmCommonServiceImpl.insertRemarks(lObjBillRmk, inputMap);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error in saveBillRemarks. Error is : " + e, e);
            throw e;
        }
    }


    /**
     * Saves the Bill Budget Heads Data in TrnBillBudheadDtls. In case of
     * Bill Creation it inserts the data else it updates the data.
     * 
     * @param lObjBillBudHdVO
     *            TrnBillBudheadDtlsVO.
     * @param inputMap
     *            InputMap.
     * @throws Exception
     *             Exception
     */

    public void saveBillBudHd(TrnBillBudheadDtls lObjBillBudHdVO, Map inputMap) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        BdgtHeadDtlsDAOImpl lObjBdgtHeadDtlsDAOImpl = new BdgtHeadDtlsDAOImpl(TrnBillBudheadDtls.class, srvcLoc
                .getSessionFactory());

        try
        {
            if (lObjBillBudHdVO.getBillBudId() > 0)
            {
                lObjBdgtHeadDtlsDAOImpl.update(lObjBillBudHdVO);
            }
            else
            {
                BptmCommonServiceImpl.insertBudHeadDtls(lObjBillBudHdVO, inputMap);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error in saveBillBudHd. Error is : " + e, e);
            throw e;
        }
    }


    /**
     * Saves the Bills data. This method is not called from Action - Service
     * Mapping but being called from Workflow mappings done in DB. For more info
     * about mapping check query [select * from wf_rule_mst w WHERE w.RULE_ID =
     * 110001]
     * 
     * @param inputMap
     *            InputMap.
     * @return objRes ResultObject
     */

    public synchronized ResultObject createOnlineBillFromWF(Map inputMap)
    {
    	gLogger.info("OnlineBill start " + System.currentTimeMillis());
    
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        WorkFlowVO workFlowVO = new WorkFlowVO();
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        Connection conn = serv.getSessionFactory().getCurrentSession().connection();

        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrSubject = bundleConst.getString("OnlineBillSubject");
        String lStrAction = null;

        TrnBillRegister lObjBillReg = (TrnBillRegister) inputMap.get("BillRegVO");
        Map lMapInsertOutput = null;

        try
        {
            setSessionInfo(inputMap);

        
            /*if (lObjBillReg.getBillNo() == 0) // Call the workflow at time of
            // Create Bill ONLY
            {
                // Setting the workflow
            	//added by Ankit Bhatt
            	synchronized(workFlowVO){
            		//ended by Ankit Bhatt.
                workFlowVO.setAppMap(inputMap);
                workFlowVO.setCrtEmpId(gLngEmpId + "");
                workFlowVO.setCrtPost(gLngPostId + "");
                workFlowVO.setFromPost(gLngPostId + "");
                workFlowVO.setCrtUsr(gLngUserId + "");
                workFlowVO.setConnection(conn);
                workFlowVO.setActId(1); // Id for Creating a document (Action
                // Id)
                workFlowVO.setDocId(110001); // Id for Bill document
                workFlowVO.setJobRefId("1"); // Default value for new bill
                workFlowVO.setLocID(gStrLocId);
                workFlowVO.setDbId(gLngDBId);
                //Added by Mrugesh
                workFlowVO.setJobTitle("OnlineBill");
                workFlowVO.setHierarchyFlag(1);
                //Ended
                gLogger.info("Post ID for Hierarchy is " + gLngPostId);
                OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
                Map lMapHierarchyInfo = orgUtil.getHierarchyByPostIDAndDescription(gLngPostId + "", lStrSubject);
                List lLstHierarchy = (List) lMapHierarchyInfo.get("Result");
                Long lLngHeirRefId = null;
                if (lLstHierarchy != null && lLstHierarchy.size() > 0)
                {
                	lLngHeirRefId = Long.parseLong(lLstHierarchy.get(0).toString());
                }
                workFlowVO.setHirRefId(lLngHeirRefId);
                WorkFlowUtility wfUtility = new WorkFlowUtility();
               
                Map lMapWorkFlow = wfUtility.invokeWorkFlow(workFlowVO);
                WorkFlowVO lObjWrkFlwVO = (WorkFlowVO) lMapWorkFlow.get("WorkFlowVO");
                lMapInsertOutput = lObjWrkFlwVO.getAppMap();
              
                //added by Ankit Bhatt.
            	}
            	//ended by Ankit Bhatt
            }
            else*/
            // Save the data
            ///{
            	lMapInsertOutput = saveOnlineBillDtls(inputMap);
            //}

                  
            gLogger.info("lMapWrkFlwOutput in createOnlineBillFromWF is : " + lMapInsertOutput);

            lStrAction = OnlineBillUtil.requestSetter("userAction", request);

            if (lStrAction.equals("save"))
            {
            	lMapInsertOutput.put("isEditable", "Y");            	
        		lMapInsertOutput.put("MESSAGECODE", (long) 1045);
            	/*long paybillGenerated = Long.parseLong(lMapInsertOutput.get("paybillGenerated").toString());
            	if(paybillGenerated==1){
            		
            	}
            	else if(paybillGenerated==3){
            		lMapInsertOutput.put("MESSAGECODE", (long) 300011);
            		String orderNameMapping = new AjaxXmlBuilder().addItem("ajax_key", "").toString();
        	        inputMap.put("ajaxKey", orderNameMapping);                   
                    objRes.setResultValue(inputMap);
                    objRes.setResultCode(ErrorConstants.SUCCESS);
                    objRes.setViewName("ajaxData");
                    return objRes;
            		//throw new Exception("Paybill is Already generated for this month");
            		
            	}*/
            }
            else if (lStrAction.equals("approve"))
            {
                lMapInsertOutput.put("isEditable", "N");
                lMapInsertOutput.put("MESSAGECODE", (long) 1046);
            }            
            
            
            /*
             * String branchNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
        result=objectArgs;
        result.put("ajaxKey", branchNameIdStr);
           
        resultObject.setResultValue(result);
        resultObject.setViewName("ajaxData");    
            */
            
            StringBuffer data=new StringBuffer();
            	data.append("<deptdetails>");
			data.append("<DeptName>1</DeptName>");
			data.append("</deptdetails>");
            
			String orderNameMapping = new AjaxXmlBuilder().addItem("ajax_key", data.toString()).toString();
	         
            gLogger.info("for testing the string buffer is :"+orderNameMapping);
            
            lMapInsertOutput.put("ajaxKey", orderNameMapping);
            inputMap.put("ajaxKey", orderNameMapping);
            
            objRes.setResultCode(ErrorConstants.SUCCESS); 
            objRes.setResultValue(inputMap);
            gLogger.info("OnlineBill end " + System.currentTimeMillis());
	       objRes.setViewName("ajaxData");
        }
        /*catch (Exception e)
        {
            gLogger.error("Error in execution of createOnlineBillFromWF. Error is : " + e, e);
            
            lMapInsertOutput.put("MESSAGECODE", (long) 1047);
            objRes.setResultValue(lMapInsertOutput);
            objRes.setViewName("ajaxData");
        }*/
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            inputMap.put("MESSAGECODE", (long) 1047);
            
            String orderNameMapping = new AjaxXmlBuilder().addItem("ajax_key", "").toString();
	        inputMap.put("ajaxKey", orderNameMapping);
            
            objRes.setThrowable(e);
            objRes.setResultValue(inputMap);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setViewName("ajaxData");
        }

        
        return objRes;
    }


    /**
     * Forwards the Bill to the selected person in the hierarchy. Its also
     * being used in case of forwarding the bill to the Treasury Office.
     * 
     * @param Map
     *            inputMap
     * @return lObjResult ResultObject
     */

    public ResultObject forwardOnlineBill(Map inputMap)
    {
        ResultObject lObjResult = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(TrnBillRegister.class, serv.getSessionFactory());

        String lStrBillNo = null;
        String lStrBillStatus = null;
        String lStrToHierchyRefId = null;
        String lStrToPostId = null;
        String lStrSubject = bundleConst.getString("OnlineBillSubject");
        String lStrSeltdTrsry = null;

        TrnBillRegister lObjTrnBillReg = null;
        TrnBillMvmnt lObjBillMvmntVO = null;
        Connection conn = serv.getSessionFactory().getCurrentSession().connection();

        try
        {
            setSessionInfo(inputMap);

            lStrBillNo = OnlineBillUtil.requestSetter("hidBillNo", request);
            lStrBillStatus = OnlineBillUtil.requestSetter("CurrBillStatus", request);
            lStrToHierchyRefId = OnlineBillUtil.requestSetter("ToHeirarchyRefId", request);
            lStrToPostId = OnlineBillUtil.requestSetter("toPost", request);
            lStrSeltdTrsry = OnlineBillUtil.requestSetter("seltdTrsry", request);

            gLogger.info("Selected Treasury office code is = " + lStrSeltdTrsry);

            String[] lStrToPostLvl = lStrToPostId.split("~");

            lObjTrnBillReg = lObjPhyBillDAOImpl.read(Long.parseLong(lStrBillNo));

            lObjTrnBillReg.setCurrBillStatus(lStrBillStatus);
            lObjTrnBillReg.setCurrBillStatusDate(new Date(System.currentTimeMillis()));
            lObjTrnBillReg.setUpdatedDate(new Date(System.currentTimeMillis()));
            lObjTrnBillReg.setUpdatedPostId(gLngPostId);
            lObjTrnBillReg.setUpdatedUserId(gLngUserId);

            if (lStrSeltdTrsry.length() > 0 && !lStrSeltdTrsry.equals("-1"))
            {
                CmnLocationMstDaoImpl lObjCmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv
                        .getSessionFactory());
                lObjTrnBillReg.setTsryOfficeCode(lObjCmnLocationMstDaoImpl.read(Long.parseLong(lStrSeltdTrsry))
                        .getLocationCode());
                
                lObjPhyBillDAOImpl.update(lObjTrnBillReg);

                // Updating the Bill Control No
                BptmCommonServiceImpl.generateBillControlNum(inputMap);
            }
            
            lObjBillMvmntVO = getWrkFlwBillMvmntVO(inputMap);

            /* Workflow Code starts here */
            WorkFlowVO workFlowVO = new WorkFlowVO();
            workFlowVO.setActId(2); // Forward Action Id
            workFlowVO.setDocId(110001); // Online Bill Doc id
            workFlowVO.setJobRefId(lStrBillNo);
            workFlowVO.setLocID(gStrLocId);
            workFlowVO.setDbId(gLngDBId);
            workFlowVO.setHierarchyFlag(1);
            workFlowVO.setAppMap(inputMap);
            workFlowVO.setCrtEmpId(gLngEmpId + "");
            workFlowVO.setCrtPost(gLngPostId + "");
            workFlowVO.setCrtUsr(gLngUserId + "");
            workFlowVO.setToPost(lStrToPostLvl[0]);
            workFlowVO.setConnection(conn);
            workFlowVO.setFromPost(gLngPostId + "");

            OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
            Map lMapHierarchyInfo = orgUtil.getHierarchyByPostIDAndDescription(gLngPostId + "", lStrSubject);

            List lLstHierarchy = (List) lMapHierarchyInfo.get("Result");
            Long lLngHeirRefId = null;

            if (lLstHierarchy != null && lLstHierarchy.size() > 0)
            {
                lLngHeirRefId = Long.parseLong(lLstHierarchy.get(0).toString());
            }

            gLogger.info("lObjBillMvmntVO.getBillNo() is : " + lObjBillMvmntVO.getBillNo());
            gLogger.info("lObjBillMvmntVO.getMvmntStatus() is : " + lObjBillMvmntVO.getMvmntStatus());

            inputMap.put("BillMovementVO", lObjBillMvmntVO);

            workFlowVO.setHirRefId(lLngHeirRefId);
            if (Long.parseLong(lStrToHierchyRefId) != -1)
            {
                workFlowVO.setToHirRefId(Long.parseLong(lStrToHierchyRefId));
            }

            workFlowVO.setToLevelId(Integer.parseInt(lStrToPostLvl[1]));

            gLogger.info("workFlowVO.getFromPost() is : " + workFlowVO.getFromPost());
            gLogger.info("workFlowVO.getToPost() is : " + workFlowVO.getToPost());
            gLogger.info("workFlowVO.getJobRefId() is : " + workFlowVO.getJobRefId());
            gLogger.info("workFlowVO.getToLevelId() is : " + workFlowVO.getToLevelId());
            gLogger.info("workFlowVO.getHirRefId() is : " + workFlowVO.getHirRefId());
            gLogger.info("workFlowVO.getToHirRefId() is : " + workFlowVO.getToHirRefId());

            WorkFlowUtility wfUtility = new WorkFlowUtility();
            wfUtility.invokeWorkFlow(workFlowVO);

            inputMap.put("billStatus", "saved");
            ResultObject lRsGetBill = serv.executeService("GET_MY_SAVED_BILLS", inputMap);

            lObjResult.setResultValue(lRsGetBill.getResultValue());
            lObjResult.setViewName(lRsGetBill.getViewName());
        }
        catch (Exception e)
        {
            gLogger.error("Error in execution of forwardOnlineBillFromWF. Error is : " + e, e);
        }
        return lObjResult;
    }


    /**
     * Returns the worklist for the logged in user
     * 
     * @param Map
     *            inputMap
     * @return ResultObject
     */

    public ResultObject getMySavedBills(Map lMapInput)
    {
        ResultObject lObjResult = new ResultObject(0, "FAIL");
        ServiceLocator serv = (ServiceLocator)lMapInput.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest)lMapInput.get("requestObj");
        List lLstSavedBills = null;
        String lStrCurrBillStatus = null;
        String lStrBillNo = null;
        List lLstDocByPost = null;
        com.tcs.sgv.wf.valueobject.WfDocMvmntMstVO lWfDocMvmntVO = null;
        String lStrMyBills = null;
        String TokenFlag = "";
        String month = "";
        String year = "";
        String billNo = "";
        
        try
        {
            setSessionInfo(lMapInput);
            OnlineBillDAO lObjOnlineBillDAO = new OnlineBillDAOImpl(serv.getSessionFactory());
            Map loginDetailsMap = (Map)(Map)lMapInput.get("baseLoginMap");
            long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString()).longValue();
            String lStrTxtSearch = request.getParameter("statusFlag");
            String lStrCmbSearch = request.getParameter("recFlag");
            TokenFlag = request.getParameter("TokenFlag") != null && !(request.getParameter("TokenFlag").equals(""))? request.getParameter("TokenFlag").toString():"";
            month = request.getParameter("month") != null && !(request.getParameter("month").equals(""))? request.getParameter("month").toString():"";
            year = request.getParameter("year") != null && !(request.getParameter("year").equals(""))? request.getParameter("year").toString():"";
            billNo = request.getParameter("billNo") != null && !(request.getParameter("billNo").equals(""))? request.getParameter("billNo").toString():"";
            String directViewFlag = request.getParameter("directBillView") != null && !(request.getParameter("directBillView").equals(""))? request.getParameter("directBillView").toString():"";        
            gLogger.info(":::::::::::::::  directBillView = " + directViewFlag);
             
         
            gLogger.info(":::::::::::::::::::::::::>>>>>>>>>> In getMySavedBills TokenFlag " + TokenFlag);
            gLogger.info(":::::::::::::::::::::::::>>>>>>>>>> in getmysavedbill month year and bill number : " + month + " " + year + " " + billNo);
            gLogger.info((new StringBuilder("lStrTxtSearch is:- ")).append(lStrTxtSearch).toString());
            
            gLogger.info((new StringBuilder("lStrTxtSearch is:-")).append(lStrTxtSearch).toString());
            gLogger.info((new StringBuilder("lStrCmbSearch is:-")).append(lStrCmbSearch).toString());
            gLogger.info((new StringBuilder("Bill Status is:- is:-")).append(request.getParameter("billStatus")).toString());
            lStrCurrBillStatus = OnlineBillUtil.requestSetter("billStatus", request);
            if(lStrCurrBillStatus.length() == 0)
                lStrCurrBillStatus = (String)lMapInput.get("billStatus");
            gLogger.info((new StringBuilder("Bill Status is:- is:-")).append(request.getParameter("billStatus")).toString());
            if(lStrCurrBillStatus.length() > 0 && lStrCurrBillStatus.equalsIgnoreCase(bundleConst.getString("STATUS.BillSentToTO")))
                lStrMyBills = getMyBillsSentToTreasury(lMapInput);
            else
                lStrMyBills = getMyBills(lMapInput);
            if(lStrCurrBillStatus.length() > 0)
                lStrCurrBillStatus.equalsIgnoreCase(bundleConst.getString("STATUS.BillRejected"));
            lMapInput.put("StrCurrBillStatus", lStrCurrBillStatus);
            lMapInput.put("StrMyBills", lStrMyBills.toString());
            lMapInput.put("gLngLangId", gLngLangId);
            lMapInput.put("StrTxtSearch", lStrTxtSearch);
            lMapInput.put("StrCmbSearch", lStrCmbSearch);
            lMapInput.put("locId", Long.valueOf(locId));
            lLstSavedBills = lObjOnlineBillDAO.getMySavedBills(lMapInput);
            lMapInput.put("MySavedBills", lLstSavedBills);
            lObjResult.setResultValue(lMapInput);
            if(TokenFlag != null && TokenFlag.equals("YES"))
            {
            	lMapInput.put("TokenFlag", TokenFlag);
            	lMapInput.put("Month", month);
            	lMapInput.put("Year", year);
            	lMapInput.put("billNo", billNo);
            	ResultObject resultObj = serv.executeService("ViewTokenNumber", lMapInput);
            	Map resultMap = (Map) resultObj.getResultValue();
            	lObjResult.setViewName("ViewTokenNumber");
            }
            else if(directViewFlag != null && directViewFlag.equals("YES"))
            {
            	lObjResult.setViewName("mySavedBills");
            }
            else
            {
            	ResultObject resultObj = serv.executeService("ViewTokenNumber", lMapInput);
            	Map resultMap = (Map) resultObj.getResultValue();
            	lObjResult.setViewName("ViewTokenNumber");
            }
        }
        catch(Exception e)
        {
            gLogger.error((new StringBuilder("Exception in getMySavedBills method of class TABillServiceImpl. Error is : ")).append(e).toString(), e);
        }
        return lObjResult;
    }

    //Sets session information in the global variables
    private void setSessionInfo(Map inputMap)
    {
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");

        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngEmpId = SessionHelper.getEmpId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(request);
        gStrLocId = SessionHelper.getLocationCode(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        gStrLocationCode = SessionHelper.getLocationCode(inputMap);
    }


    /**
     * Fetches the saved Data for editing/viewing the Bill
     * 
     * @param Map lMapInput
     * @return lObjResult ResultObject
     */
    public ResultObject getBillData(Map lMapInput)
    {
    	gLogger.info("getBillData called..Ankit");
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");

        String lStrBillSpecSrvcName = null;
        TrnBillRegister lObjTrnBillRegister = null;
        TrnBillBudheadDtls lObjTrnBudHdDtls = null;
        TrnBillRemarks lObjTrnBillRmrks = null;
        RltBillParty lObjRltBillParty = null;
        List<RltBillParty> lLstRltBillPartyVO = null;
        BptmCommonServicesDAOImpl lObjTrnBillRegisterDAO = null;
        BdgtHeadDtlsDAOImpl lObjTrnBudHdDtlsDAO = null;
        RltBillPartyDAOImpl lObjRltBillPartyDAO = null;
        OnlineBillDAO lObjOnlineBillDao = null;
        ShowBillDAOImpl lObjShowBillDaoImpl = null;
        String lStrCurrUsrRmrks = null;
        Object[] lObjArrPK = null;
        List lLstPK = null;
        ResultObject lResultObj = null;
        Map lMap = null;
        Map lResultMap = null;
        BigDecimal lLngDDOExp = null;
        BigDecimal lLngBalance = null;
        String lStrBillCntrNo = null;
        List lMonthList = null;
        String lStrPagePath = null;
        List rcptObjHds = null;
        List expObjHds = null;
        List recObjHds = null;
        List expEdpList = null;
        Long lLngBillType = null;
        String lStrSubjectId = null;
        String lStrBillNo = null;
        String lStrBillStatus = null;
        String lStrIsEditable = null;
        Long lLngSubjectId = null;
        Long lLngBillNo = null;
        Map lMapExpClsDtls = null;
        Map lMapFundDtls = null;
        Map lMapBudTypeDtls = null;
        Map lMapBillTypeDtls = null;
        Map lMapEmpTypeDtls = null;
        String lStrMonthName = null;
        String lStrIsNewFromRejected = null;
        String lStrChallanFlag = null;
        String BillType=null;
        try
        {
            setSessionInfo(lMapInput);

            lStrSubjectId = OnlineBillUtil.requestSetter("hidBillTypeId", request);
            lStrBillNo = OnlineBillUtil.requestSetter("billNo", request);
            lStrBillStatus = OnlineBillUtil.requestSetter("billStatus", request);
            lStrIsNewFromRejected = OnlineBillUtil.requestSetter("isNewFromRejected", request);
            BillType = OnlineBillUtil.requestSetter("BillType", request);
            if (lStrBillNo.length() == 0)
            {
                lStrBillNo = (String) lMapInput.get("jobId");
            }

            lMapInput.put("billNo", lStrBillNo);

            if (lStrBillNo != null && lStrBillNo.length() > 0)
                lLngBillNo = Long.parseLong(lStrBillNo);

            if (lStrSubjectId.length() > 0)
                lLngSubjectId = Long.parseLong(lStrSubjectId);

            // Once the bill is approved, it cannot be edited
            if (lStrBillStatus.length() > 0
                    && lStrBillStatus.equalsIgnoreCase(bundleConst.getString("STATUS.BillCreated")))
            {
                lStrIsEditable = "Y";
            }
            else if (lStrBillStatus.length() == 0)// print case
            {
                lStrIsEditable = null;
            }
            else
            {
                lStrIsEditable = "N";
            }

            // get Employee Details From Trn_onlinebill_emp
            TrnOnlinebillEmpDAO lObjDAO = new TrnOnlinebillEmpDAOImpl(TrnOnlinebillEmp.class, serv.getSessionFactory());
            String[] lStrBillNoArr = new String[1];
            lStrBillNoArr[0] = lStrBillNo;
            List lLstEmpDtlsVO = lObjDAO.getEmpDtlsByBillNo(lStrBillNoArr);
            // End 

            ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
            List lLstBillCode = BptmCommonServiceImpl.getLookupValues(lObjRsrcBndle.getString("CMN.BillCode"),
                    gLngLangId, lMapInput);

            lObjOnlineBillDao = new OnlineBillDAOImpl(serv.getSessionFactory());
            lLstPK = lObjOnlineBillDao.getPKForTable(lLngBillNo);

            if (lLstPK != null && lLstPK.size() > 0)
            {
                lObjArrPK = (Object[]) lLstPK.get(0);

                lObjTrnBillRegisterDAO = new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
                lObjTrnBillRegister = (TrnBillRegister) lObjTrnBillRegisterDAO.read(lLngBillNo);
                gLogger.info("TrnBillRegister after Read in getBillDataa" +  lObjTrnBillRegister);

                lObjTrnBudHdDtlsDAO = new BdgtHeadDtlsDAOImpl(TrnBillBudheadDtls.class, serv.getSessionFactory());
                lObjTrnBudHdDtls = (TrnBillBudheadDtls) lObjTrnBudHdDtlsDAO.read((Long) lObjArrPK[1]);
            }

            lObjTrnBillRmrks = lObjOnlineBillDao.getRmrksForCurrUser(lLngBillNo, gLngUserId);
            lLstRltBillPartyVO = lObjOnlineBillDao.getBillPartyRltInfo(lLngBillNo);

            if (lLngSubjectId != null)
            {
                lLngBillType = lLngSubjectId;
            }

            if (lLngBillType == null)
            {
                lLngBillType = lObjTrnBillRegister.getSubjectId();
            }

            lMapInput.put("subjectId", lLngBillType);
            lResultObj = serv.executeService("EDP_DETAILS", lMapInput);
            lMap = (Map) lResultObj.getResultValue();
            rcptObjHds = (List) lMap.get("rcptObjHds");
            expObjHds = (List) lMap.get("expObjHds");
            recObjHds = (List) lMap.get("recObjHds");
            expEdpList = (List) lMap.get("expEdpList");

            ConfigOnlineBill lObjConfigBill = ConfigOnlineBillUtil
                    .getInstance(lObjTrnBillRegister.getSubjectId(), serv);

            String lStrIsConfigStatus = lObjConfigBill.getIsConfigured();
            lStrPagePath = lObjConfigBill.getJspPath();
            lStrBillSpecSrvcName = lObjConfigBill.getFetchBillDataSrvc();

            lMapInput.put("LangId", gLngLangId);
            lResultObj = serv.executeService("GET_MONTH_DTLS", lMapInput);
            lMap = (Map) lResultObj.getResultValue();
            lResultMap = (Map) lMap.get("ResultMap");
            lMonthList = (List) lResultMap.get("MonthList");

            CmnLookupMstDAO lDAOCmnLkUpMst = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
            List lListFund = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang("Fund", gLngLangId);
            List lListClassOfExp = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang("ClassOfExp", gLngLangId);
            List lBudjetType = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang("OnlineBillBudgetType", gLngLangId);
            Object[] lObjTemp = lBudjetType.toArray();
            Object[] lObjBudjetType = new Object[lObjTemp.length];
            for (Object lObj : lObjTemp)
            {
                CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
                lObjBudjetType[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO;
            }
            lObjTemp = null;
            List lEmpType = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang("go_ngo", gLngLangId);
            List lBillType = lDAOCmnLkUpMst.getAllChildrenByLookUpNameAndLang("TcBillType", gLngLangId);

            if (lObjTrnBillRegister != null && lObjTrnBudHdDtls != null)
            {
                lMapInput.put("demandCode", lObjTrnBillRegister.getDemandCode());
                lMapInput.put("majorHead", lObjTrnBillRegister.getBudmjrHd());
                lMapInput.put("subMajorHead", lObjTrnBudHdDtls.getBudSubmjrHd());
                lMapInput.put("minorHead", lObjTrnBudHdDtls.getBudMinHd());
                lMapInput.put("subHead", lObjTrnBudHdDtls.getBudSubHd());
                lMapInput.put("budgetType", lObjTrnBudHdDtls.getBudType() + "");
                lMapInput.put("ddoCode", lObjTrnBillRegister.getDdoCode());
                //Added by Mrugesh for showing voucher no
                if(lObjTrnBillRegister.getTokenNum()!=null)
                	lMapInput.put("tokenNo", lObjTrnBillRegister.getTokenNum());
                if(lObjTrnBillRegister.getUpdatedDate()!=null)
                	lMapInput.put("tokenDate", lObjTrnBillRegister.getUpdatedDate());
                //Ended by Mrugesh

                if (lObjTrnBillRegister.getTcBill().equalsIgnoreCase("TC"))
                {
                    lMapInput.put("receiptIdOBPSaved", lObjTrnBillRegister.getReceiptId());

                    lStrChallanFlag = "Y";
                    lMapInput.put("challanFlag", lStrChallanFlag);

                    //System.out.println("\n ReceiptId is : " + lObjTrnBillRegister.getReceiptId() + "\n ReceiptId from map : " + lMapInput.get("receiptIdOBPSaved") + "\n");

                    gLogger.info("ReceiptId is : " + lObjTrnBillRegister.getReceiptId());
                    gLogger.info("");
                    gLogger.info("ReceiptId is : " + lMapInput.get("receiptIdOBPSaved"));

                    gLogger.info(" CHALLAN DETAILS LOADING STARTS ");

                    lResultObj = serv.executeService("LOAD_CHALLAN_DETAILS_OBPM", lMapInput);
                    lMapInput = (Map) lResultObj.getResultValue();

                    gLogger.info(" CHALLAN DETAILS LOADING ENDS ");

                }
                // added by jay for challan - END

                ResultObject lObjResult = serv.executeService("GET_DDO_EXP", lMapInput);

                lResultMap = (Map) lObjResult.getResultValue();
                lLngDDOExp = (BigDecimal) lResultMap.get("TotalDDOExp");
                BigDecimal lBDGrntAmt = lObjTrnBillRegister.getGrantAmount();
                BigDecimal lBDNetAmt = lObjTrnBillRegister.getBillNetAmount();

                if (lLngDDOExp != null && lBDNetAmt != null && lBDGrntAmt != null)
                {
                    lLngDDOExp = lLngDDOExp.add(lBDNetAmt);
                    lLngBalance = lBDGrntAmt.subtract(lLngDDOExp);
                }

                lStrBillCntrNo = lObjTrnBillRegister.getBillCntrlNo();

                BillCommonDAOImpl lObj = new BillCommonDAOImpl(serv.getSessionFactory());
                lStrMonthName = lObj.getMonthName(gLngLangId, lLngBillNo);

                lMapExpClsDtls = getCmnLookUpValueAndDescription(lDAOCmnLkUpMst, lObjTrnBudHdDtls.getClsExp());
                lMapFundDtls = getCmnLookUpValueAndDescription(lDAOCmnLkUpMst, lObjTrnBudHdDtls.getFund());
                lMapBudTypeDtls = getCmnLookUpValueAndDescription(lDAOCmnLkUpMst, lObjTrnBudHdDtls.getBudType());

                if (lObjTrnBillRegister.getTcBill() != null)
                {
                    lMapBillTypeDtls = getCmnLookUpValueAndDescription(lDAOCmnLkUpMst, lObjTrnBillRegister.getTcBill());
                }

                if (lObjTrnBillRegister.getGoNgo() != null && !lObjTrnBillRegister.getGoNgo().equals("-1"))
                {
                    lMapEmpTypeDtls = getCmnLookUpValueAndDescription(lDAOCmnLkUpMst, lObjTrnBillRegister.getGoNgo());
                }

                if (lObjTrnBillRegister.getScannedDocId() != null)
                {
                    CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv
                            .getSessionFactory());
                    CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(lObjTrnBillRegister
                            .getScannedDocId());
                    lMapInput.put("scan", cmnAttachmentMst);
                }
            }

            lObjShowBillDaoImpl = new ShowBillDAOImpl(TrnShowBill.class, serv.getSessionFactory());
            lStrCurrUsrRmrks = lObjShowBillDaoImpl.setLastRemarks(lLngBillNo.toString(), gLngUserId);

            // Objection
            TrnShowBill lObjTrnShowBill = new TrnShowBill();
            List lLstObj = lObjShowBillDaoImpl.getSelectedObj(lStrBillNo, gLngUserId);
            String lStrObj = "~";

            for (int i = 0; i < lLstObj.size(); i++)
            {
                lStrObj = lStrObj + "~" + lLstObj.get(i);
            }

            lObjTrnShowBill.setObjections(lStrObj);
            PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
            lObjTrnShowBill.setObjCount(lObjPhyBillDAOImpl.getObjectionsForUser("", Long.parseLong(lStrBillNo)));
            
            lMapInput.put("ShowBillVO", lObjTrnShowBill);
            // End of Objection
            
            
            //added by Ankit Bhatt for Displaying Data in Paybill Parameter page.
            
            lMapInput.put("trnBillNo",lObjTrnBillRegister.getBillNo());
            
		    ResultObject resultBill = serv.executeService("getSelectedHeads", lMapInput);
		    Map billMap = (Map)resultBill.getResultValue();
		    lMapInput.put("dmdCodeCode", billMap.get("demandNo"));
            lMapInput.put("mjHeadCode", billMap.get("mjrHead"));
            lMapInput.put("subMjrHeadCode", billMap.get("subMjrHead"));
            lMapInput.put("mnrHeadCode", billMap.get("mnrHead"));
            lMapInput.put("subHeadCode", billMap.get("subHead"));
            //lMapInput.put("ctrlNo", billMap.get("ctrlNo"));
            //Added by Mrugesh
            lMapInput.put("subHeadDesc", billMap.get("subHeadDesc"));
            lMapInput.put("mjrHeadDesc", billMap.get("mjrHeadDesc"));
            lMapInput.put("subMjrHeadDesc", billMap.get("subMjrHeadDesc"));
            lMapInput.put("mnrHeadDesc", billMap.get("mnrHeadDesc"));
            lMapInput.put("demandDesc", billMap.get("demandDesc"));
            lMapInput.put("billCtrlNo", billMap.get("billCtrlNo"));
            lMapInput.put("monthNo", billMap.get("monthNo"));
            lMapInput.put("yearSel", billMap.get("yearSel"));
            //Ended by Mrugesh
          //added by ravysh 
            lMapInput.put("BillTypeSupplOrMain", BillType);
            
            if( billMap.get("ctrlNo")!=null&& !billMap.get("ctrlNo").equals(""))
            lStrBillCntrNo=lStrBillCntrNo+"("+ billMap.get("ctrlNo")+")";
            //ended by Ankit Bhatt.

            lMapInput.put("EmpDtlsList", lLstEmpDtlsVO);
            lMapInput.put("TrnBillRegister", lObjTrnBillRegister);
            lMapInput.put("TrnBillBudheadDtls", lObjTrnBudHdDtls);
            lMapInput.put("TrnBillRemarks", lObjTrnBillRmrks);
            lMapInput.put("RltBillParty", lLstRltBillPartyVO);
            lMapInput.put("billStatus", lObjTrnBillRegister.getCurrBillStatus());
            lMapInput.put("currRemarks", lStrCurrUsrRmrks);
            lMapInput.put("BillNo", lLngBillNo);
            lMapInput.put("MonthList", lMonthList);
            lMapInput.put("PagePath", lStrPagePath);
            lMapInput.put("FundCombo", lListFund);
            lMapInput.put("ClassOfExpCombo", lListClassOfExp);
            lMapInput.put("BudTypeCombo", lObjBudjetType);
            lMapInput.put("TotalExpenditure", lLngDDOExp);
            lMapInput.put("AvailableBalance", lLngBalance);
            lMapInput.put("rcptObjHds", rcptObjHds);
            lMapInput.put("expObjHds", expObjHds);
            lMapInput.put("recObjHds", recObjHds);
            lMapInput.put("expEdpList", expEdpList);
            lMapInput.put("BillCntrNo", lStrBillCntrNo);
            lMapInput.put("EditBill", lStrIsEditable);
            lMapInput.put("MonthName", lStrMonthName);
            lMapInput.put("ClassOfExp", (String) lMapExpClsDtls.get("Label"));
            lMapInput.put("TypeOfBudget", (String) lMapBudTypeDtls.get("Label"));
            lMapInput.put("BillCodeList", lLstBillCode);
            lMapInput.put("Selected_ExpCls", lMapExpClsDtls);
            lMapInput.put("Selected_Fund", lMapFundDtls);
            lMapInput.put("Selected_BudType", lMapBudTypeDtls);
            lMapInput.put("Selected_BillType", lMapBillTypeDtls);
            lMapInput.put("Selected_EmpType", lMapEmpTypeDtls);
            lMapInput.put("BillTypeId", lLngBillType);
            lMapInput.put("BillType", lBillType);
            lMapInput.put("EmpType", lEmpType);
            lMapInput.put("isNewFromRejected", lStrIsNewFromRejected);
            lMapInput.put("SelReqId", OnlineBillUtil.requestSetter("hidSelRqstId", request));
            lMapInput.put("isConfigStatus", lStrIsConfigStatus);
            lMapInput.put("isFromChqPrep", OnlineBillUtil.requestSetter("isChq", request));

            if (lStrIsNewFromRejected.length() > 0)
            {
                lMapInput.put("prevBillNo", lStrBillNo);
            }
            else
            {
                lMapInput.put("prevBillNo", lObjTrnBillRegister.getPrevBillNo());
            }

            Object prevBillNo = (Object) lMapInput.get("prevBillNo");

            lMapInput.put("oldBillCntrlNo", prevBillNo != null ? lObjPhyBillDAOImpl.read(
                    Long.parseLong(prevBillNo.toString())).getBillCntrlNo() : null);

            // Calling bill specific method to get bill specific data
            ResultObject resultObj = serv.executeService(lStrBillSpecSrvcName, lMapInput);

            lMapInput.put("BillDtls", (Map) resultObj.getResultValue());
            Map lObjMap = getSelectedMonthDtls(lMapInput);

            request.getSession().setAttribute("VerifyMap", lMapInput);

            if (lStrIsEditable != null)
            {
                lMapInput = getSelectedMonthDtls(lMapInput);
                objRes.setViewName("viewSavedBill");
            }
            else
            {
                objRes.setViewName(lObjConfigBill.getGtrFormatView());
            }

            objRes.setResultValue(lMapInput);
        }
        catch (Exception e)
        {
            gLogger.error("Error in getBillData method of class OnlineBillServiceImpl.Error is : " + e, e);
        }

        return objRes;
    }


    /**
     * Method to get All the Bills with its details as per condition.
     * 
     * @param Map:inputMap
     * @return List
     * @author TCS
     */
    private String getMyBills(Map inputMap)
    {
    	StringBuffer lStrMyBills = new StringBuffer();
    	try
        {
    	 ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
         ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
         HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");

    	 PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
      	 List  trnBillRegisterList = lObjPhyBillDAOImpl.getBillData();
/*        WorkFlowVO workFlowVO = new WorkFlowVO();
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        Connection conn = serv.getSessionFactory().getCurrentSession().connection();
        String lStrBillNo = null;
        List lLstDocByPost = null;
        WfDocMvmntMstVO lWfDocMvmntVO = null;
        StringBuffer lStrMyBills = new StringBuffer();

        List list = new ArrayList();
        List resultLst = null;
        
            workFlowVO.setAppMap(inputMap);
            workFlowVO.setCrtEmpId(gLngEmpId.toString());
            workFlowVO.setCrtPost(gLngPostId.toString());
            workFlowVO.setCrtUsr(gLngUserId.toString());
            workFlowVO.setConnection(conn);
            OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);

            workFlowVO.setActId(100001);
            workFlowVO.setDocId(110001);

            workFlowVO.setJobRefId("1");
            workFlowVO.setLocID(gStrLocId);
            workFlowVO.setDbId(gLngDBId);

            // adding the posts
            list.add(gLngPostId.toString());

            resultLst = orgUtil.getDocList(list); // returns list of documents
            // based on post

            Iterator lItr = resultLst.iterator();

            lStrMyBills.append("(");

            while (lItr.hasNext())
            {
                lWfDocMvmntVO = (WfDocMvmntMstVO) lItr.next();
                lStrBillNo = lWfDocMvmntVO.getJobRefId();
                lStrMyBills.append("'" + lStrBillNo + "'");

                if (lItr.hasNext())
                {
                    lStrMyBills.append(",");
                }
            }*/
      	Iterator lItr = trnBillRegisterList.iterator();
      	Object[] lObjArrBillVO = null;
      	String lStrBillNo = null;
      	 lStrMyBills.append("(");
      	while (lItr.hasNext())
        {   
      		 lObjArrBillVO = (Object[]) lItr.next();
      		if(lObjArrBillVO!=null)
             lStrBillNo = String.valueOf(lObjArrBillVO[0]);

            lStrMyBills.append("'" + lStrBillNo + "'");

            if (lItr.hasNext())
            {
                lStrMyBills.append(",");
            }
        }
    	    
            lStrMyBills.append(")");

            gLogger.info(" Size of list of Doc Mvmnt Mst VOs is : " + trnBillRegisterList.size());

        }
        catch (Exception e)
        {
            gLogger.error("Error in getMyBills method of class OnlineBillServiceImpl.Error is : " + e, e);
        }

        return lStrMyBills.toString();
    }

    /**
     * Discards a bill
     * 
     * @param Map:inputMap
     * @return ResultObject
     * @author TCS
     */
    public ResultObject discardBill(Map lMapInput)
    {   gLogger.info("inside discardbill method");
        ResultObject lObjResult = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        System.out.println("requestrequestrequestrequest--->>>>>>>>>>" + request);
        //Long lLngBillNo = Long.parseLong(OnlineBillUtil.requestSetter("hidBillNo", request));
        String  billNo = "";
        String strbillnos = (String)(OnlineBillUtil.requestSetter("hidBillNo", request));
        String TokenFlag = (String)(OnlineBillUtil.requestSetter("TokenFlag", request));
        String month = (String)(OnlineBillUtil.requestSetter("month", request));
        System.out.println("monthmonthmonth---------->>>>>>>>" + month);
        String year = (String)(OnlineBillUtil.requestSetter("year", request));
        billNo = (String)(OnlineBillUtil.requestSetter("billNo", request));
   /*  String billcontrolno = (String) (OnlineBillUtil.requestSetter("billCntrlNo", request));
   
     
        System.out.println("billcontrolnobillcontrolnobillcontrolno" + billcontrolno);
        StringBuffer lsb = new StringBuffer(""); 
        for(int i=0; i<billcontrolno.length(); i++ )
        {
        	
        	if(Character.isLetter(billcontrolno.charAt(i)))
        	{
        		lsb.append(billcontrolno.charAt(i));
        		
        	}
        	
        }
         month= lsb.substring(0, lsb.length()-1);
        int Moonth = Integer.parseInt(month);
        System.out.println("MoonthMoonthMoonth" + Moonth);*/
      /*  String billno="";
        String Year="";
       for(int i=0; i<billcontrolno.length(); i++ )
        {
        	
    	   billno = (String) billcontrolno.subSequence(billcontrolno.indexOf("( ")+1,billcontrolno.lastIndexOf('('));
        	
    	   Year = (String) billcontrolno.subSequence(billcontrolno.indexOf(") "),billcontrolno.lastIndexOf(')'));
        }
       System.out.println("bbbinnobbbinnobbbinnobbbinno" + billno);
       System.out.println("YearYearYearYearYearYear   " + Year);
       
       
		  System.out.println("MONTHHHMONTHHHMONTHHHMONTHHH" + month);
		  int a[] = new int[12];
	       
	       if(month.equalsIgnoreCase("January"))    	  
	       		a[1] = 1;
	      
	       if(month.equalsIgnoreCase("February"))
	    	   a[2] = 2;
	       System.out.println("abccccccccccccc" + a[1]);
	       System.out.println("abccccccccccccc" + a[2]);
	       
	      if(month.equalsIgnoreCase("March"))
	    	   a[3] = 3;
	       if(month.equalsIgnoreCase("Aprial"))
	    	   a[4] = 4;
	       if(month.equalsIgnoreCase("May"))
	    	   a[5] = 5;
	       if(month.equalsIgnoreCase("June"))
	    	   a[6] = 6;
	       if(month.equalsIgnoreCase("July"))
	    	   a[7] = 7;
	       if(month.equalsIgnoreCase("August"))
	    	   a[8] = 8;
	       if(month.equalsIgnoreCase("September"))
	    	   a[9] = 9;
	       if(month.equalsIgnoreCase("October"))
	    	   a[10] = 10;
	       if(month.equalsIgnoreCase("November"))
	    	   a[11] = 11;
	       if(month.equalsIgnoreCase("December"))
	    	   a[12] = 12;
  */
      
      
    	   
     /*  Map hMap = new HashMap();
      hMap.put("month", a[1]);
      hMap.put("month", a[2]);
      hMap.put("month", a[3]);
      hMap.put("month", a[4]);
      hMap.put("month", a[5]);
      hMap.put("month", a[6]);
      hMap.put("month", a[7]);
      hMap.put("month", a[8]); ;
      hMap.put("month", a[9]);
      hMap.put("month", a[10]);
      hMap.put("month", a[11]); 
      hMap.put("month", a[12]);*/
      
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    	//CmnLookupMstDAO cmnLookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
       // List<CmnLookupMst> monthList =	cmnLookupDao.getListByColumnAndValue("LOOKUP_NAME", MONTHHH);
     /*  System.out.println("monthListmonthListmonthList" + monthList.size());
        if( monthList!=null &&  monthList.size()>0)
        {
        	for(Iterator monthIt=monthList.iterator();monthIt.hasNext();)
				{
        		  CmnLookupMst lookupMst = (CmnLookupMst)monthIt.next();
        		String  monthNumber = lookupMst.getLookupShortName();
        		System.out.println("monthNumbermonthNumbermonthNumber" +monthNumber);
				}
        }*/
        	//monthNumber=  monthList.get(0).getLookupShortName();
     
       
        
        
        
        gLogger.info(":::::::::::::::::::::::>>>>>>>>>>>>>>>>>>>>>>> TokenFlag : " + TokenFlag);
        gLogger.info(":::::::::::::::::::::::::::::::::::::::::::>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> in discardBill month year and bill number : " + month + " " + year + " " + billNo);
        StringTokenizer billTokens = new StringTokenizer(strbillnos,","); //prasenjit added this for multiple bill discard issue
		 long  lLngBillNo = 0;
		// long[] gradeIds = new long[billTokens.countTokens()];
System.out.println("monthmonthmonthmonthmonth1111111111111111111111111 " + month);
		 lMapInput.put("TokenFlag", TokenFlag);
		 lMapInput.put("month", month);
		 lMapInput.put("year", year);
		 lMapInput.put("billNo", billNo);
		 while(billTokens.hasMoreElements())
		 { 
			 String strbillno = billTokens.nextElement().toString();
			 gLogger.info(" token and element is " + strbillno);
		  lLngBillNo = Long.parseLong(strbillno);
			 
		 
        TrnBillRegister lObjTrnBillVO = new TrnBillRegister();
        RltBillRqst lObjRltBillVO = new RltBillRqst();
        CmnLookupMst lObjCmnLookupVO = null;

        RltBillRqstDAO lObjRltBillRqstDAO = new RltBillRqstDAOImpl(RltBillRqst.class, serv.getSessionFactory());
        CmnLookupMstDAO lObjCmnLookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
        BillRequestDAO lObjBillRqstDAO = new BillRequestDAOImpl(serv.getSessionFactory());

        List<RltBillRqst> lLstBillRqst = null;
        
        try
        {
            setSessionInfo(lMapInput);

            lObjCmnLookupVO = lObjCmnLookupDAO.getLookUpVOByLookUpNameAndLang(
                    bundleConst.getString("Lookup.DISACTIVE"), gLngLangId);

            BptmCommonServicesDAOImpl lObjBPTMVO = new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv
                    .getSessionFactory());

            lObjTrnBillVO = (TrnBillRegister) lObjBPTMVO.read(lLngBillNo);
          
            
            //by manoj for logical delete of paybill
             
            lMapInput.put("trnBillIds", lLngBillNo);
            lMapInput.put("trnBillIds", lLngBillNo);
            lMapInput.put("outer", 1);
            
             
            
            ResultObject resultBill=serv.executeService("deletePaybill", lMapInput);
            lMapInput = (Map)resultBill.getResultValue();
            //end by manoj for logical delete of paybill

            lObjTrnBillVO.setCurrBillStatus(bundleConst.getString("STATUS.BillDiscarded"));
            lObjTrnBillVO.setCurrBillStatusDate(new Date());

            lObjBPTMVO.update(lObjTrnBillVO);

            lLstBillRqst = lObjBillRqstDAO.getRqstInfoFrmBill(lLngBillNo);

            Iterator<RltBillRqst> lItrBillRqst = lLstBillRqst.iterator();

            while (lItrBillRqst.hasNext())
            {
                lObjRltBillVO = lItrBillRqst.next();
                lObjRltBillVO.setStatus(lObjCmnLookupVO.getLookupId());

                lObjRltBillRqstDAO.update(lObjRltBillVO);
            }
            
            lMapInput.put("billStatus", "saved");
            lMapInput.put("MESSAGECODE",300011);
            lObjResult = getMySavedBills(lMapInput);
            lObjResult.setResultValue(lMapInput);

        }
        catch (Exception e)
        {
            gLogger.error("Error in discardBill and Error is :" + e, e);
        }
		 }
        return lObjResult;
		 
    }
    

    private String get(String requestSetter) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
     * Saves the Digital Signatures for Common bill Details
     * 
     * @param Map lMapService
     * @return ResultObject
     */
    public void insertDigiSign(Map lMapService)
    {
        String lstrAppId = null;
        String lstrKey = null;
        String lstrfkVal = null;
        String lstrRowId = null;

        String lstrBillBudHeadDtlsPk = null;
        String lstrBillRegisterpk = null;
        String lStrRemarskPk = null;
        String lstrEdpDtlsPk = null;
        String lStrTrnEmpId = null;
        String lStrReceiptDetailId = null;
        String lStrRcptBudId = null;
        String lStrChallanId = null;

        Long lLngEdpId = 0L;
        Long lLngBillBudId = 0L;
        Long lLngBillNo = 0L;
        Long lLngRemarksId = 0L;
        Long lLngTrnEmpId = 0L;
        Long llngReceiptDetailId = 0L;
        Long lLngRcptBudId = 0L;
        Long lLngChallanId = 0L;
        
        int lIntSeq1 = 0;
        int lIntSeq2 = 0;
        int lIntSeq3 = 0;
        int lIntSeq7 = 0;
        int lIntSeq8 = 0;
        int lIntSeq9 = 0;
        int lIntSeq10 = 0;
        int lIntSeq11 = 0;
        int lIntRowNumConst = 0;
        int lIntRowNumIncr = 0;
        TrnReceiptDetails lObjTrnReceiptDetails = null;
        TrnRcptBudheadDtls lObjTrnRcptBudheadDtls = null;
        TrnChallanParty lObjTrnChallanParty = null;
        
        List<TrnRcptBudheadDtls> lLstTrnRcptBudheadDtls = new ArrayList<TrnRcptBudheadDtls>(); 
        List<TrnChallanParty> lLstTrnChallanParty = new ArrayList<TrnChallanParty>();

        try
        {

            ServiceLocator srvcLoc = (ServiceLocator) lMapService.get("serviceLocator");

            DigitalFrameworkUtil digiUtil = new DigitalFrameworkUtil();
            lstrKey = digiUtil.getKey();
            lIntSeq1 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq1"));
            lIntSeq2 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq2"));
            lIntSeq3 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq3"));
            lIntSeq7 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq7"));
            lIntSeq8 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq8"));
            lIntSeq9 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq9"));
            lIntSeq10 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq10"));
            lIntSeq11 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq11"));
            lIntRowNumConst = Integer.parseInt(bundleConst.getString("DIGI.DigiRowNumConst"));
            lIntRowNumIncr = Integer.parseInt(bundleConst.getString("DIGI.DigiRowNumIncr"));

            TrnBillBudheadDtls lObjBudHeadVO = (TrnBillBudheadDtls) lMapService.get("BillHeadVO");
            TrnBillRegister lObjBillReg = (TrnBillRegister) lMapService.get("BillRegVO");
            TrnBillRemarks lObjBillRmrks = (TrnBillRemarks) lMapService.get("BillRmrksVO");

            ConfigOnlineBill lObjConfigBill = ConfigOnlineBillUtil.getInstance(lObjBillReg.getSubjectId(), srvcLoc);

            BillCommonDAOImpl lobgBillsCmnDao = new BillCommonDAOImpl(srvcLoc.getSessionFactory());
            HttpServletRequest request = (HttpServletRequest) lMapService.get("requestObj");

            lLngBillBudId = lObjBudHeadVO.getBillBudId();
            lstrBillBudHeadDtlsPk = lLngBillBudId.toString();

            lLngBillNo = lObjBillReg.getBillNo();
            lstrBillRegisterpk = lLngBillNo.toString();

            lLngRemarksId = lObjBillRmrks.getRmrksId();
            lStrRemarskPk = lLngRemarksId.toString();
            lstrAppId = lObjConfigBill.getDigisigApplId();

            lLngTrnEmpId = (Long) lMapService.get("TrnEmpId");
            lStrTrnEmpId = String.valueOf(lLngTrnEmpId); 
            gLogger.info("This is before starting digital signature method");

            List<TrnBillEdpDtls> lLstPayVO = (List<TrnBillEdpDtls>) lMapService.get("payVOlist");
            List<TrnBillEdpDtls> lLstRecVO = (List<TrnBillEdpDtls>) lMapService.get("recVOlist");
            TrnBillEdpDtls lObjBillEDPVO = null;

            Iterator<TrnBillEdpDtls> lItrPay = lLstPayVO.iterator();
            while (lItrPay.hasNext())
            {
                lObjBillEDPVO = lItrPay.next();
                lLngEdpId = lObjBillEDPVO.getBillEdpId();
                lstrEdpDtlsPk = lLngEdpId.toString();
                int res = lobgBillsCmnDao.insertDigiMapping(lstrAppId, lIntSeq1, lstrEdpDtlsPk, lstrfkVal,
                        lIntRowNumIncr, lstrRowId, lstrKey);
                gLogger.info("insertion process at edp code" + res);
                lIntRowNumIncr++;
            }

            Iterator<TrnBillEdpDtls> lItrRec = lLstRecVO.iterator();
            while (lItrRec.hasNext())
            {
                lObjBillEDPVO = lItrRec.next();
                lLngEdpId = lObjBillEDPVO.getBillEdpId();
                lstrEdpDtlsPk = lLngEdpId.toString();
                int res = lobgBillsCmnDao.insertDigiMapping(lstrAppId, lIntSeq1, lstrEdpDtlsPk, lstrfkVal,
                        lIntRowNumIncr, lstrRowId, lstrKey);
                gLogger.info("insertion process at edp code" + res);
                lIntRowNumIncr++;
            }

            gLogger.info("This is at  after inserting edp details");

            int lBudHeadDtlsRes = lobgBillsCmnDao.insertDigiMapping(lstrAppId, lIntSeq2, lstrBillBudHeadDtlsPk,
                    lstrfkVal, lIntRowNumConst, lstrRowId, lstrKey);
            gLogger.info("This is at  after inserting budjethead details" + lBudHeadDtlsRes);

            int lBillRegisterRes = lobgBillsCmnDao.insertDigiMapping(lstrAppId, lIntSeq3, lstrBillRegisterpk,
                    lstrfkVal, lIntRowNumConst, lstrRowId, lstrKey);
            gLogger.info("This is at  after inserting billregister details" + lBillRegisterRes);

            lMapService.put("DigitalKey", lstrKey);
            lMapService.put("ApplicationId", lstrAppId);
            lMapService.put("BundleObjcet", bundleConst);
            ResultObject lRsDigiSigBill = srvcLoc.executeService(lObjConfigBill.getDigisigSaveSrvc(), lMapService);

            int lBillRemarksRes = lobgBillsCmnDao.insertDigiMapping(lstrAppId, lIntSeq7, lStrRemarskPk, lstrfkVal,
                    lIntRowNumConst, lstrRowId, lstrKey);

            int lEmpRes = lobgBillsCmnDao.insertDigiMapping(lstrAppId, lIntSeq8, lStrTrnEmpId, lstrfkVal,
                    lIntRowNumConst, lstrRowId, lstrKey);

            gLogger.info("This is at  after inserting billReamarks details" + lBillRemarksRes);
          
            String lStrIsTcBill = request.getParameter("cmbBillType");
           
            if (lObjBillReg.getTcBill().equalsIgnoreCase("TC"))
             {
                lObjTrnReceiptDetails = (TrnReceiptDetails)lMapService.get("trnReceiptDetailsVO");
                lLstTrnRcptBudheadDtls = (List<TrnRcptBudheadDtls>)lMapService.get("trnRcptBudheadDtlsVOLst");
                lLstTrnChallanParty = (List<TrnChallanParty>)lMapService.get("trnChallanPartyVOLst");
    
                gLogger.info("This is at before starting digig sign for challan details");
                
                llngReceiptDetailId = lObjTrnReceiptDetails.getReceiptDetailId();
                lStrReceiptDetailId = llngReceiptDetailId.toString();
                
                int lRcptRes = lobgBillsCmnDao.insertDigiMapping(lstrAppId, lIntSeq9, lStrReceiptDetailId, lstrfkVal,
                        lIntRowNumConst, lstrRowId, lstrKey);
                
                lIntRowNumIncr = Integer.parseInt(bundleConst.getString("DIGI.DigiRowNumIncr"));
                if(lLstTrnRcptBudheadDtls != null) // &&
                                                    // lLstTrnRcptBudheadDtls.size()
                                                    // > 0
                {
                    ListIterator<TrnRcptBudheadDtls> lItrBudHd = lLstTrnRcptBudheadDtls.listIterator();
                        
                    while(lItrBudHd.hasNext())
                    {
                        lObjTrnRcptBudheadDtls = (TrnRcptBudheadDtls)lItrBudHd.next();
                        lLngRcptBudId = lObjTrnRcptBudheadDtls.getRcptBudId();
                        lStrRcptBudId = lLngRcptBudId.toString();
                        int lIntBudRes =  lobgBillsCmnDao.insertDigiMapping(lstrAppId, lIntSeq10, lStrRcptBudId, lstrfkVal,
                                lIntRowNumIncr, lstrRowId, lstrKey);
                        lIntRowNumIncr++;
                    }
                }
                
                lIntRowNumIncr = Integer.parseInt(bundleConst.getString("DIGI.DigiRowNumIncr"));
                if(lLstTrnChallanParty != null)
                {
                    ListIterator<TrnChallanParty> lItrCp = lLstTrnChallanParty.listIterator();
                        
                    while(lItrCp.hasNext())
                    {
                        lObjTrnChallanParty = (TrnChallanParty)lItrCp.next();
                        lLngChallanId = lObjTrnChallanParty.getTrnChallanPartyId();
                        lStrChallanId = lLngChallanId.toString();
                        int lIntPartyRes =  lobgBillsCmnDao.insertDigiMapping(lstrAppId, lIntSeq11, lStrChallanId, lstrfkVal,
                                lIntRowNumIncr, lstrRowId, lstrKey);
                        lIntRowNumIncr++;
                    }
                }   
            }
            gLogger.info("lMapService b4 SaveDigiSign is : " + lMapService);

            String lStrDigiMethod = (String) lMapService.get("DIGIMETHOD");

            // if (lStrDigiMethod.equalsIgnoreCase("SAVE")) {

            gLogger.info("THIS IS AT INSERTION OPERATION:");
            String res = digiUtil.createDigTableData(request, lstrAppId);
            String res1 = digiUtil.insertDigitalData(lstrAppId);
            gLogger.info("Status of insertion operation :" + res + "********" + res1);
            // }
            /*
             * if (lStrDigiMethod.equalsIgnoreCase("EDIT")) { gLogger.info("THIS
             * IS AT UPDATION OPERATION:"); String resUpdate =
             * digiUtil.createDigTableData(request, lstrAppId); String
             * resupdate1 = digiUtil.updateSignatures(lstrAppId);
             * gLogger.info("Status of updation operation :" + resUpdate +
             * "********" + resupdate1); }
             */
        }

        catch (Exception e)
        {
            gLogger.error("Error in insertDigiSign. Error is : " + e, e);
        }
    }


    private Map getSelectedMonthDtls(Map lMapInput)
    {
        Map lMapBillDtls = (Map) lMapInput.get("BillDtls");
        Long lLngBillType = (Long) lMapInput.get("subjectId");
        String lStrMnthCode = null;
        String lStrYr = null;
        String lStrFinYrDesc = null;
        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
        BillCommonDAOImpl lObj = new BillCommonDAOImpl(serv.getSessionFactory());
        SimpleDateFormat lSdf = new SimpleDateFormat("yyyy");

        if (lLngBillType != null && lLngBillType.equals(new Long(bundleConst.getString("BillType.MEDICAL"))))
        {
            TrnMedblHdr lObjBillSpecVO = (TrnMedblHdr) lMapBillDtls.get("TrnMedblHdr");
            lStrMnthCode = lObjBillSpecVO.getMonthCode();

            lStrYr = lSdf.format(lObjBillSpecVO.getCreatedDate());
            SgvcFinYearMst lVO = lObj.getFinYrInfo(lObjBillSpecVO.getCreatedDate(), gLngLangId);
            lStrFinYrDesc = lVO.getFinYearDesc();
        }

        if (lLngBillType != null && lLngBillType.equals(new Long(bundleConst.getString("BillType.TA"))))
        {
            TrnTablHdr lObjBillSpecVO = (TrnTablHdr) lMapBillDtls.get("TrnTablHdr");
            lStrMnthCode = lObjBillSpecVO.getMonthCode();

            lStrYr = lSdf.format(lObjBillSpecVO.getCreatedDate());
            SgvcFinYearMst lVO = lObj.getFinYrInfo(lObjBillSpecVO.getCreatedDate(), gLngLangId);
            lStrFinYrDesc = lVO.getFinYearDesc();
        }

        String lStrMnthDesc = null;
        List lLstCmnVO = null;
        Iterator itr = null;
        CommonVO lObjCmnVO = null;
        Map lMapMonthDtls = null;

        lLstCmnVO = (List) lMapInput.get("MonthList");
        itr = lLstCmnVO.iterator();

        while (itr.hasNext())
        {
            lObjCmnVO = (CommonVO) itr.next();

            if (lObjCmnVO != null && lObjCmnVO.getCommonId().equalsIgnoreCase(lStrMnthCode))
            {
                lStrMnthDesc = lObjCmnVO.getCommonDesc();
                break;
            }
        }

        lMapMonthDtls = new HashMap();
        lMapMonthDtls.put("Value", lStrMnthCode);
        lMapMonthDtls.put("Label", lStrMnthDesc);

        lMapInput.put("Selected_Month", lMapMonthDtls);
        lMapInput.put("Selected_Year", lStrYr);
        lMapInput.put("Appropriation", lStrFinYrDesc);

        return lMapInput;
    }


    private Map getCmnLookUpValueAndDescription(CmnLookupMstDAO lDAOCmnLkUpMst, String lStrLookupName)
    {
        CmnLookupMst lStrCmnLookUpDescVO = lDAOCmnLkUpMst.getLookUpVOByLookUpNameAndLang(lStrLookupName, gLngLangId);
        String lStrCmnLookUpDesc = lStrCmnLookUpDescVO.getLookupDesc();
        String lStrCmnLookUpShrtName = lStrCmnLookUpDescVO.getLookupShortName();

        Map lMapValueAndDesc = new HashMap();
        lMapValueAndDesc.put("Value", lStrLookupName);
        lMapValueAndDesc.put("Desc", lStrCmnLookUpDesc);
        lMapValueAndDesc.put("Label", lStrCmnLookUpShrtName);

        return lMapValueAndDesc;
    }

    /**
     * Retrieves the treasury offices for the given DDO/DDO staff member
     * 
     * @param Map lMapInput
     * @return ResultObject
     */
    public ResultObject getTrsryOffices(Map lMapInput)
    {
        ResultObject lObjResult = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");

        Connection conn = serv.getSessionFactory().getCurrentSession().connection();

        String lStrSubject = bundleConst.getString("OnlineBillSubject");

        List lLstResult = null;
        List lLstDDOInfo = null;

        DDOInfoDAO lDDoInfo = (DDOInfoDAO) new DDOInfoDAOImpl(serv.getSessionFactory());
        OrgDdoMst lObjVO = null;

        WorkFlowVO lObjWrkFlw = new WorkFlowVO();
        lObjWrkFlw.setConnection(conn);

        Long lLngHeirRefId = null;

        try
        {
            setSessionInfo(lMapInput);

            OrgUtilityImpl orgUtil = new OrgUtilityImpl(lObjWrkFlw);
            Map lMapHierarchyInfo = orgUtil.getHierarchyByPostIDAndDescription(gLngPostId + "", lStrSubject);

            List lLstHierarchy = (List) lMapHierarchyInfo.get("Result");

            if (lLstHierarchy != null && lLstHierarchy.size() > 0)
            {
                lLngHeirRefId = Long.parseLong(lLstHierarchy.get(0).toString());
            }

            lLstDDOInfo = lDDoInfo.getDDOInfoByPost(gLngPostId, gLngLangId, gLngDBId);

            if (lLstDDOInfo != null && lLstDDOInfo.size() > 0)
            {
                lObjVO = (OrgDdoMst) lLstDDOInfo.get(0);
                lLstResult = lDDoInfo.getTrsryOfficeForDDO(lObjVO.getDdoCode(), gLngLangId);
            }
        }
        catch (Exception e)
        {
            gLogger.error("Error in getTrsryOffices and Error is : " + e, e);
        }

        if (lLstResult != null)
        {
            gLogger.info("Size of lLstResult in getTrsryOffices is : " + lLstResult.size());
        }
        else
        {
            gLogger.error("lLstResult is NULL in getTrsryOffices !!");
        }

        lMapInput.put("TrsryList", lLstResult);
        lMapInput.put("HierarchyId", lLngHeirRefId);

        lObjResult.setResultValue(lMapInput);
        lObjResult.setViewName("showTrsryList");

        return lObjResult;
    }


    private TrnBillMvmnt getWrkFlwBillMvmntVO(Map inputMap)
    {
        TrnBillMvmnt lObjBillMvmntVO = new TrnBillMvmnt();

        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

        String lStrBillNo = null;
        String lStrBillStatus = null;
        String lStrToPostId = null;
        String[] lStrToPostLvl = null;

        Long lLngReceiveUsrId = null;

        BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv
                .getSessionFactory());

        Long lLngBillMvmntId = null;

        try
        {
            lLngBillMvmntId = BptmCommonServiceImpl.getNextSeqNum("trn_bill_mvmnt", inputMap);
            lStrBillNo = OnlineBillUtil.requestSetter("hidBillNo", request);
            lStrBillStatus = OnlineBillUtil.requestSetter("CurrBillStatus", request);
            lStrToPostId = OnlineBillUtil.requestSetter("toPost", request);
            lStrToPostLvl = lStrToPostId.split("~");

            lLngReceiveUsrId = Long.parseLong(lObjCmnSrvcDAOImpl.getUserIdFromPost(lStrToPostLvl[0], gLngLangId));

            lObjBillMvmntVO.setBillMvmtId(lLngBillMvmntId);
            lObjBillMvmntVO.setBillNo(Long.parseLong(lStrBillNo));
            lObjBillMvmntVO.setStatusUpdtDate(new Date(System.currentTimeMillis()));
            lObjBillMvmntVO.setMvmntStatus(lStrBillStatus);
            lObjBillMvmntVO.setReceivedFlag(new Short("0"));
            lObjBillMvmntVO.setCreatedDate(new Date(System.currentTimeMillis()));
            lObjBillMvmntVO.setCreatedPostId(gLngPostId);
            lObjBillMvmntVO.setCreatedUserId(gLngUserId);
            lObjBillMvmntVO.setStatusUpdtUserid(lLngReceiveUsrId);
            lObjBillMvmntVO.setStatusUpdtPostid(Long.parseLong(lStrToPostLvl[0]));
            lObjBillMvmntVO.setLocationCode(gStrLocationCode);
            lObjBillMvmntVO.setDbId(gLngDBId);
        }
        catch (Exception e)
        {
            gLogger.error("Error in getWrkFlwBillMvmntVO. Error is : " + e, e);
        }

        return lObjBillMvmntVO;
    }

    /**
     * Saves data in DSS tables
     * 
     * @param Map lMapInput
     * @return ResultObject
     */
    public Map saveDSSData(Map lMapService)
    {
        Map lMapDSSOutput = null;
        Map lMapDSSValidation = null;
        Map lMapDSSInput = new HashMap();

        DssDataService lObjDSSDataService = new DssDataServiceImpl();

        boolean lBoolIsNewBill = (Boolean) lMapService.get("IsNewBill");
        Long lLngBillNO = (Long) lMapService.get("BillNo");
        RptExpenditureDtls lObjRptHdrVO = (RptExpenditureDtls) lMapService.get("DSSRptHdr");

        try
        {
            lObjRptHdrVO.setExpNo(new BigDecimal(lLngBillNO));
            lMapDSSValidation = lObjRptHdrVO.validateData();

            gLogger.info("Flag from the Validation is : " + lMapDSSValidation.get("flag"));
            gLogger.info("Status from the Validation is : " + lMapDSSValidation.get("Status"));

            lMapDSSInput.put("map", lMapService);
            lMapDSSInput.put("RptExpenditureVO", lObjRptHdrVO);

            if (lBoolIsNewBill)
            {
                lMapDSSOutput = lObjDSSDataService.insertExpData(lMapDSSInput);
            }
            else
            {
                lMapDSSOutput = lObjDSSDataService.updateExpData(lMapDSSInput);
            }

            gLogger.info("DSS Output is : " + lMapDSSOutput);
        }
        catch (Exception e)
        {
            gLogger.error("Error in saveDSSData. Error is : " + e, e);
        }
        return lMapDSSOutput;
    }


    private String getMyBillsSentToTreasury(Map lMapInput)
    {
        List lLstMyBills = null;
        StringBuffer lStrMyBills = new StringBuffer();
        String lStrBillNo = null;
        Object[] lObjArrBillVO = null;

        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
        OnlineBillDAO lObjOnlineBillDAO = new OnlineBillDAOImpl(serv.getSessionFactory());

        lLstMyBills = lObjOnlineBillDAO.getBillsSentToTO(gLngPostId);

        if (lLstMyBills != null)
        {
            Iterator lItr = lLstMyBills.iterator();

            lStrMyBills.append("(");

            while (lItr.hasNext())
            {
                lObjArrBillVO = (Object[]) lItr.next();
                lStrBillNo = ((Long) lObjArrBillVO[0]).toString();
                lStrMyBills.append("'" + lStrBillNo + "'");

                if (lItr.hasNext())
                {
                    lStrMyBills.append(",");
                }
            }
            lStrMyBills.append(")");
        }
        return lStrMyBills.toString();
    }


    /**
     * Veriffies the Digital Signatures for Common bill Details And Execute the
     * billspecific service to verify the Digital Signatures
     * 
     * @param Map lMapInput
     * @return ResultObject
     */
    public ResultObject verifyDigiSign(Map lMapArg)
    {
        String lStrAppId = null;

        TrnBillBudheadDtls lObjTrnBudHdDtls = null;
        TrnBillRemarks lObjTrnBillRmrks = null;
        TrnBillRegister lObjTrnBillRegister = null;
        TrnBillEdpDtls lObjBillEDPVO = null;

        BptmCommonServicesDAOImpl lObjTrnBillRegisterDAO = null;
        BdgtHeadDtlsDAOImpl lObjTrnBudHdDtlsDAO = null;
        RemarksDAOImpl lObjTrnBillRmrksDAO = null;
        BillCommonDAOImpl lObjBillCmnDAO = null;

        Object[] lObjArrPK = null;

        Long billBudId;
        Long rmrksId = null;
        Long lLngBillNo;
        Long lLngEmpId = 0L;

        List lLstPK;

        int lIntSeq1;
        int lIntSeq2;
        int lIntSeq3;
        int lIntSeq7;
        int lIntSeq8;
        int lIntSeq9;
        int lIntSeq10;
        int lIntSeq11;
        int lIntRowNumConst;
        int lIntRowNumIncr;

        String lStrFkVal = null;
        String lStrBillNo = null;
        String lStrRowId = null;
        String lStrDigiKey = null;
        String lStrBillBudId = null;
        String lStrRemarksId = null;
        String lStrEmpId = null;
        String lStrBillCntrNo = null;
        Long lLngEdpId = null;
        Long llngReceiptDetailId = 0L;
        Long lLngRcptBudId = 0L;
        Long lLngChallanId = 0L;
        
        String lLstrEdpId = null;
        String lStrReceiptDetailId = null;
        String lStrRcptBudId = null;
        String lStrChallanId = null;
        List lLstEdpid = null;
        Map lMapRes = new HashMap();
        Enumeration lObjEnu = null;
        Map lMapResMap = new HashMap();
        List expObjHds = null;
        List recObjHds = null;
        TrnOnlinebillEmp lObjEmpDtls = null;
        List lMapTampered = new ArrayList();
        ResultObject lObjResult = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        TrnReceiptDetails lObjTrnReceiptDetails = null;
        TrnRcptBudheadDtls lObjTrnRcptBudheadDtls = null;
        TrnChallanParty lObjTrnChallanParty = null;
        
        List<TrnRcptBudheadDtls> lLstTrnRcptBudheadDtls = new ArrayList<TrnRcptBudheadDtls>(); 
        List<TrnChallanParty> lLstTrnChallanParty = new ArrayList<TrnChallanParty>();
        try
        {
            ServiceLocator serv = (ServiceLocator) lMapArg.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) lMapArg.get("requestObj");

            Map lMapInput = (Map) request.getSession().getAttribute("VerifyMap");

            lStrBillCntrNo = (String) lMapInput.get("BillCntrNo");
            DigitalFrameworkUtil digiUtil = new DigitalFrameworkUtil();
            TrnBillRegister lObjBillReg = (TrnBillRegister) lMapInput.get("TrnBillRegister");

            ConfigOnlineBill lObjConfigBill = ConfigOnlineBillUtil.getInstance(lObjBillReg.getSubjectId(), serv);

            lStrAppId = lObjConfigBill.getDigisigApplId();
            lStrDigiKey = digiUtil.getKey().toString();

            lIntSeq1 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq1"));
            lIntSeq2 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq2"));
            lIntSeq3 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq3"));
            lIntSeq7 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq7"));
            lIntSeq8 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq8"));
            lIntSeq9 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq9"));
            lIntSeq10 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq10"));
            lIntSeq11 = Integer.parseInt(bundleConst.getString("DIGI.DigiSeq11"));

            lIntRowNumConst = Integer.parseInt(bundleConst.getString("DIGI.DigiRowNumConst"));
            lIntRowNumIncr = Integer.parseInt(bundleConst.getString("DIGI.DigiRowNumIncr"));

            OnlineBillDAOImpl lObjOnlineBillDao = null;
            lObjOnlineBillDao = new OnlineBillDAOImpl(serv.getSessionFactory());
            lObjBillCmnDAO = new BillCommonDAOImpl(serv.getSessionFactory());

            // lStrSubjectId = requestSetter("hidBillTypeId", request);
            // lStrBillNo = OnlineBillUtil.requestSetter("billNo", request);
            // lLngBillNo = Long.parseLong(lStrBillNo);

            lLngBillNo = lObjBillReg.getBillNo();
            lStrBillNo = lLngBillNo.toString();

            // Getting all pk's for inserting in to sgva_Mapping_key_temp table

            lLstPK = lObjOnlineBillDao.getPKForTable(lLngBillNo);

            if (lLstPK != null && lLstPK.size() > 0)
            {
                lObjArrPK = (Object[]) lLstPK.get(0);

                lObjTrnBillRegisterDAO = new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
                lObjTrnBillRegister = (TrnBillRegister) lObjTrnBillRegisterDAO.read(lLngBillNo);

                lObjTrnBudHdDtlsDAO = new BdgtHeadDtlsDAOImpl(TrnBillBudheadDtls.class, serv.getSessionFactory());
                lObjTrnBudHdDtls = (TrnBillBudheadDtls) lObjTrnBudHdDtlsDAO.read((Long) lObjArrPK[1]);
            }

            lLstEdpid = lObjBillCmnDAO.getBillEdpId(lLngBillNo);
            Iterator litrlist = lLstEdpid.iterator();
            while (litrlist.hasNext())
            {
                lLngEdpId = (Long) litrlist.next();
                lLstrEdpId = lLngEdpId.toString();
                int lIntEdpRes = lObjBillCmnDAO.insertDigiMapping(lStrAppId, lIntSeq1, lLstrEdpId, lStrFkVal,
                        lIntRowNumIncr, lStrRowId, lStrDigiKey);
                gLogger.info("Result for saving edpdtls in to SgvaMappingkeytemp" + lIntEdpRes);
                lIntRowNumIncr = lIntRowNumIncr + 1;
            }

            if (lObjTrnBillRmrks != null)
            {
                lObjTrnBillRmrks = (TrnBillRemarks) lMapInput.get("TrnBillRemarks");
                rmrksId = lObjTrnBillRmrks.getRmrksId();
                lStrRemarksId = rmrksId.toString();
            }
            billBudId = lObjTrnBudHdDtls.getBillBudId();
            lStrBillBudId = billBudId.toString();
            int lIntBudres = lObjBillCmnDAO.insertDigiMapping(lStrAppId, lIntSeq2, lStrBillBudId, lStrFkVal,
                    lIntRowNumConst, lStrRowId, lStrDigiKey);

            int lIntBillRegres = lObjBillCmnDAO.insertDigiMapping(lStrAppId, lIntSeq3, lStrBillNo, lStrFkVal,
                    lIntRowNumConst, lStrRowId, lStrDigiKey);

            lMapInput.put("DigiKey", lStrDigiKey);
            lMapInput.put("ApplicationId", lStrAppId);
            lMapInput.put("BundleObjcet", bundleConst);
            ResultObject lMapDigiSigVerify = serv.executeService(lObjConfigBill.getDigisigVrfySrvc(), lMapInput);

            if (rmrksId != null)
            {
                int lIntRemarksres = lObjBillCmnDAO.insertDigiMapping(lStrAppId, lIntSeq7, lStrRemarksId, lStrFkVal,
                        lIntRowNumConst, lStrRowId, lStrDigiKey);
            }

            List<TrnOnlinebillEmp> lLstEmpDtls = (List<TrnOnlinebillEmp>) lMapInput.get("EmpDtlsList");

            Iterator<TrnOnlinebillEmp> lItrEmpDtls = lLstEmpDtls.iterator();
            lIntRowNumIncr = Integer.parseInt(bundleConst.getString("DIGI.DigiRowNumIncr"));
            while (lItrEmpDtls.hasNext())
            {
                lObjEmpDtls = lItrEmpDtls.next();
                lLngEmpId = lObjEmpDtls.getTrnOnlinebillEmpId();
                lStrEmpId = String.valueOf(lLngEmpId);               
                int res = lObjBillCmnDAO.insertDigiMapping(lStrAppId, lIntSeq8, lStrEmpId, lStrFkVal, lIntRowNumIncr,
                        lStrRowId, lStrDigiKey);
                gLogger.info("insertion process at EMP" + res);
                lIntRowNumIncr++;
            }
            if(lObjBillReg.getTcBill().equalsIgnoreCase("TC"))
            {
                lObjTrnReceiptDetails = (TrnReceiptDetails) lMapInput.get("challanReceiptVO");
                lLstTrnRcptBudheadDtls =  (List<TrnRcptBudheadDtls>) lMapInput.get("challanBudhdVOLst");
                lLstTrnChallanParty = (List<TrnChallanParty>) lMapInput.get("challanPartyLst");   
                gLogger.info("This is at before starting digig sign for challan details");
                
                llngReceiptDetailId = lObjTrnReceiptDetails.getReceiptDetailId();
                lStrReceiptDetailId = llngReceiptDetailId.toString();
                
                int lRcptRes = lObjBillCmnDAO.insertDigiMapping(lStrAppId, lIntSeq9, lStrReceiptDetailId, lStrFkVal,
                        lIntRowNumConst, lStrRowId, lStrDigiKey);
                
                lIntRowNumIncr = Integer.parseInt(bundleConst.getString("DIGI.DigiRowNumIncr"));
                if(lLstTrnRcptBudheadDtls != null)
                {
                    ListIterator<TrnRcptBudheadDtls> lItrBudHd = lLstTrnRcptBudheadDtls.listIterator();
                        
                    while(lItrBudHd.hasNext())
                    {
                        lObjTrnRcptBudheadDtls = (TrnRcptBudheadDtls)lItrBudHd.next();
                        lLngRcptBudId = lObjTrnRcptBudheadDtls.getRcptBudId();
                        lStrRcptBudId = lLngRcptBudId.toString();
                        int lIntBudRes =  lObjBillCmnDAO.insertDigiMapping(lStrAppId, lIntSeq10, lStrRcptBudId, lStrFkVal,
                                lIntRowNumIncr, lStrRowId, lStrDigiKey);
                        lIntRowNumIncr++;
                    }
                }
                
                lIntRowNumIncr = Integer.parseInt(bundleConst.getString("DIGI.DigiRowNumIncr"));
                if(lLstTrnChallanParty != null)
                {
                    ListIterator<TrnChallanParty> lItrCp = lLstTrnChallanParty.listIterator();
                        
                    while(lItrCp.hasNext())
                    {
                        lObjTrnChallanParty = (TrnChallanParty)lItrCp.next();
                        lLngChallanId = lObjTrnChallanParty.getTrnChallanPartyId();
                        lStrChallanId = lLngChallanId.toString();
                        int lIntPartyRes =  lObjBillCmnDAO.insertDigiMapping(lStrAppId, lIntSeq11, lStrChallanId, lStrFkVal,
                                lIntRowNumIncr, lStrRowId, lStrDigiKey);
                        lIntRowNumIncr++;
                    }
                }   
               
            }
            String res = digiUtil.createDigTableData(lStrAppId);
            Hashtable hashVerification = digiUtil.getVerificationStatus(lStrAppId);
            //Hashtable hashVerification = digiUtil.getSignerDetails(lStrAppId);
           
            ArrayList lObjArr = new ArrayList();

            lObjEnu = hashVerification.keys();

            lMapInput.put("BillCntrNo", lStrBillCntrNo);
            gLogger.info("Bill Control number is" + lStrBillCntrNo);
            while (lObjEnu.hasMoreElements())
            {
                String str = (String) lObjEnu.nextElement();
                gLogger.info("****" + str + "**" + hashVerification.get(str));
                if (hashVerification.get(str).equals("true"))
                {
                    int j = str.length();
                    str = str.substring(0, j - 1);
                    String lStrConst = verifybundleConst.getString(str);
                    if (lMapTampered.contains(lStrConst) != true)
                    {
                        lMapTampered.add(lStrConst);
                    }
                    gLogger.info("Verification Map value is  :" + str);
                }
            }
            if(lMapTampered.size()>0)
            {
                lMapInput.put("Tampered", "YES");
                lMapInput.put("DigiVerificationResList",lMapTampered);
                
            }
            else
            {
                lMapInput.put("Tampered", "NO");
            }
            
            request.getSession().setAttribute("hashSigned",hashVerification);
            lObjResult.setResultValue(lMapInput);
            lObjResult.setViewName("Verification");

        }
        catch (Exception e)
        {
            gLogger.error("Error in verifyDigiSign method of OnlineBillServiceImpl Class" + e);
        }
        return lObjResult;

    }

    /**
     * Saves Challan Information for Online Bill
     * 
     * @param Map lMapInput
     * @return ResultObject
     */
    public ResultObject saveChallan(Map lMapInput)
    {
        gLogger.info("Inside saveChallan() of OnlineBillServiceImpl : start");
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");

        TrnReceiptDetails lObjTrnReceiptDetails = null;
        TrnRcptBudheadDtls lObjTrnRcptBudheadDtls = null;
        TrnChallanParty lObjTrnChallanParty = null;

        List<TrnRcptBudheadDtls> lLstTrnRcptBudheadDtls = new ArrayList<TrnRcptBudheadDtls>();
        List<TrnChallanParty> lLstTrnChallanParty = new ArrayList<TrnChallanParty>();

        try
        {
            TrnBillRegister lObjBillReg = (TrnBillRegister) lMapInput.get("BillRegVO");
            lObjTrnReceiptDetails = (TrnReceiptDetails) lMapInput.get("trnReceiptDetailsVO");
            lLstTrnRcptBudheadDtls = (List<TrnRcptBudheadDtls>) lMapInput.get("trnRcptBudheadDtlsVOLst");
            lLstTrnChallanParty = (List<TrnChallanParty>) lMapInput.get("trnChallanPartyVOLst");

            if (saveReceiptDtls(lObjTrnReceiptDetails, lMapInput))
            {
                gLogger.info(" Record successfully saved in TrnReceiptDetails ");

                // Following Logic is used to delete records that are inserted
                // earlier
                // when RcptBudHdDtls is Updated - start
                RcptBudDtlsDAOImpl rbdDAO = new RcptBudDtlsDAOImpl(TrnRcptBudheadDtls.class, serv.getSessionFactory());

                OnlineBillDAOImpl oDAO = new OnlineBillDAOImpl(serv.getSessionFactory());
                List lLstPK = oDAO.getPKToUpdateBudHd(lObjBillReg.getBillNo());

                if (lLstPK != null && lLstPK.size() > 0)
                {
                    ListIterator<Object[]> lItrPK = lLstPK.listIterator();
                    Object[] lObjArrRow = null;

                    while (lItrPK.hasNext())
                    {
                        lObjArrRow = (Object[]) lItrPK.next();
                        lObjTrnRcptBudheadDtls = rbdDAO.read((Long) lObjArrRow[1]);
                        rbdDAO.delete(lObjTrnRcptBudheadDtls);
                    }
                }
                // End of deletion

                // Now insert into RcptBudHdDtls - start
                if (lLstTrnRcptBudheadDtls != null) 
                {
                    ListIterator<TrnRcptBudheadDtls> lItrBudHd = lLstTrnRcptBudheadDtls.listIterator();

                    while (lItrBudHd.hasNext())
                    {
                        lObjTrnRcptBudheadDtls = (TrnRcptBudheadDtls) lItrBudHd.next();                        
                        lObjTrnRcptBudheadDtls.setReceiptDetailId(lObjTrnReceiptDetails.getReceiptDetailId());
                        lObjTrnRcptBudheadDtls.setBudMajorHead(lObjTrnReceiptDetails.getMajorHead());
                        saveRcptBudHdDtls(lObjTrnRcptBudheadDtls, lMapInput);
                    }
                }
                // End of Insertion

                // Following Logic is used to delete records that are inserted earlier
                // when trn_challan_party is Updated - start
                TrnChallanPartyDAOImpl lObjChallanPartyDAO = new TrnChallanPartyDAOImpl(TrnChallanParty.class, serv
                        .getSessionFactory());
                List lLstPKChallan = oDAO.getPKToUpdateChallanParty(lObjBillReg.getBillNo());

                if (lLstPKChallan != null && lLstPKChallan.size() > 0)
                {
                    Object[] lObjArr = null;
                    ListIterator<Object[]> lItrPK = lLstPKChallan.listIterator();

                    while (lItrPK.hasNext())
                    {
                        lObjArr = (Object[]) lItrPK.next();
                        lObjTrnChallanParty = (TrnChallanParty) lObjChallanPartyDAO.read((Long) lObjArr[1]);
                        lObjChallanPartyDAO.delete(lObjTrnChallanParty);
                    }
                }
                // End of Deletion
                // Now insert into trn_challan_party - start
                if (lLstTrnChallanParty != null)
                {
                    ListIterator<TrnChallanParty> lItrCp = lLstTrnChallanParty.listIterator();

                    while (lItrCp.hasNext())
                    {
                        lObjTrnChallanParty = (TrnChallanParty) lItrCp.next();
                        lObjTrnChallanParty.setChallanId(lObjTrnReceiptDetails.getReceiptDetailId());
                        saveChallanParty(lObjTrnChallanParty, lMapInput);
                    }
                }
                // End of insertion
            }
            else
            {
                gLogger.info(" Record not saved in TrnReceiptDetails ");
            }                

            lMapInput.put("TrnReceiptDetailsVO", lObjTrnReceiptDetails);
            lMapInput.put("TrnRcptBudheadDtlsVOLst", lLstTrnRcptBudheadDtls);
            lMapInput.put("TrnChallanPartyVOLst", lLstTrnChallanParty);

            objRes.setResultValue(lMapInput);
        }
        catch (Exception e)
        {
            gLogger.error("Error inside saveChallan() of OnlineBillServiceImpl : ", e);
        }

        gLogger.info("Inside saveChallan() of OnlineBillServiceImpl : end");

        return objRes;
    }

    // save record into trn_receipt_details
    private boolean saveReceiptDtls(TrnReceiptDetails lObjTrnReceiptDetails, Map lMapInput)
    {
        gLogger.info("Inside saveReceiptDtls() of OnlineBillServiceImpl : start");

        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
        TrnRcptDtlsDAOImpl rDAO = new TrnRcptDtlsDAOImpl(TrnReceiptDetails.class, serv.getSessionFactory());
        Long lLngReceiptDetailId = null;

        try
        {
            if (lObjTrnReceiptDetails.getReceiptDetailId() > 0)
            {
                rDAO.update(lObjTrnReceiptDetails);
            }
            else
            {
                lLngReceiptDetailId = BptmCommonServiceImpl.getNextSeqNum("trn_receipt_details", lMapInput);
                lObjTrnReceiptDetails.setReceiptDetailId(lLngReceiptDetailId);
                rDAO.create(lObjTrnReceiptDetails);
            }

            lMapInput.put("challanRcptId", lObjTrnReceiptDetails.getReceiptDetailId());
            lMapInput.put("challanMajorHd", lObjTrnReceiptDetails.getMajorHead());
        }
        catch (Exception e)
        {
            gLogger.error("Error inside saveReceiptDtls() of OnlineBillServiceImpl : ", e);
            return false;
        }

        gLogger.info("Inside saveReceiptDtls() of OnlineBillServiceImpl : end");
        return true;
    }

    // save record in trn_rcpt_budhd_dtls
    private boolean saveRcptBudHdDtls(TrnRcptBudheadDtls lObjTrnRcptBudheadDtls, Map lMapInput)
    {
        gLogger.info("Inside saveRcptBudHdDtls() of OnlineBillServiceImpl : start");

        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
        RcptBudDtlsDAOImpl rbdDAO = new RcptBudDtlsDAOImpl(TrnRcptBudheadDtls.class, serv.getSessionFactory());
        Long lLngRcptBudHdId = null;
        try
        {
            lLngRcptBudHdId = BptmCommonServiceImpl.getNextSeqNum("trn_rcpt_budhead_dtls", lMapInput);
            lObjTrnRcptBudheadDtls.setRcptBudId(lLngRcptBudHdId);
            rbdDAO.create(lObjTrnRcptBudheadDtls);
        }
        catch (Exception e)
        {
            gLogger.error("Error saveRcptBudHdDtls() of OnlineBillServiceImpl : ", e);
        }

        gLogger.info("Inside saveRcptBudHdDtls() of OnlineBillServiceImpl : end");
        return true;
    }

    // save record in trn_challan_party
    private boolean saveChallanParty(TrnChallanParty lObjTrnChallanParty, Map lMapInput)
    {
        gLogger.info("Inside saveChallanParty() 0f OnlineBillServiceImpl : start");

        Long lLngChallanPartyId = null;
        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
        TrnChallanPartyDAOImpl lObjCpDAO = new TrnChallanPartyDAOImpl(TrnChallanParty.class, serv.getSessionFactory());

        try
        {
            lLngChallanPartyId = BptmCommonServiceImpl.getNextSeqNum("trn_challan_party", lMapInput);
            lObjTrnChallanParty.setTrnChallanPartyId(lLngChallanPartyId);
            lObjCpDAO.create(lObjTrnChallanParty);
        }
        catch (Exception e)
        {
            gLogger.error("Error inside saveChallanParty() 0f OnlineBillServiceImpl : ", e);
        }
        gLogger.info("Inside saveChallanParty() 0f OnlineBillServiceImpl : end");
        return true;
    }

    /**
     * Fetches the saved challan details for TC bill
     * 
     * @param Map lMapInput
     * @returns ResultObject
     */
    public ResultObject loadChallanDetails(Map lMapInput)
    {
        gLogger.info("Inside loadChallanDetails() 0f OnlineBillServiceImpl : start");

        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");

        Long lLngReceiptId = null;
        OnlineBillDAOImpl obDAO = new OnlineBillDAOImpl(serv.getSessionFactory());

        try
        {
            lLngReceiptId = Long.parseLong(lMapInput.get("receiptIdOBPSaved").toString());
            lMapInput = obDAO.getChallanData(lLngReceiptId, lMapInput);
        }
        catch (Exception e)
        {
            gLogger.error("Error inside loadChallanDetails() 0f OnlineBillServiceImpl ", e);
        }

        objRes.setResultValue(lMapInput);
        gLogger.info("Inside loadChallanDetails() 0f OnlineBillServiceImpl : end");

        return objRes;
    }
}

 class TempException extends Exception
{
	TempException(){
	super();}

}