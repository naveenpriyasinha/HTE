package com.tcs.sgv.exprcpt.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.apps.dao.budget.BudHdDAOImpl;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.constant.MessageConstant;
import com.tcs.sgv.common.constant.WorkFlowConstants;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.ChallanMovementDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.OrganizationDAOImpl;
import com.tcs.sgv.common.dao.PostConfigurationDAOImpl;
import com.tcs.sgv.common.dao.WorkFlowDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.helper.WorkFlowHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.ConfigAudSelection;
import com.tcs.sgv.common.valueobject.MstBank;
import com.tcs.sgv.common.valueobject.RltBankBranch;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnRcptBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptExpEdpDtls;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;
import com.tcs.sgv.exprcpt.dao.CommonReceiptDAOImpl;
import com.tcs.sgv.exprcpt.dao.RcptBudDtlsDAOImpl;
import com.tcs.sgv.exprcpt.dao.ReceiptDAOImpl;
import com.tcs.sgv.exprcpt.dao.TrnRcptMvmntDAOImpl;
import com.tcs.sgv.exprcpt.dao.VerifiedAccountDAOImpl;
import com.tcs.sgv.exprcpt.dao.WebChallanDtlsDAO;
import com.tcs.sgv.exprcpt.dao.WebPurposeDtlsDAO;
import com.tcs.sgv.exprcpt.valueobject.DistChallanVO;
import com.tcs.sgv.exprcpt.valueobject.TrnBsRcptEntries;
import com.tcs.sgv.exprcpt.valueobject.TrnRcptMvmnt;
import com.tcs.sgv.exprcpt.valueobject.TrnVerifiedAccount;
import com.tcs.sgv.exprcpt.valueobject.TrnVoucherDetails;
import com.tcs.sgv.exprcpt.valueobject.WebChallanDetails;
import com.tcs.sgv.exprcpt.valueobject.WebPurposeDetails;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.util.WorkFlowUtility;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;
 
/**
*Class Name  ReceiptServiceImpl
*Description Implementation of Receipt Service  class
*Date of Creation 26 Jun 2006
*@Author Jignesh
*-----------------------------------------------------------------------------
* DATE        AUTHOR                    DESCRIPTION
* 27-jun-2007 Vimal            Challan detail posting & dss integration
* 22-Oct-2007 Vimal            Subtresury Challan Detail posting
*-----------------------------------------------------------------------------
*/

public class ReceiptServiceImpl extends ServiceImpl  
{

    Log logger = LogFactory.getLog(getClass());
    
    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getReceipts(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }
            HttpSession session=request.getSession();
            List<String> postLst = new ArrayList<String>();
            postLst.add(SessionHelper.getPostId(objectArgs).toString());
            objectArgs.put("postLst", postLst);
            List rcptList =WorkFlowHelper.getChallansFromWorkFlow(objectArgs);

            ReceiptDAOImpl receiptImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());

            List receiptList = null;
            List majorHeadLst = null;
            if(rcptList != null)
            {
                receiptList = receiptImpl.getReceipts(Long.parseLong(session.getAttribute("userId").toString()),rcptList);
            }
            majorHeadLst = receiptImpl.getMajorHeadList();

            objectArgs.put("receiptList",receiptList);
            objectArgs.put("majorHeadLst",majorHeadLst);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("accChallanFromAB");			
        }
        catch(Exception e)
        {
            objRes.setThrowable(e);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getReceipts # \n"+e);
            e.printStackTrace();
        }
        return objRes;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject accReceiptsFromChqBranch(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

        ReceiptDAOImpl receiptImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());

        HashMap<String,String> majorMap = new HashMap<String, String>();
        majorMap.put("8448", "8448");
        majorMap.put("8449", "8449");
        majorMap.put("8443", "8443");
        majorMap.put("8336", "8336");

        HttpSession session=request.getSession();	
        Map<String,Object> objectArg = new HashMap<String,Object>();

        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }
            String[] receiptDetId = StringUtility.getParameterValues("RecNo", request);
            String[] mjrHead = null;
            
            if(receiptDetId != null && receiptDetId.length > 0)
            {
                mjrHead = new String[receiptDetId.length];
                objectArg.put("RecNos",receiptDetId);
                objectArg.put("userId",Long.parseLong(session.getAttribute("userId").toString()));
                
                for(int i = 0; i < receiptDetId.length; i++)
                    mjrHead[i] = (String)request.getParameter("txtMjrHead_" + receiptDetId[i]);
    
                objectArg.put("mjrHead",mjrHead);
                objectArg.put("majorMap",majorMap);
    
                receiptImpl.accReceiptsFromChqBranch(objectArg,objectArgs,serv.getSessionFactory());
            }
            List<String> postLst = new ArrayList<String>();
            postLst.add(SessionHelper.getPostId(objectArgs).toString());
            objectArgs.put("postLst", postLst);
            List rcptList =WorkFlowHelper.getChallansFromWorkFlow(objectArgs);

            List majorHeadLst = null;
            List receiptList = receiptImpl.getReceipts(Long.parseLong(session.getAttribute("userId").toString()),rcptList);
            majorHeadLst = receiptImpl.getMajorHeadList();

            objectArgs.put("receiptList",receiptList);
            objectArgs.put("majorHeadLst",majorHeadLst);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("accChallanFromAB");			
        }
        catch(Exception e)
        {
            objRes.setThrowable(e);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getReceipts # \n"+e);
            e.printStackTrace();
        }
        return objRes;

    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getReceiptForDist(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }
            HttpSession session=request.getSession();
            ReceiptDAOImpl receImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            PostConfigurationDAOImpl postConfigDAO = new PostConfigurationDAOImpl(ConfigAudSelection.class,serv.getSessionFactory());


            List<String> postLst = new ArrayList<String>();
            postLst.add(SessionHelper.getPostId(objectArgs).toString());
            objectArgs.put("postLst", postLst);
            List rcptList =WorkFlowHelper.getChallansFromWorkFlow(objectArgs);

            List receiptList = null;
            int noOfChallans = 0;
            if(rcptList != null)
            {
                receiptList = receImpl.getChallansForDist(Long.parseLong(session.getAttribute("userId").toString()),rcptList);

                if (receiptList!=null) 
                {
                    Iterator it = receiptList.iterator();				
                    Map<String,String>  map = new HashMap<String,String>();
                    while(it.hasNext()) 
                    {
                        DistChallanVO distVO = (DistChallanVO)it.next();
                        if(distVO!=null)
                        {						
                            map.put("majorHead", distVO.getMajorHead());						
                            distVO.setEmpPostV0(postConfigDAO.getSelectedEmp(SessionHelper.getLocationCode(objectArgs), String.valueOf(DBConstants.BRCH_ID_BOOK_RECEIPT), map));
                        }
                    }
                }
                noOfChallans=receImpl.countChallanDist(Long.parseLong(session.getAttribute("userId").toString()),rcptList);
            }


            objectArgs.put("receiptList",receiptList);
            objectArgs.put("noOfVouchers",noOfChallans);
            objectArgs.put("empsList",postConfigDAO.getEmpsList(SessionHelper.getLocationCode(objectArgs),String.valueOf(DBConstants.BRCH_ID_BOOK_RECEIPT)));
            objRes.setResultValue(objectArgs);
            objRes.setViewName("distChallanForDetPost");	

        }
        catch(Exception e)
        {
            objRes.setThrowable(e);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            e.printStackTrace();
            logger.error("Exception occured in ReceiptServiceImpl.getChallanForDist # \n"+e);
        }
        return objRes;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject distributeReceipts(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        Connection conn = serv.getSessionFactory().getCurrentSession().connection();

        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }

            HttpSession session=request.getSession();
            String majorHeads[] = request.getParameterValues("chkMajorHead");
            Map<String,Long> mjrHdMap = new HashMap<String,Long>();
            Long postId = null;

            if(majorHeads!=null)
            {
                for(int i=0;i<majorHeads.length;i++) 
                {
                    try {mjrHdMap.put(majorHeads[i], new Long(request.getParameter("cmb_" +
                            ""+majorHeads[i])));} catch(Exception e){e.printStackTrace();}
                }
            }

            postId  = SessionHelper.getPostId(objectArgs);
            List<String> postLst = new ArrayList<String>();
            postLst.add(postId.toString());
            objectArgs.put("postLst",postLst);
            List receiptLst = WorkFlowHelper.getChallansFromWorkFlow(objectArgs);

            ReceiptDAOImpl receImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            List rcptList = receImpl.getUndistributedChallan(Long.parseLong(session.getAttribute("userId").toString()),receiptLst);

            if (rcptList!=null && rcptList.size()>0 && majorHeads!=null) 
            {
                Iterator it=rcptList.iterator();
                OrganizationDAOImpl orgDAO = new OrganizationDAOImpl(Object.class,
                        serv.getSessionFactory());				
                while(it.hasNext())
                {
                    TrnReceiptDetails vo=(TrnReceiptDetails)it.next();
                    Map<String,String> map=new HashMap<String,String>();
                    map.put("majorHead", vo.getMajorHead());
                    Long hrchyPostId = mjrHdMap.get(vo.getMajorHead());
                    System.out.println ("hrchyPostId : " + hrchyPostId + " trnVouchVO : " + vo.getReceiptDetailId()+ " ");
                    //System.out.println("VO ID:::::::::::::::::::::: " + vo.getReceiptDetailId());					
                    if (hrchyPostId!=null) 
                    {
                        Long hrchyUserId = orgDAO.getUserByPostId(hrchyPostId);
                        //System.out.println("USER ID : " + hrchyUserId);

                        TrnRcptMvmnt rcptMvnVo=new TrnRcptMvmnt();
                        rcptMvnVo.setReceiptDetailId(vo.getReceiptDetailId());
                        rcptMvnVo.setMvmntStatus("Challan Distributed");
                        rcptMvnVo.setReceivedFlag(new Short("0"));
                        rcptMvnVo.setCreatedUserId(SessionHelper.getUserId(request));
                        rcptMvnVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
                        rcptMvnVo.setCreatedDate(new Date(System.currentTimeMillis()));
                        rcptMvnVo.setUpdatedUserId(SessionHelper.getUserId(request));
                        rcptMvnVo.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
                        rcptMvnVo.setUpdatedDate(new Date(System.currentTimeMillis()));
                        rcptMvnVo.setStatusUpdatedUserid(hrchyUserId);
                        rcptMvnVo.setStatusUpdtPostid(hrchyPostId);
                        rcptMvnVo.setLocationCode(SessionHelper.getLocationCode(objectArgs));
                        rcptMvnVo.setDbId(SessionHelper.getDbId(objectArgs));
                        objectArgs.put("receiptMovementVo", rcptMvnVo);

                        WorkFlowVO workFlowVO = new WorkFlowVO();
                        workFlowVO.setAppMap(objectArgs);			

                        if(SessionHelper.getEmpId(objectArgs)!=null) workFlowVO.setCrtEmpId(SessionHelper.getEmpId(objectArgs).toString());
                        if(SessionHelper.getPostId(objectArgs)!=null) workFlowVO.setCrtPost(SessionHelper.getPostId(objectArgs).toString());
                        if(SessionHelper.getUserId(request)!=null) workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());

                        workFlowVO.setActId(WorkFlowConstants.WF_FORWARD);
                        workFlowVO.setDocId(WorkFlowConstants.CHALLAN_DOCUMENT);
                        workFlowVO.setJobRefId(vo.getReceiptDetailId()+"");
                        workFlowVO.setLocID(SessionHelper.getLocationCode(objectArgs));
                        workFlowVO.setDbId(SessionHelper.getDbId(objectArgs));
                        workFlowVO.setToPost(Long.toString(hrchyPostId));
                        workFlowVO.setFromPost(SessionHelper.getPostId(objectArgs).toString());
                        workFlowVO.setCrtPost(SessionHelper.getPostId(objectArgs).toString());
                        workFlowVO.setCrtEmpId(SessionHelper.getEmpId(objectArgs).toString());
                        workFlowVO.setHierarchyFlag(1);	

                        workFlowVO.setConnection(conn);

                        WorkFlowUtility wfUtility = new WorkFlowUtility();
                        workFlowVO.setAppMap(objectArgs);

                        //System.out.println("BEFORE INVOKE........................");
                        Map returnMap = wfUtility.invokeWorkFlow(workFlowVO);
                        //System.out.println("AFTER INVOKE........................");
                        WorkFlowVO wrkFlw = (WorkFlowVO)returnMap.get("WorkFlowVO");
                    }
                }
                objectArgs.put("msg","Challan distributed Successfully");
            }

            objRes.setResultValue(new HashMap());
            objectArgs.put("MESSAGECODE",(long)1051);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("ajaxData");
        }
        catch(Exception e)
        {
            objRes.setThrowable(e);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            e.printStackTrace();
            logger.error("Exception occured in ReceiptServiceImpl.distributeReceipts # \n"+e);
        }
        return objRes;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getReceiptsForDetPost(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }

            HttpSession session=request.getSession();
            List<String> postLst = new ArrayList<String>();
            postLst.add(SessionHelper.getPostId(objectArgs).toString());
            objectArgs.put("postLst", postLst);
            List rcptList =WorkFlowHelper.getChallansFromWorkFlow(objectArgs);

            ReceiptDAOImpl receImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            List receiptList = null;
            if(rcptList != null)
            {
                receiptList = receImpl.getReceiptsForDetPost(Long.parseLong(session.getAttribute("userId").toString()),rcptList);
            }
            logger.info("\n\n**************Voucher List for Detail Posting***** " + receiptList);
            objectArgs.put("receiptList",receiptList);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("accChallanForDetPost");			
        }
        catch(Exception e)
        {
            objRes.setThrowable(e);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            e.printStackTrace();
            logger.error("Exception occured in ReceiptServiceImpl.getReceiptsForDetPost # \n"+e);
        }
        return objRes;

    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject accReceiptsForDetPost(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }
            HttpSession session=request.getSession();
            Map<String,Object> objectArg = new HashMap<String,Object>();
            objectArg.put("RcptDtId",request.getParameterValues("RcptDtId"));
            objectArg.put("userId",Long.parseLong(session.getAttribute("userId").toString()));

            List<String> postLst = new ArrayList<String>();
            postLst.add(SessionHelper.getPostId(objectArgs).toString());
            objectArgs.put("postLst", postLst);
            List rcptList =WorkFlowHelper.getChallansFromWorkFlow(objectArgs);

            ReceiptDAOImpl receiptImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            receiptImpl.accReceiptsForDetPost(objectArg);
            List receiptList = receiptImpl.getReceiptsForDetPost(Long.parseLong(session.getAttribute("userId").toString()),rcptList);

            logger.info("\n\n**************receipt List***** " + receiptList);
            objectArgs.put("receiptList",receiptList);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("accChallanForDetPost");			
        }
        catch(Exception e)
        {
            objRes.setThrowable(e);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            e.printStackTrace();
            logger.error("Exception occured in ReceiptServiceImpl.accReceiptsForDetPost # \n"+e);
        }
        return objRes;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getReceiptListForDet(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        Integer posted = null;

        try
        { 
            posted = Integer.parseInt(request.getParameter("posted"));
            //System.out.println("Posted in getting == "+ posted);
        }
        catch(Exception ex){}
        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }
            HttpSession session=request.getSession();
            List<String> postLst = new ArrayList<String>();
            postLst.add(SessionHelper.getPostId(objectArgs).toString());
            objectArgs.put("postLst", postLst);
            List rcptList =WorkFlowHelper.getChallansFromWorkFlow(objectArgs);

            ReceiptDAOImpl receiptImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            List receiptList = null;
            if(rcptList != null)
            {
                VerifiedAccountDAOImpl veriAccDAO = new VerifiedAccountDAOImpl(TrnVerifiedAccount.class,serv.getSessionFactory()); 
                Date endDate = veriAccDAO.getEndDateFromVerAcc(SessionHelper.getLocationCode(objectArgs));
                receiptList = receiptImpl.getChallanListForDet(endDate,Long.parseLong(session.getAttribute("userId").toString()),rcptList,posted);
            }
            logger.info("\n\n**************receipt List***** " + receiptList);
            objectArgs.put("receiptList",receiptList);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("challanListForDetPost");			
        }
        catch(Exception e)
        {
            objRes.setThrowable(e);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            e.printStackTrace();
            logger.error("Exception occured in ReceiptServiceImpl.getChallanListForDet # \n"+e);
        }
        return objRes;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject insertUpdateReceipt(Map<String,Object> objectArgs)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        Integer posted = null;

        try{posted = Integer.parseInt(request.getParameter("posted"));}
        catch(Exception ex)
        {
            posted = Integer.parseInt(request.getAttribute("posted").toString());
        }
        try
        {
            if(objectArgs == null || resObj == null )
            {
                resObj.setResultCode(-1);
                return(resObj);
            }
            
            resObj.setResultValue("rcptVO");

            TrnReceiptDetails rcptVO = (TrnReceiptDetails) objectArgs.get("rcptVO");
            ReceiptDAOImpl rcptDAO = new ReceiptDAOImpl(TrnReceiptDetails.class, serv.getSessionFactory());
            Long receiptDetailId = rcptVO.getReceiptDetailId();	
            Integer isRecRev = rcptVO.getRecRev();

            if(receiptDetailId != 0 && receiptDetailId != null)
            {
                TrnReceiptDetails rcptVOForDel = rcptDAO.read(receiptDetailId);
                CmnLookupMstDAOImpl cmnLookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
                List receiptTypes = cmnLookupDAO.getAllChildren("Rcpt_Type");
                String receiptTypeCode = null;
                for (Object key : receiptTypes)
                {
                    CmnLookupMst objCmn = (CmnLookupMst)key;
                    if(objCmn.getLookupName().equalsIgnoreCase(DBConstants.RPT_RCPT_TYPE_CHALLAN))
                        receiptTypeCode = objCmn.getLookupName();
                }
                String receiptCatCode = null;
                try{ receiptCatCode =  rcptVOForDel.getReceiptType().toString();}catch(Exception ex){}

                CommonReceiptDAOImpl crDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
                if(isRecRev == 1)
                    crDAO.delFromRptDtls(receiptDetailId, receiptTypeCode, receiptCatCode, rcptVOForDel.getReceiptNo(), objectArgs);
                else
                {
                    crDAO.delFromRptExpDtls(receiptTypeCode, rcptVOForDel.getReceiptNo(), objectArgs, receiptDetailId);
                }
                crDAO.delFromRcptBudDtls(rcptVOForDel.getReceiptDetailId());
            }

            if(receiptDetailId != 0 && receiptDetailId != null)
                rcptDAO.update(rcptVO);
            else
            {
                receiptDetailId = BptmCommonServiceImpl.getNextSeqNum("trn_receipt_details", objectArgs);
                rcptVO.setReceiptDetailId(receiptDetailId);
                rcptDAO.create(rcptVO);
            }
            if(posted != null && posted == 0)
            { 
                //System.out.println("movement called");
                objectArgs.put("rcptVO", rcptVO);
                insertRcptMvmnt(objectArgs);
            }

            resObj.setResultValue(objectArgs);
        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.saveReceiptDetPost # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject insertBudhdDetails(Map<String,Object> objectArgs)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        CmnLookupMstDAOImpl cmnLookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        
        List receiptTypes = cmnLookupDAO.getAllChildren("Rcpt_Type");
        String receiptCatCode = null;
        for (Object key : receiptTypes)
        {
            CmnLookupMst objCmn = (CmnLookupMst)key;
            if(objCmn.getLookupName().equalsIgnoreCase(DBConstants.RPT_RCPT_TYPE_CHALLAN))
                receiptCatCode = objCmn.getLookupName();
        }

        try
        {
            TrnReceiptDetails rcptVO = (TrnReceiptDetails) objectArgs.get("rcptVO");
            List rcptBudList = (List) objectArgs.get("rcptBudDtls");
            RcptBudDtlsDAOImpl rcptBudDAO = new RcptBudDtlsDAOImpl(TrnRcptBudheadDtls.class, serv.getSessionFactory());

            Long receiptDetailId = null;
            try{receiptDetailId = rcptVO.getReceiptDetailId();}catch(Exception ex){}
            Integer isRecRev = null;
            try{ isRecRev = rcptVO.getRecRev();}catch(Exception ex){}
            Integer tc = null;
            tc = rcptVO.getTc();
            String receiptTypeCode = null;
            try{ receiptTypeCode =  rcptVO.getReceiptType().toString();}catch(Exception ex){}
            ArrayList<RptReceiptDtls> oRptReceitpList = new ArrayList<RptReceiptDtls>();

            for(int i=0;i<rcptBudList.size();i++)
            {
                TrnRcptBudheadDtls rcptbudVO=(TrnRcptBudheadDtls) rcptBudList.get(i);
                Long budheadId=BptmCommonServiceImpl.getNextSeqNum("trn_rcpt_budhead_dtls", objectArgs);

                rcptbudVO.setReceiptDetailId(receiptDetailId);
                rcptbudVO.setRcptBudId(budheadId);
                rcptbudVO.setLocationCode(SessionHelper.getLocationCode(objectArgs));
                rcptbudVO.setDbId(SessionHelper.getDbId(objectArgs));
                rcptBudDAO.create(rcptbudVO);
                if(isRecRev == 1 && isRecRev != null)
                {
                    RptReceiptDtls oRdtl = new RptReceiptDtls();
                    BigDecimal rcptDetId = null;
                    BigDecimal amount = null; 
                    String fundTypeCode = null;
                    Integer scheme = null;

                    try{ rcptDetId = new BigDecimal(receiptDetailId);}catch(Exception ex){}
                    try{ amount = rcptbudVO.getAmount();}catch(Exception ex){}
                    try{ fundTypeCode = rcptVO.getFund().toString();}catch(Exception ex){}
                    try{ scheme = Integer.parseInt(rcptVO.getSchemeNo());}catch(Exception ex){}

                    oRdtl.setRcptNo(new BigDecimal(budheadId));
                    oRdtl.setTrnReceiptId(rcptDetId);
                    oRdtl.setMjrHd(rcptbudVO.getBudMajorHead());
                    oRdtl.setMinHd(rcptbudVO.getBudMinHead());
                    oRdtl.setSubMjrHd(rcptbudVO.getBudSubmjrHead());
                    oRdtl.setSubHd(rcptbudVO.getBudSubHead());
                    oRdtl.setObjHd(rcptbudVO.getBudObjectCode());
                    oRdtl.setDtlHd(rcptbudVO.getBudDetailHead());
                    oRdtl.setEdpCode(rcptbudVO.getBudEdpCode());
                    oRdtl.setAmount(amount);
                    oRdtl.setChallanCatgCode(receiptTypeCode);
                    oRdtl.setTsryCode(rcptVO.getLocationCode());
                    oRdtl.setDdoCode(rcptVO.getDdoCode());
                    oRdtl.setFundTypeCode(fundTypeCode);
                    oRdtl.setDemandNo(rcptVO.getDemand());
                    oRdtl.setScheme(scheme);
                    oRdtl.setDedctnType('O');
                    if(tc != null && tc == 1)
                        receiptCatCode = "TcBill";
                    oRdtl.setRcptTypeCode(receiptCatCode);
                    oRdtl.setRcptStatusCode("Approved");
                    try { oRdtl.setRcptStatusDate(new Date(System.currentTimeMillis())); } catch(Exception ex){}
                    try { oRdtl.setRevenueDt(rcptVO.getRcvdByBankDate()); } catch(Exception ex){ex.printStackTrace();}
                    oRptReceitpList.add(oRdtl);
                }
                else if(isRecRev != null)
                {
                    ArrayList<RptExpEdpDtls> expEdpList = new ArrayList<RptExpEdpDtls>();
                    RptExpenditureDtls rptExpVO = new RptExpenditureDtls();
                    RptExpEdpDtls rptExpEdpVO = new RptExpEdpDtls();
                    BigDecimal expEdpId = null;
                    String expEdpTypeCode = null;
                    String fundTypeCode = null;
                    String budTypeCode = null;
                    Integer scheme = null;

                    expEdpTypeCode = receiptCatCode.toString();
                    try{ fundTypeCode = rcptVO.getFund().toString();}catch(Exception ex){}
                    try{ budTypeCode = rcptVO.getBudType().toString();}catch(Exception ex){}
                    try{ scheme = Integer.parseInt(rcptVO.getSchemeNo());}catch(Exception ex){}
                    try{ expEdpId = new BigDecimal(receiptDetailId);}catch(Exception ex){}

                    rptExpVO.setTsryCode(rcptVO.getLocationCode());
                    rptExpVO.setDdoCode(rcptVO.getDdoCode());
                    rptExpVO.setExpNo(new BigDecimal(budheadId));
                    rptExpVO.setExpTypeCode(expEdpTypeCode);
                    try{rptExpVO.setClsExpCode(rcptVO.getClsExp().toString());}catch(Exception ex){}
                    rptExpVO.setFundTypeCode(fundTypeCode);
                    rptExpVO.setBudTypeCode(budTypeCode);
                    rptExpVO.setDemandNo(rcptVO.getDemand());
                    rptExpVO.setScheme(scheme);
                    rptExpVO.setMjrHd(rcptVO.getMajorHead());
                    rptExpVO.setMinHd(rcptbudVO.getBudMinHead());
                    rptExpVO.setSubMjrHd(rcptbudVO.getBudSubmjrHead());
                    rptExpVO.setSubHd(rcptbudVO.getBudSubHead());
                    rptExpVO.setDtlHd(rcptbudVO.getBudDetailHead());
                    rptExpVO.setExpDt(rcptVO.getRcvdByBankDate());
                    rptExpVO.setExpStatusCode(DBConstants.ST_DTL_PSTNG_DONE);
                    try{rptExpVO.setExpStatusDt(new Date(System.currentTimeMillis()));}catch(Exception ex){}
                    //expRcptList.add(rptExpVO);
                    rptExpEdpVO.setObjHd(rcptbudVO.getBudObjectCode());
                    rptExpEdpVO.setEdpHd(rcptbudVO.getBudEdpCode());
                    rptExpEdpVO.setEdpType('1');
                    rptExpEdpVO.setAmount(rcptbudVO.getAmount());
                    rptExpEdpVO.setTrnExpEdpId(expEdpId);
                    expEdpList.add(rptExpEdpVO);
                    objectArgs.put("RptExpenditureVO",rptExpVO);
                    objectArgs.put("RptExpEdpList",expEdpList);
                    Map rMap = insertExpDetails(objectArgs);

                }
            }
            if(isRecRev == 1 && isRecRev != null)
            {
                objectArgs.put("rptReceiptList",oRptReceitpList);
                Map rMap = insertChallanDtls(objectArgs);
            }
            
            Integer posted = null;
            try{posted = Integer.parseInt(request.getAttribute("posted").toString());}catch(Exception ex){}
            if(posted != null && posted == 0)
            { return resObj;}
            
            resObj.setResultValue(new HashMap());
            objectArgs.put("MESSAGECODE",(long)1048);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("ajaxData");
        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.insertBudhdDetails # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getBankBranchDetail(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        //System.out.println("Inside ajax");
        String ajaxString = "";
        Long langId = SessionHelper.getLangId(objectArgs);

        try
        {
            if(objectArgs == null || resObj == null)
            {
                resObj.setResultCode(-1);
                return resObj;
            }
            String branchCode1 = request.getParameter("txtBranchCode");
            List bankBranchList = null;
            String bankName = null;
            String branchName = null;
            String bankCode = null;
            String branchCode = null;

            if(branchCode1 != null && !branchCode1.equals(""))
            {
                CommonReceiptDAOImpl validateDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
                bankBranchList = validateDAO.validateBankBranch(branchCode1,langId,SessionHelper.getLocationCode(objectArgs));
            }
            if(bankBranchList != null)
            {
                Iterator itrBankBranch = bankBranchList.iterator();
                while(itrBankBranch.hasNext())
                {
                    Object[] rowObj = (Object[]) itrBankBranch.next();
                    bankName = rowObj[0].toString();
                    branchName = rowObj[1].toString();
                    bankCode = rowObj[2].toString();
                    branchCode = rowObj[3].toString();
                }
            }	
            ajaxString= new AjaxXmlBuilder().addItem("id_txtBank", bankName).addItem("id_txtBranch",branchName)
            .addItem("id_hidtxtBank", bankCode).addItem("id_hidtxtBranch",branchCode).toString();

            System.out.print(" Ajax String is  ::::: "+ ajaxString);
            objectArgs.put("ajaxKey", ajaxString);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("ajaxData");
        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getBankBranchDetail # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject validateMajorHeadDetail(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        String ajaxString=null;

        try
        {
            if(objectArgs == null || resObj == null)
            {
                resObj.setResultCode(-1);
                return resObj;
            }

            String majorHead = request.getParameter("txtMajorHead1").toString();
            String subMajorHead = request.getParameter("txtSubMajorHead1").toString();
            String minorHead = request.getParameter("txtMinorHead1").toString();
            String subHead = request.getParameter("txtSubHead1").toString();
            CommonReceiptDAOImpl validateDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());

            boolean validateFlag = validateDAO.validateMajorHeadDetail(majorHead, subMajorHead, minorHead, subHead);

            if(validateFlag == true)
            {
                ajaxString= new AjaxXmlBuilder().addItem("id_hidValidateMajorDtls","true").toString();
            }
            else
            {
                ajaxString= new AjaxXmlBuilder().addItem("id_hidValidateMajorDtls","false").toString();
            }

            //System.out.println(ajaxString);
            objectArgs.put("ajaxKey", ajaxString);
            objectArgs.put("validateFlag", validateFlag);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("ajaxData");

        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.validateMajorHeadDetail # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject loadReceiptDetail(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

        String locCode = SessionHelper.getLocationCode(objectArgs);
        List ddoNames = new ArrayList();
        List ddoCardex = new ArrayList();
        List ddoDemand = new ArrayList();
        List challanTypes = new ArrayList();
        List fund = new ArrayList();
        List ClassOfExp = new ArrayList();
        List BudgetType = new ArrayList();
        Long receiptId = null;
        Integer Tc = null;
        Integer isRecRev = null;

        try{receiptId = new Long(request.getParameter("receiptId"));}catch(Exception ex){}
        try{ isRecRev = new Integer(request.getParameter("isRecRev"));}catch(Exception ex){}
        try
        {
            CommonReceiptDAOImpl receiptDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
            ReceiptDAOImpl rcptDAO = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            if(receiptId != null)
            {
                TrnReceiptDetails rcptVO = receiptDAO.getReceiptDetail(receiptId);
                objectArgs.put("rcptDtlsVO", rcptVO);

                List rcptBudhdDtlsList = receiptDAO.getReceiptBudHeadDetail(receiptId);
                objectArgs.put("rcptBudhdDtlsList", rcptBudhdDtlsList);

                String bankCode = rcptVO.getBankCode();
                String branchCode = rcptVO.getBankBranchCode();
                MstBank bankVO = new MstBank();
                RltBankBranch branchVO = new RltBankBranch();
                if(bankCode != null && branchCode != null)
                {
                    bankVO = receiptDAO.getBankName(bankCode);
                    bankVO.setBankCode(bankCode);
                    branchVO = receiptDAO.getBranchName(branchCode,locCode);
                    branchVO.setBranchCode(new Long(branchCode));
                }
                objectArgs.put("bankVO", bankVO);  
                objectArgs.put("branchVO", branchVO);

                Tc = rcptVO.getTc();
                TrnVoucherDetails voucherVO = new TrnVoucherDetails();
                if(Tc != null && Tc == 1)
                {
                    voucherVO = rcptDAO.getVoucherMjrhead(receiptId);
                }
                objectArgs.put("voucherVO", voucherVO);
            }
            if(locCode != null && locCode != "")
            {
                ddoNames = receiptDAO.getDdoNameDetails(locCode);
            }

            BillCommonDAOImpl billDAO = new BillCommonDAOImpl(serv.getSessionFactory());
            SgvcFinYearMst finDAO = billDAO.getFinYrInfo(new Date(System.currentTimeMillis()), SessionHelper.getLangId(objectArgs));
            ddoDemand = receiptDAO.getCardexDemandDtls(finDAO.getFinYearId());

            CmnLookupMstDAOImpl cmnLookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
            challanTypes = cmnLookupDAO.getAllChildrenByLookUpNameAndLang("Challan_Catg", SessionHelper.getLangId(objectArgs));
            fund = cmnLookupDAO.getAllChildrenByLookUpNameAndLang("Fund",SessionHelper.getLangId(objectArgs));
            ClassOfExp = cmnLookupDAO.getAllChildrenByLookUpNameAndLang("ClassOfExp",SessionHelper.getLangId(objectArgs));
            BudgetType = cmnLookupDAO.getAllChildrenByLookUpNameAndLang("BudjetType", SessionHelper.getLangId(objectArgs));

            objectArgs.put("ddoNames", ddoNames);
            objectArgs.put("ddoCardex", ddoCardex);
            objectArgs.put("ddoDemand", ddoDemand);
            objectArgs.put("fund",fund);
            objectArgs.put("ClassOfExp",ClassOfExp);
            objectArgs.put("challanTypes", challanTypes);
            objectArgs.put("BudjetType",BudgetType);


            resObj.setResultValue(objectArgs);

            String actionFlag = request.getParameter("actionFlag");
            //System.out.println("action Flag = " + actionFlag);
            if(isRecRev != null)
            {
                if(isRecRev == 1)
                {
                    resObj.setViewName("challanDetailPosting");
                }
                else
                {
                    resObj.setViewName("challanDetailPostingPAO");
                }
            }
            else
            {
                resObj.setViewName(actionFlag);
            }
            //resObj.setViewName(actionFlag);

        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getReceiptDetail # \n"+ex);
            ex.printStackTrace();
        }

        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject loadSubTreasuryEntry(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

        String locCode = SessionHelper.getLocationCode(objectArgs);
        List ddoNames = new ArrayList();
        List ddoCardex = new ArrayList();
        List ddoDemand = new ArrayList();
        List challanTypes = new ArrayList();
        List fund = new ArrayList();
        List ClassOfExp = new ArrayList();
        List BudgetType = new ArrayList();
        Long receiptId = null;
        Integer Tc = null;

        try{receiptId = new Long(request.getParameter("receiptId"));}catch(Exception ex){}
        try
        {
            CommonReceiptDAOImpl receiptDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
            ReceiptDAOImpl rcptDAO = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            if(receiptId != null)
            {
                TrnReceiptDetails rcptVO = receiptDAO.getReceiptDetail(receiptId);
                objectArgs.put("rcptDtlsVO", rcptVO);

                List rcptBudhdDtlsList = receiptDAO.getReceiptBudHeadDetail(receiptId);
                objectArgs.put("rcptBudhdDtlsList", rcptBudhdDtlsList);

                String bankCode = rcptVO.getBankCode();
                String branchCode = rcptVO.getBankBranchCode();
                MstBank bankVO = new MstBank();
                RltBankBranch branchVO = new RltBankBranch();
                if(bankCode != null && branchCode != null)
                {
                    bankVO = receiptDAO.getBankName(bankCode);
                    bankVO.setBankCode(bankCode);
                    branchVO = receiptDAO.getBranchName(branchCode,locCode);
                    branchVO.setBranchCode(new Long(branchCode));
                }
                objectArgs.put("bankVO", bankVO);  
                objectArgs.put("branchVO", branchVO);

                Tc = rcptVO.getTc();
                TrnVoucherDetails voucherVO = new TrnVoucherDetails();
                if(Tc != null && Tc == 1)
                {
                    voucherVO = rcptDAO.getVoucherMjrhead(receiptId);
                }
                objectArgs.put("voucherVO", voucherVO);
            }
            if(locCode != null && locCode != "")
            {
                ddoNames = receiptDAO.getDdoNameDetails(locCode);
            }

            BillCommonDAOImpl billDAO = new BillCommonDAOImpl(serv.getSessionFactory());
            SgvcFinYearMst finDAO = billDAO.getFinYrInfo(new Date(System.currentTimeMillis()), SessionHelper.getLangId(objectArgs));
            ddoDemand = receiptDAO.getCardexDemandDtls(finDAO.getFinYearId());

            CmnLookupMstDAOImpl cmnLookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
            challanTypes = cmnLookupDAO.getAllChildrenByLookUpNameAndLang("Challan_Catg", SessionHelper.getLangId(objectArgs));
            fund = cmnLookupDAO.getAllChildrenByLookUpNameAndLang("Fund",SessionHelper.getLangId(objectArgs));
            ClassOfExp = cmnLookupDAO.getAllChildrenByLookUpNameAndLang("ClassOfExp",SessionHelper.getLangId(objectArgs));
            BudgetType = cmnLookupDAO.getAllChildrenByLookUpNameAndLang("BudjetType", SessionHelper.getLangId(objectArgs));

            List trsyList = receiptDAO.getSubTreasury(SessionHelper.getLocationCode(objectArgs));

            objectArgs.put("ddoNames", ddoNames);
            objectArgs.put("ddoCardex", ddoCardex);
            objectArgs.put("ddoDemand", ddoDemand);
            objectArgs.put("fund",fund);
            objectArgs.put("ClassOfExp",ClassOfExp);
            objectArgs.put("challanTypes", challanTypes);
            objectArgs.put("BudjetType",BudgetType);
            objectArgs.put("trsyList",trsyList);

            resObj.setResultValue(objectArgs);

            String actionFlag = request.getParameter("actionFlag");
            //System.out.println("action Flag = " + actionFlag);

            resObj.setViewName("subTreasuryRcptEntry");

        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getReceiptDetail # \n"+ex);
            ex.printStackTrace();
        }

        return resObj;
    }
    
    public ResultObject loadOnlineChallan(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

        String locCode = SessionHelper.getLocationCode(objectArgs);
        Long receiptId = null;
        
        try{receiptId = new Long(request.getParameter("receiptId"));}catch(Exception ex){}
        try
        {
            CommonReceiptDAOImpl receiptDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
            if(receiptId != null)
            {
                WebChallanDtlsDAO  webRcptDAO = new WebChallanDtlsDAO(WebChallanDetails.class,serv.getSessionFactory());
                WebPurposeDtlsDAO webPurDAO = new WebPurposeDtlsDAO(WebPurposeDetails.class,serv.getSessionFactory());
                WebChallanDetails webRcptVO = new WebChallanDetails();
                webRcptVO = webRcptDAO.read(receiptId.toString());
                
                TrnReceiptDetails rcptVO = new TrnReceiptDetails();
                rcptVO.setReceiptNo(webRcptVO.getWcdChallanId());
                rcptVO.setBankBranchCode(webRcptVO.getWcdBankCode());
                try{rcptVO.setAmount(new BigDecimal(webRcptVO.getWcdTotalAmount()));}catch(Exception ex){}
                rcptVO.setDepositorName(webRcptVO.getWcdDlrName());
                rcptVO.setBankCode(receiptDAO.getBankCode(webRcptVO.getWcdBankCode()));
                rcptVO.setReceiptType(webRcptVO.getWcdTaxType());
                rcptVO.setIndentNo(webRcptVO.getWcdRegNo());
                try{rcptVO.setTinNo(Long.parseLong(webRcptVO.getWcdCinNo()));}catch(Exception ex){}
                
                rcptVO.setReceiptDate(webRcptVO.getPmtEffctDate());
                rcptVO.setRcvdByBankDate(webRcptVO.getPmtEffctDate());
                
                Integer fromDate = Integer.parseInt(webRcptVO.getWcdAssmntPeriod().toString());
                Integer toDate = Integer.parseInt(webRcptVO.getWcdAssmntYear().toString());
                Map<String,Date> fromToDateMap = receiptDAO.getFromToDateList(fromDate,toDate);
                rcptVO.setFromPeriod((Date)fromToDateMap.get("fromDate"));
                rcptVO.setToPeriod((Date)fromToDateMap.get("toDate"));
                
                List rcptBudhdDtlsList = receiptDAO.getonlineChallanPurpose(receiptId.toString(),serv.getSessionFactory(),objectArgs,SessionHelper.getUserId(request));
                objectArgs.put("rcptDtlsVO", rcptVO);
                objectArgs.put("rcptBudhdDtlsList", rcptBudhdDtlsList);

                if(rcptBudhdDtlsList != null && rcptBudhdDtlsList.size() > 0)
                {
                  TrnRcptBudheadDtls vo = (TrnRcptBudheadDtls)rcptBudhdDtlsList.get(0);
                  rcptVO.setMajorHead(vo.getBudMajorHead());
                  
                }  
                
                String bankCode = rcptVO.getBankCode();
                String branchCode = rcptVO.getBankBranchCode();
                MstBank bankVO = new MstBank();
                RltBankBranch branchVO = new RltBankBranch();
                if(bankCode != null && branchCode != null)
                {
                    bankVO = receiptDAO.getBankName(bankCode);
                    bankVO.setBankCode(bankCode);
                    branchVO = receiptDAO.getBranchName(branchCode,locCode);
                    branchVO.setBranchCode(new Long(branchCode));
                }
                objectArgs.put("bankVO", bankVO);  
                objectArgs.put("branchVO", branchVO);
            }
            resObj.setResultValue(objectArgs);
            resObj.setViewName("onlineChallanDetDisp");

        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getReceiptDetail # \n"+ex);
            ex.printStackTrace();
        }

        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getDdoNames(Map<String,Object> objectArgs)
    {

        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        String majorHead = request.getParameter("txtMajorHead1").toString();
        String challanType = request.getParameter("challanType");

        //System.out.println(majorHead);
        //System.out.println(challanType);
        String ajaxString=null;
        try
        {
            CommonReceiptDAOImpl receiptDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
            List resList = new ArrayList();
            //System.out.println(majorHead);
            String locCode = SessionHelper.getLocationCode(objectArgs);
            if(challanType!= null && majorHead != "" && majorHead == "Mjr8009")
            {				
                resList = receiptDAO.getDdoNameDetails(locCode);
                ajaxString= new AjaxXmlBuilder().addItems(resList,"ddoName","ddoCode").toString();
            }
            else
            {
                ajaxString= new AjaxXmlBuilder().addItem(null,"id_cmbDdoName").toString();
            }
            //System.out.println(ajaxString);
            objectArgs.put("ajaxKey", ajaxString);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("ajaxData");
        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getDdoNames # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getDdoCardexNumbers(Map<String,Object> objectArgs)
    {

        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        String ddoCode = request.getParameter("cmbDdoName").toString();
        //System.out.println("Cardex called");
        String ajaxString = null;
        try
        {
            CommonReceiptDAOImpl receiptDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
            List resList = new ArrayList();
            //System.out.println(ddoCode);
            if(ddoCode != null || ddoCode != "")
            {				
                resList = receiptDAO.getCardexNo(ddoCode);
                ajaxString= new AjaxXmlBuilder().addItems(resList,"cardexNo","cardexNo").toString();
            }
            else
            {
                ajaxString= new AjaxXmlBuilder().addItem("---select---","id_cmbCardex").toString();
            }
            //System.out.println(ajaxString);
            objectArgs.put("ajaxKey", ajaxString);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("ajaxData");
        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getDdoCardexNumbers # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getCardexDemand(Map<String, Object> objectArgs)
    {

        HttpServletRequest request = (HttpServletRequest) objectArgs
        .get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        String cardexNo = request.getParameter("cmbCardex").toString();
        String ajaxString = "";
        //System.out.println("Inside demand");
        try
        {
            // CommonReceiptDAOImpl receiptDAO = new
            // CommonReceiptDAOImpl(SgvaBudsubhdMst.class,
            // serv.getSessionFactory());
            List resList = new ArrayList();

            if (cardexNo != "" || cardexNo != null)
            {
                new BudHdDAOImpl(serv.getSessionFactory());

                // resList = receiptDAO.getCardexDemandDtls(cardexNo);
                ajaxString = new AjaxXmlBuilder().addItems(resList, "demandId",
                "demandId").toString();
            }
            else
            {
                ajaxString = new AjaxXmlBuilder().addItem(null, "id_cmbDemand")
                .toString();
            }
            //System.out.println(ajaxString);
            objectArgs.put("ajaxKey", ajaxString);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("ajaxData");
        }
        catch (Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger
            .error("Exception occured in ReceiptServiceImpl.getCardexDemand # \n"
                    + ex);
            ex.printStackTrace();
        }
        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject validateMajorHead(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        String ajaxString=null;
        try
        {
            if(objectArgs == null || resObj == null)
            {
                resObj.setResultCode(-1);
                return resObj;
            }
            //System.out.println("Inside service validate major");
            String majorHead = request.getParameter("txtMajorHead1").toString();
            CommonReceiptDAOImpl validateDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
            boolean validateFlag = validateDAO.validateMajorHead(majorHead);
            if(validateFlag == true)
            {
                ajaxString= new AjaxXmlBuilder().addItem("id_hidValidateMajorDtls","1").toString();
            }
            else
            {
                ajaxString= new AjaxXmlBuilder().addItem("id_hidValidateMajorDtls","0").toString();
            }
            //System.out.println("val flag"+validateFlag);
            System.out.print(" Ajax String is  ::::: "+ ajaxString);
            objectArgs.put("ajaxKey", ajaxString);
            objectArgs.put("validateFlag", validateFlag);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("ajaxData");

        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.validateMajorHead # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getTinPartyName(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS,"FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        Integer tinNo = null;
        try{tinNo = Integer.parseInt(request.getParameter("txtPartyTinno"));}catch(Exception ex){}
        String ajaxString=null;
        try
        {
            if(objectArgs == null || resObj == null)
            {
                resObj.setResultCode(-1);
                return resObj;
            }
            if(tinNo != null)
            {
                CommonReceiptDAOImpl validateDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
                Iterator itrPartyName = validateDAO.getPartyName(tinNo).iterator();
                if(itrPartyName.hasNext())
                {

                }
                ajaxString= new AjaxXmlBuilder().addItem("id_txtPartyName","true").toString();
            }
            objectArgs.put("ajaxKey", ajaxString);
        }
        catch(Exception ex)
        {

            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getTinPartyName # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getSearchReceipt(Map<String,Object> objectArgs) 
    {
        //System.out.println("Inside service");
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        String searchBy=request.getParameter("searchby");
        String searchVal=request.getParameter("txtSearch");
        String viewPage=request.getParameter("viewPage");
        //System.out.println("view name ="+viewPage);

        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }

            Long userId = SessionHelper.getUserId(request);

            Long postId  = SessionHelper.getPostId(objectArgs);
            List<String> postLst = new ArrayList<String>();
            postLst.add(postId.toString());
            objectArgs.put("postLst",postLst);
            //System.out.println("postLst " + objectArgs.get("postLst"));
            List billLst = WorkFlowHelper.getChallansFromWorkFlow(objectArgs);

            Map<String,Object> map=new HashMap<String,Object>();
            map.put("viewPage", viewPage);
            map.put("searchBy", searchBy);
            map.put("searchValue", searchVal);
            map.put("userId",userId);

            ReceiptDAOImpl receiptImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());	
            List receiptList = receiptImpl.getSearchChallan(map, billLst);

            objectArgs.put("receiptList", receiptList);
            objRes.setResultValue(objectArgs);
            objRes.setViewName(viewPage);

        } 
        catch(Exception e)
        {
            List<String> messageList=new ArrayList<String>();
            messageList.add("Error in Receipt Searching.");
            objectArgs.put(MessageConstant.MESSAGE_LIST,messageList);
            objRes.setResultValue(objectArgs);
            objRes.setResultCode(-1);
            objRes.setViewName("messageResult");
            e.printStackTrace();
            logger.error("Exception occured in ReceiptServiceImpl.getSearchReceipt # \n"+e);
        }
        return objRes;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject createReceiptFromWF(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        try 
        {
            ReceiptDAOImpl trnRcptDAO = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            List challanList = (List) objectArgs.get("trnBsRcptEntries");
            if(challanList != null)
            {
                for(int i = 0;i < challanList.size(); i++)
                {
                    TrnBsRcptEntries trnBsRcptEntries = (TrnBsRcptEntries) challanList.get(i);
                    TrnReceiptDetails trnRcptVO = new TrnReceiptDetails();
                    trnRcptVO.setMajorHead(trnBsRcptEntries.getMajorHead());
                    trnRcptVO.setAmount(trnBsRcptEntries.getAmount());
                    trnRcptVO.setDepositorName(trnBsRcptEntries.getDepositorName());
                    trnRcptVO.setTrnCounter(new Integer(1));
                    trnRcptVO.setCreatedPostId(trnBsRcptEntries.getCreatedPostId());
                    trnRcptVO.setCreatedUserId(trnBsRcptEntries.getCreatedUserId());
                    trnRcptVO.setCreatedDate(new Date(System.currentTimeMillis()));
                    trnRcptVO.setDbId(trnBsRcptEntries.getDbId());
                    trnRcptVO.setLocationCode(trnBsRcptEntries.getLocationCode());
                    trnRcptVO.setReceiptNo(trnRcptDAO.getNextRcptNo(SessionHelper.getLocationCode(objectArgs)).toString());
                    trnRcptVO.setReceiptType(null);
                    trnRcptVO.setRcvdByBankDate((Date) objectArgs.get("receivedByBank"));
                    //Long seqNo = trnRcptDAO.getNextRcptId(objectArgs);
                    Long seqNo = BptmCommonServiceImpl.getNextSeqNum("trn_receipt_details", objectArgs);
                    trnRcptVO.setReceiptDetailId(seqNo);
                    trnRcptDAO.create(trnRcptVO);

                    objectArgs.put("trnReceiptDetailsVO", trnRcptVO);			

                    WorkFlowVO workFlowVO = new WorkFlowVO();			
                    workFlowVO.setAppMap(objectArgs);
                    workFlowVO.setDocId(WorkFlowConstants.CHALLAN_DOCUMENT);
                    workFlowVO.setActId(WorkFlowConstants.WF_CREATE);
                    workFlowVO.setConnection(serv.getSessionFactory().getCurrentSession().connection());
                    OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
                    Map resultMap = orgUtil.getHierarchyByPostIDAndDescription(SessionHelper.getPostId(objectArgs).toString(), "Challan Processing");
                    List resultList = (List)resultMap.get("Result");
                    Long lStrHeirRefId = new Long(0);
                    if(resultList!= null && resultList.size()>0)
                    {
                        lStrHeirRefId = Long.parseLong(resultList.get(0).toString());
                    }

                    logger.info(" Heirarchy found is........... "  + lStrHeirRefId);
                    workFlowVO.setHirRefId(lStrHeirRefId);
                    workFlowVO.setJobRefId(trnRcptVO.getReceiptDetailId()+"");
                    workFlowVO.setCrtEmpId(SessionHelper.getEmpId(objectArgs).toString());
                    workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
                    workFlowVO.setCrtPost(SessionHelper.getPostId(objectArgs).toString());
                    workFlowVO.setLocID(SessionHelper.getLocationCode(objectArgs));
                    workFlowVO.setDbId(SessionHelper.getDbId(objectArgs));
                    WorkFlowUtility wfUtility = new WorkFlowUtility();
                    wfUtility.invokeWorkFlow(workFlowVO);

                }
            }
        }
        catch (Exception e)
        {
            objRes.setThrowable(e);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            e.printStackTrace();
            logger.error("Exception occured in ReceiptServiceImpl.createReceiptFromWF # \n"+e);
        }
        return null;
    }

    /**
     * @param objectArgs
     * @return Map
     */
    public Map insertReceiptDetails(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        WorkFlowVO  wfVO = (WorkFlowVO)objectArgs.get("WorkFlowVO");
        Map<String,Object> serviceMap = wfVO.getAppMap();
        HttpServletRequest request = (HttpServletRequest) serviceMap.get("requestObj");
        ServiceLocator serv = (ServiceLocator)serviceMap.get("serviceLocator");

        try 
        {
            TrnReceiptDetails trnRcptVO = (TrnReceiptDetails) serviceMap.get("trnReceiptDetailsVO");
            TrnRcptMvmnt rcptMvnVo=new TrnRcptMvmnt();

            rcptMvnVo.setReceivedUserId(SessionHelper.getUserId(request));
            rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));
            rcptMvnVo.setMvmntStatus("Challan Created");
            rcptMvnVo.setReceivedFlag(new Short("0"));
            rcptMvnVo.setCreatedPostId(SessionHelper.getPostId(serviceMap));
            rcptMvnVo.setCreatedUserId(SessionHelper.getUserId(request));
            rcptMvnVo.setCreatedDate(new Date(System.currentTimeMillis()));
            rcptMvnVo.setUpdatedUserId(SessionHelper.getUserId(request));
            rcptMvnVo.setUpdatedPostId(SessionHelper.getPostId(serviceMap));
            rcptMvnVo.setUpdatedDate(new Date(System.currentTimeMillis()));
            rcptMvnVo.setMovemntId(1);
            rcptMvnVo.setReceiptDetailId(trnRcptVO.getReceiptDetailId());
            rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));
            rcptMvnVo.setStatusUpdatedUserid(SessionHelper.getUserId(request));
            rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));

            FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
            FrmServiceMst servDetails = servDetailsImpl.readService("CREATE_CHALLANMVMNT_SRVC");

            serviceMap.put("receiptMovementVo", rcptMvnVo);
            serv.executeService(servDetails,serviceMap);
            wfVO.setAppMap(serviceMap);			
            serviceMap.put("WorkFlowVO", wfVO);						
        }
        catch(Exception ex) 
        {
            objRes.setThrowable(ex);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.insertReceiptDetails # \n"+ex);
            ex.printStackTrace();
        }
        return serviceMap;
    }

    /**
     * @param objectArgs
     * @return Map
     */
    public Map insertReceiptMovmentWF(Map<String,Object> objectArgs)
    {
        WorkFlowVO wfVO = (WorkFlowVO)objectArgs.get("WorkFlowVO");
        Map appMap = wfVO.getAppMap();
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator)appMap.get("serviceLocator");

        try
        {
            Long lLngCaseID = BptmCommonServiceImpl.getNextSeqNum("trn_rcpt_mvmnt", appMap);
            //System.out.println("Receipt ID >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: " +lLngCaseID);
            ChallanMovementDAOImpl rcptDAO = new ChallanMovementDAOImpl(TrnRcptMvmnt.class,serv.getSessionFactory());			
            TrnRcptMvmnt trnMvmntVO = (TrnRcptMvmnt)appMap.get("receiptMovementVo");
            //System.out.println("trnMvmntVO : " + trnMvmntVO);
            trnMvmntVO.setRcptMvmntId(lLngCaseID);			
            trnMvmntVO.setMovemntId(rcptDAO.getmaxMovementId(trnMvmntVO.getReceiptDetailId()));			
            rcptDAO.create(trnMvmntVO);

        }
        catch(Exception ex)
        {
            objRes.setThrowable(ex);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.insertReceiptMovmentWF # \n"+ex);
            ex.printStackTrace();
        }
        return objectArgs;
    }

    /**
     * @param objectArgs
     */
    public void createTCChallanDoc(Map<String,Object> objectArgs) 
    {		
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        try 
        {
            Long receiptDetailId = (Long) objectArgs.get("receiptDetailId");
            //System.out.println("receiptDetailId : " + receiptDetailId);
            if (receiptDetailId!=null) 
            {
                ReceiptDAOImpl rcptDAO = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
                TrnReceiptDetails rcptVO = rcptDAO.read(receiptDetailId);
                objectArgs.put("trnReceiptDetailsVO", rcptVO);
                WorkFlowVO workFlowVO = new WorkFlowVO();			
                workFlowVO.setAppMap(objectArgs);
                workFlowVO.setDocId(WorkFlowConstants.CHALLAN_DOCUMENT);
                workFlowVO.setActId(WorkFlowConstants.WF_CREATE);
                workFlowVO.setConnection(serv.getSessionFactory().getCurrentSession().connection());
                WorkFlowDAOImpl workFlowDAO = new WorkFlowDAOImpl();
                OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
                long hierachyRefId = workFlowDAO.getHierarchyRefIdByDocAndLoc(SessionHelper.getLocationCode(objectArgs),WorkFlowConstants.CHALLAN_DOCUMENT,serv.getSessionFactory());
                //System.out.println("-----------------" + hierachyRefId);
                Map wfMap = (Map) orgUtil.getLowestPostInHierarchy(hierachyRefId);
                //System.out.println("xxxxxxxxxxxxxxxxx " + ((Object[])((List)wfMap.get("Result")).iterator().next())[0] + "------------- " + SessionHelper.getUserId(request));
                //System.out.println("before ");
                String postId = (String)((Object[])((List)wfMap.get("Result")).iterator().next())[0];
                //System.out.println("after " + postId);				
                logger.info(" Heirarchy found is.......xxxxxx.... "  + postId);
                //System.out.println("xxxxxxxxxxxxxxxxxxxxx ----------- " + hierachyRefId);	
                workFlowVO.setHirRefId(hierachyRefId);
                workFlowVO.setJobRefId(receiptDetailId.toString());
                workFlowVO.setCrtEmpId(SessionHelper.getEmpId(objectArgs).toString());
                workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
                workFlowVO.setCrtPost(postId);
                workFlowVO.setLocID(SessionHelper.getLocationCode(objectArgs));
                workFlowVO.setDbId(SessionHelper.getDbId(objectArgs));
                workFlowVO.setToPost(postId);
                workFlowVO.setFromPost(postId);
                WorkFlowUtility wfUtility = new WorkFlowUtility();
                wfUtility.invokeWorkFlow(workFlowVO);

                TrnRcptMvmnt rcptMvnVo=new TrnRcptMvmnt();
                rcptMvnVo.setReceivedUserId(SessionHelper.getUserId(request));
                rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));
                rcptMvnVo.setMvmntStatus("Challan Created");
                rcptMvnVo.setReceivedFlag(new Short("0"));
                rcptMvnVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
                rcptMvnVo.setCreatedUserId(SessionHelper.getUserId(request));
                rcptMvnVo.setCreatedDate(new Date(System.currentTimeMillis()));
                rcptMvnVo.setUpdatedUserId(SessionHelper.getUserId(request));
                rcptMvnVo.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
                rcptMvnVo.setUpdatedDate(new Date(System.currentTimeMillis()));
                rcptMvnVo.setMovemntId(1);
                rcptMvnVo.setReceiptDetailId(rcptVO.getReceiptDetailId());
                rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));
                rcptMvnVo.setStatusUpdatedUserid(new Long(postId));
                rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));
                objectArgs.put("receiptMovementVo", rcptMvnVo);
                objectArgs.put("WorkFlowVO", workFlowVO);						
            }
        }
        catch(Exception ex) 
        {
            objRes.setThrowable(ex);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.createTCChallanDoc # \n"+ex);
            ex.printStackTrace();

        }
    }

    /**
     * @param objectArgs
     * @return Map
     */
    public Map insertChallanDtls(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        List expEdpList =(List) objectArgs.get("rptReceiptList");

        try
        {
            DssDataServiceImpl servImpl = new DssDataServiceImpl();
            Map<String,Object> dssMap=new HashMap<String,Object>();
            dssMap.put("map",objectArgs);
            dssMap.put("RptReceiptVOArrLst",expEdpList);	

            Map returnMap=servImpl.insertReceiptData(dssMap);

            return returnMap;
        } 
        catch(Exception ex)
        {
            objRes.setThrowable(ex);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in VoucherServiceImpl.insertEdpDtls # \n"+ex);
            ex.printStackTrace();

        }
        return null;
    }

    /**
     * @param objectArgs
     * @return Map
     */
    public Map insertExpDetails(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        RptExpenditureDtls expVO= (RptExpenditureDtls) objectArgs.get("RptExpenditureVO");
        List expEdpList= (List) objectArgs.get("RptExpEdpList");

        try
        {
            DssDataServiceImpl servImpl = new DssDataServiceImpl();
            Map<String,Object> dssMap=new HashMap<String,Object>();
            dssMap.put("map",objectArgs);
            dssMap.put("RptExpenditureVO",expVO);	
            dssMap.put("RptExpEdpVOArrLst", expEdpList);

            Map returnMap=servImpl.insertExpData(dssMap);

            return returnMap;
        } 
        catch(Exception ex)
        {
            objRes.setThrowable(ex);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in VoucherServiceImpl.insertEdpDtls # \n"+ex);
            ex.printStackTrace();

        }
        return null;
    }

    /**
     * @param objectArgs
     * @param expNo
     * @param expTypeCode
     * @return Map
     */
    public Map insertExpEdpDetails(Map<String,Object> objectArgs, BigDecimal expNo, String expTypeCode) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        List expEdpList= (List) objectArgs.get("RptExpEdpList");

        try
        {
            DssDataServiceImpl servImpl = new DssDataServiceImpl();
            Map<String,Object> dssMap=new HashMap<String,Object>();
            dssMap.put("map",objectArgs);
            dssMap.put("RptExpEdpVOArrLst",expEdpList);	
            dssMap.put("expNo",expNo);
            dssMap.put("expTypeCode",expTypeCode);

            Map returnMap=servImpl.insertExpEdpData(dssMap);

            return returnMap;
        } 
        catch(Exception ex)
        {
            objRes.setThrowable(ex);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in VoucherServiceImpl.insertEdpDtls # \n"+ex);
            ex.printStackTrace();

        }
        return null;
    }

    /**
     * @param objectArgs
     */
    public void insertRcptMvmnt(Map<String,Object> objectArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        TrnReceiptDetails trnRcptVO = (TrnReceiptDetails) objectArgs.get("rcptVO");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        TrnRcptMvmnt rcptMvnVo = new TrnRcptMvmnt();
        TrnRcptMvmntDAOImpl rcptMvnDAO = new TrnRcptMvmntDAOImpl(TrnRcptMvmnt.class, serv.getSessionFactory());
        ChallanMovementDAOImpl rcptDAO = new ChallanMovementDAOImpl(TrnRcptMvmnt.class,serv.getSessionFactory());
        Long mvmntId = BptmCommonServiceImpl.getNextSeqNum("trn_rcpt_mvmnt", objectArgs);

        try
        {
            rcptMvnVo.setRcptMvmntId(mvmntId);
            rcptMvnVo.setReceivedUserId(SessionHelper.getUserId(request));
            rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));
            rcptMvnVo.setMvmntStatus(DBConstants.ST_DTL_PSTNG_DONE);
            rcptMvnVo.setReceivedFlag(new Short("1"));
            rcptMvnVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
            rcptMvnVo.setCreatedUserId(SessionHelper.getUserId(request));
            rcptMvnVo.setCreatedDate(new Date(System.currentTimeMillis()));
            rcptMvnVo.setUpdatedUserId(SessionHelper.getUserId(request));
            rcptMvnVo.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
            rcptMvnVo.setUpdatedDate(new Date(System.currentTimeMillis()));
            rcptMvnVo.setMovemntId(rcptDAO.getmaxMovementId(trnRcptVO.getReceiptDetailId()));
            rcptMvnVo.setReceiptDetailId(trnRcptVO.getReceiptDetailId());
            rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));
            rcptMvnVo.setStatusUpdatedUserid(SessionHelper.getUserId(request));
            rcptMvnVo.setStatusUpdtPostid(SessionHelper.getPostId(objectArgs));
            rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));
            rcptMvnVo.setLocationCode(SessionHelper.getLocationCode(objectArgs));
            rcptMvnVo.setDbId(SessionHelper.getDbId(objectArgs));

            rcptMvnDAO.create(rcptMvnVo);
        }
        catch(Exception ex)
        {
            objRes.setThrowable(ex);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.insertRcptMvmnt # \n"+ex);
            ex.printStackTrace();

        }
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject sendReceiptBack(Map<String,Object> objectArgs)
    {	
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }

            BptmCommonServicesDAOImpl bptmDAO = new BptmCommonServicesDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
            String rcptDetId[] = request.getParameterValues("rcptListForBack");
            if(rcptDetId != null)
            {
                try
                {
                    for(int i=0 ;i < rcptDetId.length; i++)
                    {
                        TrnRcptMvmnt rcptMvnVo = new TrnRcptMvmnt();
                        rcptMvnVo.setReceivedUserId(SessionHelper.getUserId(request));
                        rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));
                        rcptMvnVo.setMvmntStatus(DBConstants.ST_CHALLAN_GEN);
                        rcptMvnVo.setReceivedFlag(new Short("0"));
                        rcptMvnVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
                        rcptMvnVo.setCreatedUserId(SessionHelper.getUserId(request));
                        rcptMvnVo.setCreatedDate(new Date(System.currentTimeMillis()));
                        rcptMvnVo.setUpdatedUserId(SessionHelper.getUserId(request));
                        rcptMvnVo.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
                        rcptMvnVo.setUpdatedDate(new Date(System.currentTimeMillis()));

                        rcptMvnVo.setReceiptDetailId(Long.parseLong(rcptDetId[i].toString()));
                        rcptMvnVo.setStatusUpdatedDate(new Date(System.currentTimeMillis()));
                        rcptMvnVo.setLocationCode(SessionHelper.getLocationCode(objectArgs));
                        rcptMvnVo.setDbId(SessionHelper.getDbId(objectArgs));

                        WorkFlowVO workFlowVO = new WorkFlowVO();
                        workFlowVO.setAppMap(objectArgs);			
                        workFlowVO.setCrtEmpId(SessionHelper.getEmpId(objectArgs).toString());
                        workFlowVO.setCrtPost(SessionHelper.getPostId(objectArgs).toString());
                        workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
                        workFlowVO.setActId(WorkFlowConstants.WF_RETURN);
                        workFlowVO.setDocId(WorkFlowConstants.CHALLAN_DOCUMENT);
                        workFlowVO.setJobRefId(rcptDetId[i]);
                        workFlowVO.setLocID(SessionHelper.getLocationCode(objectArgs));
                        workFlowVO.setDbId(SessionHelper.getDbId(objectArgs));

                        Connection conn = serv.getSessionFactory().getCurrentSession().connection();
                        workFlowVO.setConnection(conn);


                        OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
                        String toPost = orgUtil.getReturnPost(workFlowVO);
                        String toUsr = bptmDAO.getUserIdFromPost(toPost, SessionHelper.getLangId(objectArgs));


                        rcptMvnVo.setStatusUpdatedUserid(Long.parseLong(toUsr));
                        rcptMvnVo.setStatusUpdtPostid(Long.parseLong(toPost));
                        objectArgs.put("receiptMovementVo", rcptMvnVo);

                        WorkFlowUtility wfUtility = new WorkFlowUtility();
                        workFlowVO.setAppMap(objectArgs);
                        //System.out.println("BEFORE INVOKE........................");
                        Map returnMap = wfUtility.invokeWorkFlow(workFlowVO);
                        //System.out.println("AFTER INVOKE........................");
                        WorkFlowVO wrkFlw = (WorkFlowVO)returnMap.get("WorkFlowVO");
                    }
                }
                catch(Exception ex)
                {
                    objRes.setThrowable(ex);
                    objRes.setResultCode(-1);
                    objRes.setViewName("errorPage");
                    logger.error("Exception occured in ReceiptServiceImpl.sendReceiptBack # \n"+ex);
                    ex.printStackTrace();
                }
            }

            HttpSession session=request.getSession();
            Integer posted = null;

            try
            { 
                posted = Integer.parseInt(request.getParameter("posted"));
                //System.out.println("Posted in getting == "+ posted);
            }
            catch(Exception ex){posted = 0;}

            List<String> postLst = new ArrayList<String>();
            List receiptList = null;
            postLst.add(SessionHelper.getPostId(objectArgs).toString());
            objectArgs.put("postLst", postLst);
            List rcptList =WorkFlowHelper.getChallansFromWorkFlow(objectArgs);

            ReceiptDAOImpl receiptImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            if(rcptList != null)
            {
                VerifiedAccountDAOImpl veriAccDAO = new VerifiedAccountDAOImpl(TrnVerifiedAccount.class,serv.getSessionFactory()); 
                Date endDate = veriAccDAO.getEndDateFromVerAcc(SessionHelper.getLocationCode(objectArgs));
                receiptList = receiptImpl.getChallanListForDet(endDate,Long.parseLong(session.getAttribute("userId").toString()),rcptList,posted);
            }
            logger.info("\n\n**************receipt List***** " + receiptList);
            objectArgs.put("receiptList",receiptList);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("challanListForDetPost");	
        }
        catch(Exception ex)
        {			
            objRes.setThrowable(ex);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.sendReceiptBack # \n"+ex);
            ex.printStackTrace();
        }

        return objRes;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getVendorDetails(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        //System.out.println("Inside ajax");
        String ajaxString = "";

        try
        {
            if(objectArgs == null || resObj == null)
            {
                resObj.setResultCode(-1);
                return resObj;
            }
            String vendorCode = request.getParameter("txtVendorCode");
            List vendorList = null;
            String vendorName = null;
            String vendorRegNo = null;
            if(vendorCode != null && !vendorCode.equals(""))
            {
                CommonReceiptDAOImpl cmnRcptDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
                vendorList = cmnRcptDAO.getVendorDetailByCode(vendorCode);
            }
            if(vendorList != null)
            {
                Iterator itrVendorDet = vendorList.iterator();
                while(itrVendorDet.hasNext())
                {
                    Object[] rowObj = (Object[]) itrVendorDet.next();
                    vendorName = rowObj[0].toString();
                    vendorRegNo = rowObj[1].toString();

                }
            }
            ajaxString= new AjaxXmlBuilder().addItem("id_txtVendorName", vendorName).addItem("id_txtRegNo",vendorRegNo).toString();
            //System.out.println("Ajax String " + ajaxString);
            objectArgs.put("ajaxKey", ajaxString);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("ajaxData");
        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getBankBranchDetail # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject receiptListForSubTreasury(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }
            ReceiptDAOImpl receiptImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());

            List receiptList = null;
            receiptList = receiptImpl.getReceiptsforSubTreasury(String.valueOf(SessionHelper.getPostId(objectArgs)));

            objectArgs.put("receiptList",receiptList);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("subTrsryPostedChallanList");			
        }
        catch(Exception e)
        {
            objRes.setThrowable(e);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.receiptListForSubTreasury # \n"+e);
            e.printStackTrace();
        }
        return objRes;
    }

    /**
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject recordReceiptForSubTreasury(Map<String,Object> objectArgs) 
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

        try
        {
            if (objRes==null || objectArgs == null )
            {				
                objRes.setResultCode(-1);
                return objRes; 
            }
            ReceiptDAOImpl receiptImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            List receiptList = null;

            VerifiedAccountDAOImpl veriAccDAO = new VerifiedAccountDAOImpl(TrnVerifiedAccount.class,serv.getSessionFactory()); 
            Date endDate = veriAccDAO.getEndDateFromVerAcc(SessionHelper.getLocationCode(objectArgs));

            List<String> postLst = new ArrayList<String>();
            postLst.add(SessionHelper.getPostId(objectArgs).toString());
            objectArgs.put("postLst", postLst);
            List rcptList =WorkFlowHelper.getChallansFromWorkFlow(objectArgs);

            receiptList = receiptImpl.getRecordReceiptsforSubTreasury(endDate,rcptList,SessionHelper.getPostId(objectArgs).toString(),SessionHelper.getLocationCode(objectArgs).toString());

            objectArgs.put("receiptList",receiptList);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("subTrsyRecordChallanList");			
        }
        catch(Exception e)
        {
            objRes.setThrowable(e);
            objRes.setResultCode(-1);
            objRes.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.receiptListForSubTreasury # \n"+e);
            e.printStackTrace();
        }
        return objRes;
    }
    
    public ResultObject getLcAccDetail(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        //System.out.println("Inside ajax");
        String ajaxString = "";

        try
        {
            if(objectArgs == null || resObj == null)
            {
                resObj.setResultCode(-1);
                return resObj;
            }
            String lcAccCode = request.getParameter("txtLciAccCode");
            Long langId = SessionHelper.getLangId(objectArgs);
            String lcAccDesc = null;
            
            if(lcAccCode != null && !lcAccCode.equals(""))
            {
                CommonReceiptDAOImpl cmnRcptDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
                lcAccDesc = cmnRcptDAO.getLcAccDetailByCode(lcAccCode,langId);
            }
            
            ajaxString= new AjaxXmlBuilder().addItem("id_txtLciAccDesc",lcAccDesc).toString();
            //System.out.println("Ajax String " + ajaxString);
            objectArgs.put("ajaxKey", ajaxString);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("ajaxData");
        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getLcAccDetail # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }
    
    public ResultObject getOnlineChallanList(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        
        try
        {
            if(objectArgs == null || resObj == null)
            {
                resObj.setResultCode(-1);
                return resObj;
            }
            List receiptList = null;
            
            ReceiptDAOImpl receiptImpl = new ReceiptDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
            receiptList = receiptImpl.getOnlineChallan();

            objectArgs.put("receiptList",receiptList);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("onlineChallanList"); 
        }
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.getOnlineChallanList # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }
    
    public ResultObject accOnlineChallan(Map<String,Object> objectArgs)
    {
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

        WebChallanDtlsDAO  webRcptDAO = new WebChallanDtlsDAO(WebChallanDetails.class,serv.getSessionFactory()); 
        WebPurposeDtlsDAO webPurDAO = new WebPurposeDtlsDAO(WebPurposeDetails.class,serv.getSessionFactory());
        CommonReceiptDAOImpl receiptDAO = new CommonReceiptDAOImpl(SgvaBudsubhdMst.class, serv.getSessionFactory());
        
        try
        {
            String[] challlanId = StringUtility.getParameterValues("RcptDtId", request);
            if(challlanId.length > 0 && challlanId != null)
            {
                for(int i = 0; i < challlanId.length; i++)
                {
                        WebChallanDetails webRcptVO = new WebChallanDetails();
                        webRcptVO = webRcptDAO.read(challlanId[i]);
                 
                        
                        TrnReceiptDetails rcptVO = new TrnReceiptDetails();
                        
                        rcptVO.setReceiptNo(webRcptVO.getWcdChallanId());
                        rcptVO.setBankBranchCode(webRcptVO.getWcdBankCode());
                        rcptVO.setBankCode(receiptDAO.getBankCode(webRcptVO.getWcdBankCode()));
                        try{rcptVO.setAmount(new BigDecimal(webRcptVO.getWcdTotalAmount()));}catch(Exception ex){}
                        rcptVO.setDepositorName(webRcptVO.getWcdDlrName());
                        rcptVO.setReceiptType(webRcptVO.getWcdTaxType());
                        rcptVO.setIndentNo(webRcptVO.getWcdRegNo());
                        try{rcptVO.setTinNo(Long.parseLong(webRcptVO.getWcdCinNo()));}catch(Exception ex){}
                     
                        rcptVO.setLocationCode(SessionHelper.getLocationCode(objectArgs));
                        rcptVO.setDbId(SessionHelper.getDbId(objectArgs));
                        rcptVO.setCreatedDate(new Date(System.currentTimeMillis()));
                        rcptVO.setCreatedPostId(SessionHelper.getPostId(objectArgs));
                        rcptVO.setCreatedUserId(SessionHelper.getUserId(request));
                        rcptVO.setTrnCounter(new Integer(1));
                        rcptVO.setRecRev(new Integer(1));
                        rcptVO.setTc(new Integer(0));
                        try { rcptVO.setRcvdByBankDate(webRcptVO.getPmtEffctDate()); } catch(Exception ex){}
                        
                        request.setAttribute("posted", new Integer(0));
                        objectArgs.put("requestObj",request);
                        objectArgs.put("rcptVO",rcptVO);
                        
                        
                        List rcptBudhdDtlsList = receiptDAO.getonlineChallanPurpose(challlanId[i],serv.getSessionFactory(),objectArgs,SessionHelper.getUserId(request));
                        Integer fromDate = Integer.parseInt(webRcptVO.getWcdAssmntPeriod().toString());
                        Integer toDate = Integer.parseInt(webRcptVO.getWcdAssmntYear().toString());
                        Map<String,Date> fromToDateMap = receiptDAO.getFromToDateList(fromDate,toDate);
                        rcptVO.setFromPeriod(fromToDateMap.get("fromDate"));
                        rcptVO.setToPeriod(fromToDateMap.get("toDate"));
                        if(rcptBudhdDtlsList != null && rcptBudhdDtlsList.size() > 0)
                        {
                          TrnRcptBudheadDtls vo = (TrnRcptBudheadDtls)rcptBudhdDtlsList.get(0);
                          rcptVO.setMajorHead(vo.getBudMajorHead());
                          
                        }  
                        resObj = insertUpdateReceipt(objectArgs);
                        objectArgs.put("rcptBudDtls",rcptBudhdDtlsList);
                        objectArgs.put("rcptVO",rcptVO);
                        resObj = insertBudhdDetails(objectArgs);
                        
                        webRcptVO.setWcdApproved(true);
                        webRcptVO.setWcdChallanId(challlanId[i]);
                        webRcptDAO.update(webRcptVO);
                  
                }
            }
            resObj.setResultValue(new HashMap());
            objectArgs.put("MESSAGECODE",(long)1048);
            resObj.setResultValue(objectArgs);
            resObj.setViewName("ajaxData");
            
        }        
        catch(Exception ex)
        {
            resObj.setThrowable(ex);
            resObj.setResultCode(-1);
            resObj.setViewName("errorPage");
            logger.error("Exception occured in ReceiptServiceImpl.accOnlineChallan # \n"+ex);
            ex.printStackTrace();
        }
        return resObj;
    }

}
